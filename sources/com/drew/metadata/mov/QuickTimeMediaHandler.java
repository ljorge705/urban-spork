package com.drew.metadata.mov;

import com.drew.imaging.quicktime.QuickTimeHandler;
import com.drew.lang.SequentialByteArrayReader;
import com.drew.lang.SequentialReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.mov.QuickTimeDirectory;
import com.drew.metadata.mov.atoms.Atom;
import com.drew.metadata.mov.media.QuickTimeMediaDirectory;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

/* loaded from: classes5.dex */
public abstract class QuickTimeMediaHandler<T extends QuickTimeDirectory> extends QuickTimeHandler<T> {
    protected abstract String getMediaInformation();

    protected abstract void processMediaInformation(SequentialReader sequentialReader, Atom atom) throws IOException;

    protected abstract void processSampleDescription(SequentialReader sequentialReader, Atom atom) throws IOException;

    protected abstract void processTimeToSample(SequentialReader sequentialReader, Atom atom) throws IOException;

    public QuickTimeMediaHandler(Metadata metadata) {
        super(metadata);
        if (QuickTimeHandlerFactory.HANDLER_PARAM_CREATION_TIME == null || QuickTimeHandlerFactory.HANDLER_PARAM_MODIFICATION_TIME == null) {
            return;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(1904, 0, 1, 0, 0, 0);
        long time = calendar.getTime().getTime();
        String string = new Date((QuickTimeHandlerFactory.HANDLER_PARAM_CREATION_TIME.longValue() * 1000) + time).toString();
        String string2 = new Date((QuickTimeHandlerFactory.HANDLER_PARAM_MODIFICATION_TIME.longValue() * 1000) + time).toString();
        this.directory.setString(QuickTimeMediaDirectory.TAG_CREATION_TIME, string);
        this.directory.setString(QuickTimeMediaDirectory.TAG_MODIFICATION_TIME, string2);
    }

    @Override // com.drew.imaging.quicktime.QuickTimeHandler
    public boolean shouldAcceptAtom(Atom atom) {
        return atom.type.equals(getMediaInformation()) || atom.type.equals("stsd") || atom.type.equals("stts");
    }

    @Override // com.drew.imaging.quicktime.QuickTimeHandler
    public boolean shouldAcceptContainer(Atom atom) {
        return atom.type.equals("stbl") || atom.type.equals("minf") || atom.type.equals("gmhd") || atom.type.equals("tmcd");
    }

    @Override // com.drew.imaging.quicktime.QuickTimeHandler
    public QuickTimeMediaHandler processAtom(Atom atom, byte[] bArr) throws IOException {
        if (bArr != null) {
            SequentialByteArrayReader sequentialByteArrayReader = new SequentialByteArrayReader(bArr);
            if (atom.type.equals(getMediaInformation())) {
                processMediaInformation(sequentialByteArrayReader, atom);
            } else if (atom.type.equals("stsd")) {
                processSampleDescription(sequentialByteArrayReader, atom);
            } else if (atom.type.equals("stts")) {
                processTimeToSample(sequentialByteArrayReader, atom);
            }
        }
        return this;
    }
}
