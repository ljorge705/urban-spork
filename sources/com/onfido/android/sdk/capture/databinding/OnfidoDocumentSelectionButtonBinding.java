package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.onfido.android.sdk.capture.R;

/* loaded from: classes2.dex */
public final class OnfidoDocumentSelectionButtonBinding implements ViewBinding {
    public final ImageView arrow;
    public final RelativeLayout container;
    public final TextView documentDescription;
    public final TextView documentName;
    public final ImageView icon;
    public final FrameLayout iconContainer;
    private final RelativeLayout rootView;

    private OnfidoDocumentSelectionButtonBinding(RelativeLayout relativeLayout, ImageView imageView, RelativeLayout relativeLayout2, TextView textView, TextView textView2, ImageView imageView2, FrameLayout frameLayout) {
        this.rootView = relativeLayout;
        this.arrow = imageView;
        this.container = relativeLayout2;
        this.documentDescription = textView;
        this.documentName = textView2;
        this.icon = imageView2;
        this.iconContainer = frameLayout;
    }

    public static OnfidoDocumentSelectionButtonBinding bind(View view) {
        int i = R.id.arrow;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            RelativeLayout relativeLayout = (RelativeLayout) view;
            i = R.id.documentDescription;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
            if (textView != null) {
                i = R.id.documentName;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView2 != null) {
                    i = R.id.icon;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i);
                    if (imageView2 != null) {
                        i = R.id.iconContainer;
                        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i);
                        if (frameLayout != null) {
                            return new OnfidoDocumentSelectionButtonBinding(relativeLayout, imageView, relativeLayout, textView, textView2, imageView2, frameLayout);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static OnfidoDocumentSelectionButtonBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static OnfidoDocumentSelectionButtonBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_document_selection_button, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
