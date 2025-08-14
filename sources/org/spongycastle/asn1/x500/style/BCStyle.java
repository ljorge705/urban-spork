package org.spongycastle.asn1.x500.style;

import androidx.autofill.HintConstants;
import androidx.exifinterface.media.ExifInterface;
import com.clevertap.android.sdk.Constants;
import com.google.firebase.dynamiclinks.DynamicLink;
import java.util.Hashtable;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1GeneralizedTime;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.DERIA5String;
import org.spongycastle.asn1.DERPrintableString;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.x500.RDN;
import org.spongycastle.asn1.x500.X500Name;
import org.spongycastle.asn1.x500.X500NameStyle;
import org.spongycastle.asn1.x509.X509ObjectIdentifiers;

/* loaded from: classes4.dex */
public class BCStyle extends AbstractX500NameStyle {
    public static final ASN1ObjectIdentifier BUSINESS_CATEGORY;
    public static final ASN1ObjectIdentifier C;
    public static final ASN1ObjectIdentifier CN;
    public static final ASN1ObjectIdentifier COUNTRY_OF_CITIZENSHIP;
    public static final ASN1ObjectIdentifier COUNTRY_OF_RESIDENCE;
    public static final ASN1ObjectIdentifier DATE_OF_BIRTH;
    public static final ASN1ObjectIdentifier DC;
    public static final ASN1ObjectIdentifier DMD_NAME;
    public static final ASN1ObjectIdentifier DN_QUALIFIER;
    private static final Hashtable DefaultLookUp;
    private static final Hashtable DefaultSymbols;
    public static final ASN1ObjectIdentifier E;
    public static final ASN1ObjectIdentifier EmailAddress;
    public static final ASN1ObjectIdentifier GENDER;
    public static final ASN1ObjectIdentifier GENERATION;
    public static final ASN1ObjectIdentifier GIVENNAME;
    public static final ASN1ObjectIdentifier INITIALS;
    public static final X500NameStyle INSTANCE;
    public static final ASN1ObjectIdentifier L;
    public static final ASN1ObjectIdentifier NAME;
    public static final ASN1ObjectIdentifier NAME_AT_BIRTH;
    public static final ASN1ObjectIdentifier O;
    public static final ASN1ObjectIdentifier ORGANIZATION_IDENTIFIER;
    public static final ASN1ObjectIdentifier OU;
    public static final ASN1ObjectIdentifier PLACE_OF_BIRTH;
    public static final ASN1ObjectIdentifier POSTAL_ADDRESS;
    public static final ASN1ObjectIdentifier POSTAL_CODE;
    public static final ASN1ObjectIdentifier PSEUDONYM;
    public static final ASN1ObjectIdentifier SERIALNUMBER;
    public static final ASN1ObjectIdentifier SN;
    public static final ASN1ObjectIdentifier ST;
    public static final ASN1ObjectIdentifier STREET;
    public static final ASN1ObjectIdentifier SURNAME;
    public static final ASN1ObjectIdentifier T;
    public static final ASN1ObjectIdentifier TELEPHONE_NUMBER;
    public static final ASN1ObjectIdentifier UID;
    public static final ASN1ObjectIdentifier UNIQUE_IDENTIFIER;
    public static final ASN1ObjectIdentifier UnstructuredAddress;
    public static final ASN1ObjectIdentifier UnstructuredName;
    protected final Hashtable defaultSymbols = copyHashTable(DefaultSymbols);
    protected final Hashtable defaultLookUp = copyHashTable(DefaultLookUp);

