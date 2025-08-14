package com.clevertap.android.sdk.network.http;

import com.clevertap.android.sdk.Constants;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

/* compiled from: Response.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0018\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\t0\u0007\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\u0002\u0010\u000fJ\b\u0010\u0018\u001a\u00020\u000eH\u0016J\u0010\u0010\u0019\u001a\u0004\u0018\u00010\b2\u0006\u0010\u001a\u001a\u00020\bJ\u0006\u0010\u001b\u001a\u00020\u001cJ\b\u0010\u001d\u001a\u0004\u0018\u00010\bR\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R#\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\t0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u001e"}, d2 = {"Lcom/clevertap/android/sdk/network/http/Response;", "Ljava/io/Closeable;", "request", "Lcom/clevertap/android/sdk/network/http/Request;", "code", "", "headers", "", "", "", "bodyStream", "Ljava/io/InputStream;", "closeDelegate", "Lkotlin/Function0;", "", "(Lcom/clevertap/android/sdk/network/http/Request;ILjava/util/Map;Ljava/io/InputStream;Lkotlin/jvm/functions/Function0;)V", "bodyReader", "Ljava/io/Reader;", "getCode", "()I", "getHeaders", "()Ljava/util/Map;", "getRequest", "()Lcom/clevertap/android/sdk/network/http/Request;", Constants.KEY_HIDE_CLOSE, "getHeaderValue", "header", "isSuccess", "", "readBody", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class Response implements Closeable {
    private final Reader bodyReader;
    private final Function0<Unit> closeDelegate;
    private final int code;
    private final Map<String, List<String>> headers;
    private final Request request;

    public final int getCode() {
        return this.code;
    }

    public final Map<String, List<String>> getHeaders() {
        return this.headers;
    }

    public final Request getRequest() {
        return this.request;
    }

    public final boolean isSuccess() {
        return this.code == 200;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Response(Request request, int i, Map<String, ? extends List<String>> headers, InputStream inputStream, Function0<Unit> closeDelegate) {
        BufferedReader bufferedReader;
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(headers, "headers");
        Intrinsics.checkNotNullParameter(closeDelegate, "closeDelegate");
        this.request = request;
        this.code = i;
        this.headers = headers;
        this.closeDelegate = closeDelegate;
        if (inputStream != null) {
            Reader inputStreamReader = new InputStreamReader(inputStream, Charsets.UTF_8);
            bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
        } else {
            bufferedReader = null;
        }
        this.bodyReader = bufferedReader;
    }

    public final String getHeaderValue(String header) {
        Intrinsics.checkNotNullParameter(header, "header");
        List<String> list = this.headers.get(header);
        if (list != null) {
            return (String) CollectionsKt.lastOrNull((List) list);
        }
        return null;
    }

    public final String readBody() {
        Reader reader = this.bodyReader;
        if (reader != null) {
            return TextStreamsKt.readText(reader);
        }
        return null;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        Reader reader = this.bodyReader;
        if (reader != null) {
            reader.close();
        }
        this.closeDelegate.invoke();
    }
}
