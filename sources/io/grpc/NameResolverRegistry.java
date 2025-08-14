package io.grpc;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import io.grpc.NameResolver;
import io.grpc.ServiceProviders;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

/* loaded from: classes6.dex */
public final class NameResolverRegistry {
    private static final String UNKNOWN_SCHEME = "unknown";
    private static NameResolverRegistry instance;
    private static final Logger logger = Logger.getLogger(NameResolverRegistry.class.getName());
    private final NameResolver.Factory factory = new NameResolverFactory();
    private String defaultScheme = "unknown";
    private final LinkedHashSet<NameResolverProvider> allProviders = new LinkedHashSet<>();
    private ImmutableMap<String, NameResolverProvider> effectiveProviders = ImmutableMap.of();

    public NameResolver.Factory asFactory() {
        return this.factory;
    }

    public synchronized void register(NameResolverProvider nameResolverProvider) {
        addProvider(nameResolverProvider);
        refreshProviders();
    }

    private synchronized void addProvider(NameResolverProvider nameResolverProvider) {
        Preconditions.checkArgument(nameResolverProvider.isAvailable(), "isAvailable() returned false");
        this.allProviders.add(nameResolverProvider);
    }

    public synchronized void deregister(NameResolverProvider nameResolverProvider) {
        this.allProviders.remove(nameResolverProvider);
        refreshProviders();
    }

    private synchronized void refreshProviders() {
        HashMap map = new HashMap();
        String scheme = "unknown";
        Iterator<NameResolverProvider> it = this.allProviders.iterator();
        int iPriority = Integer.MIN_VALUE;
        while (it.hasNext()) {
            NameResolverProvider next = it.next();
            String scheme2 = next.getScheme();
            NameResolverProvider nameResolverProvider = (NameResolverProvider) map.get(scheme2);
            if (nameResolverProvider == null || nameResolverProvider.priority() < next.priority()) {
                map.put(scheme2, next);
            }
            if (iPriority < next.priority()) {
                iPriority = next.priority();
                scheme = next.getScheme();
            }
        }
        this.effectiveProviders = ImmutableMap.copyOf((Map) map);
        this.defaultScheme = scheme;
    }

    public static synchronized NameResolverRegistry getDefaultRegistry() {
        if (instance == null) {
            List<NameResolverProvider> listLoadAll = ServiceProviders.loadAll(NameResolverProvider.class, getHardCodedClasses(), NameResolverProvider.class.getClassLoader(), new NameResolverPriorityAccessor());
            if (listLoadAll.isEmpty()) {
                logger.warning("No NameResolverProviders found via ServiceLoader, including for DNS. This is probably due to a broken build. If using ProGuard, check your configuration");
            }
            instance = new NameResolverRegistry();
            for (NameResolverProvider nameResolverProvider : listLoadAll) {
                logger.fine("Service loader found " + nameResolverProvider);
                instance.addProvider(nameResolverProvider);
            }
            instance.refreshProviders();
        }
        return instance;
    }

    synchronized Map<String, NameResolverProvider> providers() {
        return this.effectiveProviders;
    }

    static List<Class<?>> getHardCodedClasses() {
        ArrayList arrayList = new ArrayList();
        try {
            arrayList.add(Class.forName("io.grpc.internal.DnsNameResolverProvider"));
        } catch (ClassNotFoundException e) {
            logger.log(Level.FINE, "Unable to find DNS NameResolver", (Throwable) e);
        }
        return Collections.unmodifiableList(arrayList);
    }

    private final class NameResolverFactory extends NameResolver.Factory {
        private NameResolverFactory() {
        }

        @Override // io.grpc.NameResolver.Factory
        @Nullable
        public NameResolver newNameResolver(URI uri, NameResolver.Args args) {
            NameResolverProvider nameResolverProvider = NameResolverRegistry.this.providers().get(uri.getScheme());
            if (nameResolverProvider == null) {
                return null;
            }
            return nameResolverProvider.newNameResolver(uri, args);
        }

        @Override // io.grpc.NameResolver.Factory
        public String getDefaultScheme() {
            String str;
            synchronized (NameResolverRegistry.this) {
                str = NameResolverRegistry.this.defaultScheme;
            }
            return str;
        }
    }

    private static final class NameResolverPriorityAccessor implements ServiceProviders.PriorityAccessor<NameResolverProvider> {
        private NameResolverPriorityAccessor() {
        }

        @Override // io.grpc.ServiceProviders.PriorityAccessor
        public boolean isAvailable(NameResolverProvider nameResolverProvider) {
            return nameResolverProvider.isAvailable();
        }

        @Override // io.grpc.ServiceProviders.PriorityAccessor
        public int getPriority(NameResolverProvider nameResolverProvider) {
            return nameResolverProvider.priority();
        }
    }
}
