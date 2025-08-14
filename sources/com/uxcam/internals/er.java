package com.uxcam.internals;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.WindowManager;
import com.uxcam.screenaction.models.ViewRootData;
import com.uxcam.screenaction.utils.ReflectionUtil;
import com.uxcam.screenshot.state.ScreenshotStateHolder;
import com.uxcam.screenshot.utils.ScreenShotBitmapUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public final class er implements eq {

    /* renamed from: a, reason: collision with root package name */
    public final ik f141a;
    public final ScreenshotStateHolder b;

    public er(im imVar, ScreenshotStateHolder screenshotStateHolder) {
        this.f141a = imVar;
        this.b = screenshotStateHolder;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v0, types: [android.app.Activity] */
    /* JADX WARN: Type inference failed for: r12v1, types: [android.app.Activity] */
    /* JADX WARN: Type inference failed for: r12v14 */
    /* JADX WARN: Type inference failed for: r12v16 */
    /* JADX WARN: Type inference failed for: r12v17 */
    /* JADX WARN: Type inference failed for: r12v3, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r12v5, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r12v6, types: [java.lang.Object] */
    @Override // com.uxcam.internals.eq
    public final ArrayList a(Activity activity, he heVar, int i) {
        Object fieldValueFlutter;
        Object fieldValueFlutter2;
        ArrayList arrayList = new ArrayList();
        try {
            if (this.b.isFlutter()) {
                ReflectionUtil.findFieldFlutter("mWindowManager", activity.getWindowManager().getClass());
                activity = ReflectionUtil.getFieldValueFlutter("mWindowManager", activity.getWindowManager());
            } else {
                ReflectionUtil.findField("mWindowManager", activity.getWindowManager().getClass());
                activity = ReflectionUtil.getFieldValue("mWindowManager", activity.getWindowManager());
            }
        } catch (Exception unused) {
            activity = !this.b.isFlutter() ? ReflectionUtil.getFieldValue("mGlobal", activity.getWindowManager()) : ReflectionUtil.getFieldValueFlutter("mGlobal", activity.getWindowManager());
        }
        if (this.b.isFlutter()) {
            fieldValueFlutter = ReflectionUtil.getFieldValueFlutter("mRoots", activity);
            fieldValueFlutter2 = ReflectionUtil.getFieldValueFlutter("mParams", activity);
        } else {
            fieldValueFlutter = ReflectionUtil.getFieldValue("mRoots", activity);
            fieldValueFlutter2 = ReflectionUtil.getFieldValue("mParams", activity);
        }
        Object[] array = ((List) fieldValueFlutter).toArray();
        List list = (List) fieldValueFlutter2;
        WindowManager.LayoutParams[] layoutParamsArr = (WindowManager.LayoutParams[]) list.toArray(new WindowManager.LayoutParams[list.size()]);
        for (int i2 = 0; i2 < array.length; i2++) {
            Object obj = array[i2];
            View view = !this.b.isFlutter() ? (View) ReflectionUtil.getFieldValue("mView", obj) : (View) ReflectionUtil.getFieldValueFlutter("mView", obj);
            int[] iArr = new int[2];
            if (view != null) {
                view.getLocationOnScreen(iArr);
                int i3 = iArr[0];
                Rect rect = new Rect(i3, iArr[1], view.getWidth() + i3, view.getHeight() + iArr[1]);
                if (view.isShown()) {
                    arrayList.add(new ViewRootData(view, rect, layoutParamsArr[i2]));
                    ScreenShotBitmapUtil.getInstance().correctlyCalculateBitmapPercentWidthHeight(rect, i);
                }
                if (!(view.getContext() instanceof Activity)) {
                    this.f141a.a(view, heVar);
                }
            }
        }
        return arrayList;
    }
}
