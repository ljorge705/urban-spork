package com.google.firebase.dynamiclinks;

import android.content.Intent;
import android.net.Uri;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.dynamiclinks.DynamicLink;

/* loaded from: classes5.dex */
public abstract class FirebaseDynamicLinks {
    public abstract DynamicLink.Builder createDynamicLink();

    public abstract Task<PendingDynamicLinkData> getDynamicLink(Intent intent);

    public abstract Task<PendingDynamicLinkData> getDynamicLink(Uri uri);

    public static synchronized FirebaseDynamicLinks getInstance() {
        return getInstance(FirebaseApp.getInstance());
    }

    public static synchronized FirebaseDynamicLinks getInstance(FirebaseApp firebaseApp) {
        return (FirebaseDynamicLinks) firebaseApp.get(FirebaseDynamicLinks.class);
    }
}
