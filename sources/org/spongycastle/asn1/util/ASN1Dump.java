package org.spongycastle.asn1.util;

import java.io.IOException;
import java.util.Enumeration;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.spongycastle.asn1.ASN1ApplicationSpecific;
import org.spongycastle.asn1.ASN1Boolean;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1Enumerated;
import org.spongycastle.asn1.ASN1GeneralizedTime;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1Set;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.ASN1UTCTime;
import org.spongycastle.asn1.BERApplicationSpecific;
import org.spongycastle.asn1.BEROctetString;
import org.spongycastle.asn1.BERSequence;
import org.spongycastle.asn1.BERSet;
import org.spongycastle.asn1.BERTaggedObject;
import org.spongycastle.asn1.DERApplicationSpecific;
import org.spongycastle.asn1.DERBMPString;
import org.spongycastle.asn1.DERBitString;
import org.spongycastle.asn1.DERExternal;
import org.spongycastle.asn1.DERGraphicString;
import org.spongycastle.asn1.DERIA5String;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.DERPrintableString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERT61String;
import org.spongycastle.asn1.DERUTF8String;
import org.spongycastle.asn1.DERVideotexString;
import org.spongycastle.asn1.DERVisibleString;
import org.spongycastle.util.Strings;
import org.spongycastle.util.encoders.Hex;

/* loaded from: classes4.dex */
public class ASN1Dump {
    private static final int SAMPLE_SIZE = 32;
    private static final String TAB = "    ";

