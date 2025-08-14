package org.bouncycastle.pqc.crypto.falcon;

/* loaded from: classes4.dex */
class FalconSign {
    FPREngine fpr = new FPREngine();
    FalconFFT fft = new FalconFFT();
    FalconCommon common = new FalconCommon();

    FalconSign() {
    }

    private static int MKN(int i) {
        return 1 << i;
    }

    int do_sign_dyn(SamplerZ samplerZ, SamplerCtx samplerCtx, short[] sArr, int i, byte[] bArr, int i2, byte[] bArr2, int i3, byte[] bArr3, int i4, byte[] bArr4, int i5, short[] sArr2, int i6, int i7, FalconFPR[] falconFPRArr, int i8) {
        int iMKN = MKN(i7);
        int i9 = i8 + iMKN;
        int i10 = i9 + iMKN;
        int i11 = i10 + iMKN;
        smallints_to_fpr(falconFPRArr, i9, bArr, i2, i7);
        smallints_to_fpr(falconFPRArr, i8, bArr2, i3, i7);
        smallints_to_fpr(falconFPRArr, i11, bArr3, i4, i7);
        smallints_to_fpr(falconFPRArr, i10, bArr4, i5, i7);
        this.fft.FFT(falconFPRArr, i9, i7);
        this.fft.FFT(falconFPRArr, i8, i7);
        this.fft.FFT(falconFPRArr, i11, i7);
        this.fft.FFT(falconFPRArr, i10, i7);
        this.fft.poly_neg(falconFPRArr, i9, i7);
        this.fft.poly_neg(falconFPRArr, i11, i7);
        int i12 = i11 + iMKN;
        int i13 = i12 + iMKN;
        System.arraycopy(falconFPRArr, i9, falconFPRArr, i12, iMKN);
        this.fft.poly_mulselfadj_fft(falconFPRArr, i12, i7);
        System.arraycopy(falconFPRArr, i8, falconFPRArr, i13, iMKN);
        this.fft.poly_muladj_fft(falconFPRArr, i13, falconFPRArr, i10, i7);
        this.fft.poly_mulselfadj_fft(falconFPRArr, i8, i7);
        this.fft.poly_add(falconFPRArr, i8, falconFPRArr, i12, i7);
        System.arraycopy(falconFPRArr, i9, falconFPRArr, i12, iMKN);
        this.fft.poly_muladj_fft(falconFPRArr, i9, falconFPRArr, i11, i7);
        this.fft.poly_add(falconFPRArr, i9, falconFPRArr, i13, i7);
        this.fft.poly_mulselfadj_fft(falconFPRArr, i10, i7);
        System.arraycopy(falconFPRArr, i11, falconFPRArr, i13, iMKN);
        this.fft.poly_mulselfadj_fft(falconFPRArr, i13, i7);
        this.fft.poly_add(falconFPRArr, i10, falconFPRArr, i13, i7);
        int i14 = i13 + iMKN;
        int i15 = 0;
        while (i15 < iMKN) {
            falconFPRArr[i13 + i15] = this.fpr.fpr_of(sArr2[i6 + i15]);
            i15++;
            i14 = i14;
        }
        int i16 = i14;
        this.fft.FFT(falconFPRArr, i13, i7);
        FalconFPR falconFPR = this.fpr.fpr_inverse_of_q;
        System.arraycopy(falconFPRArr, i13, falconFPRArr, i16, iMKN);
        this.fft.poly_mul_fft(falconFPRArr, i16, falconFPRArr, i12, i7);
        this.fft.poly_mulconst(falconFPRArr, i16, this.fpr.fpr_neg(falconFPR), i7);
        this.fft.poly_mul_fft(falconFPRArr, i13, falconFPRArr, i11, i7);
        this.fft.poly_mulconst(falconFPRArr, i13, falconFPR, i7);
        int i17 = iMKN * 2;
        System.arraycopy(falconFPRArr, i13, falconFPRArr, i11, i17);
        ffSampling_fft_dyntree(samplerZ, samplerCtx, falconFPRArr, i11, falconFPRArr, i12, falconFPRArr, i8, falconFPRArr, i9, falconFPRArr, i10, i7, i7, falconFPRArr, i13);
        System.arraycopy(falconFPRArr, i11, falconFPRArr, i12, i17);
        smallints_to_fpr(falconFPRArr, i9, bArr, i2, i7);
        smallints_to_fpr(falconFPRArr, i8, bArr2, i3, i7);
        smallints_to_fpr(falconFPRArr, i11, bArr3, i4, i7);
        smallints_to_fpr(falconFPRArr, i10, bArr4, i5, i7);
        this.fft.FFT(falconFPRArr, i9, i7);
        this.fft.FFT(falconFPRArr, i8, i7);
        this.fft.FFT(falconFPRArr, i11, i7);
        this.fft.FFT(falconFPRArr, i10, i7);
        this.fft.poly_neg(falconFPRArr, i9, i7);
        this.fft.poly_neg(falconFPRArr, i11, i7);
        int i18 = i16 + iMKN;
        System.arraycopy(falconFPRArr, i12, falconFPRArr, i16, iMKN);
        System.arraycopy(falconFPRArr, i13, falconFPRArr, i18, iMKN);
        this.fft.poly_mul_fft(falconFPRArr, i16, falconFPRArr, i8, i7);
        this.fft.poly_mul_fft(falconFPRArr, i18, falconFPRArr, i10, i7);
        this.fft.poly_add(falconFPRArr, i16, falconFPRArr, i18, i7);
        System.arraycopy(falconFPRArr, i12, falconFPRArr, i18, iMKN);
        this.fft.poly_mul_fft(falconFPRArr, i18, falconFPRArr, i9, i7);
        System.arraycopy(falconFPRArr, i16, falconFPRArr, i12, iMKN);
        this.fft.poly_mul_fft(falconFPRArr, i13, falconFPRArr, i11, i7);
        this.fft.poly_add(falconFPRArr, i13, falconFPRArr, i18, i7);
        this.fft.iFFT(falconFPRArr, i12, i7);
        this.fft.iFFT(falconFPRArr, i13, i7);
        short[] sArr3 = new short[iMKN];
        int i19 = 0;
        int i20 = 0;
        for (int i21 = 0; i21 < iMKN; i21++) {
            int iFpr_rint = (sArr2[i6 + i21] & 65535) - ((int) this.fpr.fpr_rint(falconFPRArr[i12 + i21]));
            i19 += iFpr_rint * iFpr_rint;
            i20 |= i19;
            sArr3[i21] = (short) iFpr_rint;
        }
        int i22 = (-(i20 >>> 31)) | i19;
        short[] sArr4 = new short[iMKN];
        for (int i23 = 0; i23 < iMKN; i23++) {
            sArr4[i23] = (short) (-this.fpr.fpr_rint(falconFPRArr[i13 + i23]));
        }
        if (this.common.is_short_half(i22, sArr4, 0, i7) == 0) {
            return 0;
        }
        System.arraycopy(sArr4, 0, sArr, i, iMKN);
        return 1;
    }

