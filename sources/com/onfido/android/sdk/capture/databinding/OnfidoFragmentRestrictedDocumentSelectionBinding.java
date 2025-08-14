package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.ui.widget.OnfidoButton;
import com.onfido.android.sdk.capture.ui.widget.ShadowedNestedScrollView;

/* loaded from: classes2.dex */
public final class OnfidoFragmentRestrictedDocumentSelectionBinding implements ViewBinding {
    public final OnfidoButton btRetry;
    public final OnfidoRestrictedDocumentSelectionCountryPickerViewBinding countryPicker;
    public final RecyclerView documentList;
    public final TextView headerDocumentType;
    public final OnfidoWatermarkLayoutBinding onfidoInclude;
    private final ConstraintLayout rootView;
    public final ShadowedNestedScrollView scrollView;
    public final TextView title;
    public final TextView tvNfcRequiredWarning;
    public final TextView tvSelectedCountryTitle;

    private OnfidoFragmentRestrictedDocumentSelectionBinding(ConstraintLayout constraintLayout, OnfidoButton onfidoButton, OnfidoRestrictedDocumentSelectionCountryPickerViewBinding onfidoRestrictedDocumentSelectionCountryPickerViewBinding, RecyclerView recyclerView, TextView textView, OnfidoWatermarkLayoutBinding onfidoWatermarkLayoutBinding, ShadowedNestedScrollView shadowedNestedScrollView, TextView textView2, TextView textView3, TextView textView4) {
        this.rootView = constraintLayout;
        this.btRetry = onfidoButton;
        this.countryPicker = onfidoRestrictedDocumentSelectionCountryPickerViewBinding;
        this.documentList = recyclerView;
        this.headerDocumentType = textView;
        this.onfidoInclude = onfidoWatermarkLayoutBinding;
        this.scrollView = shadowedNestedScrollView;
        this.title = textView2;
        this.tvNfcRequiredWarning = textView3;
        this.tvSelectedCountryTitle = textView4;
    }

    public static OnfidoFragmentRestrictedDocumentSelectionBinding bind(View view) {
        View viewFindChildViewById;
        View viewFindChildViewById2;
        int i = R.id.btRetry;
        OnfidoButton onfidoButton = (OnfidoButton) ViewBindings.findChildViewById(view, i);
        if (onfidoButton != null && (viewFindChildViewById = ViewBindings.findChildViewById(view, (i = R.id.countryPicker))) != null) {
            OnfidoRestrictedDocumentSelectionCountryPickerViewBinding onfidoRestrictedDocumentSelectionCountryPickerViewBindingBind = OnfidoRestrictedDocumentSelectionCountryPickerViewBinding.bind(viewFindChildViewById);
            i = R.id.documentList;
            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
            if (recyclerView != null) {
                i = R.id.headerDocumentType;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView != null && (viewFindChildViewById2 = ViewBindings.findChildViewById(view, (i = R.id.onfidoInclude))) != null) {
                    OnfidoWatermarkLayoutBinding onfidoWatermarkLayoutBindingBind = OnfidoWatermarkLayoutBinding.bind(viewFindChildViewById2);
                    i = R.id.scrollView;
                    ShadowedNestedScrollView shadowedNestedScrollView = (ShadowedNestedScrollView) ViewBindings.findChildViewById(view, i);
                    if (shadowedNestedScrollView != null) {
                        i = R.id.title;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                        if (textView2 != null) {
                            i = R.id.tvNfcRequiredWarning;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                            if (textView3 != null) {
                                i = R.id.tvSelectedCountryTitle;
                                TextView textView4 = (TextView) ViewBindings.findChildViewById(view, i);
                                if (textView4 != null) {
                                    return new OnfidoFragmentRestrictedDocumentSelectionBinding((ConstraintLayout) view, onfidoButton, onfidoRestrictedDocumentSelectionCountryPickerViewBindingBind, recyclerView, textView, onfidoWatermarkLayoutBindingBind, shadowedNestedScrollView, textView2, textView3, textView4);
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static OnfidoFragmentRestrictedDocumentSelectionBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static OnfidoFragmentRestrictedDocumentSelectionBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_fragment_restricted_document_selection, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
