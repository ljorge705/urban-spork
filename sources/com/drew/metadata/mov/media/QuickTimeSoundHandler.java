package com.drew.metadata.mov.media;

import com.drew.lang.SequentialReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.mov.QuickTimeHandlerFactory;
import com.drew.metadata.mov.QuickTimeMediaHandler;
import com.drew.metadata.mov.atoms.Atom;
import com.drew.metadata.mov.atoms.SoundInformationMediaHeaderAtom;
import com.drew.metadata.mov.atoms.SoundSampleDescriptionAtom;
import java.io.IOException;

/* loaded from: classes5.dex */
public class QuickTimeSoundHandler extends QuickTimeMediaHandler<QuickTimeSoundDirectory> {
    @Override // com.drew.metadata.mov.QuickTimeMediaHandler
    protected String getMediaInformation() {
        return "smhd";
    }

    public QuickTimeSoundHandler(Metadata metadata) {
        super(metadata);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.drew.imaging.quicktime.QuickTimeHandler
    public QuickTimeSoundDirectory getDirectory() {
        return new QuickTimeSoundDirectory();
    }

    @Override // com.drew.metadata.mov.QuickTimeMediaHandler
    public void processSampleDescription(SequentialReader sequentialReader, Atom atom) throws IOException {
        new SoundSampleDescriptionAtom(sequentialReader, atom).addMetadata((QuickTimeSoundDirectory) this.directory);
    }

    @Override // com.drew.metadata.mov.QuickTimeMediaHandler
    public void processMediaInformation(SequentialReader sequentialReader, Atom atom) throws IOException {
        new SoundInformationMediaHeaderAtom(sequentialReader, atom).addMetadata((QuickTimeSoundDirectory) this.directory);
    }

    @Override // com.drew.metadata.mov.QuickTimeMediaHandler
    protected void processTimeToSample(SequentialReader sequentialReader, Atom atom) throws IOException {
        ((QuickTimeSoundDirectory) this.directory).setDouble(772, QuickTimeHandlerFactory.HANDLER_PARAM_TIME_SCALE.longValue());
    }
}
