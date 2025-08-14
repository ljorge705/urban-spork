package org.spongycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.util.Hashtable;
import java.util.Vector;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.tls.DTLSReliableHandshake;
import org.spongycastle.crypto.tls.SessionParameters;
import org.spongycastle.crypto.util.PublicKeyFactory;
import org.spongycastle.util.Arrays;

/* loaded from: classes7.dex */
public class DTLSServerProtocol extends DTLSProtocol {
    protected boolean verifyRequests;

    public boolean getVerifyRequests() {
        return this.verifyRequests;
    }

    public void setVerifyRequests(boolean z) {
        this.verifyRequests = z;
    }

    public DTLSServerProtocol(SecureRandom secureRandom) {
        super(secureRandom);
        this.verifyRequests = true;
    }

    public DTLSTransport accept(TlsServer tlsServer, DatagramTransport datagramTransport) throws IOException {
        if (tlsServer == null) {
            throw new IllegalArgumentException("'server' cannot be null");
        }
        if (datagramTransport == null) {
            throw new IllegalArgumentException("'transport' cannot be null");
        }
        SecurityParameters securityParameters = new SecurityParameters();
        securityParameters.entity = 0;
        ServerHandshakeState serverHandshakeState = new ServerHandshakeState();
        serverHandshakeState.server = tlsServer;
        serverHandshakeState.serverContext = new TlsServerContextImpl(this.secureRandom, securityParameters);
        securityParameters.serverRandom = TlsProtocol.createRandomBlock(tlsServer.shouldUseGMTUnixTime(), serverHandshakeState.serverContext.getNonceRandomGenerator());
        tlsServer.init(serverHandshakeState.serverContext);
        DTLSRecordLayer dTLSRecordLayer = new DTLSRecordLayer(datagramTransport, serverHandshakeState.serverContext, tlsServer, (short) 22);
        try {
            try {
                try {
                    return serverHandshake(serverHandshakeState, dTLSRecordLayer);
                } catch (IOException e) {
                    abortServerHandshake(serverHandshakeState, dTLSRecordLayer, (short) 80);
                    throw e;
                }
            } catch (RuntimeException e2) {
                abortServerHandshake(serverHandshakeState, dTLSRecordLayer, (short) 80);
                throw new TlsFatalAlert((short) 80, e2);
            } catch (TlsFatalAlert e3) {
                abortServerHandshake(serverHandshakeState, dTLSRecordLayer, e3.getAlertDescription());
                throw e3;
            }
        } finally {
            securityParameters.clear();
        }
    }

    protected void abortServerHandshake(ServerHandshakeState serverHandshakeState, DTLSRecordLayer dTLSRecordLayer, short s) {
        dTLSRecordLayer.fail(s);
        invalidateSession(serverHandshakeState);
    }

