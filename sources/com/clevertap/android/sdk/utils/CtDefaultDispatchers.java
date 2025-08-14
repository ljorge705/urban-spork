package com.clevertap.android.sdk.utils;

import io.sentry.protocol.SentryThread;
import kotlin.Metadata;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.Dispatchers;

/* compiled from: CtDefaultDispatchers.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0004H\u0016J\b\u0010\u0006\u001a\u00020\u0004H\u0016¨\u0006\u0007"}, d2 = {"Lcom/clevertap/android/sdk/utils/CtDefaultDispatchers;", "Lcom/clevertap/android/sdk/utils/DispatcherProvider;", "()V", "io", "Lkotlinx/coroutines/CoroutineDispatcher;", SentryThread.JsonKeys.MAIN, "processing", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class CtDefaultDispatchers implements DispatcherProvider {
    @Override // com.clevertap.android.sdk.utils.DispatcherProvider
    public CoroutineDispatcher io() {
        return Dispatchers.getIO();
    }

    @Override // com.clevertap.android.sdk.utils.DispatcherProvider
    public CoroutineDispatcher main() {
        return Dispatchers.getMain();
    }

    @Override // com.clevertap.android.sdk.utils.DispatcherProvider
    public CoroutineDispatcher processing() {
        return Dispatchers.getUnconfined();
    }
}
