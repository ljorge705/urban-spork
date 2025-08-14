package com.drew.imaging.jpeg;

import com.drew.lang.StreamReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.adobe.AdobeJpegReader;
import com.drew.metadata.exif.ExifReader;
import com.drew.metadata.file.FileSystemMetadataReader;
import com.drew.metadata.icc.IccReader;
import com.drew.metadata.iptc.IptcReader;
import com.drew.metadata.jfif.JfifReader;
import com.drew.metadata.jfxx.JfxxReader;
import com.drew.metadata.jpeg.JpegCommentReader;
import com.drew.metadata.jpeg.JpegDhtReader;
import com.drew.metadata.jpeg.JpegDnlReader;
import com.drew.metadata.jpeg.JpegReader;
import com.drew.metadata.photoshop.DuckyReader;
import com.drew.metadata.photoshop.PhotoshopReader;
import com.drew.metadata.xmp.XmpReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

/* loaded from: classes5.dex */
public class JpegMetadataReader {
    public static final Iterable<JpegSegmentMetadataReader> ALL_READERS = Arrays.asList(new JpegReader(), new JpegCommentReader(), new JfifReader(), new JfxxReader(), new ExifReader(), new XmpReader(), new IccReader(), new PhotoshopReader(), new DuckyReader(), new IptcReader(), new AdobeJpegReader(), new JpegDhtReader(), new JpegDnlReader());

    public static Metadata readMetadata(InputStream inputStream, Iterable<JpegSegmentMetadataReader> iterable) throws IOException, JpegProcessingException {
        Metadata metadata = new Metadata();
        process(metadata, inputStream, iterable);
        return metadata;
    }

    public static Metadata readMetadata(InputStream inputStream) throws IOException, JpegProcessingException {
        return readMetadata(inputStream, (Iterable<JpegSegmentMetadataReader>) null);
    }

    public static Metadata readMetadata(File file, Iterable<JpegSegmentMetadataReader> iterable) throws IOException, JpegProcessingException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            Metadata metadata = readMetadata(fileInputStream, iterable);
            fileInputStream.close();
            new FileSystemMetadataReader().read(file, metadata);
            return metadata;
        } catch (Throwable th) {
            fileInputStream.close();
            throw th;
        }
    }

    public static Metadata readMetadata(File file) throws IOException, JpegProcessingException {
        return readMetadata(file, (Iterable<JpegSegmentMetadataReader>) null);
    }

    public static void process(Metadata metadata, InputStream inputStream) throws IOException, JpegProcessingException {
        process(metadata, inputStream, null);
    }

    public static void process(Metadata metadata, InputStream inputStream, Iterable<JpegSegmentMetadataReader> iterable) throws IOException, JpegProcessingException {
        if (iterable == null) {
            iterable = ALL_READERS;
        }
        HashSet hashSet = new HashSet();
        Iterator<JpegSegmentMetadataReader> it = iterable.iterator();
        while (it.hasNext()) {
            Iterator<JpegSegmentType> it2 = it.next().getSegmentTypes().iterator();
            while (it2.hasNext()) {
                hashSet.add(it2.next());
            }
        }
        processJpegSegmentData(metadata, iterable, JpegSegmentReader.readSegments(new StreamReader(inputStream), hashSet));
    }

    public static void processJpegSegmentData(Metadata metadata, Iterable<JpegSegmentMetadataReader> iterable, JpegSegmentData jpegSegmentData) {
        for (JpegSegmentMetadataReader jpegSegmentMetadataReader : iterable) {
            for (JpegSegmentType jpegSegmentType : jpegSegmentMetadataReader.getSegmentTypes()) {
                jpegSegmentMetadataReader.readJpegSegments(jpegSegmentData.getSegments(jpegSegmentType), metadata, jpegSegmentType);
            }
        }
    }

    private JpegMetadataReader() throws Exception {
        throw new Exception("Not intended for instantiation");
    }
}
