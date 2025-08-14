package com.clevertap.android.sdk.inapp;

import android.widget.RelativeLayout;

/* loaded from: classes5.dex */
public class CTInAppHtmlCoverFragment extends CTInAppBaseFullHtmlFragment {
    @Override // com.clevertap.android.sdk.inapp.CTInAppBaseFullHtmlFragment
    protected RelativeLayout.LayoutParams getLayoutParamsForCloseButton() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(11, this.webView.getId());
        layoutParams.addRule(10, this.webView.getId());
        int scaledPixels = getScaledPixels(40) / 4;
        layoutParams.setMargins(0, scaledPixels, scaledPixels, 0);
        return layoutParams;
    }
}
