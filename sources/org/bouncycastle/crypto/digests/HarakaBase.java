package org.bouncycastle.crypto.digests;

import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import kotlin.io.encoding.Base64;
import net.sf.scuba.smartcards.ISO7816;
import net.sf.scuba.smartcards.ISOFileInfo;
import okio.Utf8;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.util.Bytes;
import org.jmrtd.lds.CVCAFile;

/* loaded from: classes4.dex */
public abstract class HarakaBase implements Digest {
    protected static final int DIGEST_SIZE = 32;
    static final byte[][] RC = {new byte[]{-99, 123, ISOFileInfo.DATA_BYTES2, 117, -16, -2, -59, -78, 10, ISO7816.INS_GET_RESPONSE, 32, -26, 76, ISO7816.INS_MANAGE_CHANNEL, -124, 6}, new byte[]{Ascii.ETB, -9, 8, 47, -92, 107, 15, ISOFileInfo.FMD_BYTE, 107, ISOFileInfo.A0, -13, -120, -31, ISO7816.INS_READ_BINARY_STAMPED, 102, ISOFileInfo.SECURITY_ATTR_EXP}, new byte[]{Ascii.DC4, -111, 2, -97, 96, -99, 2, -49, -104, -124, -14, 83, 45, -34, 2, ISO7816.INS_DECREASE_STAMPED}, new byte[]{121, 79, 91, -3, -81, -68, -13, -69, 8, 79, 123, 46, -26, -22, ISO7816.INS_UPDATE_BINARY, 14}, new byte[]{ISO7816.INS_REHABILITATE_CHV, ISO7816.INS_MANAGE_CHANNEL, 57, -66, 28, -51, -18, 121, ISOFileInfo.SECURITY_ATTR_EXP, ISO7816.INS_REHABILITATE_CHV, 114, 72, -53, ISO7816.INS_READ_BINARY, -49, -53}, new byte[]{123, 5, ISOFileInfo.LCS_BYTE, 43, -19, 53, 83, ISOFileInfo.ENV_TEMP_EF, -73, ISO7816.INS_INCREASE, -112, 110, -18, -51, -22, 126}, new byte[]{Ascii.ESC, -17, 79, ISO7816.INS_PUT_DATA, 97, 39, 65, ISO7816.INS_APPEND_RECORD, ISO7816.INS_WRITE_BINARY, 124, 46, 94, 67, -113, ISO7816.INS_ENVELOPE, 103}, new byte[]{59, 11, -57, Ascii.US, ISO7816.INS_APPEND_RECORD, -3, 95, 103, 7, -52, ISO7816.INS_GET_DATA, -81, ISO7816.INS_READ_BINARY, -39, ISO7816.INS_CHANGE_CHV, 41}, new byte[]{-18, 101, -44, -71, ISO7816.INS_GET_DATA, -113, -37, -20, -23, Byte.MAX_VALUE, -122, -26, -15, 99, 77, ISOFileInfo.AB}, new byte[]{51, 126, 3, -83, 79, SignedBytes.MAX_POWER_OF_TWO, ISO7816.INS_PSO, 91, ISOFileInfo.FMD_BYTE, -51, -73, -44, -124, -65, ISO7816.INS_DECREASE, 28}, new byte[]{0, -104, -10, ISOFileInfo.ENV_TEMP_EF, 46, ISOFileInfo.SECURITY_ATTR_EXP, 2, 105, -65, 35, Ascii.ETB, -108, -71, 11, -52, -78}, new byte[]{ISOFileInfo.LCS_BYTE, 45, -99, 92, -56, -98, -86, 74, 114, 85, ISOFileInfo.FCI_BYTE, -34, -90, 120, 4, -6}, new byte[]{-44, -97, Ascii.DC2, 41, 46, 79, -6, 14, Ascii.DC2, ISO7816.INS_PSO, 119, 107, 43, -97, ISO7816.INS_READ_BINARY_STAMPED, -33}, new byte[]{-18, Ascii.DC2, 106, -69, -82, 17, ISO7816.INS_UPDATE_BINARY, ISO7816.INS_INCREASE, 54, -94, 73, -12, ISO7816.INS_REHABILITATE_CHV, 3, ISOFileInfo.A1, 30}, new byte[]{-90, -20, -88, -100, -55, 0, -106, 95, -124, 0, 5, 75, -120, 73, 4, -81}, new byte[]{-20, -109, -27, 39, -29, -57, -94, 120, 79, -100, Ascii.EM, -99, ISO7816.INS_LOAD_KEY_FILE, 94, 2, 33}, new byte[]{115, 1, -44, -126, -51, 46, 40, -71, -73, -55, 89, -89, -8, -86, 58, -65}, new byte[]{107, 125, ISO7816.INS_DECREASE, 16, -39, -17, -14, 55, Ascii.ETB, ISO7816.INS_READ_BINARY, -122, 97, 13, ISO7816.INS_MANAGE_CHANNEL, 96, ISOFileInfo.FCP_BYTE}, new byte[]{-58, -102, -4, -10, 83, -111, ISO7816.INS_ENVELOPE, ISOFileInfo.DATA_BYTES2, 67, 4, ISO7816.INS_DECREASE, 33, ISO7816.INS_ENVELOPE, 69, ISO7816.INS_GET_DATA, 90}, new byte[]{58, -108, -47, 54, -24, -110, -81, ISO7816.INS_UNBLOCK_CHV, -69, 104, 107, ISO7816.INS_MSE, 60, -105, 35, -110}, new byte[]{ISO7816.INS_READ_BINARY_STAMPED, 113, 16, -27, 88, -71, -70, 108, -21, -122, 88, ISO7816.INS_MSE, 56, -110, -65, -45}, new byte[]{ISOFileInfo.ENV_TEMP_EF, Ascii.DC2, -31, ISO7816.INS_CHANGE_CHV, -35, -3, Base64.padSymbol, -109, 119, -58, -16, -82, -27, 60, -122, -37}, new byte[]{ISO7816.INS_READ_BINARY2, Ascii.DC2, ISO7816.INS_MSE, -53, -29, ISOFileInfo.ENV_TEMP_EF, ISO7816.INS_DELETE_FILE, ISOFileInfo.FILE_IDENTIFIER, -100, ISOFileInfo.A0, -21, -1, 104, ISOFileInfo.FCP_BYTE, 96, -69}, new byte[]{125, -9, 43, -57, 78, Ascii.SUB, -71, 45, -100, -47, ISO7816.INS_DELETE_FILE, ISO7816.INS_APPEND_RECORD, ISO7816.INS_UPDATE_RECORD, -45, 75, 115}, new byte[]{78, -110, ISO7816.INS_READ_RECORD2, ISO7816.INS_UNBLOCK_CHV, -60, Ascii.NAK, Ascii.DC4, 75, 67, Ascii.ESC, ISO7816.INS_DECREASE, 97, -61, 71, -69, 67}, new byte[]{-103, 104, -21, Ascii.SYN, -35, 49, -78, 3, -10, -17, 7, -25, -88, 117, -89, -37}, new byte[]{ISO7816.INS_UNBLOCK_CHV, 71, ISO7816.INS_GET_DATA, 126, 2, 35, 94, ISOFileInfo.CHANNEL_SECURITY, 119, 89, 117, 60, 75, 97, -13, 109}, new byte[]{-7, Ascii.ETB, -122, -72, -71, -27, Ascii.ESC, 109, 119, 125, -34, ISO7816.INS_UPDATE_BINARY, Ascii.ETB, 90, -89, -51}, new byte[]{93, -18, 70, -87, -99, 6, 108, -99, -86, -23, -88, 107, -16, 67, 107, -20}, new byte[]{-63, 39, -13, 59, 89, 17, 83, -94, 43, 51, 87, -7, 80, 105, 30, -53}, new byte[]{-39, ISO7816.INS_WRITE_BINARY, 14, 96, 83, 3, -19, ISO7816.INS_DELETE_FILE, -100, 97, ISO7816.INS_PUT_DATA, 0, 117, 12, -18, ISO7816.INS_UNBLOCK_CHV}, new byte[]{80, -93, -92, 99, -68, -70, -69, Byte.MIN_VALUE, ISOFileInfo.AB, 12, -23, -106, ISOFileInfo.A1, ISOFileInfo.A5, ISO7816.INS_READ_BINARY2, -16}, new byte[]{57, ISO7816.INS_GET_DATA, ISOFileInfo.ENV_TEMP_EF, -109, ISO7816.INS_DECREASE, -34, 13, ISOFileInfo.AB, -120, 41, -106, 94, 2, ISO7816.INS_READ_BINARY2, Base64.padSymbol, -82}, new byte[]{CVCAFile.CAR_TAG, ISO7816.INS_READ_BINARY_STAMPED, 117, 46, -88, -13, Ascii.DC4, -120, 11, -92, 84, -43, 56, -113, -69, Ascii.ETB}, new byte[]{-10, Ascii.SYN, 10, 54, 121, -73, ISO7816.INS_READ_RECORD_STAMPED, -82, -41, Byte.MAX_VALUE, CVCAFile.CAR_TAG, 95, 91, ISOFileInfo.LCS_BYTE, -69, ISO7816.INS_DECREASE_STAMPED}, new byte[]{-34, -81, -70, -1, Ascii.CAN, 89, -50, 67, 56, 84, -27, -53, 65, 82, -10, 38}, new byte[]{120, -55, -98, ISOFileInfo.FILE_IDENTIFIER, -9, -100, ISO7816.INS_GET_DATA, -94, 106, 2, -13, -71, 84, -102, -23, 76}, new byte[]{53, Ascii.DC2, -112, ISO7816.INS_MSE, 40, 110, ISO7816.INS_GET_RESPONSE, SignedBytes.MAX_POWER_OF_TWO, -66, -9, -33, Ascii.ESC, Ascii.SUB, ISOFileInfo.A5, 81, -82}, new byte[]{-49, 89, -90, 72, 15, -68, 115, -63, 43, ISO7816.INS_WRITE_RECORD, 126, -70, 60, 97, -63, ISOFileInfo.A0}, new byte[]{ISOFileInfo.A1, -99, -59, -23, -3, -67, ISO7816.INS_UPDATE_BINARY, 74, -120, -126, 40, 2, 3, -52, 106, 117}};
    private static final byte[][] S = {new byte[]{99, 124, 119, 123, -14, 107, ISOFileInfo.FCI_BYTE, -59, ISO7816.INS_DECREASE, 1, 103, 43, -2, -41, ISOFileInfo.AB, 118}, new byte[]{ISO7816.INS_GET_DATA, -126, -55, 125, -6, 89, 71, -16, -83, -44, -94, -81, -100, -92, 114, ISO7816.INS_GET_RESPONSE}, new byte[]{-73, -3, -109, 38, 54, Utf8.REPLACEMENT_BYTE, -9, -52, ISO7816.INS_DECREASE_STAMPED, ISOFileInfo.A5, -27, -15, 113, ISO7816.INS_LOAD_KEY_FILE, 49, Ascii.NAK}, new byte[]{4, -57, 35, -61, Ascii.CAN, -106, 5, -102, 7, Ascii.DC2, Byte.MIN_VALUE, ISO7816.INS_APPEND_RECORD, -21, 39, -78, 117}, new byte[]{9, ISOFileInfo.FILE_IDENTIFIER, ISO7816.INS_UNBLOCK_CHV, Ascii.SUB, Ascii.ESC, 110, 90, ISOFileInfo.A0, 82, 59, ISO7816.INS_UPDATE_BINARY, ISO7816.INS_READ_RECORD2, 41, -29, 47, -124}, new byte[]{83, -47, 0, -19, 32, -4, ISO7816.INS_READ_BINARY2, 91, 106, -53, -66, 57, 74, 76, 88, -49}, new byte[]{ISO7816.INS_WRITE_BINARY, -17, -86, -5, 67, 77, 51, ISOFileInfo.PROP_INFO, 69, -7, 2, Byte.MAX_VALUE, 80, 60, -97, -88}, new byte[]{81, -93, SignedBytes.MAX_POWER_OF_TWO, -113, -110, -99, 56, -11, -68, ISO7816.INS_READ_RECORD_STAMPED, ISO7816.INS_PUT_DATA, 33, 16, -1, -13, ISO7816.INS_WRITE_RECORD}, new byte[]{-51, 12, 19, -20, 95, -105, ISO7816.INS_REHABILITATE_CHV, Ascii.ETB, -60, -89, 126, Base64.padSymbol, ISOFileInfo.FMD_BYTE, 93, Ascii.EM, 115}, new byte[]{96, ISOFileInfo.DATA_BYTES2, 79, ISO7816.INS_UPDATE_RECORD, ISO7816.INS_MSE, ISO7816.INS_PSO, -112, -120, 70, -18, -72, Ascii.DC4, -34, 94, 11, -37}, new byte[]{ISO7816.INS_CREATE_FILE, ISO7816.INS_INCREASE, 58, 10, 73, 6, ISO7816.INS_CHANGE_CHV, 92, ISO7816.INS_ENVELOPE, -45, -84, ISOFileInfo.FCP_BYTE, -111, -107, ISO7816.INS_DELETE_FILE, 121}, new byte[]{-25, -56, 55, 109, ISOFileInfo.ENV_TEMP_EF, -43, 78, -87, 108, 86, -12, -22, 101, 122, -82, 8}, new byte[]{-70, 120, 37, 46, 28, -90, ISO7816.INS_READ_BINARY_STAMPED, -58, -24, -35, 116, Ascii.US, 75, -67, ISOFileInfo.SECURITY_ATTR_EXP, ISOFileInfo.LCS_BYTE}, new byte[]{ISO7816.INS_MANAGE_CHANNEL, 62, -75, 102, 72, 3, -10, 14, 97, 53, 87, -71, -122, -63, 29, -98}, new byte[]{-31, -8, -104, 17, 105, -39, ISOFileInfo.CHANNEL_SECURITY, -108, -101, 30, ISOFileInfo.FCI_EXT, -23, -50, 85, 40, -33}, new byte[]{ISOFileInfo.SECURITY_ATTR_COMPACT, ISOFileInfo.A1, -119, 13, -65, -26, CVCAFile.CAR_TAG, 104, 65, -103, 45, 15, ISO7816.INS_READ_BINARY, 84, -69, Ascii.SYN}};

