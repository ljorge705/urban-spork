package org.spongycastle.pqc.crypto.ntru;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.digests.SHA256Digest;
import org.spongycastle.crypto.digests.SHA512Digest;

/* loaded from: classes7.dex */
public class NTRUSigningParameters implements Cloneable {
    public int B;
    public int N;
    double beta;
    public double betaSq;
    int bitsF;
    public int d;
    public int d1;
    public int d2;
    public int d3;
    public Digest hashAlg;
    double normBound;
    public double normBoundSq;
    public int q;
    public int signFailTolerance;

    private void init() {
        double d = this.beta;
        this.betaSq = d * d;
        double d2 = this.normBound;
        this.normBoundSq = d2 * d2;
    }

    public NTRUSigningParameters(int i, int i2, int i3, int i4, double d, double d2, Digest digest) {
        this.signFailTolerance = 100;
        this.bitsF = 6;
        this.N = i;
        this.q = i2;
        this.d = i3;
        this.B = i4;
        this.beta = d;
        this.normBound = d2;
        this.hashAlg = digest;
        init();
    }

    public NTRUSigningParameters(int i, int i2, int i3, int i4, int i5, int i6, double d, double d2, double d3, Digest digest) {
        this.signFailTolerance = 100;
        this.bitsF = 6;
        this.N = i;
        this.q = i2;
        this.d1 = i3;
        this.d2 = i4;
        this.d3 = i5;
        this.B = i6;
        this.beta = d;
        this.normBound = d2;
        this.hashAlg = digest;
        init();
    }

    public NTRUSigningParameters(InputStream inputStream) throws IOException {
        this.signFailTolerance = 100;
        this.bitsF = 6;
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        this.N = dataInputStream.readInt();
        this.q = dataInputStream.readInt();
        this.d = dataInputStream.readInt();
        this.d1 = dataInputStream.readInt();
        this.d2 = dataInputStream.readInt();
        this.d3 = dataInputStream.readInt();
        this.B = dataInputStream.readInt();
        this.beta = dataInputStream.readDouble();
        this.normBound = dataInputStream.readDouble();
        this.signFailTolerance = dataInputStream.readInt();
        this.bitsF = dataInputStream.readInt();
        String utf = dataInputStream.readUTF();
        if ("SHA-512".equals(utf)) {
            this.hashAlg = new SHA512Digest();
        } else if ("SHA-256".equals(utf)) {
            this.hashAlg = new SHA256Digest();
        }
        init();
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeInt(this.N);
        dataOutputStream.writeInt(this.q);
        dataOutputStream.writeInt(this.d);
        dataOutputStream.writeInt(this.d1);
        dataOutputStream.writeInt(this.d2);
        dataOutputStream.writeInt(this.d3);
        dataOutputStream.writeInt(this.B);
        dataOutputStream.writeDouble(this.beta);
        dataOutputStream.writeDouble(this.normBound);
        dataOutputStream.writeInt(this.signFailTolerance);
        dataOutputStream.writeInt(this.bitsF);
        dataOutputStream.writeUTF(this.hashAlg.getAlgorithmName());
    }

    public NTRUSigningParameters clone() {
        return new NTRUSigningParameters(this.N, this.q, this.d, this.B, this.beta, this.normBound, this.hashAlg);
    }

    public int hashCode() {
        int i = ((this.B + 31) * 31) + this.N;
        long jDoubleToLongBits = Double.doubleToLongBits(this.beta);
        int i2 = (i * 31) + ((int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32)));
        long jDoubleToLongBits2 = Double.doubleToLongBits(this.betaSq);
        int i3 = ((((((((((((i2 * 31) + ((int) (jDoubleToLongBits2 ^ (jDoubleToLongBits2 >>> 32)))) * 31) + this.bitsF) * 31) + this.d) * 31) + this.d1) * 31) + this.d2) * 31) + this.d3) * 31;
        Digest digest = this.hashAlg;
        int iHashCode = i3 + (digest == null ? 0 : digest.getAlgorithmName().hashCode());
        long jDoubleToLongBits3 = Double.doubleToLongBits(this.normBound);
        int i4 = (iHashCode * 31) + ((int) (jDoubleToLongBits3 ^ (jDoubleToLongBits3 >>> 32)));
        long jDoubleToLongBits4 = Double.doubleToLongBits(this.normBoundSq);
        return (((((i4 * 31) + ((int) (jDoubleToLongBits4 ^ (jDoubleToLongBits4 >>> 32)))) * 31) + this.q) * 31) + this.signFailTolerance;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof NTRUSigningParameters)) {
            return false;
        }
        NTRUSigningParameters nTRUSigningParameters = (NTRUSigningParameters) obj;
        if (this.B != nTRUSigningParameters.B || this.N != nTRUSigningParameters.N || Double.doubleToLongBits(this.beta) != Double.doubleToLongBits(nTRUSigningParameters.beta) || Double.doubleToLongBits(this.betaSq) != Double.doubleToLongBits(nTRUSigningParameters.betaSq) || this.bitsF != nTRUSigningParameters.bitsF || this.d != nTRUSigningParameters.d || this.d1 != nTRUSigningParameters.d1 || this.d2 != nTRUSigningParameters.d2 || this.d3 != nTRUSigningParameters.d3) {
            return false;
        }
        Digest digest = this.hashAlg;
        if (digest == null) {
            if (nTRUSigningParameters.hashAlg != null) {
                return false;
            }
        } else if (!digest.getAlgorithmName().equals(nTRUSigningParameters.hashAlg.getAlgorithmName())) {
            return false;
        }
        return Double.doubleToLongBits(this.normBound) == Double.doubleToLongBits(nTRUSigningParameters.normBound) && Double.doubleToLongBits(this.normBoundSq) == Double.doubleToLongBits(nTRUSigningParameters.normBoundSq) && this.q == nTRUSigningParameters.q && this.signFailTolerance == nTRUSigningParameters.signFailTolerance;
    }

    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        StringBuilder sb = new StringBuilder("SignatureParameters(N=" + this.N + " q=" + this.q);
        sb.append(" B=" + this.B + " beta=" + decimalFormat.format(this.beta) + " normBound=" + decimalFormat.format(this.normBound) + " hashAlg=" + this.hashAlg + ")");
        return sb.toString();
    }
}
