package org.bouncycastle.pqc.legacy.crypto.ntru;

import com.drew.metadata.photoshop.PhotoshopDirectory;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.util.Arrays;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.crypto.util.DigestFactory;

/* loaded from: classes4.dex */
public class NTRUEncryptionKeyGenerationParameters extends KeyGenerationParameters implements Cloneable {
    public int N;
    public int bufferLenBits;
    int bufferLenTrits;
    public int c;
    public int db;
    public int df;
    public int df1;
    public int df2;
    public int df3;
    public int dg;
    public int dm0;
    public int dr;
    public int dr1;
    public int dr2;
    public int dr3;
    public boolean fastFp;
    public Digest hashAlg;
    public boolean hashSeed;
    int llen;
    public int maxMsgLenBytes;
    public int minCallsMask;
    public int minCallsR;
    public byte[] oid;
    public int pkLen;
    public int polyType;
    public int q;
    public boolean sparse;
    public static final NTRUEncryptionKeyGenerationParameters EES1087EP2 = new NTRUEncryptionKeyGenerationParameters(PhotoshopDirectory.TAG_AUTO_SAVE_FORMAT, 2048, 120, 120, 256, 13, 25, 14, true, new byte[]{0, 6, 3}, true, false, new SHA512Digest());
    public static final NTRUEncryptionKeyGenerationParameters EES1171EP1 = new NTRUEncryptionKeyGenerationParameters(1171, 2048, 106, 106, 256, 13, 20, 15, true, new byte[]{0, 6, 4}, true, false, new SHA512Digest());
    public static final NTRUEncryptionKeyGenerationParameters EES1499EP1 = new NTRUEncryptionKeyGenerationParameters(1499, 2048, 79, 79, 256, 13, 17, 19, true, new byte[]{0, 6, 5}, true, false, new SHA512Digest());
    public static final NTRUEncryptionKeyGenerationParameters APR2011_439 = new NTRUEncryptionKeyGenerationParameters(439, 2048, 146, 130, 128, 9, 32, 9, true, new byte[]{0, 7, 101}, true, false, new SHA256Digest());
    public static final NTRUEncryptionKeyGenerationParameters APR2011_439_FAST = new NTRUEncryptionKeyGenerationParameters(439, 2048, 9, 8, 5, 130, 128, 9, 32, 9, true, new byte[]{0, 7, 101}, true, true, new SHA256Digest());
    public static final NTRUEncryptionKeyGenerationParameters APR2011_743 = new NTRUEncryptionKeyGenerationParameters(743, 2048, 248, 220, 256, 10, 27, 14, true, new byte[]{0, 7, 105}, false, false, new SHA512Digest());
    public static final NTRUEncryptionKeyGenerationParameters APR2011_743_FAST = new NTRUEncryptionKeyGenerationParameters(743, 2048, 11, 11, 15, 220, 256, 10, 27, 14, true, new byte[]{0, 7, 105}, false, true, new SHA512Digest());

    public NTRUEncryptionKeyGenerationParameters(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, boolean z, byte[] bArr, boolean z2, boolean z3, Digest digest) {
        this(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, z, bArr, z2, z3, digest, null);
    }

    public NTRUEncryptionKeyGenerationParameters(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, boolean z, byte[] bArr, boolean z2, boolean z3, Digest digest, SecureRandom secureRandom) {
        super(secureRandom != null ? secureRandom : CryptoServicesRegistrar.getSecureRandom(), i7);
        this.N = i;
        this.q = i2;
        this.df1 = i3;
        this.df2 = i4;
        this.df3 = i5;
        this.db = i7;
        this.dm0 = i6;
        this.c = i8;
        this.minCallsR = i9;
        this.minCallsMask = i10;
        this.hashSeed = z;
        this.oid = bArr;
        this.sparse = z2;
        this.fastFp = z3;
        this.polyType = 1;
        this.hashAlg = digest;
        init();
    }

    public NTRUEncryptionKeyGenerationParameters(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z, byte[] bArr, boolean z2, boolean z3, Digest digest) {
        this(i, i2, i3, i4, i5, i6, i7, i8, z, bArr, z2, z3, digest, null);
    }

    public NTRUEncryptionKeyGenerationParameters(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z, byte[] bArr, boolean z2, boolean z3, Digest digest, SecureRandom secureRandom) {
        super(secureRandom == null ? CryptoServicesRegistrar.getSecureRandom() : secureRandom, i5);
        this.N = i;
        this.q = i2;
        this.df = i3;
        this.db = i5;
        this.dm0 = i4;
        this.c = i6;
        this.minCallsR = i7;
        this.minCallsMask = i8;
        this.hashSeed = z;
        this.oid = bArr;
        this.sparse = z2;
        this.fastFp = z3;
        this.polyType = 0;
        this.hashAlg = digest;
        init();
    }

