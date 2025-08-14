package fr.greweb.reactnativeviewshot;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import com.facebook.react.bridge.GuardedAsyncTask;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.UIManagerModule;
import com.onfido.android.sdk.capture.core.OnfidoLauncher;
import com.onfido.reactnative.sdk.ReactNativeBridgeUtiles;
import fr.greweb.reactnativeviewshot.ViewShot;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/* loaded from: classes6.dex */
public class RNViewShotModule extends ReactContextBaseJavaModule {
    public static final String RNVIEW_SHOT = "RNViewShot";
    private static final String TEMP_FILE_PREFIX = "ReactNative-snapshot-image";
    private final Executor executor;
    private final ReactApplicationContext reactContext;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return RNVIEW_SHOT;
    }

    public RNViewShotModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.executor = Executors.newCachedThreadPool();
        this.reactContext = reactApplicationContext;
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public Map<String, Object> getConstants() {
        return Collections.emptyMap();
    }

    @Override // com.facebook.react.bridge.NativeModule
    public void onCatalystInstanceDestroy() {
        super.onCatalystInstanceDestroy();
        new CleanTask(getReactApplicationContext()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    @ReactMethod
    public void releaseCapture(String str) {
        String path = Uri.parse(str).getPath();
        if (path == null) {
            return;
        }
        File file = new File(path);
        if (file.exists()) {
            File parentFile = file.getParentFile();
            if (parentFile.equals(this.reactContext.getExternalCacheDir()) || parentFile.equals(this.reactContext.getCacheDir())) {
                file.delete();
            }
        }
    }

    @ReactMethod
    public void captureRef(int i, ReadableMap readableMap, Promise promise) {
        int i2;
        int i3;
        getReactApplicationContext().getResources().getDisplayMetrics();
        String string = readableMap.getString("format");
        if ("jpg".equals(string)) {
            i2 = 0;
        } else {
            if ("webm".equals(string)) {
                i3 = 2;
            } else if ("raw".equals(string)) {
                i3 = -1;
            } else {
                i2 = 1;
            }
            i2 = i3;
        }
        double d = readableMap.getDouble("quality");
        Integer numValueOf = readableMap.hasKey("width") ? Integer.valueOf(readableMap.getInt("width")) : null;
        Integer numValueOf2 = readableMap.hasKey("height") ? Integer.valueOf(readableMap.getInt("height")) : null;
        String string2 = readableMap.getString(OnfidoLauncher.KEY_RESULT);
        String string3 = readableMap.hasKey(ReactNativeBridgeUtiles.KEY_FILE_NAME) ? readableMap.getString(ReactNativeBridgeUtiles.KEY_FILE_NAME) : null;
        try {
            ((UIManagerModule) this.reactContext.getNativeModule(UIManagerModule.class)).addUIBlock(new ViewShot(i, string, i2, d, numValueOf, numValueOf2, ViewShot.Results.TEMP_FILE.equals(string2) ? createTempFile(getReactApplicationContext(), string, string3) : null, string2, Boolean.valueOf(readableMap.getBoolean("snapshotContentContainer")), this.reactContext, getCurrentActivity(), readableMap.hasKey("handleGLSurfaceViewOnAndroid") && readableMap.getBoolean("handleGLSurfaceViewOnAndroid"), promise, this.executor));
        } catch (Throwable th) {
            Log.e(RNVIEW_SHOT, "Failed to snapshot view tag " + i, th);
            promise.reject(ViewShot.ERROR_UNABLE_TO_SNAPSHOT, "Failed to snapshot view tag " + i);
        }
    }

    @ReactMethod
    public void captureScreen(ReadableMap readableMap, Promise promise) {
        captureRef(-1, readableMap, promise);
    }

    private static class CleanTask extends GuardedAsyncTask<Void, Void> implements FilenameFilter {
        private final File cacheDir;
        private final File externalCacheDir;

        private CleanTask(ReactContext reactContext) {
            super(reactContext);
            this.cacheDir = reactContext.getCacheDir();
            this.externalCacheDir = reactContext.getExternalCacheDir();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.facebook.react.bridge.GuardedAsyncTask
        public void doInBackgroundGuarded(Void... voidArr) {
            File file = this.cacheDir;
            if (file != null) {
                cleanDirectory(file);
            }
            File file2 = this.externalCacheDir;
            if (file2 != null) {
                cleanDirectory(file2);
            }
        }

        @Override // java.io.FilenameFilter
        public final boolean accept(File file, String str) {
            return str.startsWith(RNViewShotModule.TEMP_FILE_PREFIX);
        }

        private void cleanDirectory(File file) {
            File[] fileArrListFiles = file.listFiles(this);
            if (fileArrListFiles != null) {
                for (File file2 : fileArrListFiles) {
                    if (file2.delete()) {
                        Log.d(RNViewShotModule.RNVIEW_SHOT, "deleted file: " + file2.getAbsolutePath());
                    }
                }
            }
        }
    }

    private File createTempFile(Context context, String str, String str2) throws IOException {
        File externalCacheDir = context.getExternalCacheDir();
        File cacheDir = context.getCacheDir();
        if (externalCacheDir == null && cacheDir == null) {
            throw new IOException("No cache directory available");
        }
        if (externalCacheDir == null || (cacheDir != null && externalCacheDir.getFreeSpace() <= cacheDir.getFreeSpace())) {
            externalCacheDir = cacheDir;
        }
        String str3 = "." + str;
        if (str2 != null) {
            return File.createTempFile(str2, str3, externalCacheDir);
        }
        return File.createTempFile(TEMP_FILE_PREFIX, str3, externalCacheDir);
    }
}
