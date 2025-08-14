package org.bouncycastle.asn1.util;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1BMPString;
import org.bouncycastle.asn1.ASN1BitString;
import org.bouncycastle.asn1.ASN1Boolean;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Enumerated;
import org.bouncycastle.asn1.ASN1External;
import org.bouncycastle.asn1.ASN1GeneralizedTime;
import org.bouncycastle.asn1.ASN1GraphicString;
import org.bouncycastle.asn1.ASN1IA5String;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Null;
import org.bouncycastle.asn1.ASN1NumericString;
import org.bouncycastle.asn1.ASN1ObjectDescriptor;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1PrintableString;
import org.bouncycastle.asn1.ASN1RelativeOID;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.ASN1T61String;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.ASN1UTCTime;
import org.bouncycastle.asn1.ASN1UTF8String;
import org.bouncycastle.asn1.ASN1Util;
import org.bouncycastle.asn1.ASN1VideotexString;
import org.bouncycastle.asn1.ASN1VisibleString;
import org.bouncycastle.asn1.BEROctetString;
import org.bouncycastle.asn1.BERSequence;
import org.bouncycastle.asn1.BERSet;
import org.bouncycastle.asn1.BERTaggedObject;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERSet;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.DLBitString;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.encoders.Hex;

/* loaded from: classes4.dex */
public class ASN1Dump {
    private static final int SAMPLE_SIZE = 32;
    private static final String TAB = "    ";

