package org.bouncycastle.pqc.crypto.xmss;

import java.util.ArrayList;
import java.util.List;
import org.bouncycastle.pqc.crypto.xmss.OTSHashAddress;
import org.bouncycastle.util.Arrays;

/* loaded from: classes4.dex */
final class WOTSPlus {
    private final KeyedHashFunctions khf;
    private final WOTSPlusParameters params;
    private byte[] publicSeed;
    private byte[] secretKeySeed;

    WOTSPlus(WOTSPlusParameters wOTSPlusParameters) {
        if (wOTSPlusParameters == null) {
            throw new NullPointerException("params == null");
        }
        this.params = wOTSPlusParameters;
        int treeDigestSize = wOTSPlusParameters.getTreeDigestSize();
        this.khf = new KeyedHashFunctions(wOTSPlusParameters.getTreeDigest(), treeDigestSize);
        this.secretKeySeed = new byte[treeDigestSize];
        this.publicSeed = new byte[treeDigestSize];
    }

    private byte[] chain(byte[] bArr, int i, int i2, OTSHashAddress oTSHashAddress) {
        int treeDigestSize = this.params.getTreeDigestSize();
        if (bArr == null) {
            throw new NullPointerException("startHash == null");
        }
        if (bArr.length != treeDigestSize) {
            throw new IllegalArgumentException("startHash needs to be " + treeDigestSize + "bytes");
        }
        if (oTSHashAddress == null) {
            throw new NullPointerException("otsHashAddress == null");
        }
        if (oTSHashAddress.toByteArray() == null) {
            throw new NullPointerException("otsHashAddress byte array == null");
        }
        int i3 = i + i2;
        if (i3 > this.params.getWinternitzParameter() - 1) {
            throw new IllegalArgumentException("max chain length must not be greater than w");
        }
        if (i2 == 0) {
            return bArr;
        }
        byte[] bArrChain = chain(bArr, i, i2 - 1, oTSHashAddress);
        OTSHashAddress oTSHashAddress2 = (OTSHashAddress) new OTSHashAddress.Builder().withLayerAddress(oTSHashAddress.getLayerAddress()).withTreeAddress(oTSHashAddress.getTreeAddress()).withOTSAddress(oTSHashAddress.getOTSAddress()).withChainAddress(oTSHashAddress.getChainAddress()).withHashAddress(i3 - 1).withKeyAndMask(0).build();
        byte[] bArrPRF = this.khf.PRF(this.publicSeed, oTSHashAddress2.toByteArray());
        byte[] bArrPRF2 = this.khf.PRF(this.publicSeed, ((OTSHashAddress) new OTSHashAddress.Builder().withLayerAddress(oTSHashAddress2.getLayerAddress()).withTreeAddress(oTSHashAddress2.getTreeAddress()).withOTSAddress(oTSHashAddress2.getOTSAddress()).withChainAddress(oTSHashAddress2.getChainAddress()).withHashAddress(oTSHashAddress2.getHashAddress()).withKeyAndMask(1).build()).toByteArray());
        byte[] bArr2 = new byte[treeDigestSize];
        for (int i4 = 0; i4 < treeDigestSize; i4++) {
            bArr2[i4] = (byte) (bArrChain[i4] ^ bArrPRF2[i4]);
        }
        return this.khf.F(bArrPRF, bArr2);
    }

