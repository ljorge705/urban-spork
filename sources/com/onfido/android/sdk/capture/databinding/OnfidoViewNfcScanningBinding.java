package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.ui.camera.view.AutoPlayVideoView;

/* loaded from: classes2.dex */
public final class OnfidoViewNfcScanningBinding implements ViewBinding {
    public final AutoPlayVideoView instructionVideoView;
    public final ImageView nfcIcon;
    public final CircularProgressIndicator progressBarScanning;
    private final ConstraintLayout rootView;
    public final AppCompatButton secondaryAction;
    public final TextView subtitle;
    public final ImageView successIcon;
    public final TextView title;
    public final LinearLayout titleContainer;

    private OnfidoViewNfcScanningBinding(ConstraintLayout constraintLayout, AutoPlayVideoView autoPlayVideoView, ImageView imageView, CircularProgressIndicator circularProgressIndicator, AppCompatButton appCompatButton, TextView textView, ImageView imageView2, TextView textView2, LinearLayout linearLayout) {
        this.rootView = constraintLayout;
        this.instructionVideoView = autoPlayVideoView;
        this.nfcIcon = imageView;
        this.progressBarScanning = circularProgressIndicator;
        this.secondaryAction = appCompatButton;
        this.subtitle = textView;
        this.successIcon = imageView2;
        this.title = textView2;
        this.titleContainer = linearLayout;
    }

    public static OnfidoViewNfcScanningBinding bind(View view) {
        int i = R.id.instructionVideoView;
        AutoPlayVideoView autoPlayVideoView = (AutoPlayVideoView) ViewBindings.findChildViewById(view, i);
        if (autoPlayVideoView != null) {
            i = R.id.nfcIcon;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
            if (imageView != null) {
                i = R.id.progressBarScanning;
                CircularProgressIndicator circularProgressIndicator = (CircularProgressIndicator) ViewBindings.findChildViewById(view, i);
                if (circularProgressIndicator != null) {
                    i = R.id.secondaryAction;
                    AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(view, i);
                    if (appCompatButton != null) {
                        i = R.id.subtitle;
                        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                        if (textView != null) {
                            i = R.id.successIcon;
                            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i);
                            if (imageView2 != null) {
                                i = R.id.title;
                                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                                if (textView2 != null) {
                                    i = R.id.title_container;
                                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                    if (linearLayout != null) {
                                        return new OnfidoViewNfcScanningBinding((ConstraintLayout) view, autoPlayVideoView, imageView, circularProgressIndicator, appCompatButton, textView, imageView2, textView2, linearLayout);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static OnfidoViewNfcScanningBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static OnfidoViewNfcScanningBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_view_nfc_scanning, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
