package org.spongycastle.asn1;

import java.io.IOException;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Strings;

/* loaded from: classes4.dex */
public class DERIA5String extends ASN1Primitive implements ASN1String {
    private final byte[] string;

    @Override // org.spongycastle.asn1.ASN1Primitive
    boolean isConstructed() {
        return false;
    }

    public static DERIA5String getInstance(Object obj) {
        if (obj == null || (obj instanceof DERIA5String)) {
            return (DERIA5String) obj;
        }
        if (obj instanceof byte[]) {
            try {
                return (DERIA5String) fromByteArray((byte[]) obj);
            } catch (Exception e) {
                throw new IllegalArgumentException("encoding error in getInstance: " + e.toString());
            }
        }
        throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
    }

    public static DERIA5String getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        ASN1Primitive object = aSN1TaggedObject.getObject();
        if (z || (object instanceof DERIA5String)) {
            return getInstance(object);
        }
        return new DERIA5String(((ASN1OctetString) object).getOctets());
    }

    DERIA5String(byte[] bArr) {
        this.string = bArr;
    }

    public DERIA5String(String str) {
        this(str, false);
    }

    public DERIA5String(String str, boolean z) {
        if (str == null) {
            throw new NullPointerException("string cannot be null");
        }
        if (z && !isIA5String(str)) {
            throw new IllegalArgumentException("string contains illegal characters");
        }
        this.string = Strings.toByteArray(str);
    }

    @Override // org.spongycastle.asn1.ASN1String
    public String getString() {
        return Strings.fromByteArray(this.string);
    }

    public String toString() {
        return getString();
    }

    public byte[] getOctets() {
        return Arrays.clone(this.string);
    }

    @Override // org.spongycastle.asn1.ASN1Primitive
    int encodedLength() {
        return StreamUtil.calculateBodyLength(this.string.length) + 1 + this.string.length;
    }

    @Override // org.spongycastle.asn1.ASN1Primitive
    void encode(ASN1OutputStream aSN1OutputStream) throws IOException {
        aSN1OutputStream.writeEncoded(22, this.string);
    }

    @Override // org.spongycastle.asn1.ASN1Primitive, org.spongycastle.asn1.ASN1Object
    public int hashCode() {
        return Arrays.hashCode(this.string);
    }

    @Override // org.spongycastle.asn1.ASN1Primitive
    boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        if (aSN1Primitive instanceof DERIA5String) {
            return Arrays.areEqual(this.string, ((DERIA5String) aSN1Primitive).string);
        }
        return false;
    }

    public static boolean isIA5String(String str) {
        for (int length = str.length() - 1; length >= 0; length--) {
            if (str.charAt(length) > 127) {
                return false;
            }
        }
        return true;
    }
}
