package com.adobe.internal.xmp.impl;

import androidx.exifinterface.media.ExifInterface;
import com.adobe.internal.xmp.XMPConst;
import com.adobe.internal.xmp.XMPException;
import com.adobe.internal.xmp.XMPSchemaRegistry;
import com.adobe.internal.xmp.options.AliasOptions;
import com.adobe.internal.xmp.properties.XMPAliasInfo;
import io.sentry.protocol.Device;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* loaded from: classes5.dex */
public final class XMPSchemaRegistryImpl implements XMPSchemaRegistry, XMPConst {
    private Map namespaceToPrefixMap = new HashMap();
    private Map prefixToNamespaceMap = new HashMap();
    private Map aliasMap = new HashMap();
    private Pattern p = Pattern.compile("[/*?\\[\\]]");

    public XMPSchemaRegistryImpl() {
        try {
            registerStandardNamespaces();
            registerStandardAliases();
        } catch (XMPException unused) {
            throw new RuntimeException("The XMPSchemaRegistry cannot be initialized!");
        }
    }

    @Override // com.adobe.internal.xmp.XMPSchemaRegistry
    public synchronized String registerNamespace(String str, String str2) throws XMPException {
        ParameterAsserts.assertSchemaNS(str);
        ParameterAsserts.assertPrefix(str2);
        if (str2.charAt(str2.length() - 1) != ':') {
            str2 = str2 + AbstractJsonLexerKt.COLON;
        }
        if (!Utils.isXMLNameNS(str2.substring(0, str2.length() - 1))) {
            throw new XMPException("The prefix is a bad XML name", 201);
        }
        String str3 = (String) this.namespaceToPrefixMap.get(str);
        String str4 = (String) this.prefixToNamespaceMap.get(str2);
        if (str3 != null) {
            return str3;
        }
        if (str4 != null) {
            String str5 = str2;
            int i = 1;
            while (this.prefixToNamespaceMap.containsKey(str5)) {
                str5 = str2.substring(0, str2.length() - 1) + "_" + i + "_:";
                i++;
            }
            str2 = str5;
        }
        this.prefixToNamespaceMap.put(str2, str);
        this.namespaceToPrefixMap.put(str, str2);
        return str2;
    }

    @Override // com.adobe.internal.xmp.XMPSchemaRegistry
    public synchronized void deleteNamespace(String str) {
        String namespacePrefix = getNamespacePrefix(str);
        if (namespacePrefix != null) {
            this.namespaceToPrefixMap.remove(str);
            this.prefixToNamespaceMap.remove(namespacePrefix);
        }
    }

    @Override // com.adobe.internal.xmp.XMPSchemaRegistry
    public synchronized String getNamespacePrefix(String str) {
        return (String) this.namespaceToPrefixMap.get(str);
    }

    @Override // com.adobe.internal.xmp.XMPSchemaRegistry
    public synchronized String getNamespaceURI(String str) {
        if (str != null) {
            if (!str.endsWith(":")) {
                str = str + ":";
            }
        }
        return (String) this.prefixToNamespaceMap.get(str);
    }

    @Override // com.adobe.internal.xmp.XMPSchemaRegistry
    public synchronized Map getNamespaces() {
        return Collections.unmodifiableMap(new TreeMap(this.namespaceToPrefixMap));
    }

    @Override // com.adobe.internal.xmp.XMPSchemaRegistry
    public synchronized Map getPrefixes() {
        return Collections.unmodifiableMap(new TreeMap(this.prefixToNamespaceMap));
    }

