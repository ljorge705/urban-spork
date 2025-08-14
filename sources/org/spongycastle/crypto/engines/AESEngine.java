package org.spongycastle.crypto.engines;

import com.drew.metadata.mp4.media.Mp4VideoDirectory;
import com.facebook.imageutils.JfifUtil;
import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import com.oblador.keychain.cipherStorage.CipherStorageKeystoreAesCbc;
import java.lang.reflect.Array;
import kotlin.io.encoding.Base64;
import net.sf.scuba.smartcards.ISO7816;
import net.sf.scuba.smartcards.ISOFileInfo;
import okio.Utf8;
import org.jmrtd.cbeff.ISO781611;
import org.jmrtd.lds.CVCAFile;
import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.tls.CipherSuite;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Pack;

/* loaded from: classes4.dex */
public class AESEngine implements BlockCipher {
    private static final int BLOCK_SIZE = 16;
    private static final int m1 = -2139062144;
    private static final int m2 = 2139062143;
    private static final int m3 = 27;
    private static final int m4 = -1061109568;
    private static final int m5 = 1061109567;
    private int C0;
    private int C1;
    private int C2;
    private int C3;
    private int ROUNDS;
    private int[][] WorkingKey = null;
    private boolean forEncryption;
    private byte[] s;
    private static final byte[] S = {99, 124, 119, 123, -14, 107, ISOFileInfo.FCI_BYTE, -59, ISO7816.INS_DECREASE, 1, 103, 43, -2, -41, ISOFileInfo.AB, 118, ISO7816.INS_GET_DATA, -126, -55, 125, -6, 89, 71, -16, -83, -44, -94, -81, -100, -92, 114, ISO7816.INS_GET_RESPONSE, -73, -3, -109, 38, 54, Utf8.REPLACEMENT_BYTE, -9, -52, ISO7816.INS_DECREASE_STAMPED, ISOFileInfo.A5, -27, -15, 113, ISO7816.INS_LOAD_KEY_FILE, 49, Ascii.NAK, 4, -57, 35, -61, Ascii.CAN, -106, 5, -102, 7, Ascii.DC2, Byte.MIN_VALUE, ISO7816.INS_APPEND_RECORD, -21, 39, -78, 117, 9, ISOFileInfo.FILE_IDENTIFIER, ISO7816.INS_UNBLOCK_CHV, Ascii.SUB, Ascii.ESC, 110, 90, ISOFileInfo.A0, 82, 59, ISO7816.INS_UPDATE_BINARY, ISO7816.INS_READ_RECORD2, 41, -29, 47, -124, 83, -47, 0, -19, 32, -4, ISO7816.INS_READ_BINARY2, 91, 106, -53, -66, 57, 74, 76, 88, -49, ISO7816.INS_WRITE_BINARY, -17, -86, -5, 67, 77, 51, ISOFileInfo.PROP_INFO, 69, -7, 2, Byte.MAX_VALUE, 80, 60, -97, -88, 81, -93, SignedBytes.MAX_POWER_OF_TWO, -113, -110, -99, 56, -11, -68, ISO7816.INS_READ_RECORD_STAMPED, ISO7816.INS_PUT_DATA, 33, 16, -1, -13, ISO7816.INS_WRITE_RECORD, -51, 12, 19, -20, 95, -105, ISO7816.INS_REHABILITATE_CHV, Ascii.ETB, -60, -89, 126, Base64.padSymbol, ISOFileInfo.FMD_BYTE, 93, Ascii.EM, 115, 96, ISOFileInfo.DATA_BYTES2, 79, ISO7816.INS_UPDATE_RECORD, ISO7816.INS_MSE, ISO7816.INS_PSO, -112, -120, 70, -18, -72, Ascii.DC4, -34, 94, 11, -37, ISO7816.INS_CREATE_FILE, ISO7816.INS_INCREASE, 58, 10, 73, 6, ISO7816.INS_CHANGE_CHV, 92, ISO7816.INS_ENVELOPE, -45, -84, ISOFileInfo.FCP_BYTE, -111, -107, ISO7816.INS_DELETE_FILE, 121, -25, -56, 55, 109, ISOFileInfo.ENV_TEMP_EF, -43, 78, -87, 108, 86, -12, -22, 101, 122, -82, 8, -70, 120, 37, 46, 28, -90, ISO7816.INS_READ_BINARY_STAMPED, -58, -24, -35, 116, Ascii.US, 75, -67, ISOFileInfo.SECURITY_ATTR_EXP, ISOFileInfo.LCS_BYTE, ISO7816.INS_MANAGE_CHANNEL, 62, -75, 102, 72, 3, -10, 14, 97, 53, 87, -71, -122, -63, 29, -98, -31, -8, -104, 17, 105, -39, ISOFileInfo.CHANNEL_SECURITY, -108, -101, 30, ISOFileInfo.FCI_EXT, -23, -50, 85, 40, -33, ISOFileInfo.SECURITY_ATTR_COMPACT, ISOFileInfo.A1, -119, 13, -65, -26, CVCAFile.CAR_TAG, 104, 65, -103, 45, 15, ISO7816.INS_READ_BINARY, 84, -69, Ascii.SYN};
    private static final byte[] Si = {82, 9, 106, -43, ISO7816.INS_DECREASE, 54, ISOFileInfo.A5, 56, -65, SignedBytes.MAX_POWER_OF_TWO, -93, -98, ISOFileInfo.DATA_BYTES2, -13, -41, -5, 124, -29, 57, -126, -101, 47, -1, ISOFileInfo.FCI_EXT, ISO7816.INS_DECREASE_STAMPED, ISOFileInfo.CHANNEL_SECURITY, 67, ISO7816.INS_REHABILITATE_CHV, -60, -34, -23, -53, 84, 123, -108, ISO7816.INS_INCREASE, -90, ISO7816.INS_ENVELOPE, 35, Base64.padSymbol, -18, 76, -107, 11, CVCAFile.CAR_TAG, -6, -61, 78, 8, 46, ISOFileInfo.A1, 102, 40, -39, ISO7816.INS_CHANGE_CHV, -78, 118, 91, -94, 73, 109, ISOFileInfo.SECURITY_ATTR_EXP, -47, 37, 114, -8, -10, ISOFileInfo.FMD_BYTE, -122, 104, -104, Ascii.SYN, -44, -92, 92, -52, 93, 101, ISO7816.INS_READ_RECORD_STAMPED, -110, 108, ISO7816.INS_MANAGE_CHANNEL, 72, 80, -3, -19, -71, ISO7816.INS_PUT_DATA, 94, Ascii.NAK, 70, 87, -89, ISOFileInfo.ENV_TEMP_EF, -99, -124, -112, ISO7816.INS_LOAD_KEY_FILE, ISOFileInfo.AB, 0, ISOFileInfo.SECURITY_ATTR_COMPACT, -68, -45, 10, -9, ISO7816.INS_DELETE_FILE, 88, 5, -72, ISO7816.INS_READ_RECORD2, 69, 6, ISO7816.INS_WRITE_BINARY, ISO7816.INS_UNBLOCK_CHV, 30, -113, ISO7816.INS_GET_DATA, Utf8.REPLACEMENT_BYTE, 15, 2, -63, -81, -67, 3, 1, 19, ISOFileInfo.LCS_BYTE, 107, 58, -111, 17, 65, 79, 103, ISO7816.INS_UPDATE_RECORD, -22, -105, -14, -49, -50, -16, ISO7816.INS_READ_BINARY_STAMPED, -26, 115, -106, -84, 116, ISO7816.INS_MSE, -25, -83, 53, ISOFileInfo.PROP_INFO, ISO7816.INS_APPEND_RECORD, -7, 55, -24, 28, 117, -33, 110, 71, -15, Ascii.SUB, 113, 29, 41, -59, -119, ISOFileInfo.FCI_BYTE, -73, ISOFileInfo.FCP_BYTE, 14, -86, Ascii.CAN, -66, Ascii.ESC, -4, 86, 62, 75, -58, ISO7816.INS_WRITE_RECORD, 121, 32, -102, -37, ISO7816.INS_GET_RESPONSE, -2, 120, -51, 90, -12, Ascii.US, -35, -88, 51, -120, 7, -57, 49, ISO7816.INS_READ_BINARY2, Ascii.DC2, 16, 89, 39, Byte.MIN_VALUE, -20, 95, 96, 81, Byte.MAX_VALUE, -87, Ascii.EM, -75, 74, 13, 45, -27, 122, -97, -109, -55, -100, -17, ISOFileInfo.A0, ISO7816.INS_CREATE_FILE, 59, 77, -82, ISO7816.INS_PSO, -11, ISO7816.INS_READ_BINARY, -56, -21, -69, 60, ISOFileInfo.FILE_IDENTIFIER, 83, -103, 97, Ascii.ETB, 43, 4, 126, -70, 119, ISO7816.INS_UPDATE_BINARY, 38, -31, 105, Ascii.DC4, 99, 85, 33, 12, 125};
    private static final int[] rcon = {1, 2, 4, 8, 16, 32, 64, 128, 27, 54, 108, JfifUtil.MARKER_SOI, 171, 77, 154, 47, 94, 188, 99, 198, 151, 53, 106, Mp4VideoDirectory.TAG_OPCOLOR, 179, ISO781611.SMT_TAG, 250, 239, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256, 145};
    private static final int[] T0 = {-1520213050, -2072216328, -1720223762, -1921287178, 234025727, -1117033514, -1318096930, 1422247313, 1345335392, 50397442, -1452841010, 2099981142, 436141799, 1658312629, -424957107, -1703512340, 1170918031, -1652391393, 1086966153, -2021818886, 368769775, -346465870, -918075506, 200339707, -324162239, 1742001331, -39673249, -357585083, -1080255453, -140204973, -1770884380, 1539358875, -1028147339, 486407649, -1366060227, 1780885068, 1513502316, 1094664062, 49805301, 1338821763, 1546925160, -190470831, 887481809, 150073849, -1821281822, 1943591083, 1395732834, 1058346282, 201589768, 1388824469, 1696801606, 1589887901, 672667696, -1583966665, 251987210, -1248159185, 151455502, 907153956, -1686077413, 1038279391, 652995533, 1764173646, -843926913, -1619692054, 453576978, -1635548387, 1949051992, 773462580, 756751158, -1301385508, -296068428, -73359269, -162377052, 1295727478, 1641469623, -827083907, 2066295122, 1055122397, 1898917726, -1752923117, -179088474, 1758581177, 0, 753790401, 1612718144, 536673507, -927878791, -312779850, -1100322092, 1187761037, -641810841, 1262041458, -565556588, -733197160, -396863312, 1255133061, 1808847035, 720367557, -441800113, 385612781, -985447546, -682799718, 1429418854, -1803188975, -817543798, 284817897, 100794884, -2122350594, -263171936, 1144798328, -1163944155, -475486133, -212774494, -22830243, -1069531008, -1970303227, -1382903233, -1130521311, 1211644016, 83228145, -541279133, -1044990345, 1977277103, 1663115586, 806359072, 452984805, 250868733, 1842533055, 1288555905, 336333848, 890442534, 804056259, -513843266, -1567123659, -867941240, 957814574, 1472513171, -223893675, -2105639172, 1195195770, -1402706744, -413311558, 723065138, -1787595802, -1604296512, -1736343271, -783331426, 2145180835, 1713513028, 2116692564, -1416589253, -2088204277, -901364084, 703524551, -742868885, 1007948840, 2044649127, -497131844, 487262998, 1994120109, 1004593371, 1446130276, 1312438900, 503974420, -615954030, 168166924, 1814307912, -463709000, 1573044895, 1859376061, -273896381, -1503501628, -1466855111, -1533700815, 937747667, -1954973198, 854058965, 1137232011, 1496790894, -1217565222, -1936880383, 1691735473, -766620004, -525751991, -1267962664, -95005012, 133494003, 636152527, -1352309302, -1904575756, -374428089, 403179536, -709182865, -2005370640, 1864705354, 1915629148, 605822008, -240736681, -944458637, 1371981463, 602466507, 2094914977, -1670089496, 555687742, -582268010, -591544991, -2037675251, -2054518257, -1871679264, 1111375484, -994724495, -1436129588, -666351472, 84083462, 32962295, 302911004, -1553899070, 1597322602, -111716434, -793134743, -1853454825, 1489093017, 656219450, -1180787161, 954327513, 335083755, -1281845205, 856756514, -1150719534, 1893325225, -1987146233, -1483434957, -1231316179, 572399164, -1836611819, 552200649, 1238290055, -11184726, 2015897680, 2061492133, -1886614525, -123625127, -2138470135, 386731290, -624967835, 837215959, -968736124, -1201116976, -1019133566, -1332111063, 1999449434, 286199582, -877612933, -61582168, -692339859, 974525996};
    private static final int[] Tinv0 = {1353184337, 1399144830, -1012656358, -1772214470, -882136261, -247096033, -1420232020, -1828461749, 1442459680, -160598355, -1854485368, 625738485, -52959921, -674551099, -2143013594, -1885117771, 1230680542, 1729870373, -1743852987, -507445667, 41234371, 317738113, -1550367091, -956705941, -413167869, -1784901099, -344298049, -631680363, 763608788, -752782248, 694804553, 1154009486, 1787413109, 2021232372, 1799248025, -579749593, -1236278850, 397248752, 1722556617, -1271214467, 407560035, -2110711067, 1613975959, 1165972322, -529046351, -2068943941, 480281086, -1809118983, 1483229296, 436028815, -2022908268, -1208452270, 601060267, -503166094, 1468997603, 715871590, 120122290, 63092015, -1703164538, -1526188077, -226023376, -1297760477, -1167457534, 1552029421, 723308426, -1833666137, -252573709, -1578997426, -839591323, -708967162, 526529745, -1963022652, -1655493068, -1604979806, 853641733, 1978398372, 971801355, -1427152832, 111112542, 1360031421, -108388034, 1023860118, -1375387939, 1186850381, -1249028975, 90031217, 1876166148, -15380384, 620468249, -1746289194, -868007799, 2006899047, -1119688528, -2004121337, 945494503, -605108103, 1191869601, -384875908, -920746760, 0, -2088337399, 1223502642, -1401941730, 1316117100, -67170563, 1446544655, 517320253, 658058550, 1691946762, 564550760, -783000677, 976107044, -1318647284, 266819475, -761860428, -1634624741, 1338359936, -1574904735, 1766553434, 370807324, 179999714, -450191168, 1138762300, 488053522, 185403662, -1379431438, -1180125651, -928440812, -2061897385, 1275557295, -1143105042, -44007517, -1624899081, -1124765092, -985962940, 880737115, 1982415755, -590994485, 1761406390, 1676797112, -891538985, 277177154, 1076008723, 538035844, 2099530373, -130171950, 288553390, 1839278535, 1261411869, -214912292, -330136051, -790380169, 1813426987, -1715900247, -95906799, 577038663, -997393240, 440397984, -668172970, -275762398, -951170681, -1043253031, -22885748, 906744984, -813566554, 685669029, 646887386, -1530942145, -459458004, 227702864, -1681105046, 1648787028, -1038905866, -390539120, 1593260334, -173030526, -1098883681, 2090061929, -1456614033, -1290656305, 999926984, -1484974064, 1852021992, 2075868123, 158869197, -199730834, 28809964, -1466282109, 1701746150, 2129067946, 147831841, -420997649, -644094022, -835293366, -737566742, -696471511, -1347247055, 824393514, 815048134, -1067015627, 935087732, -1496677636, -1328508704, 366520115, 1251476721, -136647615, 240176511, 804688151, -1915335306, 1303441219, 1414376140, -553347356, -474623586, 461924940, -1205916479, 2136040774, 82468509, 1563790337, 1937016826, 776014843, 1511876531, 1389550482, 861278441, 323475053, -1939744870, 2047648055, -1911228327, -1992551445, -299390514, 902390199, -303751967, 1018251130, 1507840668, 1064563285, 2043548696, -1086863501, -355600557, 1537932639, 342834655, -2032450440, -2114736182, 1053059257, 741614648, 1598071746, 1925389590, 203809468, -1958134744, 1100287487, 1895934009, -558691320, -1662733096, -1866377628, 1636092795, 1890988757, 1952214088, 1113045200};

