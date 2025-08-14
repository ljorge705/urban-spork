package com.drew.metadata.mov.atoms.canon;

import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.imaging.jpeg.JpegSegmentData;
import com.drew.imaging.jpeg.JpegSegmentReader;
import com.drew.imaging.jpeg.JpegSegmentType;
import com.drew.lang.SequentialReader;
import com.drew.lang.StreamReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.exif.ExifReader;
import com.drew.metadata.mov.QuickTimeDirectory;
import com.drew.metadata.mov.atoms.Atom;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

/* loaded from: classes5.dex */
public class CanonThumbnailAtom extends Atom {
    private String dateTime;

    public CanonThumbnailAtom(SequentialReader sequentialReader) throws IOException {
        super(sequentialReader);
        readCNDA(sequentialReader);
    }

    private void readCNDA(SequentialReader sequentialReader) throws IOException {
        if (this.type.compareTo("CNDA") == 0) {
            ExifReader exifReader = new ExifReader();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(sequentialReader.getBytes((int) this.size));
            HashSet hashSet = new HashSet();
            Iterator<JpegSegmentType> it = exifReader.getSegmentTypes().iterator();
            while (it.hasNext()) {
                hashSet.add(it.next());
            }
            try {
                JpegSegmentData segments = JpegSegmentReader.readSegments(new StreamReader(byteArrayInputStream), hashSet);
                Metadata metadata = new Metadata();
                for (JpegSegmentType jpegSegmentType : exifReader.getSegmentTypes()) {
                    exifReader.readJpegSegments(segments.getSegments(jpegSegmentType), metadata, jpegSegmentType);
                }
                Directory firstDirectoryOfType = metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
                if (firstDirectoryOfType != null) {
                    for (Tag tag : firstDirectoryOfType.getTags()) {
                        if (tag.getTagType() == 306) {
                            this.dateTime = tag.getDescription();
                        }
                    }
                }
            } catch (JpegProcessingException unused) {
            }
        }
    }

    public void addMetadata(QuickTimeDirectory quickTimeDirectory) {
        quickTimeDirectory.setString(8192, this.dateTime);
    }
}
