package com.uxcam.internals;

import androidx.core.view.ViewCompat;
import com.drew.metadata.mov.QuickTimeAtomTypes;
import java.nio.ByteBuffer;

/* loaded from: classes6.dex */
public final class gn extends cm {
    public final short c;
    public final short d;
    public final short e;
    public final short[] f;
    public final short[] g;
    public final String h;

    public gn(short[] sArr, short[] sArr2) {
        this(new cy(QuickTimeAtomTypes.ATOM_TIMECODE_MEDIA_INFO));
        this.c = (short) 0;
        this.d = (short) 0;
        this.e = (short) 12;
        this.f = sArr;
        this.g = sArr2;
        this.h = "Lucida Grande";
    }

    @Override // com.uxcam.internals.an
    public final void a(ByteBuffer byteBuffer) {
        byteBuffer.putInt(this.b & ViewCompat.MEASURED_SIZE_MASK);
        byteBuffer.putShort(this.c);
        byteBuffer.putShort(this.d);
        byteBuffer.putShort(this.e);
        byteBuffer.putShort((short) 0);
        byteBuffer.putShort(this.f[0]);
        byteBuffer.putShort(this.f[1]);
        byteBuffer.putShort(this.f[2]);
        byteBuffer.putShort(this.g[0]);
        byteBuffer.putShort(this.g[1]);
        byteBuffer.putShort(this.g[2]);
        String str = this.h;
        byteBuffer.put((byte) str.length());
        byteBuffer.put(de.a(str));
    }

    public gn(cy cyVar) {
        super(cyVar);
        this.f = new short[3];
        this.g = new short[3];
    }
}
