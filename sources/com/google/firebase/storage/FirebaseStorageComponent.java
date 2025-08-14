package com.google.firebase.storage;

import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.interop.InteropAppCheckTokenProvider;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.inject.Provider;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
class FirebaseStorageComponent {

    /* renamed from: app, reason: collision with root package name */
    private final FirebaseApp f64app;
    private final Provider<InteropAppCheckTokenProvider> appCheckProvider;
    private final Provider<InternalAuthProvider> authProvider;
    private final Map<String, FirebaseStorage> instances = new HashMap();

    FirebaseStorageComponent(FirebaseApp firebaseApp, Provider<InternalAuthProvider> provider, Provider<InteropAppCheckTokenProvider> provider2) {
        this.f64app = firebaseApp;
        this.authProvider = provider;
        this.appCheckProvider = provider2;
    }

    synchronized FirebaseStorage get(String str) {
        FirebaseStorage firebaseStorage;
        firebaseStorage = this.instances.get(str);
        if (firebaseStorage == null) {
            firebaseStorage = new FirebaseStorage(str, this.f64app, this.authProvider, this.appCheckProvider);
            this.instances.put(str, firebaseStorage);
        }
        return firebaseStorage;
    }

    synchronized void clearInstancesForTesting() {
        this.instances.clear();
    }
}
