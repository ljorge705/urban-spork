package com.adobe.internal.xmp.impl;

import com.adobe.internal.xmp.XMPException;
import com.adobe.internal.xmp.options.SerializeOptions;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/* loaded from: classes5.dex */
public class XMPSerializerHelper {
    public static void serialize(XMPMetaImpl xMPMetaImpl, OutputStream outputStream, SerializeOptions serializeOptions) throws XMPException {
        if (serializeOptions == null) {
            serializeOptions = new SerializeOptions();
        }
        if (serializeOptions.getSort()) {
            xMPMetaImpl.sort();
        }
        new XMPSerializerRDF().serialize(xMPMetaImpl, outputStream, serializeOptions);
    }

    public static String serializeToString(XMPMetaImpl xMPMetaImpl, SerializeOptions serializeOptions) throws XMPException {
        if (serializeOptions == null) {
            serializeOptions = new SerializeOptions();
        }
        serializeOptions.setEncodeUTF16BE(true);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(2048);
        serialize(xMPMetaImpl, byteArrayOutputStream, serializeOptions);
        try {
            return byteArrayOutputStream.toString(serializeOptions.getEncoding());
        } catch (UnsupportedEncodingException unused) {
            return byteArrayOutputStream.toString();
        }
    }

    public static byte[] serializeToBuffer(XMPMetaImpl xMPMetaImpl, SerializeOptions serializeOptions) throws XMPException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(2048);
        serialize(xMPMetaImpl, byteArrayOutputStream, serializeOptions);
        return byteArrayOutputStream.toByteArray();
    }
}
