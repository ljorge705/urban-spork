package io.grpc.internal;

import com.google.common.base.Preconditions;
import io.grpc.CallCredentials;
import io.grpc.CallOptions;
import io.grpc.ClientStreamTracer;
import io.grpc.Context;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import javax.annotation.Nullable;

/* loaded from: classes6.dex */
final class MetadataApplierImpl extends CallCredentials.MetadataApplier {
    private final CallOptions callOptions;
    DelayedStream delayedStream;
    boolean finalized;
    private final MetadataApplierListener listener;
    private final MethodDescriptor<?, ?> method;
    private final Metadata origHeaders;

    @Nullable
    private ClientStream returnedStream;
    private final ClientStreamTracer[] tracers;
    private final ClientTransport transport;
    private final Object lock = new Object();
    private final Context ctx = Context.current();

    public interface MetadataApplierListener {
        void onComplete();
    }

    MetadataApplierImpl(ClientTransport clientTransport, MethodDescriptor<?, ?> methodDescriptor, Metadata metadata, CallOptions callOptions, MetadataApplierListener metadataApplierListener, ClientStreamTracer[] clientStreamTracerArr) {
        this.transport = clientTransport;
        this.method = methodDescriptor;
        this.origHeaders = metadata;
        this.callOptions = callOptions;
        this.listener = metadataApplierListener;
        this.tracers = clientStreamTracerArr;
    }

    @Override // io.grpc.CallCredentials.MetadataApplier
    public void apply(Metadata metadata) {
        Preconditions.checkState(!this.finalized, "apply() or fail() already called");
        Preconditions.checkNotNull(metadata, "headers");
        this.origHeaders.merge(metadata);
        Context contextAttach = this.ctx.attach();
        try {
            ClientStream clientStreamNewStream = this.transport.newStream(this.method, this.origHeaders, this.callOptions, this.tracers);
            this.ctx.detach(contextAttach);
            finalizeWith(clientStreamNewStream);
        } catch (Throwable th) {
            this.ctx.detach(contextAttach);
            throw th;
        }
    }

    @Override // io.grpc.CallCredentials.MetadataApplier
    public void fail(Status status) {
        Preconditions.checkArgument(!status.isOk(), "Cannot fail with OK status");
        Preconditions.checkState(!this.finalized, "apply() or fail() already called");
        finalizeWith(new FailingClientStream(GrpcUtil.replaceInappropriateControlPlaneStatus(status), this.tracers));
    }

    private void finalizeWith(ClientStream clientStream) {
        boolean z;
        Preconditions.checkState(!this.finalized, "already finalized");
        this.finalized = true;
        synchronized (this.lock) {
            if (this.returnedStream == null) {
                this.returnedStream = clientStream;
                z = true;
            } else {
                z = false;
            }
        }
        if (z) {
            this.listener.onComplete();
            return;
        }
        Preconditions.checkState(this.delayedStream != null, "delayedStream is null");
        Runnable stream = this.delayedStream.setStream(clientStream);
        if (stream != null) {
            stream.run();
        }
        this.listener.onComplete();
    }

    ClientStream returnStream() {
        synchronized (this.lock) {
            ClientStream clientStream = this.returnedStream;
            if (clientStream != null) {
                return clientStream;
            }
            DelayedStream delayedStream = new DelayedStream();
            this.delayedStream = delayedStream;
            this.returnedStream = delayedStream;
            return delayedStream;
        }
    }
}
