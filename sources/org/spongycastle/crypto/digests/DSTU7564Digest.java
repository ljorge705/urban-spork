package org.spongycastle.crypto.digests;

import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import kotlin.io.encoding.Base64;
import net.sf.scuba.smartcards.ISO7816;
import net.sf.scuba.smartcards.ISOFileInfo;
import okio.Utf8;
import org.jmrtd.lds.CVCAFile;
import org.spongycastle.crypto.ExtendedDigest;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Memoable;
import org.spongycastle.util.Pack;

/* loaded from: classes4.dex */
public class DSTU7564Digest implements ExtendedDigest, Memoable {
    private static final int BITS_IN_BYTE = 8;
    private static final int NB_1024 = 16;
    private static final int NB_512 = 8;
    private static final int NR_1024 = 14;
    private static final int NR_512 = 10;
    private static final int REDUCTIONAL_POLYNOMIAL = 285;
    private static final int ROWS = 8;
    private static final int STATE_BYTES_SIZE_1024 = 128;
    private static final int STATE_BYTES_SIZE_512 = 64;
    private static final byte[][] mds_matrix = {new byte[]{1, 1, 5, 1, 8, 6, 7, 4}, new byte[]{4, 1, 1, 5, 1, 8, 6, 7}, new byte[]{7, 4, 1, 1, 5, 1, 8, 6}, new byte[]{6, 7, 4, 1, 1, 5, 1, 8}, new byte[]{8, 6, 7, 4, 1, 1, 5, 1}, new byte[]{1, 8, 6, 7, 4, 1, 1, 5}, new byte[]{5, 1, 8, 6, 7, 4, 1, 1}, new byte[]{1, 5, 1, 8, 6, 7, 4, 1}};
    private static final byte[][] sBoxes = {new byte[]{-88, 67, 95, 6, 107, 117, 108, 89, 113, -33, ISOFileInfo.FCI_EXT, -107, Ascii.ETB, -16, ISO7816.INS_LOAD_KEY_FILE, 9, 109, -13, 29, -53, -55, 77, ISO7816.INS_UNBLOCK_CHV, -81, 121, ISO7816.INS_CREATE_FILE, -105, -3, ISOFileInfo.FCI_BYTE, 75, 69, 57, 62, -35, -93, 79, ISO7816.INS_READ_BINARY_STAMPED, ISO7816.INS_READ_RECORD_STAMPED, -102, 14, Ascii.US, -65, Ascii.NAK, -31, 73, ISO7816.INS_WRITE_RECORD, -109, -58, -110, 114, -98, 97, -47, 99, -6, -18, -12, Ascii.EM, -43, -83, 88, -92, -69, ISOFileInfo.A1, ISO7816.INS_UPDATE_RECORD, -14, ISOFileInfo.FILE_IDENTIFIER, 55, CVCAFile.CAR_TAG, ISO7816.INS_DELETE_FILE, 122, ISO7816.INS_INCREASE, -100, -52, ISOFileInfo.AB, 74, -113, 110, 4, 39, 46, -25, ISO7816.INS_APPEND_RECORD, 90, -106, Ascii.SYN, 35, 43, ISO7816.INS_ENVELOPE, 101, 102, 15, -68, -87, 71, 65, ISO7816.INS_DECREASE_STAMPED, 72, -4, -73, 106, -120, ISOFileInfo.A5, 83, -122, -7, 91, -37, 56, 123, -61, 30, ISO7816.INS_MSE, 51, ISO7816.INS_CHANGE_CHV, 40, 54, -57, -78, 59, ISOFileInfo.CHANNEL_SECURITY, 119, -70, -11, Ascii.DC4, -97, 8, 85, -101, 76, -2, 96, 92, ISO7816.INS_PUT_DATA, Ascii.CAN, 70, -51, 125, 33, ISO7816.INS_READ_BINARY, Utf8.REPLACEMENT_BYTE, Ascii.ESC, -119, -1, -21, -124, 105, 58, -99, -41, -45, ISO7816.INS_MANAGE_CHANNEL, 103, SignedBytes.MAX_POWER_OF_TWO, -75, -34, 93, ISO7816.INS_DECREASE, -111, ISO7816.INS_READ_BINARY2, 120, 17, 1, -27, 0, 104, -104, ISOFileInfo.A0, -59, 2, -90, 116, 45, 11, -94, 118, ISO7816.INS_READ_RECORD2, -66, -50, -67, -82, -23, ISOFileInfo.LCS_BYTE, 49, 28, -20, -15, -103, -108, -86, -10, 38, 47, -17, -24, ISOFileInfo.SECURITY_ATTR_COMPACT, 53, 3, -44, Byte.MAX_VALUE, -5, 5, -63, 94, -112, 32, Base64.padSymbol, -126, -9, -22, 10, 13, 126, -8, 80, Ascii.SUB, -60, 7, 87, -72, 60, ISOFileInfo.FCP_BYTE, -29, -56, -84, 82, ISOFileInfo.FMD_BYTE, 16, ISO7816.INS_WRITE_BINARY, -39, 19, 12, Ascii.DC2, 41, 81, -71, -49, ISO7816.INS_UPDATE_BINARY, 115, ISOFileInfo.ENV_TEMP_EF, ISOFileInfo.DATA_BYTES2, 84, ISO7816.INS_GET_RESPONSE, -19, 78, ISO7816.INS_REHABILITATE_CHV, -89, ISO7816.INS_PSO, ISOFileInfo.PROP_INFO, 37, -26, ISO7816.INS_GET_DATA, 124, ISOFileInfo.SECURITY_ATTR_EXP, 86, Byte.MIN_VALUE}, new byte[]{-50, -69, -21, -110, -22, -53, 19, -63, -23, 58, ISO7816.INS_UPDATE_BINARY, -78, ISO7816.INS_WRITE_RECORD, -112, Ascii.ETB, -8, CVCAFile.CAR_TAG, Ascii.NAK, 86, ISO7816.INS_READ_BINARY_STAMPED, 101, 28, -120, 67, -59, 92, 54, -70, -11, 87, 103, ISOFileInfo.ENV_TEMP_EF, 49, -10, ISOFileInfo.FMD_BYTE, 88, -98, -12, ISO7816.INS_MSE, -86, 117, 15, 2, ISO7816.INS_READ_BINARY2, -33, 109, 115, 77, 124, 38, 46, -9, 8, 93, ISO7816.INS_REHABILITATE_CHV, 62, -97, Ascii.DC4, -56, -82, 84, 16, ISO7816.INS_LOAD_KEY_FILE, -68, Ascii.SUB, 107, 105, -13, -67, 51, ISOFileInfo.AB, -6, -47, -101, 104, 78, Ascii.SYN, -107, -111, -18, 76, 99, ISOFileInfo.CHANNEL_SECURITY, 91, -52, 60, Ascii.EM, ISOFileInfo.A1, ISOFileInfo.DATA_BYTES2, 73, 123, -39, ISOFileInfo.FCI_BYTE, 55, 96, ISO7816.INS_GET_DATA, -25, 43, 72, -3, -106, 69, -4, 65, Ascii.DC2, 13, 121, -27, -119, ISOFileInfo.SECURITY_ATTR_COMPACT, -29, 32, ISO7816.INS_DECREASE, ISO7816.INS_UPDATE_RECORD, -73, 108, 74, -75, Utf8.REPLACEMENT_BYTE, -105, -44, ISOFileInfo.FCP_BYTE, 45, 6, -92, ISOFileInfo.A5, ISOFileInfo.FILE_IDENTIFIER, 95, ISO7816.INS_PSO, ISO7816.INS_PUT_DATA, -55, 0, 126, -94, 85, -65, 17, -43, -100, -49, 14, 10, Base64.padSymbol, 81, 125, -109, Ascii.ESC, -2, -60, 71, 9, -122, 11, -113, -99, 106, 7, -71, ISO7816.INS_READ_BINARY, -104, Ascii.CAN, ISO7816.INS_INCREASE, 113, 75, -17, 59, ISO7816.INS_MANAGE_CHANNEL, ISOFileInfo.A0, ISO7816.INS_DELETE_FILE, SignedBytes.MAX_POWER_OF_TWO, -1, -61, -87, -26, 120, -7, ISOFileInfo.SECURITY_ATTR_EXP, 70, Byte.MIN_VALUE, 30, 56, -31, -72, -88, ISO7816.INS_CREATE_FILE, 12, 35, 118, 29, 37, ISO7816.INS_CHANGE_CHV, 5, -15, 110, -108, 40, -102, -124, -24, -93, 79, 119, -45, ISOFileInfo.PROP_INFO, ISO7816.INS_APPEND_RECORD, 82, -14, -126, 80, 122, 47, 116, 83, ISO7816.INS_READ_RECORD2, 97, -81, 57, 53, -34, -51, Ascii.US, -103, -84, -83, 114, ISO7816.INS_UNBLOCK_CHV, -35, ISO7816.INS_WRITE_BINARY, ISOFileInfo.FCI_EXT, -66, 94, -90, -20, 4, -58, 3, ISO7816.INS_DECREASE_STAMPED, -5, -37, 89, ISO7816.INS_READ_RECORD_STAMPED, ISO7816.INS_ENVELOPE, 1, -16, 90, -19, -89, 102, 33, Byte.MAX_VALUE, ISOFileInfo.LCS_BYTE, 39, -57, ISO7816.INS_GET_RESPONSE, 41, -41}, new byte[]{-109, -39, -102, -75, -104, ISO7816.INS_MSE, 69, -4, -70, 106, -33, 2, -97, ISO7816.INS_UPDATE_RECORD, 81, 89, 74, Ascii.ETB, 43, ISO7816.INS_ENVELOPE, -108, -12, -69, -93, ISOFileInfo.FCP_BYTE, ISO7816.INS_DELETE_FILE, 113, -44, -51, ISO7816.INS_MANAGE_CHANNEL, Ascii.SYN, -31, 73, 60, ISO7816.INS_GET_RESPONSE, ISO7816.INS_LOAD_KEY_FILE, 92, -101, -83, ISOFileInfo.PROP_INFO, 83, ISOFileInfo.A1, 122, -56, 45, ISO7816.INS_CREATE_FILE, -47, 114, -90, ISO7816.INS_UNBLOCK_CHV, -60, -29, 118, 120, -73, ISO7816.INS_READ_BINARY_STAMPED, 9, 59, 14, 65, 76, -34, -78, -112, 37, ISOFileInfo.A5, -41, 3, 17, 0, -61, 46, -110, -17, 78, Ascii.DC2, -99, 125, -53, 53, 16, -43, 79, -98, 77, -87, 85, -58, ISO7816.INS_WRITE_BINARY, 123, Ascii.CAN, -105, -45, 54, -26, 72, 86, ISOFileInfo.DATA_BYTES2, -113, 119, -52, -100, -71, ISO7816.INS_APPEND_RECORD, -84, -72, 47, Ascii.NAK, -92, 124, ISO7816.INS_PUT_DATA, 56, 30, 11, 5, ISO7816.INS_UPDATE_BINARY, Ascii.DC4, 110, 108, 126, 102, -3, ISO7816.INS_READ_BINARY2, -27, 96, -81, 94, 51, ISOFileInfo.FCI_EXT, -55, -16, 93, 109, Utf8.REPLACEMENT_BYTE, -120, ISOFileInfo.ENV_TEMP_EF, -57, -9, 29, -23, -20, -19, Byte.MIN_VALUE, 41, 39, -49, -103, -88, 80, 15, 55, ISO7816.INS_CHANGE_CHV, 40, ISO7816.INS_DECREASE, -107, ISO7816.INS_WRITE_RECORD, 62, 91, SignedBytes.MAX_POWER_OF_TWO, ISOFileInfo.FILE_IDENTIFIER, ISO7816.INS_READ_RECORD2, 105, 87, Ascii.US, 7, 28, ISOFileInfo.LCS_BYTE, -68, 32, -21, -50, ISOFileInfo.CHANNEL_SECURITY, ISOFileInfo.AB, -18, 49, -94, 115, -7, ISO7816.INS_GET_DATA, 58, Ascii.SUB, -5, 13, -63, -2, -6, -14, ISOFileInfo.FCI_BYTE, -67, -106, -35, 67, 82, ISO7816.INS_READ_RECORD_STAMPED, 8, -13, -82, -66, Ascii.EM, -119, ISO7816.INS_INCREASE, 38, ISO7816.INS_READ_BINARY, -22, 75, ISOFileInfo.FMD_BYTE, -124, -126, 107, -11, 121, -65, 1, 95, 117, 99, Ascii.ESC, 35, Base64.padSymbol, 104, ISO7816.INS_PSO, 101, -24, -111, -10, -1, 19, 88, -15, 71, 10, Byte.MAX_VALUE, -59, -89, -25, 97, 90, 6, 70, ISO7816.INS_REHABILITATE_CHV, CVCAFile.CAR_TAG, 4, ISOFileInfo.A0, -37, 57, -122, 84, -86, ISOFileInfo.SECURITY_ATTR_COMPACT, ISO7816.INS_DECREASE_STAMPED, 33, ISOFileInfo.SECURITY_ATTR_EXP, -8, 12, 116, 103}, new byte[]{104, ISOFileInfo.ENV_TEMP_EF, ISO7816.INS_GET_DATA, 77, 115, 75, 78, ISO7816.INS_PSO, -44, 82, 38, ISO7816.INS_READ_RECORD2, 84, 30, Ascii.EM, Ascii.US, ISO7816.INS_MSE, 3, 70, Base64.padSymbol, 45, 74, 83, ISOFileInfo.FILE_IDENTIFIER, 19, ISOFileInfo.LCS_BYTE, -73, -43, 37, 121, -11, -67, 88, 47, 13, 2, -19, 81, -98, 17, -14, 62, 85, 94, -47, Ascii.SYN, 60, 102, ISO7816.INS_MANAGE_CHANNEL, 93, -13, 69, SignedBytes.MAX_POWER_OF_TWO, -52, -24, -108, 86, 8, -50, Ascii.SUB, 58, ISO7816.INS_WRITE_RECORD, -31, -33, -75, 56, 110, 14, -27, -12, -7, -122, -23, 79, ISO7816.INS_UPDATE_BINARY, ISOFileInfo.PROP_INFO, 35, -49, ISO7816.INS_INCREASE, -103, 49, Ascii.DC4, -82, -18, -56, 72, -45, ISO7816.INS_DECREASE, ISOFileInfo.A1, -110, 65, ISO7816.INS_READ_BINARY2, Ascii.CAN, -60, ISO7816.INS_UNBLOCK_CHV, 113, 114, ISO7816.INS_REHABILITATE_CHV, Ascii.NAK, -3, 55, -66, 95, -86, -101, -120, ISO7816.INS_LOAD_KEY_FILE, ISOFileInfo.AB, -119, -100, -6, 96, -22, -68, ISOFileInfo.FCP_BYTE, 12, ISO7816.INS_CHANGE_CHV, -90, -88, -20, 103, 32, -37, 124, 40, -35, -84, 91, ISO7816.INS_DECREASE_STAMPED, 126, 16, -15, 123, -113, 99, ISOFileInfo.A0, 5, -102, 67, 119, 33, -65, 39, 9, -61, -97, ISO7816.INS_READ_RECORD_STAMPED, -41, 41, ISO7816.INS_ENVELOPE, -21, ISO7816.INS_GET_RESPONSE, -92, ISOFileInfo.SECURITY_ATTR_EXP, ISOFileInfo.SECURITY_ATTR_COMPACT, 29, -5, -1, -63, -78, -105, 46, -8, 101, -10, 117, 7, 4, 73, 51, ISO7816.INS_DELETE_FILE, -39, -71, ISO7816.INS_WRITE_BINARY, CVCAFile.CAR_TAG, -57, 108, -112, 0, ISOFileInfo.CHANNEL_SECURITY, ISOFileInfo.FCI_BYTE, 80, 1, -59, ISO7816.INS_PUT_DATA, 71, Utf8.REPLACEMENT_BYTE, -51, 105, -94, ISO7816.INS_APPEND_RECORD, 122, -89, -58, -109, 15, 10, 6, -26, 43, -106, -93, 28, -81, 106, Ascii.DC2, -124, 57, -25, ISO7816.INS_READ_BINARY, -126, -9, -2, -99, ISOFileInfo.FCI_EXT, 92, ISOFileInfo.DATA_BYTES2, 53, -34, ISO7816.INS_READ_BINARY_STAMPED, ISOFileInfo.A5, -4, Byte.MIN_VALUE, -17, -53, -69, 107, 118, -70, 90, 125, 120, 11, -107, -29, -83, 116, -104, 59, 54, ISOFileInfo.FMD_BYTE, 109, ISO7816.INS_UPDATE_RECORD, -16, 89, -87, 76, Ascii.ETB, Byte.MAX_VALUE, -111, -72, -55, 87, Ascii.ESC, ISO7816.INS_CREATE_FILE, 97}};
    private int blockSize;
    private byte[] buf;
    private int bufOff;
    private int columns;
    private int hashSize;
    private long inputLength;
    private byte[] mixColumnsResult;
    private byte[] padded;
    private int rounds;
    private byte[][] state;
    private byte[] tempBuffer;
    private long[] tempLongBuffer;
    private byte[][] tempState1;
    private byte[][] tempState2;

