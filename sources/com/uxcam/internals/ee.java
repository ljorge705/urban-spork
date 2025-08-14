package com.uxcam.internals;

import java.nio.ByteBuffer;

/* loaded from: classes6.dex */
public final class ee extends an {
    public ee() {
        super(new cy("pasp"));
    }

    @Override // com.uxcam.internals.an
    public final void a(ByteBuffer byteBuffer) {
        byteBuffer.putInt(0);
        byteBuffer.putInt(0);
    }

    @Override // com.uxcam.internals.an
    public final void a(StringBuilder sb) {
        super.a(sb);
        sb.append(": 0:0");
    }
}
