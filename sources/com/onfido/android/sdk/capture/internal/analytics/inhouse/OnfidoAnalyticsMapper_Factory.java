package com.onfido.android.sdk.capture.internal.analytics.inhouse;

import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.android.sdk.capture.analytics.IdentityInteractor;
import com.onfido.android.sdk.capture.internal.util.SessionIdProvider;
import com.onfido.android.sdk.capture.internal.util.environment.EnvironmentIntegrityChecker;
import com.onfido.android.sdk.capture.utils.DeviceMetadataProvider;
import com.onfido.android.sdk.capture.utils.TimeProvider;
import com.onfido.android.sdk.capture.utils.UuidProvider;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;
import kotlinx.serialization.json.Json;

/* loaded from: classes2.dex */
public final class OnfidoAnalyticsMapper_Factory implements Factory<OnfidoAnalyticsMapper> {
    private final Provider<DeviceMetadataProvider> deviceMetadataProvider;
    private final Provider<EnvironmentIntegrityChecker> environmentIntegrityCheckerProvider;
    private final Provider<IdentityInteractor> identityInteractorProvider;
    private final Provider<Json> jsonParserProvider;
    private final Provider<AnalyticsMetadataProvider> metadataProvider;
    private final Provider<OnfidoConfig> onfidoConfigProvider;
    private final Provider<SdkTokenParser> sdkTokenParserProvider;
    private final Provider<SessionIdProvider> sessionIdProvider;
    private final Provider<TimeProvider> timeProvider;
    private final Provider<UuidProvider> uuidProvider;

    public OnfidoAnalyticsMapper_Factory(Provider<TimeProvider> provider, Provider<UuidProvider> provider2, Provider<AnalyticsMetadataProvider> provider3, Provider<OnfidoConfig> provider4, Provider<SessionIdProvider> provider5, Provider<SdkTokenParser> provider6, Provider<DeviceMetadataProvider> provider7, Provider<Json> provider8, Provider<EnvironmentIntegrityChecker> provider9, Provider<IdentityInteractor> provider10) {
        this.timeProvider = provider;
        this.uuidProvider = provider2;
        this.metadataProvider = provider3;
        this.onfidoConfigProvider = provider4;
        this.sessionIdProvider = provider5;
        this.sdkTokenParserProvider = provider6;
        this.deviceMetadataProvider = provider7;
        this.jsonParserProvider = provider8;
        this.environmentIntegrityCheckerProvider = provider9;
        this.identityInteractorProvider = provider10;
    }

    public static OnfidoAnalyticsMapper_Factory create(Provider<TimeProvider> provider, Provider<UuidProvider> provider2, Provider<AnalyticsMetadataProvider> provider3, Provider<OnfidoConfig> provider4, Provider<SessionIdProvider> provider5, Provider<SdkTokenParser> provider6, Provider<DeviceMetadataProvider> provider7, Provider<Json> provider8, Provider<EnvironmentIntegrityChecker> provider9, Provider<IdentityInteractor> provider10) {
        return new OnfidoAnalyticsMapper_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10);
    }

    public static OnfidoAnalyticsMapper newInstance(TimeProvider timeProvider, UuidProvider uuidProvider, AnalyticsMetadataProvider analyticsMetadataProvider, OnfidoConfig onfidoConfig, SessionIdProvider sessionIdProvider, SdkTokenParser sdkTokenParser, DeviceMetadataProvider deviceMetadataProvider, Json json, EnvironmentIntegrityChecker environmentIntegrityChecker, IdentityInteractor identityInteractor) {
        return new OnfidoAnalyticsMapper(timeProvider, uuidProvider, analyticsMetadataProvider, onfidoConfig, sessionIdProvider, sdkTokenParser, deviceMetadataProvider, json, environmentIntegrityChecker, identityInteractor);
    }

    @Override // com.onfido.javax.inject.Provider
    public OnfidoAnalyticsMapper get() {
        return newInstance(this.timeProvider.get(), this.uuidProvider.get(), this.metadataProvider.get(), this.onfidoConfigProvider.get(), this.sessionIdProvider.get(), this.sdkTokenParserProvider.get(), this.deviceMetadataProvider.get(), this.jsonParserProvider.get(), this.environmentIntegrityCheckerProvider.get(), this.identityInteractorProvider.get());
    }
}
