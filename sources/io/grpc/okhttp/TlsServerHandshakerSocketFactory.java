package io.grpc.okhttp;

import io.grpc.Attributes;
import io.grpc.Grpc;
import io.grpc.InternalChannelz;
import io.grpc.SecurityLevel;
import io.grpc.internal.GrpcAttributes;
import io.grpc.okhttp.HandshakerSocketFactory;
import io.grpc.okhttp.SslSocketFactoryServerCredentials;
import io.grpc.okhttp.internal.ConnectionSpec;
import io.grpc.okhttp.internal.Protocol;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/* loaded from: classes6.dex */
final class TlsServerHandshakerSocketFactory implements HandshakerSocketFactory {
    private final ConnectionSpec connectionSpec;
    private final PlaintextHandshakerSocketFactory delegate = new PlaintextHandshakerSocketFactory();
    private final SSLSocketFactory socketFactory;

    public TlsServerHandshakerSocketFactory(SslSocketFactoryServerCredentials.ServerCredentials serverCredentials) {
        this.socketFactory = serverCredentials.getFactory();
        this.connectionSpec = serverCredentials.getConnectionSpec();
    }

    @Override // io.grpc.okhttp.HandshakerSocketFactory
    public HandshakerSocketFactory.HandshakeResult handshake(Socket socket, Attributes attributes) throws IOException {
        HandshakerSocketFactory.HandshakeResult handshakeResultHandshake = this.delegate.handshake(socket, attributes);
        Socket socketCreateSocket = this.socketFactory.createSocket(handshakeResultHandshake.socket, (String) null, -1, true);
        if (!(socketCreateSocket instanceof SSLSocket)) {
            throw new IOException("SocketFactory " + this.socketFactory + " did not produce an SSLSocket: " + socketCreateSocket.getClass());
        }
        SSLSocket sSLSocket = (SSLSocket) socketCreateSocket;
        sSLSocket.setUseClientMode(false);
        this.connectionSpec.apply(sSLSocket, false);
        Protocol protocol = Protocol.HTTP_2;
        String strNegotiate = OkHttpProtocolNegotiator.get().negotiate(sSLSocket, null, this.connectionSpec.supportsTlsExtensions() ? Arrays.asList(protocol) : null);
        if (!protocol.toString().equals(strNegotiate)) {
            throw new IOException("Expected NPN/ALPN " + protocol + ": " + strNegotiate);
        }
        return new HandshakerSocketFactory.HandshakeResult(socketCreateSocket, handshakeResultHandshake.attributes.toBuilder().set(GrpcAttributes.ATTR_SECURITY_LEVEL, SecurityLevel.PRIVACY_AND_INTEGRITY).set(Grpc.TRANSPORT_ATTR_SSL_SESSION, sSLSocket.getSession()).build(), new InternalChannelz.Security(new InternalChannelz.Tls(sSLSocket.getSession())));
    }
}
