package androidx.compose.material;

import java.util.Map;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: Add missing generic type declarations: [T] */
/* compiled from: Collect.kt */
@Metadata(d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\u0000"}, d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3", "Lkotlinx/coroutines/flow/FlowCollector;", "emit", "", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class SwipeableState$snapTo$$inlined$collect$1<T> implements FlowCollector<Map<Float, ? extends T>> {
    final /* synthetic */ Object $targetValue$inlined;
    final /* synthetic */ SwipeableState this$0;

    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "androidx.compose.material.SwipeableState$snapTo$$inlined$collect$1", f = "Swipeable.kt", i = {}, l = {139}, m = "emit", n = {}, s = {})
    /* renamed from: androidx.compose.material.SwipeableState$snapTo$$inlined$collect$1$1, reason: invalid class name */
    public static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SwipeableState$snapTo$$inlined$collect$1.this.emit(null, this);
        }
    }

    public SwipeableState$snapTo$$inlined$collect$1(Object obj, SwipeableState swipeableState) {
        this.$targetValue$inlined = obj;
        this.this$0 = swipeableState;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object emit(java.util.Map<java.lang.Float, ? extends T> r5, kotlin.coroutines.Continuation<? super kotlin.Unit> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof androidx.compose.material.SwipeableState$snapTo$$inlined$collect$1.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r6
            androidx.compose.material.SwipeableState$snapTo$$inlined$collect$1$1 r0 = (androidx.compose.material.SwipeableState$snapTo$$inlined$collect$1.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L19
        L14:
            androidx.compose.material.SwipeableState$snapTo$$inlined$collect$1$1 r0 = new androidx.compose.material.SwipeableState$snapTo$$inlined$collect$1$1
            r0.<init>(r6)
        L19:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L36
            if (r2 != r3) goto L2e
            java.lang.Object r5 = r0.L$0
            androidx.compose.material.SwipeableState$snapTo$$inlined$collect$1 r5 = (androidx.compose.material.SwipeableState$snapTo$$inlined$collect$1) r5
            kotlin.ResultKt.throwOnFailure(r6)
            goto L58
        L2e:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L36:
            kotlin.ResultKt.throwOnFailure(r6)
            r6 = r0
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
            java.util.Map r5 = (java.util.Map) r5
            java.lang.Object r6 = r4.$targetValue$inlined
            java.lang.Float r5 = androidx.compose.material.SwipeableKt.access$getOffset(r5, r6)
            if (r5 == 0) goto L62
            androidx.compose.material.SwipeableState r6 = r4.this$0
            float r5 = r5.floatValue()
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r5 = androidx.compose.material.SwipeableState.access$snapInternalToOffset(r6, r5, r0)
            if (r5 != r1) goto L57
            return r1
        L57:
            r5 = r4
        L58:
            androidx.compose.material.SwipeableState r6 = r5.this$0
            java.lang.Object r5 = r5.$targetValue$inlined
            androidx.compose.material.SwipeableState.access$setCurrentValue(r6, r5)
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        L62:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r6 = "The target value must have an associated anchor."
            java.lang.String r6 = r6.toString()
            r5.<init>(r6)
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.SwipeableState$snapTo$$inlined$collect$1.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
