package androidx.compose.foundation;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.animation.core.StartOffset;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: BasicMarquee.kt */
@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001d\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u000fH\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0017\u001aK\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\u0006\u0010\u001b\u001a\u00020\u00012\u0006\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u00012\u0006\u0010\u001e\u001a\u00020\u00012\u0006\u0010\u001f\u001a\u00020\u000f2\u0006\u0010 \u001a\u00020!H\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\"\u0010#\u001a&\u0010$\u001a\b\u0012\u0004\u0012\u00020\u001a0%2\u0006\u0010\u001f\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00020\u0001H\u0002\u001aU\u0010&\u001a\u00020'*\u00020'2\b\b\u0002\u0010\u001b\u001a\u00020\u00012\b\b\u0002\u0010(\u001a\u00020)2\b\b\u0002\u0010\u001e\u001a\u00020\u00012\b\b\u0002\u0010\u001d\u001a\u00020\u00012\b\b\u0002\u0010\u0015\u001a\u00020\n2\b\b\u0002\u0010\u001f\u001a\u00020\u000fH\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b*\u0010+\"\u001c\u0010\u0000\u001a\u00020\u00018GX\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0002\u0010\u0003\u001a\u0004\b\u0004\u0010\u0005\"\u001c\u0010\u0006\u001a\u00020\u00018GX\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0007\u0010\u0003\u001a\u0004\b\b\u0010\u0005\"\u001c\u0010\t\u001a\u00020\n8GX\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\u0003\u001a\u0004\b\f\u0010\r\"!\u0010\u000e\u001a\u00020\u000f8GX\u0087\u0004ø\u0001\u0000¢\u0006\u0010\n\u0002\u0010\u0013\u0012\u0004\b\u0010\u0010\u0003\u001a\u0004\b\u0011\u0010\u0012\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006,"}, d2 = {"DefaultMarqueeDelayMillis", "", "getDefaultMarqueeDelayMillis$annotations", "()V", "getDefaultMarqueeDelayMillis", "()I", "DefaultMarqueeIterations", "getDefaultMarqueeIterations$annotations", "getDefaultMarqueeIterations", "DefaultMarqueeSpacing", "Landroidx/compose/foundation/MarqueeSpacing;", "getDefaultMarqueeSpacing$annotations", "getDefaultMarqueeSpacing", "()Landroidx/compose/foundation/MarqueeSpacing;", "DefaultMarqueeVelocity", "Landroidx/compose/ui/unit/Dp;", "getDefaultMarqueeVelocity$annotations", "getDefaultMarqueeVelocity", "()F", "F", "MarqueeSpacing", "spacing", "MarqueeSpacing-0680j_4", "(F)Landroidx/compose/foundation/MarqueeSpacing;", "createMarqueeAnimationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "", "iterations", "targetValue", "initialDelayMillis", "delayMillis", "velocity", "density", "Landroidx/compose/ui/unit/Density;", "createMarqueeAnimationSpec-Z4HSEVQ", "(IFIIFLandroidx/compose/ui/unit/Density;)Landroidx/compose/animation/core/AnimationSpec;", "velocityBasedTween", "Landroidx/compose/animation/core/TweenSpec;", "basicMarquee", "Landroidx/compose/ui/Modifier;", "animationMode", "Landroidx/compose/foundation/MarqueeAnimationMode;", "basicMarquee-1Mj1MLw", "(Landroidx/compose/ui/Modifier;IIIILandroidx/compose/foundation/MarqueeSpacing;F)Landroidx/compose/ui/Modifier;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class BasicMarqueeKt {
    private static final int DefaultMarqueeDelayMillis = 1200;
    private static final int DefaultMarqueeIterations = 3;
    private static final MarqueeSpacing DefaultMarqueeSpacing = MarqueeSpacing.INSTANCE.fractionOfContainer(0.33333334f);
    private static final float DefaultMarqueeVelocity = Dp.m4390constructorimpl(30);

    public static final int getDefaultMarqueeDelayMillis() {
        return DefaultMarqueeDelayMillis;
    }

    public static /* synthetic */ void getDefaultMarqueeDelayMillis$annotations() {
    }

    public static final int getDefaultMarqueeIterations() {
        return DefaultMarqueeIterations;
    }

    public static /* synthetic */ void getDefaultMarqueeIterations$annotations() {
    }

    public static final MarqueeSpacing getDefaultMarqueeSpacing() {
        return DefaultMarqueeSpacing;
    }

    public static /* synthetic */ void getDefaultMarqueeSpacing$annotations() {
    }

    public static final float getDefaultMarqueeVelocity() {
        return DefaultMarqueeVelocity;
    }

    public static /* synthetic */ void getDefaultMarqueeVelocity$annotations() {
    }

    /* renamed from: basicMarquee-1Mj1MLw$default, reason: not valid java name */
    public static /* synthetic */ Modifier m431basicMarquee1Mj1MLw$default(Modifier modifier, int i, int i2, int i3, int i4, MarqueeSpacing marqueeSpacing, float f, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            i = DefaultMarqueeIterations;
        }
        if ((i5 & 2) != 0) {
            i2 = MarqueeAnimationMode.INSTANCE.m486getImmediatelyZbEOnfQ();
        }
        int i6 = i2;
        if ((i5 & 4) != 0) {
            i3 = DefaultMarqueeDelayMillis;
        }
        int i7 = i3;
        if ((i5 & 8) != 0) {
            i4 = MarqueeAnimationMode.m480equalsimpl0(i6, MarqueeAnimationMode.INSTANCE.m486getImmediatelyZbEOnfQ()) ? i7 : 0;
        }
        int i8 = i4;
        if ((i5 & 16) != 0) {
            marqueeSpacing = DefaultMarqueeSpacing;
        }
        MarqueeSpacing marqueeSpacing2 = marqueeSpacing;
        if ((i5 & 32) != 0) {
            f = DefaultMarqueeVelocity;
        }
        return m430basicMarquee1Mj1MLw(modifier, i, i6, i7, i8, marqueeSpacing2, f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: createMarqueeAnimationSpec-Z4HSEVQ, reason: not valid java name */
    public static final AnimationSpec<Float> m432createMarqueeAnimationSpecZ4HSEVQ(int i, float f, int i2, int i3, float f2, Density density) {
        TweenSpec<Float> tweenSpecVelocityBasedTween = velocityBasedTween(Math.abs(density.mo577toPx0680j_4(f2)), f, i3);
        long jM387constructorimpl$default = StartOffset.m387constructorimpl$default((-i3) + i2, 0, 2, null);
        if (i == Integer.MAX_VALUE) {
            return AnimationSpecKt.m367infiniteRepeatable9IiC70o$default(tweenSpecVelocityBasedTween, null, jM387constructorimpl$default, 2, null);
        }
        return AnimationSpecKt.m369repeatable91I0pcU$default(i, tweenSpecVelocityBasedTween, null, jM387constructorimpl$default, 4, null);
    }

    private static final TweenSpec<Float> velocityBasedTween(float f, float f2, int i) {
        return AnimationSpecKt.tween((int) Math.ceil(f2 / (f / 1000.0f)), i, EasingKt.getLinearEasing());
    }

    /* renamed from: MarqueeSpacing-0680j_4, reason: not valid java name */
    public static final MarqueeSpacing m428MarqueeSpacing0680j_4(final float f) {
        return new MarqueeSpacing() { // from class: androidx.compose.foundation.BasicMarqueeKt$MarqueeSpacing$1
            @Override // androidx.compose.foundation.MarqueeSpacing
            public final int calculateSpacing(Density MarqueeSpacing, int i, int i2) {
                Intrinsics.checkNotNullParameter(MarqueeSpacing, "$this$MarqueeSpacing");
                return MarqueeSpacing.mo571roundToPx0680j_4(f);
            }
        };
    }

    /* renamed from: basicMarquee-1Mj1MLw, reason: not valid java name */
    public static final Modifier m430basicMarquee1Mj1MLw(Modifier basicMarquee, final int i, final int i2, final int i3, final int i4, final MarqueeSpacing spacing, final float f) {
        Intrinsics.checkNotNullParameter(basicMarquee, "$this$basicMarquee");
        Intrinsics.checkNotNullParameter(spacing, "spacing");
        return ComposedModifierKt.composed(basicMarquee, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.BasicMarqueeKt$basicMarquee-1Mj1MLw$$inlined$debugInspectorInfo$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo inspectorInfo) {
                Intrinsics.checkNotNullParameter(inspectorInfo, "$this$null");
                inspectorInfo.setName("basicMarquee");
                inspectorInfo.getProperties().set("iterations", Integer.valueOf(i));
                inspectorInfo.getProperties().set("animationMode", MarqueeAnimationMode.m477boximpl(i2));
                inspectorInfo.getProperties().set("delayMillis", Integer.valueOf(i3));
                inspectorInfo.getProperties().set("initialDelayMillis", Integer.valueOf(i4));
                inspectorInfo.getProperties().set("spacing", spacing);
                inspectorInfo.getProperties().set("velocity", Dp.m4388boximpl(f));
            }
        } : InspectableValueKt.getNoInspectorInfo(), new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.foundation.BasicMarqueeKt$basicMarquee$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier, Composer composer, Integer num) {
                return invoke(modifier, composer, num.intValue());
            }

            public final Modifier invoke(Modifier composed, Composer composer, int i5) {
                Intrinsics.checkNotNullParameter(composed, "$this$composed");
                composer.startReplaceableGroup(-562302205);
                ComposerKt.sourceInformation(composer, "C151@7528L7,152@7583L7,153@7610L421,172@8136L39,172@8111L64:BasicMarquee.kt#71ulvw");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-562302205, i5, -1, "androidx.compose.foundation.basicMarquee.<anonymous> (BasicMarquee.kt:150)");
                }
                ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume = composer.consume(localDensity);
                ComposerKt.sourceInformationMarkerEnd(composer);
                Density density = (Density) objConsume;
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume2 = composer.consume(localLayoutDirection);
                ComposerKt.sourceInformationMarkerEnd(composer);
                LayoutDirection layoutDirection = (LayoutDirection) objConsume2;
                Object[] objArr = {Integer.valueOf(i), Integer.valueOf(i3), Integer.valueOf(i4), Dp.m4388boximpl(f), density, layoutDirection};
                int i6 = i;
                int i7 = i3;
                int i8 = i4;
                float f2 = f;
                composer.startReplaceableGroup(-568225417);
                ComposerKt.sourceInformation(composer, "CC(remember)P(1):Composables.kt#9igjgp");
                boolean zChanged = false;
                for (int i9 = 0; i9 < 6; i9++) {
                    zChanged |= composer.changed(objArr[i9]);
                }
                Object objRememberedValue = composer.rememberedValue();
                if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new MarqueeModifier(i6, i7, i8, Dp.m4390constructorimpl(f2 * (layoutDirection == LayoutDirection.Ltr ? 1.0f : -1.0f)), density, null);
                    composer.updateRememberedValue(objRememberedValue);
                }
                composer.endReplaceableGroup();
                MarqueeModifier marqueeModifier = (MarqueeModifier) objRememberedValue;
                marqueeModifier.setSpacing(spacing);
                marqueeModifier.m489setAnimationMode97h66l8(i2);
                composer.startReplaceableGroup(1157296644);
                ComposerKt.sourceInformation(composer, "CC(remember)P(1):Composables.kt#9igjgp");
                boolean zChanged2 = composer.changed(marqueeModifier);
                Object objRememberedValue2 = composer.rememberedValue();
                if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue2 = (Function2) new BasicMarqueeKt$basicMarquee$2$1$1(marqueeModifier, null);
                    composer.updateRememberedValue(objRememberedValue2);
                }
                composer.endReplaceableGroup();
                EffectsKt.LaunchedEffect(marqueeModifier, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue2, composer, 64);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer.endReplaceableGroup();
                return marqueeModifier;
            }
        });
    }
}
