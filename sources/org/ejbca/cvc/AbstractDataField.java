package org.ejbca.cvc;

import java.io.DataOutputStream;
import java.io.IOException;

/* loaded from: classes4.dex */
public abstract class AbstractDataField extends CVCObject {
    private static final long serialVersionUID = 1;

    protected abstract byte[] getEncoded();

    protected abstract String valueAsText();

    public AbstractDataField(CVCTagEnum cVCTagEnum) {
        super(cVCTagEnum);
    }

    @Override // org.ejbca.cvc.CVCObject
    protected int encode(DataOutputStream dataOutputStream) throws IOException {
        int size = dataOutputStream.size();
        dataOutputStream.write(toByteArray(Integer.valueOf(getTag().getValue())));
        byte[] encoded = getEncoded();
        dataOutputStream.write(encodeLength(encoded.length));
        dataOutputStream.write(encoded);
        return dataOutputStream.size() - size;
    }

    @Override // org.ejbca.cvc.CVCObject
    public String getAsText(String str, boolean z) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(super.getAsText(str, z)).append(valueAsText());
        return stringBuffer.toString();
    }
}