    static void _dumpAsString(String str, boolean z, ASN1Primitive aSN1Primitive, StringBuffer stringBuffer) {
        StringBuilder sbAppend;
        String str2;
        ASN1Primitive externalContent;
        StringBuilder sbAppend2;
        String string;
        StringBuilder sbAppend3;
        BigInteger value;
        StringBuilder sbAppend4;
        String str3;
        String strDumpBinaryDataAsString;
        StringBuilder sbAppend5;
        StringBuilder sbAppend6;
        String id;
        StringBuilder sbAppend7;
        int length;
        String strLineSeparator = Strings.lineSeparator();
        if (!(aSN1Primitive instanceof ASN1Null)) {
            int i = 0;
            if (aSN1Primitive instanceof ASN1Sequence) {
                stringBuffer.append(str);
                stringBuffer.append(aSN1Primitive instanceof BERSequence ? "BER Sequence" : aSN1Primitive instanceof DERSequence ? "DER Sequence" : "Sequence");
                stringBuffer.append(strLineSeparator);
                ASN1Sequence aSN1Sequence = (ASN1Sequence) aSN1Primitive;
                String str4 = str + TAB;
                int size = aSN1Sequence.size();
                while (i < size) {
                    _dumpAsString(str4, z, aSN1Sequence.getObjectAt(i).toASN1Primitive(), stringBuffer);
                    i++;
                }
                return;
            }
            if (aSN1Primitive instanceof ASN1Set) {
                stringBuffer.append(str);
                stringBuffer.append(aSN1Primitive instanceof BERSet ? "BER Set" : aSN1Primitive instanceof DERSet ? "DER Set" : "Set");
                stringBuffer.append(strLineSeparator);
                ASN1Set aSN1Set = (ASN1Set) aSN1Primitive;
                String str5 = str + TAB;
                int size2 = aSN1Set.size();
                while (i < size2) {
                    _dumpAsString(str5, z, aSN1Set.getObjectAt(i).toASN1Primitive(), stringBuffer);
                    i++;
                }
                return;
            }
            if (aSN1Primitive instanceof ASN1TaggedObject) {
                stringBuffer.append(str);
                stringBuffer.append(aSN1Primitive instanceof BERTaggedObject ? "BER Tagged " : aSN1Primitive instanceof DERTaggedObject ? "DER Tagged " : "Tagged ");
                ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) aSN1Primitive;
                stringBuffer.append(ASN1Util.getTagText(aSN1TaggedObject));
                if (!aSN1TaggedObject.isExplicit()) {
                    stringBuffer.append(" IMPLICIT ");
                }
                stringBuffer.append(strLineSeparator);
                str2 = str + TAB;
                externalContent = aSN1TaggedObject.getBaseObject().toASN1Primitive();
            } else {
                if (!(aSN1Primitive instanceof ASN1OctetString)) {
                    if (aSN1Primitive instanceof ASN1ObjectIdentifier) {
                        sbAppend6 = new StringBuilder().append(str).append("ObjectIdentifier(");
                        id = ((ASN1ObjectIdentifier) aSN1Primitive).getId();
                    } else {
                        if (!(aSN1Primitive instanceof ASN1RelativeOID)) {
                            if (aSN1Primitive instanceof ASN1Boolean) {
                                sbAppend5 = new StringBuilder().append(str).append("Boolean(").append(((ASN1Boolean) aSN1Primitive).isTrue());
                            } else {
                                if (aSN1Primitive instanceof ASN1Integer) {
                                    sbAppend3 = new StringBuilder().append(str).append("Integer(");
                                    value = ((ASN1Integer) aSN1Primitive).getValue();
                                } else {
                                    if (!(aSN1Primitive instanceof ASN1BitString)) {
                                        if (aSN1Primitive instanceof ASN1IA5String) {
                                            sbAppend2 = new StringBuilder().append(str).append("IA5String(");
                                            string = ((ASN1IA5String) aSN1Primitive).getString();
                                        } else if (aSN1Primitive instanceof ASN1UTF8String) {
                                            sbAppend2 = new StringBuilder().append(str).append("UTF8String(");
                                            string = ((ASN1UTF8String) aSN1Primitive).getString();
                                        } else if (aSN1Primitive instanceof ASN1NumericString) {
                                            sbAppend2 = new StringBuilder().append(str).append("NumericString(");
                                            string = ((ASN1NumericString) aSN1Primitive).getString();
                                        } else if (aSN1Primitive instanceof ASN1PrintableString) {
                                            sbAppend2 = new StringBuilder().append(str).append("PrintableString(");
                                            string = ((ASN1PrintableString) aSN1Primitive).getString();
                                        } else if (aSN1Primitive instanceof ASN1VisibleString) {
                                            sbAppend2 = new StringBuilder().append(str).append("VisibleString(");
                                            string = ((ASN1VisibleString) aSN1Primitive).getString();
                                        } else if (aSN1Primitive instanceof ASN1BMPString) {
                                            sbAppend2 = new StringBuilder().append(str).append("BMPString(");
                                            string = ((ASN1BMPString) aSN1Primitive).getString();
                                        } else if (aSN1Primitive instanceof ASN1T61String) {
                                            sbAppend2 = new StringBuilder().append(str).append("T61String(");
                                            string = ((ASN1T61String) aSN1Primitive).getString();
                                        } else if (aSN1Primitive instanceof ASN1GraphicString) {
                                            sbAppend2 = new StringBuilder().append(str).append("GraphicString(");
                                            string = ((ASN1GraphicString) aSN1Primitive).getString();
                                        } else if (aSN1Primitive instanceof ASN1VideotexString) {
                                            sbAppend2 = new StringBuilder().append(str).append("VideotexString(");
                                            string = ((ASN1VideotexString) aSN1Primitive).getString();
                                        } else if (aSN1Primitive instanceof ASN1UTCTime) {
                                            sbAppend2 = new StringBuilder().append(str).append("UTCTime(");
                                            string = ((ASN1UTCTime) aSN1Primitive).getTime();
                                        } else if (aSN1Primitive instanceof ASN1GeneralizedTime) {
                                            sbAppend2 = new StringBuilder().append(str).append("GeneralizedTime(");
                                            string = ((ASN1GeneralizedTime) aSN1Primitive).getTime();
                                        } else if (aSN1Primitive instanceof ASN1Enumerated) {
                                            sbAppend3 = new StringBuilder().append(str).append("DER Enumerated(");
                                            value = ((ASN1Enumerated) aSN1Primitive).getValue();
                                        } else if (aSN1Primitive instanceof ASN1ObjectDescriptor) {
                                            sbAppend2 = new StringBuilder().append(str).append("ObjectDescriptor(");
                                            string = ((ASN1ObjectDescriptor) aSN1Primitive).getBaseGraphicString().getString();
                                        } else {
                                            if (!(aSN1Primitive instanceof ASN1External)) {
                                                sbAppend = new StringBuilder().append(str).append(aSN1Primitive.toString());
                                                strDumpBinaryDataAsString = sbAppend.append(strLineSeparator).toString();
                                                stringBuffer.append(strDumpBinaryDataAsString);
                                                return;
                                            }
                                            ASN1External aSN1External = (ASN1External) aSN1Primitive;
                                            stringBuffer.append(str + "External " + strLineSeparator);
                                            str2 = str + TAB;
                                            if (aSN1External.getDirectReference() != null) {
                                                stringBuffer.append(str2 + "Direct Reference: " + aSN1External.getDirectReference().getId() + strLineSeparator);
                                            }
                                            if (aSN1External.getIndirectReference() != null) {
                                                stringBuffer.append(str2 + "Indirect Reference: " + aSN1External.getIndirectReference().toString() + strLineSeparator);
                                            }
                                            if (aSN1External.getDataValueDescriptor() != null) {
                                                _dumpAsString(str2, z, aSN1External.getDataValueDescriptor(), stringBuffer);
                                            }
                                            stringBuffer.append(str2 + "Encoding: " + aSN1External.getEncoding() + strLineSeparator);
                                            externalContent = aSN1External.getExternalContent();
                                        }
                                        sbAppend = sbAppend2.append(string).append(") ");
                                        strDumpBinaryDataAsString = sbAppend.append(strLineSeparator).toString();
                                        stringBuffer.append(strDumpBinaryDataAsString);
                                        return;
                                    }
                                    ASN1BitString aSN1BitString = (ASN1BitString) aSN1Primitive;
                                    byte[] bytes = aSN1BitString.getBytes();
                                    int padBits = aSN1BitString.getPadBits();
                                    if (aSN1BitString instanceof DERBitString) {
                                        sbAppend4 = new StringBuilder().append(str);
                                        str3 = "DER Bit String[";
                                    } else if (aSN1BitString instanceof DLBitString) {
                                        sbAppend4 = new StringBuilder().append(str);
                                        str3 = "DL Bit String[";
                                    } else {
                                        sbAppend4 = new StringBuilder().append(str);
                                        str3 = "BER Bit String[";
                                    }
                                    stringBuffer.append(sbAppend4.append(str3).append(bytes.length).append(", ").append(padBits).append("] ").toString());
                                    if (z) {
                                        strDumpBinaryDataAsString = dumpBinaryDataAsString(str, bytes);
                                        stringBuffer.append(strDumpBinaryDataAsString);
                                        return;
                                    }
                                }
                                sbAppend5 = sbAppend3.append(value);
                            }
                            sbAppend = sbAppend5.append(")");
                            strDumpBinaryDataAsString = sbAppend.append(strLineSeparator).toString();
                            stringBuffer.append(strDumpBinaryDataAsString);
                            return;
                        }
                        sbAppend6 = new StringBuilder().append(str).append("RelativeOID(");
                        id = ((ASN1RelativeOID) aSN1Primitive).getId();
                    }
                    sbAppend5 = sbAppend6.append(id);
                    sbAppend = sbAppend5.append(")");
                    strDumpBinaryDataAsString = sbAppend.append(strLineSeparator).toString();
                    stringBuffer.append(strDumpBinaryDataAsString);
                    return;
                }
                ASN1OctetString aSN1OctetString = (ASN1OctetString) aSN1Primitive;
                if (aSN1Primitive instanceof BEROctetString) {
                    sbAppend7 = new StringBuilder().append(str).append("BER Constructed Octet String[");
                    length = aSN1OctetString.getOctets().length;
                } else {
                    sbAppend7 = new StringBuilder().append(str).append("DER Octet String[");
                    length = aSN1OctetString.getOctets().length;
                }
                stringBuffer.append(sbAppend7.append(length).append("] ").toString());
                if (z) {
                    strDumpBinaryDataAsString = dumpBinaryDataAsString(str, aSN1OctetString.getOctets());
                    stringBuffer.append(strDumpBinaryDataAsString);
                    return;
                }
            }
            _dumpAsString(str2, z, externalContent, stringBuffer);
            return;
        }
        stringBuffer.append(str);
        stringBuffer.append("NULL");
        stringBuffer.append(strLineSeparator);
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

