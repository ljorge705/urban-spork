package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.onfido.android.sdk.capture.R;

/* loaded from: classes2.dex */
public final class OnfidoRestrictedDocumentSelectionCountryPickerViewBinding implements ViewBinding {
    public final ImageView arrowIcon;
    public final ImageView countryIcon;
    public final TextView countryName;
    private final ConstraintLayout rootView;

    private OnfidoRestrictedDocumentSelectionCountryPickerViewBinding(ConstraintLayout constraintLayout, ImageView imageView, ImageView imageView2, TextView textView) {
        this.rootView = constraintLayout;
        this.arrowIcon = imageView;
        this.countryIcon = imageView2;
        this.countryName = textView;
    }

    public static OnfidoRestrictedDocumentSelectionCountryPickerViewBinding bind(View view) {
        int i = R.id.arrowIcon;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            i = R.id.countryIcon;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i);
            if (imageView2 != null) {
                i = R.id.countryName;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView != null) {
                    return new OnfidoRestrictedDocumentSelectionCountryPickerViewBinding((ConstraintLayout) view, imageView, imageView2, textView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static OnfidoRestrictedDocumentSelectionCountryPickerViewBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static OnfidoRestrictedDocumentSelectionCountryPickerViewBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_restricted_document_selection_country_picker_view, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
