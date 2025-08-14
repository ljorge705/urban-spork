package org.spongycastle.pqc.crypto.xmss;

import java.io.Serializable;

/* loaded from: classes7.dex */
public final class XMSSNode implements Serializable {
    private static final long serialVersionUID = 1;
    private final int height;
    private final byte[] value;

    public int getHeight() {
        return this.height;
    }

    protected XMSSNode(int i, byte[] bArr) {
        this.height = i;
        this.value = bArr;
    }

    public byte[] getValue() {
        return XMSSUtil.cloneArray(this.value);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public XMSSNode clone() {
        return new XMSSNode(getHeight(), getValue());
    }
}
