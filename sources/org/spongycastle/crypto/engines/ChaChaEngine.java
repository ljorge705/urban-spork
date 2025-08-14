package org.spongycastle.crypto.engines;

import org.spongycastle.util.Pack;

/* loaded from: classes4.dex */
public class ChaChaEngine extends Salsa20Engine {
    public ChaChaEngine() {
    }

    public ChaChaEngine(int i) {
        super(i);
    }

    @Override // org.spongycastle.crypto.engines.Salsa20Engine, org.spongycastle.crypto.StreamCipher
    public String getAlgorithmName() {
        return "ChaCha" + this.rounds;
    }

    @Override // org.spongycastle.crypto.engines.Salsa20Engine
    protected void advanceCounter(long j) {
        int i = (int) (j >>> 32);
        int i2 = (int) j;
        if (i > 0) {
            int[] iArr = this.engineState;
            iArr[13] = iArr[13] + i;
        }
        int i3 = this.engineState[12];
        int[] iArr2 = this.engineState;
        iArr2[12] = iArr2[12] + i2;
        if (i3 == 0 || this.engineState[12] >= i3) {
            return;
        }
        int[] iArr3 = this.engineState;
        iArr3[13] = iArr3[13] + 1;
    }

    @Override // org.spongycastle.crypto.engines.Salsa20Engine
    protected void advanceCounter() {
        int[] iArr = this.engineState;
        int i = iArr[12] + 1;
        iArr[12] = i;
        if (i == 0) {
            int[] iArr2 = this.engineState;
            iArr2[13] = iArr2[13] + 1;
        }
    }

    @Override // org.spongycastle.crypto.engines.Salsa20Engine
    protected void retreatCounter(long j) {
        int i = (int) (j >>> 32);
        int i2 = (int) j;
        if (i != 0) {
            if ((this.engineState[13] & 4294967295L) >= (i & 4294967295L)) {
                int[] iArr = this.engineState;
                iArr[13] = iArr[13] - i;
            } else {
                throw new IllegalStateException("attempt to reduce counter past zero.");
            }
        }
        if ((this.engineState[12] & 4294967295L) >= (4294967295L & i2)) {
            int[] iArr2 = this.engineState;
            iArr2[12] = iArr2[12] - i2;
        } else {
            if (this.engineState[13] != 0) {
                this.engineState[13] = r11[13] - 1;
                int[] iArr3 = this.engineState;
                iArr3[12] = iArr3[12] - i2;
                return;
            }
            throw new IllegalStateException("attempt to reduce counter past zero.");
        }
    }

    @Override // org.spongycastle.crypto.engines.Salsa20Engine
    protected void retreatCounter() {
        if (this.engineState[12] == 0 && this.engineState[13] == 0) {
            throw new IllegalStateException("attempt to reduce counter past zero.");
        }
        int[] iArr = this.engineState;
        int i = iArr[12] - 1;
        iArr[12] = i;
        if (i == -1) {
            this.engineState[13] = r0[13] - 1;
        }
    }

    @Override // org.spongycastle.crypto.engines.Salsa20Engine
    protected long getCounter() {
        return (this.engineState[13] << 32) | (this.engineState[12] & 4294967295L);
    }

    @Override // org.spongycastle.crypto.engines.Salsa20Engine
    protected void resetCounter() {
        int[] iArr = this.engineState;
        this.engineState[13] = 0;
        iArr[12] = 0;
    }

    @Override // org.spongycastle.crypto.engines.Salsa20Engine
    protected void setKey(byte[] bArr, byte[] bArr2) {
        if (bArr != null) {
            if (bArr.length != 16 && bArr.length != 32) {
                throw new IllegalArgumentException(getAlgorithmName() + " requires 128 bit or 256 bit key");
            }
            packTauOrSigma(bArr.length, this.engineState, 0);
            Pack.littleEndianToInt(bArr, 0, this.engineState, 4, 4);
            Pack.littleEndianToInt(bArr, bArr.length - 16, this.engineState, 8, 4);
        }
        Pack.littleEndianToInt(bArr2, 0, this.engineState, 14, 2);
    }

    @Override // org.spongycastle.crypto.engines.Salsa20Engine
    protected void generateKeyStream(byte[] bArr) {
        chachaCore(this.rounds, this.engineState, this.x);
        Pack.intToLittleEndian(this.x, bArr, 0);
    }