    static byte[] aesEnc(byte[] bArr, byte[] bArr2) {
        byte[] bArrMixColumns = mixColumns(shiftRows(subBytes(bArr)));
        Bytes.xorTo(16, bArr2, bArrMixColumns);
        return bArrMixColumns;
    }

    private static byte[] mixColumns(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        int i = 0;
        for (int i2 = 0; i2 < 4; i2++) {
            int i3 = i2 * 4;
            int i4 = i3 + 1;
            int i5 = i3 + 2;
            int i6 = i3 + 3;
            bArr2[i] = (byte) ((((mulX(bArr[i3]) ^ mulX(bArr[i4])) ^ bArr[i4]) ^ bArr[i5]) ^ bArr[i6]);
            bArr2[i + 1] = (byte) ((((bArr[i3] ^ mulX(bArr[i4])) ^ mulX(bArr[i5])) ^ bArr[i5]) ^ bArr[i6]);
            int i7 = i + 3;
            bArr2[i + 2] = (byte) ((((bArr[i3] ^ bArr[i4]) ^ mulX(bArr[i5])) ^ mulX(bArr[i6])) ^ bArr[i6]);
            i += 4;
            bArr2[i7] = (byte) ((((mulX(bArr[i3]) ^ bArr[i3]) ^ bArr[i4]) ^ bArr[i5]) ^ mulX(bArr[i6]));
        }
        return bArr2;
    }

