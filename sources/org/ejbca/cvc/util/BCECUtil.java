package org.ejbca.cvc.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SignatureException;
import java.util.Locale;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1OutputStream;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;

/* loaded from: classes4.dex */
public final class BCECUtil {
    private BCECUtil() {
    }

    public static byte[] convertX962SigToCVC(String str, byte[] bArr) throws IOException {
        byte[] bArr2;
        if (!str.toUpperCase(Locale.getDefault()).contains("EC")) {
            return bArr;
        }
        ASN1InputStream aSN1InputStream = new ASN1InputStream(bArr);
        try {
            ASN1Sequence aSN1Sequence = (ASN1Sequence) aSN1InputStream.readObject();
            aSN1InputStream.close();
            BigInteger value = ((ASN1Integer) aSN1Sequence.getObjectAt(0)).getValue();
            BigInteger value2 = ((ASN1Integer) aSN1Sequence.getObjectAt(1)).getValue();
            byte[] bArrMakeUnsigned = makeUnsigned(value);
            byte[] bArrMakeUnsigned2 = makeUnsigned(value2);
            if (bArrMakeUnsigned.length > bArrMakeUnsigned2.length) {
                bArr2 = new byte[bArrMakeUnsigned.length * 2];
            } else {
                bArr2 = new byte[bArrMakeUnsigned2.length * 2];
            }
            System.arraycopy(bArrMakeUnsigned, 0, bArr2, (bArr2.length / 2) - bArrMakeUnsigned.length, bArrMakeUnsigned.length);
            System.arraycopy(bArrMakeUnsigned2, 0, bArr2, bArr2.length - bArrMakeUnsigned2.length, bArrMakeUnsigned2.length);
            return bArr2;
        } catch (Throwable th) {
            aSN1InputStream.close();
            throw th;
        }
    }

    public static byte[] convertCVCSigToX962(String str, byte[] bArr) throws SignatureException {
        if (!str.toUpperCase(Locale.getDefault()).contains("EC")) {
            return bArr;
        }
        int length = bArr.length / 2;
        byte[] bArr2 = new byte[length];
        int length2 = bArr.length / 2;
        byte[] bArr3 = new byte[length2];
        System.arraycopy(bArr, 0, bArr2, 0, length);
        System.arraycopy(bArr, length, bArr3, 0, length2);
        BigInteger bigInteger = new BigInteger(1, bArr2);
        BigInteger bigInteger2 = new BigInteger(1, bArr3);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ASN1OutputStream aSN1OutputStreamCreate = ASN1OutputStream.create(byteArrayOutputStream, "DER");
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new ASN1Integer(bigInteger));
        aSN1EncodableVector.add(new ASN1Integer(bigInteger2));
        try {
            aSN1OutputStreamCreate.writeObject((ASN1Primitive) new DERSequence(aSN1EncodableVector));
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new SignatureException(e);
        }
    }

    private static byte[] makeUnsigned(BigInteger bigInteger) {
        byte[] byteArray = bigInteger.toByteArray();
        if (byteArray[0] != 0) {
            return byteArray;
        }
        int length = byteArray.length - 1;
        byte[] bArr = new byte[length];
        System.arraycopy(byteArray, 1, bArr, 0, length);
        return bArr;
    }
}
