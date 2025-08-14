package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.fragment.app.FragmentContainerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.onfido.android.sdk.capture.R;

/* loaded from: classes2.dex */
public final class OnfidoAvcFragmentHostBinding implements ViewBinding {
    public final RelativeLayout avcHostLoadingView;
    public final FragmentContainerView fragmentContainerView;
    private final FrameLayout rootView;

    private OnfidoAvcFragmentHostBinding(FrameLayout frameLayout, RelativeLayout relativeLayout, FragmentContainerView fragmentContainerView) {
        this.rootView = frameLayout;
        this.avcHostLoadingView = relativeLayout;
        this.fragmentContainerView = fragmentContainerView;
    }

    public static OnfidoAvcFragmentHostBinding bind(View view) {
        int i = R.id.avcHostLoadingView;
        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, i);
        if (relativeLayout != null) {
            i = R.id.fragmentContainerView;
            FragmentContainerView fragmentContainerView = (FragmentContainerView) ViewBindings.findChildViewById(view, i);
            if (fragmentContainerView != null) {
                return new OnfidoAvcFragmentHostBinding((FrameLayout) view, relativeLayout, fragmentContainerView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static OnfidoAvcFragmentHostBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static OnfidoAvcFragmentHostBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_avc_fragment_host, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