    static {
        ASN1ObjectIdentifier aSN1ObjectIdentifierIntern = new ASN1ObjectIdentifier("2.5.4.6").intern();
        C = aSN1ObjectIdentifierIntern;
        ASN1ObjectIdentifier aSN1ObjectIdentifierIntern2 = new ASN1ObjectIdentifier("2.5.4.10").intern();
        O = aSN1ObjectIdentifierIntern2;
        ASN1ObjectIdentifier aSN1ObjectIdentifierIntern3 = new ASN1ObjectIdentifier("2.5.4.11").intern();
        OU = aSN1ObjectIdentifierIntern3;
        ASN1ObjectIdentifier aSN1ObjectIdentifierIntern4 = new ASN1ObjectIdentifier("2.5.4.12").intern();
        T = aSN1ObjectIdentifierIntern4;
        ASN1ObjectIdentifier aSN1ObjectIdentifierIntern5 = new ASN1ObjectIdentifier("2.5.4.3").intern();
        CN = aSN1ObjectIdentifierIntern5;
        ASN1ObjectIdentifier aSN1ObjectIdentifierIntern6 = new ASN1ObjectIdentifier("2.5.4.5").intern();
        SN = aSN1ObjectIdentifierIntern6;
        ASN1ObjectIdentifier aSN1ObjectIdentifierIntern7 = new ASN1ObjectIdentifier("2.5.4.9").intern();
        STREET = aSN1ObjectIdentifierIntern7;
        SERIALNUMBER = aSN1ObjectIdentifierIntern6;
        ASN1ObjectIdentifier aSN1ObjectIdentifierIntern8 = new ASN1ObjectIdentifier("2.5.4.7").intern();
        L = aSN1ObjectIdentifierIntern8;
        ASN1ObjectIdentifier aSN1ObjectIdentifierIntern9 = new ASN1ObjectIdentifier("2.5.4.8").intern();
        ST = aSN1ObjectIdentifierIntern9;
        ASN1ObjectIdentifier aSN1ObjectIdentifierIntern10 = new ASN1ObjectIdentifier("2.5.4.4").intern();
        SURNAME = aSN1ObjectIdentifierIntern10;
        ASN1ObjectIdentifier aSN1ObjectIdentifierIntern11 = new ASN1ObjectIdentifier("2.5.4.42").intern();
        GIVENNAME = aSN1ObjectIdentifierIntern11;
        ASN1ObjectIdentifier aSN1ObjectIdentifierIntern12 = new ASN1ObjectIdentifier("2.5.4.43").intern();
        INITIALS = aSN1ObjectIdentifierIntern12;
        ASN1ObjectIdentifier aSN1ObjectIdentifierIntern13 = new ASN1ObjectIdentifier("2.5.4.44").intern();
        GENERATION = aSN1ObjectIdentifierIntern13;
        ASN1ObjectIdentifier aSN1ObjectIdentifierIntern14 = new ASN1ObjectIdentifier("2.5.4.45").intern();
        UNIQUE_IDENTIFIER = aSN1ObjectIdentifierIntern14;
        ASN1ObjectIdentifier aSN1ObjectIdentifierIntern15 = new ASN1ObjectIdentifier("2.5.4.15").intern();
        BUSINESS_CATEGORY = aSN1ObjectIdentifierIntern15;
        ASN1ObjectIdentifier aSN1ObjectIdentifierIntern16 = new ASN1ObjectIdentifier("2.5.4.17").intern();
        POSTAL_CODE = aSN1ObjectIdentifierIntern16;
        ASN1ObjectIdentifier aSN1ObjectIdentifierIntern17 = new ASN1ObjectIdentifier("2.5.4.46").intern();
        DN_QUALIFIER = aSN1ObjectIdentifierIntern17;
        ASN1ObjectIdentifier aSN1ObjectIdentifierIntern18 = new ASN1ObjectIdentifier("2.5.4.65").intern();
        PSEUDONYM = aSN1ObjectIdentifierIntern18;
        ASN1ObjectIdentifier aSN1ObjectIdentifierIntern19 = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.9.1").intern();
        DATE_OF_BIRTH = aSN1ObjectIdentifierIntern19;
        ASN1ObjectIdentifier aSN1ObjectIdentifierIntern20 = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.9.2").intern();
        PLACE_OF_BIRTH = aSN1ObjectIdentifierIntern20;
        ASN1ObjectIdentifier aSN1ObjectIdentifierIntern21 = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.9.3").intern();
        GENDER = aSN1ObjectIdentifierIntern21;
        ASN1ObjectIdentifier aSN1ObjectIdentifierIntern22 = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.9.4").intern();
        COUNTRY_OF_CITIZENSHIP = aSN1ObjectIdentifierIntern22;
        ASN1ObjectIdentifier aSN1ObjectIdentifierIntern23 = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.9.5").intern();
        COUNTRY_OF_RESIDENCE = aSN1ObjectIdentifierIntern23;
        ASN1ObjectIdentifier aSN1ObjectIdentifierIntern24 = new ASN1ObjectIdentifier("1.3.36.8.3.14").intern();
        NAME_AT_BIRTH = aSN1ObjectIdentifierIntern24;
        ASN1ObjectIdentifier aSN1ObjectIdentifierIntern25 = new ASN1ObjectIdentifier("2.5.4.16").intern();
        POSTAL_ADDRESS = aSN1ObjectIdentifierIntern25;
        DMD_NAME = new ASN1ObjectIdentifier("2.5.4.54").intern();
        ASN1ObjectIdentifier aSN1ObjectIdentifier = X509ObjectIdentifiers.id_at_telephoneNumber;
        TELEPHONE_NUMBER = aSN1ObjectIdentifier;
        ASN1ObjectIdentifier aSN1ObjectIdentifier2 = X509ObjectIdentifiers.id_at_name;
        NAME = aSN1ObjectIdentifier2;
        ASN1ObjectIdentifier aSN1ObjectIdentifier3 = X509ObjectIdentifiers.id_at_organizationIdentifier;
        ORGANIZATION_IDENTIFIER = aSN1ObjectIdentifier3;
        ASN1ObjectIdentifier aSN1ObjectIdentifier4 = PKCSObjectIdentifiers.pkcs_9_at_emailAddress;
        EmailAddress = aSN1ObjectIdentifier4;
        ASN1ObjectIdentifier aSN1ObjectIdentifier5 = PKCSObjectIdentifiers.pkcs_9_at_unstructuredName;
        UnstructuredName = aSN1ObjectIdentifier5;
        ASN1ObjectIdentifier aSN1ObjectIdentifier6 = PKCSObjectIdentifiers.pkcs_9_at_unstructuredAddress;
        UnstructuredAddress = aSN1ObjectIdentifier6;
        E = aSN1ObjectIdentifier4;
        ASN1ObjectIdentifier aSN1ObjectIdentifier7 = new ASN1ObjectIdentifier("0.9.2342.19200300.100.1.25");
        DC = aSN1ObjectIdentifier7;
        ASN1ObjectIdentifier aSN1ObjectIdentifier8 = new ASN1ObjectIdentifier("0.9.2342.19200300.100.1.1");
        UID = aSN1ObjectIdentifier8;
        Hashtable hashtable = new Hashtable();
        DefaultSymbols = hashtable;
        Hashtable hashtable2 = new Hashtable();
        DefaultLookUp = hashtable2;
        hashtable.put(aSN1ObjectIdentifierIntern, "C");
        hashtable.put(aSN1ObjectIdentifierIntern2, "O");
        hashtable.put(aSN1ObjectIdentifierIntern4, ExifInterface.GPS_DIRECTION_TRUE);
        hashtable.put(aSN1ObjectIdentifierIntern3, "OU");
        hashtable.put(aSN1ObjectIdentifierIntern5, "CN");
        hashtable.put(aSN1ObjectIdentifierIntern8, "L");
        hashtable.put(aSN1ObjectIdentifierIntern9, "ST");
        hashtable.put(aSN1ObjectIdentifierIntern6, "SERIALNUMBER");
        hashtable.put(aSN1ObjectIdentifier4, ExifInterface.LONGITUDE_EAST);
        hashtable.put(aSN1ObjectIdentifier7, "DC");
        hashtable.put(aSN1ObjectIdentifier8, "UID");
        hashtable.put(aSN1ObjectIdentifierIntern7, "STREET");
        hashtable.put(aSN1ObjectIdentifierIntern10, "SURNAME");
        hashtable.put(aSN1ObjectIdentifierIntern11, "GIVENNAME");
        hashtable.put(aSN1ObjectIdentifierIntern12, "INITIALS");
        hashtable.put(aSN1ObjectIdentifierIntern13, "GENERATION");
        hashtable.put(aSN1ObjectIdentifier6, "unstructuredAddress");
        hashtable.put(aSN1ObjectIdentifier5, "unstructuredName");
        hashtable.put(aSN1ObjectIdentifierIntern14, "UniqueIdentifier");
        hashtable.put(aSN1ObjectIdentifierIntern17, "DN");
        hashtable.put(aSN1ObjectIdentifierIntern18, "Pseudonym");
        hashtable.put(aSN1ObjectIdentifierIntern25, "PostalAddress");
        hashtable.put(aSN1ObjectIdentifierIntern24, "NameAtBirth");
        hashtable.put(aSN1ObjectIdentifierIntern22, "CountryOfCitizenship");
        hashtable.put(aSN1ObjectIdentifierIntern23, "CountryOfResidence");
        hashtable.put(aSN1ObjectIdentifierIntern21, "Gender");
        hashtable.put(aSN1ObjectIdentifierIntern20, "PlaceOfBirth");
        hashtable.put(aSN1ObjectIdentifierIntern19, "DateOfBirth");
        hashtable.put(aSN1ObjectIdentifierIntern16, "PostalCode");
        hashtable.put(aSN1ObjectIdentifierIntern15, "BusinessCategory");
        hashtable.put(aSN1ObjectIdentifier, "TelephoneNumber");
        hashtable.put(aSN1ObjectIdentifier2, Constants.KEY_ENCRYPTION_NAME);
        hashtable.put(aSN1ObjectIdentifier3, "organizationIdentifier");
        hashtable2.put("c", aSN1ObjectIdentifierIntern);
        hashtable2.put("o", aSN1ObjectIdentifierIntern2);
        hashtable2.put("t", aSN1ObjectIdentifierIntern4);
        hashtable2.put("ou", aSN1ObjectIdentifierIntern3);
        hashtable2.put("cn", aSN1ObjectIdentifierIntern5);
        hashtable2.put("l", aSN1ObjectIdentifierIntern8);
        hashtable2.put(DynamicLink.SocialMetaTagParameters.KEY_SOCIAL_TITLE, aSN1ObjectIdentifierIntern9);
        hashtable2.put("sn", aSN1ObjectIdentifierIntern6);
        hashtable2.put("serialnumber", aSN1ObjectIdentifierIntern6);
        hashtable2.put("street", aSN1ObjectIdentifierIntern7);
        hashtable2.put("emailaddress", aSN1ObjectIdentifier4);
        hashtable2.put("dc", aSN1ObjectIdentifier7);
        hashtable2.put("e", aSN1ObjectIdentifier4);
        hashtable2.put("uid", aSN1ObjectIdentifier8);
        hashtable2.put("surname", aSN1ObjectIdentifierIntern10);
        hashtable2.put("givenname", aSN1ObjectIdentifierIntern11);
        hashtable2.put("initials", aSN1ObjectIdentifierIntern12);
        hashtable2.put("generation", aSN1ObjectIdentifierIntern13);
        hashtable2.put("unstructuredaddress", aSN1ObjectIdentifier6);
        hashtable2.put("unstructuredname", aSN1ObjectIdentifier5);
        hashtable2.put("uniqueidentifier", aSN1ObjectIdentifierIntern14);
        hashtable2.put("dn", aSN1ObjectIdentifierIntern17);
        hashtable2.put("pseudonym", aSN1ObjectIdentifierIntern18);
        hashtable2.put("postaladdress", aSN1ObjectIdentifierIntern25);
        hashtable2.put("nameofbirth", aSN1ObjectIdentifierIntern24);
        hashtable2.put("countryofcitizenship", aSN1ObjectIdentifierIntern22);
        hashtable2.put("countryofresidence", aSN1ObjectIdentifierIntern23);
        hashtable2.put(HintConstants.AUTOFILL_HINT_GENDER, aSN1ObjectIdentifierIntern21);
        hashtable2.put("placeofbirth", aSN1ObjectIdentifierIntern20);
        hashtable2.put("dateofbirth", aSN1ObjectIdentifierIntern19);
        hashtable2.put("postalcode", aSN1ObjectIdentifierIntern16);
        hashtable2.put("businesscategory", aSN1ObjectIdentifierIntern15);
        hashtable2.put("telephonenumber", aSN1ObjectIdentifier);
        hashtable2.put("name", aSN1ObjectIdentifier2);
        hashtable2.put("organizationidentifier", aSN1ObjectIdentifier3);
        INSTANCE = new BCStyle();
    }

