package com.drew.metadata.ico;

import com.drew.lang.SequentialReader;
import com.drew.metadata.Metadata;
import java.io.IOException;

/* loaded from: classes5.dex */
public class IcoReader {
    public void extract(SequentialReader sequentialReader, Metadata metadata) {
        sequentialReader.setMotorolaByteOrder(false);
        try {
            if (sequentialReader.getUInt16() != 0) {
                IcoDirectory icoDirectory = new IcoDirectory();
                icoDirectory.addError("Invalid header bytes");
                metadata.addDirectory(icoDirectory);
                return;
            }
            int uInt16 = sequentialReader.getUInt16();
            if (uInt16 != 1 && uInt16 != 2) {
                IcoDirectory icoDirectory2 = new IcoDirectory();
                icoDirectory2.addError("Invalid type " + uInt16 + " -- expecting 1 or 2");
                metadata.addDirectory(icoDirectory2);
                return;
            }
            int uInt162 = sequentialReader.getUInt16();
            if (uInt162 == 0) {
                IcoDirectory icoDirectory3 = new IcoDirectory();
                icoDirectory3.addError("Image count cannot be zero");
                metadata.addDirectory(icoDirectory3);
                return;
            }
            for (int i = 0; i < uInt162; i++) {
                IcoDirectory icoDirectory4 = new IcoDirectory();
                try {
                    icoDirectory4.setInt(1, uInt16);
                    icoDirectory4.setInt(2, sequentialReader.getUInt8());
                    icoDirectory4.setInt(3, sequentialReader.getUInt8());
                    icoDirectory4.setInt(4, sequentialReader.getUInt8());
                    sequentialReader.getUInt8();
                    if (uInt16 == 1) {
                        icoDirectory4.setInt(5, sequentialReader.getUInt16());
                        icoDirectory4.setInt(7, sequentialReader.getUInt16());
                    } else {
                        icoDirectory4.setInt(6, sequentialReader.getUInt16());
                        icoDirectory4.setInt(8, sequentialReader.getUInt16());
                    }
                    icoDirectory4.setLong(9, sequentialReader.getUInt32());
                    icoDirectory4.setLong(10, sequentialReader.getUInt32());
                } catch (IOException e) {
                    icoDirectory4.addError("Exception reading ICO file metadata: " + e.getMessage());
                }
                metadata.addDirectory(icoDirectory4);
            }
        } catch (IOException e2) {
            IcoDirectory icoDirectory5 = new IcoDirectory();
            icoDirectory5.addError("Exception reading ICO file metadata: " + e2.getMessage());
            metadata.addDirectory(icoDirectory5);
        }
    }
}
