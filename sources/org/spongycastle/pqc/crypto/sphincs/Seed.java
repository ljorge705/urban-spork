package org.spongycastle.pqc.crypto.sphincs;

import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.engines.ChaChaEngine;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.pqc.crypto.sphincs.Tree;
import org.spongycastle.util.Pack;

/* loaded from: classes7.dex */
class Seed {
    Seed() {
    }

    static void get_seed(HashFunctions hashFunctions, byte[] bArr, int i, byte[] bArr2, Tree.leafaddr leafaddrVar) {
        byte[] bArr3 = new byte[40];
        for (int i2 = 0; i2 < 32; i2++) {
            bArr3[i2] = bArr2[i2];
        }
        Pack.longToLittleEndian((leafaddrVar.subleaf << 59) | leafaddrVar.level | (leafaddrVar.subtree << 4), bArr3, 32);
        hashFunctions.varlen_hash(bArr, i, bArr3, 40);
    }

    static void prg(byte[] bArr, int i, long j, byte[] bArr2, int i2) throws DataLengthException, IllegalArgumentException {
        ChaChaEngine chaChaEngine = new ChaChaEngine(12);
        chaChaEngine.init(true, new ParametersWithIV(new KeyParameter(bArr2, i2, 32), new byte[8]));
        chaChaEngine.processBytes(bArr, i, (int) j, bArr, i);
    }
}
