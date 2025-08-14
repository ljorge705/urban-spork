package com.ReactNativeBlobUtil;

import android.content.res.AssetFileDescriptor;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.StatFs;
import androidx.work.Data;
import com.facebook.common.util.UriUtil;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import io.sentry.rrweb.RRWebVideoEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes5.dex */
class ReactNativeBlobUtilFS {
    private DeviceEventManagerModule.RCTDeviceEventEmitter emitter;
    private ReactApplicationContext mCtx;

    ReactNativeBlobUtilFS(ReactApplicationContext reactApplicationContext) {
        this.mCtx = reactApplicationContext;
        this.emitter = (DeviceEventManagerModule.RCTDeviceEventEmitter) reactApplicationContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class);
    }

    static boolean writeFile(String str, String str2, String str3, boolean z) throws Throwable {
        FileOutputStream fileOutputStream;
        try {
            File file = new File(ReactNativeBlobUtilUtils.normalizePath(str));
            File parentFile = file.getParentFile();
            if (!file.exists() && ((parentFile != null && !parentFile.exists() && !parentFile.mkdirs() && !parentFile.exists()) || !file.createNewFile())) {
                return false;
            }
            if (str2.equalsIgnoreCase(ReactNativeBlobUtilConst.DATA_ENCODE_URI)) {
                File file2 = new File(ReactNativeBlobUtilUtils.normalizePath(str3));
                if (!file2.exists()) {
                    return false;
                }
                byte[] bArr = new byte[Data.MAX_DATA_BYTES];
                FileInputStream fileInputStream = null;
                try {
                    FileInputStream fileInputStream2 = new FileInputStream(file2);
                    try {
                        fileOutputStream = new FileOutputStream(file, z);
                        while (true) {
                            try {
                                int i = fileInputStream2.read(bArr);
                                if (i > 0) {
                                    fileOutputStream.write(bArr, 0, i);
                                } else {
                                    fileInputStream2.close();
                                    fileOutputStream.close();
                                    return true;
                                }
                            } catch (Throwable th) {
                                th = th;
                                fileInputStream = fileInputStream2;
                                if (fileInputStream != null) {
                                    fileInputStream.close();
                                }
                                if (fileOutputStream != null) {
                                    fileOutputStream.close();
                                }
                                throw th;
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        fileOutputStream = null;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    fileOutputStream = null;
                }
            } else {
                byte[] bArrStringToBytes = ReactNativeBlobUtilUtils.stringToBytes(str3, str2);
                FileOutputStream fileOutputStream2 = new FileOutputStream(file, z);
                try {
                    fileOutputStream2.write(bArrStringToBytes);
                    int length = bArrStringToBytes.length;
                    return true;
                } finally {
                    fileOutputStream2.close();
                }
            }
        } catch (FileNotFoundException | Exception unused) {
            return false;
        }
    }

    static void writeFile(String str, String str2, String str3, boolean z, boolean z2, Promise promise) {
        int length;
        FileOutputStream fileOutputStream;
        try {
            File file = new File(str);
            File parentFile = file.getParentFile();
            if (!file.exists()) {
                if (parentFile != null && !parentFile.exists() && !parentFile.mkdirs() && !parentFile.exists()) {
                    promise.reject("EUNSPECIFIED", "Failed to create parent directory of '" + str + "'");
                    return;
                } else if (!file.createNewFile()) {
                    promise.reject("ENOENT", "File '" + str + "' does not exist and could not be created");
                    return;
                }
            }
            if (str2.equalsIgnoreCase(ReactNativeBlobUtilConst.DATA_ENCODE_URI)) {
                String strNormalizePath = ReactNativeBlobUtilUtils.normalizePath(str3);
                File file2 = new File(strNormalizePath);
                if (!file2.exists()) {
                    promise.reject("ENOENT", "No such file '" + str + "' ('" + strNormalizePath + "')");
                    return;
                }
                byte[] bArr = new byte[Data.MAX_DATA_BYTES];
                FileInputStream fileInputStream = null;
                try {
                    FileInputStream fileInputStream2 = new FileInputStream(file2);
                    try {
                        fileOutputStream = new FileOutputStream(file, z2);
                        length = 0;
                        while (true) {
                            try {
                                int i = fileInputStream2.read(bArr);
                                if (i <= 0) {
                                    break;
                                }
                                fileOutputStream.write(bArr, 0, i);
                                length += i;
                            } catch (Throwable th) {
                                th = th;
                                fileInputStream = fileInputStream2;
                                if (fileInputStream != null) {
                                    fileInputStream.close();
                                }
                                if (fileOutputStream != null) {
                                    fileOutputStream.close();
                                }
                                throw th;
                            }
                        }
                        fileInputStream2.close();
                        fileOutputStream.close();
                    } catch (Throwable th2) {
                        th = th2;
                        fileOutputStream = null;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    fileOutputStream = null;
                }
            } else {
                byte[] bArrStringToBytes = ReactNativeBlobUtilUtils.stringToBytes(str3, str2);
                if (z) {
                    if (ReactNativeBlobUtilFileTransformer.sharedFileTransformer == null) {
                        throw new IllegalStateException("Write file with transform was specified but the shared file transformer is not set");
                    }
                    bArrStringToBytes = ReactNativeBlobUtilFileTransformer.sharedFileTransformer.onWriteFile(bArrStringToBytes);
                }
                FileOutputStream fileOutputStream2 = new FileOutputStream(file, z2);
                try {
                    fileOutputStream2.write(bArrStringToBytes);
                    length = bArrStringToBytes.length;
                } finally {
                    fileOutputStream2.close();
                }
            }
            promise.resolve(Integer.valueOf(length));
        } catch (FileNotFoundException unused) {
            promise.reject("ENOENT", "File '" + str + "' does not exist and could not be created, or it is a directory");
        } catch (Exception e) {
            promise.reject("EUNSPECIFIED", e.getLocalizedMessage());
        }
    }

    static void writeFile(String str, ReadableArray readableArray, boolean z, Promise promise) {
        try {
            File file = new File(str);
            File parentFile = file.getParentFile();
            if (!file.exists()) {
                if (parentFile != null && !parentFile.exists() && !parentFile.mkdirs() && !parentFile.exists()) {
                    promise.reject("ENOTDIR", "Failed to create parent directory of '" + str + "'");
                    return;
                } else if (!file.createNewFile()) {
                    promise.reject("ENOENT", "File '" + str + "' does not exist and could not be created");
                    return;
                }
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file, z);
            try {
                byte[] bArr = new byte[readableArray.size()];
                for (int i = 0; i < readableArray.size(); i++) {
                    bArr[i] = (byte) readableArray.getInt(i);
                }
                fileOutputStream.write(bArr);
                fileOutputStream.close();
                promise.resolve(Integer.valueOf(readableArray.size()));
            } catch (Throwable th) {
                fileOutputStream.close();
                throw th;
            }
        } catch (FileNotFoundException unused) {
            promise.reject("ENOENT", "File '" + str + "' does not exist and could not be created");
        } catch (Exception e) {
            promise.reject("EUNSPECIFIED", e.getLocalizedMessage());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00e3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static void readFile(java.lang.String r7, java.lang.String r8, boolean r9, com.facebook.react.bridge.Promise r10) {
        /*
            Method dump skipped, instructions count: 362
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ReactNativeBlobUtil.ReactNativeBlobUtilFS.readFile(java.lang.String, java.lang.String, boolean, com.facebook.react.bridge.Promise):void");
    }

    static Map<String, Object> getSystemfolders(ReactApplicationContext reactApplicationContext) {
        HashMap map = new HashMap();
        map.put("DocumentDir", getFilesDirPath(reactApplicationContext));
        map.put("CacheDir", getCacheDirPath(reactApplicationContext));
        map.put("DCIMDir", getExternalFilesDirPath(reactApplicationContext, Environment.DIRECTORY_DCIM));
        map.put("PictureDir", getExternalFilesDirPath(reactApplicationContext, Environment.DIRECTORY_PICTURES));
        map.put("MusicDir", getExternalFilesDirPath(reactApplicationContext, Environment.DIRECTORY_MUSIC));
        map.put("DownloadDir", getExternalFilesDirPath(reactApplicationContext, Environment.DIRECTORY_DOWNLOADS));
        map.put("MovieDir", getExternalFilesDirPath(reactApplicationContext, Environment.DIRECTORY_MOVIES));
        map.put("RingtoneDir", getExternalFilesDirPath(reactApplicationContext, Environment.DIRECTORY_RINGTONES));
        if (Environment.getExternalStorageState().equals("mounted")) {
            map.put("SDCardDir", getExternalFilesDirPath(reactApplicationContext, null));
            File externalFilesDir = reactApplicationContext.getExternalFilesDir(null);
            if (externalFilesDir != null && externalFilesDir.getParentFile() != null) {
                map.put("SDCardApplicationDir", externalFilesDir.getParentFile().getAbsolutePath());
            } else {
                map.put("SDCardApplicationDir", "");
            }
        } else {
            map.put("SDCardDir", "");
            map.put("SDCardApplicationDir", "");
        }
        map.put("MainBundleDir", reactApplicationContext.getApplicationInfo().dataDir);
        map.put("LibraryDir", "");
        map.put("ApplicationSupportDir", "");
        return map;
    }

    static Map<String, Object> getLegacySystemfolders(ReactApplicationContext reactApplicationContext) {
        HashMap map = new HashMap();
        map.put("LegacyDCIMDir", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath());
        map.put("LegacyPictureDir", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath());
        map.put("LegacyMusicDir", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).getAbsolutePath());
        map.put("LegacyDownloadDir", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath());
        map.put("LegacyMovieDir", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES).getAbsolutePath());
        map.put("LegacyRingtoneDir", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_RINGTONES).getAbsolutePath());
        if (Environment.getExternalStorageState().equals("mounted")) {
            map.put("LegacySDCardDir", Environment.getExternalStorageDirectory().getAbsolutePath());
        } else {
            map.put("LegacySDCardDir", "");
        }
        return map;
    }

    static String getExternalFilesDirPath(ReactApplicationContext reactApplicationContext, String str) {
        File externalFilesDir = reactApplicationContext.getExternalFilesDir(str);
        return externalFilesDir != null ? externalFilesDir.getAbsolutePath() : "";
    }

    static String getFilesDirPath(ReactApplicationContext reactApplicationContext) {
        File filesDir = reactApplicationContext.getFilesDir();
        return filesDir != null ? filesDir.getAbsolutePath() : "";
    }

    static String getCacheDirPath(ReactApplicationContext reactApplicationContext) {
        File cacheDir = reactApplicationContext.getCacheDir();
        return cacheDir != null ? cacheDir.getAbsolutePath() : "";
    }

    public static void getSDCardDir(ReactApplicationContext reactApplicationContext, Promise promise) {
        if (Environment.getExternalStorageState().equals("mounted")) {
            try {
                promise.resolve(reactApplicationContext.getExternalFilesDir(null).getAbsolutePath());
                return;
            } catch (Exception e) {
                promise.reject("ReactNativeBlobUtil.getSDCardDir", e.getLocalizedMessage());
                return;
            }
        }
        promise.reject("ReactNativeBlobUtil.getSDCardDir", "External storage not mounted");
    }

    public static void getSDCardApplicationDir(ReactApplicationContext reactApplicationContext, Promise promise) {
        if (Environment.getExternalStorageState().equals("mounted")) {
            try {
                promise.resolve(reactApplicationContext.getExternalFilesDir(null).getParentFile().getAbsolutePath());
                return;
            } catch (Exception e) {
                promise.reject("ReactNativeBlobUtil.getSDCardApplicationDir", e.getLocalizedMessage());
                return;
            }
        }
        promise.reject("ReactNativeBlobUtil.getSDCardApplicationDir", "External storage not mounted");
    }

    static String getTmpPath(String str) {
        return ReactNativeBlobUtilImpl.RCTContext.getFilesDir() + "/ReactNativeBlobUtilTmp_" + str;
    }

    static void unlink(String str, Callback callback) {
        try {
            deleteRecursive(new File(ReactNativeBlobUtilUtils.normalizePath(str)));
            callback.invoke(null, true);
        } catch (Exception e) {
            callback.invoke(e.getLocalizedMessage(), false);
        }
    }

    private static void deleteRecursive(File file) throws IOException {
        if (file.isDirectory()) {
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles == null) {
                throw new NullPointerException("Received null trying to list files of directory '" + file + "'");
            }
            for (File file2 : fileArrListFiles) {
                deleteRecursive(file2);
            }
        }
        if (!file.delete()) {
            throw new IOException("Failed to delete '" + file + "'");
        }
    }

    static void mkdir(String str, Promise promise) {
        String strNormalizePath = ReactNativeBlobUtilUtils.normalizePath(str);
        File file = new File(strNormalizePath);
        if (file.exists()) {
            promise.reject("EEXIST", (file.isDirectory() ? "Folder" : "File") + " '" + strNormalizePath + "' already exists");
            return;
        }
        try {
            if (!file.mkdirs()) {
                promise.reject("EUNSPECIFIED", "mkdir failed to create some or all directories in '" + strNormalizePath + "'");
            } else {
                promise.resolve(true);
            }
        } catch (Exception e) {
            promise.reject("EUNSPECIFIED", e.getLocalizedMessage());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00fc A[Catch: Exception -> 0x00f8, TRY_LEAVE, TryCatch #5 {Exception -> 0x00f8, blocks: (B:58:0x00f4, B:62:0x00fc), top: B:70:0x00f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00f4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static void cp(java.lang.String r7, java.lang.String r8, com.facebook.react.bridge.Callback r9) {
        /*
            Method dump skipped, instructions count: 273
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ReactNativeBlobUtil.ReactNativeBlobUtilFS.cp(java.lang.String, java.lang.String, com.facebook.react.bridge.Callback):void");
    }

    static void mv(String str, String str2, Callback callback) {
        String strNormalizePath = ReactNativeBlobUtilUtils.normalizePath(str);
        String strNormalizePath2 = ReactNativeBlobUtilUtils.normalizePath(str2);
        File file = new File(strNormalizePath);
        if (!file.exists()) {
            callback.invoke("Source file at path `" + strNormalizePath + "` does not exist");
            return;
        }
        try {
            File file2 = new File(strNormalizePath2);
            File parentFile = file2.getParentFile();
            if (parentFile != null && !parentFile.exists()) {
                callback.invoke("mv failed because the destination directory doesn't exist");
                return;
            }
            if (file2.exists()) {
                file2.delete();
            }
            if (!file.renameTo(file2)) {
                callback.invoke("mv failed for unknown reasons");
            } else {
                callback.invoke(new Object[0]);
            }
        } catch (Exception e) {
            callback.invoke(e.toString());
        }
    }

    static void exists(String str, Callback callback) {
        if (isAsset(str)) {
            try {
                ReactNativeBlobUtilImpl.RCTContext.getAssets().openFd(str.replace(ReactNativeBlobUtilConst.FILE_PREFIX_BUNDLE_ASSET, ""));
                callback.invoke(true, false);
                return;
            } catch (IOException unused) {
                callback.invoke(false, false);
                return;
            }
        }
        String strNormalizePath = ReactNativeBlobUtilUtils.normalizePath(str);
        if (strNormalizePath != null) {
            callback.invoke(Boolean.valueOf(new File(strNormalizePath).exists()), Boolean.valueOf(new File(strNormalizePath).isDirectory()));
        } else {
            callback.invoke(false, false);
        }
    }

    static void ls(String str, Promise promise) {
        try {
            String strNormalizePath = ReactNativeBlobUtilUtils.normalizePath(str);
            File file = new File(strNormalizePath);
            if (!file.exists()) {
                promise.reject("ENOENT", "No such file '" + strNormalizePath + "'");
                return;
            }
            if (!file.isDirectory()) {
                promise.reject("ENOTDIR", "Not a directory '" + strNormalizePath + "'");
                return;
            }
            String[] list = new File(strNormalizePath).list();
            WritableArray writableArrayCreateArray = Arguments.createArray();
            for (String str2 : list) {
                writableArrayCreateArray.pushString(str2);
            }
            promise.resolve(writableArrayCreateArray);
        } catch (Exception e) {
            e.printStackTrace();
            promise.reject("EUNSPECIFIED", e.getLocalizedMessage());
        }
    }

    static void slice(String str, String str2, int i, int i2, String str3, Promise promise) {
        try {
            String strNormalizePath = ReactNativeBlobUtilUtils.normalizePath(str);
            String strNormalizePath2 = ReactNativeBlobUtilUtils.normalizePath(str2);
            File file = new File(strNormalizePath);
            if (file.isDirectory()) {
                promise.reject("EISDIR", "Expecting a file but '" + strNormalizePath + "' is a directory");
                return;
            }
            if (!file.exists()) {
                promise.reject("ENOENT", "No such file '" + strNormalizePath + "'");
                return;
            }
            int length = (int) file.length();
            int iMin = Math.min(length, i2) - i;
            FileInputStream fileInputStream = new FileInputStream(new File(strNormalizePath));
            FileOutputStream fileOutputStream = new FileOutputStream(new File(strNormalizePath2));
            int iSkip = (int) fileInputStream.skip(i);
            if (iSkip != i) {
                promise.reject("EUNSPECIFIED", "Skipped " + iSkip + " instead of the specified " + i + " bytes, size is " + length);
                return;
            }
            byte[] bArr = new byte[Data.MAX_DATA_BYTES];
            int i3 = 0;
            while (i3 < iMin) {
                int i4 = fileInputStream.read(bArr, 0, Data.MAX_DATA_BYTES);
                int i5 = iMin - i3;
                if (i4 <= 0) {
                    break;
                }
                fileOutputStream.write(bArr, 0, Math.min(i5, i4));
                i3 += i4;
            }
            fileInputStream.close();
            fileOutputStream.flush();
            fileOutputStream.close();
            promise.resolve(strNormalizePath2);
        } catch (Exception e) {
            e.printStackTrace();
            promise.reject("EUNSPECIFIED", e.getLocalizedMessage());
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.ReactNativeBlobUtil.ReactNativeBlobUtilFS$1] */
    static void lstat(String str, final Callback callback) {
        new AsyncTask<String, Integer, Integer>() { // from class: com.ReactNativeBlobUtil.ReactNativeBlobUtilFS.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Integer doInBackground(String... strArr) {
                WritableArray writableArrayCreateArray = Arguments.createArray();
                if (strArr[0] == null) {
                    callback.invoke("the path specified for lstat is either `null` or `undefined`.");
                    return 0;
                }
                File file = new File(strArr[0]);
                if (!file.exists()) {
                    callback.invoke("failed to lstat path `" + strArr[0] + "` because it does not exist or it is not a folder");
                    return 0;
                }
                if (file.isDirectory()) {
                    for (String str2 : file.list()) {
                        writableArrayCreateArray.pushMap(ReactNativeBlobUtilFS.statFile(file.getPath() + "/" + str2));
                    }
                } else {
                    writableArrayCreateArray.pushMap(ReactNativeBlobUtilFS.statFile(file.getAbsolutePath()));
                }
                callback.invoke(null, writableArrayCreateArray);
                return 0;
            }
        }.execute(ReactNativeBlobUtilUtils.normalizePath(str));
    }

    static void stat(String str, Callback callback) {
        try {
            String strNormalizePath = ReactNativeBlobUtilUtils.normalizePath(str);
            WritableMap writableMapStatFile = statFile(strNormalizePath);
            if (writableMapStatFile == null) {
                callback.invoke("failed to stat path `" + strNormalizePath + "` because it does not exist or it is not a folder", null);
            } else {
                callback.invoke(null, writableMapStatFile);
            }
        } catch (Exception e) {
            callback.invoke(e.getLocalizedMessage());
        }
    }

    static WritableMap statFile(String str) {
        try {
            String strNormalizePath = ReactNativeBlobUtilUtils.normalizePath(str);
            WritableMap writableMapCreateMap = Arguments.createMap();
            if (isAsset(strNormalizePath)) {
                String strReplace = strNormalizePath.replace(ReactNativeBlobUtilConst.FILE_PREFIX_BUNDLE_ASSET, "");
                AssetFileDescriptor assetFileDescriptorOpenFd = ReactNativeBlobUtilImpl.RCTContext.getAssets().openFd(strReplace);
                writableMapCreateMap.putString("filename", strReplace);
                writableMapCreateMap.putString(ReactNativeBlobUtilConst.RNFB_RESPONSE_PATH, strNormalizePath);
                writableMapCreateMap.putString("type", UriUtil.LOCAL_ASSET_SCHEME);
                writableMapCreateMap.putString(RRWebVideoEvent.JsonKeys.SIZE, String.valueOf(assetFileDescriptorOpenFd.getLength()));
                writableMapCreateMap.putInt("lastModified", 0);
            } else {
                File file = new File(strNormalizePath);
                if (!file.exists()) {
                    return null;
                }
                writableMapCreateMap.putString("filename", file.getName());
                writableMapCreateMap.putString(ReactNativeBlobUtilConst.RNFB_RESPONSE_PATH, file.getPath());
                writableMapCreateMap.putString("type", file.isDirectory() ? "directory" : "file");
                writableMapCreateMap.putString(RRWebVideoEvent.JsonKeys.SIZE, String.valueOf(file.length()));
                writableMapCreateMap.putString("lastModified", String.valueOf(file.lastModified()));
            }
            return writableMapCreateMap;
        } catch (Exception unused) {
            return null;
        }
    }

    void scanFile(String[] strArr, String[] strArr2, final Callback callback) {
        try {
            MediaScannerConnection.scanFile(this.mCtx, strArr, strArr2, new MediaScannerConnection.OnScanCompletedListener() { // from class: com.ReactNativeBlobUtil.ReactNativeBlobUtilFS.2
                @Override // android.media.MediaScannerConnection.OnScanCompletedListener
                public void onScanCompleted(String str, Uri uri) {
                    callback.invoke(null, true);
                }
            });
        } catch (Exception e) {
            callback.invoke(e.getLocalizedMessage(), null);
        }
    }

    static void hash(String str, String str2, Promise promise) {
        try {
            HashMap map = new HashMap();
            map.put("md5", "MD5");
            map.put("sha1", "SHA-1");
            map.put("sha224", "SHA-224");
            map.put("sha256", "SHA-256");
            map.put("sha384", "SHA-384");
            map.put("sha512", "SHA-512");
            if (!map.containsKey(str2)) {
                promise.reject("EINVAL", "Invalid algorithm '" + str2 + "', must be one of md5, sha1, sha224, sha256, sha384, sha512");
                return;
            }
            String strNormalizePath = ReactNativeBlobUtilUtils.normalizePath(str);
            File file = new File(strNormalizePath);
            if (file.isDirectory()) {
                promise.reject("EISDIR", "Expecting a file but '" + strNormalizePath + "' is a directory");
                return;
            }
            if (!file.exists()) {
                promise.reject("ENOENT", "No such file '" + strNormalizePath + "'");
                return;
            }
            MessageDigest messageDigest = MessageDigest.getInstance((String) map.get(str2));
            FileInputStream fileInputStream = new FileInputStream(strNormalizePath);
            byte[] bArr = new byte[1048576];
            if (file.length() != 0) {
                while (true) {
                    int i = fileInputStream.read(bArr);
                    if (i == -1) {
                        break;
                    } else {
                        messageDigest.update(bArr, 0, i);
                    }
                }
            }
            StringBuilder sb = new StringBuilder();
            for (byte b : messageDigest.digest()) {
                sb.append(String.format("%02x", Byte.valueOf(b)));
            }
            promise.resolve(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
            promise.reject("EUNSPECIFIED", e.getLocalizedMessage());
        }
    }

    static void createFile(String str, String str2, String str3, Promise promise) {
        try {
            String strNormalizePath = ReactNativeBlobUtilUtils.normalizePath(str);
            File file = new File(strNormalizePath);
            boolean zCreateNewFile = file.createNewFile();
            if (str3.equals(ReactNativeBlobUtilConst.DATA_ENCODE_URI)) {
                File file2 = new File(str2.replace(ReactNativeBlobUtilConst.FILE_PREFIX, ""));
                if (!file2.exists()) {
                    promise.reject("ENOENT", "Source file : " + str2 + " does not exist");
                    return;
                }
                FileInputStream fileInputStream = new FileInputStream(file2);
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                byte[] bArr = new byte[Data.MAX_DATA_BYTES];
                for (int i = fileInputStream.read(bArr); i > 0; i = fileInputStream.read(bArr)) {
                    fileOutputStream.write(bArr, 0, i);
                }
                fileInputStream.close();
                fileOutputStream.close();
            } else {
                if (!zCreateNewFile) {
                    promise.reject("EEXIST", "File `" + strNormalizePath + "` already exists");
                    return;
                }
                new FileOutputStream(file).write(ReactNativeBlobUtilUtils.stringToBytes(str2, str3));
            }
            promise.resolve(strNormalizePath);
        } catch (Exception e) {
            promise.reject("EUNSPECIFIED", e.getLocalizedMessage());
        }
    }

    static void createFileASCII(String str, ReadableArray readableArray, Promise promise) {
        try {
            String strNormalizePath = ReactNativeBlobUtilUtils.normalizePath(str);
            File file = new File(strNormalizePath);
            if (!file.createNewFile()) {
                promise.reject("EEXIST", "File at path `" + strNormalizePath + "` already exists");
                return;
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] bArr = new byte[readableArray.size()];
            for (int i = 0; i < readableArray.size(); i++) {
                bArr[i] = (byte) readableArray.getInt(i);
            }
            fileOutputStream.write(bArr);
            promise.resolve(strNormalizePath);
        } catch (Exception e) {
            promise.reject("EUNSPECIFIED", e.getLocalizedMessage());
        }
    }

    static void df(Callback callback, ReactApplicationContext reactApplicationContext) {
        StatFs statFs = new StatFs(reactApplicationContext.getFilesDir().getPath());
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString("internal_free", String.valueOf(statFs.getFreeBytes()));
        writableMapCreateMap.putString("internal_total", String.valueOf(statFs.getTotalBytes()));
        File externalFilesDir = reactApplicationContext.getExternalFilesDir(null);
        if (externalFilesDir != null) {
            StatFs statFs2 = new StatFs(externalFilesDir.getPath());
            writableMapCreateMap.putString("external_free", String.valueOf(statFs2.getFreeBytes()));
            writableMapCreateMap.putString("external_total", String.valueOf(statFs2.getTotalBytes()));
        } else {
            writableMapCreateMap.putString("external_free", "-1");
            writableMapCreateMap.putString("external_total", "-1");
        }
        callback.invoke(null, writableMapCreateMap);
    }

    static void removeSession(ReadableArray readableArray, final Callback callback) {
        new AsyncTask<ReadableArray, Integer, Integer>() { // from class: com.ReactNativeBlobUtil.ReactNativeBlobUtilFS.3
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Integer doInBackground(ReadableArray... readableArrayArr) {
                try {
                    ArrayList arrayList = new ArrayList();
                    for (int i = 0; i < readableArrayArr[0].size(); i++) {
                        String string = readableArrayArr[0].getString(i);
                        File file = new File(string);
                        if (file.exists() && !file.delete()) {
                            arrayList.add(string);
                        }
                    }
                    if (arrayList.isEmpty()) {
                        callback.invoke(null, true);
                    } else {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Failed to delete: ");
                        Iterator it = arrayList.iterator();
                        while (it.hasNext()) {
                            sb.append((String) it.next()).append(", ");
                        }
                        callback.invoke(sb.toString());
                    }
                } catch (Exception e) {
                    callback.invoke(e.getLocalizedMessage());
                }
                return Integer.valueOf(readableArrayArr[0].size());
            }
        }.execute(readableArray);
    }

    private static InputStream inputStreamFromPath(String str) throws IOException {
        if (str.startsWith(ReactNativeBlobUtilConst.FILE_PREFIX_BUNDLE_ASSET)) {
            return ReactNativeBlobUtilImpl.RCTContext.getAssets().open(str.replace(ReactNativeBlobUtilConst.FILE_PREFIX_BUNDLE_ASSET, ""));
        }
        return new FileInputStream(new File(str));
    }

    private static boolean isPathExists(String str) throws IOException {
        if (str.startsWith(ReactNativeBlobUtilConst.FILE_PREFIX_BUNDLE_ASSET)) {
            try {
                ReactNativeBlobUtilImpl.RCTContext.getAssets().open(str.replace(ReactNativeBlobUtilConst.FILE_PREFIX_BUNDLE_ASSET, ""));
                return true;
            } catch (IOException unused) {
                return false;
            }
        }
        return new File(str).exists();
    }

    static boolean isAsset(String str) {
        return str != null && str.startsWith(ReactNativeBlobUtilConst.FILE_PREFIX_BUNDLE_ASSET);
    }
}
