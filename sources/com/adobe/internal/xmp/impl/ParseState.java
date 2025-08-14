package com.adobe.internal.xmp.impl;

import com.adobe.internal.xmp.XMPException;

/* compiled from: ISO8601Converter.java */
/* loaded from: classes5.dex */
class ParseState {
    private int pos = 0;
    private String str;

    public int pos() {
        return this.pos;
    }

    public void skip() {
        this.pos++;
    }

    public ParseState(String str) {
        this.str = str;
    }

    public int length() {
        return this.str.length();
    }

    public boolean hasNext() {
        return this.pos < this.str.length();
    }

    public char ch(int i) {
        if (i < this.str.length()) {
            return this.str.charAt(i);
        }
        return (char) 0;
    }

    public char ch() {
        if (this.pos < this.str.length()) {
            return this.str.charAt(this.pos);
        }
        return (char) 0;
    }

    public int gatherInt(String str, int i) throws XMPException {
        char cCh = ch(this.pos);
        int i2 = 0;
        boolean z = false;
        while ('0' <= cCh && cCh <= '9') {
            i2 = (i2 * 10) + (cCh - '0');
            z = true;
            int i3 = this.pos + 1;
            this.pos = i3;
            cCh = ch(i3);
        }
        if (!z) {
            throw new XMPException(str, 5);
        }
        if (i2 > i) {
            return i;
        }
        if (i2 < 0) {
            return 0;
        }
        return i2;
    }
}
