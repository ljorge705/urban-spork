package com.onfido.android.sdk.capture.internal.util.logging;

import com.onfido.javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0007\b\u0007¢\u0006\u0002\u0010\u0002J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005H\u0016J\u0016\u0010\u000b\u001a\u00020\t2\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\fH\u0016J\u000e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\fH\u0016J\u0016\u0010\u000e\u001a\u00020\t2\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\fH\u0016R2\u0010\u0003\u001a&\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u0005 \u0006*\u0012\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u0005\u0018\u00010\u00070\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/util/logging/LoggerMemoryCachingDataSource;", "Lcom/onfido/android/sdk/capture/internal/util/logging/LoggerCachingDataSource;", "()V", "logs", "", "Lcom/onfido/android/sdk/capture/internal/util/logging/OnfidoRemoteLog;", "kotlin.jvm.PlatformType", "", "add", "", OnfidoLogMapper.LOG_EVENT_TYPE, "addAll", "", "getLogs", "removeAll", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class LoggerMemoryCachingDataSource implements LoggerCachingDataSource {
    private final Collection<OnfidoRemoteLog> logs = Collections.synchronizedCollection(new ArrayList());

    @Inject
    public LoggerMemoryCachingDataSource() {
    }

    @Override // com.onfido.android.sdk.capture.internal.util.logging.LoggerCachingDataSource
    public void add(OnfidoRemoteLog log) {
        Intrinsics.checkNotNullParameter(log, "log");
        this.logs.add(log);
    }

    @Override // com.onfido.android.sdk.capture.internal.util.logging.LoggerCachingDataSource
    public void addAll(List<OnfidoRemoteLog> logs) {
        Intrinsics.checkNotNullParameter(logs, "logs");
        this.logs.addAll(logs);
    }

    @Override // com.onfido.android.sdk.capture.internal.util.logging.LoggerCachingDataSource
    public List<OnfidoRemoteLog> getLogs() {
        Collection<OnfidoRemoteLog> logs = this.logs;
        Intrinsics.checkNotNullExpressionValue(logs, "logs");
        return CollectionsKt.toList(logs);
    }

    @Override // com.onfido.android.sdk.capture.internal.util.logging.LoggerCachingDataSource
    public void removeAll(List<OnfidoRemoteLog> logs) {
        Intrinsics.checkNotNullParameter(logs, "logs");
        this.logs.removeAll(CollectionsKt.toSet(logs));
    }
}
