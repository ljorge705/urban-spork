package com.adobe.internal.xmp;

import com.adobe.internal.xmp.impl.Base64;
import com.adobe.internal.xmp.impl.ISO8601Converter;
import com.adobe.internal.xmp.impl.XMPUtilsImpl;
import com.adobe.internal.xmp.options.PropertyOptions;

/* loaded from: classes5.dex */
public class XMPUtils {
    public static String convertFromBoolean(boolean z) {
        return z ? XMPConst.TRUESTR : XMPConst.FALSESTR;
    }

    private XMPUtils() {
    }

    public static String catenateArrayItems(XMPMeta xMPMeta, String str, String str2, String str3, String str4, boolean z) throws XMPException {
        return XMPUtilsImpl.catenateArrayItems(xMPMeta, str, str2, str3, str4, z);
    }

    public static void separateArrayItems(XMPMeta xMPMeta, String str, String str2, String str3, PropertyOptions propertyOptions, boolean z) throws XMPException {
        XMPUtilsImpl.separateArrayItems(xMPMeta, str, str2, str3, propertyOptions, z);
    }

    public static void removeProperties(XMPMeta xMPMeta, String str, String str2, boolean z, boolean z2) throws XMPException {
        XMPUtilsImpl.removeProperties(xMPMeta, str, str2, z, z2);
    }

    public static void appendProperties(XMPMeta xMPMeta, XMPMeta xMPMeta2, boolean z, boolean z2) throws XMPException {
        appendProperties(xMPMeta, xMPMeta2, z, z2, false);
    }

    public static void appendProperties(XMPMeta xMPMeta, XMPMeta xMPMeta2, boolean z, boolean z2, boolean z3) throws XMPException {
        XMPUtilsImpl.appendProperties(xMPMeta, xMPMeta2, z, z2, z3);
    }

    public static boolean convertToBoolean(String str) throws XMPException {
        if (str == null || str.length() == 0) {
            throw new XMPException("Empty convert-string", 5);
        }
        String lowerCase = str.toLowerCase();
        try {
            return Integer.parseInt(lowerCase) != 0;
        } catch (NumberFormatException unused) {
            return "true".equals(lowerCase) || "t".equals(lowerCase) || "on".equals(lowerCase) || "yes".equals(lowerCase);
        }
    }

    public static int convertToInteger(String str) throws XMPException {
        if (str != null) {
            try {
                if (str.length() != 0) {
                    if (str.startsWith("0x")) {
                        return Integer.parseInt(str.substring(2), 16);
                    }
                    return Integer.parseInt(str);
                }
            } catch (NumberFormatException unused) {
                throw new XMPException("Invalid integer string", 5);
            }
        }
        throw new XMPException("Empty convert-string", 5);
    }

    public static String convertFromInteger(int i) {
        return String.valueOf(i);
    }

    public static long convertToLong(String str) throws XMPException {
        if (str != null) {
            try {
                if (str.length() != 0) {
                    if (str.startsWith("0x")) {
                        return Long.parseLong(str.substring(2), 16);
                    }
                    return Long.parseLong(str);
                }
            } catch (NumberFormatException unused) {
                throw new XMPException("Invalid long string", 5);
            }
        }
        throw new XMPException("Empty convert-string", 5);
    }

    public static String convertFromLong(long j) {
        return String.valueOf(j);
    }

    public static double convertToDouble(String str) throws XMPException {
        if (str != null) {
            try {
                if (str.length() != 0) {
                    return Double.parseDouble(str);
                }
            } catch (NumberFormatException unused) {
                throw new XMPException("Invalid double string", 5);
            }
        }
        throw new XMPException("Empty convert-string", 5);
    }

    public static String convertFromDouble(double d) {
        return String.valueOf(d);
    }

    public static XMPDateTime convertToDate(String str) throws XMPException {
        if (str == null || str.length() == 0) {
            throw new XMPException("Empty convert-string", 5);
        }
        return ISO8601Converter.parse(str);
    }

    public static String convertFromDate(XMPDateTime xMPDateTime) {
        return ISO8601Converter.render(xMPDateTime);
    }

    public static String encodeBase64(byte[] bArr) {
        return new String(Base64.encode(bArr));
    }

    public static byte[] decodeBase64(String str) throws XMPException {
        try {
            return Base64.decode(str.getBytes());
        } catch (Throwable th) {
            throw new XMPException("Invalid base64 string", 5, th);
        }
    }

    public static void duplicateSubtree(XMPMeta xMPMeta, XMPMeta xMPMeta2, String str, String str2, String str3, String str4, PropertyOptions propertyOptions) throws XMPException {
        XMPUtilsImpl.duplicateSubtree(xMPMeta, xMPMeta2, str, str2, str3, str4, propertyOptions);
    }
}
