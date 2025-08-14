package com.drew.metadata.gif;

import com.drew.metadata.Directory;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class GifAnimationDirectory extends Directory {
    public static final int TAG_ITERATION_COUNT = 1;
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.Directory
    public String getName() {
        return "GIF Animation";
    }

    @Override // com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        map.put(1, "Iteration Count");
    }

    public GifAnimationDirectory() {
        setDescriptor(new GifAnimationDescriptor(this));
    }
}
