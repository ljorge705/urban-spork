package org.bouncycastle.pqc.crypto.crystals.dilithium;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.StreamCipher;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.modes.SICBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;

/* loaded from: classes4.dex */
abstract class Symmetric {
    final int stream128BlockBytes;
    final int stream256BlockBytes;

    @Deprecated
    static class AesSymmetric extends Symmetric {
        private final StreamCipher cipher;

        AesSymmetric() {
            super(64, 64);
            this.cipher = SICBlockCipher.newInstance(AESEngine.newInstance());
        }

        private void aes128(byte[] bArr, int i, int i2) throws DataLengthException {
            this.cipher.processBytes(new byte[i2], 0, i2, bArr, i);
        }

        private void streamInit(byte[] bArr, short s) throws IllegalArgumentException {
            byte[] bArr2 = new byte[12];
            bArr2[0] = (byte) s;
            bArr2[1] = (byte) (s >> 8);
            this.cipher.init(true, new ParametersWithIV(new KeyParameter(bArr, 0, 32), bArr2));
        }

        @Override // org.bouncycastle.pqc.crypto.crystals.dilithium.Symmetric
        void stream128init(byte[] bArr, short s) throws IllegalArgumentException {
            streamInit(bArr, s);
        }

        @Override // org.bouncycastle.pqc.crypto.crystals.dilithium.Symmetric
        void stream128squeezeBlocks(byte[] bArr, int i, int i2) throws DataLengthException {
            aes128(bArr, i, i2);
        }

        @Override // org.bouncycastle.pqc.crypto.crystals.dilithium.Symmetric
        void stream256init(byte[] bArr, short s) throws IllegalArgumentException {
            streamInit(bArr, s);
        }

        @Override // org.bouncycastle.pqc.crypto.crystals.dilithium.Symmetric
        void stream256squeezeBlocks(byte[] bArr, int i, int i2) throws DataLengthException {
            aes128(bArr, i, i2);
        }
    }

    static class ShakeSymmetric extends Symmetric {
        private final SHAKEDigest digest128;
        private final SHAKEDigest digest256;

        ShakeSymmetric() {
            super(168, 136);
            this.digest128 = new SHAKEDigest(128);
            this.digest256 = new SHAKEDigest(256);
        }

        private void streamInit(SHAKEDigest sHAKEDigest, byte[] bArr, short s) {
            sHAKEDigest.reset();
            sHAKEDigest.update(bArr, 0, bArr.length);
            sHAKEDigest.update(new byte[]{(byte) s, (byte) (s >> 8)}, 0, 2);
        }

        @Override // org.bouncycastle.pqc.crypto.crystals.dilithium.Symmetric
        void stream128init(byte[] bArr, short s) {
            streamInit(this.digest128, bArr, s);
        }

        @Override // org.bouncycastle.pqc.crypto.crystals.dilithium.Symmetric
        void stream128squeezeBlocks(byte[] bArr, int i, int i2) {
            this.digest128.doOutput(bArr, i, i2);
        }

        @Override // org.bouncycastle.pqc.crypto.crystals.dilithium.Symmetric
        void stream256init(byte[] bArr, short s) {
            streamInit(this.digest256, bArr, s);
        }

        @Override // org.bouncycastle.pqc.crypto.crystals.dilithium.Symmetric
        void stream256squeezeBlocks(byte[] bArr, int i, int i2) {
            this.digest256.doOutput(bArr, i, i2);
        }
    }

    Symmetric(int i, int i2) {
        this.stream128BlockBytes = i;
        this.stream256BlockBytes = i2;
    }

    abstract void stream128init(byte[] bArr, short s);

    abstract void stream128squeezeBlocks(byte[] bArr, int i, int i2);

    abstract void stream256init(byte[] bArr, short s);

    abstract void stream256squeezeBlocks(byte[] bArr, int i, int i2);
}
