package androidx.compose.ui.graphics;

import android.graphics.Shader;
import androidx.compose.ui.geometry.Size;
import io.sentry.protocol.ViewHierarchyNode;
import io.sentry.rrweb.RRWebVideoEvent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Brush.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0006\b'\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J+\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0011J!\u0010\u0012\u001a\u00060\u0007j\u0002`\b2\u0006\u0010\u000b\u001a\u00020\u0004H&ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014R\u0019\u0010\u0003\u001a\u00020\u0004X\u0082\u000eø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u0005R\u0016\u0010\u0006\u001a\n\u0018\u00010\u0007j\u0004\u0018\u0001`\bX\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0015"}, d2 = {"Landroidx/compose/ui/graphics/ShaderBrush;", "Landroidx/compose/ui/graphics/Brush;", "()V", "createdSize", "Landroidx/compose/ui/geometry/Size;", "J", "internalShader", "Landroid/graphics/Shader;", "Landroidx/compose/ui/graphics/Shader;", "applyTo", "", RRWebVideoEvent.JsonKeys.SIZE, "p", "Landroidx/compose/ui/graphics/Paint;", ViewHierarchyNode.JsonKeys.ALPHA, "", "applyTo-Pq9zytI", "(JLandroidx/compose/ui/graphics/Paint;F)V", "createShader", "createShader-uvyYCjk", "(J)Landroid/graphics/Shader;", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class ShaderBrush extends Brush {
    private long createdSize;
    private Shader internalShader;

    /* renamed from: createShader-uvyYCjk */
    public abstract Shader mo1846createShaderuvyYCjk(long size);

    public ShaderBrush() {
        super(null);
        this.createdSize = Size.INSTANCE.m1716getUnspecifiedNHjbRc();
    }

    @Override // androidx.compose.ui.graphics.Brush
    /* renamed from: applyTo-Pq9zytI */
    public final void mo1824applyToPq9zytI(long size, Paint p, float alpha) {
        Intrinsics.checkNotNullParameter(p, "p");
        Shader shaderMo1846createShaderuvyYCjk = this.internalShader;
        if (shaderMo1846createShaderuvyYCjk == null || !Size.m1704equalsimpl0(this.createdSize, size)) {
            shaderMo1846createShaderuvyYCjk = mo1846createShaderuvyYCjk(size);
            this.internalShader = shaderMo1846createShaderuvyYCjk;
            this.createdSize = size;
        }
        if (!Color.m1878equalsimpl0(p.mo1753getColor0d7_KjU(), Color.INSTANCE.m1903getBlack0d7_KjU())) {
            p.mo1759setColor8_81llA(Color.INSTANCE.m1903getBlack0d7_KjU());
        }
        if (!Intrinsics.areEqual(p.getShader(), shaderMo1846createShaderuvyYCjk)) {
            p.setShader(shaderMo1846createShaderuvyYCjk);
        }
        if (p.getAlpha() == alpha) {
            return;
        }
        p.setAlpha(alpha);
    }
}
