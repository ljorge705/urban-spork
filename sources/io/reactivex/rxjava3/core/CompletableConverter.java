package io.reactivex.rxjava3.core;

@FunctionalInterface
/* loaded from: classes6.dex */
public interface CompletableConverter<R> {
    R apply(Completable upstream);
}
