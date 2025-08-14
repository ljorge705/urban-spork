package androidx.compose.foundation;

import androidx.compose.foundation.gestures.PressGestureScope;
import androidx.compose.foundation.gestures.ScrollableKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.interaction.PressInteraction;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.key.Key;
import androidx.compose.ui.input.key.KeyEvent;
import androidx.compose.ui.input.key.KeyInputModifierKt;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.modifier.ModifierLocalConsumer;
import androidx.compose.ui.modifier.ModifierLocalReadScope;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import com.facebook.react.uimanager.ViewProps;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

/* compiled from: Clickable.kt */
@Metadata(d1 = {"\u0000d\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a<\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00052\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00060\bH\u0001ø\u0001\u0000¢\u0006\u0002\u0010\n\u001aW\u0010\u000b\u001a\u00020\f*\u00020\f2\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00010\u0016ø\u0001\u0001ø\u0001\u0000¢\u0006\u0002\b\u0017\u001aE\u0010\u000b\u001a\u00020\f*\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00010\u0016ø\u0001\u0001ø\u0001\u0000¢\u0006\u0002\b\u0018\u001a\u0089\u0001\u0010\u0019\u001a\u00020\f*\u00020\f2\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00122\u0010\b\u0002\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00162\u0010\b\u0002\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00162\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00010\u0016H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0002\b\u001d\u001aw\u0010\u0019\u001a\u00020\f*\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00122\u0010\b\u0002\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00162\u0010\b\u0002\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00162\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00010\u0016H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0002\b\u001e\u001a©\u0001\u0010\u001f\u001a\u00020\f*\u00020\f2\u0006\u0010 \u001a\u00020\f2\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010!\u001a\u00020\"2\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00060\b2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020%0$2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00122\u0010\b\u0002\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00162\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00010\u0016H\u0000ø\u0001\u0001ø\u0001\u0000¢\u0006\u0002\b&\u001aQ\u0010'\u001a\u00020\u0001*\u00020(2\u0006\u0010)\u001a\u00020%2\u0006\u0010\u0002\u001a\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00052\u0012\u0010*\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u00160$H\u0080@ø\u0001\u0001ø\u0001\u0000ø\u0001\u0000¢\u0006\u0004\b+\u0010,\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006-"}, d2 = {"PressedInteractionSourceDisposableEffect", "", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "pressedInteraction", "Landroidx/compose/runtime/MutableState;", "Landroidx/compose/foundation/interaction/PressInteraction$Press;", "currentKeyPressInteractions", "", "Landroidx/compose/ui/input/key/Key;", "(Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/MutableState;Ljava/util/Map;Landroidx/compose/runtime/Composer;I)V", "clickable", "Landroidx/compose/ui/Modifier;", "indication", "Landroidx/compose/foundation/Indication;", ViewProps.ENABLED, "", "onClickLabel", "", ViewProps.ROLE, "Landroidx/compose/ui/semantics/Role;", "onClick", "Lkotlin/Function0;", "clickable-O2vRcR0", "clickable-XHw0xAI", "combinedClickable", "onLongClickLabel", "onLongClick", "onDoubleClick", "combinedClickable-XVZzFYc", "combinedClickable-cJG_KMw", "genericClickableWithoutGesture", "gestureModifiers", "indicationScope", "Lkotlinx/coroutines/CoroutineScope;", "keyClickOffset", "Landroidx/compose/runtime/State;", "Landroidx/compose/ui/geometry/Offset;", "genericClickableWithoutGesture-bdNGguI", "handlePressInteraction", "Landroidx/compose/foundation/gestures/PressGestureScope;", "pressPoint", "delayPressInteraction", "handlePressInteraction-EPk0efs", "(Landroidx/compose/foundation/gestures/PressGestureScope;JLandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/MutableState;Landroidx/compose/runtime/State;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ClickableKt {
    /* renamed from: clickable-XHw0xAI$default, reason: not valid java name */
    public static /* synthetic */ Modifier m451clickableXHw0xAI$default(Modifier modifier, boolean z, String str, Role role, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        if ((i & 2) != 0) {
            str = null;
        }
        if ((i & 4) != 0) {
            role = null;
        }
        return m450clickableXHw0xAI(modifier, z, str, role, function0);
    }

    /* renamed from: clickable-O2vRcR0$default, reason: not valid java name */
    public static /* synthetic */ Modifier m449clickableO2vRcR0$default(Modifier modifier, MutableInteractionSource mutableInteractionSource, Indication indication, boolean z, String str, Role role, Function0 function0, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        return m448clickableO2vRcR0(modifier, mutableInteractionSource, indication, z, (i & 8) != 0 ? null : str, (i & 16) != 0 ? null : role, function0);
    }

    public static final void PressedInteractionSourceDisposableEffect(final MutableInteractionSource interactionSource, final MutableState<PressInteraction.Press> pressedInteraction, final Map<Key, PressInteraction.Press> currentKeyPressInteractions, Composer composer, final int i) {
        Intrinsics.checkNotNullParameter(interactionSource, "interactionSource");
        Intrinsics.checkNotNullParameter(pressedInteraction, "pressedInteraction");
        Intrinsics.checkNotNullParameter(currentKeyPressInteractions, "currentKeyPressInteractions");
        Composer composerStartRestartGroup = composer.startRestartGroup(1297229208);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(PressedInteractionSourceDisposableEffect)P(1,2)414@17663L504:Clickable.kt#71ulvw");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1297229208, i, -1, "androidx.compose.foundation.PressedInteractionSourceDisposableEffect (Clickable.kt:409)");
        }
        EffectsKt.DisposableEffect(interactionSource, new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.foundation.ClickableKt.PressedInteractionSourceDisposableEffect.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final DisposableEffectResult invoke(DisposableEffectScope DisposableEffect) {
                Intrinsics.checkNotNullParameter(DisposableEffect, "$this$DisposableEffect");
                final MutableState<PressInteraction.Press> mutableState = pressedInteraction;
                final Map<Key, PressInteraction.Press> map = currentKeyPressInteractions;
                final MutableInteractionSource mutableInteractionSource = interactionSource;
                return new DisposableEffectResult() { // from class: androidx.compose.foundation.ClickableKt$PressedInteractionSourceDisposableEffect$1$invoke$$inlined$onDispose$1
                    @Override // androidx.compose.runtime.DisposableEffectResult
                    public void dispose() {
                        PressInteraction.Press press = (PressInteraction.Press) mutableState.getValue();
                        if (press != null) {
                            mutableInteractionSource.tryEmit(new PressInteraction.Cancel(press));
                            mutableState.setValue(null);
                        }
                        Iterator it = map.values().iterator();
                        while (it.hasNext()) {
                            mutableInteractionSource.tryEmit(new PressInteraction.Cancel((PressInteraction.Press) it.next()));
                        }
                        map.clear();
                    }
                };
            }
        }, composerStartRestartGroup, i & 14);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.ClickableKt.PressedInteractionSourceDisposableEffect.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                invoke(composer2, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer2, int i2) {
                ClickableKt.PressedInteractionSourceDisposableEffect(interactionSource, pressedInteraction, currentKeyPressInteractions, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
            }
        });
    }

    /* renamed from: handlePressInteraction-EPk0efs, reason: not valid java name */
    public static final Object m458handlePressInteractionEPk0efs(PressGestureScope pressGestureScope, long j, MutableInteractionSource mutableInteractionSource, MutableState<PressInteraction.Press> mutableState, State<? extends Function0<Boolean>> state, Continuation<? super Unit> continuation) {
        Object objCoroutineScope = CoroutineScopeKt.coroutineScope(new ClickableKt$handlePressInteraction$2(pressGestureScope, j, mutableInteractionSource, mutableState, state, null), continuation);
        return objCoroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCoroutineScope : Unit.INSTANCE;
    }

    private static final Modifier genericClickableWithoutGesture_bdNGguI$clickSemantics(Modifier modifier, final Role role, final String str, final Function0<Unit> function0, final String str2, final boolean z, final Function0<Unit> function02) {
        return SemanticsModifierKt.semantics(modifier, true, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.foundation.ClickableKt$genericClickableWithoutGesture$clickSemantics$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                invoke2(semanticsPropertyReceiver);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SemanticsPropertyReceiver semantics) {
                Intrinsics.checkNotNullParameter(semantics, "$this$semantics");
                Role role2 = role;
                if (role2 != null) {
                    SemanticsPropertiesKt.m3766setRolekuIjeqM(semantics, role2.getValue());
                }
                String str3 = str;
                final Function0<Unit> function03 = function02;
                SemanticsPropertiesKt.onClick(semantics, str3, new Function0<Boolean>() { // from class: androidx.compose.foundation.ClickableKt$genericClickableWithoutGesture$clickSemantics$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final Boolean invoke() {
                        function03.invoke();
                        return true;
                    }
                });
                if (function0 != null) {
                    String str4 = str2;
                    final Function0<Unit> function04 = function0;
                    SemanticsPropertiesKt.onLongClick(semantics, str4, new Function0<Boolean>() { // from class: androidx.compose.foundation.ClickableKt$genericClickableWithoutGesture$clickSemantics$1.2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Boolean invoke() {
                            function04.invoke();
                            return true;
                        }
                    });
                }
                if (z) {
                    return;
                }
                SemanticsPropertiesKt.disabled(semantics);
            }
        });
    }

    private static final Modifier genericClickableWithoutGesture_bdNGguI$detectPressAndClickFromKey(Modifier modifier, final boolean z, final Map<Key, PressInteraction.Press> map, final State<Offset> state, final CoroutineScope coroutineScope, final Function0<Unit> function0, final MutableInteractionSource mutableInteractionSource) {
        return KeyInputModifierKt.onKeyEvent(modifier, new Function1<KeyEvent, Boolean>() { // from class: androidx.compose.foundation.ClickableKt$genericClickableWithoutGesture$detectPressAndClickFromKey$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(KeyEvent keyEvent) {
                return m465invokeZmokQxo(keyEvent.m3134unboximpl());
            }

            /* JADX WARN: Removed duplicated region for block: B:17:0x0089  */
            /* renamed from: invoke-ZmokQxo, reason: not valid java name */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Boolean m465invokeZmokQxo(android.view.KeyEvent r11) {
                /*
                    r10 = this;
                    java.lang.String r0 = "keyEvent"
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
                    boolean r0 = r1
                    r1 = 1
                    r2 = 0
                    r3 = 0
                    if (r0 == 0) goto L54
                    boolean r0 = androidx.compose.foundation.Clickable_androidKt.m468isPressZmokQxo(r11)
                    if (r0 == 0) goto L54
                    java.util.Map<androidx.compose.ui.input.key.Key, androidx.compose.foundation.interaction.PressInteraction$Press> r0 = r2
                    long r4 = androidx.compose.ui.input.key.KeyEvent_androidKt.m3145getKeyZmokQxo(r11)
                    androidx.compose.ui.input.key.Key r4 = androidx.compose.ui.input.key.Key.m2547boximpl(r4)
                    boolean r0 = r0.containsKey(r4)
                    if (r0 != 0) goto L89
                    androidx.compose.foundation.interaction.PressInteraction$Press r0 = new androidx.compose.foundation.interaction.PressInteraction$Press
                    androidx.compose.runtime.State<androidx.compose.ui.geometry.Offset> r2 = r3
                    java.lang.Object r2 = r2.getValue()
                    androidx.compose.ui.geometry.Offset r2 = (androidx.compose.ui.geometry.Offset) r2
                    long r4 = r2.getPackedValue()
                    r0.<init>(r4, r3)
                    java.util.Map<androidx.compose.ui.input.key.Key, androidx.compose.foundation.interaction.PressInteraction$Press> r2 = r2
                    long r4 = androidx.compose.ui.input.key.KeyEvent_androidKt.m3145getKeyZmokQxo(r11)
                    androidx.compose.ui.input.key.Key r11 = androidx.compose.ui.input.key.Key.m2547boximpl(r4)
                    r2.put(r11, r0)
                    kotlinx.coroutines.CoroutineScope r4 = r4
                    r5 = 0
                    r6 = 0
                    androidx.compose.foundation.ClickableKt$genericClickableWithoutGesture$detectPressAndClickFromKey$1$1 r11 = new androidx.compose.foundation.ClickableKt$genericClickableWithoutGesture$detectPressAndClickFromKey$1$1
                    androidx.compose.foundation.interaction.MutableInteractionSource r2 = r6
                    r11.<init>(r2, r0, r3)
                    r7 = r11
                    kotlin.jvm.functions.Function2 r7 = (kotlin.jvm.functions.Function2) r7
                    r8 = 3
                    r9 = 0
                    kotlinx.coroutines.BuildersKt.launch$default(r4, r5, r6, r7, r8, r9)
                    goto L8a
                L54:
                    boolean r0 = r1
                    if (r0 == 0) goto L89
                    boolean r0 = androidx.compose.foundation.Clickable_androidKt.m466isClickZmokQxo(r11)
                    if (r0 == 0) goto L89
                    java.util.Map<androidx.compose.ui.input.key.Key, androidx.compose.foundation.interaction.PressInteraction$Press> r0 = r2
                    long r4 = androidx.compose.ui.input.key.KeyEvent_androidKt.m3145getKeyZmokQxo(r11)
                    androidx.compose.ui.input.key.Key r11 = androidx.compose.ui.input.key.Key.m2547boximpl(r4)
                    java.lang.Object r11 = r0.remove(r11)
                    androidx.compose.foundation.interaction.PressInteraction$Press r11 = (androidx.compose.foundation.interaction.PressInteraction.Press) r11
                    if (r11 == 0) goto L83
                    kotlinx.coroutines.CoroutineScope r4 = r4
                    androidx.compose.foundation.interaction.MutableInteractionSource r0 = r6
                    r5 = 0
                    r6 = 0
                    androidx.compose.foundation.ClickableKt$genericClickableWithoutGesture$detectPressAndClickFromKey$1$2$1 r2 = new androidx.compose.foundation.ClickableKt$genericClickableWithoutGesture$detectPressAndClickFromKey$1$2$1
                    r2.<init>(r0, r11, r3)
                    r7 = r2
                    kotlin.jvm.functions.Function2 r7 = (kotlin.jvm.functions.Function2) r7
                    r8 = 3
                    r9 = 0
                    kotlinx.coroutines.BuildersKt.launch$default(r4, r5, r6, r7, r8, r9)
                L83:
                    kotlin.jvm.functions.Function0<kotlin.Unit> r11 = r5
                    r11.invoke()
                    goto L8a
                L89:
                    r1 = r2
                L8a:
                    java.lang.Boolean r11 = java.lang.Boolean.valueOf(r1)
                    return r11
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.ClickableKt$genericClickableWithoutGesture$detectPressAndClickFromKey$1.m465invokeZmokQxo(android.view.KeyEvent):java.lang.Boolean");
            }

            /* compiled from: Clickable.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.foundation.ClickableKt$genericClickableWithoutGesture$detectPressAndClickFromKey$1$1", f = "Clickable.kt", i = {}, l = {540}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: androidx.compose.foundation.ClickableKt$genericClickableWithoutGesture$detectPressAndClickFromKey$1$1, reason: invalid class name */
            static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ MutableInteractionSource $interactionSource;
                final /* synthetic */ PressInteraction.Press $press;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(MutableInteractionSource mutableInteractionSource, PressInteraction.Press press, Continuation<? super AnonymousClass1> continuation) {
                    super(2, continuation);
                    this.$interactionSource = mutableInteractionSource;
                    this.$press = press;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new AnonymousClass1(this.$interactionSource, this.$press, continuation);
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
                        if (this.$interactionSource.emit(this.$press, this) == coroutine_suspended) {
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
        });
    }

    /* renamed from: genericClickableWithoutGesture-bdNGguI, reason: not valid java name */
    public static final Modifier m456genericClickableWithoutGesturebdNGguI(Modifier genericClickableWithoutGesture, Modifier gestureModifiers, MutableInteractionSource interactionSource, Indication indication, CoroutineScope indicationScope, Map<Key, PressInteraction.Press> currentKeyPressInteractions, State<Offset> keyClickOffset, boolean z, String str, Role role, String str2, Function0<Unit> function0, Function0<Unit> onClick) {
        Intrinsics.checkNotNullParameter(genericClickableWithoutGesture, "$this$genericClickableWithoutGesture");
        Intrinsics.checkNotNullParameter(gestureModifiers, "gestureModifiers");
        Intrinsics.checkNotNullParameter(interactionSource, "interactionSource");
        Intrinsics.checkNotNullParameter(indicationScope, "indicationScope");
        Intrinsics.checkNotNullParameter(currentKeyPressInteractions, "currentKeyPressInteractions");
        Intrinsics.checkNotNullParameter(keyClickOffset, "keyClickOffset");
        Intrinsics.checkNotNullParameter(onClick, "onClick");
        return FocusableKt.focusableInNonTouchMode(HoverableKt.hoverable(IndicationKt.indication(genericClickableWithoutGesture_bdNGguI$detectPressAndClickFromKey(genericClickableWithoutGesture_bdNGguI$clickSemantics(genericClickableWithoutGesture, role, str, function0, str2, z, onClick), z, currentKeyPressInteractions, keyClickOffset, indicationScope, onClick, interactionSource), interactionSource, indication), interactionSource, z), z, interactionSource).then(gestureModifiers);
    }

    /* renamed from: clickable-XHw0xAI, reason: not valid java name */
    public static final Modifier m450clickableXHw0xAI(Modifier clickable, final boolean z, final String str, final Role role, final Function0<Unit> onClick) {
        Intrinsics.checkNotNullParameter(clickable, "$this$clickable");
        Intrinsics.checkNotNullParameter(onClick, "onClick");
        return ComposedModifierKt.composed(clickable, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.ClickableKt$clickable-XHw0xAI$$inlined$debugInspectorInfo$1
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
                inspectorInfo.setName("clickable");
                inspectorInfo.getProperties().set(ViewProps.ENABLED, Boolean.valueOf(z));
                inspectorInfo.getProperties().set("onClickLabel", str);
                inspectorInfo.getProperties().set(ViewProps.ROLE, role);
                inspectorInfo.getProperties().set("onClick", onClick);
            }
        } : InspectableValueKt.getNoInspectorInfo(), new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.foundation.ClickableKt$clickable$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier, Composer composer, Integer num) {
                return invoke(modifier, composer, num.intValue());
            }

            public final Modifier invoke(Modifier composed, Composer composer, int i) {
                Intrinsics.checkNotNullParameter(composed, "$this$composed");
                composer.startReplaceableGroup(-756081143);
                ComposerKt.sourceInformation(composer, "C98@4098L7,99@4135L39:Clickable.kt#71ulvw");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-756081143, i, -1, "androidx.compose.foundation.clickable.<anonymous> (Clickable.kt:92)");
                }
                Modifier.Companion companion = Modifier.INSTANCE;
                ProvidableCompositionLocal<Indication> localIndication = IndicationKt.getLocalIndication();
                ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume = composer.consume(localIndication);
                ComposerKt.sourceInformationMarkerEnd(composer);
                Indication indication = (Indication) objConsume;
                composer.startReplaceableGroup(-492369756);
                ComposerKt.sourceInformation(composer, "CC(remember):Composables.kt#9igjgp");
                Object objRememberedValue = composer.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                    composer.updateRememberedValue(objRememberedValue);
                }
                composer.endReplaceableGroup();
                Modifier modifierM448clickableO2vRcR0 = ClickableKt.m448clickableO2vRcR0(companion, (MutableInteractionSource) objRememberedValue, indication, z, str, role, onClick);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer.endReplaceableGroup();
                return modifierM448clickableO2vRcR0;
            }
        });
    }

    /* renamed from: clickable-O2vRcR0, reason: not valid java name */
    public static final Modifier m448clickableO2vRcR0(Modifier clickable, final MutableInteractionSource interactionSource, final Indication indication, final boolean z, final String str, final Role role, final Function0<Unit> onClick) {
        Intrinsics.checkNotNullParameter(clickable, "$this$clickable");
        Intrinsics.checkNotNullParameter(interactionSource, "interactionSource");
        Intrinsics.checkNotNullParameter(onClick, "onClick");
        return ComposedModifierKt.composed(clickable, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.ClickableKt$clickable-O2vRcR0$$inlined$debugInspectorInfo$1
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
                inspectorInfo.setName("clickable");
                inspectorInfo.getProperties().set(ViewProps.ENABLED, Boolean.valueOf(z));
                inspectorInfo.getProperties().set("onClickLabel", str);
                inspectorInfo.getProperties().set(ViewProps.ROLE, role);
                inspectorInfo.getProperties().set("onClick", onClick);
                inspectorInfo.getProperties().set("indication", indication);
                inspectorInfo.getProperties().set("interactionSource", interactionSource);
            }
        } : InspectableValueKt.getNoInspectorInfo(), new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.foundation.ClickableKt$clickable$4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier, Composer composer, Integer num) {
                return invoke(modifier, composer, num.intValue());
            }

            public final Modifier invoke(Modifier composed, Composer composer, int i) {
                Intrinsics.checkNotNullParameter(composed, "$this$composed");
                composer.startReplaceableGroup(92076020);
                ComposerKt.sourceInformation(composer, "C136@5787L29,137@5850L58,138@5951L56,146@6266L36,147@6350L33,148@6441L95,148@6420L116,151@6564L40,153@6678L550,171@7281L445,186@7960L24:Clickable.kt#71ulvw");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(92076020, i, -1, "androidx.compose.foundation.clickable.<anonymous> (Clickable.kt:135)");
                }
                State stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(onClick, composer, 0);
                composer.startReplaceableGroup(-492369756);
                ComposerKt.sourceInformation(composer, "CC(remember):Composables.kt#9igjgp");
                Object objRememberedValue = composer.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                    composer.updateRememberedValue(objRememberedValue);
                }
                composer.endReplaceableGroup();
                MutableState mutableState = (MutableState) objRememberedValue;
                composer.startReplaceableGroup(-492369756);
                ComposerKt.sourceInformation(composer, "CC(remember):Composables.kt#9igjgp");
                Object objRememberedValue2 = composer.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue2 = (Map) new LinkedHashMap();
                    composer.updateRememberedValue(objRememberedValue2);
                }
                composer.endReplaceableGroup();
                Map map = (Map) objRememberedValue2;
                composer.startReplaceableGroup(1841981561);
                ComposerKt.sourceInformation(composer, "140@6043L170");
                if (z) {
                    ClickableKt.PressedInteractionSourceDisposableEffect(interactionSource, mutableState, map, composer, 560);
                }
                composer.endReplaceableGroup();
                final Function0<Boolean> function0IsComposeRootInScrollableContainer = Clickable_androidKt.isComposeRootInScrollableContainer(composer, 0);
                composer.startReplaceableGroup(-492369756);
                ComposerKt.sourceInformation(composer, "CC(remember):Composables.kt#9igjgp");
                Object objRememberedValue3 = composer.rememberedValue();
                if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(true, null, 2, null);
                    composer.updateRememberedValue(objRememberedValue3);
                }
                composer.endReplaceableGroup();
                final MutableState mutableState2 = (MutableState) objRememberedValue3;
                composer.startReplaceableGroup(511388516);
                ComposerKt.sourceInformation(composer, "CC(remember)P(1,2):Composables.kt#9igjgp");
                boolean zChanged = composer.changed(mutableState2) | composer.changed(function0IsComposeRootInScrollableContainer);
                Object objRememberedValue4 = composer.rememberedValue();
                if (zChanged || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue4 = (Function0) new Function0<Boolean>() { // from class: androidx.compose.foundation.ClickableKt$clickable$4$delayPressInteraction$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Boolean invoke() {
                            return Boolean.valueOf(mutableState2.getValue().booleanValue() || function0IsComposeRootInScrollableContainer.invoke().booleanValue());
                        }
                    };
                    composer.updateRememberedValue(objRememberedValue4);
                }
                composer.endReplaceableGroup();
                State stateRememberUpdatedState2 = SnapshotStateKt.rememberUpdatedState(objRememberedValue4, composer, 0);
                composer.startReplaceableGroup(-492369756);
                ComposerKt.sourceInformation(composer, "CC(remember):Composables.kt#9igjgp");
                Object objRememberedValue5 = composer.rememberedValue();
                if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue5 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Offset.m1628boximpl(Offset.INSTANCE.m1655getZeroF1C5BW0()), null, 2, null);
                    composer.updateRememberedValue(objRememberedValue5);
                }
                composer.endReplaceableGroup();
                MutableState mutableState3 = (MutableState) objRememberedValue5;
                Modifier.Companion companion = Modifier.INSTANCE;
                MutableInteractionSource mutableInteractionSource = interactionSource;
                Boolean boolValueOf = Boolean.valueOf(z);
                Boolean boolValueOf2 = Boolean.valueOf(z);
                MutableInteractionSource mutableInteractionSource2 = interactionSource;
                Object[] objArr = {mutableState3, boolValueOf2, mutableInteractionSource2, mutableState, stateRememberUpdatedState2, stateRememberUpdatedState};
                boolean z2 = z;
                composer.startReplaceableGroup(-568225417);
                ComposerKt.sourceInformation(composer, "CC(remember)P(1):Composables.kt#9igjgp");
                boolean zChanged2 = false;
                for (int i2 = 0; i2 < 6; i2++) {
                    zChanged2 |= composer.changed(objArr[i2]);
                }
                Object objRememberedValue6 = composer.rememberedValue();
                if (zChanged2 || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue6 = (Function2) new ClickableKt$clickable$4$gesture$1$1(mutableState3, z2, mutableInteractionSource2, mutableState, stateRememberUpdatedState2, stateRememberUpdatedState, null);
                    composer.updateRememberedValue(objRememberedValue6);
                }
                composer.endReplaceableGroup();
                Modifier modifierPointerInput = SuspendingPointerInputFilterKt.pointerInput(companion, mutableInteractionSource, boolValueOf, (Function2) objRememberedValue6);
                Modifier.Companion companion2 = Modifier.INSTANCE;
                composer.startReplaceableGroup(-492369756);
                ComposerKt.sourceInformation(composer, "CC(remember):Composables.kt#9igjgp");
                Object objRememberedValue7 = composer.rememberedValue();
                if (objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue7 = new ModifierLocalConsumer() { // from class: androidx.compose.foundation.ClickableKt$clickable$4$1$1
                        /* JADX WARN: Multi-variable type inference failed */
                        @Override // androidx.compose.ui.modifier.ModifierLocalConsumer
                        public void onModifierLocalsUpdated(ModifierLocalReadScope scope) {
                            Intrinsics.checkNotNullParameter(scope, "scope");
                            mutableState2.setValue(scope.getCurrent(ScrollableKt.getModifierLocalScrollableContainer()));
                        }
                    };
                    composer.updateRememberedValue(objRememberedValue7);
                }
                composer.endReplaceableGroup();
                Modifier modifierThen = companion2.then((Modifier) objRememberedValue7);
                MutableInteractionSource mutableInteractionSource3 = interactionSource;
                Indication indication2 = indication;
                composer.startReplaceableGroup(773894976);
                ComposerKt.sourceInformation(composer, "CC(rememberCoroutineScope)476@19869L144:Effects.kt#9igjgp");
                composer.startReplaceableGroup(-492369756);
                ComposerKt.sourceInformation(composer, "CC(remember):Composables.kt#9igjgp");
                Object objRememberedValue8 = composer.rememberedValue();
                if (objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                    Object compositionScopedCoroutineScopeCanceller = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composer));
                    composer.updateRememberedValue(compositionScopedCoroutineScopeCanceller);
                    objRememberedValue8 = compositionScopedCoroutineScopeCanceller;
                }
                composer.endReplaceableGroup();
                CoroutineScope coroutineScope = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue8).getCoroutineScope();
                composer.endReplaceableGroup();
                Modifier modifierM456genericClickableWithoutGesturebdNGguI = ClickableKt.m456genericClickableWithoutGesturebdNGguI(modifierThen, modifierPointerInput, mutableInteractionSource3, indication2, coroutineScope, map, mutableState3, z, str, role, null, null, onClick);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer.endReplaceableGroup();
                return modifierM456genericClickableWithoutGesturebdNGguI;
            }
        });
    }

    /* renamed from: combinedClickable-cJG_KMw, reason: not valid java name */
    public static final Modifier m454combinedClickablecJG_KMw(Modifier combinedClickable, final boolean z, final String str, final Role role, final String str2, final Function0<Unit> function0, final Function0<Unit> function02, final Function0<Unit> onClick) {
        Intrinsics.checkNotNullParameter(combinedClickable, "$this$combinedClickable");
        Intrinsics.checkNotNullParameter(onClick, "onClick");
        return ComposedModifierKt.composed(combinedClickable, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.ClickableKt$combinedClickable-cJG_KMw$$inlined$debugInspectorInfo$1
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
                inspectorInfo.setName("combinedClickable");
                inspectorInfo.getProperties().set(ViewProps.ENABLED, Boolean.valueOf(z));
                inspectorInfo.getProperties().set("onClickLabel", str);
                inspectorInfo.getProperties().set(ViewProps.ROLE, role);
                inspectorInfo.getProperties().set("onClick", onClick);
                inspectorInfo.getProperties().set("onDoubleClick", function02);
                inspectorInfo.getProperties().set("onLongClick", function0);
                inspectorInfo.getProperties().set("onLongClickLabel", str2);
            }
        } : InspectableValueKt.getNoInspectorInfo(), new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.foundation.ClickableKt$combinedClickable$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier, Composer composer, Integer num) {
                return invoke(modifier, composer, num.intValue());
            }

            public final Modifier invoke(Modifier composed, Composer composer, int i) {
                Intrinsics.checkNotNullParameter(composed, "$this$composed");
                composer.startReplaceableGroup(1969174843);
                ComposerKt.sourceInformation(composer, "C261@10940L7,262@10977L39:Clickable.kt#71ulvw");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1969174843, i, -1, "androidx.compose.foundation.combinedClickable.<anonymous> (Clickable.kt:252)");
                }
                Modifier.Companion companion = Modifier.INSTANCE;
                ProvidableCompositionLocal<Indication> localIndication = IndicationKt.getLocalIndication();
                ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume = composer.consume(localIndication);
                ComposerKt.sourceInformationMarkerEnd(composer);
                Indication indication = (Indication) objConsume;
                composer.startReplaceableGroup(-492369756);
                ComposerKt.sourceInformation(composer, "CC(remember):Composables.kt#9igjgp");
                Object objRememberedValue = composer.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                    composer.updateRememberedValue(objRememberedValue);
                }
                composer.endReplaceableGroup();
                Modifier modifierM452combinedClickableXVZzFYc = ClickableKt.m452combinedClickableXVZzFYc(companion, (MutableInteractionSource) objRememberedValue, indication, z, str, role, str2, function0, function02, onClick);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer.endReplaceableGroup();
                return modifierM452combinedClickableXVZzFYc;
            }
        });
    }

    /* renamed from: combinedClickable-XVZzFYc, reason: not valid java name */
    public static final Modifier m452combinedClickableXVZzFYc(Modifier combinedClickable, final MutableInteractionSource interactionSource, final Indication indication, final boolean z, final String str, final Role role, final String str2, final Function0<Unit> function0, final Function0<Unit> function02, final Function0<Unit> onClick) {
        Intrinsics.checkNotNullParameter(combinedClickable, "$this$combinedClickable");
        Intrinsics.checkNotNullParameter(interactionSource, "interactionSource");
        Intrinsics.checkNotNullParameter(onClick, "onClick");
        return ComposedModifierKt.composed(combinedClickable, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.ClickableKt$combinedClickable-XVZzFYc$$inlined$debugInspectorInfo$1
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
                inspectorInfo.setName("combinedClickable");
                inspectorInfo.getProperties().set(ViewProps.ENABLED, Boolean.valueOf(z));
                inspectorInfo.getProperties().set("onClickLabel", str);
                inspectorInfo.getProperties().set(ViewProps.ROLE, role);
                inspectorInfo.getProperties().set("onClick", onClick);
                inspectorInfo.getProperties().set("onDoubleClick", function02);
                inspectorInfo.getProperties().set("onLongClick", function0);
                inspectorInfo.getProperties().set("onLongClickLabel", str2);
                inspectorInfo.getProperties().set("indication", indication);
                inspectorInfo.getProperties().set("interactionSource", interactionSource);
            }
        } : InspectableValueKt.getNoInspectorInfo(), new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.foundation.ClickableKt$combinedClickable$4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier, Composer composer, Integer num) {
                return invoke(modifier, composer, num.intValue());
            }

            public final Modifier invoke(Modifier composed, Composer composer, int i) {
                Intrinsics.checkNotNullParameter(composed, "$this$composed");
                composer.startReplaceableGroup(1841718000);
                ComposerKt.sourceInformation(composer, "C307@13021L29,308@13082L33,309@13149L35,312@13316L58,313@13417L56,332@14268L36,333@14352L33,334@14443L95,334@14422L116,337@14566L40,340@14722L1028,368@15803L445,383@16482L24:Clickable.kt#71ulvw");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1841718000, i, -1, "androidx.compose.foundation.combinedClickable.<anonymous> (Clickable.kt:306)");
                }
                State stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(onClick, composer, 0);
                State stateRememberUpdatedState2 = SnapshotStateKt.rememberUpdatedState(function0, composer, 0);
                State stateRememberUpdatedState3 = SnapshotStateKt.rememberUpdatedState(function02, composer, 0);
                boolean z2 = function0 != null;
                boolean z3 = function02 != null;
                composer.startReplaceableGroup(-492369756);
                ComposerKt.sourceInformation(composer, "CC(remember):Composables.kt#9igjgp");
                Object objRememberedValue = composer.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                    composer.updateRememberedValue(objRememberedValue);
                }
                composer.endReplaceableGroup();
                final MutableState mutableState = (MutableState) objRememberedValue;
                composer.startReplaceableGroup(-492369756);
                ComposerKt.sourceInformation(composer, "CC(remember):Composables.kt#9igjgp");
                Object objRememberedValue2 = composer.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue2 = (Map) new LinkedHashMap();
                    composer.updateRememberedValue(objRememberedValue2);
                }
                composer.endReplaceableGroup();
                Map map = (Map) objRememberedValue2;
                composer.startReplaceableGroup(1321107720);
                ComposerKt.sourceInformation(composer, "317@13690L342,317@13659L373,326@14045L170");
                if (z) {
                    Boolean boolValueOf = Boolean.valueOf(z2);
                    final MutableInteractionSource mutableInteractionSource = interactionSource;
                    composer.startReplaceableGroup(511388516);
                    ComposerKt.sourceInformation(composer, "CC(remember)P(1,2):Composables.kt#9igjgp");
                    boolean zChanged = composer.changed(mutableState) | composer.changed(mutableInteractionSource);
                    Object objRememberedValue3 = composer.rememberedValue();
                    if (zChanged || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue3 = (Function1) new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.foundation.ClickableKt$combinedClickable$4$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final DisposableEffectResult invoke(DisposableEffectScope DisposableEffect) {
                                Intrinsics.checkNotNullParameter(DisposableEffect, "$this$DisposableEffect");
                                final MutableState<PressInteraction.Press> mutableState2 = mutableState;
                                final MutableInteractionSource mutableInteractionSource2 = mutableInteractionSource;
                                return new DisposableEffectResult() { // from class: androidx.compose.foundation.ClickableKt$combinedClickable$4$1$1$invoke$$inlined$onDispose$1
                                    @Override // androidx.compose.runtime.DisposableEffectResult
                                    public void dispose() {
                                        PressInteraction.Press press = (PressInteraction.Press) mutableState2.getValue();
                                        if (press != null) {
                                            mutableInteractionSource2.tryEmit(new PressInteraction.Cancel(press));
                                            mutableState2.setValue(null);
                                        }
                                    }
                                };
                            }
                        };
                        composer.updateRememberedValue(objRememberedValue3);
                    }
                    composer.endReplaceableGroup();
                    EffectsKt.DisposableEffect(boolValueOf, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue3, composer, 0);
                    ClickableKt.PressedInteractionSourceDisposableEffect(interactionSource, mutableState, map, composer, 560);
                }
                composer.endReplaceableGroup();
                final Function0<Boolean> function0IsComposeRootInScrollableContainer = Clickable_androidKt.isComposeRootInScrollableContainer(composer, 0);
                composer.startReplaceableGroup(-492369756);
                ComposerKt.sourceInformation(composer, "CC(remember):Composables.kt#9igjgp");
                Object objRememberedValue4 = composer.rememberedValue();
                if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                    Object objMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(true, null, 2, null);
                    composer.updateRememberedValue(objMutableStateOf$default);
                    objRememberedValue4 = objMutableStateOf$default;
                }
                composer.endReplaceableGroup();
                final MutableState mutableState2 = (MutableState) objRememberedValue4;
                composer.startReplaceableGroup(511388516);
                ComposerKt.sourceInformation(composer, "CC(remember)P(1,2):Composables.kt#9igjgp");
                boolean zChanged2 = composer.changed(mutableState2) | composer.changed(function0IsComposeRootInScrollableContainer);
                Object objRememberedValue5 = composer.rememberedValue();
                if (zChanged2 || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue5 = (Function0) new Function0<Boolean>() { // from class: androidx.compose.foundation.ClickableKt$combinedClickable$4$delayPressInteraction$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Boolean invoke() {
                            return Boolean.valueOf(mutableState2.getValue().booleanValue() || function0IsComposeRootInScrollableContainer.invoke().booleanValue());
                        }
                    };
                    composer.updateRememberedValue(objRememberedValue5);
                }
                composer.endReplaceableGroup();
                State stateRememberUpdatedState4 = SnapshotStateKt.rememberUpdatedState(objRememberedValue5, composer, 0);
                composer.startReplaceableGroup(-492369756);
                ComposerKt.sourceInformation(composer, "CC(remember):Composables.kt#9igjgp");
                Object objRememberedValue6 = composer.rememberedValue();
                if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue6 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Offset.m1628boximpl(Offset.INSTANCE.m1655getZeroF1C5BW0()), null, 2, null);
                    composer.updateRememberedValue(objRememberedValue6);
                }
                composer.endReplaceableGroup();
                MutableState mutableState3 = (MutableState) objRememberedValue6;
                Modifier.Companion companion = Modifier.INSTANCE;
                Object[] objArr = {interactionSource, Boolean.valueOf(z2), Boolean.valueOf(z3), Boolean.valueOf(z)};
                Boolean boolValueOf2 = Boolean.valueOf(z3);
                Boolean boolValueOf3 = Boolean.valueOf(z);
                Boolean boolValueOf4 = Boolean.valueOf(z2);
                MutableInteractionSource mutableInteractionSource2 = interactionSource;
                Object[] objArr2 = {mutableState3, boolValueOf2, boolValueOf3, stateRememberUpdatedState3, boolValueOf4, stateRememberUpdatedState2, mutableInteractionSource2, mutableState, stateRememberUpdatedState4, stateRememberUpdatedState};
                boolean z4 = z;
                composer.startReplaceableGroup(-568225417);
                ComposerKt.sourceInformation(composer, "CC(remember)P(1):Composables.kt#9igjgp");
                boolean zChanged3 = false;
                for (int i2 = 0; i2 < 10; i2++) {
                    zChanged3 |= composer.changed(objArr2[i2]);
                }
                Object objRememberedValue7 = composer.rememberedValue();
                if (zChanged3 || objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue7 = (Function2) new ClickableKt$combinedClickable$4$gesture$1$1(mutableState3, z3, z4, z2, stateRememberUpdatedState3, stateRememberUpdatedState2, mutableInteractionSource2, mutableState, stateRememberUpdatedState4, stateRememberUpdatedState, null);
                    composer.updateRememberedValue(objRememberedValue7);
                }
                composer.endReplaceableGroup();
                Modifier modifierPointerInput = SuspendingPointerInputFilterKt.pointerInput((Modifier) companion, objArr, (Function2<? super PointerInputScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue7);
                Modifier.Companion companion2 = Modifier.INSTANCE;
                composer.startReplaceableGroup(-492369756);
                ComposerKt.sourceInformation(composer, "CC(remember):Composables.kt#9igjgp");
                Object objRememberedValue8 = composer.rememberedValue();
                if (objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue8 = new ModifierLocalConsumer() { // from class: androidx.compose.foundation.ClickableKt$combinedClickable$4$2$1
                        /* JADX WARN: Multi-variable type inference failed */
                        @Override // androidx.compose.ui.modifier.ModifierLocalConsumer
                        public void onModifierLocalsUpdated(ModifierLocalReadScope scope) {
                            Intrinsics.checkNotNullParameter(scope, "scope");
                            mutableState2.setValue(scope.getCurrent(ScrollableKt.getModifierLocalScrollableContainer()));
                        }
                    };
                    composer.updateRememberedValue(objRememberedValue8);
                }
                composer.endReplaceableGroup();
                Modifier modifierThen = companion2.then((Modifier) objRememberedValue8);
                MutableInteractionSource mutableInteractionSource3 = interactionSource;
                Indication indication2 = indication;
                composer.startReplaceableGroup(773894976);
                ComposerKt.sourceInformation(composer, "CC(rememberCoroutineScope)476@19869L144:Effects.kt#9igjgp");
                composer.startReplaceableGroup(-492369756);
                ComposerKt.sourceInformation(composer, "CC(remember):Composables.kt#9igjgp");
                Object objRememberedValue9 = composer.rememberedValue();
                if (objRememberedValue9 == Composer.INSTANCE.getEmpty()) {
                    Object compositionScopedCoroutineScopeCanceller = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composer));
                    composer.updateRememberedValue(compositionScopedCoroutineScopeCanceller);
                    objRememberedValue9 = compositionScopedCoroutineScopeCanceller;
                }
                composer.endReplaceableGroup();
                CoroutineScope coroutineScope = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue9).getCoroutineScope();
                composer.endReplaceableGroup();
                Modifier modifierM456genericClickableWithoutGesturebdNGguI = ClickableKt.m456genericClickableWithoutGesturebdNGguI(modifierThen, modifierPointerInput, mutableInteractionSource3, indication2, coroutineScope, map, mutableState3, z, str, role, str2, function0, onClick);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer.endReplaceableGroup();
                return modifierM456genericClickableWithoutGesturebdNGguI;
            }
        });
    }
}
