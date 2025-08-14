package org.spongycastle.jcajce.provider.symmetric;

import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.spec.IvParameterSpec;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.spongycastle.asn1.cryptopro.GOST28147Parameters;
import org.spongycastle.crypto.BufferedBlockCipher;
import org.spongycastle.crypto.CipherKeyGenerator;
import org.spongycastle.crypto.engines.CryptoProWrapEngine;
import org.spongycastle.crypto.engines.GOST28147Engine;
import org.spongycastle.crypto.engines.GOST28147WrapEngine;
import org.spongycastle.crypto.macs.GOST28147Mac;
import org.spongycastle.crypto.modes.CBCBlockCipher;
import org.spongycastle.crypto.modes.GCFBBlockCipher;
import org.spongycastle.jcajce.provider.config.ConfigurableProvider;
import org.spongycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameterGenerator;
import org.spongycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameters;
import org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher;
import org.spongycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.spongycastle.jcajce.provider.symmetric.util.BaseMac;
import org.spongycastle.jcajce.provider.symmetric.util.BaseWrapCipher;
import org.spongycastle.jcajce.provider.util.AlgorithmProvider;
import org.spongycastle.jcajce.spec.GOST28147ParameterSpec;

/* loaded from: classes7.dex */
public final class GOST28147 {
    private static Map<ASN1ObjectIdentifier, String> oidMappings = new HashMap();
    private static Map<String, ASN1ObjectIdentifier> nameMappings = new HashMap();

    static {
        oidMappings.put(CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_TestParamSet, "E-TEST");
        oidMappings.put(CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_A_ParamSet, "E-A");
        oidMappings.put(CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_B_ParamSet, "E-B");
        oidMappings.put(CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_C_ParamSet, "E-C");
        oidMappings.put(CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_D_ParamSet, "E-D");
        nameMappings.put("E-A", CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_A_ParamSet);
        nameMappings.put("E-B", CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_B_ParamSet);
        nameMappings.put("E-C", CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_C_ParamSet);
        nameMappings.put("E-D", CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_D_ParamSet);
    }

    private GOST28147() {
    }

    public static class ECB extends BaseBlockCipher {
        public ECB() {
            super(new GOST28147Engine());
        }
    }

    public static class CBC extends BaseBlockCipher {
        public CBC() {
            super(new CBCBlockCipher(new GOST28147Engine()), 64);
        }
    }

    public static class GCFB extends BaseBlockCipher {
        public GCFB() {
            super(new BufferedBlockCipher(new GCFBBlockCipher(new GOST28147Engine())), 64);
        }
    }

    public static class GostWrap extends BaseWrapCipher {
        public GostWrap() {
            super(new GOST28147WrapEngine());
        }
    }

    public static class CryptoProWrap extends BaseWrapCipher {
        public CryptoProWrap() {
            super(new CryptoProWrapEngine());
        }
    }

    public static class Mac extends BaseMac {
        public Mac() {
            super(new GOST28147Mac());
        }
    }

    public static class KeyGen extends BaseKeyGenerator {
        public KeyGen() {
            this(256);
        }

        public KeyGen(int i) {
            super("GOST28147", i, new CipherKeyGenerator());
        }
    }

    public static class AlgParamGen extends BaseAlgorithmParameterGenerator {
        byte[] iv = new byte[8];
        byte[] sBox = GOST28147Engine.getSBox("E-A");

        @Override // java.security.AlgorithmParameterGeneratorSpi
        protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
            if (algorithmParameterSpec instanceof GOST28147ParameterSpec) {
                this.sBox = ((GOST28147ParameterSpec) algorithmParameterSpec).getSBox();
                return;
            }
            throw new InvalidAlgorithmParameterException("parameter spec not supported");
        }

