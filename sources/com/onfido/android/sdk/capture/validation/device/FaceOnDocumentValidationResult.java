package com.onfido.android.sdk.capture.validation.device;

import com.clevertap.android.sdk.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u0010\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J$\u0010\f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001¢\u0006\u0002\u0010\rJ\u0013\u0010\u000e\u001a\u00020\u00032\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0003J\u000f\u0010\u0011\u001a\u0004\u0018\u00010\u0003H\u0016¢\u0006\u0002\u0010\u0006J\b\u0010\u0012\u001a\u00020\u0003H\u0002J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\b\u0010\u0015\u001a\u00020\u0003H\u0016J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0002\u0010\u0006R\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0018"}, d2 = {"Lcom/onfido/android/sdk/capture/validation/device/FaceOnDocumentValidationResult;", "Lcom/onfido/android/sdk/capture/validation/device/OnDeviceValidationResult;", "isFaceOnDocument", "", "wasExecuted", "(Ljava/lang/Boolean;Z)V", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getWasExecuted", "()Z", "component1", "component2", Constants.COPY_TYPE, "(Ljava/lang/Boolean;Z)Lcom/onfido/android/sdk/capture/validation/device/FaceOnDocumentValidationResult;", "equals", "other", "", "executionResult", "hasFaceOnDocument", "hashCode", "", "isValid", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class FaceOnDocumentValidationResult extends OnDeviceValidationResult {
    private final Boolean isFaceOnDocument;
    private final boolean wasExecuted;

    /* JADX WARN: Multi-variable type inference failed */
    public FaceOnDocumentValidationResult() {
        this(null, false, 3, 0 == true ? 1 : 0);
    }

    public static /* synthetic */ FaceOnDocumentValidationResult copy$default(FaceOnDocumentValidationResult faceOnDocumentValidationResult, Boolean bool, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            bool = faceOnDocumentValidationResult.isFaceOnDocument;
        }
        if ((i & 2) != 0) {
            z = faceOnDocumentValidationResult.wasExecuted;
        }
        return faceOnDocumentValidationResult.copy(bool, z);
    }

    private final boolean hasFaceOnDocument() {
        Boolean bool = this.isFaceOnDocument;
        return bool != null && bool.booleanValue();
    }

    /* renamed from: component1, reason: from getter */
    public final Boolean getIsFaceOnDocument() {
        return this.isFaceOnDocument;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getWasExecuted() {
        return this.wasExecuted;
    }

    public final FaceOnDocumentValidationResult copy(Boolean isFaceOnDocument, boolean wasExecuted) {
        return new FaceOnDocumentValidationResult(isFaceOnDocument, wasExecuted);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FaceOnDocumentValidationResult)) {
            return false;
        }
        FaceOnDocumentValidationResult faceOnDocumentValidationResult = (FaceOnDocumentValidationResult) other;
        return Intrinsics.areEqual(this.isFaceOnDocument, faceOnDocumentValidationResult.isFaceOnDocument) && this.wasExecuted == faceOnDocumentValidationResult.wasExecuted;
    }

    @Override // com.onfido.android.sdk.capture.validation.device.OnDeviceValidationResult
    public Boolean executionResult() {
        if (getWasExecuted()) {
            return Boolean.valueOf(hasFaceOnDocument());
        }
        return null;
    }

    @Override // com.onfido.android.sdk.capture.validation.device.OnDeviceValidationResult
    public boolean getWasExecuted() {
        return this.wasExecuted;
    }

    public int hashCode() {
        Boolean bool = this.isFaceOnDocument;
        return ((bool == null ? 0 : bool.hashCode()) * 31) + Boolean.hashCode(this.wasExecuted);
    }

    public final Boolean isFaceOnDocument() {
        return this.isFaceOnDocument;
    }

    @Override // com.onfido.android.sdk.capture.validation.device.OnDeviceValidationResult
    public boolean isValid() {
        return !getWasExecuted() || hasFaceOnDocument();
    }

    public String toString() {
        return "FaceOnDocumentValidationResult(isFaceOnDocument=" + this.isFaceOnDocument + ", wasExecuted=" + this.wasExecuted + ')';
    }

    public FaceOnDocumentValidationResult(Boolean bool, boolean z) {
        super(z);
        this.isFaceOnDocument = bool;
        this.wasExecuted = z;
    }

    public /* synthetic */ FaceOnDocumentValidationResult(Boolean bool, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : bool, (i & 2) != 0 ? false : z);
    }
}
