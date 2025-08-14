package com.uxcam.internals;

import java.io.File;

/* loaded from: classes6.dex */
public final class fs {

    /* renamed from: a, reason: collision with root package name */
    public final File f161a;
    public final hd b;
    public String c;

    public fs(File file, hd hdVar) {
        this.f161a = new File(file, "data.gz.aes");
        this.b = hdVar;
    }

    public final File a() {
        return this.f161a;
    }
}
