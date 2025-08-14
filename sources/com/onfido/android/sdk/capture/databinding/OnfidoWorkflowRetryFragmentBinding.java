package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.ui.widget.OnfidoButton;

/* loaded from: classes2.dex */
public final class OnfidoWorkflowRetryFragmentBinding implements ViewBinding {
    public final TextView description;
    public final OnfidoButton retryButton;
    private final LinearLayout rootView;
    public final TextView title;

    private OnfidoWorkflowRetryFragmentBinding(LinearLayout linearLayout, TextView textView, OnfidoButton onfidoButton, TextView textView2) {
        this.rootView = linearLayout;
        this.description = textView;
        this.retryButton = onfidoButton;
        this.title = textView2;
    }

    public static OnfidoWorkflowRetryFragmentBinding bind(View view) {
        int i = R.id.description;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
        if (textView != null) {
            i = R.id.retryButton;
            OnfidoButton onfidoButton = (OnfidoButton) ViewBindings.findChildViewById(view, i);
            if (onfidoButton != null) {
                i = R.id.title;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView2 != null) {
                    return new OnfidoWorkflowRetryFragmentBinding((LinearLayout) view, textView, onfidoButton, textView2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static OnfidoWorkflowRetryFragmentBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static OnfidoWorkflowRetryFragmentBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_workflow_retry_fragment, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
