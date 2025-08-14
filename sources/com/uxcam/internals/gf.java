package com.uxcam.internals;

/* loaded from: classes6.dex */
public final class gf {

    /* renamed from: a, reason: collision with root package name */
    public final int f172a;
    public final int b;

    public gf(int i, int i2) {
        this.f172a = i;
        this.b = i2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || gf.class != obj.getClass()) {
            return false;
        }
        gf gfVar = (gf) obj;
        return this.b == gfVar.b && this.f172a == gfVar.f172a;
    }

    public final int hashCode() {
        return ((this.b + 31) * 31) + this.f172a;
    }
}
