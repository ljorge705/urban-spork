package com.drew.metadata.jpeg;

import com.drew.metadata.Directory;
import com.drew.metadata.MetadataException;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class JpegDirectory extends Directory {
    public static final int TAG_COMPONENT_DATA_1 = 6;
    public static final int TAG_COMPONENT_DATA_2 = 7;
    public static final int TAG_COMPONENT_DATA_3 = 8;
    public static final int TAG_COMPONENT_DATA_4 = 9;
    public static final int TAG_COMPRESSION_TYPE = -3;
    public static final int TAG_DATA_PRECISION = 0;
    public static final int TAG_IMAGE_HEIGHT = 1;
    public static final int TAG_IMAGE_WIDTH = 3;
    public static final int TAG_NUMBER_OF_COMPONENTS = 5;
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.Directory
    public String getName() {
        return "JPEG";
    }

    @Override // com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        map.put(-3, "Compression Type");
        map.put(0, "Data Precision");
        map.put(3, "Image Width");
        map.put(1, "Image Height");
        map.put(5, "Number of Components");
        map.put(6, "Component 1");
        map.put(7, "Component 2");
        map.put(8, "Component 3");
        map.put(9, "Component 4");
    }

    public JpegDirectory() {
        setDescriptor(new JpegDescriptor(this));
    }

    public JpegComponent getComponent(int i) {
        return (JpegComponent) getObject(i + 6);
    }

    public int getImageWidth() throws MetadataException {
        return getInt(3);
    }

    public int getImageHeight() throws MetadataException {
        return getInt(1);
    }

    public int getNumberOfComponents() throws MetadataException {
        return getInt(5);
    }
}
