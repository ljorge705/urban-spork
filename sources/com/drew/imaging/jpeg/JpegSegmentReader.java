package com.drew.imaging.jpeg;

import com.drew.lang.SequentialReader;
import com.drew.lang.StreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

/* loaded from: classes5.dex */
public class JpegSegmentReader {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final byte MARKER_EOI = -39;
    private static final byte SEGMENT_IDENTIFIER = -1;
    private static final byte SEGMENT_SOS = -38;

    public static JpegSegmentData readSegments(File file, Iterable<JpegSegmentType> iterable) throws Throwable {
        FileInputStream fileInputStream = null;
        try {
            FileInputStream fileInputStream2 = new FileInputStream(file);
            try {
                JpegSegmentData segments = readSegments(new StreamReader(fileInputStream2), iterable);
                fileInputStream2.close();
                return segments;
            } catch (Throwable th) {
                th = th;
                fileInputStream = fileInputStream2;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static JpegSegmentData readSegments(SequentialReader sequentialReader, Iterable<JpegSegmentType> iterable) throws IOException, JpegProcessingException {
        HashSet hashSet;
        int uInt16 = sequentialReader.getUInt16();
        if (uInt16 != 65496) {
            throw new JpegProcessingException("JPEG data is expected to begin with 0xFFD8 (ÿØ) not 0x" + Integer.toHexString(uInt16));
        }
        if (iterable != null) {
            hashSet = new HashSet();
            Iterator<JpegSegmentType> it = iterable.iterator();
            while (it.hasNext()) {
                hashSet.add(Byte.valueOf(it.next().byteValue));
            }
        } else {
            hashSet = null;
        }
        HashSet hashSet2 = hashSet;
        JpegSegmentData jpegSegmentData = new JpegSegmentData();
        while (true) {
            byte int8 = sequentialReader.getInt8();
            byte int82 = sequentialReader.getInt8();
            while (true) {
                if (int8 == -1 && int82 != -1 && int82 != 0) {
                    break;
                }
                byte b = int82;
                int82 = sequentialReader.getInt8();
                int8 = b;
            }
            if (int82 == -38 || int82 == -39) {
                return jpegSegmentData;
            }
            int uInt162 = sequentialReader.getUInt16() - 2;
            if (uInt162 < 0) {
                throw new JpegProcessingException("JPEG segment size would be less than zero");
            }
            if (hashSet2 == null || hashSet2.contains(Byte.valueOf(int82))) {
                jpegSegmentData.addSegment(int82, sequentialReader.getBytes(uInt162));
            } else if (!sequentialReader.trySkip(uInt162)) {
                return jpegSegmentData;
            }
        }
    }

    private JpegSegmentReader() throws Exception {
        throw new Exception("Not intended for instantiation.");
    }
}
