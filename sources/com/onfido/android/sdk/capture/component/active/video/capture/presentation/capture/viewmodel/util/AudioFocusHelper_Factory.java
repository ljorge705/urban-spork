package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.util;

import android.content.Context;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class AudioFocusHelper_Factory implements Factory<AudioFocusHelper> {
    private final Provider<Context> contextProvider;

    public AudioFocusHelper_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public static AudioFocusHelper_Factory create(Provider<Context> provider) {
        return new AudioFocusHelper_Factory(provider);
    }

    public static AudioFocusHelper newInstance(Context context) {
        return new AudioFocusHelper(context);
    }

    @Override // com.onfido.javax.inject.Provider
    public AudioFocusHelper get() {
        return newInstance(this.contextProvider.get());
    }
}
