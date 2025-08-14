package com.drew.metadata.exif;

import com.drew.metadata.TagDescriptor;

/* loaded from: classes5.dex */
public class PrintIMDescriptor extends TagDescriptor<PrintIMDirectory> {
    public PrintIMDescriptor(PrintIMDirectory printIMDirectory) {
        super(printIMDirectory);
    }

    @Override // com.drew.metadata.TagDescriptor
    public String getDescription(int i) {
        if (i == 0) {
            return super.getDescription(i);
        }
        Integer integer = ((PrintIMDirectory) this._directory).getInteger(i);
        if (integer == null) {
            return null;
        }
        return String.format("0x%08x", integer);
    }
}