    private List<Integer> convertToBaseW(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new NullPointerException("msg == null");
        }
        if (i != 4 && i != 16) {
            throw new IllegalArgumentException("w needs to be 4 or 16");
        }
        int iLog2 = XMSSUtil.log2(i);
        if (i2 > (bArr.length * 8) / iLog2) {
            throw new IllegalArgumentException("outLength too big");
        }
        ArrayList arrayList = new ArrayList();
        for (int i3 : bArr) {
            for (int i4 = 8 - iLog2; i4 >= 0; i4 -= iLog2) {
                arrayList.add(Integer.valueOf((i3 >> i4) & (i - 1)));
                if (arrayList.size() == i2) {
                    return arrayList;
                }
            }
        }
        return arrayList;
    }

    private byte[] expandSecretKeySeed(int i) {
        if (i < 0 || i >= this.params.getLen()) {
            throw new IllegalArgumentException("index out of bounds");
        }
        return this.khf.PRF(this.secretKeySeed, XMSSUtil.toBytesBigEndian(i, 32));
    }

    protected KeyedHashFunctions getKhf() {
        return this.khf;
    }

    protected WOTSPlusParameters getParams() {
        return this.params;
    }

    protected WOTSPlusPrivateKeyParameters getPrivateKey() {
        int len = this.params.getLen();
        byte[][] bArr = new byte[len][];
        for (int i = 0; i < len; i++) {
            bArr[i] = expandSecretKeySeed(i);
        }
        return new WOTSPlusPrivateKeyParameters(this.params, bArr);
    }

    WOTSPlusPublicKeyParameters getPublicKey(OTSHashAddress oTSHashAddress) {
        if (oTSHashAddress == null) {
            throw new NullPointerException("otsHashAddress == null");
        }
        byte[][] bArr = new byte[this.params.getLen()][];
        for (int i = 0; i < this.params.getLen(); i++) {
            oTSHashAddress = (OTSHashAddress) new OTSHashAddress.Builder().withLayerAddress(oTSHashAddress.getLayerAddress()).withTreeAddress(oTSHashAddress.getTreeAddress()).withOTSAddress(oTSHashAddress.getOTSAddress()).withChainAddress(i).withHashAddress(oTSHashAddress.getHashAddress()).withKeyAndMask(oTSHashAddress.getKeyAndMask()).build();
            bArr[i] = chain(expandSecretKeySeed(i), 0, this.params.getWinternitzParameter() - 1, oTSHashAddress);
        }
        return new WOTSPlusPublicKeyParameters(this.params, bArr);
    }

    WOTSPlusPublicKeyParameters getPublicKeyFromSignature(byte[] bArr, WOTSPlusSignature wOTSPlusSignature, OTSHashAddress oTSHashAddress) {
        if (bArr == null) {
            throw new NullPointerException("messageDigest == null");
        }
        if (bArr.length != this.params.getTreeDigestSize()) {
            throw new IllegalArgumentException("size of messageDigest needs to be equal to size of digest");
        }
        if (wOTSPlusSignature == null) {
            throw new NullPointerException("signature == null");
        }
        if (oTSHashAddress == null) {
            throw new NullPointerException("otsHashAddress == null");
        }
        List<Integer> listConvertToBaseW = convertToBaseW(bArr, this.params.getWinternitzParameter(), this.params.getLen1());
        int winternitzParameter = 0;
        for (int i = 0; i < this.params.getLen1(); i++) {
            winternitzParameter += (this.params.getWinternitzParameter() - 1) - listConvertToBaseW.get(i).intValue();
        }
        listConvertToBaseW.addAll(convertToBaseW(XMSSUtil.toBytesBigEndian(winternitzParameter << (8 - ((this.params.getLen2() * XMSSUtil.log2(this.params.getWinternitzParameter())) % 8)), (int) Math.ceil((this.params.getLen2() * XMSSUtil.log2(this.params.getWinternitzParameter())) / 8.0d)), this.params.getWinternitzParameter(), this.params.getLen2()));
        byte[][] bArr2 = new byte[this.params.getLen()][];
        for (int i2 = 0; i2 < this.params.getLen(); i2++) {
            oTSHashAddress = (OTSHashAddress) new OTSHashAddress.Builder().withLayerAddress(oTSHashAddress.getLayerAddress()).withTreeAddress(oTSHashAddress.getTreeAddress()).withOTSAddress(oTSHashAddress.getOTSAddress()).withChainAddress(i2).withHashAddress(oTSHashAddress.getHashAddress()).withKeyAndMask(oTSHashAddress.getKeyAndMask()).build();
            bArr2[i2] = chain(wOTSPlusSignature.toByteArray()[i2], listConvertToBaseW.get(i2).intValue(), (this.params.getWinternitzParameter() - 1) - listConvertToBaseW.get(i2).intValue(), oTSHashAddress);
        }
        return new WOTSPlusPublicKeyParameters(this.params, bArr2);
    }

    protected byte[] getPublicSeed() {
        return Arrays.clone(this.publicSeed);
    }

    protected byte[] getSecretKeySeed() {
        return Arrays.clone(this.secretKeySeed);
    }

    protected byte[] getWOTSPlusSecretKey(byte[] bArr, OTSHashAddress oTSHashAddress) {
        return this.khf.PRF(bArr, ((OTSHashAddress) new OTSHashAddress.Builder().withLayerAddress(oTSHashAddress.getLayerAddress()).withTreeAddress(oTSHashAddress.getTreeAddress()).withOTSAddress(oTSHashAddress.getOTSAddress()).build()).toByteArray());
    }

    void importKeys(byte[] bArr, byte[] bArr2) {
        if (bArr == null) {
            throw new NullPointerException("secretKeySeed == null");
        }
        if (bArr.length != this.params.getTreeDigestSize()) {
            throw new IllegalArgumentException("size of secretKeySeed needs to be equal to size of digest");
        }
        if (bArr2 == null) {
            throw new NullPointerException("publicSeed == null");
        }
        if (bArr2.length != this.params.getTreeDigestSize()) {
            throw new IllegalArgumentException("size of publicSeed needs to be equal to size of digest");
        }
        this.secretKeySeed = bArr;
        this.publicSeed = bArr2;
    }

    WOTSPlusSignature sign(byte[] bArr, OTSHashAddress oTSHashAddress) {
        if (bArr == null) {
            throw new NullPointerException("messageDigest == null");
        }
        if (bArr.length != this.params.getTreeDigestSize()) {
            throw new IllegalArgumentException("size of messageDigest needs to be equal to size of digest");
        }
        if (oTSHashAddress == null) {
            throw new NullPointerException("otsHashAddress == null");
        }
        List<Integer> listConvertToBaseW = convertToBaseW(bArr, this.params.getWinternitzParameter(), this.params.getLen1());
        int winternitzParameter = 0;
        for (int i = 0; i < this.params.getLen1(); i++) {
            winternitzParameter += (this.params.getWinternitzParameter() - 1) - listConvertToBaseW.get(i).intValue();
        }
        listConvertToBaseW.addAll(convertToBaseW(XMSSUtil.toBytesBigEndian(winternitzParameter << (8 - ((this.params.getLen2() * XMSSUtil.log2(this.params.getWinternitzParameter())) % 8)), (int) Math.ceil((this.params.getLen2() * XMSSUtil.log2(this.params.getWinternitzParameter())) / 8.0d)), this.params.getWinternitzParameter(), this.params.getLen2()));
        byte[][] bArr2 = new byte[this.params.getLen()][];
        for (int i2 = 0; i2 < this.params.getLen(); i2++) {
            oTSHashAddress = (OTSHashAddress) new OTSHashAddress.Builder().withLayerAddress(oTSHashAddress.getLayerAddress()).withTreeAddress(oTSHashAddress.getTreeAddress()).withOTSAddress(oTSHashAddress.getOTSAddress()).withChainAddress(i2).withHashAddress(oTSHashAddress.getHashAddress()).withKeyAndMask(oTSHashAddress.getKeyAndMask()).build();
            bArr2[i2] = chain(expandSecretKeySeed(i2), 0, listConvertToBaseW.get(i2).intValue(), oTSHashAddress);
        }
        return new WOTSPlusSignature(this.params, bArr2);
    }
}
