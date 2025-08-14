package org.spongycastle.cms;

import org.spongycastle.util.Arrays;

/* loaded from: classes4.dex */
public class KEKRecipientId extends RecipientId {
    private byte[] keyIdentifier;

    public KEKRecipientId(byte[] bArr) {
        super(1);
        this.keyIdentifier = bArr;
    }

    public int hashCode() {
        return Arrays.hashCode(this.keyIdentifier);
    }

    public boolean equals(Object obj) {
        if (obj instanceof KEKRecipientId) {
            return Arrays.areEqual(this.keyIdentifier, ((KEKRecipientId) obj).keyIdentifier);
        }
        return false;
    }

    public byte[] getKeyIdentifier() {
        return Arrays.clone(this.keyIdentifier);
    }

    @Override // org.spongycastle.cms.RecipientId, org.spongycastle.util.Selector
    public Object clone() {
        return new KEKRecipientId(this.keyIdentifier);
    }

    @Override // org.spongycastle.util.Selector
    public boolean match(Object obj) {
        if (obj instanceof byte[]) {
            return Arrays.areEqual(this.keyIdentifier, (byte[]) obj);
        }
        if (obj instanceof KEKRecipientInformation) {
            return ((KEKRecipientInformation) obj).getRID().equals(this);
        }
        return false;
    }
}