    private static int FFmulX(int i) {
        return (((i & m1) >>> 7) * 27) ^ ((m2 & i) << 1);
    }

    private static int FFmulX2(int i) {
        int i2 = (m5 & i) << 2;
        int i3 = i & m4;
        int i4 = i3 ^ (i3 >>> 1);
        return (i4 >>> 5) ^ (i2 ^ (i4 >>> 2));
    }

    private static int shift(int i, int i2) {
        return (i << (-i2)) | (i >>> i2);
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return CipherStorageKeystoreAesCbc.ALGORITHM_AES;
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public void reset() {
    }

    private static int inv_mcol(int i) {
        int iShift = shift(i, 8) ^ i;
        int iFFmulX = i ^ FFmulX(iShift);
        int iFFmulX2 = iShift ^ FFmulX2(iFFmulX);
        return iFFmulX ^ (iFFmulX2 ^ shift(iFFmulX2, 16));
    }

    private static int subWord(int i) {
        byte[] bArr = S;
        return (bArr[(i >> 24) & 255] << Ascii.CAN) | (bArr[i & 255] & 255) | ((bArr[(i >> 8) & 255] & 255) << 8) | ((bArr[(i >> 16) & 255] & 255) << 16);
    }

    private int[][] generateWorkingKey(byte[] bArr, boolean z) {
        int length = bArr.length;
        if (length < 16 || length > 32 || (length & 7) != 0) {
            throw new IllegalArgumentException("Key length not 128/192/256 bits.");
        }
        int i = length >>> 2;
        this.ROUNDS = i + 6;
        int[][] iArr = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, i + 7, 4);
        if (i == 4) {
            int iLittleEndianToInt = Pack.littleEndianToInt(bArr, 0);
            iArr[0][0] = iLittleEndianToInt;
            int iLittleEndianToInt2 = Pack.littleEndianToInt(bArr, 4);
            iArr[0][1] = iLittleEndianToInt2;
            int iLittleEndianToInt3 = Pack.littleEndianToInt(bArr, 8);
            iArr[0][2] = iLittleEndianToInt3;
            int iLittleEndianToInt4 = Pack.littleEndianToInt(bArr, 12);
            iArr[0][3] = iLittleEndianToInt4;
            for (int i2 = 1; i2 <= 10; i2++) {
                iLittleEndianToInt ^= subWord(shift(iLittleEndianToInt4, 8)) ^ rcon[i2 - 1];
                int[] iArr2 = iArr[i2];
                iArr2[0] = iLittleEndianToInt;
                iLittleEndianToInt2 ^= iLittleEndianToInt;
                iArr2[1] = iLittleEndianToInt2;
                iLittleEndianToInt3 ^= iLittleEndianToInt2;
                iArr2[2] = iLittleEndianToInt3;
                iLittleEndianToInt4 ^= iLittleEndianToInt3;
                iArr2[3] = iLittleEndianToInt4;
            }
        } else if (i == 6) {
            int iLittleEndianToInt5 = Pack.littleEndianToInt(bArr, 0);
            iArr[0][0] = iLittleEndianToInt5;
            int iLittleEndianToInt6 = Pack.littleEndianToInt(bArr, 4);
            iArr[0][1] = iLittleEndianToInt6;
            int iLittleEndianToInt7 = Pack.littleEndianToInt(bArr, 8);
            iArr[0][2] = iLittleEndianToInt7;
            int iLittleEndianToInt8 = Pack.littleEndianToInt(bArr, 12);
            iArr[0][3] = iLittleEndianToInt8;
            int iLittleEndianToInt9 = Pack.littleEndianToInt(bArr, 16);
            iArr[1][0] = iLittleEndianToInt9;
            int iLittleEndianToInt10 = Pack.littleEndianToInt(bArr, 20);
            iArr[1][1] = iLittleEndianToInt10;
            int iSubWord = iLittleEndianToInt5 ^ (subWord(shift(iLittleEndianToInt10, 8)) ^ 1);
            int[] iArr3 = iArr[1];
            iArr3[2] = iSubWord;
            int i3 = iLittleEndianToInt6 ^ iSubWord;
            iArr3[3] = i3;
            int i4 = iLittleEndianToInt7 ^ i3;
            int[] iArr4 = iArr[2];
            iArr4[0] = i4;
            int i5 = iLittleEndianToInt8 ^ i4;
            iArr4[1] = i5;
            int i6 = iLittleEndianToInt9 ^ i5;
            iArr4[2] = i6;
            int i7 = iLittleEndianToInt10 ^ i6;
            iArr4[3] = i7;
            int i8 = 2;
            for (int i9 = 3; i9 < 12; i9 += 3) {
                int iSubWord2 = iSubWord ^ (subWord(shift(i7, 8)) ^ i8);
                int[] iArr5 = iArr[i9];
                iArr5[0] = iSubWord2;
                int i10 = i3 ^ iSubWord2;
                iArr5[1] = i10;
                int i11 = i4 ^ i10;
                iArr5[2] = i11;
                int i12 = i5 ^ i11;
                iArr5[3] = i12;
                int i13 = i6 ^ i12;
                int i14 = i9 + 1;
                int[] iArr6 = iArr[i14];
                iArr6[0] = i13;
                int i15 = i7 ^ i13;
                iArr6[1] = i15;
                int iSubWord3 = subWord(shift(i15, 8)) ^ (i8 << 1);
                i8 <<= 2;
                iSubWord = iSubWord2 ^ iSubWord3;
                int[] iArr7 = iArr[i14];
                iArr7[2] = iSubWord;
                i3 = i10 ^ iSubWord;
                iArr7[3] = i3;
                i4 = i11 ^ i3;
                int[] iArr8 = iArr[i9 + 2];
                iArr8[0] = i4;
                i5 = i12 ^ i4;
                iArr8[1] = i5;
                i6 = i13 ^ i5;
                iArr8[2] = i6;
                i7 = i15 ^ i6;
                iArr8[3] = i7;
            }
            int iSubWord4 = (subWord(shift(i7, 8)) ^ i8) ^ iSubWord;
            int[] iArr9 = iArr[12];
            iArr9[0] = iSubWord4;
            int i16 = iSubWord4 ^ i3;
            iArr9[1] = i16;
            int i17 = i16 ^ i4;
            iArr9[2] = i17;
            iArr9[3] = i17 ^ i5;
        } else if (i == 8) {
            int iLittleEndianToInt11 = Pack.littleEndianToInt(bArr, 0);
            iArr[0][0] = iLittleEndianToInt11;
            int iLittleEndianToInt12 = Pack.littleEndianToInt(bArr, 4);
            iArr[0][1] = iLittleEndianToInt12;
            int iLittleEndianToInt13 = Pack.littleEndianToInt(bArr, 8);
            iArr[0][2] = iLittleEndianToInt13;
            int iLittleEndianToInt14 = Pack.littleEndianToInt(bArr, 12);
            iArr[0][3] = iLittleEndianToInt14;
            int iLittleEndianToInt15 = Pack.littleEndianToInt(bArr, 16);
            iArr[1][0] = iLittleEndianToInt15;
            int iLittleEndianToInt16 = Pack.littleEndianToInt(bArr, 20);
            iArr[1][1] = iLittleEndianToInt16;
            int iLittleEndianToInt17 = Pack.littleEndianToInt(bArr, 24);
            iArr[1][2] = iLittleEndianToInt17;
            int iLittleEndianToInt18 = Pack.littleEndianToInt(bArr, 28);
            iArr[1][3] = iLittleEndianToInt18;
            int i18 = 1;
            for (int i19 = 2; i19 < 14; i19 += 2) {
                int iSubWord5 = subWord(shift(iLittleEndianToInt18, 8)) ^ i18;
                i18 <<= 1;
                iLittleEndianToInt11 ^= iSubWord5;
                int[] iArr10 = iArr[i19];
                iArr10[0] = iLittleEndianToInt11;
                iLittleEndianToInt12 ^= iLittleEndianToInt11;
                iArr10[1] = iLittleEndianToInt12;
                iLittleEndianToInt13 ^= iLittleEndianToInt12;
                iArr10[2] = iLittleEndianToInt13;
                iLittleEndianToInt14 ^= iLittleEndianToInt13;
                iArr10[3] = iLittleEndianToInt14;
                iLittleEndianToInt15 ^= subWord(iLittleEndianToInt14);
                int[] iArr11 = iArr[i19 + 1];
                iArr11[0] = iLittleEndianToInt15;
                iLittleEndianToInt16 ^= iLittleEndianToInt15;
                iArr11[1] = iLittleEndianToInt16;
                iLittleEndianToInt17 ^= iLittleEndianToInt16;
                iArr11[2] = iLittleEndianToInt17;
                iLittleEndianToInt18 ^= iLittleEndianToInt17;
                iArr11[3] = iLittleEndianToInt18;
            }
            int iSubWord6 = (subWord(shift(iLittleEndianToInt18, 8)) ^ i18) ^ iLittleEndianToInt11;
            int[] iArr12 = iArr[14];
            iArr12[0] = iSubWord6;
            int i20 = iSubWord6 ^ iLittleEndianToInt12;
            iArr12[1] = i20;
            int i21 = i20 ^ iLittleEndianToInt13;
            iArr12[2] = i21;
            iArr12[3] = i21 ^ iLittleEndianToInt14;
        } else {
            throw new IllegalStateException("Should never get here");
        }
        if (!z) {
            for (int i22 = 1; i22 < this.ROUNDS; i22++) {
                for (int i23 = 0; i23 < 4; i23++) {
                    int[] iArr13 = iArr[i22];
                    iArr13[i23] = inv_mcol(iArr13[i23]);
                }
            }
        }
        return iArr;
    }

