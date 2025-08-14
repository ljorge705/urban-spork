package com.clevertap.android.sdk.inapp;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
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
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentActivity;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.R;
import com.clevertap.android.sdk.customviews.CloseImageView;
import com.clevertap.android.sdk.gif.GifImageView;
import com.clevertap.android.sdk.video.InAppVideoPlayerHandle;
import com.clevertap.android.sdk.video.VideoLibChecker;
import com.clevertap.android.sdk.video.VideoLibraryIntegrated;
import com.clevertap.android.sdk.video.inapps.ExoplayerHandle;
import com.clevertap.android.sdk.video.inapps.Media3Handle;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class CTInAppNativeInterstitialFragment extends CTInAppBaseFullNativeFragment {
    private CloseImageView closeImageView;
    private boolean exoPlayerFullscreen = false;
    private Dialog fullScreenDialog;
    private ImageView fullScreenIcon;
    private GifImageView gifImageView;
    private InAppVideoPlayerHandle handle;
    private ViewGroup.LayoutParams imageViewLayoutParams;
    private RelativeLayout relativeLayout;
    private FrameLayout videoFrameInDialog;
    private FrameLayout videoFrameLayout;

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (VideoLibChecker.mediaLibType == VideoLibraryIntegrated.MEDIA3) {
            this.handle = new Media3Handle();
        } else {
            this.handle = new ExoplayerHandle();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate;
        if (this.inAppNotification.isTablet() && isTablet()) {
            viewInflate = layoutInflater.inflate(R.layout.tab_inapp_interstitial, viewGroup, false);
        } else {
            viewInflate = layoutInflater.inflate(R.layout.inapp_interstitial, viewGroup, false);
        }
        FrameLayout frameLayout = (FrameLayout) viewInflate.findViewById(R.id.inapp_interstitial_frame_layout);
        this.closeImageView = (CloseImageView) frameLayout.findViewById(199272);
        RelativeLayout relativeLayout = (RelativeLayout) frameLayout.findViewById(R.id.interstitial_relative_layout);
        this.relativeLayout = relativeLayout;
        this.videoFrameLayout = (FrameLayout) relativeLayout.findViewById(R.id.video_frame);
        this.relativeLayout.setBackgroundColor(Color.parseColor(this.inAppNotification.getBackgroundColor()));
        frameLayout.setBackground(new ColorDrawable(-1157627904));
        resizeContainer(frameLayout, this.closeImageView);
        setMediaForInApp();
        setTitleAndMessage();
        setButtons();
        handleCloseButton();
        return viewInflate;
    }

    private void handleCloseButton() {
        if (!this.inAppNotification.isHideCloseButton()) {
            this.closeImageView.setOnClickListener(null);
            this.closeImageView.setVisibility(8);
        } else {
            this.closeImageView.setVisibility(0);
            this.closeImageView.setOnClickListener(new View.OnClickListener() { // from class: com.clevertap.android.sdk.inapp.CTInAppNativeInterstitialFragment$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.m4790xa70c304c(view);
                }
            });
        }
    }

    /* renamed from: lambda$handleCloseButton$0$com-clevertap-android-sdk-inapp-CTInAppNativeInterstitialFragment, reason: not valid java name */
    /* synthetic */ void m4790xa70c304c(View view) {
        didDismiss(null);
        GifImageView gifImageView = this.gifImageView;
        if (gifImageView != null) {
            gifImageView.clear();
        }
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.finish();
        }
    }

    private void setButtons() {
        ArrayList arrayList = new ArrayList();
        LinearLayout linearLayout = (LinearLayout) this.relativeLayout.findViewById(R.id.interstitial_linear_layout);
        Button button = (Button) linearLayout.findViewById(R.id.interstitial_button1);
        arrayList.add(button);
        Button button2 = (Button) linearLayout.findViewById(R.id.interstitial_button2);
        arrayList.add(button2);
        ArrayList<CTInAppNotificationButton> buttons = this.inAppNotification.getButtons();
        if (buttons.size() == 1) {
            if (this.currentOrientation == 2) {
                button.setVisibility(8);
            } else if (this.currentOrientation == 1) {
                button.setVisibility(4);
            }
            setupInAppButton(button2, buttons.get(0), 0);
            return;
        }
        if (buttons.isEmpty()) {
            return;
        }
        for (int i = 0; i < buttons.size(); i++) {
            if (i < 2) {
                setupInAppButton((Button) arrayList.get(i), buttons.get(i), i);
            }
        }
    }

    private void setTitleAndMessage() {
        TextView textView = (TextView) this.relativeLayout.findViewById(R.id.interstitial_title);
        textView.setText(this.inAppNotification.getTitle());
        textView.setTextColor(Color.parseColor(this.inAppNotification.getTitleColor()));
        TextView textView2 = (TextView) this.relativeLayout.findViewById(R.id.interstitial_message);
        textView2.setText(this.inAppNotification.getMessage());
        textView2.setTextColor(Color.parseColor(this.inAppNotification.getMessageColor()));
    }

    private void setMediaForInApp() {
        if (this.inAppNotification.getMediaList().isEmpty()) {
            return;
        }
        CTInAppNotificationMedia cTInAppNotificationMedia = this.inAppNotification.getMediaList().get(0);
        if (cTInAppNotificationMedia.isImage()) {
            Bitmap bitmapCachedInAppImageV1 = resourceProvider().cachedInAppImageV1(cTInAppNotificationMedia.getMediaUrl());
            if (bitmapCachedInAppImageV1 != null) {
                ImageView imageView = (ImageView) this.relativeLayout.findViewById(R.id.backgroundImage);
                imageView.setVisibility(0);
                imageView.setImageBitmap(bitmapCachedInAppImageV1);
                return;
            }
            return;
        }
        if (cTInAppNotificationMedia.isGIF()) {
            byte[] bArrCachedInAppGifV1 = resourceProvider().cachedInAppGifV1(cTInAppNotificationMedia.getMediaUrl());
            if (bArrCachedInAppGifV1 != null) {
                GifImageView gifImageView = (GifImageView) this.relativeLayout.findViewById(R.id.gifImage);
                this.gifImageView = gifImageView;
                gifImageView.setVisibility(0);
                this.gifImageView.setBytes(bArrCachedInAppGifV1);
                this.gifImageView.startAnimation();
                return;
            }
            return;
        }
        if (cTInAppNotificationMedia.isVideo()) {
            initFullScreenIconForStream();
            prepareMedia();
            playMedia();
        } else if (cTInAppNotificationMedia.isAudio()) {
            initFullScreenIconForStream();
            prepareMedia();
            playMedia();
            disableFullScreenButton();
        }
    }

    private void initFullScreenIconForStream() {
        float fApplyDimension;
        ImageView imageView = new ImageView(this.context);
        this.fullScreenIcon = imageView;
        imageView.setImageDrawable(ResourcesCompat.getDrawable(this.context.getResources(), R.drawable.ct_ic_fullscreen_expand, null));
        this.fullScreenIcon.setOnClickListener(new View.OnClickListener() { // from class: com.clevertap.android.sdk.inapp.CTInAppNativeInterstitialFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m4791xf60510b0(view);
            }
        });
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        if (this.inAppNotification.isTablet() && isTablet()) {
            fApplyDimension = TypedValue.applyDimension(1, 30.0f, displayMetrics);
        } else {
            fApplyDimension = TypedValue.applyDimension(1, 20.0f, displayMetrics);
        }
        int i = (int) fApplyDimension;
        int iApplyDimension = (int) TypedValue.applyDimension(1, 4.0f, displayMetrics);
        int iApplyDimension2 = (int) TypedValue.applyDimension(1, 2.0f, displayMetrics);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(i, i);
        layoutParams.gravity = GravityCompat.END;
        layoutParams.setMargins(0, iApplyDimension, iApplyDimension2, 0);
        this.fullScreenIcon.setLayoutParams(layoutParams);
    }

    /* renamed from: lambda$initFullScreenIconForStream$1$com-clevertap-android-sdk-inapp-CTInAppNativeInterstitialFragment, reason: not valid java name */
    /* synthetic */ void m4791xf60510b0(View view) {
        if (this.exoPlayerFullscreen) {
            closeFullscreenDialog();
        } else {
            openFullscreenDialog();
        }
    }

    private void resizeContainer(final FrameLayout frameLayout, final CloseImageView closeImageView) {
        int i = this.currentOrientation;
        if (i == 1) {
            this.relativeLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.clevertap.android.sdk.inapp.CTInAppNativeInterstitialFragment.1
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) CTInAppNativeInterstitialFragment.this.relativeLayout.getLayoutParams();
                    if (CTInAppNativeInterstitialFragment.this.inAppNotification.isTablet() && CTInAppNativeInterstitialFragment.this.isTablet()) {
                        CTInAppNativeInterstitialFragment cTInAppNativeInterstitialFragment = CTInAppNativeInterstitialFragment.this;
                        cTInAppNativeInterstitialFragment.redrawInterstitialTabletInApp(cTInAppNativeInterstitialFragment.relativeLayout, layoutParams, frameLayout, closeImageView);
                    } else if (CTInAppNativeInterstitialFragment.this.isTablet()) {
                        CTInAppNativeInterstitialFragment cTInAppNativeInterstitialFragment2 = CTInAppNativeInterstitialFragment.this;
                        cTInAppNativeInterstitialFragment2.redrawInterstitialMobileInAppOnTablet(cTInAppNativeInterstitialFragment2.relativeLayout, layoutParams, frameLayout, closeImageView);
                    } else {
                        CTInAppNativeInterstitialFragment cTInAppNativeInterstitialFragment3 = CTInAppNativeInterstitialFragment.this;
                        cTInAppNativeInterstitialFragment3.redrawInterstitialInApp(cTInAppNativeInterstitialFragment3.relativeLayout, layoutParams, closeImageView);
                    }
                    CTInAppNativeInterstitialFragment.this.relativeLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
        } else {
            if (i != 2) {
                return;
            }
            this.relativeLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.clevertap.android.sdk.inapp.CTInAppNativeInterstitialFragment.2
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) CTInAppNativeInterstitialFragment.this.relativeLayout.getLayoutParams();
                    if (CTInAppNativeInterstitialFragment.this.inAppNotification.isTablet() && CTInAppNativeInterstitialFragment.this.isTablet()) {
                        CTInAppNativeInterstitialFragment cTInAppNativeInterstitialFragment = CTInAppNativeInterstitialFragment.this;
                        cTInAppNativeInterstitialFragment.redrawLandscapeInterstitialTabletInApp(cTInAppNativeInterstitialFragment.relativeLayout, layoutParams, frameLayout, closeImageView);
                    } else if (CTInAppNativeInterstitialFragment.this.isTablet()) {
                        CTInAppNativeInterstitialFragment cTInAppNativeInterstitialFragment2 = CTInAppNativeInterstitialFragment.this;
                        cTInAppNativeInterstitialFragment2.redrawLandscapeInterstitialMobileInAppOnTablet(cTInAppNativeInterstitialFragment2.relativeLayout, layoutParams, frameLayout, closeImageView);
                    } else {
                        CTInAppNativeInterstitialFragment cTInAppNativeInterstitialFragment3 = CTInAppNativeInterstitialFragment.this;
                        cTInAppNativeInterstitialFragment3.redrawLandscapeInterstitialInApp(cTInAppNativeInterstitialFragment3.relativeLayout, layoutParams, closeImageView);
                    }
                    CTInAppNativeInterstitialFragment.this.relativeLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        if (this.gifImageView != null) {
            this.gifImageView.setBytes(resourceProvider().cachedInAppGifV1(this.inAppNotification.getMediaList().get(0).getMediaUrl()));
            this.gifImageView.startAnimation();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.inAppNotification.hasStreamMedia()) {
            prepareMedia();
            playMedia();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        GifImageView gifImageView = this.gifImageView;
        if (gifImageView != null) {
            gifImageView.clear();
        }
        if (this.exoPlayerFullscreen) {
            closeFullscreenDialog();
        }
        this.handle.savePosition();
        this.handle.pause();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        GifImageView gifImageView = this.gifImageView;
        if (gifImageView != null) {
            gifImageView.clear();
        }
        this.handle.pause();
    }

    @Override // com.clevertap.android.sdk.inapp.CTInAppBaseFullFragment, com.clevertap.android.sdk.inapp.CTInAppBaseFragment
    void cleanup() {
        super.cleanup();
        GifImageView gifImageView = this.gifImageView;
        if (gifImageView != null) {
            gifImageView.clear();
        }
        this.handle.pause();
    }

    private void disableFullScreenButton() {
        this.fullScreenIcon.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeFullscreenDialog() {
        View viewVideoSurface = this.handle.videoSurface();
        this.handle.switchToFullScreen(false);
        this.fullScreenIcon.setLayoutParams(this.imageViewLayoutParams);
        this.videoFrameInDialog.removeAllViews();
        this.videoFrameLayout.addView(viewVideoSurface);
        this.videoFrameLayout.addView(this.fullScreenIcon);
        this.exoPlayerFullscreen = false;
        this.fullScreenDialog.dismiss();
        this.fullScreenIcon.setImageDrawable(ContextCompat.getDrawable(this.context, R.drawable.ct_ic_fullscreen_expand));
    }

    private void openFullscreenDialog() {
        View viewVideoSurface = this.handle.videoSurface();
        this.imageViewLayoutParams = this.fullScreenIcon.getLayoutParams();
        this.handle.switchToFullScreen(true);
        this.videoFrameLayout.removeAllViews();
        if (this.fullScreenDialog == null) {
            this.fullScreenDialog = new Dialog(this.context, android.R.style.Theme.Black.NoTitleBar.Fullscreen) { // from class: com.clevertap.android.sdk.inapp.CTInAppNativeInterstitialFragment.3
                @Override // android.app.Dialog
                public void onBackPressed() {
                    if (CTInAppNativeInterstitialFragment.this.exoPlayerFullscreen) {
                        CTInAppNativeInterstitialFragment.this.closeFullscreenDialog();
                    }
                    super.onBackPressed();
                }
            };
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
            FrameLayout frameLayout = new FrameLayout(this.context);
            this.videoFrameInDialog = frameLayout;
            this.fullScreenDialog.addContentView(frameLayout, layoutParams);
        }
        this.videoFrameInDialog.addView(viewVideoSurface);
        this.exoPlayerFullscreen = true;
        this.fullScreenDialog.show();
    }

    private void playMedia() {
        this.handle.play();
    }

    private void prepareMedia() {
        this.handle.initPlayerView(this.context, this.inAppNotification.isTablet() && isTablet());
        addViewsForStreamMedia();
        this.handle.initExoplayer(this.context, this.inAppNotification.getMediaList().get(0).getMediaUrl());
    }

    private void addViewsForStreamMedia() {
        this.videoFrameLayout.setVisibility(0);
        View viewVideoSurface = this.handle.videoSurface();
        if (this.videoFrameLayout.getChildCount() == 0) {
            this.videoFrameLayout.addView(viewVideoSurface);
            this.videoFrameLayout.addView(this.fullScreenIcon);
        } else {
            Logger.d("Video views and controls are already added, not re-attaching");
        }
    }
}
