package io.sentry.cache;

import io.sentry.Breadcrumb;
import io.sentry.IScope;
import io.sentry.JsonDeserializer;
import io.sentry.ScopeObserverAdapter;
import io.sentry.SentryLevel;
import io.sentry.SentryOptions;
import io.sentry.SpanContext;
import io.sentry.protocol.Contexts;
import io.sentry.protocol.Request;
import io.sentry.protocol.SentryId;
import io.sentry.protocol.User;
import java.util.Collection;
import java.util.Map;

/* loaded from: classes6.dex */
public final class PersistingScopeObserver extends ScopeObserverAdapter {
    public static final String BREADCRUMBS_FILENAME = "breadcrumbs.json";
    public static final String CONTEXTS_FILENAME = "contexts.json";
    public static final String EXTRAS_FILENAME = "extras.json";
    public static final String FINGERPRINT_FILENAME = "fingerprint.json";
    public static final String LEVEL_FILENAME = "level.json";
    public static final String REPLAY_FILENAME = "replay.json";
    public static final String REQUEST_FILENAME = "request.json";
    public static final String SCOPE_CACHE = ".scope-cache";
    public static final String TAGS_FILENAME = "tags.json";
    public static final String TRACE_FILENAME = "trace.json";
    public static final String TRANSACTION_FILENAME = "transaction.json";
    public static final String USER_FILENAME = "user.json";
    private final SentryOptions options;

    public PersistingScopeObserver(SentryOptions sentryOptions) {
        this.options = sentryOptions;
    }

