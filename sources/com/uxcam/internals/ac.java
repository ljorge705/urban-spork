package com.uxcam.internals;

/* loaded from: classes6.dex */
public final class ac implements bf {

    /* renamed from: a, reason: collision with root package name */
    public final String f75a;

    public ac(String str) {
        this.f75a = str;
    }

    @Override // com.uxcam.internals.bf
    public final int a() {
        return 1;
    }

    public final boolean equals(Object obj) {
        if ((obj instanceof ac) && ((ac) obj).f75a.equalsIgnoreCase(this.f75a)) {
            return true;
        }
        return super.equals(obj);
    }

    @Override // com.uxcam.internals.bf
    public final String getValue() {
        return this.f75a;
    }

    public final int hashCode() {
        return this.f75a.hashCode();
    }
}
