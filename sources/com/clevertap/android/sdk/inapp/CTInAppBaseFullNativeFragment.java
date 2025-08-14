package com.clevertap.android.sdk.inapp;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.Button;
import com.clevertap.android.sdk.inapp.CTInAppBaseFragment;

/* loaded from: classes5.dex */
public abstract class CTInAppBaseFullNativeFragment extends CTInAppBaseFullFragment {
    int getDPI() {
        WindowManager windowManager = (WindowManager) this.context.getSystemService("window");
        if (windowManager == null) {
            return 0;
        }
        if (Build.VERSION.SDK_INT >= 30) {
            return this.context.getResources().getConfiguration().densityDpi;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.densityDpi;
    }

    void setupInAppButton(Button button, CTInAppNotificationButton cTInAppNotificationButton, int i) {
        ShapeDrawable shapeDrawable;
        if (cTInAppNotificationButton != null) {
            button.setVisibility(0);
            button.setTag(Integer.valueOf(i));
            button.setText(cTInAppNotificationButton.getText());
            button.setTextColor(Color.parseColor(cTInAppNotificationButton.getTextColor()));
            button.setOnClickListener(new CTInAppBaseFragment.CTInAppNativeButtonClickListener());
            ShapeDrawable shapeDrawable2 = null;
            if (cTInAppNotificationButton.getBorderRadius().isEmpty()) {
                shapeDrawable = null;
            } else {
                float f = Float.parseFloat(cTInAppNotificationButton.getBorderRadius()) * (480.0f / getDPI()) * 2.0f;
                shapeDrawable = new ShapeDrawable(new RoundRectShape(new float[]{f, f, f, f, f, f, f, f}, null, new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f}));
                shapeDrawable.getPaint().setColor(Color.parseColor(cTInAppNotificationButton.getBackgroundColor()));
                shapeDrawable.getPaint().setStyle(Paint.Style.FILL);
                shapeDrawable.getPaint().setAntiAlias(true);
                shapeDrawable2 = new ShapeDrawable(new RoundRectShape(new float[]{f, f, f, f, f, f, f, f}, null, new float[]{f, f, f, f, f, f, f, f}));
            }
            if (!cTInAppNotificationButton.getBorderColor().isEmpty() && shapeDrawable2 != null) {
                shapeDrawable2.getPaint().setColor(Color.parseColor(cTInAppNotificationButton.getBorderColor()));
                shapeDrawable2.setPadding(1, 1, 1, 1);
                shapeDrawable2.getPaint().setStyle(Paint.Style.FILL);
            }
            if (shapeDrawable != null) {
                button.setBackground(new LayerDrawable(new Drawable[]{shapeDrawable2, shapeDrawable}));
                return;
            }
            return;
        }
        button.setVisibility(8);
    }
}
