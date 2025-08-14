package com.drew.metadata.heif.boxes;

import com.drew.lang.Charsets;
import com.drew.lang.SequentialByteArrayReader;
import com.drew.lang.SequentialReader;
import com.drew.metadata.heif.HeifDirectory;
import java.io.IOException;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class ItemInfoBox extends FullBox {
    ArrayList<ItemInfoEntry> entries;
    long entryCount;

    public void addMetadata(HeifDirectory heifDirectory) {
    }

    public ItemInfoBox(SequentialReader sequentialReader, Box box) throws IOException {
        super(sequentialReader, box);
        if (this.version == 0) {
            this.entryCount = sequentialReader.getUInt16();
        } else {
            this.entryCount = sequentialReader.getUInt32();
        }
        this.entries = new ArrayList<>();
        for (int i = 1; i <= this.entryCount; i++) {
            this.entries.add(new ItemInfoEntry(new SequentialByteArrayReader(sequentialReader.getBytes(((int) r0.size) - 8)), new Box(sequentialReader)));
        }
    }

    class ItemInfoEntry extends FullBox {
        String contentEncoding;
        String contentType;
        String extensionType;
        long itemID;
        String itemName;
        long itemProtectionIndex;
        String itemType;
        String itemUriType;

        public ItemInfoEntry(SequentialReader sequentialReader, Box box) throws IOException {
            super(sequentialReader, box);
            if (this.version == 0 || this.version == 1) {
                this.itemID = sequentialReader.getUInt16();
                this.itemProtectionIndex = sequentialReader.getUInt16();
                this.itemName = sequentialReader.getNullTerminatedString((int) (box.size - sequentialReader.getPosition()), Charsets.UTF_8);
                this.contentType = sequentialReader.getNullTerminatedString((int) (box.size - sequentialReader.getPosition()), Charsets.UTF_8);
                if (box.size - sequentialReader.getPosition() > 0) {
                    this.extensionType = sequentialReader.getNullTerminatedString((int) (box.size - sequentialReader.getPosition()), Charsets.UTF_8);
                }
            }
            if (this.version == 1 && box.size - 28 >= 4) {
                this.contentEncoding = sequentialReader.getString(4);
            }
            if (this.version >= 2) {
                if (this.version == 2) {
                    this.itemID = sequentialReader.getUInt16();
                } else if (this.version == 3) {
                    this.itemID = sequentialReader.getUInt32();
                }
                this.itemProtectionIndex = sequentialReader.getUInt16();
                this.itemType = sequentialReader.getString(4);
                this.itemName = sequentialReader.getNullTerminatedString((int) (box.size - sequentialReader.getPosition()), Charsets.UTF_8);
                if (this.itemType.equals("mime")) {
                    this.contentType = sequentialReader.getNullTerminatedString((int) (box.size - sequentialReader.getPosition()), Charsets.UTF_8);
                    if (box.size - sequentialReader.getPosition() > 0) {
                        this.contentEncoding = sequentialReader.getNullTerminatedString((int) (box.size - sequentialReader.getPosition()), Charsets.UTF_8);
                        return;
                    }
                    return;
                }
                if (this.itemType.equals("uri ")) {
                    this.itemUriType = sequentialReader.getString((int) (box.size - sequentialReader.getPosition()));
                }
            }
        }
    }
}
