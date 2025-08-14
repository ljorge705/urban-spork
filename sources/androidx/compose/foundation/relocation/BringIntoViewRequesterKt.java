package androidx.compose.foundation.relocation;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BringIntoViewRequester.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\b\u0010\u0000\u001a\u00020\u0001H\u0007\u001a\u0014\u0010\u0002\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0001H\u0007¨\u0006\u0004"}, d2 = {"BringIntoViewRequester", "Landroidx/compose/foundation/relocation/BringIntoViewRequester;", "bringIntoViewRequester", "Landroidx/compose/ui/Modifier;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class BringIntoViewRequesterKt {
    public static final BringIntoViewRequester BringIntoViewRequester() {
        return new BringIntoViewRequesterImpl();
    }

    public static final Modifier bringIntoViewRequester(Modifier modifier, final BringIntoViewRequester bringIntoViewRequester) {
        Intrinsics.checkNotNullParameter(modifier, "<this>");
        Intrinsics.checkNotNullParameter(bringIntoViewRequester, "bringIntoViewRequester");
        return ComposedModifierKt.composed(modifier, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.relocation.BringIntoViewRequesterKt$bringIntoViewRequester$$inlined$debugInspectorInfo$1
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
                inspectorInfo.setName("bringIntoViewRequester");
                inspectorInfo.getProperties().set("bringIntoViewRequester", bringIntoViewRequester);
            }
        } : InspectableValueKt.getNoInspectorInfo(), new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.foundation.relocation.BringIntoViewRequesterKt.bringIntoViewRequester.2
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier2, Composer composer, Integer num) {
                return invoke(modifier2, composer, num.intValue());
            }

            public final Modifier invoke(Modifier composed, Composer composer, int i) {
                Intrinsics.checkNotNullParameter(composed, "$this$composed");
                composer.startReplaceableGroup(-992853993);
                ComposerKt.sourceInformation(composer, "C106@4535L36,107@4591L91,111@4755L180:BringIntoViewRequester.kt#exjx5q");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-992853993, i, -1, "androidx.compose.foundation.relocation.bringIntoViewRequester.<anonymous> (BringIntoViewRequester.kt:105)");
                }
                BringIntoViewParent bringIntoViewParentRememberDefaultBringIntoViewParent = BringIntoViewResponder_androidKt.rememberDefaultBringIntoViewParent(composer, 0);
                composer.startReplaceableGroup(1157296644);
                ComposerKt.sourceInformation(composer, "CC(remember)P(1):Composables.kt#9igjgp");
                boolean zChanged = composer.changed(bringIntoViewParentRememberDefaultBringIntoViewParent);
                Object objRememberedValue = composer.rememberedValue();
                if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new BringIntoViewRequesterModifier(bringIntoViewParentRememberDefaultBringIntoViewParent);
                    composer.updateRememberedValue(objRememberedValue);
                }
                composer.endReplaceableGroup();
                final BringIntoViewRequesterModifier bringIntoViewRequesterModifier = (BringIntoViewRequesterModifier) objRememberedValue;
                BringIntoViewRequester bringIntoViewRequester2 = bringIntoViewRequester;
                if (bringIntoViewRequester2 instanceof BringIntoViewRequesterImpl) {
                    final BringIntoViewRequester bringIntoViewRequester3 = bringIntoViewRequester;
                    EffectsKt.DisposableEffect(bringIntoViewRequester2, new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.foundation.relocation.BringIntoViewRequesterKt.bringIntoViewRequester.2.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final DisposableEffectResult invoke(DisposableEffectScope DisposableEffect) {
                            Intrinsics.checkNotNullParameter(DisposableEffect, "$this$DisposableEffect");
                            ((BringIntoViewRequesterImpl) bringIntoViewRequester3).getModifiers().add(bringIntoViewRequesterModifier);
                            final BringIntoViewRequester bringIntoViewRequester4 = bringIntoViewRequester3;
                            final BringIntoViewRequesterModifier bringIntoViewRequesterModifier2 = bringIntoViewRequesterModifier;
                            return new DisposableEffectResult() { // from class: androidx.compose.foundation.relocation.BringIntoViewRequesterKt$bringIntoViewRequester$2$1$invoke$$inlined$onDispose$1
                                @Override // androidx.compose.runtime.DisposableEffectResult
                                public void dispose() {
                                    ((BringIntoViewRequesterImpl) bringIntoViewRequester4).getModifiers().remove(bringIntoViewRequesterModifier2);
                                }
                            };
                        }
                    }, composer, 0);
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer.endReplaceableGroup();
                return bringIntoViewRequesterModifier;
            }
        });
    }
}
