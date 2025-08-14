package com.onfido.android.sdk.capture.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.onfido.android.sdk.capture.databinding.OnfidoRecyclerViewBinding;
import com.onfido.android.sdk.capture.utils.ViewExtensionsKt;
import io.sentry.Session;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u0006\u0010\u000f\u001a\u00020\fJ\u0006\u0010\u0010\u001a\u00020\fJ\u000e\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u0007J\u0016\u0010\u0013\u001a\u00020\f2\u000e\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0015R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/widget/RecyclerView;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", Session.JsonKeys.ATTRS, "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "binding", "Lcom/onfido/android/sdk/capture/databinding/OnfidoRecyclerViewBinding;", "addOnScrollListener", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Landroidx/recyclerview/widget/RecyclerView$OnScrollListener;", "enterLoadingState", "exitLoadingState", "scrollToPosition", "index", "setAdapter", "adapter", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class RecyclerView extends FrameLayout {
    private OnfidoRecyclerViewBinding binding;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public RecyclerView(Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public final void addOnScrollListener(RecyclerView.OnScrollListener listener) {
        androidx.recyclerview.widget.RecyclerView recyclerView;
        Intrinsics.checkNotNullParameter(listener, "listener");
        OnfidoRecyclerViewBinding onfidoRecyclerViewBinding = this.binding;
        if (onfidoRecyclerViewBinding == null || (recyclerView = onfidoRecyclerViewBinding.recyclerView) == null) {
            return;
        }
        recyclerView.addOnScrollListener(listener);
    }

    public final void enterLoadingState() {
        ProgressBar progressBar;
        androidx.recyclerview.widget.RecyclerView recyclerView;
        OnfidoRecyclerViewBinding onfidoRecyclerViewBinding = this.binding;
        if (onfidoRecyclerViewBinding != null && (recyclerView = onfidoRecyclerViewBinding.recyclerView) != null) {
            ViewExtensionsKt.toGone$default(recyclerView, false, 1, null);
        }
        OnfidoRecyclerViewBinding onfidoRecyclerViewBinding2 = this.binding;
        if (onfidoRecyclerViewBinding2 == null || (progressBar = onfidoRecyclerViewBinding2.progress) == null) {
            return;
        }
        ViewExtensionsKt.toVisible$default(progressBar, false, 1, null);
    }

    public final void exitLoadingState() {
        androidx.recyclerview.widget.RecyclerView recyclerView;
        ProgressBar progressBar;
        OnfidoRecyclerViewBinding onfidoRecyclerViewBinding = this.binding;
        if (onfidoRecyclerViewBinding != null && (progressBar = onfidoRecyclerViewBinding.progress) != null) {
            ViewExtensionsKt.toGone$default(progressBar, false, 1, null);
        }
        OnfidoRecyclerViewBinding onfidoRecyclerViewBinding2 = this.binding;
        if (onfidoRecyclerViewBinding2 == null || (recyclerView = onfidoRecyclerViewBinding2.recyclerView) == null) {
            return;
        }
        ViewExtensionsKt.toVisible$default(recyclerView, false, 1, null);
    }

    public final void scrollToPosition(int index) {
        androidx.recyclerview.widget.RecyclerView recyclerView;
        OnfidoRecyclerViewBinding onfidoRecyclerViewBinding = this.binding;
        if (onfidoRecyclerViewBinding == null || (recyclerView = onfidoRecyclerViewBinding.recyclerView) == null) {
            return;
        }
        recyclerView.scrollToPosition(index);
    }

    public final void setAdapter(RecyclerView.Adapter<RecyclerView.ViewHolder> adapter) {
        OnfidoRecyclerViewBinding onfidoRecyclerViewBinding = this.binding;
        androidx.recyclerview.widget.RecyclerView recyclerView = onfidoRecyclerViewBinding != null ? onfidoRecyclerViewBinding.recyclerView : null;
        if (recyclerView == null) {
            return;
        }
        recyclerView.setAdapter(adapter);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public RecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        if (isInEditMode()) {
            return;
        }
        OnfidoRecyclerViewBinding onfidoRecyclerViewBindingInflate = OnfidoRecyclerViewBinding.inflate(LayoutInflater.from(context), this, true);
        this.binding = onfidoRecyclerViewBindingInflate;
        androidx.recyclerview.widget.RecyclerView recyclerView = onfidoRecyclerViewBindingInflate != null ? onfidoRecyclerViewBindingInflate.recyclerView : null;
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context, 1, false));
        }
        OnfidoRecyclerViewBinding onfidoRecyclerViewBinding = this.binding;
        androidx.recyclerview.widget.RecyclerView recyclerView2 = onfidoRecyclerViewBinding != null ? onfidoRecyclerViewBinding.recyclerView : null;
        if (recyclerView2 == null) {
            return;
        }
        recyclerView2.setVerticalScrollBarEnabled(true);
    }

    public /* synthetic */ RecyclerView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }
}
