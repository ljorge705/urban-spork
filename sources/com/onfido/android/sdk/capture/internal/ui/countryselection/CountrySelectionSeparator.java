package com.onfido.android.sdk.capture.internal.ui.countryselection;

import com.clevertap.android.sdk.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/ui/countryselection/CountrySelectionSeparator;", "Lcom/onfido/android/sdk/capture/internal/ui/countryselection/BaseAdapterItem;", "type", "Lcom/onfido/android/sdk/capture/internal/ui/countryselection/CountrySelectionSeparatorType;", "(Lcom/onfido/android/sdk/capture/internal/ui/countryselection/CountrySelectionSeparatorType;)V", "getType", "()Lcom/onfido/android/sdk/capture/internal/ui/countryselection/CountrySelectionSeparatorType;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class CountrySelectionSeparator extends BaseAdapterItem {
    private final CountrySelectionSeparatorType type;

    public CountrySelectionSeparator(CountrySelectionSeparatorType type) {
        Intrinsics.checkNotNullParameter(type, "type");
        this.type = type;
    }

    public static /* synthetic */ CountrySelectionSeparator copy$default(CountrySelectionSeparator countrySelectionSeparator, CountrySelectionSeparatorType countrySelectionSeparatorType, int i, Object obj) {
        if ((i & 1) != 0) {
            countrySelectionSeparatorType = countrySelectionSeparator.type;
        }
        return countrySelectionSeparator.copy(countrySelectionSeparatorType);
    }

    /* renamed from: component1, reason: from getter */
    public final CountrySelectionSeparatorType getType() {
        return this.type;
    }

    public final CountrySelectionSeparator copy(CountrySelectionSeparatorType type) {
        Intrinsics.checkNotNullParameter(type, "type");
        return new CountrySelectionSeparator(type);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof CountrySelectionSeparator) && this.type == ((CountrySelectionSeparator) other).type;
    }

    public final CountrySelectionSeparatorType getType() {
        return this.type;
    }

    public int hashCode() {
        return this.type.hashCode();
    }

    public String toString() {
        return "CountrySelectionSeparator(type=" + this.type + ')';
    }
}
