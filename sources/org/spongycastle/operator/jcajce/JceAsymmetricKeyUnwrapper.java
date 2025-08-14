package org.spongycastle.operator.jcajce;

import java.security.PrivateKey;
import java.security.Provider;
import java.util.HashMap;
import java.util.Map;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.jcajce.util.DefaultJcaJceHelper;
import org.spongycastle.jcajce.util.NamedJcaJceHelper;
import org.spongycastle.jcajce.util.ProviderJcaJceHelper;
import org.spongycastle.operator.AsymmetricKeyUnwrapper;

/* loaded from: classes7.dex */
public class JceAsymmetricKeyUnwrapper extends AsymmetricKeyUnwrapper {
    private Map extraMappings;
    private OperatorHelper helper;
    private PrivateKey privKey;
    private boolean unwrappedKeyMustBeEncodable;

    public JceAsymmetricKeyUnwrapper setMustProduceEncodableUnwrappedKey(boolean z) {
        this.unwrappedKeyMustBeEncodable = z;
        return this;
    }

    public JceAsymmetricKeyUnwrapper(AlgorithmIdentifier algorithmIdentifier, PrivateKey privateKey) {
        super(algorithmIdentifier);
        this.helper = new OperatorHelper(new DefaultJcaJceHelper());
        this.extraMappings = new HashMap();
        this.privKey = privateKey;
    }

    public JceAsymmetricKeyUnwrapper setProvider(Provider provider) {
        this.helper = new OperatorHelper(new ProviderJcaJceHelper(provider));
        return this;
    }

    public JceAsymmetricKeyUnwrapper setProvider(String str) {
        this.helper = new OperatorHelper(new NamedJcaJceHelper(str));
        return this;
    }

