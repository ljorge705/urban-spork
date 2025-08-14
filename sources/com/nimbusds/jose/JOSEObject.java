package com.nimbusds.jose;

import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jose.util.JSONObjectUtils;
import java.io.Serializable;
import java.text.ParseException;
import org.apache.commons.lang3.ClassUtils;

/* loaded from: classes2.dex */
public abstract class JOSEObject implements Serializable {
    public static final String MIME_TYPE_COMPACT = "application/jose; charset=UTF-8";

    @Deprecated
    public static final String MIME_TYPE_JS = "application/jose+json; charset=UTF-8";
    private static final long serialVersionUID = 1;
    private Base64URL[] parsedParts;
    private Payload payload;

    public abstract Header getHeader();

    public Base64URL[] getParsedParts() {
        return this.parsedParts;
    }

    public Payload getPayload() {
        return this.payload;
    }

    public abstract String serialize();

    protected void setParsedParts(Base64URL... base64URLArr) {
        this.parsedParts = base64URLArr;
    }

    protected void setPayload(Payload payload) {
        this.payload = payload;
    }

    protected JOSEObject() {
        this.payload = null;
        this.parsedParts = null;
    }

    protected JOSEObject(Payload payload) {
        this.payload = payload;
    }

    public String getParsedString() {
        if (this.parsedParts == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (Base64URL base64URL : this.parsedParts) {
            if (sb.length() > 0) {
                sb.append(ClassUtils.PACKAGE_SEPARATOR_CHAR);
            }
            if (base64URL != null) {
                sb.append(base64URL);
            }
        }
        return sb.toString();
    }

    public static Base64URL[] split(String str) throws ParseException {
        String strTrim = str.trim();
        int iIndexOf = strTrim.indexOf(".");
        if (iIndexOf == -1) {
            throw new ParseException("Invalid serialized unsecured/JWS/JWE object: Missing part delimiters", 0);
        }
        int i = iIndexOf + 1;
        int iIndexOf2 = strTrim.indexOf(".", i);
        if (iIndexOf2 == -1) {
            throw new ParseException("Invalid serialized unsecured/JWS/JWE object: Missing second delimiter", 0);
        }
        int i2 = iIndexOf2 + 1;
        int iIndexOf3 = strTrim.indexOf(".", i2);
        if (iIndexOf3 == -1) {
            return new Base64URL[]{new Base64URL(strTrim.substring(0, iIndexOf)), new Base64URL(strTrim.substring(i, iIndexOf2)), new Base64URL(strTrim.substring(i2))};
        }
        int i3 = iIndexOf3 + 1;
        int iIndexOf4 = strTrim.indexOf(".", i3);
        if (iIndexOf4 == -1) {
            throw new ParseException("Invalid serialized JWE object: Missing fourth delimiter", 0);
        }
        if (iIndexOf4 == -1 || strTrim.indexOf(".", iIndexOf4 + 1) == -1) {
            return new Base64URL[]{new Base64URL(strTrim.substring(0, iIndexOf)), new Base64URL(strTrim.substring(i, iIndexOf2)), new Base64URL(strTrim.substring(i2, iIndexOf3)), new Base64URL(strTrim.substring(i3, iIndexOf4)), new Base64URL(strTrim.substring(iIndexOf4 + 1))};
        }
        throw new ParseException("Invalid serialized unsecured/JWS/JWE object: Too many part delimiters", 0);
    }

    public static JOSEObject parse(String str) throws ParseException {
        try {
            Algorithm algorithm = Header.parseAlgorithm(JSONObjectUtils.parse(split(str)[0].decodeToString()));
            if (algorithm.equals(Algorithm.NONE)) {
                return PlainObject.parse(str);
            }
            if (algorithm instanceof JWSAlgorithm) {
                return JWSObject.parse(str);
            }
            if (algorithm instanceof JWEAlgorithm) {
                return JWEObject.parse(str);
            }
            throw new AssertionError("Unexpected algorithm type: " + algorithm);
        } catch (ParseException e) {
            throw new ParseException("Invalid unsecured/JWS/JWE header: " + e.getMessage(), 0);
        }
    }
}
