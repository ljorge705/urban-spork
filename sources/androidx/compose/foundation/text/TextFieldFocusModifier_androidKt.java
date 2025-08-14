package androidx.compose.foundation.text;

import android.view.InputDevice;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusDirection;
import androidx.compose.ui.focus.FocusManager;
import androidx.compose.ui.input.key.KeyEvent;
import androidx.compose.ui.input.key.KeyEventType;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.input.key.KeyInputModifierKt;
import androidx.compose.ui.input.key.Key_androidKt;
import androidx.compose.ui.text.input.TextInputSession;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TextFieldFocusModifier.android.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0000¨\u0006\u0006"}, d2 = {"interceptDPadAndMoveFocus", "Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/foundation/text/TextFieldState;", "focusManager", "Landroidx/compose/ui/focus/FocusManager;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class TextFieldFocusModifier_androidKt {
    public static final Modifier interceptDPadAndMoveFocus(Modifier modifier, final TextFieldState state, final FocusManager focusManager) {
        Intrinsics.checkNotNullParameter(modifier, "<this>");
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(focusManager, "focusManager");
        return KeyInputModifierKt.onPreviewKeyEvent(modifier, new Function1<KeyEvent, Boolean>() { // from class: androidx.compose.foundation.text.TextFieldFocusModifier_androidKt.interceptDPadAndMoveFocus.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(KeyEvent keyEvent) {
                return m1043invokeZmokQxo(keyEvent.m3134unboximpl());
            }

            /* renamed from: invoke-ZmokQxo, reason: not valid java name */
            public final Boolean m1043invokeZmokQxo(android.view.KeyEvent keyEvent) {
                Intrinsics.checkNotNullParameter(keyEvent, "keyEvent");
                InputDevice device = keyEvent.getDevice();
                boolean zMo1583moveFocus3ESFkO8 = false;
                if (device == null) {
                    return false;
                }
                if ((device.getKeyboardType() == 2 && device.isVirtual()) || !KeyEventType.m3138equalsimpl0(KeyEvent_androidKt.m3146getTypeZmokQxo(keyEvent), KeyEventType.INSTANCE.m3142getKeyDownCS__XNY())) {
                    return false;
                }
                switch (Key_androidKt.m3154getNativeKeyCodeYVgTNJs(KeyEvent_androidKt.m3145getKeyZmokQxo(keyEvent))) {
                    case 19:
                        zMo1583moveFocus3ESFkO8 = focusManager.mo1583moveFocus3ESFkO8(FocusDirection.INSTANCE.m1582getUpdhqQ8s());
                        break;
                    case 20:
                        zMo1583moveFocus3ESFkO8 = focusManager.mo1583moveFocus3ESFkO8(FocusDirection.INSTANCE.m1573getDowndhqQ8s());
                        break;
                    case 21:
                        zMo1583moveFocus3ESFkO8 = focusManager.mo1583moveFocus3ESFkO8(FocusDirection.INSTANCE.m1577getLeftdhqQ8s());
                        break;
                    case 22:
                        zMo1583moveFocus3ESFkO8 = focusManager.mo1583moveFocus3ESFkO8(FocusDirection.INSTANCE.m1581getRightdhqQ8s());
                        break;
                    case 23:
                        TextInputSession inputSession = state.getInputSession();
                        if (inputSession != null) {
                            inputSession.showSoftwareKeyboard();
                        }
                        zMo1583moveFocus3ESFkO8 = true;
                        break;
                }
                return Boolean.valueOf(zMo1583moveFocus3ESFkO8);
            }
        });
    }
}
