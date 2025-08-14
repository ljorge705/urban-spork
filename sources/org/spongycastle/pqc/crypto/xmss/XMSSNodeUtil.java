package org.spongycastle.pqc.crypto.xmss;

import org.spongycastle.pqc.crypto.xmss.HashTreeAddress;
import org.spongycastle.pqc.crypto.xmss.LTreeAddress;

/* loaded from: classes7.dex */
class XMSSNodeUtil {
    XMSSNodeUtil() {
    }

    static XMSSNode lTree(WOTSPlus wOTSPlus, WOTSPlusPublicKeyParameters wOTSPlusPublicKeyParameters, LTreeAddress lTreeAddress) {
        double d;
        if (wOTSPlusPublicKeyParameters == null) {
            throw new NullPointerException("publicKey == null");
        }
        if (lTreeAddress == null) {
            throw new NullPointerException("address == null");
        }
        int len = wOTSPlus.getParams().getLen();
        byte[][] byteArray = wOTSPlusPublicKeyParameters.toByteArray();
        XMSSNode[] xMSSNodeArr = new XMSSNode[byteArray.length];
        for (int i = 0; i < byteArray.length; i++) {
            xMSSNodeArr[i] = new XMSSNode(0, byteArray[i]);
        }
        XMSSAddress xMSSAddressBuild = new LTreeAddress.Builder().withLayerAddress(lTreeAddress.getLayerAddress()).withTreeAddress(lTreeAddress.getTreeAddress()).withLTreeAddress(lTreeAddress.getLTreeAddress()).withTreeHeight(0).withTreeIndex(lTreeAddress.getTreeIndex()).withKeyAndMask(lTreeAddress.getKeyAndMask()).build();
        while (true) {
            LTreeAddress lTreeAddress2 = (LTreeAddress) xMSSAddressBuild;
            if (len > 1) {
                int i2 = 0;
                while (true) {
                    d = len / 2;
                    if (i2 >= ((int) Math.floor(d))) {
                        break;
                    }
                    lTreeAddress2 = (LTreeAddress) new LTreeAddress.Builder().withLayerAddress(lTreeAddress2.getLayerAddress()).withTreeAddress(lTreeAddress2.getTreeAddress()).withLTreeAddress(lTreeAddress2.getLTreeAddress()).withTreeHeight(lTreeAddress2.getTreeHeight()).withTreeIndex(i2).withKeyAndMask(lTreeAddress2.getKeyAndMask()).build();
                    int i3 = i2 * 2;
                    xMSSNodeArr[i2] = randomizeHash(wOTSPlus, xMSSNodeArr[i3], xMSSNodeArr[i3 + 1], lTreeAddress2);
                    i2++;
                }
                if (len % 2 == 1) {
                    xMSSNodeArr[(int) Math.floor(d)] = xMSSNodeArr[len - 1];
                }
                len = (int) Math.ceil(len / 2.0d);
                xMSSAddressBuild = new LTreeAddress.Builder().withLayerAddress(lTreeAddress2.getLayerAddress()).withTreeAddress(lTreeAddress2.getTreeAddress()).withLTreeAddress(lTreeAddress2.getLTreeAddress()).withTreeHeight(lTreeAddress2.getTreeHeight() + 1).withTreeIndex(lTreeAddress2.getTreeIndex()).withKeyAndMask(lTreeAddress2.getKeyAndMask()).build();
            } else {
                return xMSSNodeArr[0];
            }
        }
    }

