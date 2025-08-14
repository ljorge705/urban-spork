package com.drew.metadata.mov.media;

import com.drew.metadata.mov.QuickTimeDirectory;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class QuickTimeSoundDirectory extends QuickTimeDirectory {
    public static final int TAG_AUDIO_FORMAT = 769;
    public static final int TAG_AUDIO_SAMPLE_RATE = 772;
    public static final int TAG_AUDIO_SAMPLE_SIZE = 771;
    public static final int TAG_NUMBER_OF_CHANNELS = 770;
    public static final int TAG_SOUND_BALANCE = 773;
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.mov.QuickTimeDirectory, com.drew.metadata.Directory
    public String getName() {
        return "QuickTime Sound";
    }

    @Override // com.drew.metadata.mov.QuickTimeDirectory, com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    public QuickTimeSoundDirectory() {
        setDescriptor(new QuickTimeSoundDescriptor(this));
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        QuickTimeMediaDirectory.addQuickTimeMediaTags(map);
        map.put(769, "Format");
        map.put(770, "Number of Channels");
        map.put(771, "Sample Size");
        map.put(772, "Sample Rate");
        map.put(773, "Balance");
    }
}
