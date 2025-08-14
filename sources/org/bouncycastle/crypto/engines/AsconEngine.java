package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.constraints.DefaultServiceProperties;
import org.bouncycastle.crypto.modes.AEADCipher;
import org.bouncycastle.crypto.params.AEADParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Longs;
import org.bouncycastle.util.Pack;

/* loaded from: classes4.dex */
public class AsconEngine implements AEADCipher {
    private final int ASCON_AEAD_RATE;
    private final long ASCON_IV;
    private final int CRYPTO_ABYTES;
    private final int CRYPTO_KEYBYTES;
    private long K0;
    private long K1;
    private long K2;
    private long N0;
    private long N1;
    private final String algorithmName;
    private final AsconParameters asconParameters;
    private byte[] initialAssociatedText;
    private final byte[] m_buf;
    private final int m_bufferSizeDecrypt;
    private byte[] mac;
    private final int nr;
    private long x0;
    private long x1;
    private long x2;
    private long x3;
    private long x4;
    private State m_state = State.Uninitialized;
    private int m_bufPos = 0;

    /* renamed from: org.bouncycastle.crypto.engines.AsconEngine$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$bouncycastle$crypto$engines$AsconEngine$AsconParameters;
        static final /* synthetic */ int[] $SwitchMap$org$bouncycastle$crypto$engines$AsconEngine$State;

