package org.spongycastle.dvcs;

import org.spongycastle.asn1.dvcs.Data;

/* loaded from: classes7.dex */
public abstract class DVCSRequestData {
    protected Data data;

    public Data toASN1Structure() {
        return this.data;
    }

    protected DVCSRequestData(Data data) {
        this.data = data;
    }
}
