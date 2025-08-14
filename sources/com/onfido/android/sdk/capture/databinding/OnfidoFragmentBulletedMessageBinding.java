package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.ui.widget.OnfidoButton;
import com.onfido.android.sdk.capture.ui.widget.ShadowedScrollView;

/* loaded from: classes2.dex */
public final class OnfidoFragmentBulletedMessageBinding implements ViewBinding {
    public final LinearLayout descriptionContainer;
    public final TextView infoTextView;
    public final TextView listHeader;
    public final OnfidoButton next;
    private final RelativeLayout rootView;
    public final ShadowedScrollView scrollView;
    public final LinearLayout stepsContainer;
    public final TextView subtitle;
    public final TextView title;

    private OnfidoFragmentBulletedMessageBinding(RelativeLayout relativeLayout, LinearLayout linearLayout, TextView textView, TextView textView2, OnfidoButton onfidoButton, ShadowedScrollView shadowedScrollView, LinearLayout linearLayout2, TextView textView3, TextView textView4) {
        this.rootView = relativeLayout;
        this.descriptionContainer = linearLayout;
        this.infoTextView = textView;
        this.listHeader = textView2;
        this.next = onfidoButton;
        this.scrollView = shadowedScrollView;
        this.stepsContainer = linearLayout2;
        this.subtitle = textView3;
        this.title = textView4;
    }

    public static OnfidoFragmentBulletedMessageBinding bind(View view) {
        int i = R.id.descriptionContainer;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
        if (linearLayout != null) {
            i = R.id.infoTextView;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
            if (textView != null) {
                i = R.id.listHeader;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView2 != null) {
                    i = R.id.next;
                    OnfidoButton onfidoButton = (OnfidoButton) ViewBindings.findChildViewById(view, i);
                    if (onfidoButton != null) {
                        i = R.id.scrollView;
                        ShadowedScrollView shadowedScrollView = (ShadowedScrollView) ViewBindings.findChildViewById(view, i);
                        if (shadowedScrollView != null) {
                            i = R.id.stepsContainer;
                            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                            if (linearLayout2 != null) {
                                i = R.id.subtitle;
                                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                                if (textView3 != null) {
                                    i = R.id.title;
                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, i);
                                    if (textView4 != null) {
                                        return new OnfidoFragmentBulletedMessageBinding((RelativeLayout) view, linearLayout, textView, textView2, onfidoButton, shadowedScrollView, linearLayout2, textView3, textView4);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static OnfidoFragmentBulletedMessageBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static OnfidoFragmentBulletedMessageBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_fragment_bulleted_message, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
