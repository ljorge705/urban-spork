package androidx.compose.ui.graphics.drawscope;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DensityKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CanvasDrawScope.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0002\u001a\u00020\u0003*\u00020\u0004H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"DefaultDensity", "Landroidx/compose/ui/unit/Density;", "asDrawTransform", "Landroidx/compose/ui/graphics/drawscope/DrawTransform;", "Landroidx/compose/ui/graphics/drawscope/DrawContext;", "ui-graphics_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class CanvasDrawScopeKt {
    private static final Density DefaultDensity = DensityKt.Density(1.0f, 1.0f);

    /* JADX INFO: Access modifiers changed from: private */
    public static final DrawTransform asDrawTransform(final DrawContext drawContext) {
        return new DrawTransform() { // from class: androidx.compose.ui.graphics.drawscope.CanvasDrawScopeKt.asDrawTransform.1
            @Override // androidx.compose.ui.graphics.drawscope.DrawTransform
            /* renamed from: getSize-NH-jbRc, reason: not valid java name */
            public long mo2347getSizeNHjbRc() {
                return drawContext.mo2342getSizeNHjbRc();
            }

            @Override // androidx.compose.ui.graphics.drawscope.DrawTransform
            /* renamed from: getCenter-F1C5BW0, reason: not valid java name */
            public long mo2346getCenterF1C5BW0() {
                return SizeKt.m1718getCenteruvyYCjk(mo2347getSizeNHjbRc());
            }

            @Override // androidx.compose.ui.graphics.drawscope.DrawTransform
            public void inset(float left, float top, float right, float bottom) {
                Canvas canvas = drawContext.getCanvas();
                DrawContext drawContext2 = drawContext;
                long jSize = SizeKt.Size(Size.m1708getWidthimpl(mo2347getSizeNHjbRc()) - (right + left), Size.m1705getHeightimpl(mo2347getSizeNHjbRc()) - (bottom + top));
                if (Size.m1708getWidthimpl(jSize) < 0.0f || Size.m1705getHeightimpl(jSize) < 0.0f) {
                    throw new IllegalArgumentException("Width and height must be greater than or equal to zero".toString());
                }
                drawContext2.mo2343setSizeuvyYCjk(jSize);
                canvas.translate(left, top);
            }

            @Override // androidx.compose.ui.graphics.drawscope.DrawTransform
            /* renamed from: clipRect-N_I0leg, reason: not valid java name */
            public void mo2345clipRectN_I0leg(float left, float top, float right, float bottom, int clipOp) {
                drawContext.getCanvas().mo1734clipRectN_I0leg(left, top, right, bottom, clipOp);
            }

            @Override // androidx.compose.ui.graphics.drawscope.DrawTransform
            /* renamed from: clipPath-mtrdD-E, reason: not valid java name */
            public void mo2344clipPathmtrdDE(Path path, int clipOp) {
                Intrinsics.checkNotNullParameter(path, "path");
                drawContext.getCanvas().mo1733clipPathmtrdDE(path, clipOp);
            }

            @Override // androidx.compose.ui.graphics.drawscope.DrawTransform
            public void translate(float left, float top) {
                drawContext.getCanvas().translate(left, top);
            }

            @Override // androidx.compose.ui.graphics.drawscope.DrawTransform
            /* renamed from: rotate-Uv8p0NA, reason: not valid java name */
            public void mo2348rotateUv8p0NA(float degrees, long pivot) {
                Canvas canvas = drawContext.getCanvas();
                canvas.translate(Offset.m1639getXimpl(pivot), Offset.m1640getYimpl(pivot));
                canvas.rotate(degrees);
                canvas.translate(-Offset.m1639getXimpl(pivot), -Offset.m1640getYimpl(pivot));
            }

            @Override // androidx.compose.ui.graphics.drawscope.DrawTransform
            /* renamed from: scale-0AR0LA0, reason: not valid java name */
            public void mo2349scale0AR0LA0(float scaleX, float scaleY, long pivot) {
                Canvas canvas = drawContext.getCanvas();
                canvas.translate(Offset.m1639getXimpl(pivot), Offset.m1640getYimpl(pivot));
                canvas.scale(scaleX, scaleY);
                canvas.translate(-Offset.m1639getXimpl(pivot), -Offset.m1640getYimpl(pivot));
            }

            @Override // androidx.compose.ui.graphics.drawscope.DrawTransform
            /* renamed from: transform-58bKbWc, reason: not valid java name */
            public void mo2350transform58bKbWc(float[] matrix) {
                Intrinsics.checkNotNullParameter(matrix, "matrix");
                drawContext.getCanvas().mo1735concat58bKbWc(matrix);
            }
        };
    }
}
