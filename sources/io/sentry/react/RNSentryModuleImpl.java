package io.sentry.react;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.SparseIntArray;
import androidx.core.app.FrameMetricsAggregator;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import app.notifee.core.event.LogEvent;
import com.clevertap.android.sdk.Constants;
import com.facebook.hermes.instrumentation.HermesSamplingProfiler;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.common.JavascriptException;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.reactnativecommunity.clipboard.ClipboardModule;
import io.sentry.Breadcrumb;
import io.sentry.Hint;
import io.sentry.HubAdapter;
import io.sentry.ILogger;
import io.sentry.IScope;
import io.sentry.ISentryExecutorService;
import io.sentry.Integration;
import io.sentry.ProfilingTraceData;
import io.sentry.ScopeCallback;
import io.sentry.Sentry;
import io.sentry.SentryBaseEvent;
import io.sentry.SentryDate;
import io.sentry.SentryDateProvider;
import io.sentry.SentryEvent;
import io.sentry.SentryExecutorService;
import io.sentry.SentryLevel;
import io.sentry.SentryOptions;
import io.sentry.SentryReplayOptions;
import io.sentry.Session;
import io.sentry.UncaughtExceptionHandlerIntegration;
import io.sentry.android.core.AndroidLogger;
import io.sentry.android.core.AndroidProfiler;
import io.sentry.android.core.AnrIntegration;
import io.sentry.android.core.BuildInfoProvider;
import io.sentry.android.core.CurrentActivityHolder;
import io.sentry.android.core.InternalSentrySdk;
import io.sentry.android.core.NdkIntegration;
import io.sentry.android.core.SentryAndroid;
import io.sentry.android.core.SentryAndroidDateProvider;
import io.sentry.android.core.SentryAndroidOptions;
import io.sentry.android.core.ViewHierarchyEventProcessor;
import io.sentry.android.core.internal.debugmeta.AssetsDebugMetaLoader;
import io.sentry.android.core.internal.util.ScreenshotUtils;
import io.sentry.android.core.internal.util.SentryFrameMetricsCollector;
import io.sentry.android.core.performance.AppStartMetrics;
import io.sentry.protocol.OperatingSystem;
import io.sentry.protocol.SdkVersion;
import io.sentry.protocol.SentryId;
import io.sentry.protocol.SentryPackage;
import io.sentry.protocol.SentryStackFrame;
import io.sentry.protocol.User;
import io.sentry.protocol.ViewHierarchy;
import io.sentry.react.replay.RNSentryReplayMask;
import io.sentry.react.replay.RNSentryReplayUnmask;
import io.sentry.util.DebugMetaPropertiesApplier;
import io.sentry.util.FileUtils;
import io.sentry.util.JsonSerializationUtils;
import io.sentry.vendor.Base64;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.spongycastle.cms.CMSAttributeTableGenerator;

/* loaded from: classes6.dex */
public class RNSentryModuleImpl {
    private static final int FROZEN_FRAME_THRESHOLD = 700;
    public static final String NAME = "RNSentry";
    private static final int SCREENSHOT_TIMEOUT_SECONDS = 2;
    private static final int SLOW_FRAME_THRESHOLD = 16;
    private static final Charset UTF_8;
    private static final BuildInfoProvider buildInfo;
    private static boolean hasFetchedAppStart = false;
    private static final ILogger logger;
    private static final String modulesPath = "modules.json";
    private boolean androidXAvailable;
    private final PackageInfo packageInfo;
    private final ReactApplicationContext reactApplicationContext;
    private FrameMetricsAggregator frameMetricsAggregator = null;
    private int profilingTracesHz = 101;
    private AndroidProfiler androidProfiler = null;
    private boolean isProguardDebugMetaLoaded = false;
    private String proguardUuid = null;
    private String cacheDirPath = null;
    private ISentryExecutorService executorService = null;
    private long maxTraceFileSize = 5242880;
    private final Runnable emitNewFrameEvent = createEmitNewFrameEvent();
    private final SentryDateProvider dateProvider = new SentryAndroidDateProvider();

    private ReactApplicationContext getReactApplicationContext() {
        return this.reactApplicationContext;
    }

    private boolean isFrameMetricsAggregatorAvailable() {
        return this.androidXAvailable && this.frameMetricsAggregator != null;
    }

    static {
        AndroidLogger androidLogger = new AndroidLogger(NAME);
        logger = androidLogger;
        buildInfo = new BuildInfoProvider(androidLogger);
        UTF_8 = Charset.forName("UTF-8");
    }

    public RNSentryModuleImpl(ReactApplicationContext reactApplicationContext) {
        this.packageInfo = getPackageInfo(reactApplicationContext);
        this.reactApplicationContext = reactApplicationContext;
    }

    private Activity getCurrentActivity() {
        return this.reactApplicationContext.getCurrentActivity();
    }

