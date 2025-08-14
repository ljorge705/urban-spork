package com.onfido.android.sdk.capture.ui.proofOfAddress.host;

import androidx.lifecycle.SavedStateHandle;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.ui.proofOfAddress.network.PoaRepository;
import com.onfido.android.sdk.capture.utils.RawResourceReader;
import com.onfido.javax.inject.Provider;
import kotlinx.serialization.json.Json;

/* renamed from: com.onfido.android.sdk.capture.ui.proofOfAddress.host.PoaHostViewModel_Factory, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0714PoaHostViewModel_Factory {
    private final Provider<Json> jsonParserProvider;
    private final Provider<PoaRepository> poaRepositoryProvider;
    private final Provider<RawResourceReader> resourceReaderProvider;
    private final Provider<SchedulersProvider> schedulersProvider;

    public C0714PoaHostViewModel_Factory(Provider<PoaRepository> provider, Provider<SchedulersProvider> provider2, Provider<RawResourceReader> provider3, Provider<Json> provider4) {
        this.poaRepositoryProvider = provider;
        this.schedulersProvider = provider2;
        this.resourceReaderProvider = provider3;
        this.jsonParserProvider = provider4;
    }

    public static C0714PoaHostViewModel_Factory create(Provider<PoaRepository> provider, Provider<SchedulersProvider> provider2, Provider<RawResourceReader> provider3, Provider<Json> provider4) {
        return new C0714PoaHostViewModel_Factory(provider, provider2, provider3, provider4);
    }

    public static PoaHostViewModel newInstance(PoaRepository poaRepository, SchedulersProvider schedulersProvider, RawResourceReader rawResourceReader, Json json, SavedStateHandle savedStateHandle) {
        return new PoaHostViewModel(poaRepository, schedulersProvider, rawResourceReader, json, savedStateHandle);
    }

    public PoaHostViewModel get(SavedStateHandle savedStateHandle) {
        return newInstance(this.poaRepositoryProvider.get(), this.schedulersProvider.get(), this.resourceReaderProvider.get(), this.jsonParserProvider.get(), savedStateHandle);
    }
}
