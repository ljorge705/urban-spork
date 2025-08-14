package org.spongycastle.asn1;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import org.apache.commons.lang3.time.TimeZones;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Strings;

/* loaded from: classes4.dex */
public class ASN1GeneralizedTime extends ASN1Primitive {
    private byte[] time;

    @Override // org.spongycastle.asn1.ASN1Primitive
    boolean isConstructed() {
        return false;
    }

    public static ASN1GeneralizedTime getInstance(Object obj) {
        if (obj == null || (obj instanceof ASN1GeneralizedTime)) {
            return (ASN1GeneralizedTime) obj;
        }
        if (obj instanceof byte[]) {
            try {
                return (ASN1GeneralizedTime) fromByteArray((byte[]) obj);
            } catch (Exception e) {
                throw new IllegalArgumentException("encoding error in getInstance: " + e.toString());
            }
        }
        throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
    }

    public static ASN1GeneralizedTime getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        ASN1Primitive object = aSN1TaggedObject.getObject();
        if (z || (object instanceof ASN1GeneralizedTime)) {
            return getInstance(object);
        }
        return new ASN1GeneralizedTime(((ASN1OctetString) object).getOctets());
    }

    public ASN1GeneralizedTime(String str) {
        this.time = Strings.toByteArray(str);
        try {
            getDate();
        } catch (ParseException e) {
            throw new IllegalArgumentException("invalid date string: " + e.getMessage());
        }
    }

    public ASN1GeneralizedTime(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss'Z'");
        simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "Z"));
        this.time = Strings.toByteArray(simpleDateFormat.format(date));
    }

    public ASN1GeneralizedTime(Date date, Locale locale) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss'Z'", locale);
        simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "Z"));
        this.time = Strings.toByteArray(simpleDateFormat.format(date));
    }

    ASN1GeneralizedTime(byte[] bArr) {
        this.time = bArr;
    }

    public String getTimeString() {
        return Strings.fromByteArray(this.time);
    }

    public String getTime() {
        String strFromByteArray = Strings.fromByteArray(this.time);
        if (strFromByteArray.charAt(strFromByteArray.length() - 1) == 'Z') {
            return strFromByteArray.substring(0, strFromByteArray.length() - 1) + "GMT+00:00";
        }
        int length = strFromByteArray.length();
        int i = length - 5;
        char cCharAt = strFromByteArray.charAt(i);
        if (cCharAt == '-' || cCharAt == '+') {
            int i2 = length - 2;
            return strFromByteArray.substring(0, i) + TimeZones.GMT_ID + strFromByteArray.substring(i, i2) + ":" + strFromByteArray.substring(i2);
        }
        int length2 = strFromByteArray.length() - 3;
        char cCharAt2 = strFromByteArray.charAt(length2);
        if (cCharAt2 == '-' || cCharAt2 == '+') {
            return strFromByteArray.substring(0, length2) + TimeZones.GMT_ID + strFromByteArray.substring(length2) + ":00";
        }
        return strFromByteArray + calculateGMTOffset();
    }

    private String calculateGMTOffset() {
        String str;
        TimeZone timeZone = TimeZone.getDefault();
        int rawOffset = timeZone.getRawOffset();
        if (rawOffset < 0) {
            rawOffset = -rawOffset;
            str = "-";
        } else {
            str = "+";
        }
        int i = rawOffset / 3600000;
        int i2 = (rawOffset - (3600000 * i)) / 60000;
        try {
            if (timeZone.useDaylightTime() && timeZone.inDaylightTime(getDate())) {
                i += str.equals("+") ? 1 : -1;
            }
        } catch (ParseException unused) {
        }
        return TimeZones.GMT_ID + str + convert(i) + ":" + convert(i2);
    }

    private String convert(int i) {
        if (i < 10) {
            return "0" + i;
        }
        return Integer.toString(i);
    }

    public Date getDate() throws ParseException {
        SimpleDateFormat simpleDateFormat;
        SimpleDateFormat simpleDateFormat2;
        char cCharAt;
        String strFromByteArray = Strings.fromByteArray(this.time);
        if (strFromByteArray.endsWith("Z")) {
            if (hasFractionalSeconds()) {
                simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss.SSS'Z'");
            } else {
                simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss'Z'");
            }
            simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "Z"));
        } else if (strFromByteArray.indexOf(45) > 0 || strFromByteArray.indexOf(43) > 0) {
            strFromByteArray = getTime();
            if (hasFractionalSeconds()) {
                simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss.SSSz");
            } else {
                simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssz");
            }
            simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "Z"));
        } else {
            if (hasFractionalSeconds()) {
                simpleDateFormat2 = new SimpleDateFormat("yyyyMMddHHmmss.SSS");
            } else {
                simpleDateFormat2 = new SimpleDateFormat("yyyyMMddHHmmss");
            }
            simpleDateFormat = simpleDateFormat2;
            simpleDateFormat.setTimeZone(new SimpleTimeZone(0, TimeZone.getDefault().getID()));
        }
        if (hasFractionalSeconds()) {
            String strSubstring = strFromByteArray.substring(14);
            int i = 1;
            while (i < strSubstring.length() && '0' <= (cCharAt = strSubstring.charAt(i)) && cCharAt <= '9') {
                i++;
            }
            int i2 = i - 1;
            if (i2 > 3) {
                strFromByteArray = strFromByteArray.substring(0, 14) + (strSubstring.substring(0, 4) + strSubstring.substring(i));
            } else if (i2 == 1) {
                strFromByteArray = strFromByteArray.substring(0, 14) + (strSubstring.substring(0, i) + "00" + strSubstring.substring(i));
            } else if (i2 == 2) {
                strFromByteArray = strFromByteArray.substring(0, 14) + (strSubstring.substring(0, i) + "0" + strSubstring.substring(i));
            }
        }
        return simpleDateFormat.parse(strFromByteArray);
    }

    private boolean hasFractionalSeconds() {
        int i = 0;
        while (true) {
            byte[] bArr = this.time;
            if (i == bArr.length) {
                return false;
            }
            if (bArr[i] == 46 && i == 14) {
                return true;
            }
            i++;
        }
    }

    @Override // org.spongycastle.asn1.ASN1Primitive
    int encodedLength() {
        int length = this.time.length;
        return StreamUtil.calculateBodyLength(length) + 1 + length;
    }

    @Override // org.spongycastle.asn1.ASN1Primitive
    void encode(ASN1OutputStream aSN1OutputStream) throws IOException {
        aSN1OutputStream.writeEncoded(24, this.time);
    }

    @Override // org.spongycastle.asn1.ASN1Primitive
    boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        if (aSN1Primitive instanceof ASN1GeneralizedTime) {
            return Arrays.areEqual(this.time, ((ASN1GeneralizedTime) aSN1Primitive).time);
        }
        return false;
    }

    @Override // org.spongycastle.asn1.ASN1Primitive, org.spongycastle.asn1.ASN1Object
    public int hashCode() {
        return Arrays.hashCode(this.time);
    }
}
