package org.spongycastle.jcajce.provider.symmetric.util;

import com.google.android.gms.stats.CodePackage;
import com.oblador.keychain.cipherStorage.CipherStorageKeystoreAesCbc;
import com.oblador.keychain.cipherStorage.CipherStorageKeystoreRsaEcb;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.ShortBufferException;
import javax.crypto.interfaces.PBEKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.RC2ParameterSpec;
import javax.crypto.spec.RC5ParameterSpec;
import org.spongycastle.asn1.cms.GCMParameters;
import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.BufferedBlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.engines.DSTU7624Engine;
import org.spongycastle.crypto.modes.AEADBlockCipher;
import org.spongycastle.crypto.modes.CBCBlockCipher;
import org.spongycastle.crypto.modes.CCMBlockCipher;
import org.spongycastle.crypto.modes.CFBBlockCipher;
import org.spongycastle.crypto.modes.CTSBlockCipher;
import org.spongycastle.crypto.modes.EAXBlockCipher;
import org.spongycastle.crypto.modes.GCFBBlockCipher;
import org.spongycastle.crypto.modes.GCMBlockCipher;
import org.spongycastle.crypto.modes.GOFBBlockCipher;
import org.spongycastle.crypto.modes.KCCMBlockCipher;
import org.spongycastle.crypto.modes.KCTRBlockCipher;
import org.spongycastle.crypto.modes.KGCMBlockCipher;
import org.spongycastle.crypto.modes.OCBBlockCipher;
import org.spongycastle.crypto.modes.OFBBlockCipher;
import org.spongycastle.crypto.modes.OpenPGPCFBBlockCipher;
import org.spongycastle.crypto.modes.PGPCFBBlockCipher;
import org.spongycastle.crypto.modes.SICBlockCipher;
import org.spongycastle.crypto.paddings.BlockCipherPadding;
import org.spongycastle.crypto.paddings.ISO10126d2Padding;
import org.spongycastle.crypto.paddings.ISO7816d4Padding;
import org.spongycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.spongycastle.crypto.paddings.TBCPadding;
import org.spongycastle.crypto.paddings.X923Padding;
import org.spongycastle.crypto.paddings.ZeroBytePadding;
import org.spongycastle.crypto.params.AEADParameters;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.crypto.params.ParametersWithSBox;
import org.spongycastle.crypto.params.RC2Parameters;
import org.spongycastle.crypto.params.RC5Parameters;
import org.spongycastle.jcajce.PBKDF1Key;
import org.spongycastle.jcajce.PBKDF1KeyWithParameters;
import org.spongycastle.jcajce.PKCS12Key;
import org.spongycastle.jcajce.PKCS12KeyWithParameters;
import org.spongycastle.jcajce.provider.symmetric.util.PBE;
import org.spongycastle.jcajce.spec.AEADParameterSpec;
import org.spongycastle.jcajce.spec.GOST28147ParameterSpec;
import org.spongycastle.jcajce.spec.RepeatedSecretKeySpec;
import org.spongycastle.util.Strings;

/* loaded from: classes7.dex */
public class BaseBlockCipher extends BaseWrapCipher implements PBE {
    private static final Class gcmSpecClass = ClassUtil.loadClass(BaseBlockCipher.class, "javax.crypto.spec.GCMParameterSpec");
    private AEADParameters aeadParams;
    private Class[] availableSpecs;
    private BlockCipher baseEngine;
    private GenericBlockCipher cipher;
    private int digest;
    private BlockCipherProvider engineProvider;
    private boolean fixedIv;
    private int ivLength;
    private ParametersWithIV ivParam;
    private int keySizeInBits;
    private String modeName;
    private boolean padded;
    private String pbeAlgorithm;
    private PBEParameterSpec pbeSpec;
    private int scheme;

    private interface GenericBlockCipher {
        int doFinal(byte[] bArr, int i) throws IllegalStateException, BadPaddingException;

        String getAlgorithmName();

        int getOutputSize(int i);

        BlockCipher getUnderlyingCipher();

        int getUpdateOutputSize(int i);

        void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException;

        int processByte(byte b, byte[] bArr, int i) throws DataLengthException;

        int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws DataLengthException;

        void updateAAD(byte[] bArr, int i, int i2);

        boolean wrapOnNoPadding();
    }

    protected BaseBlockCipher(BlockCipher blockCipher) {
        this.availableSpecs = new Class[]{RC2ParameterSpec.class, RC5ParameterSpec.class, gcmSpecClass, GOST28147ParameterSpec.class, IvParameterSpec.class, PBEParameterSpec.class};
        this.scheme = -1;
        this.ivLength = 0;
        this.fixedIv = true;
        this.pbeSpec = null;
        this.pbeAlgorithm = null;
        this.modeName = null;
        this.baseEngine = blockCipher;
        this.cipher = new BufferedGenericBlockCipher(blockCipher);
    }

    protected BaseBlockCipher(BlockCipher blockCipher, int i, int i2, int i3, int i4) {
        this.availableSpecs = new Class[]{RC2ParameterSpec.class, RC5ParameterSpec.class, gcmSpecClass, GOST28147ParameterSpec.class, IvParameterSpec.class, PBEParameterSpec.class};
        this.fixedIv = true;
        this.pbeSpec = null;
        this.pbeAlgorithm = null;
        this.modeName = null;
        this.baseEngine = blockCipher;
        this.scheme = i;
        this.digest = i2;
        this.keySizeInBits = i3;
        this.ivLength = i4;
        this.cipher = new BufferedGenericBlockCipher(blockCipher);
    }

    protected BaseBlockCipher(BlockCipherProvider blockCipherProvider) {
        this.availableSpecs = new Class[]{RC2ParameterSpec.class, RC5ParameterSpec.class, gcmSpecClass, GOST28147ParameterSpec.class, IvParameterSpec.class, PBEParameterSpec.class};
        this.scheme = -1;
        this.ivLength = 0;
        this.fixedIv = true;
        this.pbeSpec = null;
        this.pbeAlgorithm = null;
        this.modeName = null;
        this.baseEngine = blockCipherProvider.get();
        this.engineProvider = blockCipherProvider;
        this.cipher = new BufferedGenericBlockCipher(blockCipherProvider.get());
    }

