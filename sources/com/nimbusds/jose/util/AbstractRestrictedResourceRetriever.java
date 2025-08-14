package com.nimbusds.jose.util;

import java.util.List;
import java.util.Map;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
/* loaded from: classes2.dex */
public abstract class AbstractRestrictedResourceRetriever implements RestrictedResourceRetriever {
    private int connectTimeout;
    private Map<String, List<String>> headers;
    private int readTimeout;
    private int sizeLimit;

    @Override // com.nimbusds.jose.util.RestrictedResourceRetriever
    public int getConnectTimeout() {
        return this.connectTimeout;
    }

    @Override // com.nimbusds.jose.util.RestrictedResourceRetriever
    public Map<String, List<String>> getHeaders() {
        return this.headers;
    }

    @Override // com.nimbusds.jose.util.RestrictedResourceRetriever
    public int getReadTimeout() {
        return this.readTimeout;
    }

    @Override // com.nimbusds.jose.util.RestrictedResourceRetriever
    public int getSizeLimit() {
        return this.sizeLimit;
    }

    @Override // com.nimbusds.jose.util.RestrictedResourceRetriever
    public void setHeaders(Map<String, List<String>> map) {
        this.headers = map;
    }

    public AbstractRestrictedResourceRetriever(int i, int i2, int i3) {
        setConnectTimeout(i);
        setReadTimeout(i2);
        setSizeLimit(i3);
    }

    @Override // com.nimbusds.jose.util.RestrictedResourceRetriever
    public void setConnectTimeout(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("The connect timeout must not be negative");
        }
        this.connectTimeout = i;
    }

    @Override // com.nimbusds.jose.util.RestrictedResourceRetriever
    public void setReadTimeout(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("The read timeout must not be negative");
        }
        this.readTimeout = i;
    }

    @Override // com.nimbusds.jose.util.RestrictedResourceRetriever
    public void setSizeLimit(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("The size limit must not be negative");
        }
        this.sizeLimit = i;
    }
}
