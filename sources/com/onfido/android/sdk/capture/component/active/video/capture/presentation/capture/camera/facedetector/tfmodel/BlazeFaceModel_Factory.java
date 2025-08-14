package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.tfmodel;

import android.content.Context;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class BlazeFaceModel_Factory implements Factory<BlazeFaceModel> {
    private final Provider<Context> contextProvider;

    public BlazeFaceModel_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public static BlazeFaceModel_Factory create(Provider<Context> provider) {
        return new BlazeFaceModel_Factory(provider);
    }

    public static BlazeFaceModel newInstance(Context context) {
        return new BlazeFaceModel(context);
    }

    @Override // com.onfido.javax.inject.Provider
    public BlazeFaceModel get() {
        return newInstance(this.contextProvider.get());
    }
}