    protected BCStyle() {
    }

    @Override // org.spongycastle.asn1.x500.style.AbstractX500NameStyle
    protected ASN1Encodable encodeStringValue(ASN1ObjectIdentifier aSN1ObjectIdentifier, String str) {
        if (aSN1ObjectIdentifier.equals(EmailAddress) || aSN1ObjectIdentifier.equals(DC)) {
            return new DERIA5String(str);
        }
        if (aSN1ObjectIdentifier.equals(DATE_OF_BIRTH)) {
            return new ASN1GeneralizedTime(str);
        }
        if (aSN1ObjectIdentifier.equals(C) || aSN1ObjectIdentifier.equals(SN) || aSN1ObjectIdentifier.equals(DN_QUALIFIER) || aSN1ObjectIdentifier.equals(TELEPHONE_NUMBER)) {
            return new DERPrintableString(str);
        }
        return super.encodeStringValue(aSN1ObjectIdentifier, str);
    }

    @Override // org.spongycastle.asn1.x500.X500NameStyle
    public String oidToDisplayName(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (String) DefaultSymbols.get(aSN1ObjectIdentifier);
    }

    @Override // org.spongycastle.asn1.x500.X500NameStyle
    public String[] oidToAttrNames(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return IETFUtils.findAttrNamesForOID(aSN1ObjectIdentifier, this.defaultLookUp);
    }

    @Override // org.spongycastle.asn1.x500.X500NameStyle
    public ASN1ObjectIdentifier attrNameToOID(String str) {
        return IETFUtils.decodeAttrName(str, this.defaultLookUp);
    }

    @Override // org.spongycastle.asn1.x500.X500NameStyle
    public RDN[] fromString(String str) {
        return IETFUtils.rDNsFromString(str, this);
    }

    @Override // org.spongycastle.asn1.x500.X500NameStyle
    public String toString(X500Name x500Name) {
        StringBuffer stringBuffer = new StringBuffer();
        boolean z = true;
        for (RDN rdn : x500Name.getRDNs()) {
            if (z) {
                z = false;
            } else {
                stringBuffer.append(AbstractJsonLexerKt.COMMA);
            }
            IETFUtils.appendRDN(stringBuffer, rdn, this.defaultSymbols);
        }
        return stringBuffer.toString();
    }
}
