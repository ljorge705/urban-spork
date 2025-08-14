package androidx.compose.material;

import com.drew.metadata.iptc.IptcDirectory;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: Slider.kt */
@Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
@DebugMetadata(c = "androidx.compose.material.SliderKt", f = "Slider.kt", i = {0}, l = {IptcDirectory.TAG_OBJECT_PREVIEW_FILE_FORMAT_VERSION}, m = "awaitSlop-rnUCldI", n = {"initialDelta"}, s = {"L$0"})
/* loaded from: classes.dex */
final class SliderKt$awaitSlop$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;

    SliderKt$awaitSlop$1(Continuation<? super SliderKt$awaitSlop$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return SliderKt.m1401awaitSloprnUCldI(null, 0L, this);
    }
}
