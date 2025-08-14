package com.reactnativecommunity.art;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.SurfaceTexture;
import android.os.Build;
import android.view.Surface;
import android.view.TextureView;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.ReactShadowNode;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIViewOperationQueue;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import javax.annotation.Nullable;

/* loaded from: classes6.dex */
public class ARTSurfaceViewShadowNode extends LayoutShadowNode implements TextureView.SurfaceTextureListener, LifecycleEventListener {

    @Nullable
    private Integer mBackgroundColor;

    @Nullable
    private Surface mSurface;

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    public boolean isVirtual() {
        return false;
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    public boolean isVirtualAnchor() {
        return true;
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    @ReactProp(customType = "Color", name = ViewProps.BACKGROUND_COLOR)
    public void setBackgroundColor(Integer num) {
        this.mBackgroundColor = num;
        markUpdated();
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    public void onCollectExtraUpdates(UIViewOperationQueue uIViewOperationQueue) throws Surface.OutOfResourcesException, IllegalArgumentException {
        super.onCollectExtraUpdates(uIViewOperationQueue);
        drawOutput(false);
        uIViewOperationQueue.enqueueUpdateExtraData(getReactTag(), this);
    }

    private void drawOutput(boolean z) throws Surface.OutOfResourcesException, IllegalArgumentException {
        Surface surface = this.mSurface;
        if (surface == null || !surface.isValid()) {
            markChildrenUpdatesSeen(this);
            return;
        }
        try {
            Canvas canvasLockCanvas = this.mSurface.lockCanvas(null);
            canvasLockCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
            Integer num = this.mBackgroundColor;
            if (num != null) {
                canvasLockCanvas.drawColor(num.intValue());
            }
            Paint paint = new Paint();
            for (int i = 0; i < getChildCount(); i++) {
                ARTVirtualNode aRTVirtualNode = (ARTVirtualNode) getChildAt(i);
                aRTVirtualNode.draw(canvasLockCanvas, paint, 1.0f);
                if (z) {
                    aRTVirtualNode.markUpdated();
                } else {
                    aRTVirtualNode.markUpdateSeen();
                }
            }
            Surface surface2 = this.mSurface;
            if (surface2 == null) {
                return;
            }
            surface2.unlockCanvasAndPost(canvasLockCanvas);
        } catch (IllegalArgumentException | IllegalStateException e) {
            FLog.e("ReactNative", e.getClass().getSimpleName() + " in Surface.unlockCanvasAndPost");
        }
    }

    public void setupSurfaceTextureListener(ARTSurfaceView aRTSurfaceView) throws Surface.OutOfResourcesException, IllegalArgumentException {
        SurfaceTexture surfaceTexture = aRTSurfaceView.getSurfaceTexture();
        aRTSurfaceView.setSurfaceTextureListener(this);
        if (surfaceTexture == null || this.mSurface != null) {
            return;
        }
        this.mSurface = new Surface(surfaceTexture);
        drawOutput(true);
    }

    private void markChildrenUpdatesSeen(ReactShadowNode reactShadowNode) {
        for (int i = 0; i < reactShadowNode.getChildCount(); i++) {
            ReactShadowNode childAt = reactShadowNode.getChildAt(i);
            childAt.markUpdateSeen();
            markChildrenUpdatesSeen(childAt);
        }
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    public void setThemedContext(ThemedReactContext themedReactContext) {
        super.setThemedContext(themedReactContext);
        if (Build.VERSION.SDK_INT > 24) {
            themedReactContext.addLifecycleEventListener(this);
        }
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    public void dispose() {
        super.dispose();
        if (Build.VERSION.SDK_INT > 24) {
            getThemedContext().removeLifecycleEventListener(this);
        }
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() throws Surface.OutOfResourcesException, IllegalArgumentException {
        drawOutput(false);
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) throws Surface.OutOfResourcesException, IllegalArgumentException {
        this.mSurface = new Surface(surfaceTexture);
        drawOutput(false);
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        this.mSurface.release();
        this.mSurface = null;
        return true;
    }
}
