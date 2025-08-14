package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.onfido.android.sdk.capture.R;

/* loaded from: classes2.dex */
public final class OnfidoCaptureValidationBubbleBinding implements ViewBinding {
    public final FrameLayout bubbleIconBackground;
    public final LinearLayout bubbleRoot;
    public final TextView bubbleSubtitle;
    public final TextView bubbleTitle;
    private final LinearLayout rootView;
    public final ImageView warningIcon;

    private OnfidoCaptureValidationBubbleBinding(LinearLayout linearLayout, FrameLayout frameLayout, LinearLayout linearLayout2, TextView textView, TextView textView2, ImageView imageView) {
        this.rootView = linearLayout;
        this.bubbleIconBackground = frameLayout;
        this.bubbleRoot = linearLayout2;
        this.bubbleSubtitle = textView;
        this.bubbleTitle = textView2;
        this.warningIcon = imageView;
    }

    public static OnfidoCaptureValidationBubbleBinding bind(View view) {
        int i = R.id.bubbleIconBackground;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i);
        if (frameLayout != null) {
            LinearLayout linearLayout = (LinearLayout) view;
            i = R.id.bubbleSubtitle;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
            if (textView != null) {
                i = R.id.bubbleTitle;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView2 != null) {
                    i = R.id.warningIcon;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
                    if (imageView != null) {
                        return new OnfidoCaptureValidationBubbleBinding(linearLayout, frameLayout, linearLayout, textView, textView2, imageView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static OnfidoCaptureValidationBubbleBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static OnfidoCaptureValidationBubbleBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_capture_validation_bubble, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
