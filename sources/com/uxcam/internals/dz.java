package com.uxcam.internals;

import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.LinkedList;

/* loaded from: classes6.dex */
public class dz extends an {
    public final LinkedList b;

    public dz(cy cyVar) {
        super(cyVar);
        this.b = new LinkedList();
        int i = ao.f84a;
    }

    @Override // com.uxcam.internals.an
    public void a(ByteBuffer byteBuffer) {
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            ((an) it.next()).b(byteBuffer);
        }
    }

    public final void b(StringBuilder sb) {
        StringBuilder sb2 = new StringBuilder();
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            ((an) it.next()).a(sb2);
            if (it.hasNext()) {
                sb2.append(",\n");
            }
        }
        sb.append(sb2.toString().replaceAll("([^\n]*)\n", "  $1\n"));
    }

    @Override // com.uxcam.internals.an
    public void a(StringBuilder sb) {
        super.a(sb);
        sb.append(": {\n");
        b(sb);
        sb.append("\n}");
    }
}
