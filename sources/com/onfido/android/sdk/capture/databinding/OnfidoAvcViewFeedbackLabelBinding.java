package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.onfido.android.sdk.capture.R;

/* loaded from: classes2.dex */
public final class OnfidoAvcViewFeedbackLabelBinding implements ViewBinding {
    public final TextView feedbackTextView;
    private final TextView rootView;

    private OnfidoAvcViewFeedbackLabelBinding(TextView textView, TextView textView2) {
        this.rootView = textView;
        this.feedbackTextView = textView2;
    }

    public static OnfidoAvcViewFeedbackLabelBinding bind(View view) {
        if (view == null) {
            throw new NullPointerException("rootView");
        }
        TextView textView = (TextView) view;
        return new OnfidoAvcViewFeedbackLabelBinding(textView, textView);
    }

    public static OnfidoAvcViewFeedbackLabelBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public TextView getRoot() {
        return this.rootView;
    }

    public static OnfidoAvcViewFeedbackLabelBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_avc_view_feedback_label, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
