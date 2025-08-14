package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.ui.widget.RecyclerView;

/* loaded from: classes2.dex */
public final class OnfidoFragmentCountrySelectionBinding implements ViewBinding {
    public final AppCompatButton closeButton;
    public final RecyclerView countriesList;
    public final FrameLayout countryPopoverSheetHint;
    public final TextView description;
    private final RelativeLayout rootView;
    public final AppCompatButton title;

    private OnfidoFragmentCountrySelectionBinding(RelativeLayout relativeLayout, AppCompatButton appCompatButton, RecyclerView recyclerView, FrameLayout frameLayout, TextView textView, AppCompatButton appCompatButton2) {
        this.rootView = relativeLayout;
        this.closeButton = appCompatButton;
        this.countriesList = recyclerView;
        this.countryPopoverSheetHint = frameLayout;
        this.description = textView;
        this.title = appCompatButton2;
    }

    public static OnfidoFragmentCountrySelectionBinding bind(View view) {
        int i = R.id.close_button;
        AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(view, i);
        if (appCompatButton != null) {
            i = R.id.countriesList;
            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
            if (recyclerView != null) {
                i = R.id.country_popover_sheet_hint;
                FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i);
                if (frameLayout != null) {
                    i = R.id.description;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                    if (textView != null) {
                        i = R.id.title;
                        AppCompatButton appCompatButton2 = (AppCompatButton) ViewBindings.findChildViewById(view, i);
                        if (appCompatButton2 != null) {
                            return new OnfidoFragmentCountrySelectionBinding((RelativeLayout) view, appCompatButton, recyclerView, frameLayout, textView, appCompatButton2);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static OnfidoFragmentCountrySelectionBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static OnfidoFragmentCountrySelectionBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_fragment_country_selection, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
