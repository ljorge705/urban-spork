package com.drew.metadata.mov.media;

import com.drew.lang.SequentialReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.mov.QuickTimeAtomTypes;
import com.drew.metadata.mov.QuickTimeMediaHandler;
import com.drew.metadata.mov.atoms.Atom;
import com.drew.metadata.mov.atoms.TimecodeInformationMediaAtom;
import com.drew.metadata.mov.atoms.TimecodeSampleDescriptionAtom;
import java.io.IOException;

/* loaded from: classes5.dex */
public class QuickTimeTimecodeHandler extends QuickTimeMediaHandler<QuickTimeTimecodeDirectory> {
    @Override // com.drew.metadata.mov.QuickTimeMediaHandler
    protected String getMediaInformation() {
        return QuickTimeAtomTypes.ATOM_TIMECODE_MEDIA_INFO;
    }

    @Override // com.drew.metadata.mov.QuickTimeMediaHandler
    protected void processTimeToSample(SequentialReader sequentialReader, Atom atom) throws IOException {
    }

    public QuickTimeTimecodeHandler(Metadata metadata) {
        super(metadata);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.drew.imaging.quicktime.QuickTimeHandler
    public QuickTimeTimecodeDirectory getDirectory() {
        return new QuickTimeTimecodeDirectory();
    }

    @Override // com.drew.metadata.mov.QuickTimeMediaHandler
    public void processSampleDescription(SequentialReader sequentialReader, Atom atom) throws IOException {
        new TimecodeSampleDescriptionAtom(sequentialReader, atom).addMetadata((QuickTimeTimecodeDirectory) this.directory);
    }

    @Override // com.drew.metadata.mov.QuickTimeMediaHandler
    public void processMediaInformation(SequentialReader sequentialReader, Atom atom) throws IOException {
        new TimecodeInformationMediaAtom(sequentialReader, atom).addMetadata((QuickTimeTimecodeDirectory) this.directory);
    }
}
