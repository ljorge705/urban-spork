package io.grpc.internal;

import com.google.common.base.MoreObjects;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.internal.ClientStreamListener;
import io.grpc.internal.StreamListener;

/* loaded from: classes6.dex */
abstract class ForwardingClientStreamListener implements ClientStreamListener {
    protected abstract ClientStreamListener delegate();

    ForwardingClientStreamListener() {
    }

    @Override // io.grpc.internal.ClientStreamListener
    public void headersRead(Metadata metadata) {
        delegate().headersRead(metadata);
    }

    @Override // io.grpc.internal.ClientStreamListener
    public void closed(Status status, ClientStreamListener.RpcProgress rpcProgress, Metadata metadata) {
        delegate().closed(status, rpcProgress, metadata);
    }

    @Override // io.grpc.internal.StreamListener
    public void messagesAvailable(StreamListener.MessageProducer messageProducer) {
        delegate().messagesAvailable(messageProducer);
    }

    @Override // io.grpc.internal.StreamListener
    public void onReady() {
        delegate().onReady();
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("delegate", delegate()).toString();
    }
}
