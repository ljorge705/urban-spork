package org.spongycastle.cert.dane;

import org.spongycastle.util.Selector;

/* loaded from: classes4.dex */
public class DANEEntrySelector implements Selector {
    private final String domainName;

    @Override // org.spongycastle.util.Selector
    public Object clone() {
        return this;
    }

    public String getDomainName() {
        return this.domainName;
    }

    DANEEntrySelector(String str) {
        this.domainName = str;
    }

    @Override // org.spongycastle.util.Selector
    public boolean match(Object obj) {
        return ((DANEEntry) obj).getDomainName().equals(this.domainName);
    }
}
