package io.sentry.android.replay.util;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.facebook.react.uimanager.ViewProps;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import io.sentry.SentryOptions;
import io.sentry.android.replay.viewhierarchy.ComposeViewHierarchyNode;
import io.sentry.android.replay.viewhierarchy.ViewHierarchyNode;
import io.sentry.rrweb.RRWebOptionsEvent;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Views.kt */
@Metadata(d1 = {"\u0000L\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0005\u001a\u00020\u0006*\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\tH\u0000\u001a,\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b*\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u0001H\u0000\u001a\u000e\u0010\u0011\u001a\u00020\u0012*\u0004\u0018\u00010\u0013H\u0001\u001a\u001a\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u00020\u0012\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0015*\u00020\u0007H\u0000\u001a\u0016\u0010\u0016\u001a\u00020\u0006*\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\tH\u0000\u001a\f\u0010\u0017\u001a\u00020\u0001*\u00020\u0001H\u0000\u001a\u001c\u0010\u0018\u001a\u00020\u0006*\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0000\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u001d"}, d2 = {"totalPaddingTopSafe", "", "Landroid/widget/TextView;", "getTotalPaddingTopSafe", "(Landroid/widget/TextView;)I", "addOnDrawListenerSafe", "", "Landroid/view/View;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Landroid/view/ViewTreeObserver$OnDrawListener;", "getVisibleRects", "", "Landroid/graphics/Rect;", "Lio/sentry/android/replay/util/TextLayout;", "globalRect", ViewProps.PADDING_LEFT, ViewProps.PADDING_TOP, "isMaskable", "", "Landroid/graphics/drawable/Drawable;", "isVisibleToUser", "Lkotlin/Pair;", "removeOnDrawListenerSafe", "toOpaque", "traverse", "parentNode", "Lio/sentry/android/replay/viewhierarchy/ViewHierarchyNode;", RRWebOptionsEvent.EVENT_TAG, "Lio/sentry/SentryOptions;", "sentry-android-replay_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class ViewsKt {
    public static final int toOpaque(int i) {
        return i | ViewCompat.MEASURED_STATE_MASK;
    }

    public static final void traverse(View view, ViewHierarchyNode parentNode, SentryOptions options) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        Intrinsics.checkNotNullParameter(parentNode, "parentNode");
        Intrinsics.checkNotNullParameter(options, "options");
        if ((view instanceof ViewGroup) && !ComposeViewHierarchyNode.INSTANCE.fromView(view, parentNode, options)) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (viewGroup.getChildCount() == 0) {
                return;
            }
            ArrayList arrayList = new ArrayList(viewGroup.getChildCount());
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt != null) {
                    ViewHierarchyNode viewHierarchyNodeFromView = ViewHierarchyNode.INSTANCE.fromView(childAt, parentNode, viewGroup.indexOfChild(childAt), options);
                    arrayList.add(viewHierarchyNodeFromView);
                    traverse(childAt, viewHierarchyNodeFromView, options);
                }
            }
            parentNode.setChildren(arrayList);
        }
    }

    public static final Pair<Boolean, Rect> isVisibleToUser(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        if (view.isAttachedToWindow()) {
            if (view.getWindowVisibility() != 0) {
                return TuplesKt.to(false, null);
            }
            Object parent = view;
            while (parent instanceof View) {
                float transitionAlpha = Build.VERSION.SDK_INT >= 29 ? ((View) parent).getTransitionAlpha() : 1.0f;
                View view2 = (View) parent;
                if (view2.getAlpha() <= 0.0f || transitionAlpha <= 0.0f || view2.getVisibility() != 0) {
                    return TuplesKt.to(false, null);
                }
                parent = view2.getParent();
            }
            Rect rect = new Rect();
            return TuplesKt.to(Boolean.valueOf(view.getGlobalVisibleRect(rect, new Point())), rect);
        }
        return TuplesKt.to(false, null);
    }

    public static final boolean isMaskable(Drawable drawable) {
        if ((drawable instanceof InsetDrawable) || (drawable instanceof ColorDrawable) || (drawable instanceof VectorDrawable) || (drawable instanceof GradientDrawable)) {
            return false;
        }
        if (!(drawable instanceof BitmapDrawable)) {
            return true;
        }
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        return bitmap != null && !bitmap.isRecycled() && bitmap.getHeight() > 10 && bitmap.getWidth() > 10;
    }

    public static final List<Rect> getVisibleRects(TextLayout textLayout, Rect globalRect, int i, int i2) {
        Intrinsics.checkNotNullParameter(globalRect, "globalRect");
        if (textLayout == null) {
            return CollectionsKt.listOf(globalRect);
        }
        ArrayList arrayList = new ArrayList();
        int lineCount = textLayout.getLineCount();
        for (int i3 = 0; i3 < lineCount; i3++) {
            int primaryHorizontal = (int) textLayout.getPrimaryHorizontal(i3, textLayout.getLineStart(i3));
            int ellipsisCount = textLayout.getEllipsisCount(i3);
            int lineVisibleEnd = textLayout.getLineVisibleEnd(i3);
            int primaryHorizontal2 = (int) textLayout.getPrimaryHorizontal(i3, (lineVisibleEnd - ellipsisCount) + (ellipsisCount > 0 ? 1 : 0));
            if (primaryHorizontal2 == 0 && lineVisibleEnd > 0) {
                primaryHorizontal2 = ((int) textLayout.getPrimaryHorizontal(i3, lineVisibleEnd - 1)) + 1;
            }
            int lineTop = textLayout.getLineTop(i3);
            int lineBottom = textLayout.getLineBottom(i3);
            Rect rect = new Rect();
            rect.left = globalRect.left + i + primaryHorizontal;
            rect.right = rect.left + (primaryHorizontal2 - primaryHorizontal);
            rect.top = globalRect.top + i2 + lineTop;
            rect.bottom = rect.top + (lineBottom - lineTop);
            arrayList.add(rect);
        }
        return arrayList;
    }

    public static final int getTotalPaddingTopSafe(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<this>");
        try {
            return textView.getTotalPaddingTop();
        } catch (NullPointerException unused) {
            return textView.getExtendedPaddingTop();
        }
    }

    public static final void addOnDrawListenerSafe(View view, ViewTreeObserver.OnDrawListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (view == null || view.getViewTreeObserver() == null || !view.getViewTreeObserver().isAlive()) {
            return;
        }
        view.getViewTreeObserver().addOnDrawListener(listener);
    }

    public static final void removeOnDrawListenerSafe(View view, ViewTreeObserver.OnDrawListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (view == null || view.getViewTreeObserver() == null || !view.getViewTreeObserver().isAlive()) {
            return;
        }
        view.getViewTreeObserver().removeOnDrawListener(listener);
    }
}
