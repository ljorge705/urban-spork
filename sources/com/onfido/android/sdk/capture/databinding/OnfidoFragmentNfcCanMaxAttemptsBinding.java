package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.ui.widget.OnfidoButton;

/* loaded from: classes2.dex */
public final class OnfidoFragmentNfcCanMaxAttemptsBinding implements ViewBinding {
    public final OnfidoButton btnExit;
    public final ImageView icon;
    public final OnfidoWatermarkLayoutBinding onfidoInclude3;
    private final LinearLayout rootView;
    public final TextView subtitle;
    public final TextView title;

    private OnfidoFragmentNfcCanMaxAttemptsBinding(LinearLayout linearLayout, OnfidoButton onfidoButton, ImageView imageView, OnfidoWatermarkLayoutBinding onfidoWatermarkLayoutBinding, TextView textView, TextView textView2) {
        this.rootView = linearLayout;
        this.btnExit = onfidoButton;
        this.icon = imageView;
        this.onfidoInclude3 = onfidoWatermarkLayoutBinding;
        this.subtitle = textView;
        this.title = textView2;
    }

    public static OnfidoFragmentNfcCanMaxAttemptsBinding bind(View view) {
        View viewFindChildViewById;
        int i = R.id.btnExit;
        OnfidoButton onfidoButton = (OnfidoButton) ViewBindings.findChildViewById(view, i);
        if (onfidoButton != null) {
            i = R.id.icon;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
            if (imageView != null && (viewFindChildViewById = ViewBindings.findChildViewById(view, (i = R.id.onfidoInclude3))) != null) {
                OnfidoWatermarkLayoutBinding onfidoWatermarkLayoutBindingBind = OnfidoWatermarkLayoutBinding.bind(viewFindChildViewById);
                i = R.id.subtitle;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView != null) {
                    i = R.id.title;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                    if (textView2 != null) {
                        return new OnfidoFragmentNfcCanMaxAttemptsBinding((LinearLayout) view, onfidoButton, imageView, onfidoWatermarkLayoutBindingBind, textView, textView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static OnfidoFragmentNfcCanMaxAttemptsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static OnfidoFragmentNfcCanMaxAttemptsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_fragment_nfc_can_max_attempts, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
