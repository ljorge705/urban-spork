package com.reactnativecommunity.art;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.annotations.ReactProp;
import javax.annotation.Nullable;

/* loaded from: classes6.dex */
public class ARTShapeShadowNode extends ARTVirtualNode {
    private static final int CAP_BUTT = 0;
    private static final int CAP_ROUND = 1;
    private static final int CAP_SQUARE = 2;
    private static final int COLOR_TYPE_LINEAR_GRADIENT = 1;
    private static final int COLOR_TYPE_PATTERN = 3;
    private static final int COLOR_TYPE_RADIAL_GRADIENT = 2;
    private static final int COLOR_TYPE_SOLID_COLOR = 0;
    private static final int JOIN_BEVEL = 2;
    private static final int JOIN_MITER = 0;
    private static final int JOIN_ROUND = 1;
    private static final int PATH_TYPE_ARC = 4;
    private static final int PATH_TYPE_CLOSE = 1;
    private static final int PATH_TYPE_CURVETO = 3;
    private static final int PATH_TYPE_LINETO = 2;
    private static final int PATH_TYPE_MOVETO = 0;

    @Nullable
    private float[] mBrushData;

    @Nullable
    protected Path mPath;

    @Nullable
    private String mStrokeColor;

    @Nullable
    private float[] mStrokeDash;
    private float mStrokeWidth = 1.0f;
    private int mStrokeCap = 1;
    private int mStrokeJoin = 1;

    private float modulus(float f, float f2) {
        float f3 = f % f2;
        return f3 < 0.0f ? f3 + f2 : f3;
    }

    @ReactProp(name = "d")
    public void setShapePath(@Nullable ReadableArray readableArray) {
        this.mPath = createPath(PropHelper.toFloatArray(readableArray));
        markUpdated();
    }

    @ReactProp(name = "stroke")
    public void setStroke(@Nullable String str) {
        this.mStrokeColor = str;
        markUpdated();
    }

    @ReactProp(name = "strokeDash")
    public void setStrokeDash(@Nullable ReadableArray readableArray) {
        this.mStrokeDash = PropHelper.toFloatArray(readableArray);
        markUpdated();
    }

    @ReactProp(name = "fill")
    public void setFill(@Nullable ReadableArray readableArray) {
        this.mBrushData = PropHelper.toFloatArray(readableArray);
        markUpdated();
    }

    @ReactProp(defaultFloat = 1.0f, name = "strokeWidth")
    public void setStrokeWidth(float f) {
        this.mStrokeWidth = f;
        markUpdated();
    }

    @ReactProp(defaultInt = 1, name = "strokeCap")
    public void setStrokeCap(int i) {
        this.mStrokeCap = i;
        markUpdated();
    }

    @ReactProp(defaultInt = 1, name = "strokeJoin")
    public void setStrokeJoin(int i) {
        this.mStrokeJoin = i;
        markUpdated();
    }

    @Override // com.reactnativecommunity.art.ARTVirtualNode
    public void draw(Canvas canvas, Paint paint, float f) {
        float f2 = f * this.mOpacity;
        if (f2 > 0.01f) {
            saveAndSetupCanvas(canvas);
            if (this.mPath == null) {
                throw new JSApplicationIllegalArgumentException("Shapes should have a valid path (d) prop");
            }
            if (setupFillPaint(paint, f2)) {
                canvas.drawPath(this.mPath, paint);
            }
            if (setupStrokePaint(paint, f2)) {
                canvas.drawPath(this.mPath, paint);
            }
            restoreCanvas(canvas);
        }
        markUpdateSeen();
    }

    protected boolean setupStrokePaint(Paint paint, float f) {
        if (this.mStrokeWidth == 0.0f || this.mStrokeColor == null) {
            return false;
        }
        paint.reset();
        paint.setFlags(1);
        paint.setStyle(Paint.Style.STROKE);
        int i = this.mStrokeCap;
        if (i == 0) {
            paint.setStrokeCap(Paint.Cap.BUTT);
        } else if (i == 1) {
            paint.setStrokeCap(Paint.Cap.ROUND);
        } else if (i == 2) {
            paint.setStrokeCap(Paint.Cap.SQUARE);
        } else {
            throw new JSApplicationIllegalArgumentException("strokeCap " + this.mStrokeCap + " unrecognized");
        }
        int i2 = this.mStrokeJoin;
        if (i2 == 0) {
            paint.setStrokeJoin(Paint.Join.MITER);
        } else if (i2 == 1) {
            paint.setStrokeJoin(Paint.Join.ROUND);
        } else if (i2 == 2) {
            paint.setStrokeJoin(Paint.Join.BEVEL);
        } else {
            throw new JSApplicationIllegalArgumentException("strokeJoin " + this.mStrokeJoin + " unrecognized");
        }
        paint.setStrokeWidth(this.mStrokeWidth * this.mScale);
        paint.setColor(Color.parseColor(this.mStrokeColor));
        float[] fArr = this.mStrokeDash;
        if (fArr != null && fArr.length > 0) {
            paint.setPathEffect(new DashPathEffect(this.mStrokeDash, 0.0f));
        }
        if (this.mShadowOpacity > 0.0f) {
            paint.setShadowLayer(this.mShadowRadius, this.mShadowOffsetX, this.mShadowOffsetY, this.mShadowColor);
        }
        return true;
    }