    private void registerStandardNamespaces() throws XMPException {
        registerNamespace(XMPConst.NS_XML, "xml");
        registerNamespace(XMPConst.NS_RDF, "rdf");
        registerNamespace("http://purl.org/dc/elements/1.1/", "dc");
        registerNamespace(XMPConst.NS_IPTCCORE, "Iptc4xmpCore");
        registerNamespace(XMPConst.NS_IPTCEXT, "Iptc4xmpExt");
        registerNamespace(XMPConst.NS_DICOM, "DICOM");
        registerNamespace(XMPConst.NS_PLUS, "plus");
        registerNamespace(XMPConst.NS_X, "x");
        registerNamespace(XMPConst.NS_IX, "iX");
        registerNamespace("http://ns.adobe.com/xap/1.0/", "xmp");
        registerNamespace(XMPConst.NS_XMP_RIGHTS, "xmpRights");
        registerNamespace(XMPConst.NS_XMP_MM, "xmpMM");
        registerNamespace(XMPConst.NS_XMP_BJ, "xmpBJ");
        registerNamespace(XMPConst.NS_XMP_NOTE, "xmpNote");
        registerNamespace(XMPConst.NS_PDF, "pdf");
        registerNamespace(XMPConst.NS_PDFX, "pdfx");
        registerNamespace(XMPConst.NS_PDFX_ID, "pdfxid");
        registerNamespace(XMPConst.NS_PDFA_SCHEMA, "pdfaSchema");
        registerNamespace(XMPConst.NS_PDFA_PROPERTY, "pdfaProperty");
        registerNamespace(XMPConst.NS_PDFA_TYPE, "pdfaType");
        registerNamespace(XMPConst.NS_PDFA_FIELD, "pdfaField");
        registerNamespace(XMPConst.NS_PDFA_ID, "pdfaid");
        registerNamespace(XMPConst.NS_PDFA_EXTENSION, "pdfaExtension");
        registerNamespace(XMPConst.NS_PHOTOSHOP, "photoshop");
        registerNamespace(XMPConst.NS_PSALBUM, "album");
        registerNamespace("http://ns.adobe.com/exif/1.0/", "exif");
        registerNamespace(XMPConst.NS_EXIFX, "exifEX");
        registerNamespace("http://ns.adobe.com/exif/1.0/aux/", "aux");
        registerNamespace("http://ns.adobe.com/tiff/1.0/", "tiff");
        registerNamespace(XMPConst.NS_PNG, "png");
        registerNamespace(XMPConst.NS_JPEG, "jpeg");
        registerNamespace(XMPConst.NS_JP2K, "jp2k");
        registerNamespace(XMPConst.NS_CAMERARAW, "crs");
        registerNamespace(XMPConst.NS_ADOBESTOCKPHOTO, "bmsp");
        registerNamespace(XMPConst.NS_CREATOR_ATOM, "creatorAtom");
        registerNamespace(XMPConst.NS_ASF, "asf");
        registerNamespace(XMPConst.NS_WAV, "wav");
        registerNamespace(XMPConst.NS_BWF, "bext");
        registerNamespace(XMPConst.NS_RIFFINFO, "riffinfo");
        registerNamespace(XMPConst.NS_SCRIPT, "xmpScript");
        registerNamespace(XMPConst.NS_TXMP, "txmp");
        registerNamespace(XMPConst.NS_SWF, "swf");
        registerNamespace(XMPConst.NS_CCV, "ccv");
        registerNamespace(XMPConst.NS_DM, "xmpDM");
        registerNamespace(XMPConst.NS_TRANSIENT, "xmpx");
        registerNamespace(XMPConst.TYPE_TEXT, "xmpT");
        registerNamespace(XMPConst.TYPE_PAGEDFILE, "xmpTPg");
        registerNamespace(XMPConst.TYPE_GRAPHICS, "xmpG");
        registerNamespace(XMPConst.TYPE_IMAGE, "xmpGImg");
        registerNamespace(XMPConst.TYPE_FONT, "stFnt");
        registerNamespace(XMPConst.TYPE_DIMENSIONS, "stDim");
        registerNamespace(XMPConst.TYPE_RESOURCEEVENT, "stEvt");
        registerNamespace(XMPConst.TYPE_RESOURCEREF, "stRef");
        registerNamespace(XMPConst.TYPE_ST_VERSION, "stVer");
        registerNamespace(XMPConst.TYPE_ST_JOB, "stJob");
        registerNamespace(XMPConst.TYPE_MANIFESTITEM, "stMfs");
        registerNamespace(XMPConst.TYPE_IDENTIFIERQUAL, "xmpidq");
    }

    @Override // com.adobe.internal.xmp.XMPSchemaRegistry
    public synchronized XMPAliasInfo resolveAlias(String str, String str2) {
        String namespacePrefix = getNamespacePrefix(str);
        if (namespacePrefix == null) {
            return null;
        }
        return (XMPAliasInfo) this.aliasMap.get(namespacePrefix + str2);
    }

    @Override // com.adobe.internal.xmp.XMPSchemaRegistry
    public synchronized XMPAliasInfo findAlias(String str) {
        return (XMPAliasInfo) this.aliasMap.get(str);
    }

