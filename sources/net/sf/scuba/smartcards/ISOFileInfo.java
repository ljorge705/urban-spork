package net.sf.scuba.smartcards;

import java.math.BigInteger;
import net.sf.scuba.util.Hex;
import org.jmrtd.cbeff.ISO781611;

/* loaded from: classes4.dex */
public class ISOFileInfo extends FileInfo {
    public static final byte A0 = -96;
    public static final byte A1 = -95;
    public static final byte A2 = -94;
    public static final byte A5 = -91;
    public static final byte AB = -85;
    public static final byte AC = -84;
    public static final byte CHANNEL_SECURITY = -114;
    public static final byte DATA_BYTES1 = Byte.MIN_VALUE;
    public static final byte DATA_BYTES2 = -127;
    public static final byte DF_NAME = -124;
    public static final byte ENV_TEMP_EF = -115;
    public static final byte FCI_BYTE = 111;
    public static final byte FCI_EXT = -121;
    public static final byte FCP_BYTE = 98;
    public static final byte FILE_DESCRIPTOR = -126;
    public static final byte FILE_IDENTIFIER = -125;
    public static final byte FMD_BYTE = 100;
    public static final byte LCS_BYTE = -118;
    public static final byte PROP_INFO = -123;
    public static final byte SECURITY_ATTR_COMPACT = -116;
    public static final byte SECURITY_ATTR_EXP = -117;
    public static final byte SECURITY_ATTR_PROP = -122;
    public static final byte SHORT_EF = -120;
    byte[] a0;
    byte[] a1;
    byte[] a2;
    byte[] a5;
    byte[] ab;
    byte[] ac;
    byte channelSecurity;
    byte dataCodingByte;
    byte descriptorByte;
    byte[] dfName;
    short envTempEF;
    short fciExt;
    short fid;
    int fileLength;
    int fileLengthFCI;
    byte lcsByte;
    byte mainTag;
    short maxRecordSize;
    short maxRecordsCount;
    byte[] propInfo;
    byte[] secAttrCompact;
    byte[] secAttrExp;
    byte[] secAttrProp;
    byte shortEF;

    @Override // net.sf.scuba.smartcards.FileInfo
    public short getFID() {
        return this.fid;
    }

    @Override // net.sf.scuba.smartcards.FileInfo
    public int getFileLength() {
        return this.fileLength;
    }

