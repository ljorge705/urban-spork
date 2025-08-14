package com.onfido.api.client.data;

import com.clevertap.android.sdk.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DocumentMediaIntegrity.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\u000b\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0003J!\u0010\u000b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/onfido/api/client/data/DocumentMediaIntegrity;", "", "signatureBase64", "", "clientNonce", "(Ljava/lang/String;Ljava/lang/String;)V", "getClientNonce", "()Ljava/lang/String;", "getSignatureBase64", "component1", "component2", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "", "toString", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final /* data */ class DocumentMediaIntegrity {
    private final String clientNonce;
    private final String signatureBase64;

    public static /* synthetic */ DocumentMediaIntegrity copy$default(DocumentMediaIntegrity documentMediaIntegrity, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = documentMediaIntegrity.signatureBase64;
        }
        if ((i & 2) != 0) {
            str2 = documentMediaIntegrity.clientNonce;
        }
        return documentMediaIntegrity.copy(str, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getSignatureBase64() {
        return this.signatureBase64;
    }

    /* renamed from: component2, reason: from getter */
    public final String getClientNonce() {
        return this.clientNonce;
    }

    public final DocumentMediaIntegrity copy(String signatureBase64, String clientNonce) {
        return new DocumentMediaIntegrity(signatureBase64, clientNonce);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DocumentMediaIntegrity)) {
            return false;
        }
        DocumentMediaIntegrity documentMediaIntegrity = (DocumentMediaIntegrity) other;
        return Intrinsics.areEqual(this.signatureBase64, documentMediaIntegrity.signatureBase64) && Intrinsics.areEqual(this.clientNonce, documentMediaIntegrity.clientNonce);
    }

    public final String getClientNonce() {
        return this.clientNonce;
    }

    public final String getSignatureBase64() {
        return this.signatureBase64;
    }

    public int hashCode() {
        String str = this.signatureBase64;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.clientNonce;
        return iHashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "DocumentMediaIntegrity(signatureBase64=" + this.signatureBase64 + ", clientNonce=" + this.clientNonce + ")";
    }

    public DocumentMediaIntegrity(String str, String str2) {
        this.signatureBase64 = str;
        this.clientNonce = str2;
    }
}
