package org.spongycastle.jcajce.provider.symmetric;

import com.google.android.gms.stats.CodePackage;
import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import javax.crypto.spec.IvParameterSpec;
import org.spongycastle.asn1.cms.CCMParameters;
import org.spongycastle.asn1.cms.GCMParameters;
import org.spongycastle.asn1.nsri.NSRIObjectIdentifiers;
import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.BufferedBlockCipher;
import org.spongycastle.crypto.CipherKeyGenerator;
import org.spongycastle.crypto.engines.ARIAEngine;
import org.spongycastle.crypto.engines.ARIAWrapEngine;
import org.spongycastle.crypto.engines.ARIAWrapPadEngine;
import org.spongycastle.crypto.engines.RFC3211WrapEngine;
import org.spongycastle.crypto.generators.Poly1305KeyGenerator;
import org.spongycastle.crypto.macs.GMac;
import org.spongycastle.crypto.modes.CBCBlockCipher;
import org.spongycastle.crypto.modes.CFBBlockCipher;
import org.spongycastle.crypto.modes.GCMBlockCipher;
import org.spongycastle.crypto.modes.OFBBlockCipher;
import org.spongycastle.jcajce.provider.config.ConfigurableProvider;
import org.spongycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameterGenerator;
import org.spongycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameters;
import org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher;
import org.spongycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.spongycastle.jcajce.provider.symmetric.util.BaseMac;
import org.spongycastle.jcajce.provider.symmetric.util.BaseWrapCipher;
import org.spongycastle.jcajce.provider.symmetric.util.BlockCipherProvider;
import org.spongycastle.jcajce.provider.symmetric.util.IvAlgorithmParameters;
import org.spongycastle.jcajce.spec.AEADParameterSpec;

/* loaded from: classes7.dex */
public final class ARIA {

    public static class AlgParams extends IvAlgorithmParameters {
        @Override // org.spongycastle.jcajce.provider.symmetric.util.IvAlgorithmParameters, java.security.AlgorithmParametersSpi
        protected String engineToString() {
            return "ARIA IV";
        }
    }

    private ARIA() {
    }

    public static class ECB extends BaseBlockCipher {
        public ECB() {
            super(new BlockCipherProvider() { // from class: org.spongycastle.jcajce.provider.symmetric.ARIA.ECB.1
                @Override // org.spongycastle.jcajce.provider.symmetric.util.BlockCipherProvider
                public BlockCipher get() {
                    return new ARIAEngine();
                }
            });
        }
    }

    public static class CBC extends BaseBlockCipher {
        public CBC() {
            super(new CBCBlockCipher(new ARIAEngine()), 128);
        }
    }

    public static class CFB extends BaseBlockCipher {
        public CFB() {
            super(new BufferedBlockCipher(new CFBBlockCipher(new ARIAEngine(), 128)), 128);
        }
    }

    public static class OFB extends BaseBlockCipher {
        public OFB() {
            super(new BufferedBlockCipher(new OFBBlockCipher(new ARIAEngine(), 128)), 128);
        }
    }

    public static class Wrap extends BaseWrapCipher {
        public Wrap() {
            super(new ARIAWrapEngine());
        }
    }

    public static class WrapPad extends BaseWrapCipher {
        public WrapPad() {
            super(new ARIAWrapPadEngine());
        }
    }

    public static class RFC3211Wrap extends BaseWrapCipher {
        public RFC3211Wrap() {
            super(new RFC3211WrapEngine(new ARIAEngine()), 16);
        }
    }

    public static class GMAC extends BaseMac {
        public GMAC() {
            super(new GMac(new GCMBlockCipher(new ARIAEngine())));
        }
    }

    public static class Poly1305 extends BaseMac {
        public Poly1305() {
            super(new org.spongycastle.crypto.macs.Poly1305(new ARIAEngine()));
        }
    }

    public static class Poly1305KeyGen extends BaseKeyGenerator {
        public Poly1305KeyGen() {
            super("Poly1305-ARIA", 256, new Poly1305KeyGenerator());
        }
    }

    public static class KeyGen extends BaseKeyGenerator {
        public KeyGen() {
            this(256);
        }

        public KeyGen(int i) {
            super("ARIA", i, new CipherKeyGenerator());
        }
    }

    public static class KeyGen128 extends KeyGen {
        public KeyGen128() {
            super(128);
        }
    }

    public static class KeyGen192 extends KeyGen {
        public KeyGen192() {
            super(192);
        }
    }

    public static class KeyGen256 extends KeyGen {
        public KeyGen256() {
            super(256);
        }
    }

