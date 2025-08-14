package com.reactnativecommunity.clipboard;

import android.content.ClipData;
import android.content.ClipboardManager;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule;

@ReactModule(name = ClipboardModule.NAME)
/* loaded from: classes6.dex */
public class ClipboardModule extends ReactContextBaseJavaModule {
    public static final String CLIPBOARD_TEXT_CHANGED = "RNCClipboard_TEXT_CHANGED";
    public static final String MIMETYPE_HEIC = "image/heic";
    public static final String MIMETYPE_HEIF = "image/heif";
    public static final String MIMETYPE_JPEG = "image/jpeg";
    public static final String MIMETYPE_JPG = "image/jpg";
    public static final String MIMETYPE_PNG = "image/png";
    public static final String MIMETYPE_WEBP = "image/webp";
    public static final String NAME = "RNCClipboard";
    private ClipboardManager.OnPrimaryClipChangedListener listener;
    private ReactApplicationContext reactContext;

    @ReactMethod
    public void addListener(String str) {
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @ReactMethod
    public void removeListeners(Integer num) {
    }

    public ClipboardModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.listener = null;
        this.reactContext = reactApplicationContext;
    }

    private ClipboardManager getClipboardService() {
        return (ClipboardManager) this.reactContext.getSystemService("clipboard");
    }

    @ReactMethod
    public void getString(Promise promise) {
        try {
            ClipboardManager clipboardService = getClipboardService();
            ClipData primaryClip = clipboardService.getPrimaryClip();
            if (primaryClip != null && primaryClip.getItemCount() >= 1) {
                promise.resolve("" + ((Object) clipboardService.getPrimaryClip().getItemAt(0).getText()));
            } else {
                promise.resolve("");
            }
        } catch (Exception e) {
            promise.reject(e);
        }
    }

    @ReactMethod
    public void setString(String str) {
        try {
            getClipboardService().setPrimaryClip(ClipData.newPlainText(null, str));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0012  */
    @com.facebook.react.bridge.ReactMethod
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void hasString(com.facebook.react.bridge.Promise r3) {
        /*
            r2 = this;
            android.content.ClipboardManager r0 = r2.getClipboardService()     // Catch: java.lang.Exception -> L1b
            android.content.ClipData r0 = r0.getPrimaryClip()     // Catch: java.lang.Exception -> L1b
            if (r0 == 0) goto L12
            int r0 = r0.getItemCount()     // Catch: java.lang.Exception -> L1b
            r1 = 1
            if (r0 < r1) goto L12
            goto L13
        L12:
            r1 = 0
        L13:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r1)     // Catch: java.lang.Exception -> L1b
            r3.resolve(r0)     // Catch: java.lang.Exception -> L1b
            goto L1f
        L1b:
            r0 = move-exception
            r3.reject(r0)
        L1f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reactnativecommunity.clipboard.ClipboardModule.hasString(com.facebook.react.bridge.Promise):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x0093  */
    @com.facebook.react.bridge.ReactMethod
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void getImage(com.facebook.react.bridge.Promise r14) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 264
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reactnativecommunity.clipboard.ClipboardModule.getImage(com.facebook.react.bridge.Promise):void");
    }

    @ReactMethod
    public void setListener() {
        try {
            ClipboardManager clipboardService = getClipboardService();
            ClipboardManager.OnPrimaryClipChangedListener onPrimaryClipChangedListener = new ClipboardManager.OnPrimaryClipChangedListener() { // from class: com.reactnativecommunity.clipboard.ClipboardModule.1
                @Override // android.content.ClipboardManager.OnPrimaryClipChangedListener
                public void onPrimaryClipChanged() {
                    ((DeviceEventManagerModule.RCTDeviceEventEmitter) ClipboardModule.this.reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(ClipboardModule.CLIPBOARD_TEXT_CHANGED, null);
                }
            };
            this.listener = onPrimaryClipChangedListener;
            clipboardService.addPrimaryClipChangedListener(onPrimaryClipChangedListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ReactMethod
    public void removeListener() {
        if (this.listener != null) {
            try {
                getClipboardService().removePrimaryClipChangedListener(this.listener);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
