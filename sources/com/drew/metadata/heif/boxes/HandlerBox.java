package com.drew.metadata.heif.boxes;

import com.drew.lang.Charsets;
import com.drew.lang.SequentialReader;
import java.io.IOException;

/* loaded from: classes5.dex */
public class HandlerBox extends FullBox {
    String handlerType;
    String name;

    public String getHandlerType() {
        return this.handlerType;
    }

    public HandlerBox(SequentialReader sequentialReader, Box box) throws IOException {
        super(sequentialReader, box);
        sequentialReader.skip(4L);
        this.handlerType = sequentialReader.getString(4);
        sequentialReader.skip(12L);
        this.name = sequentialReader.getNullTerminatedString(((int) box.size) - 32, Charsets.UTF_8);
    }
}
