package org.bouncycastle.crypto.engines;

import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import kotlin.io.encoding.Base64;
import net.sf.scuba.smartcards.ISO7816;
import net.sf.scuba.smartcards.ISOFileInfo;
import okio.Utf8;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.constraints.DefaultServiceProperties;
import org.bouncycastle.crypto.modes.AEADCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.jmrtd.lds.CVCAFile;

/* loaded from: classes4.dex */
public class ElephantEngine implements AEADCipher {
    private final int BLOCK_SIZE;
    private final byte CRYPTO_ABYTES;
    private byte[] ad;
    private int adOff;
    private int adlen;
    private final String algorithmName;
    private final byte[] buffer;
    private byte[] current_mask;
    private byte[] expanded_key;
    private boolean forEncryption;
    private boolean initialised;
    private byte[] inputMessage;
    private int inputOff;
    private byte lfsrIV;
    private int nBits;
    private final int nRounds;
    private int nSBox;
    private int nb_its;
    private byte[] next_mask;
    private byte[] npub;
    private final byte[] outputMessage;
    private final ElephantParameters parameters;
    private byte[] previous_mask;
    private final byte[] previous_outputMessage;
    private byte[] tag;
    private final byte[] tag_buffer;
    private final byte CRYPTO_KEYBYTES = 16;
    private final byte CRYPTO_NPUBBYTES = 12;
    private State m_state = State.Uninitialized;
    private final ByteArrayOutputStream aadData = new ByteArrayOutputStream();
    private final byte[] sBoxLayer = {-18, -19, -21, ISO7816.INS_CREATE_FILE, ISO7816.INS_APPEND_RECORD, -31, ISO7816.INS_DELETE_FILE, -17, -25, -22, -24, -27, -23, -20, -29, -26, -34, -35, -37, ISO7816.INS_WRITE_BINARY, ISO7816.INS_WRITE_RECORD, -47, -44, -33, -41, ISO7816.INS_PUT_DATA, ISO7816.INS_LOAD_KEY_FILE, -43, -39, ISO7816.INS_UPDATE_RECORD, -45, ISO7816.INS_UPDATE_BINARY, -66, -67, -69, ISO7816.INS_READ_BINARY, -78, ISO7816.INS_READ_BINARY2, ISO7816.INS_READ_BINARY_STAMPED, -65, -73, -70, -72, -75, -71, -68, ISO7816.INS_READ_RECORD2, ISO7816.INS_READ_RECORD_STAMPED, 14, 13, 11, 0, 2, 1, 4, 15, 7, 10, 8, 5, 9, 12, 3, 6, 46, 45, 43, 32, ISO7816.INS_MSE, 33, ISO7816.INS_CHANGE_CHV, 47, 39, ISO7816.INS_PSO, 40, 37, 41, ISO7816.INS_UNBLOCK_CHV, 35, 38, 30, 29, Ascii.ESC, 16, Ascii.DC2, 17, Ascii.DC4, Ascii.US, Ascii.ETB, Ascii.SUB, Ascii.CAN, Ascii.NAK, Ascii.EM, 28, 19, Ascii.SYN, 78, 77, 75, SignedBytes.MAX_POWER_OF_TWO, CVCAFile.CAR_TAG, 65, ISO7816.INS_REHABILITATE_CHV, 79, 71, 74, 72, 69, 73, 76, 67, 70, -2, -3, -5, -16, -14, -15, -12, -1, -9, -6, -8, -11, -7, -4, -13, -10, 126, 125, 123, ISO7816.INS_MANAGE_CHANNEL, 114, 113, 116, Byte.MAX_VALUE, 119, 122, 120, 117, 121, 124, 115, 118, -82, -83, ISOFileInfo.AB, ISOFileInfo.A0, -94, ISOFileInfo.A1, -92, -81, -89, -86, -88, ISOFileInfo.A5, -87, -84, -93, -90, ISOFileInfo.CHANNEL_SECURITY, ISOFileInfo.ENV_TEMP_EF, ISOFileInfo.SECURITY_ATTR_EXP, Byte.MIN_VALUE, -126, ISOFileInfo.DATA_BYTES2, -124, -113, ISOFileInfo.FCI_EXT, ISOFileInfo.LCS_BYTE, -120, ISOFileInfo.PROP_INFO, -119, ISOFileInfo.SECURITY_ATTR_COMPACT, ISOFileInfo.FILE_IDENTIFIER, -122, 94, 93, 91, 80, 82, 81, 84, 95, 87, 90, 88, 85, 89, 92, 83, 86, -98, -99, -101, -112, -110, -111, -108, -97, -105, -102, -104, -107, -103, -100, -109, -106, -50, -51, -53, ISO7816.INS_GET_RESPONSE, ISO7816.INS_ENVELOPE, -63, -60, -49, -57, ISO7816.INS_GET_DATA, -56, -59, -55, -52, -61, -58, 62, Base64.padSymbol, 59, ISO7816.INS_DECREASE, ISO7816.INS_INCREASE, 49, ISO7816.INS_DECREASE_STAMPED, Utf8.REPLACEMENT_BYTE, 55, 58, 56, 53, 57, 60, 51, 54, 110, 109, 107, 96, ISOFileInfo.FCP_BYTE, 97, ISOFileInfo.FMD_BYTE, ISOFileInfo.FCI_BYTE, 103, 106, 104, 101, 105, 108, 99, 102};
    private final byte[] KeccakRoundConstants = {1, -126, ISOFileInfo.LCS_BYTE, 0, ISOFileInfo.SECURITY_ATTR_EXP, 1, ISOFileInfo.DATA_BYTES2, 9, ISOFileInfo.LCS_BYTE, -120, 9, 10, ISOFileInfo.SECURITY_ATTR_EXP, ISOFileInfo.SECURITY_ATTR_EXP, -119, 3, 2, Byte.MIN_VALUE};
    private final int[] KeccakRhoOffsets = {0, 1, 6, 4, 3, 4, 4, 6, 7, 4, 3, 2, 3, 1, 7, 1, 5, 7, 5, 0, 2, 2, 5, 0, 6};