    public NTRUEncryptionKeyGenerationParameters(InputStream inputStream) throws IOException {
        Digest sHA256Digest;
        super(CryptoServicesRegistrar.getSecureRandom(), -1);
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        this.N = dataInputStream.readInt();
        this.q = dataInputStream.readInt();
        this.df = dataInputStream.readInt();
        this.df1 = dataInputStream.readInt();
        this.df2 = dataInputStream.readInt();
        this.df3 = dataInputStream.readInt();
        this.db = dataInputStream.readInt();
        this.dm0 = dataInputStream.readInt();
        this.c = dataInputStream.readInt();
        this.minCallsR = dataInputStream.readInt();
        this.minCallsMask = dataInputStream.readInt();
        this.hashSeed = dataInputStream.readBoolean();
        byte[] bArr = new byte[3];
        this.oid = bArr;
        dataInputStream.readFully(bArr);
        this.sparse = dataInputStream.readBoolean();
        this.fastFp = dataInputStream.readBoolean();
        this.polyType = dataInputStream.read();
        String utf = dataInputStream.readUTF();
        if (!"SHA-512".equals(utf)) {
            sHA256Digest = "SHA-256".equals(utf) ? new SHA256Digest() : sHA256Digest;
            init();
        }
        sHA256Digest = new SHA512Digest();
        this.hashAlg = sHA256Digest;
        init();
    }

    private void init() {
        this.dr = this.df;
        this.dr1 = this.df1;
        this.dr2 = this.df2;
        this.dr3 = this.df3;
        int i = this.N;
        this.dg = i / 3;
        this.llen = 1;
        int i2 = this.db;
        this.maxMsgLenBytes = (((((i * 3) / 2) / 8) - 1) - (i2 / 8)) - 1;
        this.bufferLenBits = (((((i * 3) / 2) + 7) / 8) * 8) + 1;
        this.bufferLenTrits = i - 1;
        this.pkLen = i2;
    }

    public NTRUEncryptionKeyGenerationParameters clone() {
        return this.polyType == 0 ? new NTRUEncryptionKeyGenerationParameters(this.N, this.q, this.df, this.dm0, this.db, this.c, this.minCallsR, this.minCallsMask, this.hashSeed, this.oid, this.sparse, this.fastFp, DigestFactory.cloneDigest(this.hashAlg)) : new NTRUEncryptionKeyGenerationParameters(this.N, this.q, this.df1, this.df2, this.df3, this.dm0, this.db, this.c, this.minCallsR, this.minCallsMask, this.hashSeed, this.oid, this.sparse, this.fastFp, DigestFactory.cloneDigest(this.hashAlg));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        NTRUEncryptionKeyGenerationParameters nTRUEncryptionKeyGenerationParameters = (NTRUEncryptionKeyGenerationParameters) obj;
        if (this.N != nTRUEncryptionKeyGenerationParameters.N || this.bufferLenBits != nTRUEncryptionKeyGenerationParameters.bufferLenBits || this.bufferLenTrits != nTRUEncryptionKeyGenerationParameters.bufferLenTrits || this.c != nTRUEncryptionKeyGenerationParameters.c || this.db != nTRUEncryptionKeyGenerationParameters.db || this.df != nTRUEncryptionKeyGenerationParameters.df || this.df1 != nTRUEncryptionKeyGenerationParameters.df1 || this.df2 != nTRUEncryptionKeyGenerationParameters.df2 || this.df3 != nTRUEncryptionKeyGenerationParameters.df3 || this.dg != nTRUEncryptionKeyGenerationParameters.dg || this.dm0 != nTRUEncryptionKeyGenerationParameters.dm0 || this.dr != nTRUEncryptionKeyGenerationParameters.dr || this.dr1 != nTRUEncryptionKeyGenerationParameters.dr1 || this.dr2 != nTRUEncryptionKeyGenerationParameters.dr2 || this.dr3 != nTRUEncryptionKeyGenerationParameters.dr3 || this.fastFp != nTRUEncryptionKeyGenerationParameters.fastFp) {
            return false;
        }
        Digest digest = this.hashAlg;
        if (digest == null) {
            if (nTRUEncryptionKeyGenerationParameters.hashAlg != null) {
                return false;
            }
        } else if (!digest.getAlgorithmName().equals(nTRUEncryptionKeyGenerationParameters.hashAlg.getAlgorithmName())) {
            return false;
        }
        return this.hashSeed == nTRUEncryptionKeyGenerationParameters.hashSeed && this.llen == nTRUEncryptionKeyGenerationParameters.llen && this.maxMsgLenBytes == nTRUEncryptionKeyGenerationParameters.maxMsgLenBytes && this.minCallsMask == nTRUEncryptionKeyGenerationParameters.minCallsMask && this.minCallsR == nTRUEncryptionKeyGenerationParameters.minCallsR && Arrays.equals(this.oid, nTRUEncryptionKeyGenerationParameters.oid) && this.pkLen == nTRUEncryptionKeyGenerationParameters.pkLen && this.polyType == nTRUEncryptionKeyGenerationParameters.polyType && this.q == nTRUEncryptionKeyGenerationParameters.q && this.sparse == nTRUEncryptionKeyGenerationParameters.sparse;
    }

