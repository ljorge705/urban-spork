package org.spongycastle.jce.provider;

import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.spec.PBEParameterSpec;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.PBEParametersGenerator;
import org.spongycastle.crypto.digests.MD5Digest;
import org.spongycastle.crypto.digests.RIPEMD160Digest;
import org.spongycastle.crypto.digests.SHA1Digest;
import org.spongycastle.crypto.generators.PKCS12ParametersGenerator;
import org.spongycastle.crypto.generators.PKCS5S1ParametersGenerator;
import org.spongycastle.crypto.generators.PKCS5S2ParametersGenerator;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.jcajce.provider.symmetric.util.BCPBEKey;

/* loaded from: classes7.dex */
public interface BrokenPBE {
    public static final int MD5 = 0;
    public static final int OLD_PKCS12 = 3;
    public static final int PKCS12 = 2;
    public static final int PKCS5S1 = 0;
    public static final int PKCS5S2 = 1;
    public static final int RIPEMD160 = 2;
    public static final int SHA1 = 1;

    public static class Util {
        private static void setOddParity(byte[] bArr) {
            for (int i = 0; i < bArr.length; i++) {
                byte b = bArr[i];
                bArr[i] = (byte) ((((b >> 7) ^ ((((((b >> 1) ^ (b >> 2)) ^ (b >> 3)) ^ (b >> 4)) ^ (b >> 5)) ^ (b >> 6))) ^ 1) | (b & 254));
            }
        }

        private static PBEParametersGenerator makePBEGenerator(int i, int i2) {
            if (i == 0) {
                if (i2 == 0) {
                    return new PKCS5S1ParametersGenerator(new MD5Digest());
                }
                if (i2 == 1) {
                    return new PKCS5S1ParametersGenerator(new SHA1Digest());
                }
                throw new IllegalStateException("PKCS5 scheme 1 only supports only MD5 and SHA1.");
            }
            if (i == 1) {
                return new PKCS5S2ParametersGenerator();
            }
            if (i == 3) {
                if (i2 == 0) {
                    return new OldPKCS12ParametersGenerator(new MD5Digest());
                }
                if (i2 == 1) {
                    return new OldPKCS12ParametersGenerator(new SHA1Digest());
                }
                if (i2 == 2) {
                    return new OldPKCS12ParametersGenerator(new RIPEMD160Digest());
                }
                throw new IllegalStateException("unknown digest scheme for PBE encryption.");
            }
            if (i2 == 0) {
                return new PKCS12ParametersGenerator(new MD5Digest());
            }
            if (i2 == 1) {
                return new PKCS12ParametersGenerator(new SHA1Digest());
            }
            if (i2 == 2) {
                return new PKCS12ParametersGenerator(new RIPEMD160Digest());
            }
            throw new IllegalStateException("unknown digest scheme for PBE encryption.");
        }

        static CipherParameters makePBEParameters(BCPBEKey bCPBEKey, AlgorithmParameterSpec algorithmParameterSpec, int i, int i2, String str, int i3, int i4) {
            CipherParameters cipherParametersGenerateDerivedParameters;
            if (algorithmParameterSpec == null || !(algorithmParameterSpec instanceof PBEParameterSpec)) {
                throw new IllegalArgumentException("Need a PBEParameter spec with a PBE key.");
            }
            PBEParameterSpec pBEParameterSpec = (PBEParameterSpec) algorithmParameterSpec;
            PBEParametersGenerator pBEParametersGeneratorMakePBEGenerator = makePBEGenerator(i, i2);
            byte[] encoded = bCPBEKey.getEncoded();
            pBEParametersGeneratorMakePBEGenerator.init(encoded, pBEParameterSpec.getSalt(), pBEParameterSpec.getIterationCount());
            if (i4 != 0) {
                cipherParametersGenerateDerivedParameters = pBEParametersGeneratorMakePBEGenerator.generateDerivedParameters(i3, i4);
            } else {
                cipherParametersGenerateDerivedParameters = pBEParametersGeneratorMakePBEGenerator.generateDerivedParameters(i3);
            }
            if (str.startsWith("DES")) {
                if (cipherParametersGenerateDerivedParameters instanceof ParametersWithIV) {
                    setOddParity(((KeyParameter) ((ParametersWithIV) cipherParametersGenerateDerivedParameters).getParameters()).getKey());
                } else {
                    setOddParity(((KeyParameter) cipherParametersGenerateDerivedParameters).getKey());
                }
            }
            for (int i5 = 0; i5 != encoded.length; i5++) {
                encoded[i5] = 0;
            }
            return cipherParametersGenerateDerivedParameters;
        }

        static CipherParameters makePBEMacParameters(BCPBEKey bCPBEKey, AlgorithmParameterSpec algorithmParameterSpec, int i, int i2, int i3) {
            if (algorithmParameterSpec == null || !(algorithmParameterSpec instanceof PBEParameterSpec)) {
                throw new IllegalArgumentException("Need a PBEParameter spec with a PBE key.");
            }
            PBEParameterSpec pBEParameterSpec = (PBEParameterSpec) algorithmParameterSpec;
            PBEParametersGenerator pBEParametersGeneratorMakePBEGenerator = makePBEGenerator(i, i2);
            byte[] encoded = bCPBEKey.getEncoded();
            pBEParametersGeneratorMakePBEGenerator.init(encoded, pBEParameterSpec.getSalt(), pBEParameterSpec.getIterationCount());
            CipherParameters cipherParametersGenerateDerivedMacParameters = pBEParametersGeneratorMakePBEGenerator.generateDerivedMacParameters(i3);
            for (int i4 = 0; i4 != encoded.length; i4++) {
                encoded[i4] = 0;
            }
            return cipherParametersGenerateDerivedMacParameters;
        }
    }
}
