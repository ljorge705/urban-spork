package org.spongycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.spongycastle.crypto.tls.TlsProtocol;
import org.spongycastle.util.Arrays;

/* loaded from: classes7.dex */
public class TlsClientProtocol extends TlsProtocol {
    protected TlsAuthentication authentication;
    protected CertificateRequest certificateRequest;
    protected CertificateStatus certificateStatus;
    protected TlsKeyExchange keyExchange;
    protected byte[] selectedSessionID;
    protected TlsClient tlsClient;
    TlsClientContextImpl tlsClientContext;

    @Override // org.spongycastle.crypto.tls.TlsProtocol
    protected TlsContext getContext() {
        return this.tlsClientContext;
    }

    @Override // org.spongycastle.crypto.tls.TlsProtocol
    AbstractTlsContext getContextAdmin() {
        return this.tlsClientContext;
    }

    @Override // org.spongycastle.crypto.tls.TlsProtocol
    protected TlsPeer getPeer() {
        return this.tlsClient;
    }

    public TlsClientProtocol(InputStream inputStream, OutputStream outputStream, SecureRandom secureRandom) {
        super(inputStream, outputStream, secureRandom);
        this.tlsClient = null;
        this.tlsClientContext = null;
        this.selectedSessionID = null;
        this.keyExchange = null;
        this.authentication = null;
        this.certificateStatus = null;
        this.certificateRequest = null;
    }

    public TlsClientProtocol(SecureRandom secureRandom) {
        super(secureRandom);
        this.tlsClient = null;
        this.tlsClientContext = null;
        this.selectedSessionID = null;
        this.keyExchange = null;
        this.authentication = null;
        this.certificateStatus = null;
        this.certificateRequest = null;
    }

    public void connect(TlsClient tlsClient) throws IOException {
        SessionParameters sessionParametersExportSessionParameters;
        if (tlsClient == null) {
            throw new IllegalArgumentException("'tlsClient' cannot be null");
        }
        if (this.tlsClient != null) {
            throw new IllegalStateException("'connect' can only be called once");
        }
        this.tlsClient = tlsClient;
        this.securityParameters = new SecurityParameters();
        this.securityParameters.entity = 1;
        this.tlsClientContext = new TlsClientContextImpl(this.secureRandom, this.securityParameters);
        this.securityParameters.clientRandom = createRandomBlock(tlsClient.shouldUseGMTUnixTime(), this.tlsClientContext.getNonceRandomGenerator());
        this.tlsClient.init(this.tlsClientContext);
        this.recordStream.init(this.tlsClientContext);
        TlsSession sessionToResume = tlsClient.getSessionToResume();
        if (sessionToResume != null && sessionToResume.isResumable() && (sessionParametersExportSessionParameters = sessionToResume.exportSessionParameters()) != null) {
            this.tlsSession = sessionToResume;
            this.sessionParameters = sessionParametersExportSessionParameters;
        }
        sendClientHelloMessage();
        this.connection_state = (short) 1;
        blockForHandshake();
    }

    @Override // org.spongycastle.crypto.tls.TlsProtocol
    protected void cleanupHandshake() {
        super.cleanupHandshake();
        this.selectedSessionID = null;
        this.keyExchange = null;
        this.authentication = null;
        this.certificateStatus = null;
        this.certificateRequest = null;
    }

