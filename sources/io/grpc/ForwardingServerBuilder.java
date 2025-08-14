package io.grpc;

import com.google.common.base.MoreObjects;
import io.grpc.ServerBuilder;
import io.grpc.ServerStreamTracer;
import java.io.File;
import java.io.InputStream;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

/* loaded from: classes6.dex */
public abstract class ForwardingServerBuilder<T extends ServerBuilder<T>> extends ServerBuilder<T> {
    private T thisT() {
        return this;
    }

    protected abstract ServerBuilder<?> delegate();

    protected ForwardingServerBuilder() {
    }

    public static ServerBuilder<?> forPort(int i) {
        throw new UnsupportedOperationException("Subclass failed to hide static factory");
    }

    @Override // io.grpc.ServerBuilder
    public T directExecutor() {
        delegate().directExecutor();
        return (T) thisT();
    }

    @Override // io.grpc.ServerBuilder
    public T executor(@Nullable Executor executor) {
        delegate().executor(executor);
        return (T) thisT();
    }

    @Override // io.grpc.ServerBuilder
    public T callExecutor(ServerCallExecutorSupplier serverCallExecutorSupplier) {
        delegate().callExecutor(serverCallExecutorSupplier);
        return (T) thisT();
    }

    @Override // io.grpc.ServerBuilder
    public T addService(ServerServiceDefinition serverServiceDefinition) {
        delegate().addService(serverServiceDefinition);
        return (T) thisT();
    }

    @Override // io.grpc.ServerBuilder
    public T addService(BindableService bindableService) {
        delegate().addService(bindableService);
        return (T) thisT();
    }

    @Override // io.grpc.ServerBuilder
    public T intercept(ServerInterceptor serverInterceptor) {
        delegate().intercept(serverInterceptor);
        return (T) thisT();
    }

    @Override // io.grpc.ServerBuilder
    public T addTransportFilter(ServerTransportFilter serverTransportFilter) {
        delegate().addTransportFilter(serverTransportFilter);
        return (T) thisT();
    }

    @Override // io.grpc.ServerBuilder
    public T addStreamTracerFactory(ServerStreamTracer.Factory factory) {
        delegate().addStreamTracerFactory(factory);
        return (T) thisT();
    }

    @Override // io.grpc.ServerBuilder
    public T fallbackHandlerRegistry(@Nullable HandlerRegistry handlerRegistry) {
        delegate().fallbackHandlerRegistry(handlerRegistry);
        return (T) thisT();
    }

    @Override // io.grpc.ServerBuilder
    public T useTransportSecurity(File file, File file2) {
        delegate().useTransportSecurity(file, file2);
        return (T) thisT();
    }

    @Override // io.grpc.ServerBuilder
    public T useTransportSecurity(InputStream inputStream, InputStream inputStream2) {
        delegate().useTransportSecurity(inputStream, inputStream2);
        return (T) thisT();
    }

    @Override // io.grpc.ServerBuilder
    public T decompressorRegistry(@Nullable DecompressorRegistry decompressorRegistry) {
        delegate().decompressorRegistry(decompressorRegistry);
        return (T) thisT();
    }

    @Override // io.grpc.ServerBuilder
    public T compressorRegistry(@Nullable CompressorRegistry compressorRegistry) {
        delegate().compressorRegistry(compressorRegistry);
        return (T) thisT();
    }

    @Override // io.grpc.ServerBuilder
    public T handshakeTimeout(long j, TimeUnit timeUnit) {
        delegate().handshakeTimeout(j, timeUnit);
        return (T) thisT();
    }

    @Override // io.grpc.ServerBuilder
    public T keepAliveTime(long j, TimeUnit timeUnit) {
        delegate().keepAliveTime(j, timeUnit);
        return (T) thisT();
    }

    @Override // io.grpc.ServerBuilder
    public T keepAliveTimeout(long j, TimeUnit timeUnit) {
        delegate().keepAliveTimeout(j, timeUnit);
        return (T) thisT();
    }

    @Override // io.grpc.ServerBuilder
    public T maxConnectionIdle(long j, TimeUnit timeUnit) {
        delegate().maxConnectionIdle(j, timeUnit);
        return (T) thisT();
    }

    @Override // io.grpc.ServerBuilder
    public T maxConnectionAge(long j, TimeUnit timeUnit) {
        delegate().maxConnectionAge(j, timeUnit);
        return (T) thisT();
    }

    @Override // io.grpc.ServerBuilder
    public T maxConnectionAgeGrace(long j, TimeUnit timeUnit) {
        delegate().maxConnectionAgeGrace(j, timeUnit);
        return (T) thisT();
    }

    @Override // io.grpc.ServerBuilder
    public T permitKeepAliveTime(long j, TimeUnit timeUnit) {
        delegate().permitKeepAliveTime(j, timeUnit);
        return (T) thisT();
    }

    @Override // io.grpc.ServerBuilder
    public T permitKeepAliveWithoutCalls(boolean z) {
        delegate().permitKeepAliveWithoutCalls(z);
        return (T) thisT();
    }

    @Override // io.grpc.ServerBuilder
    public T maxInboundMessageSize(int i) {
        delegate().maxInboundMessageSize(i);
        return (T) thisT();
    }

    @Override // io.grpc.ServerBuilder
    public T maxInboundMetadataSize(int i) {
        delegate().maxInboundMetadataSize(i);
        return (T) thisT();
    }

    @Override // io.grpc.ServerBuilder
    public T setBinaryLog(BinaryLog binaryLog) {
        delegate().setBinaryLog(binaryLog);
        return (T) thisT();
    }

    @Override // io.grpc.ServerBuilder
    public Server build() {
        return delegate().build();
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("delegate", delegate()).toString();
    }
}
