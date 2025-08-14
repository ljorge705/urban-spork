package com.nimbusds.jwt;

import com.nimbusds.jose.Payload;
import com.nimbusds.jose.util.JSONArrayUtils;
import com.nimbusds.jose.util.JSONObjectUtils;
import com.nimbusds.jwt.util.DateUtils;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import net.jcip.annotations.Immutable;

@Immutable
/* loaded from: classes2.dex */
public final class JWTClaimsSet implements Serializable {
    private static final Set<String> REGISTERED_CLAIM_NAMES;
    private static final long serialVersionUID = 1;
    private final Map<String, Object> claims;

    public static Set<String> getRegisteredNames() {
        return REGISTERED_CLAIM_NAMES;
    }

    static {
        HashSet hashSet = new HashSet();
        hashSet.add(JWTClaimNames.ISSUER);
        hashSet.add(JWTClaimNames.SUBJECT);
        hashSet.add(JWTClaimNames.AUDIENCE);
        hashSet.add("exp");
        hashSet.add("nbf");
        hashSet.add("iat");
        hashSet.add(JWTClaimNames.JWT_ID);
        REGISTERED_CLAIM_NAMES = Collections.unmodifiableSet(hashSet);
    }

    public static class Builder {
        private final Map<String, Object> claims;

        public Builder() {
            this.claims = new LinkedHashMap();
        }

        public Builder(JWTClaimsSet jWTClaimsSet) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            this.claims = linkedHashMap;
            linkedHashMap.putAll(jWTClaimsSet.claims);
        }

        public Builder issuer(String str) {
            this.claims.put(JWTClaimNames.ISSUER, str);
            return this;
        }

        public Builder subject(String str) {
            this.claims.put(JWTClaimNames.SUBJECT, str);
            return this;
        }

        public Builder audience(List<String> list) {
            this.claims.put(JWTClaimNames.AUDIENCE, list);
            return this;
        }

        public Builder audience(String str) {
            if (str == null) {
                this.claims.put(JWTClaimNames.AUDIENCE, null);
            } else {
                this.claims.put(JWTClaimNames.AUDIENCE, Collections.singletonList(str));
            }
            return this;
        }

        public Builder expirationTime(Date date) {
            this.claims.put("exp", date);
            return this;
        }

        public Builder notBeforeTime(Date date) {
            this.claims.put("nbf", date);
            return this;
        }

        public Builder issueTime(Date date) {
            this.claims.put("iat", date);
            return this;
        }

        public Builder jwtID(String str) {
            this.claims.put(JWTClaimNames.JWT_ID, str);
            return this;
        }

        public Builder claim(String str, Object obj) {
            this.claims.put(str, obj);
            return this;
        }

        public Map<String, Object> getClaims() {
            return Collections.unmodifiableMap(this.claims);
        }

