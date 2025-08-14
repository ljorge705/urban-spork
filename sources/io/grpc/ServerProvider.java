package io.grpc;

import com.google.common.base.Preconditions;
import io.grpc.ManagedChannelProvider;

/* loaded from: classes6.dex */
public abstract class ServerProvider {
    protected abstract ServerBuilder<?> builderForPort(int i);

    protected abstract boolean isAvailable();

    protected abstract int priority();

    public static ServerProvider provider() {
        ServerProvider serverProviderProvider = ServerRegistry.getDefaultRegistry().provider();
        if (serverProviderProvider != null) {
            return serverProviderProvider;
        }
        throw new ManagedChannelProvider.ProviderNotFoundException("No functional server found. Try adding a dependency on the grpc-netty or grpc-netty-shaded artifact");
    }

    protected NewServerBuilderResult newServerBuilderForPort(int i, ServerCredentials serverCredentials) {
        return NewServerBuilderResult.error("ServerCredentials are unsupported");
    }

    public static final class NewServerBuilderResult {
        private final String error;
        private final ServerBuilder<?> serverBuilder;

        public String getError() {
            return this.error;
        }

        public ServerBuilder<?> getServerBuilder() {
            return this.serverBuilder;
        }

        private NewServerBuilderResult(ServerBuilder<?> serverBuilder, String str) {
            this.serverBuilder = serverBuilder;
            this.error = str;
        }

        public static NewServerBuilderResult serverBuilder(ServerBuilder<?> serverBuilder) {
            return new NewServerBuilderResult((ServerBuilder) Preconditions.checkNotNull(serverBuilder), null);
        }

        public static NewServerBuilderResult error(String str) {
            return new NewServerBuilderResult(null, (String) Preconditions.checkNotNull(str));
        }
    }
}
