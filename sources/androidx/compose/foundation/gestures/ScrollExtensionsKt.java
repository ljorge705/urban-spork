package androidx.compose.foundation.gestures;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.SuspendAnimationKt;
import androidx.compose.foundation.MutatePriority;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;

/* compiled from: ScrollExtensions.kt */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a-\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u001a\u001d\u0010\u0007\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\b\u001a\u001f\u0010\t\u001a\u00020\n*\u00020\u00022\b\b\u0002\u0010\u000b\u001a\u00020\fH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\r\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000e"}, d2 = {"animateScrollBy", "", "Landroidx/compose/foundation/gestures/ScrollableState;", "value", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "(Landroidx/compose/foundation/gestures/ScrollableState;FLandroidx/compose/animation/core/AnimationSpec;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "scrollBy", "(Landroidx/compose/foundation/gestures/ScrollableState;FLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "stopScroll", "", "scrollPriority", "Landroidx/compose/foundation/MutatePriority;", "(Landroidx/compose/foundation/gestures/ScrollableState;Landroidx/compose/foundation/MutatePriority;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ScrollExtensionsKt {

    /* compiled from: ScrollExtensions.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.ScrollExtensionsKt", f = "ScrollExtensions.kt", i = {0}, l = {40}, m = "animateScrollBy", n = {"previousValue"}, s = {"L$0"})
    /* renamed from: androidx.compose.foundation.gestures.ScrollExtensionsKt$animateScrollBy$1, reason: invalid class name */
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ScrollExtensionsKt.animateScrollBy(null, 0.0f, null, this);
        }
    }

    /* compiled from: ScrollExtensions.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.ScrollExtensionsKt", f = "ScrollExtensions.kt", i = {0}, l = {61}, m = "scrollBy", n = {"consumed"}, s = {"L$0"})
    /* renamed from: androidx.compose.foundation.gestures.ScrollExtensionsKt$scrollBy$1, reason: invalid class name and case insensitive filesystem */
    static final class C02821 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C02821(Continuation<? super C02821> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ScrollExtensionsKt.scrollBy(null, 0.0f, this);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object animateScrollBy(androidx.compose.foundation.gestures.ScrollableState r7, float r8, androidx.compose.animation.core.AnimationSpec<java.lang.Float> r9, kotlin.coroutines.Continuation<? super java.lang.Float> r10) {
        /*
            boolean r0 = r10 instanceof androidx.compose.foundation.gestures.ScrollExtensionsKt.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r10
            androidx.compose.foundation.gestures.ScrollExtensionsKt$animateScrollBy$1 r0 = (androidx.compose.foundation.gestures.ScrollExtensionsKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L19
        L14:
            androidx.compose.foundation.gestures.ScrollExtensionsKt$animateScrollBy$1 r0 = new androidx.compose.foundation.gestures.ScrollExtensionsKt$animateScrollBy$1
            r0.<init>(r10)
        L19:
            r4 = r0
            java.lang.Object r10 = r4.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r4.label
            r2 = 1
            if (r1 == 0) goto L37
            if (r1 != r2) goto L2f
            java.lang.Object r7 = r4.L$0
            kotlin.jvm.internal.Ref$FloatRef r7 = (kotlin.jvm.internal.Ref.FloatRef) r7
            kotlin.ResultKt.throwOnFailure(r10)
            goto L5a
        L2f:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L37:
            kotlin.ResultKt.throwOnFailure(r10)
            kotlin.jvm.internal.Ref$FloatRef r10 = new kotlin.jvm.internal.Ref$FloatRef
            r10.<init>()
            r3 = 0
            androidx.compose.foundation.gestures.ScrollExtensionsKt$animateScrollBy$2 r1 = new androidx.compose.foundation.gestures.ScrollExtensionsKt$animateScrollBy$2
            r5 = 0
            r1.<init>(r8, r9, r10, r5)
            r8 = r1
            kotlin.jvm.functions.Function2 r8 = (kotlin.jvm.functions.Function2) r8
            r5 = 1
            r6 = 0
            r4.L$0 = r10
            r4.label = r2
            r1 = r7
            r2 = r3
            r3 = r8
            java.lang.Object r7 = androidx.compose.foundation.gestures.ScrollableState.scroll$default(r1, r2, r3, r4, r5, r6)
            if (r7 != r0) goto L59
            return r0
        L59:
            r7 = r10
        L5a:
            float r7 = r7.element
            java.lang.Float r7 = kotlin.coroutines.jvm.internal.Boxing.boxFloat(r7)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.ScrollExtensionsKt.animateScrollBy(androidx.compose.foundation.gestures.ScrollableState, float, androidx.compose.animation.core.AnimationSpec, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object animateScrollBy$default(ScrollableState scrollableState, float f, AnimationSpec animationSpec, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            animationSpec = AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
        }
        return animateScrollBy(scrollableState, f, animationSpec, continuation);
    }

    /* compiled from: ScrollExtensions.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/ScrollScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.ScrollExtensionsKt$animateScrollBy$2", f = "ScrollExtensions.kt", i = {}, l = {41}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.compose.foundation.gestures.ScrollExtensionsKt$animateScrollBy$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<ScrollScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ AnimationSpec<Float> $animationSpec;
        final /* synthetic */ Ref.FloatRef $previousValue;
        final /* synthetic */ float $value;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(float f, AnimationSpec<Float> animationSpec, Ref.FloatRef floatRef, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$value = f;
            this.$animationSpec = animationSpec;
            this.$previousValue = floatRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$value, this.$animationSpec, this.$previousValue, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(ScrollScope scrollScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(scrollScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                final ScrollScope scrollScope = (ScrollScope) this.L$0;
                float f = this.$value;
                AnimationSpec<Float> animationSpec = this.$animationSpec;
                final Ref.FloatRef floatRef = this.$previousValue;
                this.label = 1;
                if (SuspendAnimationKt.animate$default(0.0f, f, 0.0f, animationSpec, new Function2<Float, Float, Unit>() { // from class: androidx.compose.foundation.gestures.ScrollExtensionsKt.animateScrollBy.2.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Float f2, Float f3) {
                        invoke(f2.floatValue(), f3.floatValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(float f2, float f3) {
                        floatRef.element += scrollScope.scrollBy(f2 - floatRef.element);
                    }
                }, this, 4, null) == coroutine_suspended) {
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

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object scrollBy(androidx.compose.foundation.gestures.ScrollableState r7, float r8, kotlin.coroutines.Continuation<? super java.lang.Float> r9) {
        /*
            boolean r0 = r9 instanceof androidx.compose.foundation.gestures.ScrollExtensionsKt.C02821
            if (r0 == 0) goto L14
            r0 = r9
            androidx.compose.foundation.gestures.ScrollExtensionsKt$scrollBy$1 r0 = (androidx.compose.foundation.gestures.ScrollExtensionsKt.C02821) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L19
        L14:
            androidx.compose.foundation.gestures.ScrollExtensionsKt$scrollBy$1 r0 = new androidx.compose.foundation.gestures.ScrollExtensionsKt$scrollBy$1
            r0.<init>(r9)
        L19:
            r4 = r0
            java.lang.Object r9 = r4.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r4.label
            r2 = 1
            if (r1 == 0) goto L37
            if (r1 != r2) goto L2f
            java.lang.Object r7 = r4.L$0
            kotlin.jvm.internal.Ref$FloatRef r7 = (kotlin.jvm.internal.Ref.FloatRef) r7
            kotlin.ResultKt.throwOnFailure(r9)
            goto L5a
        L2f:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L37:
            kotlin.ResultKt.throwOnFailure(r9)
            kotlin.jvm.internal.Ref$FloatRef r9 = new kotlin.jvm.internal.Ref$FloatRef
            r9.<init>()
            r3 = 0
            androidx.compose.foundation.gestures.ScrollExtensionsKt$scrollBy$2 r1 = new androidx.compose.foundation.gestures.ScrollExtensionsKt$scrollBy$2
            r5 = 0
            r1.<init>(r9, r8, r5)
            r8 = r1
            kotlin.jvm.functions.Function2 r8 = (kotlin.jvm.functions.Function2) r8
            r5 = 1
            r6 = 0
            r4.L$0 = r9
            r4.label = r2
            r1 = r7
            r2 = r3
            r3 = r8
            java.lang.Object r7 = androidx.compose.foundation.gestures.ScrollableState.scroll$default(r1, r2, r3, r4, r5, r6)
            if (r7 != r0) goto L59
            return r0
        L59:
            r7 = r9
        L5a:
            float r7 = r7.element
            java.lang.Float r7 = kotlin.coroutines.jvm.internal.Boxing.boxFloat(r7)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.ScrollExtensionsKt.scrollBy(androidx.compose.foundation.gestures.ScrollableState, float, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* compiled from: ScrollExtensions.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/ScrollScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.ScrollExtensionsKt$scrollBy$2", f = "ScrollExtensions.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.compose.foundation.gestures.ScrollExtensionsKt$scrollBy$2, reason: invalid class name and case insensitive filesystem */
    static final class C02832 extends SuspendLambda implements Function2<ScrollScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.FloatRef $consumed;
        final /* synthetic */ float $value;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02832(Ref.FloatRef floatRef, float f, Continuation<? super C02832> continuation) {
            super(2, continuation);
            this.$consumed = floatRef;
            this.$value = f;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C02832 c02832 = new C02832(this.$consumed, this.$value, continuation);
            c02832.L$0 = obj;
            return c02832;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(ScrollScope scrollScope, Continuation<? super Unit> continuation) {
            return ((C02832) create(scrollScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                ScrollScope scrollScope = (ScrollScope) this.L$0;
                this.$consumed.element = scrollScope.scrollBy(this.$value);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* compiled from: ScrollExtensions.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/ScrollScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.ScrollExtensionsKt$stopScroll$2", f = "ScrollExtensions.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.compose.foundation.gestures.ScrollExtensionsKt$stopScroll$2, reason: invalid class name and case insensitive filesystem */
    static final class C02842 extends SuspendLambda implements Function2<ScrollScope, Continuation<? super Unit>, Object> {
        int label;

        C02842(Continuation<? super C02842> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C02842(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(ScrollScope scrollScope, Continuation<? super Unit> continuation) {
            return ((C02842) create(scrollScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public static /* synthetic */ Object stopScroll$default(ScrollableState scrollableState, MutatePriority mutatePriority, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            mutatePriority = MutatePriority.Default;
        }
        return stopScroll(scrollableState, mutatePriority, continuation);
    }

    public static final Object stopScroll(ScrollableState scrollableState, MutatePriority mutatePriority, Continuation<? super Unit> continuation) {
        Object objScroll = scrollableState.scroll(mutatePriority, new C02842(null), continuation);
        return objScroll == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objScroll : Unit.INSTANCE;
    }
}
