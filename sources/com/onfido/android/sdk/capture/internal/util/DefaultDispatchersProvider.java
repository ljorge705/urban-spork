package com.onfido.android.sdk.capture.internal.util;

import com.facebook.hermes.intl.Constants;
import io.sentry.protocol.SentryThread;
import kotlin.Metadata;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.Dispatchers;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006¨\u0006\u000b"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/util/DefaultDispatchersProvider;", "Lcom/onfido/android/sdk/capture/internal/util/DispatchersProvider;", "()V", Constants.COLLATION_DEFAULT, "Lkotlinx/coroutines/CoroutineDispatcher;", "getDefault", "()Lkotlinx/coroutines/CoroutineDispatcher;", "io", "getIo", SentryThread.JsonKeys.MAIN, "getMain", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DefaultDispatchersProvider implements DispatchersProvider {
    public static final DefaultDispatchersProvider INSTANCE = new DefaultDispatchersProvider();
    private static final CoroutineDispatcher main = Dispatchers.getMain().getImmediate();
    private static final CoroutineDispatcher default = Dispatchers.getDefault();
    private static final CoroutineDispatcher io = Dispatchers.getIO();

    private DefaultDispatchersProvider() {
    }

    @Override // com.onfido.android.sdk.capture.internal.util.DispatchersProvider
    /* renamed from: getDefault */
    public CoroutineDispatcher mo5607getDefault() {
        return default;
    }

    @Override // com.onfido.android.sdk.capture.internal.util.DispatchersProvider
    public CoroutineDispatcher getIo() {
        return io;
    }

    @Override // com.onfido.android.sdk.capture.internal.util.DispatchersProvider
    public CoroutineDispatcher getMain() {
        return main;
    }
}
