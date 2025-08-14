package com.drew.metadata.jfif;

import com.drew.metadata.TagDescriptor;

/* loaded from: classes5.dex */
public class JfifDescriptor extends TagDescriptor<JfifDirectory> {
    public JfifDescriptor(JfifDirectory jfifDirectory) {
        super(jfifDirectory);
    }

    @Override // com.drew.metadata.TagDescriptor
    public String getDescription(int i) {
        if (i == 5) {
            return getImageVersionDescription();
        }
        if (i == 10) {
            return getImageResYDescription();
        }
        if (i == 7) {
            return getImageResUnitsDescription();
        }
        if (i == 8) {
            return getImageResXDescription();
        }
        return super.getDescription(i);
    }

    public String getImageVersionDescription() {
        Integer integer = ((JfifDirectory) this._directory).getInteger(5);
        if (integer == null) {
            return null;
        }
        return String.format("%d.%d", Integer.valueOf((integer.intValue() & 65280) >> 8), Integer.valueOf(integer.intValue() & 255));
    }

    public String getImageResYDescription() {
        Integer integer = ((JfifDirectory) this._directory).getInteger(10);
        if (integer == null) {
            return null;
        }
        Object[] objArr = new Object[2];
        objArr[0] = integer;
        objArr[1] = integer.intValue() == 1 ? "" : "s";
        return String.format("%d dot%s", objArr);
    }

    public String getImageResXDescription() {
        Integer integer = ((JfifDirectory) this._directory).getInteger(8);
        if (integer == null) {
            return null;
        }
        Object[] objArr = new Object[2];
        objArr[0] = integer;
        objArr[1] = integer.intValue() == 1 ? "" : "s";
        return String.format("%d dot%s", objArr);
    }

    public String getImageResUnitsDescription() {
        Integer integer = ((JfifDirectory) this._directory).getInteger(7);
        if (integer == null) {
            return null;
        }
        int iIntValue = integer.intValue();
        return iIntValue != 0 ? iIntValue != 1 ? iIntValue != 2 ? "unit" : "centimetre" : "inch" : "none";
    }
}