    protected BaseBlockCipher(AEADBlockCipher aEADBlockCipher) {
        this.availableSpecs = new Class[]{RC2ParameterSpec.class, RC5ParameterSpec.class, gcmSpecClass, GOST28147ParameterSpec.class, IvParameterSpec.class, PBEParameterSpec.class};
        this.scheme = -1;
        this.ivLength = 0;
        this.fixedIv = true;
        this.pbeSpec = null;
        this.pbeAlgorithm = null;
        this.modeName = null;
        BlockCipher underlyingCipher = aEADBlockCipher.getUnderlyingCipher();
        this.baseEngine = underlyingCipher;
        this.ivLength = underlyingCipher.getBlockSize();
        this.cipher = new AEADGenericBlockCipher(aEADBlockCipher);
    }

    protected BaseBlockCipher(AEADBlockCipher aEADBlockCipher, boolean z, int i) {
        this.availableSpecs = new Class[]{RC2ParameterSpec.class, RC5ParameterSpec.class, gcmSpecClass, GOST28147ParameterSpec.class, IvParameterSpec.class, PBEParameterSpec.class};
        this.scheme = -1;
        this.ivLength = 0;
        this.fixedIv = true;
        this.pbeSpec = null;
        this.pbeAlgorithm = null;
        this.modeName = null;
        this.baseEngine = aEADBlockCipher.getUnderlyingCipher();
        this.fixedIv = z;
        this.ivLength = i;
        this.cipher = new AEADGenericBlockCipher(aEADBlockCipher);
    }

    protected BaseBlockCipher(BlockCipher blockCipher, int i) {
        this.availableSpecs = new Class[]{RC2ParameterSpec.class, RC5ParameterSpec.class, gcmSpecClass, GOST28147ParameterSpec.class, IvParameterSpec.class, PBEParameterSpec.class};
        this.scheme = -1;
        this.ivLength = 0;
        this.fixedIv = true;
        this.pbeSpec = null;
        this.pbeAlgorithm = null;
        this.modeName = null;
        this.baseEngine = blockCipher;
        this.cipher = new BufferedGenericBlockCipher(blockCipher);
        this.ivLength = i / 8;
    }

    protected BaseBlockCipher(BufferedBlockCipher bufferedBlockCipher, int i) {
        this.availableSpecs = new Class[]{RC2ParameterSpec.class, RC5ParameterSpec.class, gcmSpecClass, GOST28147ParameterSpec.class, IvParameterSpec.class, PBEParameterSpec.class};
        this.scheme = -1;
        this.ivLength = 0;
        this.fixedIv = true;
        this.pbeSpec = null;
        this.pbeAlgorithm = null;
        this.modeName = null;
        this.baseEngine = bufferedBlockCipher.getUnderlyingCipher();
        this.cipher = new BufferedGenericBlockCipher(bufferedBlockCipher);
        this.ivLength = i / 8;
    }

    @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseWrapCipher, javax.crypto.CipherSpi
    protected int engineGetBlockSize() {
        return this.baseEngine.getBlockSize();
    }

    @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseWrapCipher, javax.crypto.CipherSpi
    protected byte[] engineGetIV() {
        AEADParameters aEADParameters = this.aeadParams;
        if (aEADParameters != null) {
            return aEADParameters.getNonce();
        }
        ParametersWithIV parametersWithIV = this.ivParam;
        if (parametersWithIV != null) {
            return parametersWithIV.getIV();
        }
        return null;
    }

    @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseWrapCipher, javax.crypto.CipherSpi
    protected int engineGetKeySize(Key key) {
        return key.getEncoded().length * 8;
    }

    @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseWrapCipher, javax.crypto.CipherSpi
    protected int engineGetOutputSize(int i) {
        return this.cipher.getOutputSize(i);
    }

    @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseWrapCipher, javax.crypto.CipherSpi
    protected AlgorithmParameters engineGetParameters() throws InvalidParameterSpecException, IOException {
        if (this.engineParams == null) {
            if (this.pbeSpec != null) {
                try {
                    this.engineParams = createParametersInstance(this.pbeAlgorithm);
                    this.engineParams.init(this.pbeSpec);
                } catch (Exception unused) {
                    return null;
                }
            } else if (this.aeadParams != null) {
                try {
                    this.engineParams = createParametersInstance(CodePackage.GCM);
                    this.engineParams.init(new GCMParameters(this.aeadParams.getNonce(), this.aeadParams.getMacSize() / 8).getEncoded());
                } catch (Exception e) {
                    throw new RuntimeException(e.toString());
                }
            } else if (this.ivParam != null) {
                String algorithmName = this.cipher.getUnderlyingCipher().getAlgorithmName();
                if (algorithmName.indexOf(47) >= 0) {
                    algorithmName = algorithmName.substring(0, algorithmName.indexOf(47));
                }
                try {
                    this.engineParams = createParametersInstance(algorithmName);
                    this.engineParams.init(new IvParameterSpec(this.ivParam.getIV()));
                } catch (Exception e2) {
                    throw new RuntimeException(e2.toString());
                }
            }
        }
        return this.engineParams;
    }

