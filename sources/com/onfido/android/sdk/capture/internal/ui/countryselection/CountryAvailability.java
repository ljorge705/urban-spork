package com.onfido.android.sdk.capture.internal.ui.countryselection;

import com.clevertap.android.sdk.Constants;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.onfido.android.sdk.capture.utils.CountryCode;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/ui/countryselection/CountryAvailability;", "Lcom/onfido/android/sdk/capture/internal/ui/countryselection/BaseAdapterItem;", RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE, "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "(Lcom/onfido/android/sdk/capture/utils/CountryCode;)V", "getCountryCode", "()Lcom/onfido/android/sdk/capture/utils/CountryCode;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class CountryAvailability extends BaseAdapterItem {
    private final CountryCode countryCode;

    public CountryAvailability(CountryCode countryCode) {
        Intrinsics.checkNotNullParameter(countryCode, "countryCode");
        this.countryCode = countryCode;
    }

    public static /* synthetic */ CountryAvailability copy$default(CountryAvailability countryAvailability, CountryCode countryCode, int i, Object obj) {
        if ((i & 1) != 0) {
            countryCode = countryAvailability.countryCode;
        }
        return countryAvailability.copy(countryCode);
    }

    /* renamed from: component1, reason: from getter */
    public final CountryCode getCountryCode() {
        return this.countryCode;
    }

    public final CountryAvailability copy(CountryCode countryCode) {
        Intrinsics.checkNotNullParameter(countryCode, "countryCode");
        return new CountryAvailability(countryCode);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof CountryAvailability) && this.countryCode == ((CountryAvailability) other).countryCode;
    }

    public final CountryCode getCountryCode() {
        return this.countryCode;
    }

    public int hashCode() {
        return this.countryCode.hashCode();
    }

    public String toString() {
        return "CountryAvailability(countryCode=" + this.countryCode + ')';
    }
}
