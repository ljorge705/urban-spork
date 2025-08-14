package com.uxcam.screenaction.models;

import android.graphics.Rect;
import android.view.View;
import android.view.WindowManager;
import com.clevertap.android.sdk.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0010\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0007¢\u0006\u0004\b \u0010!J\u0006\u0010\u0002\u001a\u00020\u0000J\t\u0010\u0004\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0006\u001a\u00020\u0005HÆ\u0003J\t\u0010\b\u001a\u00020\u0007HÆ\u0003J'\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u0007HÆ\u0001J\t\u0010\u000e\u001a\u00020\rHÖ\u0001J\t\u0010\u0010\u001a\u00020\u000fHÖ\u0001J\u0013\u0010\u0013\u001a\u00020\u00122\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\n\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u0017\u0010\u000b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001f¨\u0006\""}, d2 = {"Lcom/uxcam/screenaction/models/ViewRootData;", "", "duplicate", "Landroid/view/View;", "component1", "Landroid/graphics/Rect;", "component2", "Landroid/view/WindowManager$LayoutParams;", "component3", "view", "winFrame", "layoutParams", Constants.COPY_TYPE, "", "toString", "", "hashCode", "other", "", "equals", "a", "Landroid/view/View;", "getView", "()Landroid/view/View;", "b", "Landroid/graphics/Rect;", "getWinFrame", "()Landroid/graphics/Rect;", "c", "Landroid/view/WindowManager$LayoutParams;", "getLayoutParams", "()Landroid/view/WindowManager$LayoutParams;", "<init>", "(Landroid/view/View;Landroid/graphics/Rect;Landroid/view/WindowManager$LayoutParams;)V", "screenaction_littleRelease"}, k = 1, mv = {1, 8, 0})
/* loaded from: classes6.dex */
public final /* data */ class ViewRootData {

    /* renamed from: a, reason: collision with root package name and from kotlin metadata */
    public final View view;

    /* renamed from: b, reason: from kotlin metadata */
    public final Rect winFrame;

    /* renamed from: c, reason: from kotlin metadata */
    public final WindowManager.LayoutParams layoutParams;

    public ViewRootData(View view, Rect winFrame, WindowManager.LayoutParams layoutParams) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(winFrame, "winFrame");
        Intrinsics.checkNotNullParameter(layoutParams, "layoutParams");
        this.view = view;
        this.winFrame = winFrame;
        this.layoutParams = layoutParams;
    }

    public static /* synthetic */ ViewRootData copy$default(ViewRootData viewRootData, View view, Rect rect, WindowManager.LayoutParams layoutParams, int i, Object obj) {
        if ((i & 1) != 0) {
            view = viewRootData.view;
        }
        if ((i & 2) != 0) {
            rect = viewRootData.winFrame;
        }
        if ((i & 4) != 0) {
            layoutParams = viewRootData.layoutParams;
        }
        return viewRootData.copy(view, rect, layoutParams);
    }

    /* renamed from: component1, reason: from getter */
    public final View getView() {
        return this.view;
    }

    /* renamed from: component2, reason: from getter */
    public final Rect getWinFrame() {
        return this.winFrame;
    }

    /* renamed from: component3, reason: from getter */
    public final WindowManager.LayoutParams getLayoutParams() {
        return this.layoutParams;
    }

    public final ViewRootData copy(View view, Rect winFrame, WindowManager.LayoutParams layoutParams) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(winFrame, "winFrame");
        Intrinsics.checkNotNullParameter(layoutParams, "layoutParams");
        return new ViewRootData(view, winFrame, layoutParams);
    }

    public final ViewRootData duplicate() {
        return new ViewRootData(this.view, this.winFrame, this.layoutParams);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ViewRootData)) {
            return false;
        }
        ViewRootData viewRootData = (ViewRootData) other;
        return Intrinsics.areEqual(this.view, viewRootData.view) && Intrinsics.areEqual(this.winFrame, viewRootData.winFrame) && Intrinsics.areEqual(this.layoutParams, viewRootData.layoutParams);
    }

    public final WindowManager.LayoutParams getLayoutParams() {
        return this.layoutParams;
    }

    public final View getView() {
        return this.view;
    }

    public final Rect getWinFrame() {
        return this.winFrame;
    }

    public int hashCode() {
        return this.layoutParams.hashCode() + ((this.winFrame.hashCode() + (this.view.hashCode() * 31)) * 31);
    }

    public String toString() {
        return "ViewRootData(view=" + this.view + ", winFrame=" + this.winFrame + ", layoutParams=" + this.layoutParams + ')';
    }
}
