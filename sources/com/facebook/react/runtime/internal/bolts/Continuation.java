package com.facebook.react.runtime.internal.bolts;

/* loaded from: classes5.dex */
public interface Continuation<TTaskResult, TContinuationResult> {
    TContinuationResult then(Task<TTaskResult> task) throws Exception;
}
