package io.perfmark;

import javax.annotation.Nullable;

/* loaded from: classes6.dex */
public final class Tag {
    final long tagId;

    @Nullable
    final String tagName;

    Tag(@Nullable String str, long j) {
        this.tagName = str;
        this.tagId = j;
    }
}
