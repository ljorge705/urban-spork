package com.uxcam.internals;

import android.os.Looper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes6.dex */
public final class dd<E> extends ArrayList<ew> {

    /* renamed from: a, reason: collision with root package name */
    public static final ExecutorService f119a = Executors.newSingleThreadExecutor();

    public static void b(ew ewVar) {
        if (ff.a() == null) {
            HashMap map = new HashMap();
            String strReplace = "[ #event# ]".replace("#event#", "Exception");
            map.put("site_of_error", "JCodeArray::processImages()");
            map.put("reason", "ScreenVideoHandler is null, cannot process bitmap");
            ht.a(strReplace, (Map<String, String>) map);
            return;
        }
        bt btVar = new bt(ff.h);
        int i = ewVar.f143a;
        if (bt.c) {
            gk.c.getClass();
            try {
                btVar.b = ewVar.b;
                btVar.a(i);
                dd<ew> ddVar = ff.f;
                if (ddVar != null) {
                    ddVar.remove(ewVar);
                }
                ff ffVarA = ff.a();
                if (ffVarA != null) {
                    ffVarA.a(btVar.f103a);
                    return;
                }
                HashMap map2 = new HashMap();
                String strReplace2 = "[ #event# ]".replace("#event#", "Exception");
                map2.put("site_of_error", "EncodeVideo::processScreenBitmap()");
                map2.put("reason", "ScreenVideoHandler is null, cannot finish encoding.");
                ht.a(strReplace2, (Map<String, String>) map2);
            } catch (Exception e) {
                gk.a("bt").getClass();
                fj fjVarB = new fj().b("EncodeVideo::processScreenBitmap()");
                fjVarB.a("reason", e.getMessage());
                fjVarB.a(2);
            }
        }
    }

    public static void c(final ew ewVar) {
        Runnable runnable = new Runnable() { // from class: com.uxcam.internals.dd$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                dd.b(ewVar);
            }
        };
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            f119a.submit(runnable);
        } else {
            runnable.run();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void a(ew ewVar) {
        c(ewVar);
        super.add(ewVar);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object obj) {
        ew ewVar = (ew) obj;
        c(ewVar);
        return super.add(ewVar);
    }
}