    private byte multiplyGF(byte b, byte b2) {
        byte b3 = 0;
        for (int i = 0; i < 8; i++) {
            if ((b2 & 1) == 1) {
                b3 = (byte) (b3 ^ b);
            }
            byte b4 = (byte) (b & Byte.MIN_VALUE);
            b = (byte) (b << 1);
            if (b4 == Byte.MIN_VALUE) {
                b = (byte) (b ^ 285);
            }
            b2 = (byte) (b2 >> 1);
        }
        return b3;
    }

    @Override // org.spongycastle.crypto.Digest
    public String getAlgorithmName() {
        return "DSTU7564";
    }

    @Override // org.spongycastle.crypto.ExtendedDigest
    public int getByteLength() {
        return this.blockSize;
    }

    @Override // org.spongycastle.crypto.Digest
    public int getDigestSize() {
        return this.hashSize;
    }

    public DSTU7564Digest(DSTU7564Digest dSTU7564Digest) {
        copyIn(dSTU7564Digest);
    }

    private void copyIn(DSTU7564Digest dSTU7564Digest) {
        this.hashSize = dSTU7564Digest.hashSize;
        this.blockSize = dSTU7564Digest.blockSize;
        this.columns = dSTU7564Digest.columns;
        this.rounds = dSTU7564Digest.rounds;
        this.padded = Arrays.clone(dSTU7564Digest.padded);
        this.state = Arrays.clone(dSTU7564Digest.state);
        this.tempState1 = Arrays.clone(dSTU7564Digest.tempState1);
        this.tempState2 = Arrays.clone(dSTU7564Digest.tempState2);
        this.tempBuffer = Arrays.clone(dSTU7564Digest.tempBuffer);
        this.mixColumnsResult = Arrays.clone(dSTU7564Digest.mixColumnsResult);
        this.tempLongBuffer = Arrays.clone(dSTU7564Digest.tempLongBuffer);
        this.inputLength = dSTU7564Digest.inputLength;
        this.bufOff = dSTU7564Digest.bufOff;
        this.buf = Arrays.clone(dSTU7564Digest.buf);
    }

