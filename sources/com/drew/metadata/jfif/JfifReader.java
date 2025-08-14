package com.drew.metadata.jfif;

import com.drew.imaging.jpeg.JpegSegmentMetadataReader;
import com.drew.imaging.jpeg.JpegSegmentType;
import com.drew.lang.ByteArrayReader;
import com.drew.lang.RandomAccessReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataReader;
import java.io.IOException;
import java.util.Collections;

/* loaded from: classes5.dex */
public class JfifReader implements JpegSegmentMetadataReader, MetadataReader {
    public static final String PREAMBLE = "JFIF";

    @Override // com.drew.imaging.jpeg.JpegSegmentMetadataReader
    public Iterable<JpegSegmentType> getSegmentTypes() {
        return Collections.singletonList(JpegSegmentType.APP0);
    }

    @Override // com.drew.imaging.jpeg.JpegSegmentMetadataReader
    public void readJpegSegments(Iterable<byte[]> iterable, Metadata metadata, JpegSegmentType jpegSegmentType) {
        for (byte[] bArr : iterable) {
            if (bArr.length >= 4 && PREAMBLE.equals(new String(bArr, 0, 4))) {
                extract(new ByteArrayReader(bArr), metadata);
            }
        }
    }

    @Override // com.drew.metadata.MetadataReader
    public void extract(RandomAccessReader randomAccessReader, Metadata metadata) {
        JfifDirectory jfifDirectory = new JfifDirectory();
        metadata.addDirectory(jfifDirectory);
        try {
            jfifDirectory.setInt(5, randomAccessReader.getUInt16(5));
            jfifDirectory.setInt(7, randomAccessReader.getUInt8(7));
            jfifDirectory.setInt(8, randomAccessReader.getUInt16(8));
            jfifDirectory.setInt(10, randomAccessReader.getUInt16(10));
            jfifDirectory.setInt(12, randomAccessReader.getUInt8(12));
            jfifDirectory.setInt(13, randomAccessReader.getUInt8(13));
        } catch (IOException e) {
            jfifDirectory.addError(e.getMessage());
        }
    }
}
