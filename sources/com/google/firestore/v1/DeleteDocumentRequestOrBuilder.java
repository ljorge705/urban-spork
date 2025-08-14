package com.google.firestore.v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

/* loaded from: classes2.dex */
public interface DeleteDocumentRequestOrBuilder extends MessageLiteOrBuilder {
    Precondition getCurrentDocument();

    String getName();

    ByteString getNameBytes();

    boolean hasCurrentDocument();
}
