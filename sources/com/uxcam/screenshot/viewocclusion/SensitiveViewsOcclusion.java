package com.uxcam.screenshot.viewocclusion;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;
import com.uxcam.screenaction.models.UXCamOccludeView;
import java.util.List;

/* loaded from: classes6.dex */
public interface SensitiveViewsOcclusion {
    void a(View view, Canvas canvas, List<UXCamOccludeView> list, List<Rect> list2);
}
