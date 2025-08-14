package io.sentry.react;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import io.sentry.ILogger;
import io.sentry.SentryDate;
import io.sentry.SentryDateProvider;
import io.sentry.SentryLevel;
import io.sentry.android.core.AndroidLogger;
import io.sentry.android.core.BuildInfoProvider;
import io.sentry.android.core.SentryAndroidDateProvider;
import io.sentry.android.core.internal.util.FirstDrawDoneListener;
import java.util.Map;

/* loaded from: classes6.dex */
public class RNSentryOnDrawReporterManager extends SimpleViewManager<RNSentryOnDrawReporterView> {
    public static final String REACT_CLASS = "RNSentryOnDrawReporter";
    private final ReactApplicationContext mCallerContext;

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    public RNSentryOnDrawReporterManager(ReactApplicationContext reactApplicationContext) {
        this.mCallerContext = reactApplicationContext;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public RNSentryOnDrawReporterView createViewInstance(ThemedReactContext themedReactContext) {
        return new RNSentryOnDrawReporterView(this.mCallerContext, new BuildInfoProvider(new AndroidLogger()));
    }

    @ReactProp(defaultBoolean = false, name = "initialDisplay")
    public void setInitialDisplay(RNSentryOnDrawReporterView rNSentryOnDrawReporterView, boolean z) {
        rNSentryOnDrawReporterView.setInitialDisplay(z);
    }

    @ReactProp(defaultBoolean = false, name = "fullDisplay")
    public void setFullDisplay(RNSentryOnDrawReporterView rNSentryOnDrawReporterView, boolean z) {
        rNSentryOnDrawReporterView.setFullDisplay(z);
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public Map getExportedCustomBubblingEventTypeConstants() {
        return MapBuilder.builder().put("onDrawNextFrameView", MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onDrawNextFrame"))).build();
    }

    public static class RNSentryOnDrawReporterView extends View {
        private static final ILogger logger = new AndroidLogger("RNSentryOnDrawReporterView");
        private final BuildInfoProvider buildInfo;
        private final SentryDateProvider dateProvider;
        private final Runnable emitFullDisplayEvent;
        private final Runnable emitInitialDisplayEvent;
        private final ReactApplicationContext reactContext;

        public RNSentryOnDrawReporterView(Context context) {
            super(context);
            this.dateProvider = new SentryAndroidDateProvider();
            this.reactContext = null;
            this.buildInfo = null;
            this.emitInitialDisplayEvent = null;
            this.emitFullDisplayEvent = null;
        }

        public RNSentryOnDrawReporterView(ReactApplicationContext reactApplicationContext, BuildInfoProvider buildInfoProvider) {
            super(reactApplicationContext);
            this.dateProvider = new SentryAndroidDateProvider();
            this.reactContext = reactApplicationContext;
            this.buildInfo = buildInfoProvider;
            this.emitInitialDisplayEvent = new Runnable() { // from class: io.sentry.react.RNSentryOnDrawReporterManager$RNSentryOnDrawReporterView$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$new$0();
                }
            };
            this.emitFullDisplayEvent = new Runnable() { // from class: io.sentry.react.RNSentryOnDrawReporterManager$RNSentryOnDrawReporterView$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$new$1();
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$new$0() {
            emitDisplayEvent("initialDisplay");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$new$1() {
            emitDisplayEvent("fullDisplay");
        }

        public void setFullDisplay(boolean z) {
            if (z) {
                logger.log(SentryLevel.DEBUG, "[TimeToDisplay] Register full display event emitter.", new Object[0]);
                registerForNextDraw(this.emitFullDisplayEvent);
            }
        }

        public void setInitialDisplay(boolean z) {
            if (z) {
                logger.log(SentryLevel.DEBUG, "[TimeToDisplay] Register initial display event emitter.", new Object[0]);
                registerForNextDraw(this.emitInitialDisplayEvent);
            }
        }

        private void registerForNextDraw(Runnable runnable) {
            if (runnable == null) {
                logger.log(SentryLevel.ERROR, "[TimeToDisplay] Won't emit next frame drawn event, emitter is null.", new Object[0]);
                return;
            }
            if (this.buildInfo == null) {
                logger.log(SentryLevel.ERROR, "[TimeToDisplay] Won't emit next frame drawn event, buildInfo is null.", new Object[0]);
                return;
            }
            ReactApplicationContext reactApplicationContext = this.reactContext;
            if (reactApplicationContext == null) {
                logger.log(SentryLevel.ERROR, "[TimeToDisplay] Won't emit next frame drawn event, reactContext is null.", new Object[0]);
                return;
            }
            Activity currentActivity = reactApplicationContext.getCurrentActivity();
            if (currentActivity == null) {
                logger.log(SentryLevel.ERROR, "[TimeToDisplay] Won't emit next frame drawn event, reactContext is null.", new Object[0]);
            } else {
                FirstDrawDoneListener.registerForNextDraw(currentActivity, runnable, this.buildInfo);
            }
        }

        private void emitDisplayEvent(String str) {
            SentryDate sentryDateNow = this.dateProvider.now();
            WritableMap writableMapCreateMap = Arguments.createMap();
            writableMapCreateMap.putString("type", str);
            writableMapCreateMap.putDouble("newFrameTimestampInSeconds", sentryDateNow.nanoTimestamp() / 1.0E9d);
            ReactApplicationContext reactApplicationContext = this.reactContext;
            if (reactApplicationContext == null) {
                logger.log(SentryLevel.ERROR, "[TimeToDisplay] Recorded next frame draw but can't emit the event, reactContext is null.", new Object[0]);
            } else {
                ((RCTEventEmitter) reactApplicationContext.getJSModule(RCTEventEmitter.class)).receiveEvent(getId(), "onDrawNextFrameView", writableMapCreateMap);
            }
        }
    }
}
