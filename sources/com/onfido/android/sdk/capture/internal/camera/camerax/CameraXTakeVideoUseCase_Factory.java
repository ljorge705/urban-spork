package com.onfido.android.sdk.capture.internal.camera.camerax;

import android.content.Context;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class CameraXTakeVideoUseCase_Factory implements Factory<CameraXTakeVideoUseCase> {
    private final Provider<Context> applicationContextProvider;

    public CameraXTakeVideoUseCase_Factory(Provider<Context> provider) {
        this.applicationContextProvider = provider;
    }

    public static CameraXTakeVideoUseCase_Factory create(Provider<Context> provider) {
        return new CameraXTakeVideoUseCase_Factory(provider);
    }

    public static CameraXTakeVideoUseCase newInstance(Context context) {
        return new CameraXTakeVideoUseCase(context);
    }

    @Override // com.onfido.javax.inject.Provider
    public CameraXTakeVideoUseCase get() {
        return newInstance(this.applicationContextProvider.get());
    }
}
