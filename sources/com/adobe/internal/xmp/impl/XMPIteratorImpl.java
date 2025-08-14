package com.adobe.internal.xmp.impl;

import com.adobe.internal.xmp.XMPException;
import com.adobe.internal.xmp.XMPIterator;
import com.adobe.internal.xmp.XMPMetaFactory;
import com.adobe.internal.xmp.impl.xpath.XMPPath;
import com.adobe.internal.xmp.impl.xpath.XMPPathParser;
import com.adobe.internal.xmp.options.IteratorOptions;
import com.adobe.internal.xmp.options.PropertyOptions;
import com.adobe.internal.xmp.properties.XMPPropertyInfo;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* loaded from: classes5.dex */
public class XMPIteratorImpl implements XMPIterator {
    private String baseNS;
    private Iterator nodeIterator;
    private IteratorOptions options;
    protected boolean skipSiblings = false;
    protected boolean skipSubtree = false;

    protected String getBaseNS() {
        return this.baseNS;
    }

    protected IteratorOptions getOptions() {
        return this.options;
    }

    protected void setBaseNS(String str) {
        this.baseNS = str;
    }

    @Override // com.adobe.internal.xmp.XMPIterator
    public void skipSubtree() {
        this.skipSubtree = true;
    }

    public XMPIteratorImpl(XMPMetaImpl xMPMetaImpl, String str, String str2, IteratorOptions iteratorOptions) throws XMPException {
        XMPNode xMPNodeFindSchemaNode;
        String string = null;
        this.baseNS = null;
        this.nodeIterator = null;
        this.options = iteratorOptions == null ? new IteratorOptions() : iteratorOptions;
        boolean z = str != null && str.length() > 0;
        boolean z2 = str2 != null && str2.length() > 0;
        if (!z && !z2) {
            xMPNodeFindSchemaNode = xMPMetaImpl.getRoot();
        } else if (z && z2) {
            XMPPath xMPPathExpandXPath = XMPPathParser.expandXPath(str, str2);
            XMPPath xMPPath = new XMPPath();
            for (int i = 0; i < xMPPathExpandXPath.size() - 1; i++) {
                xMPPath.add(xMPPathExpandXPath.getSegment(i));
            }
            xMPNodeFindSchemaNode = XMPNodeUtils.findNode(xMPMetaImpl.getRoot(), xMPPathExpandXPath, false, null);
            this.baseNS = str;
            string = xMPPath.toString();
        } else if (z && !z2) {
            xMPNodeFindSchemaNode = XMPNodeUtils.findSchemaNode(xMPMetaImpl.getRoot(), str, false);
        } else {
            throw new XMPException("Schema namespace URI is required", 101);
        }
        if (xMPNodeFindSchemaNode != null) {
            if (!this.options.isJustChildren()) {
                this.nodeIterator = new NodeIterator(xMPNodeFindSchemaNode, string, 1);
                return;
            } else {
                this.nodeIterator = new NodeIteratorChildren(xMPNodeFindSchemaNode, string);
                return;
            }
        }
        this.nodeIterator = Collections.EMPTY_LIST.iterator();
    }

    @Override // com.adobe.internal.xmp.XMPIterator
    public void skipSiblings() {
        skipSubtree();
        this.skipSiblings = true;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.nodeIterator.hasNext();
    }

    @Override // java.util.Iterator
    public Object next() {
        return this.nodeIterator.next();
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("The XMPIterator does not support remove().");
    }

    private class NodeIterator implements Iterator {
        protected static final int ITERATE_CHILDREN = 1;
        protected static final int ITERATE_NODE = 0;
        protected static final int ITERATE_QUALIFIER = 2;
        private Iterator childrenIterator;
        private int index;
        private String path;
        private XMPPropertyInfo returnProperty;
        private int state;
        private Iterator subIterator;
        private XMPNode visitedNode;

        protected Iterator getChildrenIterator() {
            return this.childrenIterator;
        }

        protected XMPPropertyInfo getReturnProperty() {
            return this.returnProperty;
        }

        protected void setChildrenIterator(Iterator it) {
            this.childrenIterator = it;
        }

        protected void setReturnProperty(XMPPropertyInfo xMPPropertyInfo) {
            this.returnProperty = xMPPropertyInfo;
        }

        public NodeIterator() {
            this.state = 0;
            this.childrenIterator = null;
            this.index = 0;
            this.subIterator = Collections.EMPTY_LIST.iterator();
            this.returnProperty = null;
        }

        public NodeIterator(XMPNode xMPNode, String str, int i) {
            this.state = 0;
            this.childrenIterator = null;
            this.index = 0;
            this.subIterator = Collections.EMPTY_LIST.iterator();
            this.returnProperty = null;
            this.visitedNode = xMPNode;
            this.state = 0;
            if (xMPNode.getOptions().isSchemaNode()) {
                XMPIteratorImpl.this.setBaseNS(xMPNode.getName());
            }
            this.path = accumulatePath(xMPNode, str, i);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.returnProperty != null) {
                return true;
            }
            int i = this.state;
            if (i == 0) {
                return reportNode();
            }
            if (i == 1) {
                if (this.childrenIterator == null) {
                    this.childrenIterator = this.visitedNode.iterateChildren();
                }
                boolean zIterateChildren = iterateChildren(this.childrenIterator);
                if (zIterateChildren || !this.visitedNode.hasQualifier() || XMPIteratorImpl.this.getOptions().isOmitQualifiers()) {
                    return zIterateChildren;
                }
                this.state = 2;
                this.childrenIterator = null;
                return hasNext();
            }
            if (this.childrenIterator == null) {
                this.childrenIterator = this.visitedNode.iterateQualifier();
            }
            return iterateChildren(this.childrenIterator);
        }

