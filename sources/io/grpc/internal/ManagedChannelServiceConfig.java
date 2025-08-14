package io.grpc.internal;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import io.grpc.CallOptions;
import io.grpc.InternalConfigSelector;
import io.grpc.LoadBalancer;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import io.grpc.internal.RetriableStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

/* loaded from: classes6.dex */
final class ManagedChannelServiceConfig {

    @Nullable
    private final MethodInfo defaultMethodConfig;

    @Nullable
    private final Map<String, ?> healthCheckingConfig;

    @Nullable
    private final Object loadBalancingConfig;

    @Nullable
    private final RetriableStream.Throttle retryThrottling;
    private final Map<String, MethodInfo> serviceMap;
    private final Map<String, MethodInfo> serviceMethodMap;

    @Nullable
    Map<String, ?> getHealthCheckingConfig() {
        return this.healthCheckingConfig;
    }

    @Nullable
    Object getLoadBalancingConfig() {
        return this.loadBalancingConfig;
    }

    @Nullable
    RetriableStream.Throttle getRetryThrottling() {
        return this.retryThrottling;
    }

    ManagedChannelServiceConfig(@Nullable MethodInfo methodInfo, Map<String, MethodInfo> map, Map<String, MethodInfo> map2, @Nullable RetriableStream.Throttle throttle, @Nullable Object obj, @Nullable Map<String, ?> map3) {
        this.defaultMethodConfig = methodInfo;
        this.serviceMethodMap = Collections.unmodifiableMap(new HashMap(map));
        this.serviceMap = Collections.unmodifiableMap(new HashMap(map2));
        this.retryThrottling = throttle;
        this.loadBalancingConfig = obj;
        this.healthCheckingConfig = map3 != null ? Collections.unmodifiableMap(new HashMap(map3)) : null;
    }

    static ManagedChannelServiceConfig empty() {
        return new ManagedChannelServiceConfig(null, new HashMap(), new HashMap(), null, null, null);
    }

    static ManagedChannelServiceConfig fromServiceConfig(Map<String, ?> map, boolean z, int i, int i2, @Nullable Object obj) {
        RetriableStream.Throttle throttlePolicy = z ? ServiceConfigUtil.getThrottlePolicy(map) : null;
        HashMap map2 = new HashMap();
        HashMap map3 = new HashMap();
        Map<String, ?> healthCheckedService = ServiceConfigUtil.getHealthCheckedService(map);
        List<Map<String, ?>> methodConfigFromServiceConfig = ServiceConfigUtil.getMethodConfigFromServiceConfig(map);
        if (methodConfigFromServiceConfig == null) {
            return new ManagedChannelServiceConfig(null, map2, map3, throttlePolicy, obj, healthCheckedService);
        }
        MethodInfo methodInfo = null;
        for (Map<String, ?> map4 : methodConfigFromServiceConfig) {
            MethodInfo methodInfo2 = new MethodInfo(map4, z, i, i2);
            List<Map<String, ?>> nameListFromMethodConfig = ServiceConfigUtil.getNameListFromMethodConfig(map4);
            if (nameListFromMethodConfig != null && !nameListFromMethodConfig.isEmpty()) {
                for (Map<String, ?> map5 : nameListFromMethodConfig) {
                    String serviceFromName = ServiceConfigUtil.getServiceFromName(map5);
                    String methodFromName = ServiceConfigUtil.getMethodFromName(map5);
                    if (Strings.isNullOrEmpty(serviceFromName)) {
                        Preconditions.checkArgument(Strings.isNullOrEmpty(methodFromName), "missing service name for method %s", methodFromName);
                        Preconditions.checkArgument(methodInfo == null, "Duplicate default method config in service config %s", map);
                        methodInfo = methodInfo2;
                    } else if (Strings.isNullOrEmpty(methodFromName)) {
                        Preconditions.checkArgument(!map3.containsKey(serviceFromName), "Duplicate service %s", serviceFromName);
                        map3.put(serviceFromName, methodInfo2);
                    } else {
                        String strGenerateFullMethodName = MethodDescriptor.generateFullMethodName(serviceFromName, methodFromName);
                        Preconditions.checkArgument(!map2.containsKey(strGenerateFullMethodName), "Duplicate method name %s", strGenerateFullMethodName);
                        map2.put(strGenerateFullMethodName, methodInfo2);
                    }
                }
            }
        }
        return new ManagedChannelServiceConfig(methodInfo, map2, map3, throttlePolicy, obj, healthCheckedService);
    }

    @Nullable
    InternalConfigSelector getDefaultConfigSelector() {
        if (this.serviceMap.isEmpty() && this.serviceMethodMap.isEmpty() && this.defaultMethodConfig == null) {
            return null;
        }
        return new ServiceConfigConvertedSelector();
    }

