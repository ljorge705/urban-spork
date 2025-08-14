package com.onfido.android.sdk.capture.internal.util.logging;

import com.onfido.dagger.internal.Factory;

/* loaded from: classes2.dex */
public final class LoggerMemoryCachingDataSource_Factory implements Factory<LoggerMemoryCachingDataSource> {

    private static final class InstanceHolder {
        private static final LoggerMemoryCachingDataSource_Factory INSTANCE = new LoggerMemoryCachingDataSource_Factory();

        private InstanceHolder() {
        }
    }

    public static LoggerMemoryCachingDataSource_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static LoggerMemoryCachingDataSource newInstance() {
        return new LoggerMemoryCachingDataSource();
    }

    @Override // com.onfido.javax.inject.Provider
    public LoggerMemoryCachingDataSource get() {
        return newInstance();
    }
}
