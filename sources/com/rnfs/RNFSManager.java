package com.rnfs;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.util.Base64;
import android.util.SparseArray;
import androidx.work.Data;
import com.ReactNativeBlobUtil.ReactNativeBlobUtilConst;
import com.clevertap.android.sdk.Constants;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.RCTNativeAppEventEmitter;
import com.rnfs.DownloadParams;
import com.rnfs.UploadParams;
import io.sentry.rrweb.RRWebVideoEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.URL;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@ReactModule(name = RNFSManager.MODULE_NAME)
/* loaded from: classes6.dex */
public class RNFSManager extends ReactContextBaseJavaModule {
    static final String MODULE_NAME = "RNFSManager";
    private static final String RNFSCachesDirectoryPath = "RNFSCachesDirectoryPath";
    private static final String RNFSDocumentDirectory = "RNFSDocumentDirectory";
    private static final String RNFSDocumentDirectoryPath = "RNFSDocumentDirectoryPath";
    private static final String RNFSDownloadDirectoryPath = "RNFSDownloadDirectoryPath";
    private static final String RNFSExternalCachesDirectoryPath = "RNFSExternalCachesDirectoryPath";
    private static final String RNFSExternalDirectoryPath = "RNFSExternalDirectoryPath";
    private static final String RNFSExternalStorageDirectoryPath = "RNFSExternalStorageDirectoryPath";
    private static final String RNFSFileTypeDirectory = "RNFSFileTypeDirectory";
    private static final String RNFSFileTypeRegular = "RNFSFileTypeRegular";
    private static final String RNFSPicturesDirectoryPath = "RNFSPicturesDirectoryPath";
    private static final String RNFSTemporaryDirectoryPath = "RNFSTemporaryDirectoryPath";
    private SparseArray<Downloader> downloaders;
    private ReactApplicationContext reactContext;
    private SparseArray<Uploader> uploaders;

    private String getWriteAccessByAPILevel() {
        return Build.VERSION.SDK_INT <= 28 ? Constants.INAPP_WINDOW : "rwt";
    }

    @ReactMethod
    public void addListener(String str) {
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return MODULE_NAME;
    }

    @ReactMethod
    public void pathForBundle(String str, Promise promise) {
    }

    @ReactMethod
    public void pathForGroup(String str, Promise promise) {
    }

    @ReactMethod
    public void removeListeners(Integer num) {
    }

    public RNFSManager(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.downloaders = new SparseArray<>();
        this.uploaders = new SparseArray<>();
        this.reactContext = reactApplicationContext;
    }

    private Uri getFileUri(String str, boolean z) throws IORejectionException {
        Uri uri = Uri.parse(str);
        if (uri.getScheme() != null) {
            return uri;
        }
        File file = new File(str);
        if (!z && file.isDirectory()) {
            throw new IORejectionException("EISDIR", "EISDIR: illegal operation on a directory, read '" + str + "'");
        }
        return Uri.parse("file://" + str);
    }

