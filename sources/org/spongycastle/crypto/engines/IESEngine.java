package org.spongycastle.crypto.engines;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.spongycastle.crypto.BasicAgreement;
import org.spongycastle.crypto.BufferedBlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.DerivationFunction;
import org.spongycastle.crypto.EphemeralKeyPair;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.KeyParser;
import org.spongycastle.crypto.Mac;
import org.spongycastle.crypto.generators.EphemeralKeyPairGenerator;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.IESParameters;
import org.spongycastle.crypto.params.IESWithCipherParameters;
import org.spongycastle.crypto.params.KDFParameters;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.BigIntegers;
import org.spongycastle.util.Pack;

/* loaded from: classes4.dex */
public class IESEngine {
    private byte[] IV;
    byte[] V;
    BasicAgreement agree;
    BufferedBlockCipher cipher;
    boolean forEncryption;
    DerivationFunction kdf;
    private EphemeralKeyPairGenerator keyPairGenerator;
    private KeyParser keyParser;
    Mac mac;
    byte[] macBuf;
    IESParameters param;
    CipherParameters privParam;
    CipherParameters pubParam;

    public BufferedBlockCipher getCipher() {
        return this.cipher;
    }

    public Mac getMac() {
        return this.mac;
    }

    public IESEngine(BasicAgreement basicAgreement, DerivationFunction derivationFunction, Mac mac) {
        this.agree = basicAgreement;
        this.kdf = derivationFunction;
        this.mac = mac;
        this.macBuf = new byte[mac.getMacSize()];
        this.cipher = null;
    }

    public IESEngine(BasicAgreement basicAgreement, DerivationFunction derivationFunction, Mac mac, BufferedBlockCipher bufferedBlockCipher) {
        this.agree = basicAgreement;
        this.kdf = derivationFunction;
        this.mac = mac;
        this.macBuf = new byte[mac.getMacSize()];
        this.cipher = bufferedBlockCipher;
    }

    public void init(boolean z, CipherParameters cipherParameters, CipherParameters cipherParameters2, CipherParameters cipherParameters3) {
        this.forEncryption = z;
        this.privParam = cipherParameters;
        this.pubParam = cipherParameters2;
        this.V = new byte[0];
        extractParams(cipherParameters3);
    }

    public void init(AsymmetricKeyParameter asymmetricKeyParameter, CipherParameters cipherParameters, EphemeralKeyPairGenerator ephemeralKeyPairGenerator) {
        this.forEncryption = true;
        this.pubParam = asymmetricKeyParameter;
        this.keyPairGenerator = ephemeralKeyPairGenerator;
        extractParams(cipherParameters);
    }

    public void init(AsymmetricKeyParameter asymmetricKeyParameter, CipherParameters cipherParameters, KeyParser keyParser) {
        this.forEncryption = false;
        this.privParam = asymmetricKeyParameter;
        this.keyParser = keyParser;
        extractParams(cipherParameters);
    }

