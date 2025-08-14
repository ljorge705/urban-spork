package com.drew.metadata.exif.makernotes;

import androidx.exifinterface.media.ExifInterface;
import com.drew.metadata.Directory;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class SonyType1MakernoteDirectory extends Directory {
    public static final int TAG_AF_ILLUMINATOR = 45124;
    public static final int TAG_AF_MODE = 45123;
    public static final int TAG_AF_POINT_SELECTED = 8222;
    public static final int TAG_ANTI_BLUR = 45131;
    public static final int TAG_AUTO_PORTRAIT_FRAMED = 8214;
    public static final int TAG_BRIGHTNESS = 8199;
    public static final int TAG_CAMERA_INFO = 16;
    public static final int TAG_CAMERA_SETTINGS = 276;
    public static final int TAG_COLOR_COMPENSATION_FILTER = 45090;
    public static final int TAG_COLOR_MODE = 45097;
    public static final int TAG_COLOR_MODE_SETTING = 45088;
    public static final int TAG_COLOR_TEMPERATURE = 45089;
    public static final int TAG_CONTRAST = 8196;
    public static final int TAG_DISTORTION_CORRECTION = 8211;
    public static final int TAG_DYNAMIC_RANGE_OPTIMISER = 45093;
    public static final int TAG_DYNAMIC_RANGE_OPTIMIZER = 45135;
    public static final int TAG_EXPOSURE_MODE = 45121;
    public static final int TAG_EXTRA_INFO = 278;
    public static final int TAG_FILE_FORMAT = 45056;
    public static final int TAG_FLASH_EXPOSURE_COMP = 260;
    public static final int TAG_FLASH_LEVEL = 45128;
    public static final int TAG_FOCUS_INFO = 32;
    public static final int TAG_FOCUS_MODE = 8219;
    public static final int TAG_FOCUS_MODE_2 = 45122;
    public static final int TAG_FULL_IMAGE_SIZE = 45099;
    public static final int TAG_HDR = 8202;
    public static final int TAG_HIGH_ISO_NOISE_REDUCTION = 8201;
    public static final int TAG_HIGH_ISO_NOISE_REDUCTION_2 = 45136;
    public static final int TAG_IMAGE_QUALITY = 258;
    public static final int TAG_IMAGE_STABILISATION = 45094;
    public static final int TAG_INTELLIGENT_AUTO = 45138;
    public static final int TAG_JPEG_QUALITY = 45127;
    public static final int TAG_LATERAL_CHROMATIC_ABERRATION = 8210;
    public static final int TAG_LENS_ID = 45095;
    public static final int TAG_LENS_SPEC = 45098;
    public static final int TAG_LONG_EXPOSURE_NOISE_REDUCTION = 8200;
    public static final int TAG_LONG_EXPOSURE_NOISE_REDUCTION_OR_FOCUS_MODE = 45134;
    public static final int TAG_MACRO = 45120;
    public static final int TAG_MINOLTA_MAKERNOTE = 45096;
    public static final int TAG_MULTI_BURST_IMAGE_HEIGHT = 4098;
    public static final int TAG_MULTI_BURST_IMAGE_WIDTH = 4097;
    public static final int TAG_MULTI_BURST_MODE = 4096;
    public static final int TAG_MULTI_FRAME_NOISE_REDUCTION = 8203;
    public static final int TAG_NO_PRINT = 65535;
    public static final int TAG_PANORAMA = 4099;
    public static final int TAG_PICTURE_EFFECT = 8206;
    public static final int TAG_PREVIEW_IMAGE = 8193;
    public static final int TAG_PREVIEW_IMAGE_SIZE = 45100;
    public static final int TAG_PRINT_IMAGE_MATCHING_INFO = 3584;
    public static final int TAG_RATING = 8194;
    public static final int TAG_RELEASE_MODE = 45129;
    public static final int TAG_SATURATION = 8197;
    public static final int TAG_SCENE_MODE = 45091;
    public static final int TAG_SEQUENCE_NUMBER = 45130;
    public static final int TAG_SHARPNESS = 8198;
    public static final int TAG_SHOT_INFO = 12288;
    public static final int TAG_SOFT_SKIN_EFFECT = 8207;
    public static final int TAG_SONY_MODEL_ID = 45057;
    public static final int TAG_TELECONVERTER = 261;
    public static final int TAG_VIGNETTING_CORRECTION = 8209;
    public static final int TAG_WB_SHIFT_AMBER_MAGENTA = 8212;
    public static final int TAG_WHITE_BALANCE = 277;
    public static final int TAG_WHITE_BALANCE_2 = 45140;
    public static final int TAG_WHITE_BALANCE_FINE_TUNE = 274;
    public static final int TAG_ZONE_MATCHING = 45092;
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.Directory
    public String getName() {
        return "Sony Makernote";
    }

    @Override // com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        map.put(16, "Camera Info");
        map.put(32, "Focus Info");
        map.put(258, "Image Quality");
        map.put(260, "Flash Exposure Compensation");
        map.put(261, "Teleconverter Model");
        map.put(274, "White Balance Fine Tune Value");
        map.put(276, "Camera Settings");
        map.put(277, "White Balance");
        map.put(278, "Extra Info");
        map.put(3584, "Print Image Matching (PIM) Info");
        map.put(4096, "Multi Burst Mode");
        map.put(4097, "Multi Burst Image Width");
        map.put(4098, "Multi Burst Image Height");
        map.put(4099, "Panorama");
        map.put(8193, "Preview Image");
        map.put(8194, "Rating");
        map.put(Integer.valueOf(TAG_CONTRAST), ExifInterface.TAG_CONTRAST);
        map.put(8197, ExifInterface.TAG_SATURATION);
        map.put(Integer.valueOf(TAG_SHARPNESS), ExifInterface.TAG_SHARPNESS);
        map.put(Integer.valueOf(TAG_BRIGHTNESS), "Brightness");
        map.put(Integer.valueOf(TAG_LONG_EXPOSURE_NOISE_REDUCTION), "Long Exposure Noise Reduction");
        map.put(Integer.valueOf(TAG_HIGH_ISO_NOISE_REDUCTION), "High ISO Noise Reduction");
        map.put(Integer.valueOf(TAG_HDR), "HDR");
        map.put(Integer.valueOf(TAG_MULTI_FRAME_NOISE_REDUCTION), "Multi Frame Noise Reduction");
        map.put(Integer.valueOf(TAG_PICTURE_EFFECT), "Picture Effect");
        map.put(Integer.valueOf(TAG_SOFT_SKIN_EFFECT), "Soft Skin Effect");
        map.put(8209, "Vignetting Correction");
        map.put(8210, "Lateral Chromatic Aberration");
        map.put(Integer.valueOf(TAG_DISTORTION_CORRECTION), "Distortion Correction");
        map.put(Integer.valueOf(TAG_WB_SHIFT_AMBER_MAGENTA), "WB Shift Amber/Magenta");
        map.put(Integer.valueOf(TAG_AUTO_PORTRAIT_FRAMED), "Auto Portrait Framing");
        map.put(Integer.valueOf(TAG_FOCUS_MODE), "Focus Mode");
        map.put(Integer.valueOf(TAG_AF_POINT_SELECTED), "AF Point Selected");
        map.put(12288, "Shot Info");
        map.put(Integer.valueOf(TAG_FILE_FORMAT), "File Format");
        map.put(Integer.valueOf(TAG_SONY_MODEL_ID), "Sony Model ID");
        map.put(Integer.valueOf(TAG_COLOR_MODE_SETTING), "Color Mode Setting");
        map.put(Integer.valueOf(TAG_COLOR_TEMPERATURE), "Color Temperature");
        map.put(Integer.valueOf(TAG_COLOR_COMPENSATION_FILTER), "Color Compensation Filter");
        map.put(Integer.valueOf(TAG_SCENE_MODE), "Scene Mode");
        map.put(Integer.valueOf(TAG_ZONE_MATCHING), "Zone Matching");
        map.put(Integer.valueOf(TAG_DYNAMIC_RANGE_OPTIMISER), "Dynamic Range Optimizer");
        map.put(Integer.valueOf(TAG_IMAGE_STABILISATION), "Image Stabilisation");
        map.put(Integer.valueOf(TAG_LENS_ID), "Lens ID");
        map.put(Integer.valueOf(TAG_MINOLTA_MAKERNOTE), "Minolta Makernote");
        map.put(Integer.valueOf(TAG_COLOR_MODE), "Color Mode");
        map.put(Integer.valueOf(TAG_LENS_SPEC), "Lens Spec");
        map.put(Integer.valueOf(TAG_FULL_IMAGE_SIZE), "Full Image Size");
        map.put(Integer.valueOf(TAG_PREVIEW_IMAGE_SIZE), "Preview Image Size");
        map.put(Integer.valueOf(TAG_MACRO), "Macro");
        map.put(Integer.valueOf(TAG_EXPOSURE_MODE), "Exposure Mode");
        map.put(Integer.valueOf(TAG_FOCUS_MODE_2), "Focus Mode");
        map.put(Integer.valueOf(TAG_AF_MODE), "AF Mode");
        map.put(Integer.valueOf(TAG_AF_ILLUMINATOR), "AF Illuminator");
        map.put(Integer.valueOf(TAG_JPEG_QUALITY), "Quality");
        map.put(Integer.valueOf(TAG_FLASH_LEVEL), "Flash Level");
        map.put(Integer.valueOf(TAG_RELEASE_MODE), "Release Mode");
        map.put(Integer.valueOf(TAG_SEQUENCE_NUMBER), "Sequence Number");
        map.put(Integer.valueOf(TAG_ANTI_BLUR), "Anti Blur");
        map.put(Integer.valueOf(TAG_LONG_EXPOSURE_NOISE_REDUCTION_OR_FOCUS_MODE), "Long Exposure Noise Reduction");
        map.put(Integer.valueOf(TAG_DYNAMIC_RANGE_OPTIMIZER), "Dynamic Range Optimizer");
        map.put(Integer.valueOf(TAG_HIGH_ISO_NOISE_REDUCTION_2), "High ISO Noise Reduction");
        map.put(Integer.valueOf(TAG_INTELLIGENT_AUTO), "Intelligent Auto");
        map.put(Integer.valueOf(TAG_WHITE_BALANCE_2), "White Balance 2");
        map.put(65535, "No Print");
    }

    public SonyType1MakernoteDirectory() {
        setDescriptor(new SonyType1MakernoteDescriptor(this));
    }
}
