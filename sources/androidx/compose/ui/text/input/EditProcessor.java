package androidx.compose.ui.text.input;

import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.AnnotatedStringKt;
import androidx.compose.ui.text.TextRange;
import com.drew.metadata.exif.makernotes.PanasonicMakernoteDirectory;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* compiled from: EditProcessor.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0014\u0010\f\u001a\u00020\b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eJ \u0010\u0010\u001a\u00020\u00112\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\b\u0010\u0012\u001a\u0004\u0018\u00010\u000fH\u0002J\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017J\u0006\u0010\u0018\u001a\u00020\bJ\f\u0010\u0019\u001a\u00020\u0011*\u00020\u000fH\u0002R\u001e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001e\u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\b@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u001a"}, d2 = {"Landroidx/compose/ui/text/input/EditProcessor;", "", "()V", "<set-?>", "Landroidx/compose/ui/text/input/EditingBuffer;", "mBuffer", "getMBuffer$ui_text_release", "()Landroidx/compose/ui/text/input/EditingBuffer;", "Landroidx/compose/ui/text/input/TextFieldValue;", "mBufferState", "getMBufferState$ui_text_release", "()Landroidx/compose/ui/text/input/TextFieldValue;", "apply", "editCommands", "", "Landroidx/compose/ui/text/input/EditCommand;", "generateBatchErrorMessage", "", "failedCommand", "reset", "", "value", "textInputSession", "Landroidx/compose/ui/text/input/TextInputSession;", "toTextFieldValue", "toStringForLog", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class EditProcessor {
    public static final int $stable = 8;
    private TextFieldValue mBufferState = new TextFieldValue(AnnotatedStringKt.emptyAnnotatedString(), TextRange.INSTANCE.m3910getZerod9O1mEE(), (TextRange) null, (DefaultConstructorMarker) null);
    private EditingBuffer mBuffer = new EditingBuffer(this.mBufferState.getAnnotatedString(), this.mBufferState.getSelection(), (DefaultConstructorMarker) null);

    /* renamed from: getMBuffer$ui_text_release, reason: from getter */
    public final EditingBuffer getMBuffer() {
        return this.mBuffer;
    }

    /* renamed from: getMBufferState$ui_text_release, reason: from getter */
    public final TextFieldValue getMBufferState() {
        return this.mBufferState;
    }

    public final TextFieldValue toTextFieldValue() {
        return this.mBufferState;
    }

    public final void reset(TextFieldValue value, TextInputSession textInputSession) {
        Intrinsics.checkNotNullParameter(value, "value");
        boolean z = true;
        boolean z2 = !Intrinsics.areEqual(value.getComposition(), this.mBuffer.m4035getCompositionMzsxiRA$ui_text_release());
        boolean z3 = false;
        if (!Intrinsics.areEqual(this.mBufferState.getAnnotatedString(), value.getAnnotatedString())) {
            this.mBuffer = new EditingBuffer(value.getAnnotatedString(), value.getSelection(), (DefaultConstructorMarker) null);
        } else if (TextRange.m3898equalsimpl0(this.mBufferState.getSelection(), value.getSelection())) {
            z = false;
        } else {
            this.mBuffer.setSelection$ui_text_release(TextRange.m3903getMinimpl(value.getSelection()), TextRange.m3902getMaximpl(value.getSelection()));
            z3 = true;
            z = false;
        }
        if (value.getComposition() == null) {
            this.mBuffer.commitComposition$ui_text_release();
        } else if (!TextRange.m3899getCollapsedimpl(value.getComposition().getPackedValue())) {
            this.mBuffer.setComposition$ui_text_release(TextRange.m3903getMinimpl(value.getComposition().getPackedValue()), TextRange.m3902getMaximpl(value.getComposition().getPackedValue()));
        }
        if (z || (!z3 && z2)) {
            this.mBuffer.commitComposition$ui_text_release();
            value = TextFieldValue.m4109copy3r_uNRQ$default(value, (AnnotatedString) null, 0L, (TextRange) null, 3, (Object) null);
        }
        TextFieldValue textFieldValue = this.mBufferState;
        this.mBufferState = value;
        if (textInputSession != null) {
            textInputSession.updateState(textFieldValue, value);
        }
    }

    private final String generateBatchErrorMessage(List<? extends EditCommand> editCommands, final EditCommand failedCommand) {
        StringBuilder sb = new StringBuilder();
        StringBuilder sbAppend = sb.append("Error while applying EditCommand batch to buffer (length=" + this.mBuffer.getLength$ui_text_release() + ", composition=" + this.mBuffer.m4035getCompositionMzsxiRA$ui_text_release() + ", selection=" + ((Object) TextRange.m3908toStringimpl(this.mBuffer.m4036getSelectiond9O1mEE$ui_text_release())) + "):");
        Intrinsics.checkNotNullExpressionValue(sbAppend, "append(value)");
        Intrinsics.checkNotNullExpressionValue(sbAppend.append('\n'), "append('\\n')");
        CollectionsKt.joinTo(editCommands, sb, (PanasonicMakernoteDirectory.TAG_CLEAR_RETOUCH & 2) != 0 ? ", " : "\n", (PanasonicMakernoteDirectory.TAG_CLEAR_RETOUCH & 4) != 0 ? "" : null, (PanasonicMakernoteDirectory.TAG_CLEAR_RETOUCH & 8) != 0 ? "" : null, (PanasonicMakernoteDirectory.TAG_CLEAR_RETOUCH & 16) != 0 ? -1 : 0, (PanasonicMakernoteDirectory.TAG_CLEAR_RETOUCH & 32) != 0 ? "..." : null, (PanasonicMakernoteDirectory.TAG_CLEAR_RETOUCH & 64) != 0 ? null : new Function1<EditCommand, CharSequence>() { // from class: androidx.compose.ui.text.input.EditProcessor$generateBatchErrorMessage$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final CharSequence invoke(EditCommand it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return (failedCommand == it ? " > " : "   ") + this.toStringForLog(it);
            }
        });
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String toStringForLog(EditCommand editCommand) {
        if (editCommand instanceof CommitTextCommand) {
            CommitTextCommand commitTextCommand = (CommitTextCommand) editCommand;
            return "CommitTextCommand(text.length=" + commitTextCommand.getText().length() + ", newCursorPosition=" + commitTextCommand.getNewCursorPosition() + ')';
        }
        if (editCommand instanceof SetComposingTextCommand) {
            SetComposingTextCommand setComposingTextCommand = (SetComposingTextCommand) editCommand;
            return "SetComposingTextCommand(text.length=" + setComposingTextCommand.getText().length() + ", newCursorPosition=" + setComposingTextCommand.getNewCursorPosition() + ')';
        }
        if (!(editCommand instanceof SetComposingRegionCommand) && !(editCommand instanceof DeleteSurroundingTextCommand) && !(editCommand instanceof DeleteSurroundingTextInCodePointsCommand) && !(editCommand instanceof SetSelectionCommand) && !(editCommand instanceof FinishComposingTextCommand) && !(editCommand instanceof BackspaceCommand) && !(editCommand instanceof MoveCursorCommand) && !(editCommand instanceof DeleteAllCommand)) {
            StringBuilder sb = new StringBuilder("Unknown EditCommand: ");
            String simpleName = Reflection.getOrCreateKotlinClass(editCommand.getClass()).getSimpleName();
            if (simpleName == null) {
                simpleName = "{anonymous EditCommand}";
            }
            return sb.append(simpleName).toString();
        }
        return editCommand.toString();
    }

    public final TextFieldValue apply(List<? extends EditCommand> editCommands) {
        EditCommand editCommand;
        Exception e;
        Intrinsics.checkNotNullParameter(editCommands, "editCommands");
        EditCommand editCommand2 = null;
        try {
            int size = editCommands.size();
            int i = 0;
            while (i < size) {
                editCommand = editCommands.get(i);
                try {
                    editCommand.applyTo(this.mBuffer);
                    i++;
                    editCommand2 = editCommand;
                } catch (Exception e2) {
                    e = e2;
                    throw new RuntimeException(generateBatchErrorMessage(editCommands, editCommand), e);
                }
            }
            TextFieldValue textFieldValue = new TextFieldValue(this.mBuffer.toAnnotatedString$ui_text_release(), this.mBuffer.m4036getSelectiond9O1mEE$ui_text_release(), this.mBuffer.m4035getCompositionMzsxiRA$ui_text_release(), (DefaultConstructorMarker) null);
            this.mBufferState = textFieldValue;
            return textFieldValue;
        } catch (Exception e3) {
            editCommand = editCommand2;
            e = e3;
        }
    }
}