    private String getOriginalFilepath(String str, boolean z) throws IORejectionException {
        Uri fileUri = getFileUri(str, z);
        if (fileUri.getScheme().equals("content")) {
            try {
                Cursor cursorQuery = this.reactContext.getContentResolver().query(fileUri, null, null, null, null);
                if (cursorQuery.moveToFirst()) {
                    str = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("_data"));
                }
                cursorQuery.close();
            } catch (IllegalArgumentException unused) {
            }
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public InputStream getInputStream(String str) throws IORejectionException, FileNotFoundException {
        try {
            InputStream inputStreamOpenInputStream = this.reactContext.getContentResolver().openInputStream(getFileUri(str, false));
            if (inputStreamOpenInputStream != null) {
                return inputStreamOpenInputStream;
            }
            throw new IORejectionException("ENOENT", "ENOENT: could not open an input stream for '" + str + "'");
        } catch (FileNotFoundException e) {
            throw new IORejectionException("ENOENT", "ENOENT: " + e.getMessage() + ", open '" + str + "'");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public OutputStream getOutputStream(String str, boolean z) throws IORejectionException, FileNotFoundException {
        try {
            OutputStream outputStreamOpenOutputStream = this.reactContext.getContentResolver().openOutputStream(getFileUri(str, false), z ? "wa" : getWriteAccessByAPILevel());
            if (outputStreamOpenOutputStream != null) {
                return outputStreamOpenOutputStream;
            }
            throw new IORejectionException("ENOENT", "ENOENT: could not open an output stream for '" + str + "'");
        } catch (FileNotFoundException e) {
            throw new IORejectionException("ENOENT", "ENOENT: " + e.getMessage() + ", open '" + str + "'");
        }
    }

    private static byte[] getInputStreamBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            try {
                int i = inputStream.read(bArr);
                if (i == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, i);
            } finally {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException unused) {
                }
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    @ReactMethod
    public void writeFile(String str, String str2, ReadableMap readableMap, Promise promise) throws IOException {
        try {
            byte[] bArrDecode = Base64.decode(str2, 0);
            OutputStream outputStream = getOutputStream(str, false);
            outputStream.write(bArrDecode);
            outputStream.close();
            promise.resolve(null);
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, str, e);
        }
    }

    @ReactMethod
    public void appendFile(String str, String str2, Promise promise) throws IOException {
        try {
            byte[] bArrDecode = Base64.decode(str2, 0);
            OutputStream outputStream = getOutputStream(str, true);
            outputStream.write(bArrDecode);
            outputStream.close();
            promise.resolve(null);
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, str, e);
        }
    }

    @ReactMethod
    public void write(String str, String str2, int i, Promise promise) throws IOException {
        try {
            byte[] bArrDecode = Base64.decode(str2, 0);
            if (i < 0) {
                OutputStream outputStream = getOutputStream(str, true);
                outputStream.write(bArrDecode);
                outputStream.close();
            } else {
                RandomAccessFile randomAccessFile = new RandomAccessFile(str, "rw");
                randomAccessFile.seek(i);
                randomAccessFile.write(bArrDecode);
                randomAccessFile.close();
            }
            promise.resolve(null);
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, str, e);
        }
    }

