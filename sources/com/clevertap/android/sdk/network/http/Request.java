package com.clevertap.android.sdk.network.http;

import android.net.Uri;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Request.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\t\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\bR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/clevertap/android/sdk/network/http/Request;", "", "url", "Landroid/net/Uri;", "headers", "", "", "body", "(Landroid/net/Uri;Ljava/util/Map;Ljava/lang/String;)V", "getBody", "()Ljava/lang/String;", "getHeaders", "()Ljava/util/Map;", "getUrl", "()Landroid/net/Uri;", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class Request {
    private final String body;
    private final Map<String, String> headers;
    private final Uri url;

    public final String getBody() {
        return this.body;
    }

    public final Map<String, String> getHeaders() {
        return this.headers;
    }

    public final Uri getUrl() {
        return this.url;
    }

    public Request(Uri url, Map<String, String> headers, String str) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(headers, "headers");
        this.url = url;
        this.headers = headers;
        this.body = str;
    }
}
