package com.adobe.internal.xmp.impl;

import com.adobe.internal.xmp.XMPConst;
import com.adobe.internal.xmp.XMPException;
import com.adobe.internal.xmp.XMPMeta;

/* loaded from: classes5.dex */
class ParameterAsserts implements XMPConst {
    private ParameterAsserts() {
    }

    public static void assertArrayName(String str) throws XMPException {
        if (str == null || str.length() == 0) {
            throw new XMPException("Empty array name", 4);
        }
    }

    public static void assertPropName(String str) throws XMPException {
        if (str == null || str.length() == 0) {
            throw new XMPException("Empty property name", 4);
        }
    }

    public static void assertSchemaNS(String str) throws XMPException {
        if (str == null || str.length() == 0) {
            throw new XMPException("Empty schema namespace URI", 4);
        }
    }

    public static void assertPrefix(String str) throws XMPException {
        if (str == null || str.length() == 0) {
            throw new XMPException("Empty prefix", 4);
        }
    }

    public static void assertSpecificLang(String str) throws XMPException {
        if (str == null || str.length() == 0) {
            throw new XMPException("Empty specific language", 4);
        }
    }

    public static void assertStructName(String str) throws XMPException {
        if (str == null || str.length() == 0) {
            throw new XMPException("Empty array name", 4);
        }
    }

    public static void assertNotNull(Object obj) throws XMPException {
        if (obj == null) {
            throw new XMPException("Parameter must not be null", 4);
        }
        if ((obj instanceof String) && ((String) obj).length() == 0) {
            throw new XMPException("Parameter must not be null or empty", 4);
        }
    }

    public static void assertImplementation(XMPMeta xMPMeta) throws XMPException {
        if (xMPMeta == null) {
            throw new XMPException("Parameter must not be null", 4);
        }
        if (!(xMPMeta instanceof XMPMetaImpl)) {
            throw new XMPException("The XMPMeta-object is not compatible with this implementation", 4);
        }
    }
}
