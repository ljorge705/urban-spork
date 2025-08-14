package com.adobe.internal.xmp.impl;

import com.adobe.internal.xmp.XMPException;
import com.adobe.internal.xmp.XMPMeta;
import com.adobe.internal.xmp.options.ParseOptions;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/* loaded from: classes5.dex */
public class XMPMetaParser {
    private static final Object XMP_RDF = new Object();
    private static DocumentBuilderFactory factory = createDocumentBuilderFactory();

    private XMPMetaParser() {
    }

    public static XMPMeta parse(Object obj, ParseOptions parseOptions) throws XMPException {
        ParameterAsserts.assertNotNull(obj);
        if (parseOptions == null) {
            parseOptions = new ParseOptions();
        }
        Object[] objArrFindRootNode = findRootNode(parseXml(obj, parseOptions), parseOptions.getRequireXMPMeta(), new Object[3]);
        if (objArrFindRootNode != null && objArrFindRootNode[1] == XMP_RDF) {
            XMPMetaImpl xMPMetaImpl = ParseRDF.parse((Node) objArrFindRootNode[0], parseOptions);
            xMPMetaImpl.setPacketHeader((String) objArrFindRootNode[2]);
            return !parseOptions.getOmitNormalization() ? XMPNormalizer.process(xMPMetaImpl, parseOptions) : xMPMetaImpl;
        }
        return new XMPMetaImpl();
    }

    private static Document parseXml(Object obj, ParseOptions parseOptions) throws XMPException {
        if (obj instanceof InputStream) {
            return parseXmlFromInputStream((InputStream) obj, parseOptions);
        }
        if (obj instanceof byte[]) {
            return parseXmlFromBytebuffer(new ByteBuffer((byte[]) obj), parseOptions);
        }
        return parseXmlFromString((String) obj, parseOptions);
    }

    private static Document parseXmlFromInputStream(InputStream inputStream, ParseOptions parseOptions) throws XMPException {
        if (!parseOptions.getAcceptLatin1() && !parseOptions.getFixControlChars() && !parseOptions.getDisallowDoctype()) {
            return parseInputSource(new InputSource(inputStream));
        }
        try {
            return parseXmlFromBytebuffer(new ByteBuffer(inputStream), parseOptions);
        } catch (IOException e) {
            throw new XMPException("Error reading the XML-file", 204, e);
        }
    }

    private static Document parseXmlFromBytebuffer(ByteBuffer byteBuffer, ParseOptions parseOptions) throws XMPException {
        try {
            InputSource inputSource = new InputSource(byteBuffer.getByteStream());
            try {
                if (parseOptions.getDisallowDoctype()) {
                    try {
                        factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
                    } catch (Throwable unused) {
                    }
                }
                return parseInputSource(inputSource);
            } catch (XMPException e) {
                if ("DOCTYPE is disallowed".equals(e.getCause().getMessage())) {
                    throw new XMPException(e.getCause().getMessage(), 201);
                }
                if (e.getErrorCode() != 201 && e.getErrorCode() != 204) {
                    throw e;
                }
                if (parseOptions.getAcceptLatin1()) {
                    byteBuffer = Latin1Converter.convert(byteBuffer);
                }
                if (parseOptions.getFixControlChars()) {
                    return parseInputSource(new InputSource(new FixASCIIControlsReader(new InputStreamReader(byteBuffer.getByteStream(), byteBuffer.getEncoding()))));
                }
                return parseInputSource(new InputSource(byteBuffer.getByteStream()));
            }
        } catch (UnsupportedEncodingException e2) {
            throw new XMPException("Unsupported Encoding", 9, e2);
        }
    }

    private static Document parseXmlFromString(String str, ParseOptions parseOptions) throws XMPException {
        try {
            if (parseOptions.getDisallowDoctype()) {
                try {
                    factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
                } catch (Throwable unused) {
                }
            }
            return parseInputSource(new InputSource(new StringReader(str)));
        } catch (XMPException e) {
            if (e.getErrorCode() == 201 && parseOptions.getFixControlChars()) {
                return parseInputSource(new InputSource(new FixASCIIControlsReader(new StringReader(str))));
            }
            throw e;
        }
    }