        @Override // java.security.AlgorithmParameterGeneratorSpi
        protected AlgorithmParameters engineGenerateParameters() throws InvalidParameterSpecException {
            if (this.random == null) {
                this.random = new SecureRandom();
            }
            this.random.nextBytes(this.iv);
            try {
                AlgorithmParameters algorithmParametersCreateParametersInstance = createParametersInstance("GOST28147");
                algorithmParametersCreateParametersInstance.init(new GOST28147ParameterSpec(this.sBox, this.iv));
                return algorithmParametersCreateParametersInstance;
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    public static abstract class BaseAlgParams extends BaseAlgorithmParameters {
        private byte[] iv;
        private ASN1ObjectIdentifier sBox = CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_A_ParamSet;

        abstract void localInit(byte[] bArr) throws IOException;

        @Override // java.security.AlgorithmParametersSpi
        protected final void engineInit(byte[] bArr) throws IOException {
            engineInit(bArr, "ASN.1");
        }

        @Override // java.security.AlgorithmParametersSpi
        protected final byte[] engineGetEncoded() throws IOException {
            return engineGetEncoded("ASN.1");
        }

        @Override // java.security.AlgorithmParametersSpi
        protected final byte[] engineGetEncoded(String str) throws IOException {
            if (isASN1FormatString(str)) {
                return localGetEncoded();
            }
            throw new IOException("Unknown parameter format: " + str);
        }

        @Override // java.security.AlgorithmParametersSpi
        protected final void engineInit(byte[] bArr, String str) throws IOException {
            if (bArr == null) {
                throw new NullPointerException("Encoded parameters cannot be null");
            }
            if (isASN1FormatString(str)) {
                try {
                    localInit(bArr);
                    return;
                } catch (IOException e) {
                    throw e;
                } catch (Exception e2) {
                    throw new IOException("Parameter parsing failed: " + e2.getMessage());
                }
            }
            throw new IOException("Unknown parameter format: " + str);
        }

        protected byte[] localGetEncoded() throws IOException {
            return new GOST28147Parameters(this.iv, this.sBox).getEncoded();
        }

        @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameters
        protected AlgorithmParameterSpec localEngineGetParameterSpec(Class cls) throws InvalidParameterSpecException {
            if (cls == IvParameterSpec.class) {
                return new IvParameterSpec(this.iv);
            }
            if (cls == GOST28147ParameterSpec.class || cls == AlgorithmParameterSpec.class) {
                return new GOST28147ParameterSpec(this.sBox, this.iv);
            }
            throw new InvalidParameterSpecException("AlgorithmParameterSpec not recognized: " + cls.getName());
        }

        @Override // java.security.AlgorithmParametersSpi
        protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec) throws InvalidParameterSpecException {
            if (algorithmParameterSpec instanceof IvParameterSpec) {
                this.iv = ((IvParameterSpec) algorithmParameterSpec).getIV();
            } else {
                if (algorithmParameterSpec instanceof GOST28147ParameterSpec) {
                    this.iv = ((GOST28147ParameterSpec) algorithmParameterSpec).getIV();
                    try {
                        this.sBox = getSBoxOID(((GOST28147ParameterSpec) algorithmParameterSpec).getSBox());
                        return;
                    } catch (IllegalArgumentException e) {
                        throw new InvalidParameterSpecException(e.getMessage());
                    }
                }
                throw new InvalidParameterSpecException("IvParameterSpec required to initialise a IV parameters algorithm parameters object");
            }
        }

        protected static ASN1ObjectIdentifier getSBoxOID(String str) {
            ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier) GOST28147.nameMappings.get(str);
            if (aSN1ObjectIdentifier != null) {
                return aSN1ObjectIdentifier;
            }
            throw new IllegalArgumentException("Unknown SBOX name: " + str);
        }

        protected static ASN1ObjectIdentifier getSBoxOID(byte[] bArr) {
            return getSBoxOID(GOST28147Engine.getSBoxName(bArr));
        }
    }

    public static class AlgParams extends BaseAlgParams {
        private byte[] iv;
        private ASN1ObjectIdentifier sBox = CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_A_ParamSet;

        @Override // java.security.AlgorithmParametersSpi
        protected String engineToString() {
            return "GOST 28147 IV Parameters";
        }

        @Override // org.spongycastle.jcajce.provider.symmetric.GOST28147.BaseAlgParams
        protected byte[] localGetEncoded() throws IOException {
            return new GOST28147Parameters(this.iv, this.sBox).getEncoded();
        }

        @Override // org.spongycastle.jcajce.provider.symmetric.GOST28147.BaseAlgParams, org.spongycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameters
        protected AlgorithmParameterSpec localEngineGetParameterSpec(Class cls) throws InvalidParameterSpecException {
            if (cls == IvParameterSpec.class) {
                return new IvParameterSpec(this.iv);
            }
            if (cls == GOST28147ParameterSpec.class || cls == AlgorithmParameterSpec.class) {
                return new GOST28147ParameterSpec(this.sBox, this.iv);
            }
            throw new InvalidParameterSpecException("AlgorithmParameterSpec not recognized: " + cls.getName());
        }

