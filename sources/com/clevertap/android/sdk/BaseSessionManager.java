package com.clevertap.android.sdk;

import android.content.Context;

/* loaded from: classes5.dex */
abstract class BaseSessionManager {
    abstract void destroySession();

    abstract void lazyCreateSession(Context context);

    BaseSessionManager() {
    }
}
