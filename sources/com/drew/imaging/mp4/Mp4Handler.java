package com.drew.imaging.mp4;

import com.drew.metadata.Metadata;
import com.drew.metadata.mp4.Mp4Directory;
import com.drew.metadata.mp4.boxes.Box;
import java.io.IOException;

/* loaded from: classes5.dex */
public abstract class Mp4Handler<T extends Mp4Directory> {
    protected T directory;
    protected Metadata metadata;

    protected abstract T getDirectory();

    protected abstract Mp4Handler processBox(Box box, byte[] bArr) throws IOException;

    protected abstract boolean shouldAcceptBox(Box box);

    protected abstract boolean shouldAcceptContainer(Box box);

    public Mp4Handler(Metadata metadata) {
        this.metadata = metadata;
        T t = (T) getDirectory();
        this.directory = t;
        metadata.addDirectory(t);
    }

    protected Mp4Handler processContainer(Box box) throws IOException {
        return processBox(box, null);
    }

    public void addError(String str) {
        this.directory.addError(str);
    }
}
