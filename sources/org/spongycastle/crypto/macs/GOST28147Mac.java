package org.spongycastle.crypto.macs;

import androidx.core.view.ViewCompat;
import com.google.common.base.Ascii;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.Mac;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.crypto.params.ParametersWithSBox;

/* loaded from: classes7.dex */
public class GOST28147Mac implements Mac {
    private int blockSize = 8;
    private int macSize = 4;
    private boolean firstStep = true;
    private int[] workingKey = null;
    private byte[] macIV = null;
    private byte[] S = {9, 6, 3, 2, 8, 11, 1, 7, 10, 4, 14, 15, 12, 0, 13, 5, 3, 7, 14, 9, 8, 10, 15, 0, 5, 2, 6, 12, 11, 4, 13, 1, 14, 4, 6, 2, 11, 3, 13, 8, 12, 15, 5, 10, 0, 7, 1, 9, 14, 7, 10, 12, 13, 1, 3, 9, 0, 2, 11, 4, 15, 8, 5, 6, 11, 5, 1, 9, 8, 13, 15, 0, 14, 4, 2, 3, 12, 7, 10, 6, 3, 10, 13, 12, 1, 2, 0, 11, 7, 5, 9, 4, 8, 15, 14, 6, 1, 13, 2, 9, 7, 10, 6, 0, 8, 12, 4, 5, 15, 3, 11, 14, 11, 10, 15, 5, 0, 12, 14, 8, 6, 2, 3, 9, 1, 7, 13, 4};
    private byte[] mac = new byte[8];
    private byte[] buf = new byte[8];
    private int bufOff = 0;

    @Override // org.spongycastle.crypto.Mac
    public String getAlgorithmName() {
        return "GOST28147Mac";
    }

    @Override // org.spongycastle.crypto.Mac
    public int getMacSize() {
        return this.macSize;
    }

    private int[] generateWorkingKey(byte[] bArr) {
        if (bArr.length != 32) {
            throw new IllegalArgumentException("Key length invalid. Key needs to be 32 byte - 256 bit!!!");
        }
        int[] iArr = new int[8];
        for (int i = 0; i != 8; i++) {
            iArr[i] = bytesToint(bArr, i * 4);
        }
        return iArr;
    }

    @Override // org.spongycastle.crypto.Mac
    public void init(CipherParameters cipherParameters) throws IllegalArgumentException {
        reset();
        this.buf = new byte[this.blockSize];
        this.macIV = null;
        if (cipherParameters instanceof ParametersWithSBox) {
            ParametersWithSBox parametersWithSBox = (ParametersWithSBox) cipherParameters;
            System.arraycopy(parametersWithSBox.getSBox(), 0, this.S, 0, parametersWithSBox.getSBox().length);
            if (parametersWithSBox.getParameters() != null) {
                this.workingKey = generateWorkingKey(((KeyParameter) parametersWithSBox.getParameters()).getKey());
                return;
            }
            return;
        }
        if (cipherParameters instanceof KeyParameter) {
            this.workingKey = generateWorkingKey(((KeyParameter) cipherParameters).getKey());
            return;
        }
        if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            this.workingKey = generateWorkingKey(((KeyParameter) parametersWithIV.getParameters()).getKey());
            byte[] iv = parametersWithIV.getIV();
            byte[] bArr = this.mac;
            System.arraycopy(iv, 0, bArr, 0, bArr.length);
            this.macIV = parametersWithIV.getIV();
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to GOST28147 init - " + cipherParameters.getClass().getName());
    }

    private int gost28147_mainStep(int i, int i2) {
        int i3 = i2 + i;
        byte[] bArr = this.S;
        int i4 = bArr[i3 & 15] + (bArr[((i3 >> 4) & 15) + 16] << 4) + (bArr[((i3 >> 8) & 15) + 32] << 8) + (bArr[((i3 >> 12) & 15) + 48] << 12) + (bArr[((i3 >> 16) & 15) + 64] << 16) + (bArr[((i3 >> 20) & 15) + 80] << Ascii.DC4) + (bArr[((i3 >> 24) & 15) + 96] << Ascii.CAN) + (bArr[((i3 >> 28) & 15) + 112] << 28);
        return (i4 << 11) | (i4 >>> 21);
    }

    private void gost28147MacFunc(int[] iArr, byte[] bArr, int i, byte[] bArr2, int i2) {
        int iBytesToint = bytesToint(bArr, i);
        int iBytesToint2 = bytesToint(bArr, i + 4);
        for (int i3 = 0; i3 < 2; i3++) {
            int i4 = 0;
            while (i4 < 8) {
                int iGost28147_mainStep = iBytesToint2 ^ gost28147_mainStep(iBytesToint, iArr[i4]);
                i4++;
                int i5 = iBytesToint;
                iBytesToint = iGost28147_mainStep;
                iBytesToint2 = i5;
            }
        }
        intTobytes(iBytesToint, bArr2, i2);
        intTobytes(iBytesToint2, bArr2, i2 + 4);
    }

