package com.nimbusds.jose;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.util.Base64;
import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jose.util.JSONObjectUtils;
import com.nimbusds.jose.util.X509CertChainUtils;
import java.net.URI;
import java.text.ParseException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.jcip.annotations.Immutable;

@Immutable
/* loaded from: classes2.dex */
public final class JWSHeader extends CommonSEHeader {
    private static final Set<String> REGISTERED_PARAMETER_NAMES;
    private static final long serialVersionUID = 1;
    private final boolean b64;

    public static Set<String> getRegisteredParameterNames() {
        return REGISTERED_PARAMETER_NAMES;
    }

    public boolean isBase64URLEncodePayload() {
        return this.b64;
    }

    @Override // com.nimbusds.jose.CommonSEHeader
    public /* bridge */ /* synthetic */ JWK getJWK() {
        return super.getJWK();
    }

    @Override // com.nimbusds.jose.CommonSEHeader
    public /* bridge */ /* synthetic */ URI getJWKURL() {
        return super.getJWKURL();
    }

    @Override // com.nimbusds.jose.CommonSEHeader
    public /* bridge */ /* synthetic */ String getKeyID() {
        return super.getKeyID();
    }

    @Override // com.nimbusds.jose.CommonSEHeader
    public /* bridge */ /* synthetic */ List getX509CertChain() {
        return super.getX509CertChain();
    }

    @Override // com.nimbusds.jose.CommonSEHeader
    public /* bridge */ /* synthetic */ Base64URL getX509CertSHA256Thumbprint() {
        return super.getX509CertSHA256Thumbprint();
    }

    @Override // com.nimbusds.jose.CommonSEHeader
    @Deprecated
    public /* bridge */ /* synthetic */ Base64URL getX509CertThumbprint() {
        return super.getX509CertThumbprint();
    }

    @Override // com.nimbusds.jose.CommonSEHeader
    public /* bridge */ /* synthetic */ URI getX509CertURL() {
        return super.getX509CertURL();
    }

    static {
        HashSet hashSet = new HashSet();
        hashSet.add("alg");
        hashSet.add(HeaderParameterNames.JWK_SET_URL);
        hashSet.add(HeaderParameterNames.JWK);
        hashSet.add("x5u");
        hashSet.add("x5t");
        hashSet.add("x5t#S256");
        hashSet.add("x5c");
        hashSet.add("kid");
        hashSet.add("typ");
        hashSet.add(HeaderParameterNames.CONTENT_TYPE);
        hashSet.add(HeaderParameterNames.CRITICAL);
        hashSet.add(HeaderParameterNames.BASE64_URL_ENCODE_PAYLOAD);
        REGISTERED_PARAMETER_NAMES = Collections.unmodifiableSet(hashSet);
    }

    public static class Builder {
        private final JWSAlgorithm alg;
        private boolean b64;
        private Set<String> crit;
        private String cty;
        private Map<String, Object> customParams;
        private URI jku;
        private JWK jwk;
        private String kid;
        private Base64URL parsedBase64URL;
        private JOSEObjectType typ;
        private List<Base64> x5c;

        @Deprecated
        private Base64URL x5t;
        private Base64URL x5t256;
        private URI x5u;

        public Builder base64URLEncodePayload(boolean z) {
            this.b64 = z;
            return this;
        }

        public Builder contentType(String str) {
            this.cty = str;
            return this;
        }

        public Builder criticalParams(Set<String> set) {
            this.crit = set;
            return this;
        }

        public Builder customParams(Map<String, Object> map) {
            this.customParams = map;
            return this;
        }

        public Builder jwkURL(URI uri) {
            this.jku = uri;
            return this;
        }

        public Builder keyID(String str) {
            this.kid = str;
            return this;
        }

        public Builder parsedBase64URL(Base64URL base64URL) {
            this.parsedBase64URL = base64URL;
            return this;
        }

        public Builder type(JOSEObjectType jOSEObjectType) {
            this.typ = jOSEObjectType;
            return this;
        }

        public Builder x509CertChain(List<Base64> list) {
            this.x5c = list;
            return this;
        }

        public Builder x509CertSHA256Thumbprint(Base64URL base64URL) {
            this.x5t256 = base64URL;
            return this;
        }

