package com.drew.metadata.jfxx;

import com.drew.metadata.TagDescriptor;

/* loaded from: classes5.dex */
public class JfxxDescriptor extends TagDescriptor<JfxxDirectory> {
    public JfxxDescriptor(JfxxDirectory jfxxDirectory) {
        super(jfxxDirectory);
    }

    @Override // com.drew.metadata.TagDescriptor
    public String getDescription(int i) {
        if (i == 5) {
            return getExtensionCodeDescription();
        }
        return super.getDescription(i);
    }

    public String getExtensionCodeDescription() {
        Integer integer = ((JfxxDirectory) this._directory).getInteger(5);
        if (integer == null) {
            return null;
        }
        int iIntValue = integer.intValue();
        return iIntValue != 16 ? iIntValue != 17 ? iIntValue != 19 ? "Unknown extension code " + integer : "Thumbnail stored using 3 bytes/pixel" : "Thumbnail stored using 1 byte/pixel" : "Thumbnail coded using JPEG";
    }
}