    @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseWrapCipher, javax.crypto.CipherSpi
    protected void engineSetMode(String str) throws NoSuchAlgorithmException, NumberFormatException {
        String upperCase = Strings.toUpperCase(str);
        this.modeName = upperCase;
        if (upperCase.equals(CipherStorageKeystoreRsaEcb.BLOCK_MODE_ECB)) {
            this.ivLength = 0;
            this.cipher = new BufferedGenericBlockCipher(this.baseEngine);
            return;
        }
        if (this.modeName.equals(CipherStorageKeystoreAesCbc.BLOCK_MODE_CBC)) {
            this.ivLength = this.baseEngine.getBlockSize();
            this.cipher = new BufferedGenericBlockCipher(new CBCBlockCipher(this.baseEngine));
            return;
        }
        if (this.modeName.startsWith("OFB")) {
            this.ivLength = this.baseEngine.getBlockSize();
            if (this.modeName.length() != 3) {
                this.cipher = new BufferedGenericBlockCipher(new OFBBlockCipher(this.baseEngine, Integer.parseInt(this.modeName.substring(3))));
                return;
            } else {
                BlockCipher blockCipher = this.baseEngine;
                this.cipher = new BufferedGenericBlockCipher(new OFBBlockCipher(blockCipher, blockCipher.getBlockSize() * 8));
                return;
            }
        }
        if (this.modeName.startsWith("CFB")) {
            this.ivLength = this.baseEngine.getBlockSize();
            if (this.modeName.length() != 3) {
                this.cipher = new BufferedGenericBlockCipher(new CFBBlockCipher(this.baseEngine, Integer.parseInt(this.modeName.substring(3))));
                return;
            } else {
                BlockCipher blockCipher2 = this.baseEngine;
                this.cipher = new BufferedGenericBlockCipher(new CFBBlockCipher(blockCipher2, blockCipher2.getBlockSize() * 8));
                return;
            }
        }
        if (this.modeName.startsWith("PGP")) {
            boolean zEqualsIgnoreCase = this.modeName.equalsIgnoreCase("PGPCFBwithIV");
            this.ivLength = this.baseEngine.getBlockSize();
            this.cipher = new BufferedGenericBlockCipher(new PGPCFBBlockCipher(this.baseEngine, zEqualsIgnoreCase));
            return;
        }
        if (this.modeName.equalsIgnoreCase("OpenPGPCFB")) {
            this.ivLength = 0;
            this.cipher = new BufferedGenericBlockCipher(new OpenPGPCFBBlockCipher(this.baseEngine));
            return;
        }
        if (this.modeName.startsWith("SIC")) {
            int blockSize = this.baseEngine.getBlockSize();
            this.ivLength = blockSize;
            if (blockSize < 16) {
                throw new IllegalArgumentException("Warning: SIC-Mode can become a twotime-pad if the blocksize of the cipher is too small. Use a cipher with a block size of at least 128 bits (e.g. AES)");
            }
            this.fixedIv = false;
            this.cipher = new BufferedGenericBlockCipher(new BufferedBlockCipher(new SICBlockCipher(this.baseEngine)));
            return;
        }
        if (this.modeName.startsWith("CTR")) {
            this.ivLength = this.baseEngine.getBlockSize();
            this.fixedIv = false;
            if (this.baseEngine instanceof DSTU7624Engine) {
                this.cipher = new BufferedGenericBlockCipher(new BufferedBlockCipher(new KCTRBlockCipher(this.baseEngine)));
                return;
            } else {
                this.cipher = new BufferedGenericBlockCipher(new BufferedBlockCipher(new SICBlockCipher(this.baseEngine)));
                return;
            }
        }
        if (this.modeName.startsWith("GOFB")) {
            this.ivLength = this.baseEngine.getBlockSize();
            this.cipher = new BufferedGenericBlockCipher(new BufferedBlockCipher(new GOFBBlockCipher(this.baseEngine)));
            return;
        }
        if (this.modeName.startsWith("GCFB")) {
            this.ivLength = this.baseEngine.getBlockSize();
            this.cipher = new BufferedGenericBlockCipher(new BufferedBlockCipher(new GCFBBlockCipher(this.baseEngine)));
            return;
        }
        if (this.modeName.startsWith("CTS")) {
            this.ivLength = this.baseEngine.getBlockSize();
            this.cipher = new BufferedGenericBlockCipher(new CTSBlockCipher(new CBCBlockCipher(this.baseEngine)));
            return;
        }
        if (this.modeName.startsWith("CCM")) {
            this.ivLength = 13;
            if (this.baseEngine instanceof DSTU7624Engine) {
                this.cipher = new AEADGenericBlockCipher(new KCCMBlockCipher(this.baseEngine));
                return;
            } else {
                this.cipher = new AEADGenericBlockCipher(new CCMBlockCipher(this.baseEngine));
                return;
            }
        }
        if (this.modeName.startsWith("OCB")) {
            if (this.engineProvider != null) {
                this.ivLength = 15;
                this.cipher = new AEADGenericBlockCipher(new OCBBlockCipher(this.baseEngine, this.engineProvider.get()));
                return;
            }
            throw new NoSuchAlgorithmException("can't support mode " + str);
        }
        if (this.modeName.startsWith("EAX")) {
            this.ivLength = this.baseEngine.getBlockSize();
            this.cipher = new AEADGenericBlockCipher(new EAXBlockCipher(this.baseEngine));
        } else {
            if (this.modeName.startsWith(CodePackage.GCM)) {
                this.ivLength = this.baseEngine.getBlockSize();
                if (this.baseEngine instanceof DSTU7624Engine) {
                    this.cipher = new AEADGenericBlockCipher(new KGCMBlockCipher(this.baseEngine));
                    return;
                } else {
                    this.cipher = new AEADGenericBlockCipher(new GCMBlockCipher(this.baseEngine));
                    return;
                }
            }
            throw new NoSuchAlgorithmException("can't support mode " + str);
        }
    }

