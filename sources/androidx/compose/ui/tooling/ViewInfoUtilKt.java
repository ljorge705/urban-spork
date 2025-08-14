package androidx.compose.ui.tooling;

import androidx.compose.ui.unit.IntRect;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* compiled from: ViewInfoUtil.kt */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\u001a.\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00020\u00012\u0014\b\u0002\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00050\u0004H\u0002\u001a2\u0010\u0006\u001a\u00020\u0007*\b\u0012\u0004\u0012\u00020\u00020\u00012\b\b\u0002\u0010\b\u001a\u00020\t2\u0014\b\u0002\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00050\u0004H\u0000¨\u0006\n"}, d2 = {"filterTree", "", "Landroidx/compose/ui/tooling/ViewInfo;", "filter", "Lkotlin/Function1;", "", "toDebugString", "", "indentation", "", "ui-tooling_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ViewInfoUtilKt {
    static /* synthetic */ List filterTree$default(List list, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = new Function1<ViewInfo, Boolean>() { // from class: androidx.compose.ui.tooling.ViewInfoUtilKt.filterTree.1
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(ViewInfo it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return true;
                }
            };
        }
        return filterTree(list, function1);
    }

    private static final List<ViewInfo> filterTree(List<ViewInfo> list, Function1<? super ViewInfo, Boolean> function1) {
        List listListOf;
        List<ViewInfo> listListOf2;
        ArrayList arrayList = new ArrayList();
        for (ViewInfo viewInfo : list) {
            List<ViewInfo> listFilterTree = filterTree(viewInfo.getChildren(), function1);
            ArrayList arrayList2 = new ArrayList();
            for (ViewInfo viewInfo2 : listFilterTree) {
                if (viewInfo2.getLocation() == null) {
                    listListOf2 = viewInfo2.getChildren();
                } else {
                    listListOf2 = CollectionsKt.listOf(viewInfo2);
                }
                CollectionsKt.addAll(arrayList2, listListOf2);
            }
            ArrayList arrayList3 = arrayList2;
            if (function1.invoke(viewInfo).booleanValue()) {
                listListOf = CollectionsKt.listOf(new ViewInfo(viewInfo.getFileName(), viewInfo.getLineNumber(), viewInfo.getBounds(), viewInfo.getLocation(), arrayList3, viewInfo.getLayoutInfo()));
            } else {
                listListOf = CollectionsKt.listOf(new ViewInfo("<root>", -1, IntRect.INSTANCE.getZero(), null, arrayList3, null));
            }
            CollectionsKt.addAll(arrayList, listListOf);
        }
        return arrayList;
    }

    public static /* synthetic */ String toDebugString$default(List list, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        if ((i2 & 2) != 0) {
            function1 = new Function1<ViewInfo, Boolean>() { // from class: androidx.compose.ui.tooling.ViewInfoUtilKt.toDebugString.1
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(ViewInfo it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return true;
                }
            };
        }
        return toDebugString(list, i, function1);
    }

    public static final String toDebugString(List<ViewInfo> list, int i, Function1<? super ViewInfo, Boolean> filter) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Intrinsics.checkNotNullParameter(filter, "filter");
        String strRepeat = StringsKt.repeat(".", i);
        StringBuilder sb = new StringBuilder();
        for (ViewInfo viewInfo : CollectionsKt.sortedWith(filterTree(list, filter), ComparisonsKt.compareBy(new Function1<ViewInfo, Comparable<?>>() { // from class: androidx.compose.ui.tooling.ViewInfoUtilKt.toDebugString.2
            @Override // kotlin.jvm.functions.Function1
            public final Comparable<?> invoke(ViewInfo it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.getFileName();
            }
        }, new Function1<ViewInfo, Comparable<?>>() { // from class: androidx.compose.ui.tooling.ViewInfoUtilKt.toDebugString.3
            @Override // kotlin.jvm.functions.Function1
            public final Comparable<?> invoke(ViewInfo it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Integer.valueOf(it.getLineNumber());
            }
        }, new Function1<ViewInfo, Comparable<?>>() { // from class: androidx.compose.ui.tooling.ViewInfoUtilKt.toDebugString.4
            @Override // kotlin.jvm.functions.Function1
            public final Comparable<?> invoke(ViewInfo it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Integer.valueOf(it.allChildren().size());
            }
        }))) {
            if (viewInfo.getLocation() != null) {
                StringBuilder sbAppend = sb.append(strRepeat + '|' + viewInfo.getFileName() + AbstractJsonLexerKt.COLON + viewInfo.getLineNumber());
                Intrinsics.checkNotNullExpressionValue(sbAppend, "append(value)");
                Intrinsics.checkNotNullExpressionValue(sbAppend.append('\n'), "append('\\n')");
            } else {
                StringBuilder sbAppend2 = sb.append(strRepeat + "|<root>");
                Intrinsics.checkNotNullExpressionValue(sbAppend2, "append(value)");
                Intrinsics.checkNotNullExpressionValue(sbAppend2.append('\n'), "append('\\n')");
            }
            String string = StringsKt.trim((CharSequence) toDebugString(viewInfo.getChildren(), i + 1, filter)).toString();
            if (string.length() > 0) {
                StringBuilder sbAppend3 = sb.append(string);
                Intrinsics.checkNotNullExpressionValue(sbAppend3, "append(value)");
                Intrinsics.checkNotNullExpressionValue(sbAppend3.append('\n'), "append('\\n')");
            }
        }
        String string2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string2, "builder.toString()");
        return string2;
    }
}
