package com.uxcam.internals;

/* loaded from: classes6.dex */
public final class ic {

    /* renamed from: a, reason: collision with root package name */
    public final dc f205a = new dc();
    public final dc b = new dc();
    public final db c = new db();
    public final db d = new db();

    public final ic a(String str, int i) throws NumberFormatException {
        int i2 = Integer.parseInt(str, 2);
        int length = str.length();
        this.c.a(i2 << (32 - length));
        this.d.a(length);
        this.f205a.a(i, this.c.b - 1);
        this.b.a(this.c.b - 1, i);
        return this;
    }

    public final ib a() {
        return new ib(this, this.c.a(), this.d.a());
    }
}
