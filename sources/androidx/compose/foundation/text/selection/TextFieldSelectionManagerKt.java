package androidx.compose.foundation.text.selection;

import androidx.compose.foundation.text.Handle;
import androidx.compose.foundation.text.LongPressTextDragObserverKt;
import androidx.compose.foundation.text.TextDelegate;
import androidx.compose.foundation.text.TextDragObserver;
import androidx.compose.foundation.text.TextFieldState;
import androidx.compose.foundation.text.TextLayoutResultProxy;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import androidx.compose.ui.unit.IntSize;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.ClosedRange;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* compiled from: TextFieldSelectionManager.kt */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a%\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0001¢\u0006\u0002\u0010\b\u001a%\u0010\t\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fH\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\r\u0010\u000e\u001a\u0014\u0010\u000f\u001a\u00020\u0003*\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\u0010"}, d2 = {"TextFieldSelectionHandle", "", "isStartHandle", "", "direction", "Landroidx/compose/ui/text/style/ResolvedTextDirection;", "manager", "Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;", "(ZLandroidx/compose/ui/text/style/ResolvedTextDirection;Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;Landroidx/compose/runtime/Composer;I)V", "calculateSelectionMagnifierCenterAndroid", "Landroidx/compose/ui/geometry/Offset;", "magnifierSize", "Landroidx/compose/ui/unit/IntSize;", "calculateSelectionMagnifierCenterAndroid-O0kMr_c", "(Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;J)J", "isSelectionHandleInVisibleBound", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class TextFieldSelectionManagerKt {

    /* compiled from: TextFieldSelectionManager.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Handle.values().length];
            try {
                iArr[Handle.Cursor.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Handle.SelectionStart.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Handle.SelectionEnd.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final void TextFieldSelectionHandle(final boolean z, final ResolvedTextDirection direction, final TextFieldSelectionManager manager, Composer composer, final int i) {
        Intrinsics.checkNotNullParameter(direction, "direction");
        Intrinsics.checkNotNullParameter(manager, "manager");
        Composer composerStartRestartGroup = composer.startRestartGroup(-1344558920);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(TextFieldSelectionHandle)P(1)808@30733L90,813@30889L327:TextFieldSelectionManager.kt#eksfi3");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1344558920, i, -1, "androidx.compose.foundation.text.selection.TextFieldSelectionHandle (TextFieldSelectionManager.kt:803)");
        }
        Boolean boolValueOf = Boolean.valueOf(z);
        composerStartRestartGroup.startReplaceableGroup(511388516);
        ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember)P(1,2):Composables.kt#9igjgp");
        boolean zChanged = composerStartRestartGroup.changed(boolValueOf) | composerStartRestartGroup.changed(manager);
        Object objRememberedValue = composerStartRestartGroup.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = manager.handleDragObserver$foundation_release(z);
            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
        }
        composerStartRestartGroup.endReplaceableGroup();
        TextDragObserver textDragObserver = (TextDragObserver) objRememberedValue;
        int i2 = i << 3;
        AndroidSelectionHandles_androidKt.m1073SelectionHandle8fL75g(manager.m1150getHandlePositiontuRUvjQ$foundation_release(z), z, direction, TextRange.m3904getReversedimpl(manager.getValue$foundation_release().getSelection()), SuspendingPointerInputFilterKt.pointerInput(Modifier.INSTANCE, textDragObserver, new AnonymousClass1(textDragObserver, null)), null, composerStartRestartGroup, (i2 & 112) | 196608 | (i2 & 896));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManagerKt.TextFieldSelectionHandle.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                invoke(composer2, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer2, int i3) {
                TextFieldSelectionManagerKt.TextFieldSelectionHandle(z, direction, manager, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
            }
        });
    }

    public static final boolean isSelectionHandleInVisibleBound(TextFieldSelectionManager textFieldSelectionManager, boolean z) {
        LayoutCoordinates layoutCoordinates;
        Rect rectVisibleBounds;
        Intrinsics.checkNotNullParameter(textFieldSelectionManager, "<this>");
        TextFieldState state$foundation_release = textFieldSelectionManager.getState();
        if (state$foundation_release == null || (layoutCoordinates = state$foundation_release.getLayoutCoordinates()) == null || (rectVisibleBounds = SelectionManagerKt.visibleBounds(layoutCoordinates)) == null) {
            return false;
        }
        return SelectionManagerKt.m1130containsInclusiveUv8p0NA(rectVisibleBounds, textFieldSelectionManager.m1150getHandlePositiontuRUvjQ$foundation_release(z));
    }

    /* renamed from: calculateSelectionMagnifierCenterAndroid-O0kMr_c, reason: not valid java name */
    public static final long m1151calculateSelectionMagnifierCenterAndroidO0kMr_c(TextFieldSelectionManager manager, long j) {
        int iM3905getStartimpl;
        TextLayoutResultProxy layoutResult;
        TextLayoutResult value;
        TextDelegate textDelegate;
        AnnotatedString text;
        LayoutCoordinates layoutCoordinates;
        TextLayoutResultProxy layoutResult2;
        LayoutCoordinates innerTextFieldCoordinates;
        Intrinsics.checkNotNullParameter(manager, "manager");
        if (manager.getValue$foundation_release().getText().length() == 0) {
            return Offset.INSTANCE.m1654getUnspecifiedF1C5BW0();
        }
        Handle draggingHandle = manager.getDraggingHandle();
        int i = draggingHandle == null ? -1 : WhenMappings.$EnumSwitchMapping$0[draggingHandle.ordinal()];
        if (i == -1) {
            return Offset.INSTANCE.m1654getUnspecifiedF1C5BW0();
        }
        if (i == 1 || i == 2) {
            iM3905getStartimpl = TextRange.m3905getStartimpl(manager.getValue$foundation_release().getSelection());
        } else {
            if (i != 3) {
                throw new NoWhenBranchMatchedException();
            }
            iM3905getStartimpl = TextRange.m3900getEndimpl(manager.getValue$foundation_release().getSelection());
        }
        int iOriginalToTransformed = manager.getOffsetMapping().originalToTransformed(iM3905getStartimpl);
        TextFieldState state$foundation_release = manager.getState();
        if (state$foundation_release == null || (layoutResult = state$foundation_release.getLayoutResult()) == null || (value = layoutResult.getValue()) == null) {
            return Offset.INSTANCE.m1654getUnspecifiedF1C5BW0();
        }
        TextFieldState state$foundation_release2 = manager.getState();
        if (state$foundation_release2 == null || (textDelegate = state$foundation_release2.getTextDelegate()) == null || (text = textDelegate.getText()) == null) {
            return Offset.INSTANCE.m1654getUnspecifiedF1C5BW0();
        }
        int iCoerceIn = RangesKt.coerceIn(iOriginalToTransformed, (ClosedRange<Integer>) StringsKt.getIndices(text));
        long jM1669getCenterF1C5BW0 = value.getBoundingBox(iCoerceIn).m1669getCenterF1C5BW0();
        TextFieldState state$foundation_release3 = manager.getState();
        if (state$foundation_release3 == null || (layoutCoordinates = state$foundation_release3.getLayoutCoordinates()) == null) {
            return Offset.INSTANCE.m1654getUnspecifiedF1C5BW0();
        }
        TextFieldState state$foundation_release4 = manager.getState();
        if (state$foundation_release4 == null || (layoutResult2 = state$foundation_release4.getLayoutResult()) == null || (innerTextFieldCoordinates = layoutResult2.getInnerTextFieldCoordinates()) == null) {
            return Offset.INSTANCE.m1654getUnspecifiedF1C5BW0();
        }
        Offset offsetM1148getCurrentDragPosition_m7T9E = manager.m1148getCurrentDragPosition_m7T9E();
        if (offsetM1148getCurrentDragPosition_m7T9E == null) {
            return Offset.INSTANCE.m1654getUnspecifiedF1C5BW0();
        }
        float fM1639getXimpl = Offset.m1639getXimpl(innerTextFieldCoordinates.mo3402localPositionOfR5De75A(layoutCoordinates, offsetM1148getCurrentDragPosition_m7T9E.getPackedValue()));
        int lineForOffset = value.getLineForOffset(iCoerceIn);
        int lineStart = value.getLineStart(lineForOffset);
        int lineEnd = value.getLineEnd(lineForOffset, true);
        boolean z = TextRange.m3905getStartimpl(manager.getValue$foundation_release().getSelection()) > TextRange.m3900getEndimpl(manager.getValue$foundation_release().getSelection());
        float horizontalPosition = TextSelectionDelegateKt.getHorizontalPosition(value, lineStart, true, z);
        float horizontalPosition2 = TextSelectionDelegateKt.getHorizontalPosition(value, lineEnd, false, z);
        float fCoerceIn = RangesKt.coerceIn(fM1639getXimpl, Math.min(horizontalPosition, horizontalPosition2), Math.max(horizontalPosition, horizontalPosition2));
        if (Math.abs(fM1639getXimpl - fCoerceIn) > IntSize.m4550getWidthimpl(j) / 2) {
            return Offset.INSTANCE.m1654getUnspecifiedF1C5BW0();
        }
        return layoutCoordinates.mo3402localPositionOfR5De75A(innerTextFieldCoordinates, OffsetKt.Offset(fCoerceIn, Offset.m1640getYimpl(jM1669getCenterF1C5BW0)));
    }

    /* compiled from: TextFieldSelectionManager.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.selection.TextFieldSelectionManagerKt$TextFieldSelectionHandle$1", f = "TextFieldSelectionManager.kt", i = {}, l = {820}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.compose.foundation.text.selection.TextFieldSelectionManagerKt$TextFieldSelectionHandle$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ TextDragObserver $observer;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(TextDragObserver textDragObserver, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$observer = textDragObserver;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$observer, continuation);
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
                this.label = 1;
                if (LongPressTextDragObserverKt.detectDownAndDragGesturesWithObserver((PointerInputScope) this.L$0, this.$observer, this) == coroutine_suspended) {
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
