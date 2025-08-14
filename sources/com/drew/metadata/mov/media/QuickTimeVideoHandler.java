package com.drew.metadata.mov.media;

import com.drew.lang.SequentialReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.mov.QuickTimeMediaHandler;
import com.drew.metadata.mov.atoms.Atom;
import com.drew.metadata.mov.atoms.TimeToSampleAtom;
import com.drew.metadata.mov.atoms.VideoInformationMediaHeaderAtom;
import com.drew.metadata.mov.atoms.VideoSampleDescriptionAtom;
import java.io.IOException;

/* loaded from: classes5.dex */
public class QuickTimeVideoHandler extends QuickTimeMediaHandler<QuickTimeVideoDirectory> {
    @Override // com.drew.metadata.mov.QuickTimeMediaHandler
    protected String getMediaInformation() {
        return "vmhd";
    }

    public QuickTimeVideoHandler(Metadata metadata) {
        super(metadata);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.drew.imaging.quicktime.QuickTimeHandler
    public QuickTimeVideoDirectory getDirectory() {
        return new QuickTimeVideoDirectory();
    }

    @Override // com.drew.metadata.mov.QuickTimeMediaHandler
    public void processSampleDescription(SequentialReader sequentialReader, Atom atom) throws IOException {
        new VideoSampleDescriptionAtom(sequentialReader, atom).addMetadata((QuickTimeVideoDirectory) this.directory);
    }

    @Override // com.drew.metadata.mov.QuickTimeMediaHandler
    public void processMediaInformation(SequentialReader sequentialReader, Atom atom) throws IOException {
        new VideoInformationMediaHeaderAtom(sequentialReader, atom).addMetadata((QuickTimeVideoDirectory) this.directory);
    }

    @Override // com.drew.metadata.mov.QuickTimeMediaHandler
    public void processTimeToSample(SequentialReader sequentialReader, Atom atom) throws IOException {
        new TimeToSampleAtom(sequentialReader, atom).addMetadata((QuickTimeVideoDirectory) this.directory);
    }
}
