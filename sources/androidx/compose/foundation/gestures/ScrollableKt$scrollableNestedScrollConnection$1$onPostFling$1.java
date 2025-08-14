package androidx.compose.foundation.gestures;

import androidx.compose.foundation.gestures.ScrollableKt;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: Scrollable.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.gestures.ScrollableKt$scrollableNestedScrollConnection$1", f = "Scrollable.kt", i = {0, 0}, l = {516}, m = "onPostFling-RZ2iAVY", n = {"this", "available"}, s = {"L$0", "J$0"})
/* loaded from: classes.dex */
final class ScrollableKt$scrollableNestedScrollConnection$1$onPostFling$1 extends ContinuationImpl {
    long J$0;
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ScrollableKt.C02871 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ScrollableKt$scrollableNestedScrollConnection$1$onPostFling$1(ScrollableKt.C02871 c02871, Continuation<? super ScrollableKt$scrollableNestedScrollConnection$1$onPostFling$1> continuation) {
        super(continuation);
        this.this$0 = c02871;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.mo583onPostFlingRZ2iAVY(0L, 0L, this);
    }
}
