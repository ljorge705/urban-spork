package com.uxcam.internals;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes6.dex */
public final class bz implements bf {

    /* renamed from: a, reason: collision with root package name */
    public final Map f105a;
    public final String b;
    public final float c;
    public String d;
    public boolean e;

    public bz(String str, float f, String str2, HashMap map) {
        this.b = str;
        this.c = f;
        this.d = str2;
        this.f105a = map;
    }

    @Override // com.uxcam.internals.bf
    public final int a() {
        return 2;
    }

    @Override // com.uxcam.internals.bf
    public final String getValue() {
        return this.b;
    }
}
