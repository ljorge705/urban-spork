package com.onfido.api.client.interceptor;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Invocation;

/* loaded from: classes6.dex */
public class ApiVersioningInterceptor implements Interceptor {
    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        API api = (API) ((Invocation) chain.request().tag(Invocation.class)).method().getAnnotation(API.class);
        Request request = chain.request();
        if (api != null) {
            String url = request.url().getUrl();
            StringBuilder sb = new StringBuilder(url);
            sb.insert(url.indexOf("/", request.url().scheme().length() + 3), "/" + api.version());
            request = request.newBuilder().url(request.url().newBuilder(sb.toString()).build()).build();
        }
        return chain.proceed(request);
    }
}
