package com.nimbusds.jose.jwk.source;

import com.nimbusds.jose.KeySourceException;
import com.nimbusds.jose.RemoteKeySourceException;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKMatcher;
import com.nimbusds.jose.jwk.JWKSelector;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jose.util.DefaultResourceRetriever;
import com.nimbusds.jose.util.ResourceRetriever;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import net.jcip.annotations.ThreadSafe;

@Deprecated
@ThreadSafe
/* loaded from: classes2.dex */
public class RemoteJWKSet<C extends SecurityContext> implements JWKSource<C> {
    public static final int DEFAULT_HTTP_CONNECT_TIMEOUT = 500;
    public static final int DEFAULT_HTTP_READ_TIMEOUT = 500;
    public static final int DEFAULT_HTTP_SIZE_LIMIT = 51200;
    private final JWKSource<C> failoverJWKSource;
    private final JWKSetCache jwkSetCache;
    private final ResourceRetriever jwkSetRetriever;
    private final URL jwkSetURL;

    public JWKSource<C> getFailoverJWKSource() {
        return this.failoverJWKSource;
    }

    public JWKSetCache getJWKSetCache() {
        return this.jwkSetCache;
    }

    public URL getJWKSetURL() {
        return this.jwkSetURL;
    }

    public ResourceRetriever getResourceRetriever() {
        return this.jwkSetRetriever;
    }

    public static int resolveDefaultHTTPConnectTimeout() {
        return resolveDefault(RemoteJWKSet.class.getName() + ".defaultHttpConnectTimeout", 500);
    }

    public static int resolveDefaultHTTPReadTimeout() {
        return resolveDefault(RemoteJWKSet.class.getName() + ".defaultHttpReadTimeout", 500);
    }

    public static int resolveDefaultHTTPSizeLimit() {
        return resolveDefault(RemoteJWKSet.class.getName() + ".defaultHttpSizeLimit", 51200);
    }

    private static int resolveDefault(String str, int i) {
        String property = System.getProperty(str);
        if (property == null) {
            return i;
        }
        try {
            return Integer.parseInt(property);
        } catch (NumberFormatException unused) {
            return i;
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public RemoteJWKSet(URL url) {
        this(url, (JWKSource) null);
    }

    public RemoteJWKSet(URL url, JWKSource<C> jWKSource) {
        this(url, jWKSource, null, null);
    }

    public RemoteJWKSet(URL url, ResourceRetriever resourceRetriever) {
        this(url, resourceRetriever, null);
    }

    public RemoteJWKSet(URL url, ResourceRetriever resourceRetriever, JWKSetCache jWKSetCache) {
        this(url, null, resourceRetriever, jWKSetCache);
    }

    public RemoteJWKSet(URL url, JWKSource<C> jWKSource, ResourceRetriever resourceRetriever, JWKSetCache jWKSetCache) {
        if (url == null) {
            throw new IllegalArgumentException("The JWK set URL must not be null");
        }
        this.jwkSetURL = url;
        this.failoverJWKSource = jWKSource;
        if (resourceRetriever != null) {
            this.jwkSetRetriever = resourceRetriever;
        } else {
            this.jwkSetRetriever = new DefaultResourceRetriever(resolveDefaultHTTPConnectTimeout(), resolveDefaultHTTPReadTimeout(), resolveDefaultHTTPSizeLimit());
        }
        if (jWKSetCache != null) {
            this.jwkSetCache = jWKSetCache;
        } else {
            this.jwkSetCache = new DefaultJWKSetCache();
        }
    }

    private JWKSet updateJWKSetFromURL() throws RemoteKeySourceException {
        try {
            try {
                JWKSet jWKSet = JWKSet.parse(this.jwkSetRetriever.retrieveResource(this.jwkSetURL).getContent());
                this.jwkSetCache.put(jWKSet);
                return jWKSet;
            } catch (ParseException e) {
                throw new RemoteKeySourceException("Couldn't parse remote JWK set: " + e.getMessage(), e);
            }
        } catch (IOException e2) {
            throw new RemoteKeySourceException("Couldn't retrieve remote JWK set: " + e2.getMessage(), e2);
        }
    }

    public JWKSet getCachedJWKSet() {
        return this.jwkSetCache.get();
    }

    protected static String getFirstSpecifiedKeyID(JWKMatcher jWKMatcher) {
        Set<String> keyIDs = jWKMatcher.getKeyIDs();
        if (keyIDs != null && !keyIDs.isEmpty()) {
            for (String str : keyIDs) {
                if (str != null) {
                    return str;
                }
            }
        }
        return null;
    }

    private List<JWK> failover(Exception exc, JWKSelector jWKSelector, C c) throws RemoteKeySourceException {
        if (getFailoverJWKSource() == null) {
            return null;
        }
        try {
            return getFailoverJWKSource().get(jWKSelector, c);
        } catch (KeySourceException e) {
            throw new RemoteKeySourceException(exc.getMessage() + "; Failover JWK source retrieval failed with: " + e.getMessage(), e);
        }
    }

    @Override // com.nimbusds.jose.jwk.source.JWKSource
    public List<JWK> get(JWKSelector jWKSelector, C c) throws Exception {
        JWKSet jWKSetUpdateJWKSetFromURL;
        JWKSet jWKSetUpdateJWKSetFromURL2 = this.jwkSetCache.get();
        if (this.jwkSetCache.requiresRefresh() || jWKSetUpdateJWKSetFromURL2 == null) {
            try {
                synchronized (this) {
                    jWKSetUpdateJWKSetFromURL2 = this.jwkSetCache.get();
                    if (this.jwkSetCache.requiresRefresh() || jWKSetUpdateJWKSetFromURL2 == null) {
                        jWKSetUpdateJWKSetFromURL2 = updateJWKSetFromURL();
                    }
                }
            } catch (Exception e) {
                List<JWK> listFailover = failover(e, jWKSelector, c);
                if (listFailover != null) {
                    return listFailover;
                }
                if (jWKSetUpdateJWKSetFromURL2 == null) {
                    throw e;
                }
            }
        }
        List<JWK> listSelect = jWKSelector.select(jWKSetUpdateJWKSetFromURL2);
        if (!listSelect.isEmpty()) {
            return listSelect;
        }
        String firstSpecifiedKeyID = getFirstSpecifiedKeyID(jWKSelector.getMatcher());
        if (firstSpecifiedKeyID == null) {
            return Collections.emptyList();
        }
        if (jWKSetUpdateJWKSetFromURL2.getKeyByKeyId(firstSpecifiedKeyID) != null) {
            return Collections.emptyList();
        }
        try {
            synchronized (this) {
                if (jWKSetUpdateJWKSetFromURL2 == this.jwkSetCache.get()) {
                    jWKSetUpdateJWKSetFromURL = updateJWKSetFromURL();
                } else {
                    jWKSetUpdateJWKSetFromURL = this.jwkSetCache.get();
                }
                if (jWKSetUpdateJWKSetFromURL == null) {
                    return Collections.emptyList();
                }
                return jWKSelector.select(jWKSetUpdateJWKSetFromURL);
            }
        } catch (KeySourceException e2) {
            List<JWK> listFailover2 = failover(e2, jWKSelector, c);
            if (listFailover2 != null) {
                return listFailover2;
            }
            throw e2;
        }
    }
}