    public AESEngine() {
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            this.WorkingKey = generateWorkingKey(((KeyParameter) cipherParameters).getKey(), z);
            this.forEncryption = z;
            if (z) {
                this.s = Arrays.clone(S);
                return;
            } else {
                this.s = Arrays.clone(Si);
                return;
            }
        }
        throw new IllegalArgumentException("invalid parameter passed to AES init - " + cipherParameters.getClass().getName());
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (this.WorkingKey == null) {
            throw new IllegalStateException("AES engine not initialised");
        }
        if (i + 16 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i2 + 16 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        if (this.forEncryption) {
            unpackBlock(bArr, i);
            encryptBlock(this.WorkingKey);
            packBlock(bArr2, i2);
            return 16;
        }
        unpackBlock(bArr, i);
        decryptBlock(this.WorkingKey);
        packBlock(bArr2, i2);
        return 16;
    }

    private void unpackBlock(byte[] bArr, int i) {
        this.C0 = ((bArr[i + 1] & 255) << 8) | (bArr[i] & 255) | ((bArr[i + 2] & 255) << 16) | (bArr[i + 3] << Ascii.CAN);
        this.C1 = ((bArr[i + 5] & 255) << 8) | (bArr[i + 4] & 255) | ((bArr[i + 6] & 255) << 16) | (bArr[i + 7] << Ascii.CAN);
        this.C2 = ((bArr[i + 9] & 255) << 8) | (bArr[i + 8] & 255) | ((bArr[i + 10] & 255) << 16) | (bArr[i + 11] << Ascii.CAN);
        int i2 = ((bArr[i + 13] & 255) << 8) | (bArr[i + 12] & 255);
        this.C3 = (bArr[i + 15] << Ascii.CAN) | i2 | ((bArr[i + 14] & 255) << 16);
    }

