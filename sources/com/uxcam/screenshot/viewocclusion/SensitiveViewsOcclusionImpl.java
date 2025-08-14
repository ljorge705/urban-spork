package com.uxcam.screenshot.viewocclusion;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import androidx.core.internal.view.SupportMenu;
import com.uxcam.screenaction.models.UXCamOccludeView;
import java.util.List;

/* loaded from: classes6.dex */
public class SensitiveViewsOcclusionImpl implements SensitiveViewsOcclusion {

    /* renamed from: a, reason: collision with root package name */
    public final Paint f289a;

    public SensitiveViewsOcclusionImpl(Paint paint) {
        this.f289a = paint;
    }

    @Override // com.uxcam.screenshot.viewocclusion.SensitiveViewsOcclusion
    public final void a(View view, Canvas canvas, List<UXCamOccludeView> list, List<Rect> list2) {
        int[] iArr;
        if (!list.isEmpty()) {
            for (UXCamOccludeView uXCamOccludeView : list) {
                if (uXCamOccludeView.getView().get() != null && uXCamOccludeView.getView().get().isShown() && uXCamOccludeView.getView().get().getVisibility() == 0) {
                    View view2 = uXCamOccludeView.getView().get();
                    try {
                        iArr = new int[2];
                        view2.getLocationOnScreen(iArr);
                        this.f289a.setColor(SupportMenu.CATEGORY_MASK);
                        this.f289a.setStrokeWidth(3.0f);
                    } catch (Exception e) {
                        e = e;
                    }
                    if (view.getWindowToken() == null || view.getWindowToken().equals(view2.getWindowToken())) {
                        int[] iArr2 = new int[2];
                        try {
                            view.getLocationOnScreen(iArr2);
                            float f = iArr[0] - iArr2[0];
                            float f2 = iArr[1] - iArr2[1];
                            canvas.drawRect(f, f2, f + view2.getWidth(), view2.getHeight() + f2, this.f289a);
                        } catch (Exception e2) {
                            e = e2;
                            canvas.drawColor(-7829368);
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        if (list2.isEmpty()) {
            return;
        }
        for (Rect rect : list2) {
            Paint paint = new Paint();
            paint.setColor(SupportMenu.CATEGORY_MASK);
            paint.setStrokeWidth(3.0f);
            canvas.drawRect(rect, paint);
        }
    }
}
