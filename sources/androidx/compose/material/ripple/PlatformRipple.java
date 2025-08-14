package androidx.compose.material.ripple;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.State;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import com.clevertap.android.sdk.Constants;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Ripple.android.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001B&\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007ø\u0001\u0000¢\u0006\u0002\u0010\tJ\r\u0010\n\u001a\u00020\u000bH\u0003¢\u0006\u0002\u0010\fJI\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u0007H\u0017ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0013\u0010\u0014\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006\u0015"}, d2 = {"Landroidx/compose/material/ripple/PlatformRipple;", "Landroidx/compose/material/ripple/Ripple;", "bounded", "", Constants.KEY_RADIUS, "Landroidx/compose/ui/unit/Dp;", "color", "Landroidx/compose/runtime/State;", "Landroidx/compose/ui/graphics/Color;", "(ZFLandroidx/compose/runtime/State;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "findNearestViewGroup", "Landroid/view/ViewGroup;", "(Landroidx/compose/runtime/Composer;I)Landroid/view/ViewGroup;", "rememberUpdatedRippleInstance", "Landroidx/compose/material/ripple/RippleIndicationInstance;", "interactionSource", "Landroidx/compose/foundation/interaction/InteractionSource;", "rippleAlpha", "Landroidx/compose/material/ripple/RippleAlpha;", "rememberUpdatedRippleInstance-942rkJo", "(Landroidx/compose/foundation/interaction/InteractionSource;ZFLandroidx/compose/runtime/State;Landroidx/compose/runtime/State;Landroidx/compose/runtime/Composer;I)Landroidx/compose/material/ripple/RippleIndicationInstance;", "material-ripple_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class PlatformRipple extends Ripple {
    public /* synthetic */ PlatformRipple(boolean z, float f, State state, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, f, state);
    }

    private PlatformRipple(boolean z, float f, State<Color> state) {
        super(z, f, state, null);
    }

    @Override // androidx.compose.material.ripple.Ripple
    /* renamed from: rememberUpdatedRippleInstance-942rkJo */
    public RippleIndicationInstance mo1478rememberUpdatedRippleInstance942rkJo(InteractionSource interactionSource, boolean z, float f, State<Color> color, State<RippleAlpha> rippleAlpha, Composer composer, int i) {
        View rippleContainer;
        Intrinsics.checkNotNullParameter(interactionSource, "interactionSource");
        Intrinsics.checkNotNullParameter(color, "color");
        Intrinsics.checkNotNullParameter(rippleAlpha, "rippleAlpha");
        composer.startReplaceableGroup(1643266907);
        ComposerKt.sourceInformation(composer, "C(rememberUpdatedRippleInstance)P(2!1,3:c#ui.unit.Dp)64@2484L22,90@3354L160:Ripple.android.kt#vhb33q");
        ViewGroup viewGroupFindNearestViewGroup = findNearestViewGroup(composer, (i >> 15) & 14);
        if (viewGroupFindNearestViewGroup.isInEditMode()) {
            composer.startReplaceableGroup(1643267309);
            ComposerKt.sourceInformation(composer, "67@2658L133");
            composer.startReplaceableGroup(-3686552);
            ComposerKt.sourceInformation(composer, "C(remember)P(1,2):Composables.kt#9igjgp");
            boolean zChanged = composer.changed(interactionSource) | composer.changed(this);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new CommonRippleIndicationInstance(z, f, color, rippleAlpha, null);
                composer.updateRememberedValue(objRememberedValue);
            }
            composer.endReplaceableGroup();
            composer.endReplaceableGroup();
            composer.endReplaceableGroup();
            return (CommonRippleIndicationInstance) objRememberedValue;
        }
        composer.startReplaceableGroup(1643267473);
        composer.endReplaceableGroup();
        int childCount = viewGroupFindNearestViewGroup.getChildCount();
        if (childCount > 0) {
            int i2 = 0;
            while (true) {
                int i3 = i2 + 1;
                rippleContainer = viewGroupFindNearestViewGroup.getChildAt(i2);
                if (rippleContainer instanceof RippleContainer) {
                    break;
                }
                if (i3 >= childCount) {
                    break;
                }
                i2 = i3;
            }
        } else {
            rippleContainer = null;
        }
        if (rippleContainer == null) {
            Context context = viewGroupFindNearestViewGroup.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "view.context");
            rippleContainer = new RippleContainer(context);
            viewGroupFindNearestViewGroup.addView(rippleContainer);
            Unit unit = Unit.INSTANCE;
        }
        composer.startReplaceableGroup(-3686095);
        ComposerKt.sourceInformation(composer, "C(remember)P(1,2,3):Composables.kt#9igjgp");
        boolean zChanged2 = composer.changed(interactionSource) | composer.changed(this) | composer.changed(rippleContainer);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue2 = new AndroidRippleIndicationInstance(z, f, color, rippleAlpha, (RippleContainer) rippleContainer, null);
            composer.updateRememberedValue(objRememberedValue2);
        }
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        return (AndroidRippleIndicationInstance) objRememberedValue2;
    }

    private final ViewGroup findNearestViewGroup(Composer composer, int i) {
        composer.startReplaceableGroup(601470064);
        ComposerKt.sourceInformation(composer, "C(findNearestViewGroup)105@4003L7:Ripple.android.kt#vhb33q");
        ProvidableCompositionLocal<View> localView = AndroidCompositionLocals_androidKt.getLocalView();
        ComposerKt.sourceInformationMarkerStart(composer, 103361330, "C:CompositionLocal.kt#9igjgp");
        Object objConsume = composer.consume(localView);
        ComposerKt.sourceInformationMarkerEnd(composer);
        while (!(objConsume instanceof ViewGroup)) {
            ViewParent parent = ((View) objConsume).getParent();
            if (!(parent instanceof View)) {
                throw new IllegalArgumentException(("Couldn't find a valid parent for " + objConsume + ". Are you overriding LocalView and providing a View that is not attached to the view hierarchy?").toString());
            }
            Intrinsics.checkNotNullExpressionValue(parent, "parent");
            objConsume = parent;
        }
        ViewGroup viewGroup = (ViewGroup) objConsume;
        composer.endReplaceableGroup();
        return viewGroup;
    }
}
