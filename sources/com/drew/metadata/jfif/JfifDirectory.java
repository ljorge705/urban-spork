package com.drew.metadata.jfif;

import com.clevertap.android.sdk.Constants;
import com.drew.metadata.Directory;
import com.drew.metadata.MetadataException;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class JfifDirectory extends Directory {
    public static final int TAG_RESX = 8;
    public static final int TAG_RESY = 10;
    public static final int TAG_THUMB_HEIGHT = 13;
    public static final int TAG_THUMB_WIDTH = 12;
    public static final int TAG_UNITS = 7;
    public static final int TAG_VERSION = 5;
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.Directory
    public String getName() {
        return JfifReader.PREAMBLE;
    }

    @Override // com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        map.put(5, Constants.CLTAP_APP_VERSION);
        map.put(7, "Resolution Units");
        map.put(10, "Y Resolution");
        map.put(8, "X Resolution");
        map.put(12, "Thumbnail Width Pixels");
        map.put(13, "Thumbnail Height Pixels");
    }

    public JfifDirectory() {
        setDescriptor(new JfifDescriptor(this));
    }

    public int getVersion() throws MetadataException {
        return getInt(5);
    }

    public int getResUnits() throws MetadataException {
        return getInt(7);
    }

    @Deprecated
    public int getImageWidth() throws MetadataException {
        return getInt(10);
    }

    public int getResY() throws MetadataException {
        return getInt(10);
    }

    @Deprecated
    public int getImageHeight() throws MetadataException {
        return getInt(8);
    }

    public int getResX() throws MetadataException {
        return getInt(8);
    }
}
