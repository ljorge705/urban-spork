package com.nimbusds.jose.crypto.impl;

import com.nimbusds.jose.Header;
import com.nimbusds.jose.HeaderParameterNames;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEHeader;
import java.util.Collections;
import java.util.Set;

/* loaded from: classes2.dex */
public class CriticalHeaderParamsDeferral {
    private Set<String> deferredParams = Collections.emptySet();

    public Set<String> getProcessedCriticalHeaderParams() {
        return Collections.singleton(HeaderParameterNames.BASE64_URL_ENCODE_PAYLOAD);
    }

    public Set<String> getDeferredCriticalHeaderParams() {
        return Collections.unmodifiableSet(this.deferredParams);
    }

    public void setDeferredCriticalHeaderParams(Set<String> set) {
        if (set == null) {
            this.deferredParams = Collections.emptySet();
        } else {
            this.deferredParams = set;
        }
    }

    public boolean headerPasses(Header header) {
        if (header.getCriticalParams() == null) {
            return true;
        }
        for (String str : header.getCriticalParams()) {
            if (!getProcessedCriticalHeaderParams().contains(str) && !getDeferredCriticalHeaderParams().contains(str)) {
                return false;
            }
        }
        return true;
    }

    public void ensureHeaderPasses(JWEHeader jWEHeader) throws JOSEException {
        if (!headerPasses(jWEHeader)) {
            throw new JOSEException("Unsupported critical header parameter(s)");
        }
    }
}
