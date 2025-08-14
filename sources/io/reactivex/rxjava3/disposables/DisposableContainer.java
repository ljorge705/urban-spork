package io.reactivex.rxjava3.disposables;

/* loaded from: classes6.dex */
public interface DisposableContainer {
    boolean add(Disposable d);

    boolean delete(Disposable d);

    boolean remove(Disposable d);
}
