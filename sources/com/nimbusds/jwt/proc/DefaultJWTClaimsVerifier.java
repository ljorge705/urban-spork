package com.nimbusds.jwt.proc;

import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.JWTClaimNames;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.util.DateUtils;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
/* loaded from: classes2.dex */
public class DefaultJWTClaimsVerifier<C extends SecurityContext> implements JWTClaimsSetVerifier<C>, ClockSkewAware {
    public static final int DEFAULT_MAX_CLOCK_SKEW_SECONDS = 60;
    private final Set<String> acceptedAudienceValues;
    private final JWTClaimsSet exactMatchClaims;
    private int maxClockSkew;
    private final Set<String> prohibitedClaims;
    private final Set<String> requiredClaims;

    public Set<String> getAcceptedAudienceValues() {
        return this.acceptedAudienceValues;
    }

    public JWTClaimsSet getExactMatchClaims() {
        return this.exactMatchClaims;
    }

    @Override // com.nimbusds.jwt.proc.ClockSkewAware
    public int getMaxClockSkew() {
        return this.maxClockSkew;
    }

    public Set<String> getProhibitedClaims() {
        return this.prohibitedClaims;
    }

    public Set<String> getRequiredClaims() {
        return this.requiredClaims;
    }

    @Override // com.nimbusds.jwt.proc.ClockSkewAware
    public void setMaxClockSkew(int i) {
        this.maxClockSkew = i;
    }

    @Deprecated
    public DefaultJWTClaimsVerifier() {
        this(null, null, null, null);
    }

    public DefaultJWTClaimsVerifier(JWTClaimsSet jWTClaimsSet, Set<String> set) {
        this(null, jWTClaimsSet, set, null);
    }

    public DefaultJWTClaimsVerifier(String str, JWTClaimsSet jWTClaimsSet, Set<String> set) {
        this(str != null ? Collections.singleton(str) : null, jWTClaimsSet, set, null);
    }

    public DefaultJWTClaimsVerifier(Set<String> set, JWTClaimsSet jWTClaimsSet, Set<String> set2, Set<String> set3) {
        this.maxClockSkew = 60;
        Set<String> setUnmodifiableSet = set != null ? Collections.unmodifiableSet(set) : null;
        this.acceptedAudienceValues = setUnmodifiableSet;
        jWTClaimsSet = jWTClaimsSet == null ? new JWTClaimsSet.Builder().build() : jWTClaimsSet;
        this.exactMatchClaims = jWTClaimsSet;
        HashSet hashSet = new HashSet(jWTClaimsSet.getClaims().keySet());
        if (setUnmodifiableSet != null && !setUnmodifiableSet.contains(null)) {
            hashSet.add(JWTClaimNames.AUDIENCE);
        }
        if (set2 != null) {
            hashSet.addAll(set2);
        }
        this.requiredClaims = Collections.unmodifiableSet(hashSet);
        this.prohibitedClaims = set3 != null ? Collections.unmodifiableSet(set3) : Collections.emptySet();
    }

    @Override // com.nimbusds.jwt.proc.JWTClaimsSetVerifier
    public void verify(JWTClaimsSet jWTClaimsSet, C c) throws BadJWTException {
        if (this.acceptedAudienceValues != null) {
            List<String> audience = jWTClaimsSet.getAudience();
            if (audience != null && !audience.isEmpty()) {
                Iterator<String> it = audience.iterator();
                while (it.hasNext()) {
                    if (this.acceptedAudienceValues.contains(it.next())) {
                    }
                }
                throw new BadJWTException("JWT audience rejected: " + audience);
            }
            if (!this.acceptedAudienceValues.contains(null)) {
                throw new BadJWTException("JWT missing required audience");
            }
        }
        if (!jWTClaimsSet.getClaims().keySet().containsAll(this.requiredClaims)) {
            TreeSet treeSet = new TreeSet(this.requiredClaims);
            treeSet.removeAll(jWTClaimsSet.getClaims().keySet());
            throw new BadJWTException("JWT missing required claims: " + treeSet);
        }
        TreeSet treeSet2 = new TreeSet();
        for (String str : this.prohibitedClaims) {
            if (jWTClaimsSet.getClaims().containsKey(str)) {
                treeSet2.add(str);
            }
        }
        if (!treeSet2.isEmpty()) {
            throw new BadJWTException("JWT has prohibited claims: " + treeSet2);
        }
        for (String str2 : this.exactMatchClaims.getClaims().keySet()) {
            Object claim = jWTClaimsSet.getClaim(str2);
            Object claim2 = this.exactMatchClaims.getClaim(str2);
            if (!claim.equals(claim2)) {
                throw new BadJWTException("JWT " + str2 + " claim has value " + claim + ", must be " + claim2);
            }
        }
        Date dateCurrentTime = currentTime();
        if (dateCurrentTime != null) {
            Date expirationTime = jWTClaimsSet.getExpirationTime();
            if (expirationTime != null && !DateUtils.isAfter(expirationTime, dateCurrentTime, this.maxClockSkew)) {
                throw new BadJWTException("Expired JWT");
            }
            Date notBeforeTime = jWTClaimsSet.getNotBeforeTime();
            if (notBeforeTime != null && !DateUtils.isBefore(notBeforeTime, dateCurrentTime, this.maxClockSkew)) {
                throw new BadJWTException("JWT before use time");
            }
        }
    }

    protected Date currentTime() {
        return new Date();
    }
}
