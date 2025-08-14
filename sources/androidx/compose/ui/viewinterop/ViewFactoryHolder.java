package androidx.compose.ui.viewinterop;

import android.content.Context;
import android.view.View;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher;
import androidx.compose.ui.platform.ViewRootForInspector;
import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AndroidView.android.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u00032\u00020\u0004B#\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bR@\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00028\u0000\u0018\u00010\r2\u0014\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00028\u0000\u0018\u00010\r@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001e\u0010\u0013\u001a\u0004\u0018\u00018\u0000X\u0080\u000e¢\u0006\u0010\n\u0002\u0010\u0018\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R<\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00190\r2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00190\r@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0010\"\u0004\b\u001c\u0010\u0012R\u0014\u0010\u001d\u001a\u00020\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u0015¨\u0006\u001f"}, d2 = {"Landroidx/compose/ui/viewinterop/ViewFactoryHolder;", ExifInterface.GPS_DIRECTION_TRUE, "Landroid/view/View;", "Landroidx/compose/ui/viewinterop/AndroidViewHolder;", "Landroidx/compose/ui/platform/ViewRootForInspector;", "context", "Landroid/content/Context;", "parentContext", "Landroidx/compose/runtime/CompositionContext;", "dispatcher", "Landroidx/compose/ui/input/nestedscroll/NestedScrollDispatcher;", "(Landroid/content/Context;Landroidx/compose/runtime/CompositionContext;Landroidx/compose/ui/input/nestedscroll/NestedScrollDispatcher;)V", "value", "Lkotlin/Function1;", "factory", "getFactory", "()Lkotlin/jvm/functions/Function1;", "setFactory", "(Lkotlin/jvm/functions/Function1;)V", "typedView", "getTypedView$ui_release", "()Landroid/view/View;", "setTypedView$ui_release", "(Landroid/view/View;)V", "Landroid/view/View;", "", "updateBlock", "getUpdateBlock", "setUpdateBlock", "viewRoot", "getViewRoot", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ViewFactoryHolder<T extends View> extends AndroidViewHolder implements ViewRootForInspector {
    private Function1<? super Context, ? extends T> factory;
    private T typedView;
    private Function1<? super T, Unit> updateBlock;

    public final Function1<Context, T> getFactory() {
        return this.factory;
    }

    public final T getTypedView$ui_release() {
        return this.typedView;
    }

    public final Function1<T, Unit> getUpdateBlock() {
        return this.updateBlock;
    }

    public final void setTypedView$ui_release(T t) {
        this.typedView = t;
    }

    public /* synthetic */ ViewFactoryHolder(Context context, CompositionContext compositionContext, NestedScrollDispatcher nestedScrollDispatcher, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : compositionContext, (i & 4) != 0 ? new NestedScrollDispatcher() : nestedScrollDispatcher);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ViewFactoryHolder(Context context, CompositionContext compositionContext, NestedScrollDispatcher dispatcher) {
        super(context, compositionContext, dispatcher);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(dispatcher, "dispatcher");
        setClipChildren(false);
        this.updateBlock = AndroidView_androidKt.getNoOpUpdate();
    }

    @Override // androidx.compose.ui.platform.ViewRootForInspector
    public View getViewRoot() {
        return this;
    }

    public final void setFactory(Function1<? super Context, ? extends T> function1) {
        this.factory = function1;
        if (function1 != null) {
            Context context = getContext();
            Intrinsics.checkNotNullExpressionValue(context, "context");
            T tInvoke = function1.invoke(context);
            this.typedView = tInvoke;
            setView$ui_release(tInvoke);
        }
    }

    public final void setUpdateBlock(Function1<? super T, Unit> value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.updateBlock = value;
        setUpdate(new Function0<Unit>(this) { // from class: androidx.compose.ui.viewinterop.ViewFactoryHolder$updateBlock$1
            final /* synthetic */ ViewFactoryHolder<T> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
                this.this$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                View typedView$ui_release = this.this$0.getTypedView$ui_release();
                if (typedView$ui_release != null) {
                    this.this$0.getUpdateBlock().invoke(typedView$ui_release);
                }
            }
        });
    }
}
