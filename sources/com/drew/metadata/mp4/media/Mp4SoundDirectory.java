package com.drew.metadata.mp4.media;

import java.util.HashMap;

/* loaded from: classes5.dex */
public class Mp4SoundDirectory extends Mp4MediaDirectory {
    public static final int TAG_AUDIO_FORMAT = 301;
    public static final int TAG_AUDIO_SAMPLE_RATE = 304;
    public static final int TAG_AUDIO_SAMPLE_SIZE = 303;
    public static final int TAG_NUMBER_OF_CHANNELS = 302;
    public static final int TAG_SOUND_BALANCE = 305;
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.mp4.Mp4Directory, com.drew.metadata.Directory
    public String getName() {
        return "MP4 Sound";
    }

    @Override // com.drew.metadata.mp4.Mp4Directory, com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    public Mp4SoundDirectory() {
        setDescriptor(new Mp4SoundDescriptor(this));
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        Mp4MediaDirectory.addMp4MediaTags(map);
        map.put(301, "Format");
        map.put(Integer.valueOf(TAG_NUMBER_OF_CHANNELS), "Number of Channels");
        map.put(Integer.valueOf(TAG_AUDIO_SAMPLE_SIZE), "Sample Size");
        map.put(304, "Sample Rate");
        map.put(305, "Balance");
    }
}