    private static Document parseInputSource(InputSource inputSource) throws ParserConfigurationException, XMPException {
        try {
            DocumentBuilder documentBuilderNewDocumentBuilder = factory.newDocumentBuilder();
            documentBuilderNewDocumentBuilder.setErrorHandler(null);
            return documentBuilderNewDocumentBuilder.parse(inputSource);
        } catch (IOException e) {
            throw new XMPException("Error reading the XML-file", 204, e);
        } catch (ParserConfigurationException e2) {
            throw new XMPException("XML Parser not correctly configured", 0, e2);
        } catch (SAXException e3) {
            throw new XMPException("XML parsing failure", 201, e3);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0031  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.Object[] findRootNode(org.w3c.dom.Node r7, boolean r8, java.lang.Object[] r9) {
        /*
            org.w3c.dom.NodeList r7 = r7.getChildNodes()
            r0 = 0
            r1 = r0
        L6:
            int r2 = r7.getLength()
            if (r1 >= r2) goto L8c
            org.w3c.dom.Node r2 = r7.item(r1)
            short r3 = r2.getNodeType()
            r4 = 7
            if (r4 != r3) goto L31
            r3 = r2
            org.w3c.dom.ProcessingInstruction r3 = (org.w3c.dom.ProcessingInstruction) r3
            java.lang.String r5 = r3.getTarget()
            java.lang.String r6 = "xpacket"
            boolean r5 = r6.equals(r5)
            if (r5 == 0) goto L31
            if (r9 == 0) goto L88
            r2 = 2
            java.lang.String r3 = r3.getData()
            r9[r2] = r3
            goto L88
        L31:
            r3 = 3
            short r5 = r2.getNodeType()
            if (r3 == r5) goto L88
            short r3 = r2.getNodeType()
            if (r4 == r3) goto L88
            java.lang.String r3 = r2.getNamespaceURI()
            java.lang.String r4 = r2.getLocalName()
            java.lang.String r5 = "xmpmeta"
            boolean r5 = r5.equals(r4)
            if (r5 != 0) goto L58
            java.lang.String r5 = "xapmeta"
            boolean r5 = r5.equals(r4)
            if (r5 == 0) goto L65
        L58:
            java.lang.String r5 = "adobe:ns:meta/"
            boolean r5 = r5.equals(r3)
            if (r5 == 0) goto L65
            java.lang.Object[] r7 = findRootNode(r2, r0, r9)
            return r7
        L65:
            if (r8 != 0) goto L81
            java.lang.String r5 = "RDF"
            boolean r4 = r5.equals(r4)
            if (r4 == 0) goto L81
            java.lang.String r4 = "http://www.w3.org/1999/02/22-rdf-syntax-ns#"
            boolean r3 = r4.equals(r3)
            if (r3 == 0) goto L81
            if (r9 == 0) goto L80
            r9[r0] = r2
            java.lang.Object r7 = com.adobe.internal.xmp.impl.XMPMetaParser.XMP_RDF
            r8 = 1
            r9[r8] = r7
        L80:
            return r9
        L81:
            java.lang.Object[] r2 = findRootNode(r2, r8, r9)
            if (r2 == 0) goto L88
            return r2
        L88:
            int r1 = r1 + 1
            goto L6
        L8c:
            r7 = 0
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.internal.xmp.impl.XMPMetaParser.findRootNode(org.w3c.dom.Node, boolean, java.lang.Object[]):java.lang.Object[]");
    }

    private static DocumentBuilderFactory createDocumentBuilderFactory() {
        DocumentBuilderFactory documentBuilderFactoryNewInstance = DocumentBuilderFactory.newInstance();
        documentBuilderFactoryNewInstance.setNamespaceAware(true);
        documentBuilderFactoryNewInstance.setIgnoringComments(true);
        documentBuilderFactoryNewInstance.setExpandEntityReferences(false);
        try {
            documentBuilderFactoryNewInstance.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            documentBuilderFactoryNewInstance.setFeature("http://xml.org/sax/features/external-general-entities", false);
            documentBuilderFactoryNewInstance.setFeature("http://xerces.apache.org/xerces2-j/features.html#disallow-doctype-decl", false);
            documentBuilderFactoryNewInstance.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            documentBuilderFactoryNewInstance.setFeature("http://xerces.apache.org/xerces2-j/features.html#external-parameter-entities", false);
            documentBuilderFactoryNewInstance.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            documentBuilderFactoryNewInstance.setXIncludeAware(false);
            documentBuilderFactoryNewInstance.setExpandEntityReferences(false);
        } catch (Throwable unused) {
        }
        return documentBuilderFactoryNewInstance;
    }
}
