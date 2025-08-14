package org.ejbca.cvc;

import org.bouncycastle.util.encoders.Hex;

/* loaded from: classes4.dex */
public class AccessRightsRawValue implements AccessRights {
    private final byte[] bytes;

    @Override // org.ejbca.cvc.AccessRights
    public byte[] getEncoded() {
        return this.bytes;
    }

    @Override // org.ejbca.cvc.AccessRights
    public String name() {
        return "RAW_ACCESS_RIGHTS";
    }

    AccessRightsRawValue(byte[] bArr) {
        this.bytes = bArr;
    }

    public String toString() {
        return "AccessRightsRawValue(" + Hex.toHexString(this.bytes).toUpperCase() + ")";
    }
}