        @Override // org.spongycastle.jcajce.provider.symmetric.GOST28147.BaseAlgParams, java.security.AlgorithmParametersSpi
        protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec) throws InvalidParameterSpecException {
            if (algorithmParameterSpec instanceof IvParameterSpec) {
                this.iv = ((IvParameterSpec) algorithmParameterSpec).getIV();
            } else {
                if (algorithmParameterSpec instanceof GOST28147ParameterSpec) {
                    this.iv = ((GOST28147ParameterSpec) algorithmParameterSpec).getIV();
                    try {
                        this.sBox = getSBoxOID(((GOST28147ParameterSpec) algorithmParameterSpec).getSBox());
                        return;
                    } catch (IllegalArgumentException e) {
                        throw new InvalidParameterSpecException(e.getMessage());
                    }
                }
                throw new InvalidParameterSpecException("IvParameterSpec required to initialise a IV parameters algorithm parameters object");
            }
        }

        @Override // org.spongycastle.jcajce.provider.symmetric.GOST28147.BaseAlgParams
        protected void localInit(byte[] bArr) throws IOException {
            ASN1Primitive aSN1PrimitiveFromByteArray = ASN1Primitive.fromByteArray(bArr);
            if (aSN1PrimitiveFromByteArray instanceof ASN1OctetString) {
                this.iv = ASN1OctetString.getInstance(aSN1PrimitiveFromByteArray).getOctets();
            } else {
                if (aSN1PrimitiveFromByteArray instanceof ASN1Sequence) {
                    GOST28147Parameters gOST28147Parameters = GOST28147Parameters.getInstance(aSN1PrimitiveFromByteArray);
                    this.sBox = gOST28147Parameters.getEncryptionParamSet();
                    this.iv = gOST28147Parameters.getIV();
                    return;
                }
                throw new IOException("Unable to recognize parameters");
            }
        }
    }

    public static class Mappings extends AlgorithmProvider {
        private static final String PREFIX = GOST28147.class.getName();

        @Override // org.spongycastle.jcajce.provider.util.AlgorithmProvider
        public void configure(ConfigurableProvider configurableProvider) {
            StringBuilder sb = new StringBuilder();
            String str = PREFIX;
            configurableProvider.addAlgorithm("Cipher.GOST28147", sb.append(str).append("$ECB").toString());
            configurableProvider.addAlgorithm("Alg.Alias.Cipher.GOST", "GOST28147");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher.GOST-28147", "GOST28147");
            configurableProvider.addAlgorithm("Cipher." + CryptoProObjectIdentifiers.gostR28147_gcfb, str + "$GCFB");
            configurableProvider.addAlgorithm("KeyGenerator.GOST28147", str + "$KeyGen");
            configurableProvider.addAlgorithm("Alg.Alias.KeyGenerator.GOST", "GOST28147");
            configurableProvider.addAlgorithm("Alg.Alias.KeyGenerator.GOST-28147", "GOST28147");
            configurableProvider.addAlgorithm("Alg.Alias.KeyGenerator." + CryptoProObjectIdentifiers.gostR28147_gcfb, "GOST28147");
            configurableProvider.addAlgorithm("AlgorithmParameters.GOST28147", str + "$AlgParams");
            configurableProvider.addAlgorithm("AlgorithmParameterGenerator.GOST28147", str + "$AlgParamGen");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameters." + CryptoProObjectIdentifiers.gostR28147_gcfb, "GOST28147");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator." + CryptoProObjectIdentifiers.gostR28147_gcfb, "GOST28147");
            configurableProvider.addAlgorithm("Cipher." + CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_KeyWrap, str + "$CryptoProWrap");
            configurableProvider.addAlgorithm("Cipher." + CryptoProObjectIdentifiers.id_Gost28147_89_None_KeyWrap, str + "$GostWrap");
            configurableProvider.addAlgorithm("Mac.GOST28147MAC", str + "$Mac");
            configurableProvider.addAlgorithm("Alg.Alias.Mac.GOST28147", "GOST28147MAC");
        }
    }
}
