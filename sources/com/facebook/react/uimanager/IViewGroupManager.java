package com.facebook.react.uimanager;

import android.view.View;
import com.facebook.react.bridge.UiThreadUtil;

/* loaded from: classes5.dex */
public interface IViewGroupManager<T extends View> extends IViewManagerWithChildren {
    void addView(T t, View view, int i);

    View getChildAt(T t, int i);

    int getChildCount(T t);

    void removeViewAt(T t, int i);

    default void removeAllViews(T t) {
        UiThreadUtil.assertOnUiThread();
        for (int childCount = getChildCount(t) - 1; childCount >= 0; childCount--) {
            removeViewAt(t, childCount);
        }
    }
}
