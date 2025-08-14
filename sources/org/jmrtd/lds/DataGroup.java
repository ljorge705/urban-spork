package org.jmrtd.lds;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes4.dex */
public abstract class DataGroup extends AbstractTaggedLDSFile {
    private static final long serialVersionUID = -4761360877353069639L;

    protected DataGroup(int i) {
        super(i);
    }

    protected DataGroup(int i, InputStream inputStream) throws IOException {
        super(i, inputStream);
    }

    @Override // org.jmrtd.lds.AbstractTaggedLDSFile
    public String toString() {
        return "DataGroup [" + Integer.toHexString(getTag()) + " (" + getLength() + ")]";
    }
}
