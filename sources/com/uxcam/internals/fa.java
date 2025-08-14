package com.uxcam.internals;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.uxcam.screenaction.di.ScreenActionModule;
import com.uxcam.screenaction.models.GestureData;
import com.uxcam.screenaction.models.ScreenAction;
import com.uxcam.screenshot.di.ScreenshotModule;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* loaded from: classes6.dex */
public final class fa implements ex {

    /* renamed from: a, reason: collision with root package name */
    public final cg f144a;
    public final ci b;
    public final ey c;
    public final fd d;
    public final boolean e;

    public fa(ch fragmentDataHash, cj fragmentLifecycleDataProvider, ez managerHelper, fe screenTagRepository) {
        Intrinsics.checkNotNullParameter(fragmentDataHash, "fragmentDataHash");
        Intrinsics.checkNotNullParameter(fragmentLifecycleDataProvider, "fragmentLifecycleDataProvider");
        Intrinsics.checkNotNullParameter(managerHelper, "managerHelper");
        Intrinsics.checkNotNullParameter(screenTagRepository, "screenTagRepository");
        this.f144a = fragmentDataHash;
        this.b = fragmentLifecycleDataProvider;
        this.c = managerHelper;
        this.d = screenTagRepository;
        this.e = a();
    }

    @Override // com.uxcam.internals.ex
    public final hg b(String str) {
        hg next;
        List<hg> listF = this.d.f();
        Intrinsics.checkNotNull(listF);
        Iterator<hg> it = listF.iterator();
        do {
            if (!it.hasNext()) {
                return null;
            }
            next = it.next();
        } while (!Intrinsics.areEqual(next != null ? next.f191a : null, str));
        return next;
    }

    @Override // com.uxcam.internals.ex
    public final void d() {
        this.d.d();
    }

    @Override // com.uxcam.internals.ex
    public final String e() {
        return this.d.e();
    }

    @Override // com.uxcam.internals.ex
    public final void f() {
        this.d.g();
    }

    @Override // com.uxcam.internals.ex
    public final boolean g() {
        fd fdVar = this.d;
        TreeSet activitiesToIgnore = ga.D;
        Intrinsics.checkNotNullExpressionValue(activitiesToIgnore, "activitiesToIgnore");
        return fdVar.a(activitiesToIgnore);
    }

    @Override // com.uxcam.internals.ex
    public final void b(FragmentManager fragmentManager, Fragment fragment) {
        if (fragment instanceof DialogFragment) {
            Fragment fragmentD = this.b.d(fragmentManager);
            if (fragmentD != null) {
                String fragmentName = fragmentD.getClass().getSimpleName();
                fd fdVar = this.d;
                Intrinsics.checkNotNullExpressionValue(fragmentName, "fragmentName");
                fdVar.b(fragmentName);
                this.d.b();
                a(fragmentManager, fragmentName);
            } else {
                ey eyVar = this.c;
                eyVar.a(this.d.c(eyVar.a()));
            }
        }
        this.d.i();
    }

    @Override // com.uxcam.internals.ex
    public final void a(String str, boolean z) {
        this.d.a(str, z, this.c.a());
    }

    @Override // com.uxcam.internals.ex
    public final boolean a(String str) {
        return StringsKt.equals(str, this.d.e(), true);
    }

