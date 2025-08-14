package com.uxcam.screenshot.viewocclusion;

import android.view.View;
import android.view.ViewGroup;
import com.uxcam.screenaction.models.ViewRootData;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\bf\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/uxcam/screenshot/viewocclusion/SensitiveViewsFinder;", "", "screenshot_littleRelease"}, k = 1, mv = {1, 8, 0})
/* loaded from: classes6.dex */
public interface SensitiveViewsFinder {
    KeyboardVisibilityCheckResult a(View view, int i);

    SensitiveViewsFinderResult a(View view, String str, List list, boolean z);

    SensitiveViewsFinderResult a(ViewGroup viewGroup, String str, List list, boolean z);

    ArrayList a(ViewRootData viewRootData, List list);
}
