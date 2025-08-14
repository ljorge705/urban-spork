package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.ui.widget.OnfidoButton;

/* loaded from: classes2.dex */
public final class OnfidoFragmentNfcCanEntryBinding implements ViewBinding {
    public final TextInputLayout canNumberField;
    public final TextInputEditText canTextInput;
    public final OnfidoButton continueButton;
    public final TextView description;
    public final TextView noCanButton;
    private final ScrollView rootView;
    public final TextView title;

    private OnfidoFragmentNfcCanEntryBinding(ScrollView scrollView, TextInputLayout textInputLayout, TextInputEditText textInputEditText, OnfidoButton onfidoButton, TextView textView, TextView textView2, TextView textView3) {
        this.rootView = scrollView;
        this.canNumberField = textInputLayout;
        this.canTextInput = textInputEditText;
        this.continueButton = onfidoButton;
        this.description = textView;
        this.noCanButton = textView2;
        this.title = textView3;
    }

    public static OnfidoFragmentNfcCanEntryBinding bind(View view) {
        int i = R.id.canNumberField;
        TextInputLayout textInputLayout = (TextInputLayout) ViewBindings.findChildViewById(view, i);
        if (textInputLayout != null) {
            i = R.id.canTextInput;
            TextInputEditText textInputEditText = (TextInputEditText) ViewBindings.findChildViewById(view, i);
            if (textInputEditText != null) {
                i = R.id.continueButton;
                OnfidoButton onfidoButton = (OnfidoButton) ViewBindings.findChildViewById(view, i);
                if (onfidoButton != null) {
                    i = R.id.description;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                    if (textView != null) {
                        i = R.id.noCanButton;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                        if (textView2 != null) {
                            i = R.id.title;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                            if (textView3 != null) {
                                return new OnfidoFragmentNfcCanEntryBinding((ScrollView) view, textInputLayout, textInputEditText, onfidoButton, textView, textView2, textView3);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static OnfidoFragmentNfcCanEntryBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public ScrollView getRoot() {
        return this.rootView;
    }

    public static OnfidoFragmentNfcCanEntryBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_fragment_nfc_can_entry, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