    @ReactMethod
    public void exists(String str, Promise promise) {
        try {
            promise.resolve(Boolean.valueOf(new File(str).exists()));
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, str, e);
        }
    }

    @ReactMethod
    public void readFile(String str, Promise promise) {
        try {
            promise.resolve(Base64.encodeToString(getInputStreamBytes(getInputStream(str)), 2));
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, str, e);
        }
    }

    @ReactMethod
    public void read(String str, int i, int i2, Promise promise) throws IOException {
        try {
            InputStream inputStream = getInputStream(str);
            byte[] bArr = new byte[i];
            inputStream.skip(i2);
            promise.resolve(Base64.encodeToString(bArr, 0, inputStream.read(bArr, 0, i), 2));
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, str, e);
        }
    }

    @ReactMethod
    public void readFileAssets(String str, Promise promise) throws IOException {
        InputStream inputStreamOpen = null;
        try {
            try {
                inputStreamOpen = getReactApplicationContext().getAssets().open(str, 0);
            } catch (Exception e) {
                e.printStackTrace();
                reject(promise, str, e);
                if (0 == 0) {
                    return;
                }
            }
            if (inputStreamOpen == null) {
                reject(promise, str, new Exception("Failed to open file"));
                if (inputStreamOpen != null) {
                    try {
                        inputStreamOpen.close();
                        return;
                    } catch (IOException unused) {
                        return;
                    }
                }
                return;
            }
            byte[] bArr = new byte[inputStreamOpen.available()];
            inputStreamOpen.read(bArr);
            promise.resolve(Base64.encodeToString(bArr, 2));
            if (inputStreamOpen == null) {
                return;
            }
            try {
                inputStreamOpen.close();
            } catch (IOException unused2) {
            }
        } catch (Throwable th) {
            if (0 != 0) {
                try {
                    inputStreamOpen.close();
                } catch (IOException unused3) {
                }
            }
            throw th;
        }
    }

    @ReactMethod
    public void readFileRes(String str, Promise promise) throws IOException {
        InputStream inputStreamOpenRawResource = null;
        try {
            try {
                inputStreamOpenRawResource = getReactApplicationContext().getResources().openRawResource(getResIdentifier(str));
            } catch (Exception e) {
                e.printStackTrace();
                reject(promise, str, e);
                if (0 == 0) {
                    return;
                }
            }
            if (inputStreamOpenRawResource == null) {
                reject(promise, str, new Exception("Failed to open file"));
                if (inputStreamOpenRawResource != null) {
                    try {
                        inputStreamOpenRawResource.close();
                        return;
                    } catch (IOException unused) {
                        return;
                    }
                }
                return;
            }
            byte[] bArr = new byte[inputStreamOpenRawResource.available()];
            inputStreamOpenRawResource.read(bArr);
            promise.resolve(Base64.encodeToString(bArr, 2));
            if (inputStreamOpenRawResource == null) {
                return;
            }
            try {
                inputStreamOpenRawResource.close();
            } catch (IOException unused2) {
            }
        } catch (Throwable th) {
            if (0 != 0) {
                try {
                    inputStreamOpenRawResource.close();
                } catch (IOException unused3) {
                }
            }
            throw th;
        }
    }

    private int getResIdentifier(String str) {
        boolean z = true;
        String strSubstring = str.substring(str.lastIndexOf(".") + 1);
        String strSubstring2 = str.substring(0, str.lastIndexOf("."));
        if (!strSubstring.equals("png") && !strSubstring.equals("jpg") && !strSubstring.equals("jpeg") && !strSubstring.equals("bmp") && !strSubstring.equals("gif") && !strSubstring.equals("webp") && !strSubstring.equals("psd") && !strSubstring.equals("svg") && !strSubstring.equals("tiff")) {
            z = false;
        }
        return getReactApplicationContext().getResources().getIdentifier(strSubstring2, Boolean.valueOf(z).booleanValue() ? "drawable" : "raw", getReactApplicationContext().getPackageName());
    }

    @ReactMethod
    public void hash(String str, String str2, Promise promise) throws Exception {
        try {
            HashMap map = new HashMap();
            map.put("md5", "MD5");
            map.put("sha1", "SHA-1");
            map.put("sha224", "SHA-224");
            map.put("sha256", "SHA-256");
            map.put("sha384", "SHA-384");
            map.put("sha512", "SHA-512");
            if (!map.containsKey(str2)) {
                throw new Exception("Invalid hash algorithm");
            }
            File file = new File(str);
            if (file.isDirectory()) {
                rejectFileIsDirectory(promise);
                return;
            }
            if (!file.exists()) {
                rejectFileNotFound(promise, str);
                return;
            }
            MessageDigest messageDigest = MessageDigest.getInstance((String) map.get(str2));
            FileInputStream fileInputStream = new FileInputStream(str);
            byte[] bArr = new byte[Data.MAX_DATA_BYTES];
            while (true) {
                int i = fileInputStream.read(bArr);
                if (i == -1) {
                    break;
                } else {
                    messageDigest.update(bArr, 0, i);
                }
            }
            StringBuilder sb = new StringBuilder();
            for (byte b : messageDigest.digest()) {
                sb.append(String.format("%02x", Byte.valueOf(b)));
            }
            promise.resolve(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, str, e);
        }
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [com.rnfs.RNFSManager$1] */
    @ReactMethod
    public void moveFile(final String str, String str2, ReadableMap readableMap, final Promise promise) {
        try {
            final File file = new File(str);
            if (!file.renameTo(new File(str2))) {
                new CopyFileTask() { // from class: com.rnfs.RNFSManager.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super();
                    }

                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // android.os.AsyncTask
                    public void onPostExecute(Exception exc) {
                        if (exc == null) {
                            file.delete();
                            promise.resolve(true);
                        } else {
                            exc.printStackTrace();
                            RNFSManager.this.reject(promise, str, exc);
                        }
                    }
                }.execute(new String[]{str, str2});
            } else {
                promise.resolve(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, str, e);
        }
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [com.rnfs.RNFSManager$2] */
    @ReactMethod
    public void copyFile(final String str, String str2, ReadableMap readableMap, final Promise promise) {
        new CopyFileTask() { // from class: com.rnfs.RNFSManager.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(Exception exc) {
                if (exc == null) {
                    promise.resolve(null);
                } else {
                    exc.printStackTrace();
                    RNFSManager.this.reject(promise, str, exc);
                }
            }
        }.execute(new String[]{str, str2});
    }

    private class CopyFileTask extends AsyncTask<String, Void, Exception> {
        private CopyFileTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Exception doInBackground(String... strArr) throws IOException {
            try {
                String str = strArr[0];
                String str2 = strArr[1];
                InputStream inputStream = RNFSManager.this.getInputStream(str);
                OutputStream outputStream = RNFSManager.this.getOutputStream(str2, false);
                byte[] bArr = new byte[1024];
                while (true) {
                    int i = inputStream.read(bArr);
                    if (i > 0) {
                        outputStream.write(bArr, 0, i);
                        Thread.yield();
                    } else {
                        inputStream.close();
                        outputStream.close();
                        return null;
                    }
                }
            } catch (Exception e) {
                return e;
            }
        }
    }

    @ReactMethod
    public void readDir(String str, Promise promise) throws Exception {
        try {
            File file = new File(str);
            if (!file.exists()) {
                throw new Exception("Folder does not exist");
            }
            File[] fileArrListFiles = file.listFiles();
            WritableArray writableArrayCreateArray = Arguments.createArray();
            for (File file2 : fileArrListFiles) {
                WritableMap writableMapCreateMap = Arguments.createMap();
                writableMapCreateMap.putDouble("mtime", file2.lastModified() / 1000.0d);
                writableMapCreateMap.putString("name", file2.getName());
                writableMapCreateMap.putString(ReactNativeBlobUtilConst.RNFB_RESPONSE_PATH, file2.getAbsolutePath());
                writableMapCreateMap.putDouble(RRWebVideoEvent.JsonKeys.SIZE, file2.length());
                writableMapCreateMap.putInt("type", file2.isDirectory() ? 1 : 0);
                writableArrayCreateArray.pushMap(writableMapCreateMap);
            }
            promise.resolve(writableArrayCreateArray);
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, str, e);
        }
    }

    @ReactMethod
    public void readDirAssets(String str, Promise promise) throws IOException {
        int length;
        try {
            AssetManager assets = getReactApplicationContext().getAssets();
            String[] list = assets.list(str);
            WritableArray writableArrayCreateArray = Arguments.createArray();
            for (String str2 : list) {
                WritableMap writableMapCreateMap = Arguments.createMap();
                writableMapCreateMap.putString("name", str2);
                if (!str.isEmpty()) {
                    str2 = String.format("%s/%s", str, str2);
                }
                writableMapCreateMap.putString(ReactNativeBlobUtilConst.RNFB_RESPONSE_PATH, str2);
                int i = 1;
                try {
                    AssetFileDescriptor assetFileDescriptorOpenFd = assets.openFd(str2);
                    if (assetFileDescriptorOpenFd != null) {
                        length = (int) assetFileDescriptorOpenFd.getLength();
                        try {
                            assetFileDescriptorOpenFd.close();
                            i = 0;
                        } catch (IOException e) {
                            e = e;
                            i = 1 ^ (e.getMessage().contains("compressed") ? 1 : 0);
                            writableMapCreateMap.putInt(RRWebVideoEvent.JsonKeys.SIZE, length);
                            writableMapCreateMap.putInt("type", i);
                            writableArrayCreateArray.pushMap(writableMapCreateMap);
                        }
                    } else {
                        length = 0;
                    }
                } catch (IOException e2) {
                    e = e2;
                    length = 0;
                }
                writableMapCreateMap.putInt(RRWebVideoEvent.JsonKeys.SIZE, length);
                writableMapCreateMap.putInt("type", i);
                writableArrayCreateArray.pushMap(writableMapCreateMap);
            }
            promise.resolve(writableArrayCreateArray);
        } catch (IOException e3) {
            reject(promise, str, e3);
        }
    }

    @ReactMethod
    public void copyFileAssets(String str, String str2, Promise promise) throws Throwable {
        try {
            copyInputStream(getReactApplicationContext().getAssets().open(str), str, str2, promise);
        } catch (IOException unused) {
            reject(promise, str, new Exception(String.format("Asset '%s' could not be opened", str)));
        }
    }

    @ReactMethod
    public void copyFileRes(String str, String str2, Promise promise) throws Throwable {
        try {
            copyInputStream(getReactApplicationContext().getResources().openRawResource(getResIdentifier(str)), str, str2, promise);
        } catch (Exception unused) {
            reject(promise, str, new Exception(String.format("Res '%s' could not be opened", str)));
        }
    }

    @ReactMethod
    public void existsAssets(String str, Promise promise) throws IOException {
        try {
            AssetManager assets = getReactApplicationContext().getAssets();
            try {
                String[] list = assets.list(str);
                if (list != null && list.length > 0) {
                    promise.resolve(true);
                    return;
                }
            } catch (Exception unused) {
            }
            InputStream inputStreamOpen = null;
            try {
                try {
                    inputStreamOpen = assets.open(str);
                    promise.resolve(true);
                    if (inputStreamOpen == null) {
                        return;
                    }
                } catch (Exception unused2) {
                    promise.resolve(false);
                    if (inputStreamOpen == null) {
                        return;
                    }
                }
                try {
                    inputStreamOpen.close();
                } catch (Exception unused3) {
                }
            } catch (Throwable th) {
                if (inputStreamOpen != null) {
                    try {
                        inputStreamOpen.close();
                    } catch (Exception unused4) {
                    }
                }
                throw th;
            }
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, str, e);
        }
    }

    @ReactMethod
    public void existsRes(String str, Promise promise) {
        try {
            if (getResIdentifier(str) > 0) {
                promise.resolve(true);
            } else {
                promise.resolve(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, str, e);
        }
    }

    /* JADX WARN: Not initialized variable reg: 2, insn: 0x0024: MOVE (r1 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:16:0x0024 */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0056 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x005b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:57:? A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void copyInputStream(java.io.InputStream r8, java.lang.String r9, java.lang.String r10, com.facebook.react.bridge.Promise r11) throws java.lang.Throwable {
        /*
            r7 = this;
            r0 = 0
            r1 = 0
            java.io.OutputStream r2 = r7.getOutputStream(r10, r0)     // Catch: java.lang.Throwable -> L28 java.lang.Exception -> L2a
            r3 = 10240(0x2800, float:1.4349E-41)
            byte[] r3 = new byte[r3]     // Catch: java.lang.Throwable -> L23 java.lang.Exception -> L26
        La:
            int r4 = r8.read(r3)     // Catch: java.lang.Throwable -> L23 java.lang.Exception -> L26
            r5 = -1
            if (r4 == r5) goto L15
            r2.write(r3, r0, r4)     // Catch: java.lang.Throwable -> L23 java.lang.Exception -> L26
            goto La
        L15:
            r11.resolve(r1)     // Catch: java.lang.Throwable -> L23 java.lang.Exception -> L26
            if (r8 == 0) goto L1d
            r8.close()     // Catch: java.io.IOException -> L1d
        L1d:
            if (r2 == 0) goto L53
        L1f:
            r2.close()     // Catch: java.io.IOException -> L53
            goto L53
        L23:
            r9 = move-exception
            r1 = r2
            goto L54
        L26:
            r1 = move-exception
            goto L2e
        L28:
            r9 = move-exception
            goto L54
        L2a:
            r2 = move-exception
            r6 = r2
            r2 = r1
            r1 = r6
        L2e:
            java.lang.Exception r3 = new java.lang.Exception     // Catch: java.lang.Throwable -> L23
            java.lang.String r4 = "Failed to copy '%s' to %s (%s)"
            r5 = 3
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch: java.lang.Throwable -> L23
            r5[r0] = r9     // Catch: java.lang.Throwable -> L23
            r0 = 1
            r5[r0] = r10     // Catch: java.lang.Throwable -> L23
            java.lang.String r10 = r1.getLocalizedMessage()     // Catch: java.lang.Throwable -> L23
            r0 = 2
            r5[r0] = r10     // Catch: java.lang.Throwable -> L23
            java.lang.String r10 = java.lang.String.format(r4, r5)     // Catch: java.lang.Throwable -> L23
            r3.<init>(r10)     // Catch: java.lang.Throwable -> L23
            r7.reject(r11, r9, r3)     // Catch: java.lang.Throwable -> L23
            if (r8 == 0) goto L50
            r8.close()     // Catch: java.io.IOException -> L50
        L50:
            if (r2 == 0) goto L53
            goto L1f
        L53:
            return
        L54:
            if (r8 == 0) goto L59
            r8.close()     // Catch: java.io.IOException -> L59
        L59:
            if (r1 == 0) goto L5e
            r1.close()     // Catch: java.io.IOException -> L5e
        L5e:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.rnfs.RNFSManager.copyInputStream(java.io.InputStream, java.lang.String, java.lang.String, com.facebook.react.bridge.Promise):void");
    }

    @ReactMethod
    public void setReadable(String str, Boolean bool, Boolean bool2, Promise promise) throws Exception {
        try {
            File file = new File(str);
            if (!file.exists()) {
                throw new Exception("File does not exist");
            }
            file.setReadable(bool.booleanValue(), bool2.booleanValue());
            promise.resolve(true);
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, str, e);
        }
    }

    @ReactMethod
    public void stat(String str, Promise promise) throws Exception {
        try {
            String originalFilepath = getOriginalFilepath(str, true);
            File file = new File(originalFilepath);
            if (!file.exists()) {
                throw new Exception("File does not exist");
            }
            WritableMap writableMapCreateMap = Arguments.createMap();
            writableMapCreateMap.putInt("ctime", (int) (file.lastModified() / 1000));
            writableMapCreateMap.putInt("mtime", (int) (file.lastModified() / 1000));
            writableMapCreateMap.putDouble(RRWebVideoEvent.JsonKeys.SIZE, file.length());
            writableMapCreateMap.putInt("type", file.isDirectory() ? 1 : 0);
            writableMapCreateMap.putString("originalFilepath", originalFilepath);
            promise.resolve(writableMapCreateMap);
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, str, e);
        }
    }

    @ReactMethod
    public void unlink(String str, Promise promise) throws Exception {
        try {
            File file = new File(str);
            if (!file.exists()) {
                throw new Exception("File does not exist");
            }
            DeleteRecursive(file);
            promise.resolve(null);
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, str, e);
        }
    }

    private void DeleteRecursive(File file) {
        if (file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                DeleteRecursive(file2);
            }
        }
        file.delete();
    }

    @ReactMethod
    public void mkdir(String str, ReadableMap readableMap, Promise promise) throws Exception {
        try {
            File file = new File(str);
            file.mkdirs();
            if (!file.exists()) {
                throw new Exception("Directory could not be created");
            }
            promise.resolve(null);
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, str, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendEvent(ReactContext reactContext, String str, WritableMap writableMap) {
        ((RCTNativeAppEventEmitter) reactContext.getJSModule(RCTNativeAppEventEmitter.class)).emit(str, writableMap);
    }

    @ReactMethod
    public void downloadFile(final ReadableMap readableMap, final Promise promise) {
        try {
            File file = new File(readableMap.getString("toFile"));
            URL url = new URL(readableMap.getString("fromUrl"));
            final int i = readableMap.getInt("jobId");
            ReadableMap map = readableMap.getMap("headers");
            int i2 = readableMap.getInt("progressInterval");
            int i3 = readableMap.getInt("progressDivider");
            int i4 = readableMap.getInt("readTimeout");
            int i5 = readableMap.getInt("connectionTimeout");
            boolean z = readableMap.getBoolean("hasBeginCallback");
            boolean z2 = readableMap.getBoolean("hasProgressCallback");
            DownloadParams downloadParams = new DownloadParams();
            downloadParams.src = url;
            downloadParams.dest = file;
            downloadParams.headers = map;
            downloadParams.progressInterval = i2;
            downloadParams.progressDivider = i3;
            downloadParams.readTimeout = i4;
            downloadParams.connectionTimeout = i5;
            downloadParams.onTaskCompleted = new DownloadParams.OnTaskCompleted() { // from class: com.rnfs.RNFSManager.3
                @Override // com.rnfs.DownloadParams.OnTaskCompleted
                public void onTaskCompleted(DownloadResult downloadResult) {
                    if (downloadResult.exception == null) {
                        WritableMap writableMapCreateMap = Arguments.createMap();
                        writableMapCreateMap.putInt("jobId", i);
                        writableMapCreateMap.putInt("statusCode", downloadResult.statusCode);
                        writableMapCreateMap.putDouble("bytesWritten", downloadResult.bytesWritten);
                        promise.resolve(writableMapCreateMap);
                        return;
                    }
                    RNFSManager.this.reject(promise, readableMap.getString("toFile"), downloadResult.exception);
                }
            };
            if (z) {
                downloadParams.onDownloadBegin = new DownloadParams.OnDownloadBegin() { // from class: com.rnfs.RNFSManager.4
                    @Override // com.rnfs.DownloadParams.OnDownloadBegin
                    public void onDownloadBegin(int i6, long j, Map<String, String> map2) {
                        WritableMap writableMapCreateMap = Arguments.createMap();
                        for (Map.Entry<String, String> entry : map2.entrySet()) {
                            writableMapCreateMap.putString(entry.getKey(), entry.getValue());
                        }
                        WritableMap writableMapCreateMap2 = Arguments.createMap();
                        writableMapCreateMap2.putInt("jobId", i);
                        writableMapCreateMap2.putInt("statusCode", i6);
                        writableMapCreateMap2.putDouble("contentLength", j);
                        writableMapCreateMap2.putMap("headers", writableMapCreateMap);
                        RNFSManager rNFSManager = RNFSManager.this;
                        rNFSManager.sendEvent(rNFSManager.getReactApplicationContext(), "DownloadBegin", writableMapCreateMap2);
                    }
                };
            }
            if (z2) {
                downloadParams.onDownloadProgress = new DownloadParams.OnDownloadProgress() { // from class: com.rnfs.RNFSManager.5
                    @Override // com.rnfs.DownloadParams.OnDownloadProgress
                    public void onDownloadProgress(long j, long j2) {
                        WritableMap writableMapCreateMap = Arguments.createMap();
                        writableMapCreateMap.putInt("jobId", i);
                        writableMapCreateMap.putDouble("contentLength", j);
                        writableMapCreateMap.putDouble("bytesWritten", j2);
                        RNFSManager rNFSManager = RNFSManager.this;
                        rNFSManager.sendEvent(rNFSManager.getReactApplicationContext(), "DownloadProgress", writableMapCreateMap);
                    }
                };
            }
            Downloader downloader = new Downloader();
            downloader.execute(downloadParams);
            this.downloaders.put(i, downloader);
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, readableMap.getString("toFile"), e);
        }
    }

    @ReactMethod
    public void stopDownload(int i) {
        Downloader downloader = this.downloaders.get(i);
        if (downloader != null) {
            downloader.stop();
        }
    }

    @ReactMethod
    public void uploadFiles(final ReadableMap readableMap, final Promise promise) {
        String str;
        try {
            ReadableArray array = readableMap.getArray("files");
            URL url = new URL(readableMap.getString("toUrl"));
            final int i = readableMap.getInt("jobId");
            ReadableMap map = readableMap.getMap("headers");
            ReadableMap map2 = readableMap.getMap("fields");
            String string = readableMap.getString("method");
            boolean z = readableMap.getBoolean("binaryStreamOnly");
            boolean z2 = readableMap.getBoolean("hasBeginCallback");
            boolean z3 = readableMap.getBoolean("hasProgressCallback");
            ArrayList<ReadableMap> arrayList = new ArrayList<>();
            UploadParams uploadParams = new UploadParams();
            str = "toUrl";
            for (int i2 = 0; i2 < array.size(); i2++) {
                try {
                    arrayList.add(array.getMap(i2));
                } catch (Exception e) {
                    e = e;
                    e.printStackTrace();
                    reject(promise, readableMap.getString(str), e);
                    return;
                }
            }
            uploadParams.src = url;
            uploadParams.files = arrayList;
            uploadParams.headers = map;
            uploadParams.method = string;
            uploadParams.fields = map2;
            uploadParams.binaryStreamOnly = z;
            uploadParams.onUploadComplete = new UploadParams.onUploadComplete() { // from class: com.rnfs.RNFSManager.6
                @Override // com.rnfs.UploadParams.onUploadComplete
                public void onUploadComplete(UploadResult uploadResult) {
                    if (uploadResult.exception == null) {
                        WritableMap writableMapCreateMap = Arguments.createMap();
                        writableMapCreateMap.putInt("jobId", i);
                        writableMapCreateMap.putInt("statusCode", uploadResult.statusCode);
                        writableMapCreateMap.putMap("headers", uploadResult.headers);
                        writableMapCreateMap.putString("body", uploadResult.body);
                        promise.resolve(writableMapCreateMap);
                        return;
                    }
                    RNFSManager.this.reject(promise, readableMap.getString("toUrl"), uploadResult.exception);
                }
            };
            if (z2) {
                uploadParams.onUploadBegin = new UploadParams.onUploadBegin() { // from class: com.rnfs.RNFSManager.7
                    @Override // com.rnfs.UploadParams.onUploadBegin
                    public void onUploadBegin() {
                        WritableMap writableMapCreateMap = Arguments.createMap();
                        writableMapCreateMap.putInt("jobId", i);
                        RNFSManager rNFSManager = RNFSManager.this;
                        rNFSManager.sendEvent(rNFSManager.getReactApplicationContext(), "UploadBegin", writableMapCreateMap);
                    }
                };
            }
            if (z3) {
                uploadParams.onUploadProgress = new UploadParams.onUploadProgress() { // from class: com.rnfs.RNFSManager.8
                    @Override // com.rnfs.UploadParams.onUploadProgress
                    public void onUploadProgress(int i3, int i4) {
                        WritableMap writableMapCreateMap = Arguments.createMap();
                        writableMapCreateMap.putInt("jobId", i);
                        writableMapCreateMap.putInt("totalBytesExpectedToSend", i3);
                        writableMapCreateMap.putInt("totalBytesSent", i4);
                        RNFSManager rNFSManager = RNFSManager.this;
                        rNFSManager.sendEvent(rNFSManager.getReactApplicationContext(), "UploadProgress", writableMapCreateMap);
                    }
                };
            }
            Uploader uploader = new Uploader();
            uploader.execute(uploadParams);
            this.uploaders.put(i, uploader);
        } catch (Exception e2) {
            e = e2;
            str = "toUrl";
        }
    }

    @ReactMethod
    public void stopUpload(int i) {
        Uploader uploader = this.uploaders.get(i);
        if (uploader != null) {
            uploader.stop();
        }
    }

    @ReactMethod
    public void getFSInfo(Promise promise) {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        StatFs statFs2 = new StatFs(Environment.getExternalStorageDirectory().getPath());
        long totalBytes = statFs.getTotalBytes();
        long freeBytes = statFs.getFreeBytes();
        long totalBytes2 = statFs2.getTotalBytes();
        long freeBytes2 = statFs2.getFreeBytes();
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putDouble("totalSpace", totalBytes);
        writableMapCreateMap.putDouble("freeSpace", freeBytes);
        writableMapCreateMap.putDouble("totalSpaceEx", totalBytes2);
        writableMapCreateMap.putDouble("freeSpaceEx", freeBytes2);
        promise.resolve(writableMapCreateMap);
    }

    @ReactMethod
    public void touch(String str, double d, double d2, Promise promise) {
        try {
            promise.resolve(Boolean.valueOf(new File(str).setLastModified((long) d)));
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, str, e);
        }
    }

    @ReactMethod
    public void getAllExternalFilesDirs(Promise promise) {
        File[] externalFilesDirs = getReactApplicationContext().getExternalFilesDirs(null);
        WritableArray writableArrayCreateArray = Arguments.createArray();
        for (File file : externalFilesDirs) {
            if (file != null) {
                writableArrayCreateArray.pushString(file.getAbsolutePath());
            }
        }
        promise.resolve(writableArrayCreateArray);
    }

    @ReactMethod
    public void scanFile(String str, final Promise promise) {
        MediaScannerConnection.scanFile(getReactApplicationContext(), new String[]{str}, null, new MediaScannerConnection.MediaScannerConnectionClient() { // from class: com.rnfs.RNFSManager.9
            @Override // android.media.MediaScannerConnection.MediaScannerConnectionClient
            public void onMediaScannerConnected() {
            }

            @Override // android.media.MediaScannerConnection.OnScanCompletedListener
            public void onScanCompleted(String str2, Uri uri) {
                promise.resolve(str2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reject(Promise promise, String str, Exception exc) {
        if (exc instanceof FileNotFoundException) {
            rejectFileNotFound(promise, str);
        } else if (exc instanceof IORejectionException) {
            IORejectionException iORejectionException = (IORejectionException) exc;
            promise.reject(iORejectionException.getCode(), iORejectionException.getMessage());
        } else {
            promise.reject((String) null, exc.getMessage());
        }
    }

    private void rejectFileNotFound(Promise promise, String str) {
        promise.reject("ENOENT", "ENOENT: no such file or directory, open '" + str + "'");
    }

    private void rejectFileIsDirectory(Promise promise) {
        promise.reject("EISDIR", "EISDIR: illegal operation on a directory, read");
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public Map<String, Object> getConstants() {
        HashMap map = new HashMap();
        map.put(RNFSDocumentDirectory, 0);
        map.put(RNFSDocumentDirectoryPath, getReactApplicationContext().getFilesDir().getAbsolutePath());
        map.put(RNFSTemporaryDirectoryPath, getReactApplicationContext().getCacheDir().getAbsolutePath());
        map.put(RNFSPicturesDirectoryPath, Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath());
        map.put(RNFSCachesDirectoryPath, getReactApplicationContext().getCacheDir().getAbsolutePath());
        map.put(RNFSDownloadDirectoryPath, Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath());
        map.put(RNFSFileTypeRegular, 0);
        map.put(RNFSFileTypeDirectory, 1);
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        if (externalStorageDirectory != null) {
            map.put(RNFSExternalStorageDirectoryPath, externalStorageDirectory.getAbsolutePath());
        } else {
            map.put(RNFSExternalStorageDirectoryPath, null);
        }
        File externalFilesDir = getReactApplicationContext().getExternalFilesDir(null);
        if (externalFilesDir != null) {
            map.put(RNFSExternalDirectoryPath, externalFilesDir.getAbsolutePath());
        } else {
            map.put(RNFSExternalDirectoryPath, null);
        }
        File externalCacheDir = getReactApplicationContext().getExternalCacheDir();
        if (externalCacheDir != null) {
            map.put(RNFSExternalCachesDirectoryPath, externalCacheDir.getAbsolutePath());
        } else {
            map.put(RNFSExternalCachesDirectoryPath, null);
        }
        return map;
    }
}
