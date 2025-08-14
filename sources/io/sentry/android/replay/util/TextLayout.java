package io.sentry.android.replay.util;

import kotlin.Metadata;

/* compiled from: TextLayout.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u0007\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0003H&J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0003H&J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0003H&J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0003H&J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0003H&J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0003H&R\u0014\u0010\u0002\u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, d2 = {"Lio/sentry/android/replay/util/TextLayout;", "", "dominantTextColor", "", "getDominantTextColor", "()Ljava/lang/Integer;", "lineCount", "getLineCount", "()I", "getEllipsisCount", "line", "getLineBottom", "getLineStart", "getLineTop", "getLineVisibleEnd", "getPrimaryHorizontal", "", "offset", "sentry-android-replay_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes6.dex */
public interface TextLayout {
    Integer getDominantTextColor();

    int getEllipsisCount(int line);

    int getLineBottom(int line);

    int getLineCount();

    int getLineStart(int line);

    int getLineTop(int line);

    int getLineVisibleEnd(int line);

    float getPrimaryHorizontal(int line, int offset);
}
