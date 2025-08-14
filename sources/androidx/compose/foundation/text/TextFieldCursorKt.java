package androidx.compose.foundation.text;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimatableKt;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.KeyframesSpec;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.input.OffsetMapping;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.unit.Dp;
import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: TextFieldCursor.kt */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a4\u0010\b\u001a\u00020\t*\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0000\"\u0019\u0010\u0000\u001a\u00020\u0001X\u0080\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\u0002\u0010\u0003\"\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0014"}, d2 = {"DefaultCursorThickness", "Landroidx/compose/ui/unit/Dp;", "getDefaultCursorThickness", "()F", "F", "cursorAnimationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "", "cursor", "Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/foundation/text/TextFieldState;", "value", "Landroidx/compose/ui/text/input/TextFieldValue;", "offsetMapping", "Landroidx/compose/ui/text/input/OffsetMapping;", "cursorBrush", "Landroidx/compose/ui/graphics/Brush;", ViewProps.ENABLED, "", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class TextFieldCursorKt {
    private static final AnimationSpec<Float> cursorAnimationSpec = AnimationSpecKt.m367infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.foundation.text.TextFieldCursorKt$cursorAnimationSpec$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
            invoke2(keyframesSpecConfig);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframes) {
            Intrinsics.checkNotNullParameter(keyframes, "$this$keyframes");
            keyframes.setDurationMillis(1000);
            Float fValueOf = Float.valueOf(1.0f);
            keyframes.at(fValueOf, 0);
            keyframes.at(fValueOf, 499);
            Float fValueOf2 = Float.valueOf(0.0f);
            keyframes.at(fValueOf2, 500);
            keyframes.at(fValueOf2, 999);
        }
    }), null, 0, 6, null);
    private static final float DefaultCursorThickness = Dp.m4390constructorimpl(2);

    public static final float getDefaultCursorThickness() {
        return DefaultCursorThickness;
    }

    public static final Modifier cursor(Modifier modifier, final TextFieldState state, final TextFieldValue value, final OffsetMapping offsetMapping, final Brush cursorBrush, boolean z) {
        Intrinsics.checkNotNullParameter(modifier, "<this>");
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(offsetMapping, "offsetMapping");
        Intrinsics.checkNotNullParameter(cursorBrush, "cursorBrush");
        return z ? ComposedModifierKt.composed$default(modifier, null, new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.foundation.text.TextFieldCursorKt.cursor.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier2, Composer composer, Integer num) {
                return invoke(modifier2, composer, num.intValue());
            }

            public final Modifier invoke(Modifier composed, Composer composer, int i) {
                Modifier.Companion companionDrawWithContent;
                Intrinsics.checkNotNullParameter(composed, "$this$composed");
                composer.startReplaceableGroup(1634330012);
                ComposerKt.sourceInformation(composer, "C46@1774L27,49@1976L491:TextFieldCursor.kt#423gt5");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1634330012, i, -1, "androidx.compose.foundation.text.cursor.<anonymous> (TextFieldCursor.kt:45)");
                }
                composer.startReplaceableGroup(-492369756);
                ComposerKt.sourceInformation(composer, "CC(remember):Composables.kt#9igjgp");
                Object objRememberedValue = composer.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = AnimatableKt.Animatable$default(1.0f, 0.0f, 2, null);
                    composer.updateRememberedValue(objRememberedValue);
                }
                composer.endReplaceableGroup();
                final Animatable animatable = (Animatable) objRememberedValue;
                Brush brush = cursorBrush;
                boolean z2 = ((brush instanceof SolidColor) && ((SolidColor) brush).getValue() == Color.INSTANCE.m1913getUnspecified0d7_KjU()) ? false : true;
                if (state.getHasFocus() && TextRange.m3899getCollapsedimpl(value.getSelection()) && z2) {
                    EffectsKt.LaunchedEffect(value.getAnnotatedString(), TextRange.m3893boximpl(value.getSelection()), new C00421(animatable, null), composer, 512);
                    final OffsetMapping offsetMapping2 = offsetMapping;
                    final TextFieldValue textFieldValue = value;
                    final TextFieldState textFieldState = state;
                    final Brush brush2 = cursorBrush;
                    companionDrawWithContent = DrawModifierKt.drawWithContent(composed, new Function1<ContentDrawScope, Unit>() { // from class: androidx.compose.foundation.text.TextFieldCursorKt.cursor.1.2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(ContentDrawScope contentDrawScope) {
                            invoke2(contentDrawScope);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(ContentDrawScope drawWithContent) {
                            Rect rect;
                            TextLayoutResult value2;
                            Intrinsics.checkNotNullParameter(drawWithContent, "$this$drawWithContent");
                            drawWithContent.drawContent();
                            float fCoerceIn = RangesKt.coerceIn(animatable.getValue().floatValue(), 0.0f, 1.0f);
                            if (fCoerceIn == 0.0f) {
                                return;
                            }
                            int iOriginalToTransformed = offsetMapping2.originalToTransformed(TextRange.m3905getStartimpl(textFieldValue.getSelection()));
                            TextLayoutResultProxy layoutResult = textFieldState.getLayoutResult();
                            if (layoutResult == null || (value2 = layoutResult.getValue()) == null || (rect = value2.getCursorRect(iOriginalToTransformed)) == null) {
                                rect = new Rect(0.0f, 0.0f, 0.0f, 0.0f);
                            }
                            float f = drawWithContent.mo577toPx0680j_4(TextFieldCursorKt.getDefaultCursorThickness());
                            float f2 = f / 2;
                            float fCoerceAtMost = RangesKt.coerceAtMost(rect.getLeft() + f2, Size.m1708getWidthimpl(drawWithContent.mo2417getSizeNHjbRc()) - f2);
                            DrawScope.m2403drawLine1RTmtNc$default(drawWithContent, brush2, OffsetKt.Offset(fCoerceAtMost, rect.getTop()), OffsetKt.Offset(fCoerceAtMost, rect.getBottom()), f, 0, null, fCoerceIn, null, 0, 432, null);
                        }
                    });
                } else {
                    companionDrawWithContent = Modifier.INSTANCE;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer.endReplaceableGroup();
                return companionDrawWithContent;
            }

            /* compiled from: TextFieldCursor.kt */
            @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.foundation.text.TextFieldCursorKt$cursor$1$1", f = "TextFieldCursor.kt", i = {}, l = {52}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: androidx.compose.foundation.text.TextFieldCursorKt$cursor$1$1, reason: invalid class name and collision with other inner class name */
            static final class C00421 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ Animatable<Float, AnimationVector1D> $cursorAlpha;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C00421(Animatable<Float, AnimationVector1D> animatable, Continuation<? super C00421> continuation) {
                    super(2, continuation);
                    this.$cursorAlpha = animatable;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new C00421(this.$cursorAlpha, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C00421) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                /* compiled from: TextFieldCursor.kt */
                @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
                @DebugMetadata(c = "androidx.compose.foundation.text.TextFieldCursorKt$cursor$1$1$1", f = "TextFieldCursor.kt", i = {}, l = {54, 56}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: androidx.compose.foundation.text.TextFieldCursorKt$cursor$1$1$1, reason: invalid class name and collision with other inner class name */
                static final class C00431 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    final /* synthetic */ Animatable<Float, AnimationVector1D> $cursorAlpha;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    C00431(Animatable<Float, AnimationVector1D> animatable, Continuation<? super C00431> continuation) {
                        super(2, continuation);
                        this.$cursorAlpha = animatable;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        return new C00431(this.$cursorAlpha, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((C00431) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        int i = this.label;
                        if (i == 0) {
                            ResultKt.throwOnFailure(obj);
                            this.label = 1;
                            if (this.$cursorAlpha.snapTo(Boxing.boxFloat(1.0f), this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        } else {
                            if (i != 1) {
                                if (i != 2) {
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                }
                                ResultKt.throwOnFailure(obj);
                                return Unit.INSTANCE;
                            }
                            ResultKt.throwOnFailure(obj);
                        }
                        this.label = 2;
                        if (Animatable.animateTo$default(this.$cursorAlpha, Boxing.boxFloat(0.0f), TextFieldCursorKt.cursorAnimationSpec, null, null, this, 12, null) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        return Unit.INSTANCE;
                    }
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        this.label = 1;
                        if (BuildersKt.withContext(FixedMotionDurationScale.INSTANCE, new C00431(this.$cursorAlpha, null), this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    return Unit.INSTANCE;
                }
            }
        }, 1, null) : modifier;
    }
}