    private Runnable createEmitNewFrameEvent() {
        return new Runnable() { // from class: io.sentry.react.RNSentryModuleImpl$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$createEmitNewFrameEvent$0();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$createEmitNewFrameEvent$0() {
        SentryDate sentryDateNow = this.dateProvider.now();
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putDouble("newFrameTimestampInSeconds", sentryDateNow.nanoTimestamp() / 1.0E9d);
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("rn_sentry_new_frame", writableMapCreateMap);
    }

    private void initFragmentInitialFrameTracking() {
        FragmentManager supportFragmentManager;
        RNSentryReactFragmentLifecycleTracer rNSentryReactFragmentLifecycleTracer = new RNSentryReactFragmentLifecycleTracer(buildInfo, this.emitNewFrameEvent, logger);
        FragmentActivity fragmentActivity = (FragmentActivity) getCurrentActivity();
        if (fragmentActivity == null || (supportFragmentManager = fragmentActivity.getSupportFragmentManager()) == null) {
            return;
        }
        supportFragmentManager.registerFragmentLifecycleCallbacks(rNSentryReactFragmentLifecycleTracer, true);
    }

    public void initNativeReactNavigationNewFrameTracking(Promise promise) {
        initFragmentInitialFrameTracking();
    }

    public void initNativeSdk(final ReadableMap readableMap, Promise promise) {
        SentryAndroid.init(getReactApplicationContext(), (Sentry.OptionsConfiguration<SentryAndroidOptions>) new Sentry.OptionsConfiguration() { // from class: io.sentry.react.RNSentryModuleImpl$$ExternalSyntheticLambda0
            @Override // io.sentry.Sentry.OptionsConfiguration
            public final void configure(SentryOptions sentryOptions) {
                this.f$0.lambda$initNativeSdk$1(readableMap, (SentryAndroidOptions) sentryOptions);
            }
        });
        promise.resolve(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initNativeSdk$1(ReadableMap readableMap, SentryAndroidOptions sentryAndroidOptions) {
        getSentryAndroidOptions(sentryAndroidOptions, readableMap, logger);
    }

    protected void getSentryAndroidOptions(final SentryAndroidOptions sentryAndroidOptions, ReadableMap readableMap, ILogger iLogger) {
        SdkVersion sdkVersion = sentryAndroidOptions.getSdkVersion();
        if (sdkVersion == null) {
            sdkVersion = new SdkVersion("sentry.java.android.react-native", "7.20.1");
        } else {
            sdkVersion.setName("sentry.java.android.react-native");
        }
        sdkVersion.addPackage("npm:@sentry/react-native", "6.6.0");
        sentryAndroidOptions.setSentryClientName(sdkVersion.getName() + "/" + sdkVersion.getVersion());
        sentryAndroidOptions.setNativeSdkName("sentry.native.android.react-native");
        sentryAndroidOptions.setSdkVersion(sdkVersion);
        if (readableMap.hasKey(LogEvent.LEVEL_DEBUG) && readableMap.getBoolean(LogEvent.LEVEL_DEBUG)) {
            sentryAndroidOptions.setDebug(true);
        }
        if (readableMap.hasKey("dsn") && readableMap.getString("dsn") != null) {
            String string = readableMap.getString("dsn");
            iLogger.log(SentryLevel.INFO, String.format("Starting with DSN: '%s'", string), new Object[0]);
            sentryAndroidOptions.setDsn(string);
        } else {
            sentryAndroidOptions.setDsn("");
        }
        if (readableMap.hasKey("sampleRate")) {
            sentryAndroidOptions.setSampleRate(Double.valueOf(readableMap.getDouble("sampleRate")));
        }
        if (readableMap.hasKey("sendClientReports")) {
            sentryAndroidOptions.setSendClientReports(readableMap.getBoolean("sendClientReports"));
        }
        if (readableMap.hasKey("maxBreadcrumbs")) {
            sentryAndroidOptions.setMaxBreadcrumbs(readableMap.getInt("maxBreadcrumbs"));
        }
        if (readableMap.hasKey("maxCacheItems")) {
            sentryAndroidOptions.setMaxCacheItems(readableMap.getInt("maxCacheItems"));
        }
        if (readableMap.hasKey("environment") && readableMap.getString("environment") != null) {
            sentryAndroidOptions.setEnvironment(readableMap.getString("environment"));
        }
        if (readableMap.hasKey("release") && readableMap.getString("release") != null) {
            sentryAndroidOptions.setRelease(readableMap.getString("release"));
        }
        if (readableMap.hasKey(SentryBaseEvent.JsonKeys.DIST) && readableMap.getString(SentryBaseEvent.JsonKeys.DIST) != null) {
            sentryAndroidOptions.setDist(readableMap.getString(SentryBaseEvent.JsonKeys.DIST));
        }
        if (readableMap.hasKey("enableAutoSessionTracking")) {
            sentryAndroidOptions.setEnableAutoSessionTracking(readableMap.getBoolean("enableAutoSessionTracking"));
        }
        if (readableMap.hasKey("sessionTrackingIntervalMillis")) {
            sentryAndroidOptions.setSessionTrackingIntervalMillis(readableMap.getInt("sessionTrackingIntervalMillis"));
        }
        if (readableMap.hasKey("shutdownTimeout")) {
            sentryAndroidOptions.setShutdownTimeoutMillis(readableMap.getInt("shutdownTimeout"));
        }
        if (readableMap.hasKey("enableNdkScopeSync")) {
            sentryAndroidOptions.setEnableScopeSync(readableMap.getBoolean("enableNdkScopeSync"));
        }
        if (readableMap.hasKey("attachStacktrace")) {
            sentryAndroidOptions.setAttachStacktrace(readableMap.getBoolean("attachStacktrace"));
        }
        if (readableMap.hasKey("attachThreads")) {
            sentryAndroidOptions.setAttachThreads(readableMap.getBoolean("attachThreads"));
        }
        if (readableMap.hasKey("attachScreenshot")) {
            sentryAndroidOptions.setAttachScreenshot(readableMap.getBoolean("attachScreenshot"));
        }
        if (readableMap.hasKey("attachViewHierarchy")) {
            sentryAndroidOptions.setAttachViewHierarchy(readableMap.getBoolean("attachViewHierarchy"));
        }
        if (readableMap.hasKey("sendDefaultPii")) {
            sentryAndroidOptions.setSendDefaultPii(readableMap.getBoolean("sendDefaultPii"));
        }
        if (readableMap.hasKey("maxQueueSize")) {
            sentryAndroidOptions.setMaxQueueSize(readableMap.getInt("maxQueueSize"));
        }
        if (readableMap.hasKey("enableNdk")) {
            sentryAndroidOptions.setEnableNdk(readableMap.getBoolean("enableNdk"));
        }
        if (readableMap.hasKey("spotlight")) {
            if (readableMap.getType("spotlight") == ReadableType.Boolean) {
                sentryAndroidOptions.setEnableSpotlight(readableMap.getBoolean("spotlight"));
                sentryAndroidOptions.setSpotlightConnectionUrl(readableMap.getString("defaultSidecarUrl"));
            } else if (readableMap.getType("spotlight") == ReadableType.String) {
                sentryAndroidOptions.setEnableSpotlight(true);
                sentryAndroidOptions.setSpotlightConnectionUrl(readableMap.getString("spotlight"));
            }
        }
        SentryReplayOptions replayOptions = getReplayOptions(readableMap);
        sentryAndroidOptions.setSessionReplay(replayOptions);
        if (isReplayEnabled(replayOptions)) {
            sentryAndroidOptions.getReplayController().setBreadcrumbConverter(new RNSentryReplayBreadcrumbConverter());
        }
        final String uRLFromDSN = getURLFromDSN(readableMap.getString("dsn"));
        final String string2 = readableMap.getString("devServerUrl");
        sentryAndroidOptions.setBeforeBreadcrumb(new SentryOptions.BeforeBreadcrumbCallback() { // from class: io.sentry.react.RNSentryModuleImpl$$ExternalSyntheticLambda3
            @Override // io.sentry.SentryOptions.BeforeBreadcrumbCallback
            public final Breadcrumb execute(Breadcrumb breadcrumb, Hint hint) {
                return RNSentryModuleImpl.lambda$getSentryAndroidOptions$2(uRLFromDSN, string2, breadcrumb, hint);
            }
        });
        sentryAndroidOptions.addIgnoredExceptionForType(JavascriptException.class);
        sentryAndroidOptions.setBeforeSend(new SentryOptions.BeforeSendCallback() { // from class: io.sentry.react.RNSentryModuleImpl$$ExternalSyntheticLambda4
            @Override // io.sentry.SentryOptions.BeforeSendCallback
            public final SentryEvent execute(SentryEvent sentryEvent, Hint hint) {
                return this.f$0.lambda$getSentryAndroidOptions$3(sentryAndroidOptions, sentryEvent, hint);
            }
        });
        if (readableMap.hasKey("enableNativeCrashHandling") && !readableMap.getBoolean("enableNativeCrashHandling")) {
            List<Integration> integrations = sentryAndroidOptions.getIntegrations();
            for (Integration integration : integrations) {
                if ((integration instanceof UncaughtExceptionHandlerIntegration) || (integration instanceof AnrIntegration) || (integration instanceof NdkIntegration)) {
                    integrations.remove(integration);
                }
            }
        }
        iLogger.log(SentryLevel.INFO, String.format("Native Integrations '%s'", sentryAndroidOptions.getIntegrations()), new Object[0]);
        CurrentActivityHolder currentActivityHolder = CurrentActivityHolder.getInstance();
        Activity currentActivity = getCurrentActivity();
        if (currentActivity != null) {
            currentActivityHolder.setActivity(currentActivity);
        }
    }

    static /* synthetic */ Breadcrumb lambda$getSentryAndroidOptions$2(String str, String str2, Breadcrumb breadcrumb, Hint hint) {
        Object data = breadcrumb.getData("url");
        String str3 = data instanceof String ? (String) data : "";
        if ("http".equals(breadcrumb.getType())) {
            if (str != null && str3.startsWith(str)) {
                return null;
            }
            if (str2 != null && str3.startsWith(str2)) {
                return null;
            }
        }
        return breadcrumb;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ SentryEvent lambda$getSentryAndroidOptions$3(SentryAndroidOptions sentryAndroidOptions, SentryEvent sentryEvent, Hint hint) {
        setEventOriginTag(sentryEvent);
        addPackages(sentryEvent, sentryAndroidOptions.getSdkVersion());
        return sentryEvent;
    }

    private boolean isReplayEnabled(SentryReplayOptions sentryReplayOptions) {
        return (sentryReplayOptions.getSessionSampleRate() == null && sentryReplayOptions.getOnErrorSampleRate() == null) ? false : true;
    }

    private SentryReplayOptions getReplayOptions(ReadableMap readableMap) {
        ReadableMap map;
        SentryReplayOptions sentryReplayOptions = new SentryReplayOptions(false, new SdkVersion("sentry.javascript.react-native", "6.6.0"));
        if (!readableMap.hasKey("replaysSessionSampleRate") && !readableMap.hasKey("replaysOnErrorSampleRate")) {
            return sentryReplayOptions;
        }
        sentryReplayOptions.setSessionSampleRate(readableMap.hasKey("replaysSessionSampleRate") ? Double.valueOf(readableMap.getDouble("replaysSessionSampleRate")) : null);
        sentryReplayOptions.setOnErrorSampleRate(readableMap.hasKey("replaysOnErrorSampleRate") ? Double.valueOf(readableMap.getDouble("replaysOnErrorSampleRate")) : null);
        if (!readableMap.hasKey("mobileReplayOptions") || (map = readableMap.getMap("mobileReplayOptions")) == null) {
            return sentryReplayOptions;
        }
        sentryReplayOptions.setMaskAllText(!map.hasKey("maskAllText") || map.getBoolean("maskAllText"));
        sentryReplayOptions.setMaskAllImages(!map.hasKey("maskAllImages") || map.getBoolean("maskAllImages"));
        if (!map.hasKey("maskAllVectors") || map.getBoolean("maskAllVectors")) {
            sentryReplayOptions.addMaskViewClass("com.horcrux.svg.SvgView");
        }
        sentryReplayOptions.setMaskViewContainerClass(RNSentryReplayMask.class.getName());
        sentryReplayOptions.setUnmaskViewContainerClass(RNSentryReplayUnmask.class.getName());
        return sentryReplayOptions;
    }

    public void crash() {
        throw new RuntimeException("TEST - Sentry Client Crash (only works in release mode)");
    }

    public void addListener(String str) {
        logger.log(SentryLevel.ERROR, "addListener of NativeEventEmitter can't be used on Android!", new Object[0]);
    }

    public void removeListeners(double d) {
        logger.log(SentryLevel.ERROR, "removeListeners of NativeEventEmitter can't be used on Android!", new Object[0]);
    }

    public void fetchModules(Promise promise) {
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(getReactApplicationContext().getResources().getAssets().open(modulesPath));
            try {
                byte[] bArr = new byte[bufferedInputStream.available()];
                bufferedInputStream.read(bArr);
                bufferedInputStream.close();
                promise.resolve(new String(bArr, UTF_8));
                bufferedInputStream.close();
            } catch (Throwable th) {
                try {
                    bufferedInputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (FileNotFoundException unused) {
            promise.resolve(null);
        } catch (Throwable unused2) {
            logger.log(SentryLevel.WARNING, "Fetching JS Modules failed.", new Object[0]);
            promise.resolve(null);
        }
    }

    public void fetchNativeRelease(Promise promise) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString("id", this.packageInfo.packageName);
        writableMapCreateMap.putString("version", this.packageInfo.versionName);
        writableMapCreateMap.putString(OperatingSystem.JsonKeys.BUILD, String.valueOf(this.packageInfo.versionCode));
        promise.resolve(writableMapCreateMap);
    }

    public void fetchNativeAppStart(Promise promise) {
        fetchNativeAppStart(promise, InternalSentrySdk.getAppStartMeasurement(), logger, AppStartMetrics.getInstance().isAppLaunchedInForeground());
    }

    protected void fetchNativeAppStart(Promise promise, Map<String, Object> map, ILogger iLogger, boolean z) {
        if (!z) {
            iLogger.log(SentryLevel.WARNING, "Invalid app start data: app not launched in foreground.", new Object[0]);
            promise.resolve(null);
        } else {
            WritableMap writableMap = (WritableMap) RNSentryMapConverter.convertToWritable(map);
            writableMap.putBoolean("has_fetched", hasFetchedAppStart);
            hasFetchedAppStart = true;
            promise.resolve(writableMap);
        }
    }

    public void fetchNativeFrames(Promise promise) {
        int i;
        int i2;
        int i3;
        SparseIntArray sparseIntArray;
        if (!isFrameMetricsAggregatorAvailable()) {
            promise.resolve(null);
            return;
        }
        try {
            SparseIntArray[] metrics = this.frameMetricsAggregator.getMetrics();
            if (metrics == null || (sparseIntArray = metrics[0]) == null) {
                i = 0;
                i2 = 0;
                i3 = 0;
            } else {
                i = 0;
                i2 = 0;
                i3 = 0;
                for (int i4 = 0; i4 < sparseIntArray.size(); i4++) {
                    int iKeyAt = sparseIntArray.keyAt(i4);
                    int iValueAt = sparseIntArray.valueAt(i4);
                    i += iValueAt;
                    if (iKeyAt > 700) {
                        i3 += iValueAt;
                    } else if (iKeyAt > 16) {
                        i2 += iValueAt;
                    }
                }
            }
            WritableMap writableMapCreateMap = Arguments.createMap();
            writableMapCreateMap.putInt("totalFrames", i);
            writableMapCreateMap.putInt("slowFrames", i2);
            writableMapCreateMap.putInt("frozenFrames", i3);
            promise.resolve(writableMapCreateMap);
        } catch (Throwable unused) {
            logger.log(SentryLevel.WARNING, "Error fetching native frames.", new Object[0]);
            promise.resolve(null);
        }
    }

    public void captureReplay(boolean z, Promise promise) {
        Sentry.getCurrentHub().getOptions().getReplayController().captureReplay(Boolean.valueOf(z));
        promise.resolve(getCurrentReplayId());
    }

    public String getCurrentReplayId() {
        SentryId replayId;
        IScope currentScope = InternalSentrySdk.getCurrentScope();
        if (currentScope == null || (replayId = currentScope.getReplayId()) == SentryId.EMPTY_ID) {
            return null;
        }
        return replayId.toString();
    }

    public void captureEnvelope(String str, ReadableMap readableMap, Promise promise) {
        try {
            InternalSentrySdk.captureEnvelope(Base64.decode(str, 0), (readableMap.hasKey("hardCrashed") && readableMap.getBoolean("hardCrashed")) ? false : true);
        } catch (Throwable unused) {
            logger.log(SentryLevel.ERROR, "Error while capturing envelope", new Object[0]);
            promise.resolve(false);
        }
        promise.resolve(true);
    }

    public void captureScreenshot(Promise promise) throws InterruptedException {
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            logger.log(SentryLevel.WARNING, "CurrentActivity is null, can't capture screenshot.", new Object[0]);
            promise.resolve(null);
            return;
        }
        byte[] bArrTakeScreenshotOnUiThread = takeScreenshotOnUiThread(currentActivity);
        if (bArrTakeScreenshotOnUiThread == null || bArrTakeScreenshotOnUiThread.length == 0) {
            logger.log(SentryLevel.WARNING, "Screenshot is null, screen was not captured.", new Object[0]);
            promise.resolve(null);
            return;
        }
        WritableNativeArray writableNativeArray = new WritableNativeArray();
        for (byte b : bArrTakeScreenshotOnUiThread) {
            writableNativeArray.pushInt(b);
        }
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putString(CMSAttributeTableGenerator.CONTENT_TYPE, ClipboardModule.MIMETYPE_PNG);
        writableNativeMap.putArray("data", writableNativeArray);
        writableNativeMap.putString("filename", "screenshot.png");
        WritableNativeArray writableNativeArray2 = new WritableNativeArray();
        writableNativeArray2.pushMap(writableNativeMap);
        promise.resolve(writableNativeArray2);
    }

    private static byte[] takeScreenshotOnUiThread(final Activity activity) throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final byte[][] bArr = {new byte[0]};
        Runnable runnable = new Runnable() { // from class: io.sentry.react.RNSentryModuleImpl$$ExternalSyntheticLambda9
            @Override // java.lang.Runnable
            public final void run() {
                RNSentryModuleImpl.lambda$takeScreenshotOnUiThread$4(bArr, activity, countDownLatch);
            }
        };
        if (UiThreadUtil.isOnUiThread()) {
            runnable.run();
        } else {
            UiThreadUtil.runOnUiThread(runnable);
        }
        try {
            countDownLatch.await(2L, TimeUnit.SECONDS);
            return bArr[0];
        } catch (InterruptedException unused) {
            logger.log(SentryLevel.ERROR, "Screenshot process was interrupted.", new Object[0]);
            return new byte[0];
        }
    }

    static /* synthetic */ void lambda$takeScreenshotOnUiThread$4(byte[][] bArr, Activity activity, CountDownLatch countDownLatch) {
        bArr[0] = ScreenshotUtils.takeScreenshot(activity, logger, buildInfo);
        countDownLatch.countDown();
    }

    public void fetchViewHierarchy(Promise promise) {
        Activity currentActivity = getCurrentActivity();
        ILogger iLogger = logger;
        ViewHierarchy viewHierarchySnapshotViewHierarchy = ViewHierarchyEventProcessor.snapshotViewHierarchy(currentActivity, iLogger);
        if (viewHierarchySnapshotViewHierarchy == null) {
            iLogger.log(SentryLevel.ERROR, "Could not get ViewHierarchy.", new Object[0]);
            promise.resolve(null);
            return;
        }
        byte[] bArrBytesFrom = JsonSerializationUtils.bytesFrom(HubAdapter.getInstance().getOptions().getSerializer(), iLogger, viewHierarchySnapshotViewHierarchy);
        if (bArrBytesFrom == null) {
            iLogger.log(SentryLevel.ERROR, "Could not serialize ViewHierarchy.", new Object[0]);
            promise.resolve(null);
        } else {
            if (bArrBytesFrom.length < 1) {
                iLogger.log(SentryLevel.ERROR, "Got empty bytes array after serializing ViewHierarchy.", new Object[0]);
                promise.resolve(null);
                return;
            }
            WritableNativeArray writableNativeArray = new WritableNativeArray();
            for (byte b : bArrBytesFrom) {
                writableNativeArray.pushInt(b);
            }
            promise.resolve(writableNativeArray);
        }
    }

    private static PackageInfo getPackageInfo(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException unused) {
            logger.log(SentryLevel.WARNING, "Error getting package info.", new Object[0]);
            return null;
        }
    }

    public void setUser(final ReadableMap readableMap, final ReadableMap readableMap2) {
        Sentry.configureScope(new ScopeCallback() { // from class: io.sentry.react.RNSentryModuleImpl$$ExternalSyntheticLambda1
            @Override // io.sentry.ScopeCallback
            public final void run(IScope iScope) {
                RNSentryModuleImpl.lambda$setUser$5(readableMap, readableMap2, iScope);
            }
        });
    }

    static /* synthetic */ void lambda$setUser$5(ReadableMap readableMap, ReadableMap readableMap2, IScope iScope) {
        if (readableMap == null && readableMap2 == null) {
            iScope.setUser(null);
            return;
        }
        User user = new User();
        if (readableMap != null) {
            if (readableMap.hasKey("email")) {
                user.setEmail(readableMap.getString("email"));
            }
            if (readableMap.hasKey("id")) {
                user.setId(readableMap.getString("id"));
            }
            if (readableMap.hasKey("username")) {
                user.setUsername(readableMap.getString("username"));
            }
            if (readableMap.hasKey("ip_address")) {
                user.setIpAddress(readableMap.getString("ip_address"));
            }
            if (readableMap.hasKey("segment")) {
                user.setSegment(readableMap.getString("segment"));
            }
        }
        if (readableMap2 != null) {
            HashMap map = new HashMap();
            ReadableMapKeySetIterator readableMapKeySetIteratorKeySetIterator = readableMap2.keySetIterator();
            while (readableMapKeySetIteratorKeySetIterator.hasNextKey()) {
                String strNextKey = readableMapKeySetIteratorKeySetIterator.nextKey();
                String string = readableMap2.getString(strNextKey);
                if (string != null) {
                    map.put(strNextKey, string);
                }
            }
            user.setData(map);
        }
        iScope.setUser(user);
    }

    public void addBreadcrumb(final ReadableMap readableMap) {
        Sentry.configureScope(new ScopeCallback() { // from class: io.sentry.react.RNSentryModuleImpl$$ExternalSyntheticLambda7
            @Override // io.sentry.ScopeCallback
            public final void run(IScope iScope) {
                RNSentryModuleImpl.lambda$addBreadcrumb$6(readableMap, iScope);
            }
        });
    }

    static /* synthetic */ void lambda$addBreadcrumb$6(ReadableMap readableMap, IScope iScope) {
        iScope.addBreadcrumb(RNSentryBreadcrumb.fromMap(readableMap));
        String currentScreenFrom = RNSentryBreadcrumb.getCurrentScreenFrom(readableMap);
        if (currentScreenFrom != null) {
            iScope.setScreen(currentScreenFrom);
        }
    }

    public void clearBreadcrumbs() {
        Sentry.configureScope(new ScopeCallback() { // from class: io.sentry.react.RNSentryModuleImpl$$ExternalSyntheticLambda8
            @Override // io.sentry.ScopeCallback
            public final void run(IScope iScope) {
                iScope.clearBreadcrumbs();
            }
        });
    }

    public void setExtra(final String str, final String str2) {
        if (str == null || str2 == null) {
            logger.log(SentryLevel.ERROR, "RNSentry.setExtra called with null key or value, can't change extra.", new Object[0]);
        } else {
            Sentry.configureScope(new ScopeCallback() { // from class: io.sentry.react.RNSentryModuleImpl$$ExternalSyntheticLambda10
                @Override // io.sentry.ScopeCallback
                public final void run(IScope iScope) {
                    iScope.setExtra(str, str2);
                }
            });
        }
    }

    public void setContext(final String str, final ReadableMap readableMap) {
        if (str == null) {
            logger.log(SentryLevel.ERROR, "RNSentry.setContext called with null key, can't change context.", new Object[0]);
        } else {
            Sentry.configureScope(new ScopeCallback() { // from class: io.sentry.react.RNSentryModuleImpl$$ExternalSyntheticLambda6
                @Override // io.sentry.ScopeCallback
                public final void run(IScope iScope) {
                    RNSentryModuleImpl.lambda$setContext$9(readableMap, str, iScope);
                }
            });
        }
    }

    static /* synthetic */ void lambda$setContext$9(ReadableMap readableMap, String str, IScope iScope) {
        if (readableMap == null) {
            iScope.removeContexts(str);
        } else {
            iScope.setContexts(str, readableMap.toHashMap());
        }
    }

    public void setTag(final String str, final String str2) {
        Sentry.configureScope(new ScopeCallback() { // from class: io.sentry.react.RNSentryModuleImpl$$ExternalSyntheticLambda5
            @Override // io.sentry.ScopeCallback
            public final void run(IScope iScope) {
                iScope.setTag(str, str2);
            }
        });
    }

    public void closeNativeSdk(Promise promise) {
        Sentry.close();
        disableNativeFramesTracking();
        promise.resolve(true);
    }

    public void enableNativeFramesTracking() throws ClassNotFoundException {
        boolean zCheckAndroidXAvailability = checkAndroidXAvailability();
        this.androidXAvailable = zCheckAndroidXAvailability;
        if (zCheckAndroidXAvailability) {
            this.frameMetricsAggregator = new FrameMetricsAggregator();
            Activity currentActivity = getCurrentActivity();
            FrameMetricsAggregator frameMetricsAggregator = this.frameMetricsAggregator;
            if (frameMetricsAggregator != null && currentActivity != null) {
                try {
                    frameMetricsAggregator.add(currentActivity);
                    logger.log(SentryLevel.INFO, "FrameMetricsAggregator installed.", new Object[0]);
                    return;
                } catch (Throwable unused) {
                    logger.log(SentryLevel.ERROR, "Error adding Activity to frameMetricsAggregator.", new Object[0]);
                    return;
                }
            }
            logger.log(SentryLevel.INFO, "currentActivity isn't available.", new Object[0]);
            return;
        }
        logger.log(SentryLevel.WARNING, "androidx.core' isn't available as a dependency.", new Object[0]);
    }

    public void disableNativeFramesTracking() {
        if (isFrameMetricsAggregatorAvailable()) {
            this.frameMetricsAggregator.stop();
            this.frameMetricsAggregator = null;
        }
    }

    public void getNewScreenTimeToDisplay(Promise promise) {
        RNSentryTimeToDisplay.getTimeToDisplay(promise, this.dateProvider);
    }

    private String getProfilingTracesDirPath() {
        if (this.cacheDirPath == null) {
            this.cacheDirPath = new File(getReactApplicationContext().getCacheDir(), "sentry/react").getAbsolutePath();
        }
        File file = new File(this.cacheDirPath, "profiling_trace");
        file.mkdirs();
        return file.getAbsolutePath();
    }

    private void initializeAndroidProfiler() {
        if (this.executorService == null) {
            this.executorService = new SentryExecutorService();
        }
        String profilingTracesDirPath = getProfilingTracesDirPath();
        int micros = ((int) TimeUnit.SECONDS.toMicros(1L)) / this.profilingTracesHz;
        ReactApplicationContext reactApplicationContext = this.reactApplicationContext;
        ILogger iLogger = logger;
        BuildInfoProvider buildInfoProvider = buildInfo;
        this.androidProfiler = new AndroidProfiler(profilingTracesDirPath, micros, new SentryFrameMetricsCollector(reactApplicationContext, iLogger, buildInfoProvider), this.executorService, iLogger, buildInfoProvider);
    }

    public WritableMap startProfiling(boolean z) {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        if (this.androidProfiler == null && z) {
            initializeAndroidProfiler();
        }
        try {
            HermesSamplingProfiler.enable();
            AndroidProfiler androidProfiler = this.androidProfiler;
            if (androidProfiler != null) {
                androidProfiler.start();
            }
            writableNativeMap.putBoolean(Session.JsonKeys.STARTED, true);
        } catch (Throwable th) {
            writableNativeMap.putBoolean(Session.JsonKeys.STARTED, false);
            writableNativeMap.putString("error", th.toString());
        }
        return writableNativeMap;
    }

    public WritableMap stopProfiling() {
        boolean zIsDebug = HubAdapter.getInstance().getOptions().isDebug();
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        File fileCreateTempFile = null;
        try {
            AndroidProfiler androidProfiler = this.androidProfiler;
            AndroidProfiler.ProfileEndData profileEndDataEndAndCollect = androidProfiler != null ? androidProfiler.endAndCollect(false, null) : null;
            HermesSamplingProfiler.disable();
            fileCreateTempFile = File.createTempFile("sampling-profiler-trace", ".cpuprofile", this.reactApplicationContext.getCacheDir());
            if (zIsDebug) {
                logger.log(SentryLevel.INFO, "Profile saved to: " + fileCreateTempFile.getAbsolutePath(), new Object[0]);
            }
            HermesSamplingProfiler.dumpSampledTraceToFile(fileCreateTempFile.getPath());
            writableNativeMap.putString("profile", readStringFromFile(fileCreateTempFile));
            if (profileEndDataEndAndCollect != null) {
                WritableNativeMap writableNativeMap2 = new WritableNativeMap();
                writableNativeMap2.putString(ProfilingTraceData.JsonKeys.SAMPLED_PROFILE, Base64.encodeToString(FileUtils.readBytesFromFile(profileEndDataEndAndCollect.traceFile.getPath(), this.maxTraceFileSize), 3));
                writableNativeMap2.putInt(ProfilingTraceData.JsonKeys.ANDROID_API_LEVEL, buildInfo.getSdkInfoVersion());
                writableNativeMap2.putString(ProfilingTraceData.JsonKeys.BUILD_ID, getProguardUuid());
                writableNativeMap.putMap("androidProfile", writableNativeMap2);
            }
            if (fileCreateTempFile != null) {
                try {
                    if (!fileCreateTempFile.delete()) {
                        logger.log(SentryLevel.WARNING, "Profile not deleted from:" + fileCreateTempFile.getAbsolutePath(), new Object[0]);
                    }
                } catch (Throwable unused) {
                    logger.log(SentryLevel.WARNING, "Profile not deleted from:" + fileCreateTempFile.getAbsolutePath(), new Object[0]);
                }
            }
        } catch (Throwable th) {
            try {
                writableNativeMap.putString("error", th.toString());
                if (fileCreateTempFile != null) {
                    try {
                        if (!fileCreateTempFile.delete()) {
                            logger.log(SentryLevel.WARNING, "Profile not deleted from:" + fileCreateTempFile.getAbsolutePath(), new Object[0]);
                        }
                    } catch (Throwable unused2) {
                        logger.log(SentryLevel.WARNING, "Profile not deleted from:" + fileCreateTempFile.getAbsolutePath(), new Object[0]);
                    }
                }
            } catch (Throwable th2) {
                if (fileCreateTempFile != null) {
                    try {
                        if (!fileCreateTempFile.delete()) {
                            logger.log(SentryLevel.WARNING, "Profile not deleted from:" + fileCreateTempFile.getAbsolutePath(), new Object[0]);
                        }
                    } catch (Throwable unused3) {
                        logger.log(SentryLevel.WARNING, "Profile not deleted from:" + fileCreateTempFile.getAbsolutePath(), new Object[0]);
                    }
                }
                throw th2;
            }
        }
        return writableNativeMap;
    }

    private String getProguardUuid() throws IOException {
        if (this.isProguardDebugMetaLoaded) {
            return this.proguardUuid;
        }
        this.isProguardDebugMetaLoaded = true;
        List<Properties> listLoadDebugMeta = new AssetsDebugMetaLoader(getReactApplicationContext(), logger).loadDebugMeta();
        if (listLoadDebugMeta == null) {
            return null;
        }
        Iterator<Properties> it = listLoadDebugMeta.iterator();
        while (it.hasNext()) {
            String proguardUuid = DebugMetaPropertiesApplier.getProguardUuid(it.next());
            this.proguardUuid = proguardUuid;
            if (proguardUuid != null) {
                logger.log(SentryLevel.INFO, "Proguard uuid found: " + this.proguardUuid, new Object[0]);
                return this.proguardUuid;
            }
        }
        logger.log(SentryLevel.WARNING, "No proguard uuid found in debug meta properties file!", new Object[0]);
        return null;
    }

    private String readStringFromFile(File file) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        try {
            StringBuilder sb = new StringBuilder();
            while (true) {
                String line = bufferedReader.readLine();
                if (line != null) {
                    sb.append(line);
                    sb.append('\n');
                } else {
                    String string = sb.toString();
                    bufferedReader.close();
                    return string;
                }
            }
        } catch (Throwable th) {
            try {
                bufferedReader.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public void fetchNativeDeviceContexts(Promise promise) {
        fetchNativeDeviceContexts(promise, HubAdapter.getInstance().getOptions(), getReactApplicationContext().getApplicationContext(), InternalSentrySdk.getCurrentScope());
    }

    protected void fetchNativeDeviceContexts(Promise promise, SentryOptions sentryOptions, Context context, IScope iScope) {
        if (!(sentryOptions instanceof SentryAndroidOptions)) {
            promise.resolve(null);
            return;
        }
        if (context == null) {
            promise.resolve(null);
            return;
        }
        if (iScope != null) {
            Iterator<Breadcrumb> it = iScope.getBreadcrumbs().iterator();
            while (it.hasNext()) {
                if ("react-native".equals(it.next().getOrigin())) {
                    it.remove();
                }
            }
        }
        promise.resolve(RNSentryMapConverter.convertToWritable(InternalSentrySdk.serializeScope(context, (SentryAndroidOptions) sentryOptions, iScope)));
    }

    public void fetchNativeSdkInfo(Promise promise) {
        SdkVersion sdkVersion = HubAdapter.getInstance().getOptions().getSdkVersion();
        if (sdkVersion == null) {
            promise.resolve(null);
            return;
        }
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putString("name", sdkVersion.getName());
        writableNativeMap.putString("version", sdkVersion.getVersion());
        promise.resolve(writableNativeMap);
    }

    public String fetchNativePackageName() {
        return this.packageInfo.packageName;
    }

    public void crashedLastRun(Promise promise) {
        promise.resolve(Sentry.isCrashedLastRun());
    }

    private void setEventOriginTag(SentryEvent sentryEvent) {
        SdkVersion sdk = sentryEvent.getSdk();
        if (sdk != null) {
            String name = sdk.getName();
            name.hashCode();
            if (name.equals("sentry.java.android.react-native")) {
                setEventEnvironmentTag(sentryEvent, SentryBaseEvent.DEFAULT_PLATFORM);
            } else if (name.equals("sentry.native.android.react-native")) {
                setEventEnvironmentTag(sentryEvent, SentryStackFrame.JsonKeys.NATIVE);
            }
        }
    }

    private void setEventEnvironmentTag(SentryEvent sentryEvent, String str) {
        sentryEvent.setTag("event.origin", Constants.KEY_ANDROID);
        sentryEvent.setTag("event.environment", str);
    }

    private void addPackages(SentryEvent sentryEvent, SdkVersion sdkVersion) {
        SdkVersion sdk = sentryEvent.getSdk();
        if (sdk == null || !"sentry.javascript.react-native".equals(sdk.getName()) || sdkVersion == null) {
            return;
        }
        List<SentryPackage> packages = sdkVersion.getPackages();
        if (packages != null) {
            for (SentryPackage sentryPackage : packages) {
                sdk.addPackage(sentryPackage.getName(), sentryPackage.getVersion());
            }
        }
        List<String> integrations = sdkVersion.getIntegrations();
        if (integrations != null) {
            Iterator<String> it = integrations.iterator();
            while (it.hasNext()) {
                sdk.addIntegration(it.next());
            }
        }
        sentryEvent.setSdk(sdk);
    }

    private boolean checkAndroidXAvailability() throws ClassNotFoundException {
        try {
            Class.forName("androidx.core.app.FrameMetricsAggregator");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public static String getURLFromDSN(String str) {
        if (str == null) {
            return null;
        }
        try {
            URI uri = new URI(str);
            return uri.getScheme() + "://" + uri.getHost();
        } catch (URISyntaxException unused) {
            return null;
        }
    }
}