    @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseWrapCipher, javax.crypto.CipherSpi
    protected void engineSetPadding(String str) throws NoSuchPaddingException {
        String upperCase = Strings.toUpperCase(str);
        if (upperCase.equals("NOPADDING")) {
            if (this.cipher.wrapOnNoPadding()) {
                this.cipher = new BufferedGenericBlockCipher(new BufferedBlockCipher(this.cipher.getUnderlyingCipher()));
                return;
            }
            return;
        }
        if (upperCase.equals("WITHCTS")) {
            this.cipher = new BufferedGenericBlockCipher(new CTSBlockCipher(this.cipher.getUnderlyingCipher()));
            return;
        }
        this.padded = true;
        if (isAEADModeName(this.modeName)) {
            throw new NoSuchPaddingException("Only NoPadding can be used with AEAD modes.");
        }
        if (upperCase.equals("PKCS5PADDING") || upperCase.equals("PKCS7PADDING")) {
            this.cipher = new BufferedGenericBlockCipher(this.cipher.getUnderlyingCipher());
            return;
        }
        if (upperCase.equals("ZEROBYTEPADDING")) {
            this.cipher = new BufferedGenericBlockCipher(this.cipher.getUnderlyingCipher(), new ZeroBytePadding());
            return;
        }
        if (upperCase.equals("ISO10126PADDING") || upperCase.equals("ISO10126-2PADDING")) {
            this.cipher = new BufferedGenericBlockCipher(this.cipher.getUnderlyingCipher(), new ISO10126d2Padding());
            return;
        }
        if (upperCase.equals("X9.23PADDING") || upperCase.equals("X923PADDING")) {
            this.cipher = new BufferedGenericBlockCipher(this.cipher.getUnderlyingCipher(), new X923Padding());
            return;
        }
        if (upperCase.equals("ISO7816-4PADDING") || upperCase.equals("ISO9797-1PADDING")) {
            this.cipher = new BufferedGenericBlockCipher(this.cipher.getUnderlyingCipher(), new ISO7816d4Padding());
        } else {
            if (upperCase.equals("TBCPADDING")) {
                this.cipher = new BufferedGenericBlockCipher(this.cipher.getUnderlyingCipher(), new TBCPadding());
                return;
            }
            throw new NoSuchPaddingException("Padding " + str + " unknown.");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v82, types: [org.spongycastle.crypto.params.ParametersWithIV] */
    /* JADX WARN: Type inference failed for: r6v0 */
    /* JADX WARN: Type inference failed for: r6v11 */
    /* JADX WARN: Type inference failed for: r6v15, types: [org.spongycastle.crypto.CipherParameters, org.spongycastle.crypto.params.RC5Parameters] */
    /* JADX WARN: Type inference failed for: r6v18, types: [org.spongycastle.crypto.CipherParameters, org.spongycastle.crypto.params.RC2Parameters] */
    /* JADX WARN: Type inference failed for: r6v21 */
    /* JADX WARN: Type inference failed for: r6v22, types: [org.spongycastle.crypto.CipherParameters, org.spongycastle.crypto.params.ParametersWithSBox] */
    /* JADX WARN: Type inference failed for: r6v25, types: [org.spongycastle.crypto.params.ParametersWithIV] */
    /* JADX WARN: Type inference failed for: r6v28, types: [org.spongycastle.crypto.CipherParameters] */
    /* JADX WARN: Type inference failed for: r6v35, types: [org.spongycastle.crypto.params.AEADParameters] */
    /* JADX WARN: Type inference failed for: r6v56 */
    /* JADX WARN: Type inference failed for: r6v57 */
    /* JADX WARN: Type inference failed for: r6v58 */
    /* JADX WARN: Type inference failed for: r6v59 */
    /* JADX WARN: Type inference failed for: r6v60 */
    /* JADX WARN: Type inference failed for: r6v61 */
    /* JADX WARN: Type inference failed for: r6v62 */
    /* JADX WARN: Type inference failed for: r6v63 */
    /* JADX WARN: Type inference failed for: r6v64 */
    /* JADX WARN: Type inference failed for: r6v8, types: [org.spongycastle.crypto.CipherParameters] */
    @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseWrapCipher, javax.crypto.CipherSpi
    protected void engineInit(int i, Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws NoSuchMethodException, InvalidKeyException, SecurityException, InvalidAlgorithmParameterException {
        CipherParameters cipherParametersMakePBEParameters;
        KeyParameter keyParameter;
        ParametersWithIV parametersWithIV;
        ParametersWithIV parametersWithIV2;
        KeyParameter keyParameter2;
        CipherParameters cipherParametersMakePBEParameters2;
        KeyParameter rC5Parameters = 0;
        this.pbeSpec = null;
        this.pbeAlgorithm = null;
        this.engineParams = null;
        this.aeadParams = null;
        if (!(key instanceof SecretKey)) {
            throw new InvalidKeyException("Key for algorithm " + (key != null ? key.getAlgorithm() : null) + " not suitable for symmetric enryption.");
        }
        if (algorithmParameterSpec == null && this.baseEngine.getAlgorithmName().startsWith("RC5-64")) {
            throw new InvalidAlgorithmParameterException("RC5 requires an RC5ParametersSpec to be passed in.");
        }
        int i2 = this.scheme;
        if (i2 == 2 || (key instanceof PKCS12Key)) {
            try {
                SecretKey secretKey = (SecretKey) key;
                if (algorithmParameterSpec instanceof PBEParameterSpec) {
                    this.pbeSpec = (PBEParameterSpec) algorithmParameterSpec;
                }
                boolean z = secretKey instanceof PBEKey;
                if (z && this.pbeSpec == null) {
                    PBEKey pBEKey = (PBEKey) secretKey;
                    if (pBEKey.getSalt() == null) {
                        throw new InvalidAlgorithmParameterException("PBEKey requires parameters to specify salt");
                    }
                    this.pbeSpec = new PBEParameterSpec(pBEKey.getSalt(), pBEKey.getIterationCount());
                }
                if (this.pbeSpec == null && !z) {
                    throw new InvalidKeyException("Algorithm requires a PBE key");
                }
                if (key instanceof BCPBEKey) {
                    CipherParameters param = ((BCPBEKey) key).getParam();
                    if (!(param instanceof ParametersWithIV)) {
                        if (param == null) {
                            param = PBE.Util.makePBEParameters(secretKey.getEncoded(), 2, this.digest, this.keySizeInBits, this.ivLength * 8, this.pbeSpec, this.cipher.getAlgorithmName());
                        } else {
                            throw new InvalidKeyException("Algorithm requires a PBE key suitable for PKCS12");
                        }
                    }
                    cipherParametersMakePBEParameters = param;
                } else {
                    cipherParametersMakePBEParameters = PBE.Util.makePBEParameters(secretKey.getEncoded(), 2, this.digest, this.keySizeInBits, this.ivLength * 8, this.pbeSpec, this.cipher.getAlgorithmName());
                }
                boolean z2 = cipherParametersMakePBEParameters instanceof ParametersWithIV;
                rC5Parameters = cipherParametersMakePBEParameters;
                if (z2) {
                    this.ivParam = (ParametersWithIV) cipherParametersMakePBEParameters;
                    rC5Parameters = cipherParametersMakePBEParameters;
                }
            } catch (Exception unused) {
                throw new InvalidKeyException("PKCS12 requires a SecretKey/PBEKey");
            }
        } else if (key instanceof PBKDF1Key) {
            PBKDF1Key pBKDF1Key = (PBKDF1Key) key;
            if (algorithmParameterSpec instanceof PBEParameterSpec) {
                this.pbeSpec = (PBEParameterSpec) algorithmParameterSpec;
            }
            if ((pBKDF1Key instanceof PBKDF1KeyWithParameters) && this.pbeSpec == null) {
                PBKDF1KeyWithParameters pBKDF1KeyWithParameters = (PBKDF1KeyWithParameters) pBKDF1Key;
                this.pbeSpec = new PBEParameterSpec(pBKDF1KeyWithParameters.getSalt(), pBKDF1KeyWithParameters.getIterationCount());
            }
            CipherParameters cipherParametersMakePBEParameters3 = PBE.Util.makePBEParameters(pBKDF1Key.getEncoded(), 0, this.digest, this.keySizeInBits, this.ivLength * 8, this.pbeSpec, this.cipher.getAlgorithmName());
            boolean z3 = cipherParametersMakePBEParameters3 instanceof ParametersWithIV;
            rC5Parameters = cipherParametersMakePBEParameters3;
            if (z3) {
                this.ivParam = (ParametersWithIV) cipherParametersMakePBEParameters3;
                rC5Parameters = cipherParametersMakePBEParameters3;
            }
        } else if (key instanceof BCPBEKey) {
            BCPBEKey bCPBEKey = (BCPBEKey) key;
            if (bCPBEKey.getOID() != null) {
                this.pbeAlgorithm = bCPBEKey.getOID().getId();
            } else {
                this.pbeAlgorithm = bCPBEKey.getAlgorithm();
            }
            if (bCPBEKey.getParam() != null) {
                cipherParametersMakePBEParameters2 = adjustParameters(algorithmParameterSpec, bCPBEKey.getParam());
            } else if (algorithmParameterSpec instanceof PBEParameterSpec) {
                this.pbeSpec = (PBEParameterSpec) algorithmParameterSpec;
                cipherParametersMakePBEParameters2 = PBE.Util.makePBEParameters(bCPBEKey, algorithmParameterSpec, this.cipher.getUnderlyingCipher().getAlgorithmName());
            } else {
                throw new InvalidAlgorithmParameterException("PBE requires PBE parameters to be set.");
            }
            boolean z4 = cipherParametersMakePBEParameters2 instanceof ParametersWithIV;
            rC5Parameters = cipherParametersMakePBEParameters2;
            if (z4) {
                this.ivParam = (ParametersWithIV) cipherParametersMakePBEParameters2;
                rC5Parameters = cipherParametersMakePBEParameters2;
            }
        } else if (key instanceof PBEKey) {
            PBEKey pBEKey2 = (PBEKey) key;
            PBEParameterSpec pBEParameterSpec = (PBEParameterSpec) algorithmParameterSpec;
            this.pbeSpec = pBEParameterSpec;
            if ((pBEKey2 instanceof PKCS12KeyWithParameters) && pBEParameterSpec == null) {
                this.pbeSpec = new PBEParameterSpec(pBEKey2.getSalt(), pBEKey2.getIterationCount());
            }
            CipherParameters cipherParametersMakePBEParameters4 = PBE.Util.makePBEParameters(pBEKey2.getEncoded(), this.scheme, this.digest, this.keySizeInBits, this.ivLength * 8, this.pbeSpec, this.cipher.getAlgorithmName());
            boolean z5 = cipherParametersMakePBEParameters4 instanceof ParametersWithIV;
            rC5Parameters = cipherParametersMakePBEParameters4;
            if (z5) {
                this.ivParam = (ParametersWithIV) cipherParametersMakePBEParameters4;
                rC5Parameters = cipherParametersMakePBEParameters4;
            }
        } else if (!(key instanceof RepeatedSecretKeySpec)) {
            if (i2 == 0 || i2 == 4 || i2 == 1 || i2 == 5) {
                throw new InvalidKeyException("Algorithm requires a PBE key");
            }
            rC5Parameters = new KeyParameter(key.getEncoded());
        }
        if (algorithmParameterSpec instanceof AEADParameterSpec) {
            if (!isAEADModeName(this.modeName) && !(this.cipher instanceof AEADGenericBlockCipher)) {
                throw new InvalidAlgorithmParameterException("AEADParameterSpec can only be used with AEAD modes.");
            }
            AEADParameterSpec aEADParameterSpec = (AEADParameterSpec) algorithmParameterSpec;
            if (rC5Parameters instanceof ParametersWithIV) {
                keyParameter2 = (KeyParameter) ((ParametersWithIV) rC5Parameters).getParameters();
            } else {
                keyParameter2 = rC5Parameters;
            }
            rC5Parameters = new AEADParameters(keyParameter2, aEADParameterSpec.getMacSizeInBits(), aEADParameterSpec.getNonce(), aEADParameterSpec.getAssociatedData());
            this.aeadParams = rC5Parameters;
        } else if (algorithmParameterSpec instanceof IvParameterSpec) {
            if (this.ivLength != 0) {
                IvParameterSpec ivParameterSpec = (IvParameterSpec) algorithmParameterSpec;
                if (ivParameterSpec.getIV().length != this.ivLength && !(this.cipher instanceof AEADGenericBlockCipher) && this.fixedIv) {
                    throw new InvalidAlgorithmParameterException("IV must be " + this.ivLength + " bytes long.");
                }
                if (rC5Parameters instanceof ParametersWithIV) {
                    parametersWithIV2 = new ParametersWithIV(((ParametersWithIV) rC5Parameters).getParameters(), ivParameterSpec.getIV());
                } else {
                    parametersWithIV2 = new ParametersWithIV(rC5Parameters, ivParameterSpec.getIV());
                }
                rC5Parameters = parametersWithIV2;
                this.ivParam = rC5Parameters;
            } else {
                String str = this.modeName;
                if (str != null && str.equals(CipherStorageKeystoreRsaEcb.BLOCK_MODE_ECB)) {
                    throw new InvalidAlgorithmParameterException("ECB mode does not use an IV");
                }
            }
        } else if (algorithmParameterSpec instanceof GOST28147ParameterSpec) {
            GOST28147ParameterSpec gOST28147ParameterSpec = (GOST28147ParameterSpec) algorithmParameterSpec;
            rC5Parameters = new ParametersWithSBox(new KeyParameter(key.getEncoded()), gOST28147ParameterSpec.getSbox());
            if (gOST28147ParameterSpec.getIV() != null && this.ivLength != 0) {
                if (rC5Parameters instanceof ParametersWithIV) {
                    parametersWithIV = new ParametersWithIV(((ParametersWithIV) rC5Parameters).getParameters(), gOST28147ParameterSpec.getIV());
                } else {
                    parametersWithIV = new ParametersWithIV(rC5Parameters, gOST28147ParameterSpec.getIV());
                }
                this.ivParam = parametersWithIV;
                rC5Parameters = parametersWithIV;
            }
        } else if (algorithmParameterSpec instanceof RC2ParameterSpec) {
            RC2ParameterSpec rC2ParameterSpec = (RC2ParameterSpec) algorithmParameterSpec;
            rC5Parameters = new RC2Parameters(key.getEncoded(), rC2ParameterSpec.getEffectiveKeyBits());
            if (rC2ParameterSpec.getIV() != null && this.ivLength != 0) {
                if (rC5Parameters instanceof ParametersWithIV) {
                    parametersWithIV = new ParametersWithIV(((ParametersWithIV) rC5Parameters).getParameters(), rC2ParameterSpec.getIV());
                } else {
                    parametersWithIV = new ParametersWithIV(rC5Parameters, rC2ParameterSpec.getIV());
                }
                this.ivParam = parametersWithIV;
                rC5Parameters = parametersWithIV;
            }
        } else if (algorithmParameterSpec instanceof RC5ParameterSpec) {
            RC5ParameterSpec rC5ParameterSpec = (RC5ParameterSpec) algorithmParameterSpec;
            rC5Parameters = new RC5Parameters(key.getEncoded(), rC5ParameterSpec.getRounds());
            if (this.baseEngine.getAlgorithmName().startsWith("RC5")) {
                if (this.baseEngine.getAlgorithmName().equals("RC5-32")) {
                    if (rC5ParameterSpec.getWordSize() != 32) {
                        throw new InvalidAlgorithmParameterException("RC5 already set up for a word size of 32 not " + rC5ParameterSpec.getWordSize() + ".");
                    }
                } else if (this.baseEngine.getAlgorithmName().equals("RC5-64") && rC5ParameterSpec.getWordSize() != 64) {
                    throw new InvalidAlgorithmParameterException("RC5 already set up for a word size of 64 not " + rC5ParameterSpec.getWordSize() + ".");
                }
                if (rC5ParameterSpec.getIV() != null && this.ivLength != 0) {
                    if (rC5Parameters instanceof ParametersWithIV) {
                        parametersWithIV = new ParametersWithIV(((ParametersWithIV) rC5Parameters).getParameters(), rC5ParameterSpec.getIV());
                    } else {
                        parametersWithIV = new ParametersWithIV(rC5Parameters, rC5ParameterSpec.getIV());
                    }
                    this.ivParam = parametersWithIV;
                    rC5Parameters = parametersWithIV;
                }
            } else {
                throw new InvalidAlgorithmParameterException("RC5 parameters passed to a cipher that is not RC5.");
            }
        } else {
            Class cls = gcmSpecClass;
            if (cls != null && cls.isInstance(algorithmParameterSpec)) {
                if (!isAEADModeName(this.modeName) && !(this.cipher instanceof AEADGenericBlockCipher)) {
                    throw new InvalidAlgorithmParameterException("GCMParameterSpec can only be used with AEAD modes.");
                }
                try {
                    Method declaredMethod = cls.getDeclaredMethod("getTLen", new Class[0]);
                    Method declaredMethod2 = cls.getDeclaredMethod("getIV", new Class[0]);
                    if (rC5Parameters instanceof ParametersWithIV) {
                        keyParameter = (KeyParameter) ((ParametersWithIV) rC5Parameters).getParameters();
                    } else {
                        keyParameter = rC5Parameters;
                    }
                    AEADParameters aEADParameters = new AEADParameters(keyParameter, ((Integer) declaredMethod.invoke(algorithmParameterSpec, new Object[0])).intValue(), (byte[]) declaredMethod2.invoke(algorithmParameterSpec, new Object[0]));
                    this.aeadParams = aEADParameters;
                    rC5Parameters = aEADParameters;
                } catch (Exception unused2) {
                    throw new InvalidAlgorithmParameterException("Cannot process GCMParameterSpec.");
                }
            } else if (algorithmParameterSpec != null && !(algorithmParameterSpec instanceof PBEParameterSpec)) {
                throw new InvalidAlgorithmParameterException("unknown parameter type.");
            }
        }
        ParametersWithRandom parametersWithRandom = rC5Parameters;
        if (this.ivLength != 0) {
            boolean z6 = rC5Parameters instanceof ParametersWithIV;
            parametersWithRandom = rC5Parameters;
            if (!z6) {
                boolean z7 = rC5Parameters instanceof AEADParameters;
                parametersWithRandom = rC5Parameters;
                if (!z7) {
                    SecureRandom secureRandom2 = secureRandom == null ? new SecureRandom() : secureRandom;
                    if (i == 1 || i == 3) {
                        byte[] bArr = new byte[this.ivLength];
                        secureRandom2.nextBytes(bArr);
                        ?? parametersWithIV3 = new ParametersWithIV(rC5Parameters, bArr);
                        this.ivParam = parametersWithIV3;
                        parametersWithRandom = parametersWithIV3;
                    } else {
                        parametersWithRandom = rC5Parameters;
                        if (this.cipher.getUnderlyingCipher().getAlgorithmName().indexOf("PGPCFB") < 0) {
                            throw new InvalidAlgorithmParameterException("no IV set when one expected");
                        }
                    }
                }
            }
        }
        if (secureRandom != null && this.padded) {
            parametersWithRandom = new ParametersWithRandom(parametersWithRandom, secureRandom);
        }
        try {
            if (i == 1) {
                this.cipher.init(true, parametersWithRandom);
            } else {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            throw new InvalidParameterException("unknown opmode " + i + " passed");
                        }
                    }
                    this.cipher.init(true, parametersWithRandom);
                }
                this.cipher.init(false, parametersWithRandom);
            }
            GenericBlockCipher genericBlockCipher = this.cipher;
            if ((genericBlockCipher instanceof AEADGenericBlockCipher) && this.aeadParams == null) {
                this.aeadParams = new AEADParameters((KeyParameter) this.ivParam.getParameters(), ((AEADGenericBlockCipher) genericBlockCipher).cipher.getMac().length * 8, this.ivParam.getIV());
            }
        } catch (Exception e) {
            throw new InvalidKeyOrParametersException(e.getMessage(), e);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private CipherParameters adjustParameters(AlgorithmParameterSpec algorithmParameterSpec, CipherParameters cipherParameters) {
        ParametersWithSBox parametersWithSBox;
        if (cipherParameters instanceof ParametersWithIV) {
            CipherParameters parameters = ((ParametersWithIV) cipherParameters).getParameters();
            if (algorithmParameterSpec instanceof IvParameterSpec) {
                ParametersWithIV parametersWithIV = new ParametersWithIV(parameters, ((IvParameterSpec) algorithmParameterSpec).getIV());
                this.ivParam = parametersWithIV;
                return parametersWithIV;
            }
            if (!(algorithmParameterSpec instanceof GOST28147ParameterSpec)) {
                return cipherParameters;
            }
            GOST28147ParameterSpec gOST28147ParameterSpec = (GOST28147ParameterSpec) algorithmParameterSpec;
            ParametersWithSBox parametersWithSBox2 = new ParametersWithSBox(cipherParameters, gOST28147ParameterSpec.getSbox());
            if (gOST28147ParameterSpec.getIV() == null || this.ivLength == 0) {
                return parametersWithSBox2;
            }
            ParametersWithIV parametersWithIV2 = new ParametersWithIV(parameters, gOST28147ParameterSpec.getIV());
            this.ivParam = parametersWithIV2;
            return parametersWithIV2;
        }
        if (algorithmParameterSpec instanceof IvParameterSpec) {
            ParametersWithIV parametersWithIV3 = new ParametersWithIV(cipherParameters, ((IvParameterSpec) algorithmParameterSpec).getIV());
            this.ivParam = parametersWithIV3;
            parametersWithSBox = parametersWithIV3;
        } else {
            if (!(algorithmParameterSpec instanceof GOST28147ParameterSpec)) {
                return cipherParameters;
            }
            GOST28147ParameterSpec gOST28147ParameterSpec2 = (GOST28147ParameterSpec) algorithmParameterSpec;
            ParametersWithSBox parametersWithSBox3 = new ParametersWithSBox(cipherParameters, gOST28147ParameterSpec2.getSbox());
            parametersWithSBox = parametersWithSBox3;
            if (gOST28147ParameterSpec2.getIV() != null) {
                parametersWithSBox = parametersWithSBox3;
                if (this.ivLength != 0) {
                    return new ParametersWithIV(parametersWithSBox3, gOST28147ParameterSpec2.getIV());
                }
            }
        }
        return parametersWithSBox;
    }

    @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseWrapCipher, javax.crypto.CipherSpi
    protected void engineInit(int i, Key key, AlgorithmParameters algorithmParameters, SecureRandom secureRandom) throws NoSuchMethodException, InvalidParameterSpecException, InvalidKeyException, SecurityException, InvalidAlgorithmParameterException {
        AlgorithmParameterSpec parameterSpec = null;
        if (algorithmParameters != null) {
            int i2 = 0;
            while (true) {
                Class[] clsArr = this.availableSpecs;
                if (i2 == clsArr.length) {
                    break;
                }
                Class cls = clsArr[i2];
                if (cls != null) {
                    try {
                        parameterSpec = algorithmParameters.getParameterSpec(cls);
                        break;
                    } catch (Exception unused) {
                        continue;
                    }
                }
                i2++;
            }
            if (parameterSpec == null) {
                throw new InvalidAlgorithmParameterException("can't handle parameter " + algorithmParameters.toString());
            }
        }
        engineInit(i, key, parameterSpec, secureRandom);
        this.engineParams = algorithmParameters;
    }

    @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseWrapCipher, javax.crypto.CipherSpi
    protected void engineInit(int i, Key key, SecureRandom secureRandom) throws NoSuchMethodException, InvalidKeyException, SecurityException {
        try {
            engineInit(i, key, (AlgorithmParameterSpec) null, secureRandom);
        } catch (InvalidAlgorithmParameterException e) {
            throw new InvalidKeyException(e.getMessage());
        }
    }

    @Override // javax.crypto.CipherSpi
    protected void engineUpdateAAD(byte[] bArr, int i, int i2) {
        this.cipher.updateAAD(bArr, i, i2);
    }

    @Override // javax.crypto.CipherSpi
    protected void engineUpdateAAD(ByteBuffer byteBuffer) {
        engineUpdateAAD(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.limit() - byteBuffer.position());
    }

    @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseWrapCipher, javax.crypto.CipherSpi
    protected byte[] engineUpdate(byte[] bArr, int i, int i2) throws DataLengthException {
        int updateOutputSize = this.cipher.getUpdateOutputSize(i2);
        if (updateOutputSize > 0) {
            byte[] bArr2 = new byte[updateOutputSize];
            int iProcessBytes = this.cipher.processBytes(bArr, i, i2, bArr2, 0);
            if (iProcessBytes == 0) {
                return null;
            }
            if (iProcessBytes == updateOutputSize) {
                return bArr2;
            }
            byte[] bArr3 = new byte[iProcessBytes];
            System.arraycopy(bArr2, 0, bArr3, 0, iProcessBytes);
            return bArr3;
        }
        this.cipher.processBytes(bArr, i, i2, null, 0);
        return null;
    }

    @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseWrapCipher, javax.crypto.CipherSpi
    protected int engineUpdate(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws ShortBufferException {
        if (this.cipher.getUpdateOutputSize(i2) + i3 > bArr2.length) {
            throw new ShortBufferException("output buffer too short for input.");
        }
        try {
            return this.cipher.processBytes(bArr, i, i2, bArr2, i3);
        } catch (DataLengthException e) {
            throw new IllegalStateException(e.toString());
        }
    }

    @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseWrapCipher, javax.crypto.CipherSpi
    protected byte[] engineDoFinal(byte[] bArr, int i, int i2) throws BadPaddingException, IllegalBlockSizeException {
        int iEngineGetOutputSize = engineGetOutputSize(i2);
        byte[] bArr2 = new byte[iEngineGetOutputSize];
        int iProcessBytes = i2 != 0 ? this.cipher.processBytes(bArr, i, i2, bArr2, 0) : 0;
        try {
            int iDoFinal = iProcessBytes + this.cipher.doFinal(bArr2, iProcessBytes);
            if (iDoFinal == iEngineGetOutputSize) {
                return bArr2;
            }
            byte[] bArr3 = new byte[iDoFinal];
            System.arraycopy(bArr2, 0, bArr3, 0, iDoFinal);
            return bArr3;
        } catch (DataLengthException e) {
            throw new IllegalBlockSizeException(e.getMessage());
        }
    }

    @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseWrapCipher, javax.crypto.CipherSpi
    protected int engineDoFinal(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws BadPaddingException, IllegalBlockSizeException, ShortBufferException {
        int iProcessBytes;
        if (engineGetOutputSize(i2) + i3 > bArr2.length) {
            throw new ShortBufferException("output buffer too short for input.");
        }
        if (i2 != 0) {
            try {
                iProcessBytes = this.cipher.processBytes(bArr, i, i2, bArr2, i3);
            } catch (OutputLengthException e) {
                throw new IllegalBlockSizeException(e.getMessage());
            } catch (DataLengthException e2) {
                throw new IllegalBlockSizeException(e2.getMessage());
            }
        } else {
            iProcessBytes = 0;
        }
        return iProcessBytes + this.cipher.doFinal(bArr2, i3 + iProcessBytes);
    }

    private boolean isAEADModeName(String str) {
        return "CCM".equals(str) || "EAX".equals(str) || CodePackage.GCM.equals(str) || "OCB".equals(str);
    }

    private static class BufferedGenericBlockCipher implements GenericBlockCipher {
        private BufferedBlockCipher cipher;

        BufferedGenericBlockCipher(BufferedBlockCipher bufferedBlockCipher) {
            this.cipher = bufferedBlockCipher;
        }

        BufferedGenericBlockCipher(BlockCipher blockCipher) {
            this.cipher = new PaddedBufferedBlockCipher(blockCipher);
        }

        BufferedGenericBlockCipher(BlockCipher blockCipher, BlockCipherPadding blockCipherPadding) {
            this.cipher = new PaddedBufferedBlockCipher(blockCipher, blockCipherPadding);
        }

        @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher.GenericBlockCipher
        public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
            this.cipher.init(z, cipherParameters);
        }

        @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher.GenericBlockCipher
        public boolean wrapOnNoPadding() {
            return !(this.cipher instanceof CTSBlockCipher);
        }

        @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher.GenericBlockCipher
        public String getAlgorithmName() {
            return this.cipher.getUnderlyingCipher().getAlgorithmName();
        }

        @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher.GenericBlockCipher
        public BlockCipher getUnderlyingCipher() {
            return this.cipher.getUnderlyingCipher();
        }

        @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher.GenericBlockCipher
        public int getOutputSize(int i) {
            return this.cipher.getOutputSize(i);
        }

        @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher.GenericBlockCipher
        public int getUpdateOutputSize(int i) {
            return this.cipher.getUpdateOutputSize(i);
        }

        @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher.GenericBlockCipher
        public void updateAAD(byte[] bArr, int i, int i2) {
            throw new UnsupportedOperationException("AAD is not supported in the current mode.");
        }

        @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher.GenericBlockCipher
        public int processByte(byte b, byte[] bArr, int i) throws DataLengthException {
            return this.cipher.processByte(b, bArr, i);
        }

        @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher.GenericBlockCipher
        public int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws DataLengthException {
            return this.cipher.processBytes(bArr, i, i2, bArr2, i3);
        }

        @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher.GenericBlockCipher
        public int doFinal(byte[] bArr, int i) throws IllegalStateException, BadPaddingException {
            try {
                return this.cipher.doFinal(bArr, i);
            } catch (InvalidCipherTextException e) {
                throw new BadPaddingException(e.getMessage());
            }
        }
    }

    private static class AEADGenericBlockCipher implements GenericBlockCipher {
        private static final Constructor aeadBadTagConstructor;
        private AEADBlockCipher cipher;

        @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher.GenericBlockCipher
        public boolean wrapOnNoPadding() {
            return false;
        }

        static {
            Class clsLoadClass = ClassUtil.loadClass(BaseBlockCipher.class, "javax.crypto.AEADBadTagException");
            if (clsLoadClass != null) {
                aeadBadTagConstructor = findExceptionConstructor(clsLoadClass);
            } else {
                aeadBadTagConstructor = null;
            }
        }

        private static Constructor findExceptionConstructor(Class cls) {
            try {
                return cls.getConstructor(String.class);
            } catch (Exception unused) {
                return null;
            }
        }

        AEADGenericBlockCipher(AEADBlockCipher aEADBlockCipher) {
            this.cipher = aEADBlockCipher;
        }

        @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher.GenericBlockCipher
        public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
            this.cipher.init(z, cipherParameters);
        }

        @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher.GenericBlockCipher
        public String getAlgorithmName() {
            return this.cipher.getUnderlyingCipher().getAlgorithmName();
        }

        @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher.GenericBlockCipher
        public BlockCipher getUnderlyingCipher() {
            return this.cipher.getUnderlyingCipher();
        }

        @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher.GenericBlockCipher
        public int getOutputSize(int i) {
            return this.cipher.getOutputSize(i);
        }

        @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher.GenericBlockCipher
        public int getUpdateOutputSize(int i) {
            return this.cipher.getUpdateOutputSize(i);
        }

        @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher.GenericBlockCipher
        public void updateAAD(byte[] bArr, int i, int i2) {
            this.cipher.processAADBytes(bArr, i, i2);
        }

        @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher.GenericBlockCipher
        public int processByte(byte b, byte[] bArr, int i) throws DataLengthException {
            return this.cipher.processByte(b, bArr, i);
        }

        @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher.GenericBlockCipher
        public int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws DataLengthException {
            return this.cipher.processBytes(bArr, i, i2, bArr2, i3);
        }

        @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher.GenericBlockCipher
        public int doFinal(byte[] bArr, int i) throws IllegalStateException, BadPaddingException {
            BadPaddingException badPaddingException;
            try {
                return this.cipher.doFinal(bArr, i);
            } catch (InvalidCipherTextException e) {
                Constructor constructor = aeadBadTagConstructor;
                if (constructor != null) {
                    try {
                        badPaddingException = (BadPaddingException) constructor.newInstance(e.getMessage());
                    } catch (Exception unused) {
                        badPaddingException = null;
                    }
                    if (badPaddingException != null) {
                        throw badPaddingException;
                    }
                }
                throw new BadPaddingException(e.getMessage());
            }
        }
    }

    private static class InvalidKeyOrParametersException extends InvalidKeyException {
        private final Throwable cause;

        @Override // java.lang.Throwable
        public Throwable getCause() {
            return this.cause;
        }

        InvalidKeyOrParametersException(String str, Throwable th) {
            super(str);
            this.cause = th;
        }
    }
}
