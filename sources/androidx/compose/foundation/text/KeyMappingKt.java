package androidx.compose.foundation.text;

import androidx.compose.ui.input.key.Key;
import androidx.compose.ui.input.key.KeyEvent;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;

/* compiled from: KeyMapping.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\u001a\u001f\u0010\u0004\u001a\u00020\u00012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006H\u0000ø\u0001\u0000\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\t"}, d2 = {"defaultKeyMapping", "Landroidx/compose/foundation/text/KeyMapping;", "getDefaultKeyMapping", "()Landroidx/compose/foundation/text/KeyMapping;", "commonKeyMapping", "shortcutModifier", "Lkotlin/Function1;", "Landroidx/compose/ui/input/key/KeyEvent;", "", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class KeyMappingKt {
    private static final KeyMapping defaultKeyMapping;

    public static final KeyMapping getDefaultKeyMapping() {
        return defaultKeyMapping;
    }

    public static final KeyMapping commonKeyMapping(final Function1<? super KeyEvent, Boolean> shortcutModifier) {
        Intrinsics.checkNotNullParameter(shortcutModifier, "shortcutModifier");
        return new KeyMapping() { // from class: androidx.compose.foundation.text.KeyMappingKt.commonKeyMapping.1
            @Override // androidx.compose.foundation.text.KeyMapping
            /* renamed from: map-ZmokQxo */
            public KeyCommand mo988mapZmokQxo(android.view.KeyEvent event) {
                Intrinsics.checkNotNullParameter(event, "event");
                if (shortcutModifier.invoke(KeyEvent.m3128boximpl(event)).booleanValue() && KeyEvent_androidKt.m3151isShiftPressedZmokQxo(event)) {
                    if (Key.m2550equalsimpl0(KeyEvent_androidKt.m3145getKeyZmokQxo(event), MappedKeys.INSTANCE.m1023getZEK5gGoQ())) {
                        return KeyCommand.REDO;
                    }
                    return null;
                }
                if (shortcutModifier.invoke(KeyEvent.m3128boximpl(event)).booleanValue()) {
                    long jM3145getKeyZmokQxo = KeyEvent_androidKt.m3145getKeyZmokQxo(event);
                    if (Key.m2550equalsimpl0(jM3145getKeyZmokQxo, MappedKeys.INSTANCE.m1003getCEK5gGoQ()) || Key.m2550equalsimpl0(jM3145getKeyZmokQxo, MappedKeys.INSTANCE.m1013getInsertEK5gGoQ())) {
                        return KeyCommand.COPY;
                    }
                    if (Key.m2550equalsimpl0(jM3145getKeyZmokQxo, MappedKeys.INSTANCE.m1020getVEK5gGoQ())) {
                        return KeyCommand.PASTE;
                    }
                    if (Key.m2550equalsimpl0(jM3145getKeyZmokQxo, MappedKeys.INSTANCE.m1021getXEK5gGoQ())) {
                        return KeyCommand.CUT;
                    }
                    if (Key.m2550equalsimpl0(jM3145getKeyZmokQxo, MappedKeys.INSTANCE.m1000getAEK5gGoQ())) {
                        return KeyCommand.SELECT_ALL;
                    }
                    if (Key.m2550equalsimpl0(jM3145getKeyZmokQxo, MappedKeys.INSTANCE.m1022getYEK5gGoQ())) {
                        return KeyCommand.REDO;
                    }
                    if (Key.m2550equalsimpl0(jM3145getKeyZmokQxo, MappedKeys.INSTANCE.m1023getZEK5gGoQ())) {
                        return KeyCommand.UNDO;
                    }
                    return null;
                }
                if (KeyEvent_androidKt.m3149isCtrlPressedZmokQxo(event)) {
                    return null;
                }
                if (KeyEvent_androidKt.m3151isShiftPressedZmokQxo(event)) {
                    long jM3145getKeyZmokQxo2 = KeyEvent_androidKt.m3145getKeyZmokQxo(event);
                    if (Key.m2550equalsimpl0(jM3145getKeyZmokQxo2, MappedKeys.INSTANCE.m1008getDirectionLeftEK5gGoQ())) {
                        return KeyCommand.SELECT_LEFT_CHAR;
                    }
                    if (Key.m2550equalsimpl0(jM3145getKeyZmokQxo2, MappedKeys.INSTANCE.m1009getDirectionRightEK5gGoQ())) {
                        return KeyCommand.SELECT_RIGHT_CHAR;
                    }
                    if (Key.m2550equalsimpl0(jM3145getKeyZmokQxo2, MappedKeys.INSTANCE.m1010getDirectionUpEK5gGoQ())) {
                        return KeyCommand.SELECT_UP;
                    }
                    if (Key.m2550equalsimpl0(jM3145getKeyZmokQxo2, MappedKeys.INSTANCE.m1007getDirectionDownEK5gGoQ())) {
                        return KeyCommand.SELECT_DOWN;
                    }
                    if (Key.m2550equalsimpl0(jM3145getKeyZmokQxo2, MappedKeys.INSTANCE.m1017getPageUpEK5gGoQ())) {
                        return KeyCommand.SELECT_PAGE_UP;
                    }
                    if (Key.m2550equalsimpl0(jM3145getKeyZmokQxo2, MappedKeys.INSTANCE.m1016getPageDownEK5gGoQ())) {
                        return KeyCommand.SELECT_PAGE_DOWN;
                    }
                    if (Key.m2550equalsimpl0(jM3145getKeyZmokQxo2, MappedKeys.INSTANCE.m1015getMoveHomeEK5gGoQ())) {
                        return KeyCommand.SELECT_LINE_START;
                    }
                    if (Key.m2550equalsimpl0(jM3145getKeyZmokQxo2, MappedKeys.INSTANCE.m1014getMoveEndEK5gGoQ())) {
                        return KeyCommand.SELECT_LINE_END;
                    }
                    if (Key.m2550equalsimpl0(jM3145getKeyZmokQxo2, MappedKeys.INSTANCE.m1013getInsertEK5gGoQ())) {
                        return KeyCommand.PASTE;
                    }
                    return null;
                }
                long jM3145getKeyZmokQxo3 = KeyEvent_androidKt.m3145getKeyZmokQxo(event);
                if (Key.m2550equalsimpl0(jM3145getKeyZmokQxo3, MappedKeys.INSTANCE.m1008getDirectionLeftEK5gGoQ())) {
                    return KeyCommand.LEFT_CHAR;
                }
                if (Key.m2550equalsimpl0(jM3145getKeyZmokQxo3, MappedKeys.INSTANCE.m1009getDirectionRightEK5gGoQ())) {
                    return KeyCommand.RIGHT_CHAR;
                }
                if (Key.m2550equalsimpl0(jM3145getKeyZmokQxo3, MappedKeys.INSTANCE.m1010getDirectionUpEK5gGoQ())) {
                    return KeyCommand.UP;
                }
                if (Key.m2550equalsimpl0(jM3145getKeyZmokQxo3, MappedKeys.INSTANCE.m1007getDirectionDownEK5gGoQ())) {
                    return KeyCommand.DOWN;
                }
                if (Key.m2550equalsimpl0(jM3145getKeyZmokQxo3, MappedKeys.INSTANCE.m1017getPageUpEK5gGoQ())) {
                    return KeyCommand.PAGE_UP;
                }
                if (Key.m2550equalsimpl0(jM3145getKeyZmokQxo3, MappedKeys.INSTANCE.m1016getPageDownEK5gGoQ())) {
                    return KeyCommand.PAGE_DOWN;
                }
                if (Key.m2550equalsimpl0(jM3145getKeyZmokQxo3, MappedKeys.INSTANCE.m1015getMoveHomeEK5gGoQ())) {
                    return KeyCommand.LINE_START;
                }
                if (Key.m2550equalsimpl0(jM3145getKeyZmokQxo3, MappedKeys.INSTANCE.m1014getMoveEndEK5gGoQ())) {
                    return KeyCommand.LINE_END;
                }
                if (Key.m2550equalsimpl0(jM3145getKeyZmokQxo3, MappedKeys.INSTANCE.m1011getEnterEK5gGoQ())) {
                    return KeyCommand.NEW_LINE;
                }
                if (Key.m2550equalsimpl0(jM3145getKeyZmokQxo3, MappedKeys.INSTANCE.m1002getBackspaceEK5gGoQ())) {
                    return KeyCommand.DELETE_PREV_CHAR;
                }
                if (Key.m2550equalsimpl0(jM3145getKeyZmokQxo3, MappedKeys.INSTANCE.m1006getDeleteEK5gGoQ())) {
                    return KeyCommand.DELETE_NEXT_CHAR;
                }
                if (Key.m2550equalsimpl0(jM3145getKeyZmokQxo3, MappedKeys.INSTANCE.m1018getPasteEK5gGoQ())) {
                    return KeyCommand.PASTE;
                }
                if (Key.m2550equalsimpl0(jM3145getKeyZmokQxo3, MappedKeys.INSTANCE.m1005getCutEK5gGoQ())) {
                    return KeyCommand.CUT;
                }
                if (Key.m2550equalsimpl0(jM3145getKeyZmokQxo3, MappedKeys.INSTANCE.m1004getCopyEK5gGoQ())) {
                    return KeyCommand.COPY;
                }
                if (Key.m2550equalsimpl0(jM3145getKeyZmokQxo3, MappedKeys.INSTANCE.m1019getTabEK5gGoQ())) {
                    return KeyCommand.TAB;
                }
                return null;
            }
        };
    }

    static {
        final KeyMapping keyMappingCommonKeyMapping = commonKeyMapping(new PropertyReference1Impl() { // from class: androidx.compose.foundation.text.KeyMappingKt$defaultKeyMapping$1
            @Override // kotlin.jvm.internal.PropertyReference1Impl, kotlin.reflect.KProperty1
            public Object get(Object obj) {
                return Boolean.valueOf(KeyEvent_androidKt.m3149isCtrlPressedZmokQxo(((KeyEvent) obj).m3134unboximpl()));
            }
        });
        defaultKeyMapping = new KeyMapping() { // from class: androidx.compose.foundation.text.KeyMappingKt$defaultKeyMapping$2$1
            @Override // androidx.compose.foundation.text.KeyMapping
            /* renamed from: map-ZmokQxo */
            public KeyCommand mo988mapZmokQxo(android.view.KeyEvent event) {
                Intrinsics.checkNotNullParameter(event, "event");
                KeyCommand keyCommand = null;
                if (KeyEvent_androidKt.m3151isShiftPressedZmokQxo(event) && KeyEvent_androidKt.m3149isCtrlPressedZmokQxo(event)) {
                    long jM3145getKeyZmokQxo = KeyEvent_androidKt.m3145getKeyZmokQxo(event);
                    if (Key.m2550equalsimpl0(jM3145getKeyZmokQxo, MappedKeys.INSTANCE.m1008getDirectionLeftEK5gGoQ())) {
                        keyCommand = KeyCommand.SELECT_LEFT_WORD;
                    } else if (Key.m2550equalsimpl0(jM3145getKeyZmokQxo, MappedKeys.INSTANCE.m1009getDirectionRightEK5gGoQ())) {
                        keyCommand = KeyCommand.SELECT_RIGHT_WORD;
                    } else if (Key.m2550equalsimpl0(jM3145getKeyZmokQxo, MappedKeys.INSTANCE.m1010getDirectionUpEK5gGoQ())) {
                        keyCommand = KeyCommand.SELECT_PREV_PARAGRAPH;
                    } else if (Key.m2550equalsimpl0(jM3145getKeyZmokQxo, MappedKeys.INSTANCE.m1007getDirectionDownEK5gGoQ())) {
                        keyCommand = KeyCommand.SELECT_NEXT_PARAGRAPH;
                    }
                } else if (KeyEvent_androidKt.m3149isCtrlPressedZmokQxo(event)) {
                    long jM3145getKeyZmokQxo2 = KeyEvent_androidKt.m3145getKeyZmokQxo(event);
                    if (Key.m2550equalsimpl0(jM3145getKeyZmokQxo2, MappedKeys.INSTANCE.m1008getDirectionLeftEK5gGoQ())) {
                        keyCommand = KeyCommand.LEFT_WORD;
                    } else if (Key.m2550equalsimpl0(jM3145getKeyZmokQxo2, MappedKeys.INSTANCE.m1009getDirectionRightEK5gGoQ())) {
                        keyCommand = KeyCommand.RIGHT_WORD;
                    } else if (Key.m2550equalsimpl0(jM3145getKeyZmokQxo2, MappedKeys.INSTANCE.m1010getDirectionUpEK5gGoQ())) {
                        keyCommand = KeyCommand.PREV_PARAGRAPH;
                    } else if (Key.m2550equalsimpl0(jM3145getKeyZmokQxo2, MappedKeys.INSTANCE.m1007getDirectionDownEK5gGoQ())) {
                        keyCommand = KeyCommand.NEXT_PARAGRAPH;
                    } else if (Key.m2550equalsimpl0(jM3145getKeyZmokQxo2, MappedKeys.INSTANCE.m1012getHEK5gGoQ())) {
                        keyCommand = KeyCommand.DELETE_PREV_CHAR;
                    } else if (Key.m2550equalsimpl0(jM3145getKeyZmokQxo2, MappedKeys.INSTANCE.m1006getDeleteEK5gGoQ())) {
                        keyCommand = KeyCommand.DELETE_NEXT_WORD;
                    } else if (Key.m2550equalsimpl0(jM3145getKeyZmokQxo2, MappedKeys.INSTANCE.m1002getBackspaceEK5gGoQ())) {
                        keyCommand = KeyCommand.DELETE_PREV_WORD;
                    } else if (Key.m2550equalsimpl0(jM3145getKeyZmokQxo2, MappedKeys.INSTANCE.m1001getBackslashEK5gGoQ())) {
                        keyCommand = KeyCommand.DESELECT;
                    }
                } else if (KeyEvent_androidKt.m3151isShiftPressedZmokQxo(event)) {
                    long jM3145getKeyZmokQxo3 = KeyEvent_androidKt.m3145getKeyZmokQxo(event);
                    if (Key.m2550equalsimpl0(jM3145getKeyZmokQxo3, MappedKeys.INSTANCE.m1015getMoveHomeEK5gGoQ())) {
                        keyCommand = KeyCommand.SELECT_HOME;
                    } else if (Key.m2550equalsimpl0(jM3145getKeyZmokQxo3, MappedKeys.INSTANCE.m1014getMoveEndEK5gGoQ())) {
                        keyCommand = KeyCommand.SELECT_END;
                    }
                } else if (KeyEvent_androidKt.m3148isAltPressedZmokQxo(event)) {
                    long jM3145getKeyZmokQxo4 = KeyEvent_androidKt.m3145getKeyZmokQxo(event);
                    if (Key.m2550equalsimpl0(jM3145getKeyZmokQxo4, MappedKeys.INSTANCE.m1002getBackspaceEK5gGoQ())) {
                        keyCommand = KeyCommand.DELETE_FROM_LINE_START;
                    } else if (Key.m2550equalsimpl0(jM3145getKeyZmokQxo4, MappedKeys.INSTANCE.m1006getDeleteEK5gGoQ())) {
                        keyCommand = KeyCommand.DELETE_TO_LINE_END;
                    }
                }
                return keyCommand == null ? keyMappingCommonKeyMapping.mo988mapZmokQxo(event) : keyCommand;
            }
        };
    }
}
