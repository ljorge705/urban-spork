package org.spongycastle.crypto.engines;

import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import kotlin.io.encoding.Base64;
import net.sf.scuba.smartcards.ISO7816;
import net.sf.scuba.smartcards.ISOFileInfo;
import okio.Utf8;
import org.jmrtd.lds.CVCAFile;
import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Pack;

/* loaded from: classes4.dex */
public class DSTU7624Engine implements BlockCipher {
    private static final int BITS_IN_BYTE = 8;
    private static final int BITS_IN_LONG = 64;
    private static final int BITS_IN_WORD = 64;
    private static final int ROUNDS_128 = 10;
    private static final int ROUNDS_256 = 14;
    private static final int ROUNDS_512 = 18;
    private boolean forEncryption;
    private long[] internalState;
    private byte[] internalStateBytes;
    private long[][] roundKeys;
    private int roundsAmount;
    private byte[] tempInternalStateBytes;
    private int wordsInBlock;
    private int wordsInKey;
    private long[] workingKey;
    private byte[][] mdsMatrix = {new byte[]{1, 1, 5, 1, 8, 6, 7, 4}, new byte[]{4, 1, 1, 5, 1, 8, 6, 7}, new byte[]{7, 4, 1, 1, 5, 1, 8, 6}, new byte[]{6, 7, 4, 1, 1, 5, 1, 8}, new byte[]{8, 6, 7, 4, 1, 1, 5, 1}, new byte[]{1, 8, 6, 7, 4, 1, 1, 5}, new byte[]{5, 1, 8, 6, 7, 4, 1, 1}, new byte[]{1, 5, 1, 8, 6, 7, 4, 1}};
    private byte[][] mdsInvMatrix = {new byte[]{-83, -107, 118, -88, 47, 73, -41, ISO7816.INS_GET_DATA}, new byte[]{ISO7816.INS_GET_DATA, -83, -107, 118, -88, 47, 73, -41}, new byte[]{-41, ISO7816.INS_GET_DATA, -83, -107, 118, -88, 47, 73}, new byte[]{73, -41, ISO7816.INS_GET_DATA, -83, -107, 118, -88, 47}, new byte[]{47, 73, -41, ISO7816.INS_GET_DATA, -83, -107, 118, -88}, new byte[]{-88, 47, 73, -41, ISO7816.INS_GET_DATA, -83, -107, 118}, new byte[]{118, -88, 47, 73, -41, ISO7816.INS_GET_DATA, -83, -107}, new byte[]{-107, 118, -88, 47, 73, -41, ISO7816.INS_GET_DATA, -83}};
    private byte[][] sboxesForEncryption = {new byte[]{-88, 67, 95, 6, 107, 117, 108, 89, 113, -33, ISOFileInfo.FCI_EXT, -107, Ascii.ETB, -16, ISO7816.INS_LOAD_KEY_FILE, 9, 109, -13, 29, -53, -55, 77, ISO7816.INS_UNBLOCK_CHV, -81, 121, ISO7816.INS_CREATE_FILE, -105, -3, ISOFileInfo.FCI_BYTE, 75, 69, 57, 62, -35, -93, 79, ISO7816.INS_READ_BINARY_STAMPED, ISO7816.INS_READ_RECORD_STAMPED, -102, 14, Ascii.US, -65, Ascii.NAK, -31, 73, ISO7816.INS_WRITE_RECORD, -109, -58, -110, 114, -98, 97, -47, 99, -6, -18, -12, Ascii.EM, -43, -83, 88, -92, -69, ISOFileInfo.A1, ISO7816.INS_UPDATE_RECORD, -14, ISOFileInfo.FILE_IDENTIFIER, 55, CVCAFile.CAR_TAG, ISO7816.INS_DELETE_FILE, 122, ISO7816.INS_INCREASE, -100, -52, ISOFileInfo.AB, 74, -113, 110, 4, 39, 46, -25, ISO7816.INS_APPEND_RECORD, 90, -106, Ascii.SYN, 35, 43, ISO7816.INS_ENVELOPE, 101, 102, 15, -68, -87, 71, 65, ISO7816.INS_DECREASE_STAMPED, 72, -4, -73, 106, -120, ISOFileInfo.A5, 83, -122, -7, 91, -37, 56, 123, -61, 30, ISO7816.INS_MSE, 51, ISO7816.INS_CHANGE_CHV, 40, 54, -57, -78, 59, ISOFileInfo.CHANNEL_SECURITY, 119, -70, -11, Ascii.DC4, -97, 8, 85, -101, 76, -2, 96, 92, ISO7816.INS_PUT_DATA, Ascii.CAN, 70, -51, 125, 33, ISO7816.INS_READ_BINARY, Utf8.REPLACEMENT_BYTE, Ascii.ESC, -119, -1, -21, -124, 105, 58, -99, -41, -45, ISO7816.INS_MANAGE_CHANNEL, 103, SignedBytes.MAX_POWER_OF_TWO, -75, -34, 93, ISO7816.INS_DECREASE, -111, ISO7816.INS_READ_BINARY2, 120, 17, 1, -27, 0, 104, -104, ISOFileInfo.A0, -59, 2, -90, 116, 45, 11, -94, 118, ISO7816.INS_READ_RECORD2, -66, -50, -67, -82, -23, ISOFileInfo.LCS_BYTE, 49, 28, -20, -15, -103, -108, -86, -10, 38, 47, -17, -24, ISOFileInfo.SECURITY_ATTR_COMPACT, 53, 3, -44, Byte.MAX_VALUE, -5, 5, -63, 94, -112, 32, Base64.padSymbol, -126, -9, -22, 10, 13, 126, -8, 80, Ascii.SUB, -60, 7, 87, -72, 60, ISOFileInfo.FCP_BYTE, -29, -56, -84, 82, ISOFileInfo.FMD_BYTE, 16, ISO7816.INS_WRITE_BINARY, -39, 19, 12, Ascii.DC2, 41, 81, -71, -49, ISO7816.INS_UPDATE_BINARY, 115, ISOFileInfo.ENV_TEMP_EF, ISOFileInfo.DATA_BYTES2, 84, ISO7816.INS_GET_RESPONSE, -19, 78, ISO7816.INS_REHABILITATE_CHV, -89, ISO7816.INS_PSO, ISOFileInfo.PROP_INFO, 37, -26, ISO7816.INS_GET_DATA, 124, ISOFileInfo.SECURITY_ATTR_EXP, 86, Byte.MIN_VALUE}, new byte[]{-50, -69, -21, -110, -22, -53, 19, -63, -23, 58, ISO7816.INS_UPDATE_BINARY, -78, ISO7816.INS_WRITE_RECORD, -112, Ascii.ETB, -8, CVCAFile.CAR_TAG, Ascii.NAK, 86, ISO7816.INS_READ_BINARY_STAMPED, 101, 28, -120, 67, -59, 92, 54, -70, -11, 87, 103, ISOFileInfo.ENV_TEMP_EF, 49, -10, ISOFileInfo.FMD_BYTE, 88, -98, -12, ISO7816.INS_MSE, -86, 117, 15, 2, ISO7816.INS_READ_BINARY2, -33, 109, 115, 77, 124, 38, 46, -9, 8, 93, ISO7816.INS_REHABILITATE_CHV, 62, -97, Ascii.DC4, -56, -82, 84, 16, ISO7816.INS_LOAD_KEY_FILE, -68, Ascii.SUB, 107, 105, -13, -67, 51, ISOFileInfo.AB, -6, -47, -101, 104, 78, Ascii.SYN, -107, -111, -18, 76, 99, ISOFileInfo.CHANNEL_SECURITY, 91, -52, 60, Ascii.EM, ISOFileInfo.A1, ISOFileInfo.DATA_BYTES2, 73, 123, -39, ISOFileInfo.FCI_BYTE, 55, 96, ISO7816.INS_GET_DATA, -25, 43, 72, -3, -106, 69, -4, 65, Ascii.DC2, 13, 121, -27, -119, ISOFileInfo.SECURITY_ATTR_COMPACT, -29, 32, ISO7816.INS_DECREASE, ISO7816.INS_UPDATE_RECORD, -73, 108, 74, -75, Utf8.REPLACEMENT_BYTE, -105, -44, ISOFileInfo.FCP_BYTE, 45, 6, -92, ISOFileInfo.A5, ISOFileInfo.FILE_IDENTIFIER, 95, ISO7816.INS_PSO, ISO7816.INS_PUT_DATA, -55, 0, 126, -94, 85, -65, 17, -43, -100, -49, 14, 10, Base64.padSymbol, 81, 125, -109, Ascii.ESC, -2, -60, 71, 9, -122, 11, -113, -99, 106, 7, -71, ISO7816.INS_READ_BINARY, -104, Ascii.CAN, ISO7816.INS_INCREASE, 113, 75, -17, 59, ISO7816.INS_MANAGE_CHANNEL, ISOFileInfo.A0, ISO7816.INS_DELETE_FILE, SignedBytes.MAX_POWER_OF_TWO, -1, -61, -87, -26, 120, -7, ISOFileInfo.SECURITY_ATTR_EXP, 70, Byte.MIN_VALUE, 30, 56, -31, -72, -88, ISO7816.INS_CREATE_FILE, 12, 35, 118, 29, 37, ISO7816.INS_CHANGE_CHV, 5, -15, 110, -108, 40, -102, -124, -24, -93, 79, 119, -45, ISOFileInfo.PROP_INFO, ISO7816.INS_APPEND_RECORD, 82, -14, -126, 80, 122, 47, 116, 83, ISO7816.INS_READ_RECORD2, 97, -81, 57, 53, -34, -51, Ascii.US, -103, -84, -83, 114, ISO7816.INS_UNBLOCK_CHV, -35, ISO7816.INS_WRITE_BINARY, ISOFileInfo.FCI_EXT, -66, 94, -90, -20, 4, -58, 3, ISO7816.INS_DECREASE_STAMPED, -5, -37, 89, ISO7816.INS_READ_RECORD_STAMPED, ISO7816.INS_ENVELOPE, 1, -16, 90, -19, -89, 102, 33, Byte.MAX_VALUE, ISOFileInfo.LCS_BYTE, 39, -57, ISO7816.INS_GET_RESPONSE, 41, -41}, new byte[]{-109, -39, -102, -75, -104, ISO7816.INS_MSE, 69, -4, -70, 106, -33, 2, -97, ISO7816.INS_UPDATE_RECORD, 81, 89, 74, Ascii.ETB, 43, ISO7816.INS_ENVELOPE, -108, -12, -69, -93, ISOFileInfo.FCP_BYTE, ISO7816.INS_DELETE_FILE, 113, -44, -51, ISO7816.INS_MANAGE_CHANNEL, Ascii.SYN, -31, 73, 60, ISO7816.INS_GET_RESPONSE, ISO7816.INS_LOAD_KEY_FILE, 92, -101, -83, ISOFileInfo.PROP_INFO, 83, ISOFileInfo.A1, 122, -56, 45, ISO7816.INS_CREATE_FILE, -47, 114, -90, ISO7816.INS_UNBLOCK_CHV, -60, -29, 118, 120, -73, ISO7816.INS_READ_BINARY_STAMPED, 9, 59, 14, 65, 76, -34, -78, -112, 37, ISOFileInfo.A5, -41, 3, 17, 0, -61, 46, -110, -17, 78, Ascii.DC2, -99, 125, -53, 53, 16, -43, 79, -98, 77, -87, 85, -58, ISO7816.INS_WRITE_BINARY, 123, Ascii.CAN, -105, -45, 54, -26, 72, 86, ISOFileInfo.DATA_BYTES2, -113, 119, -52, -100, -71, ISO7816.INS_APPEND_RECORD, -84, -72, 47, Ascii.NAK, -92, 124, ISO7816.INS_PUT_DATA, 56, 30, 11, 5, ISO7816.INS_UPDATE_BINARY, Ascii.DC4, 110, 108, 126, 102, -3, ISO7816.INS_READ_BINARY2, -27, 96, -81, 94, 51, ISOFileInfo.FCI_EXT, -55, -16, 93, 109, Utf8.REPLACEMENT_BYTE, -120, ISOFileInfo.ENV_TEMP_EF, -57, -9, 29, -23, -20, -19, Byte.MIN_VALUE, 41, 39, -49, -103, -88, 80, 15, 55, ISO7816.INS_CHANGE_CHV, 40, ISO7816.INS_DECREASE, -107, ISO7816.INS_WRITE_RECORD, 62, 91, SignedBytes.MAX_POWER_OF_TWO, ISOFileInfo.FILE_IDENTIFIER, ISO7816.INS_READ_RECORD2, 105, 87, Ascii.US, 7, 28, ISOFileInfo.LCS_BYTE, -68, 32, -21, -50, ISOFileInfo.CHANNEL_SECURITY, ISOFileInfo.AB, -18, 49, -94, 115, -7, ISO7816.INS_GET_DATA, 58, Ascii.SUB, -5, 13, -63, -2, -6, -14, ISOFileInfo.FCI_BYTE, -67, -106, -35, 67, 82, ISO7816.INS_READ_RECORD_STAMPED, 8, -13, -82, -66, Ascii.EM, -119, ISO7816.INS_INCREASE, 38, ISO7816.INS_READ_BINARY, -22, 75, ISOFileInfo.FMD_BYTE, -124, -126, 107, -11, 121, -65, 1, 95, 117, 99, Ascii.ESC, 35, Base64.padSymbol, 104, ISO7816.INS_PSO, 101, -24, -111, -10, -1, 19, 88, -15, 71, 10, Byte.MAX_VALUE, -59, -89, -25, 97, 90, 6, 70, ISO7816.INS_REHABILITATE_CHV, CVCAFile.CAR_TAG, 4, ISOFileInfo.A0, -37, 57, -122, 84, -86, ISOFileInfo.SECURITY_ATTR_COMPACT, ISO7816.INS_DECREASE_STAMPED, 33, ISOFileInfo.SECURITY_ATTR_EXP, -8, 12, 116, 103}, new byte[]{104, ISOFileInfo.ENV_TEMP_EF, ISO7816.INS_GET_DATA, 77, 115, 75, 78, ISO7816.INS_PSO, -44, 82, 38, ISO7816.INS_READ_RECORD2, 84, 30, Ascii.EM, Ascii.US, ISO7816.INS_MSE, 3, 70, Base64.padSymbol, 45, 74, 83, ISOFileInfo.FILE_IDENTIFIER, 19, ISOFileInfo.LCS_BYTE, -73, -43, 37, 121, -11, -67, 88, 47, 13, 2, -19, 81, -98, 17, -14, 62, 85, 94, -47, Ascii.SYN, 60, 102, ISO7816.INS_MANAGE_CHANNEL, 93, -13, 69, SignedBytes.MAX_POWER_OF_TWO, -52, -24, -108, 86, 8, -50, Ascii.SUB, 58, ISO7816.INS_WRITE_RECORD, -31, -33, -75, 56, 110, 14, -27, -12, -7, -122, -23, 79, ISO7816.INS_UPDATE_BINARY, ISOFileInfo.PROP_INFO, 35, -49, ISO7816.INS_INCREASE, -103, 49, Ascii.DC4, -82, -18, -56, 72, -45, ISO7816.INS_DECREASE, ISOFileInfo.A1, -110, 65, ISO7816.INS_READ_BINARY2, Ascii.CAN, -60, ISO7816.INS_UNBLOCK_CHV, 113, 114, ISO7816.INS_REHABILITATE_CHV, Ascii.NAK, -3, 55, -66, 95, -86, -101, -120, ISO7816.INS_LOAD_KEY_FILE, ISOFileInfo.AB, -119, -100, -6, 96, -22, -68, ISOFileInfo.FCP_BYTE, 12, ISO7816.INS_CHANGE_CHV, -90, -88, -20, 103, 32, -37, 124, 40, -35, -84, 91, ISO7816.INS_DECREASE_STAMPED, 126, 16, -15, 123, -113, 99, ISOFileInfo.A0, 5, -102, 67, 119, 33, -65, 39, 9, -61, -97, ISO7816.INS_READ_RECORD_STAMPED, -41, 41, ISO7816.INS_ENVELOPE, -21, ISO7816.INS_GET_RESPONSE, -92, ISOFileInfo.SECURITY_ATTR_EXP, ISOFileInfo.SECURITY_ATTR_COMPACT, 29, -5, -1, -63, -78, -105, 46, -8, 101, -10, 117, 7, 4, 73, 51, ISO7816.INS_DELETE_FILE, -39, -71, ISO7816.INS_WRITE_BINARY, CVCAFile.CAR_TAG, -57, 108, -112, 0, ISOFileInfo.CHANNEL_SECURITY, ISOFileInfo.FCI_BYTE, 80, 1, -59, ISO7816.INS_PUT_DATA, 71, Utf8.REPLACEMENT_BYTE, -51, 105, -94, ISO7816.INS_APPEND_RECORD, 122, -89, -58, -109, 15, 10, 6, -26, 43, -106, -93, 28, -81, 106, Ascii.DC2, -124, 57, -25, ISO7816.INS_READ_BINARY, -126, -9, -2, -99, ISOFileInfo.FCI_EXT, 92, ISOFileInfo.DATA_BYTES2, 53, -34, ISO7816.INS_READ_BINARY_STAMPED, ISOFileInfo.A5, -4, Byte.MIN_VALUE, -17, -53, -69, 107, 118, -70, 90, 125, 120, 11, -107, -29, -83, 116, -104, 59, 54, ISOFileInfo.FMD_BYTE, 109, ISO7816.INS_UPDATE_RECORD, -16, 89, -87, 76, Ascii.ETB, Byte.MAX_VALUE, -111, -72, -55, 87, Ascii.ESC, ISO7816.INS_CREATE_FILE, 97}};
    private byte[][] sboxesForDecryption = {new byte[]{-92, -94, -87, -59, 78, -55, 3, -39, 126, 15, ISO7816.INS_WRITE_RECORD, -83, -25, -45, 39, 91, -29, ISOFileInfo.A1, -24, -26, 124, ISO7816.INS_PSO, 85, 12, -122, 57, -41, ISOFileInfo.ENV_TEMP_EF, -72, Ascii.DC2, ISOFileInfo.FCI_BYTE, 40, -51, ISOFileInfo.LCS_BYTE, ISO7816.INS_MANAGE_CHANNEL, 86, 114, -7, -65, 79, 115, -23, -9, 87, Ascii.SYN, -84, 80, ISO7816.INS_GET_RESPONSE, -99, -73, 71, 113, 96, -60, 116, 67, 108, Ascii.US, -109, 119, ISO7816.INS_UPDATE_RECORD, -50, 32, ISOFileInfo.SECURITY_ATTR_COMPACT, -103, 95, ISO7816.INS_REHABILITATE_CHV, 1, -11, 30, ISOFileInfo.FCI_EXT, 94, 97, ISO7816.INS_UNBLOCK_CHV, 75, 29, ISOFileInfo.DATA_BYTES2, Ascii.NAK, -12, 35, ISO7816.INS_UPDATE_BINARY, -22, -31, 103, -15, Byte.MAX_VALUE, -2, ISO7816.INS_PUT_DATA, 60, 7, 83, 106, -124, -100, -53, 2, ISOFileInfo.FILE_IDENTIFIER, 51, -35, 53, ISO7816.INS_APPEND_RECORD, 89, 90, -104, ISOFileInfo.A5, -110, ISOFileInfo.FMD_BYTE, 4, 6, 16, 77, 28, -105, 8, 49, -18, ISOFileInfo.AB, 5, -81, 121, ISOFileInfo.A0, Ascii.CAN, 70, 109, -4, -119, -44, -57, -1, -16, -49, CVCAFile.CAR_TAG, -111, -8, 104, 10, 101, ISOFileInfo.CHANNEL_SECURITY, ISO7816.INS_READ_RECORD_STAMPED, -3, -61, -17, 120, 76, -52, -98, ISO7816.INS_DECREASE, 46, -68, 11, 84, Ascii.SUB, -90, -69, 38, Byte.MIN_VALUE, 72, -108, ISO7816.INS_INCREASE, 125, -89, Utf8.REPLACEMENT_BYTE, -82, ISO7816.INS_MSE, Base64.padSymbol, 102, -86, -10, 0, 93, -67, 74, ISO7816.INS_CREATE_FILE, 59, ISO7816.INS_READ_BINARY_STAMPED, Ascii.ETB, ISOFileInfo.SECURITY_ATTR_EXP, -97, 118, ISO7816.INS_READ_BINARY, ISO7816.INS_CHANGE_CHV, -102, 37, 99, -37, -21, 122, 62, 92, ISO7816.INS_READ_RECORD2, ISO7816.INS_READ_BINARY2, 41, -14, ISO7816.INS_GET_DATA, 88, 110, ISO7816.INS_LOAD_KEY_FILE, -88, 47, 117, -33, Ascii.DC4, -5, 19, 73, -120, -78, -20, ISO7816.INS_DELETE_FILE, ISO7816.INS_DECREASE_STAMPED, 45, -106, -58, 58, -19, -107, 14, -27, ISOFileInfo.PROP_INFO, 107, SignedBytes.MAX_POWER_OF_TWO, 33, -101, 9, Ascii.EM, 43, 82, -34, 69, -93, -6, 81, ISO7816.INS_ENVELOPE, -75, -47, -112, -71, -13, 55, -63, 13, -70, 65, 17, 56, 123, -66, ISO7816.INS_WRITE_BINARY, -43, 105, 54, -56, ISOFileInfo.FCP_BYTE, Ascii.ESC, -126, -113}, new byte[]{ISOFileInfo.FILE_IDENTIFIER, -14, ISO7816.INS_PSO, -21, -23, -65, 123, -100, ISO7816.INS_DECREASE_STAMPED, -106, ISOFileInfo.ENV_TEMP_EF, -104, -71, 105, ISOFileInfo.SECURITY_ATTR_COMPACT, 41, Base64.padSymbol, -120, 104, 6, 57, 17, 76, 14, ISOFileInfo.A0, 86, SignedBytes.MAX_POWER_OF_TWO, -110, Ascii.NAK, -68, ISO7816.INS_READ_RECORD2, ISO7816.INS_UPDATE_RECORD, ISOFileInfo.FCI_BYTE, -8, 38, -70, -66, -67, 49, -5, -61, -2, Byte.MIN_VALUE, 97, -31, 122, ISO7816.INS_INCREASE, ISO7816.INS_WRITE_RECORD, ISO7816.INS_MANAGE_CHANNEL, 32, ISOFileInfo.A1, 69, -20, -39, Ascii.SUB, 93, ISO7816.INS_READ_BINARY_STAMPED, ISO7816.INS_LOAD_KEY_FILE, 9, ISOFileInfo.A5, 85, ISOFileInfo.CHANNEL_SECURITY, 55, 118, -87, 103, 16, Ascii.ETB, 54, 101, ISO7816.INS_READ_BINARY2, -107, ISOFileInfo.FCP_BYTE, 89, 116, -93, 80, 47, 75, -56, ISO7816.INS_WRITE_BINARY, -113, -51, -44, 60, -122, Ascii.DC2, 29, 35, -17, -12, 83, Ascii.EM, 53, -26, Byte.MAX_VALUE, 94, ISO7816.INS_UPDATE_BINARY, 121, 81, ISO7816.INS_MSE, Ascii.DC4, -9, 30, 74, CVCAFile.CAR_TAG, -101, 65, 115, 45, -63, 92, -90, -94, ISO7816.INS_CREATE_FILE, 46, -45, 40, -69, -55, -82, 106, -47, 90, ISO7816.INS_DECREASE, -112, -124, -7, -78, 88, -49, 126, -59, -53, -105, ISO7816.INS_DELETE_FILE, Ascii.SYN, 108, -6, ISO7816.INS_READ_BINARY, 109, Ascii.US, 82, -103, 13, 78, 3, -111, ISO7816.INS_ENVELOPE, 77, ISOFileInfo.FMD_BYTE, 119, -97, -35, -60, 73, ISOFileInfo.LCS_BYTE, -102, ISO7816.INS_CHANGE_CHV, 56, -89, 87, ISOFileInfo.PROP_INFO, -57, 124, 125, -25, -10, -73, -84, 39, 70, -34, -33, 59, -41, -98, 43, 11, -43, 19, 117, -16, 114, ISO7816.INS_READ_RECORD_STAMPED, -99, Ascii.ESC, 1, Utf8.REPLACEMENT_BYTE, ISO7816.INS_REHABILITATE_CHV, -27, ISOFileInfo.FCI_EXT, -3, 7, -15, ISOFileInfo.AB, -108, Ascii.CAN, -22, -4, 58, -126, 95, 5, 84, -37, 0, ISOFileInfo.SECURITY_ATTR_EXP, -29, 72, 12, ISO7816.INS_GET_DATA, 120, -119, 10, -1, 62, 91, ISOFileInfo.DATA_BYTES2, -18, 113, ISO7816.INS_APPEND_RECORD, ISO7816.INS_PUT_DATA, ISO7816.INS_UNBLOCK_CHV, -72, -75, -52, 110, -88, 107, -83, 96, -58, 8, 4, 2, -24, -11, 79, -92, -13, ISO7816.INS_GET_RESPONSE, -50, 67, 37, 28, 33, 51, 15, -81, 71, -19, 102, 99, -109, -86}, new byte[]{69, -44, 11, 67, -15, 114, -19, -92, ISO7816.INS_ENVELOPE, 56, -26, 113, -3, ISO7816.INS_READ_RECORD_STAMPED, 58, -107, 80, ISO7816.INS_REHABILITATE_CHV, 75, ISO7816.INS_APPEND_RECORD, 116, 107, 30, 17, 90, -58, ISO7816.INS_READ_BINARY_STAMPED, ISO7816.INS_LOAD_KEY_FILE, ISOFileInfo.A5, ISOFileInfo.LCS_BYTE, ISO7816.INS_MANAGE_CHANNEL, -93, -88, -6, 5, -39, -105, SignedBytes.MAX_POWER_OF_TWO, -55, -112, -104, -113, ISO7816.INS_UPDATE_RECORD, Ascii.DC2, 49, ISO7816.INS_UNBLOCK_CHV, 71, 106, -103, -82, -56, Byte.MAX_VALUE, -7, 79, 93, -106, ISOFileInfo.FCI_BYTE, -12, ISO7816.INS_READ_RECORD2, 57, 33, ISO7816.INS_PUT_DATA, -100, ISOFileInfo.PROP_INFO, -98, 59, -16, -65, -17, 6, -18, -27, 95, 32, 16, -52, 60, 84, 74, 82, -108, 14, ISO7816.INS_GET_RESPONSE, 40, -10, 86, 96, -94, -29, 15, -20, -99, ISO7816.INS_CHANGE_CHV, ISOFileInfo.FILE_IDENTIFIER, 126, -43, 124, -21, Ascii.CAN, -41, -51, -35, 120, -1, -37, ISOFileInfo.A1, 9, ISO7816.INS_WRITE_BINARY, 118, -124, 117, -69, 29, Ascii.SUB, 47, ISO7816.INS_READ_BINARY, -2, ISO7816.INS_UPDATE_BINARY, ISO7816.INS_DECREASE_STAMPED, 99, 53, ISO7816.INS_WRITE_RECORD, ISO7816.INS_PSO, 89, 109, 77, 119, -25, ISOFileInfo.CHANNEL_SECURITY, 97, -49, -97, -50, 39, -11, Byte.MIN_VALUE, -122, -57, -90, -5, -8, ISOFileInfo.FCI_EXT, ISOFileInfo.AB, ISOFileInfo.FCP_BYTE, Utf8.REPLACEMENT_BYTE, -33, 72, 0, Ascii.DC4, -102, -67, 91, 4, -110, 2, 37, 101, 76, 83, 12, -14, 41, -81, Ascii.ETB, 108, 65, ISO7816.INS_DECREASE, -23, -109, 85, -9, -84, 104, 38, -60, 125, ISO7816.INS_GET_DATA, 122, 62, ISOFileInfo.A0, 55, 3, -63, 54, 105, 102, 8, Ascii.SYN, -89, -68, -59, -45, ISO7816.INS_MSE, -73, 19, 70, ISO7816.INS_INCREASE, -24, 87, -120, 43, ISOFileInfo.DATA_BYTES2, -78, 78, ISOFileInfo.FMD_BYTE, 28, -86, -111, 88, 46, -101, 92, Ascii.ESC, 81, 115, CVCAFile.CAR_TAG, 35, 1, 110, -13, 13, -66, Base64.padSymbol, 10, 45, Ascii.US, 103, 51, Ascii.EM, 123, 94, -22, -34, ISOFileInfo.SECURITY_ATTR_EXP, -53, -87, ISOFileInfo.SECURITY_ATTR_COMPACT, ISOFileInfo.ENV_TEMP_EF, -83, 73, -126, ISO7816.INS_DELETE_FILE, -70, -61, Ascii.NAK, -47, ISO7816.INS_CREATE_FILE, -119, -4, ISO7816.INS_READ_BINARY2, -71, -75, 7, 121, -72, -31}, new byte[]{-78, ISO7816.INS_READ_RECORD_STAMPED, 35, 17, -89, -120, -59, -90, 57, -113, -60, -24, 115, ISO7816.INS_MSE, 67, -61, -126, 39, -51, Ascii.CAN, 81, ISOFileInfo.FCP_BYTE, 45, -9, 92, 14, 59, -3, ISO7816.INS_GET_DATA, -101, 13, 15, 121, ISOFileInfo.SECURITY_ATTR_COMPACT, 16, 76, 116, 28, 10, ISOFileInfo.CHANNEL_SECURITY, 124, -108, 7, -57, 94, Ascii.DC4, ISOFileInfo.A1, 33, 87, 80, 78, -87, Byte.MIN_VALUE, -39, -17, ISOFileInfo.FMD_BYTE, 65, -49, 60, -18, 46, 19, 41, -70, ISO7816.INS_DECREASE_STAMPED, 90, -82, ISOFileInfo.LCS_BYTE, 97, 51, Ascii.DC2, -71, 85, -88, Ascii.NAK, 5, -10, 3, 6, 73, -75, 37, 9, Ascii.SYN, 12, ISO7816.INS_PSO, 56, -4, 32, -12, -27, Byte.MAX_VALUE, -41, 49, 43, 102, ISOFileInfo.FCI_BYTE, -1, 114, -122, -16, -93, 47, 120, 0, -68, -52, ISO7816.INS_APPEND_RECORD, ISO7816.INS_READ_BINARY, -15, CVCAFile.CAR_TAG, ISO7816.INS_READ_BINARY_STAMPED, ISO7816.INS_DECREASE, 95, 96, 4, -20, ISOFileInfo.A5, -29, ISOFileInfo.SECURITY_ATTR_EXP, -25, 29, -65, -124, 123, -26, ISOFileInfo.DATA_BYTES2, -8, -34, ISO7816.INS_LOAD_KEY_FILE, ISO7816.INS_WRITE_RECORD, Ascii.ETB, -50, 75, 71, ISO7816.INS_UPDATE_BINARY, 105, 108, Ascii.EM, -103, -102, 1, ISO7816.INS_READ_RECORD2, ISOFileInfo.PROP_INFO, ISO7816.INS_READ_BINARY2, -7, 89, ISO7816.INS_ENVELOPE, 55, -23, -56, ISOFileInfo.A0, -19, 79, -119, 104, 109, -43, 38, -111, ISOFileInfo.FCI_EXT, 88, -67, -55, -104, ISO7816.INS_UPDATE_RECORD, 117, ISO7816.INS_GET_RESPONSE, 118, -11, 103, 107, 126, -21, 82, -53, -47, 91, -97, 11, -37, SignedBytes.MAX_POWER_OF_TWO, -110, Ascii.SUB, -6, -84, ISO7816.INS_DELETE_FILE, -31, 113, Ascii.US, 101, ISOFileInfo.ENV_TEMP_EF, -105, -98, -107, -112, 93, -73, -63, -81, 84, -5, 2, ISO7816.INS_CREATE_FILE, 53, -69, 58, 77, -83, ISO7816.INS_UNBLOCK_CHV, Base64.padSymbol, 86, 8, Ascii.ESC, 74, -109, 106, ISOFileInfo.AB, -72, 122, -14, 125, ISO7816.INS_PUT_DATA, Utf8.REPLACEMENT_BYTE, -2, 62, -66, -22, -86, ISO7816.INS_REHABILITATE_CHV, -58, ISO7816.INS_WRITE_BINARY, 54, 72, ISO7816.INS_MANAGE_CHANNEL, -106, 119, ISO7816.INS_CHANGE_CHV, 83, -33, -13, ISOFileInfo.FILE_IDENTIFIER, 40, ISO7816.INS_INCREASE, 69, 30, -92, -45, -94, 70, 110, -100, -35, 99, -44, -99}};

