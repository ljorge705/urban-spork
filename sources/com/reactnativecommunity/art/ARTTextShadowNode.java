package com.reactnativecommunity.art;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextUtils;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.annotations.ReactProp;
import javax.annotation.Nullable;

/* loaded from: classes6.dex */
public class ARTTextShadowNode extends ARTShapeShadowNode {
    private static final int DEFAULT_FONT_SIZE = 12;
    private static final String PROP_FONT = "font";
    private static final String PROP_FONT_FAMILY = "fontFamily";
    private static final String PROP_FONT_SIZE = "fontSize";
    private static final String PROP_FONT_STYLE = "fontStyle";
    private static final String PROP_FONT_WEIGHT = "fontWeight";
    private static final String PROP_LINES = "lines";
    private static final int TEXT_ALIGNMENT_CENTER = 2;
    private static final int TEXT_ALIGNMENT_LEFT = 0;
    private static final int TEXT_ALIGNMENT_RIGHT = 1;

    @Nullable
    private ReadableMap mFrame;
    private int mTextAlignment = 0;

    @ReactProp(defaultInt = 0, name = "alignment")
    public void setAlignment(int i) {
        this.mTextAlignment = i;
    }

    @ReactProp(name = "frame")
    public void setFrame(@Nullable ReadableMap readableMap) {
        this.mFrame = readableMap;
    }

    @Override // com.reactnativecommunity.art.ARTShapeShadowNode, com.reactnativecommunity.art.ARTVirtualNode
    public void draw(Canvas canvas, Paint paint, float f) {
        ReadableArray array;
        if (this.mFrame == null) {
            return;
        }
        float f2 = f * this.mOpacity;
        if (f2 > 0.01f && this.mFrame.hasKey(PROP_LINES) && (array = this.mFrame.getArray(PROP_LINES)) != null && array.size() != 0) {
            saveAndSetupCanvas(canvas);
            int size = array.size();
            String[] strArr = new String[size];
            for (int i = 0; i < size; i++) {
                strArr[i] = array.getString(i);
            }
            String strJoin = TextUtils.join("\n", strArr);
            if (setupStrokePaint(paint, f2)) {
                applyTextPropertiesToPaint(paint);
                if (this.mPath == null) {
                    canvas.drawText(strJoin, 0.0f, -paint.ascent(), paint);
                } else {
                    canvas.drawTextOnPath(strJoin, this.mPath, 0.0f, 0.0f, paint);
                }
            }
            if (setupFillPaint(paint, f2)) {
                applyTextPropertiesToPaint(paint);
                if (this.mPath == null) {
                    canvas.drawText(strJoin, 0.0f, -paint.ascent(), paint);
                } else {
                    canvas.drawTextOnPath(strJoin, this.mPath, 0.0f, 0.0f, paint);
                }
            }
            if (this.mShadowOpacity > 0.0f) {
                paint.setShadowLayer(this.mShadowRadius, this.mShadowOffsetX, this.mShadowOffsetY, this.mShadowColor);
            }
            restoreCanvas(canvas);
            markUpdateSeen();
        }
    }

    private void applyTextPropertiesToPaint(Paint paint) {
        ReadableMap map;
        int i = this.mTextAlignment;
        int i2 = 2;
        if (i == 0) {
            paint.setTextAlign(Paint.Align.LEFT);
        } else if (i == 1) {
            paint.setTextAlign(Paint.Align.RIGHT);
        } else if (i == 2) {
            paint.setTextAlign(Paint.Align.CENTER);
        }
        ReadableMap readableMap = this.mFrame;
        if (readableMap == null || !readableMap.hasKey(PROP_FONT) || (map = this.mFrame.getMap(PROP_FONT)) == null) {
            return;
        }
        paint.setTextSize((map.hasKey("fontSize") ? (float) map.getDouble("fontSize") : 12.0f) * this.mScale);
        boolean z = map.hasKey("fontWeight") && "bold".equals(map.getString("fontWeight"));
        boolean z2 = map.hasKey("fontStyle") && "italic".equals(map.getString("fontStyle"));
        if (z && z2) {
            i2 = 3;
        } else if (z) {
            i2 = 1;
        } else if (!z2) {
            i2 = 0;
        }
        paint.setTypeface(Typeface.create(map.getString("fontFamily"), i2));
    }
}
