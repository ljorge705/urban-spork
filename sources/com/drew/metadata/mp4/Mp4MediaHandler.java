package com.drew.metadata.mp4;

import com.drew.imaging.mp4.Mp4Handler;
import com.drew.lang.SequentialByteArrayReader;
import com.drew.lang.SequentialReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.mp4.boxes.Box;
import com.drew.metadata.mp4.media.Mp4MediaDirectory;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

/* loaded from: classes5.dex */
public abstract class Mp4MediaHandler<T extends Mp4MediaDirectory> extends Mp4Handler<T> {
    protected abstract String getMediaInformation();

    protected abstract void processMediaInformation(SequentialReader sequentialReader, Box box) throws IOException;

    protected abstract void processSampleDescription(SequentialReader sequentialReader, Box box) throws IOException;

    protected abstract void processTimeToSample(SequentialReader sequentialReader, Box box) throws IOException;

    public Mp4MediaHandler(Metadata metadata) {
        super(metadata);
        if (Mp4HandlerFactory.HANDLER_PARAM_CREATION_TIME == null || Mp4HandlerFactory.HANDLER_PARAM_MODIFICATION_TIME == null) {
            return;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(1904, 0, 1, 0, 0, 0);
        long time = calendar.getTime().getTime();
        String string = new Date((Mp4HandlerFactory.HANDLER_PARAM_CREATION_TIME.longValue() * 1000) + time).toString();
        String string2 = new Date((Mp4HandlerFactory.HANDLER_PARAM_MODIFICATION_TIME.longValue() * 1000) + time).toString();
        String str = Mp4HandlerFactory.HANDLER_PARAM_LANGUAGE;
        ((Mp4MediaDirectory) this.directory).setString(101, string);
        ((Mp4MediaDirectory) this.directory).setString(102, string2);
        ((Mp4MediaDirectory) this.directory).setString(104, str);
    }

    @Override // com.drew.imaging.mp4.Mp4Handler
    public boolean shouldAcceptBox(Box box) {
        return box.type.equals(getMediaInformation()) || box.type.equals("stsd") || box.type.equals("stts");
    }

    @Override // com.drew.imaging.mp4.Mp4Handler
    public boolean shouldAcceptContainer(Box box) {
        return box.type.equals("stbl") || box.type.equals("minf");
    }

    @Override // com.drew.imaging.mp4.Mp4Handler
    public Mp4Handler processBox(Box box, byte[] bArr) throws IOException {
        if (bArr != null) {
            SequentialByteArrayReader sequentialByteArrayReader = new SequentialByteArrayReader(bArr);
            if (box.type.equals(getMediaInformation())) {
                processMediaInformation(sequentialByteArrayReader, box);
            } else if (box.type.equals("stsd")) {
                processSampleDescription(sequentialByteArrayReader, box);
            } else if (box.type.equals("stts")) {
                processTimeToSample(sequentialByteArrayReader, box);
            }
        }
        return this;
    }
}
