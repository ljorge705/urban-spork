package com.adobe.internal.xmp.impl;

import com.adobe.internal.xmp.XMPConst;
import com.adobe.internal.xmp.XMPError;
import com.adobe.internal.xmp.XMPException;
import com.adobe.internal.xmp.XMPMetaFactory;
import com.adobe.internal.xmp.XMPSchemaRegistry;
import com.adobe.internal.xmp.options.ParseOptions;
import com.adobe.internal.xmp.options.PropertyOptions;
import java.util.ArrayList;
import java.util.Iterator;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/* loaded from: classes5.dex */
public class ParseRDF implements XMPError, XMPConst {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final String DEFAULT_PREFIX = "_dflt";
    public static final int RDFTERM_ABOUT = 3;
    public static final int RDFTERM_ABOUT_EACH = 10;
    public static final int RDFTERM_ABOUT_EACH_PREFIX = 11;
    public static final int RDFTERM_BAG_ID = 12;
    public static final int RDFTERM_DATATYPE = 7;
    public static final int RDFTERM_DESCRIPTION = 8;
    public static final int RDFTERM_FIRST_CORE = 1;
    public static final int RDFTERM_FIRST_OLD = 10;
    public static final int RDFTERM_FIRST_SYNTAX = 1;
    public static final int RDFTERM_ID = 2;
    public static final int RDFTERM_LAST_CORE = 7;
    public static final int RDFTERM_LAST_OLD = 12;
    public static final int RDFTERM_LAST_SYNTAX = 9;
    public static final int RDFTERM_LI = 9;
    public static final int RDFTERM_NODE_ID = 6;
    public static final int RDFTERM_OTHER = 0;
    public static final int RDFTERM_PARSE_TYPE = 4;
    public static final int RDFTERM_RDF = 1;
    public static final int RDFTERM_RESOURCE = 5;

    private static boolean isCoreSyntaxTerm(int i) {
        return 1 <= i && i <= 7;
    }

    private static boolean isOldTerm(int i) {
        return 10 <= i && i <= 12;
    }

    static XMPMetaImpl parse(Node node, ParseOptions parseOptions) throws XMPException {
        XMPMetaImpl xMPMetaImpl = new XMPMetaImpl();
        rdf_RDF(xMPMetaImpl, node, parseOptions);
        return xMPMetaImpl;
    }

    static void rdf_RDF(XMPMetaImpl xMPMetaImpl, Node node, ParseOptions parseOptions) throws DOMException, XMPException {
        if (node.hasAttributes()) {
            rdf_NodeElementList(xMPMetaImpl, xMPMetaImpl.getRoot(), node, parseOptions);
            return;
        }
        throw new XMPException("Invalid attributes of rdf:RDF element", 202);
    }

    private static void rdf_NodeElementList(XMPMetaImpl xMPMetaImpl, XMPNode xMPNode, Node node, ParseOptions parseOptions) throws DOMException, XMPException {
        for (int i = 0; i < node.getChildNodes().getLength(); i++) {
            Node nodeItem = node.getChildNodes().item(i);
            if (!isWhitespaceNode(nodeItem)) {
                rdf_NodeElement(xMPMetaImpl, xMPNode, nodeItem, true, parseOptions);
            }
        }
    }

    private static void rdf_NodeElement(XMPMetaImpl xMPMetaImpl, XMPNode xMPNode, Node node, boolean z, ParseOptions parseOptions) throws DOMException, XMPException {
        int rDFTermKind = getRDFTermKind(node);
        if (rDFTermKind != 8 && rDFTermKind != 0) {
            throw new XMPException("Node element must be rdf:Description or typed node", 202);
        }
        if (z && rDFTermKind == 0) {
            throw new XMPException("Top level typed node not allowed", 203);
        }
        rdf_NodeElementAttrs(xMPMetaImpl, xMPNode, node, z, parseOptions);
        rdf_PropertyElementList(xMPMetaImpl, xMPNode, node, z, parseOptions);
    }