    int do_sign_tree(SamplerZ samplerZ, SamplerCtx samplerCtx, short[] sArr, int i, FalconFPR[] falconFPRArr, int i2, short[] sArr2, int i3, int i4, FalconFPR[] falconFPRArr2, int i5) {
        int iMKN = MKN(i4);
        int i6 = i5 + iMKN;
        int iSkoff_b00 = i2 + skoff_b00(i4);
        int iSkoff_b01 = i2 + skoff_b01(i4);
        int iSkoff_b10 = i2 + skoff_b10(i4);
        int iSkoff_b11 = i2 + skoff_b11(i4);
        int iSkoff_tree = i2 + skoff_tree(i4);
        for (int i7 = 0; i7 < iMKN; i7++) {
            falconFPRArr2[i5 + i7] = this.fpr.fpr_of(sArr2[i3 + i7]);
        }
        this.fft.FFT(falconFPRArr2, i5, i4);
        FalconFPR falconFPR = this.fpr.fpr_inverse_of_q;
        System.arraycopy(falconFPRArr2, i5, falconFPRArr2, i6, iMKN);
        this.fft.poly_mul_fft(falconFPRArr2, i6, falconFPRArr, iSkoff_b01, i4);
        this.fft.poly_mulconst(falconFPRArr2, i6, this.fpr.fpr_neg(falconFPR), i4);
        this.fft.poly_mul_fft(falconFPRArr2, i5, falconFPRArr, iSkoff_b11, i4);
        this.fft.poly_mulconst(falconFPRArr2, i5, falconFPR, i4);
        int i8 = i6 + iMKN;
        int i9 = i8 + iMKN;
        ffSampling_fft(samplerZ, samplerCtx, falconFPRArr2, i8, falconFPRArr2, i9, falconFPRArr, iSkoff_tree, falconFPRArr2, i5, falconFPRArr2, i6, i4, falconFPRArr2, i9 + iMKN);
        System.arraycopy(falconFPRArr2, i8, falconFPRArr2, i5, iMKN);
        System.arraycopy(falconFPRArr2, i9, falconFPRArr2, i6, iMKN);
        this.fft.poly_mul_fft(falconFPRArr2, i8, falconFPRArr, iSkoff_b00, i4);
        this.fft.poly_mul_fft(falconFPRArr2, i9, falconFPRArr, iSkoff_b10, i4);
        this.fft.poly_add(falconFPRArr2, i8, falconFPRArr2, i9, i4);
        System.arraycopy(falconFPRArr2, i5, falconFPRArr2, i9, iMKN);
        this.fft.poly_mul_fft(falconFPRArr2, i9, falconFPRArr, iSkoff_b01, i4);
        System.arraycopy(falconFPRArr2, i8, falconFPRArr2, i5, iMKN);
        this.fft.poly_mul_fft(falconFPRArr2, i6, falconFPRArr, iSkoff_b11, i4);
        this.fft.poly_add(falconFPRArr2, i6, falconFPRArr2, i9, i4);
        this.fft.iFFT(falconFPRArr2, i5, i4);
        this.fft.iFFT(falconFPRArr2, i6, i4);
        short[] sArr3 = new short[iMKN];
        int i10 = 0;
        int i11 = 0;
        for (int i12 = 0; i12 < iMKN; i12++) {
            int iFpr_rint = (sArr2[i3 + i12] & 65535) - ((int) this.fpr.fpr_rint(falconFPRArr2[i5 + i12]));
            i10 += iFpr_rint * iFpr_rint;
            i11 |= i10;
            sArr3[i12] = (short) iFpr_rint;
        }
        int i13 = (-(i11 >>> 31)) | i10;
        short[] sArr4 = new short[iMKN];
        for (int i14 = 0; i14 < iMKN; i14++) {
            sArr4[i14] = (short) (-this.fpr.fpr_rint(falconFPRArr2[i6 + i14]));
        }
        if (this.common.is_short_half(i13, sArr4, 0, i4) == 0) {
            return 0;
        }
        System.arraycopy(sArr4, 0, sArr, i, iMKN);
        System.arraycopy(sArr3, 0, falconFPRArr2, i5, iMKN);
        return 1;
    }

