package com.adobe.internal.xmp;

import com.adobe.internal.xmp.impl.XMPMetaImpl;
import com.adobe.internal.xmp.impl.XMPMetaParser;
import com.adobe.internal.xmp.impl.XMPSchemaRegistryImpl;
import com.adobe.internal.xmp.impl.XMPSerializerHelper;
import com.adobe.internal.xmp.options.ParseOptions;
import com.adobe.internal.xmp.options.SerializeOptions;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.Attributes;
import java.util.jar.Manifest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes5.dex */
public final class XMPMetaFactory {
    private static XMPSchemaRegistry schema = new XMPSchemaRegistryImpl();
    private static XMPVersionInfo versionInfo = null;

    public static XMPSchemaRegistry getSchemaRegistry() {
        return schema;
    }

    private XMPMetaFactory() {
    }

    public static XMPMeta create() {
        return new XMPMetaImpl();
    }

    public static XMPMeta parse(InputStream inputStream) throws XMPException {
        return parse(inputStream, null);
    }

    public static XMPMeta parse(InputStream inputStream, ParseOptions parseOptions) throws XMPException {
        return XMPMetaParser.parse(inputStream, parseOptions);
    }

    public static XMPMeta parseFromString(String str) throws XMPException {
        return parseFromString(str, null);
    }

    public static XMPMeta parseFromString(String str, ParseOptions parseOptions) throws XMPException {
        return XMPMetaParser.parse(str, parseOptions);
    }

    public static XMPMeta parseFromBuffer(byte[] bArr) throws XMPException {
        return parseFromBuffer(bArr, null);
    }

    public static XMPMeta parseFromBuffer(byte[] bArr, ParseOptions parseOptions) throws XMPException {
        return XMPMetaParser.parse(bArr, parseOptions);
    }

    public static void serialize(XMPMeta xMPMeta, OutputStream outputStream) throws XMPException {
        serialize(xMPMeta, outputStream, null);
    }

    public static void serialize(XMPMeta xMPMeta, OutputStream outputStream, SerializeOptions serializeOptions) throws XMPException {
        assertImplementation(xMPMeta);
        XMPSerializerHelper.serialize((XMPMetaImpl) xMPMeta, outputStream, serializeOptions);
    }

    public static byte[] serializeToBuffer(XMPMeta xMPMeta, SerializeOptions serializeOptions) throws XMPException {
        assertImplementation(xMPMeta);
        return XMPSerializerHelper.serializeToBuffer((XMPMetaImpl) xMPMeta, serializeOptions);
    }

    public static String serializeToString(XMPMeta xMPMeta, SerializeOptions serializeOptions) throws XMPException {
        assertImplementation(xMPMeta);
        return XMPSerializerHelper.serializeToString((XMPMetaImpl) xMPMeta, serializeOptions);
    }

    private static void assertImplementation(XMPMeta xMPMeta) {
        if (!(xMPMeta instanceof XMPMetaImpl)) {
            throw new UnsupportedOperationException("The serializing service works onlywith the XMPMeta implementation of this library");
        }
    }

    public static void reset() {
        schema = new XMPSchemaRegistryImpl();
    }

    public static synchronized XMPVersionInfo getVersionInfo() {
        int i;
        final int i2;
        final int i3;
        Enumeration<URL> resources;
        if (versionInfo == null) {
            String value = "Test.SNAPSHOT";
            final int i4 = 0;
            int i5 = 5;
            try {
                resources = XMPMetaFactory.class.getClassLoader().getResources("META-INF/MANIFEST.MF");
            } catch (IOException unused) {
                i = 0;
            }
            while (resources.hasMoreElements()) {
                Attributes mainAttributes = new Manifest(resources.nextElement().openStream()).getMainAttributes();
                if ("com.adobe.xmp.xmpcore".equals(mainAttributes.getValue("Bundle-SymbolicName")) && mainAttributes.getValue("Bundle-Version") != null) {
                    value = mainAttributes.getValue("Bundle-Version");
                    Matcher matcher = Pattern.compile("(\\d+)\\.(\\d+)\\.(\\d+).*").matcher(value);
                    if (matcher.find()) {
                        i5 = Integer.parseInt(matcher.group(1));
                        i = Integer.parseInt(matcher.group(2));
                        try {
                            i4 = Integer.parseInt(matcher.group(3));
                        } catch (IOException unused2) {
                        }
                        int i6 = i5;
                        i3 = i4;
                        i4 = i;
                        i2 = i6;
                        break;
                    }
                }
            }
            i2 = 5;
            i3 = 0;
            final String str = "Adobe XMP Core " + value;
            versionInfo = new XMPVersionInfo() { // from class: com.adobe.internal.xmp.XMPMetaFactory.1
                @Override // com.adobe.internal.xmp.XMPVersionInfo
                public int getBuild() {
                    return 0;
                }

                @Override // com.adobe.internal.xmp.XMPVersionInfo
                public int getMajor() {
                    return i2;
                }

                @Override // com.adobe.internal.xmp.XMPVersionInfo
                public String getMessage() {
                    return str;
                }

                @Override // com.adobe.internal.xmp.XMPVersionInfo
                public int getMicro() {
                    return i3;
                }

                @Override // com.adobe.internal.xmp.XMPVersionInfo
                public int getMinor() {
                    return i4;
                }

                @Override // com.adobe.internal.xmp.XMPVersionInfo
                public boolean isDebug() {
                    return true;
                }

                public String toString() {
                    return str;
                }
            };
        }
        return versionInfo;
    }
}
