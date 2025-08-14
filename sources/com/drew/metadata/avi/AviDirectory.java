package com.drew.metadata.avi;

import com.drew.metadata.Directory;
import com.google.common.net.HttpHeaders;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class AviDirectory extends Directory {
    public static final String CHUNK_DATETIME_ORIGINAL = "IDIT";
    public static final String CHUNK_MAIN_HEADER = "avih";
    public static final String CHUNK_STREAM_HEADER = "strh";
    public static final String FORMAT = "AVI ";
    public static final String LIST_HEADER = "hdrl";
    public static final String LIST_STREAM_HEADER = "strl";
    public static final int TAG_AUDIO_CODEC = 5;
    public static final int TAG_DATETIME_ORIGINAL = 320;
    public static final int TAG_DURATION = 3;
    public static final int TAG_FRAMES_PER_SECOND = 1;
    public static final int TAG_HEIGHT = 7;
    public static final int TAG_SAMPLES_PER_SECOND = 2;
    public static final int TAG_STREAMS = 8;
    public static final int TAG_VIDEO_CODEC = 4;
    public static final int TAG_WIDTH = 6;
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.Directory
    public String getName() {
        return "AVI";
    }

    @Override // com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        map.put(1, "Frames Per Second");
        map.put(2, "Samples Per Second");
        map.put(3, "Duration");
        map.put(4, "Video Codec");
        map.put(5, "Audio Codec");
        map.put(6, HttpHeaders.WIDTH);
        map.put(7, "Height");
        map.put(8, "Stream Count");
        map.put(320, "Datetime Original");
    }

    public AviDirectory() {
        setDescriptor(new AviDescriptor(this));
    }
}
