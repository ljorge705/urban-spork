package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.recorder.persistentsurface;

import com.onfido.android.sdk.capture.utils.RawResourceReader;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class LocalPersistentRecorderSurfaceRepository_Factory implements Factory<LocalPersistentRecorderSurfaceRepository> {
    private final Provider<RawResourceReader> rawResourceReaderProvider;

    public LocalPersistentRecorderSurfaceRepository_Factory(Provider<RawResourceReader> provider) {
        this.rawResourceReaderProvider = provider;
    }

    public static LocalPersistentRecorderSurfaceRepository_Factory create(Provider<RawResourceReader> provider) {
        return new LocalPersistentRecorderSurfaceRepository_Factory(provider);
    }

    public static LocalPersistentRecorderSurfaceRepository newInstance(RawResourceReader rawResourceReader) {
        return new LocalPersistentRecorderSurfaceRepository(rawResourceReader);
    }

    @Override // com.onfido.javax.inject.Provider
    public LocalPersistentRecorderSurfaceRepository get() {
        return newInstance(this.rawResourceReaderProvider.get());
    }
}
