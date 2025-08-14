package com.drew.metadata.mov.media;

import com.drew.lang.SequentialReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.mov.QuickTimeMediaHandler;
import com.drew.metadata.mov.atoms.Atom;
import com.drew.metadata.mov.atoms.MusicSampleDescriptionAtom;
import java.io.IOException;

/* loaded from: classes5.dex */
public class QuickTimeMusicHandler extends QuickTimeMediaHandler<QuickTimeMusicDirectory> {
    @Override // com.drew.metadata.mov.QuickTimeMediaHandler
    protected String getMediaInformation() {
        return null;
    }

    @Override // com.drew.metadata.mov.QuickTimeMediaHandler
    protected void processMediaInformation(SequentialReader sequentialReader, Atom atom) throws IOException {
    }

    @Override // com.drew.metadata.mov.QuickTimeMediaHandler
    protected void processTimeToSample(SequentialReader sequentialReader, Atom atom) throws IOException {
    }

    public QuickTimeMusicHandler(Metadata metadata) {
        super(metadata);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.drew.imaging.quicktime.QuickTimeHandler
    public QuickTimeMusicDirectory getDirectory() {
        return (QuickTimeMusicDirectory) this.directory;
    }

    @Override // com.drew.metadata.mov.QuickTimeMediaHandler
    protected void processSampleDescription(SequentialReader sequentialReader, Atom atom) throws IOException {
        new MusicSampleDescriptionAtom(sequentialReader, atom).addMetadata((QuickTimeMusicDirectory) this.directory);
    }
}
