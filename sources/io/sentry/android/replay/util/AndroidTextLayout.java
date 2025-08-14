package io.sentry.android.replay.util;

import android.text.Layout;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Views.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u0007\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0006H\u0016J\u0010\u0010\u000e\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0006H\u0016J\u0010\u0010\u000f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0006H\u0016J\u0010\u0010\u0010\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0006H\u0016J\u0010\u0010\u0011\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0006H\u0016J\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0006H\u0016R\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lio/sentry/android/replay/util/AndroidTextLayout;", "Lio/sentry/android/replay/util/TextLayout;", "layout", "Landroid/text/Layout;", "(Landroid/text/Layout;)V", "dominantTextColor", "", "getDominantTextColor", "()Ljava/lang/Integer;", "lineCount", "getLineCount", "()I", "getEllipsisCount", "line", "getLineBottom", "getLineStart", "getLineTop", "getLineVisibleEnd", "getPrimaryHorizontal", "", "offset", "sentry-android-replay_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class AndroidTextLayout implements TextLayout {
    public static final int $stable = 8;
    private final Layout layout;

    public AndroidTextLayout(Layout layout) {
        Intrinsics.checkNotNullParameter(layout, "layout");
        this.layout = layout;
    }

    @Override // io.sentry.android.replay.util.TextLayout
    public int getLineCount() {
        return this.layout.getLineCount();
    }

    @Override // io.sentry.android.replay.util.TextLayout
    public Integer getDominantTextColor() {
        int i;
        if (!(this.layout.getText() instanceof Spanned)) {
            return null;
        }
        CharSequence text = this.layout.getText();
        Intrinsics.checkNotNull(text, "null cannot be cast to non-null type android.text.Spanned");
        ForegroundColorSpan[] spans = (ForegroundColorSpan[]) ((Spanned) text).getSpans(0, this.layout.getText().length(), ForegroundColorSpan.class);
        Intrinsics.checkNotNullExpressionValue(spans, "spans");
        int i2 = Integer.MIN_VALUE;
        Integer numValueOf = null;
        for (ForegroundColorSpan foregroundColorSpan : spans) {
            CharSequence text2 = this.layout.getText();
            Intrinsics.checkNotNull(text2, "null cannot be cast to non-null type android.text.Spanned");
            int spanStart = ((Spanned) text2).getSpanStart(foregroundColorSpan);
            CharSequence text3 = this.layout.getText();
            Intrinsics.checkNotNull(text3, "null cannot be cast to non-null type android.text.Spanned");
            int spanEnd = ((Spanned) text3).getSpanEnd(foregroundColorSpan);
            if (spanStart != -1 && spanEnd != -1 && (i = spanEnd - spanStart) > i2) {
                numValueOf = Integer.valueOf(foregroundColorSpan.getForegroundColor());
                i2 = i;
            }
        }
        if (numValueOf != null) {
            return Integer.valueOf(ViewsKt.toOpaque(numValueOf.intValue()));
        }
        return null;
    }

    @Override // io.sentry.android.replay.util.TextLayout
    public float getPrimaryHorizontal(int line, int offset) {
        return this.layout.getPrimaryHorizontal(offset);
    }

    @Override // io.sentry.android.replay.util.TextLayout
    public int getEllipsisCount(int line) {
        return this.layout.getEllipsisCount(line);
    }

    @Override // io.sentry.android.replay.util.TextLayout
    public int getLineVisibleEnd(int line) {
        return this.layout.getLineVisibleEnd(line);
    }

    @Override // io.sentry.android.replay.util.TextLayout
    public int getLineTop(int line) {
        return this.layout.getLineTop(line);
    }

    @Override // io.sentry.android.replay.util.TextLayout
    public int getLineBottom(int line) {
        return this.layout.getLineBottom(line);
    }

    @Override // io.sentry.android.replay.util.TextLayout
    public int getLineStart(int line) {
        return this.layout.getLineStart(line);
    }
}
