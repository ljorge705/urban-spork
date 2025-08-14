package com.drew.metadata.mp4.boxes;

import com.drew.lang.SequentialReader;
import com.drew.metadata.mp4.media.Mp4SoundDirectory;
import java.io.IOException;

/* loaded from: classes5.dex */
public class SoundMediaHeaderBox extends FullBox {
    int balance;

    public SoundMediaHeaderBox(SequentialReader sequentialReader, Box box) throws IOException {
        super(sequentialReader, box);
        this.balance = sequentialReader.getInt16();
        sequentialReader.skip(2L);
    }

    public void addMetadata(Mp4SoundDirectory mp4SoundDirectory) {
        int i = this.balance;
        mp4SoundDirectory.setDouble(305, ((-65536) & i) + ((i & 65535) / Math.pow(2.0d, 4.0d)));
    }
}
