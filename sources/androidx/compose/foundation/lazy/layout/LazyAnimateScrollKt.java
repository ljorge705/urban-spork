package androidx.compose.foundation.lazy.layout;

import androidx.compose.foundation.gestures.ScrollScope;
import androidx.compose.ui.unit.Dp;
import com.google.android.gms.common.ConnectionResult;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;

/* compiled from: LazyAnimateScroll.kt */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u001a\u0017\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0082\b\u001a%\u0010\f\u001a\u00020\b*\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0080@ø\u0001\u0000¢\u0006\u0002\u0010\u0011\"\u0013\u0010\u0000\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000\"\u0013\u0010\u0005\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u0013\u0010\u0006\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, d2 = {"BoundDistance", "Landroidx/compose/ui/unit/Dp;", "F", "DEBUG", "", "MinimumDistance", "TargetDistance", "debugLog", "", "generateMsg", "Lkotlin/Function0;", "", "animateScrollToItem", "Landroidx/compose/foundation/lazy/layout/LazyAnimateScrollScope;", "index", "", "scrollOffset", "(Landroidx/compose/foundation/lazy/layout/LazyAnimateScrollScope;IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LazyAnimateScrollKt {
    private static final boolean DEBUG = false;
    private static final float TargetDistance = Dp.m4390constructorimpl(2500);
    private static final float BoundDistance = Dp.m4390constructorimpl(ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED);
    private static final float MinimumDistance = Dp.m4390constructorimpl(50);

    private static final void debugLog(Function0<String> function0) {
    }

    /* compiled from: LazyAnimateScroll.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/ScrollScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.lazy.layout.LazyAnimateScrollKt$animateScrollToItem$2", f = "LazyAnimateScroll.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 1}, l = {137, 233}, m = "invokeSuspend", n = {"$this$scroll", "loop", "anim", "loops", "targetDistancePx", "boundDistancePx", "minDistancePx", "forward", "$this$scroll"}, s = {"L$0", "L$1", "L$2", "L$3", "F$0", "F$1", "F$2", "I$0", "L$0"})
    /* renamed from: androidx.compose.foundation.lazy.layout.LazyAnimateScrollKt$animateScrollToItem$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<ScrollScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ int $index;
        final /* synthetic */ int $scrollOffset;
        final /* synthetic */ LazyAnimateScrollScope $this_animateScrollToItem;
        float F$0;
        float F$1;
        float F$2;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(int i, LazyAnimateScrollScope lazyAnimateScrollScope, int i2, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$index = i;
            this.$this_animateScrollToItem = lazyAnimateScrollScope;
            this.$scrollOffset = i2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$index, this.$this_animateScrollToItem, this.$scrollOffset, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(ScrollScope scrollScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(scrollScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:29:0x00d9 A[Catch: ItemFoundInScroll -> 0x01cd, TryCatch #2 {ItemFoundInScroll -> 0x01cd, blocks: (B:27:0x00d5, B:29:0x00d9, B:31:0x00e1, B:41:0x0105, B:45:0x014d, B:49:0x015a), top: B:90:0x00d5 }] */
        /* JADX WARN: Removed duplicated region for block: B:34:0x00f3 A[Catch: ItemFoundInScroll -> 0x01bf, TRY_ENTER, TRY_LEAVE, TryCatch #5 {ItemFoundInScroll -> 0x01bf, blocks: (B:58:0x01b2, B:34:0x00f3), top: B:95:0x01b2 }] */
        /* JADX WARN: Removed duplicated region for block: B:38:0x0100  */
        /* JADX WARN: Removed duplicated region for block: B:43:0x0148  */
        /* JADX WARN: Removed duplicated region for block: B:44:0x014b  */
        /* JADX WARN: Removed duplicated region for block: B:47:0x0155  */
        /* JADX WARN: Removed duplicated region for block: B:48:0x0158  */
        /* JADX WARN: Removed duplicated region for block: B:56:0x01a9 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:57:0x01aa  */
        /* JADX WARN: Removed duplicated region for block: B:76:0x021d  */
        /* JADX WARN: Removed duplicated region for block: B:77:0x0220  */
        /* JADX WARN: Removed duplicated region for block: B:80:0x0247 A[RETURN] */
        /* JADX WARN: Type inference failed for: r12v1, types: [T, androidx.compose.animation.core.AnimationState] */
        /* JADX WARN: Type inference failed for: r5v11, types: [T, androidx.compose.animation.core.AnimationState] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:57:0x01aa -> B:95:0x01b2). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r36) {
            /*
                Method dump skipped, instructions count: 627
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.layout.LazyAnimateScrollKt.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean invokeSuspend$isOvershot(boolean z, LazyAnimateScrollScope lazyAnimateScrollScope, int i, int i2) {
            if (z) {
                if (lazyAnimateScrollScope.getFirstVisibleItemIndex() <= i && (lazyAnimateScrollScope.getFirstVisibleItemIndex() != i || lazyAnimateScrollScope.getFirstVisibleItemScrollOffset() <= i2)) {
                    return false;
                }
            } else if (lazyAnimateScrollScope.getFirstVisibleItemIndex() >= i && (lazyAnimateScrollScope.getFirstVisibleItemIndex() != i || lazyAnimateScrollScope.getFirstVisibleItemScrollOffset() >= i2)) {
                return false;
            }
            return true;
        }
    }

    public static final Object animateScrollToItem(LazyAnimateScrollScope lazyAnimateScrollScope, int i, int i2, Continuation<? super Unit> continuation) {
        Object objScroll = lazyAnimateScrollScope.scroll(new AnonymousClass2(i, lazyAnimateScrollScope, i2, null), continuation);
        return objScroll == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objScroll : Unit.INSTANCE;
    }
}
