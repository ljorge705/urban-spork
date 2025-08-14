package com.uxcam.internals;

import java.util.HashMap;

/* loaded from: classes6.dex */
public final class be {

    /* renamed from: a, reason: collision with root package name */
    public static final HashMap f95a;

    public static class aa implements hb {
        @Override // com.uxcam.internals.hb
        public final void a(ed edVar, ed edVar2) {
            for (int i = 0; i < 3; i++) {
                System.arraycopy(edVar.d[i], 0, edVar2.d[i], 0, Math.min(edVar.a(i) * edVar.b(i), edVar2.a(i) * edVar2.b(i)));
            }
        }
    }

    static {
        HashMap map = new HashMap();
        f95a = map;
        HashMap map2 = new HashMap();
        bd bdVar = bd.RGB;
        map2.put(bdVar, new aa());
        bd bdVar2 = bd.YUV420;
        map2.put(bdVar2, new eo());
        bd bdVar3 = bd.YUV420J;
        map2.put(bdVar3, new en());
        bd bdVar4 = bd.YUV422;
        map2.put(bdVar4, new ep(0));
        bd bdVar5 = bd.YUV422_10;
        map2.put(bdVar5, new ep(2));
        map.put(bdVar, map2);
        HashMap map3 = new HashMap();
        map3.put(bdVar2, new aa());
        map3.put(bdVar, new ip());
        map3.put(bdVar4, new iq(0));
        map3.put(bdVar5, new iq(2));
        map.put(bdVar2, map3);
        HashMap map4 = new HashMap();
        map4.put(bdVar4, new aa());
        map4.put(bdVar, new iu(0));
        map4.put(bdVar2, new iw(0));
        map4.put(bdVar3, new iv(0));
        map.put(bdVar4, map4);
        HashMap map5 = new HashMap();
        map5.put(bdVar5, new aa());
        map5.put(bdVar, new iu(2));
        map5.put(bdVar2, new iw(2));
        map5.put(bdVar3, new iv(2));
        map.put(bdVar5, map5);
        HashMap map6 = new HashMap();
        bd bdVar6 = bd.YUV444;
        map6.put(bdVar6, new aa());
        map6.put(bdVar, new iz(0));
        map6.put(bdVar2, new ja(0));
        map.put(bdVar6, map6);
        HashMap map7 = new HashMap();
        bd bdVar7 = bd.YUV444_10;
        map7.put(bdVar7, new aa());
        map7.put(bdVar, new iz(2));
        map7.put(bdVar2, new ja(2));
        map.put(bdVar7, map7);
        HashMap map8 = new HashMap();
        map8.put(bdVar3, new aa());
        map8.put(bdVar, new in());
        map8.put(bdVar2, new io());
        map.put(bdVar3, map8);
        HashMap map9 = new HashMap();
        bd bdVar8 = bd.YUV422J;
        map9.put(bdVar8, new aa());
        map9.put(bdVar, new ir());
        map9.put(bdVar2, new is());
        map9.put(bdVar3, new iw(0));
        map.put(bdVar8, map9);
        HashMap map10 = new HashMap();
        bd bdVar9 = bd.YUV444J;
        map10.put(bdVar9, new aa());
        map10.put(bdVar, new ix());
        map10.put(bdVar2, new iy());
        map10.put(bdVar3, new ja(0));
        map.put(bdVar9, map10);
    }
}
