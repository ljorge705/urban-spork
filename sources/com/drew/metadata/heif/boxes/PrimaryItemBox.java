package com.drew.metadata.heif.boxes;

import com.drew.lang.SequentialReader;
import java.io.IOException;

/* loaded from: classes5.dex */
public class PrimaryItemBox extends FullBox {
    long itemID;

    public PrimaryItemBox(SequentialReader sequentialReader, Box box) throws IOException {
        super(sequentialReader, box);
        if (this.version == 0) {
            this.itemID = sequentialReader.getUInt16();
        } else {
            this.itemID = sequentialReader.getUInt32();
        }
    }
}
