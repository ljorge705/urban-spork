package com.drew.imaging.jpeg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/* loaded from: classes5.dex */
public class JpegSegmentData {
    private final HashMap<Byte, List<byte[]>> _segmentDataMap = new HashMap<>(10);

    public void addSegment(byte b, byte[] bArr) {
        getOrCreateSegmentList(b).add(bArr);
    }

    public Iterable<JpegSegmentType> getSegmentTypes() {
        HashSet hashSet = new HashSet();
        for (Byte b : this._segmentDataMap.keySet()) {
            JpegSegmentType jpegSegmentTypeFromByte = JpegSegmentType.fromByte(b.byteValue());
            if (jpegSegmentTypeFromByte == null) {
                throw new IllegalStateException("Should not have a segmentTypeByte that is not in the enum: " + Integer.toHexString(b.byteValue()));
            }
            hashSet.add(jpegSegmentTypeFromByte);
        }
        return hashSet;
    }

    public byte[] getSegment(byte b) {
        return getSegment(b, 0);
    }

    public byte[] getSegment(JpegSegmentType jpegSegmentType) {
        return getSegment(jpegSegmentType.byteValue, 0);
    }

    public byte[] getSegment(JpegSegmentType jpegSegmentType, int i) {
        return getSegment(jpegSegmentType.byteValue, i);
    }

    public byte[] getSegment(byte b, int i) {
        List<byte[]> segmentList = getSegmentList(b);
        if (segmentList == null || segmentList.size() <= i) {
            return null;
        }
        return segmentList.get(i);
    }

    public Iterable<byte[]> getSegments(JpegSegmentType jpegSegmentType) {
        return getSegments(jpegSegmentType.byteValue);
    }

    public Iterable<byte[]> getSegments(byte b) {
        List<byte[]> segmentList = getSegmentList(b);
        return segmentList == null ? new ArrayList() : segmentList;
    }

    private List<byte[]> getSegmentList(byte b) {
        return this._segmentDataMap.get(Byte.valueOf(b));
    }

    private List<byte[]> getOrCreateSegmentList(byte b) {
        if (this._segmentDataMap.containsKey(Byte.valueOf(b))) {
            return this._segmentDataMap.get(Byte.valueOf(b));
        }
        ArrayList arrayList = new ArrayList();
        this._segmentDataMap.put(Byte.valueOf(b), arrayList);
        return arrayList;
    }

    public int getSegmentCount(JpegSegmentType jpegSegmentType) {
        return getSegmentCount(jpegSegmentType.byteValue);
    }

    public int getSegmentCount(byte b) {
        List<byte[]> segmentList = getSegmentList(b);
        if (segmentList == null) {
            return 0;
        }
        return segmentList.size();
    }

    public void removeSegmentOccurrence(JpegSegmentType jpegSegmentType, int i) {
        removeSegmentOccurrence(jpegSegmentType.byteValue, i);
    }

    public void removeSegmentOccurrence(byte b, int i) {
        this._segmentDataMap.get(Byte.valueOf(b)).remove(i);
    }

    public void removeSegment(JpegSegmentType jpegSegmentType) {
        removeSegment(jpegSegmentType.byteValue);
    }

    public void removeSegment(byte b) {
        this._segmentDataMap.remove(Byte.valueOf(b));
    }

    public boolean containsSegment(JpegSegmentType jpegSegmentType) {
        return containsSegment(jpegSegmentType.byteValue);
    }

    public boolean containsSegment(byte b) {
        return this._segmentDataMap.containsKey(Byte.valueOf(b));
    }
}
