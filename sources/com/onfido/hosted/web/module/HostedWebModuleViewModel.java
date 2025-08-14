package com.onfido.hosted.web.module;

import androidx.lifecycle.ViewModel;
import com.clevertap.android.sdk.Constants;
import com.facebook.react.uimanager.events.TouchesHelper;
import com.onfido.android.sdk.capture.BuildConfig;
import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.android.sdk.capture.core.OnfidoLauncher;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.OnfidoAnalytics;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.AnalyticsEvent;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.Event;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.EventType;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.FlowTracker;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.android.sdk.capture.utils.JsonExtKt;
import com.onfido.android.sdk.capture.utils.StringExtensionsKt;
import com.onfido.hosted.web.module.WebModuleScriptBuilder;
import com.onfido.hosted.web.module.model.CaptureSDKExternalLinkResponse;
import com.onfido.hosted.web.module.model.HostedWebModuleCallbacks;
import com.onfido.hosted.web.module.model.HostedWebModuleCancelled;
import com.onfido.hosted.web.module.model.HostedWebModuleExit;
import com.onfido.hosted.web.module.model.HostedWebModuleFailed;
import com.onfido.hosted.web.module.model.HostedWebModuleModuleInfo;
import com.onfido.hosted.web.module.model.HostedWebModuleResponseAnalytics;
import com.onfido.hosted.web.module.model.HostedWebModuleResult;
import com.onfido.hosted.web.module.model.HostedWebModuleScriptState;
import com.onfido.hosted.web.module.model.HostedWebModuleSuccess;
import com.onfido.javax.inject.Inject;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementKt;
import kotlinx.serialization.json.JsonObject;

