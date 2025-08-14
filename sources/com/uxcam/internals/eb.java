package com.uxcam.internals;

import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import okhttp3.Request;
import okhttp3.Response;

/* loaded from: classes6.dex */
public final class eb {

    /* renamed from: a, reason: collision with root package name */
    public final int f131a;
    public final Request b;
    public final Response c;
    public final Throwable d;
    public final aa e;

    public static class aa {

        /* renamed from: a, reason: collision with root package name */
        public final long f132a;
        public final long b;

        public aa(long j, long j2) {
            this.f132a = j;
            this.b = j2;
        }
    }

    public eb(Response response, aa aaVar) {
        this.e = aaVar;
        this.b = response.request();
        this.c = response;
        this.f131a = response.code();
        if (b()) {
            this.d = null;
        } else {
            this.d = new Throwable(response.code() + ": " + response.message() + ". Call was successful but the request was not.");
        }
    }

    public final boolean a() {
        return !(this.d == null || b());
    }

    public final boolean b() {
        int i = this.f131a;
        return i >= 200 && i <= 299;
    }

    public final String toString() {
        return "[ " + this.b.hashCode() + " ] CallPair{request=" + this.b.toString() + ", response=" + this.c + AbstractJsonLexerKt.END_OBJ;
    }

    public eb(Request request, Throwable th, aa aaVar) {
        this.d = th;
        this.e = aaVar;
        this.b = request;
        this.c = null;
        this.f131a = -1;
    }
}
