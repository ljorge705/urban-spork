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
public final class OnfidoViewDocumentFormatSelectionBinding implements ViewBinding {
    public final TextView cardFormat;
    public final TextView foldedFormat;
    private final LinearLayout rootView;
    public final TextView title;

    private OnfidoViewDocumentFormatSelectionBinding(LinearLayout linearLayout, TextView textView, TextView textView2, TextView textView3) {
        this.rootView = linearLayout;
        this.cardFormat = textView;
        this.foldedFormat = textView2;
        this.title = textView3;
    }

    public static OnfidoViewDocumentFormatSelectionBinding bind(View view) {
        int i = R.id.cardFormat;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
        if (textView != null) {
            i = R.id.foldedFormat;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
            if (textView2 != null) {
                i = R.id.title;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView3 != null) {
                    return new OnfidoViewDocumentFormatSelectionBinding((LinearLayout) view, textView, textView2, textView3);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static OnfidoViewDocumentFormatSelectionBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static OnfidoViewDocumentFormatSelectionBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_view_document_format_selection, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