    @Override // com.adobe.internal.xmp.XMPSchemaRegistry
    public synchronized XMPAliasInfo[] findAliases(String str) {
        ArrayList arrayList;
        String namespacePrefix = getNamespacePrefix(str);
        arrayList = new ArrayList();
        if (namespacePrefix != null) {
            for (String str2 : this.aliasMap.keySet()) {
                if (str2.startsWith(namespacePrefix)) {
                    arrayList.add(findAlias(str2));
                }
            }
        }
        return (XMPAliasInfo[]) arrayList.toArray(new XMPAliasInfo[arrayList.size()]);
    }

    synchronized void registerAlias(String str, String str2, final String str3, final String str4, AliasOptions aliasOptions) throws XMPException {
        ParameterAsserts.assertSchemaNS(str);
        ParameterAsserts.assertPropName(str2);
        ParameterAsserts.assertSchemaNS(str3);
        ParameterAsserts.assertPropName(str4);
        final AliasOptions aliasOptions2 = aliasOptions != null ? new AliasOptions(XMPNodeUtils.verifySetOptions(aliasOptions.toPropertyOptions(), null).getOptions()) : new AliasOptions();
        if (this.p.matcher(str2).find() || this.p.matcher(str4).find()) {
            throw new XMPException("Alias and actual property names must be simple", 102);
        }
        String namespacePrefix = getNamespacePrefix(str);
        final String namespacePrefix2 = getNamespacePrefix(str3);
        if (namespacePrefix == null) {
            throw new XMPException("Alias namespace is not registered", 101);
        }
        if (namespacePrefix2 == null) {
            throw new XMPException("Actual namespace is not registered", 101);
        }
        String str5 = namespacePrefix + str2;
        if (this.aliasMap.containsKey(str5)) {
            throw new XMPException("Alias is already existing", 4);
        }
        if (this.aliasMap.containsKey(namespacePrefix2 + str4)) {
            throw new XMPException("Actual property is already an alias, use the base property", 4);
        }
        this.aliasMap.put(str5, new XMPAliasInfo() { // from class: com.adobe.internal.xmp.impl.XMPSchemaRegistryImpl.1
            @Override // com.adobe.internal.xmp.properties.XMPAliasInfo
            public AliasOptions getAliasForm() {
                return aliasOptions2;
            }

            @Override // com.adobe.internal.xmp.properties.XMPAliasInfo
            public String getNamespace() {
                return str3;
            }

            @Override // com.adobe.internal.xmp.properties.XMPAliasInfo
            public String getPrefix() {
                return namespacePrefix2;
            }

            @Override // com.adobe.internal.xmp.properties.XMPAliasInfo
            public String getPropName() {
                return str4;
            }

            public String toString() {
                return namespacePrefix2 + str4 + " NS(" + str3 + "), FORM (" + getAliasForm() + ")";
            }
        });
    }

    @Override // com.adobe.internal.xmp.XMPSchemaRegistry
    public synchronized Map getAliases() {
        return Collections.unmodifiableMap(new TreeMap(this.aliasMap));
    }

