package com.google.firebase.firestore.proto;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

/* loaded from: classes2.dex */
public interface MutationQueueOrBuilder extends MessageLiteOrBuilder {
    int getLastAcknowledgedBatchId();

    ByteString getLastStreamToken();
}