    @Override // io.sentry.ScopeObserverAdapter, io.sentry.IScopeObserver
    public void setUser(final User user) {
        serializeToDisk(new Runnable() { // from class: io.sentry.cache.PersistingScopeObserver$$ExternalSyntheticLambda11
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m6073lambda$setUser$0$iosentrycachePersistingScopeObserver(user);
            }
        });
    }

    /* renamed from: lambda$setUser$0$io-sentry-cache-PersistingScopeObserver, reason: not valid java name */
    /* synthetic */ void m6073lambda$setUser$0$iosentrycachePersistingScopeObserver(User user) {
        if (user == null) {
            delete(USER_FILENAME);
        } else {
            store(user, USER_FILENAME);
        }
    }

    /* renamed from: lambda$setBreadcrumbs$1$io-sentry-cache-PersistingScopeObserver, reason: not valid java name */
    /* synthetic */ void m6063lambda$setBreadcrumbs$1$iosentrycachePersistingScopeObserver(Collection collection) {
        store(collection, BREADCRUMBS_FILENAME);
    }

    @Override // io.sentry.ScopeObserverAdapter, io.sentry.IScopeObserver
    public void setBreadcrumbs(final Collection<Breadcrumb> collection) {
        serializeToDisk(new Runnable() { // from class: io.sentry.cache.PersistingScopeObserver$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m6063lambda$setBreadcrumbs$1$iosentrycachePersistingScopeObserver(collection);
            }
        });
    }

    /* renamed from: lambda$setTags$2$io-sentry-cache-PersistingScopeObserver, reason: not valid java name */
    /* synthetic */ void m6070lambda$setTags$2$iosentrycachePersistingScopeObserver(Map map) {
        store(map, "tags.json");
    }

    @Override // io.sentry.ScopeObserverAdapter, io.sentry.IScopeObserver
    public void setTags(final Map<String, String> map) {
        serializeToDisk(new Runnable() { // from class: io.sentry.cache.PersistingScopeObserver$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m6070lambda$setTags$2$iosentrycachePersistingScopeObserver(map);
            }
        });
    }

    /* renamed from: lambda$setExtras$3$io-sentry-cache-PersistingScopeObserver, reason: not valid java name */
    /* synthetic */ void m6065lambda$setExtras$3$iosentrycachePersistingScopeObserver(Map map) {
        store(map, EXTRAS_FILENAME);
    }

    @Override // io.sentry.ScopeObserverAdapter, io.sentry.IScopeObserver
    public void setExtras(final Map<String, Object> map) {
        serializeToDisk(new Runnable() { // from class: io.sentry.cache.PersistingScopeObserver$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m6065lambda$setExtras$3$iosentrycachePersistingScopeObserver(map);
            }
        });
    }

    @Override // io.sentry.ScopeObserverAdapter, io.sentry.IScopeObserver
    public void setRequest(final Request request) {
        serializeToDisk(new Runnable() { // from class: io.sentry.cache.PersistingScopeObserver$$ExternalSyntheticLambda9
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m6069lambda$setRequest$4$iosentrycachePersistingScopeObserver(request);
            }
        });
    }

    /* renamed from: lambda$setRequest$4$io-sentry-cache-PersistingScopeObserver, reason: not valid java name */
    /* synthetic */ void m6069lambda$setRequest$4$iosentrycachePersistingScopeObserver(Request request) {
        if (request == null) {
            delete(REQUEST_FILENAME);
        } else {
            store(request, REQUEST_FILENAME);
        }
    }

    /* renamed from: lambda$setFingerprint$5$io-sentry-cache-PersistingScopeObserver, reason: not valid java name */
    /* synthetic */ void m6066lambda$setFingerprint$5$iosentrycachePersistingScopeObserver(Collection collection) {
        store(collection, FINGERPRINT_FILENAME);
    }

    @Override // io.sentry.ScopeObserverAdapter, io.sentry.IScopeObserver
    public void setFingerprint(final Collection<String> collection) {
        serializeToDisk(new Runnable() { // from class: io.sentry.cache.PersistingScopeObserver$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m6066lambda$setFingerprint$5$iosentrycachePersistingScopeObserver(collection);
            }
        });
    }

    @Override // io.sentry.ScopeObserverAdapter, io.sentry.IScopeObserver
    public void setLevel(final SentryLevel sentryLevel) {
        serializeToDisk(new Runnable() { // from class: io.sentry.cache.PersistingScopeObserver$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m6067lambda$setLevel$6$iosentrycachePersistingScopeObserver(sentryLevel);
            }
        });
    }

    /* renamed from: lambda$setLevel$6$io-sentry-cache-PersistingScopeObserver, reason: not valid java name */
    /* synthetic */ void m6067lambda$setLevel$6$iosentrycachePersistingScopeObserver(SentryLevel sentryLevel) {
        if (sentryLevel == null) {
            delete(LEVEL_FILENAME);
        } else {
            store(sentryLevel, LEVEL_FILENAME);
        }
    }

    @Override // io.sentry.ScopeObserverAdapter, io.sentry.IScopeObserver
    public void setTransaction(final String str) {
        serializeToDisk(new Runnable() { // from class: io.sentry.cache.PersistingScopeObserver$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m6072lambda$setTransaction$7$iosentrycachePersistingScopeObserver(str);
            }
        });
    }

    /* renamed from: lambda$setTransaction$7$io-sentry-cache-PersistingScopeObserver, reason: not valid java name */
    /* synthetic */ void m6072lambda$setTransaction$7$iosentrycachePersistingScopeObserver(String str) {
        if (str == null) {
            delete(TRANSACTION_FILENAME);
        } else {
            store(str, TRANSACTION_FILENAME);
        }
    }

    @Override // io.sentry.ScopeObserverAdapter, io.sentry.IScopeObserver
    public void setTrace(final SpanContext spanContext, final IScope iScope) {
        serializeToDisk(new Runnable() { // from class: io.sentry.cache.PersistingScopeObserver$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m6071lambda$setTrace$8$iosentrycachePersistingScopeObserver(spanContext, iScope);
            }
        });
    }

    /* renamed from: lambda$setTrace$8$io-sentry-cache-PersistingScopeObserver, reason: not valid java name */
    /* synthetic */ void m6071lambda$setTrace$8$iosentrycachePersistingScopeObserver(SpanContext spanContext, IScope iScope) {
        if (spanContext == null) {
            store(iScope.getPropagationContext().toSpanContext(), TRACE_FILENAME);
        } else {
            store(spanContext, TRACE_FILENAME);
        }
    }

    /* renamed from: lambda$setContexts$9$io-sentry-cache-PersistingScopeObserver, reason: not valid java name */
    /* synthetic */ void m6064lambda$setContexts$9$iosentrycachePersistingScopeObserver(Contexts contexts) {
        store(contexts, CONTEXTS_FILENAME);
    }

    @Override // io.sentry.ScopeObserverAdapter, io.sentry.IScopeObserver
    public void setContexts(final Contexts contexts) {
        serializeToDisk(new Runnable() { // from class: io.sentry.cache.PersistingScopeObserver$$ExternalSyntheticLambda10
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m6064lambda$setContexts$9$iosentrycachePersistingScopeObserver(contexts);
            }
        });
    }

    /* renamed from: lambda$setReplayId$10$io-sentry-cache-PersistingScopeObserver, reason: not valid java name */
    /* synthetic */ void m6068lambda$setReplayId$10$iosentrycachePersistingScopeObserver(SentryId sentryId) {
        store(sentryId, REPLAY_FILENAME);
    }

    @Override // io.sentry.ScopeObserverAdapter, io.sentry.IScopeObserver
    public void setReplayId(final SentryId sentryId) {
        serializeToDisk(new Runnable() { // from class: io.sentry.cache.PersistingScopeObserver$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m6068lambda$setReplayId$10$iosentrycachePersistingScopeObserver(sentryId);
            }
        });
    }

    private void serializeToDisk(final Runnable runnable) {
        if (Thread.currentThread().getName().contains("SentryExecutor")) {
            runnable.run();
            return;
        }
        try {
            this.options.getExecutorService().submit(new Runnable() { // from class: io.sentry.cache.PersistingScopeObserver$$ExternalSyntheticLambda7
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m6062xdc457aaf(runnable);
                }
            });
        } catch (Throwable th) {
            this.options.getLogger().log(SentryLevel.ERROR, "Serialization task could not be scheduled", th);
        }
    }

    /* renamed from: lambda$serializeToDisk$11$io-sentry-cache-PersistingScopeObserver, reason: not valid java name */
    /* synthetic */ void m6062xdc457aaf(Runnable runnable) {
        try {
            runnable.run();
        } catch (Throwable th) {
            this.options.getLogger().log(SentryLevel.ERROR, "Serialization task failed", th);
        }
    }

    private <T> void store(T t, String str) {
        store(this.options, t, str);
    }

    private void delete(String str) {
        CacheUtils.delete(this.options, SCOPE_CACHE, str);
    }

    public static <T> void store(SentryOptions sentryOptions, T t, String str) {
        CacheUtils.store(sentryOptions, t, SCOPE_CACHE, str);
    }

    public static <T> T read(SentryOptions sentryOptions, String str, Class<T> cls) {
        return (T) read(sentryOptions, str, cls, null);
    }

    public static <T, R> T read(SentryOptions sentryOptions, String str, Class<T> cls, JsonDeserializer<R> jsonDeserializer) {
        return (T) CacheUtils.read(sentryOptions, SCOPE_CACHE, str, cls, jsonDeserializer);
    }
}
