package io.sentry.android.replay.util;

import androidx.compose.ui.text.TextLayoutResult;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* compiled from: Nodes.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u0007\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\bH\u0016J\u0010\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\bH\u0016J\u0010\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\bH\u0016J\u0010\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\bH\u0016J\u0010\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\bH\u0016J\u0018\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\bH\u0016R\u0016\u0010\u0007\u001a\u0004\u0018\u00010\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0019"}, d2 = {"Lio/sentry/android/replay/util/ComposeTextLayout;", "Lio/sentry/android/replay/util/TextLayout;", "layout", "Landroidx/compose/ui/text/TextLayoutResult;", "hasFillModifier", "", "(Landroidx/compose/ui/text/TextLayoutResult;Z)V", "dominantTextColor", "", "getDominantTextColor", "()Ljava/lang/Integer;", "getLayout$sentry_android_replay_release", "()Landroidx/compose/ui/text/TextLayoutResult;", "lineCount", "getLineCount", "()I", "getEllipsisCount", "line", "getLineBottom", "getLineStart", "getLineTop", "getLineVisibleEnd", "getPrimaryHorizontal", "", "offset", "sentry-android-replay_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class ComposeTextLayout implements TextLayout {
    private final boolean hasFillModifier;
    private final TextLayoutResult layout;

    @Override // io.sentry.android.replay.util.TextLayout
    public Integer getDominantTextColor() {
        return null;
    }

    /* renamed from: getLayout$sentry_android_replay_release, reason: from getter */
    public final TextLayoutResult getLayout() {
        return this.layout;
    }

    public ComposeTextLayout(TextLayoutResult layout, boolean z) {
        Intrinsics.checkNotNullParameter(layout, "layout");
        this.layout = layout;
        this.hasFillModifier = z;
    }

    @Override // io.sentry.android.replay.util.TextLayout
    public int getLineCount() {
        return this.layout.getLineCount();
    }

    @Override // io.sentry.android.replay.util.TextLayout
    public float getPrimaryHorizontal(int line, int offset) {
        float horizontalPosition = this.layout.getHorizontalPosition(offset, true);
        return (this.hasFillModifier || getLineCount() != 1) ? horizontalPosition : horizontalPosition - this.layout.getLineLeft(line);
    }

    @Override // io.sentry.android.replay.util.TextLayout
    public int getEllipsisCount(int line) {
        return this.layout.isLineEllipsized(line) ? 1 : 0;
    }

    @Override // io.sentry.android.replay.util.TextLayout
    public int getLineVisibleEnd(int line) {
        return this.layout.getLineEnd(line, true);
    }

    @Override // io.sentry.android.replay.util.TextLayout
    public int getLineTop(int line) {
        return MathKt.roundToInt(this.layout.getLineTop(line));
    }

    @Override // io.sentry.android.replay.util.TextLayout
    public int getLineBottom(int line) {
        return MathKt.roundToInt(this.layout.getLineBottom(line));
    }

    @Override // io.sentry.android.replay.util.TextLayout
    public int getLineStart(int line) {
        return this.layout.getLineStart(line);
    }
}
