package androidx.compose.material.ripple;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.Indication;
import androidx.compose.foundation.interaction.DragInteraction;
import androidx.compose.foundation.interaction.Interaction;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.unit.Dp;
import com.clevertap.android.sdk.Constants;
import kotlin.Metadata;

/* compiled from: Ripple.kt */
@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002\u001a\u0018\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0002\u001a3\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000fH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0010\u0010\u0011\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006\u0012"}, d2 = {"DefaultTweenSpec", "Landroidx/compose/animation/core/TweenSpec;", "", "incomingStateLayerAnimationSpecFor", "Landroidx/compose/animation/core/AnimationSpec;", "interaction", "Landroidx/compose/foundation/interaction/Interaction;", "outgoingStateLayerAnimationSpecFor", "rememberRipple", "Landroidx/compose/foundation/Indication;", "bounded", "", Constants.KEY_RADIUS, "Landroidx/compose/ui/unit/Dp;", "color", "Landroidx/compose/ui/graphics/Color;", "rememberRipple-9IZ8Weo", "(ZFJLandroidx/compose/runtime/Composer;II)Landroidx/compose/foundation/Indication;", "material-ripple_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class RippleKt {
    private static final TweenSpec<Float> DefaultTweenSpec = new TweenSpec<>(15, 0, EasingKt.getLinearEasing(), 2, null);

    /* renamed from: rememberRipple-9IZ8Weo, reason: not valid java name */
    public static final Indication m1486rememberRipple9IZ8Weo(boolean z, float f, long j, Composer composer, int i, int i2) {
        composer.startReplaceableGroup(-1508283871);
        ComposerKt.sourceInformation(composer, "C(rememberRipple)P(!1,2:c#ui.unit.Dp,1:c#ui.graphics.Color)79@3762L27,80@3801L85:Ripple.kt#vhb33q");
        if ((i2 & 1) != 0) {
            z = true;
        }
        if ((i2 & 2) != 0) {
            f = Dp.INSTANCE.m4410getUnspecifiedD9Ej5fM();
        }
        if ((i2 & 4) != 0) {
            j = Color.INSTANCE.m1913getUnspecified0d7_KjU();
        }
        State stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(Color.m1867boximpl(j), composer, (i >> 6) & 14);
        Boolean boolValueOf = Boolean.valueOf(z);
        Dp dpM4388boximpl = Dp.m4388boximpl(f);
        composer.startReplaceableGroup(-3686552);
        ComposerKt.sourceInformation(composer, "C(remember)P(1,2):Composables.kt#9igjgp");
        boolean zChanged = composer.changed(boolValueOf) | composer.changed(dpM4388boximpl);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new PlatformRipple(z, f, stateRememberUpdatedState, null);
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        return (PlatformRipple) objRememberedValue;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final AnimationSpec<Float> incomingStateLayerAnimationSpecFor(Interaction interaction) {
        if (interaction instanceof DragInteraction.Start) {
            return new TweenSpec(45, 0, EasingKt.getLinearEasing(), 2, null);
        }
        return DefaultTweenSpec;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final AnimationSpec<Float> outgoingStateLayerAnimationSpecFor(Interaction interaction) {
        if (interaction instanceof DragInteraction.Start) {
            return new TweenSpec(150, 0, EasingKt.getLinearEasing(), 2, null);
        }
        return DefaultTweenSpec;
    }
}
