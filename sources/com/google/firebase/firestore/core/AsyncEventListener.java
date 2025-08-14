package com.google.firebase.firestore.core;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import java.util.concurrent.Executor;

/* loaded from: classes2.dex */
public class AsyncEventListener<T> implements EventListener<T> {
    private final EventListener<T> eventListener;
    private final Executor executor;
    private volatile boolean muted = false;

    public void mute() {
        this.muted = true;
    }

    public AsyncEventListener(Executor executor, EventListener<T> eventListener) {
        this.executor = executor;
        this.eventListener = eventListener;
    }

    @Override // com.google.firebase.firestore.EventListener
    public void onEvent(final T t, final FirebaseFirestoreException firebaseFirestoreException) {
        this.executor.execute(new Runnable() { // from class: com.google.firebase.firestore.core.AsyncEventListener$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m5244xdc9f5a86(t, firebaseFirestoreException);
            }
        });
    }

    /* renamed from: lambda$onEvent$0$com-google-firebase-firestore-core-AsyncEventListener, reason: not valid java name */
    /* synthetic */ void m5244xdc9f5a86(Object obj, FirebaseFirestoreException firebaseFirestoreException) {
        if (this.muted) {
            return;
        }
        this.eventListener.onEvent(obj, firebaseFirestoreException);
    }
}
