package org.spongycastle.crypto.generators;

import java.math.BigInteger;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.DerivationParameters;
import org.spongycastle.crypto.Mac;
import org.spongycastle.crypto.MacDerivationFunction;
import org.spongycastle.crypto.params.KDFDoublePipelineIterationParameters;
import org.spongycastle.crypto.params.KeyParameter;

/* loaded from: classes2.dex */
public class KDFDoublePipelineIterationBytesGenerator implements MacDerivationFunction {
    private static final BigInteger INTEGER_MAX = BigInteger.valueOf(2147483647L);
    private static final BigInteger TWO = BigInteger.valueOf(2);

    /* renamed from: a, reason: collision with root package name */
    private byte[] f388a;
    private byte[] fixedInputData;
    private int generatedBytes;
    private final int h;
    private byte[] ios;
    private byte[] k;
    private int maxSizeExcl;
    private final Mac prf;
    private boolean useCounter;

    @Override // org.spongycastle.crypto.MacDerivationFunction
    public Mac getMac() {
        return this.prf;
    }

    public KDFDoublePipelineIterationBytesGenerator(Mac mac) {
        this.prf = mac;
        int macSize = mac.getMacSize();
        this.h = macSize;
        this.f388a = new byte[macSize];
        this.k = new byte[macSize];
    }

    @Override // org.spongycastle.crypto.DerivationFunction
    public void init(DerivationParameters derivationParameters) throws IllegalArgumentException {
        if (!(derivationParameters instanceof KDFDoublePipelineIterationParameters)) {
            throw new IllegalArgumentException("Wrong type of arguments given");
        }
        KDFDoublePipelineIterationParameters kDFDoublePipelineIterationParameters = (KDFDoublePipelineIterationParameters) derivationParameters;
        this.prf.init(new KeyParameter(kDFDoublePipelineIterationParameters.getKI()));
        this.fixedInputData = kDFDoublePipelineIterationParameters.getFixedInputData();
        int r = kDFDoublePipelineIterationParameters.getR();
        this.ios = new byte[r / 8];
        if (kDFDoublePipelineIterationParameters.useCounter()) {
            BigInteger bigIntegerMultiply = TWO.pow(r).multiply(BigInteger.valueOf(this.h));
            this.maxSizeExcl = bigIntegerMultiply.compareTo(INTEGER_MAX) != 1 ? bigIntegerMultiply.intValue() : Integer.MAX_VALUE;
        } else {
            this.maxSizeExcl = Integer.MAX_VALUE;
        }
        this.useCounter = kDFDoublePipelineIterationParameters.useCounter();
        this.generatedBytes = 0;
    }

    @Override // org.spongycastle.crypto.DerivationFunction
    public int generateBytes(byte[] bArr, int i, int i2) throws IllegalStateException, DataLengthException, IllegalArgumentException {
        int i3 = this.generatedBytes;
        int i4 = i3 + i2;
        if (i4 < 0 || i4 >= this.maxSizeExcl) {
            throw new DataLengthException("Current KDFCTR may only be used for " + this.maxSizeExcl + " bytes");
        }
        if (i3 % this.h == 0) {
            generateNext();
        }
        int i5 = this.generatedBytes;
        int i6 = this.h;
        int i7 = i5 % i6;
        int iMin = Math.min(i6 - (i5 % i6), i2);
        System.arraycopy(this.k, i7, bArr, i, iMin);
        this.generatedBytes += iMin;
        int i8 = i2 - iMin;
        while (true) {
            i += iMin;
            if (i8 <= 0) {
                return i2;
            }
            generateNext();
            iMin = Math.min(this.h, i8);
            System.arraycopy(this.k, 0, bArr, i, iMin);
            this.generatedBytes += iMin;
            i8 -= iMin;
        }
    }

    private void generateNext() throws IllegalStateException, DataLengthException {
        if (this.generatedBytes == 0) {
            Mac mac = this.prf;
            byte[] bArr = this.fixedInputData;
            mac.update(bArr, 0, bArr.length);
            this.prf.doFinal(this.f388a, 0);
        } else {
            Mac mac2 = this.prf;
            byte[] bArr2 = this.f388a;
            mac2.update(bArr2, 0, bArr2.length);
            this.prf.doFinal(this.f388a, 0);
        }
        Mac mac3 = this.prf;
        byte[] bArr3 = this.f388a;
        mac3.update(bArr3, 0, bArr3.length);
        if (this.useCounter) {
            int i = (this.generatedBytes / this.h) + 1;
            byte[] bArr4 = this.ios;
            int length = bArr4.length;
            if (length != 1) {
                if (length != 2) {
                    if (length != 3) {
                        if (length == 4) {
                            bArr4[0] = (byte) (i >>> 24);
                        } else {
                            throw new IllegalStateException("Unsupported size of counter i");
                        }
                    }
                    bArr4[bArr4.length - 3] = (byte) (i >>> 16);
                }
                bArr4[bArr4.length - 2] = (byte) (i >>> 8);
            }
            bArr4[bArr4.length - 1] = (byte) i;
            this.prf.update(bArr4, 0, bArr4.length);
        }
        Mac mac4 = this.prf;
        byte[] bArr5 = this.fixedInputData;
        mac4.update(bArr5, 0, bArr5.length);
        this.prf.doFinal(this.k, 0);
    }
}
