package com.uxcam.internals;

/* loaded from: classes6.dex */
public final class dt {

    /* renamed from: a, reason: collision with root package name */
    public long f126a;
    public double b;
    public double c;

    public dt() {
        this(0);
    }

    public /* synthetic */ dt(int i) {
        this(0L, 0.0d, 0.0d);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof dt)) {
            return false;
        }
        dt dtVar = (dt) obj;
        return this.f126a == dtVar.f126a && Double.compare(this.b, dtVar.b) == 0 && Double.compare(this.c, dtVar.c) == 0;
    }

    public final int hashCode() {
        return Double.hashCode(this.c) + ((Double.hashCode(this.b) + (Long.hashCode(this.f126a) * 31)) * 31);
    }

    public final String toString() {
        return "Metrics(verificationResponseTime=" + this.f126a + ", dataFileSize=" + this.b + ", videoFileSize=" + this.c + ')';
    }

    public dt(long j, double d, double d2) {
        this.f126a = j;
        this.b = d;
        this.c = d2;
    }
}
