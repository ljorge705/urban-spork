package org.spongycastle.crypto.modes.gcm;

import java.lang.reflect.Array;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Pack;

/* loaded from: classes7.dex */
public class Tables8kGCMMultiplier implements GCMMultiplier {
    private byte[] H;
    private int[][][] M;

    @Override // org.spongycastle.crypto.modes.gcm.GCMMultiplier
    public void init(byte[] bArr) {
        if (this.M == null) {
            this.M = (int[][][]) Array.newInstance((Class<?>) Integer.TYPE, 32, 16, 4);
        } else if (Arrays.areEqual(this.H, bArr)) {
            return;
        }
        this.H = Arrays.clone(bArr);
        GCMUtil.asInts(bArr, this.M[1][8]);
        for (int i = 4; i >= 1; i >>= 1) {
            int[][] iArr = this.M[1];
            GCMUtil.multiplyP(iArr[i + i], iArr[i]);
        }
        int[][][] iArr2 = this.M;
        int i2 = 0;
        GCMUtil.multiplyP(iArr2[1][1], iArr2[0][8]);
        for (int i3 = 4; i3 >= 1; i3 >>= 1) {
            int[][] iArr3 = this.M[0];
            GCMUtil.multiplyP(iArr3[i3 + i3], iArr3[i3]);
        }
        while (true) {
            for (int i4 = 2; i4 < 16; i4 += i4) {
                for (int i5 = 1; i5 < i4; i5++) {
                    int[][] iArr4 = this.M[i2];
                    GCMUtil.xor(iArr4[i4], iArr4[i5], iArr4[i4 + i5]);
                }
            }
            int i6 = i2 + 1;
            if (i6 == 32) {
                return;
            }
            if (i6 > 1) {
                for (int i7 = 8; i7 > 0; i7 >>= 1) {
                    int[][][] iArr5 = this.M;
                    GCMUtil.multiplyP8(iArr5[i2 - 1][i7], iArr5[i6][i7]);
                }
            }
            i2 = i6;
        }
    }

    @Override // org.spongycastle.crypto.modes.gcm.GCMMultiplier
    public void multiplyH(byte[] bArr) {
        int[] iArr = new int[4];
        for (int i = 15; i >= 0; i--) {
            int[][][] iArr2 = this.M;
            int i2 = i + i;
            int[][] iArr3 = iArr2[i2];
            byte b = bArr[i];
            int[] iArr4 = iArr3[b & 15];
            int i3 = iArr[0] ^ iArr4[0];
            iArr[0] = i3;
            int i4 = iArr[1] ^ iArr4[1];
            iArr[1] = i4;
            int i5 = iArr[2] ^ iArr4[2];
            iArr[2] = i5;
            int i6 = iArr[3] ^ iArr4[3];
            iArr[3] = i6;
            int[] iArr5 = iArr2[i2 + 1][(b & 240) >>> 4];
            iArr[0] = iArr5[0] ^ i3;
            iArr[1] = iArr5[1] ^ i4;
            iArr[2] = iArr5[2] ^ i5;
            iArr[3] = iArr5[3] ^ i6;
        }
        Pack.intToBigEndian(iArr, bArr, 0);
    }
}