    @Nullable
    MethodInfo getMethodConfig(MethodDescriptor<?, ?> methodDescriptor) {
        MethodInfo methodInfo = this.serviceMethodMap.get(methodDescriptor.getFullMethodName());
        if (methodInfo == null) {
            methodInfo = this.serviceMap.get(methodDescriptor.getServiceName());
        }
        return methodInfo == null ? this.defaultMethodConfig : methodInfo;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ManagedChannelServiceConfig managedChannelServiceConfig = (ManagedChannelServiceConfig) obj;
        return Objects.equal(this.defaultMethodConfig, managedChannelServiceConfig.defaultMethodConfig) && Objects.equal(this.serviceMethodMap, managedChannelServiceConfig.serviceMethodMap) && Objects.equal(this.serviceMap, managedChannelServiceConfig.serviceMap) && Objects.equal(this.retryThrottling, managedChannelServiceConfig.retryThrottling) && Objects.equal(this.loadBalancingConfig, managedChannelServiceConfig.loadBalancingConfig);
    }

    public int hashCode() {
        return Objects.hashCode(this.defaultMethodConfig, this.serviceMethodMap, this.serviceMap, this.retryThrottling, this.loadBalancingConfig);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("defaultMethodConfig", this.defaultMethodConfig).add("serviceMethodMap", this.serviceMethodMap).add("serviceMap", this.serviceMap).add("retryThrottling", this.retryThrottling).add("loadBalancingConfig", this.loadBalancingConfig).toString();
    }

    static final class MethodInfo {
        static final CallOptions.Key<MethodInfo> KEY = CallOptions.Key.create("io.grpc.internal.ManagedChannelServiceConfig.MethodInfo");
        final HedgingPolicy hedgingPolicy;
        final Integer maxInboundMessageSize;
        final Integer maxOutboundMessageSize;
        final RetryPolicy retryPolicy;
        final Long timeoutNanos;
        final Boolean waitForReady;

        MethodInfo(Map<String, ?> map, boolean z, int i, int i2) {
            this.timeoutNanos = ServiceConfigUtil.getTimeoutFromMethodConfig(map);
            this.waitForReady = ServiceConfigUtil.getWaitForReadyFromMethodConfig(map);
            Integer maxResponseMessageBytesFromMethodConfig = ServiceConfigUtil.getMaxResponseMessageBytesFromMethodConfig(map);
            this.maxInboundMessageSize = maxResponseMessageBytesFromMethodConfig;
            if (maxResponseMessageBytesFromMethodConfig != null) {
                Preconditions.checkArgument(maxResponseMessageBytesFromMethodConfig.intValue() >= 0, "maxInboundMessageSize %s exceeds bounds", maxResponseMessageBytesFromMethodConfig);
            }
            Integer maxRequestMessageBytesFromMethodConfig = ServiceConfigUtil.getMaxRequestMessageBytesFromMethodConfig(map);
            this.maxOutboundMessageSize = maxRequestMessageBytesFromMethodConfig;
            if (maxRequestMessageBytesFromMethodConfig != null) {
                Preconditions.checkArgument(maxRequestMessageBytesFromMethodConfig.intValue() >= 0, "maxOutboundMessageSize %s exceeds bounds", maxRequestMessageBytesFromMethodConfig);
            }
            Map<String, ?> retryPolicyFromMethodConfig = z ? ServiceConfigUtil.getRetryPolicyFromMethodConfig(map) : null;
            this.retryPolicy = retryPolicyFromMethodConfig == null ? null : retryPolicy(retryPolicyFromMethodConfig, i);
            Map<String, ?> hedgingPolicyFromMethodConfig = z ? ServiceConfigUtil.getHedgingPolicyFromMethodConfig(map) : null;
            this.hedgingPolicy = hedgingPolicyFromMethodConfig != null ? hedgingPolicy(hedgingPolicyFromMethodConfig, i2) : null;
        }

