package com.uxcam.internals;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

/* loaded from: classes6.dex */
public abstract class ab {

    /* renamed from: a, reason: collision with root package name */
    public final int f74a;
    public em d;
    public int e;
    public long f;
    public boolean k;
    public ArrayList m;
    public final ArrayList g = new ArrayList();
    public final ArrayList h = new ArrayList();
    public int i = -1;
    public int j = 0;
    public final ArrayList l = new ArrayList();
    public final int b = 1;
    public final int c = 1;

    public ab(int i) {
        this.f74a = i;
    }

    public abstract ha a(dw dwVar);

    public final void a(em emVar) {
        this.d = emVar;
        this.e = 1;
    }

    public abstract long b();

    public final gf a() {
        short s;
        em emVar;
        int i = 0;
        if (this.l.get(0) instanceof ij) {
            ij ijVar = (ij) this.l.get(0);
            LinkedList linkedList = new LinkedList();
            LinkedList linkedList2 = new LinkedList();
            linkedList2.add(new String[]{"pasp"}[0]);
            an.a(ijVar, linkedList2, linkedList);
            Object[] array = linkedList.toArray((Object[]) Array.newInstance((Class<?>) ee.class, 0));
            if (((ee) (array.length > 0 ? array[0] : null)) != null) {
                emVar = new em(0, 0);
            } else {
                emVar = new em(1, 1);
            }
            i = (emVar.f138a * ijVar.i) / emVar.b;
            s = ijVar.j;
        } else {
            s = 0;
        }
        return new gf(i, s);
    }
}
