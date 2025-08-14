package org.jmrtd.lds;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.jmrtd.cert.CVCPrincipal;

/* loaded from: classes4.dex */
public class CVCAFile extends AbstractLDSFile {
    public static final byte CAR_TAG = 66;
    public static final int LENGTH = 36;
    private static final long serialVersionUID = -1100904058684365703L;
    private String altCAReference;
    private String caReference;
    private short fid;

    public short getFID() {
        return this.fid;
    }

    @Override // org.jmrtd.lds.LDSFile
    public int getLength() {
        return 36;
    }

    @Override // org.jmrtd.lds.AbstractLDSFile, org.jmrtd.lds.LDSElement
    public /* bridge */ /* synthetic */ byte[] getEncoded() {
        return super.getEncoded();
    }

    public CVCAFile(InputStream inputStream) throws IOException {
        this((short) 284, inputStream);
    }

    public CVCAFile(short s, InputStream inputStream) throws IOException {
        this.caReference = null;
        this.altCAReference = null;
        this.fid = s;
        readObject(inputStream);
    }

    public CVCAFile(String str, String str2) {
        this((short) 284, str, str2);
    }

    public CVCAFile(short s, String str, String str2) {
        this.caReference = null;
        this.altCAReference = null;
        if (str == null || str.length() > 16 || (str2 != null && str2.length() > 16)) {
            throw new IllegalArgumentException();
        }
        this.fid = s;
        this.caReference = str;
        this.altCAReference = str2;
    }

    public CVCAFile(short s, String str) {
        this(s, str, null);
    }

    @Override // org.jmrtd.lds.AbstractLDSFile
    protected void readObject(InputStream inputStream) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        int i = dataInputStream.read();
        if (i != 66) {
            throw new IllegalArgumentException("Wrong tag, expected " + Integer.toHexString(66) + ", found " + Integer.toHexString(i));
        }
        int i2 = dataInputStream.read();
        if (i2 > 16) {
            throw new IllegalArgumentException("Wrong length");
        }
        byte[] bArr = new byte[i2];
        dataInputStream.readFully(bArr);
        this.caReference = new String(bArr);
        int i3 = dataInputStream.read();
        if (i3 != 0 && i3 != -1) {
            if (i3 != 66) {
                throw new IllegalArgumentException("Wrong tag");
            }
            int i4 = dataInputStream.read();
            if (i4 > 16) {
                throw new IllegalArgumentException("Wrong length");
            }
            byte[] bArr2 = new byte[i4];
            dataInputStream.readFully(bArr2);
            this.altCAReference = new String(bArr2);
            i3 = dataInputStream.read();
        }
        while (i3 != -1) {
            if (i3 != 0) {
                throw new IllegalArgumentException("Bad file padding");
            }
            i3 = dataInputStream.read();
        }
    }

    @Override // org.jmrtd.lds.AbstractLDSFile
    protected void writeObject(OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[36];
        bArr[0] = CAR_TAG;
        bArr[1] = (byte) this.caReference.length();
        System.arraycopy(this.caReference.getBytes(), 0, bArr, 2, bArr[1]);
        String str = this.altCAReference;
        if (str != null) {
            byte b = bArr[1];
            bArr[b + 2] = CAR_TAG;
            int i = b + 3;
            bArr[i] = (byte) str.length();
            System.arraycopy(this.altCAReference.getBytes(), 0, bArr, b + 4, bArr[i]);
        }
        outputStream.write(bArr);
    }

    public CVCPrincipal getCAReference() {
        if (this.caReference == null) {
            return null;
        }
        return new CVCPrincipal(this.caReference);
    }

    public CVCPrincipal getAltCAReference() {
        if (this.altCAReference == null) {
            return null;
        }
        return new CVCPrincipal(this.altCAReference);
    }

    public String toString() {
        return "CA reference: \"" + this.caReference + "\"" + (this.altCAReference != null ? ", Alternative CA reference: " + this.altCAReference : "");
    }

    public boolean equals(Object obj) {
        if (obj == null || !getClass().equals(obj.getClass())) {
            return false;
        }
        CVCAFile cVCAFile = (CVCAFile) obj;
        if (!this.caReference.equals(cVCAFile.caReference)) {
            return false;
        }
        String str = this.altCAReference;
        return (str == null && cVCAFile.altCAReference == null) || (str != null && str.equals(cVCAFile.altCAReference));
    }

    public int hashCode() {
        int iHashCode = this.caReference.hashCode() * 11;
        String str = this.altCAReference;
        return iHashCode + (str != null ? str.hashCode() * 13 : 0) + 5;
    }
}
