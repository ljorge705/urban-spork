package com.uxcam.internals;

import androidx.core.view.ViewCompat;
import java.nio.ByteBuffer;

/* loaded from: classes6.dex */
public final class cx extends cm {
    public final String c;
    public final String d;
    public final String e;
    public final int f;
    public final int g;
    public final String h;

    public cx(String str, String str2) {
        super(new cy("hdlr"));
        this.c = str;
        this.d = str2;
        this.e = "appl";
        this.f = 0;
        this.g = 0;
        this.h = "";
    }

    @Override // com.uxcam.internals.an
    public final void a(ByteBuffer byteBuffer) {
        byteBuffer.putInt(this.b & ViewCompat.MEASURED_SIZE_MASK);
        byteBuffer.put(de.a(this.c));
        byteBuffer.put(de.a(this.d));
        byteBuffer.put(de.a(this.e));
        byteBuffer.putInt(this.f);
        byteBuffer.putInt(this.g);
        String str = this.h;
        if (str != null) {
            byteBuffer.put(de.a(str));
        }
    }

    public cx() {
        super(new cy("hdlr"));
    }
}
