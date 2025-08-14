package com.clevertap.android.sdk.inapp;

import android.graphics.Color;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import com.clevertap.android.sdk.inapp.CTInAppBaseFragment;

/* loaded from: classes5.dex */
public abstract class CTInAppBasePartialNativeFragment extends CTInAppBasePartialFragment implements View.OnTouchListener, View.OnLongClickListener {
    final GestureDetector gd = new GestureDetector(this.context, new GestureListener());
    View inAppView;

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View view) {
        return true;
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        private final int SWIPE_MIN_DISTANCE;
        private final int SWIPE_THRESHOLD_VELOCITY;

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent motionEvent) {
            return true;
        }

        private GestureListener() {
            this.SWIPE_MIN_DISTANCE = 120;
            this.SWIPE_THRESHOLD_VELOCITY = 200;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (motionEvent.getX() - motionEvent2.getX() > 120.0f && Math.abs(f) > 200.0f) {
                return remove(motionEvent, motionEvent2, false);
            }
            if (motionEvent2.getX() - motionEvent.getX() <= 120.0f || Math.abs(f) <= 200.0f) {
                return false;
            }
            return remove(motionEvent, motionEvent2, true);
        }

        private boolean remove(MotionEvent motionEvent, MotionEvent motionEvent2, boolean z) {
            TranslateAnimation translateAnimation;
            AnimationSet animationSet = new AnimationSet(true);
            if (z) {
                translateAnimation = new TranslateAnimation(0.0f, CTInAppBasePartialNativeFragment.this.getScaledPixels(50), 0.0f, 0.0f);
            } else {
                translateAnimation = new TranslateAnimation(0.0f, -CTInAppBasePartialNativeFragment.this.getScaledPixels(50), 0.0f, 0.0f);
            }
            animationSet.addAnimation(translateAnimation);
            animationSet.addAnimation(new AlphaAnimation(1.0f, 0.0f));
            animationSet.setDuration(300L);
            animationSet.setFillAfter(true);
            animationSet.setFillEnabled(true);
            animationSet.setAnimationListener(new Animation.AnimationListener() { // from class: com.clevertap.android.sdk.inapp.CTInAppBasePartialNativeFragment.GestureListener.1
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    CTInAppBasePartialNativeFragment.this.didDismiss(null);
                }
            });
            CTInAppBasePartialNativeFragment.this.inAppView.startAnimation(animationSet);
            return true;
        }
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return this.gd.onTouchEvent(motionEvent) || motionEvent.getAction() == 2;
    }

    void hideSecondaryButton(Button button, Button button2) {
        button2.setVisibility(8);
        button.setLayoutParams(new LinearLayout.LayoutParams(0, -1, 2.0f));
        button2.setLayoutParams(new LinearLayout.LayoutParams(0, -1, 0.0f));
    }

    void setupInAppButton(Button button, CTInAppNotificationButton cTInAppNotificationButton, int i) {
        if (cTInAppNotificationButton != null) {
            button.setTag(Integer.valueOf(i));
            button.setVisibility(0);
            button.setText(cTInAppNotificationButton.getText());
            button.setTextColor(Color.parseColor(cTInAppNotificationButton.getTextColor()));
            button.setBackgroundColor(Color.parseColor(cTInAppNotificationButton.getBackgroundColor()));
            button.setOnClickListener(new CTInAppBaseFragment.CTInAppNativeButtonClickListener());
            return;
        }
        button.setVisibility(8);
    }
}
