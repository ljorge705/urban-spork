package com.drew.metadata.mov.media;

import com.drew.metadata.mov.QuickTimeDirectory;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class QuickTimeMusicDirectory extends QuickTimeDirectory {
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.mov.QuickTimeDirectory, com.drew.metadata.Directory
    public String getName() {
        return "QuickTime Music";
    }

    @Override // com.drew.metadata.mov.QuickTimeDirectory, com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    public QuickTimeMusicDirectory() {
        setDescriptor(new QuickTimeMusicDescriptor(this));
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        QuickTimeMediaDirectory.addQuickTimeMediaTags(map);
    }
}
