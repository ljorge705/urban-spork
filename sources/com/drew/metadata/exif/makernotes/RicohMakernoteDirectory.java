package com.drew.metadata.exif.makernotes;

import com.clevertap.android.sdk.Constants;
import com.drew.metadata.Directory;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class RicohMakernoteDirectory extends Directory {
    public static final int TAG_MAKERNOTE_DATA_TYPE = 1;
    public static final int TAG_PRINT_IMAGE_MATCHING_INFO = 3584;
    public static final int TAG_RICOH_CAMERA_INFO_MAKERNOTE_SUB_IFD_POINTER = 8193;
    public static final int TAG_VERSION = 2;
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.Directory
    public String getName() {
        return "Ricoh Makernote";
    }

    @Override // com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        map.put(1, "Makernote Data Type");
        map.put(2, Constants.CLTAP_APP_VERSION);
        map.put(3584, "Print Image Matching (PIM) Info");
        map.put(8193, "Ricoh Camera Info Makernote Sub-IFD");
    }

    public RicohMakernoteDirectory() {
        setDescriptor(new RicohMakernoteDescriptor(this));
    }
}
