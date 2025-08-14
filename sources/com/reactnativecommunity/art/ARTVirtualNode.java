package com.reactnativecommunity.art;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import androidx.core.graphics.ColorUtils;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.DisplayMetricsHolder;
import com.facebook.react.uimanager.ReactShadowNodeImpl;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import javax.annotation.Nullable;

/* loaded from: classes6.dex */
public abstract class ARTVirtualNode extends ReactShadowNodeImpl {
    protected static final float MIN_OPACITY_FOR_DRAW = 0.01f;
    private static final float[] sMatrixData = new float[9];
    private static final float[] sRawMatrix = new float[9];
    protected float mOpacity = 1.0f;

    @Nullable
    private Matrix mMatrix = new Matrix();
    protected int mShadowColor = 0;
    protected float mShadowOpacity = 1.0f;
    protected float mShadowRadius = 0.0f;
    protected float mShadowOffsetX = 0.0f;
    protected float mShadowOffsetY = 0.0f;
    protected final float mScale = DisplayMetricsHolder.getWindowDisplayMetrics().density;

    public abstract void draw(Canvas canvas, Paint paint, float f);

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    public boolean isVirtual() {
        return true;
    }

    protected final void saveAndSetupCanvas(Canvas canvas) {
        canvas.save();
        Matrix matrix = this.mMatrix;
        if (matrix != null) {
            canvas.concat(matrix);
        }
    }

    protected void restoreCanvas(Canvas canvas) {
        canvas.restore();
    }

    @ReactProp(defaultFloat = 1.0f, name = ViewProps.OPACITY)
    public void setOpacity(float f) {
        this.mOpacity = f;
        markUpdated();
    }

    @ReactProp(name = ViewProps.TRANSFORM)
    public void setTransform(@Nullable ReadableArray readableArray) {
        if (readableArray != null) {
            int floatArray = PropHelper.toFloatArray(readableArray, sMatrixData);
            if (floatArray == 6) {
                setupMatrix();
            } else if (floatArray != -1) {
                throw new JSApplicationIllegalArgumentException("Transform matrices must be of size 6");
            }
        } else {
            this.mMatrix = null;
        }
        markUpdated();
    }

    @ReactProp(name = "shadow")
    public void setShadow(@Nullable ReadableArray readableArray) {
        if (readableArray != null) {
            this.mShadowOpacity = (float) readableArray.getDouble(1);
            this.mShadowRadius = (float) readableArray.getDouble(2);
            this.mShadowOffsetX = (float) readableArray.getDouble(3);
            this.mShadowOffsetY = (float) readableArray.getDouble(4);
            int alphaComponent = readableArray.getInt(0);
            float f = this.mShadowOpacity;
            if (f < 1.0f) {
                alphaComponent = ColorUtils.setAlphaComponent(alphaComponent, (int) (f * 255.0f));
            }
            this.mShadowColor = alphaComponent;
        } else {
            this.mShadowColor = 0;
            this.mShadowOpacity = 0.0f;
            this.mShadowRadius = 0.0f;
            this.mShadowOffsetX = 0.0f;
            this.mShadowOffsetY = 0.0f;
        }
        markUpdated();
    }

    protected void setupMatrix() {
        float[] fArr = sRawMatrix;
        float[] fArr2 = sMatrixData;
        fArr[0] = fArr2[0];
        fArr[1] = fArr2[2];
        float f = fArr2[4];
        float f2 = this.mScale;
        fArr[2] = f * f2;
        fArr[3] = fArr2[1];
        fArr[4] = fArr2[3];
        fArr[5] = fArr2[5] * f2;
        fArr[6] = 0.0f;
        fArr[7] = 0.0f;
        fArr[8] = 1.0f;
        if (this.mMatrix == null) {
            this.mMatrix = new Matrix();
        }
        this.mMatrix.setValues(fArr);
    }
}