    static void _dumpAsString(String str, boolean z, ASN1Primitive aSN1Primitive, StringBuffer stringBuffer) {
        String strLineSeparator = Strings.lineSeparator();
        if (aSN1Primitive instanceof ASN1Sequence) {
            Enumeration objects = ((ASN1Sequence) aSN1Primitive).getObjects();
            String str2 = str + TAB;
            stringBuffer.append(str);
            if (aSN1Primitive instanceof BERSequence) {
                stringBuffer.append("BER Sequence");
            } else if (aSN1Primitive instanceof DERSequence) {
                stringBuffer.append("DER Sequence");
            } else {
                stringBuffer.append("Sequence");
            }
            stringBuffer.append(strLineSeparator);
            while (objects.hasMoreElements()) {
                Object objNextElement = objects.nextElement();
                if (objNextElement == null || objNextElement.equals(DERNull.INSTANCE)) {
                    stringBuffer.append(str2);
                    stringBuffer.append("NULL");
                    stringBuffer.append(strLineSeparator);
                } else if (objNextElement instanceof ASN1Primitive) {
                    _dumpAsString(str2, z, (ASN1Primitive) objNextElement, stringBuffer);
                } else {
                    _dumpAsString(str2, z, ((ASN1Encodable) objNextElement).toASN1Primitive(), stringBuffer);
                }
            }
            return;
        }
        if (aSN1Primitive instanceof ASN1TaggedObject) {
            String str3 = str + TAB;
            stringBuffer.append(str);
            if (aSN1Primitive instanceof BERTaggedObject) {
                stringBuffer.append("BER Tagged [");
            } else {
                stringBuffer.append("Tagged [");
            }
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) aSN1Primitive;
            stringBuffer.append(Integer.toString(aSN1TaggedObject.getTagNo()));
            stringBuffer.append(AbstractJsonLexerKt.END_LIST);
            if (!aSN1TaggedObject.isExplicit()) {
                stringBuffer.append(" IMPLICIT ");
            }
            stringBuffer.append(strLineSeparator);
            if (aSN1TaggedObject.isEmpty()) {
                stringBuffer.append(str3);
                stringBuffer.append("EMPTY");
                stringBuffer.append(strLineSeparator);
                return;
            }
            _dumpAsString(str3, z, aSN1TaggedObject.getObject(), stringBuffer);
            return;
        }
        if (aSN1Primitive instanceof ASN1Set) {
            Enumeration objects2 = ((ASN1Set) aSN1Primitive).getObjects();
            String str4 = str + TAB;
            stringBuffer.append(str);
            if (aSN1Primitive instanceof BERSet) {
                stringBuffer.append("BER Set");
            } else {
                stringBuffer.append("DER Set");
            }
            stringBuffer.append(strLineSeparator);
            while (objects2.hasMoreElements()) {
                Object objNextElement2 = objects2.nextElement();
                if (objNextElement2 == null) {
                    stringBuffer.append(str4);
                    stringBuffer.append("NULL");
                    stringBuffer.append(strLineSeparator);
                } else if (objNextElement2 instanceof ASN1Primitive) {
                    _dumpAsString(str4, z, (ASN1Primitive) objNextElement2, stringBuffer);
                } else {
                    _dumpAsString(str4, z, ((ASN1Encodable) objNextElement2).toASN1Primitive(), stringBuffer);
                }
            }
            return;
        }
        if (aSN1Primitive instanceof ASN1OctetString) {
            ASN1OctetString aSN1OctetString = (ASN1OctetString) aSN1Primitive;
            if (aSN1Primitive instanceof BEROctetString) {
                stringBuffer.append(str + "BER Constructed Octet String[" + aSN1OctetString.getOctets().length + "] ");
            } else {
                stringBuffer.append(str + "DER Octet String[" + aSN1OctetString.getOctets().length + "] ");
            }
            if (z) {
                stringBuffer.append(dumpBinaryDataAsString(str, aSN1OctetString.getOctets()));
                return;
            } else {
                stringBuffer.append(strLineSeparator);
                return;
            }
        }
        if (aSN1Primitive instanceof ASN1ObjectIdentifier) {
            stringBuffer.append(str + "ObjectIdentifier(" + ((ASN1ObjectIdentifier) aSN1Primitive).getId() + ")" + strLineSeparator);
            return;
        }
        if (aSN1Primitive instanceof ASN1Boolean) {
            stringBuffer.append(str + "Boolean(" + ((ASN1Boolean) aSN1Primitive).isTrue() + ")" + strLineSeparator);
            return;
        }
        if (aSN1Primitive instanceof ASN1Integer) {
            stringBuffer.append(str + "Integer(" + ((ASN1Integer) aSN1Primitive).getValue() + ")" + strLineSeparator);
            return;
        }
        if (aSN1Primitive instanceof DERBitString) {
            DERBitString dERBitString = (DERBitString) aSN1Primitive;
            stringBuffer.append(str + "DER Bit String[" + dERBitString.getBytes().length + ", " + dERBitString.getPadBits() + "] ");
            if (z) {
                stringBuffer.append(dumpBinaryDataAsString(str, dERBitString.getBytes()));
                return;
            } else {
                stringBuffer.append(strLineSeparator);
                return;
            }
        }
        if (aSN1Primitive instanceof DERIA5String) {
            stringBuffer.append(str + "IA5String(" + ((DERIA5String) aSN1Primitive).getString() + ") " + strLineSeparator);
            return;
        }
        if (aSN1Primitive instanceof DERUTF8String) {
            stringBuffer.append(str + "UTF8String(" + ((DERUTF8String) aSN1Primitive).getString() + ") " + strLineSeparator);
            return;
        }
        if (aSN1Primitive instanceof DERPrintableString) {
            stringBuffer.append(str + "PrintableString(" + ((DERPrintableString) aSN1Primitive).getString() + ") " + strLineSeparator);
            return;
        }
        if (aSN1Primitive instanceof DERVisibleString) {
            stringBuffer.append(str + "VisibleString(" + ((DERVisibleString) aSN1Primitive).getString() + ") " + strLineSeparator);
            return;
        }
        if (aSN1Primitive instanceof DERBMPString) {
            stringBuffer.append(str + "BMPString(" + ((DERBMPString) aSN1Primitive).getString() + ") " + strLineSeparator);
            return;
        }
        if (aSN1Primitive instanceof DERT61String) {
            stringBuffer.append(str + "T61String(" + ((DERT61String) aSN1Primitive).getString() + ") " + strLineSeparator);
            return;
        }
        if (aSN1Primitive instanceof DERGraphicString) {
            stringBuffer.append(str + "GraphicString(" + ((DERGraphicString) aSN1Primitive).getString() + ") " + strLineSeparator);
            return;
        }
        if (aSN1Primitive instanceof DERVideotexString) {
            stringBuffer.append(str + "VideotexString(" + ((DERVideotexString) aSN1Primitive).getString() + ") " + strLineSeparator);
            return;
        }
        if (aSN1Primitive instanceof ASN1UTCTime) {
            stringBuffer.append(str + "UTCTime(" + ((ASN1UTCTime) aSN1Primitive).getTime() + ") " + strLineSeparator);
            return;
        }
        if (aSN1Primitive instanceof ASN1GeneralizedTime) {
            stringBuffer.append(str + "GeneralizedTime(" + ((ASN1GeneralizedTime) aSN1Primitive).getTime() + ") " + strLineSeparator);
            return;
        }
        if (aSN1Primitive instanceof BERApplicationSpecific) {
            stringBuffer.append(outputApplicationSpecific("BER", str, z, aSN1Primitive, strLineSeparator));
            return;
        }
        if (aSN1Primitive instanceof DERApplicationSpecific) {
            stringBuffer.append(outputApplicationSpecific("DER", str, z, aSN1Primitive, strLineSeparator));
            return;
        }
        if (aSN1Primitive instanceof ASN1Enumerated) {
            stringBuffer.append(str + "DER Enumerated(" + ((ASN1Enumerated) aSN1Primitive).getValue() + ")" + strLineSeparator);
            return;
        }
        if (aSN1Primitive instanceof DERExternal) {
            DERExternal dERExternal = (DERExternal) aSN1Primitive;
            stringBuffer.append(str + "External " + strLineSeparator);
            String str5 = str + TAB;
            if (dERExternal.getDirectReference() != null) {
                stringBuffer.append(str5 + "Direct Reference: " + dERExternal.getDirectReference().getId() + strLineSeparator);
            }
            if (dERExternal.getIndirectReference() != null) {
                stringBuffer.append(str5 + "Indirect Reference: " + dERExternal.getIndirectReference().toString() + strLineSeparator);
            }
            if (dERExternal.getDataValueDescriptor() != null) {
                _dumpAsString(str5, z, dERExternal.getDataValueDescriptor(), stringBuffer);
            }
            stringBuffer.append(str5 + "Encoding: " + dERExternal.getEncoding() + strLineSeparator);
            _dumpAsString(str5, z, dERExternal.getExternalContent(), stringBuffer);
            return;
        }
        stringBuffer.append(str + aSN1Primitive.toString() + strLineSeparator);
    }

    private static String outputApplicationSpecific(String str, String str2, boolean z, ASN1Primitive aSN1Primitive, String str3) {
        ASN1ApplicationSpecific aSN1ApplicationSpecific = ASN1ApplicationSpecific.getInstance(aSN1Primitive);
        StringBuffer stringBuffer = new StringBuffer();
        if (aSN1ApplicationSpecific.isConstructed()) {
            try {
                ASN1Sequence aSN1Sequence = ASN1Sequence.getInstance(aSN1ApplicationSpecific.getObject(16));
                stringBuffer.append(str2 + str + " ApplicationSpecific[" + aSN1ApplicationSpecific.getApplicationTag() + "]" + str3);
                Enumeration objects = aSN1Sequence.getObjects();
                while (objects.hasMoreElements()) {
                    _dumpAsString(str2 + TAB, z, (ASN1Primitive) objects.nextElement(), stringBuffer);
                }
            } catch (IOException e) {
                stringBuffer.append(e);
            }
            return stringBuffer.toString();
        }
        return str2 + str + " ApplicationSpecific[" + aSN1ApplicationSpecific.getApplicationTag() + "] (" + Strings.fromByteArray(Hex.encode(aSN1ApplicationSpecific.getContents())) + ")" + str3;
    }

    public static String dumpAsString(Object obj) {
        return dumpAsString(obj, false);
    }

    public static String dumpAsString(Object obj, boolean z) {
        StringBuffer stringBuffer = new StringBuffer();
        if (obj instanceof ASN1Primitive) {
            _dumpAsString("", z, (ASN1Primitive) obj, stringBuffer);
        } else if (obj instanceof ASN1Encodable) {
            _dumpAsString("", z, ((ASN1Encodable) obj).toASN1Primitive(), stringBuffer);
        } else {
            return "unknown object type " + obj.toString();
        }
        return stringBuffer.toString();
    }

    private static String dumpBinaryDataAsString(String str, byte[] bArr) {
        String strLineSeparator = Strings.lineSeparator();
        StringBuffer stringBuffer = new StringBuffer();
        String str2 = str + TAB;
        stringBuffer.append(strLineSeparator);
        for (int i = 0; i < bArr.length; i += 32) {
            if (bArr.length - i > 32) {
                stringBuffer.append(str2);
                stringBuffer.append(Strings.fromByteArray(Hex.encode(bArr, i, 32)));
                stringBuffer.append(TAB);
                stringBuffer.append(calculateAscString(bArr, i, 32));
                stringBuffer.append(strLineSeparator);
            } else {
                stringBuffer.append(str2);
                stringBuffer.append(Strings.fromByteArray(Hex.encode(bArr, i, bArr.length - i)));
                for (int length = bArr.length - i; length != 32; length++) {
                    stringBuffer.append("  ");
                }
                stringBuffer.append(TAB);
                stringBuffer.append(calculateAscString(bArr, i, bArr.length - i));
                stringBuffer.append(strLineSeparator);
            }
        }
        return stringBuffer.toString();
    }

    private static String calculateAscString(byte[] bArr, int i, int i2) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i3 = i; i3 != i + i2; i3++) {
            byte b = bArr[i3];
            if (b >= 32 && b <= 126) {
                stringBuffer.append((char) b);
            }
        }
        return stringBuffer.toString();
    }
}