    public ISOFileInfo(byte[] bArr) throws CardServiceException {
        byte b;
        int i;
        byte[] bArr2;
        this.mainTag = (byte) -1;
        this.fileLength = -1;
        this.fileLengthFCI = -1;
        this.descriptorByte = (byte) -1;
        this.dataCodingByte = (byte) -1;
        this.maxRecordSize = (short) -1;
        this.maxRecordsCount = (short) -1;
        this.fid = (short) -1;
        this.dfName = null;
        this.propInfo = null;
        this.secAttrProp = null;
        this.secAttrExp = null;
        this.secAttrCompact = null;
        this.fciExt = (short) -1;
        this.envTempEF = (short) -1;
        this.shortEF = (byte) -1;
        this.lcsByte = (byte) -1;
        this.channelSecurity = (byte) -1;
        this.a0 = null;
        this.a1 = null;
        this.a2 = null;
        this.a5 = null;
        this.ab = null;
        this.ac = null;
        if (bArr.length == 0) {
            return;
        }
        byte b2 = bArr[0];
        if (b2 != 111 && b2 != 98 && b2 != 100) {
            throw new CardServiceException("Malformed FCI data");
        }
        this.mainTag = b2;
        int i2 = bArr[1];
        byte[] bArr3 = new byte[i2];
        System.arraycopy(bArr, 2, bArr3, 0, i2);
        int i3 = 0;
        while (i3 < i2) {
            int i4 = i3 + 1;
            try {
                b = bArr3[i3];
                int i5 = i3 + 2;
                i = bArr3[i4];
                bArr2 = new byte[i];
                System.arraycopy(bArr3, i5, bArr2, 0, i);
                i3 = i5 + i;
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new CardServiceException("Malformed FCI.");
            }
            if (b == -91) {
                byte[] bArr4 = new byte[i];
                this.a5 = bArr4;
                System.arraycopy(bArr2, 0, bArr4, 0, i);
            } else if (b == -85) {
                byte[] bArr5 = new byte[i];
                this.ab = bArr5;
                System.arraycopy(bArr2, 0, bArr5, 0, i);
            } else {
                if (b != -84) {
                    switch (b) {
                        case Byte.MIN_VALUE:
                            this.fileLength = new BigInteger(bArr2).abs().intValue();
                            continue;
                        case -127:
                            checkLen(i, 2);
                            this.fileLengthFCI = new BigInteger(bArr2).intValue();
                            continue;
                        case -126:
                            checkLen(i, 1, 6);
                            this.descriptorByte = bArr2[0];
                            if (1 != i) {
                                this.dataCodingByte = bArr2[1];
                                if (2 != i) {
                                    int i6 = 3;
                                    if (i == 3) {
                                        this.maxRecordSize = bArr2[2];
                                    } else {
                                        this.maxRecordSize = new BigInteger(new byte[]{bArr2[2], bArr2[3]}).shortValue();
                                        i6 = 4;
                                    }
                                    if (i6 == i) {
                                        break;
                                    } else if (i == 5) {
                                        this.maxRecordsCount = bArr2[i6];
                                        break;
                                    } else {
                                        this.maxRecordsCount = new BigInteger(new byte[]{bArr2[i6], bArr2[i6 + 1]}).shortValue();
                                        continue;
                                    }
                                } else {
                                    break;
                                }
                            } else {
                                break;
                            }
                        case -125:
                            checkLen(i, 2);
                            this.fid = new BigInteger(bArr2).shortValue();
                            continue;
                        case -124:
                            checkLen(i, 0, 16);
                            byte[] bArr6 = new byte[i];
                            this.dfName = bArr6;
                            System.arraycopy(bArr2, 0, bArr6, 0, i);
                            continue;
                        case -123:
                            byte[] bArr7 = new byte[i];
                            this.propInfo = bArr7;
                            System.arraycopy(bArr2, 0, bArr7, 0, i);
                            continue;
                        case -122:
                            byte[] bArr8 = new byte[i];
                            this.secAttrProp = bArr8;
                            System.arraycopy(bArr2, 0, bArr8, 0, i);
                            continue;
                        case -121:
                            checkLen(i, 2);
                            this.fciExt = new BigInteger(bArr2).shortValue();
                            continue;
                        case -120:
                            checkLen(i, 0, 1);
                            if (i == 0) {
                                this.shortEF = (byte) 0;
                                break;
                            } else {
                                this.shortEF = bArr2[0];
                                continue;
                            }
                        default:
                            switch (b) {
                                case -118:
                                    checkLen(i, 1);
                                    this.lcsByte = bArr2[0];
                                    continue;
                                case -117:
                                    byte[] bArr9 = new byte[i];
                                    this.secAttrExp = bArr9;
                                    System.arraycopy(bArr2, 0, bArr9, 0, i);
                                    continue;
                                case -116:
                                    byte[] bArr10 = new byte[i];
                                    this.secAttrCompact = bArr10;
                                    System.arraycopy(bArr2, 0, bArr10, 0, i);
                                    continue;
                                case -115:
                                    checkLen(i, 2);
                                    this.envTempEF = new BigInteger(bArr2).shortValue();
                                    continue;
                                case -114:
                                    checkLen(i, 1);
                                    this.channelSecurity = bArr2[0];
                                    continue;
                                    continue;
                                default:
                                    switch (b) {
                                        case -96:
                                            byte[] bArr11 = new byte[i];
                                            this.a0 = bArr11;
                                            System.arraycopy(bArr2, 0, bArr11, 0, i);
                                            continue;
                                        case ISO781611.BIOMETRIC_HEADER_TEMPLATE_BASE_TAG /* -95 */:
                                            byte[] bArr12 = new byte[i];
                                            this.a1 = bArr12;
                                            System.arraycopy(bArr2, 0, bArr12, 0, i);
                                            continue;
                                            continue;
                                        case -94:
                                            byte[] bArr13 = new byte[i];
                                            this.a2 = bArr13;
                                            System.arraycopy(bArr2, 0, bArr13, 0, i);
                                            continue;
                                        default:
                                            throw new CardServiceException("Malformed FCI: unrecognized tag.");
                                    }
                            }
                    }
                    throw new CardServiceException("Malformed FCI.");
                }
                byte[] bArr14 = new byte[i];
                this.ac = bArr14;
                System.arraycopy(bArr2, 0, bArr14, 0, i);
            }
        }
    }

