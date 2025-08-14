package com.drew.metadata.mp4.boxes;

import com.drew.lang.SequentialReader;
import com.drew.metadata.mp4.Mp4HandlerFactory;
import com.drew.metadata.mp4.media.Mp4SoundDirectory;
import com.drew.metadata.mp4.media.Mp4VideoDirectory;
import java.io.IOException;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class TimeToSampleBox extends FullBox {
    ArrayList<EntryCount> entries;
    long entryCount;

    public TimeToSampleBox(SequentialReader sequentialReader, Box box) throws IOException {
        super(sequentialReader, box);
        this.entryCount = sequentialReader.getUInt32();
        this.entries = new ArrayList<>();
        for (int i = 0; i < this.entryCount; i++) {
            this.entries.add(new EntryCount(sequentialReader.getUInt32(), sequentialReader.getUInt32()));
        }
    }

    public void addMetadata(Mp4VideoDirectory mp4VideoDirectory) {
        float f = 0.0f;
        while (this.entries.iterator().hasNext()) {
            f += r0.next().sampleCount;
        }
        mp4VideoDirectory.setFloat(Mp4VideoDirectory.TAG_FRAME_RATE, Mp4HandlerFactory.HANDLER_PARAM_TIME_SCALE.longValue() / (Mp4HandlerFactory.HANDLER_PARAM_DURATION.longValue() / f));
    }

    public void addMetadata(Mp4SoundDirectory mp4SoundDirectory) {
        mp4SoundDirectory.setDouble(304, Mp4HandlerFactory.HANDLER_PARAM_TIME_SCALE.longValue());
    }

    class EntryCount {
        long sampleCount;
        long sampleDelta;

        public EntryCount(long j, long j2) {
            this.sampleCount = j;
            this.sampleDelta = j2;
        }
    }
}