    static XMSSNode randomizeHash(WOTSPlus wOTSPlus, XMSSNode xMSSNode, XMSSNode xMSSNode2, XMSSAddress xMSSAddress) {
        if (xMSSNode == null) {
            throw new NullPointerException("left == null");
        }
        if (xMSSNode2 == null) {
            throw new NullPointerException("right == null");
        }
        if (xMSSNode.getHeight() != xMSSNode2.getHeight()) {
            throw new IllegalStateException("height of both nodes must be equal");
        }
        if (xMSSAddress == null) {
            throw new NullPointerException("address == null");
        }
        byte[] publicSeed = wOTSPlus.getPublicSeed();
        if (xMSSAddress instanceof LTreeAddress) {
            LTreeAddress lTreeAddress = (LTreeAddress) xMSSAddress;
            xMSSAddress = (LTreeAddress) new LTreeAddress.Builder().withLayerAddress(lTreeAddress.getLayerAddress()).withTreeAddress(lTreeAddress.getTreeAddress()).withLTreeAddress(lTreeAddress.getLTreeAddress()).withTreeHeight(lTreeAddress.getTreeHeight()).withTreeIndex(lTreeAddress.getTreeIndex()).withKeyAndMask(0).build();
        } else if (xMSSAddress instanceof HashTreeAddress) {
            HashTreeAddress hashTreeAddress = (HashTreeAddress) xMSSAddress;
            xMSSAddress = (HashTreeAddress) new HashTreeAddress.Builder().withLayerAddress(hashTreeAddress.getLayerAddress()).withTreeAddress(hashTreeAddress.getTreeAddress()).withTreeHeight(hashTreeAddress.getTreeHeight()).withTreeIndex(hashTreeAddress.getTreeIndex()).withKeyAndMask(0).build();
        }
        byte[] bArrPRF = wOTSPlus.getKhf().PRF(publicSeed, xMSSAddress.toByteArray());
        if (xMSSAddress instanceof LTreeAddress) {
            LTreeAddress lTreeAddress2 = (LTreeAddress) xMSSAddress;
            xMSSAddress = (LTreeAddress) new LTreeAddress.Builder().withLayerAddress(lTreeAddress2.getLayerAddress()).withTreeAddress(lTreeAddress2.getTreeAddress()).withLTreeAddress(lTreeAddress2.getLTreeAddress()).withTreeHeight(lTreeAddress2.getTreeHeight()).withTreeIndex(lTreeAddress2.getTreeIndex()).withKeyAndMask(1).build();
        } else if (xMSSAddress instanceof HashTreeAddress) {
            HashTreeAddress hashTreeAddress2 = (HashTreeAddress) xMSSAddress;
            xMSSAddress = (HashTreeAddress) new HashTreeAddress.Builder().withLayerAddress(hashTreeAddress2.getLayerAddress()).withTreeAddress(hashTreeAddress2.getTreeAddress()).withTreeHeight(hashTreeAddress2.getTreeHeight()).withTreeIndex(hashTreeAddress2.getTreeIndex()).withKeyAndMask(1).build();
        }
        byte[] bArrPRF2 = wOTSPlus.getKhf().PRF(publicSeed, xMSSAddress.toByteArray());
        if (xMSSAddress instanceof LTreeAddress) {
            LTreeAddress lTreeAddress3 = (LTreeAddress) xMSSAddress;
            xMSSAddress = (LTreeAddress) new LTreeAddress.Builder().withLayerAddress(lTreeAddress3.getLayerAddress()).withTreeAddress(lTreeAddress3.getTreeAddress()).withLTreeAddress(lTreeAddress3.getLTreeAddress()).withTreeHeight(lTreeAddress3.getTreeHeight()).withTreeIndex(lTreeAddress3.getTreeIndex()).withKeyAndMask(2).build();
        } else if (xMSSAddress instanceof HashTreeAddress) {
            HashTreeAddress hashTreeAddress3 = (HashTreeAddress) xMSSAddress;
            xMSSAddress = (HashTreeAddress) new HashTreeAddress.Builder().withLayerAddress(hashTreeAddress3.getLayerAddress()).withTreeAddress(hashTreeAddress3.getTreeAddress()).withTreeHeight(hashTreeAddress3.getTreeHeight()).withTreeIndex(hashTreeAddress3.getTreeIndex()).withKeyAndMask(2).build();
        }
        byte[] bArrPRF3 = wOTSPlus.getKhf().PRF(publicSeed, xMSSAddress.toByteArray());
        int digestSize = wOTSPlus.getParams().getDigestSize();
        byte[] bArr = new byte[digestSize * 2];
        for (int i = 0; i < digestSize; i++) {
            bArr[i] = (byte) (xMSSNode.getValue()[i] ^ bArrPRF2[i]);
        }
        for (int i2 = 0; i2 < digestSize; i2++) {
            bArr[i2 + digestSize] = (byte) (xMSSNode2.getValue()[i2] ^ bArrPRF3[i2]);
        }
        return new XMSSNode(xMSSNode.getHeight(), wOTSPlus.getKhf().H(bArrPRF, bArr));
    }
}