    private static void rdf_NodeElementAttrs(XMPMetaImpl xMPMetaImpl, XMPNode xMPNode, Node node, boolean z, ParseOptions parseOptions) throws XMPException {
        int i = 0;
        for (int i2 = 0; i2 < node.getAttributes().getLength(); i2++) {
            Node nodeItem = node.getAttributes().item(i2);
            if (!"xmlns".equals(nodeItem.getPrefix()) && (nodeItem.getPrefix() != null || !"xmlns".equals(nodeItem.getNodeName()))) {
                int rDFTermKind = getRDFTermKind(nodeItem);
                if (rDFTermKind == 0) {
                    addChildNode(xMPMetaImpl, xMPNode, nodeItem, nodeItem.getNodeValue(), z);
                } else {
                    if (rDFTermKind != 6 && rDFTermKind != 2 && rDFTermKind != 3) {
                        throw new XMPException("Invalid nodeElement attribute", 202);
                    }
                    if (i > 0) {
                        throw new XMPException("Mutally exclusive about, ID, nodeID attributes", 202);
                    }
                    i++;
                    if (z && rDFTermKind == 3) {
                        if (xMPNode.getName() != null && xMPNode.getName().length() > 0) {
                            if (!xMPNode.getName().equals(nodeItem.getNodeValue())) {
                                throw new XMPException("Mismatched top level rdf:about values", 203);
                            }
                        } else {
                            xMPNode.setName(nodeItem.getNodeValue());
                        }
                    }
                }
            }
        }
    }

    private static void rdf_PropertyElementList(XMPMetaImpl xMPMetaImpl, XMPNode xMPNode, Node node, boolean z, ParseOptions parseOptions) throws DOMException, XMPException {
        for (int i = 0; i < node.getChildNodes().getLength(); i++) {
            Node nodeItem = node.getChildNodes().item(i);
            if (!isWhitespaceNode(nodeItem)) {
                if (nodeItem.getNodeType() != 1) {
                    throw new XMPException("Expected property element node not found", 202);
                }
                if (xMPNode.getOptions().isArrayLimited() && i > xMPNode.getOptions().getArrayElementsLimit()) {
                    return;
                } else {
                    rdf_PropertyElement(xMPMetaImpl, xMPNode, nodeItem, z, parseOptions);
                }
            }
        }
    }

