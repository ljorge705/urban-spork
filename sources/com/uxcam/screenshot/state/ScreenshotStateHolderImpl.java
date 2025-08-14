package com.uxcam.screenshot.state;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.webkit.WebView;
import com.google.android.gms.maps.GoogleMap;
import com.uxcam.screenaction.models.UXCamOccludeView;
import com.uxcam.screenshot.model.UXCamOcclusion;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.TypeIntrinsics;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/uxcam/screenshot/state/ScreenshotStateHolderImpl;", "Lcom/uxcam/screenshot/state/ScreenshotStateHolder;", "<init>", "()V", "screenshot_littleRelease"}, k = 1, mv = {1, 8, 0})
/* loaded from: classes6.dex */
public final class ScreenshotStateHolderImpl implements ScreenshotStateHolder {

    /* renamed from: a, reason: collision with root package name */
    public int f281a;
    public UXCamOcclusion b;
    public Bitmap c;
    public boolean e;
    public boolean f;
    public boolean g;
    public WeakReference<View> h;
    public GoogleMap i;
    public int j;
    public int k;
    public boolean l;

    /* renamed from: n, reason: collision with root package name */
    public boolean f282n;
    public WeakReference<WebView> o;
    public boolean p;
    public int q;
    public int r;
    public int d = -1;
    public boolean m = true;
    public final ArrayList s = new ArrayList();
    public final ArrayList<Rect> t = new ArrayList<>();

    @Override // com.uxcam.screenshot.state.ScreenshotStateHolder
    public final void addViewToHide(UXCamOccludeView uXCamOccludeView) {
        if (uXCamOccludeView == null) {
            return;
        }
        this.s.add(uXCamOccludeView);
    }

    @Override // com.uxcam.screenshot.state.ScreenshotStateHolder
    public final void addViewsToHide(List<? extends UXCamOccludeView> list) {
        if (list == null) {
            return;
        }
        this.s.addAll(CollectionsKt.filterNotNull(list));
    }

    @Override // com.uxcam.screenshot.state.ScreenshotStateHolder
    /* renamed from: getGoogleMap, reason: from getter */
    public final GoogleMap getI() {
        return this.i;
    }

    @Override // com.uxcam.screenshot.state.ScreenshotStateHolder
    public final WeakReference<View> getGoogleMapView() {
        return this.h;
    }

    @Override // com.uxcam.screenshot.state.ScreenshotStateHolder
    public final Integer getImageCount() {
        return Integer.valueOf(this.f281a);
    }

    @Override // com.uxcam.screenshot.state.ScreenshotStateHolder
    /* renamed from: getKeyboardHeight, reason: from getter */
    public final int getK() {
        return this.k;
    }

    @Override // com.uxcam.screenshot.state.ScreenshotStateHolder
    /* renamed from: getLastOcclusion, reason: from getter */
    public final UXCamOcclusion getB() {
        return this.b;
    }

    @Override // com.uxcam.screenshot.state.ScreenshotStateHolder
    /* renamed from: getLastVisibleDecorViewHeight, reason: from getter */
    public final int getJ() {
        return this.j;
    }

    @Override // com.uxcam.screenshot.state.ScreenshotStateHolder
    /* renamed from: getMapBitmap, reason: from getter */
    public final Bitmap getC() {
        return this.c;
    }

    @Override // com.uxcam.screenshot.state.ScreenshotStateHolder
    /* renamed from: getOccludeScreenAndWaitingToStop, reason: from getter */
    public final boolean getP() {
        return this.p;
    }

    @Override // com.uxcam.screenshot.state.ScreenshotStateHolder
    /* renamed from: getOrientation, reason: from getter */
    public final int getD() {
        return this.d;
    }

    @Override // com.uxcam.screenshot.state.ScreenshotStateHolder
    public final List getRectsToHide() {
        return this.t;
    }

    @Override // com.uxcam.screenshot.state.ScreenshotStateHolder
    public final List<UXCamOccludeView> getViewsToHide() {
        return this.s;
    }

    @Override // com.uxcam.screenshot.state.ScreenshotStateHolder
    public final WeakReference<WebView> getWebView() {
        return this.o;
    }

    @Override // com.uxcam.screenshot.state.ScreenshotStateHolder
    /* renamed from: getXOffset, reason: from getter */
    public final int getQ() {
        return this.q;
    }

    @Override // com.uxcam.screenshot.state.ScreenshotStateHolder
    /* renamed from: getYOffset, reason: from getter */
    public final int getR() {
        return this.r;
    }

    @Override // com.uxcam.screenshot.state.ScreenshotStateHolder
    /* renamed from: honorFlagSecure, reason: from getter */
    public final boolean getG() {
        return this.g;
    }

    @Override // com.uxcam.screenshot.state.ScreenshotStateHolder
    public final void increaseImageCount() {
        this.f281a = Integer.valueOf(this.f281a).intValue() + 1;
    }

