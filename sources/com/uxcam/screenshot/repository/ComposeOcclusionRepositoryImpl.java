package com.uxcam.screenshot.repository;

import com.uxcam.screenaction.models.OccludeComposable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/uxcam/screenshot/repository/ComposeOcclusionRepositoryImpl;", "Lcom/uxcam/screenshot/repository/ComposeOcclusionRepository;", "<init>", "()V", "screenshot_littleRelease"}, k = 1, mv = {1, 8, 0})
/* loaded from: classes6.dex */
public final class ComposeOcclusionRepositoryImpl implements ComposeOcclusionRepository {

    /* renamed from: a, reason: collision with root package name */
    public final ArrayList f276a = new ArrayList();

    @Override // com.uxcam.screenshot.repository.ComposeOcclusionRepository
    public final void addComposable(OccludeComposable composable) {
        Intrinsics.checkNotNullParameter(composable, "composable");
        Iterator it = this.f276a.iterator();
        while (it.hasNext()) {
            OccludeComposable occludeComposable = (OccludeComposable) it.next();
            if (Intrinsics.areEqual(occludeComposable.getIdentifier(), composable.getIdentifier())) {
                this.f276a.remove(occludeComposable);
                this.f276a.add(composable);
                return;
            }
        }
        this.f276a.add(composable);
    }

    @Override // com.uxcam.screenshot.repository.ComposeOcclusionRepository
    public final List<OccludeComposable> getComposablesToHide() {
        List<OccludeComposable> listUnmodifiableList = Collections.unmodifiableList(this.f276a);
        Intrinsics.checkNotNullExpressionValue(listUnmodifiableList, "unmodifiableList(occludeComposableList)");
        return listUnmodifiableList;
    }
}
