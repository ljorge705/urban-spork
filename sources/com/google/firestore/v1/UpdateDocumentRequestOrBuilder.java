package com.google.firestore.v1;

import com.google.protobuf.MessageLiteOrBuilder;

/* loaded from: classes2.dex */
public interface UpdateDocumentRequestOrBuilder extends MessageLiteOrBuilder {
    Precondition getCurrentDocument();

    Document getDocument();

    DocumentMask getMask();

    DocumentMask getUpdateMask();

    boolean hasCurrentDocument();

    boolean hasDocument();

    boolean hasMask();

    boolean hasUpdateMask();
}
