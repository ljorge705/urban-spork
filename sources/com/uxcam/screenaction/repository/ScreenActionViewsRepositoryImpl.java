package com.uxcam.screenaction.repository;

import android.view.View;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/uxcam/screenaction/repository/ScreenActionViewsRepositoryImpl;", "Lcom/uxcam/screenaction/repository/ScreenActionViewsRepository;", "<init>", "()V", "screenaction_littleRelease"}, k = 1, mv = {1, 8, 0})
/* loaded from: classes6.dex */
public final class ScreenActionViewsRepositoryImpl implements ScreenActionViewsRepository {

    /* renamed from: a, reason: collision with root package name */
    public final ArrayList f253a = new ArrayList();

    @Override // com.uxcam.screenaction.repository.ScreenActionViewsRepository
    public final void a(WeakReference<View> reference) {
        Intrinsics.checkNotNullParameter(reference, "reference");
        this.f253a.add(reference);
    }

    @Override // com.uxcam.screenaction.repository.ScreenActionViewsRepository
    /* renamed from: b, reason: from getter */
    public final ArrayList getF253a() {
        return this.f253a;
    }

    @Override // com.uxcam.screenaction.repository.ScreenActionViewsRepository
    public final void a() {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.f253a.iterator();
        while (it.hasNext()) {
            WeakReference weakReference = (WeakReference) it.next();
            if (weakReference.get() == null) {
                arrayList.add(weakReference);
            }
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            this.f253a.remove((WeakReference) it2.next());
        }
    }
}