    public JceAsymmetricKeyUnwrapper setAlgorithmMapping(ASN1ObjectIdentifier aSN1ObjectIdentifier, String str) {
        this.extraMappings.put(aSN1ObjectIdentifier, str);
        return this;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0046  */
    @Override // org.spongycastle.operator.KeyUnwrapper
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public org.spongycastle.operator.GenericKey generateUnwrappedKey(org.spongycastle.asn1.x509.AlgorithmIdentifier r6, byte[] r7) throws java.security.NoSuchAlgorithmException, java.io.IOException, java.security.InvalidKeyException, org.spongycastle.operator.OperatorException, java.security.InvalidAlgorithmParameterException {
        /*
            r5 = this;
            org.spongycastle.operator.jcajce.OperatorHelper r0 = r5.helper     // Catch: javax.crypto.BadPaddingException -> L66 javax.crypto.IllegalBlockSizeException -> L80 java.security.InvalidKeyException -> L9a
            org.spongycastle.asn1.x509.AlgorithmIdentifier r1 = r5.getAlgorithmIdentifier()     // Catch: javax.crypto.BadPaddingException -> L66 javax.crypto.IllegalBlockSizeException -> L80 java.security.InvalidKeyException -> L9a
            org.spongycastle.asn1.ASN1ObjectIdentifier r1 = r1.getAlgorithm()     // Catch: javax.crypto.BadPaddingException -> L66 javax.crypto.IllegalBlockSizeException -> L80 java.security.InvalidKeyException -> L9a
            java.util.Map r2 = r5.extraMappings     // Catch: javax.crypto.BadPaddingException -> L66 javax.crypto.IllegalBlockSizeException -> L80 java.security.InvalidKeyException -> L9a
            javax.crypto.Cipher r0 = r0.createAsymmetricWrapper(r1, r2)     // Catch: javax.crypto.BadPaddingException -> L66 javax.crypto.IllegalBlockSizeException -> L80 java.security.InvalidKeyException -> L9a
            org.spongycastle.operator.jcajce.OperatorHelper r1 = r5.helper     // Catch: javax.crypto.BadPaddingException -> L66 javax.crypto.IllegalBlockSizeException -> L80 java.security.InvalidKeyException -> L9a
            org.spongycastle.asn1.x509.AlgorithmIdentifier r2 = r5.getAlgorithmIdentifier()     // Catch: javax.crypto.BadPaddingException -> L66 javax.crypto.IllegalBlockSizeException -> L80 java.security.InvalidKeyException -> L9a
            java.security.AlgorithmParameters r1 = r1.createAlgorithmParameters(r2)     // Catch: javax.crypto.BadPaddingException -> L66 javax.crypto.IllegalBlockSizeException -> L80 java.security.InvalidKeyException -> L9a
            r2 = 4
            r3 = 0
            if (r1 == 0) goto L24
            java.security.PrivateKey r4 = r5.privKey     // Catch: java.lang.Throwable -> L47 javax.crypto.BadPaddingException -> L66 javax.crypto.IllegalBlockSizeException -> L80 java.security.InvalidKeyException -> L9a
            r0.init(r2, r4, r1)     // Catch: java.lang.Throwable -> L47 javax.crypto.BadPaddingException -> L66 javax.crypto.IllegalBlockSizeException -> L80 java.security.InvalidKeyException -> L9a
            goto L29
        L24:
            java.security.PrivateKey r1 = r5.privKey     // Catch: java.lang.Throwable -> L47 javax.crypto.BadPaddingException -> L66 javax.crypto.IllegalBlockSizeException -> L80 java.security.InvalidKeyException -> L9a
            r0.init(r2, r1)     // Catch: java.lang.Throwable -> L47 javax.crypto.BadPaddingException -> L66 javax.crypto.IllegalBlockSizeException -> L80 java.security.InvalidKeyException -> L9a
        L29:
            org.spongycastle.operator.jcajce.OperatorHelper r1 = r5.helper     // Catch: java.lang.Throwable -> L47 javax.crypto.BadPaddingException -> L66 javax.crypto.IllegalBlockSizeException -> L80 java.security.InvalidKeyException -> L9a
            org.spongycastle.asn1.ASN1ObjectIdentifier r2 = r6.getAlgorithm()     // Catch: java.lang.Throwable -> L47 javax.crypto.BadPaddingException -> L66 javax.crypto.IllegalBlockSizeException -> L80 java.security.InvalidKeyException -> L9a
            java.lang.String r1 = r1.getKeyAlgorithmName(r2)     // Catch: java.lang.Throwable -> L47 javax.crypto.BadPaddingException -> L66 javax.crypto.IllegalBlockSizeException -> L80 java.security.InvalidKeyException -> L9a
            r2 = 3
            java.security.Key r1 = r0.unwrap(r7, r1, r2)     // Catch: java.lang.Throwable -> L47 javax.crypto.BadPaddingException -> L66 javax.crypto.IllegalBlockSizeException -> L80 java.security.InvalidKeyException -> L9a
            boolean r2 = r5.unwrappedKeyMustBeEncodable     // Catch: java.lang.Throwable -> L46 javax.crypto.BadPaddingException -> L66 javax.crypto.IllegalBlockSizeException -> L80 java.security.InvalidKeyException -> L9a
            if (r2 == 0) goto L46
            byte[] r2 = r1.getEncoded()     // Catch: java.lang.Throwable -> L46 java.lang.Throwable -> L46 java.lang.Throwable -> L46 java.lang.Throwable -> L46 javax.crypto.BadPaddingException -> L66 javax.crypto.IllegalBlockSizeException -> L80 java.security.InvalidKeyException -> L9a
            if (r2 == 0) goto L47
            int r2 = r2.length     // Catch: java.lang.Throwable -> L46 java.lang.Throwable -> L46 java.lang.Throwable -> L46 java.lang.Throwable -> L46 javax.crypto.BadPaddingException -> L66 javax.crypto.IllegalBlockSizeException -> L80 java.security.InvalidKeyException -> L9a
            if (r2 != 0) goto L46
            goto L47
        L46:
            r3 = r1
        L47:
            if (r3 != 0) goto L60
            java.security.PrivateKey r1 = r5.privKey     // Catch: javax.crypto.BadPaddingException -> L66 javax.crypto.IllegalBlockSizeException -> L80 java.security.InvalidKeyException -> L9a
            r2 = 2
            r0.init(r2, r1)     // Catch: javax.crypto.BadPaddingException -> L66 javax.crypto.IllegalBlockSizeException -> L80 java.security.InvalidKeyException -> L9a
            javax.crypto.spec.SecretKeySpec r3 = new javax.crypto.spec.SecretKeySpec     // Catch: javax.crypto.BadPaddingException -> L66 javax.crypto.IllegalBlockSizeException -> L80 java.security.InvalidKeyException -> L9a
            byte[] r7 = r0.doFinal(r7)     // Catch: javax.crypto.BadPaddingException -> L66 javax.crypto.IllegalBlockSizeException -> L80 java.security.InvalidKeyException -> L9a
            org.spongycastle.asn1.ASN1ObjectIdentifier r0 = r6.getAlgorithm()     // Catch: javax.crypto.BadPaddingException -> L66 javax.crypto.IllegalBlockSizeException -> L80 java.security.InvalidKeyException -> L9a
            java.lang.String r0 = r0.getId()     // Catch: javax.crypto.BadPaddingException -> L66 javax.crypto.IllegalBlockSizeException -> L80 java.security.InvalidKeyException -> L9a
            r3.<init>(r7, r0)     // Catch: javax.crypto.BadPaddingException -> L66 javax.crypto.IllegalBlockSizeException -> L80 java.security.InvalidKeyException -> L9a
        L60:
            org.spongycastle.operator.jcajce.JceGenericKey r7 = new org.spongycastle.operator.jcajce.JceGenericKey     // Catch: javax.crypto.BadPaddingException -> L66 javax.crypto.IllegalBlockSizeException -> L80 java.security.InvalidKeyException -> L9a
            r7.<init>(r6, r3)     // Catch: javax.crypto.BadPaddingException -> L66 javax.crypto.IllegalBlockSizeException -> L80 java.security.InvalidKeyException -> L9a
            return r7
        L66:
            r6 = move-exception
            org.spongycastle.operator.OperatorException r7 = new org.spongycastle.operator.OperatorException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "bad padding: "
            r0.<init>(r1)
            java.lang.String r1 = r6.getMessage()
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            r7.<init>(r0, r6)
            throw r7
        L80:
            r6 = move-exception
            org.spongycastle.operator.OperatorException r7 = new org.spongycastle.operator.OperatorException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "illegal blocksize: "
            r0.<init>(r1)
            java.lang.String r1 = r6.getMessage()
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            r7.<init>(r0, r6)
            throw r7
        L9a:
            r6 = move-exception
            org.spongycastle.operator.OperatorException r7 = new org.spongycastle.operator.OperatorException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "key invalid: "
            r0.<init>(r1)
            java.lang.String r1 = r6.getMessage()
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            r7.<init>(r0, r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.operator.jcajce.JceAsymmetricKeyUnwrapper.generateUnwrappedKey(org.spongycastle.asn1.x509.AlgorithmIdentifier, byte[]):org.spongycastle.operator.GenericKey");
    }
}
