package io.sentry.android.replay;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.view.WindowManager;
import com.clevertap.android.sdk.Constants;
import com.google.firebase.messaging.Constants;
import io.sentry.SentryReplayOptions;
import io.sentry.rrweb.RRWebVideoEvent;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* compiled from: ScreenshotRecorder.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0087\b\u0018\u0000 !2\u00020\u0001:\u0001!B\u0017\b\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005B5\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u0007¢\u0006\u0002\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0007HÆ\u0003JE\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u0007HÖ\u0001J\t\u0010\u001f\u001a\u00020 HÖ\u0001R\u0011\u0010\n\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\t\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012¨\u0006\""}, d2 = {"Lio/sentry/android/replay/ScreenshotRecorderConfig;", "", "scaleFactorX", "", "scaleFactorY", "(FF)V", "recordingWidth", "", "recordingHeight", RRWebVideoEvent.JsonKeys.FRAME_RATE, "bitRate", "(IIFFII)V", "getBitRate", "()I", "getFrameRate", "getRecordingHeight", "getRecordingWidth", "getScaleFactorX", "()F", "getScaleFactorY", "component1", "component2", "component3", "component4", "component5", "component6", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "toString", "", "Companion", "sentry-android-replay_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes6.dex */
public final /* data */ class ScreenshotRecorderConfig {
    public static final int $stable = 0;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final int bitRate;
    private final int frameRate;
    private final int recordingHeight;
    private final int recordingWidth;
    private final float scaleFactorX;
    private final float scaleFactorY;

    public static /* synthetic */ ScreenshotRecorderConfig copy$default(ScreenshotRecorderConfig screenshotRecorderConfig, int i, int i2, float f, float f2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            i = screenshotRecorderConfig.recordingWidth;
        }
        if ((i5 & 2) != 0) {
            i2 = screenshotRecorderConfig.recordingHeight;
        }
        int i6 = i2;
        if ((i5 & 4) != 0) {
            f = screenshotRecorderConfig.scaleFactorX;
        }
        float f3 = f;
        if ((i5 & 8) != 0) {
            f2 = screenshotRecorderConfig.scaleFactorY;
        }
        float f4 = f2;
        if ((i5 & 16) != 0) {
            i3 = screenshotRecorderConfig.frameRate;
        }
        int i7 = i3;
        if ((i5 & 32) != 0) {
            i4 = screenshotRecorderConfig.bitRate;
        }
        return screenshotRecorderConfig.copy(i, i6, f3, f4, i7, i4);
    }

    /* renamed from: component1, reason: from getter */
    public final int getRecordingWidth() {
        return this.recordingWidth;
    }

    /* renamed from: component2, reason: from getter */
    public final int getRecordingHeight() {
        return this.recordingHeight;
    }

    /* renamed from: component3, reason: from getter */
    public final float getScaleFactorX() {
        return this.scaleFactorX;
    }

    /* renamed from: component4, reason: from getter */
    public final float getScaleFactorY() {
        return this.scaleFactorY;
    }

    /* renamed from: component5, reason: from getter */
    public final int getFrameRate() {
        return this.frameRate;
    }

    /* renamed from: component6, reason: from getter */
    public final int getBitRate() {
        return this.bitRate;
    }

    public final ScreenshotRecorderConfig copy(int recordingWidth, int recordingHeight, float scaleFactorX, float scaleFactorY, int frameRate, int bitRate) {
        return new ScreenshotRecorderConfig(recordingWidth, recordingHeight, scaleFactorX, scaleFactorY, frameRate, bitRate);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ScreenshotRecorderConfig)) {
            return false;
        }
        ScreenshotRecorderConfig screenshotRecorderConfig = (ScreenshotRecorderConfig) other;
        return this.recordingWidth == screenshotRecorderConfig.recordingWidth && this.recordingHeight == screenshotRecorderConfig.recordingHeight && Float.compare(this.scaleFactorX, screenshotRecorderConfig.scaleFactorX) == 0 && Float.compare(this.scaleFactorY, screenshotRecorderConfig.scaleFactorY) == 0 && this.frameRate == screenshotRecorderConfig.frameRate && this.bitRate == screenshotRecorderConfig.bitRate;
    }

    public final int getBitRate() {
        return this.bitRate;
    }

    public final int getFrameRate() {
        return this.frameRate;
    }

    public final int getRecordingHeight() {
        return this.recordingHeight;
    }

    public final int getRecordingWidth() {
        return this.recordingWidth;
    }

    public final float getScaleFactorX() {
        return this.scaleFactorX;
    }

    public final float getScaleFactorY() {
        return this.scaleFactorY;
    }

    public int hashCode() {
        return (((((((((Integer.hashCode(this.recordingWidth) * 31) + Integer.hashCode(this.recordingHeight)) * 31) + Float.hashCode(this.scaleFactorX)) * 31) + Float.hashCode(this.scaleFactorY)) * 31) + Integer.hashCode(this.frameRate)) * 31) + Integer.hashCode(this.bitRate);
    }

    public String toString() {
        return "ScreenshotRecorderConfig(recordingWidth=" + this.recordingWidth + ", recordingHeight=" + this.recordingHeight + ", scaleFactorX=" + this.scaleFactorX + ", scaleFactorY=" + this.scaleFactorY + ", frameRate=" + this.frameRate + ", bitRate=" + this.bitRate + ')';
    }

    public ScreenshotRecorderConfig(int i, int i2, float f, float f2, int i3, int i4) {
        this.recordingWidth = i;
        this.recordingHeight = i2;
        this.scaleFactorX = f;
        this.scaleFactorY = f2;
        this.frameRate = i3;
        this.bitRate = i4;
    }

    public ScreenshotRecorderConfig(float f, float f2) {
        this(0, 0, f, f2, 0, 0);
    }

    /* compiled from: ScreenshotRecorder.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\f\u0010\t\u001a\u00020\n*\u00020\nH\u0002¨\u0006\u000b"}, d2 = {"Lio/sentry/android/replay/ScreenshotRecorderConfig$Companion;", "", "()V", Constants.MessagePayloadKeys.FROM, "Lio/sentry/android/replay/ScreenshotRecorderConfig;", "context", "Landroid/content/Context;", "sessionReplay", "Lio/sentry/SentryReplayOptions;", "adjustToBlockSize", "", "sentry-android-replay_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        private final int adjustToBlockSize(int i) {
            int i2 = i % 16;
            return i2 <= 8 ? i - i2 : i + (16 - i2);
        }

        public final ScreenshotRecorderConfig from(Context context, SentryReplayOptions sessionReplay) {
            Rect rect;
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(sessionReplay, "sessionReplay");
            Object systemService = context.getSystemService("window");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.WindowManager");
            WindowManager windowManager = (WindowManager) systemService;
            if (Build.VERSION.SDK_INT >= 30) {
                rect = windowManager.getCurrentWindowMetrics().getBounds();
            } else {
                Point point = new Point();
                windowManager.getDefaultDisplay().getRealSize(point);
                rect = new Rect(0, 0, point.x, point.y);
            }
            Intrinsics.checkNotNullExpressionValue(rect, "if (VERSION.SDK_INT >= V…enBounds.y)\n            }");
            Pair pair = TuplesKt.to(Integer.valueOf(adjustToBlockSize(MathKt.roundToInt((rect.height() / context.getResources().getDisplayMetrics().density) * sessionReplay.getQuality().sizeScale))), Integer.valueOf(adjustToBlockSize(MathKt.roundToInt((rect.width() / context.getResources().getDisplayMetrics().density) * sessionReplay.getQuality().sizeScale))));
            int iIntValue = ((Number) pair.component1()).intValue();
            int iIntValue2 = ((Number) pair.component2()).intValue();
            return new ScreenshotRecorderConfig(iIntValue2, iIntValue, iIntValue2 / rect.width(), iIntValue / rect.height(), sessionReplay.getFrameRate(), sessionReplay.getQuality().bitRate);
        }
    }
}
