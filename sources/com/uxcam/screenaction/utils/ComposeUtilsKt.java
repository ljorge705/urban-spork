package com.uxcam.screenaction.utils;

import android.graphics.Rect;
import android.view.View;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.RectKt;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import com.uxcam.screenaction.models.GestureData;
import com.uxcam.screenaction.models.OccludeComposable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u001a\u001c\u0010\u0006\u001a\u00020\u00012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000b¨\u0006\f"}, d2 = {"isCompletelyVisible", "", "layoutCoordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "view", "Landroid/view/View;", "isGestureInOccludedComposable", "occludedComposables", "", "Lcom/uxcam/screenaction/models/OccludeComposable;", "gestureData", "Lcom/uxcam/screenaction/models/GestureData;", "screenaction_littleRelease"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class ComposeUtilsKt {
    public static final boolean isCompletelyVisible(LayoutCoordinates layoutCoordinates, View view) {
        Intrinsics.checkNotNullParameter(layoutCoordinates, "layoutCoordinates");
        if (!layoutCoordinates.isAttached()) {
            return false;
        }
        Rect rect = new Rect();
        Intrinsics.checkNotNull(view);
        if (!view.getGlobalVisibleRect(rect)) {
            return false;
        }
        androidx.compose.ui.geometry.Rect rectBoundsInWindow = LayoutCoordinatesKt.boundsInWindow(layoutCoordinates);
        return rectBoundsInWindow.getTop() >= ((float) rect.top) && rectBoundsInWindow.getLeft() >= ((float) rect.left) && rectBoundsInWindow.getRight() <= ((float) rect.right) && rectBoundsInWindow.getBottom() <= ((float) rect.bottom);
    }

    public static final boolean isGestureInOccludedComposable(List<OccludeComposable> occludedComposables, GestureData gestureData) {
        Object next;
        Intrinsics.checkNotNullParameter(occludedComposables, "occludedComposables");
        Intrinsics.checkNotNullParameter(gestureData, "gestureData");
        ArrayList arrayList = new ArrayList();
        for (Object obj : occludedComposables) {
            OccludeComposable occludeComposable = (OccludeComposable) obj;
            if (isCompletelyVisible(occludeComposable.getLayoutCoordinates(), occludeComposable.getView().get())) {
                arrayList.add(obj);
            }
        }
        Iterator it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            OccludeComposable occludeComposable2 = (OccludeComposable) next;
            if (RectKt.m1679Recttz77jQw(OffsetKt.Offset(occludeComposable2.getX(), occludeComposable2.getY()), SizeKt.Size(occludeComposable2.getRight() - occludeComposable2.getX(), occludeComposable2.getBottom() - occludeComposable2.getTop())).m1665containsk4lQ0M(OffsetKt.Offset(gestureData.getX(), gestureData.getY()))) {
                break;
            }
        }
        return ((OccludeComposable) next) != null;
    }
}
