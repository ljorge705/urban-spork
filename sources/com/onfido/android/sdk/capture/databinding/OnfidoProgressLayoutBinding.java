package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.onfido.android.sdk.capture.R;

/* loaded from: classes2.dex */
public final class OnfidoProgressLayoutBinding implements ViewBinding {
    public final CircularProgressIndicator progressCircular;
    public final TextView progressMessage;
    private final LinearLayout rootView;

    private OnfidoProgressLayoutBinding(LinearLayout linearLayout, CircularProgressIndicator circularProgressIndicator, TextView textView) {
        this.rootView = linearLayout;
        this.progressCircular = circularProgressIndicator;
        this.progressMessage = textView;
    }

    public static OnfidoProgressLayoutBinding bind(View view) {
        int i = R.id.progress_circular;
        CircularProgressIndicator circularProgressIndicator = (CircularProgressIndicator) ViewBindings.findChildViewById(view, i);
        if (circularProgressIndicator != null) {
            i = R.id.progressMessage;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
            if (textView != null) {
                return new OnfidoProgressLayoutBinding((LinearLayout) view, circularProgressIndicator, textView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static OnfidoProgressLayoutBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static OnfidoProgressLayoutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_progress_layout, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
