package com.drew.metadata.jfxx;

import com.drew.metadata.Directory;
import com.drew.metadata.MetadataException;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class JfxxDirectory extends Directory {
    public static final int TAG_EXTENSION_CODE = 5;
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.Directory
    public String getName() {
        return JfxxReader.PREAMBLE;
    }

    @Override // com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        map.put(5, "Extension Code");
    }

    public JfxxDirectory() {
        setDescriptor(new JfxxDescriptor(this));
    }

    public int getExtensionCode() throws MetadataException {
        return getInt(5);
    }
}
