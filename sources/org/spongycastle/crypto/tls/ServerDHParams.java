package org.spongycastle.crypto.tls;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import org.spongycastle.crypto.params.DHParameters;
import org.spongycastle.crypto.params.DHPublicKeyParameters;

/* loaded from: classes7.dex */
public class ServerDHParams {
    protected DHPublicKeyParameters publicKey;

    public DHPublicKeyParameters getPublicKey() {
        return this.publicKey;
    }

    public ServerDHParams(DHPublicKeyParameters dHPublicKeyParameters) {
        if (dHPublicKeyParameters == null) {
            throw new IllegalArgumentException("'publicKey' cannot be null");
        }
        this.publicKey = dHPublicKeyParameters;
    }

    public void encode(OutputStream outputStream) throws IOException {
        DHParameters parameters = this.publicKey.getParameters();
        BigInteger y = this.publicKey.getY();
        TlsDHUtils.writeDHParameter(parameters.getP(), outputStream);
        TlsDHUtils.writeDHParameter(parameters.getG(), outputStream);
        TlsDHUtils.writeDHParameter(y, outputStream);
    }

    public static ServerDHParams parse(InputStream inputStream) throws IOException {
        return new ServerDHParams(TlsDHUtils.validateDHPublicKey(new DHPublicKeyParameters(TlsDHUtils.readDHParameter(inputStream), new DHParameters(TlsDHUtils.readDHParameter(inputStream), TlsDHUtils.readDHParameter(inputStream)))));
    }
}