    public static String dumpAsString(Object obj) {
        return dumpAsString(obj, false);
    }

    public static String dumpAsString(Object obj, boolean z) {
        ASN1Primitive aSN1Primitive;
        if (obj instanceof ASN1Primitive) {
            aSN1Primitive = (ASN1Primitive) obj;
        } else {
            if (!(obj instanceof ASN1Encodable)) {
                return "unknown object type " + obj.toString();
            }
            aSN1Primitive = ((ASN1Encodable) obj).toASN1Primitive();
        }
        StringBuffer stringBuffer = new StringBuffer();
        _dumpAsString("", z, aSN1Primitive, stringBuffer);
        return stringBuffer.toString();
    }

    private static String dumpBinaryDataAsString(String str, byte[] bArr) {
        String strCalculateAscString;
        String strLineSeparator = Strings.lineSeparator();
        StringBuffer stringBuffer = new StringBuffer();
        String str2 = str + TAB;
        stringBuffer.append(strLineSeparator);
        for (int i = 0; i < bArr.length; i += 32) {
            int length = bArr.length - i;
            stringBuffer.append(str2);
            if (length > 32) {
                stringBuffer.append(Strings.fromByteArray(Hex.encode(bArr, i, 32)));
                stringBuffer.append(TAB);
                strCalculateAscString = calculateAscString(bArr, i, 32);
            } else {
                stringBuffer.append(Strings.fromByteArray(Hex.encode(bArr, i, bArr.length - i)));
                for (int length2 = bArr.length - i; length2 != 32; length2++) {
                    stringBuffer.append("  ");
                }
                stringBuffer.append(TAB);
                strCalculateAscString = calculateAscString(bArr, i, bArr.length - i);
            }
            stringBuffer.append(strCalculateAscString);
            stringBuffer.append(strLineSeparator);
        }
        return stringBuffer.toString();
    }
}
