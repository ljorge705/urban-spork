package com.drew.metadata.jpeg;

import com.drew.imaging.jpeg.JpegSegmentMetadataReader;
import com.drew.imaging.jpeg.JpegSegmentType;
import com.drew.metadata.Metadata;
import com.drew.metadata.StringValue;
import java.util.Collections;

/* loaded from: classes5.dex */
public class JpegCommentReader implements JpegSegmentMetadataReader {
    @Override // com.drew.imaging.jpeg.JpegSegmentMetadataReader
    public Iterable<JpegSegmentType> getSegmentTypes() {
        return Collections.singletonList(JpegSegmentType.COM);
    }

    @Override // com.drew.imaging.jpeg.JpegSegmentMetadataReader
    public void readJpegSegments(Iterable<byte[]> iterable, Metadata metadata, JpegSegmentType jpegSegmentType) {
        for (byte[] bArr : iterable) {
            JpegCommentDirectory jpegCommentDirectory = new JpegCommentDirectory();
            metadata.addDirectory(jpegCommentDirectory);
            jpegCommentDirectory.setStringValue(0, new StringValue(bArr, null));
        }
    }
}
