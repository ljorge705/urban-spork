package io.grpc;

import com.google.common.base.Preconditions;
import io.grpc.ServerStreamTracer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes6.dex */
final class GlobalInterceptors {
    private static List<ClientInterceptor> clientInterceptors;
    private static boolean isGlobalInterceptorsTracersGet;
    private static boolean isGlobalInterceptorsTracersSet;
    private static List<ServerInterceptor> serverInterceptors;
    private static List<ServerStreamTracer.Factory> serverStreamTracerFactories;

    private GlobalInterceptors() {
    }

    static synchronized void setInterceptorsTracers(List<ClientInterceptor> list, List<ServerInterceptor> list2, List<ServerStreamTracer.Factory> list3) {
        if (isGlobalInterceptorsTracersGet) {
            throw new IllegalStateException("Set cannot be called after any get call");
        }
        if (isGlobalInterceptorsTracersSet) {
            throw new IllegalStateException("Global interceptors and tracers are already set");
        }
        Preconditions.checkNotNull(list);
        Preconditions.checkNotNull(list2);
        Preconditions.checkNotNull(list3);
        clientInterceptors = Collections.unmodifiableList(new ArrayList(list));
        serverInterceptors = Collections.unmodifiableList(new ArrayList(list2));
        serverStreamTracerFactories = Collections.unmodifiableList(new ArrayList(list3));
        isGlobalInterceptorsTracersSet = true;
    }

    static synchronized List<ClientInterceptor> getClientInterceptors() {
        isGlobalInterceptorsTracersGet = true;
        return clientInterceptors;
    }

    static synchronized List<ServerInterceptor> getServerInterceptors() {
        isGlobalInterceptorsTracersGet = true;
        return serverInterceptors;
    }

    static synchronized List<ServerStreamTracer.Factory> getServerStreamTracerFactories() {
        isGlobalInterceptorsTracersGet = true;
        return serverStreamTracerFactories;
    }
}
