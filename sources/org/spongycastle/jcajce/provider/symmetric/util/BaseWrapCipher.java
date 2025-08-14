package org.spongycastle.jcajce.provider.symmetric.util;

import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.CipherSpi;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.RC2ParameterSpec;
import javax.crypto.spec.RC5ParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.spongycastle.asn1.pkcs.PrivateKeyInfo;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.Wrapper;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.crypto.params.ParametersWithSBox;
import org.spongycastle.crypto.params.ParametersWithUKM;
import org.spongycastle.jcajce.provider.symmetric.util.PBE;
import org.spongycastle.jcajce.spec.GOST28147WrapParameterSpec;
import org.spongycastle.jcajce.util.BCJcaJceHelper;
import org.spongycastle.jcajce.util.JcaJceHelper;
import org.spongycastle.jce.provider.BouncyCastleProvider;
import org.spongycastle.util.Arrays;

/* loaded from: classes7.dex */
public abstract class BaseWrapCipher extends CipherSpi implements PBE {
    private Class[] availableSpecs;
    protected AlgorithmParameters engineParams;
    private final JcaJceHelper helper;
    private byte[] iv;
    private int ivSize;
    protected int pbeHash;
    protected int pbeIvSize;
    protected int pbeKeySize;
    protected int pbeType;
    protected Wrapper wrapEngine;

