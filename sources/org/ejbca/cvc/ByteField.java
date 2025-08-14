package org.ejbca.cvc;

import java.math.BigInteger;
import org.ejbca.cvc.util.StringConverter;

/* loaded from: classes4.dex */
public class ByteField extends AbstractDataField {
    private static final long serialVersionUID = 1;
    private byte[] data;
    private boolean showBitLength;

    public byte[] getData() {
        return this.data;
    }

    @Override // org.ejbca.cvc.AbstractDataField
    protected byte[] getEncoded() {
        return this.data;
    }

    public boolean isShowBitLength() {
        return this.showBitLength;
    }

    public void setShowBitLength(boolean z) {
        this.showBitLength = z;
    }

    ByteField(CVCTagEnum cVCTagEnum) {
        super(cVCTagEnum);
        this.showBitLength = false;
    }

    ByteField(CVCTagEnum cVCTagEnum, byte[] bArr) {
        this(cVCTagEnum, bArr, false);
    }

    ByteField(CVCTagEnum cVCTagEnum, byte[] bArr, boolean z) {
        super(cVCTagEnum);
        this.data = bArr;
        this.showBitLength = z;
    }

    @Override // org.ejbca.cvc.AbstractDataField
    protected String valueAsText() {
        String str;
        if (this.showBitLength) {
            str = "[" + (this.data != null ? new BigInteger(1, this.data).bitLength() : 0) + "]  ";
        } else {
            str = "";
        }
        return str + StringConverter.byteToHex(this.data);
    }
}
