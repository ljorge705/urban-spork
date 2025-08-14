package com.drew.metadata.xmp;

import com.adobe.internal.xmp.XMPException;
import com.adobe.internal.xmp.XMPIterator;
import com.adobe.internal.xmp.XMPMeta;
import com.adobe.internal.xmp.impl.XMPMetaImpl;
import com.adobe.internal.xmp.options.IteratorOptions;
import com.adobe.internal.xmp.properties.XMPPropertyInfo;
import com.drew.metadata.Directory;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class XmpDirectory extends Directory {
    public static final int TAG_XMP_VALUE_COUNT = 65535;
    protected static final HashMap<Integer, String> _tagNameMap;
    private XMPMeta _xmpMeta;

    @Override // com.drew.metadata.Directory
    public String getName() {
        return "XMP";
    }

    @Override // com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        map.put(65535, "XMP Value Count");
    }

    public XmpDirectory() {
        setDescriptor(new XmpDescriptor(this));
    }

    public Map<String, String> getXmpProperties() {
        HashMap map = new HashMap();
        if (this._xmpMeta != null) {
            try {
                XMPIterator it = this._xmpMeta.iterator(new IteratorOptions().setJustLeafnodes(true));
                while (it.hasNext()) {
                    XMPPropertyInfo xMPPropertyInfo = (XMPPropertyInfo) it.next();
                    String path = xMPPropertyInfo.getPath();
                    String value = xMPPropertyInfo.getValue();
                    if (path != null && value != null) {
                        map.put(path, value);
                    }
                }
            } catch (XMPException unused) {
            }
        }
        return Collections.unmodifiableMap(map);
    }

    public void setXMPMeta(XMPMeta xMPMeta) {
        this._xmpMeta = xMPMeta;
        try {
            XMPIterator it = this._xmpMeta.iterator(new IteratorOptions().setJustLeafnodes(true));
            int i = 0;
            while (it.hasNext()) {
                if (((XMPPropertyInfo) it.next()).getPath() != null) {
                    i++;
                }
            }
            setInt(65535, i);
        } catch (XMPException unused) {
        }
    }

    public XMPMeta getXMPMeta() {
        if (this._xmpMeta == null) {
            this._xmpMeta = new XMPMetaImpl();
        }
        return this._xmpMeta;
    }
}
