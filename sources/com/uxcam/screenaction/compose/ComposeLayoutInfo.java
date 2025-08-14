package com.uxcam.screenaction.compose;

import android.view.View;
import android.view.ViewGroup;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.unit.IntRect;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b¨\u0006\t"}, d2 = {"Lcom/uxcam/screenaction/compose/ComposeLayoutInfo;", "", "()V", "AndroidViewInfo", "LayoutNodeInfo", "SubcompositionInfo", "Lcom/uxcam/screenaction/compose/ComposeLayoutInfo$AndroidViewInfo;", "Lcom/uxcam/screenaction/compose/ComposeLayoutInfo$LayoutNodeInfo;", "Lcom/uxcam/screenaction/compose/ComposeLayoutInfo$SubcompositionInfo;", "screenaction_littleRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes6.dex */
public abstract class ComposeLayoutInfo {

    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/uxcam/screenaction/compose/ComposeLayoutInfo$AndroidViewInfo;", "Lcom/uxcam/screenaction/compose/ComposeLayoutInfo;", "screenaction_littleRelease"}, k = 1, mv = {1, 8, 0})
    public static final /* data */ class AndroidViewInfo extends ComposeLayoutInfo {

        /* renamed from: a, reason: collision with root package name */
        public final View f221a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AndroidViewInfo(ViewGroup view) {
            super(0);
            Intrinsics.checkNotNullParameter(view, "view");
            this.f221a = view;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof AndroidViewInfo) && Intrinsics.areEqual(this.f221a, ((AndroidViewInfo) obj).f221a);
        }

        public final int hashCode() {
            return this.f221a.hashCode();
        }

        public final String toString() {
            return "AndroidViewInfo(view=" + this.f221a + ')';
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/uxcam/screenaction/compose/ComposeLayoutInfo$LayoutNodeInfo;", "Lcom/uxcam/screenaction/compose/ComposeLayoutInfo;", "screenaction_littleRelease"}, k = 1, mv = {1, 8, 0})
    public static final /* data */ class LayoutNodeInfo extends ComposeLayoutInfo {

        /* renamed from: a, reason: collision with root package name */
        public final String f222a;
        public final IntRect b;
        public final List<Modifier> c;
        public final Sequence<ComposeLayoutInfo> d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public LayoutNodeInfo(String name, IntRect bounds, List<? extends Modifier> modifiers, Sequence<? extends ComposeLayoutInfo> children) {
            super(0);
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(bounds, "bounds");
            Intrinsics.checkNotNullParameter(modifiers, "modifiers");
            Intrinsics.checkNotNullParameter(children, "children");
            this.f222a = name;
            this.b = bounds;
            this.c = modifiers;
            this.d = children;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof LayoutNodeInfo)) {
                return false;
            }
            LayoutNodeInfo layoutNodeInfo = (LayoutNodeInfo) obj;
            return Intrinsics.areEqual(this.f222a, layoutNodeInfo.f222a) && Intrinsics.areEqual(this.b, layoutNodeInfo.b) && Intrinsics.areEqual(this.c, layoutNodeInfo.c) && Intrinsics.areEqual(this.d, layoutNodeInfo.d);
        }

        public final int hashCode() {
            return this.d.hashCode() + ((this.c.hashCode() + ((this.b.hashCode() + (this.f222a.hashCode() * 31)) * 31)) * 31);
        }

        public final String toString() {
            return "LayoutNodeInfo(name=" + this.f222a + ", bounds=" + this.b + ", modifiers=" + this.c + ", children=" + this.d + ')';
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/uxcam/screenaction/compose/ComposeLayoutInfo$SubcompositionInfo;", "Lcom/uxcam/screenaction/compose/ComposeLayoutInfo;", "screenaction_littleRelease"}, k = 1, mv = {1, 8, 0})
    public static final /* data */ class SubcompositionInfo extends ComposeLayoutInfo {

        /* renamed from: a, reason: collision with root package name */
        public final String f223a;
        public final IntRect b;
        public final Sequence<ComposeLayoutInfo> c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public SubcompositionInfo(String name, IntRect bounds, Sequence<? extends ComposeLayoutInfo> children) {
            super(0);
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(bounds, "bounds");
            Intrinsics.checkNotNullParameter(children, "children");
            this.f223a = name;
            this.b = bounds;
            this.c = children;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SubcompositionInfo)) {
                return false;
            }
            SubcompositionInfo subcompositionInfo = (SubcompositionInfo) obj;
            return Intrinsics.areEqual(this.f223a, subcompositionInfo.f223a) && Intrinsics.areEqual(this.b, subcompositionInfo.b) && Intrinsics.areEqual(this.c, subcompositionInfo.c);
        }

        public final int hashCode() {
            return this.c.hashCode() + ((this.b.hashCode() + (this.f223a.hashCode() * 31)) * 31);
        }

        public final String toString() {
            return "SubcompositionInfo(name=" + this.f223a + ", bounds=" + this.b + ", children=" + this.c + ')';
        }
    }

    private ComposeLayoutInfo() {
    }

    public /* synthetic */ ComposeLayoutInfo(int i) {
        this();
    }
}
