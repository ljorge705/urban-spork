package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentContainerView;
import androidx.viewbinding.ViewBinding;
import com.onfido.android.sdk.capture.R;

/* loaded from: classes2.dex */
public final class OnfidoPoaFragmentHostBinding implements ViewBinding {
    public final FragmentContainerView poaFragmentContainerView;
    private final FragmentContainerView rootView;

    private OnfidoPoaFragmentHostBinding(FragmentContainerView fragmentContainerView, FragmentContainerView fragmentContainerView2) {
        this.rootView = fragmentContainerView;
        this.poaFragmentContainerView = fragmentContainerView2;
    }

    public static OnfidoPoaFragmentHostBinding bind(View view) {
        if (view == null) {
            throw new NullPointerException("rootView");
        }
        FragmentContainerView fragmentContainerView = (FragmentContainerView) view;
        return new OnfidoPoaFragmentHostBinding(fragmentContainerView, fragmentContainerView);
    }

    public static OnfidoPoaFragmentHostBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public FragmentContainerView getRoot() {
        return this.rootView;
    }

    public static OnfidoPoaFragmentHostBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_poa_fragment_host, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