    public static class AlgParamGen extends BaseAlgorithmParameterGenerator {
        @Override // java.security.AlgorithmParameterGeneratorSpi
        protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
            throw new InvalidAlgorithmParameterException("No supported AlgorithmParameterSpec for ARIA parameter generation.");
        }

        @Override // java.security.AlgorithmParameterGeneratorSpi
        protected AlgorithmParameters engineGenerateParameters() throws InvalidParameterSpecException {
            byte[] bArr = new byte[16];
            if (this.random == null) {
                this.random = new SecureRandom();
            }
            this.random.nextBytes(bArr);
            try {
                AlgorithmParameters algorithmParametersCreateParametersInstance = createParametersInstance("ARIA");
                algorithmParametersCreateParametersInstance.init(new IvParameterSpec(bArr));
                return algorithmParametersCreateParametersInstance;
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    public static class AlgParamsGCM extends BaseAlgorithmParameters {
        private GCMParameters gcmParams;

        @Override // java.security.AlgorithmParametersSpi
        protected String engineToString() {
            return CodePackage.GCM;
        }

        @Override // java.security.AlgorithmParametersSpi
        protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec) throws InvalidParameterSpecException {
            if (GcmSpecUtil.isGcmSpec(algorithmParameterSpec)) {
                this.gcmParams = GcmSpecUtil.extractGcmParameters(algorithmParameterSpec);
            } else {
                if (algorithmParameterSpec instanceof AEADParameterSpec) {
                    AEADParameterSpec aEADParameterSpec = (AEADParameterSpec) algorithmParameterSpec;
                    this.gcmParams = new GCMParameters(aEADParameterSpec.getNonce(), aEADParameterSpec.getMacSizeInBits() / 8);
                    return;
                }
                throw new InvalidParameterSpecException("AlgorithmParameterSpec class not recognized: " + algorithmParameterSpec.getClass().getName());
            }
        }

        @Override // java.security.AlgorithmParametersSpi
        protected void engineInit(byte[] bArr) throws IOException {
            this.gcmParams = GCMParameters.getInstance(bArr);
        }

        @Override // java.security.AlgorithmParametersSpi
        protected void engineInit(byte[] bArr, String str) throws IOException {
            if (!isASN1FormatString(str)) {
                throw new IOException("unknown format specified");
            }
            this.gcmParams = GCMParameters.getInstance(bArr);
        }

        @Override // java.security.AlgorithmParametersSpi
        protected byte[] engineGetEncoded() throws IOException {
            return this.gcmParams.getEncoded();
        }

        @Override // java.security.AlgorithmParametersSpi
        protected byte[] engineGetEncoded(String str) throws IOException {
            if (!isASN1FormatString(str)) {
                throw new IOException("unknown format specified");
            }
            return this.gcmParams.getEncoded();
        }

        @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameters
        protected AlgorithmParameterSpec localEngineGetParameterSpec(Class cls) throws InvalidParameterSpecException {
            if (cls == AlgorithmParameterSpec.class || GcmSpecUtil.isGcmSpec(cls)) {
                if (GcmSpecUtil.gcmSpecExists()) {
                    return GcmSpecUtil.extractGcmSpec(this.gcmParams.toASN1Primitive());
                }
                return new AEADParameterSpec(this.gcmParams.getNonce(), this.gcmParams.getIcvLen() * 8);
            }
            if (cls == AEADParameterSpec.class) {
                return new AEADParameterSpec(this.gcmParams.getNonce(), this.gcmParams.getIcvLen() * 8);
            }
            if (cls == IvParameterSpec.class) {
                return new IvParameterSpec(this.gcmParams.getNonce());
            }
            throw new InvalidParameterSpecException("AlgorithmParameterSpec not recognized: " + cls.getName());
        }
    }

    public static class AlgParamsCCM extends BaseAlgorithmParameters {
        private CCMParameters ccmParams;

        @Override // java.security.AlgorithmParametersSpi
        protected String engineToString() {
            return "CCM";
        }

        @Override // java.security.AlgorithmParametersSpi
        protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec) throws InvalidParameterSpecException {
            if (GcmSpecUtil.isGcmSpec(algorithmParameterSpec)) {
                this.ccmParams = CCMParameters.getInstance(GcmSpecUtil.extractGcmParameters(algorithmParameterSpec));
            } else {
                if (algorithmParameterSpec instanceof AEADParameterSpec) {
                    AEADParameterSpec aEADParameterSpec = (AEADParameterSpec) algorithmParameterSpec;
                    this.ccmParams = new CCMParameters(aEADParameterSpec.getNonce(), aEADParameterSpec.getMacSizeInBits() / 8);
                    return;
                }
                throw new InvalidParameterSpecException("AlgorithmParameterSpec class not recognized: " + algorithmParameterSpec.getClass().getName());
            }
        }

        @Override // java.security.AlgorithmParametersSpi
        protected void engineInit(byte[] bArr) throws IOException {
            this.ccmParams = CCMParameters.getInstance(bArr);
        }

        @Override // java.security.AlgorithmParametersSpi
        protected void engineInit(byte[] bArr, String str) throws IOException {
            if (!isASN1FormatString(str)) {
                throw new IOException("unknown format specified");
            }
            this.ccmParams = CCMParameters.getInstance(bArr);
        }

        @Override // java.security.AlgorithmParametersSpi
        protected byte[] engineGetEncoded() throws IOException {
            return this.ccmParams.getEncoded();
        }

        @Override // java.security.AlgorithmParametersSpi
        protected byte[] engineGetEncoded(String str) throws IOException {
            if (!isASN1FormatString(str)) {
                throw new IOException("unknown format specified");
            }
            return this.ccmParams.getEncoded();
        }

        @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameters
        protected AlgorithmParameterSpec localEngineGetParameterSpec(Class cls) throws InvalidParameterSpecException {
            if (cls == AlgorithmParameterSpec.class || GcmSpecUtil.isGcmSpec(cls)) {
                if (GcmSpecUtil.gcmSpecExists()) {
                    return GcmSpecUtil.extractGcmSpec(this.ccmParams.toASN1Primitive());
                }
                return new AEADParameterSpec(this.ccmParams.getNonce(), this.ccmParams.getIcvLen() * 8);
            }
            if (cls == AEADParameterSpec.class) {
                return new AEADParameterSpec(this.ccmParams.getNonce(), this.ccmParams.getIcvLen() * 8);
            }
            if (cls == IvParameterSpec.class) {
                return new IvParameterSpec(this.ccmParams.getNonce());
            }
            throw new InvalidParameterSpecException("AlgorithmParameterSpec not recognized: " + cls.getName());
        }
    }

