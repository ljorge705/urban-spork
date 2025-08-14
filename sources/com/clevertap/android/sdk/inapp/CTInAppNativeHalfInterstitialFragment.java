package com.clevertap.android.sdk.inapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.clevertap.android.sdk.DeviceInfo;
import com.clevertap.android.sdk.R;
import com.clevertap.android.sdk.customviews.CloseImageView;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class CTInAppNativeHalfInterstitialFragment extends CTInAppBaseFullNativeFragment {
    private RelativeLayout relativeLayout;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(final LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate;
        Bitmap bitmapCachedInAppImageV1;
        ArrayList arrayList = new ArrayList();
        if ((this.inAppNotification.isTablet() && isTablet()) || (this.inAppNotification.isLocalInApp() && isTabletFromDeviceType(layoutInflater.getContext()))) {
            viewInflate = layoutInflater.inflate(R.layout.tab_inapp_half_interstitial, viewGroup, false);
        } else {
            viewInflate = layoutInflater.inflate(R.layout.inapp_half_interstitial, viewGroup, false);
        }
        final FrameLayout frameLayout = (FrameLayout) viewInflate.findViewById(R.id.inapp_half_interstitial_frame_layout);
        final CloseImageView closeImageView = (CloseImageView) frameLayout.findViewById(199272);
        RelativeLayout relativeLayout = (RelativeLayout) frameLayout.findViewById(R.id.half_interstitial_relative_layout);
        this.relativeLayout = relativeLayout;
        relativeLayout.setBackgroundColor(Color.parseColor(this.inAppNotification.getBackgroundColor()));
        int i = this.currentOrientation;
        if (i == 1) {
            this.relativeLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.clevertap.android.sdk.inapp.CTInAppNativeHalfInterstitialFragment.1
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) CTInAppNativeHalfInterstitialFragment.this.relativeLayout.getLayoutParams();
                    if ((CTInAppNativeHalfInterstitialFragment.this.inAppNotification.isTablet() && CTInAppNativeHalfInterstitialFragment.this.isTablet()) || (CTInAppNativeHalfInterstitialFragment.this.inAppNotification.isLocalInApp() && CTInAppNativeHalfInterstitialFragment.this.isTabletFromDeviceType(layoutInflater.getContext()))) {
                        CTInAppNativeHalfInterstitialFragment cTInAppNativeHalfInterstitialFragment = CTInAppNativeHalfInterstitialFragment.this;
                        cTInAppNativeHalfInterstitialFragment.redrawHalfInterstitialInApp(cTInAppNativeHalfInterstitialFragment.relativeLayout, layoutParams, closeImageView);
                    } else if (CTInAppNativeHalfInterstitialFragment.this.isTablet()) {
                        CTInAppNativeHalfInterstitialFragment cTInAppNativeHalfInterstitialFragment2 = CTInAppNativeHalfInterstitialFragment.this;
                        cTInAppNativeHalfInterstitialFragment2.redrawHalfInterstitialMobileInAppOnTablet(cTInAppNativeHalfInterstitialFragment2.relativeLayout, layoutParams, closeImageView);
                    } else {
                        CTInAppNativeHalfInterstitialFragment cTInAppNativeHalfInterstitialFragment3 = CTInAppNativeHalfInterstitialFragment.this;
                        cTInAppNativeHalfInterstitialFragment3.redrawHalfInterstitialInApp(cTInAppNativeHalfInterstitialFragment3.relativeLayout, layoutParams, closeImageView);
                    }
                    CTInAppNativeHalfInterstitialFragment.this.relativeLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
        } else if (i == 2) {
            this.relativeLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.clevertap.android.sdk.inapp.CTInAppNativeHalfInterstitialFragment.2
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    RelativeLayout relativeLayout2 = (RelativeLayout) frameLayout.findViewById(R.id.half_interstitial_relative_layout);
                    FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) relativeLayout2.getLayoutParams();
                    if (!CTInAppNativeHalfInterstitialFragment.this.inAppNotification.isTablet() || !CTInAppNativeHalfInterstitialFragment.this.isTablet()) {
                        if (CTInAppNativeHalfInterstitialFragment.this.isTablet()) {
                            layoutParams.setMargins(CTInAppNativeHalfInterstitialFragment.this.getScaledPixels(140), CTInAppNativeHalfInterstitialFragment.this.getScaledPixels(100), CTInAppNativeHalfInterstitialFragment.this.getScaledPixels(140), CTInAppNativeHalfInterstitialFragment.this.getScaledPixels(100));
                            layoutParams.height = relativeLayout2.getMeasuredHeight() - CTInAppNativeHalfInterstitialFragment.this.getScaledPixels(130);
                            layoutParams.width = (int) (layoutParams.height * 1.3f);
                            layoutParams.gravity = 17;
                            relativeLayout2.setLayoutParams(layoutParams);
                            new Handler().post(new Runnable() { // from class: com.clevertap.android.sdk.inapp.CTInAppNativeHalfInterstitialFragment.2.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    int measuredWidth = closeImageView.getMeasuredWidth() / 2;
                                    closeImageView.setX(CTInAppNativeHalfInterstitialFragment.this.relativeLayout.getRight() - measuredWidth);
                                    closeImageView.setY(CTInAppNativeHalfInterstitialFragment.this.relativeLayout.getTop() - measuredWidth);
                                }
                            });
                        } else {
                            layoutParams.width = (int) (relativeLayout2.getMeasuredHeight() * 1.3f);
                            layoutParams.gravity = 1;
                            relativeLayout2.setLayoutParams(layoutParams);
                            new Handler().post(new Runnable() { // from class: com.clevertap.android.sdk.inapp.CTInAppNativeHalfInterstitialFragment.2.2
                                @Override // java.lang.Runnable
                                public void run() {
                                    int measuredWidth = closeImageView.getMeasuredWidth() / 2;
                                    closeImageView.setX(CTInAppNativeHalfInterstitialFragment.this.relativeLayout.getRight() - measuredWidth);
                                    closeImageView.setY(CTInAppNativeHalfInterstitialFragment.this.relativeLayout.getTop() - measuredWidth);
                                }
                            });
                        }
                    } else {
                        layoutParams.width = (int) (relativeLayout2.getMeasuredHeight() * 1.3f);
                        layoutParams.gravity = 17;
                        relativeLayout2.setLayoutParams(layoutParams);
                        new Handler().post(new Runnable() { // from class: com.clevertap.android.sdk.inapp.CTInAppNativeHalfInterstitialFragment.2.3
                            @Override // java.lang.Runnable
                            public void run() {
                                int measuredWidth = closeImageView.getMeasuredWidth() / 2;
                                closeImageView.setX(CTInAppNativeHalfInterstitialFragment.this.relativeLayout.getRight() - measuredWidth);
                                closeImageView.setY(CTInAppNativeHalfInterstitialFragment.this.relativeLayout.getTop() - measuredWidth);
                            }
                        });
                    }
                    CTInAppNativeHalfInterstitialFragment.this.relativeLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
        }
        CTInAppNotificationMedia inAppMediaForOrientation = this.inAppNotification.getInAppMediaForOrientation(this.currentOrientation);
        if (inAppMediaForOrientation != null && (bitmapCachedInAppImageV1 = resourceProvider().cachedInAppImageV1(inAppMediaForOrientation.getMediaUrl())) != null) {
            ((ImageView) this.relativeLayout.findViewById(R.id.backgroundImage)).setImageBitmap(bitmapCachedInAppImageV1);
        }
        LinearLayout linearLayout = (LinearLayout) this.relativeLayout.findViewById(R.id.half_interstitial_linear_layout);
        Button button = (Button) linearLayout.findViewById(R.id.half_interstitial_button1);
        arrayList.add(button);
        Button button2 = (Button) linearLayout.findViewById(R.id.half_interstitial_button2);
        arrayList.add(button2);
        TextView textView = (TextView) this.relativeLayout.findViewById(R.id.half_interstitial_title);
        textView.setText(this.inAppNotification.getTitle());
        textView.setTextColor(Color.parseColor(this.inAppNotification.getTitleColor()));
        TextView textView2 = (TextView) this.relativeLayout.findViewById(R.id.half_interstitial_message);
        textView2.setText(this.inAppNotification.getMessage());
        textView2.setTextColor(Color.parseColor(this.inAppNotification.getMessageColor()));
        ArrayList<CTInAppNotificationButton> buttons = this.inAppNotification.getButtons();
        if (buttons.size() == 1) {
            if (this.currentOrientation == 2) {
                button.setVisibility(8);
            } else if (this.currentOrientation == 1) {
                button.setVisibility(4);
            }
            setupInAppButton(button2, buttons.get(0), 0);
        } else if (!buttons.isEmpty()) {
            for (int i2 = 0; i2 < buttons.size(); i2++) {
                if (i2 < 2) {
                    setupInAppButton((Button) arrayList.get(i2), buttons.get(i2), i2);
                }
            }
        }
        frameLayout.setBackground(new ColorDrawable(-1157627904));
        closeImageView.setOnClickListener(new View.OnClickListener() { // from class: com.clevertap.android.sdk.inapp.CTInAppNativeHalfInterstitialFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CTInAppNativeHalfInterstitialFragment.this.didDismiss(null);
                CTInAppNativeHalfInterstitialFragment.this.getActivity().finish();
            }
        });
        if (!this.inAppNotification.isHideCloseButton()) {
            closeImageView.setVisibility(8);
        } else {
            closeImageView.setVisibility(0);
        }
        return viewInflate;
    }

    boolean isTabletFromDeviceType(Context context) {
        return DeviceInfo.getDeviceType(context) == 2;
    }
}
