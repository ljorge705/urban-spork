package org.spongycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;
import org.spongycastle.asn1.ocsp.ResponderID;
import org.spongycastle.asn1.x509.Extensions;
import org.spongycastle.util.io.Streams;

/* loaded from: classes7.dex */
public class OCSPStatusRequest {
    protected Extensions requestExtensions;
    protected Vector responderIDList;

    public Extensions getRequestExtensions() {
        return this.requestExtensions;
    }

    public Vector getResponderIDList() {
        return this.responderIDList;
    }

    public OCSPStatusRequest(Vector vector, Extensions extensions) {
        this.responderIDList = vector;
        this.requestExtensions = extensions;
    }

    public void encode(OutputStream outputStream) throws IOException {
        Vector vector = this.responderIDList;
        if (vector == null || vector.isEmpty()) {
            TlsUtils.writeUint16(0, outputStream);
        } else {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            for (int i = 0; i < this.responderIDList.size(); i++) {
                TlsUtils.writeOpaque16(((ResponderID) this.responderIDList.elementAt(i)).getEncoded("DER"), byteArrayOutputStream);
            }
            TlsUtils.checkUint16(byteArrayOutputStream.size());
            TlsUtils.writeUint16(byteArrayOutputStream.size(), outputStream);
            Streams.writeBufTo(byteArrayOutputStream, outputStream);
        }
        Extensions extensions = this.requestExtensions;
        if (extensions == null) {
            TlsUtils.writeUint16(0, outputStream);
            return;
        }
        byte[] encoded = extensions.getEncoded("DER");
        TlsUtils.checkUint16(encoded.length);
        TlsUtils.writeUint16(encoded.length, outputStream);
        outputStream.write(encoded);
    }

    public static OCSPStatusRequest parse(InputStream inputStream) throws IOException {
        Vector vector = new Vector();
        int uint16 = TlsUtils.readUint16(inputStream);
        if (uint16 > 0) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(TlsUtils.readFully(uint16, inputStream));
            do {
                vector.addElement(ResponderID.getInstance(TlsUtils.readDERObject(TlsUtils.readOpaque16(byteArrayInputStream))));
            } while (byteArrayInputStream.available() > 0);
        }
        int uint162 = TlsUtils.readUint16(inputStream);
        return new OCSPStatusRequest(vector, uint162 > 0 ? Extensions.getInstance(TlsUtils.readDERObject(TlsUtils.readFully(uint162, inputStream))) : null);
    }
}
