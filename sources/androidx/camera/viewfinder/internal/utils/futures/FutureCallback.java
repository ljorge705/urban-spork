package androidx.camera.viewfinder.internal.utils.futures;

/* loaded from: classes.dex */
public interface FutureCallback<V> {
    void onFailure(Throwable th);

    void onSuccess(V v);
}