    private int bytesToint(byte[] bArr, int i) {
        return ((bArr[i + 3] << Ascii.CAN) & ViewCompat.MEASURED_STATE_MASK) + ((bArr[i + 2] << 16) & 16711680) + ((bArr[i + 1] << 8) & 65280) + (bArr[i] & 255);
    }

    private void intTobytes(int i, byte[] bArr, int i2) {
        bArr[i2 + 3] = (byte) (i >>> 24);
        bArr[i2 + 2] = (byte) (i >>> 16);
        bArr[i2 + 1] = (byte) (i >>> 8);
        bArr[i2] = (byte) i;
    }

    private byte[] CM5func(byte[] bArr, int i, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length - i];
        System.arraycopy(bArr, i, bArr3, 0, bArr2.length);
        for (int i2 = 0; i2 != bArr2.length; i2++) {
            bArr3[i2] = (byte) (bArr3[i2] ^ bArr2[i2]);
        }
        return bArr3;
    }

    @Override // org.spongycastle.crypto.Mac
    public void update(byte b) throws IllegalStateException {
        int i = this.bufOff;
        byte[] bArr = this.buf;
        if (i == bArr.length) {
            byte[] bArrCM5func = new byte[bArr.length];
            System.arraycopy(bArr, 0, bArrCM5func, 0, this.mac.length);
            if (this.firstStep) {
                this.firstStep = false;
                byte[] bArr2 = this.macIV;
                if (bArr2 != null) {
                    bArrCM5func = CM5func(this.buf, 0, bArr2);
                }
            } else {
                bArrCM5func = CM5func(this.buf, 0, this.mac);
            }
            gost28147MacFunc(this.workingKey, bArrCM5func, 0, this.mac, 0);
            this.bufOff = 0;
        }
        byte[] bArr3 = this.buf;
        int i2 = this.bufOff;
        this.bufOff = i2 + 1;
        bArr3[i2] = b;
    }

    @Override // org.spongycastle.crypto.Mac
    public void update(byte[] bArr, int i, int i2) throws IllegalStateException, DataLengthException {
        if (i2 < 0) {
            throw new IllegalArgumentException("Can't have a negative input length!");
        }
        int i3 = this.blockSize;
        int i4 = this.bufOff;
        int i5 = i3 - i4;
        if (i2 > i5) {
            System.arraycopy(bArr, i, this.buf, i4, i5);
            byte[] bArr2 = this.buf;
            byte[] bArrCM5func = new byte[bArr2.length];
            System.arraycopy(bArr2, 0, bArrCM5func, 0, this.mac.length);
            if (this.firstStep) {
                this.firstStep = false;
                byte[] bArr3 = this.macIV;
                if (bArr3 != null) {
                    bArrCM5func = CM5func(this.buf, 0, bArr3);
                }
            } else {
                bArrCM5func = CM5func(this.buf, 0, this.mac);
            }
            gost28147MacFunc(this.workingKey, bArrCM5func, 0, this.mac, 0);
            this.bufOff = 0;
            while (true) {
                i2 -= i5;
                i += i5;
                if (i2 <= this.blockSize) {
                    break;
                }
                gost28147MacFunc(this.workingKey, CM5func(bArr, i, this.mac), 0, this.mac, 0);
                i5 = this.blockSize;
            }
        }
        System.arraycopy(bArr, i, this.buf, this.bufOff, i2);
        this.bufOff += i2;
    }

    @Override // org.spongycastle.crypto.Mac
    public int doFinal(byte[] bArr, int i) throws IllegalStateException, DataLengthException {
        while (true) {
            int i2 = this.bufOff;
            if (i2 >= this.blockSize) {
                break;
            }
            this.buf[i2] = 0;
            this.bufOff = i2 + 1;
        }
        byte[] bArr2 = this.buf;
        byte[] bArrCM5func = new byte[bArr2.length];
        System.arraycopy(bArr2, 0, bArrCM5func, 0, this.mac.length);
        if (this.firstStep) {
            this.firstStep = false;
        } else {
            bArrCM5func = CM5func(this.buf, 0, this.mac);
        }
        gost28147MacFunc(this.workingKey, bArrCM5func, 0, this.mac, 0);
        byte[] bArr3 = this.mac;
        int length = bArr3.length / 2;
        int i3 = this.macSize;
        System.arraycopy(bArr3, length - i3, bArr, i, i3);
        reset();
        return this.macSize;
    }

    @Override // org.spongycastle.crypto.Mac
    public void reset() {
        int i = 0;
        while (true) {
            byte[] bArr = this.buf;
            if (i >= bArr.length) {
                this.bufOff = 0;
                this.firstStep = true;
                return;
            } else {
                bArr[i] = 0;
                i++;
            }
        }
    }
}
