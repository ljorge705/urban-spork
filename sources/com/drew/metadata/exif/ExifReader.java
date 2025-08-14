package com.drew.metadata.exif;

import com.drew.imaging.jpeg.JpegSegmentMetadataReader;
import com.drew.imaging.jpeg.JpegSegmentType;
import com.drew.imaging.tiff.TiffProcessingException;
import com.drew.imaging.tiff.TiffReader;
import com.drew.lang.ByteArrayReader;
import com.drew.lang.RandomAccessReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import java.io.IOException;
import java.util.Collections;

/* loaded from: classes5.dex */
public class ExifReader implements JpegSegmentMetadataReader {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final String JPEG_SEGMENT_PREAMBLE = "Exif\u0000\u0000";

    @Override // com.drew.imaging.jpeg.JpegSegmentMetadataReader
    public Iterable<JpegSegmentType> getSegmentTypes() {
        return Collections.singletonList(JpegSegmentType.APP1);
    }

    @Override // com.drew.imaging.jpeg.JpegSegmentMetadataReader
    public void readJpegSegments(Iterable<byte[]> iterable, Metadata metadata, JpegSegmentType jpegSegmentType) {
        for (byte[] bArr : iterable) {
            if (bArr.length >= 6 && new String(bArr, 0, 6).equals(JPEG_SEGMENT_PREAMBLE)) {
                extract(new ByteArrayReader(bArr), metadata, 6);
            }
        }
    }

    public void extract(RandomAccessReader randomAccessReader, Metadata metadata) {
        extract(randomAccessReader, metadata, 0);
    }

    public void extract(RandomAccessReader randomAccessReader, Metadata metadata, int i) {
        extract(randomAccessReader, metadata, i, null);
    }

    public void extract(RandomAccessReader randomAccessReader, Metadata metadata, int i, Directory directory) {
        ExifTiffHandler exifTiffHandler = new ExifTiffHandler(metadata, directory);
        try {
            new TiffReader().processTiff(randomAccessReader, exifTiffHandler, i);
        } catch (TiffProcessingException e) {
            exifTiffHandler.error("Exception processing TIFF data: " + e.getMessage());
            e.printStackTrace(System.err);
        } catch (IOException e2) {
            exifTiffHandler.error("Exception processing TIFF data: " + e2.getMessage());
            e2.printStackTrace(System.err);
        }
    }
}
