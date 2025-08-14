package com.drew.imaging.heif;

import com.drew.lang.SequentialReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.heif.HeifDirectory;
import com.drew.metadata.heif.boxes.Box;
import java.io.IOException;

/* loaded from: classes5.dex */
public abstract class HeifHandler<T extends HeifDirectory> {
    protected T directory;
    protected Metadata metadata;

    protected abstract T getDirectory();

    protected abstract HeifHandler processBox(Box box, byte[] bArr) throws IOException;

    protected abstract void processContainer(Box box, SequentialReader sequentialReader) throws IOException;

    protected abstract boolean shouldAcceptBox(Box box);

    protected abstract boolean shouldAcceptContainer(Box box);

    public HeifHandler(Metadata metadata) {
        this.metadata = metadata;
        T t = (T) getDirectory();
        this.directory = t;
        metadata.addDirectory(t);
    }
}
