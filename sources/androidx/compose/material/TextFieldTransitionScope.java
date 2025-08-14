package androidx.compose.material;

import androidx.compose.runtime.State;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;

/* compiled from: TextFieldImpl.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002Jr\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2P\u0010\t\u001aL\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u00040\n¢\u0006\u0002\b\u0012H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0013\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0014"}, d2 = {"Landroidx/compose/material/TextFieldTransitionScope;", "", "()V", "Transition", "", "inputState", "Landroidx/compose/material/InputPhase;", "showLabel", "", "content", "Lkotlin/Function3;", "", "Lkotlin/ParameterName;", "name", "labelProgress", "Landroidx/compose/ui/unit/Dp;", "indicatorWidth", "placeholderOpacity", "Landroidx/compose/runtime/Composable;", "(Landroidx/compose/material/InputPhase;ZLkotlin/jvm/functions/Function5;Landroidx/compose/runtime/Composer;I)V", "material_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
final class TextFieldTransitionScope {
    public static final TextFieldTransitionScope INSTANCE = new TextFieldTransitionScope();

    /* compiled from: TextFieldImpl.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[InputPhase.valuesCustom().length];
            iArr[InputPhase.Focused.ordinal()] = 1;
            iArr[InputPhase.UnfocusedEmpty.ordinal()] = 2;
            iArr[InputPhase.UnfocusedNotEmpty.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private TextFieldTransitionScope() {
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0152  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0168  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0189  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x019f  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0200  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0212  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0232  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0242  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void Transition(final androidx.compose.material.InputPhase r25, final boolean r26, final kotlin.jvm.functions.Function5<? super java.lang.Float, ? super androidx.compose.ui.unit.Dp, ? super java.lang.Float, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r27, androidx.compose.runtime.Composer r28, final int r29) {
        /*
            Method dump skipped, instructions count: 684
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.TextFieldTransitionScope.Transition(androidx.compose.material.InputPhase, boolean, kotlin.jvm.functions.Function5, androidx.compose.runtime.Composer, int):void");
    }

    /* renamed from: Transition$lambda-1, reason: not valid java name */
    private static final float m1471Transition$lambda1(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* renamed from: Transition$lambda-3, reason: not valid java name */
    private static final float m1472Transition$lambda3(State<Dp> state) {
        return state.getValue().m4404unboximpl();
    }

    /* renamed from: Transition$lambda-5, reason: not valid java name */
    private static final float m1473Transition$lambda5(State<Float> state) {
        return state.getValue().floatValue();
    }
}
