package com.singular.sdk.internal;

import java.io.IOException;

/* loaded from: classes6.dex */
public interface Queue {
    void add(String str) throws IOException;

    String peek() throws IOException;

    void remove() throws IOException;
}