        public JWTClaimsSet build() {
            return new JWTClaimsSet(this.claims);
        }
    }

    private JWTClaimsSet(Map<String, Object> map) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        this.claims = linkedHashMap;
        linkedHashMap.putAll(map);
    }

    public String getIssuer() {
        try {
            return getStringClaim(JWTClaimNames.ISSUER);
        } catch (ParseException unused) {
            return null;
        }
    }

    public String getSubject() {
        try {
            return getStringClaim(JWTClaimNames.SUBJECT);
        } catch (ParseException unused) {
            return null;
        }
    }

    public List<String> getAudience() {
        Object claim = getClaim(JWTClaimNames.AUDIENCE);
        if (claim instanceof String) {
            return Collections.singletonList((String) claim);
        }
        try {
            List<String> stringListClaim = getStringListClaim(JWTClaimNames.AUDIENCE);
            return stringListClaim != null ? stringListClaim : Collections.emptyList();
        } catch (ParseException unused) {
            return Collections.emptyList();
        }
    }

    public Date getExpirationTime() {
        try {
            return getDateClaim("exp");
        } catch (ParseException unused) {
            return null;
        }
    }

    public Date getNotBeforeTime() {
        try {
            return getDateClaim("nbf");
        } catch (ParseException unused) {
            return null;
        }
    }

    public Date getIssueTime() {
        try {
            return getDateClaim("iat");
        } catch (ParseException unused) {
            return null;
        }
    }

    public String getJWTID() {
        try {
            return getStringClaim(JWTClaimNames.JWT_ID);
        } catch (ParseException unused) {
            return null;
        }
    }

    public Object getClaim(String str) {
        return this.claims.get(str);
    }

    public String getStringClaim(String str) throws ParseException {
        Object claim = getClaim(str);
        if (claim == null || (claim instanceof String)) {
            return (String) claim;
        }
        throw new ParseException("The " + str + " claim is not a String", 0);
    }

    public List<Object> getListClaim(String str) throws ParseException {
        if (getClaim(str) == null) {
            return null;
        }
        try {
            return (List) getClaim(str);
        } catch (ClassCastException unused) {
            throw new ParseException("The " + str + " claim is not a list / JSON array", 0);
        }
    }

    public String[] getStringArrayClaim(String str) throws ParseException {
        List<Object> listClaim = getListClaim(str);
        if (listClaim == null) {
            return null;
        }
        int size = listClaim.size();
        String[] strArr = new String[size];
        for (int i = 0; i < size; i++) {
            try {
                strArr[i] = (String) listClaim.get(i);
            } catch (ClassCastException unused) {
                throw new ParseException("The " + str + " claim is not a list / JSON array of strings", 0);
            }
        }
        return strArr;
    }

    public List<String> getStringListClaim(String str) throws ParseException {
        String[] stringArrayClaim = getStringArrayClaim(str);
        if (stringArrayClaim == null) {
            return null;
        }
        return Collections.unmodifiableList(Arrays.asList(stringArrayClaim));
    }

    public URI getURIClaim(String str) throws ParseException {
        String stringClaim = getStringClaim(str);
        if (stringClaim == null) {
            return null;
        }
        try {
            return new URI(stringClaim);
        } catch (URISyntaxException e) {
            throw new ParseException("The \"" + str + "\" claim is not a URI: " + e.getMessage(), 0);
        }
    }

    public Boolean getBooleanClaim(String str) throws ParseException {
        Object claim = getClaim(str);
        if (claim == null || (claim instanceof Boolean)) {
            return (Boolean) claim;
        }
        throw new ParseException("The \"" + str + "\" claim is not a Boolean", 0);
    }

    public Integer getIntegerClaim(String str) throws ParseException {
        Object claim = getClaim(str);
        if (claim == null) {
            return null;
        }
        if (claim instanceof Number) {
            return Integer.valueOf(((Number) claim).intValue());
        }
        throw new ParseException("The \"" + str + "\" claim is not an Integer", 0);
    }

    public Long getLongClaim(String str) throws ParseException {
        Object claim = getClaim(str);
        if (claim == null) {
            return null;
        }
        if (claim instanceof Number) {
            return Long.valueOf(((Number) claim).longValue());
        }
        throw new ParseException("The \"" + str + "\" claim is not a Number", 0);
    }

    public Date getDateClaim(String str) throws ParseException {
        Object claim = getClaim(str);
        if (claim == null) {
            return null;
        }
        if (claim instanceof Date) {
            return (Date) claim;
        }
        if (claim instanceof Number) {
            return DateUtils.fromSecondsSinceEpoch(((Number) claim).longValue());
        }
        throw new ParseException("The \"" + str + "\" claim is not a Date", 0);
    }

    public Float getFloatClaim(String str) throws ParseException {
        Object claim = getClaim(str);
        if (claim == null) {
            return null;
        }
        if (claim instanceof Number) {
            return Float.valueOf(((Number) claim).floatValue());
        }
        throw new ParseException("The \"" + str + "\" claim is not a Float", 0);
    }

    public Double getDoubleClaim(String str) throws ParseException {
        Object claim = getClaim(str);
        if (claim == null) {
            return null;
        }
        if (claim instanceof Number) {
            return Double.valueOf(((Number) claim).doubleValue());
        }
        throw new ParseException("The \"" + str + "\" claim is not a Double", 0);
    }

    public Map<String, Object> getJSONObjectClaim(String str) throws ParseException {
        Object claim = getClaim(str);
        if (claim == null) {
            return null;
        }
        if (claim instanceof Map) {
            Map<String, Object> mapNewJSONObject = JSONObjectUtils.newJSONObject();
            for (Map.Entry entry : ((Map) claim).entrySet()) {
                if (entry.getKey() instanceof String) {
                    mapNewJSONObject.put((String) entry.getKey(), entry.getValue());
                }
            }
            return mapNewJSONObject;
        }
        throw new ParseException("The \"" + str + "\" claim is not a JSON object or Map", 0);
    }

    public Map<String, Object> getClaims() {
        return Collections.unmodifiableMap(this.claims);
    }

    public Payload toPayload() {
        return new Payload(toJSONObject());
    }

    public Map<String, Object> toJSONObject() {
        return toJSONObject(false);
    }

    public Map<String, Object> toJSONObject(boolean z) {
        Map<String, Object> mapNewJSONObject = JSONObjectUtils.newJSONObject();
        for (Map.Entry<String, Object> entry : this.claims.entrySet()) {
            if (entry.getValue() instanceof Date) {
                mapNewJSONObject.put(entry.getKey(), Long.valueOf(DateUtils.toSecondsSinceEpoch((Date) entry.getValue())));
            } else if (JWTClaimNames.AUDIENCE.equals(entry.getKey())) {
                List<String> audience = getAudience();
                if (audience == null || audience.isEmpty()) {
                    if (z) {
                        mapNewJSONObject.put(JWTClaimNames.AUDIENCE, null);
                    }
                } else if (audience.size() == 1) {
                    mapNewJSONObject.put(JWTClaimNames.AUDIENCE, audience.get(0));
                } else {
                    List<Object> listNewJSONArray = JSONArrayUtils.newJSONArray();
                    listNewJSONArray.addAll(audience);
                    mapNewJSONObject.put(JWTClaimNames.AUDIENCE, listNewJSONArray);
                }
            } else if (entry.getValue() != null) {
                mapNewJSONObject.put(entry.getKey(), entry.getValue());
            } else if (z) {
                mapNewJSONObject.put(entry.getKey(), null);
            }
        }
        return mapNewJSONObject;
    }

    public String toString() {
        return JSONObjectUtils.toJSONString(toJSONObject());
    }

    public String toString(boolean z) {
        return JSONObjectUtils.toJSONString(toJSONObject(z));
    }

    public <T> T toType(JWTClaimsSetTransformer<T> jWTClaimsSetTransformer) {
        return jWTClaimsSetTransformer.transform(this);
    }

    public static JWTClaimsSet parse(Map<String, Object> map) throws ParseException {
        Builder builder = new Builder();
        for (String str : map.keySet()) {
            str.hashCode();
            switch (str) {
                case "aud":
                    Object obj = map.get(JWTClaimNames.AUDIENCE);
                    if (obj instanceof String) {
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(JSONObjectUtils.getString(map, JWTClaimNames.AUDIENCE));
                        builder.audience(arrayList);
                        break;
                    } else if (obj instanceof List) {
                        builder.audience(JSONObjectUtils.getStringList(map, JWTClaimNames.AUDIENCE));
                        break;
                    } else if (obj == null) {
                        builder.audience((String) null);
                        break;
                    } else {
                        throw new ParseException("Unexpected type of aud claim", 0);
                    }
                case "exp":
                    builder.expirationTime(new Date(JSONObjectUtils.getLong(map, "exp") * 1000));
                    break;
                case "iat":
                    builder.issueTime(new Date(JSONObjectUtils.getLong(map, "iat") * 1000));
                    break;
                case "iss":
                    builder.issuer(JSONObjectUtils.getString(map, JWTClaimNames.ISSUER));
                    break;
                case "jti":
                    builder.jwtID(JSONObjectUtils.getString(map, JWTClaimNames.JWT_ID));
                    break;
                case "nbf":
                    builder.notBeforeTime(new Date(JSONObjectUtils.getLong(map, "nbf") * 1000));
                    break;
                case "sub":
                    Object obj2 = map.get(JWTClaimNames.SUBJECT);
                    if (obj2 instanceof String) {
                        builder.subject(JSONObjectUtils.getString(map, JWTClaimNames.SUBJECT));
                        break;
                    } else if (obj2 instanceof Number) {
                        builder.subject(String.valueOf(obj2));
                        break;
                    } else if (obj2 == null) {
                        builder.subject(null);
                        break;
                    } else {
                        throw new ParseException("Unexpected type of sub claim", 0);
                    }
                default:
                    builder.claim(str, map.get(str));
                    break;
            }
        }
        return builder.build();
    }

    public static JWTClaimsSet parse(String str) throws ParseException {
        return parse(JSONObjectUtils.parse(str));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof JWTClaimsSet) {
            return Objects.equals(this.claims, ((JWTClaimsSet) obj).claims);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(this.claims);
    }
}