    public NTRUEncryptionParameters getEncryptionParameters() {
        return this.polyType == 0 ? new NTRUEncryptionParameters(this.N, this.q, this.df, this.dm0, this.db, this.c, this.minCallsR, this.minCallsMask, this.hashSeed, this.oid, this.sparse, this.fastFp, DigestFactory.cloneDigest(this.hashAlg)) : new NTRUEncryptionParameters(this.N, this.q, this.df1, this.df2, this.df3, this.dm0, this.db, this.c, this.minCallsR, this.minCallsMask, this.hashSeed, this.oid, this.sparse, this.fastFp, DigestFactory.cloneDigest(this.hashAlg));
    }

    public int getMaxMessageLength() {
        return this.maxMsgLenBytes;
    }

    public int hashCode() {
        int i = (((((((((((((((((((((((((((((((this.N + 31) * 31) + this.bufferLenBits) * 31) + this.bufferLenTrits) * 31) + this.c) * 31) + this.db) * 31) + this.df) * 31) + this.df1) * 31) + this.df2) * 31) + this.df3) * 31) + this.dg) * 31) + this.dm0) * 31) + this.dr) * 31) + this.dr1) * 31) + this.dr2) * 31) + this.dr3) * 31) + (this.fastFp ? 1231 : 1237)) * 31;
        Digest digest = this.hashAlg;
        return ((((((((((((((((((((i + (digest == null ? 0 : digest.getAlgorithmName().hashCode())) * 31) + (this.hashSeed ? 1231 : 1237)) * 31) + this.llen) * 31) + this.maxMsgLenBytes) * 31) + this.minCallsMask) * 31) + this.minCallsR) * 31) + Arrays.hashCode(this.oid)) * 31) + this.pkLen) * 31) + this.polyType) * 31) + this.q) * 31) + (this.sparse ? 1231 : 1237);
    }

    public String toString() {
        StringBuilder sbAppend;
        int i;
        StringBuilder sb = new StringBuilder("EncryptionParameters(N=" + this.N + " q=" + this.q);
        if (this.polyType == 0) {
            sbAppend = new StringBuilder(" polyType=SIMPLE df=");
            i = this.df;
        } else {
            sbAppend = new StringBuilder(" polyType=PRODUCT df1=").append(this.df1).append(" df2=").append(this.df2).append(" df3=");
            i = this.df3;
        }
        sb.append(sbAppend.append(i).toString());
        sb.append(" dm0=" + this.dm0 + " db=" + this.db + " c=" + this.c + " minCallsR=" + this.minCallsR + " minCallsMask=" + this.minCallsMask + " hashSeed=" + this.hashSeed + " hashAlg=" + this.hashAlg + " oid=" + Arrays.toString(this.oid) + " sparse=" + this.sparse + ")");
        return sb.toString();
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeInt(this.N);
        dataOutputStream.writeInt(this.q);
        dataOutputStream.writeInt(this.df);
        dataOutputStream.writeInt(this.df1);
        dataOutputStream.writeInt(this.df2);
        dataOutputStream.writeInt(this.df3);
        dataOutputStream.writeInt(this.db);
        dataOutputStream.writeInt(this.dm0);
        dataOutputStream.writeInt(this.c);
        dataOutputStream.writeInt(this.minCallsR);
        dataOutputStream.writeInt(this.minCallsMask);
        dataOutputStream.writeBoolean(this.hashSeed);
        dataOutputStream.write(this.oid);
        dataOutputStream.writeBoolean(this.sparse);
        dataOutputStream.writeBoolean(this.fastFp);
        dataOutputStream.write(this.polyType);
        dataOutputStream.writeUTF(this.hashAlg.getAlgorithmName());
    }
}