@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\t\b\u0000\u0018\u00002\u00020\u0001:\u0001=B/\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u000e\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!J\u000e\u0010\"\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!J\u000e\u0010#\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!J\u0017\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010%H\u0000¢\u0006\u0002\b'J\u0016\u0010(\u001a\u00020%2\u0006\u0010 \u001a\u00020!2\u0006\u0010)\u001a\u00020*J\u0006\u0010+\u001a\u00020%J\u0010\u0010,\u001a\u00020%2\u0006\u0010 \u001a\u00020!H\u0002J\r\u0010-\u001a\u00020\u001fH\u0000¢\u0006\u0002\b.J\r\u0010/\u001a\u00020\u001fH\u0000¢\u0006\u0002\b0J\u0006\u00101\u001a\u00020\u001fJ\u0006\u00102\u001a\u00020\u001fJ\u0016\u00103\u001a\u00020\u001f2\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u00020%J\u0010\u00107\u001a\u00020\u001f2\u0006\u00106\u001a\u00020%H\u0002J\b\u00108\u001a\u00020\u001fH\u0002J\u0010\u00109\u001a\u00020\u001f2\u0006\u00106\u001a\u00020%H\u0002J\u0010\u0010:\u001a\u00020\u001f2\u0006\u00106\u001a\u00020%H\u0002J\u0010\u0010;\u001a\u00020\u001f2\u0006\u00106\u001a\u00020%H\u0002J\b\u0010<\u001a\u00020\u001fH\u0002R\u0016\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\u000bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u0017X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u001a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u0017X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0019R\u001a\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00130\u0017X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0019¨\u0006>"}, d2 = {"Lcom/onfido/hosted/web/module/HostedWebModuleViewModel;", "Landroidx/lifecycle/ViewModel;", "onfidoConfig", "Lcom/onfido/android/sdk/capture/OnfidoConfig;", "parser", "Lkotlinx/serialization/json/Json;", "remoteConfig", "Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;", "flowTracker", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/FlowTracker;", "analytics", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/OnfidoAnalytics;", "(Lcom/onfido/android/sdk/capture/OnfidoConfig;Lkotlinx/serialization/json/Json;Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/FlowTracker;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/OnfidoAnalytics;)V", "_externalLinkResult", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/onfido/hosted/web/module/HostedWebModuleViewModel$ExternalNavigationLink;", "_result", "Lcom/onfido/hosted/web/module/model/HostedWebModuleResult;", "_shouldEvaluateScript", "Lcom/onfido/hosted/web/module/model/HostedWebModuleScriptState;", "getAnalytics$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/OnfidoAnalytics;", "externalLinkResult", "Lkotlinx/coroutines/flow/StateFlow;", "getExternalLinkResult$onfido_capture_sdk_core_release", "()Lkotlinx/coroutines/flow/StateFlow;", OnfidoLauncher.KEY_RESULT, "getResult$onfido_capture_sdk_core_release", "shouldEvaluateScript", "getShouldEvaluateScript$onfido_capture_sdk_core_release", "flowUserExit", "", "moduleInfo", "Lcom/onfido/hosted/web/module/model/HostedWebModuleModuleInfo;", "flowUserExitCanceled", "flowUserExitConfirmed", "getBridgeUrl", "", "receivedCustomUrl", "getBridgeUrl$onfido_capture_sdk_core_release", "getCaptureSDKConfigScript", "webSdkTheme", "Lcom/onfido/hosted/web/module/WebModuleScriptBuilder$WebSDKTheme;", "getNavigateBackScript", "getStep", "onCaptureSDKExternalLinkOpened", "onCaptureSDKExternalLinkOpened$onfido_capture_sdk_core_release", "onErrorRetried", "onErrorRetried$onfido_capture_sdk_core_release", "onPageFinished", "onScriptEvaluated", "processCallback", "type", "Lcom/onfido/hosted/web/module/model/HostedWebModuleCallbacks;", "message", "processCaptureSdkAnalytics", "processCaptureSdkBackNavigation", "processCaptureSdkExternalLink", "processCaptureSdkModuleError", "processCaptureSdkModuleFinish", "processCaptureSdkNavigationExit", "ExternalNavigationLink", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class HostedWebModuleViewModel extends ViewModel {
    private final MutableStateFlow<ExternalNavigationLink> _externalLinkResult;
    private final MutableStateFlow<HostedWebModuleResult> _result;
    private final MutableStateFlow<HostedWebModuleScriptState> _shouldEvaluateScript;
    private final OnfidoAnalytics analytics;
    private final StateFlow<ExternalNavigationLink> externalLinkResult;
    private final FlowTracker flowTracker;
    private final OnfidoConfig onfidoConfig;
    private final Json parser;
    private final OnfidoRemoteConfig remoteConfig;
    private final StateFlow<HostedWebModuleResult> result;
    private final StateFlow<HostedWebModuleScriptState> shouldEvaluateScript;

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0080\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/onfido/hosted/web/module/HostedWebModuleViewModel$ExternalNavigationLink;", "", "url", "", TouchesHelper.TARGET_KEY, "Lcom/onfido/hosted/web/module/model/CaptureSDKExternalLinkResponse$LinkTarget;", "(Ljava/lang/String;Lcom/onfido/hosted/web/module/model/CaptureSDKExternalLinkResponse$LinkTarget;)V", "getTarget", "()Lcom/onfido/hosted/web/module/model/CaptureSDKExternalLinkResponse$LinkTarget;", "getUrl", "()Ljava/lang/String;", "component1", "component2", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "", "toString", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class ExternalNavigationLink {
        private final CaptureSDKExternalLinkResponse.LinkTarget target;
        private final String url;

        public ExternalNavigationLink(String url, CaptureSDKExternalLinkResponse.LinkTarget linkTarget) {
            Intrinsics.checkNotNullParameter(url, "url");
            this.url = url;
            this.target = linkTarget;
        }

        public static /* synthetic */ ExternalNavigationLink copy$default(ExternalNavigationLink externalNavigationLink, String str, CaptureSDKExternalLinkResponse.LinkTarget linkTarget, int i, Object obj) {
            if ((i & 1) != 0) {
                str = externalNavigationLink.url;
            }
            if ((i & 2) != 0) {
                linkTarget = externalNavigationLink.target;
            }
            return externalNavigationLink.copy(str, linkTarget);
        }

        /* renamed from: component1, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        /* renamed from: component2, reason: from getter */
        public final CaptureSDKExternalLinkResponse.LinkTarget getTarget() {
            return this.target;
        }

        public final ExternalNavigationLink copy(String url, CaptureSDKExternalLinkResponse.LinkTarget target) {
            Intrinsics.checkNotNullParameter(url, "url");
            return new ExternalNavigationLink(url, target);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ExternalNavigationLink)) {
                return false;
            }
            ExternalNavigationLink externalNavigationLink = (ExternalNavigationLink) other;
            return Intrinsics.areEqual(this.url, externalNavigationLink.url) && this.target == externalNavigationLink.target;
        }

        public final CaptureSDKExternalLinkResponse.LinkTarget getTarget() {
            return this.target;
        }

        public final String getUrl() {
            return this.url;
        }

        public int hashCode() {
            int iHashCode = this.url.hashCode() * 31;
            CaptureSDKExternalLinkResponse.LinkTarget linkTarget = this.target;
            return iHashCode + (linkTarget == null ? 0 : linkTarget.hashCode());
        }

        public String toString() {
            return "ExternalNavigationLink(url=" + this.url + ", target=" + this.target + ')';
        }
    }

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[HostedWebModuleCallbacks.values().length];
            try {
                iArr[HostedWebModuleCallbacks.CAPTURE_MODULE_FINISH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[HostedWebModuleCallbacks.CAPTURE_MODULE_ERROR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[HostedWebModuleCallbacks.BOOTSTRAP_ERROR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[HostedWebModuleCallbacks.CAPTURE_MODULE_UNSUPPORTED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[HostedWebModuleCallbacks.ANALYTICS_SEND.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[HostedWebModuleCallbacks.NAVIGATION_EXTERNAL_LINK.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[HostedWebModuleCallbacks.NAVIGATION_EXIT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[HostedWebModuleCallbacks.NAVIGATION_BACK.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Inject
    public HostedWebModuleViewModel(OnfidoConfig onfidoConfig, Json parser, OnfidoRemoteConfig remoteConfig, FlowTracker flowTracker, OnfidoAnalytics analytics) {
        Intrinsics.checkNotNullParameter(onfidoConfig, "onfidoConfig");
        Intrinsics.checkNotNullParameter(parser, "parser");
        Intrinsics.checkNotNullParameter(remoteConfig, "remoteConfig");
        Intrinsics.checkNotNullParameter(flowTracker, "flowTracker");
        Intrinsics.checkNotNullParameter(analytics, "analytics");
        this.onfidoConfig = onfidoConfig;
        this.parser = parser;
        this.remoteConfig = remoteConfig;
        this.flowTracker = flowTracker;
        this.analytics = analytics;
        MutableStateFlow<HostedWebModuleResult> MutableStateFlow = StateFlowKt.MutableStateFlow(null);
        this._result = MutableStateFlow;
        this.result = FlowKt.asStateFlow(MutableStateFlow);
        MutableStateFlow<ExternalNavigationLink> MutableStateFlow2 = StateFlowKt.MutableStateFlow(null);
        this._externalLinkResult = MutableStateFlow2;
        this.externalLinkResult = FlowKt.asStateFlow(MutableStateFlow2);
        MutableStateFlow<HostedWebModuleScriptState> MutableStateFlow3 = StateFlowKt.MutableStateFlow(HostedWebModuleScriptState.NOT_EVALUATED);
        this._shouldEvaluateScript = MutableStateFlow3;
        this.shouldEvaluateScript = FlowKt.asStateFlow(MutableStateFlow3);
    }

    private final String getStep(HostedWebModuleModuleInfo moduleInfo) {
        if (moduleInfo.getStudioInfo() != null) {
            return moduleInfo.getStudioInfo().getTaskDefId();
        }
        if (moduleInfo.getClassicInfo() != null) {
            return moduleInfo.getClassicInfo().getStep();
        }
        throw new IllegalArgumentException("Both studio and classic Info cannot be null".toString());
    }

    private final void processCaptureSdkAnalytics(String message) {
        try {
            Json json = this.parser;
            HostedWebModuleResponseAnalytics hostedWebModuleResponseAnalytics = (HostedWebModuleResponseAnalytics) json.decodeFromString(SerializersKt.serializer(json.getSerializersModule(), Reflection.typeOf(HostedWebModuleResponseAnalytics.class)), message);
            HostedWebModuleResponseAnalytics.Event event = hostedWebModuleResponseAnalytics.getEvent();
            Map<String, String> mapComponent2 = hostedWebModuleResponseAnalytics.component2();
            EventType eventTypeFromProperties = HostedWebModuleViewModelKt.getEventTypeFromProperties(mapComponent2);
            OnfidoAnalytics onfidoAnalytics = this.analytics;
            Event event2 = new Event(event.getName(), eventTypeFromProperties, null, null, 12, null);
            if (mapComponent2 == null) {
                mapComponent2 = MapsKt.emptyMap();
            }
            onfidoAnalytics.track(new AnalyticsEvent(event2, mapComponent2, null, 4, null));
        } catch (Exception e) {
            Timber.INSTANCE.e(e);
        }
    }

    private final void processCaptureSdkBackNavigation() {
        this._result.setValue(HostedWebModuleCancelled.INSTANCE);
    }

    private final void processCaptureSdkExternalLink(String message) {
        Json json = this.parser;
        CaptureSDKExternalLinkResponse captureSDKExternalLinkResponse = (CaptureSDKExternalLinkResponse) json.decodeFromString(SerializersKt.serializer(json.getSerializersModule(), Reflection.typeOf(CaptureSDKExternalLinkResponse.class)), message);
        this._externalLinkResult.setValue(new ExternalNavigationLink(captureSDKExternalLinkResponse.getUrl(), captureSDKExternalLinkResponse.getTarget()));
    }

    private final void processCaptureSdkModuleError(String message) {
        try {
            Json json = this.parser;
            this._result.setValue(new HostedWebModuleFailed(String.valueOf(JsonExtKt.convertCamelToSnakeCase((JsonElement) json.decodeFromString(SerializersKt.serializer(json.getSerializersModule(), Reflection.typeOf(JsonElement.class)), message)))));
        } catch (SerializationException e) {
            this._result.setValue(new HostedWebModuleFailed(new JsonObject(MapsKt.mapOf(TuplesKt.to("error", JsonElementKt.JsonPrimitive(e.getMessage())))).toString()));
            Timber.INSTANCE.d("Received captureModuleError data from the CaptureSDK - Error deserializing (SerializationException): " + e, new Object[0]);
        }
    }

    private final void processCaptureSdkModuleFinish(String message) {
        try {
            Json json = this.parser;
            this._result.setValue(new HostedWebModuleSuccess(String.valueOf(JsonExtKt.convertCamelToSnakeCase((JsonElement) json.decodeFromString(SerializersKt.serializer(json.getSerializersModule(), Reflection.typeOf(JsonElement.class)), message)))));
        } catch (SerializationException e) {
            this._result.setValue(new HostedWebModuleFailed(new JsonObject(MapsKt.mapOf(TuplesKt.to("error", JsonElementKt.JsonPrimitive(e.getMessage())))).toString()));
            Timber.INSTANCE.d("Received captureSdkModuleFinish data from the CaptureSDK - Error deserializing (SerializationException): " + e, new Object[0]);
        }
    }

    private final void processCaptureSdkNavigationExit() {
        this._result.setValue(new HostedWebModuleExit());
    }

    public final void flowUserExit(HostedWebModuleModuleInfo moduleInfo) {
        Intrinsics.checkNotNullParameter(moduleInfo, "moduleInfo");
        this.flowTracker.trackFlowUserExitButtonClicked(getStep(moduleInfo));
    }

    public final void flowUserExitCanceled(HostedWebModuleModuleInfo moduleInfo) {
        Intrinsics.checkNotNullParameter(moduleInfo, "moduleInfo");
        this.flowTracker.trackFlowUserExitCanceled(getStep(moduleInfo));
    }

    public final void flowUserExitConfirmed(HostedWebModuleModuleInfo moduleInfo) {
        Intrinsics.checkNotNullParameter(moduleInfo, "moduleInfo");
        this.flowTracker.trackFlowUserExitConfirmed(getStep(moduleInfo));
    }

    /* renamed from: getAnalytics$onfido_capture_sdk_core_release, reason: from getter */
    public final OnfidoAnalytics getAnalytics() {
        return this.analytics;
    }

    public final String getBridgeUrl$onfido_capture_sdk_core_release(String receivedCustomUrl) {
        if (!StringExtensionsKt.isNotNullOrEmpty(receivedCustomUrl)) {
            receivedCustomUrl = BuildConfig.CAPTURE_SDK_BRIDGE_URL;
        } else if (receivedCustomUrl == null) {
            throw new IllegalArgumentException("Required value was null.".toString());
        }
        Timber.INSTANCE.i("getBridgeUrl - will use: " + receivedCustomUrl, new Object[0]);
        return receivedCustomUrl;
    }

    public final String getCaptureSDKConfigScript(HostedWebModuleModuleInfo moduleInfo, WebModuleScriptBuilder.WebSDKTheme webSdkTheme) {
        Intrinsics.checkNotNullParameter(moduleInfo, "moduleInfo");
        Intrinsics.checkNotNullParameter(webSdkTheme, "webSdkTheme");
        return new WebModuleScriptBuilder().getConfigScript(moduleInfo, this.onfidoConfig.getToken().getTokenValue(), this.remoteConfig.isStudioUserFlowExitEnabled() && moduleInfo.getStudioInfo() != null, webSdkTheme);
    }

    public final StateFlow<ExternalNavigationLink> getExternalLinkResult$onfido_capture_sdk_core_release() {
        return this.externalLinkResult;
    }

    public final String getNavigateBackScript() {
        return new WebModuleScriptBuilder().getNavigateBackScript();
    }

    public final StateFlow<HostedWebModuleResult> getResult$onfido_capture_sdk_core_release() {
        return this.result;
    }

    public final StateFlow<HostedWebModuleScriptState> getShouldEvaluateScript$onfido_capture_sdk_core_release() {
        return this.shouldEvaluateScript;
    }

    public final void onCaptureSDKExternalLinkOpened$onfido_capture_sdk_core_release() {
        this._externalLinkResult.setValue(null);
    }

    public final void onErrorRetried$onfido_capture_sdk_core_release() {
        this._result.setValue(null);
        this._shouldEvaluateScript.setValue(HostedWebModuleScriptState.NOT_EVALUATED);
    }

    public final void onPageFinished() {
        if (this._shouldEvaluateScript.getValue() == HostedWebModuleScriptState.NOT_EVALUATED) {
            this._shouldEvaluateScript.setValue(HostedWebModuleScriptState.SHOULD_EVALUATE);
        }
    }

    public final void onScriptEvaluated() {
        this._shouldEvaluateScript.setValue(HostedWebModuleScriptState.EVALUATED);
    }

    public final void processCallback(HostedWebModuleCallbacks type, String message) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(message, "message");
        switch (WhenMappings.$EnumSwitchMapping$0[type.ordinal()]) {
            case 1:
                processCaptureSdkModuleFinish(message);
                break;
            case 2:
            case 3:
            case 4:
                processCaptureSdkModuleError(message);
                break;
            case 5:
                processCaptureSdkAnalytics(message);
                break;
            case 6:
                processCaptureSdkExternalLink(message);
                break;
            case 7:
                processCaptureSdkNavigationExit();
                break;
            case 8:
                processCaptureSdkBackNavigation();
                break;
            default:
                Timber.INSTANCE.i("Not implemented yet", new Object[0]);
                break;
        }
    }
}