    private byte MultiplyGF(byte b, byte b2) {
        int i = b & 255;
        int i2 = b2 & 255;
        int i3 = (-(b2 & 1)) & i;
        for (int i4 = 1; i4 < 8; i4++) {
            i <<= 1;
            i2 >>>= 1;
            i3 ^= (-(i2 & 1)) & i;
        }
        int i5 = 65280 & i3;
        int i6 = ((i5 >>> 8) ^ ((((i5 >>> 4) ^ i5) ^ (i5 >>> 5)) ^ (i5 >>> 6))) ^ i3;
        int i7 = i6 & 3840;
        return (byte) (i6 ^ ((i7 >>> 8) ^ ((((i7 >>> 4) ^ i7) ^ (i7 >>> 5)) ^ (i7 >>> 6))));
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "DSTU7624";
    }

    public DSTU7624Engine(int i) throws IllegalArgumentException {
        if (i != 128 && i != 256 && i != 512) {
            throw new IllegalArgumentException("unsupported block length: only 128/256/512 are allowed");
        }
        int i2 = i / 64;
        this.wordsInBlock = i2;
        long[] jArr = new long[i2];
        this.internalState = jArr;
        this.internalStateBytes = new byte[(jArr.length * 64) / 8];
        this.tempInternalStateBytes = new byte[(jArr.length * 64) / 8];
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        if (cipherParameters instanceof KeyParameter) {
            this.forEncryption = z;
            byte[] key = ((KeyParameter) cipherParameters).getKey();
            int length = key.length * 8;
            int i = this.wordsInBlock * 64;
            if (length != 128 && length != 256 && length != 512) {
                throw new IllegalArgumentException("unsupported key length: only 128/256/512 are allowed");
            }
            if (i == 128 && length == 512) {
                throw new IllegalArgumentException("Unsupported key length");
            }
            if (i == 256 && length == 128) {
                throw new IllegalArgumentException("Unsupported key length");
            }
            if (i == 512 && length != 512) {
                throw new IllegalArgumentException("Unsupported key length");
            }
            if (length == 128) {
                this.roundsAmount = 10;
            } else if (length == 256) {
                this.roundsAmount = 14;
            } else if (length == 512) {
                this.roundsAmount = 18;
            }
            this.wordsInKey = length / 64;
            this.roundKeys = new long[this.roundsAmount + 1][];
            int i2 = 0;
            while (true) {
                long[][] jArr = this.roundKeys;
                if (i2 >= jArr.length) {
                    break;
                }
                jArr[i2] = new long[this.wordsInBlock];
                i2++;
            }
            int i3 = this.wordsInKey;
            long[] jArr2 = new long[i3];
            this.workingKey = jArr2;
            if (key.length != (i3 * 64) / 8) {
                throw new IllegalArgumentException("Invalid key parameter passed to DSTU7624Engine init");
            }
            Pack.littleEndianToLong(key, 0, jArr2);
            long[] jArr3 = new long[this.wordsInBlock];
            workingKeyExpandKT(this.workingKey, jArr3);
            workingKeyExpandEven(this.workingKey, jArr3);
            workingKeyExpandOdd();
            return;
        }
        throw new IllegalArgumentException("Invalid parameter passed to DSTU7624Engine init");
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public int getBlockSize() {
        return (this.wordsInBlock * 64) / 8;
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws IllegalStateException, DataLengthException {
        if (this.workingKey == null) {
            throw new IllegalStateException("DSTU7624 engine not initialised");
        }
        if (getBlockSize() + i > bArr.length) {
            throw new DataLengthException("Input buffer too short");
        }
        if (getBlockSize() + i2 > bArr2.length) {
            throw new OutputLengthException("Output buffer too short");
        }
        if (this.forEncryption) {
            Pack.littleEndianToLong(bArr, i, this.internalState);
            for (int i3 = 0; i3 < this.wordsInBlock; i3++) {
                long[] jArr = this.internalState;
                jArr[i3] = jArr[i3] + this.roundKeys[0][i3];
            }
            for (int i4 = 1; i4 < this.roundsAmount; i4++) {
                SubBytes();
                ShiftRows();
                MixColumns(this.mdsMatrix);
                for (int i5 = 0; i5 < this.wordsInBlock; i5++) {
                    long[] jArr2 = this.internalState;
                    jArr2[i5] = jArr2[i5] ^ this.roundKeys[i4][i5];
                }
            }
            SubBytes();
            ShiftRows();
            MixColumns(this.mdsMatrix);
            for (int i6 = 0; i6 < this.wordsInBlock; i6++) {
                long[] jArr3 = this.internalState;
                jArr3[i6] = jArr3[i6] + this.roundKeys[this.roundsAmount][i6];
            }
            Pack.longToLittleEndian(this.internalState, bArr2, i2);
        } else {
            int i7 = this.roundsAmount;
            Pack.littleEndianToLong(bArr, i, this.internalState);
            for (int i8 = 0; i8 < this.wordsInBlock; i8++) {
                long[] jArr4 = this.internalState;
                jArr4[i8] = jArr4[i8] - this.roundKeys[i7][i8];
            }
            for (int i9 = this.roundsAmount - 1; i9 > 0; i9--) {
                MixColumns(this.mdsInvMatrix);
                InvShiftRows();
                InvSubBytes();
                for (int i10 = 0; i10 < this.wordsInBlock; i10++) {
                    long[] jArr5 = this.internalState;
                    jArr5[i10] = jArr5[i10] ^ this.roundKeys[i9][i10];
                }
            }
            MixColumns(this.mdsInvMatrix);
            InvShiftRows();
            InvSubBytes();
            for (int i11 = 0; i11 < this.wordsInBlock; i11++) {
                long[] jArr6 = this.internalState;
                jArr6[i11] = jArr6[i11] - this.roundKeys[0][i11];
            }
            Pack.longToLittleEndian(this.internalState, bArr2, i2);
        }
        return getBlockSize();
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public void reset() {
        Arrays.fill(this.internalState, 0L);
        Arrays.fill(this.internalStateBytes, (byte) 0);
        Arrays.fill(this.tempInternalStateBytes, (byte) 0);
    }

    private void workingKeyExpandKT(long[] jArr, long[] jArr2) {
        int i = this.wordsInBlock;
        long[] jArr3 = new long[i];
        long[] jArr4 = new long[i];
        long[] jArr5 = new long[i];
        this.internalState = jArr5;
        long j = jArr5[0];
        int i2 = this.wordsInKey;
        jArr5[0] = j + i + i2 + 1;
        if (i == i2) {
            System.arraycopy(jArr, 0, jArr3, 0, i);
            System.arraycopy(jArr, 0, jArr4, 0, i);
        } else {
            System.arraycopy(jArr, 0, jArr3, 0, i);
            int i3 = this.wordsInBlock;
            System.arraycopy(jArr, i3, jArr4, 0, i3);
        }
        int i4 = 0;
        while (true) {
            long[] jArr6 = this.internalState;
            if (i4 >= jArr6.length) {
                break;
            }
            jArr6[i4] = jArr6[i4] + jArr3[i4];
            i4++;
        }
        SubBytes();
        ShiftRows();
        MixColumns(this.mdsMatrix);
        int i5 = 0;
        while (true) {
            long[] jArr7 = this.internalState;
            if (i5 >= jArr7.length) {
                break;
            }
            jArr7[i5] = jArr7[i5] ^ jArr4[i5];
            i5++;
        }
        SubBytes();
        ShiftRows();
        MixColumns(this.mdsMatrix);
        int i6 = 0;
        while (true) {
            long[] jArr8 = this.internalState;
            if (i6 < jArr8.length) {
                jArr8[i6] = jArr8[i6] + jArr3[i6];
                i6++;
            } else {
                SubBytes();
                ShiftRows();
                MixColumns(this.mdsMatrix);
                System.arraycopy(this.internalState, 0, jArr2, 0, this.wordsInBlock);
                return;
            }
        }
    }

    private void workingKeyExpandEven(long[] jArr, long[] jArr2) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5 = this.wordsInKey;
        long[] jArr3 = new long[i5];
        int i6 = this.wordsInBlock;
        long[] jArr4 = new long[i6];
        long[] jArr5 = new long[i6];
        System.arraycopy(jArr, 0, jArr3, 0, i5);
        for (int i7 = 0; i7 < this.wordsInBlock; i7++) {
            jArr5[i7] = 281479271743489L;
        }
        int i8 = 0;
        while (true) {
            System.arraycopy(jArr2, 0, this.internalState, 0, this.wordsInBlock);
            int i9 = 0;
            while (true) {
                i = this.wordsInBlock;
                if (i9 >= i) {
                    break;
                }
                long[] jArr6 = this.internalState;
                jArr6[i9] = jArr6[i9] + jArr5[i9];
                i9++;
            }
            System.arraycopy(this.internalState, 0, jArr4, 0, i);
            System.arraycopy(jArr3, 0, this.internalState, 0, this.wordsInBlock);
            for (int i10 = 0; i10 < this.wordsInBlock; i10++) {
                long[] jArr7 = this.internalState;
                jArr7[i10] = jArr7[i10] + jArr4[i10];
            }
            SubBytes();
            ShiftRows();
            MixColumns(this.mdsMatrix);
            for (int i11 = 0; i11 < this.wordsInBlock; i11++) {
                long[] jArr8 = this.internalState;
                jArr8[i11] = jArr8[i11] ^ jArr4[i11];
            }
            SubBytes();
            ShiftRows();
            MixColumns(this.mdsMatrix);
            int i12 = 0;
            while (true) {
                i2 = this.wordsInBlock;
                if (i12 >= i2) {
                    break;
                }
                long[] jArr9 = this.internalState;
                jArr9[i12] = jArr9[i12] + jArr4[i12];
                i12++;
            }
            System.arraycopy(this.internalState, 0, this.roundKeys[i8], 0, i2);
            if (this.roundsAmount == i8) {
                return;
            }
            if (this.wordsInBlock != this.wordsInKey) {
                i8 += 2;
                ShiftLeft(jArr5);
                System.arraycopy(jArr2, 0, this.internalState, 0, this.wordsInBlock);
                int i13 = 0;
                while (true) {
                    i3 = this.wordsInBlock;
                    if (i13 >= i3) {
                        break;
                    }
                    long[] jArr10 = this.internalState;
                    jArr10[i13] = jArr10[i13] + jArr5[i13];
                    i13++;
                }
                System.arraycopy(this.internalState, 0, jArr4, 0, i3);
                int i14 = this.wordsInBlock;
                System.arraycopy(jArr3, i14, this.internalState, 0, i14);
                for (int i15 = 0; i15 < this.wordsInBlock; i15++) {
                    long[] jArr11 = this.internalState;
                    jArr11[i15] = jArr11[i15] + jArr4[i15];
                }
                SubBytes();
                ShiftRows();
                MixColumns(this.mdsMatrix);
                for (int i16 = 0; i16 < this.wordsInBlock; i16++) {
                    long[] jArr12 = this.internalState;
                    jArr12[i16] = jArr12[i16] ^ jArr4[i16];
                }
                SubBytes();
                ShiftRows();
                MixColumns(this.mdsMatrix);
                int i17 = 0;
                while (true) {
                    i4 = this.wordsInBlock;
                    if (i17 >= i4) {
                        break;
                    }
                    long[] jArr13 = this.internalState;
                    jArr13[i17] = jArr13[i17] + jArr4[i17];
                    i17++;
                }
                System.arraycopy(this.internalState, 0, this.roundKeys[i8], 0, i4);
                if (this.roundsAmount == i8) {
                    return;
                }
            }
            i8 += 2;
            ShiftLeft(jArr5);
            long j = jArr3[0];
            int i18 = i5 - 1;
            System.arraycopy(jArr3, 1, jArr3, 0, i18);
            jArr3[i18] = j;
        }
    }

    private void workingKeyExpandOdd() {
        for (int i = 1; i < this.roundsAmount; i += 2) {
            long[][] jArr = this.roundKeys;
            System.arraycopy(jArr[i - 1], 0, jArr[i], 0, this.wordsInBlock);
            RotateLeft(this.roundKeys[i]);
        }
    }

    private void SubBytes() {
        for (int i = 0; i < this.wordsInBlock; i++) {
            long[] jArr = this.internalState;
            byte[][] bArr = this.sboxesForEncryption;
            byte[] bArr2 = bArr[0];
            long j = jArr[i];
            byte[] bArr3 = bArr[1];
            byte[] bArr4 = bArr[2];
            byte[] bArr5 = bArr[3];
            jArr[i] = ((bArr5[(int) ((j & (-72057594037927936L)) >>> 56)] << 56) & (-72057594037927936L)) | (255 & bArr2[(int) (j & 255)]) | (65280 & (bArr3[(int) ((j & 65280) >>> 8)] << 8)) | (16711680 & (bArr4[(int) ((j & 16711680) >>> 16)] << 16)) | (4278190080L & (bArr5[(int) ((j & 4278190080L) >>> 24)] << 24)) | (1095216660480L & (bArr2[(int) ((j & 1095216660480L) >>> 32)] << 32)) | (280375465082880L & (bArr3[(int) ((j & 280375465082880L) >>> 40)] << 40)) | ((bArr4[(int) ((j & 71776119061217280L) >>> 48)] << 48) & 71776119061217280L);
        }
    }

    private void InvSubBytes() {
        for (int i = 0; i < this.wordsInBlock; i++) {
            long[] jArr = this.internalState;
            byte[][] bArr = this.sboxesForDecryption;
            byte[] bArr2 = bArr[0];
            long j = jArr[i];
            byte[] bArr3 = bArr[1];
            byte[] bArr4 = bArr[2];
            byte[] bArr5 = bArr[3];
            jArr[i] = ((bArr5[(int) ((j & (-72057594037927936L)) >>> 56)] << 56) & (-72057594037927936L)) | (255 & bArr2[(int) (j & 255)]) | (65280 & (bArr3[(int) ((j & 65280) >>> 8)] << 8)) | (16711680 & (bArr4[(int) ((j & 16711680) >>> 16)] << 16)) | (4278190080L & (bArr5[(int) ((j & 4278190080L) >>> 24)] << 24)) | (1095216660480L & (bArr2[(int) ((j & 1095216660480L) >>> 32)] << 32)) | (280375465082880L & (bArr3[(int) ((j & 280375465082880L) >>> 40)] << 40)) | ((bArr4[(int) ((j & 71776119061217280L) >>> 48)] << 48) & 71776119061217280L);
        }
    }

    private void ShiftRows() {
        Pack.longToLittleEndian(this.internalState, this.internalStateBytes, 0);
        int i = -1;
        for (int i2 = 0; i2 < 8; i2++) {
            if (i2 % (8 / this.wordsInBlock) == 0) {
                i++;
            }
            int i3 = 0;
            while (true) {
                int i4 = this.wordsInBlock;
                if (i3 < i4) {
                    this.tempInternalStateBytes[((((i3 + i) % i4) * 64) / 8) + i2] = this.internalStateBytes[((i3 * 64) / 8) + i2];
                    i3++;
                }
            }
        }
        Pack.littleEndianToLong(this.tempInternalStateBytes, 0, this.internalState);
    }

    private void InvShiftRows() {
        Pack.longToLittleEndian(this.internalState, this.internalStateBytes, 0);
        int i = -1;
        for (int i2 = 0; i2 < 8; i2++) {
            if (i2 % (8 / this.wordsInBlock) == 0) {
                i++;
            }
            int i3 = 0;
            while (true) {
                int i4 = this.wordsInBlock;
                if (i3 < i4) {
                    this.tempInternalStateBytes[((i3 * 64) / 8) + i2] = this.internalStateBytes[((((i3 + i) % i4) * 64) / 8) + i2];
                    i3++;
                }
            }
        }
        Pack.littleEndianToLong(this.tempInternalStateBytes, 0, this.internalState);
    }

    private void MixColumns(byte[][] bArr) {
        Pack.longToLittleEndian(this.internalState, this.internalStateBytes, 0);
        for (int i = 0; i < this.wordsInBlock; i++) {
            long j = 0;
            long j2 = -72057594037927936L;
            for (int i2 = 7; i2 >= 0; i2--) {
                byte bMultiplyGF = 0;
                for (int i3 = 7; i3 >= 0; i3--) {
                    bMultiplyGF = (byte) (bMultiplyGF ^ MultiplyGF(this.internalStateBytes[((i * 64) / 8) + i3], bArr[i2][i3]));
                }
                j |= (bMultiplyGF << ((i2 * 64) / 8)) & j2;
                j2 >>>= 8;
            }
            this.internalState[i] = j;
        }
    }

    private void ShiftLeft(long[] jArr) {
        for (int i = 0; i < jArr.length; i++) {
            jArr[i] = jArr[i] << 1;
        }
        for (int i2 = 0; i2 < jArr.length / 2; i2++) {
            long j = jArr[i2];
            jArr[i2] = jArr[(jArr.length - i2) - 1];
            jArr[(jArr.length - i2) - 1] = j;
        }
    }

    private void RotateLeft(long[] jArr) {
        int length = (jArr.length * 2) + 3;
        int length2 = jArr.length * 8;
        byte[] bArr = new byte[(jArr.length * 64) / 8];
        Pack.longToLittleEndian(jArr, bArr, 0);
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, length);
        int i = length2 - length;
        System.arraycopy(bArr, length, bArr, 0, i);
        System.arraycopy(bArr2, 0, bArr, i, length);
        Pack.littleEndianToLong(bArr, 0, jArr);
    }
}
