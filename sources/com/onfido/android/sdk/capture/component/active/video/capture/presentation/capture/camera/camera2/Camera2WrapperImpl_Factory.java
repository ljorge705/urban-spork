package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2;

import android.content.Context;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.util.SurfaceSizeProvider;
import com.onfido.api.client.data.SdkConfiguration;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class Camera2WrapperImpl_Factory implements Factory<Camera2WrapperImpl> {
    private final Provider<Context> contextProvider;
    private final Provider<SdkConfiguration.MotionCapture.MotionVideoSettings> motionVideoSettingsProvider;
    private final Provider<SurfaceSizeProvider> surfaceSizeProvider;

    public Camera2WrapperImpl_Factory(Provider<Context> provider, Provider<SurfaceSizeProvider> provider2, Provider<SdkConfiguration.MotionCapture.MotionVideoSettings> provider3) {
        this.contextProvider = provider;
        this.surfaceSizeProvider = provider2;
        this.motionVideoSettingsProvider = provider3;
    }

    public static Camera2WrapperImpl_Factory create(Provider<Context> provider, Provider<SurfaceSizeProvider> provider2, Provider<SdkConfiguration.MotionCapture.MotionVideoSettings> provider3) {
        return new Camera2WrapperImpl_Factory(provider, provider2, provider3);
    }

    public static Camera2WrapperImpl newInstance(Context context, SurfaceSizeProvider surfaceSizeProvider, SdkConfiguration.MotionCapture.MotionVideoSettings motionVideoSettings) {
        return new Camera2WrapperImpl(context, surfaceSizeProvider, motionVideoSettings);
    }

    @Override // com.onfido.javax.inject.Provider
    public Camera2WrapperImpl get() {
        return newInstance(this.contextProvider.get(), this.surfaceSizeProvider.get(), this.motionVideoSettingsProvider.get());
    }
}