    private static void checkLen(int i, int i2) throws CardServiceException {
        if (i != i2) {
            throw new CardServiceException("Malformed FCI.");
        }
    }

    private static void checkLen(int i, int i2, int i3) throws CardServiceException {
        if (i < i2 || i > i3) {
            throw new CardServiceException("Malformed FCI.");
        }
    }

    public byte[] getFormatted() throws NumberFormatException {
        String strShortToHexString;
        String strShortToHexString2;
        byte[] bArrCatArray = new byte[0];
        if (this.mainTag == -1) {
            return bArrCatArray;
        }
        int i = this.fileLength;
        if (i != -1) {
            bArrCatArray = catArray(bArrCatArray, getArray(Byte.MIN_VALUE, Hex.hexStringToBytes(Hex.shortToHexString((short) i))));
        }
        int i2 = this.fileLengthFCI;
        if (i2 != -1) {
            bArrCatArray = catArray(bArrCatArray, getArray(DATA_BYTES2, Hex.hexStringToBytes(Hex.shortToHexString((short) i2))));
        }
        byte b = this.descriptorByte;
        if (b != -1) {
            byte[] bArr = {b};
            byte[] bArr2 = new byte[0];
            byte b2 = this.dataCodingByte;
            if (b2 != -1) {
                bArr2 = new byte[]{b2};
            }
            byte[] bArrHexStringToBytes = new byte[0];
            short s = this.maxRecordSize;
            if (s != -1) {
                if (s <= 256 && this.maxRecordsCount == -1) {
                    strShortToHexString2 = Hex.byteToHexString((byte) s);
                } else {
                    strShortToHexString2 = Hex.shortToHexString(s);
                }
                bArrHexStringToBytes = Hex.hexStringToBytes(strShortToHexString2);
            }
            byte[] bArrHexStringToBytes2 = new byte[0];
            short s2 = this.maxRecordsCount;
            if (s2 != -1) {
                if (s2 <= 256) {
                    strShortToHexString = Hex.byteToHexString((byte) s2);
                } else {
                    strShortToHexString = Hex.shortToHexString(s2);
                }
                bArrHexStringToBytes2 = Hex.hexStringToBytes(strShortToHexString);
            }
            bArrCatArray = catArray(bArrCatArray, getArray((byte) -126, catArray(catArray(catArray(bArr, bArr2), bArrHexStringToBytes), bArrHexStringToBytes2)));
        }
        short s3 = this.fid;
        if (s3 != -1) {
            bArrCatArray = catArray(bArrCatArray, getArray(FILE_IDENTIFIER, Hex.hexStringToBytes(Hex.shortToHexString(s3))));
        }
        byte[] bArr3 = this.dfName;
        if (bArr3 != null) {
            bArrCatArray = catArray(bArrCatArray, getArray((byte) -124, bArr3));
        }
        byte[] bArr4 = this.propInfo;
        if (bArr4 != null) {
            bArrCatArray = catArray(bArrCatArray, getArray(PROP_INFO, bArr4));
        }
        byte[] bArr5 = this.secAttrProp;
        if (bArr5 != null) {
            bArrCatArray = catArray(bArrCatArray, getArray((byte) -122, bArr5));
        }
        short s4 = this.fciExt;
        if (s4 != -1) {
            bArrCatArray = catArray(bArrCatArray, getArray(FCI_EXT, Hex.hexStringToBytes(Hex.shortToHexString(s4))));
        }
        byte b3 = this.shortEF;
        if (b3 != -1) {
            bArrCatArray = catArray(bArrCatArray, getArray((byte) -120, b3 == 0 ? new byte[0] : new byte[]{b3}));
        }
        byte b4 = this.lcsByte;
        if (b4 != -1) {
            bArrCatArray = catArray(bArrCatArray, getArray(LCS_BYTE, new byte[]{b4}));
        }
        byte[] bArr6 = this.secAttrExp;
        if (bArr6 != null) {
            bArrCatArray = catArray(bArrCatArray, getArray(SECURITY_ATTR_EXP, bArr6));
        }
        byte[] bArr7 = this.secAttrCompact;
        if (bArr7 != null) {
            bArrCatArray = catArray(bArrCatArray, getArray(SECURITY_ATTR_COMPACT, bArr7));
        }
        short s5 = this.envTempEF;
        if (s5 != -1) {
            bArrCatArray = catArray(bArrCatArray, getArray(ENV_TEMP_EF, Hex.hexStringToBytes(Hex.shortToHexString(s5))));
        }
        byte b5 = this.channelSecurity;
        if (b5 != -1) {
            bArrCatArray = catArray(bArrCatArray, getArray(CHANNEL_SECURITY, new byte[]{b5}));
        }
        byte[] bArr8 = this.a0;
        if (bArr8 != null) {
            bArrCatArray = catArray(bArrCatArray, getArray(A0, bArr8));
        }
        byte[] bArr9 = this.a1;
        if (bArr9 != null) {
            bArrCatArray = catArray(bArrCatArray, getArray(A1, bArr9));
        }
        byte[] bArr10 = this.a2;
        if (bArr10 != null) {
            bArrCatArray = catArray(bArrCatArray, getArray((byte) -94, bArr10));
        }
        byte[] bArr11 = this.a5;
        if (bArr11 != null) {
            bArrCatArray = catArray(bArrCatArray, getArray(A5, bArr11));
        }
        byte[] bArr12 = this.ab;
        if (bArr12 != null) {
            bArrCatArray = catArray(bArrCatArray, getArray(AB, bArr12));
        }
        byte[] bArr13 = this.ac;
        if (bArr13 != null) {
            bArrCatArray = catArray(bArrCatArray, getArray((byte) -84, bArr13));
        }
        return getArray(this.mainTag, bArrCatArray);
    }

