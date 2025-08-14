package androidx.compose.ui.tooling;

import androidx.compose.ui.layout.LayoutInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;

/* compiled from: ShadowViewInfo.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001c\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\u0000¨\u0006\u0004"}, d2 = {"stitchTrees", "", "Landroidx/compose/ui/tooling/ViewInfo;", "allViewInfoRoots", "ui-tooling_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ShadowViewInfoKt {
    public static final List<ViewInfo> stitchTrees(List<ViewInfo> allViewInfoRoots) {
        Intrinsics.checkNotNullParameter(allViewInfoRoots, "allViewInfoRoots");
        if (allViewInfoRoots.size() < 2) {
            return allViewInfoRoots;
        }
        List<ViewInfo> list = allViewInfoRoots;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(new ShadowViewInfo((ViewInfo) it.next()));
        }
        ArrayList arrayList2 = arrayList;
        ArrayList<ShadowViewInfo> arrayList3 = arrayList2;
        ArrayList arrayList4 = new ArrayList();
        Iterator it2 = arrayList3.iterator();
        while (it2.hasNext()) {
            CollectionsKt.addAll(arrayList4, ((ShadowViewInfo) it2.next()).getAllNodes());
        }
        ArrayList<ShadowViewInfo> arrayList5 = arrayList4;
        ArrayList arrayList6 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList5, 10));
        for (ShadowViewInfo shadowViewInfo : arrayList5) {
            arrayList6.add(TuplesKt.to(shadowViewInfo.getLayoutInfo(), shadowViewInfo));
        }
        ArrayList arrayList7 = new ArrayList();
        for (Object obj : arrayList6) {
            if (((Pair) obj).getFirst() != null) {
                arrayList7.add(obj);
            }
        }
        final LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj2 : arrayList7) {
            LayoutInfo layoutInfo = (LayoutInfo) ((Pair) obj2).getFirst();
            Object obj3 = linkedHashMap.get(layoutInfo);
            if (obj3 == null) {
                obj3 = (List) new ArrayList();
                linkedHashMap.put(layoutInfo, obj3);
            }
            ((List) obj3).add(obj2);
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet(arrayList2);
        for (final ShadowViewInfo shadowViewInfo2 : arrayList3) {
            ShadowViewInfo shadowViewInfo3 = (ShadowViewInfo) SequencesKt.firstOrNull(SequencesKt.map(SequencesKt.filter(SequencesKt.flatMapIterable(shadowViewInfo2.getAllNodes(), new Function1<ShadowViewInfo, List<? extends Pair<? extends LayoutInfo, ? extends ShadowViewInfo>>>() { // from class: androidx.compose.ui.tooling.ShadowViewInfoKt$stitchTrees$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final List<Pair<LayoutInfo, ShadowViewInfo>> invoke(ShadowViewInfo candidate) {
                    Intrinsics.checkNotNullParameter(candidate, "candidate");
                    Map<LayoutInfo, List<Pair<LayoutInfo, ShadowViewInfo>>> map = linkedHashMap;
                    LayoutInfo layoutInfo2 = candidate.getLayoutInfo();
                    List<Pair<LayoutInfo, ShadowViewInfo>> list2 = map.get(layoutInfo2 != null ? layoutInfo2.getParentInfo() : null);
                    return list2 == null ? CollectionsKt.emptyList() : list2;
                }
            }), new Function1<Pair<? extends LayoutInfo, ? extends ShadowViewInfo>, Boolean>() { // from class: androidx.compose.ui.tooling.ShadowViewInfoKt$stitchTrees$1$2
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Boolean invoke(Pair<? extends LayoutInfo, ? extends ShadowViewInfo> pair) {
                    return invoke2((Pair<? extends LayoutInfo, ShadowViewInfo>) pair);
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final Boolean invoke2(Pair<? extends LayoutInfo, ShadowViewInfo> it3) {
                    Intrinsics.checkNotNullParameter(it3, "it");
                    return Boolean.valueOf(!Intrinsics.areEqual(it3.getSecond().findRoot(), shadowViewInfo2));
                }
            }), new Function1<Pair<? extends LayoutInfo, ? extends ShadowViewInfo>, ShadowViewInfo>() { // from class: androidx.compose.ui.tooling.ShadowViewInfoKt$stitchTrees$1$3
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final ShadowViewInfo invoke2(Pair<? extends LayoutInfo, ShadowViewInfo> pair) {
                    Intrinsics.checkNotNullParameter(pair, "<name for destructuring parameter 0>");
                    return pair.component2();
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ ShadowViewInfo invoke(Pair<? extends LayoutInfo, ? extends ShadowViewInfo> pair) {
                    return invoke2((Pair<? extends LayoutInfo, ShadowViewInfo>) pair);
                }
            }));
            if (shadowViewInfo3 != null) {
                shadowViewInfo2.setNewParent(shadowViewInfo3);
                linkedHashSet.remove(shadowViewInfo2);
            }
        }
        LinkedHashSet linkedHashSet2 = linkedHashSet;
        ArrayList arrayList8 = new ArrayList(CollectionsKt.collectionSizeOrDefault(linkedHashSet2, 10));
        Iterator it3 = linkedHashSet2.iterator();
        while (it3.hasNext()) {
            arrayList8.add(((ShadowViewInfo) it3.next()).toViewInfo());
        }
        return arrayList8;
    }
}
