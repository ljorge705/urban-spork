package androidx.camera.viewfinder;

import android.graphics.Bitmap;
import android.util.Size;
import android.view.View;
import android.widget.FrameLayout;

/* loaded from: classes.dex */
abstract class ViewfinderImplementation {
    protected final FrameLayout mParent;
    protected Size mResolution;
    private final ViewfinderTransformation mViewfinderTransformation;
    private boolean mWasSurfaceProvided = false;

    abstract View getViewfinder();

    abstract Bitmap getViewfinderBitmap();

    abstract void initializeViewfinder();

    abstract void onAttachedToWindow();

    abstract void onDetachedFromWindow();

    abstract void onSurfaceRequested(ViewfinderSurfaceRequest viewfinderSurfaceRequest);

    ViewfinderImplementation(FrameLayout frameLayout, ViewfinderTransformation viewfinderTransformation) {
        this.mParent = frameLayout;
        this.mViewfinderTransformation = viewfinderTransformation;
    }

    void onSurfaceProvided() {
        this.mWasSurfaceProvided = true;
        redrawViewfinder();
    }

    void redrawViewfinder() {
        View viewfinder = getViewfinder();
        if (viewfinder == null || !this.mWasSurfaceProvided) {
            return;
        }
        this.mViewfinderTransformation.transformView(new Size(this.mParent.getWidth(), this.mParent.getHeight()), this.mParent.getLayoutDirection(), viewfinder);
    }

    Bitmap getBitmap() {
        Bitmap viewfinderBitmap = getViewfinderBitmap();
        if (viewfinderBitmap == null) {
            return null;
        }
        return this.mViewfinderTransformation.createTransformedBitmap(viewfinderBitmap, new Size(this.mParent.getWidth(), this.mParent.getHeight()), this.mParent.getLayoutDirection());
    }
}
