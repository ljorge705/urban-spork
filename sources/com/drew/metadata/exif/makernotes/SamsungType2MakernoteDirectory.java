package com.drew.metadata.exif.makernotes;

import com.drew.metadata.Directory;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class SamsungType2MakernoteDirectory extends Directory {
    public static final int TagCameraTemperature = 67;
    public static final int TagDeviceType = 2;
    public static final int TagFaceDetect = 256;
    public static final int TagFaceName = 291;
    public static final int TagFaceRecognition = 288;
    public static final int TagFirmwareName = 40961;
    public static final int TagMakerNoteVersion = 1;
    public static final int TagSamsungModelId = 3;
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.Directory
    public String getName() {
        return "Samsung Makernote";
    }

    @Override // com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        map.put(1, "Maker Note Version");
        map.put(2, "Device Type");
        map.put(3, "Model Id");
        map.put(67, "Camera Temperature");
        map.put(256, "Face Detect");
        map.put(288, "Face Recognition");
        map.put(291, "Face Name");
        map.put(40961, "Firmware Name");
    }

    public SamsungType2MakernoteDirectory() {
        setDescriptor(new SamsungType2MakernoteDescriptor(this));
    }
}
