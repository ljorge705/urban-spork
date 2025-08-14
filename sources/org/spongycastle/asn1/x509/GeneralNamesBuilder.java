package org.spongycastle.asn1.x509;

import java.util.Vector;

/* loaded from: classes4.dex */
public class GeneralNamesBuilder {
    private Vector names = new Vector();

    public GeneralNamesBuilder addNames(GeneralNames generalNames) {
        GeneralName[] names = generalNames.getNames();
        for (int i = 0; i != names.length; i++) {
            this.names.addElement(names[i]);
        }
        return this;
    }

    public GeneralNamesBuilder addName(GeneralName generalName) {
        this.names.addElement(generalName);
        return this;
    }

    public GeneralNames build() {
        int size = this.names.size();
        GeneralName[] generalNameArr = new GeneralName[size];
        for (int i = 0; i != size; i++) {
            generalNameArr[i] = (GeneralName) this.names.elementAt(i);
        }
        return new GeneralNames(generalNameArr);
    }
}
