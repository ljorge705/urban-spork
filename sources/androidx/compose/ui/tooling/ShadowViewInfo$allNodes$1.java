package androidx.compose.ui.tooling;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.SequenceScope;

/* compiled from: ShadowViewInfo.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlin/sequences/SequenceScope;", "Landroidx/compose/ui/tooling/ShadowViewInfo;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.ui.tooling.ShadowViewInfo$allNodes$1", f = "ShadowViewInfo.kt", i = {0, 1}, l = {45, 46}, m = "invokeSuspend", n = {"$this$sequence", "$this$sequence"}, s = {"L$0", "L$0"})
/* loaded from: classes.dex */
final class ShadowViewInfo$allNodes$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super ShadowViewInfo>, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ ShadowViewInfo this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ShadowViewInfo$allNodes$1(ShadowViewInfo shadowViewInfo, Continuation<? super ShadowViewInfo$allNodes$1> continuation) {
        super(2, continuation);
        this.this$0 = shadowViewInfo;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ShadowViewInfo$allNodes$1 shadowViewInfo$allNodes$1 = new ShadowViewInfo$allNodes$1(this.this$0, continuation);
        shadowViewInfo$allNodes$1.L$0 = obj;
        return shadowViewInfo$allNodes$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(SequenceScope<? super ShadowViewInfo> sequenceScope, Continuation<? super Unit> continuation) {
        return ((ShadowViewInfo$allNodes$1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x007a  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r6) {
        /*
            r5 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r5.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L2a
            if (r1 == r3) goto L22
            if (r1 != r2) goto L1a
            java.lang.Object r1 = r5.L$1
            java.util.Iterator r1 = (java.util.Iterator) r1
            java.lang.Object r3 = r5.L$0
            kotlin.sequences.SequenceScope r3 = (kotlin.sequences.SequenceScope) r3
            kotlin.ResultKt.throwOnFailure(r6)
            goto L73
        L1a:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L22:
            java.lang.Object r1 = r5.L$0
            kotlin.sequences.SequenceScope r1 = (kotlin.sequences.SequenceScope) r1
            kotlin.ResultKt.throwOnFailure(r6)
            goto L42
        L2a:
            kotlin.ResultKt.throwOnFailure(r6)
            java.lang.Object r6 = r5.L$0
            r1 = r6
            kotlin.sequences.SequenceScope r1 = (kotlin.sequences.SequenceScope) r1
            androidx.compose.ui.tooling.ShadowViewInfo r6 = r5.this$0
            r4 = r5
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            r5.L$0 = r1
            r5.label = r3
            java.lang.Object r6 = r1.yield(r6, r4)
            if (r6 != r0) goto L42
            return r0
        L42:
            androidx.compose.ui.tooling.ShadowViewInfo r6 = r5.this$0
            java.util.List r6 = r6.getChildren()
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.Collection r3 = (java.util.Collection) r3
            java.util.Iterator r6 = r6.iterator()
        L55:
            boolean r4 = r6.hasNext()
            if (r4 == 0) goto L69
            java.lang.Object r4 = r6.next()
            androidx.compose.ui.tooling.ShadowViewInfo r4 = (androidx.compose.ui.tooling.ShadowViewInfo) r4
            kotlin.sequences.Sequence r4 = r4.getAllNodes()
            kotlin.collections.CollectionsKt.addAll(r3, r4)
            goto L55
        L69:
            java.util.List r3 = (java.util.List) r3
            java.lang.Iterable r3 = (java.lang.Iterable) r3
            java.util.Iterator r6 = r3.iterator()
            r3 = r1
            r1 = r6
        L73:
            r6 = r5
        L74:
            boolean r4 = r1.hasNext()
            if (r4 == 0) goto L8d
            java.lang.Object r4 = r1.next()
            androidx.compose.ui.tooling.ShadowViewInfo r4 = (androidx.compose.ui.tooling.ShadowViewInfo) r4
            r6.L$0 = r3
            r6.L$1 = r1
            r6.label = r2
            java.lang.Object r4 = r3.yield(r4, r6)
            if (r4 != r0) goto L74
            return r0
        L8d:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.tooling.ShadowViewInfo$allNodes$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
