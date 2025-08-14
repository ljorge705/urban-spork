package com.uxcam.screenaction.compose;

import android.view.View;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.unit.IntRect;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0004\u0005\u0006B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0003\u0007\b\t¨\u0006\n"}, d2 = {"Lcom/uxcam/screenaction/compose/ScannableView;", "", "<init>", "()V", "AndroidView", "ChildRenderingError", "ComposeView", "Lcom/uxcam/screenaction/compose/ScannableView$AndroidView;", "Lcom/uxcam/screenaction/compose/ScannableView$ChildRenderingError;", "Lcom/uxcam/screenaction/compose/ScannableView$ComposeView;", "screenaction_littleRelease"}, k = 1, mv = {1, 8, 0})
/* loaded from: classes6.dex */
public abstract class ScannableView {

    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/uxcam/screenaction/compose/ScannableView$AndroidView;", "Lcom/uxcam/screenaction/compose/ScannableView;", "screenaction_littleRelease"}, k = 1, mv = {1, 8, 0})
    public static final class AndroidView extends ScannableView {

        /* renamed from: a, reason: collision with root package name */
        public final View f244a;
        public final Sequence<ScannableView> b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AndroidView(View view) {
            super(0);
            Intrinsics.checkNotNullParameter(view, "view");
            this.f244a = view;
            this.b = ScannableViewKt.a(view);
        }

        @Override // com.uxcam.screenaction.compose.ScannableView
        public final Sequence<ScannableView> a() {
            return this.b;
        }

        public final String toString() {
            StringBuilder sbAppend = new StringBuilder().append(AndroidView.class.getSimpleName()).append('(');
            String simpleName = this.f244a.getClass().getSimpleName();
            Intrinsics.checkNotNullExpressionValue(simpleName, "view::class.java.simpleName");
            return sbAppend.append(simpleName).append(')').toString();
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/uxcam/screenaction/compose/ScannableView$ChildRenderingError;", "Lcom/uxcam/screenaction/compose/ScannableView;", "screenaction_littleRelease"}, k = 1, mv = {1, 8, 0})
    public static final class ChildRenderingError extends ScannableView {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ChildRenderingError(String message) {
            super(0);
            Intrinsics.checkNotNullParameter(message, "message");
        }

        @Override // com.uxcam.screenaction.compose.ScannableView
        public final Sequence<ScannableView> a() {
            return SequencesKt.emptySequence();
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/uxcam/screenaction/compose/ScannableView$ComposeView;", "Lcom/uxcam/screenaction/compose/ScannableView;", "screenaction_littleRelease"}, k = 1, mv = {1, 8, 0})
    public static final class ComposeView extends ScannableView {

        /* renamed from: a, reason: collision with root package name */
        public final String f245a;
        public final IntRect b;
        public final List<Modifier> c;
        public final Sequence<ScannableView> d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ComposeView(String displayName, IntRect bounds, List modifiers, Sequence children) {
            super(0);
            Intrinsics.checkNotNullParameter(displayName, "displayName");
            Intrinsics.checkNotNullParameter(bounds, "bounds");
            Intrinsics.checkNotNullParameter(modifiers, "modifiers");
            Intrinsics.checkNotNullParameter(children, "children");
            this.f245a = displayName;
            this.b = bounds;
            this.c = modifiers;
            this.d = children;
        }

        @Override // com.uxcam.screenaction.compose.ScannableView
        public final Sequence<ScannableView> a() {
            return this.d;
        }

        public final String toString() {
            return ComposeView.class.getSimpleName() + '(' + this.f245a + ')';
        }
    }

    private ScannableView() {
    }

    public /* synthetic */ ScannableView(int i) {
        this();
    }

    public abstract Sequence<ScannableView> a();
}