    private void packBlock(byte[] bArr, int i) {
        int i2 = this.C0;
        bArr[i] = (byte) i2;
        bArr[i + 1] = (byte) (i2 >> 8);
        bArr[i + 2] = (byte) (i2 >> 16);
        bArr[i + 3] = (byte) (i2 >> 24);
        int i3 = this.C1;
        bArr[i + 4] = (byte) i3;
        bArr[i + 5] = (byte) (i3 >> 8);
        bArr[i + 6] = (byte) (i3 >> 16);
        bArr[i + 7] = (byte) (i3 >> 24);
        int i4 = this.C2;
        bArr[i + 8] = (byte) i4;
        bArr[i + 9] = (byte) (i4 >> 8);
        bArr[i + 10] = (byte) (i4 >> 16);
        bArr[i + 11] = (byte) (i4 >> 24);
        int i5 = this.C3;
        bArr[i + 12] = (byte) i5;
        bArr[i + 13] = (byte) (i5 >> 8);
        bArr[i + 14] = (byte) (i5 >> 16);
        bArr[i + 15] = (byte) (i5 >> 24);
    }

    private void encryptBlock(int[][] iArr) {
        int i = this.C0;
        char c = 0;
        int[] iArr2 = iArr[0];
        int i2 = i ^ iArr2[0];
        int i3 = 1;
        int i4 = this.C1 ^ iArr2[1];
        int i5 = this.C2 ^ iArr2[2];
        char c2 = 3;
        int iShift = iArr2[3] ^ this.C3;
        int i6 = 1;
        while (i6 < this.ROUNDS - i3) {
            int[] iArr3 = T0;
            int iShift2 = (((iArr3[i2 & 255] ^ shift(iArr3[(i4 >> 8) & 255], 24)) ^ shift(iArr3[(i5 >> 16) & 255], 16)) ^ shift(iArr3[(iShift >> 24) & 255], 8)) ^ iArr[i6][c];
            int iShift3 = (((shift(iArr3[(i5 >> 8) & 255], 24) ^ iArr3[i4 & 255]) ^ shift(iArr3[(iShift >> 16) & 255], 16)) ^ shift(iArr3[(i2 >> 24) & 255], 8)) ^ iArr[i6][i3];
            int iShift4 = (((shift(iArr3[(iShift >> 8) & 255], 24) ^ iArr3[i5 & 255]) ^ shift(iArr3[(i2 >> 16) & 255], 16)) ^ shift(iArr3[(i4 >> 24) & 255], 8)) ^ iArr[i6][2];
            int iShift5 = ((shift(iArr3[(i2 >> 8) & 255], 24) ^ iArr3[iShift & 255]) ^ shift(iArr3[(i4 >> 16) & 255], 16)) ^ shift(iArr3[(i5 >> 24) & 255], 8);
            int i7 = i6 + 1;
            int i8 = iShift5 ^ iArr[i6][c2];
            int iShift6 = (((iArr3[iShift2 & 255] ^ shift(iArr3[(iShift3 >> 8) & 255], 24)) ^ shift(iArr3[(iShift4 >> 16) & 255], 16)) ^ shift(iArr3[(i8 >> 24) & 255], 8)) ^ iArr[i7][0];
            int iShift7 = (((iArr3[iShift3 & 255] ^ shift(iArr3[(iShift4 >> 8) & 255], 24)) ^ shift(iArr3[(i8 >> 16) & 255], 16)) ^ shift(iArr3[(iShift2 >> 24) & 255], 8)) ^ iArr[i7][1];
            int iShift8 = (((shift(iArr3[(i8 >> 8) & 255], 24) ^ iArr3[iShift4 & 255]) ^ shift(iArr3[(iShift2 >> 16) & 255], 16)) ^ shift(iArr3[(iShift3 >> 24) & 255], 8)) ^ iArr[i7][2];
            i6 += 2;
            iShift = (((iArr3[i8 & 255] ^ shift(iArr3[(iShift2 >> 8) & 255], 24)) ^ shift(iArr3[(iShift3 >> 16) & 255], 16)) ^ shift(iArr3[(iShift4 >> 24) & 255], 8)) ^ iArr[i7][3];
            i2 = iShift6;
            i4 = iShift7;
            i5 = iShift8;
            c = 0;
            i3 = 1;
            c2 = 3;
        }
        int[] iArr4 = T0;
        int iShift9 = (((iArr4[i2 & 255] ^ shift(iArr4[(i4 >> 8) & 255], 24)) ^ shift(iArr4[(i5 >> 16) & 255], 16)) ^ shift(iArr4[(iShift >> 24) & 255], 8)) ^ iArr[i6][0];
        int iShift10 = (((iArr4[i4 & 255] ^ shift(iArr4[(i5 >> 8) & 255], 24)) ^ shift(iArr4[(iShift >> 16) & 255], 16)) ^ shift(iArr4[(i2 >> 24) & 255], 8)) ^ iArr[i6][1];
        int iShift11 = (((iArr4[i5 & 255] ^ shift(iArr4[(iShift >> 8) & 255], 24)) ^ shift(iArr4[(i2 >> 16) & 255], 16)) ^ shift(iArr4[(i4 >> 24) & 255], 8)) ^ iArr[i6][2];
        int iShift12 = (((shift(iArr4[(i2 >> 8) & 255], 24) ^ iArr4[iShift & 255]) ^ shift(iArr4[(i4 >> 16) & 255], 16)) ^ shift(iArr4[(i5 >> 24) & 255], 8)) ^ iArr[i6][3];
        byte[] bArr = S;
        int i9 = (bArr[iShift9 & 255] & 255) ^ ((bArr[(iShift10 >> 8) & 255] & 255) << 8);
        byte[] bArr2 = this.s;
        int i10 = (i9 ^ ((bArr2[(iShift11 >> 16) & 255] & 255) << 16)) ^ (bArr2[(iShift12 >> 24) & 255] << Ascii.CAN);
        int[] iArr5 = iArr[i6 + 1];
        this.C0 = i10 ^ iArr5[0];
        this.C1 = ((((bArr2[iShift10 & 255] & 255) ^ ((bArr[(iShift11 >> 8) & 255] & 255) << 8)) ^ ((bArr[(iShift12 >> 16) & 255] & 255) << 16)) ^ (bArr2[(iShift9 >> 24) & 255] << Ascii.CAN)) ^ iArr5[1];
        this.C2 = ((((bArr2[iShift11 & 255] & 255) ^ ((bArr[(iShift12 >> 8) & 255] & 255) << 8)) ^ ((bArr[(iShift9 >> 16) & 255] & 255) << 16)) ^ (bArr[(iShift10 >> 24) & 255] << Ascii.CAN)) ^ iArr5[2];
        this.C3 = ((((bArr2[iShift12 & 255] & 255) ^ ((bArr2[(iShift9 >> 8) & 255] & 255) << 8)) ^ ((bArr2[(iShift10 >> 16) & 255] & 255) << 16)) ^ (bArr[(iShift11 >> 24) & 255] << Ascii.CAN)) ^ iArr5[3];
    }

