package androidx.compose.foundation.text;

import android.view.KeyEvent;
import androidx.compose.foundation.text.selection.TextFieldPreparedSelection;
import androidx.compose.foundation.text.selection.TextFieldSelectionManager;
import androidx.compose.foundation.text.selection.TextPreparedSelectionState;
import androidx.compose.ui.input.key.KeyEventType;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.input.CommitTextCommand;
import androidx.compose.ui.text.input.DeleteSurroundingTextCommand;
import androidx.compose.ui.text.input.EditCommand;
import androidx.compose.ui.text.input.EditProcessor;
import androidx.compose.ui.text.input.FinishComposingTextCommand;
import androidx.compose.ui.text.input.OffsetMapping;
import androidx.compose.ui.text.input.TextFieldValue;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* compiled from: TextFieldKeyInput.kt */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\b\u0000\u0018\u00002\u00020\u0001By\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0014\u0012\u0014\b\u0002\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00170\u0016¢\u0006\u0002\u0010\u0018J!\u0010(\u001a\u00020\u00172\u0017\u0010)\u001a\u0013\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020\u00170\u0016¢\u0006\u0002\b+H\u0002J\u001b\u0010,\u001a\u00020\t2\u0006\u0010-\u001a\u00020.ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b/\u00100J\u001f\u00101\u001a\u0004\u0018\u0001022\u0006\u0010-\u001a\u00020.H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b3\u00104J\f\u00105\u001a\u00020\u0017*\u000206H\u0002J\u0012\u00105\u001a\u00020\u0017*\b\u0012\u0004\u0012\u00020607H\u0002R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u001a\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00170\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\n\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001aR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u00068"}, d2 = {"Landroidx/compose/foundation/text/TextFieldKeyInput;", "", "state", "Landroidx/compose/foundation/text/TextFieldState;", "selectionManager", "Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;", "value", "Landroidx/compose/ui/text/input/TextFieldValue;", "editable", "", "singleLine", "preparedSelectionState", "Landroidx/compose/foundation/text/selection/TextPreparedSelectionState;", "offsetMapping", "Landroidx/compose/ui/text/input/OffsetMapping;", "undoManager", "Landroidx/compose/foundation/text/UndoManager;", "keyCombiner", "Landroidx/compose/foundation/text/DeadKeyCombiner;", "keyMapping", "Landroidx/compose/foundation/text/KeyMapping;", "onValueChange", "Lkotlin/Function1;", "", "(Landroidx/compose/foundation/text/TextFieldState;Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;Landroidx/compose/ui/text/input/TextFieldValue;ZZLandroidx/compose/foundation/text/selection/TextPreparedSelectionState;Landroidx/compose/ui/text/input/OffsetMapping;Landroidx/compose/foundation/text/UndoManager;Landroidx/compose/foundation/text/DeadKeyCombiner;Landroidx/compose/foundation/text/KeyMapping;Lkotlin/jvm/functions/Function1;)V", "getEditable", "()Z", "getOffsetMapping", "()Landroidx/compose/ui/text/input/OffsetMapping;", "getPreparedSelectionState", "()Landroidx/compose/foundation/text/selection/TextPreparedSelectionState;", "getSelectionManager", "()Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;", "getSingleLine", "getState", "()Landroidx/compose/foundation/text/TextFieldState;", "getUndoManager", "()Landroidx/compose/foundation/text/UndoManager;", "getValue", "()Landroidx/compose/ui/text/input/TextFieldValue;", "commandExecutionContext", "block", "Landroidx/compose/foundation/text/selection/TextFieldPreparedSelection;", "Lkotlin/ExtensionFunctionType;", "process", "event", "Landroidx/compose/ui/input/key/KeyEvent;", "process-ZmokQxo", "(Landroid/view/KeyEvent;)Z", "typedCommand", "Landroidx/compose/ui/text/input/CommitTextCommand;", "typedCommand-ZmokQxo", "(Landroid/view/KeyEvent;)Landroidx/compose/ui/text/input/CommitTextCommand;", "apply", "Landroidx/compose/ui/text/input/EditCommand;", "", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class TextFieldKeyInput {
    private final boolean editable;
    private final DeadKeyCombiner keyCombiner;
    private final KeyMapping keyMapping;
    private final OffsetMapping offsetMapping;
    private final Function1<TextFieldValue, Unit> onValueChange;
    private final TextPreparedSelectionState preparedSelectionState;
    private final TextFieldSelectionManager selectionManager;
    private final boolean singleLine;
    private final TextFieldState state;
    private final UndoManager undoManager;
    private final TextFieldValue value;

    public final boolean getEditable() {
        return this.editable;
    }

    public final OffsetMapping getOffsetMapping() {
        return this.offsetMapping;
    }

    public final TextPreparedSelectionState getPreparedSelectionState() {
        return this.preparedSelectionState;
    }

    public final TextFieldSelectionManager getSelectionManager() {
        return this.selectionManager;
    }

    public final boolean getSingleLine() {
        return this.singleLine;
    }

    public final TextFieldState getState() {
        return this.state;
    }

    public final UndoManager getUndoManager() {
        return this.undoManager;
    }

    public final TextFieldValue getValue() {
        return this.value;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public TextFieldKeyInput(TextFieldState state, TextFieldSelectionManager selectionManager, TextFieldValue value, boolean z, boolean z2, TextPreparedSelectionState preparedSelectionState, OffsetMapping offsetMapping, UndoManager undoManager, DeadKeyCombiner keyCombiner, KeyMapping keyMapping, Function1<? super TextFieldValue, Unit> onValueChange) {
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(selectionManager, "selectionManager");
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(preparedSelectionState, "preparedSelectionState");
        Intrinsics.checkNotNullParameter(offsetMapping, "offsetMapping");
        Intrinsics.checkNotNullParameter(keyCombiner, "keyCombiner");
        Intrinsics.checkNotNullParameter(keyMapping, "keyMapping");
        Intrinsics.checkNotNullParameter(onValueChange, "onValueChange");
        this.state = state;
        this.selectionManager = selectionManager;
        this.value = value;
        this.editable = z;
        this.singleLine = z2;
        this.preparedSelectionState = preparedSelectionState;
        this.offsetMapping = offsetMapping;
        this.undoManager = undoManager;
        this.keyCombiner = keyCombiner;
        this.keyMapping = keyMapping;
        this.onValueChange = onValueChange;
    }

    public /* synthetic */ TextFieldKeyInput(TextFieldState textFieldState, TextFieldSelectionManager textFieldSelectionManager, TextFieldValue textFieldValue, boolean z, boolean z2, TextPreparedSelectionState textPreparedSelectionState, OffsetMapping offsetMapping, UndoManager undoManager, DeadKeyCombiner deadKeyCombiner, KeyMapping keyMapping, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(textFieldState, textFieldSelectionManager, (i & 4) != 0 ? new TextFieldValue((String) null, 0L, (TextRange) null, 7, (DefaultConstructorMarker) null) : textFieldValue, (i & 8) != 0 ? true : z, (i & 16) != 0 ? false : z2, textPreparedSelectionState, (i & 64) != 0 ? OffsetMapping.INSTANCE.getIdentity() : offsetMapping, (i & 128) != 0 ? null : undoManager, deadKeyCombiner, (i & 512) != 0 ? KeyMapping_androidKt.getPlatformDefaultKeyMapping() : keyMapping, (i & 1024) != 0 ? new Function1<TextFieldValue, Unit>() { // from class: androidx.compose.foundation.text.TextFieldKeyInput.1
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(TextFieldValue it) {
                Intrinsics.checkNotNullParameter(it, "it");
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(TextFieldValue textFieldValue2) {
                invoke2(textFieldValue2);
                return Unit.INSTANCE;
            }
        } : function1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void apply(List<? extends EditCommand> list) {
        EditProcessor processor = this.state.getProcessor();
        List<? extends EditCommand> mutableList = CollectionsKt.toMutableList((Collection) list);
        mutableList.add(0, new FinishComposingTextCommand());
        this.onValueChange.invoke(processor.apply(mutableList));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void apply(EditCommand editCommand) {
        apply(CollectionsKt.listOf(editCommand));
    }

    /* renamed from: typedCommand-ZmokQxo, reason: not valid java name */
    private final CommitTextCommand m1044typedCommandZmokQxo(KeyEvent event) {
        Integer numM986consumeZmokQxo;
        if (!TextFieldKeyInput_androidKt.m1047isTypedEventZmokQxo(event) || (numM986consumeZmokQxo = this.keyCombiner.m986consumeZmokQxo(event)) == null) {
            return null;
        }
        String string = StringHelpers_jvmKt.appendCodePointX(new StringBuilder(), numM986consumeZmokQxo.intValue()).toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().appendCo…ntX(codePoint).toString()");
        return new CommitTextCommand(string, 1);
    }

    /* renamed from: process-ZmokQxo, reason: not valid java name */
    public final boolean m1045processZmokQxo(KeyEvent event) {
        final KeyCommand keyCommandMo988mapZmokQxo;
        Intrinsics.checkNotNullParameter(event, "event");
        CommitTextCommand commitTextCommandM1044typedCommandZmokQxo = m1044typedCommandZmokQxo(event);
        if (commitTextCommandM1044typedCommandZmokQxo != null) {
            if (!this.editable) {
                return false;
            }
            apply(commitTextCommandM1044typedCommandZmokQxo);
            this.preparedSelectionState.resetCachedX();
            return true;
        }
        if (!KeyEventType.m3138equalsimpl0(KeyEvent_androidKt.m3146getTypeZmokQxo(event), KeyEventType.INSTANCE.m3142getKeyDownCS__XNY()) || (keyCommandMo988mapZmokQxo = this.keyMapping.mo988mapZmokQxo(event)) == null || (keyCommandMo988mapZmokQxo.getEditsText() && !this.editable)) {
            return false;
        }
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        booleanRef.element = true;
        commandExecutionContext(new Function1<TextFieldPreparedSelection, Unit>() { // from class: androidx.compose.foundation.text.TextFieldKeyInput$process$2

            /* compiled from: TextFieldKeyInput.kt */
            @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[KeyCommand.values().length];
                    try {
                        iArr[KeyCommand.COPY.ordinal()] = 1;
                    } catch (NoSuchFieldError unused) {
                    }
                    try {
                        iArr[KeyCommand.PASTE.ordinal()] = 2;
                    } catch (NoSuchFieldError unused2) {
                    }
                    try {
                        iArr[KeyCommand.CUT.ordinal()] = 3;
                    } catch (NoSuchFieldError unused3) {
                    }
                    try {
                        iArr[KeyCommand.LEFT_CHAR.ordinal()] = 4;
                    } catch (NoSuchFieldError unused4) {
                    }
                    try {
                        iArr[KeyCommand.RIGHT_CHAR.ordinal()] = 5;
                    } catch (NoSuchFieldError unused5) {
                    }
                    try {
                        iArr[KeyCommand.LEFT_WORD.ordinal()] = 6;
                    } catch (NoSuchFieldError unused6) {
                    }
                    try {
                        iArr[KeyCommand.RIGHT_WORD.ordinal()] = 7;
                    } catch (NoSuchFieldError unused7) {
                    }
                    try {
                        iArr[KeyCommand.PREV_PARAGRAPH.ordinal()] = 8;
                    } catch (NoSuchFieldError unused8) {
                    }
                    try {
                        iArr[KeyCommand.NEXT_PARAGRAPH.ordinal()] = 9;
                    } catch (NoSuchFieldError unused9) {
                    }
                    try {
                        iArr[KeyCommand.UP.ordinal()] = 10;
                    } catch (NoSuchFieldError unused10) {
                    }
                    try {
                        iArr[KeyCommand.DOWN.ordinal()] = 11;
                    } catch (NoSuchFieldError unused11) {
                    }
                    try {
                        iArr[KeyCommand.PAGE_UP.ordinal()] = 12;
                    } catch (NoSuchFieldError unused12) {
                    }
                    try {
                        iArr[KeyCommand.PAGE_DOWN.ordinal()] = 13;
                    } catch (NoSuchFieldError unused13) {
                    }
                    try {
                        iArr[KeyCommand.LINE_START.ordinal()] = 14;
                    } catch (NoSuchFieldError unused14) {
                    }
                    try {
                        iArr[KeyCommand.LINE_END.ordinal()] = 15;
                    } catch (NoSuchFieldError unused15) {
                    }
                    try {
                        iArr[KeyCommand.LINE_LEFT.ordinal()] = 16;
                    } catch (NoSuchFieldError unused16) {
                    }
                    try {
                        iArr[KeyCommand.LINE_RIGHT.ordinal()] = 17;
                    } catch (NoSuchFieldError unused17) {
                    }
                    try {
                        iArr[KeyCommand.HOME.ordinal()] = 18;
                    } catch (NoSuchFieldError unused18) {
                    }
                    try {
                        iArr[KeyCommand.END.ordinal()] = 19;
                    } catch (NoSuchFieldError unused19) {
                    }
                    try {
                        iArr[KeyCommand.DELETE_PREV_CHAR.ordinal()] = 20;
                    } catch (NoSuchFieldError unused20) {
                    }
                    try {
                        iArr[KeyCommand.DELETE_NEXT_CHAR.ordinal()] = 21;
                    } catch (NoSuchFieldError unused21) {
                    }
                    try {
                        iArr[KeyCommand.DELETE_PREV_WORD.ordinal()] = 22;
                    } catch (NoSuchFieldError unused22) {
                    }
                    try {
                        iArr[KeyCommand.DELETE_NEXT_WORD.ordinal()] = 23;
                    } catch (NoSuchFieldError unused23) {
                    }
                    try {
                        iArr[KeyCommand.DELETE_FROM_LINE_START.ordinal()] = 24;
                    } catch (NoSuchFieldError unused24) {
                    }
                    try {
                        iArr[KeyCommand.DELETE_TO_LINE_END.ordinal()] = 25;
                    } catch (NoSuchFieldError unused25) {
                    }
                    try {
                        iArr[KeyCommand.NEW_LINE.ordinal()] = 26;
                    } catch (NoSuchFieldError unused26) {
                    }
                    try {
                        iArr[KeyCommand.TAB.ordinal()] = 27;
                    } catch (NoSuchFieldError unused27) {
                    }
                    try {
                        iArr[KeyCommand.SELECT_ALL.ordinal()] = 28;
                    } catch (NoSuchFieldError unused28) {
                    }
                    try {
                        iArr[KeyCommand.SELECT_LEFT_CHAR.ordinal()] = 29;
                    } catch (NoSuchFieldError unused29) {
                    }
                    try {
                        iArr[KeyCommand.SELECT_RIGHT_CHAR.ordinal()] = 30;
                    } catch (NoSuchFieldError unused30) {
                    }
                    try {
                        iArr[KeyCommand.SELECT_LEFT_WORD.ordinal()] = 31;
                    } catch (NoSuchFieldError unused31) {
                    }
                    try {
                        iArr[KeyCommand.SELECT_RIGHT_WORD.ordinal()] = 32;
                    } catch (NoSuchFieldError unused32) {
                    }
                    try {
                        iArr[KeyCommand.SELECT_PREV_PARAGRAPH.ordinal()] = 33;
                    } catch (NoSuchFieldError unused33) {
                    }
                    try {
                        iArr[KeyCommand.SELECT_NEXT_PARAGRAPH.ordinal()] = 34;
                    } catch (NoSuchFieldError unused34) {
                    }
                    try {
                        iArr[KeyCommand.SELECT_LINE_START.ordinal()] = 35;
                    } catch (NoSuchFieldError unused35) {
                    }
                    try {
                        iArr[KeyCommand.SELECT_LINE_END.ordinal()] = 36;
                    } catch (NoSuchFieldError unused36) {
                    }
                    try {
                        iArr[KeyCommand.SELECT_LINE_LEFT.ordinal()] = 37;
                    } catch (NoSuchFieldError unused37) {
                    }
                    try {
                        iArr[KeyCommand.SELECT_LINE_RIGHT.ordinal()] = 38;
                    } catch (NoSuchFieldError unused38) {
                    }
                    try {
                        iArr[KeyCommand.SELECT_UP.ordinal()] = 39;
                    } catch (NoSuchFieldError unused39) {
                    }
                    try {
                        iArr[KeyCommand.SELECT_DOWN.ordinal()] = 40;
                    } catch (NoSuchFieldError unused40) {
                    }
                    try {
                        iArr[KeyCommand.SELECT_PAGE_UP.ordinal()] = 41;
                    } catch (NoSuchFieldError unused41) {
                    }
                    try {
                        iArr[KeyCommand.SELECT_PAGE_DOWN.ordinal()] = 42;
                    } catch (NoSuchFieldError unused42) {
                    }
                    try {
                        iArr[KeyCommand.SELECT_HOME.ordinal()] = 43;
                    } catch (NoSuchFieldError unused43) {
                    }
                    try {
                        iArr[KeyCommand.SELECT_END.ordinal()] = 44;
                    } catch (NoSuchFieldError unused44) {
                    }
                    try {
                        iArr[KeyCommand.DESELECT.ordinal()] = 45;
                    } catch (NoSuchFieldError unused45) {
                    }
                    try {
                        iArr[KeyCommand.UNDO.ordinal()] = 46;
                    } catch (NoSuchFieldError unused46) {
                    }
                    try {
                        iArr[KeyCommand.REDO.ordinal()] = 47;
                    } catch (NoSuchFieldError unused47) {
                    }
                    try {
                        iArr[KeyCommand.CHARACTER_PALETTE.ordinal()] = 48;
                    } catch (NoSuchFieldError unused48) {
                    }
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(TextFieldPreparedSelection textFieldPreparedSelection) {
                invoke2(textFieldPreparedSelection);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(TextFieldPreparedSelection commandExecutionContext) {
                TextFieldValue textFieldValueUndo;
                TextFieldValue textFieldValueRedo;
                Intrinsics.checkNotNullParameter(commandExecutionContext, "$this$commandExecutionContext");
                switch (WhenMappings.$EnumSwitchMapping$0[keyCommandMo988mapZmokQxo.ordinal()]) {
                    case 1:
                        this.getSelectionManager().copy$foundation_release(false);
                        break;
                    case 2:
                        this.getSelectionManager().paste$foundation_release();
                        break;
                    case 3:
                        this.getSelectionManager().cut$foundation_release();
                        break;
                    case 4:
                        commandExecutionContext.collapseLeftOr(new Function1<TextFieldPreparedSelection, Unit>() { // from class: androidx.compose.foundation.text.TextFieldKeyInput$process$2.1
                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextFieldPreparedSelection textFieldPreparedSelection) {
                                invoke2(textFieldPreparedSelection);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextFieldPreparedSelection collapseLeftOr) {
                                Intrinsics.checkNotNullParameter(collapseLeftOr, "$this$collapseLeftOr");
                                collapseLeftOr.moveCursorLeft();
                            }
                        });
                        break;
                    case 5:
                        commandExecutionContext.collapseRightOr(new Function1<TextFieldPreparedSelection, Unit>() { // from class: androidx.compose.foundation.text.TextFieldKeyInput$process$2.2
                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(TextFieldPreparedSelection textFieldPreparedSelection) {
                                invoke2(textFieldPreparedSelection);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(TextFieldPreparedSelection collapseRightOr) {
                                Intrinsics.checkNotNullParameter(collapseRightOr, "$this$collapseRightOr");
                                collapseRightOr.moveCursorRight();
                            }
                        });
                        break;
                    case 6:
                        commandExecutionContext.moveCursorLeftByWord();
                        break;
                    case 7:
                        commandExecutionContext.moveCursorRightByWord();
                        break;
                    case 8:
                        commandExecutionContext.moveCursorPrevByParagraph();
                        break;
                    case 9:
                        commandExecutionContext.moveCursorNextByParagraph();
                        break;
                    case 10:
                        commandExecutionContext.moveCursorUpByLine();
                        break;
                    case 11:
                        commandExecutionContext.moveCursorDownByLine();
                        break;
                    case 12:
                        commandExecutionContext.moveCursorUpByPage();
                        break;
                    case 13:
                        commandExecutionContext.moveCursorDownByPage();
                        break;
                    case 14:
                        commandExecutionContext.moveCursorToLineStart();
                        break;
                    case 15:
                        commandExecutionContext.moveCursorToLineEnd();
                        break;
                    case 16:
                        commandExecutionContext.moveCursorToLineLeftSide();
                        break;
                    case 17:
                        commandExecutionContext.moveCursorToLineRightSide();
                        break;
                    case 18:
                        commandExecutionContext.moveCursorToHome();
                        break;
                    case 19:
                        commandExecutionContext.moveCursorToEnd();
                        break;
                    case 20:
                        List<EditCommand> listDeleteIfSelectedOr = commandExecutionContext.deleteIfSelectedOr(new Function1<TextFieldPreparedSelection, EditCommand>() { // from class: androidx.compose.foundation.text.TextFieldKeyInput$process$2.3
                            @Override // kotlin.jvm.functions.Function1
                            public final EditCommand invoke(TextFieldPreparedSelection deleteIfSelectedOr) {
                                Intrinsics.checkNotNullParameter(deleteIfSelectedOr, "$this$deleteIfSelectedOr");
                                return new DeleteSurroundingTextCommand(TextRange.m3900getEndimpl(deleteIfSelectedOr.getSelection()) - deleteIfSelectedOr.getPrecedingCharacterIndex(), 0);
                            }
                        });
                        if (listDeleteIfSelectedOr != null) {
                            this.apply((List<? extends EditCommand>) listDeleteIfSelectedOr);
                            break;
                        }
                        break;
                    case 21:
                        List<EditCommand> listDeleteIfSelectedOr2 = commandExecutionContext.deleteIfSelectedOr(new Function1<TextFieldPreparedSelection, EditCommand>() { // from class: androidx.compose.foundation.text.TextFieldKeyInput$process$2.4
                            @Override // kotlin.jvm.functions.Function1
                            public final EditCommand invoke(TextFieldPreparedSelection deleteIfSelectedOr) {
                                Intrinsics.checkNotNullParameter(deleteIfSelectedOr, "$this$deleteIfSelectedOr");
                                int nextCharacterIndex = deleteIfSelectedOr.getNextCharacterIndex();
                                if (nextCharacterIndex != -1) {
                                    return new DeleteSurroundingTextCommand(0, nextCharacterIndex - TextRange.m3900getEndimpl(deleteIfSelectedOr.getSelection()));
                                }
                                return null;
                            }
                        });
                        if (listDeleteIfSelectedOr2 != null) {
                            this.apply((List<? extends EditCommand>) listDeleteIfSelectedOr2);
                            break;
                        }
                        break;
                    case 22:
                        List<EditCommand> listDeleteIfSelectedOr3 = commandExecutionContext.deleteIfSelectedOr(new Function1<TextFieldPreparedSelection, EditCommand>() { // from class: androidx.compose.foundation.text.TextFieldKeyInput$process$2.5
                            @Override // kotlin.jvm.functions.Function1
                            public final EditCommand invoke(TextFieldPreparedSelection deleteIfSelectedOr) {
                                DeleteSurroundingTextCommand deleteSurroundingTextCommand;
                                Intrinsics.checkNotNullParameter(deleteIfSelectedOr, "$this$deleteIfSelectedOr");
                                Integer previousWordOffset = deleteIfSelectedOr.getPreviousWordOffset();
                                if (previousWordOffset != null) {
                                    deleteSurroundingTextCommand = new DeleteSurroundingTextCommand(TextRange.m3900getEndimpl(deleteIfSelectedOr.getSelection()) - previousWordOffset.intValue(), 0);
                                } else {
                                    deleteSurroundingTextCommand = null;
                                }
                                return deleteSurroundingTextCommand;
                            }
                        });
                        if (listDeleteIfSelectedOr3 != null) {
                            this.apply((List<? extends EditCommand>) listDeleteIfSelectedOr3);
                            break;
                        }
                        break;
                    case 23:
                        List<EditCommand> listDeleteIfSelectedOr4 = commandExecutionContext.deleteIfSelectedOr(new Function1<TextFieldPreparedSelection, EditCommand>() { // from class: androidx.compose.foundation.text.TextFieldKeyInput$process$2.6
                            @Override // kotlin.jvm.functions.Function1
                            public final EditCommand invoke(TextFieldPreparedSelection deleteIfSelectedOr) {
                                Intrinsics.checkNotNullParameter(deleteIfSelectedOr, "$this$deleteIfSelectedOr");
                                Integer nextWordOffset = deleteIfSelectedOr.getNextWordOffset();
                                return nextWordOffset != null ? new DeleteSurroundingTextCommand(0, nextWordOffset.intValue() - TextRange.m3900getEndimpl(deleteIfSelectedOr.getSelection())) : null;
                            }
                        });
                        if (listDeleteIfSelectedOr4 != null) {
                            this.apply((List<? extends EditCommand>) listDeleteIfSelectedOr4);
                            break;
                        }
                        break;
                    case 24:
                        List<EditCommand> listDeleteIfSelectedOr5 = commandExecutionContext.deleteIfSelectedOr(new Function1<TextFieldPreparedSelection, EditCommand>() { // from class: androidx.compose.foundation.text.TextFieldKeyInput$process$2.7
                            @Override // kotlin.jvm.functions.Function1
                            public final EditCommand invoke(TextFieldPreparedSelection deleteIfSelectedOr) {
                                DeleteSurroundingTextCommand deleteSurroundingTextCommand;
                                Intrinsics.checkNotNullParameter(deleteIfSelectedOr, "$this$deleteIfSelectedOr");
                                Integer lineStartByOffset = deleteIfSelectedOr.getLineStartByOffset();
                                if (lineStartByOffset != null) {
                                    deleteSurroundingTextCommand = new DeleteSurroundingTextCommand(TextRange.m3900getEndimpl(deleteIfSelectedOr.getSelection()) - lineStartByOffset.intValue(), 0);
                                } else {
                                    deleteSurroundingTextCommand = null;
                                }
                                return deleteSurroundingTextCommand;
                            }
                        });
                        if (listDeleteIfSelectedOr5 != null) {
                            this.apply((List<? extends EditCommand>) listDeleteIfSelectedOr5);
                            break;
                        }
                        break;
                    case 25:
                        List<EditCommand> listDeleteIfSelectedOr6 = commandExecutionContext.deleteIfSelectedOr(new Function1<TextFieldPreparedSelection, EditCommand>() { // from class: androidx.compose.foundation.text.TextFieldKeyInput$process$2.8
                            @Override // kotlin.jvm.functions.Function1
                            public final EditCommand invoke(TextFieldPreparedSelection deleteIfSelectedOr) {
                                Intrinsics.checkNotNullParameter(deleteIfSelectedOr, "$this$deleteIfSelectedOr");
                                Integer lineEndByOffset = deleteIfSelectedOr.getLineEndByOffset();
                                return lineEndByOffset != null ? new DeleteSurroundingTextCommand(0, lineEndByOffset.intValue() - TextRange.m3900getEndimpl(deleteIfSelectedOr.getSelection())) : null;
                            }
                        });
                        if (listDeleteIfSelectedOr6 != null) {
                            this.apply((List<? extends EditCommand>) listDeleteIfSelectedOr6);
                            break;
                        }
                        break;
                    case 26:
                        if (!this.getSingleLine()) {
                            this.apply(new CommitTextCommand("\n", 1));
                            break;
                        } else {
                            booleanRef.element = false;
                            break;
                        }
                    case 27:
                        if (!this.getSingleLine()) {
                            this.apply(new CommitTextCommand("\t", 1));
                            break;
                        } else {
                            booleanRef.element = false;
                            break;
                        }
                    case 28:
                        commandExecutionContext.selectAll();
                        break;
                    case 29:
                        commandExecutionContext.moveCursorLeft().selectMovement();
                        break;
                    case 30:
                        commandExecutionContext.moveCursorRight().selectMovement();
                        break;
                    case 31:
                        commandExecutionContext.moveCursorLeftByWord().selectMovement();
                        break;
                    case 32:
                        commandExecutionContext.moveCursorRightByWord().selectMovement();
                        break;
                    case 33:
                        commandExecutionContext.moveCursorPrevByParagraph().selectMovement();
                        break;
                    case 34:
                        commandExecutionContext.moveCursorNextByParagraph().selectMovement();
                        break;
                    case 35:
                        commandExecutionContext.moveCursorToLineStart().selectMovement();
                        break;
                    case 36:
                        commandExecutionContext.moveCursorToLineEnd().selectMovement();
                        break;
                    case 37:
                        commandExecutionContext.moveCursorToLineLeftSide().selectMovement();
                        break;
                    case 38:
                        commandExecutionContext.moveCursorToLineRightSide().selectMovement();
                        break;
                    case 39:
                        commandExecutionContext.moveCursorUpByLine().selectMovement();
                        break;
                    case 40:
                        commandExecutionContext.moveCursorDownByLine().selectMovement();
                        break;
                    case 41:
                        commandExecutionContext.moveCursorUpByPage().selectMovement();
                        break;
                    case 42:
                        commandExecutionContext.moveCursorDownByPage().selectMovement();
                        break;
                    case 43:
                        commandExecutionContext.moveCursorToHome().selectMovement();
                        break;
                    case 44:
                        commandExecutionContext.moveCursorToEnd().selectMovement();
                        break;
                    case 45:
                        commandExecutionContext.deselect();
                        break;
                    case 46:
                        UndoManager undoManager = this.getUndoManager();
                        if (undoManager != null) {
                            undoManager.makeSnapshot(commandExecutionContext.getValue());
                        }
                        UndoManager undoManager2 = this.getUndoManager();
                        if (undoManager2 != null && (textFieldValueUndo = undoManager2.undo()) != null) {
                            this.onValueChange.invoke(textFieldValueUndo);
                            break;
                        }
                        break;
                    case 47:
                        UndoManager undoManager3 = this.getUndoManager();
                        if (undoManager3 != null && (textFieldValueRedo = undoManager3.redo()) != null) {
                            this.onValueChange.invoke(textFieldValueRedo);
                            break;
                        }
                        break;
                    case 48:
                        KeyEventHelpers_androidKt.showCharacterPalette();
                        break;
                }
            }
        });
        UndoManager undoManager = this.undoManager;
        if (undoManager != null) {
            undoManager.forceNextSnapshot();
        }
        return booleanRef.element;
    }

    private final void commandExecutionContext(Function1<? super TextFieldPreparedSelection, Unit> block) {
        TextFieldPreparedSelection textFieldPreparedSelection = new TextFieldPreparedSelection(this.value, this.offsetMapping, this.state.getLayoutResult(), this.preparedSelectionState);
        block.invoke(textFieldPreparedSelection);
        if (TextRange.m3898equalsimpl0(textFieldPreparedSelection.getSelection(), this.value.getSelection()) && Intrinsics.areEqual(textFieldPreparedSelection.getAnnotatedString(), this.value.getAnnotatedString())) {
            return;
        }
        this.onValueChange.invoke(textFieldPreparedSelection.getValue());
    }
}
