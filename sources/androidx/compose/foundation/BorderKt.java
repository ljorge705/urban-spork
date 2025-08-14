package androidx.compose.foundation;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.CacheDrawScope;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.draw.DrawResult;
import androidx.compose.ui.geometry.CornerRadius;
import androidx.compose.ui.geometry.CornerRadiusKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.RoundRect;
import androidx.compose.ui.geometry.RoundRectKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.ClipOp;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.PathOperation;
import androidx.compose.ui.graphics.RectangleShapeKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.graphics.drawscope.Fill;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.node.Ref;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.unit.Dp;
import com.clevertap.android.sdk.Constants;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Border.kt */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0001H\u0002\u001a(\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH\u0002\u001a\u001c\u0010\u000b\u001a\u00020\f*\u00020\f2\u0006\u0010\u000b\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u001a/\u0010\u000b\u001a\u00020\f*\u00020\f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000e\u001a\u00020\u000fø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0014\u0010\u0015\u001a1\u0010\u000b\u001a\u00020\f*\u00020\f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u000e\u001a\u00020\u000fø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0018\u0010\u0019\u001a\f\u0010\u001a\u001a\u00020\u001b*\u00020\u001cH\u0002\u001a:\u0010\u001d\u001a\u00020\u001b*\u00020\u001c2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001f2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010!\u001a\u00020\"2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\b\u001a\u00020\u0003H\u0002\u001aA\u0010#\u001a\u00020\u001b*\u00020\u001c2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010(\u001a\u00020\u0003H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b)\u0010*\u001aW\u0010+\u001a\u00020\u001b*\u00020\u001c2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001f2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010!\u001a\u00020,2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\b\u001a\u00020\u0003H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b-\u0010.\u001a\u0012\u0010/\u001a\u00020 *\b\u0012\u0004\u0012\u00020 0\u001fH\u0002\u001a!\u00100\u001a\u000201*\u0002012\u0006\u00102\u001a\u00020\u0003H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b3\u00104\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u00065"}, d2 = {"createInsetRoundedRect", "Landroidx/compose/ui/geometry/RoundRect;", "widthPx", "", "roundedRect", "createRoundRectPath", "Landroidx/compose/ui/graphics/Path;", "targetPath", "strokeWidth", "fillArea", "", Constants.KEY_BORDER, "Landroidx/compose/ui/Modifier;", "Landroidx/compose/foundation/BorderStroke;", "shape", "Landroidx/compose/ui/graphics/Shape;", "width", "Landroidx/compose/ui/unit/Dp;", "brush", "Landroidx/compose/ui/graphics/Brush;", "border-ziNgDLE", "(Landroidx/compose/ui/Modifier;FLandroidx/compose/ui/graphics/Brush;Landroidx/compose/ui/graphics/Shape;)Landroidx/compose/ui/Modifier;", "color", "Landroidx/compose/ui/graphics/Color;", "border-xT4_qwU", "(Landroidx/compose/ui/Modifier;FJLandroidx/compose/ui/graphics/Shape;)Landroidx/compose/ui/Modifier;", "drawContentWithoutBorder", "Landroidx/compose/ui/draw/DrawResult;", "Landroidx/compose/ui/draw/CacheDrawScope;", "drawGenericBorder", "borderCacheRef", "Landroidx/compose/ui/node/Ref;", "Landroidx/compose/foundation/BorderCache;", "outline", "Landroidx/compose/ui/graphics/Outline$Generic;", "drawRectBorder", "topLeft", "Landroidx/compose/ui/geometry/Offset;", "borderSize", "Landroidx/compose/ui/geometry/Size;", "strokeWidthPx", "drawRectBorder-NsqcLGU", "(Landroidx/compose/ui/draw/CacheDrawScope;Landroidx/compose/ui/graphics/Brush;JJZF)Landroidx/compose/ui/draw/DrawResult;", "drawRoundRectBorder", "Landroidx/compose/ui/graphics/Outline$Rounded;", "drawRoundRectBorder-SYlcjDY", "(Landroidx/compose/ui/draw/CacheDrawScope;Landroidx/compose/ui/node/Ref;Landroidx/compose/ui/graphics/Brush;Landroidx/compose/ui/graphics/Outline$Rounded;JJZF)Landroidx/compose/ui/draw/DrawResult;", "obtain", "shrink", "Landroidx/compose/ui/geometry/CornerRadius;", "value", "shrink-Kibmq7A", "(JF)J", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class BorderKt {
    public static /* synthetic */ Modifier border$default(Modifier modifier, BorderStroke borderStroke, Shape shape, int i, Object obj) {
        if ((i & 2) != 0) {
            shape = RectangleShapeKt.getRectangleShape();
        }
        return border(modifier, borderStroke, shape);
    }

    public static final Modifier border(Modifier modifier, BorderStroke border, Shape shape) {
        Intrinsics.checkNotNullParameter(modifier, "<this>");
        Intrinsics.checkNotNullParameter(border, "border");
        Intrinsics.checkNotNullParameter(shape, "shape");
        return m439borderziNgDLE(modifier, border.getWidth(), border.getBrush(), shape);
    }

    /* renamed from: border-xT4_qwU$default, reason: not valid java name */
    public static /* synthetic */ Modifier m438borderxT4_qwU$default(Modifier modifier, float f, long j, Shape shape, int i, Object obj) {
        if ((i & 4) != 0) {
            shape = RectangleShapeKt.getRectangleShape();
        }
        return m437borderxT4_qwU(modifier, f, j, shape);
    }

    /* renamed from: border-xT4_qwU, reason: not valid java name */
    public static final Modifier m437borderxT4_qwU(Modifier border, float f, long j, Shape shape) {
        Intrinsics.checkNotNullParameter(border, "$this$border");
        Intrinsics.checkNotNullParameter(shape, "shape");
        return m439borderziNgDLE(border, f, new SolidColor(j, null), shape);
    }

    private static final BorderCache obtain(Ref<BorderCache> ref) {
        BorderCache value = ref.getValue();
        if (value != null) {
            return value;
        }
        BorderCache borderCache = new BorderCache(null, null, null, null, 15, null);
        ref.setValue(borderCache);
        return borderCache;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DrawResult drawContentWithoutBorder(CacheDrawScope cacheDrawScope) {
        return cacheDrawScope.onDrawWithContent(new Function1<ContentDrawScope, Unit>() { // from class: androidx.compose.foundation.BorderKt.drawContentWithoutBorder.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ContentDrawScope contentDrawScope) {
                invoke2(contentDrawScope);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ContentDrawScope onDrawWithContent) {
                Intrinsics.checkNotNullParameter(onDrawWithContent, "$this$onDrawWithContent");
                onDrawWithContent.drawContent();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00bc  */
    /* JADX WARN: Type inference failed for: r11v11 */
    /* JADX WARN: Type inference failed for: r11v3 */
    /* JADX WARN: Type inference failed for: r11v4, types: [T, androidx.compose.ui.graphics.ImageBitmap] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final androidx.compose.ui.draw.DrawResult drawGenericBorder(androidx.compose.ui.draw.CacheDrawScope r43, androidx.compose.ui.node.Ref<androidx.compose.foundation.BorderCache> r44, final androidx.compose.ui.graphics.Brush r45, final androidx.compose.ui.graphics.Outline.Generic r46, boolean r47, float r48) {
        /*
            Method dump skipped, instructions count: 640
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.BorderKt.drawGenericBorder(androidx.compose.ui.draw.CacheDrawScope, androidx.compose.ui.node.Ref, androidx.compose.ui.graphics.Brush, androidx.compose.ui.graphics.Outline$Generic, boolean, float):androidx.compose.ui.draw.DrawResult");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: drawRoundRectBorder-SYlcjDY, reason: not valid java name */
    public static final DrawResult m441drawRoundRectBorderSYlcjDY(CacheDrawScope cacheDrawScope, Ref<BorderCache> ref, final Brush brush, Outline.Rounded rounded, final long j, final long j2, final boolean z, final float f) {
        if (RoundRectKt.isSimple(rounded.getRoundRect())) {
            final long jM1689getTopLeftCornerRadiuskKHJgLs = rounded.getRoundRect().m1689getTopLeftCornerRadiuskKHJgLs();
            final float f2 = f / 2;
            final Stroke stroke = new Stroke(f, 0.0f, 0, 0, null, 30, null);
            return cacheDrawScope.onDrawWithContent(new Function1<ContentDrawScope, Unit>() { // from class: androidx.compose.foundation.BorderKt$drawRoundRectBorder$1
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
                    if (z) {
                        DrawScope.m2413drawRoundRectZuiqVtQ$default(onDrawWithContent, brush, 0L, 0L, jM1689getTopLeftCornerRadiuskKHJgLs, 0.0f, null, null, 0, 246, null);
                        return;
                    }
                    float fM1614getXimpl = CornerRadius.m1614getXimpl(jM1689getTopLeftCornerRadiuskKHJgLs);
                    float f3 = f2;
                    if (fM1614getXimpl < f3) {
                        ContentDrawScope contentDrawScope = onDrawWithContent;
                        float f4 = f;
                        float fM1708getWidthimpl = Size.m1708getWidthimpl(onDrawWithContent.mo2417getSizeNHjbRc()) - f;
                        float fM1705getHeightimpl = Size.m1705getHeightimpl(onDrawWithContent.mo2417getSizeNHjbRc()) - f;
                        int iM1865getDifferencertfAjoo = ClipOp.INSTANCE.m1865getDifferencertfAjoo();
                        Brush brush2 = brush;
                        long j3 = jM1689getTopLeftCornerRadiuskKHJgLs;
                        DrawContext drawContext = contentDrawScope.getDrawContext();
                        long jMo2342getSizeNHjbRc = drawContext.mo2342getSizeNHjbRc();
                        drawContext.getCanvas().save();
                        drawContext.getTransform().mo2345clipRectN_I0leg(f4, f4, fM1708getWidthimpl, fM1705getHeightimpl, iM1865getDifferencertfAjoo);
                        DrawScope.m2413drawRoundRectZuiqVtQ$default(contentDrawScope, brush2, 0L, 0L, j3, 0.0f, null, null, 0, 246, null);
                        drawContext.getCanvas().restore();
                        drawContext.mo2343setSizeuvyYCjk(jMo2342getSizeNHjbRc);
                        return;
                    }
                    DrawScope.m2413drawRoundRectZuiqVtQ$default(onDrawWithContent, brush, j, j2, BorderKt.m442shrinkKibmq7A(jM1689getTopLeftCornerRadiuskKHJgLs, f3), 0.0f, stroke, null, 0, 208, null);
                }
            });
        }
        final Path pathCreateRoundRectPath = createRoundRectPath(obtain(ref).obtainPath(), rounded.getRoundRect(), f, z);
        return cacheDrawScope.onDrawWithContent(new Function1<ContentDrawScope, Unit>() { // from class: androidx.compose.foundation.BorderKt$drawRoundRectBorder$2
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
                DrawScope.m2407drawPathGBMwjPU$default(onDrawWithContent, pathCreateRoundRectPath, brush, 0.0f, null, null, 0, 60, null);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: drawRectBorder-NsqcLGU, reason: not valid java name */
    public static final DrawResult m440drawRectBorderNsqcLGU(CacheDrawScope cacheDrawScope, final Brush brush, long j, long j2, boolean z, float f) {
        final long jM1655getZeroF1C5BW0 = z ? Offset.INSTANCE.m1655getZeroF1C5BW0() : j;
        final long jM1551getSizeNHjbRc = z ? cacheDrawScope.m1551getSizeNHjbRc() : j2;
        final DrawStyle stroke = z ? Fill.INSTANCE : new Stroke(f, 0.0f, 0, 0, null, 30, null);
        return cacheDrawScope.onDrawWithContent(new Function1<ContentDrawScope, Unit>() { // from class: androidx.compose.foundation.BorderKt$drawRectBorder$1
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
                DrawScope.m2411drawRectAsUm42w$default(onDrawWithContent, brush, jM1655getZeroF1C5BW0, jM1551getSizeNHjbRc, 0.0f, stroke, null, 0, 104, null);
            }
        });
    }

    private static final Path createRoundRectPath(Path path, RoundRect roundRect, float f, boolean z) {
        path.reset();
        path.addRoundRect(roundRect);
        if (!z) {
            Path Path = AndroidPath_androidKt.Path();
            Path.addRoundRect(createInsetRoundedRect(f, roundRect));
            path.mo1772opN5in7k0(path, Path, PathOperation.INSTANCE.m2159getDifferenceb3I0S0c());
        }
        return path;
    }

    private static final RoundRect createInsetRoundedRect(float f, RoundRect roundRect) {
        return new RoundRect(f, f, roundRect.getWidth() - f, roundRect.getHeight() - f, m442shrinkKibmq7A(roundRect.m1689getTopLeftCornerRadiuskKHJgLs(), f), m442shrinkKibmq7A(roundRect.m1690getTopRightCornerRadiuskKHJgLs(), f), m442shrinkKibmq7A(roundRect.m1688getBottomRightCornerRadiuskKHJgLs(), f), m442shrinkKibmq7A(roundRect.m1687getBottomLeftCornerRadiuskKHJgLs(), f), null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: shrink-Kibmq7A, reason: not valid java name */
    public static final long m442shrinkKibmq7A(long j, float f) {
        return CornerRadiusKt.CornerRadius(Math.max(0.0f, CornerRadius.m1614getXimpl(j) - f), Math.max(0.0f, CornerRadius.m1615getYimpl(j) - f));
    }

    /* renamed from: border-ziNgDLE, reason: not valid java name */
    public static final Modifier m439borderziNgDLE(Modifier border, final float f, final Brush brush, final Shape shape) {
        Intrinsics.checkNotNullParameter(border, "$this$border");
        Intrinsics.checkNotNullParameter(brush, "brush");
        Intrinsics.checkNotNullParameter(shape, "shape");
        return ComposedModifierKt.composed(border, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.BorderKt$border-ziNgDLE$$inlined$debugInspectorInfo$1
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
                inspectorInfo.setName(Constants.KEY_BORDER);
                inspectorInfo.getProperties().set("width", Dp.m4388boximpl(f));
                if (brush instanceof SolidColor) {
                    inspectorInfo.getProperties().set("color", Color.m1867boximpl(((SolidColor) brush).getValue()));
                    inspectorInfo.setValue(Color.m1867boximpl(((SolidColor) brush).getValue()));
                } else {
                    inspectorInfo.getProperties().set("brush", brush);
                }
                inspectorInfo.getProperties().set("shape", shape);
            }
        } : InspectableValueKt.getNoInspectorInfo(), new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.foundation.BorderKt.border.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier, Composer composer, Integer num) {
                return invoke(modifier, composer, num.intValue());
            }

            public final Modifier invoke(Modifier composed, Composer composer, int i) {
                Intrinsics.checkNotNullParameter(composed, "$this$composed");
                composer.startReplaceableGroup(-1498088849);
                ComposerKt.sourceInformation(composer, "C97@4024L31:Border.kt#71ulvw");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1498088849, i, -1, "androidx.compose.foundation.border.<anonymous> (Border.kt:93)");
                }
                composer.startReplaceableGroup(-492369756);
                ComposerKt.sourceInformation(composer, "CC(remember):Composables.kt#9igjgp");
                Object objRememberedValue = composer.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new Ref();
                    composer.updateRememberedValue(objRememberedValue);
                }
                composer.endReplaceableGroup();
                final Ref ref = (Ref) objRememberedValue;
                Modifier.Companion companion = Modifier.INSTANCE;
                final float f2 = f;
                final Shape shape2 = shape;
                final Brush brush2 = brush;
                Modifier modifierThen = composed.then(DrawModifierKt.drawWithCache(companion, new Function1<CacheDrawScope, DrawResult>() { // from class: androidx.compose.foundation.BorderKt.border.2.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final DrawResult invoke(CacheDrawScope drawWithCache) {
                        Intrinsics.checkNotNullParameter(drawWithCache, "$this$drawWithCache");
                        if (drawWithCache.mo577toPx0680j_4(f2) < 0.0f || Size.m1707getMinDimensionimpl(drawWithCache.m1551getSizeNHjbRc()) <= 0.0f) {
                            return BorderKt.drawContentWithoutBorder(drawWithCache);
                        }
                        float f3 = 2;
                        float fMin = Math.min(Dp.m4395equalsimpl0(f2, Dp.INSTANCE.m4408getHairlineD9Ej5fM()) ? 1.0f : (float) Math.ceil(drawWithCache.mo577toPx0680j_4(f2)), (float) Math.ceil(Size.m1707getMinDimensionimpl(drawWithCache.m1551getSizeNHjbRc()) / f3));
                        float f4 = fMin / f3;
                        long jOffset = OffsetKt.Offset(f4, f4);
                        long jSize = SizeKt.Size(Size.m1708getWidthimpl(drawWithCache.m1551getSizeNHjbRc()) - fMin, Size.m1705getHeightimpl(drawWithCache.m1551getSizeNHjbRc()) - fMin);
                        boolean z = f3 * fMin > Size.m1707getMinDimensionimpl(drawWithCache.m1551getSizeNHjbRc());
                        Outline outlineMo469createOutlinePq9zytI = shape2.mo469createOutlinePq9zytI(drawWithCache.m1551getSizeNHjbRc(), drawWithCache.getLayoutDirection(), drawWithCache);
                        if (outlineMo469createOutlinePq9zytI instanceof Outline.Generic) {
                            return BorderKt.drawGenericBorder(drawWithCache, ref, brush2, (Outline.Generic) outlineMo469createOutlinePq9zytI, z, fMin);
                        }
                        if (outlineMo469createOutlinePq9zytI instanceof Outline.Rounded) {
                            return BorderKt.m441drawRoundRectBorderSYlcjDY(drawWithCache, ref, brush2, (Outline.Rounded) outlineMo469createOutlinePq9zytI, jOffset, jSize, z, fMin);
                        }
                        if (outlineMo469createOutlinePq9zytI instanceof Outline.Rectangle) {
                            return BorderKt.m440drawRectBorderNsqcLGU(drawWithCache, brush2, jOffset, jSize, z, fMin);
                        }
                        throw new NoWhenBranchMatchedException();
                    }
                }));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer.endReplaceableGroup();
                return modifierThen;
            }
        });
    }
}
