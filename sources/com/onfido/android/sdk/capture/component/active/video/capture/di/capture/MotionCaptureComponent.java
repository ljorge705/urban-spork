package com.onfido.android.sdk.capture.component.active.video.capture.di.capture;

import com.onfido.android.sdk.capture.common.di.FragmentScope;
import com.onfido.android.sdk.capture.component.active.video.capture.di.host.MotionHostComponent;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.MotionCaptureFragment;
import com.onfido.dagger.Component;
import io.sentry.protocol.Request;
import kotlin.Metadata;

@FragmentScope
@Component(dependencies = {MotionHostComponent.class}, modules = {MotionCaptureModule.class, MotionFrameSamplerModule.class})
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\ba\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/di/capture/MotionCaptureComponent;", "", "inject", "", Request.JsonKeys.FRAGMENT, "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/view/MotionCaptureFragment;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface MotionCaptureComponent {
    void inject(MotionCaptureFragment fragment);
}
