package com.singular.sdk.internal;

import java.io.IOException;

/* loaded from: classes6.dex */
public interface Api {

    public interface OnApiCallback {
        boolean handle(SingularInstance singularInstance, int i, String str);
    }

    OnApiCallback getOnApiCallback();

    String getPath();

    long getTimestamp();

    String getType();

    boolean makeRequest(SingularInstance singularInstance) throws IOException;

    String toJsonAsString();
}
