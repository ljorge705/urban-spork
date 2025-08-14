package com.adobe.internal.xmp.impl;

/* loaded from: classes5.dex */
public class QName {
    private String localName;
    private String prefix;

    public String getLocalName() {
        return this.localName;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public QName(String str) {
        int iIndexOf = str.indexOf(58);
        if (iIndexOf >= 0) {
            this.prefix = str.substring(0, iIndexOf);
            this.localName = str.substring(iIndexOf + 1);
        } else {
            this.prefix = "";
            this.localName = str;
        }
    }

    public QName(String str, String str2) {
        this.prefix = str;
        this.localName = str2;
    }

    public boolean hasPrefix() {
        String str = this.prefix;
        return str != null && str.length() > 0;
    }
}
