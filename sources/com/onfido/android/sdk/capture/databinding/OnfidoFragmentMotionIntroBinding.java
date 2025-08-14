package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.headturn.OnfidoAnimWebView;
import com.onfido.android.sdk.capture.ui.widget.OnfidoButton;

/* loaded from: classes2.dex */
public final class OnfidoFragmentMotionIntroBinding implements ViewBinding {
    public final OnfidoAnimWebView animWebView;
    public final TextView introDisclaimerView;
    public final TextView motionIntroGuidanceBulletOne;
    public final TextView motionIntroGuidanceBulletTwo;
    public final OnfidoButton motionIntroStartRecordingButton;
    public final TextView motionIntroSubtitle;
    public final TextView motionIntroTitle;
    private final LinearLayout rootView;

    private OnfidoFragmentMotionIntroBinding(LinearLayout linearLayout, OnfidoAnimWebView onfidoAnimWebView, TextView textView, TextView textView2, TextView textView3, OnfidoButton onfidoButton, TextView textView4, TextView textView5) {
        this.rootView = linearLayout;
        this.animWebView = onfidoAnimWebView;
        this.introDisclaimerView = textView;
        this.motionIntroGuidanceBulletOne = textView2;
        this.motionIntroGuidanceBulletTwo = textView3;
        this.motionIntroStartRecordingButton = onfidoButton;
        this.motionIntroSubtitle = textView4;
        this.motionIntroTitle = textView5;
    }

    public static OnfidoFragmentMotionIntroBinding bind(View view) {
        int i = R.id.animWebView;
        OnfidoAnimWebView onfidoAnimWebView = (OnfidoAnimWebView) ViewBindings.findChildViewById(view, i);
        if (onfidoAnimWebView != null) {
            i = R.id.introDisclaimerView;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
            if (textView != null) {
                i = R.id.motion_intro_guidance_bullet_one;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView2 != null) {
                    i = R.id.motion_intro_guidance_bullet_two;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                    if (textView3 != null) {
                        i = R.id.motionIntroStartRecordingButton;
                        OnfidoButton onfidoButton = (OnfidoButton) ViewBindings.findChildViewById(view, i);
                        if (onfidoButton != null) {
                            i = R.id.motion_intro_subtitle;
                            TextView textView4 = (TextView) ViewBindings.findChildViewById(view, i);
                            if (textView4 != null) {
                                i = R.id.motion_intro_title;
                                TextView textView5 = (TextView) ViewBindings.findChildViewById(view, i);
                                if (textView5 != null) {
                                    return new OnfidoFragmentMotionIntroBinding((LinearLayout) view, onfidoAnimWebView, textView, textView2, textView3, onfidoButton, textView4, textView5);
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static OnfidoFragmentMotionIntroBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static OnfidoFragmentMotionIntroBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_fragment_motion_intro, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
