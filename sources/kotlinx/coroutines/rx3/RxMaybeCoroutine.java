package kotlinx.coroutines.rx3;

import androidx.exifinterface.media.ExifInterface;
import io.reactivex.rxjava3.core.MaybeEmitter;
import io.sentry.protocol.Mechanism;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.AbstractCoroutine;

/* compiled from: RxMaybe.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00010\u0003B\u001b\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0002\u0010\bJ\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0014J\u0017\u0010\u000f\u001a\u00020\n2\b\u0010\u0010\u001a\u0004\u0018\u00018\u0000H\u0014¢\u0006\u0002\u0010\u0011R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lkotlinx/coroutines/rx3/RxMaybeCoroutine;", ExifInterface.GPS_DIRECTION_TRUE, "", "Lkotlinx/coroutines/AbstractCoroutine;", "parentContext", "Lkotlin/coroutines/CoroutineContext;", "subscriber", "Lio/reactivex/rxjava3/core/MaybeEmitter;", "(Lkotlin/coroutines/CoroutineContext;Lio/reactivex/rxjava3/core/MaybeEmitter;)V", "onCancelled", "", "cause", "", Mechanism.JsonKeys.HANDLED, "", "onCompleted", "value", "(Ljava/lang/Object;)V", "kotlinx-coroutines-rx3"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes5.dex */
final class RxMaybeCoroutine<T> extends AbstractCoroutine<T> {
    private final MaybeEmitter<T> subscriber;

    public RxMaybeCoroutine(CoroutineContext coroutineContext, MaybeEmitter<T> maybeEmitter) {
        super(coroutineContext, false, true);
        this.subscriber = maybeEmitter;
    }

    @Override // kotlinx.coroutines.AbstractCoroutine
    protected void onCompleted(T value) {
        try {
            if (value == null) {
                this.subscriber.onComplete();
            } else {
                this.subscriber.onSuccess(value);
            }
        } catch (Throwable th) {
            RxCancellableKt.handleUndeliverableException(th, get$context());
        }
    }

    @Override // kotlinx.coroutines.AbstractCoroutine
    protected void onCancelled(Throwable cause, boolean handled) {
        try {
            if (this.subscriber.tryOnError(cause)) {
                return;
            }
        } catch (Throwable th) {
            ExceptionsKt.addSuppressed(cause, th);
        }
        RxCancellableKt.handleUndeliverableException(cause, get$context());
    }
}