    /* renamed from: org.bouncycastle.crypto.engines.ElephantEngine$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$bouncycastle$crypto$engines$ElephantEngine$ElephantParameters;
        static final /* synthetic */ int[] $SwitchMap$org$bouncycastle$crypto$engines$ElephantEngine$State;

        static {
            int[] iArr = new int[State.values().length];
            $SwitchMap$org$bouncycastle$crypto$engines$ElephantEngine$State = iArr;
            try {
                iArr[State.EncInit.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$bouncycastle$crypto$engines$ElephantEngine$State[State.DecInit.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$bouncycastle$crypto$engines$ElephantEngine$State[State.Uninitialized.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$bouncycastle$crypto$engines$ElephantEngine$State[State.DecFinal.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$org$bouncycastle$crypto$engines$ElephantEngine$State[State.EncFinal.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$org$bouncycastle$crypto$engines$ElephantEngine$State[State.EncAad.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$org$bouncycastle$crypto$engines$ElephantEngine$State[State.EncData.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$org$bouncycastle$crypto$engines$ElephantEngine$State[State.DecData.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$org$bouncycastle$crypto$engines$ElephantEngine$State[State.DecAad.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            int[] iArr2 = new int[ElephantParameters.values().length];
            $SwitchMap$org$bouncycastle$crypto$engines$ElephantEngine$ElephantParameters = iArr2;
            try {
                iArr2[ElephantParameters.elephant160.ordinal()] = 1;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$org$bouncycastle$crypto$engines$ElephantEngine$ElephantParameters[ElephantParameters.elephant176.ordinal()] = 2;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$org$bouncycastle$crypto$engines$ElephantEngine$ElephantParameters[ElephantParameters.elephant200.ordinal()] = 3;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    public enum ElephantParameters {
        elephant160,
        elephant176,
        elephant200
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

    public ElephantEngine(ElephantParameters elephantParameters) {
        String str;
        int i = AnonymousClass1.$SwitchMap$org$bouncycastle$crypto$engines$ElephantEngine$ElephantParameters[elephantParameters.ordinal()];
        if (i == 1) {
            this.BLOCK_SIZE = 20;
            this.nBits = 160;
            this.nSBox = 20;
            this.nRounds = 80;
            this.lfsrIV = (byte) 117;
            this.CRYPTO_ABYTES = (byte) 8;
            str = "Elephant 160 AEAD";
        } else if (i == 2) {
            this.BLOCK_SIZE = 22;
            this.nBits = 176;
            this.nSBox = 22;
            this.nRounds = 90;
            this.lfsrIV = (byte) 69;
            this.CRYPTO_ABYTES = (byte) 8;
            str = "Elephant 176 AEAD";
        } else {
            if (i != 3) {
                throw new IllegalArgumentException("Invalid parameter settings for Elephant");
            }
            this.BLOCK_SIZE = 25;
            this.nRounds = 18;
            this.CRYPTO_ABYTES = (byte) 16;
            str = "Elephant 200 AEAD";
        }
        this.algorithmName = str;
        this.parameters = elephantParameters;
        int i2 = this.BLOCK_SIZE;
        this.tag_buffer = new byte[i2];
        this.previous_mask = new byte[i2];
        this.current_mask = new byte[i2];
        this.next_mask = new byte[i2];
        this.buffer = new byte[i2];
        this.previous_outputMessage = new byte[i2];
        this.outputMessage = new byte[i2];
        this.initialised = false;
        reset(false);
    }

    private void KeccakP200Round(byte[] bArr, int i) {
        byte[] bArr2 = new byte[25];
        for (int i2 = 0; i2 < 5; i2++) {
            for (int i3 = 0; i3 < 5; i3++) {
                bArr2[i2] = (byte) (bArr2[i2] ^ bArr[index(i2, i3)]);
            }
        }
        int i4 = 0;
        while (i4 < 5) {
            int i5 = i4 + 1;
            bArr2[i4 + 5] = (byte) (bArr2[(i4 + 4) % 5] ^ ROL8(bArr2[i5 % 5], 1));
            i4 = i5;
        }
        for (int i6 = 0; i6 < 5; i6++) {
            for (int i7 = 0; i7 < 5; i7++) {
                int iIndex = index(i6, i7);
                bArr[iIndex] = (byte) (bArr[iIndex] ^ bArr2[i6 + 5]);
            }
        }
        for (int i8 = 0; i8 < 5; i8++) {
            for (int i9 = 0; i9 < 5; i9++) {
                bArr2[index(i8, i9)] = ROL8(bArr[index(i8, i9)], this.KeccakRhoOffsets[index(i8, i9)]);
            }
        }
        for (int i10 = 0; i10 < 5; i10++) {
            for (int i11 = 0; i11 < 5; i11++) {
                bArr[index(i11, ((i10 * 2) + (i11 * 3)) % 5)] = bArr2[index(i10, i11)];
            }
        }
        for (int i12 = 0; i12 < 5; i12++) {
            int i13 = 0;
            while (i13 < 5) {
                int i14 = i13 + 1;
                bArr2[i13] = (byte) (bArr[index(i13, i12)] ^ ((~bArr[index(i14 % 5, i12)]) & bArr[index((i13 + 2) % 5, i12)]));
                i13 = i14;
            }
            for (int i15 = 0; i15 < 5; i15++) {
                bArr[index(i15, i12)] = bArr2[i15];
            }
        }
        bArr[0] = (byte) (this.KeccakRoundConstants[i] ^ bArr[0]);
    }

    private byte ROL8(byte b, int i) {
        int i2 = b;
        if (i != 0) {
            int i3 = b & 255;
            i2 = (i3 >>> (8 - i)) ^ (i3 << i);
        }
        return (byte) i2;
    }

    private void checkAad() {
        int i = AnonymousClass1.$SwitchMap$org$bouncycastle$crypto$engines$ElephantEngine$State[this.m_state.ordinal()];
        if (i == 5) {
            throw new IllegalArgumentException(this.algorithmName + " cannot be reused for encryption");
        }
        if (i == 7) {
            throw new IllegalArgumentException(this.algorithmName + " cannot process AAD when the length of the ciphertext to be processed exceeds the a block size");
        }
        if (i == 8) {
            throw new IllegalArgumentException(this.algorithmName + " cannot process AAD when the length of the plaintext to be processed exceeds the a block size");
        }
    }

    private void get_c_block(byte[] bArr, byte[] bArr2, int i, int i2, int i3) {
        int i4 = this.BLOCK_SIZE;
        int i5 = i3 * i4;
        if (i5 == i2) {
            Arrays.fill(bArr, 0, i4, (byte) 0);
            bArr[0] = 1;
            return;
        }
        int i6 = i2 - i5;
        if (i4 <= i6) {
            System.arraycopy(bArr2, i, bArr, 0, i4);
            return;
        }
        if (i6 > 0) {
            System.arraycopy(bArr2, i, bArr, 0, i6);
        }
        Arrays.fill(bArr, i6, this.BLOCK_SIZE, (byte) 0);
        bArr[i6] = 1;
    }

    private int index(int i, int i2) {
        return i + (i2 * 5);
    }

    private void lfsr_step(byte[] bArr, byte[] bArr2) {
        int i = AnonymousClass1.$SwitchMap$org$bouncycastle$crypto$engines$ElephantEngine$ElephantParameters[this.parameters.ordinal()];
        if (i == 1) {
            int i2 = this.BLOCK_SIZE - 1;
            byte b = bArr2[0];
            int i3 = (bArr2[3] & 255) << 7;
            int i4 = (bArr2[13] & 255) >>> 7;
            bArr[i2] = (byte) (i4 ^ (i3 ^ (((b & 255) >>> 5) | ((b & 255) << 3))));
        } else if (i == 2) {
            bArr[this.BLOCK_SIZE - 1] = (byte) ((rotl(bArr2[0]) ^ ((bArr2[3] & 255) << 7)) ^ ((bArr2[19] & 255) >>> 7));
        } else if (i == 3) {
            bArr[this.BLOCK_SIZE - 1] = (byte) ((bArr2[13] << 1) ^ (rotl(bArr2[0]) ^ rotl(bArr2[2])));
        }
        System.arraycopy(bArr2, 1, bArr, 0, this.BLOCK_SIZE - 1);
    }

    private void permutation(byte[] bArr) {
        int i;
        int i2 = AnonymousClass1.$SwitchMap$org$bouncycastle$crypto$engines$ElephantEngine$ElephantParameters[this.parameters.ordinal()];
        if (i2 != 1 && i2 != 2) {
            if (i2 != 3) {
                return;
            }
            for (int i3 = 0; i3 < this.nRounds; i3++) {
                KeccakP200Round(bArr, i3);
            }
            return;
        }
        byte b = this.lfsrIV;
        byte[] bArr2 = new byte[this.nSBox];
        for (int i4 = 0; i4 < this.nRounds; i4++) {
            bArr[0] = (byte) (bArr[0] ^ b);
            int i5 = this.nSBox - 1;
            int i6 = b & 32;
            int i7 = b & SignedBytes.MAX_POWER_OF_TWO;
            bArr[i5] = (byte) (bArr[i5] ^ ((byte) (((((((((b & 1) << 7) | ((b & 2) << 5)) | ((b & 4) << 3)) | ((b & 8) << 1)) | ((b & 16) >>> 1)) | (i6 >>> 3)) | (i7 >>> 5)) | ((b & 128) >>> 7))));
            b = (byte) (((b << 1) | ((i7 >>> 6) ^ (i6 >>> 5))) & 127);
            for (int i8 = 0; i8 < this.nSBox; i8++) {
                bArr[i8] = this.sBoxLayer[bArr[i8] & 255];
            }
            Arrays.fill(bArr2, (byte) 0);
            int i9 = 0;
            while (true) {
                i = this.nSBox;
                if (i9 < i) {
                    for (int i10 = 0; i10 < 8; i10++) {
                        int i11 = (i9 << 3) + i10;
                        int i12 = this.nBits;
                        if (i11 != i12 - 1) {
                            i11 = ((i11 * i12) >> 2) % (i12 - 1);
                        }
                        int i13 = i11 >>> 3;
                        bArr2[i13] = (byte) (((((bArr[i9] & 255) >>> i10) & 1) << (i11 & 7)) ^ bArr2[i13]);
                    }
                    i9++;
                }
            }
            System.arraycopy(bArr2, 0, bArr, 0, i);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00ca  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void processAADBytes(byte[] r8) {
        /*
            Method dump skipped, instructions count: 262
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.crypto.engines.ElephantEngine.processAADBytes(byte[]):void");
    }

    private int processBytes(byte[] bArr, byte[] bArr2, int i, int i2, int i3, int i4, int i5, int i6) {
        int i7 = this.nb_its;
        int i8 = 0;
        while (i7 < i2) {
            lfsr_step(this.next_mask, this.current_mask);
            if (i7 < i3) {
                System.arraycopy(this.npub, 0, this.buffer, 0, 12);
                Arrays.fill(this.buffer, 12, this.BLOCK_SIZE, (byte) 0);
                xor_block(this.buffer, this.current_mask, 0, this.BLOCK_SIZE);
                xor_block(this.buffer, this.next_mask, 0, this.BLOCK_SIZE);
                permutation(this.buffer);
                xor_block(this.buffer, this.current_mask, 0, this.BLOCK_SIZE);
                xor_block(this.buffer, this.next_mask, 0, this.BLOCK_SIZE);
                int i9 = i7 == i3 + (-1) ? i5 - (this.BLOCK_SIZE * i7) : this.BLOCK_SIZE;
                xor_block(this.buffer, bArr, 0, i9);
                System.arraycopy(this.buffer, 0, bArr2, i, i9);
                if (this.forEncryption) {
                    System.arraycopy(this.buffer, 0, this.outputMessage, 0, i9);
                } else {
                    System.arraycopy(bArr, 0, this.outputMessage, 0, i9);
                }
                i8 += i9;
            }
            int i10 = i8;
            if (i7 > 0 && i7 <= i4) {
                get_c_block(this.buffer, this.previous_outputMessage, 0, i5, i7 - 1);
                xor_block(this.buffer, this.previous_mask, 0, this.BLOCK_SIZE);
                xor_block(this.buffer, this.next_mask, 0, this.BLOCK_SIZE);
                permutation(this.buffer);
                xor_block(this.buffer, this.previous_mask, 0, this.BLOCK_SIZE);
                xor_block(this.buffer, this.next_mask, 0, this.BLOCK_SIZE);
                xor_block(this.tag_buffer, this.buffer, 0, this.BLOCK_SIZE);
            }
            i7++;
            if (i7 < i6) {
                processAADBytes(this.buffer);
                xor_block(this.buffer, this.next_mask, 0, this.BLOCK_SIZE);
                permutation(this.buffer);
                xor_block(this.buffer, this.next_mask, 0, this.BLOCK_SIZE);
                xor_block(this.tag_buffer, this.buffer, 0, this.BLOCK_SIZE);
            }
            byte[] bArr3 = this.previous_mask;
            this.previous_mask = this.current_mask;
            this.current_mask = this.next_mask;
            this.next_mask = bArr3;
            System.arraycopy(this.outputMessage, 0, this.previous_outputMessage, 0, this.BLOCK_SIZE);
            i8 = i10;
        }
        return i8;
    }

    private void reset(boolean z) {
        if (z) {
            this.tag = null;
        }
        this.aadData.reset();
        Arrays.fill(this.tag_buffer, (byte) 0);
        this.inputOff = 0;
        this.nb_its = 0;
        this.adOff = -1;
    }

    private byte rotl(byte b) {
        int i = b & 255;
        return (byte) ((i >>> 7) | (i << 1));
    }

    private void xor_block(byte[] bArr, byte[] bArr2, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            bArr[i3] = (byte) (bArr[i3] ^ bArr2[i3 + i]);
        }
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int doFinal(byte[] bArr, int i) throws InvalidCipherTextException, IllegalStateException {
        if (!this.initialised) {
            throw new IllegalArgumentException(this.algorithmName + " needs call init function before doFinal");
        }
        int i2 = this.inputOff;
        boolean z = this.forEncryption;
        if ((z && i2 + i + this.CRYPTO_ABYTES > bArr.length) || (!z && (i2 + i) - this.CRYPTO_ABYTES > bArr.length)) {
            throw new OutputLengthException("output buffer is too short");
        }
        byte[] byteArray = this.aadData.toByteArray();
        int i3 = AnonymousClass1.$SwitchMap$org$bouncycastle$crypto$engines$ElephantEngine$State[this.m_state.ordinal()];
        if (i3 == 1 || i3 == 2) {
            processAADBytes(this.tag_buffer);
        }
        int i4 = this.nb_its;
        int i5 = this.BLOCK_SIZE;
        int i6 = (i2 + (i4 * i5)) - (this.forEncryption ? (byte) 0 : this.CRYPTO_ABYTES);
        int length = byteArray.length;
        int i7 = i6 / i5;
        int i8 = i7 + 1;
        int i9 = (length + 12) / i5;
        int iProcessBytes = processBytes(this.inputMessage, bArr, i, Math.max(i7 + 2, i9), i6 % i5 != 0 ? i8 : i7, i8, i6, i9 + 1) + i;
        this.tag = new byte[this.CRYPTO_ABYTES];
        xor_block(this.tag_buffer, this.expanded_key, 0, this.BLOCK_SIZE);
        permutation(this.tag_buffer);
        xor_block(this.tag_buffer, this.expanded_key, 0, this.BLOCK_SIZE);
        if (this.forEncryption) {
            System.arraycopy(this.tag_buffer, 0, this.tag, 0, this.CRYPTO_ABYTES);
            byte[] bArr2 = this.tag;
            System.arraycopy(bArr2, 0, bArr, iProcessBytes, bArr2.length);
            i6 += this.CRYPTO_ABYTES;
        } else {
            this.inputOff -= this.CRYPTO_ABYTES;
            for (int i10 = 0; i10 < this.CRYPTO_ABYTES; i10++) {
                if (this.tag_buffer[i10] != this.inputMessage[this.inputOff + i10]) {
                    throw new IllegalArgumentException("Mac does not match");
                }
            }
        }
        reset(false);
        return i6;
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public String getAlgorithmName() {
        return this.algorithmName;
    }

    public int getBlockSize() {
        return this.CRYPTO_ABYTES;
    }

    public int getIVBytesSize() {
        return 12;
    }

    public int getKeyBytesSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public byte[] getMac() {
        return this.tag;
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int getOutputSize(int i) {
        int i2 = AnonymousClass1.$SwitchMap$org$bouncycastle$crypto$engines$ElephantEngine$State[this.m_state.ordinal()];
        if (i2 != 1) {
            if (i2 == 3) {
                throw new IllegalArgumentException(this.algorithmName + " needs call init function before getUpdateOutputSize");
            }
            if (i2 == 4 || i2 == 5) {
                return 0;
            }
            if (i2 != 6 && i2 != 7) {
                return Math.max(0, i - this.CRYPTO_ABYTES);
            }
        }
        return i + this.CRYPTO_ABYTES;
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int getUpdateOutputSize(int i) {
        int i2 = AnonymousClass1.$SwitchMap$org$bouncycastle$crypto$engines$ElephantEngine$State[this.m_state.ordinal()];
        if (i2 != 1) {
            if (i2 == 3) {
                throw new IllegalArgumentException(this.algorithmName + " needs call init function before getUpdateOutputSize");
            }
            if (i2 == 4 || i2 == 5) {
                return 0;
            }
            if (i2 != 6 && i2 != 7) {
                return Math.max(0, (i + this.inputOff) - this.CRYPTO_ABYTES);
            }
        }
        return this.inputOff + i + this.CRYPTO_ABYTES;
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        this.forEncryption = z;
        if (!(cipherParameters instanceof ParametersWithIV)) {
            throw new IllegalArgumentException(this.algorithmName + " init parameters must include an IV");
        }
        ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
        byte[] iv = parametersWithIV.getIV();
        this.npub = iv;
        if (iv == null || iv.length != 12) {
            throw new IllegalArgumentException(this.algorithmName + " requires exactly 12 bytes of IV");
        }
        if (!(parametersWithIV.getParameters() instanceof KeyParameter)) {
            throw new IllegalArgumentException(this.algorithmName + " init parameters must include a key");
        }
        byte[] key = ((KeyParameter) parametersWithIV.getParameters()).getKey();
        if (key.length != 16) {
            throw new IllegalArgumentException(this.algorithmName + " key must be 128 bits long");
        }
        byte[] bArr = new byte[this.BLOCK_SIZE];
        this.expanded_key = bArr;
        System.arraycopy(key, 0, bArr, 0, 16);
        permutation(this.expanded_key);
        CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties(getAlgorithmName(), 128, cipherParameters, Utils.getPurpose(z)));
        this.initialised = true;
        this.m_state = z ? State.EncInit : State.DecInit;
        this.inputMessage = new byte[this.BLOCK_SIZE + (z ? (byte) 0 : this.CRYPTO_ABYTES)];
        reset(false);
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public void processAADByte(byte b) {
        this.aadData.write(b);
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public void processAADBytes(byte[] bArr, int i, int i2) {
        if (i + i2 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        this.aadData.write(bArr, i, i2);
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int processByte(byte b, byte[] bArr, int i) throws DataLengthException {
        return processBytes(new byte[]{b}, 0, 1, bArr, i);
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws DataLengthException {
        if (i + i2 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        byte[] byteArray = this.aadData.toByteArray();
        int i4 = this.inputOff;
        if ((i4 + i2) - (this.forEncryption ? (byte) 0 : this.CRYPTO_ABYTES) < this.BLOCK_SIZE) {
            System.arraycopy(bArr, i, this.inputMessage, i4, i2);
            this.inputOff += i2;
            return 0;
        }
        int i5 = AnonymousClass1.$SwitchMap$org$bouncycastle$crypto$engines$ElephantEngine$State[this.m_state.ordinal()];
        if (i5 == 1 || i5 == 2) {
            processAADBytes(this.tag_buffer);
        }
        int i6 = (this.inputOff + i2) - (this.forEncryption ? (byte) 0 : this.CRYPTO_ABYTES);
        int length = byteArray.length;
        int i7 = this.BLOCK_SIZE;
        int i8 = i6 / i7;
        int i9 = (i6 % i7 != 0 ? i8 : i8 - 1) + 1;
        int i10 = ((length + 12) / i7) + 1;
        int iMax = Math.max(i8, 1) * this.BLOCK_SIZE;
        byte[] bArr3 = new byte[iMax];
        System.arraycopy(this.inputMessage, 0, bArr3, 0, this.inputOff);
        int i11 = this.inputOff;
        int i12 = iMax - i11;
        System.arraycopy(bArr, i, bArr3, i11, iMax - i11);
        processBytes(bArr3, bArr2, i3, i8, i9, i8, i6, i10);
        int i13 = i2 - i12;
        this.inputOff = i13;
        System.arraycopy(bArr, i + i12, this.inputMessage, 0, i13);
        this.nb_its += i8;
        return i8 * this.BLOCK_SIZE;
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public void reset() {
        reset(true);
    }
}