    protected DTLSTransport serverHandshake(ServerHandshakeState serverHandshakeState, DTLSRecordLayer dTLSRecordLayer) throws IOException {
        Certificate certificate;
        CertificateStatus certificateStatus;
        SecurityParameters securityParameters = serverHandshakeState.serverContext.getSecurityParameters();
        DTLSReliableHandshake dTLSReliableHandshake = new DTLSReliableHandshake(serverHandshakeState.serverContext, dTLSRecordLayer);
        DTLSReliableHandshake.Message messageReceiveMessage = dTLSReliableHandshake.receiveMessage();
        if (messageReceiveMessage.getType() == 1) {
            processClientHello(serverHandshakeState, messageReceiveMessage.getBody());
            byte[] bArrGenerateServerHello = generateServerHello(serverHandshakeState);
            applyMaxFragmentLengthExtension(dTLSRecordLayer, securityParameters.maxFragmentLength);
            ProtocolVersion serverVersion = serverHandshakeState.serverContext.getServerVersion();
            dTLSRecordLayer.setReadVersion(serverVersion);
            dTLSRecordLayer.setWriteVersion(serverVersion);
            dTLSReliableHandshake.sendMessage((short) 2, bArrGenerateServerHello);
            dTLSReliableHandshake.notifyHelloComplete();
            Vector serverSupplementalData = serverHandshakeState.server.getServerSupplementalData();
            if (serverSupplementalData != null) {
                dTLSReliableHandshake.sendMessage((short) 23, generateSupplementalData(serverSupplementalData));
            }
            serverHandshakeState.keyExchange = serverHandshakeState.server.getKeyExchange();
            serverHandshakeState.keyExchange.init(serverHandshakeState.serverContext);
            serverHandshakeState.serverCredentials = serverHandshakeState.server.getCredentials();
            if (serverHandshakeState.serverCredentials == null) {
                serverHandshakeState.keyExchange.skipServerCredentials();
                certificate = null;
            } else {
                serverHandshakeState.keyExchange.processServerCredentials(serverHandshakeState.serverCredentials);
                certificate = serverHandshakeState.serverCredentials.getCertificate();
                dTLSReliableHandshake.sendMessage((short) 11, generateCertificate(certificate));
            }
            if (certificate == null || certificate.isEmpty()) {
                serverHandshakeState.allowCertificateStatus = false;
            }
            if (serverHandshakeState.allowCertificateStatus && (certificateStatus = serverHandshakeState.server.getCertificateStatus()) != null) {
                dTLSReliableHandshake.sendMessage((short) 22, generateCertificateStatus(serverHandshakeState, certificateStatus));
            }
            byte[] bArrGenerateServerKeyExchange = serverHandshakeState.keyExchange.generateServerKeyExchange();
            if (bArrGenerateServerKeyExchange != null) {
                dTLSReliableHandshake.sendMessage((short) 12, bArrGenerateServerKeyExchange);
            }
            if (serverHandshakeState.serverCredentials != null) {
                serverHandshakeState.certificateRequest = serverHandshakeState.server.getCertificateRequest();
                if (serverHandshakeState.certificateRequest != null) {
                    if (TlsUtils.isTLSv12(serverHandshakeState.serverContext) != (serverHandshakeState.certificateRequest.getSupportedSignatureAlgorithms() != null)) {
                        throw new TlsFatalAlert((short) 80);
                    }
                    serverHandshakeState.keyExchange.validateCertificateRequest(serverHandshakeState.certificateRequest);
                    dTLSReliableHandshake.sendMessage((short) 13, generateCertificateRequest(serverHandshakeState, serverHandshakeState.certificateRequest));
                    TlsUtils.trackHashAlgorithms(dTLSReliableHandshake.getHandshakeHash(), serverHandshakeState.certificateRequest.getSupportedSignatureAlgorithms());
                }
            }
            dTLSReliableHandshake.sendMessage((short) 14, TlsUtils.EMPTY_BYTES);
            dTLSReliableHandshake.getHandshakeHash().sealHashAlgorithms();
            DTLSReliableHandshake.Message messageReceiveMessage2 = dTLSReliableHandshake.receiveMessage();
            if (messageReceiveMessage2.getType() == 23) {
                processClientSupplementalData(serverHandshakeState, messageReceiveMessage2.getBody());
                messageReceiveMessage2 = dTLSReliableHandshake.receiveMessage();
            } else {
                serverHandshakeState.server.processClientSupplementalData(null);
            }
            if (serverHandshakeState.certificateRequest == null) {
                serverHandshakeState.keyExchange.skipClientCredentials();
            } else if (messageReceiveMessage2.getType() == 11) {
                processClientCertificate(serverHandshakeState, messageReceiveMessage2.getBody());
                messageReceiveMessage2 = dTLSReliableHandshake.receiveMessage();
            } else {
                if (TlsUtils.isTLSv12(serverHandshakeState.serverContext)) {
                    throw new TlsFatalAlert((short) 10);
                }
                notifyClientCertificate(serverHandshakeState, Certificate.EMPTY_CHAIN);
            }
            if (messageReceiveMessage2.getType() == 16) {
                processClientKeyExchange(serverHandshakeState, messageReceiveMessage2.getBody());
                TlsHandshakeHash tlsHandshakeHashPrepareToFinish = dTLSReliableHandshake.prepareToFinish();
                securityParameters.sessionHash = TlsProtocol.getCurrentPRFHash(serverHandshakeState.serverContext, tlsHandshakeHashPrepareToFinish, null);
                TlsProtocol.establishMasterSecret(serverHandshakeState.serverContext, serverHandshakeState.keyExchange);
                dTLSRecordLayer.initPendingEpoch(serverHandshakeState.server.getCipher());
                if (expectCertificateVerifyMessage(serverHandshakeState)) {
                    processCertificateVerify(serverHandshakeState, dTLSReliableHandshake.receiveMessageBody((short) 15), tlsHandshakeHashPrepareToFinish);
                }
                processFinished(dTLSReliableHandshake.receiveMessageBody((short) 20), TlsUtils.calculateVerifyData(serverHandshakeState.serverContext, ExporterLabel.client_finished, TlsProtocol.getCurrentPRFHash(serverHandshakeState.serverContext, dTLSReliableHandshake.getHandshakeHash(), null)));
                if (serverHandshakeState.expectSessionTicket) {
                    dTLSReliableHandshake.sendMessage((short) 4, generateNewSessionTicket(serverHandshakeState, serverHandshakeState.server.getNewSessionTicket()));
                }
                dTLSReliableHandshake.sendMessage((short) 20, TlsUtils.calculateVerifyData(serverHandshakeState.serverContext, ExporterLabel.server_finished, TlsProtocol.getCurrentPRFHash(serverHandshakeState.serverContext, dTLSReliableHandshake.getHandshakeHash(), null)));
                dTLSReliableHandshake.finish();
                serverHandshakeState.server.notifyHandshakeComplete();
                return new DTLSTransport(dTLSRecordLayer);
            }
            throw new TlsFatalAlert((short) 10);
        }
        throw new TlsFatalAlert((short) 10);
    }

