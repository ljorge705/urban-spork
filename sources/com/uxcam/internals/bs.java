package com.uxcam.internals;

import androidx.core.view.ViewCompat;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public final class bs extends cm {
    public final List<br> c;

    public bs(cy cyVar) {
        super(cyVar);
    }

    @Override // com.uxcam.internals.an
    public final void a(ByteBuffer byteBuffer) {
        byteBuffer.putInt(this.b & ViewCompat.MEASURED_SIZE_MASK);
        byteBuffer.putInt(this.c.size());
        for (br brVar : this.c) {
            byteBuffer.putInt((int) brVar.b);
            byteBuffer.putInt((int) brVar.c);
            byteBuffer.putInt((int) (brVar.f102a * 65536.0f));
        }
    }

    public bs() {
        this(new cy("elst"));
    }

    public bs(ArrayList arrayList) {
        this();
        this.c = arrayList;
    }

    @Override // com.uxcam.internals.an
    public final void a(StringBuilder sb) throws IllegalAccessException, SecurityException, IllegalArgumentException, InvocationTargetException {
        super.a(sb);
        sb.append(": ");
        gx.a(this, sb, "edits");
    }
}
