package com.drew.metadata.mov;

import com.drew.imaging.quicktime.QuickTimeHandler;
import com.drew.lang.SequentialByteArrayReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.mov.atoms.Atom;
import com.drew.metadata.mov.atoms.FileTypeCompatibilityAtom;
import com.drew.metadata.mov.atoms.HandlerReferenceAtom;
import com.drew.metadata.mov.atoms.MediaHeaderAtom;
import com.drew.metadata.mov.atoms.MovieHeaderAtom;
import com.drew.metadata.mov.atoms.canon.CanonThumbnailAtom;
import java.io.IOException;

/* loaded from: classes5.dex */
public class QuickTimeAtomHandler extends QuickTimeHandler<QuickTimeDirectory> {
    private QuickTimeHandlerFactory handlerFactory;

    public QuickTimeAtomHandler(Metadata metadata) {
        super(metadata);
        this.handlerFactory = new QuickTimeHandlerFactory(this);
    }

    @Override // com.drew.imaging.quicktime.QuickTimeHandler
    protected QuickTimeDirectory getDirectory() {
        return new QuickTimeDirectory();
    }

    @Override // com.drew.imaging.quicktime.QuickTimeHandler
    public boolean shouldAcceptAtom(Atom atom) {
        return atom.type.equals("ftyp") || atom.type.equals("mvhd") || atom.type.equals("hdlr") || atom.type.equals("mdhd") || atom.type.equals(QuickTimeAtomTypes.ATOM_CANON_THUMBNAIL);
    }

    @Override // com.drew.imaging.quicktime.QuickTimeHandler
    public boolean shouldAcceptContainer(Atom atom) {
        return atom.type.equals("trak") || atom.type.equals("udta") || atom.type.equals("meta") || atom.type.equals("moov") || atom.type.equals("mdia");
    }

    @Override // com.drew.imaging.quicktime.QuickTimeHandler
    public QuickTimeHandler processAtom(Atom atom, byte[] bArr) throws IOException {
        if (bArr != null) {
            SequentialByteArrayReader sequentialByteArrayReader = new SequentialByteArrayReader(bArr);
            if (atom.type.equals("mvhd")) {
                new MovieHeaderAtom(sequentialByteArrayReader, atom).addMetadata(this.directory);
            } else if (atom.type.equals("ftyp")) {
                new FileTypeCompatibilityAtom(sequentialByteArrayReader, atom).addMetadata(this.directory);
            } else {
                if (atom.type.equals("hdlr")) {
                    return this.handlerFactory.getHandler(new HandlerReferenceAtom(sequentialByteArrayReader, atom).getComponentType(), this.metadata);
                }
                if (atom.type.equals("mdhd")) {
                    new MediaHeaderAtom(sequentialByteArrayReader, atom);
                } else if (atom.type.equals(QuickTimeAtomTypes.ATOM_CANON_THUMBNAIL)) {
                    new CanonThumbnailAtom(sequentialByteArrayReader).addMetadata(this.directory);
                }
            }
        } else if (atom.type.equals("cmov")) {
            this.directory.addError("Compressed QuickTime movies not supported");
        }
        return this;
    }
}
