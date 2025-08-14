package io.grpc.internal;

import io.grpc.InternalChannelz;
import io.grpc.InternalInstrumented;
import io.grpc.LoadBalancer;

/* loaded from: classes6.dex */
abstract class AbstractSubchannel extends LoadBalancer.Subchannel {
    abstract InternalInstrumented<InternalChannelz.ChannelStats> getInstrumentedInternalSubchannel();

    AbstractSubchannel() {
    }
}
