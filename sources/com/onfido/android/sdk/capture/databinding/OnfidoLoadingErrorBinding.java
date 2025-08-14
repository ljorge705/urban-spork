package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.onfido.android.sdk.capture.R;

/* loaded from: classes2.dex */
public final class OnfidoLoadingErrorBinding implements ViewBinding {
    public final TextView errorMessage;
    public final FrameLayout reloadButton;
    public final View reloadButtonBackground;
    private final LinearLayout rootView;

    private OnfidoLoadingErrorBinding(LinearLayout linearLayout, TextView textView, FrameLayout frameLayout, View view) {
        this.rootView = linearLayout;
        this.errorMessage = textView;
        this.reloadButton = frameLayout;
        this.reloadButtonBackground = view;
    }

    public static OnfidoLoadingErrorBinding bind(View view) {
        View viewFindChildViewById;
        int i = R.id.errorMessage;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
        if (textView != null) {
            i = R.id.reloadButton;
            FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i);
            if (frameLayout != null && (viewFindChildViewById = ViewBindings.findChildViewById(view, (i = R.id.reloadButtonBackground))) != null) {
                return new OnfidoLoadingErrorBinding((LinearLayout) view, textView, frameLayout, viewFindChildViewById);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static OnfidoLoadingErrorBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static OnfidoLoadingErrorBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_loading_error, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