        static {
            int[] iArr = new int[State.values().length];
            $SwitchMap$org$bouncycastle$crypto$engines$AsconEngine$State = iArr;
            try {
                iArr[State.DecInit.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$bouncycastle$crypto$engines$AsconEngine$State[State.EncInit.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$bouncycastle$crypto$engines$AsconEngine$State[State.DecAad.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$bouncycastle$crypto$engines$AsconEngine$State[State.EncAad.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$org$bouncycastle$crypto$engines$AsconEngine$State[State.EncFinal.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$org$bouncycastle$crypto$engines$AsconEngine$State[State.DecData.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$org$bouncycastle$crypto$engines$AsconEngine$State[State.EncData.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$org$bouncycastle$crypto$engines$AsconEngine$State[State.DecFinal.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            int[] iArr2 = new int[AsconParameters.values().length];
            $SwitchMap$org$bouncycastle$crypto$engines$AsconEngine$AsconParameters = iArr2;
            try {
                iArr2[AsconParameters.ascon80pq.ordinal()] = 1;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$org$bouncycastle$crypto$engines$AsconEngine$AsconParameters[AsconParameters.ascon128a.ordinal()] = 2;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$org$bouncycastle$crypto$engines$AsconEngine$AsconParameters[AsconParameters.ascon128.ordinal()] = 3;
            } catch (NoSuchFieldError unused11) {
            }
        }
    }

    public enum AsconParameters {
        ascon80pq,
        ascon128a,
        ascon128
    }

    private enum State {
        Uninitialized,
        EncInit,
        EncAad,
        EncData,
        EncFinal,
        DecInit,
        DecAad,
        DecData,
        DecFinal
    }

    public AsconEngine(AsconParameters asconParameters) {
        String str;
        this.asconParameters = asconParameters;
        int i = AnonymousClass1.$SwitchMap$org$bouncycastle$crypto$engines$AsconEngine$AsconParameters[asconParameters.ordinal()];
        if (i == 1) {
            this.CRYPTO_KEYBYTES = 20;
            this.CRYPTO_ABYTES = 16;
            this.ASCON_AEAD_RATE = 8;
            this.ASCON_IV = -6899501409222262784L;
            str = "Ascon-80pq AEAD";
        } else if (i == 2) {
            this.CRYPTO_KEYBYTES = 16;
            this.CRYPTO_ABYTES = 16;
            this.ASCON_AEAD_RATE = 16;
            this.ASCON_IV = -9187330011336540160L;
            str = "Ascon-128a AEAD";
        } else {
            if (i != 3) {
                throw new IllegalArgumentException("invalid parameter setting for ASCON AEAD");
            }
            this.CRYPTO_KEYBYTES = 16;
            this.CRYPTO_ABYTES = 16;
            this.ASCON_AEAD_RATE = 8;
            this.ASCON_IV = -9205344418435956736L;
            str = "Ascon-128 AEAD";
        }
        this.algorithmName = str;
        int i2 = this.ASCON_AEAD_RATE;
        this.nr = i2 == 8 ? 6 : 8;
        int i3 = i2 + this.CRYPTO_ABYTES;
        this.m_bufferSizeDecrypt = i3;
        this.m_buf = new byte[i3];
    }

    private void P(int i) {
        if (i >= 8) {
            if (i == 12) {
                ROUND(240L);
                ROUND(225L);
                ROUND(210L);
                ROUND(195L);
            }
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
        this.x0 = (Longs.rotateRight(j7, 19) ^ j7) ^ Longs.rotateRight(j7, 28);
        this.x1 = Longs.rotateRight(j8, 61) ^ (Longs.rotateRight(j8, 39) ^ j8);
        this.x2 = ~(Longs.rotateRight(j9, 6) ^ (Longs.rotateRight(j9, 1) ^ j9));
        this.x3 = (Longs.rotateRight(j10, 10) ^ j10) ^ Longs.rotateRight(j10, 17);
        this.x4 = Longs.rotateRight(j11, 41) ^ (Longs.rotateRight(j11, 7) ^ j11);
    }

    private void ascon_aeadinit() {
        long j = this.ASCON_IV;
        this.x0 = j;
        if (this.CRYPTO_KEYBYTES == 20) {
            this.x0 = j ^ this.K0;
        }
        this.x1 = this.K1;
        this.x2 = this.K2;
        this.x3 = this.N0;
        this.x4 = this.N1;
        P(12);
        if (this.CRYPTO_KEYBYTES == 20) {
            this.x2 ^= this.K0;
        }
        this.x3 ^= this.K1;
        this.x4 ^= this.K2;
    }

    private void checkAAD() {
        State state;
        int i = AnonymousClass1.$SwitchMap$org$bouncycastle$crypto$engines$AsconEngine$State[this.m_state.ordinal()];
        if (i == 1) {
            state = State.DecAad;
        } else {
            if (i != 2) {
                if (i == 3 || i == 4) {
                    return;
                }
                if (i == 5) {
                    throw new IllegalStateException(getAlgorithmName() + " cannot be reused for encryption");
                }
                throw new IllegalStateException(getAlgorithmName() + " needs to be initialized");
            }
            state = State.EncAad;
        }
        this.m_state = state;
    }

    private boolean checkData() {
        switch (AnonymousClass1.$SwitchMap$org$bouncycastle$crypto$engines$AsconEngine$State[this.m_state.ordinal()]) {
            case 1:
            case 3:
                finishAAD(State.DecData);
                return false;
            case 2:
            case 4:
                finishAAD(State.EncData);
                return true;
            case 5:
                throw new IllegalStateException(getAlgorithmName() + " cannot be reused for encryption");
            case 6:
                return false;
            case 7:
                return true;
            default:
                throw new IllegalStateException(getAlgorithmName() + " needs to be initialized");
        }
    }

    private void finishAAD(State state) {
        int i = AnonymousClass1.$SwitchMap$org$bouncycastle$crypto$engines$AsconEngine$State[this.m_state.ordinal()];
        if (i == 3 || i == 4) {
            byte[] bArr = this.m_buf;
            int i2 = this.m_bufPos;
            bArr[i2] = Byte.MIN_VALUE;
            if (i2 >= 8) {
                this.x0 ^= Pack.bigEndianToLong(bArr, 0);
                this.x1 = (((-1) << (56 - ((this.m_bufPos - 8) << 3))) & Pack.bigEndianToLong(this.m_buf, 8)) ^ this.x1;
            } else {
                this.x0 = (((-1) << (56 - (this.m_bufPos << 3))) & Pack.bigEndianToLong(bArr, 0)) ^ this.x0;
            }
            P(this.nr);
        }
        this.x4 ^= 1;
        this.m_bufPos = 0;
        this.m_state = state;
    }

    private void finishData(State state) {
        long j;
        long j2;
        int i = AnonymousClass1.$SwitchMap$org$bouncycastle$crypto$engines$AsconEngine$AsconParameters[this.asconParameters.ordinal()];
        if (i == 1) {
            long j3 = this.x1;
            long j4 = this.K0 << 32;
            long j5 = this.K1;
            this.x1 = j3 ^ (j4 | (j5 >> 32));
            long j6 = this.x2;
            long j7 = j5 << 32;
            long j8 = this.K2;
            this.x2 = j6 ^ (j7 | (j8 >> 32));
            j = this.x3;
            j2 = j8 << 32;
        } else {
            if (i != 2) {
                if (i != 3) {
                    throw new IllegalStateException();
                }
                this.x1 ^= this.K1;
                this.x2 ^= this.K2;
                P(12);
                this.x3 ^= this.K1;
                this.x4 ^= this.K2;
                this.m_state = state;
            }
            this.x2 ^= this.K1;
            j = this.x3;
            j2 = this.K2;
        }
        this.x3 = j ^ j2;
        P(12);
        this.x3 ^= this.K1;
        this.x4 ^= this.K2;
        this.m_state = state;
    }

    private void processBufferAAD(byte[] bArr, int i) {
        this.x0 ^= Pack.bigEndianToLong(bArr, i);
        if (this.ASCON_AEAD_RATE == 16) {
            this.x1 = Pack.bigEndianToLong(bArr, i + 8) ^ this.x1;
        }
        P(this.nr);
    }

    private void processBufferDecrypt(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (this.ASCON_AEAD_RATE + i2 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        long jBigEndianToLong = Pack.bigEndianToLong(bArr, i);
        Pack.longToBigEndian(this.x0 ^ jBigEndianToLong, bArr2, i2);
        this.x0 = jBigEndianToLong;
        if (this.ASCON_AEAD_RATE == 16) {
            long jBigEndianToLong2 = Pack.bigEndianToLong(bArr, i + 8);
            Pack.longToBigEndian(this.x1 ^ jBigEndianToLong2, bArr2, i2 + 8);
            this.x1 = jBigEndianToLong2;
        }
        P(this.nr);
    }

    private void processBufferEncrypt(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (this.ASCON_AEAD_RATE + i2 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        long jBigEndianToLong = this.x0 ^ Pack.bigEndianToLong(bArr, i);
        this.x0 = jBigEndianToLong;
        Pack.longToBigEndian(jBigEndianToLong, bArr2, i2);
        if (this.ASCON_AEAD_RATE == 16) {
            long jBigEndianToLong2 = Pack.bigEndianToLong(bArr, i + 8) ^ this.x1;
            this.x1 = jBigEndianToLong2;
            Pack.longToBigEndian(jBigEndianToLong2, bArr2, i2 + 8);
        }
        P(this.nr);
    }

    private void processFinalDecrypt(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        if (i2 >= 8) {
            long jBigEndianToLong = Pack.bigEndianToLong(bArr, i);
            long j = this.x0 ^ jBigEndianToLong;
            this.x0 = j;
            Pack.longToBigEndian(j, bArr2, i3);
            this.x0 = jBigEndianToLong;
            int i4 = i + 8;
            int i5 = i3 + 8;
            int i6 = i2 - 8;
            this.x1 ^= PAD(i6);
            if (i6 != 0) {
                long jLittleEndianToLong_High = Pack.littleEndianToLong_High(bArr, i4, i6);
                long j2 = this.x1 ^ jLittleEndianToLong_High;
                this.x1 = j2;
                Pack.longToLittleEndian_High(j2, bArr2, i5, i6);
                this.x1 = jLittleEndianToLong_High ^ (this.x1 & ((-1) >>> (i6 << 3)));
            }
        } else {
            this.x0 ^= PAD(i2);
            if (i2 != 0) {
                long jLittleEndianToLong_High2 = Pack.littleEndianToLong_High(bArr, i, i2);
                long j3 = this.x0 ^ jLittleEndianToLong_High2;
                this.x0 = j3;
                Pack.longToLittleEndian_High(j3, bArr2, i3, i2);
                this.x0 = jLittleEndianToLong_High2 ^ (this.x0 & ((-1) >>> (i2 << 3)));
            }
        }
        finishData(State.DecFinal);
    }

    private void processFinalEncrypt(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        long jLittleEndianToLong_High;
        if (i2 >= 8) {
            long jBigEndianToLong = this.x0 ^ Pack.bigEndianToLong(bArr, i);
            this.x0 = jBigEndianToLong;
            Pack.longToBigEndian(jBigEndianToLong, bArr2, i3);
            int i4 = i + 8;
            i3 += 8;
            i2 -= 8;
            long jPAD = this.x1 ^ PAD(i2);
            this.x1 = jPAD;
            if (i2 != 0) {
                jLittleEndianToLong_High = Pack.littleEndianToLong_High(bArr, i4, i2) ^ jPAD;
                this.x1 = jLittleEndianToLong_High;
                Pack.longToLittleEndian_High(jLittleEndianToLong_High, bArr2, i3, i2);
            }
        } else {
            long jPAD2 = this.x0 ^ PAD(i2);
            this.x0 = jPAD2;
            if (i2 != 0) {
                jLittleEndianToLong_High = Pack.littleEndianToLong_High(bArr, i, i2) ^ jPAD2;
                this.x0 = jLittleEndianToLong_High;
                Pack.longToLittleEndian_High(jLittleEndianToLong_High, bArr2, i3, i2);
            }
        }
        finishData(State.EncFinal);
    }

    private void reset(boolean z) {
        if (z) {
            this.mac = null;
        }
        Arrays.clear(this.m_buf);
        this.m_bufPos = 0;
        switch (AnonymousClass1.$SwitchMap$org$bouncycastle$crypto$engines$AsconEngine$State[this.m_state.ordinal()]) {
            case 1:
            case 2:
                break;
            case 3:
            case 6:
            case 8:
                this.m_state = State.DecInit;
                break;
            case 4:
            case 5:
            case 7:
                this.m_state = State.EncFinal;
                return;
            default:
                throw new IllegalStateException(getAlgorithmName() + " needs to be initialized");
        }
        ascon_aeadinit();
        byte[] bArr = this.initialAssociatedText;
        if (bArr != null) {
            processAADBytes(bArr, 0, bArr.length);
        }
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int doFinal(byte[] bArr, int i) throws InvalidCipherTextException, IllegalStateException, DataLengthException {
        int i2;
        if (checkData()) {
            int i3 = this.m_bufPos;
            i2 = this.CRYPTO_ABYTES + i3;
            if (i + i2 > bArr.length) {
                throw new OutputLengthException("output buffer too short");
            }
            processFinalEncrypt(this.m_buf, 0, i3, bArr, i);
            byte[] bArr2 = new byte[this.CRYPTO_ABYTES];
            this.mac = bArr2;
            Pack.longToBigEndian(this.x3, bArr2, 0);
            Pack.longToBigEndian(this.x4, this.mac, 8);
            System.arraycopy(this.mac, 0, bArr, i + this.m_bufPos, this.CRYPTO_ABYTES);
            reset(false);
        } else {
            int i4 = this.m_bufPos;
            int i5 = this.CRYPTO_ABYTES;
            if (i4 < i5) {
                throw new InvalidCipherTextException("data too short");
            }
            i2 = i4 - i5;
            this.m_bufPos = i2;
            if (i + i2 > bArr.length) {
                throw new OutputLengthException("output buffer too short");
            }
            processFinalDecrypt(this.m_buf, 0, i2, bArr, i);
            this.x3 ^= Pack.bigEndianToLong(this.m_buf, this.m_bufPos);
            long jBigEndianToLong = this.x4 ^ Pack.bigEndianToLong(this.m_buf, this.m_bufPos + 8);
            this.x4 = jBigEndianToLong;
            if ((jBigEndianToLong | this.x3) != 0) {
                throw new InvalidCipherTextException("mac check in " + getAlgorithmName() + " failed");
            }
            reset(true);
        }
        return i2;
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public String getAlgorithmName() {
        return this.algorithmName;
    }

    public String getAlgorithmVersion() {
        return "v1.2";
    }

    public int getIVBytesSize() {
        return this.CRYPTO_ABYTES;
    }

    public int getKeyBytesSize() {
        return this.CRYPTO_KEYBYTES;
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public byte[] getMac() {
        return this.mac;
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int getOutputSize(int i) {
        int iMax = Math.max(0, i);
        int i2 = AnonymousClass1.$SwitchMap$org$bouncycastle$crypto$engines$AsconEngine$State[this.m_state.ordinal()];
        if (i2 == 1 || i2 == 3) {
            return Math.max(0, iMax - this.CRYPTO_ABYTES);
        }
        if (i2 != 5) {
            if (i2 != 6) {
                if (i2 != 7) {
                    if (i2 != 8) {
                        return iMax + this.CRYPTO_ABYTES;
                    }
                }
            }
            return Math.max(0, (iMax + this.m_bufPos) - this.CRYPTO_ABYTES);
        }
        return iMax + this.m_bufPos + this.CRYPTO_ABYTES;
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0020, code lost:
    
        if (r1 != 8) goto L18;
     */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0027  */
    @Override // org.bouncycastle.crypto.modes.AEADCipher
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int getUpdateOutputSize(int r4) {
        /*
            r3 = this;
            r0 = 0
            int r4 = java.lang.Math.max(r0, r4)
            int[] r1 = org.bouncycastle.crypto.engines.AsconEngine.AnonymousClass1.$SwitchMap$org$bouncycastle$crypto$engines$AsconEngine$State
            org.bouncycastle.crypto.engines.AsconEngine$State r2 = r3.m_state
            int r2 = r2.ordinal()
            r1 = r1[r2]
            r2 = 1
            if (r1 == r2) goto L2b
            r2 = 3
            if (r1 == r2) goto L2b
            r2 = 5
            if (r1 == r2) goto L27
            r2 = 6
            if (r1 == r2) goto L23
            r2 = 7
            if (r1 == r2) goto L27
            r2 = 8
            if (r1 == r2) goto L23
            goto L32
        L23:
            int r1 = r3.m_bufPos
            int r4 = r4 + r1
            goto L2b
        L27:
            int r0 = r3.m_bufPos
            int r4 = r4 + r0
            goto L32
        L2b:
            int r1 = r3.CRYPTO_ABYTES
            int r4 = r4 - r1
            int r4 = java.lang.Math.max(r0, r4)
        L32:
            int r0 = r3.ASCON_AEAD_RATE
            int r0 = r4 % r0
            int r4 = r4 - r0
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.crypto.engines.AsconEngine.getUpdateOutputSize(int):int");
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        KeyParameter key;
        byte[] iv;
        long jBigEndianToLong;
        if (cipherParameters instanceof AEADParameters) {
            AEADParameters aEADParameters = (AEADParameters) cipherParameters;
            key = aEADParameters.getKey();
            iv = aEADParameters.getNonce();
            this.initialAssociatedText = aEADParameters.getAssociatedText();
            int macSize = aEADParameters.getMacSize();
            if (macSize != this.CRYPTO_ABYTES * 8) {
                throw new IllegalArgumentException("Invalid value for MAC size: " + macSize);
            }
        } else {
            if (!(cipherParameters instanceof ParametersWithIV)) {
                throw new IllegalArgumentException("invalid parameters passed to Ascon");
            }
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            key = (KeyParameter) parametersWithIV.getParameters();
            iv = parametersWithIV.getIV();
            this.initialAssociatedText = null;
        }
        if (key == null) {
            throw new IllegalArgumentException("Ascon Init parameters must include a key");
        }
        if (iv == null || iv.length != this.CRYPTO_ABYTES) {
            throw new IllegalArgumentException(this.asconParameters + " requires exactly " + this.CRYPTO_ABYTES + " bytes of IV");
        }
        byte[] key2 = key.getKey();
        if (key2.length != this.CRYPTO_KEYBYTES) {
            throw new IllegalArgumentException(this.asconParameters + " key must be " + this.CRYPTO_KEYBYTES + " bytes long");
        }
        CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties(getAlgorithmName(), 128, cipherParameters, Utils.getPurpose(z)));
        this.N0 = Pack.bigEndianToLong(iv, 0);
        this.N1 = Pack.bigEndianToLong(iv, 8);
        int i = this.CRYPTO_KEYBYTES;
        if (i == 16) {
            this.K1 = Pack.bigEndianToLong(key2, 0);
            jBigEndianToLong = Pack.bigEndianToLong(key2, 8);
        } else {
            if (i != 20) {
                throw new IllegalStateException();
            }
            this.K0 = Pack.bigEndianToInt(key2, 0);
            this.K1 = Pack.bigEndianToLong(key2, 4);
            jBigEndianToLong = Pack.bigEndianToLong(key2, 12);
        }
        this.K2 = jBigEndianToLong;
        this.m_state = z ? State.EncInit : State.DecInit;
        reset(true);
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public void processAADByte(byte b) {
        checkAAD();
        byte[] bArr = this.m_buf;
        int i = this.m_bufPos;
        bArr[i] = b;
        int i2 = i + 1;
        this.m_bufPos = i2;
        if (i2 == this.ASCON_AEAD_RATE) {
            processBufferAAD(bArr, 0);
        }
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public void processAADBytes(byte[] bArr, int i, int i2) {
        if (i + i2 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i2 <= 0) {
            return;
        }
        checkAAD();
        int i3 = this.m_bufPos;
        if (i3 > 0) {
            int i4 = this.ASCON_AEAD_RATE - i3;
            if (i2 < i4) {
                System.arraycopy(bArr, i, this.m_buf, i3, i2);
                this.m_bufPos += i2;
                return;
            } else {
                System.arraycopy(bArr, i, this.m_buf, i3, i4);
                i += i4;
                i2 -= i4;
                processBufferAAD(this.m_buf, 0);
            }
        }
        while (i2 >= this.ASCON_AEAD_RATE) {
            processBufferAAD(bArr, i);
            int i5 = this.ASCON_AEAD_RATE;
            i += i5;
            i2 -= i5;
        }
        System.arraycopy(bArr, i, this.m_buf, 0, i2);
        this.m_bufPos = i2;
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int processByte(byte b, byte[] bArr, int i) throws DataLengthException {
        return processBytes(new byte[]{b}, 0, 1, bArr, i);
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws DataLengthException {
        int i4;
        if (i + i2 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (checkData()) {
            int i5 = this.m_bufPos;
            if (i5 > 0) {
                int i6 = this.ASCON_AEAD_RATE - i5;
                if (i2 < i6) {
                    System.arraycopy(bArr, i, this.m_buf, i5, i2);
                    this.m_bufPos += i2;
                    return 0;
                }
                System.arraycopy(bArr, i, this.m_buf, i5, i6);
                i += i6;
                i2 -= i6;
                processBufferEncrypt(this.m_buf, 0, bArr2, i3);
                i4 = this.ASCON_AEAD_RATE;
            } else {
                i4 = 0;
            }
            while (i2 >= this.ASCON_AEAD_RATE) {
                processBufferEncrypt(bArr, i, bArr2, i3 + i4);
                int i7 = this.ASCON_AEAD_RATE;
                i += i7;
                i2 -= i7;
                i4 += i7;
            }
            System.arraycopy(bArr, i, this.m_buf, 0, i2);
            this.m_bufPos = i2;
            return i4;
        }
        int i8 = this.m_bufferSizeDecrypt;
        int i9 = this.m_bufPos;
        int i10 = i8 - i9;
        if (i2 < i10) {
            System.arraycopy(bArr, i, this.m_buf, i9, i2);
            this.m_bufPos += i2;
            return 0;
        }
        int i11 = 0;
        do {
            int i12 = this.m_bufPos;
            int i13 = this.ASCON_AEAD_RATE;
            if (i12 < i13) {
                int i14 = i13 - i12;
                System.arraycopy(bArr, i, this.m_buf, i12, i14);
                i += i14;
                i2 -= i14;
                processBufferDecrypt(this.m_buf, 0, bArr2, i3 + i11);
                i4 = i11 + this.ASCON_AEAD_RATE;
                while (i2 >= this.m_bufferSizeDecrypt) {
                    processBufferDecrypt(bArr, i, bArr2, i3 + i4);
                    int i15 = this.ASCON_AEAD_RATE;
                    i += i15;
                    i2 -= i15;
                    i4 += i15;
                }
                System.arraycopy(bArr, i, this.m_buf, 0, i2);
                this.m_bufPos = i2;
                return i4;
            }
            processBufferDecrypt(this.m_buf, 0, bArr2, i3 + i11);
            int i16 = this.m_bufPos;
            int i17 = this.ASCON_AEAD_RATE;
            int i18 = i16 - i17;
            this.m_bufPos = i18;
            byte[] bArr3 = this.m_buf;
            System.arraycopy(bArr3, i17, bArr3, 0, i18);
            int i19 = this.ASCON_AEAD_RATE;
            i11 += i19;
            i10 += i19;
        } while (i2 >= i10);
        System.arraycopy(bArr, i, this.m_buf, this.m_bufPos, i2);
        this.m_bufPos += i2;
        return i11;
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public void reset() {
        reset(true);
    }
}
