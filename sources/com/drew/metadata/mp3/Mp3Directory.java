package com.drew.metadata.mp3;

import androidx.exifinterface.media.ExifInterface;
import com.drew.metadata.Directory;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class Mp3Directory extends Directory {
    public static final int TAG_BITRATE = 3;
    public static final int TAG_COPYRIGHT = 7;
    public static final int TAG_EMPHASIS = 6;
    public static final int TAG_FRAME_SIZE = 8;
    public static final int TAG_FREQUENCY = 4;
    public static final int TAG_ID = 1;
    public static final int TAG_LAYER = 2;
    public static final int TAG_MODE = 5;
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.Directory
    public String getName() {
        return "MP3";
    }

    @Override // com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        map.put(1, "ID");
        map.put(2, "Layer");
        map.put(3, "Bitrate");
        map.put(4, "Frequency");
        map.put(5, "Mode");
        map.put(6, "Emphasis Method");
        map.put(7, ExifInterface.TAG_COPYRIGHT);
        map.put(8, "Frame Size");
    }

    public Mp3Directory() {
        setDescriptor(new Mp3Descriptor(this));
    }
}
