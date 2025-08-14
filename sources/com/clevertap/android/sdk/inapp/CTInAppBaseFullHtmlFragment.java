package com.clevertap.android.sdk.inapp;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import com.clevertap.android.sdk.CTWebInterface;
import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.R;
import com.clevertap.android.sdk.customviews.CloseImageView;

/* loaded from: classes5.dex */
public abstract class CTInAppBaseFullHtmlFragment extends CTInAppBaseFullFragment {
    protected CTInAppWebView webView;

    private class InAppWebViewClient extends WebViewClient {
        InAppWebViewClient() {
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            CTInAppBaseFullHtmlFragment.this.openActionUrl(str);
            return true;
        }
    }

    @Override // com.clevertap.android.sdk.inapp.CTInAppBaseFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
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

    protected RelativeLayout.LayoutParams getLayoutParamsForCloseButton() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(2, this.webView.getId());
        layoutParams.addRule(1, this.webView.getId());
        int i = -(getScaledPixels(40) / 2);
        layoutParams.setMargins(i, 0, 0, i);
        return layoutParams;
    }

    private View displayHTMLView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        try {
            View viewInflate = layoutInflater.inflate(R.layout.inapp_html_full, viewGroup, false);
            RelativeLayout relativeLayout = (RelativeLayout) viewInflate.findViewById(R.id.inapp_html_full_relative_layout);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            layoutParams.addRule(13);
            initWebViewLayoutParams(layoutParams);
            this.webView = new CTInAppWebView(this.context, this.inAppNotification.getWidth(), this.inAppNotification.getHeight(), this.inAppNotification.getWidthPercentage(), this.inAppNotification.getHeightPercentage());
            this.webView.setWebViewClient(new InAppWebViewClient());
            if (this.inAppNotification.isJsEnabled()) {
                this.webView.getSettings().setJavaScriptEnabled(true);
                this.webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);
                this.webView.getSettings().setAllowContentAccess(false);
                this.webView.getSettings().setAllowFileAccess(false);
                this.webView.getSettings().setAllowFileAccessFromFileURLs(false);
                this.webView.addJavascriptInterface(new CTWebInterface(CleverTapAPI.instanceWithConfig(getActivity(), this.config), this), Constants.CLEVERTAP_LOG_TAG);
            }
            if (isDarkenEnabled()) {
                relativeLayout.setBackground(new ColorDrawable(-1157627904));
            } else {
                relativeLayout.setBackground(new ColorDrawable(0));
            }
            relativeLayout.addView(this.webView, layoutParams);
            if (isCloseButtonEnabled()) {
                this.closeImageView = new CloseImageView(this.context);
                RelativeLayout.LayoutParams layoutParamsForCloseButton = getLayoutParamsForCloseButton();
                this.closeImageView.setOnClickListener(new View.OnClickListener() { // from class: com.clevertap.android.sdk.inapp.CTInAppBaseFullHtmlFragment.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        CTInAppBaseFullHtmlFragment.this.didDismiss(null);
                    }
                });
                relativeLayout.addView(this.closeImageView, layoutParamsForCloseButton);
            }
            return viewInflate;
        } catch (Throwable th) {
            this.config.getLogger().verbose(this.config.getAccountId(), "Fragment view not created", th);
            return null;
        }
    }

    private void initWebViewLayoutParams(RelativeLayout.LayoutParams layoutParams) {
        char position = this.inAppNotification.getPosition();
        if (position == 'b') {
            layoutParams.addRule(12);
        } else if (position == 'c') {
            layoutParams.addRule(13);
        } else if (position == 'l') {
            layoutParams.addRule(9);
        } else if (position == 'r') {
            layoutParams.addRule(11);
        } else if (position == 't') {
            layoutParams.addRule(10);
        }
        layoutParams.setMargins(0, 0, 0, 0);
    }

    private boolean isCloseButtonEnabled() {
        return this.inAppNotification.isShowClose();
    }

    private boolean isDarkenEnabled() {
        return this.inAppNotification.isDarkenScreen();
    }

    private void reDrawInApp() {
        this.webView.updateDimension();
        if (this.inAppNotification.getCustomInAppUrl().isEmpty()) {
            int i = this.webView.dim.y;
            int i2 = this.webView.dim.x;
            float f = getResources().getDisplayMetrics().density;
            String strReplaceFirst = this.inAppNotification.getHtml().replaceFirst("<head>", "<head>" + ("<style>body{width:" + ((int) (i2 / f)) + "px; height: " + ((int) (i / f)) + "px; margin: 0; padding:0;}</style>"));
            Logger.v("Density appears to be " + f);
            this.webView.setInitialScale((int) (f * 100.0f));
            this.webView.loadDataWithBaseURL(null, strReplaceFirst, "text/html", "utf-8", null);
            return;
        }
        String customInAppUrl = this.inAppNotification.getCustomInAppUrl();
        this.webView.setWebViewClient(new WebViewClient());
        this.webView.loadUrl(customInAppUrl);
    }
}
