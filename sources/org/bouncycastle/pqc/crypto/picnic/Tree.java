package org.bouncycastle.pqc.crypto.picnic;

import java.lang.reflect.Array;
import java.util.logging.Logger;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

/* loaded from: classes4.dex */
class Tree {
    private static final Logger LOG = Logger.getLogger(Tree.class.getName());
    private static final int MAX_SEED_SIZE_BYTES = 32;
    private int dataSize;
    private int depth;
    private PicnicEngine engine;
    private boolean[] exists;
    private boolean[] haveNode;
    byte[][] nodes;
    private int numLeaves;
    private int numNodes;

    public Tree(PicnicEngine picnicEngine, int i, int i2) {
        int i3;
        this.engine = picnicEngine;
        int iCeil_log2 = Utils.ceil_log2(i);
        int i4 = iCeil_log2 + 1;
        this.depth = i4;
        int i5 = ((1 << i4) - 1) - ((1 << iCeil_log2) - i);
        this.numNodes = i5;
        this.numLeaves = i;
        this.dataSize = i2;
        this.nodes = (byte[][]) Array.newInstance((Class<?>) Byte.TYPE, i5, i2);
        int i6 = 0;
        while (true) {
            i3 = this.numNodes;
            if (i6 >= i3) {
                break;
            }
            this.nodes[i6] = new byte[i2];
            i6++;
        }
        this.haveNode = new boolean[i3];
        boolean[] zArr = new boolean[i3];
        this.exists = zArr;
        Arrays.fill(zArr, i3 - this.numLeaves, i3, true);
        for (int i7 = this.numNodes - this.numLeaves; i7 > 0; i7--) {
            int i8 = i7 * 2;
            if (exists(i8 + 1) || exists(i8 + 2)) {
                this.exists[i7] = true;
            }
        }
        this.exists[0] = true;
    }

    private void computeParentHash(int i, byte[] bArr) {
        if (exists(i)) {
            int parent = getParent(i);
            boolean[] zArr = this.haveNode;
            if (zArr[parent]) {
                return;
            }
            int i2 = parent * 2;
            int i3 = i2 + 1;
            if (zArr[i3]) {
                int i4 = i2 + 2;
                if (!exists(i4) || this.haveNode[i4]) {
                    this.engine.digest.update((byte) 3);
                    this.engine.digest.update(this.nodes[i3], 0, this.engine.digestSizeBytes);
                    if (hasRightChild(parent)) {
                        this.engine.digest.update(this.nodes[i4], 0, this.engine.digestSizeBytes);
                    }
                    this.engine.digest.update(bArr, 0, 32);
                    this.engine.digest.update(Pack.intToLittleEndian(parent), 0, 2);
                    this.engine.digest.doFinal(this.nodes[parent], 0, this.engine.digestSizeBytes);
                    this.haveNode[parent] = true;
                }
            }
        }
    }

