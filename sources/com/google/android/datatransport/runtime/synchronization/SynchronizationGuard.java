package com.google.android.datatransport.runtime.synchronization;

/* loaded from: classes5.dex */
public interface SynchronizationGuard {

    public interface CriticalSection<T> {
        T execute();
    }

    <T> T runCriticalSection(CriticalSection<T> criticalSection);
}
