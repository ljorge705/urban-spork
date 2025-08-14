package com.uxcam.internals;

/* loaded from: classes6.dex */
public final class em {

    /* renamed from: a, reason: collision with root package name */
    public final int f138a;
    public final int b;

    public em(int i, int i2) {
        this.f138a = i;
        this.b = i2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || em.class != obj.getClass()) {
            return false;
        }
        em emVar = (em) obj;
        return this.b == emVar.b && this.f138a == emVar.f138a;
    }

    public final int hashCode() {
        return ((this.b + 31) * 31) + this.f138a;
    }
}
