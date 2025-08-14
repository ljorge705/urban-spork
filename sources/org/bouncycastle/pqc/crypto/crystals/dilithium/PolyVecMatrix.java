package org.bouncycastle.pqc.crypto.crystals.dilithium;

/* loaded from: classes4.dex */
class PolyVecMatrix {
    private final int dilithiumK;
    private final int dilithiumL;
    private final PolyVecL[] mat;

    public PolyVecMatrix(DilithiumEngine dilithiumEngine) {
        int dilithiumK = dilithiumEngine.getDilithiumK();
        this.dilithiumK = dilithiumK;
        this.dilithiumL = dilithiumEngine.getDilithiumL();
        this.mat = new PolyVecL[dilithiumK];
        for (int i = 0; i < this.dilithiumK; i++) {
            this.mat[i] = new PolyVecL(dilithiumEngine);
        }
    }

    private String addString() {
        String string = "[";
        int i = 0;
        while (i < this.dilithiumK) {
            String str = (string + "Outer Matrix " + i + " [") + this.mat[i].toString();
            string = (i == this.dilithiumK + (-1) ? new StringBuilder().append(str).append("]\n") : new StringBuilder().append(str).append("],\n")).toString();
            i++;
        }
        return string + "]\n";
    }

    public void expandMatrix(byte[] bArr) {
        for (int i = 0; i < this.dilithiumK; i++) {
            for (int i2 = 0; i2 < this.dilithiumL; i2++) {
                this.mat[i].getVectorIndex(i2).uniformBlocks(bArr, (short) ((i << 8) + i2));
            }
        }
    }

    public void pointwiseMontgomery(PolyVecK polyVecK, PolyVecL polyVecL) {
        for (int i = 0; i < this.dilithiumK; i++) {
            polyVecK.getVectorIndex(i).pointwiseAccountMontgomery(this.mat[i], polyVecL);
        }
    }

    public String toString(String str) {
        return str.concat(": \n" + addString());
    }
}
