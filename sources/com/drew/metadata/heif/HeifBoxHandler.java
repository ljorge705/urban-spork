package com.drew.metadata.heif;

import com.drew.imaging.heif.HeifHandler;
import com.drew.lang.SequentialByteArrayReader;
import com.drew.lang.SequentialReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.heif.boxes.Box;
import com.drew.metadata.heif.boxes.FileTypeBox;
import com.drew.metadata.heif.boxes.FullBox;
import com.drew.metadata.heif.boxes.HandlerBox;
import java.io.IOException;
import java.util.Arrays;

/* loaded from: classes5.dex */
public class HeifBoxHandler extends HeifHandler<HeifDirectory> {
    HandlerBox handlerBox;
    private HeifHandlerFactory handlerFactory;

    public HeifBoxHandler(Metadata metadata) {
        super(metadata);
        this.handlerFactory = new HeifHandlerFactory(this);
    }

    @Override // com.drew.imaging.heif.HeifHandler
    protected HeifDirectory getDirectory() {
        return new HeifDirectory();
    }

    @Override // com.drew.imaging.heif.HeifHandler
    public boolean shouldAcceptBox(Box box) {
        return Arrays.asList("ftyp", "hdlr", HeifBoxTypes.BOX_HVC1).contains(box.type);
    }

    @Override // com.drew.imaging.heif.HeifHandler
    public boolean shouldAcceptContainer(Box box) {
        return box.type.equals("meta") || box.type.equals(HeifContainerTypes.BOX_IMAGE_PROPERTY) || box.type.equals(HeifContainerTypes.BOX_ITEM_PROPERTY);
    }

    @Override // com.drew.imaging.heif.HeifHandler
    public HeifHandler processBox(Box box, byte[] bArr) throws IOException {
        if (bArr != null) {
            SequentialByteArrayReader sequentialByteArrayReader = new SequentialByteArrayReader(bArr);
            if (box.type.equals("ftyp")) {
                processFileType(sequentialByteArrayReader, box);
            } else if (box.type.equals("hdlr")) {
                HandlerBox handlerBox = new HandlerBox(sequentialByteArrayReader, box);
                this.handlerBox = handlerBox;
                return this.handlerFactory.getHandler(handlerBox, this.metadata);
            }
        }
        return this;
    }

    @Override // com.drew.imaging.heif.HeifHandler
    public void processContainer(Box box, SequentialReader sequentialReader) throws IOException {
        if (box.type.equals("meta")) {
            new FullBox(sequentialReader, box);
        }
    }

    private void processFileType(SequentialReader sequentialReader, Box box) throws IOException {
        FileTypeBox fileTypeBox = new FileTypeBox(sequentialReader, box);
        fileTypeBox.addMetadata(this.directory);
        if (fileTypeBox.getCompatibleBrands().contains("mif1")) {
            return;
        }
        this.directory.addError("File Type Box does not contain required brand, mif1");
    }
}
