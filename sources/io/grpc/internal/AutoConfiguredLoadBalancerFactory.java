package io.grpc.internal;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import io.grpc.ChannelLogger;
import io.grpc.ConnectivityState;
import io.grpc.ConnectivityStateInfo;
import io.grpc.LoadBalancer;
import io.grpc.LoadBalancerProvider;
import io.grpc.LoadBalancerRegistry;
import io.grpc.NameResolver;
import io.grpc.Status;
import io.grpc.internal.ServiceConfigUtil;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

/* loaded from: classes6.dex */
public final class AutoConfiguredLoadBalancerFactory {
    private final String defaultPolicy;
    private final LoadBalancerRegistry registry;

    public AutoConfiguredLoadBalancerFactory(String str) {
        this(LoadBalancerRegistry.getDefaultRegistry(), str);
    }

    AutoConfiguredLoadBalancerFactory(LoadBalancerRegistry loadBalancerRegistry, String str) {
        this.registry = (LoadBalancerRegistry) Preconditions.checkNotNull(loadBalancerRegistry, "registry");
        this.defaultPolicy = (String) Preconditions.checkNotNull(str, "defaultPolicy");
    }

    public AutoConfiguredLoadBalancer newLoadBalancer(LoadBalancer.Helper helper) {
        return new AutoConfiguredLoadBalancer(helper);
    }

    private static final class NoopLoadBalancer extends LoadBalancer {
        @Override // io.grpc.LoadBalancer
        public boolean acceptResolvedAddresses(LoadBalancer.ResolvedAddresses resolvedAddresses) {
            return true;
        }

        @Override // io.grpc.LoadBalancer
        public void handleNameResolutionError(Status status) {
        }

        @Override // io.grpc.LoadBalancer
        @Deprecated
        public void handleResolvedAddresses(LoadBalancer.ResolvedAddresses resolvedAddresses) {
        }

        @Override // io.grpc.LoadBalancer
        public void shutdown() {
        }

        private NoopLoadBalancer() {
        }
    }

    public final class AutoConfiguredLoadBalancer {
        private LoadBalancer delegate;
        private LoadBalancerProvider delegateProvider;
        private final LoadBalancer.Helper helper;

        public LoadBalancer getDelegate() {
            return this.delegate;
        }

        LoadBalancerProvider getDelegateProvider() {
            return this.delegateProvider;
        }

        void setDelegate(LoadBalancer loadBalancer) {
            this.delegate = loadBalancer;
        }

        AutoConfiguredLoadBalancer(LoadBalancer.Helper helper) {
            this.helper = helper;
            LoadBalancerProvider provider = AutoConfiguredLoadBalancerFactory.this.registry.getProvider(AutoConfiguredLoadBalancerFactory.this.defaultPolicy);
            this.delegateProvider = provider;
            if (provider == null) {
                throw new IllegalStateException("Could not find policy '" + AutoConfiguredLoadBalancerFactory.this.defaultPolicy + "'. Make sure its implementation is either registered to LoadBalancerRegistry or included in META-INF/services/io.grpc.LoadBalancerProvider from your jar files.");
            }
            this.delegate = provider.newLoadBalancer(helper);
        }