    public DSTU7564Digest(int i) {
        byte[][] bArr;
        if (i == 256 || i == 384 || i == 512) {
            this.hashSize = i / 8;
            if (i > 256) {
                this.blockSize = 128;
                this.columns = 16;
                this.rounds = 14;
                this.state = new byte[128][];
            } else {
                this.blockSize = 64;
                this.columns = 8;
                this.rounds = 10;
                this.state = new byte[64][];
            }
            int i2 = 0;
            while (true) {
                bArr = this.state;
                if (i2 >= bArr.length) {
                    break;
                }
                bArr[i2] = new byte[this.columns];
                i2++;
            }
            bArr[0][0] = (byte) bArr.length;
            this.padded = null;
            this.tempState1 = new byte[128][];
            this.tempState2 = new byte[128][];
            for (int i3 = 0; i3 < this.state.length; i3++) {
                this.tempState1[i3] = new byte[8];
                this.tempState2[i3] = new byte[8];
            }
            this.tempBuffer = new byte[16];
            this.mixColumnsResult = new byte[8];
            this.tempLongBuffer = new long[this.columns];
            this.buf = new byte[this.blockSize];
            return;
        }
        throw new IllegalArgumentException("Hash size is not recommended. Use 256/384/512 instead");
    }

    @Override // org.spongycastle.crypto.Digest
    public void update(byte b) {
        byte[] bArr = this.buf;
        int i = this.bufOff;
        int i2 = i + 1;
        this.bufOff = i2;
        bArr[i] = b;
        if (i2 == this.blockSize) {
            processBlock(bArr, 0);
            this.bufOff = 0;
        }
        this.inputLength++;
    }

