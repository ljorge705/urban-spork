package androidx.compose.ui.text;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.graphics.drawscope.DrawTransform;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextDrawStyleKt;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.IntSize;
import com.facebook.react.uimanager.ViewProps;
import io.sentry.protocol.ViewHierarchyNode;
import io.sentry.rrweb.RRWebVideoEvent;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* compiled from: TextPainter.kt */
@Metadata(d1 = {"\u0000\u0098\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0014\u0010\u0005\u001a\u00020\u0006*\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002\u001ak\u0010\n\u001a\u00020\u0006*\u00020\u000b2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u0001H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001a\u001am\u0010\n\u001a\u00020\u0006*\u00020\u000b2\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u0001H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001e\u001a\u0085\u0001\u0010\n\u001a\u00020\u0006*\u00020\u000b2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010#\u001a\u00020$2\b\b\u0002\u0010%\u001a\u00020&2\b\b\u0002\u0010'\u001a\u00020(2\b\b\u0002\u0010)\u001a\u00020*2\u0014\b\u0002\u0010+\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020.0-0,2\b\b\u0002\u0010/\u001a\u0002002\b\b\u0002\u0010\u0018\u001a\u00020\u0001H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b1\u00102\u001ao\u0010\n\u001a\u00020\u0006*\u00020\u000b2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u0002032\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010#\u001a\u00020$2\b\b\u0002\u0010%\u001a\u00020&2\b\b\u0002\u0010'\u001a\u00020(2\b\b\u0002\u0010)\u001a\u00020*2\b\b\u0002\u0010/\u001a\u0002002\b\b\u0002\u0010\u0018\u001a\u00020\u0001H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b4\u00105\u001a)\u00106\u001a\u000207*\u00020\u000b2\u0006\u0010/\u001a\u0002002\u0006\u0010\u000e\u001a\u00020\u000fH\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b8\u00109\"\u0019\u0010\u0000\u001a\u00020\u0001X\u0080\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\u0002\u0010\u0003\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006:"}, d2 = {"DefaultTextBlendMode", "Landroidx/compose/ui/graphics/BlendMode;", "getDefaultTextBlendMode", "()I", "I", "clip", "", "Landroidx/compose/ui/graphics/drawscope/DrawTransform;", "textLayoutResult", "Landroidx/compose/ui/text/TextLayoutResult;", "drawText", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "brush", "Landroidx/compose/ui/graphics/Brush;", "topLeft", "Landroidx/compose/ui/geometry/Offset;", ViewHierarchyNode.JsonKeys.ALPHA, "", "shadow", "Landroidx/compose/ui/graphics/Shadow;", "textDecoration", "Landroidx/compose/ui/text/style/TextDecoration;", "drawStyle", "Landroidx/compose/ui/graphics/drawscope/DrawStyle;", "blendMode", "drawText-LVfH_YU", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;Landroidx/compose/ui/text/TextLayoutResult;Landroidx/compose/ui/graphics/Brush;JFLandroidx/compose/ui/graphics/Shadow;Landroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/graphics/drawscope/DrawStyle;I)V", "color", "Landroidx/compose/ui/graphics/Color;", "drawText-d8-rzKo", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;Landroidx/compose/ui/text/TextLayoutResult;JJFLandroidx/compose/ui/graphics/Shadow;Landroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/graphics/drawscope/DrawStyle;I)V", "textMeasurer", "Landroidx/compose/ui/text/TextMeasurer;", "text", "Landroidx/compose/ui/text/AnnotatedString;", "style", "Landroidx/compose/ui/text/TextStyle;", ViewProps.OVERFLOW, "Landroidx/compose/ui/text/style/TextOverflow;", "softWrap", "", "maxLines", "", "placeholders", "", "Landroidx/compose/ui/text/AnnotatedString$Range;", "Landroidx/compose/ui/text/Placeholder;", RRWebVideoEvent.JsonKeys.SIZE, "Landroidx/compose/ui/geometry/Size;", "drawText-JFhB2K4", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;Landroidx/compose/ui/text/TextMeasurer;Landroidx/compose/ui/text/AnnotatedString;JLandroidx/compose/ui/text/TextStyle;IZILjava/util/List;JI)V", "", "drawText-TPWCCtM", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;Landroidx/compose/ui/text/TextMeasurer;Ljava/lang/String;JLandroidx/compose/ui/text/TextStyle;IZIJI)V", "textLayoutConstraints", "Landroidx/compose/ui/unit/Constraints;", "textLayoutConstraints-v_w8tDc", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;JJ)J", "ui-text_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class TextPainterKt {
    private static final int DefaultTextBlendMode = BlendMode.INSTANCE.m1821getSrcOver0nO6VwU();

    public static final int getDefaultTextBlendMode() {
        return DefaultTextBlendMode;
    }

    /* renamed from: drawText-JFhB2K4, reason: not valid java name */
    public static final void m3884drawTextJFhB2K4(DrawScope drawText, TextMeasurer textMeasurer, AnnotatedString text, long j, TextStyle style, int i, boolean z, int i2, List<AnnotatedString.Range<Placeholder>> placeholders, long j2, int i3) {
        Intrinsics.checkNotNullParameter(drawText, "$this$drawText");
        Intrinsics.checkNotNullParameter(textMeasurer, "textMeasurer");
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(style, "style");
        Intrinsics.checkNotNullParameter(placeholders, "placeholders");
        TextLayoutResult textLayoutResultM3881measurexDpz5zY$default = TextMeasurer.m3881measurexDpz5zY$default(textMeasurer, text, style, i, z, i2, placeholders, m3892textLayoutConstraintsv_w8tDc(drawText, j2, j), drawText.getLayoutDirection(), drawText, null, false, 1536, null);
        DrawContext drawContext = drawText.getDrawContext();
        long jMo2342getSizeNHjbRc = drawContext.mo2342getSizeNHjbRc();
        drawContext.getCanvas().save();
        DrawTransform transform = drawContext.getTransform();
        transform.translate(Offset.m1639getXimpl(j), Offset.m1640getYimpl(j));
        clip(transform, textLayoutResultM3881measurexDpz5zY$default);
        textLayoutResultM3881measurexDpz5zY$default.getMultiParagraph().m3800paintLG529CI(drawText.getDrawContext().getCanvas(), (32 & 2) != 0 ? Color.INSTANCE.m1913getUnspecified0d7_KjU() : 0L, (32 & 4) != 0 ? null : null, (32 & 8) != 0 ? null : null, (32 & 16) == 0 ? null : null, (32 & 32) != 0 ? DrawScope.INSTANCE.m2418getDefaultBlendMode0nO6VwU() : i3);
        drawContext.getCanvas().restore();
        drawContext.mo2343setSizeuvyYCjk(jMo2342getSizeNHjbRc);
    }

    /* renamed from: drawText-TPWCCtM, reason: not valid java name */
    public static final void m3888drawTextTPWCCtM(DrawScope drawText, TextMeasurer textMeasurer, String text, long j, TextStyle style, int i, boolean z, int i2, long j2, int i3) {
        Intrinsics.checkNotNullParameter(drawText, "$this$drawText");
        Intrinsics.checkNotNullParameter(textMeasurer, "textMeasurer");
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(style, "style");
        TextLayoutResult textLayoutResultM3881measurexDpz5zY$default = TextMeasurer.m3881measurexDpz5zY$default(textMeasurer, new AnnotatedString(text, null, null, 6, null), style, i, z, i2, null, m3892textLayoutConstraintsv_w8tDc(drawText, j2, j), drawText.getLayoutDirection(), drawText, null, false, 1568, null);
        DrawContext drawContext = drawText.getDrawContext();
        long jMo2342getSizeNHjbRc = drawContext.mo2342getSizeNHjbRc();
        drawContext.getCanvas().save();
        DrawTransform transform = drawContext.getTransform();
        transform.translate(Offset.m1639getXimpl(j), Offset.m1640getYimpl(j));
        clip(transform, textLayoutResultM3881measurexDpz5zY$default);
        textLayoutResultM3881measurexDpz5zY$default.getMultiParagraph().m3800paintLG529CI(drawText.getDrawContext().getCanvas(), (32 & 2) != 0 ? Color.INSTANCE.m1913getUnspecified0d7_KjU() : 0L, (32 & 4) != 0 ? null : null, (32 & 8) != 0 ? null : null, (32 & 16) == 0 ? null : null, (32 & 32) != 0 ? DrawScope.INSTANCE.m2418getDefaultBlendMode0nO6VwU() : i3);
        drawContext.getCanvas().restore();
        drawContext.mo2343setSizeuvyYCjk(jMo2342getSizeNHjbRc);
    }

    /* renamed from: drawText-d8-rzKo, reason: not valid java name */
    public static final void m3890drawTextd8rzKo(DrawScope drawText, TextLayoutResult textLayoutResult, long j, long j2, float f, Shadow shadow, TextDecoration textDecoration, DrawStyle drawStyle, int i) {
        Intrinsics.checkNotNullParameter(drawText, "$this$drawText");
        Intrinsics.checkNotNullParameter(textLayoutResult, "textLayoutResult");
        Shadow shadow2 = shadow == null ? textLayoutResult.getLayoutInput().getStyle().getShadow() : shadow;
        TextDecoration textDecoration2 = textDecoration == null ? textLayoutResult.getLayoutInput().getStyle().getTextDecoration() : textDecoration;
        DrawStyle drawStyle2 = drawStyle == null ? textLayoutResult.getLayoutInput().getStyle().getDrawStyle() : drawStyle;
        DrawContext drawContext = drawText.getDrawContext();
        long jMo2342getSizeNHjbRc = drawContext.mo2342getSizeNHjbRc();
        drawContext.getCanvas().save();
        DrawTransform transform = drawContext.getTransform();
        transform.translate(Offset.m1639getXimpl(j2), Offset.m1640getYimpl(j2));
        clip(transform, textLayoutResult);
        Brush brush = textLayoutResult.getLayoutInput().getStyle().getBrush();
        if (brush != null && j == Color.INSTANCE.m1913getUnspecified0d7_KjU()) {
            textLayoutResult.getMultiParagraph().m3802painthn5TExg(drawText.getDrawContext().getCanvas(), brush, !Float.isNaN(f) ? f : textLayoutResult.getLayoutInput().getStyle().getAlpha(), shadow2, textDecoration2, drawStyle2, i);
        } else {
            textLayoutResult.getMultiParagraph().m3800paintLG529CI(drawText.getDrawContext().getCanvas(), TextDrawStyleKt.m4280modulateDxMtmZc(j != Color.INSTANCE.m1913getUnspecified0d7_KjU() ? j : textLayoutResult.getLayoutInput().getStyle().m3925getColor0d7_KjU(), f), shadow2, textDecoration2, drawStyle2, i);
        }
        drawContext.getCanvas().restore();
        drawContext.mo2343setSizeuvyYCjk(jMo2342getSizeNHjbRc);
    }

    /* renamed from: drawText-LVfH_YU, reason: not valid java name */
    public static final void m3886drawTextLVfH_YU(DrawScope drawText, TextLayoutResult textLayoutResult, Brush brush, long j, float f, Shadow shadow, TextDecoration textDecoration, DrawStyle drawStyle, int i) {
        Intrinsics.checkNotNullParameter(drawText, "$this$drawText");
        Intrinsics.checkNotNullParameter(textLayoutResult, "textLayoutResult");
        Intrinsics.checkNotNullParameter(brush, "brush");
        Shadow shadow2 = shadow == null ? textLayoutResult.getLayoutInput().getStyle().getShadow() : shadow;
        TextDecoration textDecoration2 = textDecoration == null ? textLayoutResult.getLayoutInput().getStyle().getTextDecoration() : textDecoration;
        DrawStyle drawStyle2 = drawStyle == null ? textLayoutResult.getLayoutInput().getStyle().getDrawStyle() : drawStyle;
        DrawContext drawContext = drawText.getDrawContext();
        long jMo2342getSizeNHjbRc = drawContext.mo2342getSizeNHjbRc();
        drawContext.getCanvas().save();
        DrawTransform transform = drawContext.getTransform();
        transform.translate(Offset.m1639getXimpl(j), Offset.m1640getYimpl(j));
        clip(transform, textLayoutResult);
        textLayoutResult.getMultiParagraph().m3802painthn5TExg(drawText.getDrawContext().getCanvas(), brush, !Float.isNaN(f) ? f : textLayoutResult.getLayoutInput().getStyle().getAlpha(), shadow2, textDecoration2, drawStyle2, i);
        drawContext.getCanvas().restore();
        drawContext.mo2343setSizeuvyYCjk(jMo2342getSizeNHjbRc);
    }

    private static final void clip(DrawTransform drawTransform, TextLayoutResult textLayoutResult) {
        if (!textLayoutResult.getHasVisualOverflow() || TextOverflow.m4302equalsimpl0(textLayoutResult.getLayoutInput().getOverflow(), TextOverflow.INSTANCE.m4311getVisiblegIe3tQ8())) {
            return;
        }
        DrawTransform.m2468clipRectN_I0leg$default(drawTransform, 0.0f, 0.0f, IntSize.m4550getWidthimpl(textLayoutResult.getSize()), IntSize.m4549getHeightimpl(textLayoutResult.getSize()), 0, 16, null);
    }

    /* renamed from: textLayoutConstraints-v_w8tDc, reason: not valid java name */
    private static final long m3892textLayoutConstraintsv_w8tDc(DrawScope drawScope, long j, long j2) {
        int iRoundToInt;
        int iRoundToInt2;
        int iRoundToInt3;
        int iRoundToInt4 = 0;
        if (j == Size.INSTANCE.m1716getUnspecifiedNHjbRc() || Float.isNaN(Size.m1708getWidthimpl(j))) {
            iRoundToInt = MathKt.roundToInt((float) Math.ceil(Size.m1708getWidthimpl(drawScope.mo2417getSizeNHjbRc()) - Offset.m1639getXimpl(j2)));
            iRoundToInt2 = 0;
        } else {
            iRoundToInt2 = MathKt.roundToInt((float) Math.ceil(Size.m1708getWidthimpl(j)));
            iRoundToInt = iRoundToInt2;
        }
        if (j == Size.INSTANCE.m1716getUnspecifiedNHjbRc() || Float.isNaN(Size.m1705getHeightimpl(j))) {
            iRoundToInt3 = MathKt.roundToInt((float) Math.ceil(Size.m1705getHeightimpl(drawScope.mo2417getSizeNHjbRc()) - Offset.m1640getYimpl(j2)));
        } else {
            iRoundToInt4 = MathKt.roundToInt((float) Math.ceil(Size.m1705getHeightimpl(j)));
            iRoundToInt3 = iRoundToInt4;
        }
        return ConstraintsKt.Constraints(iRoundToInt2, iRoundToInt, iRoundToInt4, iRoundToInt3);
    }
}
