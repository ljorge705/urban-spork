package com.drew.metadata.mp4.media;

import com.drew.lang.SequentialReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.mp4.Mp4MediaHandler;
import com.drew.metadata.mp4.boxes.AudioSampleEntry;
import com.drew.metadata.mp4.boxes.Box;
import com.drew.metadata.mp4.boxes.SoundMediaHeaderBox;
import com.drew.metadata.mp4.boxes.TimeToSampleBox;
import java.io.IOException;

/* loaded from: classes5.dex */
public class Mp4SoundHandler extends Mp4MediaHandler<Mp4SoundDirectory> {
    @Override // com.drew.metadata.mp4.Mp4MediaHandler
    protected String getMediaInformation() {
        return "smhd";
    }

    public Mp4SoundHandler(Metadata metadata) {
        super(metadata);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.drew.imaging.mp4.Mp4Handler
    public Mp4SoundDirectory getDirectory() {
        return new Mp4SoundDirectory();
    }

    @Override // com.drew.metadata.mp4.Mp4MediaHandler
    public void processSampleDescription(SequentialReader sequentialReader, Box box) throws IOException {
        new AudioSampleEntry(sequentialReader, box).addMetadata((Mp4SoundDirectory) this.directory);
    }

    @Override // com.drew.metadata.mp4.Mp4MediaHandler
    public void processMediaInformation(SequentialReader sequentialReader, Box box) throws IOException {
        new SoundMediaHeaderBox(sequentialReader, box).addMetadata((Mp4SoundDirectory) this.directory);
    }

    @Override // com.drew.metadata.mp4.Mp4MediaHandler
    protected void processTimeToSample(SequentialReader sequentialReader, Box box) throws IOException {
        new TimeToSampleBox(sequentialReader, box).addMetadata((Mp4SoundDirectory) this.directory);
    }
}