    @Override // javax.crypto.CipherSpi
    protected int engineDoFinal(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws BadPaddingException, IllegalBlockSizeException, ShortBufferException {
        return 0;
    }

    @Override // javax.crypto.CipherSpi
    protected byte[] engineDoFinal(byte[] bArr, int i, int i2) throws BadPaddingException, IllegalBlockSizeException {
        return null;
    }

    @Override // javax.crypto.CipherSpi
    protected int engineGetBlockSize() {
        return 0;
    }

    @Override // javax.crypto.CipherSpi
    protected int engineGetOutputSize(int i) {
        return -1;
    }

    @Override // javax.crypto.CipherSpi
    protected AlgorithmParameters engineGetParameters() {
        return null;
    }

    protected BaseWrapCipher() {
        this.availableSpecs = new Class[]{GOST28147WrapParameterSpec.class, PBEParameterSpec.class, RC2ParameterSpec.class, RC5ParameterSpec.class, IvParameterSpec.class};
        this.pbeType = 2;
        this.pbeHash = 1;
        this.engineParams = null;
        this.wrapEngine = null;
        this.helper = new BCJcaJceHelper();
    }

    protected BaseWrapCipher(Wrapper wrapper) {
        this(wrapper, 0);
    }

    protected BaseWrapCipher(Wrapper wrapper, int i) {
        this.availableSpecs = new Class[]{GOST28147WrapParameterSpec.class, PBEParameterSpec.class, RC2ParameterSpec.class, RC5ParameterSpec.class, IvParameterSpec.class};
        this.pbeType = 2;
        this.pbeHash = 1;
        this.engineParams = null;
        this.wrapEngine = null;
        this.helper = new BCJcaJceHelper();
        this.wrapEngine = wrapper;
        this.ivSize = i;
    }

    @Override // javax.crypto.CipherSpi
    protected byte[] engineGetIV() {
        return Arrays.clone(this.iv);
    }

    @Override // javax.crypto.CipherSpi
    protected int engineGetKeySize(Key key) {
        return key.getEncoded().length * 8;
    }

    protected final AlgorithmParameters createParametersInstance(String str) throws NoSuchAlgorithmException, NoSuchProviderException {
        return this.helper.createAlgorithmParameters(str);
    }

    @Override // javax.crypto.CipherSpi
    protected void engineSetMode(String str) throws NoSuchAlgorithmException {
        throw new NoSuchAlgorithmException("can't support mode " + str);
    }

    @Override // javax.crypto.CipherSpi
    protected void engineSetPadding(String str) throws NoSuchPaddingException {
        throw new NoSuchPaddingException("Padding " + str + " unknown.");
    }

    @Override // javax.crypto.CipherSpi
    protected void engineInit(int i, Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidKeyException, InvalidAlgorithmParameterException {
        CipherParameters keyParameter;
        int i2;
        if (key instanceof BCPBEKey) {
            BCPBEKey bCPBEKey = (BCPBEKey) key;
            if (algorithmParameterSpec instanceof PBEParameterSpec) {
                keyParameter = PBE.Util.makePBEParameters(bCPBEKey, algorithmParameterSpec, this.wrapEngine.getAlgorithmName());
            } else if (bCPBEKey.getParam() != null) {
                keyParameter = bCPBEKey.getParam();
            } else {
                throw new InvalidAlgorithmParameterException("PBE requires PBE parameters to be set.");
            }
        } else {
            keyParameter = new KeyParameter(key.getEncoded());
        }
        if (algorithmParameterSpec instanceof IvParameterSpec) {
            keyParameter = new ParametersWithIV(keyParameter, ((IvParameterSpec) algorithmParameterSpec).getIV());
        }
        if (algorithmParameterSpec instanceof GOST28147WrapParameterSpec) {
            GOST28147WrapParameterSpec gOST28147WrapParameterSpec = (GOST28147WrapParameterSpec) algorithmParameterSpec;
            byte[] sBox = gOST28147WrapParameterSpec.getSBox();
            if (sBox != null) {
                keyParameter = new ParametersWithSBox(keyParameter, sBox);
            }
            keyParameter = new ParametersWithUKM(keyParameter, gOST28147WrapParameterSpec.getUKM());
        }
        if ((keyParameter instanceof KeyParameter) && (i2 = this.ivSize) != 0) {
            byte[] bArr = new byte[i2];
            this.iv = bArr;
            secureRandom.nextBytes(bArr);
            keyParameter = new ParametersWithIV(keyParameter, this.iv);
        }
        if (secureRandom != null) {
            keyParameter = new ParametersWithRandom(keyParameter, secureRandom);
        }
        if (i == 1 || i == 2) {
            throw new IllegalArgumentException("engine only valid for wrapping");
        }
        if (i == 3) {
            this.wrapEngine.init(true, keyParameter);
        } else if (i == 4) {
            this.wrapEngine.init(false, keyParameter);
        } else {
            System.out.println("eeek!");
        }
    }

    @Override // javax.crypto.CipherSpi
    protected void engineInit(int i, Key key, AlgorithmParameters algorithmParameters, SecureRandom secureRandom) throws InvalidParameterSpecException, InvalidKeyException, InvalidAlgorithmParameterException {
        AlgorithmParameterSpec parameterSpec = null;
        if (algorithmParameters != null) {
            int i2 = 0;
            while (true) {
                Class[] clsArr = this.availableSpecs;
                if (i2 == clsArr.length) {
                    break;
                }
                try {
                    parameterSpec = algorithmParameters.getParameterSpec(clsArr[i2]);
                    break;
                } catch (Exception unused) {
                    i2++;
                }
            }
            if (parameterSpec == null) {
                throw new InvalidAlgorithmParameterException("can't handle parameter " + algorithmParameters.toString());
            }
        }
        this.engineParams = algorithmParameters;
        engineInit(i, key, parameterSpec, secureRandom);
    }

    @Override // javax.crypto.CipherSpi
    protected void engineInit(int i, Key key, SecureRandom secureRandom) throws InvalidKeyException {
        try {
            engineInit(i, key, (AlgorithmParameterSpec) null, secureRandom);
        } catch (InvalidAlgorithmParameterException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override // javax.crypto.CipherSpi
    protected byte[] engineUpdate(byte[] bArr, int i, int i2) {
        throw new RuntimeException("not supported for wrapping");
    }

    @Override // javax.crypto.CipherSpi
    protected int engineUpdate(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws ShortBufferException {
        throw new RuntimeException("not supported for wrapping");
    }

    @Override // javax.crypto.CipherSpi
    protected byte[] engineWrap(Key key) throws IllegalBlockSizeException, InvalidKeyException {
        byte[] encoded = key.getEncoded();
        if (encoded == null) {
            throw new InvalidKeyException("Cannot wrap key, null encoding.");
        }
        try {
            Wrapper wrapper = this.wrapEngine;
            if (wrapper == null) {
                return engineDoFinal(encoded, 0, encoded.length);
            }
            return wrapper.wrap(encoded, 0, encoded.length);
        } catch (BadPaddingException e) {
            throw new IllegalBlockSizeException(e.getMessage());
        }
    }

    @Override // javax.crypto.CipherSpi
    protected Key engineUnwrap(byte[] bArr, String str, int i) throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] bArrUnwrap;
        try {
            Wrapper wrapper = this.wrapEngine;
            if (wrapper == null) {
                bArrUnwrap = engineDoFinal(bArr, 0, bArr.length);
            } else {
                bArrUnwrap = wrapper.unwrap(bArr, 0, bArr.length);
            }
            if (i == 3) {
                return new SecretKeySpec(bArrUnwrap, str);
            }
            if (str.equals("") && i == 2) {
                try {
                    PrivateKeyInfo privateKeyInfo = PrivateKeyInfo.getInstance(bArrUnwrap);
                    PrivateKey privateKey = BouncyCastleProvider.getPrivateKey(privateKeyInfo);
                    if (privateKey != null) {
                        return privateKey;
                    }
                    throw new InvalidKeyException("algorithm " + privateKeyInfo.getPrivateKeyAlgorithm().getAlgorithm() + " not supported");
                } catch (Exception unused) {
                    throw new InvalidKeyException("Invalid key encoding.");
                }
            }
            try {
                KeyFactory keyFactoryCreateKeyFactory = this.helper.createKeyFactory(str);
                if (i == 1) {
                    return keyFactoryCreateKeyFactory.generatePublic(new X509EncodedKeySpec(bArrUnwrap));
                }
                if (i == 2) {
                    return keyFactoryCreateKeyFactory.generatePrivate(new PKCS8EncodedKeySpec(bArrUnwrap));
                }
                throw new InvalidKeyException("Unknown key type " + i);
            } catch (NoSuchProviderException e) {
                throw new InvalidKeyException("Unknown key type " + e.getMessage());
            } catch (InvalidKeySpecException e2) {
                throw new InvalidKeyException("Unknown key type " + e2.getMessage());
            }
        } catch (BadPaddingException e3) {
            throw new InvalidKeyException(e3.getMessage());
        } catch (IllegalBlockSizeException e4) {
            throw new InvalidKeyException(e4.getMessage());
        } catch (InvalidCipherTextException e5) {
            throw new InvalidKeyException(e5.getMessage());
        }
    }
}
