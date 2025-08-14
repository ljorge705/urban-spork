package com.drew.metadata.jpeg;

import com.drew.metadata.TagDescriptor;

/* loaded from: classes5.dex */
public class HuffmanTablesDescriptor extends TagDescriptor<HuffmanTablesDirectory> {
    public HuffmanTablesDescriptor(HuffmanTablesDirectory huffmanTablesDirectory) {
        super(huffmanTablesDirectory);
    }

    @Override // com.drew.metadata.TagDescriptor
    public String getDescription(int i) {
        if (i == 1) {
            return getNumberOfTablesDescription();
        }
        return super.getDescription(i);
    }

    public String getNumberOfTablesDescription() {
        Integer integer = ((HuffmanTablesDirectory) this._directory).getInteger(1);
        if (integer == null) {
            return null;
        }
        return integer + (integer.intValue() == 1 ? " Huffman table" : " Huffman tables");
    }
}
