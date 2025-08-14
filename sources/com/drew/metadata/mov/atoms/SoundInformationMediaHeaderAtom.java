package com.drew.metadata.mov.atoms;

import com.drew.lang.SequentialReader;
import com.drew.metadata.mov.media.QuickTimeSoundDirectory;
import java.io.IOException;

/* loaded from: classes5.dex */
public class SoundInformationMediaHeaderAtom extends FullAtom {
    int balance;

    public SoundInformationMediaHeaderAtom(SequentialReader sequentialReader, Atom atom) throws IOException {
        super(sequentialReader, atom);
        this.balance = sequentialReader.getInt16();
        sequentialReader.skip(2L);
    }

    public void addMetadata(QuickTimeSoundDirectory quickTimeSoundDirectory) {
        int i = this.balance;
        quickTimeSoundDirectory.setDouble(773, ((-65536) & i) + ((i & 65535) / Math.pow(2.0d, 4.0d)));
    }
}
