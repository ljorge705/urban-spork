package com.drew.metadata.photoshop;

import com.drew.imaging.jpeg.JpegSegmentMetadataReader;
import com.drew.imaging.jpeg.JpegSegmentType;
import com.drew.lang.Charsets;
import com.drew.lang.SequentialByteArrayReader;
import com.drew.lang.SequentialReader;
import com.drew.metadata.Metadata;
import java.io.IOException;
import java.util.Collections;

/* loaded from: classes5.dex */
public class DuckyReader implements JpegSegmentMetadataReader {
    private static final String JPEG_SEGMENT_PREAMBLE = "Ducky";

    @Override // com.drew.imaging.jpeg.JpegSegmentMetadataReader
    public Iterable<JpegSegmentType> getSegmentTypes() {
        return Collections.singletonList(JpegSegmentType.APPC);
    }

    @Override // com.drew.imaging.jpeg.JpegSegmentMetadataReader
    public void readJpegSegments(Iterable<byte[]> iterable, Metadata metadata, JpegSegmentType jpegSegmentType) {
        for (byte[] bArr : iterable) {
            if (bArr.length >= 5 && JPEG_SEGMENT_PREAMBLE.equals(new String(bArr, 0, 5))) {
                extract(new SequentialByteArrayReader(bArr, 5), metadata);
            }
        }
    }

    public void extract(SequentialReader sequentialReader, Metadata metadata) {
        DuckyDirectory duckyDirectory = new DuckyDirectory();
        metadata.addDirectory(duckyDirectory);
        while (true) {
            try {
                int uInt16 = sequentialReader.getUInt16();
                if (uInt16 == 0) {
                    return;
                }
                int uInt162 = sequentialReader.getUInt16();
                if (uInt16 != 1) {
                    if (uInt16 == 2 || uInt16 == 3) {
                        sequentialReader.skip(4L);
                        duckyDirectory.setStringValue(uInt16, sequentialReader.getStringValue(uInt162 - 4, Charsets.UTF_16BE));
                    } else {
                        duckyDirectory.setByteArray(uInt16, sequentialReader.getBytes(uInt162));
                    }
                } else {
                    if (uInt162 != 4) {
                        duckyDirectory.addError("Unexpected length for the quality tag");
                        return;
                    }
                    duckyDirectory.setInt(uInt16, sequentialReader.getInt32());
                }
            } catch (IOException e) {
                duckyDirectory.addError(e.getMessage());
                return;
            }
        }
    }
}
