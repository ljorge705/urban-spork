package com.onfido.android.sdk.capture.internal.camera.camerax;

import androidx.camera.view.PreviewView;
import com.clevertap.android.sdk.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B#\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\u0016\u0010\u0012\u001a\u00020\u0007HÆ\u0003ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\nJ1\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\u00052\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001R\u0019\u0010\u0006\u001a\u00020\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u001d"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/camera/camerax/PreviewConfig;", "", "scaleType", "Landroidx/camera/view/PreviewView$ScaleType;", "viewPortEnabled", "", "frameSamplingPeriod", "Lkotlin/time/Duration;", "(Landroidx/camera/view/PreviewView$ScaleType;ZJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getFrameSamplingPeriod-UwyO8pc", "()J", "J", "getScaleType", "()Landroidx/camera/view/PreviewView$ScaleType;", "getViewPortEnabled", "()Z", "component1", "component2", "component3", "component3-UwyO8pc", Constants.COPY_TYPE, "copy-SxA4cEA", "(Landroidx/camera/view/PreviewView$ScaleType;ZJ)Lcom/onfido/android/sdk/capture/internal/camera/camerax/PreviewConfig;", "equals", "other", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class PreviewConfig {
    private final long frameSamplingPeriod;
    private final PreviewView.ScaleType scaleType;
    private final boolean viewPortEnabled;

    private PreviewConfig(PreviewView.ScaleType scaleType, boolean z, long j) {
        Intrinsics.checkNotNullParameter(scaleType, "scaleType");
        this.scaleType = scaleType;
        this.viewPortEnabled = z;
        this.frameSamplingPeriod = j;
    }

    /* renamed from: copy-SxA4cEA$default, reason: not valid java name */
    public static /* synthetic */ PreviewConfig m5589copySxA4cEA$default(PreviewConfig previewConfig, PreviewView.ScaleType scaleType, boolean z, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            scaleType = previewConfig.scaleType;
        }
        if ((i & 2) != 0) {
            z = previewConfig.viewPortEnabled;
        }
        if ((i & 4) != 0) {
            j = previewConfig.frameSamplingPeriod;
        }
        return previewConfig.m5591copySxA4cEA(scaleType, z, j);
    }

    /* renamed from: component1, reason: from getter */
    public final PreviewView.ScaleType getScaleType() {
        return this.scaleType;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getViewPortEnabled() {
        return this.viewPortEnabled;
    }

    /* renamed from: component3-UwyO8pc, reason: not valid java name and from getter */
    public final long getFrameSamplingPeriod() {
        return this.frameSamplingPeriod;
    }

    /* renamed from: copy-SxA4cEA, reason: not valid java name */
    public final PreviewConfig m5591copySxA4cEA(PreviewView.ScaleType scaleType, boolean viewPortEnabled, long frameSamplingPeriod) {
        Intrinsics.checkNotNullParameter(scaleType, "scaleType");
        return new PreviewConfig(scaleType, viewPortEnabled, frameSamplingPeriod, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PreviewConfig)) {
            return false;
        }
        PreviewConfig previewConfig = (PreviewConfig) other;
        return this.scaleType == previewConfig.scaleType && this.viewPortEnabled == previewConfig.viewPortEnabled && Duration.m7427equalsimpl0(this.frameSamplingPeriod, previewConfig.frameSamplingPeriod);
    }

    /* renamed from: getFrameSamplingPeriod-UwyO8pc, reason: not valid java name */
    public final long m5592getFrameSamplingPeriodUwyO8pc() {
        return this.frameSamplingPeriod;
    }

    public final PreviewView.ScaleType getScaleType() {
        return this.scaleType;
    }

    public final boolean getViewPortEnabled() {
        return this.viewPortEnabled;
    }

    public int hashCode() {
        return (((this.scaleType.hashCode() * 31) + Boolean.hashCode(this.viewPortEnabled)) * 31) + Duration.m7450hashCodeimpl(this.frameSamplingPeriod);
    }

    public String toString() {
        return "PreviewConfig(scaleType=" + this.scaleType + ", viewPortEnabled=" + this.viewPortEnabled + ", frameSamplingPeriod=" + ((Object) Duration.m7471toStringimpl(this.frameSamplingPeriod)) + ')';
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ PreviewConfig(PreviewView.ScaleType scaleType, boolean z, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        PreviewView.ScaleType scaleType2 = (i & 1) != 0 ? PreviewView.ScaleType.FILL_CENTER : scaleType;
        boolean z2 = (i & 2) != 0 ? true : z;
        if ((i & 4) != 0) {
            Duration.Companion companion = Duration.INSTANCE;
            j = DurationKt.toDuration(100, DurationUnit.MILLISECONDS);
        }
        this(scaleType2, z2, j, null);
    }

    public /* synthetic */ PreviewConfig(PreviewView.ScaleType scaleType, boolean z, long j, DefaultConstructorMarker defaultConstructorMarker) {
        this(scaleType, z, j);
    }
}
