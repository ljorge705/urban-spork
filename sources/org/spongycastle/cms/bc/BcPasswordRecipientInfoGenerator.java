package org.spongycastle.cms.bc;

import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.pkcs.PBKDF2Params;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.cms.CMSException;
import org.spongycastle.cms.PasswordRecipientInfoGenerator;
import org.spongycastle.crypto.PBEParametersGenerator;
import org.spongycastle.crypto.Wrapper;
import org.spongycastle.crypto.generators.PKCS5S2ParametersGenerator;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.operator.GenericKey;

/* loaded from: classes4.dex */
public class BcPasswordRecipientInfoGenerator extends PasswordRecipientInfoGenerator {
    public BcPasswordRecipientInfoGenerator(ASN1ObjectIdentifier aSN1ObjectIdentifier, char[] cArr) {
        super(aSN1ObjectIdentifier, cArr);
    }

    @Override // org.spongycastle.cms.PasswordRecipientInfoGenerator
    protected byte[] calculateDerivedKey(int i, AlgorithmIdentifier algorithmIdentifier, int i2) throws CMSException {
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

    @Override // org.spongycastle.cms.PasswordRecipientInfoGenerator
    public byte[] generateEncryptedBytes(AlgorithmIdentifier algorithmIdentifier, byte[] bArr, GenericKey genericKey) throws CMSException {
        byte[] key = ((KeyParameter) CMSUtils.getBcKey(genericKey)).getKey();
        Wrapper wrapperCreateRFC3211Wrapper = EnvelopedDataHelper.createRFC3211Wrapper(algorithmIdentifier.getAlgorithm());
        wrapperCreateRFC3211Wrapper.init(true, new ParametersWithIV(new KeyParameter(bArr), ASN1OctetString.getInstance(algorithmIdentifier.getParameters()).getOctets()));
        return wrapperCreateRFC3211Wrapper.wrap(key, 0, key.length);
    }
}