    void expand_privkey(FalconFPR[] falconFPRArr, int i, byte[] bArr, int i2, byte[] bArr2, int i3, byte[] bArr3, int i4, byte[] bArr4, int i5, int i6, FalconFPR[] falconFPRArr2, int i7) {
        int iMKN = MKN(i6);
        int iSkoff_b00 = i + skoff_b00(i6);
        int iSkoff_b01 = i + skoff_b01(i6);
        int iSkoff_b10 = i + skoff_b10(i6);
        int iSkoff_b11 = i + skoff_b11(i6);
        int iSkoff_tree = i + skoff_tree(i6);
        smallints_to_fpr(falconFPRArr, iSkoff_b01, bArr, i2, i6);
        smallints_to_fpr(falconFPRArr, iSkoff_b00, bArr2, i3, i6);
        smallints_to_fpr(falconFPRArr, iSkoff_b11, bArr3, i4, i6);
        smallints_to_fpr(falconFPRArr, iSkoff_b10, bArr4, i5, i6);
        this.fft.FFT(falconFPRArr, iSkoff_b01, i6);
        this.fft.FFT(falconFPRArr, iSkoff_b00, i6);
        this.fft.FFT(falconFPRArr, iSkoff_b11, i6);
        this.fft.FFT(falconFPRArr, iSkoff_b10, i6);
        this.fft.poly_neg(falconFPRArr, iSkoff_b01, i6);
        this.fft.poly_neg(falconFPRArr, iSkoff_b11, i6);
        int i8 = i7 + iMKN;
        int i9 = i8 + iMKN;
        int i10 = i9 + iMKN;
        System.arraycopy(falconFPRArr, iSkoff_b00, falconFPRArr2, i7, iMKN);
        this.fft.poly_mulselfadj_fft(falconFPRArr2, i7, i6);
        System.arraycopy(falconFPRArr, iSkoff_b01, falconFPRArr2, i10, iMKN);
        this.fft.poly_mulselfadj_fft(falconFPRArr2, i10, i6);
        this.fft.poly_add(falconFPRArr2, i7, falconFPRArr2, i10, i6);
        System.arraycopy(falconFPRArr, iSkoff_b00, falconFPRArr2, i8, iMKN);
        this.fft.poly_muladj_fft(falconFPRArr2, i8, falconFPRArr, iSkoff_b10, i6);
        System.arraycopy(falconFPRArr, iSkoff_b01, falconFPRArr2, i10, iMKN);
        this.fft.poly_muladj_fft(falconFPRArr2, i10, falconFPRArr, iSkoff_b11, i6);
        this.fft.poly_add(falconFPRArr2, i8, falconFPRArr2, i10, i6);
        System.arraycopy(falconFPRArr, iSkoff_b10, falconFPRArr2, i9, iMKN);
        this.fft.poly_mulselfadj_fft(falconFPRArr2, i9, i6);
        System.arraycopy(falconFPRArr, iSkoff_b11, falconFPRArr2, i10, iMKN);
        this.fft.poly_mulselfadj_fft(falconFPRArr2, i10, i6);
        this.fft.poly_add(falconFPRArr2, i9, falconFPRArr2, i10, i6);
        ffLDL_fft(falconFPRArr, iSkoff_tree, falconFPRArr2, i7, falconFPRArr2, i8, falconFPRArr2, i9, i6, falconFPRArr2, i10);
        ffLDL_binary_normalize(falconFPRArr, iSkoff_tree, i6, i6);
    }

    void ffLDL_binary_normalize(FalconFPR[] falconFPRArr, int i, int i2, int i3) {
        int iMKN = MKN(i3);
        if (iMKN == 1) {
            FPREngine fPREngine = this.fpr;
            falconFPRArr[i] = fPREngine.fpr_mul(fPREngine.fpr_sqrt(falconFPRArr[i]), this.fpr.fpr_inv_sigma[i2]);
        } else {
            int i4 = i + iMKN;
            int i5 = i3 - 1;
            ffLDL_binary_normalize(falconFPRArr, i4, i2, i5);
            ffLDL_binary_normalize(falconFPRArr, i4 + ffLDL_treesize(i5), i2, i5);
        }
    }

