package org.spongycastle.asn1;

/* loaded from: classes4.dex */
public class OIDTokenizer {
    private int index = 0;
    private String oid;

    public boolean hasMoreTokens() {
        return this.index != -1;
    }

    public OIDTokenizer(String str) {
        this.oid = str;
    }

    public String nextToken() {
        int i = this.index;
        if (i == -1) {
            return null;
        }
        int iIndexOf = this.oid.indexOf(46, i);
        if (iIndexOf == -1) {
            String strSubstring = this.oid.substring(this.index);
            this.index = -1;
            return strSubstring;
        }
        String strSubstring2 = this.oid.substring(this.index, iIndexOf);
        this.index = iIndexOf + 1;
        return strSubstring2;
    }
}
