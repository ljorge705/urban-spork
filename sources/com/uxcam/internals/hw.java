package com.uxcam.internals;

import androidx.core.view.ViewCompat;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/* loaded from: classes6.dex */
public final class hw extends cm {
    @Override // com.uxcam.internals.an
    public final void a(ByteBuffer byteBuffer) {
        byteBuffer.putInt(this.b & ViewCompat.MEASURED_SIZE_MASK);
        Charset charset = StandardCharsets.UTF_8;
    }
}
