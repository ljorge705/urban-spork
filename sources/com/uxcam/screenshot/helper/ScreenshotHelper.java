package com.uxcam.screenshot.helper;

import android.app.Activity;
import com.uxcam.screenaction.models.ViewRootData;
import java.util.List;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001JQ\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0010\u0010\n\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\f\u0018\u00010\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H&¢\u0006\u0002\u0010\u0011¨\u0006\u0012"}, d2 = {"Lcom/uxcam/screenshot/helper/ScreenshotHelper;", "", "takeScreenshotAndEncode", "", "activityName", "", "pauseForAnotherApp", "", "screenVideoWidth", "", "viewRootDataList", "", "Lcom/uxcam/screenaction/models/ViewRootData;", "activity", "Landroid/app/Activity;", "callback", "Lcom/uxcam/screenshot/helper/OnScreenshotTakenCallback;", "(Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/util/List;Landroid/app/Activity;Lcom/uxcam/screenshot/helper/OnScreenshotTakenCallback;)V", "screenshot_littleRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes6.dex */
public interface ScreenshotHelper {
    void takeScreenshotAndEncode(String activityName, Boolean pauseForAnotherApp, Integer screenVideoWidth, List<ViewRootData> viewRootDataList, Activity activity, OnScreenshotTakenCallback callback);
}
