package com.drew.metadata.mov.media;

import com.drew.lang.SequentialReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.mov.QuickTimeMediaHandler;
import com.drew.metadata.mov.atoms.Atom;
import com.drew.metadata.mov.atoms.TextSampleDescriptionAtom;
import java.io.IOException;

/* loaded from: classes5.dex */
public class QuickTimeTextHandler extends QuickTimeMediaHandler<QuickTimeTextDirectory> {
    @Override // com.drew.metadata.mov.QuickTimeMediaHandler
    protected String getMediaInformation() {
        return "gmhd";
    }

    @Override // com.drew.metadata.mov.QuickTimeMediaHandler
    protected void processMediaInformation(SequentialReader sequentialReader, Atom atom) throws IOException {
    }

    @Override // com.drew.metadata.mov.QuickTimeMediaHandler
    protected void processTimeToSample(SequentialReader sequentialReader, Atom atom) throws IOException {
    }

    public QuickTimeTextHandler(Metadata metadata) {
        super(metadata);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.drew.imaging.quicktime.QuickTimeHandler
    public QuickTimeTextDirectory getDirectory() {
        return new QuickTimeTextDirectory();
    }

    @Override // com.drew.metadata.mov.QuickTimeMediaHandler
    protected void processSampleDescription(SequentialReader sequentialReader, Atom atom) throws IOException {
        new TextSampleDescriptionAtom(sequentialReader, atom).addMetadata((QuickTimeTextDirectory) this.directory);
    }
}
