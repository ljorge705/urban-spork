package org.spongycastle.crypto.modes;

import java.io.ByteArrayOutputStream;
import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.params.AEADParameters;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.util.Arrays;

/* loaded from: classes7.dex */
public class KCCMBlockCipher implements AEADBlockCipher {
    private static final int BITS_IN_BYTE = 8;
    private static final int BYTES_IN_INT = 4;
    private static final int MAX_MAC_BIT_LENGTH = 512;
    private static final int MIN_MAC_BIT_LENGTH = 64;
    private byte[] G1;
    private int Nb_;
    private ExposedByteArrayOutputStream associatedText;
    private byte[] buffer;
    private byte[] counter;
    private ExposedByteArrayOutputStream data;
    private BlockCipher engine;
    private boolean forEncryption;
    private byte[] initialAssociatedText;
    private byte[] mac;
    private byte[] macBlock;
    private int macSize;
    private byte[] nonce;
    private byte[] s;

    @Override // org.spongycastle.crypto.modes.AEADBlockCipher
    public int getOutputSize(int i) {
        return i + this.macSize;
    }

    @Override // org.spongycastle.crypto.modes.AEADBlockCipher
    public BlockCipher getUnderlyingCipher() {
        return this.engine;
    }

    @Override // org.spongycastle.crypto.modes.AEADBlockCipher
    public int getUpdateOutputSize(int i) {
        return i;
    }

    private void setNb(int i) {
        if (i != 4 && i != 6 && i != 8) {
            throw new IllegalArgumentException("Nb = 4 is recommended by DSTU7624 but can be changed to only 6 or 8 in this implementation");
        }
        this.Nb_ = i;
    }

    public KCCMBlockCipher(BlockCipher blockCipher) {
        this(blockCipher, 4);
    }

    public KCCMBlockCipher(BlockCipher blockCipher, int i) {
        this.associatedText = new ExposedByteArrayOutputStream();
        this.data = new ExposedByteArrayOutputStream();
        this.Nb_ = 4;
        this.engine = blockCipher;
        this.macSize = blockCipher.getBlockSize();
        this.nonce = new byte[blockCipher.getBlockSize()];
        this.initialAssociatedText = new byte[blockCipher.getBlockSize()];
        this.mac = new byte[blockCipher.getBlockSize()];
        this.macBlock = new byte[blockCipher.getBlockSize()];
        this.G1 = new byte[blockCipher.getBlockSize()];
        this.buffer = new byte[blockCipher.getBlockSize()];
        this.s = new byte[blockCipher.getBlockSize()];
        this.counter = new byte[blockCipher.getBlockSize()];
        setNb(i);
    }

