package com.drew.metadata.mov;

import com.drew.imaging.quicktime.QuickTimeHandler;
import com.drew.lang.SequentialByteArrayReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.mov.atoms.Atom;
import com.drew.metadata.mov.metadata.QuickTimeMetadataDirectory;
import java.io.IOException;

/* loaded from: classes5.dex */
public abstract class QuickTimeMetadataHandler extends QuickTimeHandler {
    protected abstract void processData(byte[] bArr, SequentialByteArrayReader sequentialByteArrayReader) throws IOException;

    protected abstract void processKeys(SequentialByteArrayReader sequentialByteArrayReader) throws IOException;

    public QuickTimeMetadataHandler(Metadata metadata) {
        super(metadata);
    }

    @Override // com.drew.imaging.quicktime.QuickTimeHandler
    protected QuickTimeDirectory getDirectory() {
        return new QuickTimeMetadataDirectory();
    }

    @Override // com.drew.imaging.quicktime.QuickTimeHandler
    protected boolean shouldAcceptAtom(Atom atom) {
        return atom.type.equals("hdlr") || atom.type.equals(QuickTimeAtomTypes.ATOM_KEYS) || atom.type.equals("data");
    }

    @Override // com.drew.imaging.quicktime.QuickTimeHandler
    protected boolean shouldAcceptContainer(Atom atom) {
        return atom.type.equals("ilst");
    }

    @Override // com.drew.imaging.quicktime.QuickTimeHandler
    protected QuickTimeHandler processAtom(Atom atom, byte[] bArr) throws IOException {
        if (bArr != null) {
            SequentialByteArrayReader sequentialByteArrayReader = new SequentialByteArrayReader(bArr);
            if (atom.type.equals(QuickTimeAtomTypes.ATOM_KEYS)) {
                processKeys(sequentialByteArrayReader);
            } else if (atom.type.equals("data")) {
                processData(bArr, sequentialByteArrayReader);
            }
        }
        return this;
    }
}