    public static class Mappings extends SymmetricAlgorithmProvider {
        private static final String PREFIX = ARIA.class.getName();

        @Override // org.spongycastle.jcajce.provider.util.AlgorithmProvider
        public void configure(ConfigurableProvider configurableProvider) {
            StringBuilder sb = new StringBuilder();
            String str = PREFIX;
            configurableProvider.addAlgorithm("AlgorithmParameters.ARIA", sb.append(str).append("$AlgParams").toString());
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameters", NSRIObjectIdentifiers.id_aria128_cbc, "ARIA");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameters", NSRIObjectIdentifiers.id_aria192_cbc, "ARIA");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameters", NSRIObjectIdentifiers.id_aria256_cbc, "ARIA");
            configurableProvider.addAlgorithm("AlgorithmParameterGenerator.ARIA", str + "$AlgParamGen");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator", NSRIObjectIdentifiers.id_aria128_cbc, "ARIA");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator", NSRIObjectIdentifiers.id_aria192_cbc, "ARIA");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator", NSRIObjectIdentifiers.id_aria256_cbc, "ARIA");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator", NSRIObjectIdentifiers.id_aria128_ofb, "ARIA");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator", NSRIObjectIdentifiers.id_aria192_ofb, "ARIA");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator", NSRIObjectIdentifiers.id_aria256_ofb, "ARIA");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator", NSRIObjectIdentifiers.id_aria128_cfb, "ARIA");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator", NSRIObjectIdentifiers.id_aria192_cfb, "ARIA");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator", NSRIObjectIdentifiers.id_aria256_cfb, "ARIA");
            configurableProvider.addAlgorithm("Cipher.ARIA", str + "$ECB");
            configurableProvider.addAlgorithm("Cipher", NSRIObjectIdentifiers.id_aria128_ecb, str + "$ECB");
            configurableProvider.addAlgorithm("Cipher", NSRIObjectIdentifiers.id_aria192_ecb, str + "$ECB");
            configurableProvider.addAlgorithm("Cipher", NSRIObjectIdentifiers.id_aria256_ecb, str + "$ECB");
            configurableProvider.addAlgorithm("Cipher", NSRIObjectIdentifiers.id_aria128_cbc, str + "$CBC");
            configurableProvider.addAlgorithm("Cipher", NSRIObjectIdentifiers.id_aria192_cbc, str + "$CBC");
            configurableProvider.addAlgorithm("Cipher", NSRIObjectIdentifiers.id_aria256_cbc, str + "$CBC");
            configurableProvider.addAlgorithm("Cipher", NSRIObjectIdentifiers.id_aria128_cfb, str + "$CFB");
            configurableProvider.addAlgorithm("Cipher", NSRIObjectIdentifiers.id_aria192_cfb, str + "$CFB");
            configurableProvider.addAlgorithm("Cipher", NSRIObjectIdentifiers.id_aria256_cfb, str + "$CFB");
            configurableProvider.addAlgorithm("Cipher", NSRIObjectIdentifiers.id_aria128_ofb, str + "$OFB");
            configurableProvider.addAlgorithm("Cipher", NSRIObjectIdentifiers.id_aria192_ofb, str + "$OFB");
            configurableProvider.addAlgorithm("Cipher", NSRIObjectIdentifiers.id_aria256_ofb, str + "$OFB");
            configurableProvider.addAlgorithm("Cipher.ARIARFC3211WRAP", str + "$RFC3211Wrap");
            configurableProvider.addAlgorithm("Cipher.ARIAWRAP", str + "$Wrap");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", NSRIObjectIdentifiers.id_aria128_kw, "ARIAWRAP");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", NSRIObjectIdentifiers.id_aria192_kw, "ARIAWRAP");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", NSRIObjectIdentifiers.id_aria256_kw, "ARIAWRAP");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher.ARIAKW", "ARIAWRAP");
            configurableProvider.addAlgorithm("Cipher.ARIAWRAPPAD", str + "$WrapPad");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", NSRIObjectIdentifiers.id_aria128_kwp, "ARIAWRAPPAD");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", NSRIObjectIdentifiers.id_aria192_kwp, "ARIAWRAPPAD");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", NSRIObjectIdentifiers.id_aria256_kwp, "ARIAWRAPPAD");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher.ARIAKWP", "ARIAWRAPPAD");
            configurableProvider.addAlgorithm("KeyGenerator.ARIA", str + "$KeyGen");
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria128_kw, str + "$KeyGen128");
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria192_kw, str + "$KeyGen192");
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria256_kw, str + "$KeyGen256");
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria128_kwp, str + "$KeyGen128");
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria192_kwp, str + "$KeyGen192");
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria256_kwp, str + "$KeyGen256");
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria128_ecb, str + "$KeyGen128");
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria192_ecb, str + "$KeyGen192");
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria256_ecb, str + "$KeyGen256");
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria128_cbc, str + "$KeyGen128");
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria192_cbc, str + "$KeyGen192");
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria256_cbc, str + "$KeyGen256");
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria128_cfb, str + "$KeyGen128");
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria192_cfb, str + "$KeyGen192");
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria256_cfb, str + "$KeyGen256");
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria128_ofb, str + "$KeyGen128");
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria192_ofb, str + "$KeyGen192");
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria256_ofb, str + "$KeyGen256");
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria128_ccm, str + "$KeyGen128");
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria192_ccm, str + "$KeyGen192");
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria256_ccm, str + "$KeyGen256");
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria128_gcm, str + "$KeyGen128");
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria192_gcm, str + "$KeyGen192");
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria256_gcm, str + "$KeyGen256");
            configurableProvider.addAlgorithm("AlgorithmParameterGenerator.ARIACCM", str + "$AlgParamGenCCM");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator." + NSRIObjectIdentifiers.id_aria128_ccm, "CCM");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator." + NSRIObjectIdentifiers.id_aria192_ccm, "CCM");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator." + NSRIObjectIdentifiers.id_aria256_ccm, "CCM");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", NSRIObjectIdentifiers.id_aria128_ccm, "CCM");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", NSRIObjectIdentifiers.id_aria192_ccm, "CCM");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", NSRIObjectIdentifiers.id_aria256_ccm, "CCM");
            configurableProvider.addAlgorithm("AlgorithmParameterGenerator.ARIAGCM", str + "$AlgParamGenGCM");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator." + NSRIObjectIdentifiers.id_aria128_gcm, CodePackage.GCM);
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator." + NSRIObjectIdentifiers.id_aria192_gcm, CodePackage.GCM);
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator." + NSRIObjectIdentifiers.id_aria256_gcm, CodePackage.GCM);
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", NSRIObjectIdentifiers.id_aria128_gcm, CodePackage.GCM);
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", NSRIObjectIdentifiers.id_aria192_gcm, CodePackage.GCM);
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", NSRIObjectIdentifiers.id_aria256_gcm, CodePackage.GCM);
            addGMacAlgorithm(configurableProvider, "ARIA", str + "$GMAC", str + "$KeyGen");
            addPoly1305Algorithm(configurableProvider, "ARIA", str + "$Poly1305", str + "$Poly1305KeyGen");
        }
    }
}