    void ffLDL_fft(FalconFPR[] falconFPRArr, int i, FalconFPR[] falconFPRArr2, int i2, FalconFPR[] falconFPRArr3, int i3, FalconFPR[] falconFPRArr4, int i4, int i5, FalconFPR[] falconFPRArr5, int i6) {
        int iMKN = MKN(i5);
        if (iMKN == 1) {
            falconFPRArr[i] = falconFPRArr2[i2];
            return;
        }
        int i7 = iMKN >> 1;
        int i8 = i6 + iMKN;
        int i9 = i6 + (iMKN << 1);
        System.arraycopy(falconFPRArr2, i2, falconFPRArr5, i6, iMKN);
        this.fft.poly_LDLmv_fft(falconFPRArr5, i8, falconFPRArr, i, falconFPRArr2, i2, falconFPRArr3, i3, falconFPRArr4, i4, i5);
        this.fft.poly_split_fft(falconFPRArr5, i9, falconFPRArr5, i9 + i7, falconFPRArr5, i6, i5);
        int i10 = i6 + i7;
        this.fft.poly_split_fft(falconFPRArr5, i6, falconFPRArr5, i10, falconFPRArr5, i8, i5);
        System.arraycopy(falconFPRArr5, i9, falconFPRArr5, i8, iMKN);
        int i11 = i + iMKN;
        int i12 = i5 - 1;
        ffLDL_fft_inner(falconFPRArr, i11, falconFPRArr5, i8, falconFPRArr5, i8 + i7, i12, falconFPRArr5, i9);
        ffLDL_fft_inner(falconFPRArr, i11 + ffLDL_treesize(i12), falconFPRArr5, i6, falconFPRArr5, i10, i12, falconFPRArr5, i9);
    }

    void ffLDL_fft_inner(FalconFPR[] falconFPRArr, int i, FalconFPR[] falconFPRArr2, int i2, FalconFPR[] falconFPRArr3, int i3, int i4, FalconFPR[] falconFPRArr4, int i5) {
        int iMKN = MKN(i4);
        if (iMKN == 1) {
            falconFPRArr[i] = falconFPRArr2[i2];
            return;
        }
        int i6 = iMKN >> 1;
        this.fft.poly_LDLmv_fft(falconFPRArr4, i5, falconFPRArr, i, falconFPRArr2, i2, falconFPRArr3, i3, falconFPRArr2, i2, i4);
        int i7 = i3 + i6;
        this.fft.poly_split_fft(falconFPRArr3, i3, falconFPRArr3, i7, falconFPRArr2, i2, i4);
        int i8 = i2 + i6;
        this.fft.poly_split_fft(falconFPRArr2, i2, falconFPRArr2, i8, falconFPRArr4, i5, i4);
        int i9 = i + iMKN;
        int i10 = i4 - 1;
        ffLDL_fft_inner(falconFPRArr, i9, falconFPRArr3, i3, falconFPRArr3, i7, i10, falconFPRArr4, i5);
        ffLDL_fft_inner(falconFPRArr, i9 + ffLDL_treesize(i10), falconFPRArr2, i2, falconFPRArr2, i8, i10, falconFPRArr4, i5);
    }

    int ffLDL_treesize(int i) {
        return (i + 1) << i;
    }

