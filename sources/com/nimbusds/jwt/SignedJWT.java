package com.nimbusds.jwt;

import com.nimbusds.jose.JOSEObject;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.util.Base64URL;
import java.text.ParseException;
import java.util.Map;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
/* loaded from: classes2.dex */
public class SignedJWT extends JWSObject implements JWT {
    private static final long serialVersionUID = 1;
    private JWTClaimsSet claimsSet;

    public SignedJWT(JWSHeader jWSHeader, JWTClaimsSet jWTClaimsSet) {
        super(jWSHeader, jWTClaimsSet.toPayload());
        this.claimsSet = jWTClaimsSet;
    }

    public SignedJWT(Base64URL base64URL, Base64URL base64URL2, Base64URL base64URL3) throws ParseException {
        super(base64URL, base64URL2, base64URL3);
    }

    @Override // com.nimbusds.jwt.JWT
    public JWTClaimsSet getJWTClaimsSet() throws ParseException {
        JWTClaimsSet jWTClaimsSet = this.claimsSet;
        if (jWTClaimsSet != null) {
            return jWTClaimsSet;
        }
        Map<String, Object> jSONObject = getPayload().toJSONObject();
        if (jSONObject == null) {
            throw new ParseException("Payload of JWS object is not a valid JSON object", 0);
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

    public static SignedJWT parse(String str) throws ParseException {
        Base64URL[] base64URLArrSplit = JOSEObject.split(str);
        if (base64URLArrSplit.length != 3) {
            throw new ParseException("Unexpected number of Base64URL parts, must be three", 0);
        }
        return new SignedJWT(base64URLArrSplit[0], base64URLArrSplit[1], base64URLArrSplit[2]);
    }
}