        boolean tryAcceptResolvedAddresses(LoadBalancer.ResolvedAddresses resolvedAddresses) {
            ServiceConfigUtil.PolicySelection policySelection = (ServiceConfigUtil.PolicySelection) resolvedAddresses.getLoadBalancingPolicyConfig();
            if (policySelection == null) {
                try {
                    AutoConfiguredLoadBalancerFactory autoConfiguredLoadBalancerFactory = AutoConfiguredLoadBalancerFactory.this;
                    policySelection = new ServiceConfigUtil.PolicySelection(autoConfiguredLoadBalancerFactory.getProviderOrThrow(autoConfiguredLoadBalancerFactory.defaultPolicy, "using default policy"), null);
                } catch (PolicyException e) {
                    this.helper.updateBalancingState(ConnectivityState.TRANSIENT_FAILURE, new FailingPicker(Status.INTERNAL.withDescription(e.getMessage())));
                    this.delegate.shutdown();
                    this.delegateProvider = null;
                    this.delegate = new NoopLoadBalancer();
                    return true;
                }
            }
            if (this.delegateProvider == null || !policySelection.provider.getPolicyName().equals(this.delegateProvider.getPolicyName())) {
                this.helper.updateBalancingState(ConnectivityState.CONNECTING, new EmptyPicker());
                this.delegate.shutdown();
                LoadBalancerProvider loadBalancerProvider = policySelection.provider;
                this.delegateProvider = loadBalancerProvider;
                LoadBalancer loadBalancer = this.delegate;
                this.delegate = loadBalancerProvider.newLoadBalancer(this.helper);
                this.helper.getChannelLogger().log(ChannelLogger.ChannelLogLevel.INFO, "Load balancer changed from {0} to {1}", loadBalancer.getClass().getSimpleName(), this.delegate.getClass().getSimpleName());
            }
            Object obj = policySelection.config;
            if (obj != null) {
                this.helper.getChannelLogger().log(ChannelLogger.ChannelLogLevel.DEBUG, "Load-balancing config: {0}", policySelection.config);
            }
            return getDelegate().acceptResolvedAddresses(LoadBalancer.ResolvedAddresses.newBuilder().setAddresses(resolvedAddresses.getAddresses()).setAttributes(resolvedAddresses.getAttributes()).setLoadBalancingPolicyConfig(obj).build());
        }

        void handleNameResolutionError(Status status) {
            getDelegate().handleNameResolutionError(status);
        }

        @Deprecated
        void handleSubchannelState(LoadBalancer.Subchannel subchannel, ConnectivityStateInfo connectivityStateInfo) {
            getDelegate().handleSubchannelState(subchannel, connectivityStateInfo);
        }

        void requestConnection() {
            getDelegate().requestConnection();
        }

        void shutdown() {
            this.delegate.shutdown();
            this.delegate = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public LoadBalancerProvider getProviderOrThrow(String str, String str2) throws PolicyException {
        LoadBalancerProvider provider = this.registry.getProvider(str);
        if (provider != null) {
            return provider;
        }
        throw new PolicyException("Trying to load '" + str + "' because " + str2 + ", but it's unavailable");
    }

    @Nullable
    NameResolver.ConfigOrError parseLoadBalancerPolicy(Map<String, ?> map) {
        List<ServiceConfigUtil.LbConfig> listUnwrapLoadBalancingConfigList;
        if (map != null) {
            try {
                listUnwrapLoadBalancingConfigList = ServiceConfigUtil.unwrapLoadBalancingConfigList(ServiceConfigUtil.getLoadBalancingConfigsFromServiceConfig(map));
            } catch (RuntimeException e) {
                return NameResolver.ConfigOrError.fromError(Status.UNKNOWN.withDescription("can't parse load balancer configuration").withCause(e));
            }
        } else {
            listUnwrapLoadBalancingConfigList = null;
        }
        if (listUnwrapLoadBalancingConfigList == null || listUnwrapLoadBalancingConfigList.isEmpty()) {
            return null;
        }
        return ServiceConfigUtil.selectLbPolicyFromList(listUnwrapLoadBalancingConfigList, this.registry);
    }

    static final class PolicyException extends Exception {
        private static final long serialVersionUID = 1;

        private PolicyException(String str) {
            super(str);
        }
    }

    private static final class EmptyPicker extends LoadBalancer.SubchannelPicker {
        private EmptyPicker() {
        }

        @Override // io.grpc.LoadBalancer.SubchannelPicker
        public LoadBalancer.PickResult pickSubchannel(LoadBalancer.PickSubchannelArgs pickSubchannelArgs) {
            return LoadBalancer.PickResult.withNoResult();
        }

        public String toString() {
            return MoreObjects.toStringHelper((Class<?>) EmptyPicker.class).toString();
        }
    }

    private static final class FailingPicker extends LoadBalancer.SubchannelPicker {
        private final Status failure;

        FailingPicker(Status status) {
            this.failure = status;
        }

        @Override // io.grpc.LoadBalancer.SubchannelPicker
        public LoadBalancer.PickResult pickSubchannel(LoadBalancer.PickSubchannelArgs pickSubchannelArgs) {
            return LoadBalancer.PickResult.withError(this.failure);
        }
    }
}
