package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.ui.widget.OnfidoButton;
import com.onfido.android.sdk.capture.ui.widget.ShadowedScrollView;

/* loaded from: classes2.dex */
public final class OnfidoFragmentPermissionsRequestBinding implements ViewBinding {
    public final LinearLayout descriptionContainer;
    public final OnfidoButton enable;
    public final ImageView permissionRequestIcon;
    public final TextView requestDenyExplanation;
    public final TextView requestSubtitle;
    private final RelativeLayout rootView;
    public final ShadowedScrollView scrollView;
    public final TextView title;

    private OnfidoFragmentPermissionsRequestBinding(RelativeLayout relativeLayout, LinearLayout linearLayout, OnfidoButton onfidoButton, ImageView imageView, TextView textView, TextView textView2, ShadowedScrollView shadowedScrollView, TextView textView3) {
        this.rootView = relativeLayout;
        this.descriptionContainer = linearLayout;
        this.enable = onfidoButton;
        this.permissionRequestIcon = imageView;
        this.requestDenyExplanation = textView;
        this.requestSubtitle = textView2;
        this.scrollView = shadowedScrollView;
        this.title = textView3;
    }

    public static OnfidoFragmentPermissionsRequestBinding bind(View view) {
        int i = R.id.descriptionContainer;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
        if (linearLayout != null) {
            i = R.id.enable;
            OnfidoButton onfidoButton = (OnfidoButton) ViewBindings.findChildViewById(view, i);
            if (onfidoButton != null) {
                i = R.id.permissionRequestIcon;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
                if (imageView != null) {
                    i = R.id.requestDenyExplanation;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                    if (textView != null) {
                        i = R.id.requestSubtitle;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                        if (textView2 != null) {
                            i = R.id.scrollView;
                            ShadowedScrollView shadowedScrollView = (ShadowedScrollView) ViewBindings.findChildViewById(view, i);
                            if (shadowedScrollView != null) {
                                i = R.id.title;
                                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                                if (textView3 != null) {
                                    return new OnfidoFragmentPermissionsRequestBinding((RelativeLayout) view, linearLayout, onfidoButton, imageView, textView, textView2, shadowedScrollView, textView3);
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static OnfidoFragmentPermissionsRequestBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static OnfidoFragmentPermissionsRequestBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_fragment_permissions_request, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
