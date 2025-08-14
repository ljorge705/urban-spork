package org.spongycastle.asn1;

import java.io.IOException;

/* loaded from: classes4.dex */
public abstract class ASN1Null extends ASN1Primitive {
    @Override // org.spongycastle.asn1.ASN1Primitive
    abstract void encode(ASN1OutputStream aSN1OutputStream) throws IOException;

    @Override // org.spongycastle.asn1.ASN1Primitive, org.spongycastle.asn1.ASN1Object
    public int hashCode() {
        return -1;
    }

    public String toString() {
        return "NULL";
    }

    public static ASN1Null getInstance(Object obj) {
        if (obj instanceof ASN1Null) {
            return (ASN1Null) obj;
        }
        if (obj == null) {
            return null;
        }
        try {
            return getInstance(ASN1Primitive.fromByteArray((byte[]) obj));
        } catch (IOException e) {
            throw new IllegalArgumentException("failed to construct NULL from byte[]: " + e.getMessage());
        } catch (ClassCastException unused) {
            throw new IllegalArgumentException("unknown object in getInstance(): " + obj.getClass().getName());
        }
    }

    @Override // org.spongycastle.asn1.ASN1Primitive
    boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        return aSN1Primitive instanceof ASN1Null;
    }
}
