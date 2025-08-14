package com.uxcam.internals;

import android.app.Activity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.uxcam.screenaction.di.ScreenActionModule;
import com.uxcam.screenshot.di.ScreenshotModule;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes6.dex */
public final class ck {
    public static FragmentManager a(Fragment fragment) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        try {
            return fragment.getChildFragmentManager();
        } catch (IllegalStateException e) {
            fj fjVarB = new fj().b("UXCamFragmentDataBuilder::getChildFragmentManager()");
            fjVarB.a("reason", e.getMessage());
            fjVarB.a(2);
            return null;
        }
    }

    public static void a(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (bi.D == null) {
            bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
        }
        bi biVar = bi.D;
        Intrinsics.checkNotNull(biVar);
        Boolean boolValueOf = Boolean.valueOf(((gd) biVar.g()).f170a);
        if (bi.D == null) {
            bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
        }
        bi biVar2 = bi.D;
        Intrinsics.checkNotNull(biVar2);
        Boolean boolValueOf2 = Boolean.valueOf(((fa) biVar2.d()).e);
        Intrinsics.checkNotNull(boolValueOf2);
        if (boolValueOf2.booleanValue()) {
            Intrinsics.checkNotNull(boolValueOf);
            if (boolValueOf.booleanValue()) {
                FragmentManager supportFragmentManager = ((FragmentActivity) activity).getSupportFragmentManager();
                if (bi.D == null) {
                    bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
                }
                bi biVar3 = bi.D;
                Intrinsics.checkNotNull(biVar3);
                if (biVar3.m == null) {
                    biVar3.m = new hi(biVar3.l());
                }
                hi hiVar = biVar3.m;
                Intrinsics.checkNotNull(hiVar);
                supportFragmentManager.registerFragmentLifecycleCallbacks(hiVar, true);
            }
        }
    }
}
