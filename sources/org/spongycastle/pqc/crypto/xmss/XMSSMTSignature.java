package org.spongycastle.pqc.crypto.xmss;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.spongycastle.pqc.crypto.xmss.XMSSReducedSignature;

/* loaded from: classes7.dex */
public final class XMSSMTSignature implements XMSSStoreableObjectInterface {
    private final long index;
    private final XMSSMTParameters params;
    private final byte[] random;
    private final List<XMSSReducedSignature> reducedSignatures;

    public long getIndex() {
        return this.index;
    }

    public List<XMSSReducedSignature> getReducedSignatures() {
        return this.reducedSignatures;
    }

    private XMSSMTSignature(Builder builder) {
        XMSSMTParameters xMSSMTParameters = builder.params;
        this.params = xMSSMTParameters;
        if (xMSSMTParameters == null) {
            throw new NullPointerException("params == null");
        }
        int digestSize = xMSSMTParameters.getDigestSize();
        byte[] bArr = builder.signature;
        if (bArr != null) {
            int len = xMSSMTParameters.getWOTSPlus().getParams().getLen();
            int iCeil = (int) Math.ceil(xMSSMTParameters.getHeight() / 8.0d);
            int height = ((xMSSMTParameters.getHeight() / xMSSMTParameters.getLayers()) + len) * digestSize;
            if (bArr.length != iCeil + digestSize + (xMSSMTParameters.getLayers() * height)) {
                throw new IllegalArgumentException("signature has wrong size");
            }
            long jBytesToXBigEndian = XMSSUtil.bytesToXBigEndian(bArr, 0, iCeil);
            this.index = jBytesToXBigEndian;
            if (!XMSSUtil.isIndexValid(xMSSMTParameters.getHeight(), jBytesToXBigEndian)) {
                throw new IllegalArgumentException("index out of bounds");
            }
            this.random = XMSSUtil.extractBytesAtOffset(bArr, iCeil, digestSize);
            this.reducedSignatures = new ArrayList();
            for (int i = iCeil + digestSize; i < bArr.length; i += height) {
                this.reducedSignatures.add(new XMSSReducedSignature.Builder(this.params.getXMSSParameters()).withReducedSignature(XMSSUtil.extractBytesAtOffset(bArr, i, height)).build());
            }
            return;
        }
        this.index = builder.index;
        byte[] bArr2 = builder.random;
        if (bArr2 != null) {
            if (bArr2.length != digestSize) {
                throw new IllegalArgumentException("size of random needs to be equal to size of digest");
            }
            this.random = bArr2;
        } else {
            this.random = new byte[digestSize];
        }
        List<XMSSReducedSignature> list = builder.reducedSignatures;
        if (list != null) {
            this.reducedSignatures = list;
        } else {
            this.reducedSignatures = new ArrayList();
        }
    }

    public static class Builder {
        private final XMSSMTParameters params;
        private long index = 0;
        private byte[] random = null;
        private List<XMSSReducedSignature> reducedSignatures = null;
        private byte[] signature = null;

        public Builder withIndex(long j) {
            this.index = j;
            return this;
        }

        public Builder withReducedSignatures(List<XMSSReducedSignature> list) {
            this.reducedSignatures = list;
            return this;
        }

        public Builder withSignature(byte[] bArr) {
            this.signature = bArr;
            return this;
        }

        public Builder(XMSSMTParameters xMSSMTParameters) {
            this.params = xMSSMTParameters;
        }

        public Builder withRandom(byte[] bArr) {
            this.random = XMSSUtil.cloneArray(bArr);
            return this;
        }

        public XMSSMTSignature build() {
            return new XMSSMTSignature(this);
        }
    }

    @Override // org.spongycastle.pqc.crypto.xmss.XMSSStoreableObjectInterface
    public byte[] toByteArray() {
        int digestSize = this.params.getDigestSize();
        int len = this.params.getWOTSPlus().getParams().getLen();
        int iCeil = (int) Math.ceil(this.params.getHeight() / 8.0d);
        int height = ((this.params.getHeight() / this.params.getLayers()) + len) * digestSize;
        byte[] bArr = new byte[iCeil + digestSize + (this.params.getLayers() * height)];
        XMSSUtil.copyBytesAtOffset(bArr, XMSSUtil.toBytesBigEndian(this.index, iCeil), 0);
        XMSSUtil.copyBytesAtOffset(bArr, this.random, iCeil);
        int i = iCeil + digestSize;
        Iterator<XMSSReducedSignature> it = this.reducedSignatures.iterator();
        while (it.hasNext()) {
            XMSSUtil.copyBytesAtOffset(bArr, it.next().toByteArray(), i);
            i += height;
        }
        return bArr;
    }

    public byte[] getRandom() {
        return XMSSUtil.cloneArray(this.random);
    }
}
