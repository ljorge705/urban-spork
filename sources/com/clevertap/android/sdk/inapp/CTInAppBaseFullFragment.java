package com.clevertap.android.sdk.inapp;

import android.os.Handler;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.clevertap.android.sdk.InAppNotificationActivity;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.R;
import com.clevertap.android.sdk.Utils;
import com.clevertap.android.sdk.customviews.CloseImageView;
import com.drew.metadata.mp4.media.Mp4VideoDirectory;

/* loaded from: classes5.dex */
public abstract class CTInAppBaseFullFragment extends CTInAppBaseFragment {
    @Override // com.clevertap.android.sdk.inapp.CTInAppBaseFragment
    void cleanup() {
    }

    void addCloseImageView(final RelativeLayout relativeLayout, final CloseImageView closeImageView) {
        new Handler().post(new Runnable() { // from class: com.clevertap.android.sdk.inapp.CTInAppBaseFullFragment.1
            @Override // java.lang.Runnable
            public void run() {
                int measuredWidth = closeImageView.getMeasuredWidth() / 2;
                closeImageView.setX(relativeLayout.getRight() - measuredWidth);
                closeImageView.setY(relativeLayout.getTop() - measuredWidth);
            }
        });
    }

    @Override // com.clevertap.android.sdk.inapp.CTInAppBaseFragment
    void generateListener() {
        if (this.context instanceof InAppNotificationActivity) {
            setListener((InAppListener) this.context);
        }
    }

    boolean isTablet() {
        if (Utils.isActivityDead(getActivity())) {
            return false;
        }
        try {
            return getResources().getBoolean(R.bool.ctIsTablet);
        } catch (Exception e) {
            Logger.d("Failed to decide whether device is a smart phone or tablet!");
            e.printStackTrace();
            return false;
        }
    }

    void redrawHalfInterstitialInApp(RelativeLayout relativeLayout, FrameLayout.LayoutParams layoutParams, CloseImageView closeImageView) {
        layoutParams.height = (int) (relativeLayout.getMeasuredWidth() * 1.3f);
        relativeLayout.setLayoutParams(layoutParams);
        addCloseImageView(relativeLayout, closeImageView);
    }

    void redrawHalfInterstitialMobileInAppOnTablet(RelativeLayout relativeLayout, FrameLayout.LayoutParams layoutParams, CloseImageView closeImageView) {
        layoutParams.setMargins(getScaledPixels(140), getScaledPixels(140), getScaledPixels(140), getScaledPixels(140));
        layoutParams.width = relativeLayout.getMeasuredWidth() - getScaledPixels(Mp4VideoDirectory.TAG_COMPRESSION_TYPE);
        layoutParams.height = (int) (layoutParams.width * 1.3f);
        relativeLayout.setLayoutParams(layoutParams);
        addCloseImageView(relativeLayout, closeImageView);
    }

    void redrawInterstitialInApp(RelativeLayout relativeLayout, FrameLayout.LayoutParams layoutParams, CloseImageView closeImageView) {
        layoutParams.height = (int) (relativeLayout.getMeasuredWidth() * 1.78f);
        relativeLayout.setLayoutParams(layoutParams);
        addCloseImageView(relativeLayout, closeImageView);
    }

    void redrawInterstitialMobileInAppOnTablet(RelativeLayout relativeLayout, FrameLayout.LayoutParams layoutParams, FrameLayout frameLayout, CloseImageView closeImageView) {
        int measuredWidth = (int) ((relativeLayout.getMeasuredWidth() - getScaledPixels(200)) * 1.78f);
        int measuredHeight = frameLayout.getMeasuredHeight() - getScaledPixels(280);
        if (measuredWidth > measuredHeight) {
            layoutParams.height = measuredHeight;
            layoutParams.width = (int) (measuredHeight / 1.78f);
        } else {
            layoutParams.height = measuredWidth;
            layoutParams.width = relativeLayout.getMeasuredWidth() - getScaledPixels(200);
        }
        layoutParams.setMargins(getScaledPixels(140), getScaledPixels(140), getScaledPixels(140), getScaledPixels(140));
        relativeLayout.setLayoutParams(layoutParams);
        addCloseImageView(relativeLayout, closeImageView);
    }

    void redrawInterstitialTabletInApp(RelativeLayout relativeLayout, FrameLayout.LayoutParams layoutParams, FrameLayout frameLayout, CloseImageView closeImageView) {
        int measuredWidth = (int) (relativeLayout.getMeasuredWidth() * 1.78f);
        int measuredHeight = frameLayout.getMeasuredHeight() - getScaledPixels(80);
        if (measuredWidth > measuredHeight) {
            layoutParams.height = measuredHeight;
            layoutParams.width = (int) (measuredHeight / 1.78f);
        } else {
            layoutParams.height = measuredWidth;
        }
        relativeLayout.setLayoutParams(layoutParams);
        addCloseImageView(relativeLayout, closeImageView);
    }

    void redrawLandscapeInterstitialInApp(RelativeLayout relativeLayout, FrameLayout.LayoutParams layoutParams, CloseImageView closeImageView) {
        layoutParams.width = (int) (relativeLayout.getMeasuredHeight() * 1.78f);
        layoutParams.gravity = 1;
        relativeLayout.setLayoutParams(layoutParams);
        addCloseImageView(relativeLayout, closeImageView);
    }

    void redrawLandscapeInterstitialMobileInAppOnTablet(RelativeLayout relativeLayout, FrameLayout.LayoutParams layoutParams, FrameLayout frameLayout, CloseImageView closeImageView) {
        int measuredHeight = (int) ((relativeLayout.getMeasuredHeight() - getScaledPixels(120)) * 1.78f);
        int measuredWidth = frameLayout.getMeasuredWidth() - getScaledPixels(280);
        if (measuredHeight > measuredWidth) {
            layoutParams.width = measuredWidth;
            layoutParams.height = (int) (measuredWidth / 1.78f);
        } else {
            layoutParams.width = measuredHeight;
            layoutParams.height = relativeLayout.getMeasuredHeight() - getScaledPixels(120);
        }
        layoutParams.setMargins(getScaledPixels(140), getScaledPixels(100), getScaledPixels(140), getScaledPixels(100));
        layoutParams.gravity = 17;
        relativeLayout.setLayoutParams(layoutParams);
        addCloseImageView(relativeLayout, closeImageView);
    }

    void redrawLandscapeInterstitialTabletInApp(RelativeLayout relativeLayout, FrameLayout.LayoutParams layoutParams, FrameLayout frameLayout, CloseImageView closeImageView) {
        int measuredHeight = (int) (relativeLayout.getMeasuredHeight() * 1.78f);
        int measuredWidth = frameLayout.getMeasuredWidth() - getScaledPixels(80);
        if (measuredHeight > measuredWidth) {
            layoutParams.width = measuredWidth;
            layoutParams.height = (int) (measuredWidth / 1.78f);
        } else {
            layoutParams.width = measuredHeight;
        }
        layoutParams.gravity = 17;
        relativeLayout.setLayoutParams(layoutParams);
        addCloseImageView(relativeLayout, closeImageView);
    }
}
