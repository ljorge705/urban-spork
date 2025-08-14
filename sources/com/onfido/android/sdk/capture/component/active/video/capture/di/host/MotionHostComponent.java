package com.onfido.android.sdk.capture.component.active.video.capture.di.host;

import android.content.Context;
import com.onfido.android.sdk.capture.common.di.SdkComponent;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.host.MotionHostFragment;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.intro.MotionIntroFragment;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.nofacedetected.MotionNoFaceDetectedFragment;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.upload.MotionUploadFragment;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.uploaderror.MotionUploadErrorFragment;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.OnfidoAnalytics;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.metadata.SdkUploadMetadataHelper;
import com.onfido.android.sdk.capture.internal.util.DispatchersProvider;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.ui.options.MotionCaptureVariantOptions;
import com.onfido.android.sdk.capture.utils.TimeProvider;
import com.onfido.dagger.Component;
import io.sentry.protocol.Request;
import kotlin.Metadata;

@FeatureScope
@Component(dependencies = {SdkComponent.class}, modules = {MotionHostModule.class, MotionHostBindsModule.class, NavigationModule.class})
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\ba\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H'J\b\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\nH&J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u000bH&J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\fH&J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\rH&J\b\u0010\u000e\u001a\u00020\u000fH&J\b\u0010\u0010\u001a\u00020\u0011H&J\b\u0010\u0012\u001a\u00020\u0013H&J\b\u0010\u0014\u001a\u00020\u0015H&J\b\u0010\u0016\u001a\u00020\u0017H&J\b\u0010\u0018\u001a\u00020\u0019H&¨\u0006\u001a"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/di/host/MotionHostComponent;", "", "applicationContext", "Landroid/content/Context;", "dispatchersProvider", "Lcom/onfido/android/sdk/capture/internal/util/DispatchersProvider;", "inject", "", Request.JsonKeys.FRAGMENT, "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/host/MotionHostFragment;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/intro/MotionIntroFragment;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/nofacedetected/MotionNoFaceDetectedFragment;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/upload/MotionUploadFragment;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/uploaderror/MotionUploadErrorFragment;", "motionOptions", "Lcom/onfido/android/sdk/capture/ui/options/MotionCaptureVariantOptions;", "onfidoAnalytics", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/OnfidoAnalytics;", "onfidoRemoteConfig", "Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;", "provideTimeProvider", "Lcom/onfido/android/sdk/capture/utils/TimeProvider;", "schedulersProvider", "Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;", "sdkUploadMetadataHelper", "Lcom/onfido/android/sdk/capture/internal/metadata/SdkUploadMetadataHelper;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface MotionHostComponent {
    Context applicationContext();

    DispatchersProvider dispatchersProvider();

    void inject(MotionHostFragment fragment);

    void inject(MotionIntroFragment fragment);

    void inject(MotionNoFaceDetectedFragment fragment);

    void inject(MotionUploadFragment fragment);

    void inject(MotionUploadErrorFragment fragment);

    MotionCaptureVariantOptions motionOptions();

    OnfidoAnalytics onfidoAnalytics();

    OnfidoRemoteConfig onfidoRemoteConfig();

    TimeProvider provideTimeProvider();

    SchedulersProvider schedulersProvider();

    SdkUploadMetadataHelper sdkUploadMetadataHelper();
}
