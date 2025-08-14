package com.reactnativecommunity.picker;

import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* loaded from: classes6.dex */
public class ReactPickerLocalData {
    private final int height;

    public int getHeight() {
        return this.height;
    }

    public int hashCode() {
        return this.height + 31;
    }

    public ReactPickerLocalData(int i) {
        this.height = i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.height == ((ReactPickerLocalData) obj).height;
    }

    public String toString() {
        return "RectPickerLocalData{height=" + this.height + AbstractJsonLexerKt.END_OBJ;
    }
}
