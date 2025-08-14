package org.spongycastle.pqc.crypto.xmss;

import org.spongycastle.pqc.crypto.xmss.XMSSAddress;
import org.spongycastle.util.Pack;

/* loaded from: classes7.dex */
final class LTreeAddress extends XMSSAddress {
    private static final int TYPE = 1;
    private final int lTreeAddress;
    private final int treeHeight;
    private final int treeIndex;

    protected int getLTreeAddress() {
        return this.lTreeAddress;
    }

    protected int getTreeHeight() {
        return this.treeHeight;
    }

    protected int getTreeIndex() {
        return this.treeIndex;
    }

    private LTreeAddress(Builder builder) {
        super(builder);
        this.lTreeAddress = builder.lTreeAddress;
        this.treeHeight = builder.treeHeight;
        this.treeIndex = builder.treeIndex;
    }

    protected static class Builder extends XMSSAddress.Builder<Builder> {
        private int lTreeAddress;
        private int treeHeight;
        private int treeIndex;

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.spongycastle.pqc.crypto.xmss.XMSSAddress.Builder
        public Builder getThis() {
            return this;
        }

        protected Builder withLTreeAddress(int i) {
            this.lTreeAddress = i;
            return this;
        }

        protected Builder withTreeHeight(int i) {
            this.treeHeight = i;
            return this;
        }

        protected Builder withTreeIndex(int i) {
            this.treeIndex = i;
            return this;
        }

        protected Builder() {
            super(1);
            this.lTreeAddress = 0;
            this.treeHeight = 0;
            this.treeIndex = 0;
        }

        @Override // org.spongycastle.pqc.crypto.xmss.XMSSAddress.Builder
        protected XMSSAddress build() {
            return new LTreeAddress(this);
        }
    }

    @Override // org.spongycastle.pqc.crypto.xmss.XMSSAddress
    protected byte[] toByteArray() {
        byte[] byteArray = super.toByteArray();
        Pack.intToBigEndian(this.lTreeAddress, byteArray, 16);
        Pack.intToBigEndian(this.treeHeight, byteArray, 20);
        Pack.intToBigEndian(this.treeIndex, byteArray, 24);
        return byteArray;
    }
}