    @Override // org.spongycastle.crypto.tls.TlsProtocol
    protected void handleHandshakeMessage(short s, ByteArrayInputStream byteArrayInputStream) throws IOException {
        TlsCredentials clientCredentials;
        byte[] finalHash;
        if (this.resumedSession) {
            if (s != 20 || this.connection_state != 2) {
                throw new TlsFatalAlert((short) 10);
            }
            processFinishedMessage(byteArrayInputStream);
            this.connection_state = (short) 15;
            sendFinishedMessage();
            this.connection_state = (short) 13;
            completeHandshake();
            return;
        }
        if (s == 0) {
            assertEmpty(byteArrayInputStream);
            if (this.connection_state == 16) {
                refuseRenegotiation();
                return;
            }
            return;
        }
        if (s == 2) {
            if (this.connection_state == 1) {
                receiveServerHelloMessage(byteArrayInputStream);
                this.connection_state = (short) 2;
                this.recordStream.notifyHelloComplete();
                applyMaxFragmentLengthExtension();
                if (this.resumedSession) {
                    this.securityParameters.masterSecret = Arrays.clone(this.sessionParameters.getMasterSecret());
                    this.recordStream.setPendingConnectionState(getPeer().getCompression(), getPeer().getCipher());
                    sendChangeCipherSpecMessage();
                    return;
                }
                invalidateSession();
                if (this.selectedSessionID.length > 0) {
                    this.tlsSession = new TlsSessionImpl(this.selectedSessionID, null);
                    return;
                }
                return;
            }
            throw new TlsFatalAlert((short) 10);
        }
        if (s == 4) {
            if (this.connection_state == 13) {
                if (!this.expectSessionTicket) {
                    throw new TlsFatalAlert((short) 10);
                }
                invalidateSession();
                receiveNewSessionTicketMessage(byteArrayInputStream);
                this.connection_state = (short) 14;
                return;
            }
            throw new TlsFatalAlert((short) 10);
        }
        if (s == 20) {
            short s2 = this.connection_state;
            if (s2 != 13) {
                if (s2 != 14) {
                    throw new TlsFatalAlert((short) 10);
                }
            } else if (this.expectSessionTicket) {
                throw new TlsFatalAlert((short) 10);
            }
            processFinishedMessage(byteArrayInputStream);
            this.connection_state = (short) 15;
            completeHandshake();
            return;
        }
        if (s == 22) {
            if (this.connection_state == 4) {
                if (!this.allowCertificateStatus) {
                    throw new TlsFatalAlert((short) 10);
                }
                this.certificateStatus = CertificateStatus.parse(byteArrayInputStream);
                assertEmpty(byteArrayInputStream);
                this.connection_state = (short) 5;
                return;
            }
            throw new TlsFatalAlert((short) 10);
        }
        if (s != 23) {
            switch (s) {
                case 11:
                    short s3 = this.connection_state;
                    if (s3 == 2) {
                        handleSupplementalData(null);
                    } else if (s3 != 3) {
                        throw new TlsFatalAlert((short) 10);
                    }
                    this.peerCertificate = Certificate.parse(byteArrayInputStream);
                    assertEmpty(byteArrayInputStream);
                    if (this.peerCertificate == null || this.peerCertificate.isEmpty()) {
                        this.allowCertificateStatus = false;
                    }
                    this.keyExchange.processServerCertificate(this.peerCertificate);
                    TlsAuthentication authentication = this.tlsClient.getAuthentication();
                    this.authentication = authentication;
                    authentication.notifyServerCertificate(this.peerCertificate);
                    this.connection_state = (short) 4;
                    return;
                case 12:
                    short s4 = this.connection_state;
                    if (s4 == 2) {
                        handleSupplementalData(null);
                    } else {
                        if (s4 != 3) {
                            if (s4 != 4 && s4 != 5) {
                                throw new TlsFatalAlert((short) 10);
                            }
                        }
                        this.keyExchange.processServerKeyExchange(byteArrayInputStream);
                        assertEmpty(byteArrayInputStream);
                        this.connection_state = (short) 6;
                        return;
                    }
                    this.keyExchange.skipServerCredentials();
                    this.authentication = null;
                    this.keyExchange.processServerKeyExchange(byteArrayInputStream);
                    assertEmpty(byteArrayInputStream);
                    this.connection_state = (short) 6;
                    return;
                case 13:
                    short s5 = this.connection_state;
                    if (s5 == 4 || s5 == 5) {
                        this.keyExchange.skipServerKeyExchange();
                    } else if (s5 != 6) {
                        throw new TlsFatalAlert((short) 10);
                    }
                    if (this.authentication == null) {
                        throw new TlsFatalAlert((short) 40);
                    }
                    this.certificateRequest = CertificateRequest.parse(getContext(), byteArrayInputStream);
                    assertEmpty(byteArrayInputStream);
                    this.keyExchange.validateCertificateRequest(this.certificateRequest);
                    TlsUtils.trackHashAlgorithms(this.recordStream.getHandshakeHash(), this.certificateRequest.getSupportedSignatureAlgorithms());
                    this.connection_state = (short) 7;
                    return;
                case 14:
                    switch (this.connection_state) {
                        case 2:
                            handleSupplementalData(null);
                        case 3:
                            this.keyExchange.skipServerCredentials();
                            this.authentication = null;
                        case 4:
                        case 5:
                            this.keyExchange.skipServerKeyExchange();
                        case 6:
                        case 7:
                            assertEmpty(byteArrayInputStream);
                            this.connection_state = (short) 8;
                            this.recordStream.getHandshakeHash().sealHashAlgorithms();
                            Vector clientSupplementalData = this.tlsClient.getClientSupplementalData();
                            if (clientSupplementalData != null) {
                                sendSupplementalDataMessage(clientSupplementalData);
                            }
                            this.connection_state = (short) 9;
                            CertificateRequest certificateRequest = this.certificateRequest;
                            if (certificateRequest == null) {
                                this.keyExchange.skipClientCredentials();
                                clientCredentials = null;
                            } else {
                                clientCredentials = this.authentication.getClientCredentials(certificateRequest);
                                if (clientCredentials == null) {
                                    this.keyExchange.skipClientCredentials();
                                    sendCertificateMessage(Certificate.EMPTY_CHAIN);
                                } else {
                                    this.keyExchange.processClientCredentials(clientCredentials);
                                    sendCertificateMessage(clientCredentials.getCertificate());
                                }
                            }
                            this.connection_state = (short) 10;
                            sendClientKeyExchangeMessage();
                            this.connection_state = (short) 11;
                            if (TlsUtils.isSSL(getContext())) {
                                establishMasterSecret(getContext(), this.keyExchange);
                            }
                            TlsHandshakeHash tlsHandshakeHashPrepareToFinish = this.recordStream.prepareToFinish();
                            this.securityParameters.sessionHash = getCurrentPRFHash(getContext(), tlsHandshakeHashPrepareToFinish, null);
                            if (!TlsUtils.isSSL(getContext())) {
                                establishMasterSecret(getContext(), this.keyExchange);
                            }
                            this.recordStream.setPendingConnectionState(getPeer().getCompression(), getPeer().getCipher());
                            if (clientCredentials != null && (clientCredentials instanceof TlsSignerCredentials)) {
                                TlsSignerCredentials tlsSignerCredentials = (TlsSignerCredentials) clientCredentials;
                                SignatureAndHashAlgorithm signatureAndHashAlgorithm = TlsUtils.getSignatureAndHashAlgorithm(getContext(), tlsSignerCredentials);
                                if (signatureAndHashAlgorithm == null) {
                                    finalHash = this.securityParameters.getSessionHash();
                                } else {
                                    finalHash = tlsHandshakeHashPrepareToFinish.getFinalHash(signatureAndHashAlgorithm.getHash());
                                }
                                sendCertificateVerifyMessage(new DigitallySigned(signatureAndHashAlgorithm, tlsSignerCredentials.generateCertificateSignature(finalHash)));
                                this.connection_state = (short) 12;
                            }
                            sendChangeCipherSpecMessage();
                            sendFinishedMessage();
                            this.connection_state = (short) 13;
                            return;
                        default:
                            throw new TlsFatalAlert((short) 10);
                    }
                    break;
                default:
                    throw new TlsFatalAlert((short) 10);
            }
        } else {
            if (this.connection_state == 2) {
                handleSupplementalData(readSupplementalDataMessage(byteArrayInputStream));
                return;
            }
            throw new TlsFatalAlert((short) 10);
        }
    }

