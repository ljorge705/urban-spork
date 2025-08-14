package com.drew.metadata.heif.boxes;

import com.drew.lang.SequentialReader;
import io.sentry.protocol.DebugImage;
import java.io.IOException;

/* loaded from: classes5.dex */
public class Box {
    public long size;
    public String type;
    String usertype;

    public Box(SequentialReader sequentialReader) throws IOException {
        this.size = sequentialReader.getUInt32();
        this.type = sequentialReader.getString(4);
        long j = this.size;
        if (j == 1) {
            this.size = sequentialReader.getInt64();
        } else if (j == 0) {
            this.size = -1L;
        }
        if (this.type.equals(DebugImage.JsonKeys.UUID)) {
            this.usertype = sequentialReader.getString(16);
        }
    }

    public Box(Box box) {
        this.size = box.size;
        this.type = box.type;
        this.usertype = box.usertype;
    }
}
