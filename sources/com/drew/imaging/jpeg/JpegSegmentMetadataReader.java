package com.drew.imaging.jpeg;

import com.drew.metadata.Metadata;

/* loaded from: classes5.dex */
public interface JpegSegmentMetadataReader {
    Iterable<JpegSegmentType> getSegmentTypes();

    void readJpegSegments(Iterable<byte[]> iterable, Metadata metadata, JpegSegmentType jpegSegmentType);
}
