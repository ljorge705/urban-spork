package com.onfido.android.sdk.capture.internal.util.logging;

import com.onfido.android.sdk.capture.internal.util.logging.network.OnfidoLoggerApi;
import com.onfido.android.sdk.capture.network.error.ErrorHandler;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class LoggerRepository_Factory implements Factory<LoggerRepository> {
    private final Provider<ErrorHandler> errorHandlerProvider;
    private final Provider<OnfidoLoggerApi> loggerApiProvider;
    private final Provider<LoggerCachingDataSource> loggerCachingDataSourceProvider;
    private final Provider<OnfidoLogMapper> onfidoLogMapperProvider;

    public LoggerRepository_Factory(Provider<OnfidoLoggerApi> provider, Provider<OnfidoLogMapper> provider2, Provider<ErrorHandler> provider3, Provider<LoggerCachingDataSource> provider4) {
        this.loggerApiProvider = provider;
        this.onfidoLogMapperProvider = provider2;
        this.errorHandlerProvider = provider3;
        this.loggerCachingDataSourceProvider = provider4;
    }

    public static LoggerRepository_Factory create(Provider<OnfidoLoggerApi> provider, Provider<OnfidoLogMapper> provider2, Provider<ErrorHandler> provider3, Provider<LoggerCachingDataSource> provider4) {
        return new LoggerRepository_Factory(provider, provider2, provider3, provider4);
    }

    public static LoggerRepository newInstance(OnfidoLoggerApi onfidoLoggerApi, OnfidoLogMapper onfidoLogMapper, ErrorHandler errorHandler, LoggerCachingDataSource loggerCachingDataSource) {
        return new LoggerRepository(onfidoLoggerApi, onfidoLogMapper, errorHandler, loggerCachingDataSource);
    }

    @Override // com.onfido.javax.inject.Provider
    public LoggerRepository get() {
        return newInstance(this.loggerApiProvider.get(), this.onfidoLogMapperProvider.get(), this.errorHandlerProvider.get(), this.loggerCachingDataSourceProvider.get());
    }
}
