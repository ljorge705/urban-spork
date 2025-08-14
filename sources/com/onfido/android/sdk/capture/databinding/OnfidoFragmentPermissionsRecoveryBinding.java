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
import com.onfido.android.sdk.capture.ui.BulletStepView;
import com.onfido.android.sdk.capture.ui.widget.OnfidoButton;
import com.onfido.android.sdk.capture.ui.widget.ShadowedScrollView;

/* loaded from: classes2.dex */
public final class OnfidoFragmentPermissionsRecoveryBinding implements ViewBinding {
    public final LinearLayout descriptionContainer;
    public final BulletStepView recoveryBulletMessage;
    public final BulletStepView recoveryBulletMessage2;
    public final TextView recoverySubtitle;
    private final RelativeLayout rootView;
    public final ShadowedScrollView scrollView;
    public final OnfidoButton settingsButton;
    public final TextView title;

    private OnfidoFragmentPermissionsRecoveryBinding(RelativeLayout relativeLayout, LinearLayout linearLayout, BulletStepView bulletStepView, BulletStepView bulletStepView2, TextView textView, ShadowedScrollView shadowedScrollView, OnfidoButton onfidoButton, TextView textView2) {
        this.rootView = relativeLayout;
        this.descriptionContainer = linearLayout;
        this.recoveryBulletMessage = bulletStepView;
        this.recoveryBulletMessage2 = bulletStepView2;
        this.recoverySubtitle = textView;
        this.scrollView = shadowedScrollView;
        this.settingsButton = onfidoButton;
        this.title = textView2;
    }

    public static OnfidoFragmentPermissionsRecoveryBinding bind(View view) {
        int i = R.id.descriptionContainer;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
        if (linearLayout != null) {
            i = R.id.recoveryBulletMessage;
            BulletStepView bulletStepView = (BulletStepView) ViewBindings.findChildViewById(view, i);
            if (bulletStepView != null) {
                i = R.id.recoveryBulletMessage2;
                BulletStepView bulletStepView2 = (BulletStepView) ViewBindings.findChildViewById(view, i);
                if (bulletStepView2 != null) {
                    i = R.id.recoverySubtitle;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                    if (textView != null) {
                        i = R.id.scrollView;
                        ShadowedScrollView shadowedScrollView = (ShadowedScrollView) ViewBindings.findChildViewById(view, i);
                        if (shadowedScrollView != null) {
                            i = R.id.settingsButton;
                            OnfidoButton onfidoButton = (OnfidoButton) ViewBindings.findChildViewById(view, i);
                            if (onfidoButton != null) {
                                i = R.id.title;
                                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                                if (textView2 != null) {
                                    return new OnfidoFragmentPermissionsRecoveryBinding((RelativeLayout) view, linearLayout, bulletStepView, bulletStepView2, textView, shadowedScrollView, onfidoButton, textView2);
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static OnfidoFragmentPermissionsRecoveryBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static OnfidoFragmentPermissionsRecoveryBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_fragment_permissions_recovery, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
