package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentContainerView;
import androidx.viewbinding.ViewBinding;
import com.onfido.android.sdk.capture.R;

/* loaded from: classes2.dex */
public final class OnfidoNfcFragmentHostBinding implements ViewBinding {
    public final FragmentContainerView fragmentContainer;
    private final FragmentContainerView rootView;

    private OnfidoNfcFragmentHostBinding(FragmentContainerView fragmentContainerView, FragmentContainerView fragmentContainerView2) {
        this.rootView = fragmentContainerView;
        this.fragmentContainer = fragmentContainerView2;
    }

    public static OnfidoNfcFragmentHostBinding bind(View view) {
        if (view == null) {
            throw new NullPointerException("rootView");
        }
        FragmentContainerView fragmentContainerView = (FragmentContainerView) view;
        return new OnfidoNfcFragmentHostBinding(fragmentContainerView, fragmentContainerView);
    }

    public static OnfidoNfcFragmentHostBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public FragmentContainerView getRoot() {
        return this.rootView;
    }

    public static OnfidoNfcFragmentHostBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_nfc_fragment_host, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
