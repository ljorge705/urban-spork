package com.onfido.android.sdk.capture.edge_detector;

import com.clevertap.android.sdk.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J1\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00032\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\f\u0010\u001c\u001a\u00020\u0019*\u00020\u0003H\u0002R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\f\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\r\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\t¨\u0006\u001d"}, d2 = {"Lcom/onfido/android/sdk/capture/edge_detector/EdgeDetectionResults;", "", "topEdgeDetected", "", "leftEdgeDetected", "bottomEdgeDetected", "rightEdgeDetected", "(ZZZZ)V", "getBottomEdgeDetected", "()Z", "hasAny", "getHasAny", "hasMost", "getHasMost", "getLeftEdgeDetected", "getRightEdgeDetected", "getTopEdgeDetected", "component1", "component2", "component3", "component4", Constants.COPY_TYPE, "equals", "other", "hashCode", "", "toString", "", "toInt", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class EdgeDetectionResults {
    private final boolean bottomEdgeDetected;
    private final boolean leftEdgeDetected;
    private final boolean rightEdgeDetected;
    private final boolean topEdgeDetected;

    public EdgeDetectionResults() {
        this(false, false, false, false, 15, null);
    }

    public static /* synthetic */ EdgeDetectionResults copy$default(EdgeDetectionResults edgeDetectionResults, boolean z, boolean z2, boolean z3, boolean z4, int i, Object obj) {
        if ((i & 1) != 0) {
            z = edgeDetectionResults.topEdgeDetected;
        }
        if ((i & 2) != 0) {
            z2 = edgeDetectionResults.leftEdgeDetected;
        }
        if ((i & 4) != 0) {
            z3 = edgeDetectionResults.bottomEdgeDetected;
        }
        if ((i & 8) != 0) {
            z4 = edgeDetectionResults.rightEdgeDetected;
        }
        return edgeDetectionResults.copy(z, z2, z3, z4);
    }

    private final int toInt(boolean z) {
        return z ? 1 : 0;
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getTopEdgeDetected() {
        return this.topEdgeDetected;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getLeftEdgeDetected() {
        return this.leftEdgeDetected;
    }

    /* renamed from: component3, reason: from getter */
    public final boolean getBottomEdgeDetected() {
        return this.bottomEdgeDetected;
    }

    /* renamed from: component4, reason: from getter */
    public final boolean getRightEdgeDetected() {
        return this.rightEdgeDetected;
    }

    public final EdgeDetectionResults copy(boolean topEdgeDetected, boolean leftEdgeDetected, boolean bottomEdgeDetected, boolean rightEdgeDetected) {
        return new EdgeDetectionResults(topEdgeDetected, leftEdgeDetected, bottomEdgeDetected, rightEdgeDetected);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EdgeDetectionResults)) {
            return false;
        }
        EdgeDetectionResults edgeDetectionResults = (EdgeDetectionResults) other;
        return this.topEdgeDetected == edgeDetectionResults.topEdgeDetected && this.leftEdgeDetected == edgeDetectionResults.leftEdgeDetected && this.bottomEdgeDetected == edgeDetectionResults.bottomEdgeDetected && this.rightEdgeDetected == edgeDetectionResults.rightEdgeDetected;
    }

    public final boolean getBottomEdgeDetected() {
        return this.bottomEdgeDetected;
    }

    public final boolean getHasAny() {
        return this.topEdgeDetected || this.leftEdgeDetected || this.bottomEdgeDetected || this.rightEdgeDetected;
    }

    public final boolean getHasMost() {
        return ((toInt(this.topEdgeDetected) + toInt(this.leftEdgeDetected)) + toInt(this.bottomEdgeDetected)) + toInt(this.rightEdgeDetected) >= 3;
    }

    public final boolean getLeftEdgeDetected() {
        return this.leftEdgeDetected;
    }

    public final boolean getRightEdgeDetected() {
        return this.rightEdgeDetected;
    }

    public final boolean getTopEdgeDetected() {
        return this.topEdgeDetected;
    }

    public int hashCode() {
        return (((((Boolean.hashCode(this.topEdgeDetected) * 31) + Boolean.hashCode(this.leftEdgeDetected)) * 31) + Boolean.hashCode(this.bottomEdgeDetected)) * 31) + Boolean.hashCode(this.rightEdgeDetected);
    }

    public String toString() {
        return "EdgeDetectionResults(topEdgeDetected=" + this.topEdgeDetected + ", leftEdgeDetected=" + this.leftEdgeDetected + ", bottomEdgeDetected=" + this.bottomEdgeDetected + ", rightEdgeDetected=" + this.rightEdgeDetected + ')';
    }

    public EdgeDetectionResults(boolean z, boolean z2, boolean z3, boolean z4) {
        this.topEdgeDetected = z;
        this.leftEdgeDetected = z2;
        this.bottomEdgeDetected = z3;
        this.rightEdgeDetected = z4;
    }

    public /* synthetic */ EdgeDetectionResults(boolean z, boolean z2, boolean z3, boolean z4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? false : z2, (i & 4) != 0 ? false : z3, (i & 8) != 0 ? false : z4);
    }
}
