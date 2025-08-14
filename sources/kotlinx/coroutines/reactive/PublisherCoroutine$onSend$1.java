package kotlinx.coroutines.reactive;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlinx.coroutines.selects.SelectInstance;

/* compiled from: Publish.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes5.dex */
/* synthetic */ class PublisherCoroutine$onSend$1 extends FunctionReferenceImpl implements Function3<PublisherCoroutine<?>, SelectInstance<?>, Object, Unit> {
    public static final PublisherCoroutine$onSend$1 INSTANCE = new PublisherCoroutine$onSend$1();

    PublisherCoroutine$onSend$1() {
        super(3, PublisherCoroutine.class, "registerSelectForSend", "registerSelectForSend(Lkotlinx/coroutines/selects/SelectInstance;Ljava/lang/Object;)V", 0);
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Unit invoke(PublisherCoroutine<?> publisherCoroutine, SelectInstance<?> selectInstance, Object obj) {
        invoke2(publisherCoroutine, selectInstance, obj);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(PublisherCoroutine<?> publisherCoroutine, SelectInstance<?> selectInstance, Object obj) {
        publisherCoroutine.registerSelectForSend(selectInstance, obj);
    }
}
