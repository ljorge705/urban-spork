package com.nimbusds.jwt;

import com.nimbusds.jose.JOSEObject;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.PlainHeader;
import com.nimbusds.jose.PlainObject;
import com.nimbusds.jose.util.Base64URL;
import java.text.ParseException;
import java.util.Map;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
/* loaded from: classes2.dex */
public class PlainJWT extends PlainObject implements JWT {
    private static final long serialVersionUID = 1;
    private JWTClaimsSet claimsSet;

    public PlainJWT(JWTClaimsSet jWTClaimsSet) {
        super(jWTClaimsSet.toPayload());
        this.claimsSet = jWTClaimsSet;
    }

    public PlainJWT(PlainHeader plainHeader, JWTClaimsSet jWTClaimsSet) {
        super(plainHeader, jWTClaimsSet.toPayload());
        this.claimsSet = jWTClaimsSet;
    }

    public PlainJWT(Base64URL base64URL, Base64URL base64URL2) throws ParseException {
        super(base64URL, base64URL2);
    }

    @Override // com.nimbusds.jwt.JWT
    public JWTClaimsSet getJWTClaimsSet() throws ParseException {
        JWTClaimsSet jWTClaimsSet = this.claimsSet;
        if (jWTClaimsSet != null) {
            return jWTClaimsSet;
        }
        Map<String, Object> jSONObject = getPayload().toJSONObject();
        if (jSONObject == null) {
            throw new ParseException("Payload of unsecured JOSE object is not a valid JSON object", 0);
        }
        JWTClaimsSet jWTClaimsSet2 = JWTClaimsSet.parse(jSONObject);
        this.claimsSet = jWTClaimsSet2;
        return jWTClaimsSet2;
    }

    @Override // com.nimbusds.jose.JOSEObject
    protected void setPayload(Payload payload) {
        this.claimsSet = null;
        super.setPayload(payload);
    }

    public static PlainJWT parse(String str) throws ParseException {
        Base64URL[] base64URLArrSplit = JOSEObject.split(str);
        if (!base64URLArrSplit[2].toString().isEmpty()) {
            throw new ParseException("Unexpected third Base64URL part in the unsecured JWT object", 0);
        }
        return new PlainJWT(base64URLArrSplit[0], base64URLArrSplit[1]);
    }
}