    private static void rdf_PropertyElement(XMPMetaImpl xMPMetaImpl, XMPNode xMPNode, Node node, boolean z, ParseOptions parseOptions) throws DOMException, XMPException {
        if (!isPropertyElementName(getRDFTermKind(node))) {
            throw new XMPException("Invalid property element name", 202);
        }
        NamedNodeMap attributes = node.getAttributes();
        ArrayList arrayList = null;
        for (int i = 0; i < attributes.getLength(); i++) {
            Node nodeItem = attributes.item(i);
            if ("xmlns".equals(nodeItem.getPrefix()) || (nodeItem.getPrefix() == null && "xmlns".equals(nodeItem.getNodeName()))) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(nodeItem.getNodeName());
            }
        }
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                attributes.removeNamedItem((String) it.next());
            }
        }
        if (attributes.getLength() > 3) {
            rdf_EmptyPropertyElement(xMPMetaImpl, xMPNode, node, z);
            return;
        }
        for (int i2 = 0; i2 < attributes.getLength(); i2++) {
            Node nodeItem2 = attributes.item(i2);
            String localName = nodeItem2.getLocalName();
            String namespaceURI = nodeItem2.getNamespaceURI();
            String nodeValue = nodeItem2.getNodeValue();
            if (!XMPConst.XML_LANG.equals(nodeItem2.getNodeName()) || ("ID".equals(localName) && XMPConst.NS_RDF.equals(namespaceURI))) {
                if ("datatype".equals(localName) && XMPConst.NS_RDF.equals(namespaceURI)) {
                    rdf_LiteralPropertyElement(xMPMetaImpl, xMPNode, node, z);
                    return;
                }
                if (!"parseType".equals(localName) || !XMPConst.NS_RDF.equals(namespaceURI)) {
                    rdf_EmptyPropertyElement(xMPMetaImpl, xMPNode, node, z);
                    return;
                }
                if ("Literal".equals(nodeValue)) {
                    rdf_ParseTypeLiteralPropertyElement();
                    return;
                }
                if ("Resource".equals(nodeValue)) {
                    rdf_ParseTypeResourcePropertyElement(xMPMetaImpl, xMPNode, node, z, parseOptions);
                    return;
                } else if ("Collection".equals(nodeValue)) {
                    rdf_ParseTypeCollectionPropertyElement();
                    return;
                } else {
                    rdf_ParseTypeOtherPropertyElement();
                    return;
                }
            }
        }
        if (node.hasChildNodes()) {
            for (int i3 = 0; i3 < node.getChildNodes().getLength(); i3++) {
                if (node.getChildNodes().item(i3).getNodeType() != 3) {
                    rdf_ResourcePropertyElement(xMPMetaImpl, xMPNode, node, z, parseOptions);
                    return;
                }
            }
            rdf_LiteralPropertyElement(xMPMetaImpl, xMPNode, node, z);
            return;
        }
        rdf_EmptyPropertyElement(xMPMetaImpl, xMPNode, node, z);
    }

    private static void rdf_ResourcePropertyElement(XMPMetaImpl xMPMetaImpl, XMPNode xMPNode, Node node, boolean z, ParseOptions parseOptions) throws XMPException {
        Integer num;
        if (z && "iX:changes".equals(node.getNodeName())) {
            return;
        }
        XMPNode xMPNodeAddChildNode = addChildNode(xMPMetaImpl, xMPNode, node, "", z);
        for (int i = 0; i < node.getAttributes().getLength(); i++) {
            Node nodeItem = node.getAttributes().item(i);
            if (!"xmlns".equals(nodeItem.getPrefix()) && (nodeItem.getPrefix() != null || !"xmlns".equals(nodeItem.getNodeName()))) {
                String localName = nodeItem.getLocalName();
                String namespaceURI = nodeItem.getNamespaceURI();
                if (XMPConst.XML_LANG.equals(nodeItem.getNodeName())) {
                    addQualifierNode(xMPNodeAddChildNode, XMPConst.XML_LANG, nodeItem.getNodeValue());
                } else if (!"ID".equals(localName) || !XMPConst.NS_RDF.equals(namespaceURI)) {
                    throw new XMPException("Invalid attribute for resource property element", 202);
                }
            }
        }
        boolean z2 = false;
        for (int i2 = 0; i2 < node.getChildNodes().getLength(); i2++) {
            Node nodeItem2 = node.getChildNodes().item(i2);
            if (!isWhitespaceNode(nodeItem2)) {
                if (nodeItem2.getNodeType() != 1 || z2) {
                    if (z2) {
                        throw new XMPException("Invalid child of resource property element", 202);
                    }
                    throw new XMPException("Children of resource property element must be XML elements", 202);
                }
                boolean zEquals = XMPConst.NS_RDF.equals(nodeItem2.getNamespaceURI());
                String localName2 = nodeItem2.getLocalName();
                if (zEquals && "Bag".equals(localName2)) {
                    xMPNodeAddChildNode.getOptions().setArray(true);
                } else if (zEquals && "Seq".equals(localName2)) {
                    xMPNodeAddChildNode.getOptions().setArray(true).setArrayOrdered(true);
                } else if (zEquals && "Alt".equals(localName2)) {
                    xMPNodeAddChildNode.getOptions().setArray(true).setArrayOrdered(true).setArrayAlternate(true);
                } else {
                    xMPNodeAddChildNode.getOptions().setStruct(true);
                    if (!zEquals && !"Description".equals(localName2)) {
                        String namespaceURI2 = nodeItem2.getNamespaceURI();
                        if (namespaceURI2 == null) {
                            throw new XMPException("All XML elements must be in a namespace", 203);
                        }
                        addQualifierNode(xMPNodeAddChildNode, XMPConst.RDF_TYPE, namespaceURI2 + AbstractJsonLexerKt.COLON + localName2);
                    }
                }
                if (xMPNodeAddChildNode.getOptions().isArray() && (num = parseOptions.getXMPNodesToLimit().get(xMPNodeAddChildNode.getName())) != null) {
                    xMPNodeAddChildNode.getOptions().setArrayElementLimit(num.intValue());
                }
                rdf_NodeElement(xMPMetaImpl, xMPNodeAddChildNode, nodeItem2, false, parseOptions);
                if (xMPNodeAddChildNode.getHasValueChild()) {
                    fixupQualifiedNode(xMPNodeAddChildNode);
                } else if (xMPNodeAddChildNode.getOptions().isArrayAlternate()) {
                    XMPNodeUtils.detectAltText(xMPNodeAddChildNode);
                }
                z2 = true;
            }
        }
        if (!z2) {
            throw new XMPException("Missing child of resource property element", 202);
        }
    }

    private static void rdf_LiteralPropertyElement(XMPMetaImpl xMPMetaImpl, XMPNode xMPNode, Node node, boolean z) throws XMPException {
        XMPNode xMPNodeAddChildNode = addChildNode(xMPMetaImpl, xMPNode, node, null, z);
        for (int i = 0; i < node.getAttributes().getLength(); i++) {
            Node nodeItem = node.getAttributes().item(i);
            if (!"xmlns".equals(nodeItem.getPrefix()) && (nodeItem.getPrefix() != null || !"xmlns".equals(nodeItem.getNodeName()))) {
                String namespaceURI = nodeItem.getNamespaceURI();
                String localName = nodeItem.getLocalName();
                if (XMPConst.XML_LANG.equals(nodeItem.getNodeName())) {
                    addQualifierNode(xMPNodeAddChildNode, XMPConst.XML_LANG, nodeItem.getNodeValue());
                } else if (!XMPConst.NS_RDF.equals(namespaceURI) || (!"ID".equals(localName) && !"datatype".equals(localName))) {
                    throw new XMPException("Invalid attribute for literal property element", 202);
                }
            }
        }
        String str = "";
        for (int i2 = 0; i2 < node.getChildNodes().getLength(); i2++) {
            Node nodeItem2 = node.getChildNodes().item(i2);
            if (nodeItem2.getNodeType() == 3) {
                str = str + nodeItem2.getNodeValue();
            } else {
                throw new XMPException("Invalid child of literal property element", 202);
            }
        }
        xMPNodeAddChildNode.setValue(str);
    }

    private static void rdf_ParseTypeLiteralPropertyElement() throws XMPException {
        throw new XMPException("ParseTypeLiteral property element not allowed", 203);
    }

    private static void rdf_ParseTypeResourcePropertyElement(XMPMetaImpl xMPMetaImpl, XMPNode xMPNode, Node node, boolean z, ParseOptions parseOptions) throws XMPException {
        XMPNode xMPNodeAddChildNode = addChildNode(xMPMetaImpl, xMPNode, node, "", z);
        xMPNodeAddChildNode.getOptions().setStruct(true);
        for (int i = 0; i < node.getAttributes().getLength(); i++) {
            Node nodeItem = node.getAttributes().item(i);
            if (!"xmlns".equals(nodeItem.getPrefix()) && (nodeItem.getPrefix() != null || !"xmlns".equals(nodeItem.getNodeName()))) {
                String localName = nodeItem.getLocalName();
                String namespaceURI = nodeItem.getNamespaceURI();
                if (XMPConst.XML_LANG.equals(nodeItem.getNodeName())) {
                    addQualifierNode(xMPNodeAddChildNode, XMPConst.XML_LANG, nodeItem.getNodeValue());
                } else if (!XMPConst.NS_RDF.equals(namespaceURI) || (!"ID".equals(localName) && !"parseType".equals(localName))) {
                    throw new XMPException("Invalid attribute for ParseTypeResource property element", 202);
                }
            }
        }
        rdf_PropertyElementList(xMPMetaImpl, xMPNodeAddChildNode, node, false, parseOptions);
        if (xMPNodeAddChildNode.getHasValueChild()) {
            fixupQualifiedNode(xMPNodeAddChildNode);
        }
    }

    private static void rdf_ParseTypeCollectionPropertyElement() throws XMPException {
        throw new XMPException("ParseTypeCollection property element not allowed", 203);
    }

    private static void rdf_ParseTypeOtherPropertyElement() throws XMPException {
        throw new XMPException("ParseTypeOther property element not allowed", 203);
    }

    /* JADX WARN: Removed duplicated region for block: B:63:0x00f1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void rdf_EmptyPropertyElement(com.adobe.internal.xmp.impl.XMPMetaImpl r16, com.adobe.internal.xmp.impl.XMPNode r17, org.w3c.dom.Node r18, boolean r19) throws com.adobe.internal.xmp.XMPException {
        /*
            Method dump skipped, instructions count: 362
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.internal.xmp.impl.ParseRDF.rdf_EmptyPropertyElement(com.adobe.internal.xmp.impl.XMPMetaImpl, com.adobe.internal.xmp.impl.XMPNode, org.w3c.dom.Node, boolean):void");
    }

    private static XMPNode addChildNode(XMPMetaImpl xMPMetaImpl, XMPNode xMPNode, Node node, String str, boolean z) throws XMPException {
        XMPSchemaRegistry schemaRegistry = XMPMetaFactory.getSchemaRegistry();
        String namespaceURI = node.getNamespaceURI();
        if (namespaceURI != null) {
            if (XMPConst.NS_DC_DEPRECATED.equals(namespaceURI)) {
                namespaceURI = "http://purl.org/dc/elements/1.1/";
            }
            String namespacePrefix = schemaRegistry.getNamespacePrefix(namespaceURI);
            if (namespacePrefix == null) {
                namespacePrefix = schemaRegistry.registerNamespace(namespaceURI, node.getPrefix() != null ? node.getPrefix() : DEFAULT_PREFIX);
            }
            String str2 = namespacePrefix + node.getLocalName();
            PropertyOptions propertyOptions = new PropertyOptions();
            boolean z2 = false;
            if (z) {
                xMPNode = XMPNodeUtils.findSchemaNode(xMPMetaImpl.getRoot(), namespaceURI, DEFAULT_PREFIX, true);
                xMPNode.setImplicit(false);
                if (schemaRegistry.findAlias(str2) != null) {
                    xMPMetaImpl.getRoot().setHasAliases(true);
                    xMPNode.setHasAliases(true);
                    z2 = true;
                }
            }
            boolean zIsNumberedArrayItemName = isNumberedArrayItemName(str2);
            boolean zEquals = "rdf:value".equals(str2);
            XMPNode xMPNode2 = new XMPNode(str2, str, propertyOptions);
            xMPNode2.setAlias(z2);
            if (!zEquals) {
                xMPNode.addChild(xMPNode2);
            } else {
                xMPNode.addChild(1, xMPNode2);
            }
            if (zEquals) {
                if (z || !xMPNode.getOptions().isStruct()) {
                    throw new XMPException("Misplaced rdf:value element", 202);
                }
                xMPNode.setHasValueChild(true);
            }
            boolean zIsArray = xMPNode.getOptions().isArray();
            if (zIsArray && zIsNumberedArrayItemName) {
                xMPNode2.setName("[]");
            } else {
                if (!zIsArray && zIsNumberedArrayItemName) {
                    throw new XMPException("Misplaced rdf:li element", 202);
                }
                if (zIsArray && !zIsNumberedArrayItemName) {
                    throw new XMPException("Arrays cannot have arbitrary child names", 202);
                }
            }
            return xMPNode2;
        }
        throw new XMPException("XML namespace required for all elements and attributes", 202);
    }

    private static XMPNode addQualifierNode(XMPNode xMPNode, String str, String str2) throws XMPException {
        if (XMPConst.XML_LANG.equals(str)) {
            str2 = Utils.normalizeLangValue(str2);
        }
        XMPNode xMPNode2 = new XMPNode(str, str2, null);
        xMPNode.addQualifier(xMPNode2);
        return xMPNode2;
    }

    private static void fixupQualifiedNode(XMPNode xMPNode) throws XMPException {
        XMPNode child = xMPNode.getChild(1);
        if (child.getOptions().getHasLanguage()) {
            if (xMPNode.getOptions().getHasLanguage()) {
                throw new XMPException("Redundant xml:lang for rdf:value element", 203);
            }
            XMPNode qualifier = child.getQualifier(1);
            child.removeQualifier(qualifier);
            xMPNode.addQualifier(qualifier);
        }
        for (int i = 1; i <= child.getQualifierLength(); i++) {
            xMPNode.addQualifier(child.getQualifier(i));
        }
        for (int i2 = 2; i2 <= xMPNode.getChildrenLength(); i2++) {
            xMPNode.addQualifier(xMPNode.getChild(i2));
        }
        xMPNode.setHasValueChild(false);
        xMPNode.getOptions().setStruct(false);
        xMPNode.getOptions().mergeWith(child.getOptions());
        xMPNode.setValue(child.getValue());
        xMPNode.removeChildren();
        Iterator itIterateChildren = child.iterateChildren();
        while (itIterateChildren.hasNext()) {
            xMPNode.addChild((XMPNode) itIterateChildren.next());
        }
    }

    private static boolean isWhitespaceNode(Node node) throws DOMException {
        if (node.getNodeType() != 3) {
            return false;
        }
        String nodeValue = node.getNodeValue();
        for (int i = 0; i < nodeValue.length(); i++) {
            if (!Character.isWhitespace(nodeValue.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private static boolean isPropertyElementName(int i) {
        if (i == 8 || isOldTerm(i)) {
            return false;
        }
        return !isCoreSyntaxTerm(i);
    }

    private static int getRDFTermKind(Node node) {
        String localName = node.getLocalName();
        String namespaceURI = node.getNamespaceURI();
        if (namespaceURI == null && (("about".equals(localName) || "ID".equals(localName)) && (node instanceof Attr) && XMPConst.NS_RDF.equals(((Attr) node).getOwnerElement().getNamespaceURI()))) {
            namespaceURI = XMPConst.NS_RDF;
        }
        if (!XMPConst.NS_RDF.equals(namespaceURI)) {
            return 0;
        }
        if ("li".equals(localName)) {
            return 9;
        }
        if ("parseType".equals(localName)) {
            return 4;
        }
        if ("Description".equals(localName)) {
            return 8;
        }
        if ("about".equals(localName)) {
            return 3;
        }
        if ("resource".equals(localName)) {
            return 5;
        }
        if ("RDF".equals(localName)) {
            return 1;
        }
        if ("ID".equals(localName)) {
            return 2;
        }
        if ("nodeID".equals(localName)) {
            return 6;
        }
        if ("datatype".equals(localName)) {
            return 7;
        }
        if ("aboutEach".equals(localName)) {
            return 10;
        }
        if ("aboutEachPrefix".equals(localName)) {
            return 11;
        }
        return "bagID".equals(localName) ? 12 : 0;
    }

    private static boolean isNumberedArrayItemName(String str) {
        boolean zEquals = "rdf:li".equals(str);
        if (!str.startsWith("rdf:_")) {
            return zEquals;
        }
        boolean z = true;
        for (int i = 5; i < str.length(); i++) {
            z = z && str.charAt(i) >= '0' && str.charAt(i) <= '9';
        }
        return z;
    }
}