    private boolean contains(int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i; i3++) {
            if (iArr[i3] == i2) {
                return true;
            }
        }
        return false;
    }

    private boolean exists(int i) {
        if (i >= this.numNodes) {
            return false;
        }
        return this.exists[i];
    }

    private void expandSeeds(byte[] bArr, int i) {
        byte[] bArr2 = new byte[64];
        int parent = getParent(this.numNodes - 1);
        for (int i2 = 0; i2 <= parent; i2++) {
            if (this.haveNode[i2]) {
                hashSeed(bArr2, this.nodes[i2], bArr, (byte) 1, i, i2);
                int i3 = i2 * 2;
                int i4 = i3 + 1;
                if (!this.haveNode[i4]) {
                    System.arraycopy(bArr2, 0, this.nodes[i4], 0, this.engine.seedSizeBytes);
                    this.haveNode[i4] = true;
                }
                int i5 = i3 + 2;
                if (exists(i5) && !this.haveNode[i5]) {
                    System.arraycopy(bArr2, this.engine.seedSizeBytes, this.nodes[i5], 0, this.engine.seedSizeBytes);
                    this.haveNode[i5] = true;
                }
            }
        }
    }

    private int getParent(int i) {
        return (isLeftChild(i) ? i - 1 : i - 2) / 2;
    }

    private int[] getRevealedMerkleNodes(int[] iArr, int i, int[] iArr2) {
        int i2 = this.numNodes;
        int i3 = i2 - this.numLeaves;
        boolean[] zArr = new boolean[i2];
        for (int i4 = 0; i4 < i; i4++) {
            zArr[iArr[i4] + i3] = true;
        }
        for (int parent = getParent(this.numNodes - 1); parent > 0; parent--) {
            if (exists(parent)) {
                int i5 = parent * 2;
                int i6 = i5 + 2;
                int i7 = i5 + 1;
                if (exists(i6)) {
                    if (zArr[i7] && zArr[i6]) {
                        zArr[parent] = true;
                    }
                } else if (zArr[i7]) {
                    zArr[parent] = true;
                }
            }
        }
        int[] iArr3 = new int[this.numLeaves];
        int i8 = 0;
        for (int i9 = 0; i9 < i; i9++) {
            int parent2 = iArr[i9] + i3;
            while (true) {
                if (zArr[getParent(parent2)]) {
                    parent2 = getParent(parent2);
                    if (parent2 == 0) {
                        break;
                    }
                } else if (!contains(iArr3, i8, parent2)) {
                    iArr3[i8] = parent2;
                    i8++;
                }
            }
        }
        iArr2[0] = i8;
        return iArr3;
    }

    private int[] getRevealedNodes(int[] iArr, int i, int[] iArr2) {
        int i2 = this.depth - 1;
        int[][] iArr3 = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, i2, i);
        for (int i3 = 0; i3 < i; i3++) {
            int parent = iArr[i3] + (this.numNodes - this.numLeaves);
            iArr3[0][i3] = parent;
            int i4 = 1;
            while (true) {
                parent = getParent(parent);
                if (parent != 0) {
                    iArr3[i4][i3] = parent;
                    i4++;
                }
            }
        }
        int[] iArr4 = new int[this.numLeaves];
        int i5 = 0;
        for (int i6 = 0; i6 < i2; i6++) {
            for (int i7 = 0; i7 < i; i7++) {
                if (hasSibling(iArr3[i6][i7])) {
                    int sibling = getSibling(iArr3[i6][i7]);
                    if (!contains(iArr3[i6], i, sibling)) {
                        while (!hasRightChild(sibling) && !isLeafNode(sibling)) {
                            sibling = (sibling * 2) + 1;
                        }
                        if (!contains(iArr4, i5, sibling)) {
                            iArr4[i5] = sibling;
                            i5++;
                        }
                    }
                }
            }
        }
        iArr2[0] = i5;
        return iArr4;
    }

    private int getSibling(int i) {
        if (!isLeftChild(i)) {
            return i - 1;
        }
        int i2 = i + 1;
        if (i2 < this.numNodes) {
            return i2;
        }
        LOG.fine("getSibling: request for node with not sibling");
        return 0;
    }

    private boolean hasRightChild(int i) {
        return (i * 2) + 2 < this.numNodes && exists(i);
    }

    private boolean hasSibling(int i) {
        if (exists(i)) {
            return !isLeftChild(i) || exists(i + 1);
        }
        return false;
    }

    private void hashSeed(byte[] bArr, byte[] bArr2, byte[] bArr3, byte b, int i, int i2) {
        this.engine.digest.update(b);
        this.engine.digest.update(bArr2, 0, this.engine.seedSizeBytes);
        this.engine.digest.update(bArr3, 0, 32);
        this.engine.digest.update(Pack.shortToLittleEndian((short) (i & 65535)), 0, 2);
        this.engine.digest.update(Pack.shortToLittleEndian((short) (65535 & i2)), 0, 2);
        this.engine.digest.doFinal(bArr, 0, this.engine.seedSizeBytes * 2);
    }

    private boolean isLeafNode(int i) {
        return (i * 2) + 1 >= this.numNodes;
    }

    private boolean isLeftChild(int i) {
        return i % 2 == 1;
    }

    protected int addMerkleNodes(int[] iArr, int i, byte[] bArr, int i2) {
        int[] iArr2 = {0};
        int[] revealedMerkleNodes = getRevealedMerkleNodes(iArr, i, iArr2);
        for (int i3 = 0; i3 < iArr2[0]; i3++) {
            int i4 = this.dataSize;
            i2 -= i4;
            if (i2 < 0) {
                return -1;
            }
            System.arraycopy(bArr, i3 * i4, this.nodes[revealedMerkleNodes[i3]], 0, i4);
            this.haveNode[revealedMerkleNodes[i3]] = true;
        }
        return i2 != 0 ? -1 : 0;
    }

    protected void buildMerkleTree(byte[][] bArr, byte[] bArr2) {
        int i = this.numNodes - this.numLeaves;
        for (int i2 = 0; i2 < this.numLeaves; i2++) {
            byte[] bArr3 = bArr[i2];
            if (bArr3 != null) {
                int i3 = i + i2;
                System.arraycopy(bArr3, 0, this.nodes[i3], 0, this.dataSize);
                this.haveNode[i3] = true;
            }
        }
        for (int i4 = this.numNodes; i4 > 0; i4--) {
            computeParentHash(i4, bArr2);
        }
    }

    protected void generateSeeds(byte[] bArr, byte[] bArr2, int i) {
        this.nodes[0] = bArr;
        this.haveNode[0] = true;
        expandSeeds(bArr2, i);
    }

    protected byte[] getLeaf(int i) {
        return this.nodes[(this.numNodes - this.numLeaves) + i];
    }

    protected byte[][] getLeaves() {
        return this.nodes;
    }

    protected int getLeavesOffset() {
        return this.numNodes - this.numLeaves;
    }

    boolean hasLeftChild(Tree tree, int i) {
        return (i * 2) + 1 < this.numNodes;
    }

    protected byte[] openMerkleTree(int[] iArr, int i, int[] iArr2) {
        int[] iArr3 = new int[1];
        int[] revealedMerkleNodes = getRevealedMerkleNodes(iArr, i, iArr3);
        int i2 = iArr3[0] * this.dataSize;
        iArr2[0] = i2;
        byte[] bArr = new byte[i2];
        for (int i3 = 0; i3 < iArr3[0]; i3++) {
            byte[] bArr2 = this.nodes[revealedMerkleNodes[i3]];
            int i4 = this.dataSize;
            System.arraycopy(bArr2, 0, bArr, i3 * i4, i4);
        }
        return bArr;
    }

    protected int openMerkleTreeSize(int[] iArr, int i) {
        int[] iArr2 = new int[1];
        getRevealedMerkleNodes(iArr, i, iArr2);
        return iArr2[0] * this.engine.digestSizeBytes;
    }

    protected int reconstructSeeds(int[] iArr, int i, byte[] bArr, int i2, byte[] bArr2, int i3) {
        int[] iArr2 = {0};
        int[] revealedNodes = getRevealedNodes(iArr, i, iArr2);
        for (int i4 = 0; i4 < iArr2[0]; i4++) {
            i2 -= this.engine.seedSizeBytes;
            if (i2 < 0) {
                return -1;
            }
            System.arraycopy(bArr, this.engine.seedSizeBytes * i4, this.nodes[revealedNodes[i4]], 0, this.engine.seedSizeBytes);
            this.haveNode[revealedNodes[i4]] = true;
        }
        expandSeeds(bArr2, i3);
        return 0;
    }

    protected int revealSeeds(int[] iArr, int i, byte[] bArr, int i2) {
        int[] iArr2 = {0};
        int[] revealedNodes = getRevealedNodes(iArr, i, iArr2);
        for (int i3 = 0; i3 < iArr2[0]; i3++) {
            i2 -= this.engine.seedSizeBytes;
            if (i2 < 0) {
                LOG.fine("Insufficient sized buffer provided to revealSeeds");
                return 0;
            }
            System.arraycopy(this.nodes[revealedNodes[i3]], 0, bArr, this.engine.seedSizeBytes * i3, this.engine.seedSizeBytes);
        }
        return bArr.length - i2;
    }

    protected int revealSeedsSize(int[] iArr, int i) {
        int[] iArr2 = {0};
        getRevealedNodes(iArr, i, iArr2);
        return iArr2[0] * this.engine.seedSizeBytes;
    }

    protected int verifyMerkleTree(byte[][] bArr, byte[] bArr2) {
        int i = this.numNodes - this.numLeaves;
        for (int i2 = 0; i2 < this.numLeaves; i2++) {
            byte[] bArr3 = bArr[i2];
            if (bArr3 != null) {
                int i3 = i + i2;
                if (this.haveNode[i3]) {
                    return -1;
                }
                if (bArr3 != null) {
                    System.arraycopy(bArr3, 0, this.nodes[i3], 0, this.dataSize);
                    this.haveNode[i3] = true;
                }
            }
        }
        for (int i4 = this.numNodes; i4 > 0; i4--) {
            computeParentHash(i4, bArr2);
        }
        return !this.haveNode[0] ? -1 : 0;
    }
}
