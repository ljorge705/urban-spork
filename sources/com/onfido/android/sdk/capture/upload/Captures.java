package com.onfido.android.sdk.capture.upload;

import com.clevertap.android.sdk.Constants;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Captures.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000 !2\u00020\u0001:\u0001!B)\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0007HÆ\u0003J-\u0010\u0018\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cHÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\t\u0010\u001f\u001a\u00020 HÖ\u0001R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\""}, d2 = {"Lcom/onfido/android/sdk/capture/upload/Captures;", "Ljava/io/Serializable;", "document", "Lcom/onfido/android/sdk/capture/upload/Document;", OptionalModuleUtils.FACE, "Lcom/onfido/android/sdk/capture/upload/Face;", "poa", "Lcom/onfido/android/sdk/capture/upload/ProofOfAddress;", "(Lcom/onfido/android/sdk/capture/upload/Document;Lcom/onfido/android/sdk/capture/upload/Face;Lcom/onfido/android/sdk/capture/upload/ProofOfAddress;)V", "getDocument", "()Lcom/onfido/android/sdk/capture/upload/Document;", "setDocument", "(Lcom/onfido/android/sdk/capture/upload/Document;)V", "getFace", "()Lcom/onfido/android/sdk/capture/upload/Face;", "setFace", "(Lcom/onfido/android/sdk/capture/upload/Face;)V", "getPoa", "()Lcom/onfido/android/sdk/capture/upload/ProofOfAddress;", "setPoa", "(Lcom/onfido/android/sdk/capture/upload/ProofOfAddress;)V", "component1", "component2", "component3", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "", "Companion", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class Captures implements Serializable {
    private static final long serialVersionUID = 1;
    private Document document;
    private Face face;
    private ProofOfAddress poa;

    public Captures() {
        this(null, null, null, 7, null);
    }

    public static /* synthetic */ Captures copy$default(Captures captures, Document document, Face face, ProofOfAddress proofOfAddress, int i, Object obj) {
        if ((i & 1) != 0) {
            document = captures.document;
        }
        if ((i & 2) != 0) {
            face = captures.face;
        }
        if ((i & 4) != 0) {
            proofOfAddress = captures.poa;
        }
        return captures.copy(document, face, proofOfAddress);
    }

    /* renamed from: component1, reason: from getter */
    public final Document getDocument() {
        return this.document;
    }

    /* renamed from: component2, reason: from getter */
    public final Face getFace() {
        return this.face;
    }

    /* renamed from: component3, reason: from getter */
    public final ProofOfAddress getPoa() {
        return this.poa;
    }

    public final Captures copy(Document document, Face face, ProofOfAddress poa) {
        return new Captures(document, face, poa);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Captures)) {
            return false;
        }
        Captures captures = (Captures) other;
        return Intrinsics.areEqual(this.document, captures.document) && Intrinsics.areEqual(this.face, captures.face) && Intrinsics.areEqual(this.poa, captures.poa);
    }

    public final Document getDocument() {
        return this.document;
    }

    public final Face getFace() {
        return this.face;
    }

    public final ProofOfAddress getPoa() {
        return this.poa;
    }

    public int hashCode() {
        Document document = this.document;
        int iHashCode = (document == null ? 0 : document.hashCode()) * 31;
        Face face = this.face;
        int iHashCode2 = (iHashCode + (face == null ? 0 : face.hashCode())) * 31;
        ProofOfAddress proofOfAddress = this.poa;
        return iHashCode2 + (proofOfAddress != null ? proofOfAddress.hashCode() : 0);
    }

    public final void setDocument(Document document) {
        this.document = document;
    }

    public final void setFace(Face face) {
        this.face = face;
    }

    public final void setPoa(ProofOfAddress proofOfAddress) {
        this.poa = proofOfAddress;
    }

    public String toString() {
        return "Captures(document=" + this.document + ", face=" + this.face + ", poa=" + this.poa + ")";
    }

    public Captures(Document document, Face face, ProofOfAddress proofOfAddress) {
        this.document = document;
        this.face = face;
        this.poa = proofOfAddress;
    }

    public /* synthetic */ Captures(Document document, Face face, ProofOfAddress proofOfAddress, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : document, (i & 2) != 0 ? null : face, (i & 4) != 0 ? null : proofOfAddress);
    }
}
