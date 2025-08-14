package com.onfido.api.client;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

/* compiled from: UtilsExt.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0000¨\u0006\u0006"}, d2 = {"moveInterceptorsToTop", "", "Lokhttp3/OkHttpClient$Builder;", "interceptors", "", "Lokhttp3/Interceptor;", "onfido-api-client"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class UtilsExtKt {
    public static final void moveInterceptorsToTop(OkHttpClient.Builder builder, List<? extends Interceptor> interceptors) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        Intrinsics.checkNotNullParameter(interceptors, "interceptors");
        builder.interceptors().removeAll(interceptors);
        Iterator<T> it = interceptors.iterator();
        while (it.hasNext()) {
            builder.addInterceptor((Interceptor) it.next());
        }
    }
}
