package com.uxcam.screenaction.compose;

import android.view.View;
import com.uxcam.screenaction.compose.ScannableView;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/uxcam/screenaction/compose/ComposeRootsProviderImpl;", "Lcom/uxcam/screenaction/compose/ComposeRootsProvider;", "<init>", "()V", "screenaction_littleRelease"}, k = 1, mv = {1, 8, 0})
/* loaded from: classes6.dex */
public final class ComposeRootsProviderImpl implements ComposeRootsProvider {
    @Override // com.uxcam.screenaction.compose.ComposeRootsProvider
    public final ArrayList a(ArrayList views) {
        Intrinsics.checkNotNullParameter(views, "views");
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(views, 10));
        Iterator it = views.iterator();
        while (it.hasNext()) {
            arrayList.add(new ScannableView.AndroidView((View) it.next()));
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            CollectionsKt.addAll(arrayList2, ScannableViewKt.a((ScannableView.AndroidView) it2.next()));
        }
        return arrayList2;
    }
}
