package org.bouncycastle.pqc.legacy.crypto.ntru;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.bouncycastle.pqc.legacy.math.ntru.polynomial.IntegerPolynomial;

/* loaded from: classes4.dex */
public class NTRUEncryptionPublicKeyParameters extends NTRUEncryptionKeyParameters {
    public IntegerPolynomial h;

    public NTRUEncryptionPublicKeyParameters(InputStream inputStream, NTRUEncryptionParameters nTRUEncryptionParameters) throws IOException {
        super(false, nTRUEncryptionParameters);
        this.h = IntegerPolynomial.fromBinary(inputStream, nTRUEncryptionParameters.N, nTRUEncryptionParameters.q);
    }

    public NTRUEncryptionPublicKeyParameters(IntegerPolynomial integerPolynomial, NTRUEncryptionParameters nTRUEncryptionParameters) {
        super(false, nTRUEncryptionParameters);
        this.h = integerPolynomial;
    }

    public NTRUEncryptionPublicKeyParameters(byte[] bArr, NTRUEncryptionParameters nTRUEncryptionParameters) {
        super(false, nTRUEncryptionParameters);
        this.h = IntegerPolynomial.fromBinary(bArr, nTRUEncryptionParameters.N, nTRUEncryptionParameters.q);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof NTRUEncryptionPublicKeyParameters)) {
            return false;
        }
        NTRUEncryptionPublicKeyParameters nTRUEncryptionPublicKeyParameters = (NTRUEncryptionPublicKeyParameters) obj;
        IntegerPolynomial integerPolynomial = this.h;
        if (integerPolynomial == null) {
            if (nTRUEncryptionPublicKeyParameters.h != null) {
                return false;
            }
        } else if (!integerPolynomial.equals(nTRUEncryptionPublicKeyParameters.h)) {
            return false;
        }
        if (this.params == null) {
            if (nTRUEncryptionPublicKeyParameters.params != null) {
                return false;
            }
        } else if (!this.params.equals(nTRUEncryptionPublicKeyParameters.params)) {
            return false;
        }
        return true;
    }

    public byte[] getEncoded() {
        return this.h.toBinary(this.params.q);
    }

    public int hashCode() {
        IntegerPolynomial integerPolynomial = this.h;
        return (((integerPolynomial == null ? 0 : integerPolynomial.hashCode()) + 31) * 31) + (this.params != null ? this.params.hashCode() : 0);
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        outputStream.write(getEncoded());
    }
}
