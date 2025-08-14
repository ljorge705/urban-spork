package com.onfido.android.sdk.capture.internal.network;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Interceptor;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010#\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0005J\u000e\u0010\r\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0005R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/network/InternalOnfidoNetworkInterceptorsProvider;", "", "()V", "interceptors", "", "Lokhttp3/Interceptor;", "getInterceptors", "()Ljava/util/List;", "mutableInterceptors", "", "addInterceptor", "", "interceptor", "removeInterceptor", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class InternalOnfidoNetworkInterceptorsProvider {
    public static final InternalOnfidoNetworkInterceptorsProvider INSTANCE = new InternalOnfidoNetworkInterceptorsProvider();
    private static final Set<Interceptor> mutableInterceptors = new LinkedHashSet();

    private InternalOnfidoNetworkInterceptorsProvider() {
    }

    public final void addInterceptor(Interceptor interceptor) {
        Intrinsics.checkNotNullParameter(interceptor, "interceptor");
        mutableInterceptors.add(interceptor);
    }

    public final List<Interceptor> getInterceptors() {
        return CollectionsKt.toList(mutableInterceptors);
    }

    public final void removeInterceptor(Interceptor interceptor) {
        Intrinsics.checkNotNullParameter(interceptor, "interceptor");
        mutableInterceptors.remove(interceptor);
    }
}
