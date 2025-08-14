package com.onfido.workflow.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.onfido.android.sdk.capture.ui.widget.WatermarkView;
import com.onfido.workflow.R;

/* loaded from: classes6.dex */
public final class WorkflowLoadingFragmentBinding implements ViewBinding {
    public final CircularProgressIndicator progressCircular;
    private final ConstraintLayout rootView;
    public final TextView subtitle;
    public final TextView title;
    public final WatermarkView watermark;

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    private WorkflowLoadingFragmentBinding(ConstraintLayout constraintLayout, CircularProgressIndicator circularProgressIndicator, TextView textView, TextView textView2, WatermarkView watermarkView) {
        this.rootView = constraintLayout;
        this.progressCircular = circularProgressIndicator;
        this.subtitle = textView;
        this.title = textView2;
        this.watermark = watermarkView;
    }

    public static WorkflowLoadingFragmentBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static WorkflowLoadingFragmentBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.workflow_loading_fragment, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static WorkflowLoadingFragmentBinding bind(View view) {
        int i = R.id.progress_circular;
        CircularProgressIndicator circularProgressIndicator = (CircularProgressIndicator) ViewBindings.findChildViewById(view, i);
        if (circularProgressIndicator != null) {
            i = R.id.subtitle;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
            if (textView != null) {
                i = R.id.title;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView2 != null) {
                    i = R.id.watermark;
                    WatermarkView watermarkView = (WatermarkView) ViewBindings.findChildViewById(view, i);
                    if (watermarkView != null) {
                        return new WorkflowLoadingFragmentBinding((ConstraintLayout) view, circularProgressIndicator, textView, textView2, watermarkView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
