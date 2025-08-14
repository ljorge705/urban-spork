package com.onfido.workflow.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentContainerView;
import androidx.viewbinding.ViewBinding;
import com.onfido.workflow.R;

/* loaded from: classes6.dex */
public final class OnfidoOrchestrationFragmentBinding implements ViewBinding {
    public final FragmentContainerView fragmentContainer;
    private final FragmentContainerView rootView;

    @Override // androidx.viewbinding.ViewBinding
    public FragmentContainerView getRoot() {
        return this.rootView;
    }

    private OnfidoOrchestrationFragmentBinding(FragmentContainerView fragmentContainerView, FragmentContainerView fragmentContainerView2) {
        this.rootView = fragmentContainerView;
        this.fragmentContainer = fragmentContainerView2;
    }

    public static OnfidoOrchestrationFragmentBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static OnfidoOrchestrationFragmentBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_orchestration_fragment, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static OnfidoOrchestrationFragmentBinding bind(View view) {
        if (view == null) {
            throw new NullPointerException("rootView");
        }
        FragmentContainerView fragmentContainerView = (FragmentContainerView) view;
        return new OnfidoOrchestrationFragmentBinding(fragmentContainerView, fragmentContainerView);
    }
}
