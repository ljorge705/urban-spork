package org.spongycastle.crypto.tls;

import java.io.IOException;
import java.security.SecureRandom;
import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.util.Arrays;

/* loaded from: classes7.dex */
public class TlsBlockCipher implements TlsCipher {
    protected TlsContext context;
    protected BlockCipher decryptCipher;
    protected BlockCipher encryptCipher;
    protected boolean encryptThenMAC;
    protected byte[] randomData = new byte[256];
    protected TlsMac readMac;
    protected boolean useExplicitIV;
    protected TlsMac writeMac;

    public TlsMac getReadMac() {
        return this.readMac;
    }

    public TlsMac getWriteMac() {
        return this.writeMac;
    }

    protected int lowestBitSet(int i) {
        if (i == 0) {
            return 32;
        }
        int i2 = 0;
        while ((i & 1) == 0) {
            i2++;
            i >>= 1;
        }
        return i2;
    }

    public TlsBlockCipher(TlsContext tlsContext, BlockCipher blockCipher, BlockCipher blockCipher2, Digest digest, Digest digest2, int i) throws IOException, IllegalArgumentException {
        byte[] bArrCopyOfRange;
        byte[] bArrCopyOfRange2;
        ParametersWithIV parametersWithIV;
        ParametersWithIV parametersWithIV2;
        this.context = tlsContext;
        tlsContext.getNonceRandomGenerator().nextBytes(this.randomData);
        this.useExplicitIV = TlsUtils.isTLSv11(tlsContext);
        this.encryptThenMAC = tlsContext.getSecurityParameters().encryptThenMAC;
        int digestSize = (i * 2) + digest.getDigestSize() + digest2.getDigestSize();
        int blockSize = this.useExplicitIV ? digestSize : digestSize + blockCipher.getBlockSize() + blockCipher2.getBlockSize();
        byte[] bArrCalculateKeyBlock = TlsUtils.calculateKeyBlock(tlsContext, blockSize);
        TlsMac tlsMac = new TlsMac(tlsContext, digest, bArrCalculateKeyBlock, 0, digest.getDigestSize());
        int digestSize2 = digest.getDigestSize();
        TlsMac tlsMac2 = new TlsMac(tlsContext, digest2, bArrCalculateKeyBlock, digestSize2, digest2.getDigestSize());
        int digestSize3 = digestSize2 + digest2.getDigestSize();
        KeyParameter keyParameter = new KeyParameter(bArrCalculateKeyBlock, digestSize3, i);
        int i2 = digestSize3 + i;
        KeyParameter keyParameter2 = new KeyParameter(bArrCalculateKeyBlock, i2, i);
        int blockSize2 = i2 + i;
        if (this.useExplicitIV) {
            bArrCopyOfRange = new byte[blockCipher.getBlockSize()];
            bArrCopyOfRange2 = new byte[blockCipher2.getBlockSize()];
        } else {
            bArrCopyOfRange = Arrays.copyOfRange(bArrCalculateKeyBlock, blockSize2, blockCipher.getBlockSize() + blockSize2);
            int blockSize3 = blockSize2 + blockCipher.getBlockSize();
            bArrCopyOfRange2 = Arrays.copyOfRange(bArrCalculateKeyBlock, blockSize3, blockCipher2.getBlockSize() + blockSize3);
            blockSize2 = blockSize3 + blockCipher2.getBlockSize();
        }
        if (blockSize2 != blockSize) {
            throw new TlsFatalAlert((short) 80);
        }
        if (tlsContext.isServer()) {
            this.writeMac = tlsMac2;
            this.readMac = tlsMac;
            this.encryptCipher = blockCipher2;
            this.decryptCipher = blockCipher;
            parametersWithIV = new ParametersWithIV(keyParameter2, bArrCopyOfRange2);
            parametersWithIV2 = new ParametersWithIV(keyParameter, bArrCopyOfRange);
        } else {
            this.writeMac = tlsMac;
            this.readMac = tlsMac2;
            this.encryptCipher = blockCipher;
            this.decryptCipher = blockCipher2;
            parametersWithIV = new ParametersWithIV(keyParameter, bArrCopyOfRange);
            parametersWithIV2 = new ParametersWithIV(keyParameter2, bArrCopyOfRange2);
        }
        this.encryptCipher.init(true, parametersWithIV);
        this.decryptCipher.init(false, parametersWithIV2);
    }

    @Override // org.spongycastle.crypto.tls.TlsCipher
    public int getPlaintextLimit(int i) {
        int i2;
        int blockSize = this.encryptCipher.getBlockSize();
        int size = this.writeMac.getSize();
        if (this.useExplicitIV) {
            i -= blockSize;
        }
        if (this.encryptThenMAC) {
            int i3 = i - size;
            i2 = i3 - (i3 % blockSize);
        } else {
            i2 = (i - (i % blockSize)) - size;
        }
        return i2 - 1;
    }

