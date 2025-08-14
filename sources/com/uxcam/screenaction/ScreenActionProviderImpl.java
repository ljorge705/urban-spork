package com.uxcam.screenaction;

import com.uxcam.screenaction.compose.ComposeRootsProvider;
import com.uxcam.screenaction.compose.ComposeScreenActionProvider;
import com.uxcam.screenaction.models.GestureData;
import com.uxcam.screenaction.models.OccludeComposable;
import com.uxcam.screenaction.models.ScreenAction;
import com.uxcam.screenaction.models.UXCamOccludeView;
import com.uxcam.screenaction.models.UXCamView;
import com.uxcam.screenaction.models.ViewRootData;
import com.uxcam.screenaction.utils.ComposeUtilsKt;
import com.uxcam.screenaction.views.ViewSystemScreenActionProvider;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt__JobKt;

@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002¨\u0006\u0003"}, d2 = {"Lcom/uxcam/screenaction/ScreenActionProviderImpl;", "Lcom/uxcam/screenaction/ScreenActionProvider;", "Lkotlinx/coroutines/CoroutineScope;", "screenaction_littleRelease"}, k = 1, mv = {1, 8, 0})
/* loaded from: classes6.dex */
public final class ScreenActionProviderImpl implements ScreenActionProvider, CoroutineScope {

    /* renamed from: a, reason: collision with root package name */
    public final ViewSystemScreenActionProvider f219a;
    public final ComposeScreenActionProvider b;
    public final ComposeRootsProvider c;

    public ScreenActionProviderImpl(ViewSystemScreenActionProvider viewSystemScreenActionProvider, ComposeScreenActionProvider composeScreenActionProvider, ComposeRootsProvider composeRootsProvider) {
        Intrinsics.checkNotNullParameter(viewSystemScreenActionProvider, "viewSystemScreenActionProvider");
        Intrinsics.checkNotNullParameter(composeScreenActionProvider, "composeScreenActionProvider");
        Intrinsics.checkNotNullParameter(composeRootsProvider, "composeRootsProvider");
        this.f219a = viewSystemScreenActionProvider;
        this.b = composeScreenActionProvider;
        this.c = composeRootsProvider;
    }

    @Override // kotlinx.coroutines.CoroutineScope
    public final CoroutineContext getCoroutineContext() {
        return JobKt__JobKt.Job$default((Job) null, 1, (Object) null).plus(Dispatchers.getMain());
    }

    @Override // com.uxcam.screenaction.ScreenActionProvider
    public final void getScreenAction(float f, UXCamView uXCamView, List<? extends UXCamOccludeView> list, GestureData gestureData, List<ViewRootData> list2, List<OccludeComposable> occludedComposables, Function1<? super ScreenAction, Unit> onResult) {
        Intrinsics.checkNotNullParameter(occludedComposables, "occludedComposables");
        Intrinsics.checkNotNullParameter(onResult, "onResult");
        if (uXCamView == null || uXCamView.getView().get() == null || list2 == null) {
            onResult.invoke(null);
            return;
        }
        ComposeRootsProvider composeRootsProvider = this.c;
        List listFilterNotNull = CollectionsKt.filterNotNull(list2);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listFilterNotNull, 10));
        Iterator it = listFilterNotNull.iterator();
        while (it.hasNext()) {
            arrayList.add(((ViewRootData) it.next()).getView());
        }
        if (composeRootsProvider.a(arrayList).isEmpty()) {
            a(onResult, uXCamView, f, list);
            return;
        }
        if (gestureData == null) {
            onResult.invoke(null);
        } else if (ComposeUtilsKt.isGestureInOccludedComposable(occludedComposables, gestureData)) {
            onResult.invoke(null);
        } else {
            a(onResult, uXCamView, f, list);
        }
    }

    public final void a(Function1<? super ScreenAction, Unit> function1, UXCamView uXCamView, float f, List<? extends UXCamOccludeView> list) {
        boolean zContains;
        ViewSystemScreenActionProvider viewSystemScreenActionProvider = this.f219a;
        if (list != null) {
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            for (UXCamOccludeView uXCamOccludeView : list) {
                arrayList.add(uXCamOccludeView != null ? uXCamOccludeView.getView() : null);
            }
            zContains = arrayList.contains(uXCamView.getView());
        } else {
            zContains = false;
        }
        function1.invoke(viewSystemScreenActionProvider.a(uXCamView, f, zContains));
    }
}
