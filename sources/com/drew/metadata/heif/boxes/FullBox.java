package com.drew.metadata.heif.boxes;

import com.drew.lang.SequentialReader;
import java.io.IOException;

/* loaded from: classes5.dex */
public class FullBox extends Box {
    byte[] flags;
    int version;

    public FullBox(SequentialReader sequentialReader, Box box) throws IOException {
        super(box);
        this.version = sequentialReader.getUInt8();
        this.flags = sequentialReader.getBytes(3);
    }

    public FullBox(FullBox fullBox) {
        super(fullBox);
        this.version = fullBox.version;
        this.flags = fullBox.flags;
    }
}
