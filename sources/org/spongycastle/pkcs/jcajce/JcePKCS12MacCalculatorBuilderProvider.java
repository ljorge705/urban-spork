package org.spongycastle.pkcs.jcajce;

import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Provider;
import javax.crypto.Mac;
import javax.crypto.spec.PBEParameterSpec;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.pkcs.PKCS12PBEParams;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.jcajce.PKCS12Key;
import org.spongycastle.jcajce.io.MacOutputStream;
import org.spongycastle.jcajce.util.DefaultJcaJceHelper;
import org.spongycastle.jcajce.util.JcaJceHelper;
import org.spongycastle.jcajce.util.NamedJcaJceHelper;
import org.spongycastle.jcajce.util.ProviderJcaJceHelper;
import org.spongycastle.operator.GenericKey;
import org.spongycastle.operator.MacCalculator;
import org.spongycastle.operator.OperatorCreationException;
import org.spongycastle.pkcs.PKCS12MacCalculatorBuilder;
import org.spongycastle.pkcs.PKCS12MacCalculatorBuilderProvider;

/* loaded from: classes7.dex */
public class JcePKCS12MacCalculatorBuilderProvider implements PKCS12MacCalculatorBuilderProvider {
    private JcaJceHelper helper = new DefaultJcaJceHelper();

    public JcePKCS12MacCalculatorBuilderProvider setProvider(Provider provider) {
        this.helper = new ProviderJcaJceHelper(provider);
        return this;
    }

    public JcePKCS12MacCalculatorBuilderProvider setProvider(String str) {
        this.helper = new NamedJcaJceHelper(str);
        return this;
    }

    @Override // org.spongycastle.pkcs.PKCS12MacCalculatorBuilderProvider
    public PKCS12MacCalculatorBuilder get(final AlgorithmIdentifier algorithmIdentifier) {
        return new PKCS12MacCalculatorBuilder() { // from class: org.spongycastle.pkcs.jcajce.JcePKCS12MacCalculatorBuilderProvider.1
            @Override // org.spongycastle.pkcs.PKCS12MacCalculatorBuilder
            public MacCalculator build(char[] cArr) throws OperatorCreationException, InvalidKeyException, InvalidAlgorithmParameterException {
                final PKCS12PBEParams pKCS12PBEParams = PKCS12PBEParams.getInstance(algorithmIdentifier.getParameters());
                try {
                    final ASN1ObjectIdentifier algorithm = algorithmIdentifier.getAlgorithm();
                    final Mac macCreateMac = JcePKCS12MacCalculatorBuilderProvider.this.helper.createMac(algorithm.getId());
                    PBEParameterSpec pBEParameterSpec = new PBEParameterSpec(pKCS12PBEParams.getIV(), pKCS12PBEParams.getIterations().intValue());
                    final PKCS12Key pKCS12Key = new PKCS12Key(cArr);
                    macCreateMac.init(pKCS12Key, pBEParameterSpec);
                    return new MacCalculator() { // from class: org.spongycastle.pkcs.jcajce.JcePKCS12MacCalculatorBuilderProvider.1.1
                        @Override // org.spongycastle.operator.MacCalculator
                        public AlgorithmIdentifier getAlgorithmIdentifier() {
                            return new AlgorithmIdentifier(algorithm, pKCS12PBEParams);
                        }

                        @Override // org.spongycastle.operator.MacCalculator
                        public OutputStream getOutputStream() {
                            return new MacOutputStream(macCreateMac);
                        }

                        @Override // org.spongycastle.operator.MacCalculator
                        public byte[] getMac() {
                            return macCreateMac.doFinal();
                        }

                        @Override // org.spongycastle.operator.MacCalculator
                        public GenericKey getKey() {
                            return new GenericKey(getAlgorithmIdentifier(), pKCS12Key.getEncoded());
                        }
                    };
                } catch (Exception e) {
                    throw new OperatorCreationException("unable to create MAC calculator: " + e.getMessage(), e);
                }
            }

            @Override // org.spongycastle.pkcs.PKCS12MacCalculatorBuilder
            public AlgorithmIdentifier getDigestAlgorithmIdentifier() {
                return new AlgorithmIdentifier(algorithmIdentifier.getAlgorithm(), DERNull.INSTANCE);
            }
        };
    }
}
