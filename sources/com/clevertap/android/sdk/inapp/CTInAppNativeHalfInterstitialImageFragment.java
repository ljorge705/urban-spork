package com.clevertap.android.sdk.inapp;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
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
public class CTInAppNativeHalfInterstitialImageFragment extends CTInAppBaseFullFragment {
    private RelativeLayout relativeLayout;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate;
        Bitmap bitmapCachedInAppImageV1;
        if (this.inAppNotification.isTablet() && isTablet()) {
            viewInflate = layoutInflater.inflate(R.layout.tab_inapp_half_interstitial_image, viewGroup, false);
        } else {
            viewInflate = layoutInflater.inflate(R.layout.inapp_half_interstitial_image, viewGroup, false);
        }
        FrameLayout frameLayout = (FrameLayout) viewInflate.findViewById(R.id.inapp_half_interstitial_image_frame_layout);
        final CloseImageView closeImageView = (CloseImageView) frameLayout.findViewById(199272);
        frameLayout.setBackground(new ColorDrawable(-1157627904));
        RelativeLayout relativeLayout = (RelativeLayout) frameLayout.findViewById(R.id.half_interstitial_image_relative_layout);
        this.relativeLayout = relativeLayout;
        relativeLayout.setBackgroundColor(Color.parseColor(this.inAppNotification.getBackgroundColor()));
        ImageView imageView = (ImageView) this.relativeLayout.findViewById(R.id.half_interstitial_image);
        int i = this.currentOrientation;
        if (i == 1) {
            this.relativeLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.clevertap.android.sdk.inapp.CTInAppNativeHalfInterstitialImageFragment.1
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) CTInAppNativeHalfInterstitialImageFragment.this.relativeLayout.getLayoutParams();
                    if (CTInAppNativeHalfInterstitialImageFragment.this.inAppNotification.isTablet() && CTInAppNativeHalfInterstitialImageFragment.this.isTablet()) {
                        CTInAppNativeHalfInterstitialImageFragment cTInAppNativeHalfInterstitialImageFragment = CTInAppNativeHalfInterstitialImageFragment.this;
                        cTInAppNativeHalfInterstitialImageFragment.redrawHalfInterstitialInApp(cTInAppNativeHalfInterstitialImageFragment.relativeLayout, layoutParams, closeImageView);
                    } else if (CTInAppNativeHalfInterstitialImageFragment.this.isTablet()) {
                        CTInAppNativeHalfInterstitialImageFragment cTInAppNativeHalfInterstitialImageFragment2 = CTInAppNativeHalfInterstitialImageFragment.this;
                        cTInAppNativeHalfInterstitialImageFragment2.redrawHalfInterstitialMobileInAppOnTablet(cTInAppNativeHalfInterstitialImageFragment2.relativeLayout, layoutParams, closeImageView);
                    } else {
                        CTInAppNativeHalfInterstitialImageFragment cTInAppNativeHalfInterstitialImageFragment3 = CTInAppNativeHalfInterstitialImageFragment.this;
                        cTInAppNativeHalfInterstitialImageFragment3.redrawHalfInterstitialInApp(cTInAppNativeHalfInterstitialImageFragment3.relativeLayout, layoutParams, closeImageView);
                    }
                    CTInAppNativeHalfInterstitialImageFragment.this.relativeLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
        } else if (i == 2) {
            this.relativeLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.clevertap.android.sdk.inapp.CTInAppNativeHalfInterstitialImageFragment.2
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) CTInAppNativeHalfInterstitialImageFragment.this.relativeLayout.getLayoutParams();
                    if (CTInAppNativeHalfInterstitialImageFragment.this.inAppNotification.isTablet() && CTInAppNativeHalfInterstitialImageFragment.this.isTablet()) {
                        layoutParams.width = (int) (CTInAppNativeHalfInterstitialImageFragment.this.relativeLayout.getMeasuredHeight() * 1.3f);
                        layoutParams.gravity = 17;
                        CTInAppNativeHalfInterstitialImageFragment.this.relativeLayout.setLayoutParams(layoutParams);
                        new Handler().post(new Runnable() { // from class: com.clevertap.android.sdk.inapp.CTInAppNativeHalfInterstitialImageFragment.2.3
                            @Override // java.lang.Runnable
                            public void run() {
                                int measuredWidth = closeImageView.getMeasuredWidth() / 2;
                                closeImageView.setX(CTInAppNativeHalfInterstitialImageFragment.this.relativeLayout.getRight() - measuredWidth);
                                closeImageView.setY(CTInAppNativeHalfInterstitialImageFragment.this.relativeLayout.getTop() - measuredWidth);
                            }
                        });
                    } else if (!CTInAppNativeHalfInterstitialImageFragment.this.isTablet()) {
                        layoutParams.width = (int) (CTInAppNativeHalfInterstitialImageFragment.this.relativeLayout.getMeasuredHeight() * 1.3f);
                        layoutParams.gravity = 1;
                        CTInAppNativeHalfInterstitialImageFragment.this.relativeLayout.setLayoutParams(layoutParams);
                        new Handler().post(new Runnable() { // from class: com.clevertap.android.sdk.inapp.CTInAppNativeHalfInterstitialImageFragment.2.2
                            @Override // java.lang.Runnable
                            public void run() {
                                int measuredWidth = closeImageView.getMeasuredWidth() / 2;
                                closeImageView.setX(CTInAppNativeHalfInterstitialImageFragment.this.relativeLayout.getRight() - measuredWidth);
                                closeImageView.setY(CTInAppNativeHalfInterstitialImageFragment.this.relativeLayout.getTop() - measuredWidth);
                            }
                        });
                    } else {
                        layoutParams.setMargins(CTInAppNativeHalfInterstitialImageFragment.this.getScaledPixels(140), CTInAppNativeHalfInterstitialImageFragment.this.getScaledPixels(100), CTInAppNativeHalfInterstitialImageFragment.this.getScaledPixels(140), CTInAppNativeHalfInterstitialImageFragment.this.getScaledPixels(100));
                        layoutParams.height = CTInAppNativeHalfInterstitialImageFragment.this.relativeLayout.getMeasuredHeight() - CTInAppNativeHalfInterstitialImageFragment.this.getScaledPixels(130);
                        layoutParams.width = (int) (layoutParams.height * 1.3f);
                        layoutParams.gravity = 17;
                        CTInAppNativeHalfInterstitialImageFragment.this.relativeLayout.setLayoutParams(layoutParams);
                        new Handler().post(new Runnable() { // from class: com.clevertap.android.sdk.inapp.CTInAppNativeHalfInterstitialImageFragment.2.1
                            @Override // java.lang.Runnable
                            public void run() {
                                int measuredWidth = closeImageView.getMeasuredWidth() / 2;
                                closeImageView.setX(CTInAppNativeHalfInterstitialImageFragment.this.relativeLayout.getRight() - measuredWidth);
                                closeImageView.setY(CTInAppNativeHalfInterstitialImageFragment.this.relativeLayout.getTop() - measuredWidth);
                            }
                        });
                    }
                    CTInAppNativeHalfInterstitialImageFragment.this.relativeLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
        }
        CTInAppNotificationMedia inAppMediaForOrientation = this.inAppNotification.getInAppMediaForOrientation(this.currentOrientation);
        if (inAppMediaForOrientation != null && (bitmapCachedInAppImageV1 = resourceProvider().cachedInAppImageV1(inAppMediaForOrientation.getMediaUrl())) != null) {
            imageView.setImageBitmap(bitmapCachedInAppImageV1);
            imageView.setTag(0);
            imageView.setOnClickListener(new CTInAppBaseFragment.CTInAppNativeButtonClickListener());
        }
        closeImageView.setOnClickListener(new View.OnClickListener() { // from class: com.clevertap.android.sdk.inapp.CTInAppNativeHalfInterstitialImageFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CTInAppNativeHalfInterstitialImageFragment.this.didDismiss(null);
                CTInAppNativeHalfInterstitialImageFragment.this.getActivity().finish();
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