        public int hashCode() {
            return Objects.hashCode(this.timeoutNanos, this.waitForReady, this.maxInboundMessageSize, this.maxOutboundMessageSize, this.retryPolicy, this.hedgingPolicy);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof MethodInfo)) {
                return false;
            }
            MethodInfo methodInfo = (MethodInfo) obj;
            return Objects.equal(this.timeoutNanos, methodInfo.timeoutNanos) && Objects.equal(this.waitForReady, methodInfo.waitForReady) && Objects.equal(this.maxInboundMessageSize, methodInfo.maxInboundMessageSize) && Objects.equal(this.maxOutboundMessageSize, methodInfo.maxOutboundMessageSize) && Objects.equal(this.retryPolicy, methodInfo.retryPolicy) && Objects.equal(this.hedgingPolicy, methodInfo.hedgingPolicy);
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).add("timeoutNanos", this.timeoutNanos).add("waitForReady", this.waitForReady).add("maxInboundMessageSize", this.maxInboundMessageSize).add("maxOutboundMessageSize", this.maxOutboundMessageSize).add("retryPolicy", this.retryPolicy).add("hedgingPolicy", this.hedgingPolicy).toString();
        }

        private static RetryPolicy retryPolicy(Map<String, ?> map, int i) {
            int iIntValue = ((Integer) Preconditions.checkNotNull(ServiceConfigUtil.getMaxAttemptsFromRetryPolicy(map), "maxAttempts cannot be empty")).intValue();
            boolean z = true;
            Preconditions.checkArgument(iIntValue >= 2, "maxAttempts must be greater than 1: %s", iIntValue);
            int iMin = Math.min(iIntValue, i);
            long jLongValue = ((Long) Preconditions.checkNotNull(ServiceConfigUtil.getInitialBackoffNanosFromRetryPolicy(map), "initialBackoff cannot be empty")).longValue();
            Preconditions.checkArgument(jLongValue > 0, "initialBackoffNanos must be greater than 0: %s", jLongValue);
            long jLongValue2 = ((Long) Preconditions.checkNotNull(ServiceConfigUtil.getMaxBackoffNanosFromRetryPolicy(map), "maxBackoff cannot be empty")).longValue();
            Preconditions.checkArgument(jLongValue2 > 0, "maxBackoff must be greater than 0: %s", jLongValue2);
            double dDoubleValue = ((Double) Preconditions.checkNotNull(ServiceConfigUtil.getBackoffMultiplierFromRetryPolicy(map), "backoffMultiplier cannot be empty")).doubleValue();
            Preconditions.checkArgument(dDoubleValue > 0.0d, "backoffMultiplier must be greater than 0: %s", Double.valueOf(dDoubleValue));
            Long perAttemptRecvTimeoutNanosFromRetryPolicy = ServiceConfigUtil.getPerAttemptRecvTimeoutNanosFromRetryPolicy(map);
            Preconditions.checkArgument(perAttemptRecvTimeoutNanosFromRetryPolicy == null || perAttemptRecvTimeoutNanosFromRetryPolicy.longValue() >= 0, "perAttemptRecvTimeout cannot be negative: %s", perAttemptRecvTimeoutNanosFromRetryPolicy);
            Set<Status.Code> retryableStatusCodesFromRetryPolicy = ServiceConfigUtil.getRetryableStatusCodesFromRetryPolicy(map);
            if (perAttemptRecvTimeoutNanosFromRetryPolicy == null && retryableStatusCodesFromRetryPolicy.isEmpty()) {
                z = false;
            }
            Preconditions.checkArgument(z, "retryableStatusCodes cannot be empty without perAttemptRecvTimeout");
            return new RetryPolicy(iMin, jLongValue, jLongValue2, dDoubleValue, perAttemptRecvTimeoutNanosFromRetryPolicy, retryableStatusCodesFromRetryPolicy);
        }

        private static HedgingPolicy hedgingPolicy(Map<String, ?> map, int i) {
            int iIntValue = ((Integer) Preconditions.checkNotNull(ServiceConfigUtil.getMaxAttemptsFromHedgingPolicy(map), "maxAttempts cannot be empty")).intValue();
            Preconditions.checkArgument(iIntValue >= 2, "maxAttempts must be greater than 1: %s", iIntValue);
            int iMin = Math.min(iIntValue, i);
            long jLongValue = ((Long) Preconditions.checkNotNull(ServiceConfigUtil.getHedgingDelayNanosFromHedgingPolicy(map), "hedgingDelay cannot be empty")).longValue();
            Preconditions.checkArgument(jLongValue >= 0, "hedgingDelay must not be negative: %s", jLongValue);
            return new HedgingPolicy(iMin, jLongValue, ServiceConfigUtil.getNonFatalStatusCodesFromHedgingPolicy(map));
        }
    }

    static final class ServiceConfigConvertedSelector extends InternalConfigSelector {
        final ManagedChannelServiceConfig config;

        private ServiceConfigConvertedSelector(ManagedChannelServiceConfig managedChannelServiceConfig) {
            this.config = managedChannelServiceConfig;
        }

        @Override // io.grpc.InternalConfigSelector
        public InternalConfigSelector.Result selectConfig(LoadBalancer.PickSubchannelArgs pickSubchannelArgs) {
            return InternalConfigSelector.Result.newBuilder().setConfig(this.config).build();
        }
    }
}
