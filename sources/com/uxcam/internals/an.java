package com.uxcam.internals;

import java.nio.ByteBuffer;
import java.util.LinkedList;

/* loaded from: classes6.dex */
public abstract class an {

    /* renamed from: a, reason: collision with root package name */
    public final cy f83a;

    public an(cy cyVar) {
        this.f83a = cyVar;
    }

    public static void a(an anVar, LinkedList linkedList, LinkedList linkedList2) {
        if (linkedList.size() <= 0) {
            linkedList2.add(anVar);
            return;
        }
        String str = (String) linkedList.remove(0);
        if (anVar instanceof dz) {
            for (an anVar2 : ((dz) anVar).b) {
                if (str == null || str.equals(anVar2.f83a.f115a)) {
                    a(anVar2, linkedList, linkedList2);
                }
            }
        }
        linkedList.add(0, str);
    }

    public abstract void a(ByteBuffer byteBuffer);

    public final void b(ByteBuffer byteBuffer) {
        ByteBuffer byteBufferDuplicate = byteBuffer.duplicate();
        byteBuffer.position(byteBuffer.position() + Math.min(byteBuffer.remaining(), 8));
        a(byteBuffer);
        cy cyVar = this.f83a;
        cyVar.b = (cyVar.b > 4294967296L ? 16L : 8L) + ((byteBuffer.position() - byteBufferDuplicate.position()) - 8);
        cy cyVar2 = this.f83a;
        if ((cyVar2.b <= 4294967296L ? 8L : 16L) != 8) {
            throw new AssertionError();
        }
        cyVar2.a(byteBufferDuplicate);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        a(sb);
        return sb.toString();
    }

    public void a(StringBuilder sb) {
        sb.append("'" + this.f83a.f115a + "'");
    }
}
