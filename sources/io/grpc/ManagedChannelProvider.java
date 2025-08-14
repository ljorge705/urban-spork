package io.grpc;

import com.google.common.base.Preconditions;
import java.net.SocketAddress;
import java.util.Collection;

/* loaded from: classes6.dex */
public abstract class ManagedChannelProvider {
    protected abstract ManagedChannelBuilder<?> builderForAddress(String str, int i);

    protected abstract ManagedChannelBuilder<?> builderForTarget(String str);

    protected abstract Collection<Class<? extends SocketAddress>> getSupportedSocketAddressTypes();

    protected abstract boolean isAvailable();

    protected abstract int priority();

    public static ManagedChannelProvider provider() {
        ManagedChannelProvider managedChannelProviderProvider = ManagedChannelRegistry.getDefaultRegistry().provider();
        if (managedChannelProviderProvider != null) {
            return managedChannelProviderProvider;
        }
        throw new ProviderNotFoundException("No functional channel service provider found. Try adding a dependency on the grpc-okhttp, grpc-netty, or grpc-netty-shaded artifact");
    }

    protected NewChannelBuilderResult newChannelBuilder(String str, ChannelCredentials channelCredentials) {
        return NewChannelBuilderResult.error("ChannelCredentials are unsupported");
    }

    public static final class NewChannelBuilderResult {
        private final ManagedChannelBuilder<?> channelBuilder;
        private final String error;

        public ManagedChannelBuilder<?> getChannelBuilder() {
            return this.channelBuilder;
        }

        public String getError() {
            return this.error;
        }

        private NewChannelBuilderResult(ManagedChannelBuilder<?> managedChannelBuilder, String str) {
            this.channelBuilder = managedChannelBuilder;
            this.error = str;
        }

        public static NewChannelBuilderResult channelBuilder(ManagedChannelBuilder<?> managedChannelBuilder) {
            return new NewChannelBuilderResult((ManagedChannelBuilder) Preconditions.checkNotNull(managedChannelBuilder), null);
        }

        public static NewChannelBuilderResult error(String str) {
            return new NewChannelBuilderResult(null, (String) Preconditions.checkNotNull(str));
        }
    }

    public static final class ProviderNotFoundException extends RuntimeException {
        private static final long serialVersionUID = 1;

        public ProviderNotFoundException(String str) {
            super(str);
        }
    }
}
