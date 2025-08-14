package com.uxcam.internals;

import androidx.core.view.ViewCompat;
import java.nio.ByteBuffer;

/* loaded from: classes6.dex */
public class ay extends cm {
    public final float c;
    public final float d;

    public ay(cy cyVar) {
        super(cyVar);
    }

    @Override // com.uxcam.internals.an
    public final void a(ByteBuffer byteBuffer) {
        byteBuffer.putInt(this.b & ViewCompat.MEASURED_SIZE_MASK);
        byteBuffer.putInt((int) (this.c * 65536.0f));
        byteBuffer.putInt((int) (this.d * 65536.0f));
    }

    public ay() {
        super(new cy("clef"));
    }

    public ay(int i, int i2) {
        this();
        this.c = i;
        this.d = i2;
    }

    public ay(cy cyVar, int i, int i2) {
        super(cyVar);
        this.c = i;
        this.d = i2;
    }
}
