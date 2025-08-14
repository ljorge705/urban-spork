package com.onfido.android.sdk.capture.config;

import com.onfido.reactnative.sdk.ReactNativeBridgeUtiles;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MediaCallback.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0016R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0010"}, d2 = {"Lcom/onfido/android/sdk/capture/config/DocumentMetadata;", "", ReactNativeBridgeUtiles.KEY_DOCUMENT_SIDE, "", "type", ReactNativeBridgeUtiles.KEY_DOCUMENT_ISSUING_COUNTRY, "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getIssuingCountry", "()Ljava/lang/String;", "getSide", "getType", "equals", "", "other", "hashCode", "", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DocumentMetadata {
    private final String issuingCountry;
    private final String side;
    private final String type;

    public final String getIssuingCountry() {
        return this.issuingCountry;
    }

    public final String getSide() {
        return this.side;
    }

    public final String getType() {
        return this.type;
    }

    public DocumentMetadata(String side, String type, String str) {
        Intrinsics.checkNotNullParameter(side, "side");
        Intrinsics.checkNotNullParameter(type, "type");
        this.side = side;
        this.type = type;
        this.issuingCountry = str;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null)) {
            return false;
        }
        Intrinsics.checkNotNull(other, "null cannot be cast to non-null type com.onfido.android.sdk.capture.config.DocumentMetadata");
        DocumentMetadata documentMetadata = (DocumentMetadata) other;
        return Intrinsics.areEqual(this.side, documentMetadata.side) && Intrinsics.areEqual(this.type, documentMetadata.type) && Intrinsics.areEqual(this.issuingCountry, documentMetadata.issuingCountry);
    }

    public int hashCode() {
        int iHashCode = ((this.side.hashCode() * 31) + this.type.hashCode()) * 31;
        String str = this.issuingCountry;
        return iHashCode + (str != null ? str.hashCode() : 0);
    }
}