        @Deprecated
        public Builder x509CertThumbprint(Base64URL base64URL) {
            this.x5t = base64URL;
            return this;
        }

        public Builder x509CertURL(URI uri) {
            this.x5u = uri;
            return this;
        }

        public Builder(JWSAlgorithm jWSAlgorithm) {
            this.b64 = true;
            if (jWSAlgorithm.getName().equals(Algorithm.NONE.getName())) {
                throw new IllegalArgumentException("The JWS algorithm \"alg\" cannot be \"none\"");
            }
            this.alg = jWSAlgorithm;
        }

        public Builder(JWSHeader jWSHeader) {
            this(jWSHeader.getAlgorithm());
            this.typ = jWSHeader.getType();
            this.cty = jWSHeader.getContentType();
            this.crit = jWSHeader.getCriticalParams();
            this.jku = jWSHeader.getJWKURL();
            this.jwk = jWSHeader.getJWK();
            this.x5u = jWSHeader.getX509CertURL();
            this.x5t = jWSHeader.getX509CertThumbprint();
            this.x5t256 = jWSHeader.getX509CertSHA256Thumbprint();
            this.x5c = jWSHeader.getX509CertChain();
            this.kid = jWSHeader.getKeyID();
            this.b64 = jWSHeader.isBase64URLEncodePayload();
            this.customParams = jWSHeader.getCustomParams();
        }

        public Builder jwk(JWK jwk) {
            if (jwk != null && jwk.isPrivate()) {
                throw new IllegalArgumentException("The JWK must be public");
            }
            this.jwk = jwk;
            return this;
        }

        public Builder customParam(String str, Object obj) {
            if (JWSHeader.getRegisteredParameterNames().contains(str)) {
                throw new IllegalArgumentException("The parameter name \"" + str + "\" matches a registered name");
            }
            if (this.customParams == null) {
                this.customParams = new HashMap();
            }
            this.customParams.put(str, obj);
            return this;
        }

        public JWSHeader build() {
            return new JWSHeader(this.alg, this.typ, this.cty, this.crit, this.jku, this.jwk, this.x5u, this.x5t, this.x5t256, this.x5c, this.kid, this.b64, this.customParams, this.parsedBase64URL);
        }
    }

    public JWSHeader(JWSAlgorithm jWSAlgorithm) {
        this(jWSAlgorithm, null, null, null, null, null, null, null, null, null, null, true, null, null);
    }

    @Deprecated
    public JWSHeader(JWSAlgorithm jWSAlgorithm, JOSEObjectType jOSEObjectType, String str, Set<String> set, URI uri, JWK jwk, URI uri2, Base64URL base64URL, Base64URL base64URL2, List<Base64> list, String str2, Map<String, Object> map, Base64URL base64URL3) {
        this(jWSAlgorithm, jOSEObjectType, str, set, uri, jwk, uri2, base64URL, base64URL2, list, str2, true, map, base64URL3);
    }

    public JWSHeader(JWSAlgorithm jWSAlgorithm, JOSEObjectType jOSEObjectType, String str, Set<String> set, URI uri, JWK jwk, URI uri2, Base64URL base64URL, Base64URL base64URL2, List<Base64> list, String str2, boolean z, Map<String, Object> map, Base64URL base64URL3) {
        super(jWSAlgorithm, jOSEObjectType, str, set, uri, jwk, uri2, base64URL, base64URL2, list, str2, map, base64URL3);
        if (jWSAlgorithm == null) {
            throw new IllegalArgumentException("The algorithm \"alg\" header parameter must not be null");
        }
        if (jWSAlgorithm.getName().equals(Algorithm.NONE.getName())) {
            throw new IllegalArgumentException("The JWS algorithm \"alg\" cannot be \"none\"");
        }
        this.b64 = z;
    }

    public JWSHeader(JWSHeader jWSHeader) {
        this(jWSHeader.getAlgorithm(), jWSHeader.getType(), jWSHeader.getContentType(), jWSHeader.getCriticalParams(), jWSHeader.getJWKURL(), jWSHeader.getJWK(), jWSHeader.getX509CertURL(), jWSHeader.getX509CertThumbprint(), jWSHeader.getX509CertSHA256Thumbprint(), jWSHeader.getX509CertChain(), jWSHeader.getKeyID(), jWSHeader.isBase64URLEncodePayload(), jWSHeader.getCustomParams(), jWSHeader.getParsedBase64URL());
    }

