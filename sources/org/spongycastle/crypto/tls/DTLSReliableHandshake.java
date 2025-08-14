package org.spongycastle.crypto.tls;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.spongycastle.util.Integers;

/* loaded from: classes7.dex */
class DTLSReliableHandshake {
    private static final int MAX_RECEIVE_AHEAD = 16;
    private static final int MESSAGE_HEADER_LENGTH = 12;
    private TlsHandshakeHash handshakeHash;
    private DTLSRecordLayer recordLayer;
    private Hashtable currentInboundFlight = new Hashtable();
    private Hashtable previousInboundFlight = null;
    private Vector outboundFlight = new Vector();
    private boolean sending = true;
    private int message_seq = 0;
    private int next_receive_seq = 0;

    TlsHandshakeHash getHandshakeHash() {
        return this.handshakeHash;
    }

    DTLSReliableHandshake(TlsContext tlsContext, DTLSRecordLayer dTLSRecordLayer) {
        this.recordLayer = dTLSRecordLayer;
        DeferredHash deferredHash = new DeferredHash();
        this.handshakeHash = deferredHash;
        deferredHash.init(tlsContext);
    }

    void notifyHelloComplete() {
        this.handshakeHash = this.handshakeHash.notifyPRFDetermined();
    }

    TlsHandshakeHash prepareToFinish() {
        TlsHandshakeHash tlsHandshakeHash = this.handshakeHash;
        this.handshakeHash = tlsHandshakeHash.stopTracking();
        return tlsHandshakeHash;
    }

    void sendMessage(short s, byte[] bArr) throws IOException {
        TlsUtils.checkUint24(bArr.length);
        if (!this.sending) {
            checkInboundFlight();
            this.sending = true;
            this.outboundFlight.removeAllElements();
        }
        int i = this.message_seq;
        this.message_seq = i + 1;
        Message message = new Message(i, s, bArr);
        this.outboundFlight.addElement(message);
        writeMessage(message);
        updateHandshakeMessagesDigest(message);
    }

    byte[] receiveMessageBody(short s) throws IOException {
        Message messageReceiveMessage = receiveMessage();
        if (messageReceiveMessage.getType() != s) {
            throw new TlsFatalAlert((short) 10);
        }
        return messageReceiveMessage.getBody();
    }

    Message receiveMessage() throws IOException {
        Message pendingMessage;
        if (this.sending) {
            this.sending = false;
            prepareInboundFlight(new Hashtable());
        }
        byte[] bArr = null;
        int iBackOff = 1000;
        while (true) {
            try {
                pendingMessage = getPendingMessage();
            } catch (IOException unused) {
            }
            if (pendingMessage != null) {
                return pendingMessage;
            }
            int receiveLimit = this.recordLayer.getReceiveLimit();
            if (bArr == null || bArr.length < receiveLimit) {
                bArr = new byte[receiveLimit];
            }
            int iReceive = this.recordLayer.receive(bArr, 0, receiveLimit, iBackOff);
            if (iReceive >= 0) {
                if (processRecord(16, this.recordLayer.getReadEpoch(), bArr, 0, iReceive)) {
                    iBackOff = backOff(iBackOff);
                }
            } else {
                resendOutboundFlight();
                iBackOff = backOff(iBackOff);
            }
        }
    }

    void finish() {
        DTLSHandshakeRetransmit dTLSHandshakeRetransmit = null;
        if (!this.sending) {
            checkInboundFlight();
        } else {
            prepareInboundFlight(null);
            if (this.previousInboundFlight != null) {
                dTLSHandshakeRetransmit = new DTLSHandshakeRetransmit() { // from class: org.spongycastle.crypto.tls.DTLSReliableHandshake.1
                    @Override // org.spongycastle.crypto.tls.DTLSHandshakeRetransmit
                    public void receivedHandshakeRecord(int i, byte[] bArr, int i2, int i3) throws IOException {
                        DTLSReliableHandshake.this.processRecord(0, i, bArr, i2, i3);
                    }
                };
            }
        }
        this.recordLayer.handshakeSuccessful(dTLSHandshakeRetransmit);
    }

    void resetHandshakeMessagesDigest() {
        this.handshakeHash.reset();
    }

    private int backOff(int i) {
        return Math.min(i * 2, 60000);
    }

    private void checkInboundFlight() {
        Enumeration enumerationKeys = this.currentInboundFlight.keys();
        while (enumerationKeys.hasMoreElements()) {
            ((Integer) enumerationKeys.nextElement()).intValue();
        }
    }

    private Message getPendingMessage() throws IOException {
        byte[] bodyIfComplete;
        DTLSReassembler dTLSReassembler = (DTLSReassembler) this.currentInboundFlight.get(Integers.valueOf(this.next_receive_seq));
        if (dTLSReassembler == null || (bodyIfComplete = dTLSReassembler.getBodyIfComplete()) == null) {
            return null;
        }
        this.previousInboundFlight = null;
        int i = this.next_receive_seq;
        this.next_receive_seq = i + 1;
        return updateHandshakeMessagesDigest(new Message(i, dTLSReassembler.getMsgType(), bodyIfComplete));
    }

