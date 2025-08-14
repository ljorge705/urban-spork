package com.drew.metadata.mov.atoms;

import com.drew.lang.SequentialReader;
import com.drew.metadata.mov.atoms.SampleDescription;
import java.io.IOException;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public abstract class SampleDescriptionAtom<T extends SampleDescription> extends FullAtom {
    long numberOfEntries;
    ArrayList<T> sampleDescriptions;

    abstract T getSampleDescription(SequentialReader sequentialReader) throws IOException;

    public SampleDescriptionAtom(SequentialReader sequentialReader, Atom atom) throws IOException {
        super(sequentialReader, atom);
        this.numberOfEntries = sequentialReader.getUInt32();
        this.sampleDescriptions = new ArrayList<>((int) this.numberOfEntries);
        for (int i = 0; i < this.numberOfEntries; i++) {
            this.sampleDescriptions.add(getSampleDescription(sequentialReader));
        }
    }
}