    public static void chachaCore(int i, int[] iArr, int[] iArr2) {
        int i2 = 16;
        if (iArr.length != 16) {
            throw new IllegalArgumentException();
        }
        if (iArr2.length != 16) {
            throw new IllegalArgumentException();
        }
        if (i % 2 != 0) {
            throw new IllegalArgumentException("Number of rounds must be even");
        }
        char c = 0;
        int i3 = iArr[0];
        int i4 = iArr[1];
        int i5 = iArr[2];
        int i6 = iArr[3];
        int i7 = iArr[4];
        int i8 = iArr[5];
        int i9 = iArr[6];
        int i10 = 7;
        int i11 = iArr[7];
        int i12 = 8;
        int i13 = iArr[8];
        int i14 = iArr[9];
        int i15 = iArr[10];
        int i16 = iArr[11];
        int i17 = 12;
        int i18 = iArr[12];
        int i19 = iArr[13];
        int i20 = iArr[14];
        int iRotl = iArr[15];
        int iRotl2 = i20;
        int iRotl3 = i19;
        int iRotl4 = i18;
        int i21 = i16;
        int i22 = i15;
        int i23 = i14;
        int i24 = i13;
        int iRotl5 = i11;
        int iRotl6 = i9;
        int iRotl7 = i8;
        int iRotl8 = i7;
        int i25 = i6;
        int i26 = i5;
        int i27 = i4;
        int i28 = i3;
        int i29 = i;
        while (i29 > 0) {
            int i30 = i28 + iRotl8;
            int iRotl9 = rotl(iRotl4 ^ i30, i2);
            int i31 = i24 + iRotl9;
            int iRotl10 = rotl(iRotl8 ^ i31, i17);
            int i32 = i30 + iRotl10;
            int iRotl11 = rotl(iRotl9 ^ i32, i12);
            int i33 = i31 + iRotl11;
            int iRotl12 = rotl(iRotl10 ^ i33, i10);
            int i34 = i27 + iRotl7;
            int iRotl13 = rotl(iRotl3 ^ i34, i2);
            int i35 = i23 + iRotl13;
            int iRotl14 = rotl(iRotl7 ^ i35, i17);
            int i36 = i34 + iRotl14;
            int iRotl15 = rotl(iRotl13 ^ i36, i12);
            int i37 = i35 + iRotl15;
            int iRotl16 = rotl(iRotl14 ^ i37, i10);
            int i38 = i26 + iRotl6;
            int iRotl17 = rotl(iRotl2 ^ i38, i2);
            int i39 = i22 + iRotl17;
            int iRotl18 = rotl(iRotl6 ^ i39, i17);
            int i40 = i38 + iRotl18;
            int iRotl19 = rotl(iRotl17 ^ i40, i12);
            int i41 = i39 + iRotl19;
            int iRotl20 = rotl(iRotl18 ^ i41, i10);
            int i42 = i25 + iRotl5;
            int iRotl21 = rotl(iRotl ^ i42, 16);
            int i43 = i21 + iRotl21;
            int iRotl22 = rotl(iRotl5 ^ i43, i17);
            int i44 = i42 + iRotl22;
            int iRotl23 = rotl(iRotl21 ^ i44, 8);
            int i45 = i43 + iRotl23;
            int iRotl24 = rotl(iRotl22 ^ i45, 7);
            int i46 = i32 + iRotl16;
            int iRotl25 = rotl(iRotl23 ^ i46, 16);
            int i47 = i41 + iRotl25;
            int iRotl26 = rotl(iRotl16 ^ i47, 12);
            i28 = i46 + iRotl26;
            iRotl = rotl(iRotl25 ^ i28, 8);
            i22 = i47 + iRotl;
            iRotl7 = rotl(iRotl26 ^ i22, 7);
            int i48 = i36 + iRotl20;
            int iRotl27 = rotl(iRotl11 ^ i48, 16);
            int i49 = i45 + iRotl27;
            int iRotl28 = rotl(iRotl20 ^ i49, 12);
            i27 = i48 + iRotl28;
            iRotl4 = rotl(iRotl27 ^ i27, 8);
            i21 = i49 + iRotl4;
            iRotl6 = rotl(iRotl28 ^ i21, 7);
            int i50 = i40 + iRotl24;
            int iRotl29 = rotl(iRotl15 ^ i50, 16);
            int i51 = i33 + iRotl29;
            int iRotl30 = rotl(iRotl24 ^ i51, 12);
            i26 = i50 + iRotl30;
            iRotl3 = rotl(iRotl29 ^ i26, 8);
            i24 = i51 + iRotl3;
            iRotl5 = rotl(iRotl30 ^ i24, 7);
            int i52 = i44 + iRotl12;
            i2 = 16;
            int iRotl31 = rotl(iRotl19 ^ i52, 16);
            int i53 = i37 + iRotl31;
            int iRotl32 = rotl(iRotl12 ^ i53, 12);
            i25 = i52 + iRotl32;
            iRotl2 = rotl(iRotl31 ^ i25, 8);
            i23 = i53 + iRotl2;
            iRotl8 = rotl(iRotl32 ^ i23, 7);
            i29 -= 2;
            c = 0;
            i17 = 12;
            i12 = 8;
            i10 = 7;
        }
        iArr2[c] = i28 + iArr[c];
        iArr2[1] = i27 + iArr[1];
        iArr2[2] = i26 + iArr[2];
        iArr2[3] = i25 + iArr[3];
        iArr2[4] = iRotl8 + iArr[4];
        iArr2[5] = iRotl7 + iArr[5];
        iArr2[6] = iRotl6 + iArr[6];
        iArr2[7] = iRotl5 + iArr[7];
        iArr2[8] = i24 + iArr[8];
        iArr2[9] = i23 + iArr[9];
        iArr2[10] = i22 + iArr[10];
        iArr2[11] = i21 + iArr[11];
        iArr2[12] = iRotl4 + iArr[12];
        iArr2[13] = iRotl3 + iArr[13];
        iArr2[14] = iRotl2 + iArr[14];
        iArr2[15] = iRotl + iArr[15];
    }
}
