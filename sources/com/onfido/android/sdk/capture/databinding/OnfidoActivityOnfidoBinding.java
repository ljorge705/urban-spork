package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentContainerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.onfido.android.sdk.capture.R;

/* loaded from: classes2.dex */
public final class OnfidoActivityOnfidoBinding implements ViewBinding {
    public final FragmentContainerView hostFragmentContainer;
    public final RelativeLayout loadingView;
    private final FrameLayout rootView;
    public final Toolbar toolbar;

    private OnfidoActivityOnfidoBinding(FrameLayout frameLayout, FragmentContainerView fragmentContainerView, RelativeLayout relativeLayout, Toolbar toolbar) {
        this.rootView = frameLayout;
        this.hostFragmentContainer = fragmentContainerView;
        this.loadingView = relativeLayout;
        this.toolbar = toolbar;
    }

    public static OnfidoActivityOnfidoBinding bind(View view) {
        int i = R.id.hostFragmentContainer;
        FragmentContainerView fragmentContainerView = (FragmentContainerView) ViewBindings.findChildViewById(view, i);
        if (fragmentContainerView != null) {
            i = R.id.loadingView;
            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, i);
            if (relativeLayout != null) {
                i = R.id.toolbar;
                Toolbar toolbar = (Toolbar) ViewBindings.findChildViewById(view, i);
                if (toolbar != null) {
                    return new OnfidoActivityOnfidoBinding((FrameLayout) view, fragmentContainerView, relativeLayout, toolbar);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static OnfidoActivityOnfidoBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static OnfidoActivityOnfidoBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_activity_onfido, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
