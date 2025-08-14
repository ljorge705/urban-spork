package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.viewbinding.ViewBinding;
import com.onfido.android.sdk.capture.R;

/* loaded from: classes2.dex */
public final class OnfidoAvcViewHeadTurnTickIconBinding implements ViewBinding {
    private final ImageView rootView;

    private OnfidoAvcViewHeadTurnTickIconBinding(ImageView imageView) {
        this.rootView = imageView;
    }

    public static OnfidoAvcViewHeadTurnTickIconBinding bind(View view) {
        if (view != null) {
            return new OnfidoAvcViewHeadTurnTickIconBinding((ImageView) view);
        }
        throw new NullPointerException("rootView");
    }

    public static OnfidoAvcViewHeadTurnTickIconBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public ImageView getRoot() {
        return this.rootView;
    }

    public static OnfidoAvcViewHeadTurnTickIconBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_avc_view_head_turn_tick_icon, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
