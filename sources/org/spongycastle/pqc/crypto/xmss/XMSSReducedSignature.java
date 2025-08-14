package org.spongycastle.pqc.crypto.xmss;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class XMSSReducedSignature implements XMSSStoreableObjectInterface {
    private final List<XMSSNode> authPath;
    private final XMSSParameters params;
    private final WOTSPlusSignature wotsPlusSignature;

    public List<XMSSNode> getAuthPath() {
        return this.authPath;
    }

    public XMSSParameters getParams() {
        return this.params;
    }

    public WOTSPlusSignature getWOTSPlusSignature() {
        return this.wotsPlusSignature;
    }

    protected XMSSReducedSignature(Builder builder) {
        XMSSParameters xMSSParameters = builder.params;
        this.params = xMSSParameters;
        if (xMSSParameters == null) {
            throw new NullPointerException("params == null");
        }
        int digestSize = xMSSParameters.getDigestSize();
        int len = xMSSParameters.getWOTSPlus().getParams().getLen();
        int height = xMSSParameters.getHeight();
        byte[] bArr = builder.reducedSignature;
        if (bArr != null) {
            if (bArr.length != (len * digestSize) + (height * digestSize)) {
                throw new IllegalArgumentException("signature has wrong size");
            }
            byte[][] bArr2 = new byte[len][];
            int i = 0;
            for (int i2 = 0; i2 < len; i2++) {
                bArr2[i2] = XMSSUtil.extractBytesAtOffset(bArr, i, digestSize);
                i += digestSize;
            }
            this.wotsPlusSignature = new WOTSPlusSignature(this.params.getWOTSPlus().getParams(), bArr2);
            ArrayList arrayList = new ArrayList();
            for (int i3 = 0; i3 < height; i3++) {
                arrayList.add(new XMSSNode(i3, XMSSUtil.extractBytesAtOffset(bArr, i, digestSize)));
                i += digestSize;
            }
            this.authPath = arrayList;
            return;
        }
        WOTSPlusSignature wOTSPlusSignature = builder.wotsPlusSignature;
        if (wOTSPlusSignature != null) {
            this.wotsPlusSignature = wOTSPlusSignature;
        } else {
            this.wotsPlusSignature = new WOTSPlusSignature(xMSSParameters.getWOTSPlus().getParams(), (byte[][]) Array.newInstance((Class<?>) Byte.TYPE, len, digestSize));
        }
        List<XMSSNode> list = builder.authPath;
        if (list != null) {
            if (list.size() != height) {
                throw new IllegalArgumentException("size of authPath needs to be equal to height of tree");
            }
            this.authPath = list;
            return;
        }
        this.authPath = new ArrayList();
    }

    public static class Builder {
        private final XMSSParameters params;
        private WOTSPlusSignature wotsPlusSignature = null;
        private List<XMSSNode> authPath = null;
        private byte[] reducedSignature = null;

        public Builder withAuthPath(List<XMSSNode> list) {
            this.authPath = list;
            return this;
        }

        public Builder withWOTSPlusSignature(WOTSPlusSignature wOTSPlusSignature) {
            this.wotsPlusSignature = wOTSPlusSignature;
            return this;
        }

        public Builder(XMSSParameters xMSSParameters) {
            this.params = xMSSParameters;
        }

        public Builder withReducedSignature(byte[] bArr) {
            this.reducedSignature = XMSSUtil.cloneArray(bArr);
            return this;
        }

        public XMSSReducedSignature build() {
            return new XMSSReducedSignature(this);
        }
    }

    @Override // org.spongycastle.pqc.crypto.xmss.XMSSStoreableObjectInterface
    public byte[] toByteArray() {
        int digestSize = this.params.getDigestSize();
        byte[] bArr = new byte[(this.params.getWOTSPlus().getParams().getLen() * digestSize) + (this.params.getHeight() * digestSize)];
        int i = 0;
        for (byte[] bArr2 : this.wotsPlusSignature.toByteArray()) {
            XMSSUtil.copyBytesAtOffset(bArr, bArr2, i);
            i += digestSize;
        }
        for (int i2 = 0; i2 < this.authPath.size(); i2++) {
            XMSSUtil.copyBytesAtOffset(bArr, this.authPath.get(i2).getValue(), i);
            i += digestSize;
        }
        return bArr;
    }
}
