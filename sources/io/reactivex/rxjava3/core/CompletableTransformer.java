package io.reactivex.rxjava3.core;

@FunctionalInterface
/* loaded from: classes6.dex */
public interface CompletableTransformer {
    CompletableSource apply(Completable upstream);
}
