package org.spongycastle.cms.bc;

import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.pkcs.PBKDF2Params;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.cms.CMSException;
import org.spongycastle.cms.PasswordRecipient;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.PBEParametersGenerator;
import org.spongycastle.crypto.Wrapper;
import org.spongycastle.crypto.generators.PKCS5S2ParametersGenerator;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;

/* loaded from: classes4.dex */
public abstract class BcPasswordRecipient implements PasswordRecipient {
    private final char[] password;
    private int schemeID = 1;

    @Override // org.spongycastle.cms.PasswordRecipient
    public char[] getPassword() {
        return this.password;
    }

    @Override // org.spongycastle.cms.PasswordRecipient
    public int getPasswordConversionScheme() {
        return this.schemeID;
    }

    public BcPasswordRecipient setPasswordConversionScheme(int i) {
        this.schemeID = i;
        return this;
    }

    BcPasswordRecipient(char[] cArr) {
        this.password = cArr;
    }

    protected KeyParameter extractSecretKey(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2, byte[] bArr, byte[] bArr2) throws CMSException {
        Wrapper wrapperCreateRFC3211Wrapper = EnvelopedDataHelper.createRFC3211Wrapper(algorithmIdentifier.getAlgorithm());
        wrapperCreateRFC3211Wrapper.init(false, new ParametersWithIV(new KeyParameter(bArr), ASN1OctetString.getInstance(algorithmIdentifier.getParameters()).getOctets()));
        try {
            return new KeyParameter(wrapperCreateRFC3211Wrapper.unwrap(bArr2, 0, bArr2.length));
        } catch (InvalidCipherTextException e) {
            throw new CMSException("unable to unwrap key: " + e.getMessage(), e);
        }
    }

    @Override // org.spongycastle.cms.PasswordRecipient
    public byte[] calculateDerivedKey(int i, AlgorithmIdentifier algorithmIdentifier, int i2) throws CMSException {
        PBKDF2Params pBKDF2Params = PBKDF2Params.getInstance(algorithmIdentifier.getParameters());
        byte[] bArrPKCS5PasswordToBytes = i == 0 ? PBEParametersGenerator.PKCS5PasswordToBytes(this.password) : PBEParametersGenerator.PKCS5PasswordToUTF8Bytes(this.password);
        try {
            PKCS5S2ParametersGenerator pKCS5S2ParametersGenerator = new PKCS5S2ParametersGenerator(EnvelopedDataHelper.getPRF(pBKDF2Params.getPrf()));
            pKCS5S2ParametersGenerator.init(bArrPKCS5PasswordToBytes, pBKDF2Params.getSalt(), pBKDF2Params.getIterationCount().intValue());
            return ((KeyParameter) pKCS5S2ParametersGenerator.generateDerivedParameters(i2)).getKey();
        } catch (Exception e) {
            throw new CMSException("exception creating derived key: " + e.getMessage(), e);
        }
    }
}
