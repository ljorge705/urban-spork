package androidx.compose.foundation;

import androidx.compose.foundation.HoverableKt;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: Hoverable.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.HoverableKt$hoverable$2", f = "Hoverable.kt", i = {0}, l = {70}, m = "invoke$emitExit", n = {"hoverInteraction$delegate"}, s = {"L$0"})
/* loaded from: classes.dex */
final class HoverableKt$hoverable$2$invoke$emitExit$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;

    HoverableKt$hoverable$2$invoke$emitExit$1(Continuation<? super HoverableKt$hoverable$2$invoke$emitExit$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return HoverableKt.AnonymousClass2.invoke$emitExit(null, null, this);
    }
}
