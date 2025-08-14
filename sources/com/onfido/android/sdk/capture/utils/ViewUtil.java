package com.onfido.android.sdk.capture.utils;

import android.animation.LayoutTransition;
import android.view.View;
import android.view.ViewGroup;

/* loaded from: classes2.dex */
public class ViewUtil {
    public static void setViewVisibility(View view, boolean z) {
        view.setVisibility(z ? 0 : 4);
    }

    public static void setViewVisibilityWithoutAnimation(View view, boolean z) {
        LayoutTransition layoutTransition = ((ViewGroup) view.getParent()).getLayoutTransition();
        layoutTransition.disableTransitionType(z ? 2 : 3);
        view.setVisibility(z ? 0 : 4);
        layoutTransition.enableTransitionType(z ? 2 : 3);
    }
}
