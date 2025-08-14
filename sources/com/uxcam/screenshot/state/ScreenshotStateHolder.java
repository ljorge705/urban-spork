package com.uxcam.screenshot.state;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.view.View;
import android.webkit.WebView;
import com.facebook.react.uimanager.ViewProps;
import com.google.android.gms.maps.GoogleMap;
import com.uxcam.screenaction.models.UXCamOccludeView;
import com.uxcam.screenshot.model.UXCamOcclusion;
import java.lang.ref.WeakReference;
import java.util.List;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0016\bf\u0018\u00002\u00020\u0001J\u0012\u0010?\u001a\u00020@2\b\u0010A\u001a\u0004\u0018\u000102H&J\u0012\u0010B\u001a\u00020@2\b\u0010C\u001a\u0004\u0018\u00010DH&J\u001a\u0010E\u001a\u00020@2\u0010\u0010F\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010D\u0018\u000101H&J\b\u0010G\u001a\u00020@H&J\b\u0010H\u001a\u00020\u0010H&J\b\u0010I\u001a\u00020\u0010H&J\u000e\u0010J\u001a\b\u0012\u0004\u0012\u00020D01H&J\b\u0010K\u001a\u00020\u0014H&J\b\u0010L\u001a\u00020@H&J\b\u0010M\u001a\u00020\u0014H&J\u0012\u0010N\u001a\u00020@2\b\u0010C\u001a\u0004\u0018\u00010DH&J\u001a\u0010O\u001a\u00020@2\u0010\u0010F\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010D\u0018\u000101H&J\b\u0010P\u001a\u00020@H&J\u0017\u0010Q\u001a\u00020@2\b\u0010K\u001a\u0004\u0018\u00010\u0014H&¢\u0006\u0002\u0010RJ\u0010\u0010S\u001a\u00020@2\u0006\u0010M\u001a\u00020\u0014H&J\u0017\u0010T\u001a\u00020@2\b\u0010U\u001a\u0004\u0018\u00010\u0014H&¢\u0006\u0002\u0010RJ\u0017\u0010V\u001a\u00020@2\b\u0010U\u001a\u0004\u0018\u00010\u0014H&¢\u0006\u0002\u0010RJ\u0010\u0010W\u001a\u00020@2\u0006\u0010X\u001a\u00020\u0010H&J\u0010\u0010Y\u001a\u00020@2\u0006\u0010X\u001a\u00020\u0010H&R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\"\u0010\b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\tX¦\u000e¢\u0006\f\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u0004\u0018\u00010\u0010X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0012\u0010\u0013\u001a\u00020\u0014X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0015R\u0012\u0010\u0016\u001a\u00020\u0014X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0015R\u0018\u0010\u0017\u001a\u00020\u0014X¦\u000e¢\u0006\f\u001a\u0004\b\u0017\u0010\u0015\"\u0004\b\u0018\u0010\u0019R\u0018\u0010\u001a\u001a\u00020\u0014X¦\u000e¢\u0006\f\u001a\u0004\b\u001a\u0010\u0015\"\u0004\b\u001b\u0010\u0019R\u001a\u0010\u001c\u001a\u0004\u0018\u00010\u001dX¦\u000e¢\u0006\f\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001a\u0010\"\u001a\u0004\u0018\u00010#X¦\u000e¢\u0006\f\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u0018\u0010(\u001a\u00020\u0014X¦\u000e¢\u0006\f\u001a\u0004\b)\u0010\u0015\"\u0004\b*\u0010\u0019R\u0018\u0010+\u001a\u00020\u0010X¦\u000e¢\u0006\f\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u001c\u00100\u001a\f\u0012\u0006\u0012\u0004\u0018\u000102\u0018\u000101X¦\u0004¢\u0006\u0006\u001a\u0004\b3\u00104R\"\u00105\u001a\f\u0012\u0006\u0012\u0004\u0018\u000106\u0018\u00010\tX¦\u000e¢\u0006\f\u001a\u0004\b7\u0010\f\"\u0004\b8\u0010\u000eR\u0018\u00109\u001a\u00020\u0010X¦\u000e¢\u0006\f\u001a\u0004\b:\u0010-\"\u0004\b;\u0010/R\u0018\u0010<\u001a\u00020\u0010X¦\u000e¢\u0006\f\u001a\u0004\b=\u0010-\"\u0004\b>\u0010/¨\u0006Z"}, d2 = {"Lcom/uxcam/screenshot/state/ScreenshotStateHolder;", "", "googleMap", "Lcom/google/android/gms/maps/GoogleMap;", "getGoogleMap", "()Lcom/google/android/gms/maps/GoogleMap;", "setGoogleMap", "(Lcom/google/android/gms/maps/GoogleMap;)V", "googleMapView", "Ljava/lang/ref/WeakReference;", "Landroid/view/View;", "getGoogleMapView", "()Ljava/lang/ref/WeakReference;", "setGoogleMapView", "(Ljava/lang/ref/WeakReference;)V", "imageCount", "", "getImageCount", "()Ljava/lang/Integer;", "isImprovedScreenCaptureInUse", "", "()Z", "isPixelCopySupported", "isPreviousFrameOccluded", "setPreviousFrameOccluded", "(Z)V", "isWaitingToStop", "setWaitingToStop", "lastOcclusion", "Lcom/uxcam/screenshot/model/UXCamOcclusion;", "getLastOcclusion", "()Lcom/uxcam/screenshot/model/UXCamOcclusion;", "setLastOcclusion", "(Lcom/uxcam/screenshot/model/UXCamOcclusion;)V", "mapBitmap", "Landroid/graphics/Bitmap;", "getMapBitmap", "()Landroid/graphics/Bitmap;", "setMapBitmap", "(Landroid/graphics/Bitmap;)V", "occludeScreenAndWaitingToStop", "getOccludeScreenAndWaitingToStop", "setOccludeScreenAndWaitingToStop", "orientation", "getOrientation", "()I", "setOrientation", "(I)V", "rectsToHide", "", "Landroid/graphics/Rect;", "getRectsToHide", "()Ljava/util/List;", "webView", "Landroid/webkit/WebView;", "getWebView", "setWebView", "xOffset", "getXOffset", "setXOffset", "yOffset", "getYOffset", "setYOffset", "addRectToHide", "", "rect", "addViewToHide", "view", "Lcom/uxcam/screenaction/models/UXCamOccludeView;", "addViewsToHide", "viewsToHide", "clearRectsToHide", "getKeyboardHeight", "getLastVisibleDecorViewHeight", "getViewsToHide", "honorFlagSecure", "increaseImageCount", "isFlutter", "removeViewToHide", "removeViewsToHide", "resetImageCount", "setHonorFlagSecure", "(Ljava/lang/Boolean;)V", "setIsFlutter", "setIsImprovedScreenCaptureEnabled", ViewProps.ENABLED, "setIsImprovedScreenCaptureEnabledForCustomer", "setKeyboardHeight", "height", "setLastVisibleDecorViewHeight", "screenshot_littleRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes6.dex */
public interface ScreenshotStateHolder {
    void addRectToHide(Rect rect);

    void addViewToHide(UXCamOccludeView view);

    void addViewsToHide(List<? extends UXCamOccludeView> viewsToHide);

    void clearRectsToHide();

    GoogleMap getGoogleMap();

    WeakReference<View> getGoogleMapView();

    Integer getImageCount();

    int getKeyboardHeight();

    UXCamOcclusion getLastOcclusion();

    int getLastVisibleDecorViewHeight();

    Bitmap getMapBitmap();

    boolean getOccludeScreenAndWaitingToStop();

    int getOrientation();

    List<Rect> getRectsToHide();

    List<UXCamOccludeView> getViewsToHide();

    WeakReference<WebView> getWebView();

    int getXOffset();

    int getYOffset();

    boolean honorFlagSecure();

    void increaseImageCount();

    boolean isFlutter();

    boolean isImprovedScreenCaptureInUse();

    boolean isPixelCopySupported();

    boolean isPreviousFrameOccluded();

    boolean isWaitingToStop();

    void removeViewToHide(UXCamOccludeView view);

    void removeViewsToHide(List<? extends UXCamOccludeView> viewsToHide);

    void resetImageCount();

    void setGoogleMap(GoogleMap googleMap);

    void setGoogleMapView(WeakReference<View> weakReference);

    void setHonorFlagSecure(Boolean honorFlagSecure);

    void setIsFlutter(boolean isFlutter);

    void setIsImprovedScreenCaptureEnabled(Boolean enabled);

    void setIsImprovedScreenCaptureEnabledForCustomer(Boolean enabled);

    void setKeyboardHeight(int height);

    void setLastOcclusion(UXCamOcclusion uXCamOcclusion);

    void setLastVisibleDecorViewHeight(int height);

    void setMapBitmap(Bitmap bitmap);

    void setOccludeScreenAndWaitingToStop(boolean z);

    void setOrientation(int i);

    void setPreviousFrameOccluded(boolean z);

    void setWaitingToStop(boolean z);

    void setWebView(WeakReference<WebView> weakReference);

    void setXOffset(int i);

    void setYOffset(int i);
}