    @Override // org.spongycastle.crypto.modes.AEADBlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        CipherParameters parameters;
        if (cipherParameters instanceof AEADParameters) {
            AEADParameters aEADParameters = (AEADParameters) cipherParameters;
            if (aEADParameters.getMacSize() > 512 || aEADParameters.getMacSize() < 64 || aEADParameters.getMacSize() % 8 != 0) {
                throw new IllegalArgumentException("Invalid mac size specified");
            }
            this.nonce = aEADParameters.getNonce();
            this.macSize = aEADParameters.getMacSize() / 8;
            this.initialAssociatedText = aEADParameters.getAssociatedText();
            parameters = aEADParameters.getKey();
        } else if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            this.nonce = parametersWithIV.getIV();
            this.macSize = this.engine.getBlockSize();
            this.initialAssociatedText = null;
            parameters = parametersWithIV.getParameters();
        } else {
            throw new IllegalArgumentException("Invalid parameters specified");
        }
        this.mac = new byte[this.macSize];
        this.forEncryption = z;
        this.engine.init(true, parameters);
        this.counter[0] = 1;
        byte[] bArr = this.initialAssociatedText;
        if (bArr != null) {
            processAADBytes(bArr, 0, bArr.length);
        }
    }

    @Override // org.spongycastle.crypto.modes.AEADBlockCipher
    public String getAlgorithmName() {
        return this.engine.getAlgorithmName() + "/KCCM";
    }

    @Override // org.spongycastle.crypto.modes.AEADBlockCipher
    public void processAADByte(byte b) {
        this.associatedText.write(b);
    }

    @Override // org.spongycastle.crypto.modes.AEADBlockCipher
    public void processAADBytes(byte[] bArr, int i, int i2) {
        this.associatedText.write(bArr, i, i2);
    }

    private void processAAD(byte[] bArr, int i, int i2, int i3) throws IllegalStateException, DataLengthException {
        if (i2 - i < this.engine.getBlockSize()) {
            throw new IllegalArgumentException("authText buffer too short");
        }
        if (i2 % this.engine.getBlockSize() != 0) {
            throw new IllegalArgumentException("padding not supported");
        }
        byte[] bArr2 = this.nonce;
        System.arraycopy(bArr2, 0, this.G1, 0, (bArr2.length - this.Nb_) - 1);
        intToBytes(i3, this.buffer, 0);
        System.arraycopy(this.buffer, 0, this.G1, (this.nonce.length - this.Nb_) - 1, 4);
        byte[] bArr3 = this.G1;
        bArr3[bArr3.length - 1] = getFlag(true, this.macSize);
        this.engine.processBlock(this.G1, 0, this.macBlock, 0);
        intToBytes(i2, this.buffer, 0);
        if (i2 <= this.engine.getBlockSize() - this.Nb_) {
            for (int i4 = 0; i4 < i2; i4++) {
                byte[] bArr4 = this.buffer;
                int i5 = this.Nb_ + i4;
                bArr4[i5] = (byte) (bArr4[i5] ^ bArr[i + i4]);
            }
            for (int i6 = 0; i6 < this.engine.getBlockSize(); i6++) {
                byte[] bArr5 = this.macBlock;
                bArr5[i6] = (byte) (bArr5[i6] ^ this.buffer[i6]);
            }
            BlockCipher blockCipher = this.engine;
            byte[] bArr6 = this.macBlock;
            blockCipher.processBlock(bArr6, 0, bArr6, 0);
            return;
        }
        for (int i7 = 0; i7 < this.engine.getBlockSize(); i7++) {
            byte[] bArr7 = this.macBlock;
            bArr7[i7] = (byte) (bArr7[i7] ^ this.buffer[i7]);
        }
        BlockCipher blockCipher2 = this.engine;
        byte[] bArr8 = this.macBlock;
        blockCipher2.processBlock(bArr8, 0, bArr8, 0);
        while (i2 != 0) {
            for (int i8 = 0; i8 < this.engine.getBlockSize(); i8++) {
                byte[] bArr9 = this.macBlock;
                bArr9[i8] = (byte) (bArr9[i8] ^ bArr[i8 + i]);
            }
            BlockCipher blockCipher3 = this.engine;
            byte[] bArr10 = this.macBlock;
            blockCipher3.processBlock(bArr10, 0, bArr10, 0);
            i += this.engine.getBlockSize();
            i2 -= this.engine.getBlockSize();
        }
    }

    @Override // org.spongycastle.crypto.modes.AEADBlockCipher
    public int processByte(byte b, byte[] bArr, int i) throws IllegalStateException, DataLengthException {
        this.data.write(b);
        return 0;
    }

    @Override // org.spongycastle.crypto.modes.AEADBlockCipher
    public int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws IllegalStateException, DataLengthException {
        if (bArr.length < i + i2) {
            throw new DataLengthException("input buffer too short");
        }
        this.data.write(bArr, i, i2);
        return 0;
    }

    public int processPacket(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws IllegalStateException, InvalidCipherTextException, DataLengthException {
        int i4;
        if (bArr.length - i < i2) {
            throw new DataLengthException("input buffer too short");
        }
        if (bArr2.length - i3 < i2) {
            throw new OutputLengthException("output buffer too short");
        }
        if (this.associatedText.size() > 0) {
            if (this.forEncryption) {
                processAAD(this.associatedText.getBuffer(), 0, this.associatedText.size(), this.data.size());
            } else {
                processAAD(this.associatedText.getBuffer(), 0, this.associatedText.size(), this.data.size() - this.macSize);
            }
        }
        if (this.forEncryption) {
            if (i2 % this.engine.getBlockSize() != 0) {
                throw new DataLengthException("partial blocks not supported");
            }
            CalculateMac(bArr, i, i2);
            this.engine.processBlock(this.nonce, 0, this.s, 0);
            int blockSize = i2;
            while (blockSize > 0) {
                ProcessBlock(bArr, i, i2, bArr2, i3);
                blockSize -= this.engine.getBlockSize();
                i += this.engine.getBlockSize();
                i3 += this.engine.getBlockSize();
            }
            int i5 = 0;
            while (true) {
                byte[] bArr3 = this.counter;
                if (i5 >= bArr3.length) {
                    break;
                }
                byte[] bArr4 = this.s;
                bArr4[i5] = (byte) (bArr4[i5] + bArr3[i5]);
                i5++;
            }
            this.engine.processBlock(this.s, 0, this.buffer, 0);
            int i6 = 0;
            while (true) {
                int i7 = this.macSize;
                if (i6 < i7) {
                    bArr2[i3 + i6] = (byte) (this.buffer[i6] ^ this.macBlock[i6]);
                    i6++;
                } else {
                    System.arraycopy(this.macBlock, 0, this.mac, 0, i7);
                    reset();
                    return i2 + this.macSize;
                }
            }
        } else {
            if ((i2 - this.macSize) % this.engine.getBlockSize() != 0) {
                throw new DataLengthException("partial blocks not supported");
            }
            this.engine.processBlock(this.nonce, 0, this.s, 0);
            int blockSize2 = i2 / this.engine.getBlockSize();
            for (int i8 = 0; i8 < blockSize2; i8++) {
                ProcessBlock(bArr, i, i2, bArr2, i3);
                i += this.engine.getBlockSize();
                i3 += this.engine.getBlockSize();
            }
            if (i2 > i) {
                int i9 = 0;
                while (true) {
                    byte[] bArr5 = this.counter;
                    if (i9 >= bArr5.length) {
                        break;
                    }
                    byte[] bArr6 = this.s;
                    bArr6[i9] = (byte) (bArr6[i9] + bArr5[i9]);
                    i9++;
                }
                this.engine.processBlock(this.s, 0, this.buffer, 0);
                int i10 = 0;
                while (true) {
                    i4 = this.macSize;
                    if (i10 >= i4) {
                        break;
                    }
                    bArr2[i3 + i10] = (byte) (this.buffer[i10] ^ bArr[i + i10]);
                    i10++;
                }
                i3 += i4;
            }
            int i11 = 0;
            while (true) {
                byte[] bArr7 = this.counter;
                if (i11 >= bArr7.length) {
                    break;
                }
                byte[] bArr8 = this.s;
                bArr8[i11] = (byte) (bArr8[i11] + bArr7[i11]);
                i11++;
            }
            this.engine.processBlock(this.s, 0, this.buffer, 0);
            int i12 = this.macSize;
            System.arraycopy(bArr2, i3 - i12, this.buffer, 0, i12);
            CalculateMac(bArr2, 0, i3 - this.macSize);
            System.arraycopy(this.macBlock, 0, this.mac, 0, this.macSize);
            int i13 = this.macSize;
            byte[] bArr9 = new byte[i13];
            System.arraycopy(this.buffer, 0, bArr9, 0, i13);
            if (!Arrays.constantTimeAreEqual(this.mac, bArr9)) {
                throw new InvalidCipherTextException("mac check failed");
            }
            reset();
            return i2 - this.macSize;
        }
    }

    private void ProcessBlock(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws IllegalStateException, DataLengthException {
        int i4 = 0;
        while (true) {
            byte[] bArr3 = this.counter;
            if (i4 >= bArr3.length) {
                break;
            }
            byte[] bArr4 = this.s;
            bArr4[i4] = (byte) (bArr4[i4] + bArr3[i4]);
            i4++;
        }
        this.engine.processBlock(this.s, 0, this.buffer, 0);
        for (int i5 = 0; i5 < this.engine.getBlockSize(); i5++) {
            bArr2[i3 + i5] = (byte) (this.buffer[i5] ^ bArr[i + i5]);
        }
    }

    private void CalculateMac(byte[] bArr, int i, int i2) throws IllegalStateException, DataLengthException {
        while (i2 > 0) {
            for (int i3 = 0; i3 < this.engine.getBlockSize(); i3++) {
                byte[] bArr2 = this.macBlock;
                bArr2[i3] = (byte) (bArr2[i3] ^ bArr[i + i3]);
            }
            BlockCipher blockCipher = this.engine;
            byte[] bArr3 = this.macBlock;
            blockCipher.processBlock(bArr3, 0, bArr3, 0);
            i2 -= this.engine.getBlockSize();
            i += this.engine.getBlockSize();
        }
    }

    @Override // org.spongycastle.crypto.modes.AEADBlockCipher
    public int doFinal(byte[] bArr, int i) throws IllegalStateException, InvalidCipherTextException, DataLengthException {
        int iProcessPacket = processPacket(this.data.getBuffer(), 0, this.data.size(), bArr, i);
        reset();
        return iProcessPacket;
    }

    @Override // org.spongycastle.crypto.modes.AEADBlockCipher
    public byte[] getMac() {
        return Arrays.clone(this.mac);
    }

    @Override // org.spongycastle.crypto.modes.AEADBlockCipher
    public void reset() {
        Arrays.fill(this.G1, (byte) 0);
        Arrays.fill(this.buffer, (byte) 0);
        Arrays.fill(this.counter, (byte) 0);
        Arrays.fill(this.macBlock, (byte) 0);
        this.counter[0] = 1;
        this.data.reset();
        this.associatedText.reset();
        byte[] bArr = this.initialAssociatedText;
        if (bArr != null) {
            processAADBytes(bArr, 0, bArr.length);
        }
    }

    private void intToBytes(int i, byte[] bArr, int i2) {
        bArr[i2 + 3] = (byte) (i >> 24);
        bArr[i2 + 2] = (byte) (i >> 16);
        bArr[i2 + 1] = (byte) (i >> 8);
        bArr[i2] = (byte) i;
    }

    private byte getFlag(boolean z, int i) {
        StringBuffer stringBuffer = new StringBuffer();
        if (z) {
            stringBuffer.append("1");
        } else {
            stringBuffer.append("0");
        }
        if (i == 8) {
            stringBuffer.append("010");
        } else if (i == 16) {
            stringBuffer.append("011");
        } else if (i == 32) {
            stringBuffer.append("100");
        } else if (i == 48) {
            stringBuffer.append("101");
        } else if (i == 64) {
            stringBuffer.append("110");
        }
        String binaryString = Integer.toBinaryString(this.Nb_ - 1);
        while (binaryString.length() < 4) {
            binaryString = new StringBuffer(binaryString).insert(0, "0").toString();
        }
        stringBuffer.append(binaryString);
        return (byte) Integer.parseInt(stringBuffer.toString(), 2);
    }

    private class ExposedByteArrayOutputStream extends ByteArrayOutputStream {
        public ExposedByteArrayOutputStream() {
        }

        public byte[] getBuffer() {
            return this.buf;
        }
    }
}
