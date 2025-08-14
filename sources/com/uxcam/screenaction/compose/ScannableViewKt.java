package com.uxcam.screenaction.compose;

import android.view.View;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.semantics.SemanticsConfigurationKt;
import androidx.compose.ui.semantics.SemanticsModifier;
import androidx.compose.ui.semantics.SemanticsProperties;
import androidx.compose.ui.unit.IntRect;
import com.uxcam.screenaction.compose.ComposeLayoutInfo;
import com.uxcam.screenaction.compose.ScannableView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.StringUtils;

@Metadata(d1 = {"\u0000\u0002\n\u0000¨\u0006\u0000"}, d2 = {"screenaction_littleRelease"}, k = 2, mv = {1, 8, 0})
/* loaded from: classes6.dex */
public final class ScannableViewKt {
    public static final String a(ScannableView.ComposeView composeView) {
        ArrayList arrayList;
        ArrayList arrayList2;
        String strJoinToString$default;
        List<Modifier> list;
        Intrinsics.checkNotNullParameter(composeView, "<this>");
        Intrinsics.checkNotNullParameter(composeView, "<this>");
        if (composeView == null || (list = composeView.c) == null) {
            arrayList = null;
        } else {
            arrayList = new ArrayList();
            for (Object obj : list) {
                if (obj instanceof SemanticsModifier) {
                    arrayList.add(obj);
                }
            }
        }
        if (arrayList != null) {
            arrayList2 = new ArrayList();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                List list2 = (List) SemanticsConfigurationKt.getOrNull(((SemanticsModifier) it.next()).getSemanticsConfiguration(), SemanticsProperties.INSTANCE.getContentDescription());
                String strJoinToString$default2 = list2 != null ? CollectionsKt.joinToString$default(list2, null, null, null, 0, null, null, 63, null) : null;
                if (strJoinToString$default2 != null) {
                    arrayList2.add(strJoinToString$default2);
                }
            }
        } else {
            arrayList2 = null;
        }
        ArrayList arrayList3 = (arrayList2 == null || arrayList2.isEmpty()) ? null : arrayList2;
        if (arrayList3 == null || (strJoinToString$default = CollectionsKt.joinToString$default(arrayList3, StringUtils.SPACE, null, null, 0, null, null, 62, null)) == null) {
            return null;
        }
        return strJoinToString$default;
    }

    public static final String b(ScannableView.ComposeView composeView) {
        ArrayList arrayList;
        ArrayList arrayList2;
        String strJoinToString$default;
        List<Modifier> list;
        Intrinsics.checkNotNullParameter(composeView, "<this>");
        Intrinsics.checkNotNullParameter(composeView, "<this>");
        if (composeView == null || (list = composeView.c) == null) {
            arrayList = null;
        } else {
            arrayList = new ArrayList();
            for (Object obj : list) {
                if (obj instanceof SemanticsModifier) {
                    arrayList.add(obj);
                }
            }
        }
        if (arrayList != null) {
            arrayList2 = new ArrayList();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                List list2 = (List) SemanticsConfigurationKt.getOrNull(((SemanticsModifier) it.next()).getSemanticsConfiguration(), SemanticsProperties.INSTANCE.getText());
                String strJoinToString$default2 = list2 != null ? CollectionsKt.joinToString$default(list2, null, null, null, 0, null, null, 63, null) : null;
                if (strJoinToString$default2 != null) {
                    arrayList2.add(strJoinToString$default2);
                }
            }
        } else {
            arrayList2 = null;
        }
        ArrayList arrayList3 = (arrayList2 == null || arrayList2.isEmpty()) ? null : arrayList2;
        if (arrayList3 == null || (strJoinToString$default = CollectionsKt.joinToString$default(arrayList3, StringUtils.SPACE, null, null, 0, null, null, 62, null)) == null) {
            return null;
        }
        return strJoinToString$default;
    }

    public static final Sequence a(View view) {
        return SequencesKt.sequence(new ScannableViewKt$scannableChildren$1(view, null));
    }

    public static final ScannableView a(ComposeLayoutInfo composeLayoutInfo) {
        Intrinsics.checkNotNullParameter(composeLayoutInfo, "<this>");
        if (composeLayoutInfo instanceof ComposeLayoutInfo.LayoutNodeInfo) {
            ComposeLayoutInfo.LayoutNodeInfo layoutNodeInfo = (ComposeLayoutInfo.LayoutNodeInfo) composeLayoutInfo;
            String str = layoutNodeInfo.f222a;
            IntRect intRect = layoutNodeInfo.b;
            intRect.getRight();
            intRect.getLeft();
            IntRect intRect2 = layoutNodeInfo.b;
            intRect2.getBottom();
            intRect2.getTop();
            return new ScannableView.ComposeView(str, layoutNodeInfo.b, layoutNodeInfo.c, SequencesKt.map(layoutNodeInfo.d, ScannableViewKt$toScannableView$3.f247a));
        }
        if (composeLayoutInfo instanceof ComposeLayoutInfo.SubcompositionInfo) {
            ComposeLayoutInfo.SubcompositionInfo subcompositionInfo = (ComposeLayoutInfo.SubcompositionInfo) composeLayoutInfo;
            String str2 = subcompositionInfo.f223a;
            IntRect intRect3 = subcompositionInfo.b;
            intRect3.getRight();
            intRect3.getLeft();
            IntRect intRect4 = subcompositionInfo.b;
            intRect4.getBottom();
            intRect4.getTop();
            return new ScannableView.ComposeView(str2, subcompositionInfo.b, CollectionsKt.emptyList(), SequencesKt.map(subcompositionInfo.c, ScannableViewKt$toScannableView$6.f248a));
        }
        if (composeLayoutInfo instanceof ComposeLayoutInfo.AndroidViewInfo) {
            return new ScannableView.AndroidView(((ComposeLayoutInfo.AndroidViewInfo) composeLayoutInfo).f221a);
        }
        throw new NoWhenBranchMatchedException();
    }

    public static final ArrayList a(ScannableView scannableView) {
        Intrinsics.checkNotNullParameter(scannableView, "<this>");
        ArrayList arrayList = new ArrayList();
        if (scannableView instanceof ScannableView.ComposeView) {
            arrayList.add(scannableView);
            return arrayList;
        }
        View view = ((ScannableView.AndroidView) scannableView).f244a;
        Lazy lazy = ComposeViewsKt.f232a;
        Intrinsics.checkNotNullParameter(view, "<this>");
        String name = view.getClass().getName();
        Intrinsics.checkNotNullExpressionValue(name, "this::class.java.name");
        if (StringsKt.contains$default((CharSequence) name, (CharSequence) "AndroidComposeView", false, 2, (Object) null)) {
            arrayList.add(scannableView);
            return arrayList;
        }
        Iterator<ScannableView> it = scannableView.a().iterator();
        while (it.hasNext()) {
            arrayList.addAll(a(it.next()));
        }
        return arrayList;
    }
}
