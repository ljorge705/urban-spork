package org.spongycastle.pqc.crypto.ntru;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.pqc.math.ntru.polynomial.DenseTernaryPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.IntegerPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.Polynomial;
import org.spongycastle.pqc.math.ntru.polynomial.ProductFormPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.SparseTernaryPolynomial;

/* loaded from: classes7.dex */
public class NTRUSigningPrivateKeyParameters extends AsymmetricKeyParameter {
    private List<Basis> bases;
    private NTRUSigningPublicKeyParameters publicKey;

    public NTRUSigningPublicKeyParameters getPublicKey() {
        return this.publicKey;
    }

    public NTRUSigningPrivateKeyParameters(byte[] bArr, NTRUSigningKeyGenerationParameters nTRUSigningKeyGenerationParameters) throws IOException {
        this(new ByteArrayInputStream(bArr), nTRUSigningKeyGenerationParameters);
    }

    public NTRUSigningPrivateKeyParameters(InputStream inputStream, NTRUSigningKeyGenerationParameters nTRUSigningKeyGenerationParameters) throws IOException {
        super(true);
        this.bases = new ArrayList();
        int i = 0;
        while (i <= nTRUSigningKeyGenerationParameters.B) {
            add(new Basis(inputStream, nTRUSigningKeyGenerationParameters, i != 0));
            i++;
        }
        this.publicKey = new NTRUSigningPublicKeyParameters(inputStream, nTRUSigningKeyGenerationParameters.getSigningParameters());
    }

    public NTRUSigningPrivateKeyParameters(List<Basis> list, NTRUSigningPublicKeyParameters nTRUSigningPublicKeyParameters) {
        super(true);
        this.bases = new ArrayList(list);
        this.publicKey = nTRUSigningPublicKeyParameters;
    }

    private void add(Basis basis) {
        this.bases.add(basis);
    }

    public Basis getBasis(int i) {
        return this.bases.get(i);
    }

