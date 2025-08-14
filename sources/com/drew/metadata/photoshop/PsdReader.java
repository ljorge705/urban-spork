package com.drew.metadata.photoshop;

import com.drew.imaging.ImageProcessingException;
import com.drew.lang.SequentialReader;
import com.drew.metadata.Metadata;
import java.io.IOException;

/* loaded from: classes5.dex */
public class PsdReader {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    public void extract(SequentialReader sequentialReader, Metadata metadata) throws ImageProcessingException {
        PsdHeaderDirectory psdHeaderDirectory = new PsdHeaderDirectory();
        metadata.addDirectory(psdHeaderDirectory);
        try {
            if (sequentialReader.getInt32() != 943870035) {
                psdHeaderDirectory.addError("Invalid PSD file signature");
                return;
            }
            int uInt16 = sequentialReader.getUInt16();
            if (uInt16 != 1 && uInt16 != 2) {
                psdHeaderDirectory.addError("Invalid PSD file version (must be 1 or 2)");
                return;
            }
            sequentialReader.skip(6L);
            psdHeaderDirectory.setInt(1, sequentialReader.getUInt16());
            psdHeaderDirectory.setInt(2, sequentialReader.getInt32());
            psdHeaderDirectory.setInt(3, sequentialReader.getInt32());
            psdHeaderDirectory.setInt(4, sequentialReader.getUInt16());
            psdHeaderDirectory.setInt(5, sequentialReader.getUInt16());
            try {
                sequentialReader.skip(sequentialReader.getUInt32());
                new PhotoshopReader().extract(sequentialReader, (int) sequentialReader.getUInt32(), metadata);
            } catch (IOException unused) {
            }
        } catch (IOException unused2) {
            psdHeaderDirectory.addError("Unable to read PSD header");
        }
    }
}
