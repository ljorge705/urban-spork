package com.onfido.android.sdk.capture.utils;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.TypefaceSpan;
import io.sentry.protocol.Device;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0005H\u0002J\u0010\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u000eH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/onfido/android/sdk/capture/utils/CustomTypefaceSpan;", "Landroid/text/style/TypefaceSpan;", Device.JsonKeys.FAMILY, "", "newType", "Landroid/graphics/Typeface;", "(Ljava/lang/String;Landroid/graphics/Typeface;)V", "applyCustomTypeFace", "", "paint", "Landroid/graphics/Paint;", "tf", "updateDrawState", "ds", "Landroid/text/TextPaint;", "updateMeasureState", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CustomTypefaceSpan extends TypefaceSpan {
    private final Typeface newType;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CustomTypefaceSpan(String str, Typeface newType) {
        super(str);
        Intrinsics.checkNotNullParameter(newType, "newType");
        this.newType = newType;
    }

    private final void applyCustomTypeFace(Paint paint, Typeface tf) {
        Typeface typeface = paint.getTypeface();
        int style = (typeface == null ? 0 : typeface.getStyle()) & (~tf.getStyle());
        if ((style & 1) != 0) {
            paint.setFakeBoldText(true);
        }
        if ((style & 2) != 0) {
            paint.setTextSkewX(-0.25f);
        }
        paint.setTypeface(tf);
    }

    @Override // android.text.style.TypefaceSpan, android.text.style.CharacterStyle
    public void updateDrawState(TextPaint ds) {
        Intrinsics.checkNotNullParameter(ds, "ds");
        applyCustomTypeFace(ds, this.newType);
    }

    @Override // android.text.style.TypefaceSpan, android.text.style.MetricAffectingSpan
    public void updateMeasureState(TextPaint paint) {
        Intrinsics.checkNotNullParameter(paint, "paint");
        applyCustomTypeFace(paint, this.newType);
    }
}
