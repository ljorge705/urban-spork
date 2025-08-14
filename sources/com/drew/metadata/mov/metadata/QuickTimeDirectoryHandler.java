package com.drew.metadata.mov.metadata;

import com.drew.imaging.quicktime.QuickTimeHandler;
import com.drew.lang.SequentialByteArrayReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.mov.QuickTimeMetadataHandler;
import com.drew.metadata.mov.atoms.Atom;
import java.io.IOException;

/* loaded from: classes5.dex */
public class QuickTimeDirectoryHandler extends QuickTimeMetadataHandler {
    private String currentData;

    @Override // com.drew.metadata.mov.QuickTimeMetadataHandler
    protected void processKeys(SequentialByteArrayReader sequentialByteArrayReader) throws IOException {
    }

    public QuickTimeDirectoryHandler(Metadata metadata) {
        super(metadata);
    }

    @Override // com.drew.metadata.mov.QuickTimeMetadataHandler, com.drew.imaging.quicktime.QuickTimeHandler
    protected boolean shouldAcceptAtom(Atom atom) {
        return atom.type.equals("data");
    }

    @Override // com.drew.metadata.mov.QuickTimeMetadataHandler, com.drew.imaging.quicktime.QuickTimeHandler
    protected boolean shouldAcceptContainer(Atom atom) {
        return QuickTimeMetadataDirectory._tagIntegerMap.containsKey(atom.type) || atom.type.equals("ilst");
    }

    @Override // com.drew.metadata.mov.QuickTimeMetadataHandler, com.drew.imaging.quicktime.QuickTimeHandler
    protected QuickTimeHandler processAtom(Atom atom, byte[] bArr) throws IOException {
        if (bArr != null) {
            SequentialByteArrayReader sequentialByteArrayReader = new SequentialByteArrayReader(bArr);
            if (atom.type.equals("data") && this.currentData != null) {
                processData(bArr, sequentialByteArrayReader);
            } else {
                this.currentData = new String(sequentialByteArrayReader.getBytes(4));
            }
        } else if (QuickTimeMetadataDirectory._tagIntegerMap.containsKey(atom.type)) {
            this.currentData = atom.type;
        } else {
            this.currentData = null;
        }
        return this;
    }

    @Override // com.drew.metadata.mov.QuickTimeMetadataHandler
    protected void processData(byte[] bArr, SequentialByteArrayReader sequentialByteArrayReader) throws IOException {
        sequentialByteArrayReader.skip(8L);
        this.directory.setString(QuickTimeMetadataDirectory._tagIntegerMap.get(this.currentData).intValue(), new String(sequentialByteArrayReader.getBytes(bArr.length - 8)));
    }
}
