package com.drew.metadata.exif;

import com.drew.metadata.Directory;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class PrintIMDirectory extends Directory {
    public static final int TagPrintImVersion = 0;
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.Directory
    public String getName() {
        return "PrintIM";
    }

    @Override // com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        map.put(0, "PrintIM Version");
    }

    public PrintIMDirectory() {
        setDescriptor(new PrintIMDescriptor(this));
    }
}