    @Override // com.uxcam.internals.ex
    public final ArrayList a(List timelineDataList) {
        Intrinsics.checkNotNullParameter(timelineDataList, "timelineDataList");
        ArrayList arrayList = new ArrayList();
        Iterator it = timelineDataList.iterator();
        while (it.hasNext()) {
            gp gpVar = (gp) it.next();
            if (!arrayList.isEmpty()) {
                String str = ((gp) arrayList.get(arrayList.size() - 1)).f180a;
                Intrinsics.checkNotNull(gpVar);
                if (Intrinsics.areEqual(str, gpVar.f180a)) {
                    gp gpVar2 = (gp) arrayList.get(arrayList.size() - 1);
                    Intrinsics.checkNotNull(gpVar2);
                    float f = gpVar2.b;
                    Intrinsics.checkNotNull(gpVar);
                    gpVar2.b = RangesKt.coerceAtMost(f, gpVar.b);
                    gpVar2.e += gpVar.e;
                    ArrayList<GestureData> arrayList2 = gpVar2.c;
                    arrayList2.addAll(gpVar.c);
                    gpVar2.c = arrayList2;
                    ArrayList<ScreenAction> arrayList3 = gpVar2.d;
                    arrayList3.addAll(gpVar.d);
                    gpVar2.d = arrayList3;
                    gpVar2.f = gpVar.f;
                    gpVar2.g = gpVar.g;
                    arrayList.set(arrayList.size() - 1, gpVar2);
                } else {
                    List<String> listC = this.d.c();
                    Intrinsics.checkNotNull(listC);
                    if (listC.contains(gpVar.f180a)) {
                        String str2 = ((gp) arrayList.get(arrayList.size() - 1)).f180a;
                        Map<String, String> mapA = this.d.a();
                        Intrinsics.checkNotNull(mapA);
                        if (Intrinsics.areEqual(str2, mapA.get(gpVar.f180a))) {
                            gp gpVar3 = (gp) arrayList.get(arrayList.size() - 1);
                            Intrinsics.checkNotNull(gpVar3);
                            float f2 = gpVar3.b;
                            Intrinsics.checkNotNull(gpVar);
                            gpVar3.b = RangesKt.coerceAtMost(f2, gpVar.b);
                            gpVar3.e += gpVar.e;
                            ArrayList<GestureData> arrayList4 = gpVar3.c;
                            arrayList4.addAll(gpVar.c);
                            gpVar3.c = arrayList4;
                            ArrayList<ScreenAction> arrayList5 = gpVar3.d;
                            arrayList5.addAll(gpVar.d);
                            gpVar3.d = arrayList5;
                            gpVar3.f = gpVar.f;
                            gpVar3.g = gpVar.g;
                            arrayList.set(arrayList.size() - 1, gpVar3);
                        } else {
                            Map<String, String> mapA2 = this.d.a();
                            Intrinsics.checkNotNull(mapA2);
                            gpVar.f180a = mapA2.get(gpVar.f180a);
                            arrayList.add(gpVar);
                        }
                    } else {
                        arrayList.add(gpVar);
                    }
                }
            } else {
                Map<String, String> mapA3 = this.d.a();
                Intrinsics.checkNotNull(mapA3);
                Intrinsics.checkNotNull(gpVar);
                if (mapA3.containsKey(gpVar.f180a)) {
                    Map<String, String> mapA4 = this.d.a();
                    Intrinsics.checkNotNull(mapA4);
                    gpVar.f180a = mapA4.get(gpVar.f180a);
                }
                arrayList.add(gpVar);
            }
        }
        return arrayList;
    }

    @Override // com.uxcam.internals.ex
    public final String a(String str, String str2) {
        return this.d.a(str, str2);
    }

    @Override // com.uxcam.internals.ex
    public final void a(FragmentManager fragmentManager, Fragment fragment) {
        Intrinsics.checkNotNull(fragment);
        if (Intrinsics.areEqual(fragment.getClass().getSimpleName(), "NavHostFragment")) {
            return;
        }
        if (fragment instanceof DialogFragment) {
            this.d.i();
        }
        fd fdVar = this.d;
        String simpleName = fragment.getClass().getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "fragment.javaClass.simpleName");
        fdVar.b(simpleName);
        this.d.h();
        String simpleName2 = fragment.getClass().getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName2, "fragment.javaClass.simpleName");
        a(fragmentManager, simpleName2);
    }

    public static boolean a() throws ClassNotFoundException {
        try {
            Class.forName("androidx.fragment.app.Fragment");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public final void a(FragmentManager fragmentManager, String str) {
        boolean zB = this.b.b(fragmentManager);
        StringBuilder sb = new StringBuilder(this.d.c(this.c.a()));
        HashMap mapC = this.b.c(fragmentManager);
        String string = sb.toString();
        hf hfVar = new hf();
        hfVar.c = string;
        ArrayList arrayList = new ArrayList();
        Intrinsics.checkNotNull(fragmentManager);
        for (Fragment fragment : fragmentManager.getFragments()) {
            if (bi.D == null) {
                bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
            }
            bi biVar = bi.D;
            Intrinsics.checkNotNull(biVar);
            ck ckVarA = biVar.a();
            Intrinsics.checkNotNullExpressionValue(fragment, "fragment");
            ckVarA.getClass();
            FragmentManager fragmentManagerA = ck.a(fragment);
            if (fragmentManagerA != null) {
                List<Fragment> fragments = fragmentManagerA.getFragments();
                Intrinsics.checkNotNullExpressionValue(fragments, "childFragmentManager.fragments");
                arrayList.add(hh.a(fragments, fragment, mapC));
            }
        }
        hfVar.f190a = arrayList;
        if (this.b.a(fragmentManager)) {
            str = hfVar.toString();
        }
        sb.append("_").append(this.f144a.a(str));
        String string2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string2, "tagBuilder.toString()");
        if (zB) {
            this.d.a(string2);
        }
        this.c.a(string2);
        this.d.a(this.b.a(hfVar, string2));
    }
}
