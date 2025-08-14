package org.spongycastle.pqc.crypto.ntru;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.pqc.math.ntru.polynomial.IntegerPolynomial;

/* loaded from: classes7.dex */
public class NTRUSigningPublicKeyParameters extends AsymmetricKeyParameter {
    public IntegerPolynomial h;
    private NTRUSigningParameters params;

    public NTRUSigningPublicKeyParameters(IntegerPolynomial integerPolynomial, NTRUSigningParameters nTRUSigningParameters) {
        super(false);
        this.h = integerPolynomial;
        this.params = nTRUSigningParameters;
    }

    public NTRUSigningPublicKeyParameters(byte[] bArr, NTRUSigningParameters nTRUSigningParameters) {
        super(false);
        this.h = IntegerPolynomial.fromBinary(bArr, nTRUSigningParameters.N, nTRUSigningParameters.q);
        this.params = nTRUSigningParameters;
    }

    public NTRUSigningPublicKeyParameters(InputStream inputStream, NTRUSigningParameters nTRUSigningParameters) throws IOException {
        super(false);
        this.h = IntegerPolynomial.fromBinary(inputStream, nTRUSigningParameters.N, nTRUSigningParameters.q);
        this.params = nTRUSigningParameters;
    }

    public byte[] getEncoded() {
        return this.h.toBinary(this.params.q);
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        outputStream.write(getEncoded());
    }

    public int hashCode() {
        IntegerPolynomial integerPolynomial = this.h;
        int iHashCode = ((integerPolynomial == null ? 0 : integerPolynomial.hashCode()) + 31) * 31;
        NTRUSigningParameters nTRUSigningParameters = this.params;
        return iHashCode + (nTRUSigningParameters != null ? nTRUSigningParameters.hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        NTRUSigningPublicKeyParameters nTRUSigningPublicKeyParameters = (NTRUSigningPublicKeyParameters) obj;
        IntegerPolynomial integerPolynomial = this.h;
        if (integerPolynomial == null) {
            if (nTRUSigningPublicKeyParameters.h != null) {
                return false;
            }
        } else if (!integerPolynomial.equals(nTRUSigningPublicKeyParameters.h)) {
            return false;
        }
        NTRUSigningParameters nTRUSigningParameters = this.params;
        if (nTRUSigningParameters == null) {
            if (nTRUSigningPublicKeyParameters.params != null) {
                return false;
            }
        } else if (!nTRUSigningParameters.equals(nTRUSigningPublicKeyParameters.params)) {
            return false;
        }
        return true;
    }
}
