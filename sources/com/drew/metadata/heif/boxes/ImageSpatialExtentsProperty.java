package com.drew.metadata.heif.boxes;

import com.drew.lang.SequentialReader;
import com.drew.metadata.heif.HeifDirectory;
import java.io.IOException;

/* loaded from: classes5.dex */
public class ImageSpatialExtentsProperty extends FullBox {
    long height;
    long width;

    public ImageSpatialExtentsProperty(SequentialReader sequentialReader, Box box) throws IOException {
        super(sequentialReader, box);
        this.width = sequentialReader.getUInt32();
        this.height = sequentialReader.getUInt32();
    }

    public void addMetadata(HeifDirectory heifDirectory) {
        if (heifDirectory.containsTag(4) || heifDirectory.containsTag(5)) {
            return;
        }
        heifDirectory.setLong(4, this.width);
        heifDirectory.setLong(5, this.height);
    }
}
