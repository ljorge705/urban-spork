package com.ReactNativeBlobUtil;

import android.net.Uri;
import android.os.SystemClock;
import android.util.Base64;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.uimanager.ViewProps;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.UUID;

/* loaded from: classes5.dex */
public class ReactNativeBlobUtilStream {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final HashMap<String, ReactNativeBlobUtilStream> fileStreams = new HashMap<>();
    private final DeviceEventManagerModule.RCTDeviceEventEmitter emitter;
    private String encoding = "base64";
    private OutputStream writeStreamInstance = null;

    ReactNativeBlobUtilStream(ReactApplicationContext reactApplicationContext) {
        this.emitter = (DeviceEventManagerModule.RCTDeviceEventEmitter) reactApplicationContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class);
    }

    void readStream(String str, String str2, int i, int i2, String str3, ReactApplicationContext reactApplicationContext) {
        InputStream fileInputStream;
        String strNormalizePath = ReactNativeBlobUtilUtils.normalizePath(str);
        String str4 = strNormalizePath != null ? strNormalizePath : str;
        try {
            int i3 = str2.equalsIgnoreCase("base64") ? 4095 : 4096;
            if (i > 0) {
                i3 = i;
            }
            if (strNormalizePath != null && str4.startsWith(ReactNativeBlobUtilConst.FILE_PREFIX_BUNDLE_ASSET)) {
                fileInputStream = ReactNativeBlobUtilImpl.RCTContext.getAssets().open(str4.replace(ReactNativeBlobUtilConst.FILE_PREFIX_BUNDLE_ASSET, ""));
            } else if (strNormalizePath == null) {
                fileInputStream = ReactNativeBlobUtilImpl.RCTContext.getContentResolver().openInputStream(Uri.parse(str4));
            } else {
                fileInputStream = new FileInputStream(new File(str4));
            }
            if (str2.equalsIgnoreCase(ReactNativeBlobUtilConst.RNFB_RESPONSE_UTF8)) {
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, Charset.forName("UTF-8"));
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader, i3);
                char[] cArr = new char[i3];
                for (int i4 = 0; bufferedReader.read(cArr, i4, i3) != -1; i4 = 0) {
                    emitStreamEvent(str3, "data", new String(cArr));
                    if (i2 > 0) {
                        SystemClock.sleep(i2);
                    }
                }
                bufferedReader.close();
                inputStreamReader.close();
            } else if (str2.equalsIgnoreCase("ascii")) {
                byte[] bArr = new byte[i3];
                while (true) {
                    int i5 = fileInputStream.read(bArr);
                    if (i5 == -1) {
                        break;
                    }
                    WritableArray writableArrayCreateArray = Arguments.createArray();
                    for (int i6 = 0; i6 < i5; i6++) {
                        writableArrayCreateArray.pushInt(bArr[i6]);
                    }
                    emitStreamEvent(str3, "data", writableArrayCreateArray);
                    if (i2 > 0) {
                        SystemClock.sleep(i2);
                    }
                }
            } else if (str2.equalsIgnoreCase("base64")) {
                byte[] bArr2 = new byte[i3];
                while (true) {
                    int i7 = fileInputStream.read(bArr2);
                    if (i7 == -1) {
                        break;
                    }
                    if (i7 < i3) {
                        byte[] bArr3 = new byte[i7];
                        System.arraycopy(bArr2, 0, bArr3, 0, i7);
                        emitStreamEvent(str3, "data", Base64.encodeToString(bArr3, 2));
                    } else {
                        emitStreamEvent(str3, "data", Base64.encodeToString(bArr2, 2));
                    }
                    if (i2 > 0) {
                        SystemClock.sleep(i2);
                    }
                }
            } else {
                emitStreamEvent(str3, "error", "EINVAL", "Unrecognized encoding `" + str2 + "`, should be one of `base64`, `utf8`, `ascii`");
                fileInputStream.close();
            }
            emitStreamEvent(str3, ViewProps.END, "");
            fileInputStream.close();
        } catch (FileNotFoundException unused) {
            emitStreamEvent(str3, "error", "ENOENT", "No such file '" + str4 + "'");
        } catch (Exception e) {
            emitStreamEvent(str3, "error", "EUNSPECIFIED", "Failed to convert data to " + str2 + " encoded string. This might be because this encoding cannot be used for this data.");
            e.printStackTrace();
        }
    }

    void writeStream(String str, String str2, boolean z, Callback callback) {
        try {
            File file = new File(str);
            File parentFile = file.getParentFile();
            if (!file.exists()) {
                if (parentFile != null && !parentFile.exists() && !parentFile.mkdirs()) {
                    callback.invoke("ENOTDIR", "Failed to create parent directory of '" + str + "'");
                    return;
                } else if (!file.createNewFile()) {
                    callback.invoke("ENOENT", "File '" + str + "' does not exist and could not be created");
                    return;
                }
            } else if (file.isDirectory()) {
                callback.invoke("EISDIR", "Expecting a file but '" + str + "' is a directory");
                return;
            }
            FileOutputStream fileOutputStream = new FileOutputStream(str, z);
            this.encoding = str2;
            String string = UUID.randomUUID().toString();
            fileStreams.put(string, this);
            this.writeStreamInstance = fileOutputStream;
            callback.invoke(null, null, string);
        } catch (Exception e) {
            callback.invoke("EUNSPECIFIED", "Failed to create write stream at path `" + str + "`; " + e.getLocalizedMessage());
        }
    }

    static void writeChunk(String str, String str2, Callback callback) {
        ReactNativeBlobUtilStream reactNativeBlobUtilStream = fileStreams.get(str);
        try {
            reactNativeBlobUtilStream.writeStreamInstance.write(ReactNativeBlobUtilUtils.stringToBytes(str2, reactNativeBlobUtilStream.encoding));
            callback.invoke(new Object[0]);
        } catch (Exception e) {
            callback.invoke(e.getLocalizedMessage());
        }
    }

    static void writeArrayChunk(String str, ReadableArray readableArray, Callback callback) {
        try {
            OutputStream outputStream = fileStreams.get(str).writeStreamInstance;
            byte[] bArr = new byte[readableArray.size()];
            for (int i = 0; i < readableArray.size(); i++) {
                bArr[i] = (byte) readableArray.getInt(i);
            }
            outputStream.write(bArr);
            callback.invoke(new Object[0]);
        } catch (Exception e) {
            callback.invoke(e.getLocalizedMessage());
        }
    }

    static void closeStream(String str, Callback callback) {
        try {
            HashMap<String, ReactNativeBlobUtilStream> map = fileStreams;
            OutputStream outputStream = map.get(str).writeStreamInstance;
            map.remove(str);
            outputStream.close();
            callback.invoke(new Object[0]);
        } catch (Exception e) {
            callback.invoke(e.getLocalizedMessage());
        }
    }

    private void emitStreamEvent(String str, String str2, String str3) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString("event", str2);
        writableMapCreateMap.putString("detail", str3);
        writableMapCreateMap.putString("streamId", str);
        this.emitter.emit(ReactNativeBlobUtilConst.EVENT_FILESYSTEM, writableMapCreateMap);
    }

    private void emitStreamEvent(String str, String str2, WritableArray writableArray) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString("event", str2);
        writableMapCreateMap.putArray("detail", writableArray);
        writableMapCreateMap.putString("streamId", str);
        this.emitter.emit(ReactNativeBlobUtilConst.EVENT_FILESYSTEM, writableMapCreateMap);
    }

    private void emitStreamEvent(String str, String str2, String str3, String str4) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString("event", str2);
        writableMapCreateMap.putString("code", str3);
        writableMapCreateMap.putString("detail", str4);
        writableMapCreateMap.putString("streamId", str);
        this.emitter.emit(ReactNativeBlobUtilConst.EVENT_FILESYSTEM, writableMapCreateMap);
    }

    public static InputStream inputStreamFromPath(String str) throws IOException {
        if (str.startsWith(ReactNativeBlobUtilConst.FILE_PREFIX_BUNDLE_ASSET)) {
            return ReactNativeBlobUtilImpl.RCTContext.getAssets().open(str.replace(ReactNativeBlobUtilConst.FILE_PREFIX_BUNDLE_ASSET, ""));
        }
        return new FileInputStream(new File(str));
    }
}
