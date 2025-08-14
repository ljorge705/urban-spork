package com.reactnativecommunity.asyncstorage;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes6.dex */
public class AsyncStorageExpoMigration {
    static final String LOG_TAG = "AsyncStorageExpoMigration";

    public static void migrate(Context context) throws Throwable {
        if (isAsyncStorageDatabaseCreated(context)) {
            return;
        }
        ArrayList<File> expoDatabases = getExpoDatabases(context);
        File lastModifiedFile = getLastModifiedFile(expoDatabases);
        if (lastModifiedFile == null) {
            Log.v(LOG_TAG, "No scoped database found");
            return;
        }
        try {
            ReactDatabaseSupplier.getInstance(context).get();
            copyFile(new FileInputStream(lastModifiedFile), new FileOutputStream(context.getDatabasePath(ReactDatabaseSupplier.DATABASE_NAME)));
            Log.v(LOG_TAG, "Migrated most recently modified database " + lastModifiedFile.getName() + " to RKStorage");
            try {
                Iterator<File> it = expoDatabases.iterator();
                while (it.hasNext()) {
                    File next = it.next();
                    if (next.delete()) {
                        Log.v(LOG_TAG, "Deleted scoped database " + next.getName());
                    } else {
                        Log.v(LOG_TAG, "Failed to delete scoped database " + next.getName());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.v(LOG_TAG, "Completed the scoped AsyncStorage migration");
        } catch (Exception e2) {
            Log.v(LOG_TAG, "Failed to migrate scoped database " + lastModifiedFile.getName());
            e2.printStackTrace();
        }
    }

    private static boolean isAsyncStorageDatabaseCreated(Context context) {
        return context.getDatabasePath(ReactDatabaseSupplier.DATABASE_NAME).exists();
    }

    private static ArrayList<File> getExpoDatabases(Context context) {
        ArrayList<File> arrayList = new ArrayList<>();
        try {
            File[] fileArrListFiles = context.getDatabasePath("noop").getParentFile().listFiles();
            if (fileArrListFiles != null) {
                for (File file : fileArrListFiles) {
                    if (file.getName().startsWith("RKStorage-scoped-experience-") && !file.getName().endsWith("-journal")) {
                        arrayList.add(file);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    private static File getLastModifiedFile(ArrayList<File> arrayList) {
        File file = null;
        if (arrayList.size() == 0) {
            return null;
        }
        Iterator<File> it = arrayList.iterator();
        long j = -1;
        while (it.hasNext()) {
            File next = it.next();
            long lastModifiedTimeInMillis = getLastModifiedTimeInMillis(next);
            if (lastModifiedTimeInMillis > j) {
                file = next;
                j = lastModifiedTimeInMillis;
            }
        }
        return file != null ? file : arrayList.get(0);
    }

    private static long getLastModifiedTimeInMillis(File file) {
        try {
            if (Build.VERSION.SDK_INT >= 26) {
                return getLastModifiedTimeFromBasicFileAttrs(file);
            }
            return file.lastModified();
        } catch (Exception e) {
            e.printStackTrace();
            return -1L;
        }
    }

    private static long getLastModifiedTimeFromBasicFileAttrs(File file) {
        try {
            return Files.readAttributes(file.toPath(), BasicFileAttributes.class, new LinkOption[0]).creationTime().toMillis();
        } catch (Exception unused) {
            return -1L;
        }
    }

    private static void copyFile(FileInputStream fileInputStream, FileOutputStream fileOutputStream) throws Throwable {
        FileChannel fileChannel;
        FileChannel channel = null;
        try {
            FileChannel channel2 = fileInputStream.getChannel();
            try {
                channel = fileOutputStream.getChannel();
                channel2.transferTo(0L, channel2.size(), channel);
                if (channel2 != null) {
                    try {
                        channel2.close();
                    } finally {
                        if (channel != null) {
                            channel.close();
                        }
                    }
                }
            } catch (Throwable th) {
                th = th;
                FileChannel fileChannel2 = channel;
                channel = channel2;
                fileChannel = fileChannel2;
                if (channel != null) {
                    try {
                        channel.close();
                    } finally {
                        if (fileChannel != null) {
                            fileChannel.close();
                        }
                    }
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            fileChannel = null;
        }
    }
}
