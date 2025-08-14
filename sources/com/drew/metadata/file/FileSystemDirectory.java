package com.drew.metadata.file;

import com.drew.metadata.Directory;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class FileSystemDirectory extends Directory {
    public static final int TAG_FILE_MODIFIED_DATE = 3;
    public static final int TAG_FILE_NAME = 1;
    public static final int TAG_FILE_SIZE = 2;
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.Directory
    public String getName() {
        return "File";
    }

    @Override // com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        map.put(1, "File Name");
        map.put(2, "File Size");
        map.put(3, "File Modified Date");
    }

    public FileSystemDirectory() {
        setDescriptor(new FileSystemDescriptor(this));
    }
}
