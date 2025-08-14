package io.grpc.internal;

import com.google.common.base.Preconditions;
import io.grpc.NameResolver;
import io.grpc.Status;
import java.util.Map;

/* loaded from: classes6.dex */
public final class ScParser extends NameResolver.ServiceConfigParser {
    private final AutoConfiguredLoadBalancerFactory autoLoadBalancerFactory;
    private final int maxHedgedAttemptsLimit;
    private final int maxRetryAttemptsLimit;
    private final boolean retryEnabled;

    public ScParser(boolean z, int i, int i2, AutoConfiguredLoadBalancerFactory autoConfiguredLoadBalancerFactory) {
        this.retryEnabled = z;
        this.maxRetryAttemptsLimit = i;
        this.maxHedgedAttemptsLimit = i2;
        this.autoLoadBalancerFactory = (AutoConfiguredLoadBalancerFactory) Preconditions.checkNotNull(autoConfiguredLoadBalancerFactory, "autoLoadBalancerFactory");
    }

    @Override // io.grpc.NameResolver.ServiceConfigParser
    public NameResolver.ConfigOrError parseServiceConfig(Map<String, ?> map) {
        Object config;
        try {
            NameResolver.ConfigOrError loadBalancerPolicy = this.autoLoadBalancerFactory.parseLoadBalancerPolicy(map);
            if (loadBalancerPolicy == null) {
                config = null;
            } else {
                if (loadBalancerPolicy.getError() != null) {
                    return NameResolver.ConfigOrError.fromError(loadBalancerPolicy.getError());
                }
                config = loadBalancerPolicy.getConfig();
            }
            return NameResolver.ConfigOrError.fromConfig(ManagedChannelServiceConfig.fromServiceConfig(map, this.retryEnabled, this.maxRetryAttemptsLimit, this.maxHedgedAttemptsLimit, config));
        } catch (RuntimeException e) {
            return NameResolver.ConfigOrError.fromError(Status.UNKNOWN.withDescription("failed to parse service config").withCause(e));
        }
    }
}