    private void prepareInboundFlight(Hashtable hashtable) {
        resetAll(this.currentInboundFlight);
        this.previousInboundFlight = this.currentInboundFlight;
        this.currentInboundFlight = hashtable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean processRecord(int i, int i2, byte[] bArr, int i3, int i4) throws IOException {
        int uint24;
        int uint242;
        DTLSReassembler dTLSReassembler;
        boolean z = false;
        int i5 = i3;
        int i6 = i4;
        boolean z2 = false;
        while (i6 >= 12 && i6 >= (uint242 = (uint24 = TlsUtils.readUint24(bArr, i5 + 9)) + 12)) {
            int uint243 = TlsUtils.readUint24(bArr, i5 + 1);
            int uint244 = TlsUtils.readUint24(bArr, i5 + 6);
            if (uint244 + uint24 > uint243) {
                break;
            }
            short uint8 = TlsUtils.readUint8(bArr, i5);
            if (i2 != (uint8 == 20 ? 1 : 0)) {
                break;
            }
            int uint16 = TlsUtils.readUint16(bArr, i5 + 4);
            int i7 = this.next_receive_seq;
            if (uint16 < i7 + i) {
                if (uint16 >= i7) {
                    DTLSReassembler dTLSReassembler2 = (DTLSReassembler) this.currentInboundFlight.get(Integers.valueOf(uint16));
                    if (dTLSReassembler2 == null) {
                        dTLSReassembler2 = new DTLSReassembler(uint8, uint243);
                        this.currentInboundFlight.put(Integers.valueOf(uint16), dTLSReassembler2);
                    }
                    dTLSReassembler2.contributeFragment(uint8, uint243, bArr, i5 + 12, uint244, uint24);
                } else {
                    Hashtable hashtable = this.previousInboundFlight;
                    if (hashtable != null && (dTLSReassembler = (DTLSReassembler) hashtable.get(Integers.valueOf(uint16))) != null) {
                        dTLSReassembler.contributeFragment(uint8, uint243, bArr, i5 + 12, uint244, uint24);
                        z2 = true;
                    }
                }
            }
            i5 += uint242;
            i6 -= uint242;
        }
        if (z2 && checkAll(this.previousInboundFlight)) {
            z = true;
        }
        if (z) {
            resendOutboundFlight();
            resetAll(this.previousInboundFlight);
        }
        return z;
    }

    private void resendOutboundFlight() throws IOException {
        this.recordLayer.resetWriteEpoch();
        for (int i = 0; i < this.outboundFlight.size(); i++) {
            writeMessage((Message) this.outboundFlight.elementAt(i));
        }
    }

    private Message updateHandshakeMessagesDigest(Message message) throws IOException {
        if (message.getType() != 0) {
            byte[] body = message.getBody();
            byte[] bArr = new byte[12];
            TlsUtils.writeUint8(message.getType(), bArr, 0);
            TlsUtils.writeUint24(body.length, bArr, 1);
            TlsUtils.writeUint16(message.getSeq(), bArr, 4);
            TlsUtils.writeUint24(0, bArr, 6);
            TlsUtils.writeUint24(body.length, bArr, 9);
            this.handshakeHash.update(bArr, 0, 12);
            this.handshakeHash.update(body, 0, body.length);
        }
        return message;
    }

    private void writeMessage(Message message) throws IOException {
        int sendLimit = this.recordLayer.getSendLimit() - 12;
        if (sendLimit < 1) {
            throw new TlsFatalAlert((short) 80);
        }
        int length = message.getBody().length;
        int i = 0;
        do {
            int iMin = Math.min(length - i, sendLimit);
            writeHandshakeFragment(message, i, iMin);
            i += iMin;
        } while (i < length);
    }

    private void writeHandshakeFragment(Message message, int i, int i2) throws IOException {
        RecordLayerBuffer recordLayerBuffer = new RecordLayerBuffer(i2 + 12);
        TlsUtils.writeUint8(message.getType(), (OutputStream) recordLayerBuffer);
        TlsUtils.writeUint24(message.getBody().length, recordLayerBuffer);
        TlsUtils.writeUint16(message.getSeq(), recordLayerBuffer);
        TlsUtils.writeUint24(i, recordLayerBuffer);
        TlsUtils.writeUint24(i2, recordLayerBuffer);
        recordLayerBuffer.write(message.getBody(), i, i2);
        recordLayerBuffer.sendToRecordLayer(this.recordLayer);
    }

    private static boolean checkAll(Hashtable hashtable) {
        Enumeration enumerationElements = hashtable.elements();
        while (enumerationElements.hasMoreElements()) {
            if (((DTLSReassembler) enumerationElements.nextElement()).getBodyIfComplete() == null) {
                return false;
            }
        }
        return true;
    }

    private static void resetAll(Hashtable hashtable) {
        Enumeration enumerationElements = hashtable.elements();
        while (enumerationElements.hasMoreElements()) {
            ((DTLSReassembler) enumerationElements.nextElement()).reset();
        }
    }

    static class Message {
        private final byte[] body;
        private final int message_seq;
        private final short msg_type;

        public byte[] getBody() {
            return this.body;
        }

        public int getSeq() {
            return this.message_seq;
        }

        public short getType() {
            return this.msg_type;
        }

        private Message(int i, short s, byte[] bArr) {
            this.message_seq = i;
            this.msg_type = s;
            this.body = bArr;
        }
    }

    static class RecordLayerBuffer extends ByteArrayOutputStream {
        RecordLayerBuffer(int i) {
            super(i);
        }

        void sendToRecordLayer(DTLSRecordLayer dTLSRecordLayer) throws IOException {
            dTLSRecordLayer.send(this.buf, 0, this.count);
            this.buf = null;
        }
    }
}
