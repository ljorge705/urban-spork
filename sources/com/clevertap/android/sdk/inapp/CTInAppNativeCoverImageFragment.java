package com.clevertap.android.sdk.inapp;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.clevertap.android.sdk.R;
import com.clevertap.android.sdk.customviews.CloseImageView;
import com.clevertap.android.sdk.inapp.CTInAppBaseFragment;

/* loaded from: classes5.dex */
public class CTInAppNativeCoverImageFragment extends CTInAppBaseFullFragment {
    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Bitmap bitmapCachedInAppImageV1;
        View viewInflate = layoutInflater.inflate(R.layout.inapp_cover_image, viewGroup, false);
        FrameLayout frameLayout = (FrameLayout) viewInflate.findViewById(R.id.inapp_cover_image_frame_layout);
        frameLayout.setBackgroundColor(Color.parseColor(this.inAppNotification.getBackgroundColor()));
        ImageView imageView = (ImageView) ((RelativeLayout) frameLayout.findViewById(R.id.cover_image_relative_layout)).findViewById(R.id.cover_image);
        CTInAppNotificationMedia inAppMediaForOrientation = this.inAppNotification.getInAppMediaForOrientation(this.currentOrientation);
        if (inAppMediaForOrientation != null && (bitmapCachedInAppImageV1 = resourceProvider().cachedInAppImageV1(inAppMediaForOrientation.getMediaUrl())) != null) {
            imageView.setImageBitmap(bitmapCachedInAppImageV1);
            imageView.setTag(0);
            imageView.setOnClickListener(new CTInAppBaseFragment.CTInAppNativeButtonClickListener());
        }
        CloseImageView closeImageView = (CloseImageView) frameLayout.findViewById(199272);
        closeImageView.setOnClickListener(new View.OnClickListener() { // from class: com.clevertap.android.sdk.inapp.CTInAppNativeCoverImageFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CTInAppNativeCoverImageFragment.this.didDismiss(null);
                CTInAppNativeCoverImageFragment.this.getActivity().finish();
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
