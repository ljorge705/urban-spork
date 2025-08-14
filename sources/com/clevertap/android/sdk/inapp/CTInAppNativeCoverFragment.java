package com.clevertap.android.sdk.inapp;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.clevertap.android.sdk.R;
import com.clevertap.android.sdk.customviews.CloseImageView;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class CTInAppNativeCoverFragment extends CTInAppBaseFullNativeFragment {
    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Bitmap bitmapCachedInAppImageV1;
        ArrayList arrayList = new ArrayList();
        View viewInflate = layoutInflater.inflate(R.layout.inapp_cover, viewGroup, false);
        FrameLayout frameLayout = (FrameLayout) viewInflate.findViewById(R.id.inapp_cover_frame_layout);
        RelativeLayout relativeLayout = (RelativeLayout) frameLayout.findViewById(R.id.cover_relative_layout);
        relativeLayout.setBackgroundColor(Color.parseColor(this.inAppNotification.getBackgroundColor()));
        LinearLayout linearLayout = (LinearLayout) relativeLayout.findViewById(R.id.cover_linear_layout);
        Button button = (Button) linearLayout.findViewById(R.id.cover_button1);
        arrayList.add(button);
        Button button2 = (Button) linearLayout.findViewById(R.id.cover_button2);
        arrayList.add(button2);
        ImageView imageView = (ImageView) relativeLayout.findViewById(R.id.backgroundImage);
        CTInAppNotificationMedia inAppMediaForOrientation = this.inAppNotification.getInAppMediaForOrientation(this.currentOrientation);
        if (inAppMediaForOrientation != null && (bitmapCachedInAppImageV1 = resourceProvider().cachedInAppImageV1(inAppMediaForOrientation.getMediaUrl())) != null) {
            imageView.setImageBitmap(bitmapCachedInAppImageV1);
            imageView.setTag(0);
        }
        TextView textView = (TextView) relativeLayout.findViewById(R.id.cover_title);
        textView.setText(this.inAppNotification.getTitle());
        textView.setTextColor(Color.parseColor(this.inAppNotification.getTitleColor()));
        TextView textView2 = (TextView) relativeLayout.findViewById(R.id.cover_message);
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
            for (int i = 0; i < buttons.size(); i++) {
                if (i < 2) {
                    setupInAppButton((Button) arrayList.get(i), buttons.get(i), i);
                }
            }
        }
        CloseImageView closeImageView = (CloseImageView) frameLayout.findViewById(199272);
        closeImageView.setOnClickListener(new View.OnClickListener() { // from class: com.clevertap.android.sdk.inapp.CTInAppNativeCoverFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CTInAppNativeCoverFragment.this.didDismiss(null);
                CTInAppNativeCoverFragment.this.getActivity().finish();
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
