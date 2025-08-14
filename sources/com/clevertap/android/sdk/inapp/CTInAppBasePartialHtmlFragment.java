package com.clevertap.android.sdk.inapp;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.clevertap.android.sdk.CTWebInterface;
import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.Logger;

/* loaded from: classes5.dex */
public abstract class CTInAppBasePartialHtmlFragment extends CTInAppBasePartialFragment implements View.OnTouchListener, View.OnLongClickListener {
    private final GestureDetector gd = new GestureDetector(new GestureListener());
    private CTInAppWebView webView;

    abstract ViewGroup getLayout(View view);

    abstract View getView(LayoutInflater layoutInflater, ViewGroup viewGroup);

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View view) {
        return true;
    }

    private class InAppWebViewClient extends WebViewClient {
        InAppWebViewClient() {
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            CTInAppBasePartialHtmlFragment.this.openActionUrl(str);
            return true;
        }
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        private final int SWIPE_MIN_DISTANCE;
        private final int SWIPE_THRESHOLD_VELOCITY;

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
                translateAnimation = new TranslateAnimation(0.0f, CTInAppBasePartialHtmlFragment.this.getScaledPixels(50), 0.0f, 0.0f);
            } else {
                translateAnimation = new TranslateAnimation(0.0f, -CTInAppBasePartialHtmlFragment.this.getScaledPixels(50), 0.0f, 0.0f);
            }
            animationSet.addAnimation(translateAnimation);
            animationSet.addAnimation(new AlphaAnimation(1.0f, 0.0f));
            animationSet.setDuration(300L);
            animationSet.setFillAfter(true);
            animationSet.setFillEnabled(true);
            animationSet.setAnimationListener(new Animation.AnimationListener() { // from class: com.clevertap.android.sdk.inapp.CTInAppBasePartialHtmlFragment.GestureListener.1
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    CTInAppBasePartialHtmlFragment.this.didDismiss(null);
                }
            });
            CTInAppBasePartialHtmlFragment.this.webView.startAnimation(animationSet);
            return true;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return displayHTMLView(layoutInflater, viewGroup);
    }

    @Override // com.clevertap.android.sdk.inapp.CTInAppBaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        reDrawInApp();
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        reDrawInApp();
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return this.gd.onTouchEvent(motionEvent) || motionEvent.getAction() == 2;
    }

    private View displayHTMLView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        try {
            View view = getView(layoutInflater, viewGroup);
            ViewGroup layout = getLayout(view);
            this.webView = new CTInAppWebView(this.context, this.inAppNotification.getWidth(), this.inAppNotification.getHeight(), this.inAppNotification.getWidthPercentage(), this.inAppNotification.getHeightPercentage());
            this.webView.setWebViewClient(new InAppWebViewClient());
            this.webView.setOnTouchListener(this);
            this.webView.setOnLongClickListener(this);
            if (this.inAppNotification.isJsEnabled()) {
                this.webView.getSettings().setJavaScriptEnabled(true);
                this.webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);
                this.webView.getSettings().setAllowContentAccess(false);
                this.webView.getSettings().setAllowFileAccess(false);
                this.webView.getSettings().setAllowFileAccessFromFileURLs(false);
                this.webView.addJavascriptInterface(new CTWebInterface(CleverTapAPI.instanceWithConfig(getActivity(), this.config), this), Constants.CLEVERTAP_LOG_TAG);
            }
            if (layout != null) {
                layout.addView(this.webView);
            }
            return view;
        } catch (Throwable th) {
            this.config.getLogger().verbose(this.config.getAccountId(), "Fragment view not created", th);
            return null;
        }
    }

    private void reDrawInApp() {
        this.webView.updateDimension();
        int i = this.webView.dim.y;
        int i2 = this.webView.dim.x;
        float f = getResources().getDisplayMetrics().density;
        String strReplaceFirst = this.inAppNotification.getHtml().replaceFirst("<head>", "<head>" + ("<style>body{width:" + ((int) (i2 / f)) + "px; height: " + ((int) (i / f)) + "px; margin: 0; padding:0;}</style>"));
        Logger.v("Density appears to be " + f);
        this.webView.setInitialScale((int) (f * 100.0f));
        this.webView.loadDataWithBaseURL(null, strReplaceFirst, "text/html", "utf-8", null);
    }
}
