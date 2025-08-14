package io.reactivex.rxjava3.internal.operators.single;

import ahpfbhknxzgojyggofxj.cctxrwizduxmjefyvyrx;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import java.util.concurrent.Callable;

/* loaded from: classes6.dex */
public final class SingleFromCallable<T> extends Single<T> {
    final Callable<? extends T> callable;

    public SingleFromCallable(Callable<? extends T> callable) {
        this.callable = callable;
    }

    @Override // io.reactivex.rxjava3.core.Single
    protected void subscribeActual(SingleObserver<? super T> singleObserver) {
        Disposable disposableEmpty = Disposable.empty();
        singleObserver.onSubscribe(disposableEmpty);
        if (disposableEmpty.isDisposed()) {
            return;
        }
        try {
            cctxrwizduxmjefyvyrx cctxrwizduxmjefyvyrxVar = (Object) Objects.requireNonNull(this.callable.call(), "The callable returned a null value");
            if (disposableEmpty.isDisposed()) {
                return;
            }
            singleObserver.onSuccess(cctxrwizduxmjefyvyrxVar);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            if (!disposableEmpty.isDisposed()) {
                singleObserver.onError(th);
            } else {
                RxJavaPlugins.onError(th);
            }
        }
    }
}
