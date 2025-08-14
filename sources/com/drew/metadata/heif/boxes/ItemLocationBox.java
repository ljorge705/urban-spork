package com.drew.metadata.heif.boxes;

import com.drew.lang.SequentialReader;
import java.io.IOException;

/* loaded from: classes5.dex */
public class ItemLocationBox extends FullBox {
    byte[] baseOffset;
    int baseOffsetSize;
    int constructionMethod;
    int dataReferenceIndex;
    int extentCount;
    Extent[] extents;
    int indexSize;
    long itemCount;
    long itemID;
    int lengthSize;
    int offsetSize;

    public ItemLocationBox(SequentialReader sequentialReader, Box box) throws IOException {
        super(sequentialReader, box);
        short uInt8 = sequentialReader.getUInt8();
        this.offsetSize = (uInt8 & 240) >> 4;
        this.lengthSize = uInt8 & 15;
        short uInt82 = sequentialReader.getUInt8();
        this.baseOffsetSize = (uInt82 & 240) >> 4;
        if (this.version == 1 || this.version == 2) {
            this.indexSize = uInt82 & 15;
        }
        if (this.version < 2) {
            this.itemCount = sequentialReader.getUInt16();
        } else if (this.version == 2) {
            this.itemCount = sequentialReader.getUInt32();
        }
        for (int i = 0; i < this.itemCount; i++) {
            if (this.version < 2) {
                this.itemID = sequentialReader.getUInt16();
            } else if (this.version == 2) {
                this.itemID = sequentialReader.getUInt32();
            }
            if (this.version == 1 || this.version == 2) {
                this.constructionMethod = sequentialReader.getUInt16() & 15;
            }
            this.dataReferenceIndex = sequentialReader.getUInt16();
            this.baseOffset = sequentialReader.getBytes(this.baseOffsetSize);
            int uInt16 = sequentialReader.getUInt16();
            this.extentCount = uInt16;
            this.extents = new Extent[uInt16];
            int i2 = 0;
            Long intFromUnknownByte = null;
            while (i2 < this.extentCount) {
                if (this.version == 1 || (this.version == 2 && this.indexSize > 0)) {
                    intFromUnknownByte = getIntFromUnknownByte(this.indexSize, sequentialReader);
                }
                Long l = intFromUnknownByte;
                this.extents[i2] = new Extent(l == null ? null : l, getIntFromUnknownByte(this.offsetSize, sequentialReader).longValue(), getIntFromUnknownByte(this.lengthSize, sequentialReader).longValue());
                i2++;
                intFromUnknownByte = l;
            }
        }
    }

    public Long getIntFromUnknownByte(int i, SequentialReader sequentialReader) throws IOException {
        if (i == 1) {
            return Long.valueOf(sequentialReader.getUInt8());
        }
        if (i == 2) {
            return Long.valueOf(sequentialReader.getUInt16());
        }
        if (i == 4) {
            return Long.valueOf(sequentialReader.getUInt32());
        }
        if (i != 8) {
            return null;
        }
        return Long.valueOf(sequentialReader.getInt64());
    }

    class Extent {
        Long index;
        long length;
        long offset;

        public Extent(Long l, long j, long j2) {
            this.index = l;
            this.offset = j;
            this.length = j2;
        }
    }
}