        protected boolean reportNode() {
            this.state = 1;
            if (this.visitedNode.getParent() != null && (!XMPIteratorImpl.this.getOptions().isJustLeafnodes() || !this.visitedNode.hasChildren())) {
                this.returnProperty = createPropertyInfo(this.visitedNode, XMPIteratorImpl.this.getBaseNS(), this.path);
                return true;
            }
            return hasNext();
        }

        private boolean iterateChildren(Iterator it) {
            if (XMPIteratorImpl.this.skipSiblings) {
                XMPIteratorImpl.this.skipSiblings = false;
                this.subIterator = Collections.EMPTY_LIST.iterator();
            }
            if (!this.subIterator.hasNext() && it.hasNext()) {
                XMPNode xMPNode = (XMPNode) it.next();
                int i = this.index + 1;
                this.index = i;
                this.subIterator = XMPIteratorImpl.this.new NodeIterator(xMPNode, this.path, i);
            }
            if (!this.subIterator.hasNext()) {
                return false;
            }
            this.returnProperty = (XMPPropertyInfo) this.subIterator.next();
            return true;
        }

        @Override // java.util.Iterator
        public Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException("There are no more nodes to return");
            }
            XMPPropertyInfo xMPPropertyInfo = this.returnProperty;
            this.returnProperty = null;
            return xMPPropertyInfo;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }

        protected String accumulatePath(XMPNode xMPNode, String str, int i) {
            String name;
            String str2;
            if (xMPNode.getParent() == null || xMPNode.getOptions().isSchemaNode()) {
                return null;
            }
            if (xMPNode.getParent().getOptions().isArray()) {
                name = "[" + String.valueOf(i) + "]";
                str2 = "";
            } else {
                name = xMPNode.getName();
                str2 = "/";
            }
            if (str == null || str.length() == 0) {
                return name;
            }
            if (XMPIteratorImpl.this.getOptions().isJustLeafname()) {
                return !name.startsWith("?") ? name : name.substring(1);
            }
            return str + str2 + name;
        }

        protected XMPPropertyInfo createPropertyInfo(final XMPNode xMPNode, final String str, final String str2) {
            final String value = xMPNode.getOptions().isSchemaNode() ? null : xMPNode.getValue();
            return new XMPPropertyInfo() { // from class: com.adobe.internal.xmp.impl.XMPIteratorImpl.NodeIterator.1
                @Override // com.adobe.internal.xmp.properties.XMPProperty
                public String getLanguage() {
                    return null;
                }

                @Override // com.adobe.internal.xmp.properties.XMPPropertyInfo
                public String getPath() {
                    return str2;
                }

                @Override // com.adobe.internal.xmp.properties.XMPPropertyInfo, com.adobe.internal.xmp.properties.XMPProperty
                public String getValue() {
                    return value;
                }

                @Override // com.adobe.internal.xmp.properties.XMPPropertyInfo
                public String getNamespace() {
                    if (xMPNode.getOptions().isSchemaNode()) {
                        return str;
                    }
                    return XMPMetaFactory.getSchemaRegistry().getNamespaceURI(new QName(xMPNode.getName()).getPrefix());
                }

                @Override // com.adobe.internal.xmp.properties.XMPPropertyInfo, com.adobe.internal.xmp.properties.XMPProperty
                public PropertyOptions getOptions() {
                    return xMPNode.getOptions();
                }
            };
        }
    }

    private class NodeIteratorChildren extends NodeIterator {
        private Iterator childrenIterator;
        private int index;
        private String parentPath;

        public NodeIteratorChildren(XMPNode xMPNode, String str) {
            super();
            this.index = 0;
            if (xMPNode.getOptions().isSchemaNode()) {
                XMPIteratorImpl.this.setBaseNS(xMPNode.getName());
            }
            this.parentPath = accumulatePath(xMPNode, str, 1);
            this.childrenIterator = xMPNode.iterateChildren();
        }

        @Override // com.adobe.internal.xmp.impl.XMPIteratorImpl.NodeIterator, java.util.Iterator
        public boolean hasNext() {
            String strAccumulatePath;
            if (getReturnProperty() != null) {
                return true;
            }
            if (XMPIteratorImpl.this.skipSiblings || !this.childrenIterator.hasNext()) {
                return false;
            }
            XMPNode xMPNode = (XMPNode) this.childrenIterator.next();
            this.index++;
            if (xMPNode.getOptions().isSchemaNode()) {
                XMPIteratorImpl.this.setBaseNS(xMPNode.getName());
            } else {
                strAccumulatePath = xMPNode.getParent() != null ? accumulatePath(xMPNode, this.parentPath, this.index) : null;
                if (XMPIteratorImpl.this.getOptions().isJustLeafnodes() || !xMPNode.hasChildren()) {
                    setReturnProperty(createPropertyInfo(xMPNode, XMPIteratorImpl.this.getBaseNS(), strAccumulatePath));
                    return true;
                }
                return hasNext();
            }
            if (XMPIteratorImpl.this.getOptions().isJustLeafnodes()) {
            }
            setReturnProperty(createPropertyInfo(xMPNode, XMPIteratorImpl.this.getBaseNS(), strAccumulatePath));
            return true;
        }
    }
}
