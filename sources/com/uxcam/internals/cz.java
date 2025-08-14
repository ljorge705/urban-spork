package com.uxcam.internals;

import com.uxcam.screenaction.utils.FilePath;
import java.io.File;
import java.io.FilenameFilter;

/* loaded from: classes6.dex */
public final class cz implements FilenameFilter {
    @Override // java.io.FilenameFilter
    public final boolean accept(File file, String str) {
        return FilePath.isVideoFile(str);
    }
}
