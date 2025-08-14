package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.util;

import android.content.Context;
import androidx.window.core.layout.WindowHeightSizeClass;
import androidx.window.core.layout.WindowSizeClass;
import androidx.window.core.layout.WindowWidthSizeClass;
import androidx.window.layout.WindowMetrics;
import androidx.window.layout.WindowMetricsCalculator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0001H\u0000¨\u0006\u0007"}, d2 = {"getWindowSizeClass", "Landroidx/window/core/layout/WindowSizeClass;", "viewContext", "Landroid/content/Context;", "isTabletDisplay", "", "windowSizeClass", "onfido-capture-sdk-core_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class WindowHelperKt {
    public static final WindowSizeClass getWindowSizeClass(Context viewContext) {
        Intrinsics.checkNotNullParameter(viewContext, "viewContext");
        WindowMetrics windowMetricsComputeCurrentWindowMetrics = WindowMetricsCalculator.INSTANCE.getOrCreate().computeCurrentWindowMetrics(viewContext);
        int iWidth = windowMetricsComputeCurrentWindowMetrics.getBounds().width();
        int iHeight = windowMetricsComputeCurrentWindowMetrics.getBounds().height();
        float f = viewContext.getResources().getDisplayMetrics().density;
        return WindowSizeClass.INSTANCE.compute(iWidth / f, iHeight / f);
    }

    public static final boolean isTabletDisplay(WindowSizeClass windowSizeClass) {
        Intrinsics.checkNotNullParameter(windowSizeClass, "windowSizeClass");
        return Intrinsics.areEqual(windowSizeClass.getWindowWidthSizeClass(), WindowWidthSizeClass.MEDIUM) && Intrinsics.areEqual(windowSizeClass.getWindowHeightSizeClass(), WindowHeightSizeClass.EXPANDED);
    }
}
