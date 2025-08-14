package com.uxcam.internals;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.uxcam.screenaction.di.ScreenActionModule;
import com.uxcam.screenshot.di.ScreenshotModule;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes6.dex */
public final class hi extends FragmentManager.FragmentLifecycleCallbacks {

    /* renamed from: a, reason: collision with root package name */
    public final hr f192a;

    public hi(hr uxConfigRepository) {
        Intrinsics.checkNotNullParameter(uxConfigRepository, "uxConfigRepository");
        this.f192a = uxConfigRepository;
    }

    @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
    public final void onFragmentPaused(FragmentManager fm, Fragment f) {
        Intrinsics.checkNotNullParameter(fm, "fm");
        Intrinsics.checkNotNullParameter(f, "f");
        super.onFragmentPaused(fm, f);
        if (bi.D == null) {
            bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
        }
        bi biVar = bi.D;
        Intrinsics.checkNotNull(biVar);
        gc gcVarG = biVar.g();
        Intrinsics.checkNotNull(gcVarG);
        if (gcVarG.a() && this.f192a.a().c) {
            if (bi.D == null) {
                bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
            }
            bi biVar2 = bi.D;
            Intrinsics.checkNotNull(biVar2);
            ex exVarD = biVar2.d();
            Intrinsics.checkNotNull(exVarD);
            exVarD.b(fm, f);
        }
    }

    @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
    public final void onFragmentResumed(FragmentManager fm, Fragment f) {
        Intrinsics.checkNotNullParameter(fm, "fm");
        Intrinsics.checkNotNullParameter(f, "f");
        super.onFragmentResumed(fm, f);
        if (bi.D == null) {
            bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
        }
        bi biVar = bi.D;
        Intrinsics.checkNotNull(biVar);
        gc gcVarG = biVar.g();
        Intrinsics.checkNotNull(gcVarG);
        if (gcVarG.a() && this.f192a.a().c) {
            if (bi.D == null) {
                bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
            }
            bi biVar2 = bi.D;
            Intrinsics.checkNotNull(biVar2);
            ex exVarD = biVar2.d();
            Intrinsics.checkNotNull(exVarD);
            exVarD.a(fm, f);
        }
    }
}
