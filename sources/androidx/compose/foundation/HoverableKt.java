package androidx.compose.foundation;

import androidx.compose.foundation.HoverableKt;
import androidx.compose.foundation.interaction.HoverInteraction;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: Hoverable.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, d2 = {"hoverable", "Landroidx/compose/ui/Modifier;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", ViewProps.ENABLED, "", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class HoverableKt {
    public static /* synthetic */ Modifier hoverable$default(Modifier modifier, MutableInteractionSource mutableInteractionSource, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        return hoverable(modifier, mutableInteractionSource, z);
    }

    /* compiled from: Hoverable.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u000b¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "Landroidx/compose/ui/Modifier;", "invoke", "(Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/Modifier;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    /* renamed from: androidx.compose.foundation.HoverableKt$hoverable$2, reason: invalid class name */
    static final class AnonymousClass2 extends Lambda implements Function3<Modifier, Composer, Integer, Modifier> {
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ MutableInteractionSource $interactionSource;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(MutableInteractionSource mutableInteractionSource, boolean z) {
            super(3);
            this.$interactionSource = mutableInteractionSource;
            this.$enabled = z;
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier, Composer composer, Integer num) {
            return invoke(modifier, composer, num.intValue());
        }

        public final Modifier invoke(Modifier composed, Composer composer, int i) {
            Modifier.Companion companionPointerInput;
            Intrinsics.checkNotNullParameter(composed, "$this$composed");
            composer.startReplaceableGroup(1294013553);
            ComposerKt.sourceInformation(composer, "C55@2170L24,56@2223L58,82@3026L43,82@2990L79,85@3098L64,85@3074L88:Hoverable.kt#71ulvw");
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1294013553, i, -1, "androidx.compose.foundation.hoverable.<anonymous> (Hoverable.kt:54)");
            }
            composer.startReplaceableGroup(773894976);
            ComposerKt.sourceInformation(composer, "CC(rememberCoroutineScope)476@19869L144:Effects.kt#9igjgp");
            composer.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation(composer, "CC(remember):Composables.kt#9igjgp");
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object compositionScopedCoroutineScopeCanceller = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composer));
                composer.updateRememberedValue(compositionScopedCoroutineScopeCanceller);
                objRememberedValue = compositionScopedCoroutineScopeCanceller;
            }
            composer.endReplaceableGroup();
            CoroutineScope coroutineScope = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
            composer.endReplaceableGroup();
            composer.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation(composer, "CC(remember):Composables.kt#9igjgp");
            Object objRememberedValue2 = composer.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                composer.updateRememberedValue(objRememberedValue2);
            }
            composer.endReplaceableGroup();
            final MutableState mutableState = (MutableState) objRememberedValue2;
            final MutableInteractionSource mutableInteractionSource = this.$interactionSource;
            composer.startReplaceableGroup(511388516);
            ComposerKt.sourceInformation(composer, "CC(remember)P(1,2):Composables.kt#9igjgp");
            boolean zChanged = composer.changed(mutableState) | composer.changed(mutableInteractionSource);
            Object objRememberedValue3 = composer.rememberedValue();
            if (zChanged || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = (Function1) new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.foundation.HoverableKt$hoverable$2$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final DisposableEffectResult invoke(DisposableEffectScope DisposableEffect) {
                        Intrinsics.checkNotNullParameter(DisposableEffect, "$this$DisposableEffect");
                        final MutableState<HoverInteraction.Enter> mutableState2 = mutableState;
                        final MutableInteractionSource mutableInteractionSource2 = mutableInteractionSource;
                        return new DisposableEffectResult() { // from class: androidx.compose.foundation.HoverableKt$hoverable$2$1$1$invoke$$inlined$onDispose$1
                            @Override // androidx.compose.runtime.DisposableEffectResult
                            public void dispose() {
                                HoverableKt.AnonymousClass2.invoke$tryEmitExit(mutableState2, mutableInteractionSource2);
                            }
                        };
                    }
                };
                composer.updateRememberedValue(objRememberedValue3);
            }
            composer.endReplaceableGroup();
            EffectsKt.DisposableEffect(mutableInteractionSource, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue3, composer, 0);
            Boolean boolValueOf = Boolean.valueOf(this.$enabled);
            Object objValueOf = Boolean.valueOf(this.$enabled);
            MutableInteractionSource mutableInteractionSource2 = this.$interactionSource;
            boolean z = this.$enabled;
            composer.startReplaceableGroup(1618982084);
            ComposerKt.sourceInformation(composer, "CC(remember)P(1,2,3):Composables.kt#9igjgp");
            boolean zChanged2 = composer.changed(objValueOf) | composer.changed(mutableState) | composer.changed(mutableInteractionSource2);
            Object objRememberedValue4 = composer.rememberedValue();
            if (zChanged2 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue4 = (Function2) new HoverableKt$hoverable$2$2$1(z, mutableState, mutableInteractionSource2, null);
                composer.updateRememberedValue(objRememberedValue4);
            }
            composer.endReplaceableGroup();
            EffectsKt.LaunchedEffect(boolValueOf, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue4, composer, 64);
            if (this.$enabled) {
                companionPointerInput = SuspendingPointerInputFilterKt.pointerInput(Modifier.INSTANCE, this.$interactionSource, new AnonymousClass3(coroutineScope, this.$interactionSource, mutableState, null));
            } else {
                companionPointerInput = Modifier.INSTANCE;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composer.endReplaceableGroup();
            return companionPointerInput;
        }

        private static final HoverInteraction.Enter invoke$lambda$1(MutableState<HoverInteraction.Enter> mutableState) {
            return mutableState.getValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public static final java.lang.Object invoke$emitEnter(androidx.compose.foundation.interaction.MutableInteractionSource r4, androidx.compose.runtime.MutableState<androidx.compose.foundation.interaction.HoverInteraction.Enter> r5, kotlin.coroutines.Continuation<? super kotlin.Unit> r6) {
            /*
                boolean r0 = r6 instanceof androidx.compose.foundation.HoverableKt$hoverable$2$invoke$emitEnter$1
                if (r0 == 0) goto L14
                r0 = r6
                androidx.compose.foundation.HoverableKt$hoverable$2$invoke$emitEnter$1 r0 = (androidx.compose.foundation.HoverableKt$hoverable$2$invoke$emitEnter$1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r1 = r1 & r2
                if (r1 == 0) goto L14
                int r6 = r0.label
                int r6 = r6 - r2
                r0.label = r6
                goto L19
            L14:
                androidx.compose.foundation.HoverableKt$hoverable$2$invoke$emitEnter$1 r0 = new androidx.compose.foundation.HoverableKt$hoverable$2$invoke$emitEnter$1
                r0.<init>(r6)
            L19:
                java.lang.Object r6 = r0.result
                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r2 = r0.label
                r3 = 1
                if (r2 == 0) goto L3a
                if (r2 != r3) goto L32
                java.lang.Object r4 = r0.L$1
                androidx.compose.foundation.interaction.HoverInteraction$Enter r4 = (androidx.compose.foundation.interaction.HoverInteraction.Enter) r4
                java.lang.Object r5 = r0.L$0
                androidx.compose.runtime.MutableState r5 = (androidx.compose.runtime.MutableState) r5
                kotlin.ResultKt.throwOnFailure(r6)
                goto L59
            L32:
                java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                r4.<init>(r5)
                throw r4
            L3a:
                kotlin.ResultKt.throwOnFailure(r6)
                androidx.compose.foundation.interaction.HoverInteraction$Enter r6 = invoke$lambda$1(r5)
                if (r6 != 0) goto L5c
                androidx.compose.foundation.interaction.HoverInteraction$Enter r6 = new androidx.compose.foundation.interaction.HoverInteraction$Enter
                r6.<init>()
                r2 = r6
                androidx.compose.foundation.interaction.Interaction r2 = (androidx.compose.foundation.interaction.Interaction) r2
                r0.L$0 = r5
                r0.L$1 = r6
                r0.label = r3
                java.lang.Object r4 = r4.emit(r2, r0)
                if (r4 != r1) goto L58
                return r1
            L58:
                r4 = r6
            L59:
                invoke$lambda$2(r5, r4)
            L5c:
                kotlin.Unit r4 = kotlin.Unit.INSTANCE
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.HoverableKt.AnonymousClass2.invoke$emitEnter(androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.runtime.MutableState, kotlin.coroutines.Continuation):java.lang.Object");
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public static final java.lang.Object invoke$emitExit(androidx.compose.runtime.MutableState<androidx.compose.foundation.interaction.HoverInteraction.Enter> r4, androidx.compose.foundation.interaction.MutableInteractionSource r5, kotlin.coroutines.Continuation<? super kotlin.Unit> r6) {
            /*
                boolean r0 = r6 instanceof androidx.compose.foundation.HoverableKt$hoverable$2$invoke$emitExit$1
                if (r0 == 0) goto L14
                r0 = r6
                androidx.compose.foundation.HoverableKt$hoverable$2$invoke$emitExit$1 r0 = (androidx.compose.foundation.HoverableKt$hoverable$2$invoke$emitExit$1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r1 = r1 & r2
                if (r1 == 0) goto L14
                int r6 = r0.label
                int r6 = r6 - r2
                r0.label = r6
                goto L19
            L14:
                androidx.compose.foundation.HoverableKt$hoverable$2$invoke$emitExit$1 r0 = new androidx.compose.foundation.HoverableKt$hoverable$2$invoke$emitExit$1
                r0.<init>(r6)
            L19:
                java.lang.Object r6 = r0.result
                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r2 = r0.label
                r3 = 1
                if (r2 == 0) goto L36
                if (r2 != r3) goto L2e
                java.lang.Object r4 = r0.L$0
                androidx.compose.runtime.MutableState r4 = (androidx.compose.runtime.MutableState) r4
                kotlin.ResultKt.throwOnFailure(r6)
                goto L51
            L2e:
                java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                r4.<init>(r5)
                throw r4
            L36:
                kotlin.ResultKt.throwOnFailure(r6)
                androidx.compose.foundation.interaction.HoverInteraction$Enter r6 = invoke$lambda$1(r4)
                if (r6 == 0) goto L55
                androidx.compose.foundation.interaction.HoverInteraction$Exit r2 = new androidx.compose.foundation.interaction.HoverInteraction$Exit
                r2.<init>(r6)
                androidx.compose.foundation.interaction.Interaction r2 = (androidx.compose.foundation.interaction.Interaction) r2
                r0.L$0 = r4
                r0.label = r3
                java.lang.Object r5 = r5.emit(r2, r0)
                if (r5 != r1) goto L51
                return r1
            L51:
                r5 = 0
                invoke$lambda$2(r4, r5)
            L55:
                kotlin.Unit r4 = kotlin.Unit.INSTANCE
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.HoverableKt.AnonymousClass2.invoke$emitExit(androidx.compose.runtime.MutableState, androidx.compose.foundation.interaction.MutableInteractionSource, kotlin.coroutines.Continuation):java.lang.Object");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$tryEmitExit(MutableState<HoverInteraction.Enter> mutableState, MutableInteractionSource mutableInteractionSource) {
            HoverInteraction.Enter enterInvoke$lambda$1 = invoke$lambda$1(mutableState);
            if (enterInvoke$lambda$1 != null) {
                mutableInteractionSource.tryEmit(new HoverInteraction.Exit(enterInvoke$lambda$1));
                mutableState.setValue(null);
            }
        }

        /* compiled from: Hoverable.kt */
        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.HoverableKt$hoverable$2$3", f = "Hoverable.kt", i = {}, l = {102}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: androidx.compose.foundation.HoverableKt$hoverable$2$3, reason: invalid class name */
        static final class AnonymousClass3 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ MutableState<HoverInteraction.Enter> $hoverInteraction$delegate;
            final /* synthetic */ MutableInteractionSource $interactionSource;
            final /* synthetic */ CoroutineScope $scope;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass3(CoroutineScope coroutineScope, MutableInteractionSource mutableInteractionSource, MutableState<HoverInteraction.Enter> mutableState, Continuation<? super AnonymousClass3> continuation) {
                super(2, continuation);
                this.$scope = coroutineScope;
                this.$interactionSource = mutableInteractionSource;
                this.$hoverInteraction$delegate = mutableState;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                AnonymousClass3 anonymousClass3 = new AnonymousClass3(this.$scope, this.$interactionSource, this.$hoverInteraction$delegate, continuation);
                anonymousClass3.L$0 = obj;
                return anonymousClass3;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass3) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* compiled from: Hoverable.kt */
            @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.foundation.HoverableKt$hoverable$2$3$1", f = "Hoverable.kt", i = {0}, l = {104}, m = "invokeSuspend", n = {"$this$awaitPointerEventScope"}, s = {"L$0"})
            /* renamed from: androidx.compose.foundation.HoverableKt$hoverable$2$3$1, reason: invalid class name */
            static final class AnonymousClass1 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ CoroutineContext $currentContext;
                final /* synthetic */ MutableState<HoverInteraction.Enter> $hoverInteraction$delegate;
                final /* synthetic */ MutableInteractionSource $interactionSource;
                final /* synthetic */ CoroutineScope $scope;
                private /* synthetic */ Object L$0;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(CoroutineContext coroutineContext, CoroutineScope coroutineScope, MutableInteractionSource mutableInteractionSource, MutableState<HoverInteraction.Enter> mutableState, Continuation<? super AnonymousClass1> continuation) {
                    super(2, continuation);
                    this.$currentContext = coroutineContext;
                    this.$scope = coroutineScope;
                    this.$interactionSource = mutableInteractionSource;
                    this.$hoverInteraction$delegate = mutableState;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$currentContext, this.$scope, this.$interactionSource, this.$hoverInteraction$delegate, continuation);
                    anonymousClass1.L$0 = obj;
                    return anonymousClass1;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                    return ((AnonymousClass1) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                /* compiled from: Hoverable.kt */
                @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
                @DebugMetadata(c = "androidx.compose.foundation.HoverableKt$hoverable$2$3$1$1", f = "Hoverable.kt", i = {}, l = {106}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: androidx.compose.foundation.HoverableKt$hoverable$2$3$1$1, reason: invalid class name and collision with other inner class name */
                static final class C00101 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    final /* synthetic */ MutableState<HoverInteraction.Enter> $hoverInteraction$delegate;
                    final /* synthetic */ MutableInteractionSource $interactionSource;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    C00101(MutableInteractionSource mutableInteractionSource, MutableState<HoverInteraction.Enter> mutableState, Continuation<? super C00101> continuation) {
                        super(2, continuation);
                        this.$interactionSource = mutableInteractionSource;
                        this.$hoverInteraction$delegate = mutableState;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        return new C00101(this.$interactionSource, this.$hoverInteraction$delegate, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((C00101) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        int i = this.label;
                        if (i == 0) {
                            ResultKt.throwOnFailure(obj);
                            this.label = 1;
                            if (AnonymousClass2.invoke$emitEnter(this.$interactionSource, this.$hoverInteraction$delegate, this) == coroutine_suspended) {
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

                /* compiled from: Hoverable.kt */
                @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
                @DebugMetadata(c = "androidx.compose.foundation.HoverableKt$hoverable$2$3$1$2", f = "Hoverable.kt", i = {}, l = {107}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: androidx.compose.foundation.HoverableKt$hoverable$2$3$1$2, reason: invalid class name and collision with other inner class name */
                static final class C00112 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    final /* synthetic */ MutableState<HoverInteraction.Enter> $hoverInteraction$delegate;
                    final /* synthetic */ MutableInteractionSource $interactionSource;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    C00112(MutableState<HoverInteraction.Enter> mutableState, MutableInteractionSource mutableInteractionSource, Continuation<? super C00112> continuation) {
                        super(2, continuation);
                        this.$hoverInteraction$delegate = mutableState;
                        this.$interactionSource = mutableInteractionSource;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        return new C00112(this.$hoverInteraction$delegate, this.$interactionSource, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((C00112) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        int i = this.label;
                        if (i == 0) {
                            ResultKt.throwOnFailure(obj);
                            this.label = 1;
                            if (AnonymousClass2.invoke$emitExit(this.$hoverInteraction$delegate, this.$interactionSource, this) == coroutine_suspended) {
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

                /* JADX WARN: Removed duplicated region for block: B:11:0x0030  */
                /* JADX WARN: Removed duplicated region for block: B:17:0x0055  */
                /* JADX WARN: Removed duplicated region for block: B:18:0x006b  */
                /* JADX WARN: Removed duplicated region for block: B:22:0x0090  */
                /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:14:0x003e -> B:15:0x0043). Please report as a decompilation issue!!! */
                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object invokeSuspend(java.lang.Object r14) {
                    /*
                        r13 = this;
                        java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                        int r1 = r13.label
                        r2 = 1
                        r3 = 0
                        if (r1 == 0) goto L1f
                        if (r1 != r2) goto L17
                        java.lang.Object r1 = r13.L$0
                        androidx.compose.ui.input.pointer.AwaitPointerEventScope r1 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r1
                        kotlin.ResultKt.throwOnFailure(r14)
                        r4 = r1
                        r1 = r0
                        r0 = r13
                        goto L43
                    L17:
                        java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
                        java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                        r14.<init>(r0)
                        throw r14
                    L1f:
                        kotlin.ResultKt.throwOnFailure(r14)
                        java.lang.Object r14 = r13.L$0
                        androidx.compose.ui.input.pointer.AwaitPointerEventScope r14 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r14
                        r1 = r14
                        r14 = r13
                    L28:
                        kotlin.coroutines.CoroutineContext r4 = r14.$currentContext
                        boolean r4 = kotlinx.coroutines.JobKt.isActive(r4)
                        if (r4 == 0) goto L90
                        r4 = r14
                        kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
                        r14.L$0 = r1
                        r14.label = r2
                        java.lang.Object r4 = androidx.compose.ui.input.pointer.AwaitPointerEventScope.awaitPointerEvent$default(r1, r3, r4, r2, r3)
                        if (r4 != r0) goto L3e
                        return r0
                    L3e:
                        r12 = r0
                        r0 = r14
                        r14 = r4
                        r4 = r1
                        r1 = r12
                    L43:
                        androidx.compose.ui.input.pointer.PointerEvent r14 = (androidx.compose.ui.input.pointer.PointerEvent) r14
                        int r14 = r14.getType()
                        androidx.compose.ui.input.pointer.PointerEventType$Companion r5 = androidx.compose.ui.input.pointer.PointerEventType.INSTANCE
                        int r5 = r5.m3234getEnter7fucELk()
                        boolean r5 = androidx.compose.ui.input.pointer.PointerEventType.m3230equalsimpl0(r14, r5)
                        if (r5 == 0) goto L6b
                        kotlinx.coroutines.CoroutineScope r6 = r0.$scope
                        r7 = 0
                        r8 = 0
                        androidx.compose.foundation.HoverableKt$hoverable$2$3$1$1 r14 = new androidx.compose.foundation.HoverableKt$hoverable$2$3$1$1
                        androidx.compose.foundation.interaction.MutableInteractionSource r5 = r0.$interactionSource
                        androidx.compose.runtime.MutableState<androidx.compose.foundation.interaction.HoverInteraction$Enter> r9 = r0.$hoverInteraction$delegate
                        r14.<init>(r5, r9, r3)
                        r9 = r14
                        kotlin.jvm.functions.Function2 r9 = (kotlin.jvm.functions.Function2) r9
                        r10 = 3
                        r11 = 0
                        kotlinx.coroutines.BuildersKt.launch$default(r6, r7, r8, r9, r10, r11)
                        goto L8c
                    L6b:
                        androidx.compose.ui.input.pointer.PointerEventType$Companion r5 = androidx.compose.ui.input.pointer.PointerEventType.INSTANCE
                        int r5 = r5.m3235getExit7fucELk()
                        boolean r14 = androidx.compose.ui.input.pointer.PointerEventType.m3230equalsimpl0(r14, r5)
                        if (r14 == 0) goto L8c
                        kotlinx.coroutines.CoroutineScope r5 = r0.$scope
                        r6 = 0
                        r7 = 0
                        androidx.compose.foundation.HoverableKt$hoverable$2$3$1$2 r14 = new androidx.compose.foundation.HoverableKt$hoverable$2$3$1$2
                        androidx.compose.runtime.MutableState<androidx.compose.foundation.interaction.HoverInteraction$Enter> r8 = r0.$hoverInteraction$delegate
                        androidx.compose.foundation.interaction.MutableInteractionSource r9 = r0.$interactionSource
                        r14.<init>(r8, r9, r3)
                        r8 = r14
                        kotlin.jvm.functions.Function2 r8 = (kotlin.jvm.functions.Function2) r8
                        r9 = 3
                        r10 = 0
                        kotlinx.coroutines.BuildersKt.launch$default(r5, r6, r7, r8, r9, r10)
                    L8c:
                        r14 = r0
                        r0 = r1
                        r1 = r4
                        goto L28
                    L90:
                        kotlin.Unit r14 = kotlin.Unit.INSTANCE
                        return r14
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.HoverableKt.AnonymousClass2.AnonymousClass3.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
                }
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    this.label = 1;
                    if (((PointerInputScope) this.L$0).awaitPointerEventScope(new AnonymousClass1(getContext(), this.$scope, this.$interactionSource, this.$hoverInteraction$delegate, null), this) == coroutine_suspended) {
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

    public static final Modifier hoverable(Modifier modifier, final MutableInteractionSource interactionSource, final boolean z) {
        Intrinsics.checkNotNullParameter(modifier, "<this>");
        Intrinsics.checkNotNullParameter(interactionSource, "interactionSource");
        return ComposedModifierKt.composed(modifier, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.HoverableKt$hoverable$$inlined$debugInspectorInfo$1
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
                inspectorInfo.setName("hoverable");
                inspectorInfo.getProperties().set("interactionSource", interactionSource);
                inspectorInfo.getProperties().set(ViewProps.ENABLED, Boolean.valueOf(z));
            }
        } : InspectableValueKt.getNoInspectorInfo(), new AnonymousClass2(interactionSource, z));
    }
}
