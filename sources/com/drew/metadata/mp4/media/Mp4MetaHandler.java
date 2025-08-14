package com.drew.metadata.mp4.media;

import com.drew.lang.SequentialReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.mp4.Mp4MediaHandler;
import com.drew.metadata.mp4.boxes.Box;
import java.io.IOException;

/* loaded from: classes5.dex */
public class Mp4MetaHandler extends Mp4MediaHandler<Mp4MetaDirectory> {
    @Override // com.drew.metadata.mp4.Mp4MediaHandler
    protected String getMediaInformation() {
        return "nmhd";
    }

    @Override // com.drew.metadata.mp4.Mp4MediaHandler
    protected void processMediaInformation(SequentialReader sequentialReader, Box box) throws IOException {
    }

    @Override // com.drew.metadata.mp4.Mp4MediaHandler
    protected void processSampleDescription(SequentialReader sequentialReader, Box box) throws IOException {
    }

    @Override // com.drew.metadata.mp4.Mp4MediaHandler
    protected void processTimeToSample(SequentialReader sequentialReader, Box box) throws IOException {
    }

    public Mp4MetaHandler(Metadata metadata) {
        super(metadata);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.drew.imaging.mp4.Mp4Handler
    public Mp4MetaDirectory getDirectory() {
        return new Mp4MetaDirectory();
    }
}
