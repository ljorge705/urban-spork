package io.grpc;

import com.google.common.base.MoreObjects;
import io.grpc.ForwardingChannelBuilder;
import io.grpc.NameResolver;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

/* loaded from: classes6.dex */
public abstract class ForwardingChannelBuilder<T extends ForwardingChannelBuilder<T>> extends ManagedChannelBuilder<T> {
    protected abstract ManagedChannelBuilder<?> delegate();

    protected final T thisT() {
        return this;
    }

    @Override // io.grpc.ManagedChannelBuilder
    public /* bridge */ /* synthetic */ ManagedChannelBuilder defaultServiceConfig(@Nullable Map map) {
        return defaultServiceConfig((Map<String, ?>) map);
    }

    @Override // io.grpc.ManagedChannelBuilder
    public /* bridge */ /* synthetic */ ManagedChannelBuilder intercept(List list) {
        return intercept((List<ClientInterceptor>) list);
    }

    protected ForwardingChannelBuilder() {
    }

    public static ManagedChannelBuilder<?> forAddress(String str, int i) {
        throw new UnsupportedOperationException("Subclass failed to hide static factory");
    }

    public static ManagedChannelBuilder<?> forTarget(String str) {
        throw new UnsupportedOperationException("Subclass failed to hide static factory");
    }

    @Override // io.grpc.ManagedChannelBuilder
    public T directExecutor() {
        delegate().directExecutor();
        return (T) thisT();
    }

    @Override // io.grpc.ManagedChannelBuilder
    public T executor(Executor executor) {
        delegate().executor(executor);
        return (T) thisT();
    }

    @Override // io.grpc.ManagedChannelBuilder
    public T offloadExecutor(Executor executor) {
        delegate().offloadExecutor(executor);
        return (T) thisT();
    }

    @Override // io.grpc.ManagedChannelBuilder
    public T intercept(List<ClientInterceptor> list) {
        delegate().intercept(list);
        return (T) thisT();
    }

    @Override // io.grpc.ManagedChannelBuilder
    public T intercept(ClientInterceptor... clientInterceptorArr) {
        delegate().intercept(clientInterceptorArr);
        return (T) thisT();
    }

    @Override // io.grpc.ManagedChannelBuilder
    public T userAgent(String str) {
        delegate().userAgent(str);
        return (T) thisT();
    }

    @Override // io.grpc.ManagedChannelBuilder
    public T overrideAuthority(String str) {
        delegate().overrideAuthority(str);
        return (T) thisT();
    }

    @Override // io.grpc.ManagedChannelBuilder
    public T usePlaintext() {
        delegate().usePlaintext();
        return (T) thisT();
    }

    @Override // io.grpc.ManagedChannelBuilder
    public T useTransportSecurity() {
        delegate().useTransportSecurity();
        return (T) thisT();
    }

    @Override // io.grpc.ManagedChannelBuilder
    @Deprecated
    public T nameResolverFactory(NameResolver.Factory factory) {
        delegate().nameResolverFactory(factory);
        return (T) thisT();
    }

    @Override // io.grpc.ManagedChannelBuilder
    public T defaultLoadBalancingPolicy(String str) {
        delegate().defaultLoadBalancingPolicy(str);
        return (T) thisT();
    }

    @Override // io.grpc.ManagedChannelBuilder
    public T enableFullStreamDecompression() {
        delegate().enableFullStreamDecompression();
        return (T) thisT();
    }

    @Override // io.grpc.ManagedChannelBuilder
    public T decompressorRegistry(DecompressorRegistry decompressorRegistry) {
        delegate().decompressorRegistry(decompressorRegistry);
        return (T) thisT();
    }

    @Override // io.grpc.ManagedChannelBuilder
    public T compressorRegistry(CompressorRegistry compressorRegistry) {
        delegate().compressorRegistry(compressorRegistry);
        return (T) thisT();
    }

    @Override // io.grpc.ManagedChannelBuilder
    public T idleTimeout(long j, TimeUnit timeUnit) {
        delegate().idleTimeout(j, timeUnit);
        return (T) thisT();
    }

    @Override // io.grpc.ManagedChannelBuilder
    public T maxInboundMessageSize(int i) {
        delegate().maxInboundMessageSize(i);
        return (T) thisT();
    }

    @Override // io.grpc.ManagedChannelBuilder
    public T maxInboundMetadataSize(int i) {
        delegate().maxInboundMetadataSize(i);
        return (T) thisT();
    }

    @Override // io.grpc.ManagedChannelBuilder
    public T keepAliveTime(long j, TimeUnit timeUnit) {
        delegate().keepAliveTime(j, timeUnit);
        return (T) thisT();
    }

    @Override // io.grpc.ManagedChannelBuilder
    public T keepAliveTimeout(long j, TimeUnit timeUnit) {
        delegate().keepAliveTimeout(j, timeUnit);
        return (T) thisT();
    }

    @Override // io.grpc.ManagedChannelBuilder
    public T keepAliveWithoutCalls(boolean z) {
        delegate().keepAliveWithoutCalls(z);
        return (T) thisT();
    }

    @Override // io.grpc.ManagedChannelBuilder
    public T maxRetryAttempts(int i) {
        delegate().maxRetryAttempts(i);
        return (T) thisT();
    }

    @Override // io.grpc.ManagedChannelBuilder
    public T maxHedgedAttempts(int i) {
        delegate().maxHedgedAttempts(i);
        return (T) thisT();
    }

    @Override // io.grpc.ManagedChannelBuilder
    public T retryBufferSize(long j) {
        delegate().retryBufferSize(j);
        return (T) thisT();
    }

    @Override // io.grpc.ManagedChannelBuilder
    public T perRpcBufferLimit(long j) {
        delegate().perRpcBufferLimit(j);
        return (T) thisT();
    }

    @Override // io.grpc.ManagedChannelBuilder
    public T disableRetry() {
        delegate().disableRetry();
        return (T) thisT();
    }

    @Override // io.grpc.ManagedChannelBuilder
    public T enableRetry() {
        delegate().enableRetry();
        return (T) thisT();
    }

    @Override // io.grpc.ManagedChannelBuilder
    public T setBinaryLog(BinaryLog binaryLog) {
        delegate().setBinaryLog(binaryLog);
        return (T) thisT();
    }

    @Override // io.grpc.ManagedChannelBuilder
    public T maxTraceEvents(int i) {
        delegate().maxTraceEvents(i);
        return (T) thisT();
    }

    @Override // io.grpc.ManagedChannelBuilder
    public T proxyDetector(ProxyDetector proxyDetector) {
        delegate().proxyDetector(proxyDetector);
        return (T) thisT();
    }

    @Override // io.grpc.ManagedChannelBuilder
    public T defaultServiceConfig(@Nullable Map<String, ?> map) {
        delegate().defaultServiceConfig(map);
        return (T) thisT();
    }

    @Override // io.grpc.ManagedChannelBuilder
    public T disableServiceConfigLookUp() {
        delegate().disableServiceConfigLookUp();
        return (T) thisT();
    }

    @Override // io.grpc.ManagedChannelBuilder
    public ManagedChannel build() {
        return delegate().build();
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("delegate", delegate()).toString();
    }
}