    void ffSampling_fft(SamplerZ samplerZ, SamplerCtx samplerCtx, FalconFPR[] falconFPRArr, int i, FalconFPR[] falconFPRArr2, int i2, FalconFPR[] falconFPRArr3, int i3, FalconFPR[] falconFPRArr4, int i4, FalconFPR[] falconFPRArr5, int i5, int i6, FalconFPR[] falconFPRArr6, int i7) {
        if (i6 != 2) {
            if (i6 != 1) {
                int i8 = 1 << i6;
                int i9 = i8 >> 1;
                int i10 = i3 + i8;
                int i11 = i6 - 1;
                int iFfLDL_treesize = i10 + ffLDL_treesize(i11);
                int i12 = i2 + i9;
                this.fft.poly_split_fft(falconFPRArr2, i2, falconFPRArr2, i12, falconFPRArr5, i5, i6);
                int i13 = i7 + i9;
                int i14 = i7 + i8;
                ffSampling_fft(samplerZ, samplerCtx, falconFPRArr6, i7, falconFPRArr6, i13, falconFPRArr3, iFfLDL_treesize, falconFPRArr2, i2, falconFPRArr2, i12, i11, falconFPRArr6, i14);
                this.fft.poly_merge_fft(falconFPRArr2, i2, falconFPRArr6, i7, falconFPRArr6, i13, i6);
                System.arraycopy(falconFPRArr5, i5, falconFPRArr6, i7, i8);
                this.fft.poly_sub(falconFPRArr6, i7, falconFPRArr2, i2, i6);
                this.fft.poly_mul_fft(falconFPRArr6, i7, falconFPRArr3, i3, i6);
                this.fft.poly_add(falconFPRArr6, i7, falconFPRArr4, i4, i6);
                int i15 = i + i9;
                this.fft.poly_split_fft(falconFPRArr, i, falconFPRArr, i15, falconFPRArr6, i7, i6);
                ffSampling_fft(samplerZ, samplerCtx, falconFPRArr6, i7, falconFPRArr6, i13, falconFPRArr3, i10, falconFPRArr, i, falconFPRArr, i15, i11, falconFPRArr6, i14);
                this.fft.poly_merge_fft(falconFPRArr, i, falconFPRArr6, i7, falconFPRArr6, i13, i6);
                return;
            }
            FalconFPR falconFPR = falconFPRArr5[i5];
            FalconFPR falconFPR2 = falconFPRArr5[i5 + 1];
            FalconFPR falconFPR3 = falconFPRArr3[i3 + 3];
            FalconFPR falconFPRFpr_of = this.fpr.fpr_of(samplerZ.sample(samplerCtx, falconFPR, falconFPR3));
            falconFPRArr2[i2] = falconFPRFpr_of;
            FalconFPR falconFPRFpr_of2 = this.fpr.fpr_of(samplerZ.sample(samplerCtx, falconFPR2, falconFPR3));
            falconFPRArr2[i2 + 1] = falconFPRFpr_of2;
            FalconFPR falconFPRFpr_sub = this.fpr.fpr_sub(falconFPR, falconFPRFpr_of);
            FalconFPR falconFPRFpr_sub2 = this.fpr.fpr_sub(falconFPR2, falconFPRFpr_of2);
            FalconFPR falconFPR4 = falconFPRArr3[i3];
            FalconFPR falconFPR5 = falconFPRArr3[i3 + 1];
            FPREngine fPREngine = this.fpr;
            FalconFPR falconFPRFpr_sub3 = fPREngine.fpr_sub(fPREngine.fpr_mul(falconFPRFpr_sub, falconFPR4), this.fpr.fpr_mul(falconFPRFpr_sub2, falconFPR5));
            FPREngine fPREngine2 = this.fpr;
            FalconFPR falconFPRFpr_add = fPREngine2.fpr_add(fPREngine2.fpr_mul(falconFPRFpr_sub, falconFPR5), this.fpr.fpr_mul(falconFPRFpr_sub2, falconFPR4));
            FalconFPR falconFPRFpr_add2 = this.fpr.fpr_add(falconFPRFpr_sub3, falconFPRArr4[i4]);
            FalconFPR falconFPRFpr_add3 = this.fpr.fpr_add(falconFPRFpr_add, falconFPRArr4[i4 + 1]);
            FalconFPR falconFPR6 = falconFPRArr3[i3 + 2];
            falconFPRArr[i] = this.fpr.fpr_of(samplerZ.sample(samplerCtx, falconFPRFpr_add2, falconFPR6));
            falconFPRArr[i + 1] = this.fpr.fpr_of(samplerZ.sample(samplerCtx, falconFPRFpr_add3, falconFPR6));
            return;
        }
        FalconFPR falconFPR7 = falconFPRArr5[i5];
        int i16 = i5 + 2;
        FalconFPR falconFPR8 = falconFPRArr5[i16];
        int i17 = i5 + 1;
        FalconFPR falconFPR9 = falconFPRArr5[i17];
        int i18 = i5 + 3;
        FalconFPR falconFPR10 = falconFPRArr5[i18];
        FalconFPR falconFPRFpr_add4 = this.fpr.fpr_add(falconFPR7, falconFPR9);
        FalconFPR falconFPRFpr_add5 = this.fpr.fpr_add(falconFPR8, falconFPR10);
        FalconFPR falconFPRFpr_half = this.fpr.fpr_half(falconFPRFpr_add4);
        FalconFPR falconFPRFpr_half2 = this.fpr.fpr_half(falconFPRFpr_add5);
        FalconFPR falconFPRFpr_sub4 = this.fpr.fpr_sub(falconFPR7, falconFPR9);
        FalconFPR falconFPRFpr_sub5 = this.fpr.fpr_sub(falconFPR8, falconFPR10);
        FPREngine fPREngine3 = this.fpr;
        FalconFPR falconFPRFpr_mul = fPREngine3.fpr_mul(fPREngine3.fpr_add(falconFPRFpr_sub4, falconFPRFpr_sub5), this.fpr.fpr_invsqrt8);
        FPREngine fPREngine4 = this.fpr;
        FalconFPR falconFPRFpr_mul2 = fPREngine4.fpr_mul(fPREngine4.fpr_sub(falconFPRFpr_sub5, falconFPRFpr_sub4), this.fpr.fpr_invsqrt8);
        FalconFPR falconFPR11 = falconFPRArr3[i3 + 11];
        FalconFPR falconFPRFpr_of3 = this.fpr.fpr_of(samplerZ.sample(samplerCtx, falconFPRFpr_mul, falconFPR11));
        FalconFPR falconFPRFpr_of4 = this.fpr.fpr_of(samplerZ.sample(samplerCtx, falconFPRFpr_mul2, falconFPR11));
        FalconFPR falconFPRFpr_sub6 = this.fpr.fpr_sub(falconFPRFpr_mul, falconFPRFpr_of3);
        FalconFPR falconFPRFpr_sub7 = this.fpr.fpr_sub(falconFPRFpr_mul2, falconFPRFpr_of4);
        FalconFPR falconFPR12 = falconFPRArr3[i3 + 8];
        FalconFPR falconFPR13 = falconFPRArr3[i3 + 9];
        FPREngine fPREngine5 = this.fpr;
        FalconFPR falconFPRFpr_sub8 = fPREngine5.fpr_sub(fPREngine5.fpr_mul(falconFPRFpr_sub6, falconFPR12), this.fpr.fpr_mul(falconFPRFpr_sub7, falconFPR13));
        FPREngine fPREngine6 = this.fpr;
        FalconFPR falconFPRFpr_add6 = fPREngine6.fpr_add(fPREngine6.fpr_mul(falconFPRFpr_sub6, falconFPR13), this.fpr.fpr_mul(falconFPRFpr_sub7, falconFPR12));
        FalconFPR falconFPRFpr_add7 = this.fpr.fpr_add(falconFPRFpr_sub8, falconFPRFpr_half);
        FalconFPR falconFPRFpr_add8 = this.fpr.fpr_add(falconFPRFpr_add6, falconFPRFpr_half2);
        FalconFPR falconFPR14 = falconFPRArr3[i3 + 10];
        FalconFPR falconFPRFpr_of5 = this.fpr.fpr_of(samplerZ.sample(samplerCtx, falconFPRFpr_add7, falconFPR14));
        FalconFPR falconFPRFpr_of6 = this.fpr.fpr_of(samplerZ.sample(samplerCtx, falconFPRFpr_add8, falconFPR14));
        FPREngine fPREngine7 = this.fpr;
        FalconFPR falconFPRFpr_mul3 = fPREngine7.fpr_mul(fPREngine7.fpr_sub(falconFPRFpr_of3, falconFPRFpr_of4), this.fpr.fpr_invsqrt2);
        FPREngine fPREngine8 = this.fpr;
        FalconFPR falconFPRFpr_mul4 = fPREngine8.fpr_mul(fPREngine8.fpr_add(falconFPRFpr_of3, falconFPRFpr_of4), this.fpr.fpr_invsqrt2);
        FalconFPR falconFPRFpr_add9 = this.fpr.fpr_add(falconFPRFpr_of5, falconFPRFpr_mul3);
        falconFPRArr2[i2] = falconFPRFpr_add9;
        FalconFPR falconFPRFpr_add10 = this.fpr.fpr_add(falconFPRFpr_of6, falconFPRFpr_mul4);
        falconFPRArr2[i2 + 2] = falconFPRFpr_add10;
        FalconFPR falconFPRFpr_sub9 = this.fpr.fpr_sub(falconFPRFpr_of5, falconFPRFpr_mul3);
        falconFPRArr2[i2 + 1] = falconFPRFpr_sub9;
        FalconFPR falconFPRFpr_sub10 = this.fpr.fpr_sub(falconFPRFpr_of6, falconFPRFpr_mul4);
        falconFPRArr2[i2 + 3] = falconFPRFpr_sub10;
        FalconFPR falconFPRFpr_sub11 = this.fpr.fpr_sub(falconFPRArr5[i5], falconFPRFpr_add9);
        FalconFPR falconFPRFpr_sub12 = this.fpr.fpr_sub(falconFPRArr5[i17], falconFPRFpr_sub9);
        FalconFPR falconFPRFpr_sub13 = this.fpr.fpr_sub(falconFPRArr5[i16], falconFPRFpr_add10);
        FalconFPR falconFPRFpr_sub14 = this.fpr.fpr_sub(falconFPRArr5[i18], falconFPRFpr_sub10);
        FalconFPR falconFPR15 = falconFPRArr3[i3];
        FalconFPR falconFPR16 = falconFPRArr3[i3 + 2];
        FPREngine fPREngine9 = this.fpr;
        FalconFPR falconFPRFpr_sub15 = fPREngine9.fpr_sub(fPREngine9.fpr_mul(falconFPRFpr_sub11, falconFPR15), this.fpr.fpr_mul(falconFPRFpr_sub13, falconFPR16));
        FPREngine fPREngine10 = this.fpr;
        FalconFPR falconFPRFpr_add11 = fPREngine10.fpr_add(fPREngine10.fpr_mul(falconFPRFpr_sub11, falconFPR16), this.fpr.fpr_mul(falconFPRFpr_sub13, falconFPR15));
        FalconFPR falconFPR17 = falconFPRArr3[i3 + 1];
        FalconFPR falconFPR18 = falconFPRArr3[i3 + 3];
        FPREngine fPREngine11 = this.fpr;
        FalconFPR falconFPRFpr_sub16 = fPREngine11.fpr_sub(fPREngine11.fpr_mul(falconFPRFpr_sub12, falconFPR17), this.fpr.fpr_mul(falconFPRFpr_sub14, falconFPR18));
        FPREngine fPREngine12 = this.fpr;
        FalconFPR falconFPRFpr_add12 = fPREngine12.fpr_add(fPREngine12.fpr_mul(falconFPRFpr_sub12, falconFPR18), this.fpr.fpr_mul(falconFPRFpr_sub14, falconFPR17));
        FalconFPR falconFPRFpr_add13 = this.fpr.fpr_add(falconFPRFpr_sub15, falconFPRArr4[i4]);
        FalconFPR falconFPRFpr_add14 = this.fpr.fpr_add(falconFPRFpr_sub16, falconFPRArr4[i4 + 1]);
        FalconFPR falconFPRFpr_add15 = this.fpr.fpr_add(falconFPRFpr_add11, falconFPRArr4[i4 + 2]);
        FalconFPR falconFPRFpr_add16 = this.fpr.fpr_add(falconFPRFpr_add12, falconFPRArr4[i4 + 3]);
        FalconFPR falconFPRFpr_add17 = this.fpr.fpr_add(falconFPRFpr_add13, falconFPRFpr_add14);
        FalconFPR falconFPRFpr_add18 = this.fpr.fpr_add(falconFPRFpr_add15, falconFPRFpr_add16);
        FalconFPR falconFPRFpr_half3 = this.fpr.fpr_half(falconFPRFpr_add17);
        FalconFPR falconFPRFpr_half4 = this.fpr.fpr_half(falconFPRFpr_add18);
        FalconFPR falconFPRFpr_sub17 = this.fpr.fpr_sub(falconFPRFpr_add13, falconFPRFpr_add14);
        FalconFPR falconFPRFpr_sub18 = this.fpr.fpr_sub(falconFPRFpr_add15, falconFPRFpr_add16);
        FPREngine fPREngine13 = this.fpr;
        FalconFPR falconFPRFpr_mul5 = fPREngine13.fpr_mul(fPREngine13.fpr_add(falconFPRFpr_sub17, falconFPRFpr_sub18), this.fpr.fpr_invsqrt8);
        FPREngine fPREngine14 = this.fpr;
        FalconFPR falconFPRFpr_mul6 = fPREngine14.fpr_mul(fPREngine14.fpr_sub(falconFPRFpr_sub18, falconFPRFpr_sub17), this.fpr.fpr_invsqrt8);
        FalconFPR falconFPR19 = falconFPRArr3[i3 + 7];
        FalconFPR falconFPRFpr_of7 = this.fpr.fpr_of(samplerZ.sample(samplerCtx, falconFPRFpr_mul5, falconFPR19));
        FalconFPR falconFPRFpr_of8 = this.fpr.fpr_of(samplerZ.sample(samplerCtx, falconFPRFpr_mul6, falconFPR19));
        FalconFPR falconFPRFpr_sub19 = this.fpr.fpr_sub(falconFPRFpr_mul5, falconFPRFpr_of7);
        FalconFPR falconFPRFpr_sub20 = this.fpr.fpr_sub(falconFPRFpr_mul6, falconFPRFpr_of8);
        FalconFPR falconFPR20 = falconFPRArr3[i3 + 4];
        FalconFPR falconFPR21 = falconFPRArr3[i3 + 5];
        FPREngine fPREngine15 = this.fpr;
        FalconFPR falconFPRFpr_sub21 = fPREngine15.fpr_sub(fPREngine15.fpr_mul(falconFPRFpr_sub19, falconFPR20), this.fpr.fpr_mul(falconFPRFpr_sub20, falconFPR21));
        FPREngine fPREngine16 = this.fpr;
        FalconFPR falconFPRFpr_add19 = fPREngine16.fpr_add(fPREngine16.fpr_mul(falconFPRFpr_sub19, falconFPR21), this.fpr.fpr_mul(falconFPRFpr_sub20, falconFPR20));
        FalconFPR falconFPRFpr_add20 = this.fpr.fpr_add(falconFPRFpr_sub21, falconFPRFpr_half3);
        FalconFPR falconFPRFpr_add21 = this.fpr.fpr_add(falconFPRFpr_add19, falconFPRFpr_half4);
        FalconFPR falconFPR22 = falconFPRArr3[i3 + 6];
        FalconFPR falconFPRFpr_of9 = this.fpr.fpr_of(samplerZ.sample(samplerCtx, falconFPRFpr_add20, falconFPR22));
        FalconFPR falconFPRFpr_of10 = this.fpr.fpr_of(samplerZ.sample(samplerCtx, falconFPRFpr_add21, falconFPR22));
        FPREngine fPREngine17 = this.fpr;
        FalconFPR falconFPRFpr_mul7 = fPREngine17.fpr_mul(fPREngine17.fpr_sub(falconFPRFpr_of7, falconFPRFpr_of8), this.fpr.fpr_invsqrt2);
        FPREngine fPREngine18 = this.fpr;
        FalconFPR falconFPRFpr_mul8 = fPREngine18.fpr_mul(fPREngine18.fpr_add(falconFPRFpr_of7, falconFPRFpr_of8), this.fpr.fpr_invsqrt2);
        falconFPRArr[i] = this.fpr.fpr_add(falconFPRFpr_of9, falconFPRFpr_mul7);
        falconFPRArr[i + 2] = this.fpr.fpr_add(falconFPRFpr_of10, falconFPRFpr_mul8);
        falconFPRArr[i + 1] = this.fpr.fpr_sub(falconFPRFpr_of9, falconFPRFpr_mul7);
        falconFPRArr[i + 3] = this.fpr.fpr_sub(falconFPRFpr_of10, falconFPRFpr_mul8);
    }

