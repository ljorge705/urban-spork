package com.onfido.android.sdk.capture.core.config;

import android.os.ResultReceiver;
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
import io.sentry.protocol.OperatingSystem;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001:\u0001\u0007R\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"Lcom/onfido/android/sdk/capture/core/config/OnfidoNewConfig;", "Lcom/onfido/android/sdk/FlowConfig;", "flows", "", "Lcom/onfido/android/sdk/capture/core/config/Flow;", "getFlows", "()Ljava/util/List;", "Builder", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface OnfidoNewConfig extends FlowConfig {

    @Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\u0006\u0010\u0015\u001a\u00020\u0016J\u0006\u0010\u0017\u001a\u00020\u0000J\u000e\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u001aJ\u000e\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u001dJ\u000e\u0010\u001e\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u001f\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010 \u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020!J\u000e\u0010\"\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010#\u001a\u00020\u00002\u0006\u0010$\u001a\u00020%R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lcom/onfido/android/sdk/capture/core/config/OnfidoNewConfig$Builder;", "", "sdkToken", "", "flows", "", "Lcom/onfido/android/sdk/capture/core/config/Flow;", "(Ljava/lang/String;Ljava/util/List;)V", "backgroundRunDisabled", "", "biometricTokenCallback", "Landroid/os/ResultReceiver;", "enterpriseFeatures", "Lcom/onfido/android/sdk/capture/EnterpriseFeatures;", "locale", "Ljava/util/Locale;", "mediaCallback", "onfidoAnalyticsEventListener", "theme", "Lcom/onfido/android/sdk/capture/OnfidoTheme;", "tokenExpirationHandlerEnabled", OperatingSystem.JsonKeys.BUILD, "Lcom/onfido/android/sdk/capture/core/config/OnfidoNewConfig;", "disableBackgroundRun", "withAnalyticsEventListener", "eventListener", "Lcom/onfido/android/sdk/capture/analytics/OnfidoAnalyticsEventListener;", "withBiometricTokenCallback", "tokenCallback", "Lcom/onfido/android/sdk/capture/config/BiometricTokenCallback;", "withEnterpriseFeatures", "withLocale", "withMediaCallback", "Lcom/onfido/android/sdk/capture/config/MediaCallback;", "withTheme", "withTokenExpirationHandler", "tokenExpirationHandler", "Lcom/onfido/android/sdk/capture/token/TokenExpirationHandler;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Builder {
        private boolean backgroundRunDisabled;
        private ResultReceiver biometricTokenCallback;
        private EnterpriseFeatures enterpriseFeatures;
        private final List<Flow> flows;
        private Locale locale;
        private ResultReceiver mediaCallback;
        private ResultReceiver onfidoAnalyticsEventListener;
        private final String sdkToken;
        private OnfidoTheme theme;
        private boolean tokenExpirationHandlerEnabled;

        /* JADX WARN: Multi-variable type inference failed */
        public Builder(String sdkToken, List<? extends Flow> flows) {
            Intrinsics.checkNotNullParameter(sdkToken, "sdkToken");
            Intrinsics.checkNotNullParameter(flows, "flows");
            this.sdkToken = sdkToken;
            this.flows = flows;
            if (!(!StringsKt.isBlank(sdkToken))) {
                throw new IllegalArgumentException("Sdk Token can't be blank".toString());
            }
            if (!(!flows.isEmpty())) {
                throw new IllegalArgumentException("Flow list can't be empty".toString());
            }
            this.theme = OnfidoTheme.AUTOMATIC;
        }

        public final OnfidoNewConfig build() {
            String str = this.sdkToken;
            Locale locale = this.locale;
            EnterpriseFeatures enterpriseFeatures = this.enterpriseFeatures;
            ResultReceiver resultReceiver = this.mediaCallback;
            ResultReceiver resultReceiver2 = this.biometricTokenCallback;
            List<Flow> list = this.flows;
            boolean z = this.backgroundRunDisabled;
            return new OnfidoNewConfigImpl(str, locale, null, enterpriseFeatures, resultReceiver, resultReceiver2, this.onfidoAnalyticsEventListener, list, this.tokenExpirationHandlerEnabled, z, this.theme, 4, null);
        }

        public final Builder disableBackgroundRun() {
            this.backgroundRunDisabled = true;
            return this;
        }

        public final Builder withAnalyticsEventListener(OnfidoAnalyticsEventListener eventListener) {
            Intrinsics.checkNotNullParameter(eventListener, "eventListener");
            this.onfidoAnalyticsEventListener = new OnfidoAnalyticsEventResultReceiver(eventListener);
            return this;
        }

        public final Builder withBiometricTokenCallback(BiometricTokenCallback tokenCallback) {
            Intrinsics.checkNotNullParameter(tokenCallback, "tokenCallback");
            BiometricTokenRetrievalService.INSTANCE.setTokenRetrievalCallback(tokenCallback);
            this.biometricTokenCallback = new BiometricTokenCallbackResultReceiver(tokenCallback);
            return this;
        }

        public final Builder withEnterpriseFeatures(EnterpriseFeatures enterpriseFeatures) {
            Intrinsics.checkNotNullParameter(enterpriseFeatures, "enterpriseFeatures");
            this.enterpriseFeatures = enterpriseFeatures;
            return this;
        }

        public final Builder withLocale(Locale locale) {
            Intrinsics.checkNotNullParameter(locale, "locale");
            this.locale = locale;
            return this;
        }

        public final Builder withMediaCallback(MediaCallback mediaCallback) {
            Intrinsics.checkNotNullParameter(mediaCallback, "mediaCallback");
            this.mediaCallback = new MediaCallbackResultReceiver(mediaCallback);
            return this;
        }

        public final Builder withTheme(OnfidoTheme theme) {
            Intrinsics.checkNotNullParameter(theme, "theme");
            this.theme = theme;
            return this;
        }

        public final Builder withTokenExpirationHandler(TokenExpirationHandler tokenExpirationHandler) {
            Intrinsics.checkNotNullParameter(tokenExpirationHandler, "tokenExpirationHandler");
            this.tokenExpirationHandlerEnabled = true;
            TokenExpirationHandlerService.INSTANCE.setTokenExpirationHandler(tokenExpirationHandler);
            return this;
        }
    }

    List<Flow> getFlows();
}
