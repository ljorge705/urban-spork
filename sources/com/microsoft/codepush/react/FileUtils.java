package com.microsoft.codepush.react;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* loaded from: classes2.dex */
public class FileUtils {
    private static final int WRITE_BUFFER_SIZE = 8192;

    public static void copyDirectoryContents(String str, String str2) throws Throwable {
        BufferedInputStream bufferedInputStream;
        FileOutputStream fileOutputStream;
        File file = new File(str);
        File file2 = new File(str2);
        if (!file2.exists()) {
            file2.mkdir();
        }
        for (File file3 : file.listFiles()) {
            if (file3.isDirectory()) {
                copyDirectoryContents(CodePushUtils.appendPathComponent(str, file3.getName()), CodePushUtils.appendPathComponent(str2, file3.getName()));
            } else {
                File file4 = new File(file2, file3.getName());
                byte[] bArr = new byte[8192];
                FileInputStream fileInputStream = null;
                try {
                    FileInputStream fileInputStream2 = new FileInputStream(file3);
                    try {
                        bufferedInputStream = new BufferedInputStream(fileInputStream2);
                        try {
                            fileOutputStream = new FileOutputStream(file4);
                            while (true) {
                                try {
                                    int i = bufferedInputStream.read(bArr);
                                    if (i > 0) {
                                        fileOutputStream.write(bArr, 0, i);
                                    } else {
                                        try {
                                            break;
                                        } catch (IOException e) {
                                            throw new CodePushUnknownException("Error closing IO resources.", e);
                                        }
                                    }
                                } catch (Throwable th) {
                                    th = th;
                                    fileInputStream = fileInputStream2;
                                    if (fileInputStream != null) {
                                        try {
                                            fileInputStream.close();
                                        } catch (IOException e2) {
                                            throw new CodePushUnknownException("Error closing IO resources.", e2);
                                        }
                                    }
                                    if (bufferedInputStream != null) {
                                        bufferedInputStream.close();
                                    }
                                    if (fileOutputStream != null) {
                                        fileOutputStream.close();
                                    }
                                    throw th;
                                }
                            }
                            fileInputStream2.close();
                            bufferedInputStream.close();
                            fileOutputStream.close();
                        } catch (Throwable th2) {
                            th = th2;
                            fileOutputStream = null;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        bufferedInputStream = null;
                        fileOutputStream = null;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    bufferedInputStream = null;
                    fileOutputStream = null;
                }
            }
        }
    }

    public static void deleteDirectoryAtPath(String str) {
        if (str == null) {
            CodePushUtils.log("deleteDirectoryAtPath attempted with null directoryPath");
            return;
        }
        File file = new File(str);
        if (file.exists()) {
            deleteFileOrFolderSilently(file);
        }
    }

    public static void deleteFileAtPathSilently(String str) {
        deleteFileOrFolderSilently(new File(str));
    }

    public static void deleteFileOrFolderSilently(File file) {
        if (file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                if (file2.isDirectory()) {
                    deleteFileOrFolderSilently(file2);
                } else {
                    file2.delete();
                }
            }
        }
        if (file.delete()) {
            return;
        }
        CodePushUtils.log("Error deleting file " + file.getName());
    }

    public static boolean fileAtPathExists(String str) {
        return new File(str).exists();
    }

    public static void moveFile(File file, String str, String str2) {
        File file2 = new File(str);
        if (!file2.exists()) {
            file2.mkdirs();
        }
        File file3 = new File(str, str2);
        if (!file.renameTo(file3)) {
            throw new CodePushUnknownException("Unable to move file from " + file.getAbsolutePath() + " to " + file3.getAbsolutePath() + ".");
        }
    }

    public static String readFileToString(String str) throws Throwable {
        BufferedReader bufferedReader;
        Throwable th;
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(new File(str));
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
                try {
                    StringBuilder sb = new StringBuilder();
                    while (true) {
                        String line = bufferedReader.readLine();
                        if (line != null) {
                            sb.append(line).append("\n");
                        } else {
                            String string = sb.toString();
                            bufferedReader.close();
                            fileInputStream.close();
                            return string;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                bufferedReader = null;
                th = th3;
            }
        } catch (Throwable th4) {
            bufferedReader = null;
            th = th4;
            fileInputStream = null;
        }
    }

    private static String validateFileName(String str, File file) throws IOException {
        String str2 = file.getCanonicalPath() + File.separator;
        String canonicalPath = new File(str2, str).getCanonicalPath();
        if (canonicalPath.startsWith(str2)) {
            return canonicalPath;
        }
        throw new IllegalStateException("File is outside extraction target directory.");
    }

    public static void unzipFile(File file, String str) throws Throwable {
        BufferedInputStream bufferedInputStream;
        FileInputStream fileInputStream;
        ZipInputStream zipInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                bufferedInputStream = new BufferedInputStream(fileInputStream);
                try {
                    ZipInputStream zipInputStream2 = new ZipInputStream(bufferedInputStream);
                    try {
                        File file2 = new File(str);
                        if (file2.exists()) {
                            deleteFileOrFolderSilently(file2);
                        }
                        file2.mkdirs();
                        byte[] bArr = new byte[8192];
                        while (true) {
                            ZipEntry nextEntry = zipInputStream2.getNextEntry();
                            if (nextEntry != null) {
                                File file3 = new File(validateFileName(nextEntry.getName(), file2));
                                if (nextEntry.isDirectory()) {
                                    file3.mkdirs();
                                } else {
                                    File parentFile = file3.getParentFile();
                                    if (!parentFile.exists()) {
                                        parentFile.mkdirs();
                                    }
                                    FileOutputStream fileOutputStream = new FileOutputStream(file3);
                                    while (true) {
                                        try {
                                            int i = zipInputStream2.read(bArr);
                                            if (i == -1) {
                                                break;
                                            } else {
                                                fileOutputStream.write(bArr, 0, i);
                                            }
                                        } finally {
                                        }
                                    }
                                }
                                long time = nextEntry.getTime();
                                if (time > 0) {
                                    file3.setLastModified(time);
                                }
                            } else {
                                try {
                                    zipInputStream2.close();
                                    bufferedInputStream.close();
                                    fileInputStream.close();
                                    return;
                                } catch (IOException e) {
                                    throw new CodePushUnknownException("Error closing IO resources.", e);
                                }
                            }
                        }
                    } catch (Throwable th) {
                        th = th;
                        zipInputStream = zipInputStream2;
                        if (zipInputStream != null) {
                            try {
                                zipInputStream.close();
                            } catch (IOException e2) {
                                throw new CodePushUnknownException("Error closing IO resources.", e2);
                            }
                        }
                        if (bufferedInputStream != null) {
                            bufferedInputStream.close();
                        }
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                bufferedInputStream = null;
            }
        } catch (Throwable th4) {
            th = th4;
            bufferedInputStream = null;
            fileInputStream = null;
        }
    }

    public static void writeStringToFile(String str, String str2) throws Throwable {
        PrintWriter printWriter;
        PrintWriter printWriter2 = null;
        try {
            printWriter = new PrintWriter(str2);
        } catch (Throwable th) {
            th = th;
        }
        try {
            printWriter.print(str);
            printWriter.close();
        } catch (Throwable th2) {
            th = th2;
            printWriter2 = printWriter;
            if (printWriter2 != null) {
                printWriter2.close();
            }
            throw th;
        }
    }
}