    @Override // com.uxcam.screenshot.state.ScreenshotStateHolder
    /* renamed from: isFlutter, reason: from getter */
    public final boolean getL() {
        return this.l;
    }

    @Override // com.uxcam.screenshot.state.ScreenshotStateHolder
    public final boolean isPixelCopySupported() {
        return !this.l && Build.VERSION.SDK_INT >= 26;
    }

    @Override // com.uxcam.screenshot.state.ScreenshotStateHolder
    /* renamed from: isPreviousFrameOccluded, reason: from getter */
    public final boolean getF() {
        return this.f;
    }

    @Override // com.uxcam.screenshot.state.ScreenshotStateHolder
    /* renamed from: isWaitingToStop, reason: from getter */
    public final boolean getE() {
        return this.e;
    }

    @Override // com.uxcam.screenshot.state.ScreenshotStateHolder
    public final void removeViewToHide(UXCamOccludeView uXCamOccludeView) {
        TypeIntrinsics.asMutableCollection(this.s).remove(uXCamOccludeView);
    }

    @Override // com.uxcam.screenshot.state.ScreenshotStateHolder
    public final void removeViewsToHide(List<? extends UXCamOccludeView> list) {
        if (list == null) {
            return;
        }
        this.s.removeAll(CollectionsKt.filterNotNull(list));
    }

    @Override // com.uxcam.screenshot.state.ScreenshotStateHolder
    public final void resetImageCount() {
        this.f281a = 0;
    }

    @Override // com.uxcam.screenshot.state.ScreenshotStateHolder
    public final void setGoogleMap(GoogleMap googleMap) {
        this.i = googleMap;
    }

    @Override // com.uxcam.screenshot.state.ScreenshotStateHolder
    public final void setGoogleMapView(WeakReference<View> weakReference) {
        this.h = weakReference;
    }

    @Override // com.uxcam.screenshot.state.ScreenshotStateHolder
    public final void setHonorFlagSecure(Boolean bool) {
        if (bool == null) {
            return;
        }
        this.g = bool.booleanValue();
    }

    @Override // com.uxcam.screenshot.state.ScreenshotStateHolder
    public final void setIsFlutter(boolean z) {
        this.l = z;
    }

    @Override // com.uxcam.screenshot.state.ScreenshotStateHolder
    public final void setIsImprovedScreenCaptureEnabled(Boolean bool) {
        if (bool == null) {
            return;
        }
        this.m = bool.booleanValue();
    }

    @Override // com.uxcam.screenshot.state.ScreenshotStateHolder
    public final void setIsImprovedScreenCaptureEnabledForCustomer(Boolean bool) {
        if (bool == null) {
            return;
        }
        this.f282n = bool.booleanValue();
    }

    @Override // com.uxcam.screenshot.state.ScreenshotStateHolder
    public final void setKeyboardHeight(int i) {
        this.k = i;
    }

    @Override // com.uxcam.screenshot.state.ScreenshotStateHolder
    public final void setLastOcclusion(UXCamOcclusion uXCamOcclusion) {
        this.b = uXCamOcclusion;
    }

    @Override // com.uxcam.screenshot.state.ScreenshotStateHolder
    public final void setLastVisibleDecorViewHeight(int i) {
        this.j = i;
    }

    @Override // com.uxcam.screenshot.state.ScreenshotStateHolder
    public final void setMapBitmap(Bitmap bitmap) {
        this.c = bitmap;
    }

    @Override // com.uxcam.screenshot.state.ScreenshotStateHolder
    public final void setOccludeScreenAndWaitingToStop(boolean z) {
        this.p = z;
    }

    @Override // com.uxcam.screenshot.state.ScreenshotStateHolder
    public final void setOrientation(int i) {
        this.d = i;
    }

    @Override // com.uxcam.screenshot.state.ScreenshotStateHolder
    public final void setPreviousFrameOccluded(boolean z) {
        this.f = z;
    }

    @Override // com.uxcam.screenshot.state.ScreenshotStateHolder
    public final void setWaitingToStop(boolean z) {
        this.e = z;
    }

    @Override // com.uxcam.screenshot.state.ScreenshotStateHolder
    public final void setWebView(WeakReference<WebView> weakReference) {
        this.o = weakReference;
    }

    @Override // com.uxcam.screenshot.state.ScreenshotStateHolder
    public final void setXOffset(int i) {
        this.q = i;
    }

    @Override // com.uxcam.screenshot.state.ScreenshotStateHolder
    public final void setYOffset(int i) {
        this.r = i;
    }

    @Override // com.uxcam.screenshot.state.ScreenshotStateHolder
    public final void addRectToHide(Rect rect) {
        this.t.add(rect);
    }

    @Override // com.uxcam.screenshot.state.ScreenshotStateHolder
    public final void clearRectsToHide() {
        this.t.clear();
    }

    @Override // com.uxcam.screenshot.state.ScreenshotStateHolder
    public final boolean isImprovedScreenCaptureInUse() {
        return this.m && this.f282n && isPixelCopySupported();
    }
}
