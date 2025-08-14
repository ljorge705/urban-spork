package com.onfido.android.sdk.capture.validation.device;

import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.utils.OnfidoExtensionsKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u0000\n\u0002\b\u0006\b\u0080\b\u0018\u00002\u00020\u0001B/\u0012\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0015\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0014\u001a\u00020\tHÆ\u0003J3\u0010\u0015\u001a\u00020\u00002\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00072\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0096\u0002J\u000f\u0010\u0019\u001a\u0004\u0018\u00010\u0007H\u0016¢\u0006\u0002\u0010\u001aJ\b\u0010\u001b\u001a\u00020\tH\u0016J\b\u0010\u001c\u001a\u00020\u0007H\u0016J\t\u0010\u001d\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001e"}, d2 = {"Lcom/onfido/android/sdk/capture/validation/device/MRZExtractionResult;", "Lcom/onfido/android/sdk/capture/validation/device/OnDeviceValidationResult;", "mrzArray", "", "", "", "wasExecuted", "", "expectedLinage", "", "(Ljava/util/List;ZI)V", "getExpectedLinage", "()I", "isMRZExtracted", "getMrzArray", "()Ljava/util/List;", "getWasExecuted", "()Z", "component1", "component2", "component3", Constants.COPY_TYPE, "equals", "other", "", "executionResult", "()Ljava/lang/Boolean;", "hashCode", "isValid", "toString", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class MRZExtractionResult extends OnDeviceValidationResult {
    private final int expectedLinage;
    private final boolean isMRZExtracted;
    private final List<String[]> mrzArray;
    private final boolean wasExecuted;

    public MRZExtractionResult() {
        this(null, false, 0, 7, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ MRZExtractionResult copy$default(MRZExtractionResult mRZExtractionResult, List list, boolean z, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            list = mRZExtractionResult.mrzArray;
        }
        if ((i2 & 2) != 0) {
            z = mRZExtractionResult.wasExecuted;
        }
        if ((i2 & 4) != 0) {
            i = mRZExtractionResult.expectedLinage;
        }
        return mRZExtractionResult.copy(list, z, i);
    }

    public final List<String[]> component1() {
        return this.mrzArray;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getWasExecuted() {
        return this.wasExecuted;
    }

    /* renamed from: component3, reason: from getter */
    public final int getExpectedLinage() {
        return this.expectedLinage;
    }

    public final MRZExtractionResult copy(List<String[]> mrzArray, boolean wasExecuted, int expectedLinage) {
        Intrinsics.checkNotNullParameter(mrzArray, "mrzArray");
        return new MRZExtractionResult(mrzArray, wasExecuted, expectedLinage);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof MRZExtractionResult) && getWasExecuted() == ((MRZExtractionResult) other).getWasExecuted();
    }

    @Override // com.onfido.android.sdk.capture.validation.device.OnDeviceValidationResult
    public Boolean executionResult() {
        if (getWasExecuted()) {
            return Boolean.valueOf(this.isMRZExtracted);
        }
        return null;
    }

    public final int getExpectedLinage() {
        return this.expectedLinage;
    }

    public final List<String[]> getMrzArray() {
        return this.mrzArray;
    }

    @Override // com.onfido.android.sdk.capture.validation.device.OnDeviceValidationResult
    public boolean getWasExecuted() {
        return this.wasExecuted;
    }

    public int hashCode() {
        return OnfidoExtensionsKt.hashCode(this.mrzArray, Boolean.valueOf(getWasExecuted()));
    }

    @Override // com.onfido.android.sdk.capture.validation.device.OnDeviceValidationResult
    public boolean isValid() {
        return !getWasExecuted() || this.isMRZExtracted;
    }

    public String toString() {
        return "MRZExtractionResult(mrzArray=" + this.mrzArray + ", wasExecuted=" + this.wasExecuted + ", expectedLinage=" + this.expectedLinage + ')';
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public MRZExtractionResult(java.util.List<java.lang.String[]> r2, boolean r3, int r4) {
        /*
            r1 = this;
            java.lang.String r0 = "mrzArray"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            r1.<init>(r3)
            r1.mrzArray = r2
            r1.wasExecuted = r3
            r1.expectedLinage = r4
            boolean r3 = r1.getWasExecuted()
            if (r3 == 0) goto L26
            boolean r3 = r2.isEmpty()
            r0 = 1
            r3 = r3 ^ r0
            if (r3 == 0) goto L26
            java.lang.Object r2 = kotlin.collections.CollectionsKt.first(r2)
            java.lang.Object[] r2 = (java.lang.Object[]) r2
            int r2 = r2.length
            if (r2 != r4) goto L26
            goto L27
        L26:
            r0 = 0
        L27:
            r1.isMRZExtracted = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.validation.device.MRZExtractionResult.<init>(java.util.List, boolean, int):void");
    }

    public /* synthetic */ MRZExtractionResult(List list, boolean z, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? CollectionsKt.arrayListOf(new String[0]) : list, (i2 & 2) != 0 ? false : z, (i2 & 4) != 0 ? 2 : i);
    }
}