    void ffSampling_fft_dyntree(SamplerZ samplerZ, SamplerCtx samplerCtx, FalconFPR[] falconFPRArr, int i, FalconFPR[] falconFPRArr2, int i2, FalconFPR[] falconFPRArr3, int i3, FalconFPR[] falconFPRArr4, int i4, FalconFPR[] falconFPRArr5, int i5, int i6, int i7, FalconFPR[] falconFPRArr6, int i8) {
        if (i7 == 0) {
            FalconFPR falconFPR = falconFPRArr3[i3];
            FPREngine fPREngine = this.fpr;
            FalconFPR falconFPRFpr_mul = fPREngine.fpr_mul(fPREngine.fpr_sqrt(falconFPR), this.fpr.fpr_inv_sigma[i6]);
            falconFPRArr[i] = this.fpr.fpr_of(samplerZ.sample(samplerCtx, falconFPRArr[i], falconFPRFpr_mul));
            falconFPRArr2[i2] = this.fpr.fpr_of(samplerZ.sample(samplerCtx, falconFPRArr2[i2], falconFPRFpr_mul));
            return;
        }
        int i9 = 1 << i7;
        int i10 = i9 >> 1;
        this.fft.poly_LDL_fft(falconFPRArr3, i3, falconFPRArr4, i4, falconFPRArr5, i5, i7);
        int i11 = i8 + i10;
        this.fft.poly_split_fft(falconFPRArr6, i8, falconFPRArr6, i11, falconFPRArr3, i3, i7);
        System.arraycopy(falconFPRArr6, i8, falconFPRArr3, i3, i9);
        this.fft.poly_split_fft(falconFPRArr6, i8, falconFPRArr6, i11, falconFPRArr5, i5, i7);
        System.arraycopy(falconFPRArr6, i8, falconFPRArr5, i5, i9);
        System.arraycopy(falconFPRArr4, i4, falconFPRArr6, i8, i9);
        System.arraycopy(falconFPRArr3, i3, falconFPRArr4, i4, i10);
        int i12 = i4 + i10;
        System.arraycopy(falconFPRArr5, i5, falconFPRArr4, i12, i10);
        int i13 = i8 + i9;
        int i14 = i13 + i10;
        this.fft.poly_split_fft(falconFPRArr6, i13, falconFPRArr6, i14, falconFPRArr2, i2, i7);
        int i15 = i7 - 1;
        ffSampling_fft_dyntree(samplerZ, samplerCtx, falconFPRArr6, i13, falconFPRArr6, i14, falconFPRArr5, i5, falconFPRArr5, i5 + i10, falconFPRArr4, i12, i6, i15, falconFPRArr6, i13 + i9);
        int i16 = i8 + (i9 << 1);
        this.fft.poly_merge_fft(falconFPRArr6, i16, falconFPRArr6, i13, falconFPRArr6, i14, i7);
        System.arraycopy(falconFPRArr2, i2, falconFPRArr6, i13, i9);
        this.fft.poly_sub(falconFPRArr6, i13, falconFPRArr6, i16, i7);
        System.arraycopy(falconFPRArr6, i16, falconFPRArr2, i2, i9);
        this.fft.poly_mul_fft(falconFPRArr6, i8, falconFPRArr6, i13, i7);
        this.fft.poly_add(falconFPRArr, i, falconFPRArr6, i8, i7);
        this.fft.poly_split_fft(falconFPRArr6, i8, falconFPRArr6, i11, falconFPRArr, i, i7);
        ffSampling_fft_dyntree(samplerZ, samplerCtx, falconFPRArr6, i8, falconFPRArr6, i11, falconFPRArr3, i3, falconFPRArr3, i3 + i10, falconFPRArr4, i4, i6, i15, falconFPRArr6, i13);
        this.fft.poly_merge_fft(falconFPRArr, i, falconFPRArr6, i8, falconFPRArr6, i11, i7);
    }