    protected void handleSupplementalData(Vector vector) throws IOException {
        this.tlsClient.processServerSupplementalData(vector);
        this.connection_state = (short) 3;
        TlsKeyExchange keyExchange = this.tlsClient.getKeyExchange();
        this.keyExchange = keyExchange;
        keyExchange.init(getContext());
    }

    protected void receiveNewSessionTicketMessage(ByteArrayInputStream byteArrayInputStream) throws IOException {
        NewSessionTicket newSessionTicket = NewSessionTicket.parse(byteArrayInputStream);
        assertEmpty(byteArrayInputStream);
        this.tlsClient.notifyNewSessionTicket(newSessionTicket);
    }

    protected void receiveServerHelloMessage(ByteArrayInputStream byteArrayInputStream) throws IOException {
        ProtocolVersion version = TlsUtils.readVersion(byteArrayInputStream);
        if (version.isDTLS()) {
            throw new TlsFatalAlert((short) 47);
        }
        if (!version.equals(this.recordStream.getReadVersion())) {
            throw new TlsFatalAlert((short) 47);
        }
        if (!version.isEqualOrEarlierVersionOf(getContext().getClientVersion())) {
            throw new TlsFatalAlert((short) 47);
        }
        this.recordStream.setWriteVersion(version);
        getContextAdmin().setServerVersion(version);
        this.tlsClient.notifyServerVersion(version);
        this.securityParameters.serverRandom = TlsUtils.readFully(32, byteArrayInputStream);
        byte[] opaque8 = TlsUtils.readOpaque8(byteArrayInputStream);
        this.selectedSessionID = opaque8;
        if (opaque8.length > 32) {
            throw new TlsFatalAlert((short) 47);
        }
        this.tlsClient.notifySessionID(opaque8);
        boolean z = false;
        this.resumedSession = this.selectedSessionID.length > 0 && this.tlsSession != null && Arrays.areEqual(this.selectedSessionID, this.tlsSession.getSessionID());
        int uint16 = TlsUtils.readUint16(byteArrayInputStream);
        if (!Arrays.contains(this.offeredCipherSuites, uint16) || uint16 == 0 || CipherSuite.isSCSV(uint16) || !TlsUtils.isValidCipherSuiteForVersion(uint16, getContext().getServerVersion())) {
            throw new TlsFatalAlert((short) 47);
        }
        this.tlsClient.notifySelectedCipherSuite(uint16);
        short uint8 = TlsUtils.readUint8(byteArrayInputStream);
        if (!Arrays.contains(this.offeredCompressionMethods, uint8)) {
            throw new TlsFatalAlert((short) 47);
        }
        this.tlsClient.notifySelectedCompressionMethod(uint8);
        this.serverExtensions = readExtensions(byteArrayInputStream);
        if (this.serverExtensions != null) {
            Enumeration enumerationKeys = this.serverExtensions.keys();
            while (enumerationKeys.hasMoreElements()) {
                Integer num = (Integer) enumerationKeys.nextElement();
                if (!num.equals(EXT_RenegotiationInfo)) {
                    if (TlsUtils.getExtensionData(this.clientExtensions, num) == null) {
                        throw new TlsFatalAlert(AlertDescription.unsupported_extension);
                    }
                    boolean z2 = this.resumedSession;
                }
            }
        }
        byte[] extensionData = TlsUtils.getExtensionData(this.serverExtensions, EXT_RenegotiationInfo);
        if (extensionData != null) {
            this.secure_renegotiation = true;
            if (!Arrays.constantTimeAreEqual(extensionData, createRenegotiationInfo(TlsUtils.EMPTY_BYTES))) {
                throw new TlsFatalAlert((short) 40);
            }
        }
        this.tlsClient.notifySecureRenegotiation(this.secure_renegotiation);
        Hashtable hashtable = this.clientExtensions;
        Hashtable serverExtensions = this.serverExtensions;
        if (this.resumedSession) {
            if (uint16 != this.sessionParameters.getCipherSuite() || uint8 != this.sessionParameters.getCompressionAlgorithm()) {
                throw new TlsFatalAlert((short) 47);
            }
            serverExtensions = this.sessionParameters.readServerExtensions();
            hashtable = null;
        }
        this.securityParameters.cipherSuite = uint16;
        this.securityParameters.compressionAlgorithm = uint8;
        if (serverExtensions != null) {
            boolean zHasEncryptThenMACExtension = TlsExtensionsUtils.hasEncryptThenMACExtension(serverExtensions);
            if (zHasEncryptThenMACExtension && !TlsUtils.isBlockCipherSuite(uint16)) {
                throw new TlsFatalAlert((short) 47);
            }
            this.securityParameters.encryptThenMAC = zHasEncryptThenMACExtension;
            this.securityParameters.extendedMasterSecret = TlsExtensionsUtils.hasExtendedMasterSecretExtension(serverExtensions);
            this.securityParameters.maxFragmentLength = processMaxFragmentLengthExtension(hashtable, serverExtensions, (short) 47);
            this.securityParameters.truncatedHMac = TlsExtensionsUtils.hasTruncatedHMacExtension(serverExtensions);
            this.allowCertificateStatus = !this.resumedSession && TlsUtils.hasExpectedEmptyExtensionData(serverExtensions, TlsExtensionsUtils.EXT_status_request, (short) 47);
            if (!this.resumedSession && TlsUtils.hasExpectedEmptyExtensionData(serverExtensions, TlsProtocol.EXT_SessionTicket, (short) 47)) {
                z = true;
            }
            this.expectSessionTicket = z;
        }
        if (hashtable != null) {
            this.tlsClient.processServerExtensions(serverExtensions);
        }
        this.securityParameters.prfAlgorithm = getPRFAlgorithm(getContext(), this.securityParameters.getCipherSuite());
        this.securityParameters.verifyDataLength = 12;
    }

