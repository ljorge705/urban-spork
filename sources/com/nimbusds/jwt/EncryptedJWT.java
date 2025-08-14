package com.nimbusds.jwt;

import com.nimbusds.jose.JOSEObject;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.util.Base64URL;
import java.text.ParseException;
import java.util.Map;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
/* loaded from: classes2.dex */
public class EncryptedJWT extends JWEObject implements JWT {
    private static final long serialVersionUID = 1;
    private JWTClaimsSet claimsSet;

    public EncryptedJWT(JWEHeader jWEHeader, JWTClaimsSet jWTClaimsSet) {
        super(jWEHeader, jWTClaimsSet.toPayload());
        this.claimsSet = jWTClaimsSet;
    }

    public EncryptedJWT(Base64URL base64URL, Base64URL base64URL2, Base64URL base64URL3, Base64URL base64URL4, Base64URL base64URL5) throws ParseException {
        super(base64URL, base64URL2, base64URL3, base64URL4, base64URL5);
    }

    @Override // com.nimbusds.jwt.JWT
    public JWTClaimsSet getJWTClaimsSet() throws ParseException {
        JWTClaimsSet jWTClaimsSet = this.claimsSet;
        if (jWTClaimsSet != null) {
            return jWTClaimsSet;
        }
        Payload payload = getPayload();
        if (payload == null) {
            return null;
        }
        Map<String, Object> jSONObject = payload.toJSONObject();
        if (jSONObject == null) {
            throw new ParseException("Payload of JWE object is not a valid JSON object", 0);
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

    public static EncryptedJWT parse(String str) throws ParseException {
        Base64URL[] base64URLArrSplit = JOSEObject.split(str);
        if (base64URLArrSplit.length != 5) {
            throw new ParseException("Unexpected number of Base64URL parts, must be five", 0);
        }
        return new EncryptedJWT(base64URLArrSplit[0], base64URLArrSplit[1], base64URLArrSplit[2], base64URLArrSplit[3], base64URLArrSplit[4]);
    }
}
