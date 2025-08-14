package com.uxcam.internals;

import java.util.Arrays;

/* loaded from: classes6.dex */
public enum ap {
    /* JADX INFO: Fake field, exist only in values array */
    MOV("qt  ", new String[]{"qt  "}),
    MP4("isom", new String[]{"isom", "iso2", "avc1", "mp41"});


    /* renamed from: a, reason: collision with root package name */
    public final ce f85a;

    ap(String str, String[] strArr) {
        this.f85a = new ce(str, Arrays.asList(strArr));
    }

    public final ce a() {
        return this.f85a;
    }
}
