package org.spongycastle.cert.crmf;

import org.spongycastle.asn1.x509.AlgorithmIdentifier;

/* loaded from: classes4.dex */
public interface PKMACValuesCalculator {
    byte[] calculateDigest(byte[] bArr) throws CRMFException;

    byte[] calculateMac(byte[] bArr, byte[] bArr2) throws CRMFException;

    void setup(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2) throws CRMFException;
}