    private void decryptBlock(int[][] iArr) {
        int i = this.C0;
        int i2 = this.ROUNDS;
        int[] iArr2 = iArr[i2];
        char c = 0;
        int i3 = i ^ iArr2[0];
        int i4 = 1;
        int i5 = this.C1 ^ iArr2[1];
        int i6 = this.C2 ^ iArr2[2];
        int i7 = i2 - 1;
        char c2 = 3;
        int iShift = iArr2[3] ^ this.C3;
        while (i7 > i4) {
            int[] iArr3 = Tinv0;
            int iShift2 = (((iArr3[i3 & 255] ^ shift(iArr3[(iShift >> 8) & 255], 24)) ^ shift(iArr3[(i6 >> 16) & 255], 16)) ^ shift(iArr3[(i5 >> 24) & 255], 8)) ^ iArr[i7][c];
            int iShift3 = (((shift(iArr3[(i3 >> 8) & 255], 24) ^ iArr3[i5 & 255]) ^ shift(iArr3[(iShift >> 16) & 255], 16)) ^ shift(iArr3[(i6 >> 24) & 255], 8)) ^ iArr[i7][i4];
            int iShift4 = (((shift(iArr3[(i5 >> 8) & 255], 24) ^ iArr3[i6 & 255]) ^ shift(iArr3[(i3 >> 16) & 255], 16)) ^ shift(iArr3[(iShift >> 24) & 255], 8)) ^ iArr[i7][2];
            int iShift5 = shift(iArr3[(i3 >> 24) & 255], 8) ^ ((iArr3[iShift & 255] ^ shift(iArr3[(i6 >> 8) & 255], 24)) ^ shift(iArr3[(i5 >> 16) & 255], 16));
            int i8 = i7 - 1;
            int i9 = iShift5 ^ iArr[i7][c2];
            int iShift6 = (((iArr3[iShift2 & 255] ^ shift(iArr3[(i9 >> 8) & 255], 24)) ^ shift(iArr3[(iShift4 >> 16) & 255], 16)) ^ shift(iArr3[(iShift3 >> 24) & 255], 8)) ^ iArr[i8][0];
            int iShift7 = (((iArr3[iShift3 & 255] ^ shift(iArr3[(iShift2 >> 8) & 255], 24)) ^ shift(iArr3[(i9 >> 16) & 255], 16)) ^ shift(iArr3[(iShift4 >> 24) & 255], 8)) ^ iArr[i8][1];
            int iShift8 = (((shift(iArr3[(iShift3 >> 8) & 255], 24) ^ iArr3[iShift4 & 255]) ^ shift(iArr3[(iShift2 >> 16) & 255], 16)) ^ shift(iArr3[(i9 >> 24) & 255], 8)) ^ iArr[i8][2];
            i7 -= 2;
            iShift = iArr[i8][3] ^ (((iArr3[i9 & 255] ^ shift(iArr3[(iShift4 >> 8) & 255], 24)) ^ shift(iArr3[(iShift3 >> 16) & 255], 16)) ^ shift(iArr3[(iShift2 >> 24) & 255], 8));
            i3 = iShift6;
            i5 = iShift7;
            i6 = iShift8;
            c = 0;
            i4 = 1;
            c2 = 3;
        }
        int[] iArr4 = Tinv0;
        int iShift9 = (((iArr4[i3 & 255] ^ shift(iArr4[(iShift >> 8) & 255], 24)) ^ shift(iArr4[(i6 >> 16) & 255], 16)) ^ shift(iArr4[(i5 >> 24) & 255], 8)) ^ iArr[i7][0];
        int iShift10 = (((iArr4[i5 & 255] ^ shift(iArr4[(i3 >> 8) & 255], 24)) ^ shift(iArr4[(iShift >> 16) & 255], 16)) ^ shift(iArr4[(i6 >> 24) & 255], 8)) ^ iArr[i7][1];
        int iShift11 = (((iArr4[i6 & 255] ^ shift(iArr4[(i5 >> 8) & 255], 24)) ^ shift(iArr4[(i3 >> 16) & 255], 16)) ^ shift(iArr4[(iShift >> 24) & 255], 8)) ^ iArr[i7][2];
        int iShift12 = (shift(iArr4[(i3 >> 24) & 255], 8) ^ ((iArr4[iShift & 255] ^ shift(iArr4[(i6 >> 8) & 255], 24)) ^ shift(iArr4[(i5 >> 16) & 255], 16))) ^ iArr[i7][3];
        byte[] bArr = Si;
        int i10 = bArr[iShift9 & 255] & 255;
        byte[] bArr2 = this.s;
        int i11 = ((i10 ^ ((bArr2[(iShift12 >> 8) & 255] & 255) << 8)) ^ ((bArr2[(iShift11 >> 16) & 255] & 255) << 16)) ^ (bArr[(iShift10 >> 24) & 255] << Ascii.CAN);
        int[] iArr5 = iArr[0];
        this.C0 = i11 ^ iArr5[0];
        this.C1 = ((((bArr2[iShift10 & 255] & 255) ^ ((bArr2[(iShift9 >> 8) & 255] & 255) << 8)) ^ ((bArr[(iShift12 >> 16) & 255] & 255) << 16)) ^ (bArr2[(iShift11 >> 24) & 255] << Ascii.CAN)) ^ iArr5[1];
        this.C2 = ((((bArr2[iShift11 & 255] & 255) ^ ((bArr[(iShift10 >> 8) & 255] & 255) << 8)) ^ ((bArr[(iShift9 >> 16) & 255] & 255) << 16)) ^ (bArr2[(iShift12 >> 24) & 255] << Ascii.CAN)) ^ iArr5[2];
        this.C3 = ((((bArr[iShift12 & 255] & 255) ^ ((bArr2[(iShift11 >> 8) & 255] & 255) << 8)) ^ ((bArr2[(iShift10 >> 16) & 255] & 255) << 16)) ^ (bArr2[(iShift9 >> 24) & 255] << Ascii.CAN)) ^ iArr5[3];
    }
}
