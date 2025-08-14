package androidx.compose.foundation.lazy.layout;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.ranges.IntRange;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: LazyNearestItemsRange.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.lazy.layout.LazyNearestItemsRangeKt$rememberLazyNearestItemsRangeState$1$1", f = "LazyNearestItemsRange.kt", i = {}, l = {66}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
final class LazyNearestItemsRangeKt$rememberLazyNearestItemsRangeState$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function0<Integer> $extraItemCount;
    final /* synthetic */ Function0<Integer> $firstVisibleItemIndex;
    final /* synthetic */ Function0<Integer> $slidingWindowSize;
    final /* synthetic */ MutableState<IntRange> $state;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    LazyNearestItemsRangeKt$rememberLazyNearestItemsRangeState$1$1(Function0<Integer> function0, Function0<Integer> function02, Function0<Integer> function03, MutableState<IntRange> mutableState, Continuation<? super LazyNearestItemsRangeKt$rememberLazyNearestItemsRangeState$1$1> continuation) {
        super(2, continuation);
        this.$firstVisibleItemIndex = function0;
        this.$slidingWindowSize = function02;
        this.$extraItemCount = function03;
        this.$state = mutableState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LazyNearestItemsRangeKt$rememberLazyNearestItemsRangeState$1$1(this.$firstVisibleItemIndex, this.$slidingWindowSize, this.$extraItemCount, this.$state, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((LazyNearestItemsRangeKt$rememberLazyNearestItemsRangeState$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final Function0<Integer> function0 = this.$firstVisibleItemIndex;
            final Function0<Integer> function02 = this.$slidingWindowSize;
            final Function0<Integer> function03 = this.$extraItemCount;
            Flow flowSnapshotFlow = SnapshotStateKt.snapshotFlow(new Function0<IntRange>() { // from class: androidx.compose.foundation.lazy.layout.LazyNearestItemsRangeKt$rememberLazyNearestItemsRangeState$1$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final IntRange invoke() {
                    return LazyNearestItemsRangeKt.calculateNearestItemsRange(function0.invoke().intValue(), function02.invoke().intValue(), function03.invoke().intValue());
                }
            });
            final MutableState<IntRange> mutableState = this.$state;
            this.label = 1;
            if (flowSnapshotFlow.collect(new FlowCollector<IntRange>() { // from class: androidx.compose.foundation.lazy.layout.LazyNearestItemsRangeKt$rememberLazyNearestItemsRangeState$1$1.2
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(IntRange intRange, Continuation continuation) {
                    return emit2(intRange, (Continuation<? super Unit>) continuation);
                }

                /* renamed from: emit, reason: avoid collision after fix types in other method */
                public final Object emit2(IntRange intRange, Continuation<? super Unit> continuation) {
                    mutableState.setValue(intRange);
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
