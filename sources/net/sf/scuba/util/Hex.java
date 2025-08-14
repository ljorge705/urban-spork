package net.sf.scuba.util;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes4.dex */
public final class Hex {
    private static final String HEXCHARS = "0123456789abcdefABCDEF";
    private static final boolean LEFT = true;
    private static final String PRINTABLE = " .,:;'`\"<>()[]{}?/\\!@#$%^&*_-=+|~0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final boolean RIGHT = false;

    private Hex() {
    }

    public static String byteToHexString(byte b) {
        int i = b & 255;
        return ((i < 16 ? "0" : "") + Integer.toHexString(i)).toUpperCase();
    }

    public static String shortToHexString(short s) {
        int i = 65535 & s;
        String strSubstring = (i < 4096 ? "0" : "") + (i < 256 ? "0" : "") + (i >= 16 ? "" : "0") + Integer.toHexString(s);
        if (strSubstring.length() > 4) {
            strSubstring = strSubstring.substring(strSubstring.length() - 4, strSubstring.length());
        }
        return strSubstring.toUpperCase();
    }

    public static String intToHexString(int i) {
        return ((i < 268435456 ? "0" : "") + (i < 16777216 ? "0" : "") + (i < 1048576 ? "0" : "") + (i < 65536 ? "0" : "") + (i < 4096 ? "0" : "") + (i < 256 ? "0" : "") + (i >= 16 ? "" : "0") + Integer.toHexString(i)).toUpperCase();
    }

    public static String bytesToHexString(byte[] bArr) {
        return bytesToHexString(bArr, 1000);
    }

    public static String bytesToHexString(byte[] bArr, int i) {
        return bArr == null ? "NULL" : bytesToHexString(bArr, 0, bArr.length, i);
    }

    public static String toHexString(byte[] bArr) {
        return bytesToHexString(bArr, 0, bArr.length, 1000);
    }

    public static String toHexString(byte[] bArr, int i) {
        return bytesToHexString(bArr, 0, bArr.length, i);
    }

    public static String bytesToHexString(byte[] bArr, int i, int i2, int i3) {
        if (bArr == null) {
            return "NULL";
        }
        StringBuilder sb = new StringBuilder();
        for (int i4 = 0; i4 < i2; i4++) {
            if (i4 != 0 && i4 % i3 == 0) {
                sb.append("\n");
            }
            sb.append(byteToHexString(bArr[i + i4]));
        }
        return sb.toString();
    }

    public static String bytesToHexString(byte[] bArr, int i, int i2) {
        return bytesToHexString(bArr, i, i2, 1000);
    }

    public static byte hexStringToByte(String str) throws NumberFormatException {
        byte[] bArrHexStringToBytes = hexStringToBytes(str);
        if (bArrHexStringToBytes == null || bArrHexStringToBytes.length != 1) {
            throw new NumberFormatException();
        }
        return bArrHexStringToBytes[0];
    }

    public static short hexStringToShort(String str) throws NumberFormatException {
        byte[] bArrHexStringToBytes = hexStringToBytes(str);
        if (bArrHexStringToBytes == null || bArrHexStringToBytes.length != 2) {
            throw new NumberFormatException();
        }
        return (short) ((bArrHexStringToBytes[1] & 255) | ((bArrHexStringToBytes[0] & 255) << 8));
    }

    public static int hexStringToInt(String str) throws NumberFormatException {
        byte[] bArrHexStringToBytes = hexStringToBytes(str);
        if (bArrHexStringToBytes == null || bArrHexStringToBytes.length != 4) {
            throw new NumberFormatException();
        }
        return (bArrHexStringToBytes[3] & 255) | ((bArrHexStringToBytes[0] & 255) << 24) | ((bArrHexStringToBytes[1] & 255) << 16) | ((bArrHexStringToBytes[2] & 255) << 8);
    }

