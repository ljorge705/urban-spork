package com.onfido.android.sdk.capture.internal.camera.camerax;

import com.clevertap.android.sdk.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\b\u001a\u00020\u0003HÆ\u0003ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\t\u0010\u0006J\u001d\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\fJ\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0019\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0014"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/camera/camerax/ImageAnalysisConfig;", "", "imageAnalysisFrequency", "Lkotlin/time/Duration;", "(JLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getImageAnalysisFrequency-UwyO8pc", "()J", "J", "component1", "component1-UwyO8pc", Constants.COPY_TYPE, "copy-LRDsOJo", "(J)Lcom/onfido/android/sdk/capture/internal/camera/camerax/ImageAnalysisConfig;", "equals", "", "other", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class ImageAnalysisConfig {
    private final long imageAnalysisFrequency;

    private ImageAnalysisConfig(long j) {
        this.imageAnalysisFrequency = j;
    }

    /* renamed from: copy-LRDsOJo$default, reason: not valid java name */
    public static /* synthetic */ ImageAnalysisConfig m5585copyLRDsOJo$default(ImageAnalysisConfig imageAnalysisConfig, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = imageAnalysisConfig.imageAnalysisFrequency;
        }
        return imageAnalysisConfig.m5587copyLRDsOJo(j);
    }

    /* renamed from: component1-UwyO8pc, reason: not valid java name and from getter */
    public final long getImageAnalysisFrequency() {
        return this.imageAnalysisFrequency;
    }

    /* renamed from: copy-LRDsOJo, reason: not valid java name */
    public final ImageAnalysisConfig m5587copyLRDsOJo(long imageAnalysisFrequency) {
        return new ImageAnalysisConfig(imageAnalysisFrequency, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof ImageAnalysisConfig) && Duration.m7427equalsimpl0(this.imageAnalysisFrequency, ((ImageAnalysisConfig) other).imageAnalysisFrequency);
    }

    /* renamed from: getImageAnalysisFrequency-UwyO8pc, reason: not valid java name */
    public final long m5588getImageAnalysisFrequencyUwyO8pc() {
        return this.imageAnalysisFrequency;
    }

    public int hashCode() {
        return Duration.m7450hashCodeimpl(this.imageAnalysisFrequency);
    }

    public String toString() {
        return "ImageAnalysisConfig(imageAnalysisFrequency=" + ((Object) Duration.m7471toStringimpl(this.imageAnalysisFrequency)) + ')';
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ ImageAnalysisConfig(long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            Duration.Companion companion = Duration.INSTANCE;
            j = DurationKt.toDuration(100, DurationUnit.MILLISECONDS);
        }
        this(j, null);
    }

    public /* synthetic */ ImageAnalysisConfig(long j, DefaultConstructorMarker defaultConstructorMarker) {
        this(j);
    }
}
