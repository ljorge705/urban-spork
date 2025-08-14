package com.drew.metadata.mov.metadata;

import com.drew.imaging.quicktime.QuickTimeHandler;
import com.drew.lang.ByteUtil;
import com.drew.lang.SequentialByteArrayReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.mov.QuickTimeAtomTypes;
import com.drew.metadata.mov.QuickTimeMetadataHandler;
import com.drew.metadata.mov.atoms.Atom;
import java.io.IOException;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class QuickTimeDataHandler extends QuickTimeMetadataHandler {
    private int currentIndex;
    private ArrayList<String> keys;

    public QuickTimeDataHandler(Metadata metadata) {
        super(metadata);
        this.currentIndex = 0;
        this.keys = new ArrayList<>();
    }

    @Override // com.drew.metadata.mov.QuickTimeMetadataHandler, com.drew.imaging.quicktime.QuickTimeHandler
    protected boolean shouldAcceptAtom(Atom atom) {
        return atom.type.equals("hdlr") || atom.type.equals(QuickTimeAtomTypes.ATOM_KEYS) || atom.type.equals("data");
    }

    @Override // com.drew.metadata.mov.QuickTimeMetadataHandler, com.drew.imaging.quicktime.QuickTimeHandler
    protected boolean shouldAcceptContainer(Atom atom) {
        return atom.type.equals("ilst") || ByteUtil.getInt32(atom.type.getBytes(), 0, true) <= this.keys.size();
    }

    @Override // com.drew.metadata.mov.QuickTimeMetadataHandler, com.drew.imaging.quicktime.QuickTimeHandler
    protected QuickTimeHandler processAtom(Atom atom, byte[] bArr) throws IOException {
        if (bArr != null) {
            SequentialByteArrayReader sequentialByteArrayReader = new SequentialByteArrayReader(bArr);
            if (atom.type.equals(QuickTimeAtomTypes.ATOM_KEYS)) {
                processKeys(sequentialByteArrayReader);
            } else if (atom.type.equals("data")) {
                processData(bArr, sequentialByteArrayReader);
            }
        } else {
            int int32 = ByteUtil.getInt32(atom.type.getBytes(), 0, true);
            if (int32 > 0 && int32 < this.keys.size() + 1) {
                this.currentIndex = int32 - 1;
            }
        }
        return this;
    }

    @Override // com.drew.metadata.mov.QuickTimeMetadataHandler
    protected void processKeys(SequentialByteArrayReader sequentialByteArrayReader) throws IOException {
        sequentialByteArrayReader.skip(4L);
        int int32 = sequentialByteArrayReader.getInt32();
        for (int i = 0; i < int32; i++) {
            int int322 = sequentialByteArrayReader.getInt32();
            sequentialByteArrayReader.skip(4L);
            this.keys.add(new String(sequentialByteArrayReader.getBytes(int322 - 8)));
        }
    }

    @Override // com.drew.metadata.mov.QuickTimeMetadataHandler
    protected void processData(byte[] bArr, SequentialByteArrayReader sequentialByteArrayReader) throws IOException {
        int int32 = sequentialByteArrayReader.getInt32();
        sequentialByteArrayReader.skip(4L);
        Integer num = QuickTimeMetadataDirectory._tagIntegerMap.get(this.keys.get(this.currentIndex));
        if (num != null) {
            int length = bArr.length - 8;
            if (int32 == 1) {
                this.directory.setString(num.intValue(), sequentialByteArrayReader.getString(length, "UTF-8"));
                return;
            }
            if (int32 != 27) {
                if (int32 == 30) {
                    int i = length / 4;
                    int[] iArr = new int[i];
                    for (int i2 = 0; i2 < i; i2++) {
                        iArr[i2] = sequentialByteArrayReader.getInt32();
                    }
                    this.directory.setIntArray(num.intValue(), iArr);
                    return;
                }
                if (int32 != 13 && int32 != 14) {
                    if (int32 == 22) {
                        byte[] bArr2 = new byte[4];
                        sequentialByteArrayReader.getBytes(bArr2, 4 - length, length);
                        this.directory.setInt(num.intValue(), new SequentialByteArrayReader(bArr2).getInt32());
                        return;
                    } else {
                        if (int32 != 23) {
                            return;
                        }
                        this.directory.setFloat(num.intValue(), sequentialByteArrayReader.getFloat32());
                        return;
                    }
                }
            }
            this.directory.setByteArray(num.intValue(), sequentialByteArrayReader.getBytes(length));
        }
    }
}
