package org.bouncycastle.pqc.crypto.falcon;

/* loaded from: classes4.dex */
class FalconRNG {
    byte[] bd = new byte[512];
    long bdummy_u64 = 0;
    int ptr = 0;
    byte[] sd = new byte[256];
    long sdummy_u64 = 0;
    int type = 0;
    FalconConversions convertor = new FalconConversions();

    FalconRNG() {
    }

    private void QROUND(int i, int i2, int i3, int i4, int[] iArr) {
        int i5 = iArr[i] + iArr[i2];
        iArr[i] = i5;
        int i6 = i5 ^ iArr[i4];
        iArr[i4] = i6;
        int i7 = (i6 >>> 16) | (i6 << 16);
        iArr[i4] = i7;
        int i8 = iArr[i3] + i7;
        iArr[i3] = i8;
        int i9 = iArr[i2] ^ i8;
        iArr[i2] = i9;
        int i10 = (i9 >>> 20) | (i9 << 12);
        iArr[i2] = i10;
        int i11 = iArr[i] + i10;
        iArr[i] = i11;
        int i12 = iArr[i4] ^ i11;
        iArr[i4] = i12;
        int i13 = (i12 >>> 24) | (i12 << 8);
        iArr[i4] = i13;
        int i14 = iArr[i3] + i13;
        iArr[i3] = i14;
        int i15 = iArr[i2] ^ i14;
        iArr[i2] = i15;
        iArr[i2] = (i15 >>> 25) | (i15 << 7);
    }

    void prng_get_bytes(byte[] bArr, int i, int i2) {
        while (i2 > 0) {
            byte[] bArr2 = this.bd;
            int length = bArr2.length - this.ptr;
            if (length > i2) {
                length = i2;
            }
            System.arraycopy(bArr2, 0, bArr, i, length);
            i += length;
            i2 -= length;
            int i3 = this.ptr + length;
            this.ptr = i3;
            if (i3 == this.bd.length) {
                prng_refill();
            }
        }
    }

    long prng_get_u64() {
        int i = this.ptr;
        if (i >= this.bd.length - 9) {
            prng_refill();
            i = 0;
        }
        this.ptr = i + 8;
        byte[] bArr = this.bd;
        return ((bArr[i + 7] & 255) << 56) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 3] & 255) << 24) | ((bArr[i + 4] & 255) << 32) | ((bArr[i + 5] & 255) << 40) | ((bArr[i + 6] & 255) << 48);
    }

    byte prng_get_u8() {
        byte[] bArr = this.bd;
        int i = this.ptr;
        int i2 = i + 1;
        this.ptr = i2;
        byte b = bArr[i];
        if (i2 == bArr.length) {
            prng_refill();
        }
        return b;
    }

    void prng_init(SHAKE256 shake256) {
        byte[] bArr = new byte[56];
        shake256.inner_shake256_extract(bArr, 0, 56);
        for (int i = 0; i < 14; i++) {
            int i2 = i << 2;
            System.arraycopy(this.convertor.int_to_bytes(((bArr[i2 + 1] & 255) << 8) | (bArr[i2] & 255) | ((bArr[i2 + 2] & 255) << 16) | ((bArr[i2 + 3] & 255) << 24)), 0, this.sd, i2, 4);
        }
        System.arraycopy(this.convertor.long_to_bytes((this.convertor.bytes_to_int(this.sd, 48) & 4294967295L) + ((4294967295L & this.convertor.bytes_to_int(this.sd, 52)) << 32)), 0, this.sd, 48, 8);
        prng_refill();
    }

    void prng_refill() {
        int[] iArr = {1634760805, 857760878, 2036477234, 1797285236};
        long jBytes_to_long = this.convertor.bytes_to_long(this.sd, 48);
        for (int i = 0; i < 8; i++) {
            int[] iArr2 = new int[16];
            System.arraycopy(iArr, 0, iArr2, 0, 4);
            System.arraycopy(this.convertor.bytes_to_int_array(this.sd, 0, 12), 0, iArr2, 4, 12);
            int i2 = 14;
            int i3 = (int) jBytes_to_long;
            iArr2[14] = iArr2[14] ^ i3;
            int i4 = (int) (jBytes_to_long >>> 32);
            iArr2[15] = iArr2[15] ^ i4;
            int i5 = 0;
            while (i5 < 10) {
                QROUND(0, 4, 8, 12, iArr2);
                QROUND(1, 5, 9, 13, iArr2);
                QROUND(2, 6, 10, 14, iArr2);
                QROUND(3, 7, 11, 15, iArr2);
                QROUND(0, 5, 10, 15, iArr2);
                QROUND(1, 6, 11, 12, iArr2);
                QROUND(2, 7, 8, 13, iArr2);
                QROUND(3, 4, 9, 14, iArr2);
                i5++;
                i2 = i2;
                i4 = i4;
                i3 = i3;
            }
            int i6 = i4;
            int i7 = i3;
            int i8 = i2;
            for (int i9 = 0; i9 < 4; i9++) {
                iArr2[i9] = iArr2[i9] + iArr[i9];
            }
            for (int i10 = 4; i10 < i8; i10++) {
                iArr2[i10] = iArr2[i10] + this.convertor.bytes_to_int(this.sd, (i10 * 4) - 16);
            }
            iArr2[i8] = iArr2[i8] + (this.convertor.bytes_to_int(this.sd, 40) ^ i7);
            iArr2[15] = iArr2[15] + (this.convertor.bytes_to_int(this.sd, 44) ^ i6);
            jBytes_to_long++;
            for (int i11 = 0; i11 < 16; i11++) {
                byte[] bArr = this.bd;
                int i12 = (i << 2) + (i11 << 5);
                int i13 = iArr2[i11];
                bArr[i12] = (byte) i13;
                bArr[i12 + 1] = (byte) (i13 >>> 8);
                bArr[i12 + 2] = (byte) (i13 >>> 16);
                bArr[i12 + 3] = (byte) (i13 >>> 24);
            }
        }
        System.arraycopy(this.convertor.long_to_bytes(jBytes_to_long), 0, this.sd, 48, 8);
        this.ptr = 0;
    }
}
