package com.onfido.android.sdk.capture.validation.device;

import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.edge_detector.EdgeDetectionResults;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0003J\u000f\u0010\u0011\u001a\u0004\u0018\u00010\u0005H\u0016¢\u0006\u0002\u0010\u0012J\u0006\u0010\u0013\u001a\u00020\u0005J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\b\u0010\u0016\u001a\u00020\u0005H\u0016J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0019"}, d2 = {"Lcom/onfido/android/sdk/capture/validation/device/EdgeDetectionValidationResult;", "Lcom/onfido/android/sdk/capture/validation/device/OnDeviceValidationResult;", "edges", "Lcom/onfido/android/sdk/capture/edge_detector/EdgeDetectionResults;", "wasExecuted", "", "(Lcom/onfido/android/sdk/capture/edge_detector/EdgeDetectionResults;Z)V", "getEdges", "()Lcom/onfido/android/sdk/capture/edge_detector/EdgeDetectionResults;", "getWasExecuted", "()Z", "component1", "component2", Constants.COPY_TYPE, "equals", "other", "", "executionResult", "()Ljava/lang/Boolean;", "hasAny", "hashCode", "", "isValid", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class EdgeDetectionValidationResult extends OnDeviceValidationResult {
    private final EdgeDetectionResults edges;
    private final boolean wasExecuted;

    /* JADX WARN: Multi-variable type inference failed */
    public EdgeDetectionValidationResult() {
        this(null, false, 3, 0 == true ? 1 : 0);
    }

    public static /* synthetic */ EdgeDetectionValidationResult copy$default(EdgeDetectionValidationResult edgeDetectionValidationResult, EdgeDetectionResults edgeDetectionResults, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            edgeDetectionResults = edgeDetectionValidationResult.edges;
        }
        if ((i & 2) != 0) {
            z = edgeDetectionValidationResult.wasExecuted;
        }
        return edgeDetectionValidationResult.copy(edgeDetectionResults, z);
    }

    /* renamed from: component1, reason: from getter */
    public final EdgeDetectionResults getEdges() {
        return this.edges;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getWasExecuted() {
        return this.wasExecuted;
    }

    public final EdgeDetectionValidationResult copy(EdgeDetectionResults edges, boolean wasExecuted) {
        Intrinsics.checkNotNullParameter(edges, "edges");
        return new EdgeDetectionValidationResult(edges, wasExecuted);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EdgeDetectionValidationResult)) {
            return false;
        }
        EdgeDetectionValidationResult edgeDetectionValidationResult = (EdgeDetectionValidationResult) other;
        return Intrinsics.areEqual(this.edges, edgeDetectionValidationResult.edges) && this.wasExecuted == edgeDetectionValidationResult.wasExecuted;
    }

    @Override // com.onfido.android.sdk.capture.validation.device.OnDeviceValidationResult
    public Boolean executionResult() {
        if (getWasExecuted()) {
            return Boolean.valueOf(hasAny());
        }
        return null;
    }

    public final EdgeDetectionResults getEdges() {
        return this.edges;
    }

    @Override // com.onfido.android.sdk.capture.validation.device.OnDeviceValidationResult
    public boolean getWasExecuted() {
        return this.wasExecuted;
    }

    public final boolean hasAny() {
        return getWasExecuted() && this.edges.getHasAny();
    }

    public int hashCode() {
        return (this.edges.hashCode() * 31) + Boolean.hashCode(this.wasExecuted);
    }

    @Override // com.onfido.android.sdk.capture.validation.device.OnDeviceValidationResult
    public boolean isValid() {
        return !getWasExecuted() || this.edges.getHasMost();
    }

    public String toString() {
        return "EdgeDetectionValidationResult(edges=" + this.edges + ", wasExecuted=" + this.wasExecuted + ')';
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EdgeDetectionValidationResult(EdgeDetectionResults edges, boolean z) {
        super(z);
        Intrinsics.checkNotNullParameter(edges, "edges");
        this.edges = edges;
        this.wasExecuted = z;
    }

    public /* synthetic */ EdgeDetectionValidationResult(EdgeDetectionResults edgeDetectionResults, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new EdgeDetectionResults(false, false, false, false, 15, null) : edgeDetectionResults, (i & 2) != 0 ? false : z);
    }
}
