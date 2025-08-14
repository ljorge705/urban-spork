package com.onfido.android.sdk.capture.utils;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewGroupKt;
import com.clevertap.android.sdk.Constants;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;

@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a+\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0002¢\u0006\u0002\u0010\b\u001a\f\u0010\t\u001a\u00020\n*\u00020\u0005H\u0000\u001a+\u0010\u000b\u001a\u0004\u0018\u00010\n*\u00020\u00052\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\n0\rH\u0080\bø\u0001\u0000¢\u0006\u0002\u0010\u000f\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0010"}, d2 = {"getToolbarItem", "Landroid/view/View;", "isBackButton", "", "toolbar", "Landroidx/appcompat/widget/Toolbar;", "isImportantForAccessibility", "", "(ZLandroidx/appcompat/widget/Toolbar;Ljava/lang/Integer;)Landroid/view/View;", "makeTitleNotImportantForAccessibility", "", "performActionOnTitleTextView", Constants.KEY_ACTION, "Lkotlin/Function1;", "Landroid/widget/TextView;", "(Landroidx/appcompat/widget/Toolbar;Lkotlin/jvm/functions/Function1;)Lkotlin/Unit;", "onfido-capture-sdk-core_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ToolbarExtensionsKt {
    private static final View getToolbarItem(boolean z, Toolbar toolbar, Integer num) {
        Iterator<Integer> it = RangesKt.until(0, toolbar.getChildCount()).iterator();
        View view = null;
        while (it.hasNext()) {
            View childAt = toolbar.getChildAt(((IntIterator) it).nextInt());
            if (!(childAt instanceof TextView) || z) {
                if (!(childAt instanceof ImageButton) || !z) {
                }
            } else if (num != null) {
                ((TextView) childAt).setImportantForAccessibility(num.intValue());
            }
            view = childAt;
        }
        return view;
    }

    static /* synthetic */ View getToolbarItem$default(boolean z, Toolbar toolbar, Integer num, int i, Object obj) {
        if ((i & 4) != 0) {
            num = null;
        }
        return getToolbarItem(z, toolbar, num);
    }

    public static final void makeTitleNotImportantForAccessibility(Toolbar toolbar) {
        Intrinsics.checkNotNullParameter(toolbar, "<this>");
        getToolbarItem(false, toolbar, 2);
    }

    public static final Unit performActionOnTitleTextView(Toolbar toolbar, Function1<? super TextView, Unit> action) {
        Intrinsics.checkNotNullParameter(toolbar, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        Sequence sequenceFilter = SequencesKt.filter(ViewGroupKt.getChildren(toolbar), ToolbarExtensionsKt$performActionOnTitleTextView$$inlined$filterIsInstance$1.INSTANCE);
        Intrinsics.checkNotNull(sequenceFilter, "null cannot be cast to non-null type kotlin.sequences.Sequence<R of kotlin.sequences.SequencesKt___SequencesKt.filterIsInstance>");
        TextView textView = (TextView) SequencesKt.firstOrNull(sequenceFilter);
        if (textView == null) {
            return null;
        }
        action.invoke(textView);
        return Unit.INSTANCE;
    }
}
