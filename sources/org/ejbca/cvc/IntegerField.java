package org.ejbca.cvc;

import java.math.BigInteger;

/* loaded from: classes4.dex */
public class IntegerField extends AbstractDataField {
    private static final long serialVersionUID = 1;
    private int intValue;

    public int getValue() {
        return this.intValue;
    }

    public void setValue(int i) {
        this.intValue = i;
    }

    IntegerField(CVCTagEnum cVCTagEnum, int i) {
        super(cVCTagEnum);
        this.intValue = i;
    }

    IntegerField(CVCTagEnum cVCTagEnum, byte[] bArr) {
        super(cVCTagEnum);
        if (bArr != null && bArr.length > 4) {
            throw new IllegalArgumentException("Byte array too long, max is 4, was " + bArr.length);
        }
        this.intValue = new BigInteger(1, bArr).intValue();
    }

    @Override // org.ejbca.cvc.AbstractDataField
    protected byte[] getEncoded() {
        return toByteArray(Integer.valueOf(this.intValue));
    }

    @Override // org.ejbca.cvc.AbstractDataField
    protected String valueAsText() {
        return "" + this.intValue;
    }
}