    public byte[] getEncoded() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 0;
        while (i < this.bases.size()) {
            this.bases.get(i).encode(byteArrayOutputStream, i != 0);
            i++;
        }
        byteArrayOutputStream.write(this.publicKey.getEncoded());
        return byteArrayOutputStream.toByteArray();
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        outputStream.write(getEncoded());
    }

    public int hashCode() {
        List<Basis> list = this.bases;
        if (list == null) {
            return 31;
        }
        int iHashCode = 31 + list.hashCode();
        Iterator<Basis> it = this.bases.iterator();
        while (it.hasNext()) {
            iHashCode += it.next().hashCode();
        }
        return iHashCode;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        NTRUSigningPrivateKeyParameters nTRUSigningPrivateKeyParameters = (NTRUSigningPrivateKeyParameters) obj;
        List<Basis> list = this.bases;
        if ((list == null) != (nTRUSigningPrivateKeyParameters.bases == null)) {
            return false;
        }
        if (list == null) {
            return true;
        }
        if (list.size() != nTRUSigningPrivateKeyParameters.bases.size()) {
            return false;
        }
        for (int i = 0; i < this.bases.size(); i++) {
            Basis basis = this.bases.get(i);
            Basis basis2 = nTRUSigningPrivateKeyParameters.bases.get(i);
            if (!basis.f.equals(basis2.f) || !basis.fPrime.equals(basis2.fPrime)) {
                return false;
            }
            if ((i != 0 && !basis.h.equals(basis2.h)) || !basis.params.equals(basis2.params)) {
                return false;
            }
        }
        return true;
    }

    public static class Basis {
        public Polynomial f;
        public Polynomial fPrime;
        public IntegerPolynomial h;
        NTRUSigningKeyGenerationParameters params;

        protected Basis(Polynomial polynomial, Polynomial polynomial2, IntegerPolynomial integerPolynomial, NTRUSigningKeyGenerationParameters nTRUSigningKeyGenerationParameters) {
            this.f = polynomial;
            this.fPrime = polynomial2;
            this.h = integerPolynomial;
            this.params = nTRUSigningKeyGenerationParameters;
        }

        Basis(InputStream inputStream, NTRUSigningKeyGenerationParameters nTRUSigningKeyGenerationParameters, boolean z) throws IOException {
            int i = nTRUSigningKeyGenerationParameters.N;
            int i2 = nTRUSigningKeyGenerationParameters.q;
            int i3 = nTRUSigningKeyGenerationParameters.d1;
            int i4 = nTRUSigningKeyGenerationParameters.d2;
            int i5 = nTRUSigningKeyGenerationParameters.d3;
            boolean z2 = nTRUSigningKeyGenerationParameters.sparse;
            this.params = nTRUSigningKeyGenerationParameters;
            if (nTRUSigningKeyGenerationParameters.polyType == 1) {
                this.f = ProductFormPolynomial.fromBinary(inputStream, i, i3, i4, i5 + 1, i5);
            } else {
                IntegerPolynomial integerPolynomialFromBinary3Tight = IntegerPolynomial.fromBinary3Tight(inputStream, i);
                this.f = z2 ? new SparseTernaryPolynomial(integerPolynomialFromBinary3Tight) : new DenseTernaryPolynomial(integerPolynomialFromBinary3Tight);
            }
            if (nTRUSigningKeyGenerationParameters.basisType == 0) {
                IntegerPolynomial integerPolynomialFromBinary = IntegerPolynomial.fromBinary(inputStream, i, i2);
                for (int i6 = 0; i6 < integerPolynomialFromBinary.coeffs.length; i6++) {
                    int[] iArr = integerPolynomialFromBinary.coeffs;
                    iArr[i6] = iArr[i6] - (i2 / 2);
                }
                this.fPrime = integerPolynomialFromBinary;
            } else if (nTRUSigningKeyGenerationParameters.polyType == 1) {
                this.fPrime = ProductFormPolynomial.fromBinary(inputStream, i, i3, i4, i5 + 1, i5);
            } else {
                this.fPrime = IntegerPolynomial.fromBinary3Tight(inputStream, i);
            }
            if (z) {
                this.h = IntegerPolynomial.fromBinary(inputStream, i, i2);
            }
        }

        void encode(OutputStream outputStream, boolean z) throws IOException {
            int i = this.params.q;
            outputStream.write(getEncoded(this.f));
            if (this.params.basisType == 0) {
                IntegerPolynomial integerPolynomial = this.fPrime.toIntegerPolynomial();
                for (int i2 = 0; i2 < integerPolynomial.coeffs.length; i2++) {
                    int[] iArr = integerPolynomial.coeffs;
                    iArr[i2] = iArr[i2] + (i / 2);
                }
                outputStream.write(integerPolynomial.toBinary(i));
            } else {
                outputStream.write(getEncoded(this.fPrime));
            }
            if (z) {
                outputStream.write(this.h.toBinary(i));
            }
        }

        private byte[] getEncoded(Polynomial polynomial) {
            if (polynomial instanceof ProductFormPolynomial) {
                return ((ProductFormPolynomial) polynomial).toBinary();
            }
            return polynomial.toIntegerPolynomial().toBinary3Tight();
        }

        public int hashCode() {
            Polynomial polynomial = this.f;
            int iHashCode = ((polynomial == null ? 0 : polynomial.hashCode()) + 31) * 31;
            Polynomial polynomial2 = this.fPrime;
            int iHashCode2 = (iHashCode + (polynomial2 == null ? 0 : polynomial2.hashCode())) * 31;
            IntegerPolynomial integerPolynomial = this.h;
            int iHashCode3 = (iHashCode2 + (integerPolynomial == null ? 0 : integerPolynomial.hashCode())) * 31;
            NTRUSigningKeyGenerationParameters nTRUSigningKeyGenerationParameters = this.params;
            return iHashCode3 + (nTRUSigningKeyGenerationParameters != null ? nTRUSigningKeyGenerationParameters.hashCode() : 0);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || !(obj instanceof Basis)) {
                return false;
            }
            Basis basis = (Basis) obj;
            Polynomial polynomial = this.f;
            if (polynomial == null) {
                if (basis.f != null) {
                    return false;
                }
            } else if (!polynomial.equals(basis.f)) {
                return false;
            }
            Polynomial polynomial2 = this.fPrime;
            if (polynomial2 == null) {
                if (basis.fPrime != null) {
                    return false;
                }
            } else if (!polynomial2.equals(basis.fPrime)) {
                return false;
            }
            IntegerPolynomial integerPolynomial = this.h;
            if (integerPolynomial == null) {
                if (basis.h != null) {
                    return false;
                }
            } else if (!integerPolynomial.equals(basis.h)) {
                return false;
            }
            NTRUSigningKeyGenerationParameters nTRUSigningKeyGenerationParameters = this.params;
            if (nTRUSigningKeyGenerationParameters == null) {
                if (basis.params != null) {
                    return false;
                }
            } else if (!nTRUSigningKeyGenerationParameters.equals(basis.params)) {
                return false;
            }
            return true;
        }
    }
}
