package org.spongycastle.pqc.jcajce.provider.util;

import java.io.ByteArrayOutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.Key;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.ShortBufferException;

/* loaded from: classes7.dex */
public abstract class AsymmetricBlockCipher extends CipherSpiExt {
    protected ByteArrayOutputStream buf = new ByteArrayOutputStream();
    protected int cipherTextSize;
    protected int maxPlainTextSize;
    protected AlgorithmParameterSpec paramSpec;

    @Override // org.spongycastle.pqc.jcajce.provider.util.CipherSpiExt
    public final byte[] getIV() {
        return null;
    }

    @Override // org.spongycastle.pqc.jcajce.provider.util.CipherSpiExt
    public final AlgorithmParameterSpec getParameters() {
        return this.paramSpec;
    }

    protected abstract void initCipherDecrypt(Key key, AlgorithmParameterSpec algorithmParameterSpec) throws InvalidKeyException, InvalidAlgorithmParameterException;

    protected abstract void initCipherEncrypt(Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidKeyException, InvalidAlgorithmParameterException;

    protected abstract byte[] messageDecrypt(byte[] bArr) throws BadPaddingException, IllegalBlockSizeException;

    protected abstract byte[] messageEncrypt(byte[] bArr) throws BadPaddingException, IllegalBlockSizeException;

    @Override // org.spongycastle.pqc.jcajce.provider.util.CipherSpiExt
    protected final void setMode(String str) {
    }

    @Override // org.spongycastle.pqc.jcajce.provider.util.CipherSpiExt
    protected final void setPadding(String str) {
    }

    @Override // org.spongycastle.pqc.jcajce.provider.util.CipherSpiExt
    public final int getBlockSize() {
        return this.opMode == 1 ? this.maxPlainTextSize : this.cipherTextSize;
    }

    @Override // org.spongycastle.pqc.jcajce.provider.util.CipherSpiExt
    public final int getOutputSize(int i) {
        int size = i + this.buf.size();
        int blockSize = getBlockSize();
        if (size > blockSize) {
            return 0;
        }
        return blockSize;
    }

    public final void initEncrypt(Key key) throws InvalidKeyException {
        try {
            initEncrypt(key, null, new SecureRandom());
        } catch (InvalidAlgorithmParameterException unused) {
            throw new InvalidParameterException("This cipher needs algorithm parameters for initialization (cannot be null).");
        }
    }

    public final void initEncrypt(Key key, SecureRandom secureRandom) throws InvalidKeyException {
        try {
            initEncrypt(key, null, secureRandom);
        } catch (InvalidAlgorithmParameterException unused) {
            throw new InvalidParameterException("This cipher needs algorithm parameters for initialization (cannot be null).");
        }
    }

    public final void initEncrypt(Key key, AlgorithmParameterSpec algorithmParameterSpec) throws InvalidKeyException, InvalidAlgorithmParameterException {
        initEncrypt(key, algorithmParameterSpec, new SecureRandom());
    }

    @Override // org.spongycastle.pqc.jcajce.provider.util.CipherSpiExt
    public final void initEncrypt(Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidKeyException, InvalidAlgorithmParameterException {
        this.opMode = 1;
        initCipherEncrypt(key, algorithmParameterSpec, secureRandom);
    }

    public final void initDecrypt(Key key) throws InvalidKeyException {
        try {
            initDecrypt(key, null);
        } catch (InvalidAlgorithmParameterException unused) {
            throw new InvalidParameterException("This cipher needs algorithm parameters for initialization (cannot be null).");
        }
    }

    @Override // org.spongycastle.pqc.jcajce.provider.util.CipherSpiExt
    public final void initDecrypt(Key key, AlgorithmParameterSpec algorithmParameterSpec) throws InvalidKeyException, InvalidAlgorithmParameterException {
        this.opMode = 2;
        initCipherDecrypt(key, algorithmParameterSpec);
    }

    @Override // org.spongycastle.pqc.jcajce.provider.util.CipherSpiExt
    public final byte[] update(byte[] bArr, int i, int i2) {
        if (i2 != 0) {
            this.buf.write(bArr, i, i2);
        }
        return new byte[0];
    }

    @Override // org.spongycastle.pqc.jcajce.provider.util.CipherSpiExt
    public final int update(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        update(bArr, i, i2);
        return 0;
    }

    @Override // org.spongycastle.pqc.jcajce.provider.util.CipherSpiExt
    public final byte[] doFinal(byte[] bArr, int i, int i2) throws BadPaddingException, IllegalBlockSizeException {
        checkLength(i2);
        update(bArr, i, i2);
        byte[] byteArray = this.buf.toByteArray();
        this.buf.reset();
        int i3 = this.opMode;
        if (i3 == 1) {
            return messageEncrypt(byteArray);
        }
        if (i3 != 2) {
            return null;
        }
        return messageDecrypt(byteArray);
    }

    @Override // org.spongycastle.pqc.jcajce.provider.util.CipherSpiExt
    public final int doFinal(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws BadPaddingException, IllegalBlockSizeException, ShortBufferException {
        if (bArr2.length < getOutputSize(i2)) {
            throw new ShortBufferException("Output buffer too short.");
        }
        byte[] bArrDoFinal = doFinal(bArr, i, i2);
        System.arraycopy(bArrDoFinal, 0, bArr2, i3, bArrDoFinal.length);
        return bArrDoFinal.length;
    }

    protected void checkLength(int i) throws IllegalBlockSizeException {
        int size = i + this.buf.size();
        if (this.opMode == 1) {
            if (size > this.maxPlainTextSize) {
                throw new IllegalBlockSizeException("The length of the plaintext (" + size + " bytes) is not supported by the cipher (max. " + this.maxPlainTextSize + " bytes).");
            }
        } else if (this.opMode == 2 && size != this.cipherTextSize) {
            throw new IllegalBlockSizeException("Illegal ciphertext length (expected " + this.cipherTextSize + " bytes, was " + size + " bytes).");
        }
    }
}
