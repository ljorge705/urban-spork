package com.uxcam.internals;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.uxcam.screenaction.di.ScreenActionModule;
import com.uxcam.screenshot.di.ScreenshotModule;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes6.dex */
public final class hh {
    public static hf a(List list, Fragment fragment, Map map) {
        hf hfVar = new hf();
        Intrinsics.checkNotNull(map);
        hfVar.b = (String) map.get(fragment);
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Fragment fragment2 = (Fragment) it.next();
            if (bi.D == null) {
                bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
            }
            bi biVar = bi.D;
            Intrinsics.checkNotNull(biVar);
            biVar.a().getClass();
            FragmentManager fragmentManagerA = ck.a(fragment2);
            if (fragmentManagerA != null) {
                List<Fragment> fragments = fragmentManagerA.getFragments();
                Intrinsics.checkNotNullExpressionValue(fragments, "childFragmentManager.fragments");
                arrayList.add(a(fragments, fragment2, map));
            }
        }
        hfVar.f190a = arrayList;
        return hfVar;
    }
}
