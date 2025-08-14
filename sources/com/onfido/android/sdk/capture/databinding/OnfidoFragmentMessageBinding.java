package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.ui.widget.OnfidoButton;

/* loaded from: classes2.dex */
public final class OnfidoFragmentMessageBinding implements ViewBinding {
    public final OnfidoButton next;
    private final RelativeLayout rootView;
    public final TextView subtitle;
    public final TextView title;

    private OnfidoFragmentMessageBinding(RelativeLayout relativeLayout, OnfidoButton onfidoButton, TextView textView, TextView textView2) {
        this.rootView = relativeLayout;
        this.next = onfidoButton;
        this.subtitle = textView;
        this.title = textView2;
    }

    public static OnfidoFragmentMessageBinding bind(View view) {
        int i = R.id.next;
        OnfidoButton onfidoButton = (OnfidoButton) ViewBindings.findChildViewById(view, i);
        if (onfidoButton != null) {
            i = R.id.subtitle;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
            if (textView != null) {
                i = R.id.title;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView2 != null) {
                    return new OnfidoFragmentMessageBinding((RelativeLayout) view, onfidoButton, textView, textView2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static OnfidoFragmentMessageBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static OnfidoFragmentMessageBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_fragment_message, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