    protected boolean setupFillPaint(Paint paint, float f) {
        int[] iArr;
        float[] fArr;
        float[] fArr2 = this.mBrushData;
        int i = 0;
        if (fArr2 == null || fArr2.length <= 0) {
            return false;
        }
        paint.reset();
        paint.setFlags(1);
        paint.setStyle(Paint.Style.FILL);
        float[] fArr3 = this.mBrushData;
        int i2 = (int) fArr3[0];
        if (i2 == 0) {
            paint.setARGB((int) (fArr3.length > 4 ? fArr3[4] * f * 255.0f : f * 255.0f), (int) (fArr3[1] * 255.0f), (int) (fArr3[2] * 255.0f), (int) (fArr3[3] * 255.0f));
        } else if (i2 == 1) {
            int i3 = 5;
            if (fArr3.length < 5) {
                FLog.w("ReactNative", "[ARTShapeShadowNode setupFillPaint] expects 5 elements, received " + this.mBrushData.length);
                return false;
            }
            float f2 = fArr3[1] * this.mScale;
            float f3 = this.mBrushData[2] * this.mScale;
            float f4 = this.mBrushData[3] * this.mScale;
            float f5 = this.mBrushData[4] * this.mScale;
            int length = (this.mBrushData.length - 5) / 5;
            if (length > 0) {
                int[] iArr2 = new int[length];
                float[] fArr4 = new float[length];
                while (i < length) {
                    float[] fArr5 = this.mBrushData;
                    fArr4[i] = fArr5[(length * 4) + i3 + i];
                    int i4 = i * 4;
                    iArr2[i] = Color.argb((int) (fArr5[i4 + 8] * 255.0f), (int) (fArr5[i4 + 5] * 255.0f), (int) (fArr5[i4 + 6] * 255.0f), (int) (fArr5[i4 + 7] * 255.0f));
                    i++;
                    i3 = 5;
                }
                iArr = iArr2;
                fArr = fArr4;
            } else {
                iArr = null;
                fArr = null;
            }
            paint.setShader(new LinearGradient(f2, f3, f4, f5, iArr, fArr, Shader.TileMode.CLAMP));
        } else {
            FLog.w("ReactNative", "ART: Color type " + i2 + " not supported!");
        }
        if (this.mShadowOpacity > 0.0f) {
            paint.setShadowLayer(this.mShadowRadius, this.mShadowOffsetX, this.mShadowOffsetY, this.mShadowColor);
        }
        return true;
    }

    private Path createPath(float[] fArr) {
        Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        int i = 0;
        while (i < fArr.length) {
            int i2 = i + 1;
            int i3 = (int) fArr[i];
            if (i3 != 0) {
                if (i3 == 1) {
                    path.close();
                    i = i2;
                } else if (i3 == 2) {
                    int i4 = i + 2;
                    i += 3;
                    path.lineTo(fArr[i2] * this.mScale, fArr[i4] * this.mScale);
                } else if (i3 == 3) {
                    path.cubicTo(fArr[i2] * this.mScale, fArr[i + 2] * this.mScale, fArr[i + 3] * this.mScale, fArr[i + 4] * this.mScale, fArr[i + 5] * this.mScale, this.mScale * fArr[i + 6]);
                    i += 7;
                } else if (i3 == 4) {
                    float f = fArr[i2] * this.mScale;
                    float f2 = fArr[i + 2] * this.mScale;
                    float f3 = fArr[i + 3] * this.mScale;
                    float degrees = (float) Math.toDegrees(fArr[i + 4]);
                    int i5 = i + 6;
                    float degrees2 = (float) Math.toDegrees(fArr[i + 5]);
                    i += 7;
                    boolean z = fArr[i5] != 1.0f;
                    float f4 = degrees2 - degrees;
                    if (Math.abs(f4) >= 360.0f) {
                        path.addCircle(f, f2, f3, z ? Path.Direction.CCW : Path.Direction.CW);
                    } else {
                        float fModulus = modulus(f4, 360.0f);
                        if (z && fModulus < 360.0f) {
                            fModulus = (360.0f - fModulus) * (-1.0f);
                        }
                        path.arcTo(new RectF(f - f3, f2 - f3, f + f3, f2 + f3), degrees, fModulus);
                    }
                } else {
                    throw new JSApplicationIllegalArgumentException("Unrecognized drawing instruction " + i3);
                }
            } else {
                int i6 = i + 2;
                i += 3;
                path.moveTo(fArr[i2] * this.mScale, fArr[i6] * this.mScale);
            }
        }
        return path;
    }
}
