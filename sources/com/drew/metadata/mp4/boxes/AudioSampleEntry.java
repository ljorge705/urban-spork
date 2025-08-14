package com.drew.metadata.mp4.boxes;

import com.drew.lang.SequentialReader;
import com.drew.metadata.mp4.Mp4Dictionary;
import com.drew.metadata.mp4.media.Mp4SoundDirectory;
import java.io.IOException;

/* loaded from: classes5.dex */
public class AudioSampleEntry extends SampleEntry {
    int channelcount;
    long samplerate;
    int samplesize;

    public AudioSampleEntry(SequentialReader sequentialReader, Box box) throws IOException {
        super(sequentialReader, box);
        sequentialReader.skip(8L);
        this.channelcount = sequentialReader.getUInt16();
        this.samplesize = sequentialReader.getInt16();
        sequentialReader.skip(2L);
        sequentialReader.skip(2L);
        this.samplerate = sequentialReader.getUInt32();
    }

    public void addMetadata(Mp4SoundDirectory mp4SoundDirectory) {
        Mp4Dictionary.setLookup(301, this.format, mp4SoundDirectory);
        mp4SoundDirectory.setInt(Mp4SoundDirectory.TAG_NUMBER_OF_CHANNELS, this.channelcount);
        mp4SoundDirectory.setInt(Mp4SoundDirectory.TAG_AUDIO_SAMPLE_SIZE, this.samplesize);
    }
}
