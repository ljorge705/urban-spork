package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.onfido.android.sdk.capture.R;

/* loaded from: classes2.dex */
public final class OnfidoHostedWebModuleBottomSheetLayoutBinding implements ViewBinding {
    public final ImageButton buttonClose;
    public final WebView onfidoBottomSheetWebView;
    public final CircularProgressIndicator progressCircular;
    private final LinearLayout rootView;
    public final ConstraintLayout topLayout;

    private OnfidoHostedWebModuleBottomSheetLayoutBinding(LinearLayout linearLayout, ImageButton imageButton, WebView webView, CircularProgressIndicator circularProgressIndicator, ConstraintLayout constraintLayout) {
        this.rootView = linearLayout;
        this.buttonClose = imageButton;
        this.onfidoBottomSheetWebView = webView;
        this.progressCircular = circularProgressIndicator;
        this.topLayout = constraintLayout;
    }

    public static OnfidoHostedWebModuleBottomSheetLayoutBinding bind(View view) {
        int i = R.id.buttonClose;
        ImageButton imageButton = (ImageButton) ViewBindings.findChildViewById(view, i);
        if (imageButton != null) {
            i = R.id.onfidoBottomSheetWebView;
            WebView webView = (WebView) ViewBindings.findChildViewById(view, i);
            if (webView != null) {
                i = R.id.progress_circular;
                CircularProgressIndicator circularProgressIndicator = (CircularProgressIndicator) ViewBindings.findChildViewById(view, i);
                if (circularProgressIndicator != null) {
                    i = R.id.topLayout;
                    ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
                    if (constraintLayout != null) {
                        return new OnfidoHostedWebModuleBottomSheetLayoutBinding((LinearLayout) view, imageButton, webView, circularProgressIndicator, constraintLayout);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static OnfidoHostedWebModuleBottomSheetLayoutBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static OnfidoHostedWebModuleBottomSheetLayoutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_hosted_web_module_bottom_sheet_layout, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
