package com.drew.metadata.mp4.boxes;

import com.drew.lang.SequentialReader;
import com.drew.metadata.mp4.Mp4Directory;
import java.awt.Point;
import java.io.IOException;

/* loaded from: classes5.dex */
public class TrackHeaderBox extends FullBox {
    int alternateGroup;
    long creationTime;
    long duration;
    long height;
    int layer;
    int[] matrix;
    long modificationTime;
    long trackID;
    int volume;
    long width;

    public TrackHeaderBox(SequentialReader sequentialReader, Box box) throws IOException {
        super(sequentialReader, box);
        this.matrix = new int[9];
        if (this.version == 1) {
            this.creationTime = sequentialReader.getInt64();
            this.modificationTime = sequentialReader.getInt64();
            this.trackID = sequentialReader.getInt32();
            sequentialReader.skip(4L);
            this.duration = sequentialReader.getInt64();
        } else {
            this.creationTime = sequentialReader.getUInt32();
            this.modificationTime = sequentialReader.getUInt32();
            this.trackID = sequentialReader.getUInt32();
            sequentialReader.skip(4L);
            this.duration = sequentialReader.getUInt32();
        }
        sequentialReader.skip(8L);
        this.layer = sequentialReader.getInt16();
        this.alternateGroup = sequentialReader.getInt16();
        this.volume = sequentialReader.getInt16();
        sequentialReader.skip(2L);
        for (int i = 0; i < 9; i++) {
            this.matrix[i] = sequentialReader.getInt32();
        }
        this.width = sequentialReader.getInt32();
        this.height = sequentialReader.getInt32();
    }

    public void addMetadata(Mp4Directory mp4Directory) {
        if (this.width == 0 || this.height == 0 || mp4Directory.getDoubleObject(512) != null) {
            return;
        }
        int[] iArr = this.matrix;
        Point point = new Point(iArr[1] + iArr[4], iArr[0] + iArr[3]);
        mp4Directory.setDouble(512, Math.abs(Math.toDegrees(Math.atan2(point.y, point.x)) - 45.0d));
    }
}
