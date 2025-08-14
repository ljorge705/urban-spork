package org.spongycastle.asn1.cmc;

import java.math.BigInteger;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;

/* loaded from: classes4.dex */
public class BodyPartID extends ASN1Object {
    public static final long bodyIdMax = 4294967295L;
    private final long id;

    public long getID() {
        return this.id;
    }

    public BodyPartID(long j) {
        if (j < 0 || j > 4294967295L) {
            throw new IllegalArgumentException("id out of range");
        }
        this.id = j;
    }

    private static long convert(BigInteger bigInteger) {
        if (bigInteger.bitLength() > 32) {
            throw new IllegalArgumentException("id out of range");
        }
        return bigInteger.longValue();
    }

    private BodyPartID(ASN1Integer aSN1Integer) {
        this(convert(aSN1Integer.getValue()));
    }

    public static BodyPartID getInstance(Object obj) {
        if (obj instanceof BodyPartID) {
            return (BodyPartID) obj;
        }
        if (obj != null) {
            return new BodyPartID(ASN1Integer.getInstance(obj));
        }
        return null;
    }

    @Override // org.spongycastle.asn1.ASN1Object, org.spongycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return new ASN1Integer(this.id);
    }
}
