package androidx.compose.foundation;

import android.view.KeyEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.input.key.KeyEventType;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.input.key.Key_androidKt;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Clickable.android.kt */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0013\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\u000eH\u0001¢\u0006\u0002\u0010\u000f\u001a\f\u0010\u0010\u001a\u00020\u0005*\u00020\u0011H\u0002\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u001b\u0010\u0004\u001a\u00020\u0005*\u00020\u00068@X\u0080\u0004ø\u0001\u0000¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b\"\u001b\u0010\t\u001a\u00020\u0005*\u00020\u00068BX\u0082\u0004ø\u0001\u0000¢\u0006\u0006\u001a\u0004\b\n\u0010\b\"\u001b\u0010\u000b\u001a\u00020\u0005*\u00020\u00068@X\u0080\u0004ø\u0001\u0000¢\u0006\u0006\u001a\u0004\b\f\u0010\b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, d2 = {"TapIndicationDelay", "", "getTapIndicationDelay", "()J", "isClick", "", "Landroidx/compose/ui/input/key/KeyEvent;", "isClick-ZmokQxo", "(Landroid/view/KeyEvent;)Z", "isEnter", "isEnter-ZmokQxo", "isPress", "isPress-ZmokQxo", "isComposeRootInScrollableContainer", "Lkotlin/Function0;", "(Landroidx/compose/runtime/Composer;I)Lkotlin/jvm/functions/Function0;", "isInScrollableViewGroup", "Landroid/view/View;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class Clickable_androidKt {
    private static final long TapIndicationDelay = ViewConfiguration.getTapTimeout();

    public static final long getTapIndicationDelay() {
        return TapIndicationDelay;
    }

    public static final Function0<Boolean> isComposeRootInScrollableContainer(Composer composer, int i) {
        composer.startReplaceableGroup(-1990508712);
        ComposerKt.sourceInformation(composer, "C(isComposeRootInScrollableContainer)35@1404L7:Clickable.android.kt#71ulvw");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1990508712, i, -1, "androidx.compose.foundation.isComposeRootInScrollableContainer (Clickable.android.kt:34)");
        }
        ProvidableCompositionLocal<View> localView = AndroidCompositionLocals_androidKt.getLocalView();
        ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = composer.consume(localView);
        ComposerKt.sourceInformationMarkerEnd(composer);
        final View view = (View) objConsume;
        Function0<Boolean> function0 = new Function0<Boolean>() { // from class: androidx.compose.foundation.Clickable_androidKt.isComposeRootInScrollableContainer.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                return Boolean.valueOf(Clickable_androidKt.isInScrollableViewGroup(view));
            }
        };
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return function0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isInScrollableViewGroup(View view) {
        ViewParent parent = view.getParent();
        while (parent != null && (parent instanceof ViewGroup)) {
            ViewGroup viewGroup = (ViewGroup) parent;
            if (viewGroup.shouldDelayChildPressedState()) {
                return true;
            }
            parent = viewGroup.getParent();
        }
        return false;
    }

    /* renamed from: isPress-ZmokQxo, reason: not valid java name */
    public static final boolean m468isPressZmokQxo(KeyEvent isPress) {
        Intrinsics.checkNotNullParameter(isPress, "$this$isPress");
        return KeyEventType.m3138equalsimpl0(KeyEvent_androidKt.m3146getTypeZmokQxo(isPress), KeyEventType.INSTANCE.m3142getKeyDownCS__XNY()) && m467isEnterZmokQxo(isPress);
    }

    /* renamed from: isClick-ZmokQxo, reason: not valid java name */
    public static final boolean m466isClickZmokQxo(KeyEvent isClick) {
        Intrinsics.checkNotNullParameter(isClick, "$this$isClick");
        return KeyEventType.m3138equalsimpl0(KeyEvent_androidKt.m3146getTypeZmokQxo(isClick), KeyEventType.INSTANCE.m3143getKeyUpCS__XNY()) && m467isEnterZmokQxo(isClick);
    }

    /* renamed from: isEnter-ZmokQxo, reason: not valid java name */
    private static final boolean m467isEnterZmokQxo(KeyEvent keyEvent) {
        int iM3154getNativeKeyCodeYVgTNJs = Key_androidKt.m3154getNativeKeyCodeYVgTNJs(KeyEvent_androidKt.m3145getKeyZmokQxo(keyEvent));
        return iM3154getNativeKeyCodeYVgTNJs == 23 || iM3154getNativeKeyCodeYVgTNJs == 66 || iM3154getNativeKeyCodeYVgTNJs == 160;
    }
}
