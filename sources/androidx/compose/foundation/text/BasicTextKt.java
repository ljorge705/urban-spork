package androidx.compose.foundation.text;

import androidx.compose.foundation.text.selection.SelectionRegistrar;
import androidx.compose.foundation.text.selection.SelectionRegistrarKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.saveable.SaverKt;
import androidx.compose.runtime.saveable.SaverScope;
import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BasicText.kt */
@Metadata(d1 = {"\u0000X\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0085\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\t2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00102\u0014\b\u0002\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u0013H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0016\u0010\u0017\u001a{\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\t2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\u0014\b\u0002\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u0013H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0018\u0010\u0019\u001ae\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00142\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\t2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u0010H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001a\u0010\u001b\u001ao\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00142\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\t2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0010H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0018\u0010\u001c\u001a\u001e\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u001f0\u001e2\b\u0010 \u001a\u0004\u0018\u00010!H\u0002\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\""}, d2 = {"BasicText", "", "text", "Landroidx/compose/ui/text/AnnotatedString;", "modifier", "Landroidx/compose/ui/Modifier;", "style", "Landroidx/compose/ui/text/TextStyle;", "onTextLayout", "Lkotlin/Function1;", "Landroidx/compose/ui/text/TextLayoutResult;", ViewProps.OVERFLOW, "Landroidx/compose/ui/text/style/TextOverflow;", "softWrap", "", "maxLines", "", "minLines", "inlineContent", "", "", "Landroidx/compose/foundation/text/InlineTextContent;", "BasicText-VhcvRP8", "(Landroidx/compose/ui/text/AnnotatedString;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function1;IZIILjava/util/Map;Landroidx/compose/runtime/Composer;II)V", "BasicText-4YKlhWE", "(Landroidx/compose/ui/text/AnnotatedString;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function1;IZILjava/util/Map;Landroidx/compose/runtime/Composer;II)V", "BasicText-BpD7jsM", "(Ljava/lang/String;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function1;IZILandroidx/compose/runtime/Composer;II)V", "(Ljava/lang/String;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function1;IZIILandroidx/compose/runtime/Composer;II)V", "selectionIdSaver", "Landroidx/compose/runtime/saveable/Saver;", "", "selectionRegistrar", "Landroidx/compose/foundation/text/selection/SelectionRegistrar;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class BasicTextKt {
    /* JADX WARN: Removed duplicated region for block: B:101:0x013b  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0142  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x014f  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0158  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x015a  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0162  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x01bb  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x01be  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0204  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0248  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x025a  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x027e  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x029c  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x02b9  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x032b  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0337  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x0342  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0385  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x0399  */
    /* JADX WARN: Removed duplicated region for block: B:148:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0122  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0124  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0137  */
    /* renamed from: BasicText-4YKlhWE, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m965BasicText4YKlhWE(final java.lang.String r31, androidx.compose.ui.Modifier r32, androidx.compose.ui.text.TextStyle r33, kotlin.jvm.functions.Function1<? super androidx.compose.ui.text.TextLayoutResult, kotlin.Unit> r34, int r35, boolean r36, int r37, int r38, androidx.compose.runtime.Composer r39, final int r40, final int r41) {
        /*
            Method dump skipped, instructions count: 939
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.BasicTextKt.m965BasicText4YKlhWE(java.lang.String, androidx.compose.ui.Modifier, androidx.compose.ui.text.TextStyle, kotlin.jvm.functions.Function1, int, boolean, int, int, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x013b  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0153  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x015c  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0160  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0162  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0166  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x016f  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0171  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0175  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x017b  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0183  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0202  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0205  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x024e  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0296  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x02bb  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x02ec  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x02f5  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x036f  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x037b  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x037f  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x03d6  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x03ed  */
    /* JADX WARN: Removed duplicated region for block: B:155:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0107  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0135  */
    /* renamed from: BasicText-VhcvRP8, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m967BasicTextVhcvRP8(final androidx.compose.ui.text.AnnotatedString r33, androidx.compose.ui.Modifier r34, androidx.compose.ui.text.TextStyle r35, kotlin.jvm.functions.Function1<? super androidx.compose.ui.text.TextLayoutResult, kotlin.Unit> r36, int r37, boolean r38, int r39, int r40, java.util.Map<java.lang.String, androidx.compose.foundation.text.InlineTextContent> r41, androidx.compose.runtime.Composer r42, final int r43, final int r44) {
        /*
            Method dump skipped, instructions count: 1023
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.BasicTextKt.m967BasicTextVhcvRP8(androidx.compose.ui.text.AnnotatedString, androidx.compose.ui.Modifier, androidx.compose.ui.text.TextStyle, kotlin.jvm.functions.Function1, int, boolean, int, int, java.util.Map, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x0141  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x017d  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0191  */
    /* JADX WARN: Removed duplicated region for block: B:112:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0119  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0120  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0124  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0131  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x013a  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    /* renamed from: BasicText-BpD7jsM, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final /* synthetic */ void m966BasicTextBpD7jsM(final java.lang.String r23, androidx.compose.ui.Modifier r24, androidx.compose.ui.text.TextStyle r25, kotlin.jvm.functions.Function1 r26, int r27, boolean r28, int r29, androidx.compose.runtime.Composer r30, final int r31, final int r32) {
        /*
            Method dump skipped, instructions count: 419
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.BasicTextKt.m966BasicTextBpD7jsM(java.lang.String, androidx.compose.ui.Modifier, androidx.compose.ui.text.TextStyle, kotlin.jvm.functions.Function1, int, boolean, int, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x0142  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x014a  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x015b  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0163  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x01a2  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x01ba  */
    /* JADX WARN: Removed duplicated region for block: B:121:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0112  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0119  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x011d  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0131  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x013e  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    /* renamed from: BasicText-4YKlhWE, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final /* synthetic */ void m964BasicText4YKlhWE(final androidx.compose.ui.text.AnnotatedString r26, androidx.compose.ui.Modifier r27, androidx.compose.ui.text.TextStyle r28, kotlin.jvm.functions.Function1 r29, int r30, boolean r31, int r32, java.util.Map r33, androidx.compose.runtime.Composer r34, final int r35, final int r36) {
        /*
            Method dump skipped, instructions count: 460
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.BasicTextKt.m964BasicText4YKlhWE(androidx.compose.ui.text.AnnotatedString, androidx.compose.ui.Modifier, androidx.compose.ui.text.TextStyle, kotlin.jvm.functions.Function1, int, boolean, int, java.util.Map, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final Saver<Long, Long> selectionIdSaver(final SelectionRegistrar selectionRegistrar) {
        return SaverKt.Saver(new Function2<SaverScope, Long, Long>() { // from class: androidx.compose.foundation.text.BasicTextKt.selectionIdSaver.1
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Long invoke(SaverScope saverScope, Long l) {
                return invoke(saverScope, l.longValue());
            }

            public final Long invoke(SaverScope Saver, long j) {
                Intrinsics.checkNotNullParameter(Saver, "$this$Saver");
                if (SelectionRegistrarKt.hasSelection(selectionRegistrar, j)) {
                    return Long.valueOf(j);
                }
                return null;
            }
        }, new Function1<Long, Long>() { // from class: androidx.compose.foundation.text.BasicTextKt.selectionIdSaver.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Long invoke(Long l) {
                return invoke(l.longValue());
            }

            public final Long invoke(long j) {
                return Long.valueOf(j);
            }
        });
    }
}
