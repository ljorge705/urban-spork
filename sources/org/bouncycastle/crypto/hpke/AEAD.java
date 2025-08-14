package org.bouncycastle.crypto.hpke;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.modes.AEADCipher;
import org.bouncycastle.crypto.modes.ChaCha20Poly1305;
import org.bouncycastle.crypto.modes.GCMBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

/* loaded from: classes4.dex */
public class AEAD {
    private final short aeadId;
    private final byte[] baseNonce;
    private AEADCipher cipher;
    private final byte[] key;
    private long seq = 0;

    public AEAD(short s, byte[] bArr, byte[] bArr2) {
        AEADCipher gCMBlockCipher;
        this.key = bArr;
        this.baseNonce = bArr2;
        this.aeadId = s;
        if (s == 1 || s == 2) {
            gCMBlockCipher = new GCMBlockCipher(new AESEngine());
        } else if (s != 3) {
            return;
        } else {
            gCMBlockCipher = new ChaCha20Poly1305();
        }
        this.cipher = gCMBlockCipher;
    }

    private byte[] ComputeNonce() {
        byte[] bArrLongToBigEndian = Pack.longToBigEndian(this.seq);
        byte[] bArr = this.baseNonce;
        int length = bArr.length;
        byte[] bArrClone = Arrays.clone(bArr);
        for (int i = 0; i < 8; i++) {
            int i2 = (length - 8) + i;
            bArrClone[i2] = (byte) (bArrClone[i2] ^ bArrLongToBigEndian[i]);
        }
        return bArrClone;
    }

    public byte[] open(byte[] bArr, byte[] bArr2) throws InvalidCipherTextException {
        return open(bArr, bArr2, 0, bArr2.length);
    }

    public byte[] open(byte[] bArr, byte[] bArr2, int i, int i2) throws InvalidCipherTextException, IllegalStateException, DataLengthException, IllegalArgumentException {
        if (i < 0 || i > bArr2.length) {
            throw new IndexOutOfBoundsException("Invalid offset");
        }
        if (i + i2 > bArr2.length) {
            throw new IndexOutOfBoundsException("Invalid length");
        }
        short s = this.aeadId;
        if (s != 1 && s != 2 && s != 3) {
            throw new IllegalStateException("Export only mode, cannot be used to seal/open");
        }
        this.cipher.init(false, new ParametersWithIV(new KeyParameter(this.key), ComputeNonce()));
        this.cipher.processAADBytes(bArr, 0, bArr.length);
        byte[] bArr3 = new byte[this.cipher.getOutputSize(i2)];
        this.cipher.doFinal(bArr3, this.cipher.processBytes(bArr2, i, i2, bArr3, 0));
        this.seq++;
        return bArr3;
    }

    public byte[] seal(byte[] bArr, byte[] bArr2) throws InvalidCipherTextException {
        return seal(bArr, bArr2, 0, bArr2.length);
    }

    public byte[] seal(byte[] bArr, byte[] bArr2, int i, int i2) throws InvalidCipherTextException, IllegalStateException, DataLengthException, IllegalArgumentException {
        if (i < 0 || i > bArr2.length) {
            throw new IndexOutOfBoundsException("Invalid offset");
        }
        if (i + i2 > bArr2.length) {
            throw new IndexOutOfBoundsException("Invalid length");
        }
        short s = this.aeadId;
        if (s != 1 && s != 2 && s != 3) {
            throw new IllegalStateException("Export only mode, cannot be used to seal/open");
        }
        this.cipher.init(true, new ParametersWithIV(new KeyParameter(this.key), ComputeNonce()));
        this.cipher.processAADBytes(bArr, 0, bArr.length);
        byte[] bArr3 = new byte[this.cipher.getOutputSize(i2)];
        this.cipher.doFinal(bArr3, this.cipher.processBytes(bArr2, i, i2, bArr3, 0));
        this.seq++;
        return bArr3;
    }
}