    @Override // org.spongycastle.crypto.Digest
    public void update(byte[] bArr, int i, int i2) {
        while (this.bufOff != 0 && i2 > 0) {
            update(bArr[i]);
            i2--;
            i++;
        }
        if (i2 > 0) {
            while (i2 > this.blockSize) {
                processBlock(bArr, i);
                int i3 = this.blockSize;
                i += i3;
                this.inputLength += i3;
                i2 -= i3;
            }
            while (i2 > 0) {
                update(bArr[i]);
                i2--;
                i++;
            }
        }
    }

    @Override // org.spongycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        byte[] bArrPad = pad(this.buf, 0, this.bufOff);
        this.padded = bArrPad;
        int length = bArrPad.length;
        int i2 = 0;
        while (length != 0) {
            processBlock(this.padded, i2);
            int i3 = this.blockSize;
            i2 += i3;
            length -= i3;
        }
        byte[][] bArr2 = new byte[128][];
        int i4 = 0;
        while (true) {
            byte[][] bArr3 = this.state;
            if (i4 >= bArr3.length) {
                break;
            }
            byte[] bArr4 = new byte[8];
            bArr2[i4] = bArr4;
            System.arraycopy(bArr3[i4], 0, bArr4, 0, 8);
            i4++;
        }
        for (int i5 = 0; i5 < this.rounds; i5++) {
            for (int i6 = 0; i6 < this.columns; i6++) {
                byte[] bArr5 = bArr2[i6];
                bArr5[0] = (byte) (bArr5[0] ^ ((byte) ((i6 * 16) ^ i5)));
            }
            for (int i7 = 0; i7 < 8; i7++) {
                for (int i8 = 0; i8 < this.columns; i8++) {
                    byte[] bArr6 = bArr2[i8];
                    bArr6[i7] = sBoxes[i7 % 4][bArr6[i7] & 255];
                }
            }
            int i9 = -1;
            int i10 = 0;
            while (i10 < 8) {
                i9 = (i10 == 7 && this.columns == 16) ? 11 : i9 + 1;
                int i11 = 0;
                while (true) {
                    int i12 = this.columns;
                    if (i11 >= i12) {
                        break;
                    }
                    this.tempBuffer[(i11 + i9) % i12] = bArr2[i11][i10];
                    i11++;
                }
                for (int i13 = 0; i13 < this.columns; i13++) {
                    bArr2[i13][i10] = this.tempBuffer[i13];
                }
                i10++;
            }
            for (int i14 = 0; i14 < this.columns; i14++) {
                Arrays.fill(this.mixColumnsResult, (byte) 0);
                for (int i15 = 7; i15 >= 0; i15--) {
                    byte bMultiplyGF = 0;
                    for (int i16 = 7; i16 >= 0; i16--) {
                        bMultiplyGF = (byte) (bMultiplyGF ^ multiplyGF(bArr2[i14][i16], mds_matrix[i15][i16]));
                    }
                    this.mixColumnsResult[i15] = bMultiplyGF;
                }
                for (int i17 = 0; i17 < 8; i17++) {
                    bArr2[i14][i17] = this.mixColumnsResult[i17];
                }
            }
        }
        for (int i18 = 0; i18 < 8; i18++) {
            for (int i19 = 0; i19 < this.columns; i19++) {
                byte[] bArr7 = this.state[i19];
                bArr7[i18] = (byte) (bArr7[i18] ^ bArr2[i19][i18]);
            }
        }
        int i20 = this.columns * 8;
        byte[] bArr8 = new byte[i20];
        int i21 = 0;
        for (int i22 = 0; i22 < this.columns; i22++) {
            for (int i23 = 0; i23 < 8; i23++) {
                bArr8[i21] = this.state[i22][i23];
                i21++;
            }
        }
        int i24 = this.hashSize;
        System.arraycopy(bArr8, i20 - i24, bArr, i, i24);
        reset();
        return this.hashSize;
    }

    @Override // org.spongycastle.crypto.Digest
    public void reset() {
        byte[][] bArr;
        int i = 0;
        while (true) {
            bArr = this.state;
            if (i >= bArr.length) {
                break;
            }
            bArr[i] = new byte[this.columns];
            i++;
        }
        bArr[0][0] = (byte) bArr.length;
        this.inputLength = 0L;
        this.bufOff = 0;
        Arrays.fill(this.buf, (byte) 0);
        byte[] bArr2 = this.padded;
        if (bArr2 != null) {
            Arrays.fill(bArr2, (byte) 0);
        }
    }

    private void processBlock(byte[] bArr, int i) {
        for (int i2 = 0; i2 < this.state.length; i2++) {
            Arrays.fill(this.tempState1[i2], (byte) 0);
            Arrays.fill(this.tempState2[i2], (byte) 0);
        }
        for (int i3 = 0; i3 < 8; i3++) {
            for (int i4 = 0; i4 < this.columns; i4++) {
                int i5 = (i4 * 8) + i3 + i;
                this.tempState1[i4][i3] = (byte) (this.state[i4][i3] ^ bArr[i5]);
                this.tempState2[i4][i3] = bArr[i5];
            }
        }
        P();
        Q();
        for (int i6 = 0; i6 < 8; i6++) {
            for (int i7 = 0; i7 < this.columns; i7++) {
                byte[] bArr2 = this.state[i7];
                bArr2[i6] = (byte) (bArr2[i6] ^ ((byte) (this.tempState1[i7][i6] ^ this.tempState2[i7][i6])));
            }
        }
    }

    private void Q() {
        for (int i = 0; i < this.rounds; i++) {
            for (int i2 = 0; i2 < this.columns; i2++) {
                this.tempLongBuffer[i2] = Pack.littleEndianToLong(this.tempState2[i2], 0);
                long[] jArr = this.tempLongBuffer;
                long j = jArr[i2] + ((((((this.columns - i2) - 1) * 16) ^ i) << 56) ^ 67818912035696883L);
                jArr[i2] = j;
                Pack.longToLittleEndian(j, this.tempState2[i2], 0);
            }
            for (int i3 = 0; i3 < 8; i3++) {
                for (int i4 = 0; i4 < this.columns; i4++) {
                    byte[] bArr = this.tempState2[i4];
                    bArr[i3] = sBoxes[i3 % 4][bArr[i3] & 255];
                }
            }
            int i5 = -1;
            int i6 = 0;
            while (i6 < 8) {
                i5 = (i6 == 7 && this.columns == 16) ? 11 : i5 + 1;
                int i7 = 0;
                while (true) {
                    int i8 = this.columns;
                    if (i7 >= i8) {
                        break;
                    }
                    this.tempBuffer[(i7 + i5) % i8] = this.tempState2[i7][i6];
                    i7++;
                }
                for (int i9 = 0; i9 < this.columns; i9++) {
                    this.tempState2[i9][i6] = this.tempBuffer[i9];
                }
                i6++;
            }
            for (int i10 = 0; i10 < this.columns; i10++) {
                Arrays.fill(this.mixColumnsResult, (byte) 0);
                for (int i11 = 7; i11 >= 0; i11--) {
                    byte bMultiplyGF = 0;
                    for (int i12 = 7; i12 >= 0; i12--) {
                        bMultiplyGF = (byte) (bMultiplyGF ^ multiplyGF(this.tempState2[i10][i12], mds_matrix[i11][i12]));
                    }
                    this.mixColumnsResult[i11] = bMultiplyGF;
                }
                for (int i13 = 0; i13 < 8; i13++) {
                    this.tempState2[i10][i13] = this.mixColumnsResult[i13];
                }
            }
        }
    }

    private void P() {
        for (int i = 0; i < this.rounds; i++) {
            for (int i2 = 0; i2 < this.columns; i2++) {
                byte[] bArr = this.tempState1[i2];
                bArr[0] = (byte) (bArr[0] ^ ((byte) ((i2 * 16) ^ i)));
            }
            for (int i3 = 0; i3 < 8; i3++) {
                for (int i4 = 0; i4 < this.columns; i4++) {
                    byte[] bArr2 = this.tempState1[i4];
                    bArr2[i3] = sBoxes[i3 % 4][bArr2[i3] & 255];
                }
            }
            int i5 = -1;
            int i6 = 0;
            while (i6 < 8) {
                i5 = (i6 == 7 && this.columns == 16) ? 11 : i5 + 1;
                int i7 = 0;
                while (true) {
                    int i8 = this.columns;
                    if (i7 >= i8) {
                        break;
                    }
                    this.tempBuffer[(i7 + i5) % i8] = this.tempState1[i7][i6];
                    i7++;
                }
                for (int i9 = 0; i9 < this.columns; i9++) {
                    this.tempState1[i9][i6] = this.tempBuffer[i9];
                }
                i6++;
            }
            for (int i10 = 0; i10 < this.columns; i10++) {
                Arrays.fill(this.mixColumnsResult, (byte) 0);
                for (int i11 = 7; i11 >= 0; i11--) {
                    byte bMultiplyGF = 0;
                    for (int i12 = 7; i12 >= 0; i12--) {
                        bMultiplyGF = (byte) (bMultiplyGF ^ multiplyGF(this.tempState1[i10][i12], mds_matrix[i11][i12]));
                    }
                    this.mixColumnsResult[i11] = bMultiplyGF;
                }
                for (int i13 = 0; i13 < 8; i13++) {
                    this.tempState1[i10][i13] = this.mixColumnsResult[i13];
                }
            }
        }
    }

    private byte[] pad(byte[] bArr, int i, int i2) {
        byte[] bArr2;
        int i3 = this.blockSize;
        if (i3 - i2 < 13) {
            bArr2 = new byte[i3 * 2];
        } else {
            bArr2 = new byte[i3];
        }
        System.arraycopy(bArr, i, bArr2, 0, i2);
        bArr2[i2] = Byte.MIN_VALUE;
        Pack.longToLittleEndian(this.inputLength * 8, bArr2, bArr2.length - 12);
        return bArr2;
    }

    @Override // org.spongycastle.util.Memoable
    public Memoable copy() {
        return new DSTU7564Digest(this);
    }

    @Override // org.spongycastle.util.Memoable
    public void reset(Memoable memoable) {
        copyIn((DSTU7564Digest) memoable);
    }
}
