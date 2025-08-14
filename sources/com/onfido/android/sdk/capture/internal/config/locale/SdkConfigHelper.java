package com.onfido.android.sdk.capture.internal.config.locale;

import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.api.client.data.SdkConfiguration;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n¨\u0006\u000b"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/config/locale/SdkConfigHelper;", "", "()V", "createSDKConfiguration", "Lcom/onfido/api/client/data/SdkConfiguration;", "blurValidationMaxRetry", "", "onfidoRemoteConfig", "Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;", "source", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class SdkConfigHelper {
    public static final SdkConfigHelper INSTANCE = new SdkConfigHelper();

    private SdkConfigHelper() {
    }

    public static /* synthetic */ SdkConfiguration createSDKConfiguration$default(SdkConfigHelper sdkConfigHelper, int i, OnfidoRemoteConfig onfidoRemoteConfig, String str, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            str = "local";
        }
        return sdkConfigHelper.createSDKConfiguration(i, onfidoRemoteConfig, str);
    }

    public final SdkConfiguration createSDKConfiguration(int blurValidationMaxRetry, OnfidoRemoteConfig onfidoRemoteConfig, String source) {
        Intrinsics.checkNotNullParameter(onfidoRemoteConfig, "onfidoRemoteConfig");
        Intrinsics.checkNotNullParameter(source, "source");
        return new SdkConfiguration(new SdkConfiguration.Validations(new SdkConfiguration.Validations.OnDevice(new SdkConfiguration.Validations.OnDevice.ValidationType(blurValidationMaxRetry))), onfidoRemoteConfig.getDocumentCapture(), new SdkConfiguration.ExperimentalFeatures(onfidoRemoteConfig.isImageQualityServiceEnabled(), onfidoRemoteConfig.isMultiImageCaptureEnabled(), new SdkConfiguration.ExperimentalFeatures.MotionExperiment(onfidoRemoteConfig.getMotionExperiment().isEnabled(), onfidoRemoteConfig.getMotionExperiment().getReason()), false, false, false, false, false, false, false, false, false, false, false, false, false, (SdkConfiguration.ExperimentalFeatures.WaitingScreens) null, false, false, (SdkConfiguration.CameraXConfiguration) null, false, false, (SdkConfiguration.ExperimentalFeatures.DocumentDetectionExperiment) null, 8388600, (DefaultConstructorMarker) null), onfidoRemoteConfig.getLivenessCapture(), onfidoRemoteConfig.getSelfieCapture(), onfidoRemoteConfig.getMotionCapture(), new SdkConfiguration.SdkFeatures(onfidoRemoteConfig.isInhouseAnalyticsEnabled(), false, onfidoRemoteConfig.isApplicantConsentRequired(), onfidoRemoteConfig.getLoggerConfiguration(), false, false, false, 114, (DefaultConstructorMarker) null), source);
    }
}