    private static byte[] getArray(byte b, byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length + 2];
        bArr2[0] = b;
        bArr2[1] = (byte) bArr.length;
        System.arraycopy(bArr, 0, bArr2, 2, bArr.length);
        return bArr2;
    }

    private static byte[] catArray(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    public String toString() {
        return "Length: " + this.fileLength + "\nLength FCI: " + this.fileLengthFCI + "\nDesc byte: " + ((int) this.descriptorByte) + "\nData byte: " + ((int) this.dataCodingByte) + "\nRecord size: " + ((int) this.maxRecordSize) + "\nRecord count: " + ((int) this.maxRecordsCount) + "\nFID: " + Hex.shortToHexString(this.fid) + "\nDF name: " + Hex.bytesToHexString(this.dfName) + "\npropInfo: " + Hex.bytesToHexString(this.propInfo) + "\nsecAttrProp: " + Hex.bytesToHexString(this.secAttrProp) + "\nsecAttrExp: " + Hex.bytesToHexString(this.secAttrExp) + "\nsecAttrComp: " + Hex.bytesToHexString(this.secAttrCompact) + "\nFCI ext: " + Hex.shortToHexString(this.fciExt) + "\nEF env temp: " + Hex.shortToHexString(this.envTempEF) + "\nShort EF: " + Hex.byteToHexString(this.shortEF) + "\nLCS byte: " + Hex.byteToHexString(this.lcsByte) + "\nChannel sec: " + Hex.byteToHexString(this.channelSecurity) + "\na0: " + Hex.bytesToHexString(this.a0) + "\na1: " + Hex.bytesToHexString(this.a1) + "\na2: " + Hex.bytesToHexString(this.a2) + "\na5: " + Hex.bytesToHexString(this.a5) + "\nab: " + Hex.bytesToHexString(this.ab) + "\nac: " + Hex.bytesToHexString(this.ac) + "\n";
    }
}
