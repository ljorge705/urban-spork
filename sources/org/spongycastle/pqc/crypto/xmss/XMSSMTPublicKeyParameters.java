package org.spongycastle.pqc.crypto.xmss;

import org.spongycastle.crypto.params.AsymmetricKeyParameter;

/* loaded from: classes7.dex */
public final class XMSSMTPublicKeyParameters extends AsymmetricKeyParameter implements XMSSStoreableObjectInterface {
    private final XMSSMTParameters params;
    private final byte[] publicSeed;
    private final byte[] root;

    public XMSSMTParameters getParameters() {
        return this.params;
    }

    private XMSSMTPublicKeyParameters(Builder builder) {
        super(false);
        XMSSMTParameters xMSSMTParameters = builder.params;
        this.params = xMSSMTParameters;
        if (xMSSMTParameters == null) {
            throw new NullPointerException("params == null");
        }
        int digestSize = xMSSMTParameters.getDigestSize();
        byte[] bArr = builder.publicKey;
        if (bArr != null) {
            if (bArr.length != digestSize + digestSize) {
                throw new IllegalArgumentException("public key has wrong size");
            }
            this.root = XMSSUtil.extractBytesAtOffset(bArr, 0, digestSize);
            this.publicSeed = XMSSUtil.extractBytesAtOffset(bArr, digestSize, digestSize);
            return;
        }
        byte[] bArr2 = builder.root;
        if (bArr2 != null) {
            if (bArr2.length != digestSize) {
                throw new IllegalArgumentException("length of root must be equal to length of digest");
            }
            this.root = bArr2;
        } else {
            this.root = new byte[digestSize];
        }
        byte[] bArr3 = builder.publicSeed;
        if (bArr3 != null) {
            if (bArr3.length != digestSize) {
                throw new IllegalArgumentException("length of publicSeed must be equal to length of digest");
            }
            this.publicSeed = bArr3;
            return;
        }
        this.publicSeed = new byte[digestSize];
    }

    public static class Builder {
        private final XMSSMTParameters params;
        private byte[] root = null;
        private byte[] publicSeed = null;
        private byte[] publicKey = null;

        public Builder(XMSSMTParameters xMSSMTParameters) {
            this.params = xMSSMTParameters;
        }

        public Builder withRoot(byte[] bArr) {
            this.root = XMSSUtil.cloneArray(bArr);
            return this;
        }

        public Builder withPublicSeed(byte[] bArr) {
            this.publicSeed = XMSSUtil.cloneArray(bArr);
            return this;
        }

        public Builder withPublicKey(byte[] bArr) {
            this.publicKey = XMSSUtil.cloneArray(bArr);
            return this;
        }

        public XMSSMTPublicKeyParameters build() {
            return new XMSSMTPublicKeyParameters(this);
        }
    }

    @Override // org.spongycastle.pqc.crypto.xmss.XMSSStoreableObjectInterface
    public byte[] toByteArray() {
        int digestSize = this.params.getDigestSize();
        byte[] bArr = new byte[digestSize + digestSize];
        XMSSUtil.copyBytesAtOffset(bArr, this.root, 0);
        XMSSUtil.copyBytesAtOffset(bArr, this.publicSeed, digestSize);
        return bArr;
    }

    public byte[] getRoot() {
        return XMSSUtil.cloneArray(this.root);
    }

    public byte[] getPublicSeed() {
        return XMSSUtil.cloneArray(this.publicSeed);
    }
}
