package com.clevertap.android.sdk.inapp;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.clevertap.android.sdk.R;
import com.clevertap.android.sdk.customviews.CloseImageView;
import com.clevertap.android.sdk.inapp.CTInAppBaseFragment;

/* loaded from: classes5.dex */
public class CTInAppNativeInterstitialImageFragment extends CTInAppBaseFullFragment {
    private RelativeLayout relativeLayout;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate;
        Bitmap bitmapCachedInAppImageV1;
        if (this.inAppNotification.isTablet() && isTablet()) {
            viewInflate = layoutInflater.inflate(R.layout.tab_inapp_interstitial_image, viewGroup, false);
        } else {
            viewInflate = layoutInflater.inflate(R.layout.inapp_interstitial_image, viewGroup, false);
        }
        final FrameLayout frameLayout = (FrameLayout) viewInflate.findViewById(R.id.inapp_interstitial_image_frame_layout);
        frameLayout.setBackground(new ColorDrawable(-1157627904));
        final CloseImageView closeImageView = (CloseImageView) frameLayout.findViewById(199272);
        RelativeLayout relativeLayout = (RelativeLayout) frameLayout.findViewById(R.id.interstitial_image_relative_layout);
        this.relativeLayout = relativeLayout;
        relativeLayout.setBackgroundColor(Color.parseColor(this.inAppNotification.getBackgroundColor()));
        ImageView imageView = (ImageView) this.relativeLayout.findViewById(R.id.interstitial_image);
        int i = this.currentOrientation;
        if (i == 1) {
            this.relativeLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.clevertap.android.sdk.inapp.CTInAppNativeInterstitialImageFragment.1
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) CTInAppNativeInterstitialImageFragment.this.relativeLayout.getLayoutParams();
                    if (CTInAppNativeInterstitialImageFragment.this.inAppNotification.isTablet() && CTInAppNativeInterstitialImageFragment.this.isTablet()) {
                        CTInAppNativeInterstitialImageFragment cTInAppNativeInterstitialImageFragment = CTInAppNativeInterstitialImageFragment.this;
                        cTInAppNativeInterstitialImageFragment.redrawInterstitialTabletInApp(cTInAppNativeInterstitialImageFragment.relativeLayout, layoutParams, frameLayout, closeImageView);
                    } else if (CTInAppNativeInterstitialImageFragment.this.isTablet()) {
                        CTInAppNativeInterstitialImageFragment cTInAppNativeInterstitialImageFragment2 = CTInAppNativeInterstitialImageFragment.this;
                        cTInAppNativeInterstitialImageFragment2.redrawInterstitialMobileInAppOnTablet(cTInAppNativeInterstitialImageFragment2.relativeLayout, layoutParams, frameLayout, closeImageView);
                    } else {
                        CTInAppNativeInterstitialImageFragment cTInAppNativeInterstitialImageFragment3 = CTInAppNativeInterstitialImageFragment.this;
                        cTInAppNativeInterstitialImageFragment3.redrawInterstitialInApp(cTInAppNativeInterstitialImageFragment3.relativeLayout, layoutParams, closeImageView);
                    }
                    CTInAppNativeInterstitialImageFragment.this.relativeLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
        } else if (i == 2) {
            this.relativeLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.clevertap.android.sdk.inapp.CTInAppNativeInterstitialImageFragment.2
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) CTInAppNativeInterstitialImageFragment.this.relativeLayout.getLayoutParams();
                    if (CTInAppNativeInterstitialImageFragment.this.inAppNotification.isTablet() && CTInAppNativeInterstitialImageFragment.this.isTablet()) {
                        CTInAppNativeInterstitialImageFragment cTInAppNativeInterstitialImageFragment = CTInAppNativeInterstitialImageFragment.this;
                        cTInAppNativeInterstitialImageFragment.redrawLandscapeInterstitialTabletInApp(cTInAppNativeInterstitialImageFragment.relativeLayout, layoutParams, frameLayout, closeImageView);
                    } else if (CTInAppNativeInterstitialImageFragment.this.isTablet()) {
                        CTInAppNativeInterstitialImageFragment cTInAppNativeInterstitialImageFragment2 = CTInAppNativeInterstitialImageFragment.this;
                        cTInAppNativeInterstitialImageFragment2.redrawLandscapeInterstitialMobileInAppOnTablet(cTInAppNativeInterstitialImageFragment2.relativeLayout, layoutParams, frameLayout, closeImageView);
                    } else {
                        CTInAppNativeInterstitialImageFragment cTInAppNativeInterstitialImageFragment3 = CTInAppNativeInterstitialImageFragment.this;
                        cTInAppNativeInterstitialImageFragment3.redrawLandscapeInterstitialInApp(cTInAppNativeInterstitialImageFragment3.relativeLayout, layoutParams, closeImageView);
                    }
                    CTInAppNativeInterstitialImageFragment.this.relativeLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
        }
        CTInAppNotificationMedia inAppMediaForOrientation = this.inAppNotification.getInAppMediaForOrientation(this.currentOrientation);
        if (inAppMediaForOrientation != null && (bitmapCachedInAppImageV1 = resourceProvider().cachedInAppImageV1(inAppMediaForOrientation.getMediaUrl())) != null) {
            imageView.setImageBitmap(bitmapCachedInAppImageV1);
            imageView.setTag(0);
            imageView.setOnClickListener(new CTInAppBaseFragment.CTInAppNativeButtonClickListener());
        }
        closeImageView.setOnClickListener(new View.OnClickListener() { // from class: com.clevertap.android.sdk.inapp.CTInAppNativeInterstitialImageFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CTInAppNativeInterstitialImageFragment.this.didDismiss(null);
                CTInAppNativeInterstitialImageFragment.this.getActivity().finish();
            }
        });
        if (!this.inAppNotification.isHideCloseButton()) {
            closeImageView.setVisibility(8);
        } else {
            closeImageView.setVisibility(0);
        }
        return viewInflate;
    }
}
