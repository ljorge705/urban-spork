package androidx.compose.foundation.text.selection;

import androidx.compose.foundation.FocusableKt;
import androidx.compose.foundation.gestures.ForEachGestureKt;
import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.foundation.text.Handle;
import androidx.compose.foundation.text.TextDragObserver;
import androidx.compose.foundation.text.selection.Selection;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusChangedModifierKt;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.focus.FocusRequesterModifierKt;
import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.hapticfeedback.HapticFeedback;
import androidx.compose.ui.hapticfeedback.HapticFeedbackType;
import androidx.compose.ui.input.key.KeyEvent;
import androidx.compose.ui.input.key.KeyInputModifierKt;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.layout.OnGloballyPositionedModifierKt;
import androidx.compose.ui.platform.ClipboardManager;
import androidx.compose.ui.platform.TextToolbar;
import androidx.compose.ui.platform.TextToolbarStatus;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextRange;
import com.clevertap.android.sdk.Constants;
import com.drew.metadata.iptc.IptcDirectory;
import com.facebook.react.uimanager.ViewProps;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SelectionManager.kt */
@Metadata(d1 = {"\u0000º\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001b\u0010f\u001a\u00020L2\u0006\u0010g\u001a\u00020\u0016ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bh\u0010\"J'\u0010i\u001a\u0004\u0018\u00010\u00162\u0006\u0010j\u001a\u00020\u000f2\u0006\u0010k\u001a\u00020\u0016H\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bl\u0010mJ\r\u0010n\u001a\u00020LH\u0000¢\u0006\u0002\boJ\u0017\u0010p\u001a\u0004\u0018\u00010q2\u0006\u0010r\u001a\u00020sH\u0000¢\u0006\u0002\btJ\b\u0010u\u001a\u00020vH\u0002J\u000f\u0010w\u001a\u0004\u0018\u00010xH\u0000¢\u0006\u0002\byJ\u000e\u0010z\u001a\u00020{2\u0006\u0010|\u001a\u00020?J\r\u0010}\u001a\u00020LH\u0000¢\u0006\u0002\b~J\u0006\u0010\u007f\u001a\u00020LJ\u000f\u0010\u0080\u0001\u001a\u00020\u000fH\u0000¢\u0006\u0003\b\u0081\u0001JA\u0010\u0082\u0001\u001a\u001f\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0012\u0012\u0010\u0012\u0005\u0012\u00030\u0085\u0001\u0012\u0004\u0012\u00020\u00070\u0084\u00010\u0083\u00012\b\u0010\u0086\u0001\u001a\u00030\u0085\u00012\t\u0010\u0087\u0001\u001a\u0004\u0018\u00010\u0007H\u0000¢\u0006\u0003\b\u0088\u0001J\u000f\u0010\u0089\u0001\u001a\u00020LH\u0000¢\u0006\u0003\b\u008a\u0001J2\u0010\u008b\u0001\u001a\u00020L2\u0006\u0010g\u001a\u00020\u00162\u0006\u0010|\u001a\u00020?2\b\u0010\u008c\u0001\u001a\u00030\u008d\u0001H\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0006\b\u008e\u0001\u0010\u008f\u0001J\t\u0010\u0090\u0001\u001a\u00020LH\u0002JE\u0010\u0091\u0001\u001a\u00020?2\u0006\u0010Y\u001a\u00020\u00162\u0006\u0010/\u001a\u00020\u00162\t\u0010\u0092\u0001\u001a\u0004\u0018\u00010\u00162\u0006\u0010|\u001a\u00020?2\b\u0010\u008c\u0001\u001a\u00030\u008d\u0001H\u0000ø\u0001\u0001ø\u0001\u0000¢\u0006\u0006\b\u0093\u0001\u0010\u0094\u0001J<\u0010\u0091\u0001\u001a\u00020?2\t\u0010\u0095\u0001\u001a\u0004\u0018\u00010\u00162\b\u0010Q\u001a\u0004\u0018\u00010\u00162\u0006\u0010|\u001a\u00020?2\b\u0010\u008c\u0001\u001a\u00030\u008d\u0001H\u0000ø\u0001\u0001ø\u0001\u0000¢\u0006\u0003\b\u0096\u0001J\t\u0010\u0097\u0001\u001a\u00020LH\u0002J0\u0010\u0098\u0001\u001a\u00020L*\u00030\u0099\u00012\u0013\u0010\u009a\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020L0KH\u0082@ø\u0001\u0000ø\u0001\u0000¢\u0006\u0003\u0010\u009b\u0001J\u001d\u0010\u009c\u0001\u001a\u00020G*\u00020G2\u000e\u0010\u009d\u0001\u001a\t\u0012\u0004\u0012\u00020L0\u009e\u0001H\u0002R\u0016\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR(\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R8\u0010\u0017\u001a\u0004\u0018\u00010\u00162\b\u0010\u0015\u001a\u0004\u0018\u00010\u00168F@BX\u0086\u008e\u0002ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0012\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR4\u0010\u001e\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u00168@@BX\u0080\u008e\u0002ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0012\n\u0004\b#\u0010\u001d\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R4\u0010$\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u00168@@BX\u0080\u008e\u0002ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0012\n\u0004\b'\u0010\u001d\u001a\u0004\b%\u0010 \"\u0004\b&\u0010\"R/\u0010)\u001a\u0004\u0018\u00010(2\b\u0010\u0015\u001a\u0004\u0018\u00010(8F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b.\u0010\u001d\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R8\u0010/\u001a\u0004\u0018\u00010\u00162\b\u0010\u0015\u001a\u0004\u0018\u00010\u00168F@BX\u0086\u008e\u0002ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0012\n\u0004\b2\u0010\u001d\u001a\u0004\b0\u0010\u0019\"\u0004\b1\u0010\u001bR\u001a\u00103\u001a\u000204X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u00106\"\u0004\b7\u00108R\u001c\u00109\u001a\u0004\u0018\u00010:X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010<\"\u0004\b=\u0010>R+\u0010@\u001a\u00020?2\u0006\u0010\u0015\u001a\u00020?8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bE\u0010\u001d\u001a\u0004\bA\u0010B\"\u0004\bC\u0010DR\u0011\u0010F\u001a\u00020G8F¢\u0006\u0006\u001a\u0004\bH\u0010IR(\u0010J\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0004\u0012\u00020L0KX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bM\u0010N\"\u0004\bO\u0010PR\u0019\u0010Q\u001a\u0004\u0018\u00010\u0016X\u0082\u000eø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0002\n\u0000R(\u0010R\u001a\u0004\u0018\u00010\u00072\b\u0010\u000e\u001a\u0004\u0018\u00010\u00078F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bS\u0010T\"\u0004\bU\u0010VR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010W\u001a\u00020?8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bX\u0010BR8\u0010Y\u001a\u0004\u0018\u00010\u00162\b\u0010\u0015\u001a\u0004\u0018\u00010\u00168F@BX\u0086\u008e\u0002ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0012\n\u0004\b\\\u0010\u001d\u001a\u0004\bZ\u0010\u0019\"\u0004\b[\u0010\u001bR\u001c\u0010]\u001a\u0004\u0018\u00010^X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b_\u0010`\"\u0004\ba\u0010bR\u001a\u0010c\u001a\u00020?X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bd\u0010B\"\u0004\be\u0010D\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u009f\u0001"}, d2 = {"Landroidx/compose/foundation/text/selection/SelectionManager;", "", "selectionRegistrar", "Landroidx/compose/foundation/text/selection/SelectionRegistrarImpl;", "(Landroidx/compose/foundation/text/selection/SelectionRegistrarImpl;)V", "_selection", "Landroidx/compose/runtime/MutableState;", "Landroidx/compose/foundation/text/selection/Selection;", "clipboardManager", "Landroidx/compose/ui/platform/ClipboardManager;", "getClipboardManager", "()Landroidx/compose/ui/platform/ClipboardManager;", "setClipboardManager", "(Landroidx/compose/ui/platform/ClipboardManager;)V", "value", "Landroidx/compose/ui/layout/LayoutCoordinates;", "containerLayoutCoordinates", "getContainerLayoutCoordinates", "()Landroidx/compose/ui/layout/LayoutCoordinates;", "setContainerLayoutCoordinates", "(Landroidx/compose/ui/layout/LayoutCoordinates;)V", "<set-?>", "Landroidx/compose/ui/geometry/Offset;", "currentDragPosition", "getCurrentDragPosition-_m7T9-E", "()Landroidx/compose/ui/geometry/Offset;", "setCurrentDragPosition-_kEHs6E", "(Landroidx/compose/ui/geometry/Offset;)V", "currentDragPosition$delegate", "Landroidx/compose/runtime/MutableState;", "dragBeginPosition", "getDragBeginPosition-F1C5BW0$foundation_release", "()J", "setDragBeginPosition-k-4lQ0M", "(J)V", "dragBeginPosition$delegate", "dragTotalDistance", "getDragTotalDistance-F1C5BW0$foundation_release", "setDragTotalDistance-k-4lQ0M", "dragTotalDistance$delegate", "Landroidx/compose/foundation/text/Handle;", "draggingHandle", "getDraggingHandle", "()Landroidx/compose/foundation/text/Handle;", "setDraggingHandle", "(Landroidx/compose/foundation/text/Handle;)V", "draggingHandle$delegate", "endHandlePosition", "getEndHandlePosition-_m7T9-E", "setEndHandlePosition-_kEHs6E", "endHandlePosition$delegate", "focusRequester", "Landroidx/compose/ui/focus/FocusRequester;", "getFocusRequester", "()Landroidx/compose/ui/focus/FocusRequester;", "setFocusRequester", "(Landroidx/compose/ui/focus/FocusRequester;)V", "hapticFeedBack", "Landroidx/compose/ui/hapticfeedback/HapticFeedback;", "getHapticFeedBack", "()Landroidx/compose/ui/hapticfeedback/HapticFeedback;", "setHapticFeedBack", "(Landroidx/compose/ui/hapticfeedback/HapticFeedback;)V", "", "hasFocus", "getHasFocus", "()Z", "setHasFocus", "(Z)V", "hasFocus$delegate", "modifier", "Landroidx/compose/ui/Modifier;", "getModifier", "()Landroidx/compose/ui/Modifier;", "onSelectionChange", "Lkotlin/Function1;", "", "getOnSelectionChange", "()Lkotlin/jvm/functions/Function1;", "setOnSelectionChange", "(Lkotlin/jvm/functions/Function1;)V", "previousPosition", "selection", "getSelection", "()Landroidx/compose/foundation/text/selection/Selection;", "setSelection", "(Landroidx/compose/foundation/text/selection/Selection;)V", "shouldShowMagnifier", "getShouldShowMagnifier", "startHandlePosition", "getStartHandlePosition-_m7T9-E", "setStartHandlePosition-_kEHs6E", "startHandlePosition$delegate", "textToolbar", "Landroidx/compose/ui/platform/TextToolbar;", "getTextToolbar", "()Landroidx/compose/ui/platform/TextToolbar;", "setTextToolbar", "(Landroidx/compose/ui/platform/TextToolbar;)V", "touchMode", "getTouchMode", "setTouchMode", "contextMenuOpenAdjustment", ViewProps.POSITION, "contextMenuOpenAdjustment-k-4lQ0M", "convertToContainerCoordinates", "layoutCoordinates", "offset", "convertToContainerCoordinates-Q7Q5hAU", "(Landroidx/compose/ui/layout/LayoutCoordinates;J)Landroidx/compose/ui/geometry/Offset;", Constants.COPY_TYPE, "copy$foundation_release", "getAnchorSelectable", "Landroidx/compose/foundation/text/selection/Selectable;", "anchor", "Landroidx/compose/foundation/text/selection/Selection$AnchorInfo;", "getAnchorSelectable$foundation_release", "getContentRect", "Landroidx/compose/ui/geometry/Rect;", "getSelectedText", "Landroidx/compose/ui/text/AnnotatedString;", "getSelectedText$foundation_release", "handleDragObserver", "Landroidx/compose/foundation/text/TextDragObserver;", "isStartHandle", "hideSelectionToolbar", "hideSelectionToolbar$foundation_release", "onRelease", "requireContainerCoordinates", "requireContainerCoordinates$foundation_release", "selectAll", "Lkotlin/Pair;", "", "", "selectableId", "previousSelection", "selectAll$foundation_release", "showSelectionToolbar", "showSelectionToolbar$foundation_release", "startSelection", "adjustment", "Landroidx/compose/foundation/text/selection/SelectionAdjustment;", "startSelection-9KIMszo", "(JZLandroidx/compose/foundation/text/selection/SelectionAdjustment;)V", "updateHandleOffsets", "updateSelection", "previousHandlePosition", "updateSelection-3R_-tFg$foundation_release", "(JJLandroidx/compose/ui/geometry/Offset;ZLandroidx/compose/foundation/text/selection/SelectionAdjustment;)Z", "newPosition", "updateSelection-RHHTvR4$foundation_release", "updateSelectionToolbarPosition", "detectNonConsumingTap", "Landroidx/compose/ui/input/pointer/PointerInputScope;", "onTap", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onClearSelectionRequested", "block", "Lkotlin/Function0;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SelectionManager {
    private final MutableState<Selection> _selection;
    private ClipboardManager clipboardManager;
    private LayoutCoordinates containerLayoutCoordinates;

    /* renamed from: currentDragPosition$delegate, reason: from kotlin metadata */
    private final MutableState currentDragPosition;

    /* renamed from: dragBeginPosition$delegate, reason: from kotlin metadata */
    private final MutableState dragBeginPosition;

    /* renamed from: dragTotalDistance$delegate, reason: from kotlin metadata */
    private final MutableState dragTotalDistance;

    /* renamed from: draggingHandle$delegate, reason: from kotlin metadata */
    private final MutableState draggingHandle;

    /* renamed from: endHandlePosition$delegate, reason: from kotlin metadata */
    private final MutableState endHandlePosition;
    private FocusRequester focusRequester;
    private HapticFeedback hapticFeedBack;

    /* renamed from: hasFocus$delegate, reason: from kotlin metadata */
    private final MutableState hasFocus;
    private Function1<? super Selection, Unit> onSelectionChange;
    private Offset previousPosition;
    private final SelectionRegistrarImpl selectionRegistrar;

    /* renamed from: startHandlePosition$delegate, reason: from kotlin metadata */
    private final MutableState startHandlePosition;
    private TextToolbar textToolbar;
    private boolean touchMode;

    public final ClipboardManager getClipboardManager() {
        return this.clipboardManager;
    }

    public final LayoutCoordinates getContainerLayoutCoordinates() {
        return this.containerLayoutCoordinates;
    }

    public final FocusRequester getFocusRequester() {
        return this.focusRequester;
    }

    public final HapticFeedback getHapticFeedBack() {
        return this.hapticFeedBack;
    }

    public final Function1<Selection, Unit> getOnSelectionChange() {
        return this.onSelectionChange;
    }

    public final TextToolbar getTextToolbar() {
        return this.textToolbar;
    }

    public final boolean getTouchMode() {
        return this.touchMode;
    }

    public final void setClipboardManager(ClipboardManager clipboardManager) {
        this.clipboardManager = clipboardManager;
    }

    public final void setFocusRequester(FocusRequester focusRequester) {
        Intrinsics.checkNotNullParameter(focusRequester, "<set-?>");
        this.focusRequester = focusRequester;
    }

    public final void setHapticFeedBack(HapticFeedback hapticFeedback) {
        this.hapticFeedBack = hapticFeedback;
    }

    public final void setOnSelectionChange(Function1<? super Selection, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "<set-?>");
        this.onSelectionChange = function1;
    }

    public final void setTextToolbar(TextToolbar textToolbar) {
        this.textToolbar = textToolbar;
    }

    public final void setTouchMode(boolean z) {
        this.touchMode = z;
    }

    public SelectionManager(SelectionRegistrarImpl selectionRegistrar) {
        Intrinsics.checkNotNullParameter(selectionRegistrar, "selectionRegistrar");
        this.selectionRegistrar = selectionRegistrar;
        this._selection = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        this.touchMode = true;
        this.onSelectionChange = new Function1<Selection, Unit>() { // from class: androidx.compose.foundation.text.selection.SelectionManager$onSelectionChange$1
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Selection selection) {
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Selection selection) {
                invoke2(selection);
                return Unit.INSTANCE;
            }
        };
        this.focusRequester = new FocusRequester();
        this.hasFocus = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
        this.dragBeginPosition = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Offset.m1628boximpl(Offset.INSTANCE.m1655getZeroF1C5BW0()), null, 2, null);
        this.dragTotalDistance = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Offset.m1628boximpl(Offset.INSTANCE.m1655getZeroF1C5BW0()), null, 2, null);
        this.startHandlePosition = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        this.endHandlePosition = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        this.draggingHandle = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        this.currentDragPosition = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        selectionRegistrar.setOnPositionChangeCallback$foundation_release(new Function1<Long, Unit>() { // from class: androidx.compose.foundation.text.selection.SelectionManager.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Long l) {
                invoke(l.longValue());
                return Unit.INSTANCE;
            }

            public final void invoke(long j) {
                Selection selection;
                Selection.AnchorInfo end;
                Selection.AnchorInfo start;
                Selection selection2 = SelectionManager.this.getSelection();
                if ((selection2 == null || (start = selection2.getStart()) == null || j != start.getSelectableId()) && ((selection = SelectionManager.this.getSelection()) == null || (end = selection.getEnd()) == null || j != end.getSelectableId())) {
                    return;
                }
                SelectionManager.this.updateHandleOffsets();
                SelectionManager.this.updateSelectionToolbarPosition();
            }
        });
        selectionRegistrar.setOnSelectionUpdateStartCallback$foundation_release(new Function3<LayoutCoordinates, Offset, SelectionAdjustment, Unit>() { // from class: androidx.compose.foundation.text.selection.SelectionManager.2
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(LayoutCoordinates layoutCoordinates, Offset offset, SelectionAdjustment selectionAdjustment) {
                m1125invoked4ec7I(layoutCoordinates, offset.getPackedValue(), selectionAdjustment);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke-d-4ec7I, reason: not valid java name */
            public final void m1125invoked4ec7I(LayoutCoordinates layoutCoordinates, long j, SelectionAdjustment selectionMode) {
                Intrinsics.checkNotNullParameter(layoutCoordinates, "layoutCoordinates");
                Intrinsics.checkNotNullParameter(selectionMode, "selectionMode");
                Offset offsetM1110convertToContainerCoordinatesQ7Q5hAU = SelectionManager.this.m1110convertToContainerCoordinatesQ7Q5hAU(layoutCoordinates, j);
                if (offsetM1110convertToContainerCoordinatesQ7Q5hAU != null) {
                    SelectionManager.this.m1116startSelection9KIMszo(offsetM1110convertToContainerCoordinatesQ7Q5hAU.getPackedValue(), false, selectionMode);
                    SelectionManager.this.getFocusRequester().requestFocus();
                    SelectionManager.this.hideSelectionToolbar$foundation_release();
                }
            }
        });
        selectionRegistrar.setOnSelectionUpdateSelectAll$foundation_release(new Function1<Long, Unit>() { // from class: androidx.compose.foundation.text.selection.SelectionManager.3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Long l) {
                invoke(l.longValue());
                return Unit.INSTANCE;
            }

            public final void invoke(long j) {
                SelectionManager selectionManager = SelectionManager.this;
                Pair<Selection, Map<Long, Selection>> pairSelectAll$foundation_release = selectionManager.selectAll$foundation_release(j, selectionManager.getSelection());
                Selection selectionComponent1 = pairSelectAll$foundation_release.component1();
                Map<Long, Selection> mapComponent2 = pairSelectAll$foundation_release.component2();
                if (!Intrinsics.areEqual(selectionComponent1, SelectionManager.this.getSelection())) {
                    SelectionManager.this.selectionRegistrar.setSubselections(mapComponent2);
                    SelectionManager.this.getOnSelectionChange().invoke(selectionComponent1);
                }
                SelectionManager.this.getFocusRequester().requestFocus();
                SelectionManager.this.hideSelectionToolbar$foundation_release();
            }
        });
        selectionRegistrar.setOnSelectionUpdateCallback$foundation_release(new Function5<LayoutCoordinates, Offset, Offset, Boolean, SelectionAdjustment, Boolean>() { // from class: androidx.compose.foundation.text.selection.SelectionManager.4
            {
                super(5);
            }

            @Override // kotlin.jvm.functions.Function5
            public /* bridge */ /* synthetic */ Boolean invoke(LayoutCoordinates layoutCoordinates, Offset offset, Offset offset2, Boolean bool, SelectionAdjustment selectionAdjustment) {
                return m1126invoke5iVPX68(layoutCoordinates, offset.getPackedValue(), offset2.getPackedValue(), bool.booleanValue(), selectionAdjustment);
            }

            /* renamed from: invoke-5iVPX68, reason: not valid java name */
            public final Boolean m1126invoke5iVPX68(LayoutCoordinates layoutCoordinates, long j, long j2, boolean z, SelectionAdjustment selectionMode) {
                Intrinsics.checkNotNullParameter(layoutCoordinates, "layoutCoordinates");
                Intrinsics.checkNotNullParameter(selectionMode, "selectionMode");
                return Boolean.valueOf(SelectionManager.this.m1124updateSelectionRHHTvR4$foundation_release(SelectionManager.this.m1110convertToContainerCoordinatesQ7Q5hAU(layoutCoordinates, j), SelectionManager.this.m1110convertToContainerCoordinatesQ7Q5hAU(layoutCoordinates, j2), z, selectionMode));
            }
        });
        selectionRegistrar.setOnSelectionUpdateEndCallback$foundation_release(new Function0<Unit>() { // from class: androidx.compose.foundation.text.selection.SelectionManager.5
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
                SelectionManager.this.showSelectionToolbar$foundation_release();
                SelectionManager.this.setDraggingHandle(null);
                SelectionManager.this.m1111setCurrentDragPosition_kEHs6E(null);
            }
        });
        selectionRegistrar.setOnSelectableChangeCallback$foundation_release(new Function1<Long, Unit>() { // from class: androidx.compose.foundation.text.selection.SelectionManager.6
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Long l) {
                invoke(l.longValue());
                return Unit.INSTANCE;
            }

            public final void invoke(long j) {
                if (SelectionManager.this.selectionRegistrar.getSubselections().containsKey(Long.valueOf(j))) {
                    SelectionManager.this.onRelease();
                    SelectionManager.this.setSelection(null);
                }
            }
        });
        selectionRegistrar.setAfterSelectableUnsubscribe$foundation_release(new Function1<Long, Unit>() { // from class: androidx.compose.foundation.text.selection.SelectionManager.7
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Long l) {
                invoke(l.longValue());
                return Unit.INSTANCE;
            }

            public final void invoke(long j) {
                Selection selection;
                Selection.AnchorInfo end;
                Selection.AnchorInfo start;
                Selection selection2 = SelectionManager.this.getSelection();
                if ((selection2 == null || (start = selection2.getStart()) == null || j != start.getSelectableId()) && ((selection = SelectionManager.this.getSelection()) == null || (end = selection.getEnd()) == null || j != end.getSelectableId())) {
                    return;
                }
                SelectionManager.this.m1115setStartHandlePosition_kEHs6E(null);
                SelectionManager.this.m1114setEndHandlePosition_kEHs6E(null);
            }
        });
    }

    public final Selection getSelection() {
        return this._selection.getValue();
    }

    public final void setSelection(Selection selection) {
        this._selection.setValue(selection);
        if (selection != null) {
            updateHandleOffsets();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean getHasFocus() {
        return ((Boolean) this.hasFocus.getValue()).booleanValue();
    }

    public final void setHasFocus(boolean z) {
        this.hasFocus.setValue(Boolean.valueOf(z));
    }

    public final Modifier getModifier() {
        return KeyInputModifierKt.onKeyEvent(FocusableKt.focusable$default(FocusChangedModifierKt.onFocusChanged(FocusRequesterModifierKt.focusRequester(OnGloballyPositionedModifierKt.onGloballyPositioned(onClearSelectionRequested(Modifier.INSTANCE, new Function0<Unit>() { // from class: androidx.compose.foundation.text.selection.SelectionManager$modifier$1
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
                this.this$0.onRelease();
            }
        }), new Function1<LayoutCoordinates, Unit>() { // from class: androidx.compose.foundation.text.selection.SelectionManager$modifier$2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(LayoutCoordinates layoutCoordinates) {
                invoke2(layoutCoordinates);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(LayoutCoordinates it) {
                Intrinsics.checkNotNullParameter(it, "it");
                this.this$0.setContainerLayoutCoordinates(it);
            }
        }), this.focusRequester), new Function1<FocusState, Unit>() { // from class: androidx.compose.foundation.text.selection.SelectionManager$modifier$3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(FocusState focusState) {
                invoke2(focusState);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(FocusState focusState) {
                Intrinsics.checkNotNullParameter(focusState, "focusState");
                if (!focusState.isFocused() && this.this$0.getHasFocus()) {
                    this.this$0.onRelease();
                }
                this.this$0.setHasFocus(focusState.isFocused());
            }
        }), false, null, 3, null), new Function1<KeyEvent, Boolean>() { // from class: androidx.compose.foundation.text.selection.SelectionManager$modifier$4
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(KeyEvent keyEvent) {
                return m1127invokeZmokQxo(keyEvent.m3134unboximpl());
            }

            /* renamed from: invoke-ZmokQxo, reason: not valid java name */
            public final Boolean m1127invokeZmokQxo(android.view.KeyEvent it) {
                boolean z;
                Intrinsics.checkNotNullParameter(it, "it");
                if (SelectionManager_androidKt.m1131isCopyKeyEventZmokQxo(it)) {
                    this.this$0.copy$foundation_release();
                    z = true;
                } else {
                    z = false;
                }
                return Boolean.valueOf(z);
            }
        }).then(getShouldShowMagnifier() ? SelectionManager_androidKt.selectionMagnifier(Modifier.INSTANCE, this) : Modifier.INSTANCE);
    }

    public final void setContainerLayoutCoordinates(LayoutCoordinates layoutCoordinates) {
        this.containerLayoutCoordinates = layoutCoordinates;
        if (!getHasFocus() || getSelection() == null) {
            return;
        }
        Offset offsetM1628boximpl = layoutCoordinates != null ? Offset.m1628boximpl(LayoutCoordinatesKt.positionInWindow(layoutCoordinates)) : null;
        if (Intrinsics.areEqual(this.previousPosition, offsetM1628boximpl)) {
            return;
        }
        this.previousPosition = offsetM1628boximpl;
        updateHandleOffsets();
        updateSelectionToolbarPosition();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setDragBeginPosition-k-4lQ0M, reason: not valid java name */
    public final void m1112setDragBeginPositionk4lQ0M(long j) {
        this.dragBeginPosition.setValue(Offset.m1628boximpl(j));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: getDragBeginPosition-F1C5BW0$foundation_release, reason: not valid java name */
    public final long m1119getDragBeginPositionF1C5BW0$foundation_release() {
        return ((Offset) this.dragBeginPosition.getValue()).getPackedValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setDragTotalDistance-k-4lQ0M, reason: not valid java name */
    public final void m1113setDragTotalDistancek4lQ0M(long j) {
        this.dragTotalDistance.setValue(Offset.m1628boximpl(j));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: getDragTotalDistance-F1C5BW0$foundation_release, reason: not valid java name */
    public final long m1120getDragTotalDistanceF1C5BW0$foundation_release() {
        return ((Offset) this.dragTotalDistance.getValue()).getPackedValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: getStartHandlePosition-_m7T9-E, reason: not valid java name */
    public final Offset m1122getStartHandlePosition_m7T9E() {
        return (Offset) this.startHandlePosition.getValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: getEndHandlePosition-_m7T9-E, reason: not valid java name */
    public final Offset m1121getEndHandlePosition_m7T9E() {
        return (Offset) this.endHandlePosition.getValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Handle getDraggingHandle() {
        return (Handle) this.draggingHandle.getValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: getCurrentDragPosition-_m7T9-E, reason: not valid java name */
    public final Offset m1118getCurrentDragPosition_m7T9E() {
        return (Offset) this.currentDragPosition.getValue();
    }

    private final boolean getShouldShowMagnifier() {
        return getDraggingHandle() != null;
    }

    public final Selectable getAnchorSelectable$foundation_release(Selection.AnchorInfo anchor) {
        Intrinsics.checkNotNullParameter(anchor, "anchor");
        return this.selectionRegistrar.getSelectableMap$foundation_release().get(Long.valueOf(anchor.getSelectableId()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateHandleOffsets() {
        Selection.AnchorInfo end;
        Selection.AnchorInfo start;
        Selection selection = getSelection();
        LayoutCoordinates layoutCoordinates = this.containerLayoutCoordinates;
        Selectable anchorSelectable$foundation_release = (selection == null || (start = selection.getStart()) == null) ? null : getAnchorSelectable$foundation_release(start);
        Selectable anchorSelectable$foundation_release2 = (selection == null || (end = selection.getEnd()) == null) ? null : getAnchorSelectable$foundation_release(end);
        LayoutCoordinates layoutCoordinates2 = anchorSelectable$foundation_release != null ? anchorSelectable$foundation_release.getLayoutCoordinates() : null;
        LayoutCoordinates layoutCoordinates3 = anchorSelectable$foundation_release2 != null ? anchorSelectable$foundation_release2.getLayoutCoordinates() : null;
        if (selection == null || layoutCoordinates == null || !layoutCoordinates.isAttached() || layoutCoordinates2 == null || layoutCoordinates3 == null) {
            m1115setStartHandlePosition_kEHs6E(null);
            m1114setEndHandlePosition_kEHs6E(null);
            return;
        }
        long jMo3402localPositionOfR5De75A = layoutCoordinates.mo3402localPositionOfR5De75A(layoutCoordinates2, anchorSelectable$foundation_release.mo1078getHandlePositiondBAh8RU(selection, true));
        long jMo3402localPositionOfR5De75A2 = layoutCoordinates.mo3402localPositionOfR5De75A(layoutCoordinates3, anchorSelectable$foundation_release2.mo1078getHandlePositiondBAh8RU(selection, false));
        Rect rectVisibleBounds = SelectionManagerKt.visibleBounds(layoutCoordinates);
        Offset offsetM1628boximpl = Offset.m1628boximpl(jMo3402localPositionOfR5De75A);
        offsetM1628boximpl.getPackedValue();
        if (!SelectionManagerKt.m1130containsInclusiveUv8p0NA(rectVisibleBounds, jMo3402localPositionOfR5De75A) && getDraggingHandle() != Handle.SelectionStart) {
            offsetM1628boximpl = null;
        }
        m1115setStartHandlePosition_kEHs6E(offsetM1628boximpl);
        Offset offsetM1628boximpl2 = Offset.m1628boximpl(jMo3402localPositionOfR5De75A2);
        offsetM1628boximpl2.getPackedValue();
        m1114setEndHandlePosition_kEHs6E((SelectionManagerKt.m1130containsInclusiveUv8p0NA(rectVisibleBounds, jMo3402localPositionOfR5De75A2) || getDraggingHandle() == Handle.SelectionEnd) ? offsetM1628boximpl2 : null);
    }

    public final LayoutCoordinates requireContainerCoordinates$foundation_release() {
        LayoutCoordinates layoutCoordinates = this.containerLayoutCoordinates;
        if (layoutCoordinates == null) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (layoutCoordinates.isAttached()) {
            return layoutCoordinates;
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    public final Pair<Selection, Map<Long, Selection>> selectAll$foundation_release(long selectableId, Selection previousSelection) {
        HapticFeedback hapticFeedback;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        List<Selectable> listSort = this.selectionRegistrar.sort(requireContainerCoordinates$foundation_release());
        int size = listSort.size();
        Selection selectionMerge = null;
        for (int i = 0; i < size; i++) {
            Selectable selectable = listSort.get(i);
            Selection selectAllSelection = selectable.getSelectableId() == selectableId ? selectable.getSelectAllSelection() : null;
            if (selectAllSelection != null) {
                linkedHashMap.put(Long.valueOf(selectable.getSelectableId()), selectAllSelection);
            }
            selectionMerge = SelectionManagerKt.merge(selectionMerge, selectAllSelection);
        }
        if (!Intrinsics.areEqual(selectionMerge, previousSelection) && (hapticFeedback = this.hapticFeedBack) != null) {
            hapticFeedback.mo2523performHapticFeedbackCdsT49E(HapticFeedbackType.INSTANCE.m2532getTextHandleMove5zf0vsI());
        }
        return new Pair<>(selectionMerge, linkedHashMap);
    }

    public final AnnotatedString getSelectedText$foundation_release() {
        AnnotatedString annotatedStringPlus;
        List<Selectable> listSort = this.selectionRegistrar.sort(requireContainerCoordinates$foundation_release());
        Selection selection = getSelection();
        AnnotatedString annotatedString = null;
        if (selection == null) {
            return null;
        }
        int size = listSort.size();
        for (int i = 0; i < size; i++) {
            Selectable selectable = listSort.get(i);
            if (selectable.getSelectableId() == selection.getStart().getSelectableId() || selectable.getSelectableId() == selection.getEnd().getSelectableId() || annotatedString != null) {
                AnnotatedString currentSelectedText = SelectionManagerKt.getCurrentSelectedText(selectable, selection);
                if (annotatedString != null && (annotatedStringPlus = annotatedString.plus(currentSelectedText)) != null) {
                    currentSelectedText = annotatedStringPlus;
                }
                if ((selectable.getSelectableId() == selection.getEnd().getSelectableId() && !selection.getHandlesCrossed()) || (selectable.getSelectableId() == selection.getStart().getSelectableId() && selection.getHandlesCrossed())) {
                    return currentSelectedText;
                }
                annotatedString = currentSelectedText;
            }
        }
        return annotatedString;
    }

    public final void copy$foundation_release() {
        ClipboardManager clipboardManager;
        AnnotatedString selectedText$foundation_release = getSelectedText$foundation_release();
        if (selectedText$foundation_release == null || (clipboardManager = this.clipboardManager) == null) {
            return;
        }
        clipboardManager.setText(selectedText$foundation_release);
    }

    public final void showSelectionToolbar$foundation_release() {
        TextToolbar textToolbar;
        if (!getHasFocus() || getSelection() == null || (textToolbar = this.textToolbar) == null) {
            return;
        }
        TextToolbar.showMenu$default(textToolbar, getContentRect(), new Function0<Unit>() { // from class: androidx.compose.foundation.text.selection.SelectionManager$showSelectionToolbar$1$1
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
                this.this$0.copy$foundation_release();
                this.this$0.onRelease();
            }
        }, null, null, null, 28, null);
    }

    public final void hideSelectionToolbar$foundation_release() {
        TextToolbar textToolbar;
        if (getHasFocus()) {
            TextToolbar textToolbar2 = this.textToolbar;
            if ((textToolbar2 != null ? textToolbar2.getStatus() : null) != TextToolbarStatus.Shown || (textToolbar = this.textToolbar) == null) {
                return;
            }
            textToolbar.hide();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateSelectionToolbarPosition() {
        if (getHasFocus()) {
            TextToolbar textToolbar = this.textToolbar;
            if ((textToolbar != null ? textToolbar.getStatus() : null) == TextToolbarStatus.Shown) {
                showSelectionToolbar$foundation_release();
            }
        }
    }

    private final Rect getContentRect() {
        LayoutCoordinates layoutCoordinates;
        LayoutCoordinates layoutCoordinates2;
        Selection selection = getSelection();
        if (selection == null) {
            return Rect.INSTANCE.getZero();
        }
        Selectable anchorSelectable$foundation_release = getAnchorSelectable$foundation_release(selection.getStart());
        Selectable anchorSelectable$foundation_release2 = getAnchorSelectable$foundation_release(selection.getEnd());
        if (anchorSelectable$foundation_release == null || (layoutCoordinates = anchorSelectable$foundation_release.getLayoutCoordinates()) == null) {
            return Rect.INSTANCE.getZero();
        }
        if (anchorSelectable$foundation_release2 == null || (layoutCoordinates2 = anchorSelectable$foundation_release2.getLayoutCoordinates()) == null) {
            return Rect.INSTANCE.getZero();
        }
        LayoutCoordinates layoutCoordinates3 = this.containerLayoutCoordinates;
        if (layoutCoordinates3 != null && layoutCoordinates3.isAttached()) {
            long jMo3402localPositionOfR5De75A = layoutCoordinates3.mo3402localPositionOfR5De75A(layoutCoordinates, anchorSelectable$foundation_release.mo1078getHandlePositiondBAh8RU(selection, true));
            long jMo3402localPositionOfR5De75A2 = layoutCoordinates3.mo3402localPositionOfR5De75A(layoutCoordinates2, anchorSelectable$foundation_release2.mo1078getHandlePositiondBAh8RU(selection, false));
            long jMo3403localToRootMKHz9U = layoutCoordinates3.mo3403localToRootMKHz9U(jMo3402localPositionOfR5De75A);
            long jMo3403localToRootMKHz9U2 = layoutCoordinates3.mo3403localToRootMKHz9U(jMo3402localPositionOfR5De75A2);
            return new Rect(Math.min(Offset.m1639getXimpl(jMo3403localToRootMKHz9U), Offset.m1639getXimpl(jMo3403localToRootMKHz9U2)), Math.min(Offset.m1640getYimpl(layoutCoordinates3.mo3403localToRootMKHz9U(layoutCoordinates3.mo3402localPositionOfR5De75A(layoutCoordinates, OffsetKt.Offset(0.0f, anchorSelectable$foundation_release.getBoundingBox(selection.getStart().getOffset()).getTop())))), Offset.m1640getYimpl(layoutCoordinates3.mo3403localToRootMKHz9U(layoutCoordinates3.mo3402localPositionOfR5De75A(layoutCoordinates2, OffsetKt.Offset(0.0f, anchorSelectable$foundation_release2.getBoundingBox(selection.getEnd().getOffset()).getTop()))))), Math.max(Offset.m1639getXimpl(jMo3403localToRootMKHz9U), Offset.m1639getXimpl(jMo3403localToRootMKHz9U2)), Math.max(Offset.m1640getYimpl(jMo3403localToRootMKHz9U), Offset.m1640getYimpl(jMo3403localToRootMKHz9U2)) + ((float) (SelectionHandlesKt.getHandleHeight() * 4.0d)));
        }
        return Rect.INSTANCE.getZero();
    }

    public final void onRelease() {
        this.selectionRegistrar.setSubselections(MapsKt.emptyMap());
        hideSelectionToolbar$foundation_release();
        if (getSelection() != null) {
            this.onSelectionChange.invoke(null);
            HapticFeedback hapticFeedback = this.hapticFeedBack;
            if (hapticFeedback != null) {
                hapticFeedback.mo2523performHapticFeedbackCdsT49E(HapticFeedbackType.INSTANCE.m2532getTextHandleMove5zf0vsI());
            }
        }
    }

    public final TextDragObserver handleDragObserver(final boolean isStartHandle) {
        return new TextDragObserver() { // from class: androidx.compose.foundation.text.selection.SelectionManager.handleDragObserver.1
            @Override // androidx.compose.foundation.text.TextDragObserver
            /* renamed from: onDown-k-4lQ0M */
            public void mo1026onDownk4lQ0M(long point) {
                LayoutCoordinates layoutCoordinates;
                Selection selection = SelectionManager.this.getSelection();
                if (selection == null) {
                    return;
                }
                Selectable anchorSelectable$foundation_release = SelectionManager.this.getAnchorSelectable$foundation_release(isStartHandle ? selection.getStart() : selection.getEnd());
                if (anchorSelectable$foundation_release == null || (layoutCoordinates = anchorSelectable$foundation_release.getLayoutCoordinates()) == null) {
                    return;
                }
                long jM1097getAdjustedCoordinatesk4lQ0M = SelectionHandlesKt.m1097getAdjustedCoordinatesk4lQ0M(anchorSelectable$foundation_release.mo1078getHandlePositiondBAh8RU(selection, isStartHandle));
                SelectionManager selectionManager = SelectionManager.this;
                selectionManager.m1111setCurrentDragPosition_kEHs6E(Offset.m1628boximpl(selectionManager.requireContainerCoordinates$foundation_release().mo3402localPositionOfR5De75A(layoutCoordinates, jM1097getAdjustedCoordinatesk4lQ0M)));
                SelectionManager.this.setDraggingHandle(isStartHandle ? Handle.SelectionStart : Handle.SelectionEnd);
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onUp() {
                SelectionManager.this.setDraggingHandle(null);
                SelectionManager.this.m1111setCurrentDragPosition_kEHs6E(null);
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            /* renamed from: onStart-k-4lQ0M */
            public void mo1028onStartk4lQ0M(long startPoint) {
                LayoutCoordinates layoutCoordinates;
                long jMo1078getHandlePositiondBAh8RU;
                SelectionManager.this.hideSelectionToolbar$foundation_release();
                Selection selection = SelectionManager.this.getSelection();
                Intrinsics.checkNotNull(selection);
                Selectable selectable = SelectionManager.this.selectionRegistrar.getSelectableMap$foundation_release().get(Long.valueOf(selection.getStart().getSelectableId()));
                Selectable selectable2 = SelectionManager.this.selectionRegistrar.getSelectableMap$foundation_release().get(Long.valueOf(selection.getEnd().getSelectableId()));
                if (isStartHandle) {
                    layoutCoordinates = selectable != null ? selectable.getLayoutCoordinates() : null;
                    Intrinsics.checkNotNull(layoutCoordinates);
                } else {
                    layoutCoordinates = selectable2 != null ? selectable2.getLayoutCoordinates() : null;
                    Intrinsics.checkNotNull(layoutCoordinates);
                }
                if (isStartHandle) {
                    Intrinsics.checkNotNull(selectable);
                    jMo1078getHandlePositiondBAh8RU = selectable.mo1078getHandlePositiondBAh8RU(selection, true);
                } else {
                    Intrinsics.checkNotNull(selectable2);
                    jMo1078getHandlePositiondBAh8RU = selectable2.mo1078getHandlePositiondBAh8RU(selection, false);
                }
                long jM1097getAdjustedCoordinatesk4lQ0M = SelectionHandlesKt.m1097getAdjustedCoordinatesk4lQ0M(jMo1078getHandlePositiondBAh8RU);
                SelectionManager selectionManager = SelectionManager.this;
                selectionManager.m1112setDragBeginPositionk4lQ0M(selectionManager.requireContainerCoordinates$foundation_release().mo3402localPositionOfR5De75A(layoutCoordinates, jM1097getAdjustedCoordinatesk4lQ0M));
                SelectionManager.this.m1113setDragTotalDistancek4lQ0M(Offset.INSTANCE.m1655getZeroF1C5BW0());
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            /* renamed from: onDrag-k-4lQ0M */
            public void mo1027onDragk4lQ0M(long delta) {
                SelectionManager selectionManager = SelectionManager.this;
                selectionManager.m1113setDragTotalDistancek4lQ0M(Offset.m1644plusMKHz9U(selectionManager.m1120getDragTotalDistanceF1C5BW0$foundation_release(), delta));
                long jM1644plusMKHz9U = Offset.m1644plusMKHz9U(SelectionManager.this.m1119getDragBeginPositionF1C5BW0$foundation_release(), SelectionManager.this.m1120getDragTotalDistanceF1C5BW0$foundation_release());
                if (SelectionManager.this.m1124updateSelectionRHHTvR4$foundation_release(Offset.m1628boximpl(jM1644plusMKHz9U), Offset.m1628boximpl(SelectionManager.this.m1119getDragBeginPositionF1C5BW0$foundation_release()), isStartHandle, SelectionAdjustment.INSTANCE.getCharacterWithWordAccelerate())) {
                    SelectionManager.this.m1112setDragBeginPositionk4lQ0M(jM1644plusMKHz9U);
                    SelectionManager.this.m1113setDragTotalDistancek4lQ0M(Offset.INSTANCE.m1655getZeroF1C5BW0());
                }
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onStop() {
                SelectionManager.this.showSelectionToolbar$foundation_release();
                SelectionManager.this.setDraggingHandle(null);
                SelectionManager.this.m1111setCurrentDragPosition_kEHs6E(null);
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onCancel() {
                SelectionManager.this.showSelectionToolbar$foundation_release();
                SelectionManager.this.setDraggingHandle(null);
                SelectionManager.this.m1111setCurrentDragPosition_kEHs6E(null);
            }
        };
    }

    /* compiled from: SelectionManager.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionManager$detectNonConsumingTap$2", f = "SelectionManager.kt", i = {}, l = {IptcDirectory.TAG_SOURCE}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.compose.foundation.text.selection.SelectionManager$detectNonConsumingTap$2, reason: invalid class name and case insensitive filesystem */
    static final class C03742 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function1<Offset, Unit> $onTap;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C03742(Function1<? super Offset, Unit> function1, Continuation<? super C03742> continuation) {
            super(2, continuation);
            this.$onTap = function1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C03742 c03742 = new C03742(this.$onTap, continuation);
            c03742.L$0 = obj;
            return c03742;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
            return ((C03742) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = TapGestureDetectorKt.waitForUpOrCancellation$default((AwaitPointerEventScope) this.L$0, null, this, 1, null);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            PointerInputChange pointerInputChange = (PointerInputChange) obj;
            if (pointerInputChange != null) {
                this.$onTap.invoke(Offset.m1628boximpl(pointerInputChange.getPosition()));
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object detectNonConsumingTap(PointerInputScope pointerInputScope, Function1<? super Offset, Unit> function1, Continuation<? super Unit> continuation) {
        Object objAwaitEachGesture = ForEachGestureKt.awaitEachGesture(pointerInputScope, new C03742(function1, null), continuation);
        return objAwaitEachGesture == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAwaitEachGesture : Unit.INSTANCE;
    }

    /* compiled from: SelectionManager.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/PointerInputScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionManager$onClearSelectionRequested$1", f = "SelectionManager.kt", i = {}, l = {IptcDirectory.TAG_CAPTION_WRITER}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.compose.foundation.text.selection.SelectionManager$onClearSelectionRequested$1, reason: invalid class name and case insensitive filesystem */
    static final class C03761 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function0<Unit> $block;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03761(Function0<Unit> function0, Continuation<? super C03761> continuation) {
            super(2, continuation);
            this.$block = function0;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C03761 c03761 = SelectionManager.this.new C03761(this.$block, continuation);
            c03761.L$0 = obj;
            return c03761;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
            return ((C03761) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                PointerInputScope pointerInputScope = (PointerInputScope) this.L$0;
                SelectionManager selectionManager = SelectionManager.this;
                final Function0<Unit> function0 = this.$block;
                this.label = 1;
                if (selectionManager.detectNonConsumingTap(pointerInputScope, new Function1<Offset, Unit>() { // from class: androidx.compose.foundation.text.selection.SelectionManager.onClearSelectionRequested.1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Offset offset) {
                        m1128invokek4lQ0M(offset.getPackedValue());
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke-k-4lQ0M, reason: not valid java name */
                    public final void m1128invokek4lQ0M(long j) {
                        function0.invoke();
                    }
                }, this) == coroutine_suspended) {
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

    private final Modifier onClearSelectionRequested(Modifier modifier, Function0<Unit> function0) {
        return getHasFocus() ? SuspendingPointerInputFilterKt.pointerInput(modifier, Unit.INSTANCE, new C03761(function0, null)) : modifier;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: convertToContainerCoordinates-Q7Q5hAU, reason: not valid java name */
    public final Offset m1110convertToContainerCoordinatesQ7Q5hAU(LayoutCoordinates layoutCoordinates, long offset) {
        LayoutCoordinates layoutCoordinates2 = this.containerLayoutCoordinates;
        if (layoutCoordinates2 == null || !layoutCoordinates2.isAttached()) {
            return null;
        }
        return Offset.m1628boximpl(requireContainerCoordinates$foundation_release().mo3402localPositionOfR5De75A(layoutCoordinates, offset));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: startSelection-9KIMszo, reason: not valid java name */
    public final void m1116startSelection9KIMszo(long position, boolean isStartHandle, SelectionAdjustment adjustment) {
        m1123updateSelection3R_tFg$foundation_release(position, position, null, isStartHandle, adjustment);
    }

    /* renamed from: updateSelection-RHHTvR4$foundation_release, reason: not valid java name */
    public final boolean m1124updateSelectionRHHTvR4$foundation_release(Offset newPosition, Offset previousPosition, boolean isStartHandle, SelectionAdjustment adjustment) {
        Selection selection;
        long selectableId;
        Offset offsetM1110convertToContainerCoordinatesQ7Q5hAU;
        Intrinsics.checkNotNullParameter(adjustment, "adjustment");
        if (newPosition == null || (selection = getSelection()) == null) {
            return false;
        }
        if (isStartHandle) {
            selectableId = selection.getEnd().getSelectableId();
        } else {
            selectableId = selection.getStart().getSelectableId();
        }
        Selectable selectable = this.selectionRegistrar.getSelectableMap$foundation_release().get(Long.valueOf(selectableId));
        if (selectable == null) {
            offsetM1110convertToContainerCoordinatesQ7Q5hAU = null;
        } else {
            LayoutCoordinates layoutCoordinates = selectable.getLayoutCoordinates();
            Intrinsics.checkNotNull(layoutCoordinates);
            offsetM1110convertToContainerCoordinatesQ7Q5hAU = m1110convertToContainerCoordinatesQ7Q5hAU(layoutCoordinates, SelectionHandlesKt.m1097getAdjustedCoordinatesk4lQ0M(selectable.mo1078getHandlePositiondBAh8RU(selection, !isStartHandle)));
        }
        if (offsetM1110convertToContainerCoordinatesQ7Q5hAU == null) {
            return false;
        }
        long packedValue = offsetM1110convertToContainerCoordinatesQ7Q5hAU.getPackedValue();
        long packedValue2 = isStartHandle ? newPosition.getPackedValue() : packedValue;
        if (!isStartHandle) {
            packedValue = newPosition.getPackedValue();
        }
        return m1123updateSelection3R_tFg$foundation_release(packedValue2, packedValue, previousPosition, isStartHandle, adjustment);
    }

    /* renamed from: updateSelection-3R_-tFg$foundation_release, reason: not valid java name */
    public final boolean m1123updateSelection3R_tFg$foundation_release(long startHandlePosition, long endHandlePosition, Offset previousHandlePosition, boolean isStartHandle, SelectionAdjustment adjustment) {
        Intrinsics.checkNotNullParameter(adjustment, "adjustment");
        setDraggingHandle(isStartHandle ? Handle.SelectionStart : Handle.SelectionEnd);
        m1111setCurrentDragPosition_kEHs6E(isStartHandle ? Offset.m1628boximpl(startHandlePosition) : Offset.m1628boximpl(endHandlePosition));
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        List<Selectable> listSort = this.selectionRegistrar.sort(requireContainerCoordinates$foundation_release());
        int size = listSort.size();
        Selection selectionMerge = null;
        int i = 0;
        boolean z = false;
        while (i < size) {
            Selectable selectable = listSort.get(i);
            int i2 = i;
            Selection selection = selectionMerge;
            Pair<Selection, Boolean> pairMo1080updateSelectionqCDeeow = selectable.mo1080updateSelectionqCDeeow(startHandlePosition, endHandlePosition, previousHandlePosition, isStartHandle, requireContainerCoordinates$foundation_release(), adjustment, this.selectionRegistrar.getSubselections().get(Long.valueOf(selectable.getSelectableId())));
            Selection selectionComponent1 = pairMo1080updateSelectionqCDeeow.component1();
            z = z || pairMo1080updateSelectionqCDeeow.component2().booleanValue();
            if (selectionComponent1 != null) {
                linkedHashMap.put(Long.valueOf(selectable.getSelectableId()), selectionComponent1);
            }
            selectionMerge = SelectionManagerKt.merge(selection, selectionComponent1);
            i = i2 + 1;
        }
        Selection selection2 = selectionMerge;
        if (!Intrinsics.areEqual(selection2, getSelection())) {
            HapticFeedback hapticFeedback = this.hapticFeedBack;
            if (hapticFeedback != null) {
                hapticFeedback.mo2523performHapticFeedbackCdsT49E(HapticFeedbackType.INSTANCE.m2532getTextHandleMove5zf0vsI());
            }
            this.selectionRegistrar.setSubselections(linkedHashMap);
            this.onSelectionChange.invoke(selection2);
        }
        return z;
    }

    /* renamed from: contextMenuOpenAdjustment-k-4lQ0M, reason: not valid java name */
    public final void m1117contextMenuOpenAdjustmentk4lQ0M(long position) {
        Selection selection = getSelection();
        if (selection == null || TextRange.m3899getCollapsedimpl(selection.m1087toTextRanged9O1mEE())) {
            m1116startSelection9KIMszo(position, true, SelectionAdjustment.INSTANCE.getWord());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setStartHandlePosition-_kEHs6E, reason: not valid java name */
    public final void m1115setStartHandlePosition_kEHs6E(Offset offset) {
        this.startHandlePosition.setValue(offset);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setEndHandlePosition-_kEHs6E, reason: not valid java name */
    public final void m1114setEndHandlePosition_kEHs6E(Offset offset) {
        this.endHandlePosition.setValue(offset);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setDraggingHandle(Handle handle) {
        this.draggingHandle.setValue(handle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setCurrentDragPosition-_kEHs6E, reason: not valid java name */
    public final void m1111setCurrentDragPosition_kEHs6E(Offset offset) {
        this.currentDragPosition.setValue(offset);
    }
}
