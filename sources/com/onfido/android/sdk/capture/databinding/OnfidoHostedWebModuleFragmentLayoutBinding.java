package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.ui.widget.OnfidoButton;
import com.onfido.android.sdk.capture.ui.widget.WatermarkView;

/* loaded from: classes2.dex */
public final class OnfidoHostedWebModuleFragmentLayoutBinding implements ViewBinding {
    public final ImageView errorIcon;
    public final TextView errorTitle;
    public final WebView onfidoWebView;
    public final OnfidoButton reloadButton;
    private final LinearLayout rootView;
    public final WatermarkView watermark;
    public final ConstraintLayout webViewErrorLayout;

    private OnfidoHostedWebModuleFragmentLayoutBinding(LinearLayout linearLayout, ImageView imageView, TextView textView, WebView webView, OnfidoButton onfidoButton, WatermarkView watermarkView, ConstraintLayout constraintLayout) {
        this.rootView = linearLayout;
        this.errorIcon = imageView;
        this.errorTitle = textView;
        this.onfidoWebView = webView;
        this.reloadButton = onfidoButton;
        this.watermark = watermarkView;
        this.webViewErrorLayout = constraintLayout;
    }

    public static OnfidoHostedWebModuleFragmentLayoutBinding bind(View view) {
        int i = R.id.errorIcon;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            i = R.id.errorTitle;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
            if (textView != null) {
                i = R.id.onfidoWebView;
                WebView webView = (WebView) ViewBindings.findChildViewById(view, i);
                if (webView != null) {
                    i = R.id.reloadButton;
                    OnfidoButton onfidoButton = (OnfidoButton) ViewBindings.findChildViewById(view, i);
                    if (onfidoButton != null) {
                        i = R.id.watermark;
                        WatermarkView watermarkView = (WatermarkView) ViewBindings.findChildViewById(view, i);
                        if (watermarkView != null) {
                            i = R.id.webViewErrorLayout;
                            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
                            if (constraintLayout != null) {
                                return new OnfidoHostedWebModuleFragmentLayoutBinding((LinearLayout) view, imageView, textView, webView, onfidoButton, watermarkView, constraintLayout);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static OnfidoHostedWebModuleFragmentLayoutBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static OnfidoHostedWebModuleFragmentLayoutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_hosted_web_module_fragment_layout, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
