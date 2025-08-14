package com.uxcam.internals;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.uxcam.screenaction.di.ScreenActionModule;
import com.uxcam.screenshot.di.ScreenshotModule;
import java.util.HashMap;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes6.dex */
public final class cj implements ci {
    public static boolean e(FragmentManager fragmentManager) {
        for (Fragment fragment : fragmentManager.getFragments()) {
            if (!fragment.isInLayout()) {
                return false;
            }
            if (bi.D == null) {
                bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
            }
            bi biVar = bi.D;
            Intrinsics.checkNotNull(biVar);
            ck ckVarA = biVar.a();
            Intrinsics.checkNotNullExpressionValue(fragment, "fragment");
            ckVarA.getClass();
            FragmentManager fragmentManagerA = ck.a(fragment);
            if (fragmentManagerA != null && !e(fragmentManagerA)) {
                return false;
            }
        }
        return true;
    }

    public static HashMap f(FragmentManager fragmentManager) {
        HashMap map = new HashMap();
        if (fragmentManager == null) {
            return map;
        }
        for (Fragment fragment : fragmentManager.getFragments()) {
            map.put(fragment, fragment.getClass().getSimpleName());
            Intrinsics.checkNotNullExpressionValue(fragment.getClass().getSimpleName(), "fragment.javaClass.simpleName");
            try {
                if (bi.D == null) {
                    bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
                }
                bi biVar = bi.D;
                Intrinsics.checkNotNull(biVar);
                ck ckVarA = biVar.a();
                Intrinsics.checkNotNullExpressionValue(fragment, "fragment");
                ckVarA.getClass();
                if (ck.a(fragment) != null) {
                    Intrinsics.checkNotNullExpressionValue(fragment.getChildFragmentManager().getFragments(), "fragment.childFragmentManager.fragments");
                    if (!r2.isEmpty()) {
                        map.putAll(f(fragment.getChildFragmentManager()));
                    }
                }
            } catch (Exception unused) {
            }
        }
        return map;
    }

    public static int g(FragmentManager fragmentManager) {
        int iG = 0;
        for (Fragment fragment : fragmentManager.getFragments()) {
            if (fragment.isResumed()) {
                iG++;
            }
            if (bi.D == null) {
                bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
            }
            bi biVar = bi.D;
            Intrinsics.checkNotNull(biVar);
            ck ckVarA = biVar.a();
            Intrinsics.checkNotNullExpressionValue(fragment, "fragment");
            ckVarA.getClass();
            FragmentManager fragmentManagerA = ck.a(fragment);
            if (fragmentManagerA != null) {
                iG += g(fragmentManagerA);
            }
        }
        return iG;
    }

    @Override // com.uxcam.internals.ci
    public final hg a(hf hfVar, String str) {
        hg hgVar = new hg();
        hgVar.f191a = str;
        hgVar.b = hfVar;
        return hgVar;
    }

    @Override // com.uxcam.internals.ci
    public final boolean b(FragmentManager fragmentManager) {
        if (fragmentManager == null) {
            return false;
        }
        return e(fragmentManager) || g(fragmentManager) > 1;
    }

    @Override // com.uxcam.internals.ci
    public final HashMap c(FragmentManager fragmentManager) {
        return f(fragmentManager);
    }

    @Override // com.uxcam.internals.ci
    public final Fragment d(FragmentManager fragmentManager) {
        Intrinsics.checkNotNull(fragmentManager);
        Fragment fragmentD = null;
        for (Fragment fragment : fragmentManager.getFragments()) {
            if (fragment.isVisible()) {
                fragmentD = fragment;
            }
            if (bi.D == null) {
                bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
            }
            bi biVar = bi.D;
            Intrinsics.checkNotNull(biVar);
            ck ckVarA = biVar.a();
            Intrinsics.checkNotNullExpressionValue(fragment, "fragment");
            ckVarA.getClass();
            if (ck.a(fragment) != null) {
                Intrinsics.checkNotNullExpressionValue(fragment.getChildFragmentManager().getFragments(), "fragment.childFragmentManager.fragments");
                if (!r2.isEmpty()) {
                    fragmentD = d(fragment.getChildFragmentManager());
                }
            }
        }
        return fragmentD;
    }

    @Override // com.uxcam.internals.ci
    public final boolean a(FragmentManager fragmentManager) {
        Intrinsics.checkNotNull(fragmentManager);
        return e(fragmentManager);
    }
}
