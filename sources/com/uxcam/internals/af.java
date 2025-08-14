package com.uxcam.internals;

import androidx.core.view.ViewCompat;
import java.nio.ByteBuffer;
import java.util.HashSet;

/* loaded from: classes6.dex */
public final class af extends cm {
    public static final /* synthetic */ int c = 0;

    static {
        HashSet hashSet = new HashSet();
        hashSet.add(14);
        hashSet.add(15);
    }

    public af() {
        super(new cy(0L, "alis"));
    }

    @Override // com.uxcam.internals.an
    public final void a(ByteBuffer byteBuffer) {
        byteBuffer.putInt(this.b & ViewCompat.MEASURED_SIZE_MASK);
        if ((this.b & 1) != 0) {
            return;
        }
        de.a(null);
        throw null;
    }

    @Override // com.uxcam.internals.an
    public final void a(StringBuilder sb) {
        super.a(sb);
        sb.append(": ");
        if ((this.b & 1) != 0) {
            sb.append("'self'");
            return;
        }
        throw null;
    }
}
