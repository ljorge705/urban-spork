package com.nimbusds.jose;

import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jose.util.JSONObjectUtils;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* loaded from: classes2.dex */
public abstract class Header implements Serializable {
    private static final Map<String, Object> EMPTY_CUSTOM_PARAMS = Collections.unmodifiableMap(new HashMap());
    public static final int MAX_HEADER_STRING_LENGTH = 20000;
    private static final long serialVersionUID = 1;
    private final Algorithm alg;
    private final Set<String> crit;
    private final String cty;
    private final Map<String, Object> customParams;
    private final Base64URL parsedBase64URL;
    private final JOSEObjectType typ;

    public Algorithm getAlgorithm() {
        return this.alg;
    }

    public String getContentType() {
        return this.cty;
    }

    public Set<String> getCriticalParams() {
        return this.crit;
    }

    public Map<String, Object> getCustomParams() {
        return this.customParams;
    }

    public Base64URL getParsedBase64URL() {
        return this.parsedBase64URL;
    }

    public JOSEObjectType getType() {
        return this.typ;
    }

    protected Header(Algorithm algorithm, JOSEObjectType jOSEObjectType, String str, Set<String> set, Map<String, Object> map, Base64URL base64URL) {
        this.alg = algorithm;
        this.typ = jOSEObjectType;
        this.cty = str;
        if (set != null) {
            this.crit = Collections.unmodifiableSet(new HashSet(set));
        } else {
            this.crit = null;
        }
        if (map != null) {
            this.customParams = Collections.unmodifiableMap(new HashMap(map));
        } else {
            this.customParams = EMPTY_CUSTOM_PARAMS;
        }
        this.parsedBase64URL = base64URL;
    }

    protected Header(Header header) {
        this(header.getAlgorithm(), header.getType(), header.getContentType(), header.getCriticalParams(), header.getCustomParams(), header.getParsedBase64URL());
    }

    public Object getCustomParam(String str) {
        return this.customParams.get(str);
    }

    public Set<String> getIncludedParams() {
        HashSet hashSet = new HashSet(getCustomParams().keySet());
        if (getAlgorithm() != null) {
            hashSet.add("alg");
        }
        if (getType() != null) {
            hashSet.add("typ");
        }
        if (getContentType() != null) {
            hashSet.add(HeaderParameterNames.CONTENT_TYPE);
        }
        if (getCriticalParams() != null && !getCriticalParams().isEmpty()) {
            hashSet.add(HeaderParameterNames.CRITICAL);
        }
        return hashSet;
    }

    public Map<String, Object> toJSONObject() {
        Map<String, Object> mapNewJSONObject = JSONObjectUtils.newJSONObject();
        mapNewJSONObject.putAll(this.customParams);
        Algorithm algorithm = this.alg;
        if (algorithm != null) {
            mapNewJSONObject.put("alg", algorithm.toString());
        }
        JOSEObjectType jOSEObjectType = this.typ;
        if (jOSEObjectType != null) {
            mapNewJSONObject.put("typ", jOSEObjectType.toString());
        }
        String str = this.cty;
        if (str != null) {
            mapNewJSONObject.put(HeaderParameterNames.CONTENT_TYPE, str);
        }
        Set<String> set = this.crit;
        if (set != null && !set.isEmpty()) {
            mapNewJSONObject.put(HeaderParameterNames.CRITICAL, new ArrayList(this.crit));
        }
        return mapNewJSONObject;
    }

    public String toString() {
        return JSONObjectUtils.toJSONString(toJSONObject());
    }

    public Base64URL toBase64URL() {
        Base64URL base64URL = this.parsedBase64URL;
        return base64URL == null ? Base64URL.encode(toString()) : base64URL;
    }

    public static Algorithm parseAlgorithm(Map<String, Object> map) throws ParseException {
        String string = JSONObjectUtils.getString(map, "alg");
        if (string == null) {
            throw new ParseException("Missing \"alg\" in header JSON object", 0);
        }
        if (string.equals(Algorithm.NONE.getName())) {
            return Algorithm.NONE;
        }
        if (map.containsKey(HeaderParameterNames.ENCRYPTION_ALGORITHM)) {
            return JWEAlgorithm.parse(string);
        }
        return JWSAlgorithm.parse(string);
    }

    public Header join(UnprotectedHeader unprotectedHeader) throws ParseException {
        Map<String, Object> jSONObject = toJSONObject();
        try {
            HeaderValidation.ensureDisjoint(this, unprotectedHeader);
            if (unprotectedHeader != null) {
                jSONObject.putAll(unprotectedHeader.toJSONObject());
            }
            return parse(jSONObject, (Base64URL) null);
        } catch (IllegalHeaderException e) {
            throw new ParseException(e.getMessage(), 0);
        }
    }

    public static Header parse(Map<String, Object> map) throws ParseException {
        return parse(map, (Base64URL) null);
    }

    public static Header parse(Map<String, Object> map, Base64URL base64URL) throws ParseException {
        String string = JSONObjectUtils.getString(map, "alg");
        if (map.containsKey(HeaderParameterNames.ENCRYPTION_ALGORITHM)) {
            return JWEHeader.parse(map, base64URL);
        }
        if (Algorithm.NONE.getName().equals(string)) {
            return PlainHeader.parse(map, base64URL);
        }
        if (map.containsKey("alg")) {
            return JWSHeader.parse(map, base64URL);
        }
        throw new ParseException("Missing \"alg\" in header JSON object", 0);
    }

    public static Header parse(String str) throws ParseException {
        return parse(str, (Base64URL) null);
    }

    public static Header parse(String str, Base64URL base64URL) throws ParseException {
        return parse(JSONObjectUtils.parse(str, 20000), base64URL);
    }

    public static Header parse(Base64URL base64URL) throws ParseException {
        return parse(base64URL.decodeToString(), base64URL);
    }
}
