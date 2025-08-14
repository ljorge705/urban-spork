package com.drew.metadata.eps;

import androidx.exifinterface.media.ExifInterface;
import com.clevertap.android.sdk.Constants;
import com.drew.metadata.Directory;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class EpsDirectory extends Directory {
    public static final int TAG_AUTHOR = 2;
    public static final int TAG_BOUNDING_BOX = 3;
    public static final int TAG_COLOR_TYPE = 30;
    public static final int TAG_CONTINUE_LINE = 36;
    public static final int TAG_COPYRIGHT = 4;
    public static final int TAG_CREATION_DATE = 5;
    public static final int TAG_CREATOR = 6;
    public static final int TAG_DOCUMENT_DATA = 16;
    public static final int TAG_DSC_VERSION = 1;
    public static final int TAG_EMULATION = 17;
    public static final int TAG_EXTENSIONS = 18;
    public static final int TAG_FOR = 7;
    public static final int TAG_IMAGE_DATA = 8;
    public static final int TAG_IMAGE_HEIGHT = 29;
    public static final int TAG_IMAGE_WIDTH = 28;
    public static final int TAG_KEYWORDS = 9;
    public static final int TAG_LANGUAGE_LEVEL = 19;
    public static final int TAG_MODIFY_DATE = 10;
    public static final int TAG_OPERATOR_INTERNVENTION = 22;
    public static final int TAG_OPERATOR_MESSAGE = 23;
    public static final int TAG_ORIENTATION = 20;
    public static final int TAG_PAGES = 11;
    public static final int TAG_PAGE_ORDER = 21;
    public static final int TAG_PROOF_MODE = 24;
    public static final int TAG_RAM_SIZE = 31;
    public static final int TAG_REQUIREMENTS = 25;
    public static final int TAG_ROUTING = 12;
    public static final int TAG_SUBJECT = 13;
    public static final int TAG_TIFF_PREVIEW_OFFSET = 33;
    public static final int TAG_TIFF_PREVIEW_SIZE = 32;
    public static final int TAG_TITLE = 14;
    public static final int TAG_VERSION = 15;
    public static final int TAG_VM_LOCATION = 26;
    public static final int TAG_VM_USAGE = 27;
    public static final int TAG_WMF_PREVIEW_OFFSET = 35;
    public static final int TAG_WMF_PREVIEW_SIZE = 34;
    protected static final HashMap<String, Integer> _tagIntegerMap;
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.Directory
    public String getName() {
        return "EPS";
    }

    @Override // com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        HashMap<String, Integer> map2 = new HashMap<>();
        _tagIntegerMap = map2;
        map2.put("%!PS-Adobe-", 1);
        map2.put("%%Author", 2);
        map2.put("%%BoundingBox", 3);
        map2.put("%%Copyright", 4);
        map2.put("%%CreationDate", 5);
        map2.put("%%Creator", 6);
        map2.put("%%For", 7);
        map2.put("%ImageData", 8);
        map2.put("%%Keywords", 9);
        map2.put("%%ModDate", 10);
        map2.put("%%Pages", 11);
        map2.put("%%Routing", 12);
        map2.put("%%Subject", 13);
        map2.put("%%Title", 14);
        map2.put("%%Version", 15);
        map2.put("%%DocumentData", 16);
        map2.put("%%Emulation", 17);
        map2.put("%%Extensions", 18);
        map2.put("%%LanguageLevel", 19);
        map2.put("%%Orientation", 20);
        map2.put("%%PageOrder", 21);
        map2.put("%%OperatorIntervention", 22);
        map2.put("%%OperatorMessage", 23);
        map2.put("%%ProofMode", 24);
        map2.put("%%Requirements", 25);
        map2.put("%%VMlocation", 26);
        map2.put("%%VMusage", 27);
        map2.put("Image Width", 28);
        map2.put("Image Height", 29);
        map2.put("Color Type", 30);
        map2.put("Ram Size", 31);
        map2.put("TIFFPreview", 32);
        map2.put("TIFFPreviewOffset", 33);
        map2.put("WMFPreview", 34);
        map2.put("WMFPreviewOffset", 35);
        map2.put("%%+", 36);
        map.put(36, "Line Continuation");
        map.put(3, "Bounding Box");
        map.put(4, ExifInterface.TAG_COPYRIGHT);
        map.put(16, "Document Data");
        map.put(17, "Emulation");
        map.put(18, "Extensions");
        map.put(19, "Language Level");
        map.put(20, ExifInterface.TAG_ORIENTATION);
        map.put(21, "Page Order");
        map.put(15, Constants.CLTAP_APP_VERSION);
        map.put(8, "Image Data");
        map.put(28, "Image Width");
        map.put(29, "Image Height");
        map.put(30, "Color Type");
        map.put(31, "Ram Size");
        map.put(6, "Creator");
        map.put(5, "Creation Date");
        map.put(7, "For");
        map.put(25, "Requirements");
        map.put(12, "Routing");
        map.put(14, "Title");
        map.put(1, "DSC Version");
        map.put(11, "Pages");
        map.put(22, "Operator Intervention");
        map.put(23, "Operator Message");
        map.put(24, "Proof Mode");
        map.put(26, "VM Location");
        map.put(27, "VM Usage");
        map.put(2, "Author");
        map.put(9, "Keywords");
        map.put(10, "Modify Date");
        map.put(13, "Subject");
        map.put(32, "TIFF Preview Size");
        map.put(33, "TIFF Preview Offset");
        map.put(34, "WMF Preview Size");
        map.put(35, "WMF Preview Offset");
    }

    public EpsDirectory() {
        setDescriptor(new EpsDescriptor(this));
    }
}
