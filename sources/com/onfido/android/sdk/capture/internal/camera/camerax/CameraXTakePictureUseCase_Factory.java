package com.onfido.android.sdk.capture.internal.camera.camerax;

import android.content.Context;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class CameraXTakePictureUseCase_Factory implements Factory<CameraXTakePictureUseCase> {
    private final Provider<Context> applicationContextProvider;

    public CameraXTakePictureUseCase_Factory(Provider<Context> provider) {
        this.applicationContextProvider = provider;
    }

    public static CameraXTakePictureUseCase_Factory create(Provider<Context> provider) {
        return new CameraXTakePictureUseCase_Factory(provider);
    }

    public static CameraXTakePictureUseCase newInstance(Context context) {
        return new CameraXTakePictureUseCase(context);
    }

    @Override // com.onfido.javax.inject.Provider
    public CameraXTakePictureUseCase get() {
        return newInstance(this.applicationContextProvider.get());
    }
}
