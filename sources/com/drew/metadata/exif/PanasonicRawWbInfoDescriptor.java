package com.drew.metadata.exif;

import com.drew.metadata.TagDescriptor;

/* loaded from: classes5.dex */
public class PanasonicRawWbInfoDescriptor extends TagDescriptor<PanasonicRawWbInfoDirectory> {
    public PanasonicRawWbInfoDescriptor(PanasonicRawWbInfoDirectory panasonicRawWbInfoDirectory) {
        super(panasonicRawWbInfoDirectory);
    }

    @Override // com.drew.metadata.TagDescriptor
    public String getDescription(int i) {
        if (i == 1 || i == 4 || i == 7 || i == 10 || i == 13 || i == 16 || i == 19) {
            return getWbTypeDescription(i);
        }
        return super.getDescription(i);
    }

    public String getWbTypeDescription(int i) {
        Integer integer = ((PanasonicRawWbInfoDirectory) this._directory).getInteger(i);
        if (integer == null) {
            return null;
        }
        return super.getLightSourceDescription(integer.shortValue());
    }
}