    protected void sendCertificateVerifyMessage(DigitallySigned digitallySigned) throws IOException {
        TlsProtocol.HandshakeMessage handshakeMessage = new TlsProtocol.HandshakeMessage(this, (short) 15);
        digitallySigned.encode(handshakeMessage);
        handshakeMessage.writeToRecordStream();
    }

    protected void sendClientHelloMessage() throws IOException {
        this.recordStream.setWriteVersion(this.tlsClient.getClientHelloRecordLayerVersion());
        ProtocolVersion clientVersion = this.tlsClient.getClientVersion();
        if (clientVersion.isDTLS()) {
            throw new TlsFatalAlert((short) 80);
        }
        getContextAdmin().setClientVersion(clientVersion);
        byte[] sessionID = TlsUtils.EMPTY_BYTES;
        if (this.tlsSession != null && ((sessionID = this.tlsSession.getSessionID()) == null || sessionID.length > 32)) {
            sessionID = TlsUtils.EMPTY_BYTES;
        }
        boolean zIsFallback = this.tlsClient.isFallback();
        this.offeredCipherSuites = this.tlsClient.getCipherSuites();
        this.offeredCompressionMethods = this.tlsClient.getCompressionMethods();
        if (sessionID.length > 0 && this.sessionParameters != null && (!Arrays.contains(this.offeredCipherSuites, this.sessionParameters.getCipherSuite()) || !Arrays.contains(this.offeredCompressionMethods, this.sessionParameters.getCompressionAlgorithm()))) {
            sessionID = TlsUtils.EMPTY_BYTES;
        }
        this.clientExtensions = this.tlsClient.getClientExtensions();
        TlsProtocol.HandshakeMessage handshakeMessage = new TlsProtocol.HandshakeMessage(this, (short) 1);
        TlsUtils.writeVersion(clientVersion, handshakeMessage);
        handshakeMessage.write(this.securityParameters.getClientRandom());
        TlsUtils.writeOpaque8(sessionID, handshakeMessage);
        boolean z = TlsUtils.getExtensionData(this.clientExtensions, EXT_RenegotiationInfo) == null;
        boolean z2 = !Arrays.contains(this.offeredCipherSuites, 255);
        if (z && z2) {
            this.offeredCipherSuites = Arrays.append(this.offeredCipherSuites, 255);
        }
        if (zIsFallback && !Arrays.contains(this.offeredCipherSuites, CipherSuite.TLS_FALLBACK_SCSV)) {
            this.offeredCipherSuites = Arrays.append(this.offeredCipherSuites, CipherSuite.TLS_FALLBACK_SCSV);
        }
        TlsUtils.writeUint16ArrayWithUint16Length(this.offeredCipherSuites, handshakeMessage);
        TlsUtils.writeUint8ArrayWithUint8Length(this.offeredCompressionMethods, handshakeMessage);
        if (this.clientExtensions != null) {
            writeExtensions(handshakeMessage, this.clientExtensions);
        }
        handshakeMessage.writeToRecordStream();
    }

    protected void sendClientKeyExchangeMessage() throws IOException {
        TlsProtocol.HandshakeMessage handshakeMessage = new TlsProtocol.HandshakeMessage(this, (short) 16);
        this.keyExchange.generateClientKeyExchange(handshakeMessage);
        handshakeMessage.writeToRecordStream();
    }
}
