package com.fastopenpgp;

import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.JavaScriptContextHolder;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableArray;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FastOpenpgpModule.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\t\n\u0002\b\b\b\u0000\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J \u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007J!\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0011H\u0086 J \u0010\u0010\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007J\u0019\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0011H\u0086 J\t\u0010\u0015\u001a\u00020\nH\u0086 J\b\u0010\u0016\u001a\u00020\u0006H\u0016J\u0011\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u0013H\u0086 J\u0010\u0010\u0018\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007J\b\u0010\u0019\u001a\u00020\nH\u0016R\u0014\u0010\u0005\u001a\u00020\u0006X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u001b"}, d2 = {"Lcom/fastopenpgp/FastOpenpgpModule;", "Lcom/facebook/react/bridge/ReactContextBaseJavaModule;", "reactContext", "Lcom/facebook/react/bridge/ReactApplicationContext;", "(Lcom/facebook/react/bridge/ReactApplicationContext;)V", "TAG", "", "getTAG", "()Ljava/lang/String;", NotificationCompat.CATEGORY_CALL, "", "name", "payload", "Lcom/facebook/react/bridge/ReadableArray;", BaseJavaModule.METHOD_TYPE_PROMISE, "Lcom/facebook/react/bridge/Promise;", "callJSI", "", "jsiPtr", "", "callNative", "destruct", "getName", "initialize", "install", "onCatalystInstanceDestroy", "Companion", "react-native-fast-openpgp_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class FastOpenpgpModule extends ReactContextBaseJavaModule {
    private final String TAG;

    public final native byte[] callJSI(long jsiPtr, String name, byte[] payload);

    public final native byte[] callNative(String name, byte[] payload);

    public final native void destruct();

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "FastOpenpgp";
    }

    public final String getTAG() {
        return this.TAG;
    }

    public final native void initialize(long jsiPtr);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FastOpenpgpModule(ReactApplicationContext reactContext) {
        super(reactContext);
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        this.TAG = "[FastOpenPGPModule]";
    }

    static {
        System.loadLibrary("fast-openpgp");
    }

    @ReactMethod
    public final void callJSI(final String name, final ReadableArray payload, final Promise promise) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(payload, "payload");
        Intrinsics.checkNotNullParameter(promise, "promise");
        new Thread(new Runnable() { // from class: com.fastopenpgp.FastOpenpgpModule$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                FastOpenpgpModule.callJSI$lambda$1(this.f$0, name, payload, promise);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void callJSI$lambda$1(final FastOpenpgpModule this$0, final String name, final ReadableArray payload, final Promise promise) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(name, "$name");
        Intrinsics.checkNotNullParameter(payload, "$payload");
        Intrinsics.checkNotNullParameter(promise, "$promise");
        this$0.getReactApplicationContext().runOnJSQueueThread(new Runnable() { // from class: com.fastopenpgp.FastOpenpgpModule$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                FastOpenpgpModule.callJSI$lambda$1$lambda$0(this.f$0, name, payload, promise);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void callJSI$lambda$1$lambda$0(FastOpenpgpModule this$0, String name, ReadableArray payload, Promise promise) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(name, "$name");
        Intrinsics.checkNotNullParameter(payload, "$payload");
        Intrinsics.checkNotNullParameter(promise, "$promise");
        try {
            JavaScriptContextHolder javaScriptContextHolder = this$0.getReactApplicationContext().getJavaScriptContextHolder();
            Intrinsics.checkNotNull(javaScriptContextHolder);
            long j = javaScriptContextHolder.get();
            if (((int) j) == 0) {
                this$0.call(name, payload, promise);
                return;
            }
            int size = payload.size();
            byte[] bArr = new byte[size];
            for (int i = 0; i < size; i++) {
                bArr[i] = (byte) payload.getInt(i);
            }
            byte[] bArrCallJSI = this$0.callJSI(j, name, bArr);
            WritableArray writableArrayCreateArray = Arguments.createArray();
            for (byte b : bArrCallJSI) {
                writableArrayCreateArray.pushInt(b);
            }
            promise.resolve(writableArrayCreateArray);
        } catch (Exception e) {
            promise.reject(e);
        }
    }

    @ReactMethod
    public final void call(final String name, final ReadableArray payload, final Promise promise) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(payload, "payload");
        Intrinsics.checkNotNullParameter(promise, "promise");
        new Thread(new Runnable() { // from class: com.fastopenpgp.FastOpenpgpModule$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                FastOpenpgpModule.call$lambda$2(payload, this, name, promise);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void call$lambda$2(ReadableArray payload, FastOpenpgpModule this$0, String name, Promise promise) {
        Intrinsics.checkNotNullParameter(payload, "$payload");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(name, "$name");
        Intrinsics.checkNotNullParameter(promise, "$promise");
        try {
            int size = payload.size();
            byte[] bArr = new byte[size];
            for (int i = 0; i < size; i++) {
                bArr[i] = (byte) payload.getInt(i);
            }
            byte[] bArrCallNative = this$0.callNative(name, bArr);
            WritableArray writableArrayCreateArray = Arguments.createArray();
            for (byte b : bArrCallNative) {
                writableArrayCreateArray.pushInt(b);
            }
            promise.resolve(writableArrayCreateArray);
        } catch (Exception e) {
            promise.reject(e);
        }
    }

    @ReactMethod
    public final void install(final Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        new Thread(new Runnable() { // from class: com.fastopenpgp.FastOpenpgpModule$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                FastOpenpgpModule.install$lambda$4(this.f$0, promise);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void install$lambda$4(final FastOpenpgpModule this$0, final Promise promise) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(promise, "$promise");
        this$0.getReactApplicationContext().runOnJSQueueThread(new Runnable() { // from class: com.fastopenpgp.FastOpenpgpModule$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                FastOpenpgpModule.install$lambda$4$lambda$3(this.f$0, promise);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void install$lambda$4$lambda$3(FastOpenpgpModule this$0, Promise promise) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(promise, "$promise");
        Log.d(this$0.TAG, "installing");
        try {
            JavaScriptContextHolder javaScriptContextHolder = this$0.getReactApplicationContext().getJavaScriptContextHolder();
            Intrinsics.checkNotNull(javaScriptContextHolder);
            long j = javaScriptContextHolder.get();
            if (((int) j) == 0) {
                promise.resolve(false);
                return;
            }
            this$0.initialize(j);
            Log.i(this$0.TAG, "successfully installed");
            promise.resolve(true);
        } catch (Exception e) {
            Exception exc = e;
            Log.e(this$0.TAG, "failed to install JSI", exc);
            promise.reject(exc);
        }
    }

    @Override // com.facebook.react.bridge.NativeModule
    public void onCatalystInstanceDestroy() {
        destruct();
    }
}
