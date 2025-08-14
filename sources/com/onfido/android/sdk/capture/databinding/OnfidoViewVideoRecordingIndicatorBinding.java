package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.onfido.android.sdk.capture.R;

/* loaded from: classes2.dex */
public final class OnfidoViewVideoRecordingIndicatorBinding implements ViewBinding {
    public final TextView mainText;
    private final LinearLayout rootView;

    private OnfidoViewVideoRecordingIndicatorBinding(LinearLayout linearLayout, TextView textView) {
        this.rootView = linearLayout;
        this.mainText = textView;
    }

    public static OnfidoViewVideoRecordingIndicatorBinding bind(View view) {
        int i = R.id.mainText;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
        if (textView != null) {
            return new OnfidoViewVideoRecordingIndicatorBinding((LinearLayout) view, textView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static OnfidoViewVideoRecordingIndicatorBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static OnfidoViewVideoRecordingIndicatorBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_view_video_recording_indicator, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
