package com.rncamerakit;

import android.view.View;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.UIManagerModule;
import io.sentry.rrweb.RRWebOptionsEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RNCameraKitModule.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J \u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0014\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000eH\u0016J\b\u0010\u0011\u001a\u00020\u000fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/rncamerakit/RNCameraKitModule;", "Lcom/facebook/react/bridge/ReactContextBaseJavaModule;", "reactContext", "Lcom/facebook/react/bridge/ReactApplicationContext;", "(Lcom/facebook/react/bridge/ReactApplicationContext;)V", "capture", "", RRWebOptionsEvent.EVENT_TAG, "Lcom/facebook/react/bridge/ReadableMap;", "viewTag", "", BaseJavaModule.METHOD_TYPE_PROMISE, "Lcom/facebook/react/bridge/Promise;", "getConstants", "", "", "", "getName", "Companion", "react-native-camera-kit_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class RNCameraKitModule extends ReactContextBaseJavaModule {
    public static final int LANDSCAPE_LEFT = 1;
    public static final int LANDSCAPE_RIGHT = 3;
    public static final int PORTRAIT = 0;
    public static final int PORTRAIT_UPSIDE_DOWN = 2;
    private final ReactApplicationContext reactContext;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "RNCameraKitModule";
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RNCameraKitModule(ReactApplicationContext reactContext) {
        super(reactContext);
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        this.reactContext = reactContext;
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public Map<String, Object> getConstants() {
        return MapsKt.hashMapOf(TuplesKt.to("PORTRAIT", 0), TuplesKt.to("PORTRAIT_UPSIDE_DOWN", 2), TuplesKt.to("LANDSCAPE_LEFT", 1), TuplesKt.to("LANDSCAPE_RIGHT", 3));
    }

    @ReactMethod
    public final void capture(final ReadableMap options, final int viewTag, final Promise promise) {
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(promise, "promise");
        ReactApplicationContext reactApplicationContext = this.reactContext;
        final UIManagerModule uIManagerModule = (UIManagerModule) reactApplicationContext.getNativeModule(UIManagerModule.class);
        reactApplicationContext.runOnUiQueueThread(new Runnable() { // from class: com.rncamerakit.RNCameraKitModule$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() throws IOException {
                RNCameraKitModule.capture$lambda$0(uIManagerModule, viewTag, options, promise);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void capture$lambda$0(UIManagerModule uIManagerModule, int i, ReadableMap options, Promise promise) throws IOException {
        Intrinsics.checkNotNullParameter(options, "$options");
        Intrinsics.checkNotNullParameter(promise, "$promise");
        View viewResolveView = uIManagerModule != null ? uIManagerModule.resolveView(i) : null;
        Intrinsics.checkNotNull(viewResolveView, "null cannot be cast to non-null type com.rncamerakit.CKCamera");
        HashMap<String, Object> hashMap = options.toHashMap();
        Intrinsics.checkNotNullExpressionValue(hashMap, "toHashMap(...)");
        ((CKCamera) viewResolveView).capture(hashMap, promise);
    }
}
