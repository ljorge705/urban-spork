package com.onfido.workflow;

import android.os.ResultReceiver;
import android.util.Log;
import com.onfido.android.sdk.FlowConfig;
import com.onfido.android.sdk.capture.EnterpriseFeatures;
import com.onfido.android.sdk.capture.OnfidoTheme;
import com.onfido.android.sdk.capture.analytics.OnfidoAnalyticsEventListener;
import com.onfido.android.sdk.capture.analytics.OnfidoAnalyticsEventResultReceiver;
import com.onfido.android.sdk.capture.biometricToken.BiometricTokenRetrievalService;
import com.onfido.android.sdk.capture.config.BiometricTokenCallback;
import com.onfido.android.sdk.capture.config.BiometricTokenCallbackResultReceiver;
import com.onfido.android.sdk.capture.config.MediaCallback;
import com.onfido.android.sdk.capture.config.MediaCallbackResultReceiver;
import com.onfido.android.sdk.capture.token.TokenExpirationHandler;
import com.onfido.android.sdk.capture.token.TokenExpirationHandlerService;
import com.onfido.api.client.token.sdk.SDKToken;
import com.onfido.workflow.internal.WorkflowConfigImpl;
import io.sentry.protocol.OperatingSystem;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: WorkflowConfig.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001:\u0001 R\u0014\u0010\u0002\u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u0004\u0018\u00010\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u0004\u0018\u00010\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0005R\u0014\u0010\u0010\u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0005R\u0012\u0010\u0012\u001a\u00020\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0012\u0010\u0016\u001a\u00020\u0017X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0012\u0010\u001a\u001a\u00020\u001bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0012\u0010\u001e\u001a\u00020\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0015¨\u0006!"}, d2 = {"Lcom/onfido/workflow/WorkflowConfig;", "Lcom/onfido/android/sdk/FlowConfig;", "biometricTokenCallback", "Landroid/os/ResultReceiver;", "getBiometricTokenCallback", "()Landroid/os/ResultReceiver;", "enterpriseFeatures", "Lcom/onfido/android/sdk/capture/EnterpriseFeatures;", "getEnterpriseFeatures", "()Lcom/onfido/android/sdk/capture/EnterpriseFeatures;", "locale", "Ljava/util/Locale;", "getLocale", "()Ljava/util/Locale;", "mediaCallback", "getMediaCallback", "onfidoAnalyticsEventListener", "getOnfidoAnalyticsEventListener", "sdkToken", "", "getSdkToken", "()Ljava/lang/String;", "theme", "Lcom/onfido/android/sdk/capture/OnfidoTheme;", "getTheme", "()Lcom/onfido/android/sdk/capture/OnfidoTheme;", "tokenExpirationHandlerEnabled", "", "getTokenExpirationHandlerEnabled", "()Z", "workflowRunId", "getWorkflowRunId", "Builder", "onfido-workflow-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public interface WorkflowConfig extends FlowConfig {
    @Override // com.onfido.android.sdk.FlowConfig
    ResultReceiver getBiometricTokenCallback();

    @Override // com.onfido.android.sdk.FlowConfig
    EnterpriseFeatures getEnterpriseFeatures();

    @Override // com.onfido.android.sdk.FlowConfig
    Locale getLocale();

    @Override // com.onfido.android.sdk.FlowConfig
    ResultReceiver getMediaCallback();

    @Override // com.onfido.android.sdk.FlowConfig
    ResultReceiver getOnfidoAnalyticsEventListener();

    @Override // com.onfido.android.sdk.FlowConfig
    String getSdkToken();

    @Override // com.onfido.android.sdk.FlowConfig
    OnfidoTheme getTheme();

    @Override // com.onfido.android.sdk.FlowConfig
    boolean getTokenExpirationHandlerEnabled();

    @Override // com.onfido.android.sdk.FlowConfig
    String getWorkflowRunId();

    /* compiled from: WorkflowConfig.kt */
    @Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u0006\u0010\u0013\u001a\u00020\u0014J\u0006\u0010\u0015\u001a\u00020\u0000J\u000e\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u001bJ\u000e\u0010\u001c\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\u001d\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u001e\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u001fJ\u000e\u0010 \u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010!\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020#R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/onfido/workflow/WorkflowConfig$Builder;", "", "sdkToken", "", "workflowRunId", "(Ljava/lang/String;Ljava/lang/String;)V", "backgroundRunDisabled", "", "biometricTokenCallback", "Landroid/os/ResultReceiver;", "enterpriseFeatures", "Lcom/onfido/android/sdk/capture/EnterpriseFeatures;", "locale", "Ljava/util/Locale;", "mediaCallback", "onfidoAnalyticsEventListener", "theme", "Lcom/onfido/android/sdk/capture/OnfidoTheme;", "tokenExpirationHandlerEnabled", OperatingSystem.JsonKeys.BUILD, "Lcom/onfido/workflow/WorkflowConfig;", "disableBackgroundRun", "withAnalyticsEventListener", "eventListener", "Lcom/onfido/android/sdk/capture/analytics/OnfidoAnalyticsEventListener;", "withBiometricTokenCallback", "tokenCallback", "Lcom/onfido/android/sdk/capture/config/BiometricTokenCallback;", "withEnterpriseFeatures", "withLocale", "withMediaCallback", "Lcom/onfido/android/sdk/capture/config/MediaCallback;", "withTheme", "withTokenExpirationHandler", "tokenExpirationHandler", "Lcom/onfido/android/sdk/capture/token/TokenExpirationHandler;", "onfido-workflow-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Builder {
        private boolean backgroundRunDisabled;
        private ResultReceiver biometricTokenCallback;
        private EnterpriseFeatures enterpriseFeatures;
        private Locale locale;
        private ResultReceiver mediaCallback;
        private ResultReceiver onfidoAnalyticsEventListener;
        private final String sdkToken;
        private OnfidoTheme theme;
        private boolean tokenExpirationHandlerEnabled;
        private final String workflowRunId;

        public Builder(String sdkToken, String workflowRunId) {
            Intrinsics.checkNotNullParameter(sdkToken, "sdkToken");
            Intrinsics.checkNotNullParameter(workflowRunId, "workflowRunId");
            this.sdkToken = sdkToken;
            this.workflowRunId = workflowRunId;
            if (!new SDKToken(sdkToken, null).isStudioToken()) {
                Log.w("WorkflowConfig", "Usage of manually-generated SDK tokens alongside Studio workflows is deprecated. \nPlease use Studio tokens returned in the payload when creating a workflow run. \nFor more details see: https://documentation.onfido.com/api/latest/#sdk-tokens");
            }
            if (!(!StringsKt.isBlank(sdkToken))) {
                throw new IllegalArgumentException("sdkToken can't be blank".toString());
            }
            if (!(!StringsKt.isBlank(workflowRunId))) {
                throw new IllegalArgumentException("workflowRunId can't be blank".toString());
            }
            this.theme = OnfidoTheme.AUTOMATIC;
        }

        public final Builder withLocale(Locale locale) {
            Intrinsics.checkNotNullParameter(locale, "locale");
            this.locale = locale;
            return this;
        }

        public final Builder withEnterpriseFeatures(EnterpriseFeatures enterpriseFeatures) {
            Intrinsics.checkNotNullParameter(enterpriseFeatures, "enterpriseFeatures");
            this.enterpriseFeatures = enterpriseFeatures;
            return this;
        }

        public final Builder withMediaCallback(MediaCallback mediaCallback) {
            Intrinsics.checkNotNullParameter(mediaCallback, "mediaCallback");
            this.mediaCallback = new MediaCallbackResultReceiver(mediaCallback);
            return this;
        }

        public final Builder disableBackgroundRun() {
            this.backgroundRunDisabled = true;
            return this;
        }

        public final Builder withBiometricTokenCallback(BiometricTokenCallback tokenCallback) {
            Intrinsics.checkNotNullParameter(tokenCallback, "tokenCallback");
            BiometricTokenRetrievalService.INSTANCE.setTokenRetrievalCallback(tokenCallback);
            this.biometricTokenCallback = new BiometricTokenCallbackResultReceiver(tokenCallback);
            return this;
        }

        public final Builder withTokenExpirationHandler(TokenExpirationHandler tokenExpirationHandler) {
            Intrinsics.checkNotNullParameter(tokenExpirationHandler, "tokenExpirationHandler");
            this.tokenExpirationHandlerEnabled = true;
            TokenExpirationHandlerService.INSTANCE.setTokenExpirationHandler(tokenExpirationHandler);
            Log.d("WorkflowConfig", "withTokenExpirationHandler has been called, handler set.");
            return this;
        }

        public final Builder withAnalyticsEventListener(OnfidoAnalyticsEventListener eventListener) {
            Intrinsics.checkNotNullParameter(eventListener, "eventListener");
            this.onfidoAnalyticsEventListener = new OnfidoAnalyticsEventResultReceiver(eventListener);
            return this;
        }

        public final Builder withTheme(OnfidoTheme theme) {
            Intrinsics.checkNotNullParameter(theme, "theme");
            this.theme = theme;
            return this;
        }

        public final WorkflowConfig build() {
            return new WorkflowConfigImpl(this.sdkToken, this.workflowRunId, this.locale, this.enterpriseFeatures, this.mediaCallback, this.biometricTokenCallback, this.tokenExpirationHandlerEnabled, this.backgroundRunDisabled, this.onfidoAnalyticsEventListener, this.theme);
        }
    }
}
