package org.bouncycastle.crypto.digests;

import java.io.ByteArrayOutputStream;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.Xof;

/* loaded from: classes4.dex */
public class AsconXof implements Xof {
    private final int ASCON_PB_ROUNDS;
    private final String algorithmName;
    AsconParameters asconParameters;
    private long x0;
    private long x1;
    private long x2;
    private long x3;
    private long x4;
    private final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    private final int CRYPTO_BYTES = 32;

    /* renamed from: org.bouncycastle.crypto.digests.AsconXof$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$bouncycastle$crypto$digests$AsconXof$AsconParameters;

        static {
            int[] iArr = new int[AsconParameters.values().length];
            $SwitchMap$org$bouncycastle$crypto$digests$AsconXof$AsconParameters = iArr;
            try {
                iArr[AsconParameters.AsconXof.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$bouncycastle$crypto$digests$AsconXof$AsconParameters[AsconParameters.AsconXofA.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public enum AsconParameters {
        AsconXof,
        AsconXofA
    }

    public AsconXof(AsconParameters asconParameters) {
        String str;
        this.asconParameters = asconParameters;
        int i = AnonymousClass1.$SwitchMap$org$bouncycastle$crypto$digests$AsconXof$AsconParameters[asconParameters.ordinal()];
        if (i == 1) {
            this.ASCON_PB_ROUNDS = 12;
            str = "Ascon-Xof";
        } else {
            if (i != 2) {
                throw new IllegalArgumentException("Invalid parameter settings for Ascon Hash");
            }
            this.ASCON_PB_ROUNDS = 8;
            str = "Ascon-XofA";
        }
        this.algorithmName = str;
        reset();
    }

    private long LOADBYTES(byte[] bArr, int i, int i2) {
        long j = 0;
        for (int i3 = 0; i3 < i2; i3++) {
            j |= (bArr[i3 + i] & 255) << ((7 - i3) << 3);
        }
        return j;
    }

    private void P(int i) {
        if (i == 12) {
            ROUND(240L);
            ROUND(225L);
            ROUND(210L);
            ROUND(195L);
        }
        if (i >= 8) {
            ROUND(180L);
            ROUND(165L);
        }
        ROUND(150L);
        ROUND(135L);
        ROUND(120L);
        ROUND(105L);
        ROUND(90L);
        ROUND(75L);
    }

    private long PAD(int i) {
        return 128 << (56 - (i << 3));
    }

    private long ROR(long j, int i) {
        return (j << (64 - i)) | (j >>> i);
    }

    private void ROUND(long j) {
        long j2 = this.x0;
        long j3 = this.x1;
        long j4 = this.x2;
        long j5 = this.x3;
        long j6 = this.x4;
        long j7 = ((((j2 ^ j3) ^ j4) ^ j5) ^ j) ^ ((((j2 ^ j4) ^ j6) ^ j) & j3);
        long j8 = ((((j2 ^ j4) ^ j5) ^ j6) ^ j) ^ (((j3 ^ j4) ^ j) & (j3 ^ j5));
        long j9 = (((j3 ^ j4) ^ j6) ^ j) ^ (j5 & j6);
        long j10 = ((j4 ^ (j2 ^ j3)) ^ j) ^ ((~j2) & (j5 ^ j6));
        long j11 = ((j2 ^ j6) & j3) ^ ((j5 ^ j3) ^ j6);
        this.x0 = (ROR(j7, 19) ^ j7) ^ ROR(j7, 28);
        this.x1 = ROR(j8, 61) ^ (ROR(j8, 39) ^ j8);
        this.x2 = ~(ROR(j9, 6) ^ (ROR(j9, 1) ^ j9));
        this.x3 = (ROR(j10, 10) ^ j10) ^ ROR(j10, 17);
        this.x4 = ROR(j11, 41) ^ (ROR(j11, 7) ^ j11);
    }

    private void STOREBYTES(byte[] bArr, int i, long j, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            bArr[i3 + i] = (byte) (j >>> ((7 - i3) << 3));
        }
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        return doOutput(bArr, i, getDigestSize());
    }

    @Override // org.bouncycastle.crypto.Xof
    public int doFinal(byte[] bArr, int i, int i2) {
        return doOutput(bArr, i, i2);
    }

    @Override // org.bouncycastle.crypto.Xof
    public int doOutput(byte[] bArr, int i, int i2) {
        long j;
        if (i + 32 > bArr.length) {
            throw new OutputLengthException("output buffer is too short");
        }
        byte[] byteArray = this.buffer.toByteArray();
        int size = this.buffer.size();
        int i3 = 0;
        while (true) {
            j = this.x0;
            if (size < 8) {
                break;
            }
            this.x0 = j ^ LOADBYTES(byteArray, i3, 8);
            P(this.ASCON_PB_ROUNDS);
            i3 += 8;
            size -= 8;
        }
        long jLOADBYTES = j ^ LOADBYTES(byteArray, i3, size);
        this.x0 = jLOADBYTES;
        this.x0 = PAD(size) ^ jLOADBYTES;
        P(12);
        int i4 = 32;
        while (true) {
            long j2 = this.x0;
            if (i4 <= 8) {
                STOREBYTES(bArr, i, j2, i4);
                reset();
                return 32;
            }
            STOREBYTES(bArr, i, j2, 8);
            P(this.ASCON_PB_ROUNDS);
            i += 8;
            i4 -= 8;
        }
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return this.algorithmName;
    }

    @Override // org.bouncycastle.crypto.ExtendedDigest
    public int getByteLength() {
        return 8;
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return 32;
    }

    @Override // org.bouncycastle.crypto.Digest
    public void reset() {
        long j;
        this.buffer.reset();
        int i = AnonymousClass1.$SwitchMap$org$bouncycastle$crypto$digests$AsconXof$AsconParameters[this.asconParameters.ordinal()];
        if (i == 1) {
            this.x0 = -5368810569253202922L;
            this.x1 = 3121280575360345120L;
            this.x2 = 7395939140700676632L;
            this.x3 = 6533890155656471820L;
            j = 5710016986865767350L;
        } else {
            if (i != 2) {
                return;
            }
            this.x0 = 4940560291654768690L;
            this.x1 = -3635129828240960206L;
            this.x2 = -597534922722107095L;
            this.x3 = 2623493988082852443L;
            j = -6283826724160825537L;
        }
        this.x4 = j;
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte b) {
        this.buffer.write(b);
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte[] bArr, int i, int i2) {
        if (i + i2 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        this.buffer.write(bArr, i, i2);
    }
}
