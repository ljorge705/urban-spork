package com.uxcam.screenshot.viewocclusion;

import android.graphics.Canvas;
import android.graphics.Paint;
import androidx.core.internal.view.SupportMenu;
import com.uxcam.screenaction.models.OccludeComposable;
import com.uxcam.screenaction.utils.ComposeUtilsKt;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/uxcam/screenshot/viewocclusion/SensitiveComposableOcclusionImpl;", "Lcom/uxcam/screenshot/viewocclusion/SensitiveComposableOcclusion;", "<init>", "()V", "screenshot_littleRelease"}, k = 1, mv = {1, 8, 0})
/* loaded from: classes6.dex */
public final class SensitiveComposableOcclusionImpl implements SensitiveComposableOcclusion {
    @Override // com.uxcam.screenshot.viewocclusion.SensitiveComposableOcclusion
    public final void a(Canvas canvas, List composables) {
        Intrinsics.checkNotNullParameter(composables, "composables");
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        Iterator it = composables.iterator();
        while (it.hasNext()) {
            OccludeComposable occludeComposable = (OccludeComposable) it.next();
            if (occludeComposable.getView().get() != null && ComposeUtilsKt.isCompletelyVisible(occludeComposable.getLayoutCoordinates(), occludeComposable.getView().get())) {
                Paint paint = new Paint();
                paint.setColor(SupportMenu.CATEGORY_MASK);
                paint.setStrokeWidth(3.0f);
                canvas.drawRect(occludeComposable.getParentX() + occludeComposable.getX(), occludeComposable.getParentY() + occludeComposable.getY(), occludeComposable.getParentX() + occludeComposable.getRight(), occludeComposable.getParentY() + occludeComposable.getBottom(), paint);
            }
        }
    }
}
