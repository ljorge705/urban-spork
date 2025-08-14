package com.uxcam.internals;

import com.drew.metadata.heif.HeifBoxTypes;
import java.nio.ByteBuffer;

/* loaded from: classes6.dex */
public final class bc extends an {
    public bc() {
        super(new cy(HeifBoxTypes.BOX_COLOUR_INFO));
    }

    @Override // com.uxcam.internals.an
    public final void a(ByteBuffer byteBuffer) {
        byteBuffer.put(de.a("nclc"));
        byteBuffer.putShort((short) 0);
        byteBuffer.putShort((short) 0);
        byteBuffer.putShort((short) 0);
    }
}
