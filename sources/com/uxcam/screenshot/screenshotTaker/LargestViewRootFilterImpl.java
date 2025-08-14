package com.uxcam.screenshot.screenshotTaker;

import android.graphics.Rect;
import com.uxcam.screenaction.models.ViewRootData;
import java.util.List;

/* loaded from: classes6.dex */
public class LargestViewRootFilterImpl implements LargestViewRootFilter {
    @Override // com.uxcam.screenshot.screenshotTaker.LargestViewRootFilter
    public final void a(List<ViewRootData> list) {
        Rect rect = new Rect();
        ViewRootData viewRootDataDuplicate = null;
        for (ViewRootData viewRootData : list) {
            Rect winFrame = viewRootData.getWinFrame();
            if (rect.width() < winFrame.width() && rect.height() < winFrame.height()) {
                rect = new Rect(winFrame);
                viewRootDataDuplicate = viewRootData.duplicate();
            }
        }
        if (viewRootDataDuplicate != null) {
            list.clear();
            list.add(viewRootDataDuplicate);
        }
    }
}
