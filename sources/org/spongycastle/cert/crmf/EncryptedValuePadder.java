package org.spongycastle.cert.crmf;

/* loaded from: classes4.dex */
public interface EncryptedValuePadder {
    byte[] getPaddedData(byte[] bArr);

    byte[] getUnpaddedData(byte[] bArr);
}