    @Override // org.spongycastle.crypto.tls.TlsCipher
    public byte[] encodePlaintext(long j, short s, byte[] bArr, int i, int i2) throws IllegalStateException, DataLengthException, IllegalArgumentException {
        byte[] bArr2;
        int i3;
        int i4;
        int blockSize = this.encryptCipher.getBlockSize();
        int size = this.writeMac.getSize();
        ProtocolVersion serverVersion = this.context.getServerVersion();
        boolean z = this.encryptThenMAC;
        int iChooseExtraPadBlocks = (blockSize - 1) - ((!z ? i2 + size : i2) % blockSize);
        if ((z || !this.context.getSecurityParameters().truncatedHMac) && !serverVersion.isDTLS() && !serverVersion.isSSL()) {
            iChooseExtraPadBlocks += chooseExtraPadBlocks(this.context.getSecureRandom(), (255 - iChooseExtraPadBlocks) / blockSize) * blockSize;
        }
        int i5 = iChooseExtraPadBlocks;
        int i6 = size + i2 + i5 + 1;
        boolean z2 = this.useExplicitIV;
        if (z2) {
            i6 += blockSize;
        }
        byte[] bArr3 = new byte[i6];
        if (z2) {
            byte[] bArr4 = new byte[blockSize];
            this.context.getNonceRandomGenerator().nextBytes(bArr4);
            this.encryptCipher.init(true, new ParametersWithIV(null, bArr4));
            System.arraycopy(bArr4, 0, bArr3, 0, blockSize);
            bArr2 = bArr;
            i3 = i;
            i4 = blockSize;
        } else {
            bArr2 = bArr;
            i3 = i;
            i4 = 0;
        }
        System.arraycopy(bArr2, i3, bArr3, i4, i2);
        int length = i4 + i2;
        if (!this.encryptThenMAC) {
            byte[] bArrCalculateMac = this.writeMac.calculateMac(j, s, bArr, i, i2);
            System.arraycopy(bArrCalculateMac, 0, bArr3, length, bArrCalculateMac.length);
            length += bArrCalculateMac.length;
        }
        int i7 = length;
        int i8 = 0;
        while (i8 <= i5) {
            bArr3[i7] = (byte) i5;
            i8++;
            i7++;
        }
        while (i4 < i7) {
            this.encryptCipher.processBlock(bArr3, i4, bArr3, i4);
            i4 += blockSize;
        }
        if (!this.encryptThenMAC) {
            return bArr3;
        }
        byte[] bArrCalculateMac2 = this.writeMac.calculateMac(j, s, bArr3, 0, i7);
        System.arraycopy(bArrCalculateMac2, 0, bArr3, i7, bArrCalculateMac2.length);
        int length2 = bArrCalculateMac2.length;
        return bArr3;
    }

    @Override // org.spongycastle.crypto.tls.TlsCipher
    public byte[] decodeCiphertext(long j, short s, byte[] bArr, int i, int i2) throws IllegalStateException, DataLengthException, IOException, IllegalArgumentException {
        int i3;
        byte[] bArr2;
        int i4 = i;
        int blockSize = this.decryptCipher.getBlockSize();
        int size = this.readMac.getSize();
        int iMax = this.encryptThenMAC ? blockSize + size : Math.max(blockSize, size + 1);
        if (this.useExplicitIV) {
            iMax += blockSize;
        }
        if (i2 < iMax) {
            throw new TlsFatalAlert((short) 50);
        }
        boolean z = this.encryptThenMAC;
        int i5 = z ? i2 - size : i2;
        if (i5 % blockSize != 0) {
            throw new TlsFatalAlert((short) 21);
        }
        if (z) {
            int i6 = i4 + i2;
            if (!Arrays.constantTimeAreEqual(this.readMac.calculateMac(j, s, bArr, i, i2 - size), Arrays.copyOfRange(bArr, i6 - size, i6))) {
                throw new TlsFatalAlert((short) 20);
            }
        }
        if (this.useExplicitIV) {
            this.decryptCipher.init(false, new ParametersWithIV(null, bArr, i4, blockSize));
            i4 += blockSize;
            i5 -= blockSize;
        }
        int i7 = i4;
        int i8 = i5;
        for (int i9 = 0; i9 < i8; i9 += blockSize) {
            int i10 = i7 + i9;
            this.decryptCipher.processBlock(bArr, i10, bArr, i10);
        }
        int iCheckPaddingConstantTime = checkPaddingConstantTime(bArr, i7, i8, blockSize, this.encryptThenMAC ? 0 : size);
        boolean z2 = iCheckPaddingConstantTime == 0;
        int i11 = i8 - iCheckPaddingConstantTime;
        if (this.encryptThenMAC) {
            i3 = i7;
            bArr2 = bArr;
        } else {
            i11 -= size;
            int i12 = i7 + i11;
            i3 = i7;
            bArr2 = bArr;
            z2 |= !Arrays.constantTimeAreEqual(this.readMac.calculateMacConstantTime(j, s, bArr, i7, i11, i8 - size, this.randomData), Arrays.copyOfRange(bArr, i12, i12 + size));
        }
        if (z2) {
            throw new TlsFatalAlert((short) 20);
        }
        return Arrays.copyOfRange(bArr2, i3, i3 + i11);
    }

    protected int checkPaddingConstantTime(byte[] bArr, int i, int i2, int i3, int i4) {
        byte b;
        int i5;
        int i6 = i + i2;
        byte b2 = bArr[i6 - 1];
        int i7 = (b2 & 255) + 1;
        if ((!TlsUtils.isSSL(this.context) || i7 <= i3) && i4 + i7 <= i2) {
            int i8 = i6 - i7;
            b = 0;
            while (true) {
                int i9 = i8 + 1;
                b = (byte) ((bArr[i8] ^ b2) | b);
                if (i9 >= i6) {
                    break;
                }
                i8 = i9;
            }
            i5 = i7;
            if (b != 0) {
                i7 = 0;
            }
        } else {
            i5 = 0;
            b = 0;
            i7 = 0;
        }
        byte[] bArr2 = this.randomData;
        while (i5 < 256) {
            b = (byte) ((bArr2[i5] ^ b2) | b);
            i5++;
        }
        bArr2[0] = (byte) (bArr2[0] ^ b);
        return i7;
    }

    protected int chooseExtraPadBlocks(SecureRandom secureRandom, int i) {
        return Math.min(lowestBitSet(secureRandom.nextInt()), i);
    }
}
