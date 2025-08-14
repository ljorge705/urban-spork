package com.uxcam.internals;

import android.app.Activity;
import android.content.Context;
import com.uxcam.screenaction.di.ScreenActionModule;
import com.uxcam.screenaction.utils.Util;
import com.uxcam.screenshot.di.ScreenshotModule;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes6.dex */
public final class ez implements ey {
    public ez(hr uxConfigRepository) {
        Intrinsics.checkNotNullParameter(uxConfigRepository, "uxConfigRepository");
    }

    @Override // com.uxcam.internals.ey
    public final String a() {
        Context currentContext = Util.getCurrentContext();
        Intrinsics.checkNotNull(currentContext, "null cannot be cast to non-null type android.app.Activity");
        String simpleName = ((Activity) currentContext).getClass().getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "Util.getCurrentContext()…ity).javaClass.simpleName");
        return simpleName;
    }

    @Override // com.uxcam.internals.ey
    public final void a(String str) {
        if (bi.D == null) {
            bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
        }
        bi biVar = bi.D;
        Intrinsics.checkNotNull(biVar);
        if (biVar.l == null) {
            biVar.l = new fz(biVar.h());
        }
        fz fzVar = biVar.l;
        Intrinsics.checkNotNull(fzVar);
        fzVar.b(Util.getCurrentContext(), str);
    }
}