    private void extractParams(CipherParameters cipherParameters) {
        if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            this.IV = parametersWithIV.getIV();
            this.param = (IESParameters) parametersWithIV.getParameters();
        } else {
            this.IV = null;
            this.param = (IESParameters) cipherParameters;
        }
    }

    private byte[] encryptBlock(byte[] bArr, int i, int i2) throws IllegalStateException, InvalidCipherTextException, DataLengthException, IllegalArgumentException {
        byte[] bArr2;
        byte[] bArr3;
        if (this.cipher == null) {
            byte[] bArr4 = new byte[i2];
            int macKeySize = this.param.getMacKeySize() / 8;
            bArr3 = new byte[macKeySize];
            int i3 = i2 + macKeySize;
            byte[] bArr5 = new byte[i3];
            this.kdf.generateBytes(bArr5, 0, i3);
            if (this.V.length != 0) {
                System.arraycopy(bArr5, 0, bArr3, 0, macKeySize);
                System.arraycopy(bArr5, macKeySize, bArr4, 0, i2);
            } else {
                System.arraycopy(bArr5, 0, bArr4, 0, i2);
                System.arraycopy(bArr5, i2, bArr3, 0, macKeySize);
            }
            bArr2 = new byte[i2];
            for (int i4 = 0; i4 != i2; i4++) {
                bArr2[i4] = (byte) (bArr[i + i4] ^ bArr4[i4]);
            }
        } else {
            int cipherKeySize = ((IESWithCipherParameters) this.param).getCipherKeySize() / 8;
            byte[] bArr6 = new byte[cipherKeySize];
            int macKeySize2 = this.param.getMacKeySize() / 8;
            byte[] bArr7 = new byte[macKeySize2];
            int i5 = cipherKeySize + macKeySize2;
            byte[] bArr8 = new byte[i5];
            this.kdf.generateBytes(bArr8, 0, i5);
            System.arraycopy(bArr8, 0, bArr6, 0, cipherKeySize);
            System.arraycopy(bArr8, cipherKeySize, bArr7, 0, macKeySize2);
            if (this.IV != null) {
                this.cipher.init(true, new ParametersWithIV(new KeyParameter(bArr6), this.IV));
            } else {
                this.cipher.init(true, new KeyParameter(bArr6));
            }
            bArr2 = new byte[this.cipher.getOutputSize(i2)];
            int iProcessBytes = this.cipher.processBytes(bArr, i, i2, bArr2, 0);
            i2 = iProcessBytes + this.cipher.doFinal(bArr2, iProcessBytes);
            bArr3 = bArr7;
        }
        byte[] encodingV = this.param.getEncodingV();
        byte[] lengthTag = this.V.length != 0 ? getLengthTag(encodingV) : null;
        int macSize = this.mac.getMacSize();
        byte[] bArr9 = new byte[macSize];
        this.mac.init(new KeyParameter(bArr3));
        this.mac.update(bArr2, 0, bArr2.length);
        if (encodingV != null) {
            this.mac.update(encodingV, 0, encodingV.length);
        }
        if (this.V.length != 0) {
            this.mac.update(lengthTag, 0, lengthTag.length);
        }
        this.mac.doFinal(bArr9, 0);
        byte[] bArr10 = this.V;
        byte[] bArr11 = new byte[bArr10.length + i2 + macSize];
        System.arraycopy(bArr10, 0, bArr11, 0, bArr10.length);
        System.arraycopy(bArr2, 0, bArr11, this.V.length, i2);
        System.arraycopy(bArr9, 0, bArr11, this.V.length + i2, macSize);
        return bArr11;
    }

    private byte[] decryptBlock(byte[] bArr, int i, int i2) throws IllegalStateException, InvalidCipherTextException, DataLengthException, IllegalArgumentException {
        byte[] bArr2;
        byte[] bArr3;
        int iProcessBytes;
        if (i2 < this.V.length + this.mac.getMacSize()) {
            throw new InvalidCipherTextException("Length of input must be greater than the MAC and V combined");
        }
        if (this.cipher == null) {
            int length = (i2 - this.V.length) - this.mac.getMacSize();
            byte[] bArr4 = new byte[length];
            int macKeySize = this.param.getMacKeySize() / 8;
            bArr2 = new byte[macKeySize];
            int i3 = length + macKeySize;
            byte[] bArr5 = new byte[i3];
            this.kdf.generateBytes(bArr5, 0, i3);
            if (this.V.length != 0) {
                System.arraycopy(bArr5, 0, bArr2, 0, macKeySize);
                System.arraycopy(bArr5, macKeySize, bArr4, 0, length);
            } else {
                System.arraycopy(bArr5, 0, bArr4, 0, length);
                System.arraycopy(bArr5, length, bArr2, 0, macKeySize);
            }
            bArr3 = new byte[length];
            for (int i4 = 0; i4 != length; i4++) {
                bArr3[i4] = (byte) (bArr[(this.V.length + i) + i4] ^ bArr4[i4]);
            }
            iProcessBytes = 0;
        } else {
            int cipherKeySize = ((IESWithCipherParameters) this.param).getCipherKeySize() / 8;
            byte[] bArr6 = new byte[cipherKeySize];
            int macKeySize2 = this.param.getMacKeySize() / 8;
            bArr2 = new byte[macKeySize2];
            int i5 = cipherKeySize + macKeySize2;
            byte[] bArr7 = new byte[i5];
            this.kdf.generateBytes(bArr7, 0, i5);
            System.arraycopy(bArr7, 0, bArr6, 0, cipherKeySize);
            System.arraycopy(bArr7, cipherKeySize, bArr2, 0, macKeySize2);
            CipherParameters keyParameter = new KeyParameter(bArr6);
            if (this.IV != null) {
                keyParameter = new ParametersWithIV(keyParameter, this.IV);
            }
            this.cipher.init(false, keyParameter);
            bArr3 = new byte[this.cipher.getOutputSize((i2 - this.V.length) - this.mac.getMacSize())];
            BufferedBlockCipher bufferedBlockCipher = this.cipher;
            byte[] bArr8 = this.V;
            iProcessBytes = bufferedBlockCipher.processBytes(bArr, i + bArr8.length, (i2 - bArr8.length) - this.mac.getMacSize(), bArr3, 0);
        }
        byte[] encodingV = this.param.getEncodingV();
        byte[] lengthTag = this.V.length != 0 ? getLengthTag(encodingV) : null;
        int i6 = i + i2;
        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, i6 - this.mac.getMacSize(), i6);
        int length2 = bArrCopyOfRange.length;
        byte[] bArr9 = new byte[length2];
        this.mac.init(new KeyParameter(bArr2));
        Mac mac = this.mac;
        byte[] bArr10 = this.V;
        mac.update(bArr, i + bArr10.length, (i2 - bArr10.length) - length2);
        if (encodingV != null) {
            this.mac.update(encodingV, 0, encodingV.length);
        }
        if (this.V.length != 0) {
            this.mac.update(lengthTag, 0, lengthTag.length);
        }
        this.mac.doFinal(bArr9, 0);
        if (!Arrays.constantTimeAreEqual(bArrCopyOfRange, bArr9)) {
            throw new InvalidCipherTextException("invalid MAC");
        }
        BufferedBlockCipher bufferedBlockCipher2 = this.cipher;
        return bufferedBlockCipher2 == null ? bArr3 : Arrays.copyOfRange(bArr3, 0, iProcessBytes + bufferedBlockCipher2.doFinal(bArr3, iProcessBytes));
    }

    public byte[] processBlock(byte[] bArr, int i, int i2) throws InvalidCipherTextException {
        byte[] bArrDecryptBlock;
        if (this.forEncryption) {
            EphemeralKeyPairGenerator ephemeralKeyPairGenerator = this.keyPairGenerator;
            if (ephemeralKeyPairGenerator != null) {
                EphemeralKeyPair ephemeralKeyPairGenerate = ephemeralKeyPairGenerator.generate();
                this.privParam = ephemeralKeyPairGenerate.getKeyPair().getPrivate();
                this.V = ephemeralKeyPairGenerate.getEncodedPublicKey();
            }
        } else if (this.keyParser != null) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr, i, i2);
            try {
                this.pubParam = this.keyParser.readKey(byteArrayInputStream);
                this.V = Arrays.copyOfRange(bArr, i, (i2 - byteArrayInputStream.available()) + i);
            } catch (IOException e) {
                throw new InvalidCipherTextException("unable to recover ephemeral public key: " + e.getMessage(), e);
            } catch (IllegalArgumentException e2) {
                throw new InvalidCipherTextException("unable to recover ephemeral public key: " + e2.getMessage(), e2);
            }
        }
        this.agree.init(this.privParam);
        byte[] bArrAsUnsignedByteArray = BigIntegers.asUnsignedByteArray(this.agree.getFieldSize(), this.agree.calculateAgreement(this.pubParam));
        byte[] bArr2 = this.V;
        if (bArr2.length != 0) {
            byte[] bArrConcatenate = Arrays.concatenate(bArr2, bArrAsUnsignedByteArray);
            Arrays.fill(bArrAsUnsignedByteArray, (byte) 0);
            bArrAsUnsignedByteArray = bArrConcatenate;
        }
        try {
            this.kdf.init(new KDFParameters(bArrAsUnsignedByteArray, this.param.getDerivationV()));
            if (this.forEncryption) {
                bArrDecryptBlock = encryptBlock(bArr, i, i2);
            } else {
                bArrDecryptBlock = decryptBlock(bArr, i, i2);
            }
            return bArrDecryptBlock;
        } finally {
            Arrays.fill(bArrAsUnsignedByteArray, (byte) 0);
        }
    }

    protected byte[] getLengthTag(byte[] bArr) {
        byte[] bArr2 = new byte[8];
        if (bArr != null) {
            Pack.longToBigEndian(bArr.length * 8, bArr2, 0);
        }
        return bArr2;
    }
}
