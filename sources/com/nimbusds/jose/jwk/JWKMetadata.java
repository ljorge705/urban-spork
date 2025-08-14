package com.nimbusds.jose.jwk;

import com.nimbusds.jose.Algorithm;
import com.nimbusds.jose.util.Base64;
import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jose.util.JSONObjectUtils;
import com.nimbusds.jose.util.X509CertChainUtils;
import com.nimbusds.jwt.util.DateUtils;
import java.net.URI;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes2.dex */
final class JWKMetadata {
    JWKMetadata() {
    }

    static KeyType parseKeyType(Map<String, Object> map) throws ParseException {
        try {
            return KeyType.parse(JSONObjectUtils.getString(map, JWKParameterNames.KEY_TYPE));
        } catch (IllegalArgumentException e) {
            throw new ParseException(e.getMessage(), 0);
        }
    }

    static KeyUse parseKeyUse(Map<String, Object> map) throws ParseException {
        return KeyUse.parse(JSONObjectUtils.getString(map, JWKParameterNames.PUBLIC_KEY_USE));
    }

    static Set<KeyOperation> parseKeyOperations(Map<String, Object> map) throws ParseException {
        return KeyOperation.parse(JSONObjectUtils.getStringList(map, JWKParameterNames.KEY_OPS));
    }

    static Algorithm parseAlgorithm(Map<String, Object> map) throws ParseException {
        return Algorithm.parse(JSONObjectUtils.getString(map, "alg"));
    }

    static String parseKeyID(Map<String, Object> map) throws ParseException {
        return JSONObjectUtils.getString(map, "kid");
    }

    static URI parseX509CertURL(Map<String, Object> map) throws ParseException {
        return JSONObjectUtils.getURI(map, "x5u");
    }

    static Base64URL parseX509CertThumbprint(Map<String, Object> map) throws ParseException {
        return JSONObjectUtils.getBase64URL(map, "x5t");
    }

    static Base64URL parseX509CertSHA256Thumbprint(Map<String, Object> map) throws ParseException {
        return JSONObjectUtils.getBase64URL(map, "x5t#S256");
    }

    static List<Base64> parseX509CertChain(Map<String, Object> map) throws ParseException {
        List<Base64> base64List = X509CertChainUtils.toBase64List(JSONObjectUtils.getJSONArray(map, "x5c"));
        if (base64List == null || !base64List.isEmpty()) {
            return base64List;
        }
        return null;
    }

    static Date parseExpirationTime(Map<String, Object> map) throws ParseException {
        if (map.get("exp") == null) {
            return null;
        }
        return DateUtils.fromSecondsSinceEpoch(JSONObjectUtils.getLong(map, "exp"));
    }

    static Date parseNotBeforeTime(Map<String, Object> map) throws ParseException {
        if (map.get("nbf") == null) {
            return null;
        }
        return DateUtils.fromSecondsSinceEpoch(JSONObjectUtils.getLong(map, "nbf"));
    }

    static Date parseIssueTime(Map<String, Object> map) throws ParseException {
        if (map.get("iat") == null) {
            return null;
        }
        return DateUtils.fromSecondsSinceEpoch(JSONObjectUtils.getLong(map, "iat"));
    }
}
