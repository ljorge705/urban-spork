package io.grpc;

import io.grpc.ServerCall;

/* loaded from: classes6.dex */
public interface ServerInterceptor {
    <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> serverCall, Metadata metadata, ServerCallHandler<ReqT, RespT> serverCallHandler);
}
