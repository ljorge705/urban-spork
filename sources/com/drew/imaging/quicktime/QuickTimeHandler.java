package com.drew.imaging.quicktime;

import com.drew.metadata.Metadata;
import com.drew.metadata.mov.QuickTimeDirectory;
import com.drew.metadata.mov.atoms.Atom;
import java.io.IOException;

/* loaded from: classes5.dex */
public abstract class QuickTimeHandler<T extends QuickTimeDirectory> {
    protected T directory;
    protected Metadata metadata;

    protected abstract T getDirectory();

    protected abstract QuickTimeHandler processAtom(Atom atom, byte[] bArr) throws IOException;

    protected abstract boolean shouldAcceptAtom(Atom atom);

    protected abstract boolean shouldAcceptContainer(Atom atom);

    public QuickTimeHandler(Metadata metadata) {
        this.metadata = metadata;
        T t = (T) getDirectory();
        this.directory = t;
        metadata.addDirectory(t);
    }

    protected QuickTimeHandler processContainer(Atom atom) throws IOException {
        return processAtom(atom, null);
    }

    public void addError(String str) {
        this.directory.addError(str);
    }
}
