package com.drew.metadata.heif.boxes;

import com.drew.lang.SequentialReader;
import com.drew.metadata.heif.HeifDirectory;
import java.io.IOException;

/* loaded from: classes5.dex */
public class PixelInformationBox extends FullBox {
    int[] channels;
    int numChannels;

    public PixelInformationBox(SequentialReader sequentialReader, Box box) throws IOException {
        super(sequentialReader, box);
        int uInt8 = sequentialReader.getUInt8();
        this.numChannels = uInt8;
        this.channels = new int[uInt8];
        int i = 0;
        while (true) {
            int[] iArr = this.channels;
            if (i >= iArr.length) {
                return;
            }
            iArr[i] = sequentialReader.getUInt8();
            i++;
        }
    }

    public void addMetadata(HeifDirectory heifDirectory) {
        if (heifDirectory.containsTag(7)) {
            return;
        }
        heifDirectory.setIntArray(7, this.channels);
    }
}