    public static byte[] hexStringToBytes(String str) throws NumberFormatException {
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (!Character.isWhitespace(cCharAt)) {
                if (HEXCHARS.indexOf(cCharAt) < 0) {
                    throw new NumberFormatException();
                }
                sb.append(cCharAt);
            }
        }
        if (sb.length() % 2 != 0) {
            sb.insert(0, "0");
        }
        byte[] bArr = new byte[sb.length() / 2];
        for (int i2 = 0; i2 < sb.length(); i2 += 2) {
            bArr[i2 / 2] = (byte) (((hexDigitToInt(sb.charAt(i2)) & 255) << 4) | (hexDigitToInt(sb.charAt(i2 + 1)) & 255));
        }
        return bArr;
    }

    static int hexDigitToInt(char c) throws NumberFormatException {
        switch (c) {
            case '0':
                return 0;
            case '1':
                return 1;
            case '2':
                return 2;
            case '3':
                return 3;
            case '4':
                return 4;
            case '5':
                return 5;
            case '6':
                return 6;
            case '7':
                return 7;
            case '8':
                return 8;
            case '9':
                return 9;
            default:
                switch (c) {
                    case 'A':
                        return 10;
                    case 'B':
                        return 11;
                    case 'C':
                        return 12;
                    case 'D':
                        return 13;
                    case 'E':
                        return 14;
                    case 'F':
                        return 15;
                    default:
                        switch (c) {
                            case 'a':
                                return 10;
                            case 'b':
                                return 11;
                            case 'c':
                                return 12;
                            case 'd':
                                return 13;
                            case 'e':
                                return 14;
                            case 'f':
                                return 15;
                            default:
                                throw new NumberFormatException();
                        }
                }
        }
    }

    private static String pad(String str, int i, char c, boolean z) {
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        if (length >= i) {
            return str;
        }
        int i2 = i - length;
        for (int i3 = 0; i3 < i2; i3++) {
            sb.append(c);
        }
        if (z) {
            return sb.append(str).toString();
        }
        return str + sb.toString();
    }

    public static String bytesToSpacedHexString(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < bArr.length) {
            sb.append(byteToHexString(bArr[i]));
            sb.append(i < bArr.length + (-1) ? StringUtils.SPACE : "");
            i++;
        }
        return sb.toString().toUpperCase();
    }

    private static String[] bytesToSpacedHexStrings(byte[] bArr, int i, int i2) {
        byte[][] bArrSplit = split(bArr, i);
        String[] strArr = new String[bArrSplit.length];
        for (int i3 = 0; i3 < bArrSplit.length; i3++) {
            strArr[i3] = pad(bytesToSpacedHexString(bArrSplit[i3]), i2, ' ', false);
        }
        return strArr;
    }

    public static String bytesToASCIIString(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            char c = (char) b;
            if (PRINTABLE.indexOf(c) < 0) {
                c = ClassUtils.PACKAGE_SEPARATOR_CHAR;
            }
            sb.append(Character.toString(c));
        }
        return sb.toString();
    }

    static String[] bytesToASCIIStrings(byte[] bArr, int i) {
        byte[][] bArrSplit = split(bArr, i);
        String[] strArr = new String[bArrSplit.length];
        for (int i2 = 0; i2 < bArrSplit.length; i2++) {
            strArr[i2] = bytesToASCIIString(bArrSplit[i2]);
        }
        return strArr;
    }

    public static byte[][] split(byte[] bArr, int i) {
        int length = bArr.length / i;
        int length2 = bArr.length % i;
        byte[][] bArr2 = new byte[(length2 > 0 ? 1 : 0) + length][];
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            byte[] bArr3 = new byte[i];
            bArr2[i3] = bArr3;
            System.arraycopy(bArr, i2, bArr3, 0, i);
            i2 += i;
        }
        if (length2 > 0) {
            byte[] bArr4 = new byte[length2];
            bArr2[length] = bArr4;
            System.arraycopy(bArr, i2, bArr4, 0, length2);
        }
        return bArr2;
    }

    public static String bytesToPrettyString(byte[] bArr) {
        return bytesToPrettyString(bArr, 16, true, 4, null, true);
    }

    public static String bytesToPrettyString(byte[] bArr, int i, boolean z, int i2, String str, boolean z2) {
        StringBuilder sb = new StringBuilder();
        String[] strArrBytesToSpacedHexStrings = bytesToSpacedHexStrings(bArr, i, i * 3);
        String[] strArrBytesToASCIIStrings = bytesToASCIIStrings(bArr, i);
        int i3 = 0;
        while (i3 < strArrBytesToSpacedHexStrings.length) {
            if (z) {
                sb.append(pad(Integer.toHexString(i3 * i).toUpperCase(), i2, '0', true) + ": ");
            } else {
                sb.append(pad(i3 == 0 ? str : "", i2, ' ', true) + StringUtils.SPACE);
            }
            sb.append(strArrBytesToSpacedHexStrings[i3]);
            if (z2) {
                sb.append(StringUtils.SPACE + strArrBytesToASCIIStrings[i3]);
            }
            sb.append("\n");
            i3++;
        }
        return sb.toString();
    }
}
