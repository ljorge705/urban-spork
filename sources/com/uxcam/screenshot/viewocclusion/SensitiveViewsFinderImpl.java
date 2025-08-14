package com.uxcam.screenshot.viewocclusion;

import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import com.uxcam.screenaction.models.UXCamOccludeView;
import com.uxcam.screenaction.models.ViewRootData;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/uxcam/screenshot/viewocclusion/SensitiveViewsFinderImpl;", "Lcom/uxcam/screenshot/viewocclusion/SensitiveViewsFinder;", "<init>", "()V", "screenshot_littleRelease"}, k = 1, mv = {1, 8, 0})
/* loaded from: classes6.dex */
public final class SensitiveViewsFinderImpl implements SensitiveViewsFinder {
    @Override // com.uxcam.screenshot.viewocclusion.SensitiveViewsFinder
    public final ArrayList a(ViewRootData config, List viewsToHide) {
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(viewsToHide, "viewsToHide");
        ArrayList arrayList = new ArrayList();
        Iterator it = viewsToHide.iterator();
        while (it.hasNext()) {
            View view = ((UXCamOccludeView) it.next()).getView().get();
            if (config.getView() != null && view != null && view.getVisibility() == 0 && view.isShown()) {
                int[] iArr = new int[2];
                view.getLocationOnScreen(iArr);
                int[] iArr2 = new int[2];
                View view2 = config.getView();
                if (view2 != null) {
                    view2.getLocationOnScreen(iArr2);
                }
                float f = iArr[0] - iArr2[0];
                float f2 = iArr[1] - iArr2[1];
                arrayList.add(new RectF(f, f2, view.getWidth() + f, view.getHeight() + f2));
            }
        }
        return arrayList;
    }

    @Override // com.uxcam.screenshot.viewocclusion.SensitiveViewsFinder
    public final SensitiveViewsFinderResult a(ViewGroup parent, String str, List currentSensitiveViewsToHide, boolean z) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        Intrinsics.checkNotNullParameter(currentSensitiveViewsToHide, "currentSensitiveViewsToHide");
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        Iterator it = currentSensitiveViewsToHide.iterator();
        while (it.hasNext()) {
            UXCamOccludeView uXCamOccludeView = (UXCamOccludeView) it.next();
            if (uXCamOccludeView.getView().get() == null) {
                arrayList3.add(uXCamOccludeView);
            }
        }
        arrayList2.addAll(arrayList3);
        int childCount = parent.getChildCount();
        WeakReference<WebView> weakReference = null;
        for (int i = 0; i < childCount; i++) {
            View childAt = parent.getChildAt(i);
            if (childAt instanceof ViewGroup) {
                if (childAt instanceof WebView) {
                    weakReference = new WeakReference<>(childAt);
                }
                SensitiveViewsFinderResult sensitiveViewsFinderResultA = a((ViewGroup) childAt, str, currentSensitiveViewsToHide, z);
                List<UXCamOccludeView> list = sensitiveViewsFinderResultA.f288a;
                Intrinsics.checkNotNullExpressionValue(list, "result.sensitiveViewsToHide");
                arrayList.addAll(list);
                List<UXCamOccludeView> list2 = sensitiveViewsFinderResultA.b;
                Intrinsics.checkNotNullExpressionValue(list2, "result.sensitiveViewsToRemove");
                arrayList2.addAll(list2);
                if (weakReference == null) {
                    weakReference = sensitiveViewsFinderResultA.c;
                }
            } else if (childAt != null && (childAt instanceof TextView)) {
                Iterator it2 = currentSensitiveViewsToHide.iterator();
                while (true) {
                    if (it2.hasNext()) {
                        if (Intrinsics.areEqual(((UXCamOccludeView) it2.next()).getView().get(), childAt)) {
                            break;
                        }
                    } else {
                        TextView textView = (TextView) childAt;
                        int inputType = textView.getInputType();
                        boolean z2 = (textView instanceof EditText) && z;
                        if (inputType == 128 || inputType == 129 || z2) {
                            UXCamOccludeView uXCamOccludeView2 = new UXCamOccludeView(false);
                            uXCamOccludeView2.setView(new WeakReference<>(childAt));
                            uXCamOccludeView2.setStopTrackingGestures(true);
                            uXCamOccludeView2.setActivityName(str);
                            arrayList.add(uXCamOccludeView2);
                        }
                    }
                }
            }
        }
        return new SensitiveViewsFinderResult(arrayList, arrayList2, weakReference);
    }

    @Override // com.uxcam.screenshot.viewocclusion.SensitiveViewsFinder
    public final SensitiveViewsFinderResult a(View view, String str, List currentSensitiveViewsToHide, boolean z) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(currentSensitiveViewsToHide, "currentSensitiveViewsToHide");
        ArrayList arrayList = new ArrayList();
        if (!(view instanceof TextView)) {
            return new SensitiveViewsFinderResult(arrayList, CollectionsKt.emptyList(), null);
        }
        Iterator it = currentSensitiveViewsToHide.iterator();
        while (true) {
            if (it.hasNext()) {
                if (Intrinsics.areEqual(((UXCamOccludeView) it.next()).getView().get(), view)) {
                    break;
                }
            } else {
                TextView textView = (TextView) view;
                int inputType = textView.getInputType();
                boolean z2 = (textView instanceof EditText) && z;
                if (inputType == 128 || inputType == 129 || z2) {
                    UXCamOccludeView uXCamOccludeView = new UXCamOccludeView(false);
                    uXCamOccludeView.setView(new WeakReference<>(view));
                    uXCamOccludeView.setStopTrackingGestures(true);
                    uXCamOccludeView.setActivityName(str);
                    arrayList.add(uXCamOccludeView);
                }
            }
        }
        return new SensitiveViewsFinderResult(arrayList, CollectionsKt.emptyList(), null);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0020  */
    @Override // com.uxcam.screenshot.viewocclusion.SensitiveViewsFinder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.uxcam.screenshot.viewocclusion.KeyboardVisibilityCheckResult a(android.view.View r3, int r4) {
        /*
            r2 = this;
            java.lang.String r0 = "decorView"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            android.graphics.Rect r0 = new android.graphics.Rect
            r0.<init>()
            r3.getWindowVisibleDisplayFrame(r0)
            int r3 = r0.height()
            if (r4 == 0) goto L20
            int r1 = r3 + 150
            if (r4 <= r1) goto L1a
            int r4 = r0.bottom
            goto L21
        L1a:
            int r4 = r4 + 150
            if (r4 >= r3) goto L20
            r4 = 0
            goto L21
        L20:
            r4 = -1
        L21:
            com.uxcam.screenshot.viewocclusion.KeyboardVisibilityCheckResult r0 = new com.uxcam.screenshot.viewocclusion.KeyboardVisibilityCheckResult
            r0.<init>(r4, r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uxcam.screenshot.viewocclusion.SensitiveViewsFinderImpl.a(android.view.View, int):com.uxcam.screenshot.viewocclusion.KeyboardVisibilityCheckResult");
    }
}