    void sign_dyn(short[] sArr, int i, SHAKE256 shake256, byte[] bArr, int i2, byte[] bArr2, int i3, byte[] bArr3, int i4, byte[] bArr4, int i5, short[] sArr2, int i6, int i7, FalconFPR[] falconFPRArr, int i8) {
        SamplerCtx samplerCtx;
        SamplerZ samplerZ;
        do {
            samplerCtx = new SamplerCtx();
            samplerZ = new SamplerZ();
            samplerCtx.sigma_min = this.fpr.fpr_sigma_min[i7];
            samplerCtx.p.prng_init(shake256);
        } while (do_sign_dyn(samplerZ, samplerCtx, sArr, i, bArr, i2, bArr2, i3, bArr3, i4, bArr4, i5, sArr2, i6, i7, falconFPRArr, i8) == 0);
    }

    void sign_tree(short[] sArr, int i, SHAKE256 shake256, FalconFPR[] falconFPRArr, int i2, short[] sArr2, int i3, int i4, FalconFPR[] falconFPRArr2, int i5) {
        SamplerCtx samplerCtx;
        SamplerZ samplerZ;
        do {
            samplerCtx = new SamplerCtx();
            samplerZ = new SamplerZ();
            samplerCtx.sigma_min = this.fpr.fpr_sigma_min[i4];
            samplerCtx.p.prng_init(shake256);
        } while (do_sign_tree(samplerZ, samplerCtx, sArr, i, falconFPRArr, i2, sArr2, i3, i4, falconFPRArr2, i5) == 0);
    }

    int skoff_b00(int i) {
        return 0;
    }

    int skoff_b01(int i) {
        return MKN(i);
    }

    int skoff_b10(int i) {
        return MKN(i) * 2;
    }

    int skoff_b11(int i) {
        return MKN(i) * 3;
    }

    int skoff_tree(int i) {
        return MKN(i) * 4;
    }

    void smallints_to_fpr(FalconFPR[] falconFPRArr, int i, byte[] bArr, int i2, int i3) {
        int iMKN = MKN(i3);
        for (int i4 = 0; i4 < iMKN; i4++) {
            falconFPRArr[i + i4] = this.fpr.fpr_of(bArr[i2 + i4]);
        }
    }
}
