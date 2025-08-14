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

/* loaded from: classes2.dex */
public final class OnfidoItemRestrictedDocumentSelectionDocumentTypeBinding implements ViewBinding {
    public final ImageView ivDocumentIcon;
    private final LinearLayout rootView;
    public final TextView tvDescription;
    public final TextView tvDocumentType;

    private OnfidoItemRestrictedDocumentSelectionDocumentTypeBinding(LinearLayout linearLayout, ImageView imageView, TextView textView, TextView textView2) {
        this.rootView = linearLayout;
        this.ivDocumentIcon = imageView;
        this.tvDescription = textView;
        this.tvDocumentType = textView2;
    }

    public static OnfidoItemRestrictedDocumentSelectionDocumentTypeBinding bind(View view) {
        int i = R.id.ivDocumentIcon;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            i = R.id.tvDescription;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
            if (textView != null) {
                i = R.id.tvDocumentType;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView2 != null) {
                    return new OnfidoItemRestrictedDocumentSelectionDocumentTypeBinding((LinearLayout) view, imageView, textView, textView2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static OnfidoItemRestrictedDocumentSelectionDocumentTypeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static OnfidoItemRestrictedDocumentSelectionDocumentTypeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_item_restricted_document_selection_document_type, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
