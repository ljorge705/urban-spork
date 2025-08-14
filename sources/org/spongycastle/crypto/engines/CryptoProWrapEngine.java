package org.spongycastle.crypto.engines;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.modes.GCFBBlockCipher;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.crypto.params.ParametersWithSBox;
import org.spongycastle.crypto.params.ParametersWithUKM;
import org.spongycastle.util.Pack;

/* loaded from: classes4.dex */
public class CryptoProWrapEngine extends GOST28147WrapEngine {
    private static boolean bitSet(byte b, int i) {
        return (b & (1 << i)) != 0;
    }

    @Override // org.spongycastle.crypto.engines.GOST28147WrapEngine, org.spongycastle.crypto.Wrapper
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        KeyParameter keyParameter;
        byte[] sBox;
        if (cipherParameters instanceof ParametersWithRandom) {
            cipherParameters = ((ParametersWithRandom) cipherParameters).getParameters();
        }
        ParametersWithUKM parametersWithUKM = (ParametersWithUKM) cipherParameters;
        if (parametersWithUKM.getParameters() instanceof ParametersWithSBox) {
            keyParameter = (KeyParameter) ((ParametersWithSBox) parametersWithUKM.getParameters()).getParameters();
            sBox = ((ParametersWithSBox) parametersWithUKM.getParameters()).getSBox();
        } else {
            keyParameter = (KeyParameter) parametersWithUKM.getParameters();
            sBox = null;
        }
        KeyParameter keyParameter2 = new KeyParameter(cryptoProDiversify(keyParameter.getKey(), parametersWithUKM.getUKM(), sBox));
        if (sBox != null) {
            super.init(z, new ParametersWithUKM(new ParametersWithSBox(keyParameter2, sBox), parametersWithUKM.getUKM()));
        } else {
            super.init(z, new ParametersWithUKM(keyParameter2, parametersWithUKM.getUKM()));
        }
    }

    private static byte[] cryptoProDiversify(byte[] bArr, byte[] bArr2, byte[] bArr3) throws IllegalStateException, DataLengthException, IllegalArgumentException {
        for (int i = 0; i != 8; i++) {
            int i2 = 0;
            int i3 = 0;
            for (int i4 = 0; i4 != 8; i4++) {
                int iLittleEndianToInt = Pack.littleEndianToInt(bArr, i4 * 4);
                if (bitSet(bArr2[i], i4)) {
                    i2 += iLittleEndianToInt;
                } else {
                    i3 += iLittleEndianToInt;
                }
            }
            byte[] bArr4 = new byte[8];
            Pack.intToLittleEndian(i2, bArr4, 0);
            Pack.intToLittleEndian(i3, bArr4, 4);
            GCFBBlockCipher gCFBBlockCipher = new GCFBBlockCipher(new GOST28147Engine());
            gCFBBlockCipher.init(true, new ParametersWithIV(new ParametersWithSBox(new KeyParameter(bArr), bArr3), bArr4));
            gCFBBlockCipher.processBlock(bArr, 0, bArr, 0);
            gCFBBlockCipher.processBlock(bArr, 8, bArr, 8);
            gCFBBlockCipher.processBlock(bArr, 16, bArr, 16);
            gCFBBlockCipher.processBlock(bArr, 24, bArr, 24);
        }
        return bArr;
    }
}
