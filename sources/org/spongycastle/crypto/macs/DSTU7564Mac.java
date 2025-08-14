package org.spongycastle.crypto.macs;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.Mac;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.digests.DSTU7564Digest;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.util.Pack;

/* loaded from: classes7.dex */
public class DSTU7564Mac implements Mac {
    private static final int BITS_IN_BYTE = 8;
    private DSTU7564Digest engine;
    private long inputLength;
    private int macSize;
    private byte[] paddedKey = null;
    private byte[] invertedKey = null;

    @Override // org.spongycastle.crypto.Mac
    public String getAlgorithmName() {
        return "DSTU7564Mac";
    }

    @Override // org.spongycastle.crypto.Mac
    public int getMacSize() {
        return this.macSize;
    }

    public DSTU7564Mac(int i) {
        this.engine = new DSTU7564Digest(i);
        this.macSize = i / 8;
    }

    @Override // org.spongycastle.crypto.Mac
    public void init(CipherParameters cipherParameters) throws IllegalArgumentException {
        if (cipherParameters instanceof KeyParameter) {
            byte[] key = ((KeyParameter) cipherParameters).getKey();
            this.invertedKey = new byte[key.length];
            this.paddedKey = padKey(key);
            int i = 0;
            while (true) {
                byte[] bArr = this.invertedKey;
                if (i < bArr.length) {
                    bArr[i] = (byte) (~key[i]);
                    i++;
                } else {
                    DSTU7564Digest dSTU7564Digest = this.engine;
                    byte[] bArr2 = this.paddedKey;
                    dSTU7564Digest.update(bArr2, 0, bArr2.length);
                    return;
                }
            }
        } else {
            throw new IllegalArgumentException("Bad parameter passed");
        }
    }

    @Override // org.spongycastle.crypto.Mac
    public void update(byte b) throws IllegalStateException {
        this.engine.update(b);
        this.inputLength++;
    }

    @Override // org.spongycastle.crypto.Mac
    public void update(byte[] bArr, int i, int i2) throws IllegalStateException, DataLengthException {
        if (bArr.length - i < i2) {
            throw new DataLengthException("Input buffer too short");
        }
        if (this.paddedKey == null) {
            throw new IllegalStateException(getAlgorithmName() + " not initialised");
        }
        this.engine.update(bArr, i, i2);
        this.inputLength += i2;
    }

    @Override // org.spongycastle.crypto.Mac
    public int doFinal(byte[] bArr, int i) throws IllegalStateException, DataLengthException {
        if (this.paddedKey == null) {
            throw new IllegalStateException(getAlgorithmName() + " not initialised");
        }
        if (bArr.length - i < this.macSize) {
            throw new OutputLengthException("Output buffer too short");
        }
        pad();
        DSTU7564Digest dSTU7564Digest = this.engine;
        byte[] bArr2 = this.invertedKey;
        dSTU7564Digest.update(bArr2, 0, bArr2.length);
        this.inputLength = 0L;
        return this.engine.doFinal(bArr, i);
    }

    @Override // org.spongycastle.crypto.Mac
    public void reset() {
        this.inputLength = 0L;
        this.engine.reset();
        byte[] bArr = this.paddedKey;
        if (bArr != null) {
            this.engine.update(bArr, 0, bArr.length);
        }
    }

    private void pad() {
        int byteLength = this.engine.getByteLength() - ((int) (this.inputLength % this.engine.getByteLength()));
        if (byteLength < 13) {
            byteLength += this.engine.getByteLength();
        }
        byte[] bArr = new byte[byteLength];
        bArr[0] = Byte.MIN_VALUE;
        Pack.longToLittleEndian(this.inputLength * 8, bArr, byteLength - 12);
        this.engine.update(bArr, 0, byteLength);
    }

    private byte[] padKey(byte[] bArr) {
        int length = (((bArr.length + this.engine.getByteLength()) - 1) / this.engine.getByteLength()) * this.engine.getByteLength();
        if (this.engine.getByteLength() - (bArr.length % this.engine.getByteLength()) < 13) {
            length += this.engine.getByteLength();
        }
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        bArr2[bArr.length] = Byte.MIN_VALUE;
        Pack.intToLittleEndian(bArr.length * 8, bArr2, length - 12);
        return bArr2;
    }
}
