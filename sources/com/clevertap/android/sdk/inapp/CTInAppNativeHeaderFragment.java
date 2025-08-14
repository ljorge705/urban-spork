package com.clevertap.android.sdk.inapp;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.clevertap.android.sdk.R;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class CTInAppNativeHeaderFragment extends CTInAppBasePartialNativeFragment {
    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Bitmap bitmapCachedInAppImageV1;
        ArrayList arrayList = new ArrayList();
        this.inAppView = layoutInflater.inflate(R.layout.inapp_header, viewGroup, false);
        RelativeLayout relativeLayout = (RelativeLayout) ((FrameLayout) this.inAppView.findViewById(R.id.header_frame_layout)).findViewById(R.id.header_relative_layout);
        relativeLayout.setBackgroundColor(Color.parseColor(this.inAppNotification.getBackgroundColor()));
        LinearLayout linearLayout = (LinearLayout) relativeLayout.findViewById(R.id.header_linear_layout_1);
        LinearLayout linearLayout2 = (LinearLayout) relativeLayout.findViewById(R.id.header_linear_layout_2);
        LinearLayout linearLayout3 = (LinearLayout) relativeLayout.findViewById(R.id.header_linear_layout_3);
        Button button = (Button) linearLayout3.findViewById(R.id.header_button_1);
        arrayList.add(button);
        Button button2 = (Button) linearLayout3.findViewById(R.id.header_button_2);
        arrayList.add(button2);
        ImageView imageView = (ImageView) linearLayout.findViewById(R.id.header_icon);
        if (!this.inAppNotification.getMediaList().isEmpty() && (bitmapCachedInAppImageV1 = resourceProvider().cachedInAppImageV1(this.inAppNotification.getMediaList().get(0).getMediaUrl())) != null) {
            imageView.setImageBitmap(bitmapCachedInAppImageV1);
        } else {
            imageView.setVisibility(8);
        }
        TextView textView = (TextView) linearLayout2.findViewById(R.id.header_title);
        textView.setText(this.inAppNotification.getTitle());
        textView.setTextColor(Color.parseColor(this.inAppNotification.getTitleColor()));
        TextView textView2 = (TextView) linearLayout2.findViewById(R.id.header_message);
        textView2.setText(this.inAppNotification.getMessage());
        textView2.setTextColor(Color.parseColor(this.inAppNotification.getMessageColor()));
        ArrayList<CTInAppNotificationButton> buttons = this.inAppNotification.getButtons();
        if (buttons != null && !buttons.isEmpty()) {
            for (int i = 0; i < buttons.size(); i++) {
                if (i < 2) {
                    setupInAppButton((Button) arrayList.get(i), buttons.get(i), i);
                }
            }
        }
        if (this.inAppNotification.getButtonCount() == 1) {
            hideSecondaryButton(button, button2);
        }
        this.inAppView.setOnTouchListener(new View.OnTouchListener() { // from class: com.clevertap.android.sdk.inapp.CTInAppNativeHeaderFragment.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                CTInAppNativeHeaderFragment.this.gd.onTouchEvent(motionEvent);
                return true;
            }
        });
        return this.inAppView;
    }
}
