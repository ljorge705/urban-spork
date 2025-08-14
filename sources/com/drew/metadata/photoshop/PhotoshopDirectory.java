package com.drew.metadata.photoshop;

import com.drew.metadata.Directory;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class PhotoshopDirectory extends Directory {
    public static final int TAG_ALPHA_CHANNELS = 1006;
    public static final int TAG_ALPHA_IDENTIFIERS = 1053;
    public static final int TAG_ALTERNATE_DUOTONE_COLORS = 1066;
    public static final int TAG_ALTERNATE_SPOT_COLORS = 1067;
    public static final int TAG_AUTO_SAVE_FILE_PATH = 1086;
    public static final int TAG_AUTO_SAVE_FORMAT = 1087;
    public static final int TAG_BACKGROUND_COLOR = 1010;
    public static final int TAG_BORDER_INFORMATION = 1009;
    public static final int TAG_CAPTION = 1008;
    public static final int TAG_CAPTION_DIGEST = 1061;
    public static final int TAG_CHANNELS_ROWS_COLUMNS_DEPTH_MODE = 1000;
    public static final int TAG_CLIPPING_PATH_NAME = 2999;
    public static final int TAG_COLOR_HALFTONING_INFORMATION = 1013;
    public static final int TAG_COLOR_SAMPLERS = 1073;
    public static final int TAG_COLOR_TRANSFER_FUNCTIONS = 1016;
    public static final int TAG_COPYRIGHT = 1034;
    public static final int TAG_COUNT_INFORMATION = 1080;
    public static final int TAG_DISPLAY_INFO = 1077;
    public static final int TAG_DISPLAY_INFO_OBSOLETE = 1007;
    public static final int TAG_DUOTONE_HALFTONING_INFORMATION = 1014;
    public static final int TAG_DUOTONE_IMAGE_INFORMATION = 1018;
    public static final int TAG_DUOTONE_TRANSFER_FUNCTIONS = 1017;
    public static final int TAG_EFFECTIVE_BLACK_AND_WHITE_VALUES = 1019;
    public static final int TAG_EFFECTS_VISIBLE = 1042;
    public static final int TAG_EPS_OPTIONS = 1021;
    public static final int TAG_EXIF_DATA_1 = 1058;
    public static final int TAG_EXIF_DATA_3 = 1059;
    public static final int TAG_GLOBAL_ALTITUDE = 1049;
    public static final int TAG_GLOBAL_ANGLE = 1037;
    public static final int TAG_GRAYSCALE_AND_MULTICHANNEL_HALFTONING_INFORMATION = 1012;
    public static final int TAG_GRAYSCALE_AND_MULTICHANNEL_TRANSFER_FUNCTION = 1015;
    public static final int TAG_GRID_AND_GUIDES_INFORMATION = 1032;
    public static final int TAG_HDR_TONING_INFO = 1070;
    public static final int TAG_ICC_PROFILE_BYTES = 1039;
    public static final int TAG_ICC_UNTAGGED_PROFILE = 1041;
    public static final int TAG_IMAGE_MODE_FOR_RAW_FORMAT_FILES = 1029;
    public static final int TAG_IMAGE_READY_7_ROLLOVER = 7003;
    public static final int TAG_IMAGE_READY_DATA_SETS = 7001;
    public static final int TAG_IMAGE_READY_ROLLOVER = 7004;
    public static final int TAG_IMAGE_READY_SAVE_LAYER_SETTINGS = 7005;
    public static final int TAG_IMAGE_READY_SELECTED_STATE = 7002;
    public static final int TAG_IMAGE_READY_VARIABLES_XML = 7000;
    public static final int TAG_IMAGE_READY_VERSION = 7006;
    public static final int TAG_INDEXED_COLOR_TABLE = 1003;
    public static final int TAG_INDEXED_COLOR_TABLE_COUNT = 1046;
    public static final int TAG_IPTC = 1028;
    public static final int TAG_JPEG_QUALITY = 1030;
    public static final int TAG_JUMP_TO_XPEP = 1052;
    public static final int TAG_LAYERS_GROUP_INFORMATION = 1026;
    public static final int TAG_LAYER_COMPS = 1065;
    public static final int TAG_LAYER_GROUPS_ENABLED_ID = 1072;
    public static final int TAG_LAYER_SELECTION_IDS = 1069;
    public static final int TAG_LAYER_STATE_INFORMATION = 1024;
    public static final int TAG_LIGHTROOM_WORKFLOW = 8000;
    public static final int TAG_MAC_NSPRINTINFO = 1084;
    public static final int TAG_MAC_PRINT_INFO = 1001;
    public static final int TAG_MEASUREMENT_SCALE = 1074;
    public static final int TAG_ONION_SKINS = 1078;
    public static final int TAG_ORIGIN_PATH_INFO = 3000;
    public static final int TAG_PATH_SELECTION_STATE = 1088;
    public static final int TAG_PIXEL_ASPECT_RATIO = 1064;
    public static final int TAG_PRINT_FLAGS = 1011;
    public static final int TAG_PRINT_FLAGS_INFO = 10000;
    public static final int TAG_PRINT_INFO = 1071;
    public static final int TAG_PRINT_INFO_2 = 1082;
    public static final int TAG_PRINT_SCALE = 1062;
    public static final int TAG_PRINT_STYLE = 1083;
    public static final int TAG_QUICK_MASK_INFORMATION = 1022;
    public static final int TAG_RESOLUTION_INFO = 1005;
    public static final int TAG_SEED_NUMBER = 1044;
    public static final int TAG_SHEET_DISCLOSURE = 1076;
    public static final int TAG_SLICES = 1050;
    public static final int TAG_SPOT_HALFTONE = 1043;
    public static final int TAG_THUMBNAIL = 1036;
    public static final int TAG_THUMBNAIL_OLD = 1033;
    public static final int TAG_TIMELINE_INFORMATION = 1075;
    public static final int TAG_TRANSPARENCY_INDEX = 1047;
    public static final int TAG_UNICODE_ALPHA_NAMES = 1045;
    public static final int TAG_URL = 1035;
    public static final int TAG_URL_LIST = 1054;
    public static final int TAG_VERSION = 1057;
    public static final int TAG_WATERMARK = 1040;
    public static final int TAG_WIN_DEVMODE = 1085;
    public static final int TAG_WORKFLOW_URL = 1051;
    public static final int TAG_XML = 1002;
    public static final int TAG_XMP_DATA = 1060;
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.Directory
    public String getName() {
        return "Photoshop";
    }

    @Override // com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        map.put(1000, "Channels, Rows, Columns, Depth, Mode");
        map.put(1001, "Mac Print Info");
        map.put(1002, "XML Data");
        map.put(1003, "Indexed Color Table");
        map.put(1005, "Resolution Info");
        map.put(1006, "Alpha Channels");
        map.put(1007, "Display Info (Obsolete)");
        map.put(1008, "Caption");
        map.put(1009, "Border Information");
        map.put(1010, "Background Color");
        map.put(1011, "Print Flags");
        map.put(1012, "Grayscale and Multichannel Halftoning Information");
        map.put(1013, "Color Halftoning Information");
        map.put(1014, "Duotone Halftoning Information");
        map.put(1015, "Grayscale and Multichannel Transfer Function");
        map.put(1016, "Color Transfer Functions");
        map.put(1017, "Duotone Transfer Functions");
        map.put(1018, "Duotone Image Information");
        map.put(1019, "Effective Black and White Values");
        map.put(1021, "EPS Options");
        map.put(Integer.valueOf(TAG_QUICK_MASK_INFORMATION), "Quick Mask Information");
        map.put(1024, "Layer State Information");
        map.put(Integer.valueOf(TAG_LAYERS_GROUP_INFORMATION), "Layers Group Information");
        map.put(1028, "IPTC-NAA Record");
        map.put(1029, "Image Mode for Raw Format Files");
        map.put(1030, "JPEG Quality");
        map.put(1032, "Grid and Guides Information");
        map.put(Integer.valueOf(TAG_THUMBNAIL_OLD), "Photoshop 4.0 Thumbnail");
        map.put(Integer.valueOf(TAG_COPYRIGHT), "Copyright Flag");
        map.put(Integer.valueOf(TAG_URL), "URL");
        map.put(Integer.valueOf(TAG_THUMBNAIL), "Thumbnail Data");
        map.put(1037, "Global Angle");
        map.put(Integer.valueOf(TAG_ICC_PROFILE_BYTES), "ICC Profile Bytes");
        map.put(1040, "Watermark");
        map.put(Integer.valueOf(TAG_ICC_UNTAGGED_PROFILE), "ICC Untagged Profile");
        map.put(1042, "Effects Visible");
        map.put(1043, "Spot Halftone");
        map.put(Integer.valueOf(TAG_SEED_NUMBER), "Seed Number");
        map.put(Integer.valueOf(TAG_UNICODE_ALPHA_NAMES), "Unicode Alpha Names");
        map.put(Integer.valueOf(TAG_INDEXED_COLOR_TABLE_COUNT), "Indexed Color Table Count");
        map.put(Integer.valueOf(TAG_TRANSPARENCY_INDEX), "Transparency Index");
        map.put(1049, "Global Altitude");
        map.put(1050, "Slices");
        map.put(1051, "Workflow URL");
        map.put(1052, "Jump To XPEP");
        map.put(1053, "Alpha Identifiers");
        map.put(Integer.valueOf(TAG_URL_LIST), "URL List");
        map.put(Integer.valueOf(TAG_VERSION), "Version Info");
        map.put(Integer.valueOf(TAG_EXIF_DATA_1), "EXIF Data 1");
        map.put(Integer.valueOf(TAG_EXIF_DATA_3), "EXIF Data 3");
        map.put(Integer.valueOf(TAG_XMP_DATA), "XMP Data");
        map.put(Integer.valueOf(TAG_CAPTION_DIGEST), "Caption Digest");
        map.put(Integer.valueOf(TAG_PRINT_SCALE), "Print Scale");
        map.put(Integer.valueOf(TAG_PIXEL_ASPECT_RATIO), "Pixel Aspect Ratio");
        map.put(Integer.valueOf(TAG_LAYER_COMPS), "Layer Comps");
        map.put(Integer.valueOf(TAG_ALTERNATE_DUOTONE_COLORS), "Alternate Duotone Colors");
        map.put(Integer.valueOf(TAG_ALTERNATE_SPOT_COLORS), "Alternate Spot Colors");
        map.put(Integer.valueOf(TAG_LAYER_SELECTION_IDS), "Layer Selection IDs");
        map.put(Integer.valueOf(TAG_HDR_TONING_INFO), "HDR Toning Info");
        map.put(Integer.valueOf(TAG_PRINT_INFO), "Print Info");
        map.put(Integer.valueOf(TAG_LAYER_GROUPS_ENABLED_ID), "Layer Groups Enabled ID");
        map.put(Integer.valueOf(TAG_COLOR_SAMPLERS), "Color Samplers");
        map.put(Integer.valueOf(TAG_MEASUREMENT_SCALE), "Measurement Scale");
        map.put(Integer.valueOf(TAG_TIMELINE_INFORMATION), "Timeline Information");
        map.put(Integer.valueOf(TAG_SHEET_DISCLOSURE), "Sheet Disclosure");
        map.put(Integer.valueOf(TAG_DISPLAY_INFO), "Display Info");
        map.put(Integer.valueOf(TAG_ONION_SKINS), "Onion Skins");
        map.put(Integer.valueOf(TAG_COUNT_INFORMATION), "Count information");
        map.put(Integer.valueOf(TAG_PRINT_INFO_2), "Print Info 2");
        map.put(Integer.valueOf(TAG_PRINT_STYLE), "Print Style");
        map.put(Integer.valueOf(TAG_MAC_NSPRINTINFO), "Mac NSPrintInfo");
        map.put(Integer.valueOf(TAG_WIN_DEVMODE), "Win DEVMODE");
        map.put(Integer.valueOf(TAG_AUTO_SAVE_FILE_PATH), "Auto Save File Subpath");
        map.put(Integer.valueOf(TAG_AUTO_SAVE_FORMAT), "Auto Save Format");
        map.put(Integer.valueOf(TAG_PATH_SELECTION_STATE), "Subpath Selection State");
        map.put(Integer.valueOf(TAG_CLIPPING_PATH_NAME), "Clipping Path Name");
        map.put(3000, "Origin Subpath Info");
        map.put(Integer.valueOf(TAG_IMAGE_READY_VARIABLES_XML), "Image Ready Variables XML");
        map.put(Integer.valueOf(TAG_IMAGE_READY_DATA_SETS), "Image Ready Data Sets");
        map.put(Integer.valueOf(TAG_IMAGE_READY_SELECTED_STATE), "Image Ready Selected State");
        map.put(Integer.valueOf(TAG_IMAGE_READY_7_ROLLOVER), "Image Ready 7 Rollover Expanded State");
        map.put(Integer.valueOf(TAG_IMAGE_READY_ROLLOVER), "Image Ready Rollover Expanded State");
        map.put(Integer.valueOf(TAG_IMAGE_READY_SAVE_LAYER_SETTINGS), "Image Ready Save Layer Settings");
        map.put(Integer.valueOf(TAG_IMAGE_READY_VERSION), "Image Ready Version");
        map.put(Integer.valueOf(TAG_LIGHTROOM_WORKFLOW), "Lightroom Workflow");
        map.put(10000, "Print Flags Information");
    }

    public PhotoshopDirectory() {
        setDescriptor(new PhotoshopDescriptor(this));
    }

    public byte[] getThumbnailBytes() {
        byte[] byteArray = getByteArray(TAG_THUMBNAIL);
        if (byteArray == null) {
            byteArray = getByteArray(TAG_THUMBNAIL_OLD);
        }
        if (byteArray == null || byteArray.length <= 28) {
            return null;
        }
        int length = byteArray.length - 28;
        byte[] bArr = new byte[length];
        System.arraycopy(byteArray, 28, bArr, 0, length);
        return bArr;
    }
}
