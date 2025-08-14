package org.spongycastle.pqc.crypto.xmss;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import org.spongycastle.pqc.crypto.xmss.OTSHashAddress;
import org.spongycastle.util.Integers;

/* loaded from: classes7.dex */
public class BDSStateMap implements Serializable {
    private final Map<Integer, BDS> bdsState = new TreeMap();

    BDSStateMap() {
    }

    BDSStateMap(XMSSMTParameters xMSSMTParameters, long j, byte[] bArr, byte[] bArr2) {
        for (long j2 = 0; j2 < j; j2++) {
            updateState(xMSSMTParameters, j2, bArr, bArr2);
        }
    }

    BDSStateMap(BDSStateMap bDSStateMap, XMSSMTParameters xMSSMTParameters, long j, byte[] bArr, byte[] bArr2) {
        for (Integer num : bDSStateMap.bdsState.keySet()) {
            this.bdsState.put(num, bDSStateMap.bdsState.get(num));
        }
        updateState(xMSSMTParameters, j, bArr, bArr2);
    }

    private void updateState(XMSSMTParameters xMSSMTParameters, long j, byte[] bArr, byte[] bArr2) {
        XMSSParameters xMSSParameters = xMSSMTParameters.getXMSSParameters();
        int height = xMSSParameters.getHeight();
        long treeIndex = XMSSUtil.getTreeIndex(j, height);
        int leafIndex = XMSSUtil.getLeafIndex(j, height);
        OTSHashAddress oTSHashAddress = (OTSHashAddress) new OTSHashAddress.Builder().withTreeAddress(treeIndex).withOTSAddress(leafIndex).build();
        int i = (1 << height) - 1;
        if (leafIndex < i) {
            if (get(0) == null || leafIndex == 0) {
                put(0, new BDS(xMSSParameters, bArr, bArr2, oTSHashAddress));
            }
            update(0, bArr, bArr2, oTSHashAddress);
        }
        for (int i2 = 1; i2 < xMSSMTParameters.getLayers(); i2++) {
            int leafIndex2 = XMSSUtil.getLeafIndex(treeIndex, height);
            treeIndex = XMSSUtil.getTreeIndex(treeIndex, height);
            OTSHashAddress oTSHashAddress2 = (OTSHashAddress) new OTSHashAddress.Builder().withLayerAddress(i2).withTreeAddress(treeIndex).withOTSAddress(leafIndex2).build();
            if (leafIndex2 < i && XMSSUtil.isNewAuthenticationPathNeeded(j, height, i2)) {
                if (get(i2) == null) {
                    put(i2, new BDS(xMSSMTParameters.getXMSSParameters(), bArr, bArr2, oTSHashAddress2));
                }
                update(i2, bArr, bArr2, oTSHashAddress2);
            }
        }
    }

    void setXMSS(XMSSParameters xMSSParameters) {
        Iterator<Integer> it = this.bdsState.keySet().iterator();
        while (it.hasNext()) {
            BDS bds = this.bdsState.get(it.next());
            bds.setXMSS(xMSSParameters);
            bds.validate();
        }
    }

    public boolean isEmpty() {
        return this.bdsState.isEmpty();
    }

    public BDS get(int i) {
        return this.bdsState.get(Integers.valueOf(i));
    }

    public BDS update(int i, byte[] bArr, byte[] bArr2, OTSHashAddress oTSHashAddress) {
        return this.bdsState.put(Integers.valueOf(i), this.bdsState.get(Integers.valueOf(i)).getNextState(bArr, bArr2, oTSHashAddress));
    }

    public void put(int i, BDS bds) {
        this.bdsState.put(Integers.valueOf(i), bds);
    }
}
