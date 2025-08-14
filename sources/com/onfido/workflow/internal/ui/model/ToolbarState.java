package com.onfido.workflow.internal.ui.model;

import com.clevertap.android.sdk.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: ToolbarState.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B#\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00032\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0015"}, d2 = {"Lcom/onfido/workflow/internal/ui/model/ToolbarState;", "", "showBackButton", "", "showExitButton", "useWebToolbar", "(ZZZ)V", "getShowBackButton", "()Z", "getShowExitButton", "getUseWebToolbar", "component1", "component2", "component3", Constants.COPY_TYPE, "equals", "other", "hashCode", "", "toString", "", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final /* data */ class ToolbarState {
    private final boolean showBackButton;
    private final boolean showExitButton;
    private final boolean useWebToolbar;

    public ToolbarState() {
        this(false, false, false, 7, null);
    }

    public static /* synthetic */ ToolbarState copy$default(ToolbarState toolbarState, boolean z, boolean z2, boolean z3, int i, Object obj) {
        if ((i & 1) != 0) {
            z = toolbarState.showBackButton;
        }
        if ((i & 2) != 0) {
            z2 = toolbarState.showExitButton;
        }
        if ((i & 4) != 0) {
            z3 = toolbarState.useWebToolbar;
        }
        return toolbarState.copy(z, z2, z3);
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getShowBackButton() {
        return this.showBackButton;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getShowExitButton() {
        return this.showExitButton;
    }

    /* renamed from: component3, reason: from getter */
    public final boolean getUseWebToolbar() {
        return this.useWebToolbar;
    }

    public final ToolbarState copy(boolean showBackButton, boolean showExitButton, boolean useWebToolbar) {
        return new ToolbarState(showBackButton, showExitButton, useWebToolbar);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ToolbarState)) {
            return false;
        }
        ToolbarState toolbarState = (ToolbarState) other;
        return this.showBackButton == toolbarState.showBackButton && this.showExitButton == toolbarState.showExitButton && this.useWebToolbar == toolbarState.useWebToolbar;
    }

    public final boolean getShowBackButton() {
        return this.showBackButton;
    }

    public final boolean getShowExitButton() {
        return this.showExitButton;
    }

    public final boolean getUseWebToolbar() {
        return this.useWebToolbar;
    }

    public int hashCode() {
        return (((Boolean.hashCode(this.showBackButton) * 31) + Boolean.hashCode(this.showExitButton)) * 31) + Boolean.hashCode(this.useWebToolbar);
    }

    public String toString() {
        return "ToolbarState(showBackButton=" + this.showBackButton + ", showExitButton=" + this.showExitButton + ", useWebToolbar=" + this.useWebToolbar + ")";
    }

    public ToolbarState(boolean z, boolean z2, boolean z3) {
        this.showBackButton = z;
        this.showExitButton = z2;
        this.useWebToolbar = z3;
    }

    public /* synthetic */ ToolbarState(boolean z, boolean z2, boolean z3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? false : z2, (i & 4) != 0 ? false : z3);
    }
}
