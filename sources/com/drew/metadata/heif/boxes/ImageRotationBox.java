package com.drew.metadata.heif.boxes;

import com.drew.lang.SequentialReader;
import com.drew.metadata.heif.HeifDirectory;
import java.io.IOException;

/* loaded from: classes5.dex */
public class ImageRotationBox extends Box {
    int angle;

    public ImageRotationBox(SequentialReader sequentialReader, Box box) throws IOException {
        super(box);
        this.angle = sequentialReader.getUInt8() & 3;
    }

    public void addMetadata(HeifDirectory heifDirectory) {
        if (heifDirectory.containsTag(6)) {
            return;
        }
        heifDirectory.setInt(6, this.angle);
    }
}
