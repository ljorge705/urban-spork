package androidx.compose.ui.input.pointer;

import android.view.MotionEvent;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.viewinterop.AndroidViewHolder;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PointerInteropFilter.android.kt */
@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a/\u0010\u0000\u001a\u00020\u0001*\u00020\u00012!\u0010\u0002\u001a\u001d\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0003H\u0007\u001a,\u0010\t\u001a\u00020\u0001*\u00020\u00012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\r0\u0003H\u0007\u001a\u0014\u0010\t\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u000fH\u0001¨\u0006\u0010"}, d2 = {"motionEventSpy", "Landroidx/compose/ui/Modifier;", "watcher", "Lkotlin/Function1;", "Landroid/view/MotionEvent;", "Lkotlin/ParameterName;", "name", "motionEvent", "", "pointerInteropFilter", "requestDisallowInterceptTouchEvent", "Landroidx/compose/ui/input/pointer/RequestDisallowInterceptTouchEvent;", "onTouchEvent", "", "view", "Landroidx/compose/ui/viewinterop/AndroidViewHolder;", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class PointerInteropFilter_androidKt {
    public static /* synthetic */ Modifier pointerInteropFilter$default(Modifier modifier, RequestDisallowInterceptTouchEvent requestDisallowInterceptTouchEvent, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            requestDisallowInterceptTouchEvent = null;
        }
        return pointerInteropFilter(modifier, requestDisallowInterceptTouchEvent, function1);
    }

    public static final Modifier pointerInteropFilter(Modifier modifier, final AndroidViewHolder view) {
        Intrinsics.checkNotNullParameter(modifier, "<this>");
        Intrinsics.checkNotNullParameter(view, "view");
        PointerInteropFilter pointerInteropFilter = new PointerInteropFilter();
        pointerInteropFilter.setOnTouchEvent(new Function1<MotionEvent, Boolean>() { // from class: androidx.compose.ui.input.pointer.PointerInteropFilter_androidKt.pointerInteropFilter.3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(MotionEvent motionEvent) {
                boolean zDispatchTouchEvent;
                Intrinsics.checkNotNullParameter(motionEvent, "motionEvent");
                switch (motionEvent.getActionMasked()) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        zDispatchTouchEvent = view.dispatchTouchEvent(motionEvent);
                        break;
                    default:
                        zDispatchTouchEvent = view.dispatchGenericMotionEvent(motionEvent);
                        break;
                }
                return Boolean.valueOf(zDispatchTouchEvent);
            }
        });
        RequestDisallowInterceptTouchEvent requestDisallowInterceptTouchEvent = new RequestDisallowInterceptTouchEvent();
        pointerInteropFilter.setRequestDisallowInterceptTouchEvent(requestDisallowInterceptTouchEvent);
        view.setOnRequestDisallowInterceptTouchEvent$ui_release(requestDisallowInterceptTouchEvent);
        return modifier.then(pointerInteropFilter);
    }

    /* compiled from: PointerInteropFilter.android.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/PointerInputScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.ui.input.pointer.PointerInteropFilter_androidKt$motionEventSpy$1", f = "PointerInteropFilter.android.kt", i = {}, l = {343}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.compose.ui.input.pointer.PointerInteropFilter_androidKt$motionEventSpy$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function1<MotionEvent, Unit> $watcher;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(Function1<? super MotionEvent, Unit> function1, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$watcher = function1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$watcher, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                PointerInputScope pointerInputScope = (PointerInputScope) this.L$0;
                pointerInputScope.setInterceptOutOfBoundsChildEvents(true);
                this.label = 1;
                if (pointerInputScope.awaitPointerEventScope(new C00831(this.$watcher, null), this) == coroutine_suspended) {
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

        /* compiled from: PointerInteropFilter.android.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.ui.input.pointer.PointerInteropFilter_androidKt$motionEventSpy$1$1", f = "PointerInteropFilter.android.kt", i = {0}, l = {345}, m = "invokeSuspend", n = {"$this$awaitPointerEventScope"}, s = {"L$0"})
        /* renamed from: androidx.compose.ui.input.pointer.PointerInteropFilter_androidKt$motionEventSpy$1$1, reason: invalid class name and collision with other inner class name */
        static final class C00831 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Function1<MotionEvent, Unit> $watcher;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00831(Function1<? super MotionEvent, Unit> function1, Continuation<? super C00831> continuation) {
                super(2, continuation);
                this.$watcher = function1;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C00831 c00831 = new C00831(this.$watcher, continuation);
                c00831.L$0 = obj;
                return c00831;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                return ((C00831) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0036 A[RETURN] */
            /* JADX WARN: Removed duplicated region for block: B:12:0x0037  */
            /* JADX WARN: Removed duplicated region for block: B:15:0x0044  */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:12:0x0037 -> B:13:0x003c). Please report as a decompilation issue!!! */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r7) {
                /*
                    r6 = this;
                    java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                    int r1 = r6.label
                    r2 = 1
                    if (r1 == 0) goto L1e
                    if (r1 != r2) goto L16
                    java.lang.Object r1 = r6.L$0
                    androidx.compose.ui.input.pointer.AwaitPointerEventScope r1 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r1
                    kotlin.ResultKt.throwOnFailure(r7)
                    r3 = r1
                    r1 = r0
                    r0 = r6
                    goto L3c
                L16:
                    java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                    java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                    r7.<init>(r0)
                    throw r7
                L1e:
                    kotlin.ResultKt.throwOnFailure(r7)
                    java.lang.Object r7 = r6.L$0
                    androidx.compose.ui.input.pointer.AwaitPointerEventScope r7 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r7
                    r1 = r7
                    r7 = r6
                L27:
                    androidx.compose.ui.input.pointer.PointerEventPass r3 = androidx.compose.ui.input.pointer.PointerEventPass.Initial
                    r4 = r7
                    kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
                    r7.L$0 = r1
                    r7.label = r2
                    java.lang.Object r3 = r1.awaitPointerEvent(r3, r4)
                    if (r3 != r0) goto L37
                    return r0
                L37:
                    r5 = r0
                    r0 = r7
                    r7 = r3
                    r3 = r1
                    r1 = r5
                L3c:
                    androidx.compose.ui.input.pointer.PointerEvent r7 = (androidx.compose.ui.input.pointer.PointerEvent) r7
                    android.view.MotionEvent r7 = r7.getMotionEvent$ui_release()
                    if (r7 == 0) goto L49
                    kotlin.jvm.functions.Function1<android.view.MotionEvent, kotlin.Unit> r4 = r0.$watcher
                    r4.invoke(r7)
                L49:
                    r7 = r0
                    r0 = r1
                    r1 = r3
                    goto L27
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.input.pointer.PointerInteropFilter_androidKt.AnonymousClass1.C00831.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }
    }

    public static final Modifier motionEventSpy(Modifier modifier, Function1<? super MotionEvent, Unit> watcher) {
        Intrinsics.checkNotNullParameter(modifier, "<this>");
        Intrinsics.checkNotNullParameter(watcher, "watcher");
        return SuspendingPointerInputFilterKt.pointerInput(modifier, watcher, new AnonymousClass1(watcher, null));
    }

    public static final Modifier pointerInteropFilter(Modifier modifier, final RequestDisallowInterceptTouchEvent requestDisallowInterceptTouchEvent, final Function1<? super MotionEvent, Boolean> onTouchEvent) {
        Intrinsics.checkNotNullParameter(modifier, "<this>");
        Intrinsics.checkNotNullParameter(onTouchEvent, "onTouchEvent");
        return ComposedModifierKt.composed(modifier, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.ui.input.pointer.PointerInteropFilter_androidKt$pointerInteropFilter$$inlined$debugInspectorInfo$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo inspectorInfo) {
                Intrinsics.checkNotNullParameter(inspectorInfo, "$this$null");
                inspectorInfo.setName("pointerInteropFilter");
                inspectorInfo.getProperties().set("requestDisallowInterceptTouchEvent", requestDisallowInterceptTouchEvent);
                inspectorInfo.getProperties().set("onTouchEvent", onTouchEvent);
            }
        } : InspectableValueKt.getNoInspectorInfo(), new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.ui.input.pointer.PointerInteropFilter_androidKt.pointerInteropFilter.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier2, Composer composer, Integer num) {
                return invoke(modifier2, composer, num.intValue());
            }

            public final Modifier invoke(Modifier composed, Composer composer, int i) {
                Intrinsics.checkNotNullParameter(composed, "$this$composed");
                composer.startReplaceableGroup(374375707);
                ComposerKt.sourceInformation(composer, "C78@3403L35:PointerInteropFilter.android.kt#a556rk");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(374375707, i, -1, "androidx.compose.ui.input.pointer.pointerInteropFilter.<anonymous> (PointerInteropFilter.android.kt:77)");
                }
                composer.startReplaceableGroup(-492369756);
                ComposerKt.sourceInformation(composer, "CC(remember):Composables.kt#9igjgp");
                Object objRememberedValue = composer.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new PointerInteropFilter();
                    composer.updateRememberedValue(objRememberedValue);
                }
                composer.endReplaceableGroup();
                PointerInteropFilter pointerInteropFilter = (PointerInteropFilter) objRememberedValue;
                pointerInteropFilter.setOnTouchEvent(onTouchEvent);
                pointerInteropFilter.setRequestDisallowInterceptTouchEvent(requestDisallowInterceptTouchEvent);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer.endReplaceableGroup();
                return pointerInteropFilter;
            }
        });
    }
}