    @Override // com.nimbusds.jose.Header
    public JWSAlgorithm getAlgorithm() {
        return (JWSAlgorithm) super.getAlgorithm();
    }

    @Override // com.nimbusds.jose.CommonSEHeader, com.nimbusds.jose.Header
    public Set<String> getIncludedParams() {
        Set<String> includedParams = super.getIncludedParams();
        if (!isBase64URLEncodePayload()) {
            includedParams.add(HeaderParameterNames.BASE64_URL_ENCODE_PAYLOAD);
        }
        return includedParams;
    }

    @Override // com.nimbusds.jose.CommonSEHeader, com.nimbusds.jose.Header
    public Map<String, Object> toJSONObject() {
        Map<String, Object> jSONObject = super.toJSONObject();
        if (!isBase64URLEncodePayload()) {
            jSONObject.put(HeaderParameterNames.BASE64_URL_ENCODE_PAYLOAD, false);
        }
        return jSONObject;
    }

    public static JWSHeader parse(Map<String, Object> map) throws ParseException {
        return parse(map, (Base64URL) null);
    }

    public static JWSHeader parse(Map<String, Object> map, Base64URL base64URL) throws ParseException {
        Algorithm algorithm = Header.parseAlgorithm(map);
        if (!(algorithm instanceof JWSAlgorithm)) {
            throw new ParseException("Not a JWS header", 0);
        }
        Builder builderType = new Builder((JWSAlgorithm) algorithm).parsedBase64URL(base64URL);
        for (String str : map.keySet()) {
            if (!"alg".equals(str)) {
                if ("typ".equals(str)) {
                    String string = JSONObjectUtils.getString(map, str);
                    if (string != null) {
                        builderType = builderType.type(new JOSEObjectType(string));
                    }
                } else if (HeaderParameterNames.CONTENT_TYPE.equals(str)) {
                    builderType = builderType.contentType(JSONObjectUtils.getString(map, str));
                } else if (HeaderParameterNames.CRITICAL.equals(str)) {
                    List<String> stringList = JSONObjectUtils.getStringList(map, str);
                    if (stringList != null) {
                        builderType = builderType.criticalParams(new HashSet(stringList));
                    }
                } else if (HeaderParameterNames.JWK_SET_URL.equals(str)) {
                    builderType = builderType.jwkURL(JSONObjectUtils.getURI(map, str));
                } else if (HeaderParameterNames.JWK.equals(str)) {
                    builderType = builderType.jwk(CommonSEHeader.parsePublicJWK(JSONObjectUtils.getJSONObject(map, str)));
                } else if ("x5u".equals(str)) {
                    builderType = builderType.x509CertURL(JSONObjectUtils.getURI(map, str));
                } else if ("x5t".equals(str)) {
                    builderType = builderType.x509CertThumbprint(Base64URL.from(JSONObjectUtils.getString(map, str)));
                } else if ("x5t#S256".equals(str)) {
                    builderType = builderType.x509CertSHA256Thumbprint(Base64URL.from(JSONObjectUtils.getString(map, str)));
                } else if ("x5c".equals(str)) {
                    builderType = builderType.x509CertChain(X509CertChainUtils.toBase64List(JSONObjectUtils.getJSONArray(map, str)));
                } else if ("kid".equals(str)) {
                    builderType = builderType.keyID(JSONObjectUtils.getString(map, str));
                } else if (HeaderParameterNames.BASE64_URL_ENCODE_PAYLOAD.equals(str)) {
                    builderType = builderType.base64URLEncodePayload(JSONObjectUtils.getBoolean(map, str));
                } else {
                    builderType = builderType.customParam(str, map.get(str));
                }
            }
        }
        return builderType.build();
    }

    public static JWSHeader parse(String str) throws ParseException {
        return parse(str, (Base64URL) null);
    }

    public static JWSHeader parse(String str, Base64URL base64URL) throws ParseException {
        return parse(JSONObjectUtils.parse(str, 20000), base64URL);
    }

    public static JWSHeader parse(Base64URL base64URL) throws ParseException {
        return parse(base64URL.decodeToString(), base64URL);
    }
}