    private void registerStandardAliases() throws XMPException {
        AliasOptions arrayOrdered = new AliasOptions().setArrayOrdered(true);
        AliasOptions arrayAltText = new AliasOptions().setArrayAltText(true);
        registerAlias("http://ns.adobe.com/xap/1.0/", "Author", "http://purl.org/dc/elements/1.1/", "creator", arrayOrdered);
        registerAlias("http://ns.adobe.com/xap/1.0/", "Authors", "http://purl.org/dc/elements/1.1/", "creator", null);
        registerAlias("http://ns.adobe.com/xap/1.0/", "Description", "http://purl.org/dc/elements/1.1/", "description", null);
        registerAlias("http://ns.adobe.com/xap/1.0/", "Format", "http://purl.org/dc/elements/1.1/", "format", null);
        registerAlias("http://ns.adobe.com/xap/1.0/", "Keywords", "http://purl.org/dc/elements/1.1/", "subject", null);
        registerAlias("http://ns.adobe.com/xap/1.0/", "Locale", "http://purl.org/dc/elements/1.1/", Device.JsonKeys.LANGUAGE, null);
        registerAlias("http://ns.adobe.com/xap/1.0/", "Title", "http://purl.org/dc/elements/1.1/", "title", null);
        registerAlias(XMPConst.NS_XMP_RIGHTS, ExifInterface.TAG_COPYRIGHT, "http://purl.org/dc/elements/1.1/", "rights", null);
        registerAlias(XMPConst.NS_PDF, "Author", "http://purl.org/dc/elements/1.1/", "creator", arrayOrdered);
        registerAlias(XMPConst.NS_PDF, "BaseURL", "http://ns.adobe.com/xap/1.0/", "BaseURL", null);
        registerAlias(XMPConst.NS_PDF, "CreationDate", "http://ns.adobe.com/xap/1.0/", "CreateDate", null);
        registerAlias(XMPConst.NS_PDF, "Creator", "http://ns.adobe.com/xap/1.0/", "CreatorTool", null);
        registerAlias(XMPConst.NS_PDF, "ModDate", "http://ns.adobe.com/xap/1.0/", "ModifyDate", null);
        registerAlias(XMPConst.NS_PDF, "Subject", "http://purl.org/dc/elements/1.1/", "description", arrayAltText);
        registerAlias(XMPConst.NS_PDF, "Title", "http://purl.org/dc/elements/1.1/", "title", arrayAltText);
        registerAlias(XMPConst.NS_PHOTOSHOP, "Author", "http://purl.org/dc/elements/1.1/", "creator", arrayOrdered);
        registerAlias(XMPConst.NS_PHOTOSHOP, "Caption", "http://purl.org/dc/elements/1.1/", "description", arrayAltText);
        registerAlias(XMPConst.NS_PHOTOSHOP, ExifInterface.TAG_COPYRIGHT, "http://purl.org/dc/elements/1.1/", "rights", arrayAltText);
        registerAlias(XMPConst.NS_PHOTOSHOP, "Keywords", "http://purl.org/dc/elements/1.1/", "subject", null);
        registerAlias(XMPConst.NS_PHOTOSHOP, "Marked", XMPConst.NS_XMP_RIGHTS, "Marked", null);
        registerAlias(XMPConst.NS_PHOTOSHOP, "Title", "http://purl.org/dc/elements/1.1/", "title", arrayAltText);
        registerAlias(XMPConst.NS_PHOTOSHOP, "WebStatement", XMPConst.NS_XMP_RIGHTS, "WebStatement", null);
        registerAlias("http://ns.adobe.com/tiff/1.0/", ExifInterface.TAG_ARTIST, "http://purl.org/dc/elements/1.1/", "creator", arrayOrdered);
        registerAlias("http://ns.adobe.com/tiff/1.0/", ExifInterface.TAG_COPYRIGHT, "http://purl.org/dc/elements/1.1/", "rights", null);
        registerAlias("http://ns.adobe.com/tiff/1.0/", ExifInterface.TAG_DATETIME, "http://ns.adobe.com/xap/1.0/", "ModifyDate", null);
        registerAlias("http://ns.adobe.com/tiff/1.0/", ExifInterface.TAG_IMAGE_DESCRIPTION, "http://purl.org/dc/elements/1.1/", "description", null);
        registerAlias("http://ns.adobe.com/tiff/1.0/", ExifInterface.TAG_SOFTWARE, "http://ns.adobe.com/xap/1.0/", "CreatorTool", null);
        registerAlias(XMPConst.NS_PNG, "Author", "http://purl.org/dc/elements/1.1/", "creator", arrayOrdered);
        registerAlias(XMPConst.NS_PNG, ExifInterface.TAG_COPYRIGHT, "http://purl.org/dc/elements/1.1/", "rights", arrayAltText);
        registerAlias(XMPConst.NS_PNG, "CreationTime", "http://ns.adobe.com/xap/1.0/", "CreateDate", null);
        registerAlias(XMPConst.NS_PNG, "Description", "http://purl.org/dc/elements/1.1/", "description", arrayAltText);
        registerAlias(XMPConst.NS_PNG, "ModificationTime", "http://ns.adobe.com/xap/1.0/", "ModifyDate", null);
        registerAlias(XMPConst.NS_PNG, ExifInterface.TAG_SOFTWARE, "http://ns.adobe.com/xap/1.0/", "CreatorTool", null);
        registerAlias(XMPConst.NS_PNG, "Title", "http://purl.org/dc/elements/1.1/", "title", arrayAltText);
    }
}
