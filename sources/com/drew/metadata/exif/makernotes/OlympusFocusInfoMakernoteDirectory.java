package com.drew.metadata.exif.makernotes;

import com.drew.metadata.Directory;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class OlympusFocusInfoMakernoteDirectory extends Directory {
    public static final int TagAfInfo = 808;
    public static final int TagAfPoint = 776;
    public static final int TagAutoFocus = 521;
    public static final int TagExternalFlash = 4609;
    public static final int TagExternalFlashBounce = 4612;
    public static final int TagExternalFlashGuideNumber = 4611;
    public static final int TagExternalFlashZoom = 4613;
    public static final int TagFocusDistance = 773;
    public static final int TagFocusInfoVersion = 0;
    public static final int TagFocusStepCount = 769;
    public static final int TagFocusStepInfinity = 771;
    public static final int TagFocusStepNear = 772;
    public static final int TagImageStabilization = 5632;
    public static final int TagInternalFlash = 4616;
    public static final int TagMacroLed = 4618;
    public static final int TagManualFlash = 4617;
    public static final int TagSceneArea = 529;
    public static final int TagSceneDetect = 528;
    public static final int TagSceneDetectData = 530;
    public static final int TagSensorTemperature = 5376;
    public static final int TagZoomStepCount = 768;
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.Directory
    public String getName() {
        return "Olympus Focus Info";
    }

    @Override // com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        map.put(0, "Focus Info Version");
        map.put(521, "Auto Focus");
        map.put(528, "Scene Detect");
        map.put(529, "Scene Area");
        map.put(530, "Scene Detect Data");
        map.put(768, "Zoom Step Count");
        map.put(769, "Focus Step Count");
        map.put(771, "Focus Step Infinity");
        map.put(772, "Focus Step Near");
        map.put(773, "Focus Distance");
        map.put(Integer.valueOf(TagAfPoint), "AF Point");
        map.put(Integer.valueOf(TagAfInfo), "AF Info");
        map.put(4609, "External Flash");
        map.put(4611, "External Flash Guide Number");
        map.put(Integer.valueOf(TagExternalFlashBounce), "External Flash Bounce");
        map.put(Integer.valueOf(TagExternalFlashZoom), "External Flash Zoom");
        map.put(Integer.valueOf(TagInternalFlash), "Internal Flash");
        map.put(Integer.valueOf(TagManualFlash), "Manual Flash");
        map.put(Integer.valueOf(TagMacroLed), "Macro LED");
        map.put(Integer.valueOf(TagSensorTemperature), "Sensor Temperature");
        map.put(Integer.valueOf(TagImageStabilization), "Image Stabilization");
    }

    public OlympusFocusInfoMakernoteDirectory() {
        setDescriptor(new OlympusFocusInfoMakernoteDescriptor(this));
    }
}
