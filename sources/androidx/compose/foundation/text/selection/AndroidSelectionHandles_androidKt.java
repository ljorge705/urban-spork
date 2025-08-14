package androidx.compose.foundation.text.selection;

import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.text.Handle;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.CacheDrawScope;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.draw.DrawResult;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.CanvasKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.graphics.ImageBitmapConfig;
import androidx.compose.ui.graphics.ImageBitmapKt;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertyKey;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.window.AndroidPopup_androidKt;
import androidx.compose.ui.window.PopupProperties;
import com.clevertap.android.sdk.Constants;
import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* compiled from: AndroidSelectionHandles.android.kt */
@Metadata(d1 = {"\u0000F\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\u001a-\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H\u0001¢\u0006\u0002\u0010\t\u001a8\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0011\u0010\u000f\u001a\r\u0012\u0004\u0012\u00020\u00010\u0010¢\u0006\u0002\b\u0011H\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0012\u0010\u0013\u001aR\u0010\u0014\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u00032\u0013\u0010\u000f\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0010¢\u0006\u0002\b\u0011H\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0015\u0010\u0016\u001a\u0018\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u0005H\u0000\u001a \u0010\u0019\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H\u0002\u001a\u0014\u0010\u001a\u001a\u00020\u001b*\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0000\u001a$\u0010\u001f\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H\u0000\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006 "}, d2 = {"DefaultSelectionHandle", "", "modifier", "Landroidx/compose/ui/Modifier;", "isStartHandle", "", "direction", "Landroidx/compose/ui/text/style/ResolvedTextDirection;", "handlesCrossed", "(Landroidx/compose/ui/Modifier;ZLandroidx/compose/ui/text/style/ResolvedTextDirection;ZLandroidx/compose/runtime/Composer;I)V", "HandlePopup", ViewProps.POSITION, "Landroidx/compose/ui/geometry/Offset;", "handleReferencePoint", "Landroidx/compose/foundation/text/selection/HandleReferencePoint;", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "HandlePopup-ULxng0E", "(JLandroidx/compose/foundation/text/selection/HandleReferencePoint;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "SelectionHandle", "SelectionHandle-8fL75-g", "(JZLandroidx/compose/ui/text/style/ResolvedTextDirection;ZLandroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "isHandleLtrDirection", "areHandlesCrossed", "isLeft", "createHandleImage", "Landroidx/compose/ui/graphics/ImageBitmap;", "Landroidx/compose/ui/draw/CacheDrawScope;", Constants.KEY_RADIUS, "", "drawSelectionHandle", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class AndroidSelectionHandles_androidKt {
    /* renamed from: SelectionHandle-8fL75-g, reason: not valid java name */
    public static final void m1073SelectionHandle8fL75g(final long j, final boolean z, final ResolvedTextDirection direction, final boolean z2, final Modifier modifier, final Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i) {
        int i2;
        HandleReferencePoint handleReferencePoint;
        Intrinsics.checkNotNullParameter(direction, "direction");
        Intrinsics.checkNotNullParameter(modifier, "modifier");
        Composer composerStartRestartGroup = composer.startRestartGroup(-616295642);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(SelectionHandle)P(5:c#ui.geometry.Offset,3,1,2,4)69@2795L823:AndroidSelectionHandles.android.kt#eksfi3");
        if ((i & 14) == 0) {
            i2 = (composerStartRestartGroup.changed(j) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 112) == 0) {
            i2 |= composerStartRestartGroup.changed(z) ? 32 : 16;
        }
        if ((i & 896) == 0) {
            i2 |= composerStartRestartGroup.changed(direction) ? 256 : 128;
        }
        if ((i & 7168) == 0) {
            i2 |= composerStartRestartGroup.changed(z2) ? 2048 : 1024;
        }
        if ((57344 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(modifier) ? 16384 : 8192;
        }
        if ((458752 & i) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function2) ? 131072 : 65536;
        }
        final int i3 = i2;
        if ((374491 & i3) != 74898 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-616295642, i3, -1, "androidx.compose.foundation.text.selection.SelectionHandle (AndroidSelectionHandles.android.kt:53)");
            }
            if (isLeft(z, direction, z2)) {
                handleReferencePoint = HandleReferencePoint.TopRight;
            } else {
                handleReferencePoint = HandleReferencePoint.TopLeft;
            }
            m1072HandlePopupULxng0E(j, handleReferencePoint, ComposableLambdaKt.composableLambda(composerStartRestartGroup, 732099485, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$SelectionHandle$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                    invoke(composer2, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i4) {
                    ComposerKt.sourceInformation(composer2, "C:AndroidSelectionHandles.android.kt#eksfi3");
                    if ((i4 & 11) != 2 || !composer2.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(732099485, i4, -1, "androidx.compose.foundation.text.selection.SelectionHandle.<anonymous> (AndroidSelectionHandles.android.kt:69)");
                        }
                        if (function2 == null) {
                            composer2.startReplaceableGroup(386443790);
                            ComposerKt.sourceInformation(composer2, "73@3009L405,71@2918L645");
                            Modifier modifier2 = modifier;
                            Boolean boolValueOf = Boolean.valueOf(z);
                            Offset offsetM1628boximpl = Offset.m1628boximpl(j);
                            final boolean z3 = z;
                            final long j2 = j;
                            composer2.startReplaceableGroup(511388516);
                            ComposerKt.sourceInformation(composer2, "CC(remember)P(1,2):Composables.kt#9igjgp");
                            boolean zChanged = composer2.changed(boolValueOf) | composer2.changed(offsetM1628boximpl);
                            Object objRememberedValue = composer2.rememberedValue();
                            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$SelectionHandle$1$1$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                                        invoke2(semanticsPropertyReceiver);
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(SemanticsPropertyReceiver semantics) {
                                        Handle handle;
                                        Intrinsics.checkNotNullParameter(semantics, "$this$semantics");
                                        SemanticsPropertyKey<SelectionHandleInfo> selectionHandleInfoKey = SelectionHandlesKt.getSelectionHandleInfoKey();
                                        if (z3) {
                                            handle = Handle.SelectionStart;
                                        } else {
                                            handle = Handle.SelectionEnd;
                                        }
                                        semantics.set(selectionHandleInfoKey, new SelectionHandleInfo(handle, j2, null));
                                    }
                                };
                                composer2.updateRememberedValue(objRememberedValue);
                            }
                            composer2.endReplaceableGroup();
                            Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(modifier2, false, (Function1) objRememberedValue, 1, null);
                            boolean z4 = z;
                            ResolvedTextDirection resolvedTextDirection = direction;
                            boolean z5 = z2;
                            int i5 = i3;
                            AndroidSelectionHandles_androidKt.DefaultSelectionHandle(modifierSemantics$default, z4, resolvedTextDirection, z5, composer2, (i5 & 112) | (i5 & 896) | (i5 & 7168));
                            composer2.endReplaceableGroup();
                        } else {
                            composer2.startReplaceableGroup(386444465);
                            ComposerKt.sourceInformation(composer2, "88@3593L9");
                            function2.invoke(composer2, Integer.valueOf((i3 >> 15) & 14));
                            composer2.endReplaceableGroup();
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    composer2.skipToGroupEnd();
                }
            }), composerStartRestartGroup, (i3 & 14) | 384);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$SelectionHandle$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                invoke(composer2, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer2, int i4) {
                AndroidSelectionHandles_androidKt.m1073SelectionHandle8fL75g(j, z, direction, z2, modifier, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
            }
        });
    }

    public static final void DefaultSelectionHandle(final Modifier modifier, final boolean z, final ResolvedTextDirection direction, final boolean z2, Composer composer, final int i) {
        int i2;
        Intrinsics.checkNotNullParameter(modifier, "modifier");
        Intrinsics.checkNotNullParameter(direction, "direction");
        Composer composerStartRestartGroup = composer.startRestartGroup(47957398);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(DefaultSelectionHandle)P(3,2)101@3820L137:AndroidSelectionHandles.android.kt#eksfi3");
        if ((i & 14) == 0) {
            i2 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 112) == 0) {
            i2 |= composerStartRestartGroup.changed(z) ? 32 : 16;
        }
        if ((i & 896) == 0) {
            i2 |= composerStartRestartGroup.changed(direction) ? 256 : 128;
        }
        if ((i & 7168) == 0) {
            i2 |= composerStartRestartGroup.changed(z2) ? 2048 : 1024;
        }
        if ((i2 & 5851) != 1170 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(47957398, i, -1, "androidx.compose.foundation.text.selection.DefaultSelectionHandle (AndroidSelectionHandles.android.kt:95)");
            }
            SpacerKt.Spacer(drawSelectionHandle(SizeKt.m735sizeVpY3zN4(modifier, SelectionHandlesKt.getHandleWidth(), SelectionHandlesKt.getHandleHeight()), z, direction, z2), composerStartRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt.DefaultSelectionHandle.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                invoke(composer2, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer2, int i3) {
                AndroidSelectionHandles_androidKt.DefaultSelectionHandle(modifier, z, direction, z2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
            }
        });
    }

    public static final Modifier drawSelectionHandle(Modifier modifier, final boolean z, final ResolvedTextDirection direction, final boolean z2) {
        Intrinsics.checkNotNullParameter(modifier, "<this>");
        Intrinsics.checkNotNullParameter(direction, "direction");
        return ComposedModifierKt.composed$default(modifier, null, new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt.drawSelectionHandle.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier2, Composer composer, Integer num) {
                return invoke(modifier2, composer, num.intValue());
            }

            public final Modifier invoke(Modifier composed, Composer composer, int i) {
                Intrinsics.checkNotNullParameter(composed, "$this$composed");
                composer.startReplaceableGroup(-1538687176);
                ComposerKt.sourceInformation(composer, "C113@4195L7,115@4261L847:AndroidSelectionHandles.android.kt#eksfi3");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1538687176, i, -1, "androidx.compose.foundation.text.selection.drawSelectionHandle.<anonymous> (AndroidSelectionHandles.android.kt:112)");
                }
                ProvidableCompositionLocal<TextSelectionColors> localTextSelectionColors = TextSelectionColorsKt.getLocalTextSelectionColors();
                ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume = composer.consume(localTextSelectionColors);
                ComposerKt.sourceInformationMarkerEnd(composer);
                final long handleColor = ((TextSelectionColors) objConsume).getHandleColor();
                Modifier.Companion companion = Modifier.INSTANCE;
                Object[] objArr = {Color.m1867boximpl(handleColor), Boolean.valueOf(z), direction, Boolean.valueOf(z2)};
                final boolean z3 = z;
                final ResolvedTextDirection resolvedTextDirection = direction;
                final boolean z4 = z2;
                composer.startReplaceableGroup(-568225417);
                ComposerKt.sourceInformation(composer, "CC(remember)P(1):Composables.kt#9igjgp");
                boolean zChanged = false;
                for (int i2 = 0; i2 < 4; i2++) {
                    zChanged |= composer.changed(objArr[i2]);
                }
                Object objRememberedValue = composer.rememberedValue();
                if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = (Function1) new Function1<CacheDrawScope, DrawResult>() { // from class: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$drawSelectionHandle$1$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final DrawResult invoke(CacheDrawScope drawWithCache) {
                            Intrinsics.checkNotNullParameter(drawWithCache, "$this$drawWithCache");
                            final ImageBitmap imageBitmapCreateHandleImage = AndroidSelectionHandles_androidKt.createHandleImage(drawWithCache, Size.m1708getWidthimpl(drawWithCache.m1551getSizeNHjbRc()) / 2.0f);
                            final ColorFilter colorFilterM1918tintxETnrds$default = ColorFilter.Companion.m1918tintxETnrds$default(ColorFilter.INSTANCE, handleColor, 0, 2, null);
                            final boolean z5 = z3;
                            final ResolvedTextDirection resolvedTextDirection2 = resolvedTextDirection;
                            final boolean z6 = z4;
                            return drawWithCache.onDrawWithContent(new Function1<ContentDrawScope, Unit>() { // from class: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$drawSelectionHandle$1$1$1.1
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
                                public final void invoke2(ContentDrawScope onDrawWithContent) {
                                    Intrinsics.checkNotNullParameter(onDrawWithContent, "$this$onDrawWithContent");
                                    onDrawWithContent.drawContent();
                                    if (AndroidSelectionHandles_androidKt.isLeft(z5, resolvedTextDirection2, z6)) {
                                        ContentDrawScope contentDrawScope = onDrawWithContent;
                                        ImageBitmap imageBitmap = imageBitmapCreateHandleImage;
                                        ColorFilter colorFilter = colorFilterM1918tintxETnrds$default;
                                        long jMo2416getCenterF1C5BW0 = contentDrawScope.mo2416getCenterF1C5BW0();
                                        DrawContext drawContext = contentDrawScope.getDrawContext();
                                        long jMo2342getSizeNHjbRc = drawContext.mo2342getSizeNHjbRc();
                                        drawContext.getCanvas().save();
                                        drawContext.getTransform().mo2349scale0AR0LA0(-1.0f, 1.0f, jMo2416getCenterF1C5BW0);
                                        DrawScope.m2402drawImagegbVJVH8$default(contentDrawScope, imageBitmap, 0L, 0.0f, null, colorFilter, 0, 46, null);
                                        drawContext.getCanvas().restore();
                                        drawContext.mo2343setSizeuvyYCjk(jMo2342getSizeNHjbRc);
                                        return;
                                    }
                                    DrawScope.m2402drawImagegbVJVH8$default(onDrawWithContent, imageBitmapCreateHandleImage, 0L, 0.0f, null, colorFilterM1918tintxETnrds$default, 0, 46, null);
                                }
                            });
                        }
                    };
                    composer.updateRememberedValue(objRememberedValue);
                }
                composer.endReplaceableGroup();
                Modifier modifierThen = composed.then(DrawModifierKt.drawWithCache(companion, (Function1) objRememberedValue));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer.endReplaceableGroup();
                return modifierThen;
            }
        }, 1, null);
    }

    public static final ImageBitmap createHandleImage(CacheDrawScope cacheDrawScope, float f) {
        Intrinsics.checkNotNullParameter(cacheDrawScope, "<this>");
        int iCeil = ((int) Math.ceil(f)) * 2;
        ImageBitmap imageBitmap = HandleImageCache.INSTANCE.getImageBitmap();
        Canvas canvas = HandleImageCache.INSTANCE.getCanvas();
        CanvasDrawScope canvasDrawScope = HandleImageCache.INSTANCE.getCanvasDrawScope();
        if (imageBitmap == null || canvas == null || iCeil > imageBitmap.getWidth() || iCeil > imageBitmap.getHeight()) {
            imageBitmap = ImageBitmapKt.m2098ImageBitmapx__hDU$default(iCeil, iCeil, ImageBitmapConfig.INSTANCE.m2092getAlpha8_sVssgQ(), false, null, 24, null);
            HandleImageCache.INSTANCE.setImageBitmap(imageBitmap);
            canvas = CanvasKt.Canvas(imageBitmap);
            HandleImageCache.INSTANCE.setCanvas(canvas);
        }
        ImageBitmap imageBitmap2 = imageBitmap;
        Canvas canvas2 = canvas;
        if (canvasDrawScope == null) {
            canvasDrawScope = new CanvasDrawScope();
            HandleImageCache.INSTANCE.setCanvasDrawScope(canvasDrawScope);
        }
        CanvasDrawScope canvasDrawScope2 = canvasDrawScope;
        LayoutDirection layoutDirection = cacheDrawScope.getLayoutDirection();
        long jSize = androidx.compose.ui.geometry.SizeKt.Size(imageBitmap2.getWidth(), imageBitmap2.getHeight());
        CanvasDrawScope.DrawParams drawParams = canvasDrawScope2.getDrawParams();
        Density density = drawParams.getDensity();
        LayoutDirection layoutDirection2 = drawParams.getLayoutDirection();
        Canvas canvas3 = drawParams.getCanvas();
        long size = drawParams.getSize();
        CanvasDrawScope.DrawParams drawParams2 = canvasDrawScope2.getDrawParams();
        drawParams2.setDensity(cacheDrawScope);
        drawParams2.setLayoutDirection(layoutDirection);
        drawParams2.setCanvas(canvas2);
        drawParams2.m2341setSizeuvyYCjk(jSize);
        canvas2.save();
        CanvasDrawScope canvasDrawScope3 = canvasDrawScope2;
        DrawScope.m2412drawRectnJ9OG0$default(canvasDrawScope3, Color.INSTANCE.m1903getBlack0d7_KjU(), 0L, canvasDrawScope3.mo2417getSizeNHjbRc(), 0.0f, null, null, BlendMode.INSTANCE.m1794getClear0nO6VwU(), 58, null);
        DrawScope.m2412drawRectnJ9OG0$default(canvasDrawScope3, ColorKt.Color(4278190080L), Offset.INSTANCE.m1655getZeroF1C5BW0(), androidx.compose.ui.geometry.SizeKt.Size(f, f), 0.0f, null, null, 0, 120, null);
        DrawScope.m2399drawCircleVaOC9Bg$default(canvasDrawScope3, ColorKt.Color(4278190080L), f, OffsetKt.Offset(f, f), 0.0f, null, null, 0, 120, null);
        canvas2.restore();
        CanvasDrawScope.DrawParams drawParams3 = canvasDrawScope2.getDrawParams();
        drawParams3.setDensity(density);
        drawParams3.setLayoutDirection(layoutDirection2);
        drawParams3.setCanvas(canvas3);
        drawParams3.m2341setSizeuvyYCjk(size);
        return imageBitmap2;
    }

    /* renamed from: HandlePopup-ULxng0E, reason: not valid java name */
    public static final void m1072HandlePopupULxng0E(final long j, final HandleReferencePoint handleReferencePoint, final Function2<? super Composer, ? super Integer, Unit> content, Composer composer, final int i) {
        int i2;
        Intrinsics.checkNotNullParameter(handleReferencePoint, "handleReferencePoint");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer composerStartRestartGroup = composer.startRestartGroup(-1409050158);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(HandlePopup)P(2:c#ui.geometry.Offset,1)231@8057L113,235@8176L217:AndroidSelectionHandles.android.kt#eksfi3");
        if ((i & 14) == 0) {
            i2 = (composerStartRestartGroup.changed(j) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 112) == 0) {
            i2 |= composerStartRestartGroup.changed(handleReferencePoint) ? 32 : 16;
        }
        if ((i & 896) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(content) ? 256 : 128;
        }
        if ((i2 & 731) != 146 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1409050158, i2, -1, "androidx.compose.foundation.text.selection.HandlePopup (AndroidSelectionHandles.android.kt:224)");
            }
            long jIntOffset = IntOffsetKt.IntOffset(MathKt.roundToInt(Offset.m1639getXimpl(j)), MathKt.roundToInt(Offset.m1640getYimpl(j)));
            IntOffset intOffsetM4499boximpl = IntOffset.m4499boximpl(jIntOffset);
            composerStartRestartGroup.startReplaceableGroup(511388516);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember)P(1,2):Composables.kt#9igjgp");
            boolean zChanged = composerStartRestartGroup.changed(intOffsetM4499boximpl) | composerStartRestartGroup.changed(handleReferencePoint);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new HandlePositionProvider(handleReferencePoint, jIntOffset, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            composerStartRestartGroup.endReplaceableGroup();
            AndroidPopup_androidKt.Popup((HandlePositionProvider) objRememberedValue, null, new PopupProperties(false, false, false, null, true, false, 15, null), content, composerStartRestartGroup, ((i2 << 3) & 7168) | 384, 2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$HandlePopup$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                invoke(composer2, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer2, int i3) {
                AndroidSelectionHandles_androidKt.m1072HandlePopupULxng0E(j, handleReferencePoint, content, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isLeft(boolean z, ResolvedTextDirection resolvedTextDirection, boolean z2) {
        if (z) {
            return isHandleLtrDirection(resolvedTextDirection, z2);
        }
        return !isHandleLtrDirection(resolvedTextDirection, z2);
    }

    public static final boolean isHandleLtrDirection(ResolvedTextDirection direction, boolean z) {
        Intrinsics.checkNotNullParameter(direction, "direction");
        return (direction == ResolvedTextDirection.Ltr && !z) || (direction == ResolvedTextDirection.Rtl && z);
    }
}