    static byte mulX(byte b) {
        return (byte) ((((b & 128) >> 7) * 27) ^ ((b & Byte.MAX_VALUE) << 1));
    }

    static byte sBox(byte b) {
        return S[(b & 255) >>> 4][b & 15];
    }

    static byte[] shiftRows(byte[] bArr) {
        return new byte[]{bArr[0], bArr[5], bArr[10], bArr[15], bArr[4], bArr[9], bArr[14], bArr[3], bArr[8], bArr[13], bArr[2], bArr[7], bArr[12], bArr[1], bArr[6], bArr[11]};
    }

    static byte[] subBytes(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        bArr2[0] = sBox(bArr[0]);
        bArr2[1] = sBox(bArr[1]);
        bArr2[2] = sBox(bArr[2]);
        bArr2[3] = sBox(bArr[3]);
        bArr2[4] = sBox(bArr[4]);
        bArr2[5] = sBox(bArr[5]);
        bArr2[6] = sBox(bArr[6]);
        bArr2[7] = sBox(bArr[7]);
        bArr2[8] = sBox(bArr[8]);
        bArr2[9] = sBox(bArr[9]);
        bArr2[10] = sBox(bArr[10]);
        bArr2[11] = sBox(bArr[11]);
        bArr2[12] = sBox(bArr[12]);
        bArr2[13] = sBox(bArr[13]);
        bArr2[14] = sBox(bArr[14]);
        bArr2[15] = sBox(bArr[15]);
        return bArr2;
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return 32;
    }
}
