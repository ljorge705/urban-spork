package com.uxcam.screenaction.models;

import android.view.View;
import androidx.compose.ui.layout.LayoutCoordinates;
import com.facebook.react.uimanager.ViewProps;
import io.sentry.protocol.ViewHierarchyNode;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b \u0018\u00002\u00020\u0001B[\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u0007\u0012\u0006\u0010\u000b\u001a\u00020\u0007\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u0007\u0012\u0006\u0010\u000f\u001a\u00020\u0007¢\u0006\u0002\u0010\u0010R\u001a\u0010\n\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0002\u001a\u00020\u0001X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\u000e\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0012\"\u0004\b\u001e\u0010\u0014R\u001a\u0010\u000f\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0012\"\u0004\b \u0010\u0014R\u001a\u0010\u000b\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0012\"\u0004\b\"\u0010\u0014R\u001a\u0010\t\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0012\"\u0004\b$\u0010\u0014R \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u0012\"\u0004\b*\u0010\u0014R\u001a\u0010\b\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u0012\"\u0004\b,\u0010\u0014¨\u0006-"}, d2 = {"Lcom/uxcam/screenaction/models/OccludeComposable;", "", ViewHierarchyNode.JsonKeys.IDENTIFIER, "view", "Ljava/lang/ref/WeakReference;", "Landroid/view/View;", "x", "", "y", "top", ViewProps.BOTTOM, ViewProps.RIGHT, "layoutCoordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "parentX", "parentY", "(Ljava/lang/Object;Ljava/lang/ref/WeakReference;FFFFFLandroidx/compose/ui/layout/LayoutCoordinates;FF)V", "getBottom", "()F", "setBottom", "(F)V", "getIdentifier", "()Ljava/lang/Object;", "setIdentifier", "(Ljava/lang/Object;)V", "getLayoutCoordinates", "()Landroidx/compose/ui/layout/LayoutCoordinates;", "setLayoutCoordinates", "(Landroidx/compose/ui/layout/LayoutCoordinates;)V", "getParentX", "setParentX", "getParentY", "setParentY", "getRight", "setRight", "getTop", "setTop", "getView", "()Ljava/lang/ref/WeakReference;", "setView", "(Ljava/lang/ref/WeakReference;)V", "getX", "setX", "getY", "setY", "screenaction_littleRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class OccludeComposable {
    private float bottom;
    private Object identifier;
    private LayoutCoordinates layoutCoordinates;
    private float parentX;
    private float parentY;
    private float right;
    private float top;
    private WeakReference<View> view;
    private float x;
    private float y;

    public OccludeComposable(Object identifier, WeakReference<View> view, float f, float f2, float f3, float f4, float f5, LayoutCoordinates layoutCoordinates, float f6, float f7) {
        Intrinsics.checkNotNullParameter(identifier, "identifier");
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(layoutCoordinates, "layoutCoordinates");
        this.identifier = identifier;
        this.view = view;
        this.x = f;
        this.y = f2;
        this.top = f3;
        this.bottom = f4;
        this.right = f5;
        this.layoutCoordinates = layoutCoordinates;
        this.parentX = f6;
        this.parentY = f7;
    }

    public final float getBottom() {
        return this.bottom;
    }

    public final Object getIdentifier() {
        return this.identifier;
    }

    public final LayoutCoordinates getLayoutCoordinates() {
        return this.layoutCoordinates;
    }

    public final float getParentX() {
        return this.parentX;
    }

    public final float getParentY() {
        return this.parentY;
    }

    public final float getRight() {
        return this.right;
    }

    public final float getTop() {
        return this.top;
    }

    public final WeakReference<View> getView() {
        return this.view;
    }

    public final float getX() {
        return this.x;
    }

    public final float getY() {
        return this.y;
    }

    public final void setBottom(float f) {
        this.bottom = f;
    }

    public final void setIdentifier(Object obj) {
        Intrinsics.checkNotNullParameter(obj, "<set-?>");
        this.identifier = obj;
    }

    public final void setLayoutCoordinates(LayoutCoordinates layoutCoordinates) {
        Intrinsics.checkNotNullParameter(layoutCoordinates, "<set-?>");
        this.layoutCoordinates = layoutCoordinates;
    }

    public final void setParentX(float f) {
        this.parentX = f;
    }

    public final void setParentY(float f) {
        this.parentY = f;
    }

    public final void setRight(float f) {
        this.right = f;
    }

    public final void setTop(float f) {
        this.top = f;
    }

    public final void setView(WeakReference<View> weakReference) {
        Intrinsics.checkNotNullParameter(weakReference, "<set-?>");
        this.view = weakReference;
    }

    public final void setX(float f) {
        this.x = f;
    }

    public final void setY(float f) {
        this.y = f;
    }
}
