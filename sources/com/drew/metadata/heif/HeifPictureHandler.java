package com.drew.metadata.heif;

import com.drew.imaging.heif.HeifHandler;
import com.drew.lang.SequentialByteArrayReader;
import com.drew.lang.SequentialReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.heif.boxes.AuxiliaryTypeProperty;
import com.drew.metadata.heif.boxes.Box;
import com.drew.metadata.heif.boxes.ColourInformationBox;
import com.drew.metadata.heif.boxes.ImageRotationBox;
import com.drew.metadata.heif.boxes.ImageSpatialExtentsProperty;
import com.drew.metadata.heif.boxes.ItemInfoBox;
import com.drew.metadata.heif.boxes.ItemLocationBox;
import com.drew.metadata.heif.boxes.ItemProtectionBox;
import com.drew.metadata.heif.boxes.PixelInformationBox;
import com.drew.metadata.heif.boxes.PrimaryItemBox;
import java.io.IOException;
import java.util.Arrays;

/* loaded from: classes5.dex */
public class HeifPictureHandler extends HeifHandler<HeifDirectory> {
    ItemInfoBox itemInfoBox;
    ItemLocationBox itemLocationBox;
    ItemProtectionBox itemProtectionBox;
    PrimaryItemBox primaryItemBox;

    @Override // com.drew.imaging.heif.HeifHandler
    protected void processContainer(Box box, SequentialReader sequentialReader) throws IOException {
    }

    public HeifPictureHandler(Metadata metadata) {
        super(metadata);
        this.itemProtectionBox = null;
        this.primaryItemBox = null;
        this.itemInfoBox = null;
        this.itemLocationBox = null;
    }

    @Override // com.drew.imaging.heif.HeifHandler
    protected boolean shouldAcceptBox(Box box) {
        return Arrays.asList(HeifBoxTypes.BOX_ITEM_PROTECTION, HeifBoxTypes.BOX_PRIMARY_ITEM, HeifBoxTypes.BOX_ITEM_INFO, HeifBoxTypes.BOX_ITEM_LOCATION, HeifBoxTypes.BOX_IMAGE_SPATIAL_EXTENTS, HeifBoxTypes.BOX_AUXILIARY_TYPE_PROPERTY, HeifBoxTypes.BOX_IMAGE_ROTATION, HeifBoxTypes.BOX_COLOUR_INFO, HeifBoxTypes.BOX_PIXEL_INFORMATION).contains(box.type);
    }

    @Override // com.drew.imaging.heif.HeifHandler
    protected boolean shouldAcceptContainer(Box box) {
        return box.type.equals(HeifContainerTypes.BOX_IMAGE_PROPERTY) || box.type.equals(HeifContainerTypes.BOX_ITEM_PROPERTY);
    }

    @Override // com.drew.imaging.heif.HeifHandler
    protected HeifHandler processBox(Box box, byte[] bArr) throws IOException {
        SequentialByteArrayReader sequentialByteArrayReader = new SequentialByteArrayReader(bArr);
        if (box.type.equals(HeifBoxTypes.BOX_ITEM_PROTECTION)) {
            this.itemProtectionBox = new ItemProtectionBox(sequentialByteArrayReader, box);
        } else if (box.type.equals(HeifBoxTypes.BOX_PRIMARY_ITEM)) {
            this.primaryItemBox = new PrimaryItemBox(sequentialByteArrayReader, box);
        } else if (box.type.equals(HeifBoxTypes.BOX_ITEM_INFO)) {
            ItemInfoBox itemInfoBox = new ItemInfoBox(sequentialByteArrayReader, box);
            this.itemInfoBox = itemInfoBox;
            itemInfoBox.addMetadata(this.directory);
        } else if (box.type.equals(HeifBoxTypes.BOX_ITEM_LOCATION)) {
            this.itemLocationBox = new ItemLocationBox(sequentialByteArrayReader, box);
        } else if (box.type.equals(HeifBoxTypes.BOX_IMAGE_SPATIAL_EXTENTS)) {
            new ImageSpatialExtentsProperty(sequentialByteArrayReader, box).addMetadata(this.directory);
        } else if (box.type.equals(HeifBoxTypes.BOX_AUXILIARY_TYPE_PROPERTY)) {
            new AuxiliaryTypeProperty(sequentialByteArrayReader, box);
        } else if (box.type.equals(HeifBoxTypes.BOX_IMAGE_ROTATION)) {
            new ImageRotationBox(sequentialByteArrayReader, box).addMetadata(this.directory);
        } else if (box.type.equals(HeifBoxTypes.BOX_COLOUR_INFO)) {
            new ColourInformationBox(sequentialByteArrayReader, box, this.metadata).addMetadata(this.directory);
        } else if (box.type.equals(HeifBoxTypes.BOX_PIXEL_INFORMATION)) {
            new PixelInformationBox(sequentialByteArrayReader, box).addMetadata(this.directory);
        }
        return this;
    }

    @Override // com.drew.imaging.heif.HeifHandler
    protected HeifDirectory getDirectory() {
        return new HeifDirectory();
    }
}
