package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.onfido.android.sdk.capture.R;

/* loaded from: classes2.dex */
public final class OnfidoItemNfcInstructionBinding implements ViewBinding {
    private final TextView rootView;
    public final TextView tvInstruction;

    private OnfidoItemNfcInstructionBinding(TextView textView, TextView textView2) {
        this.rootView = textView;
        this.tvInstruction = textView2;
    }

    public static OnfidoItemNfcInstructionBinding bind(View view) {
        if (view == null) {
            throw new NullPointerException("rootView");
        }
        TextView textView = (TextView) view;
        return new OnfidoItemNfcInstructionBinding(textView, textView);
    }

    public static OnfidoItemNfcInstructionBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public TextView getRoot() {
        return this.rootView;
    }

    public static OnfidoItemNfcInstructionBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_item_nfc_instruction, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
