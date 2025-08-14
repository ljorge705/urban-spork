package bolts;

/* loaded from: classes5.dex */
public interface Continuation<TTaskResult, TContinuationResult> {
    TContinuationResult then(Task<TTaskResult> task) throws Exception;
}
