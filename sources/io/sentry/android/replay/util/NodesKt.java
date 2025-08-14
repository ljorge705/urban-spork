package io.sentry.android.replay.util;

import android.graphics.Rect;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorProducer;
import androidx.compose.ui.graphics.painter.Painter;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.layout.ModifierInfo;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.unit.IntSize;
import java.lang.reflect.Field;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: Nodes.kt */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a)\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0082\b\u001a)\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0082\b\u001a\u0016\u0010\u0007\u001a\u00020\b*\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0000\u001a\u0015\u0010\u000b\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\f\u001a\u00020\u0001H\u0082\b\u001a\u0015\u0010\r\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u0001H\u0082\b\u001a\u001d\u0010\u000f\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\f\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u0001H\u0082\b\u001a\u000e\u0010\u0010\u001a\u0004\u0018\u00010\u0011*\u00020\u0012H\u0000\u001a\f\u0010\u0013\u001a\u00020\u0014*\u00020\u0012H\u0000\u001a\f\u0010\u0015\u001a\u00020\u0016*\u00020\u0011H\u0000¨\u0006\u0017"}, d2 = {"fastMaxOf", "", "a", "b", "c", "d", "fastMinOf", "boundsInWindow", "Landroid/graphics/Rect;", "Landroidx/compose/ui/layout/LayoutCoordinates;", "rootCoordinates", "fastCoerceAtLeast", "minimumValue", "fastCoerceAtMost", "maximumValue", "fastCoerceIn", "findPainter", "Landroidx/compose/ui/graphics/painter/Painter;", "Landroidx/compose/ui/node/LayoutNode;", "findTextAttributes", "Lio/sentry/android/replay/util/TextAttributes;", "isMaskable", "", "sentry-android-replay_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class NodesKt {
    private static final float fastCoerceAtLeast(float f, float f2) {
        return f < f2 ? f2 : f;
    }

    private static final float fastCoerceAtMost(float f, float f2) {
        return f > f2 ? f2 : f;
    }

    private static final float fastCoerceIn(float f, float f2, float f3) {
        if (f < f2) {
            f = f2;
        }
        return f > f3 ? f3 : f;
    }

    public static final Painter findPainter(LayoutNode layoutNode) {
        Intrinsics.checkNotNullParameter(layoutNode, "<this>");
        List<ModifierInfo> modifierInfo = layoutNode.getModifierInfo();
        int size = modifierInfo.size();
        for (int i = 0; i < size; i++) {
            Modifier modifier = modifierInfo.get(i).getModifier();
            String name = modifier.getClass().getName();
            Intrinsics.checkNotNullExpressionValue(name, "modifier::class.java.name");
            if (StringsKt.contains$default((CharSequence) name, (CharSequence) "Painter", false, 2, (Object) null)) {
                try {
                    Field declaredField = modifier.getClass().getDeclaredField("painter");
                    declaredField.setAccessible(true);
                    Object obj = declaredField.get(modifier);
                    if (obj instanceof Painter) {
                        return (Painter) obj;
                    }
                    return null;
                } catch (Throwable unused) {
                    return null;
                }
            }
        }
        return null;
    }

    public static final boolean isMaskable(Painter painter) {
        Intrinsics.checkNotNullParameter(painter, "<this>");
        String className = painter.getClass().getName();
        Intrinsics.checkNotNullExpressionValue(className, "className");
        String str = className;
        return (StringsKt.contains$default((CharSequence) str, (CharSequence) "Vector", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str, (CharSequence) "Color", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str, (CharSequence) "Brush", false, 2, (Object) null)) ? false : true;
    }

    public static final TextAttributes findTextAttributes(LayoutNode layoutNode) {
        ColorProducer colorProducer;
        Intrinsics.checkNotNullParameter(layoutNode, "<this>");
        List<ModifierInfo> modifierInfo = layoutNode.getModifierInfo();
        int size = modifierInfo.size();
        Color colorM1867boximpl = null;
        boolean z = false;
        for (int i = 0; i < size; i++) {
            Modifier modifier = modifierInfo.get(i).getModifier();
            String modifierClassName = modifier.getClass().getName();
            Intrinsics.checkNotNullExpressionValue(modifierClassName, "modifierClassName");
            String str = modifierClassName;
            if (StringsKt.contains$default((CharSequence) str, (CharSequence) "Text", false, 2, (Object) null)) {
                try {
                    Field declaredField = modifier.getClass().getDeclaredField("color");
                    declaredField.setAccessible(true);
                    Object obj = declaredField.get(modifier);
                    colorProducer = obj instanceof ColorProducer ? (ColorProducer) obj : null;
                } catch (Throwable unused) {
                }
                colorM1867boximpl = colorProducer != null ? Color.m1867boximpl(colorProducer.invoke-0d7_KjU()) : null;
            } else if (StringsKt.contains$default((CharSequence) str, (CharSequence) "Fill", false, 2, (Object) null)) {
                z = true;
            }
        }
        return new TextAttributes(colorM1867boximpl, z, null);
    }

    private static final float fastMinOf(float f, float f2, float f3, float f4) {
        return Math.min(f, Math.min(f2, Math.min(f3, f4)));
    }

    private static final float fastMaxOf(float f, float f2, float f3, float f4) {
        return Math.max(f, Math.max(f2, Math.max(f3, f4)));
    }

    public static final Rect boundsInWindow(LayoutCoordinates layoutCoordinates, LayoutCoordinates layoutCoordinates2) {
        Intrinsics.checkNotNullParameter(layoutCoordinates, "<this>");
        if (layoutCoordinates2 == null) {
            layoutCoordinates2 = LayoutCoordinatesKt.findRootCoordinates(layoutCoordinates);
        }
        float fM4550getWidthimpl = IntSize.m4550getWidthimpl(layoutCoordinates2.mo3401getSizeYbymL2g());
        float fM4549getHeightimpl = IntSize.m4549getHeightimpl(layoutCoordinates2.mo3401getSizeYbymL2g());
        androidx.compose.ui.geometry.Rect rectLocalBoundingBoxOf$default = LayoutCoordinates.localBoundingBoxOf$default(layoutCoordinates2, layoutCoordinates, false, 2, null);
        float left = rectLocalBoundingBoxOf$default.getLeft();
        if (left < 0.0f) {
            left = 0.0f;
        }
        if (left > fM4550getWidthimpl) {
            left = fM4550getWidthimpl;
        }
        float top = rectLocalBoundingBoxOf$default.getTop();
        if (top < 0.0f) {
            top = 0.0f;
        }
        if (top > fM4549getHeightimpl) {
            top = fM4549getHeightimpl;
        }
        float right = rectLocalBoundingBoxOf$default.getRight();
        if (right < 0.0f) {
            right = 0.0f;
        }
        if (right <= fM4550getWidthimpl) {
            fM4550getWidthimpl = right;
        }
        float bottom = rectLocalBoundingBoxOf$default.getBottom();
        float f = bottom >= 0.0f ? bottom : 0.0f;
        if (f <= fM4549getHeightimpl) {
            fM4549getHeightimpl = f;
        }
        if (left == fM4550getWidthimpl || top == fM4549getHeightimpl) {
            return new Rect();
        }
        long jMo3404localToWindowMKHz9U = layoutCoordinates2.mo3404localToWindowMKHz9U(OffsetKt.Offset(left, top));
        long jMo3404localToWindowMKHz9U2 = layoutCoordinates2.mo3404localToWindowMKHz9U(OffsetKt.Offset(fM4550getWidthimpl, top));
        long jMo3404localToWindowMKHz9U3 = layoutCoordinates2.mo3404localToWindowMKHz9U(OffsetKt.Offset(fM4550getWidthimpl, fM4549getHeightimpl));
        long jMo3404localToWindowMKHz9U4 = layoutCoordinates2.mo3404localToWindowMKHz9U(OffsetKt.Offset(left, fM4549getHeightimpl));
        float fM1639getXimpl = Offset.m1639getXimpl(jMo3404localToWindowMKHz9U);
        float fM1639getXimpl2 = Offset.m1639getXimpl(jMo3404localToWindowMKHz9U2);
        float fM1639getXimpl3 = Offset.m1639getXimpl(jMo3404localToWindowMKHz9U4);
        float fM1639getXimpl4 = Offset.m1639getXimpl(jMo3404localToWindowMKHz9U3);
        float fMin = Math.min(fM1639getXimpl, Math.min(fM1639getXimpl2, Math.min(fM1639getXimpl3, fM1639getXimpl4)));
        float fMax = Math.max(fM1639getXimpl, Math.max(fM1639getXimpl2, Math.max(fM1639getXimpl3, fM1639getXimpl4)));
        float fM1640getYimpl = Offset.m1640getYimpl(jMo3404localToWindowMKHz9U);
        float fM1640getYimpl2 = Offset.m1640getYimpl(jMo3404localToWindowMKHz9U2);
        float fM1640getYimpl3 = Offset.m1640getYimpl(jMo3404localToWindowMKHz9U4);
        float fM1640getYimpl4 = Offset.m1640getYimpl(jMo3404localToWindowMKHz9U3);
        return new Rect((int) fMin, (int) Math.min(fM1640getYimpl, Math.min(fM1640getYimpl2, Math.min(fM1640getYimpl3, fM1640getYimpl4))), (int) fMax, (int) Math.max(fM1640getYimpl, Math.max(fM1640getYimpl2, Math.max(fM1640getYimpl3, fM1640getYimpl4))));
    }
}
