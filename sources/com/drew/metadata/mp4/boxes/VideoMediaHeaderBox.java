package com.drew.metadata.mp4.boxes;

import com.drew.lang.SequentialReader;
import com.drew.metadata.mp4.media.Mp4VideoDirectory;
import java.io.IOException;

/* loaded from: classes5.dex */
public class VideoMediaHeaderBox extends FullBox {
    int graphicsMode;
    int[] opcolor;

    public VideoMediaHeaderBox(SequentialReader sequentialReader, Box box) throws IOException {
        super(sequentialReader, box);
        this.graphicsMode = sequentialReader.getUInt16();
        this.opcolor = new int[]{sequentialReader.getUInt16(), sequentialReader.getUInt16(), sequentialReader.getUInt16()};
    }

    public void addMetadata(Mp4VideoDirectory mp4VideoDirectory) {
        mp4VideoDirectory.setIntArray(Mp4VideoDirectory.TAG_OPCOLOR, this.opcolor);
        mp4VideoDirectory.setInt(211, this.graphicsMode);
    }
}