    protected byte[] generateCertificateRequest(ServerHandshakeState serverHandshakeState, CertificateRequest certificateRequest) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        certificateRequest.encode(byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    protected byte[] generateCertificateStatus(ServerHandshakeState serverHandshakeState, CertificateStatus certificateStatus) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        certificateStatus.encode(byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    protected byte[] generateNewSessionTicket(ServerHandshakeState serverHandshakeState, NewSessionTicket newSessionTicket) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        newSessionTicket.encode(byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    protected byte[] generateServerHello(ServerHandshakeState serverHandshakeState) throws IOException {
        SecurityParameters securityParameters = serverHandshakeState.serverContext.getSecurityParameters();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ProtocolVersion serverVersion = serverHandshakeState.server.getServerVersion();
        if (!serverVersion.isEqualOrEarlierVersionOf(serverHandshakeState.serverContext.getClientVersion())) {
            throw new TlsFatalAlert((short) 80);
        }
        serverHandshakeState.serverContext.setServerVersion(serverVersion);
        TlsUtils.writeVersion(serverHandshakeState.serverContext.getServerVersion(), byteArrayOutputStream);
        byteArrayOutputStream.write(securityParameters.getServerRandom());
        TlsUtils.writeOpaque8(TlsUtils.EMPTY_BYTES, byteArrayOutputStream);
        int selectedCipherSuite = serverHandshakeState.server.getSelectedCipherSuite();
        if (!Arrays.contains(serverHandshakeState.offeredCipherSuites, selectedCipherSuite) || selectedCipherSuite == 0 || CipherSuite.isSCSV(selectedCipherSuite) || !TlsUtils.isValidCipherSuiteForVersion(selectedCipherSuite, serverHandshakeState.serverContext.getServerVersion())) {
            throw new TlsFatalAlert((short) 80);
        }
        validateSelectedCipherSuite(selectedCipherSuite, (short) 80);
        securityParameters.cipherSuite = selectedCipherSuite;
        short selectedCompressionMethod = serverHandshakeState.server.getSelectedCompressionMethod();
        if (!Arrays.contains(serverHandshakeState.offeredCompressionMethods, selectedCompressionMethod)) {
            throw new TlsFatalAlert((short) 80);
        }
        securityParameters.compressionAlgorithm = selectedCompressionMethod;
        TlsUtils.writeUint16(selectedCipherSuite, byteArrayOutputStream);
        TlsUtils.writeUint8(selectedCompressionMethod, (OutputStream) byteArrayOutputStream);
        serverHandshakeState.serverExtensions = serverHandshakeState.server.getServerExtensions();
        if (serverHandshakeState.secure_renegotiation && TlsUtils.getExtensionData(serverHandshakeState.serverExtensions, TlsProtocol.EXT_RenegotiationInfo) == null) {
            serverHandshakeState.serverExtensions = TlsExtensionsUtils.ensureExtensionsInitialised(serverHandshakeState.serverExtensions);
            serverHandshakeState.serverExtensions.put(TlsProtocol.EXT_RenegotiationInfo, TlsProtocol.createRenegotiationInfo(TlsUtils.EMPTY_BYTES));
        }
        if (securityParameters.extendedMasterSecret) {
            serverHandshakeState.serverExtensions = TlsExtensionsUtils.ensureExtensionsInitialised(serverHandshakeState.serverExtensions);
            TlsExtensionsUtils.addExtendedMasterSecretExtension(serverHandshakeState.serverExtensions);
        }
        if (serverHandshakeState.serverExtensions != null) {
            securityParameters.encryptThenMAC = TlsExtensionsUtils.hasEncryptThenMACExtension(serverHandshakeState.serverExtensions);
            securityParameters.maxFragmentLength = evaluateMaxFragmentLengthExtension(serverHandshakeState.resumedSession, serverHandshakeState.clientExtensions, serverHandshakeState.serverExtensions, (short) 80);
            securityParameters.truncatedHMac = TlsExtensionsUtils.hasTruncatedHMacExtension(serverHandshakeState.serverExtensions);
            serverHandshakeState.allowCertificateStatus = !serverHandshakeState.resumedSession && TlsUtils.hasExpectedEmptyExtensionData(serverHandshakeState.serverExtensions, TlsExtensionsUtils.EXT_status_request, (short) 80);
            serverHandshakeState.expectSessionTicket = !serverHandshakeState.resumedSession && TlsUtils.hasExpectedEmptyExtensionData(serverHandshakeState.serverExtensions, TlsProtocol.EXT_SessionTicket, (short) 80);
            TlsProtocol.writeExtensions(byteArrayOutputStream, serverHandshakeState.serverExtensions);
        }
        securityParameters.prfAlgorithm = TlsProtocol.getPRFAlgorithm(serverHandshakeState.serverContext, securityParameters.getCipherSuite());
        securityParameters.verifyDataLength = 12;
        return byteArrayOutputStream.toByteArray();
    }

    protected void invalidateSession(ServerHandshakeState serverHandshakeState) {
        if (serverHandshakeState.sessionParameters != null) {
            serverHandshakeState.sessionParameters.clear();
            serverHandshakeState.sessionParameters = null;
        }
        if (serverHandshakeState.tlsSession != null) {
            serverHandshakeState.tlsSession.invalidate();
            serverHandshakeState.tlsSession = null;
        }
    }

    protected void notifyClientCertificate(ServerHandshakeState serverHandshakeState, Certificate certificate) throws IOException {
        if (serverHandshakeState.certificateRequest == null) {
            throw new IllegalStateException();
        }
        if (serverHandshakeState.clientCertificate != null) {
            throw new TlsFatalAlert((short) 10);
        }
        serverHandshakeState.clientCertificate = certificate;
        if (certificate.isEmpty()) {
            serverHandshakeState.keyExchange.skipClientCredentials();
        } else {
            serverHandshakeState.clientCertificateType = TlsUtils.getClientCertificateType(certificate, serverHandshakeState.serverCredentials.getCertificate());
            serverHandshakeState.keyExchange.processClientCertificate(certificate);
        }
        serverHandshakeState.server.notifyClientCertificate(certificate);
    }

    protected void processClientCertificate(ServerHandshakeState serverHandshakeState, byte[] bArr) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        Certificate certificate = Certificate.parse(byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
        notifyClientCertificate(serverHandshakeState, certificate);
    }

    protected void processCertificateVerify(ServerHandshakeState serverHandshakeState, byte[] bArr, TlsHandshakeHash tlsHandshakeHash) throws IOException {
        byte[] sessionHash;
        if (serverHandshakeState.certificateRequest == null) {
            throw new IllegalStateException();
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        TlsServerContextImpl tlsServerContextImpl = serverHandshakeState.serverContext;
        DigitallySigned digitallySigned = DigitallySigned.parse(tlsServerContextImpl, byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
        try {
            SignatureAndHashAlgorithm algorithm = digitallySigned.getAlgorithm();
            if (TlsUtils.isTLSv12(tlsServerContextImpl)) {
                TlsUtils.verifySupportedSignatureAlgorithm(serverHandshakeState.certificateRequest.getSupportedSignatureAlgorithms(), algorithm);
                sessionHash = tlsHandshakeHash.getFinalHash(algorithm.getHash());
            } else {
                sessionHash = tlsServerContextImpl.getSecurityParameters().getSessionHash();
            }
            AsymmetricKeyParameter asymmetricKeyParameterCreateKey = PublicKeyFactory.createKey(serverHandshakeState.clientCertificate.getCertificateAt(0).getSubjectPublicKeyInfo());
            TlsSigner tlsSignerCreateTlsSigner = TlsUtils.createTlsSigner(serverHandshakeState.clientCertificateType);
            tlsSignerCreateTlsSigner.init(tlsServerContextImpl);
            if (tlsSignerCreateTlsSigner.verifyRawSignature(algorithm, digitallySigned.getSignature(), asymmetricKeyParameterCreateKey, sessionHash)) {
            } else {
                throw new TlsFatalAlert((short) 51);
            }
        } catch (TlsFatalAlert e) {
            throw e;
        } catch (Exception e2) {
            throw new TlsFatalAlert((short) 51, e2);
        }
    }

    protected void processClientHello(ServerHandshakeState serverHandshakeState, byte[] bArr) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        ProtocolVersion version = TlsUtils.readVersion(byteArrayInputStream);
        if (!version.isDTLS()) {
            throw new TlsFatalAlert((short) 47);
        }
        byte[] fully = TlsUtils.readFully(32, byteArrayInputStream);
        if (TlsUtils.readOpaque8(byteArrayInputStream).length > 32) {
            throw new TlsFatalAlert((short) 47);
        }
        TlsUtils.readOpaque8(byteArrayInputStream);
        int uint16 = TlsUtils.readUint16(byteArrayInputStream);
        if (uint16 < 2 || (uint16 & 1) != 0) {
            throw new TlsFatalAlert((short) 50);
        }
        serverHandshakeState.offeredCipherSuites = TlsUtils.readUint16Array(uint16 / 2, byteArrayInputStream);
        short uint8 = TlsUtils.readUint8(byteArrayInputStream);
        if (uint8 < 1) {
            throw new TlsFatalAlert((short) 47);
        }
        serverHandshakeState.offeredCompressionMethods = TlsUtils.readUint8Array(uint8, byteArrayInputStream);
        serverHandshakeState.clientExtensions = TlsProtocol.readExtensions(byteArrayInputStream);
        TlsServerContextImpl tlsServerContextImpl = serverHandshakeState.serverContext;
        SecurityParameters securityParameters = tlsServerContextImpl.getSecurityParameters();
        securityParameters.extendedMasterSecret = TlsExtensionsUtils.hasExtendedMasterSecretExtension(serverHandshakeState.clientExtensions);
        tlsServerContextImpl.setClientVersion(version);
        serverHandshakeState.server.notifyClientVersion(version);
        serverHandshakeState.server.notifyFallback(Arrays.contains(serverHandshakeState.offeredCipherSuites, CipherSuite.TLS_FALLBACK_SCSV));
        securityParameters.clientRandom = fully;
        serverHandshakeState.server.notifyOfferedCipherSuites(serverHandshakeState.offeredCipherSuites);
        serverHandshakeState.server.notifyOfferedCompressionMethods(serverHandshakeState.offeredCompressionMethods);
        if (Arrays.contains(serverHandshakeState.offeredCipherSuites, 255)) {
            serverHandshakeState.secure_renegotiation = true;
        }
        byte[] extensionData = TlsUtils.getExtensionData(serverHandshakeState.clientExtensions, TlsProtocol.EXT_RenegotiationInfo);
        if (extensionData != null) {
            serverHandshakeState.secure_renegotiation = true;
            if (!Arrays.constantTimeAreEqual(extensionData, TlsProtocol.createRenegotiationInfo(TlsUtils.EMPTY_BYTES))) {
                throw new TlsFatalAlert((short) 40);
            }
        }
        serverHandshakeState.server.notifySecureRenegotiation(serverHandshakeState.secure_renegotiation);
        if (serverHandshakeState.clientExtensions != null) {
            TlsExtensionsUtils.getPaddingExtension(serverHandshakeState.clientExtensions);
            serverHandshakeState.server.processClientExtensions(serverHandshakeState.clientExtensions);
        }
    }

    protected void processClientKeyExchange(ServerHandshakeState serverHandshakeState, byte[] bArr) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        serverHandshakeState.keyExchange.processClientKeyExchange(byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
    }

    protected void processClientSupplementalData(ServerHandshakeState serverHandshakeState, byte[] bArr) throws IOException {
        serverHandshakeState.server.processClientSupplementalData(TlsProtocol.readSupplementalDataMessage(new ByteArrayInputStream(bArr)));
    }

    protected boolean expectCertificateVerifyMessage(ServerHandshakeState serverHandshakeState) {
        return serverHandshakeState.clientCertificateType >= 0 && TlsUtils.hasSigningCapability(serverHandshakeState.clientCertificateType);
    }

    protected static class ServerHandshakeState {
        TlsServer server = null;
        TlsServerContextImpl serverContext = null;
        TlsSession tlsSession = null;
        SessionParameters sessionParameters = null;
        SessionParameters.Builder sessionParametersBuilder = null;
        int[] offeredCipherSuites = null;
        short[] offeredCompressionMethods = null;
        Hashtable clientExtensions = null;
        Hashtable serverExtensions = null;
        boolean resumedSession = false;
        boolean secure_renegotiation = false;
        boolean allowCertificateStatus = false;
        boolean expectSessionTicket = false;
        TlsKeyExchange keyExchange = null;
        TlsCredentials serverCredentials = null;
        CertificateRequest certificateRequest = null;
        short clientCertificateType = -1;
        Certificate clientCertificate = null;

        protected ServerHandshakeState() {
        }
    }
}
