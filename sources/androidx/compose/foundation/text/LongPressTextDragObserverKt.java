package androidx.compose.foundation.text;

import androidx.compose.foundation.gestures.DragGestureDetectorKt;
import androidx.compose.foundation.gestures.ForEachGestureKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputScope;
import com.facebook.imagepipeline.transcoder.JpegTranscoderUtils;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Job;

/* compiled from: LongPressTextDragObserver.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u001d\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0080@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u001a\u001d\u0010\u0006\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0080@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u001a\u001d\u0010\u0007\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u001a\u001d\u0010\b\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\t"}, d2 = {"detectDownAndDragGesturesWithObserver", "", "Landroidx/compose/ui/input/pointer/PointerInputScope;", "observer", "Landroidx/compose/foundation/text/TextDragObserver;", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Landroidx/compose/foundation/text/TextDragObserver;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "detectDragGesturesAfterLongPressWithObserver", "detectDragGesturesWithObserver", "detectPreDragGesturesWithObserver", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LongPressTextDragObserverKt {
    public static final Object detectDragGesturesAfterLongPressWithObserver(PointerInputScope pointerInputScope, final TextDragObserver textDragObserver, Continuation<? super Unit> continuation) {
        Object objDetectDragGesturesAfterLongPress = DragGestureDetectorKt.detectDragGesturesAfterLongPress(pointerInputScope, new Function1<Offset, Unit>() { // from class: androidx.compose.foundation.text.LongPressTextDragObserverKt.detectDragGesturesAfterLongPressWithObserver.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Offset offset) {
                m996invokek4lQ0M(offset.getPackedValue());
                return Unit.INSTANCE;
            }

            /* renamed from: invoke-k-4lQ0M, reason: not valid java name */
            public final void m996invokek4lQ0M(long j) {
                textDragObserver.mo1028onStartk4lQ0M(j);
            }
        }, new Function0<Unit>() { // from class: androidx.compose.foundation.text.LongPressTextDragObserverKt.detectDragGesturesAfterLongPressWithObserver.3
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                textDragObserver.onStop();
            }
        }, new Function0<Unit>() { // from class: androidx.compose.foundation.text.LongPressTextDragObserverKt.detectDragGesturesAfterLongPressWithObserver.4
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                textDragObserver.onCancel();
            }
        }, new Function2<PointerInputChange, Offset, Unit>() { // from class: androidx.compose.foundation.text.LongPressTextDragObserverKt.detectDragGesturesAfterLongPressWithObserver.5
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(PointerInputChange pointerInputChange, Offset offset) {
                m997invokeUv8p0NA(pointerInputChange, offset.getPackedValue());
                return Unit.INSTANCE;
            }

            /* renamed from: invoke-Uv8p0NA, reason: not valid java name */
            public final void m997invokeUv8p0NA(PointerInputChange pointerInputChange, long j) {
                Intrinsics.checkNotNullParameter(pointerInputChange, "<anonymous parameter 0>");
                textDragObserver.mo1027onDragk4lQ0M(j);
            }
        }, continuation);
        return objDetectDragGesturesAfterLongPress == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDetectDragGesturesAfterLongPress : Unit.INSTANCE;
    }

    /* compiled from: LongPressTextDragObserver.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lkotlinx/coroutines/Job;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.LongPressTextDragObserverKt$detectDownAndDragGesturesWithObserver$2", f = "LongPressTextDragObserver.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.compose.foundation.text.LongPressTextDragObserverKt$detectDownAndDragGesturesWithObserver$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Job>, Object> {
        final /* synthetic */ TextDragObserver $observer;
        final /* synthetic */ PointerInputScope $this_detectDownAndDragGesturesWithObserver;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(PointerInputScope pointerInputScope, TextDragObserver textDragObserver, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$this_detectDownAndDragGesturesWithObserver = pointerInputScope;
            this.$observer = textDragObserver;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$this_detectDownAndDragGesturesWithObserver, this.$observer, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Job> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* compiled from: LongPressTextDragObserver.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.text.LongPressTextDragObserverKt$detectDownAndDragGesturesWithObserver$2$1", f = "LongPressTextDragObserver.kt", i = {}, l = {82}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: androidx.compose.foundation.text.LongPressTextDragObserverKt$detectDownAndDragGesturesWithObserver$2$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ TextDragObserver $observer;
            final /* synthetic */ PointerInputScope $this_detectDownAndDragGesturesWithObserver;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(PointerInputScope pointerInputScope, TextDragObserver textDragObserver, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.$this_detectDownAndDragGesturesWithObserver = pointerInputScope;
                this.$observer = textDragObserver;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.$this_detectDownAndDragGesturesWithObserver, this.$observer, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    this.label = 1;
                    if (LongPressTextDragObserverKt.detectPreDragGesturesWithObserver(this.$this_detectDownAndDragGesturesWithObserver, this.$observer, this) == coroutine_suspended) {
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

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass1(this.$this_detectDownAndDragGesturesWithObserver, this.$observer, null), 3, null);
                return BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new C00402(this.$this_detectDownAndDragGesturesWithObserver, this.$observer, null), 3, null);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }

        /* compiled from: LongPressTextDragObserver.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.text.LongPressTextDragObserverKt$detectDownAndDragGesturesWithObserver$2$2", f = "LongPressTextDragObserver.kt", i = {}, l = {JpegTranscoderUtils.DEFAULT_JPEG_QUALITY}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: androidx.compose.foundation.text.LongPressTextDragObserverKt$detectDownAndDragGesturesWithObserver$2$2, reason: invalid class name and collision with other inner class name */
        static final class C00402 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ TextDragObserver $observer;
            final /* synthetic */ PointerInputScope $this_detectDownAndDragGesturesWithObserver;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00402(PointerInputScope pointerInputScope, TextDragObserver textDragObserver, Continuation<? super C00402> continuation) {
                super(2, continuation);
                this.$this_detectDownAndDragGesturesWithObserver = pointerInputScope;
                this.$observer = textDragObserver;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C00402(this.$this_detectDownAndDragGesturesWithObserver, this.$observer, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00402) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    this.label = 1;
                    if (LongPressTextDragObserverKt.detectDragGesturesWithObserver(this.$this_detectDownAndDragGesturesWithObserver, this.$observer, this) == coroutine_suspended) {
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
    }

    public static final Object detectDownAndDragGesturesWithObserver(PointerInputScope pointerInputScope, TextDragObserver textDragObserver, Continuation<? super Unit> continuation) {
        Object objCoroutineScope = CoroutineScopeKt.coroutineScope(new AnonymousClass2(pointerInputScope, textDragObserver, null), continuation);
        return objCoroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCoroutineScope : Unit.INSTANCE;
    }

    /* compiled from: LongPressTextDragObserver.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.LongPressTextDragObserverKt$detectPreDragGesturesWithObserver$2", f = "LongPressTextDragObserver.kt", i = {0, 1, 1}, l = {98, 102}, m = "invokeSuspend", n = {"$this$awaitEachGesture", "$this$awaitEachGesture", "down"}, s = {"L$0", "L$0", "L$1"})
    /* renamed from: androidx.compose.foundation.text.LongPressTextDragObserverKt$detectPreDragGesturesWithObserver$2, reason: invalid class name and case insensitive filesystem */
    static final class C03682 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ TextDragObserver $observer;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03682(TextDragObserver textDragObserver, Continuation<? super C03682> continuation) {
            super(2, continuation);
            this.$observer = textDragObserver;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C03682 c03682 = new C03682(this.$observer, continuation);
            c03682.L$0 = obj;
            return c03682;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
            return ((C03682) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0067 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:17:0x0068  */
        /* JADX WARN: Removed duplicated region for block: B:20:0x007b  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x0068 -> B:18:0x006e). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r15) {
            /*
                r14 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r14.label
                r2 = 2
                r3 = 1
                if (r1 == 0) goto L2e
                if (r1 == r3) goto L26
                if (r1 != r2) goto L1e
                java.lang.Object r1 = r14.L$1
                androidx.compose.ui.input.pointer.PointerInputChange r1 = (androidx.compose.ui.input.pointer.PointerInputChange) r1
                java.lang.Object r4 = r14.L$0
                androidx.compose.ui.input.pointer.AwaitPointerEventScope r4 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r4
                kotlin.ResultKt.throwOnFailure(r15)
                r5 = r4
                r4 = r1
                r1 = r0
                r0 = r14
                goto L6e
            L1e:
                java.lang.IllegalStateException r15 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r15.<init>(r0)
                throw r15
            L26:
                java.lang.Object r1 = r14.L$0
                androidx.compose.ui.input.pointer.AwaitPointerEventScope r1 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r1
                kotlin.ResultKt.throwOnFailure(r15)
                goto L49
            L2e:
                kotlin.ResultKt.throwOnFailure(r15)
                java.lang.Object r15 = r14.L$0
                r1 = r15
                androidx.compose.ui.input.pointer.AwaitPointerEventScope r1 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r1
                r5 = 0
                r6 = 0
                r7 = r14
                kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7
                r8 = 3
                r9 = 0
                r14.L$0 = r1
                r14.label = r3
                r4 = r1
                java.lang.Object r15 = androidx.compose.foundation.gestures.TapGestureDetectorKt.awaitFirstDown$default(r4, r5, r6, r7, r8, r9)
                if (r15 != r0) goto L49
                return r0
            L49:
                androidx.compose.ui.input.pointer.PointerInputChange r15 = (androidx.compose.ui.input.pointer.PointerInputChange) r15
                androidx.compose.foundation.text.TextDragObserver r4 = r14.$observer
                long r5 = r15.getPosition()
                r4.mo1026onDownk4lQ0M(r5)
                r4 = r1
                r1 = r15
                r15 = r14
            L57:
                r5 = r15
                kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
                r15.L$0 = r4
                r15.L$1 = r1
                r15.label = r2
                r6 = 0
                java.lang.Object r5 = androidx.compose.ui.input.pointer.AwaitPointerEventScope.awaitPointerEvent$default(r4, r6, r5, r3, r6)
                if (r5 != r0) goto L68
                return r0
            L68:
                r13 = r0
                r0 = r15
                r15 = r5
                r5 = r4
                r4 = r1
                r1 = r13
            L6e:
                androidx.compose.ui.input.pointer.PointerEvent r15 = (androidx.compose.ui.input.pointer.PointerEvent) r15
                java.util.List r15 = r15.getChanges()
                int r6 = r15.size()
                r7 = 0
            L79:
                if (r7 >= r6) goto L9d
                java.lang.Object r8 = r15.get(r7)
                androidx.compose.ui.input.pointer.PointerInputChange r8 = (androidx.compose.ui.input.pointer.PointerInputChange) r8
                long r9 = r8.getId()
                long r11 = r4.getId()
                boolean r9 = androidx.compose.ui.input.pointer.PointerId.m3263equalsimpl0(r9, r11)
                if (r9 == 0) goto L9a
                boolean r8 = r8.getPressed()
                if (r8 == 0) goto L9a
                r15 = r0
                r0 = r1
                r1 = r4
                r4 = r5
                goto L57
            L9a:
                int r7 = r7 + 1
                goto L79
            L9d:
                androidx.compose.foundation.text.TextDragObserver r15 = r0.$observer
                r15.onUp()
                kotlin.Unit r15 = kotlin.Unit.INSTANCE
                return r15
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.LongPressTextDragObserverKt.C03682.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object detectPreDragGesturesWithObserver(PointerInputScope pointerInputScope, TextDragObserver textDragObserver, Continuation<? super Unit> continuation) {
        Object objAwaitEachGesture = ForEachGestureKt.awaitEachGesture(pointerInputScope, new C03682(textDragObserver, null), continuation);
        return objAwaitEachGesture == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAwaitEachGesture : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object detectDragGesturesWithObserver(PointerInputScope pointerInputScope, final TextDragObserver textDragObserver, Continuation<? super Unit> continuation) {
        Object objDetectDragGestures = DragGestureDetectorKt.detectDragGestures(pointerInputScope, new Function1<Offset, Unit>() { // from class: androidx.compose.foundation.text.LongPressTextDragObserverKt.detectDragGesturesWithObserver.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Offset offset) {
                m998invokek4lQ0M(offset.getPackedValue());
                return Unit.INSTANCE;
            }

            /* renamed from: invoke-k-4lQ0M, reason: not valid java name */
            public final void m998invokek4lQ0M(long j) {
                textDragObserver.mo1028onStartk4lQ0M(j);
            }
        }, new Function0<Unit>() { // from class: androidx.compose.foundation.text.LongPressTextDragObserverKt.detectDragGesturesWithObserver.3
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                textDragObserver.onStop();
            }
        }, new Function0<Unit>() { // from class: androidx.compose.foundation.text.LongPressTextDragObserverKt.detectDragGesturesWithObserver.4
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                textDragObserver.onCancel();
            }
        }, new Function2<PointerInputChange, Offset, Unit>() { // from class: androidx.compose.foundation.text.LongPressTextDragObserverKt.detectDragGesturesWithObserver.5
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(PointerInputChange pointerInputChange, Offset offset) {
                m999invokeUv8p0NA(pointerInputChange, offset.getPackedValue());
                return Unit.INSTANCE;
            }

            /* renamed from: invoke-Uv8p0NA, reason: not valid java name */
            public final void m999invokeUv8p0NA(PointerInputChange pointerInputChange, long j) {
                Intrinsics.checkNotNullParameter(pointerInputChange, "<anonymous parameter 0>");
                textDragObserver.mo1027onDragk4lQ0M(j);
            }
        }, continuation);
        return objDetectDragGestures == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDetectDragGestures : Unit.INSTANCE;
    }
}
