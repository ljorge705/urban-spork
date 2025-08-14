package com.uxcam.internals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes6.dex */
public final class fe implements fd {

    /* renamed from: a, reason: collision with root package name */
    public final fb f146a;
    public boolean b;
    public boolean c;
    public final ArrayList d;
    public final ArrayList e;
    public final ArrayList f;

    public fe(fc stateHolder) {
        Intrinsics.checkNotNullParameter(stateHolder, "stateHolder");
        this.f146a = stateHolder;
        this.d = new ArrayList();
        this.e = new ArrayList();
        this.f = new ArrayList();
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0076  */
    @Override // com.uxcam.internals.fd
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(java.lang.String r2, boolean r3, java.lang.String r4) {
        /*
            r1 = this;
            com.uxcam.internals.fb r0 = r1.f146a
            r0.n()
            boolean r0 = r1.c
            if (r0 == 0) goto L20
            com.uxcam.internals.fb r0 = r1.f146a
            java.util.ArrayList r0 = r0.g()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            boolean r4 = kotlin.collections.CollectionsKt.contains(r0, r4)
            if (r4 != 0) goto L1d
            com.uxcam.internals.fb r4 = r1.f146a
            r4.m()
        L1d:
            r4 = 0
            r1.c = r4
        L20:
            com.uxcam.internals.fb r4 = r1.f146a
            r4.i()
            if (r3 == 0) goto Lab
            com.uxcam.internals.fb r3 = r1.f146a
            java.util.ArrayList r3 = r3.g()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            boolean r3 = r3.isEmpty()
            if (r3 == 0) goto L96
            com.uxcam.internals.fb r3 = r1.f146a
            java.util.ArrayList r3 = r3.k()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            boolean r3 = r3.isEmpty()
            if (r3 != 0) goto L76
            com.uxcam.internals.bi r3 = com.uxcam.internals.bi.D
            if (r3 != 0) goto L5c
            com.uxcam.internals.bi r3 = new com.uxcam.internals.bi
            com.uxcam.screenshot.di.ScreenshotModule$Companion r4 = com.uxcam.screenshot.di.ScreenshotModule.INSTANCE
            com.uxcam.screenshot.di.ScreenshotModule r4 = r4.getInstance()
            com.uxcam.screenaction.di.ScreenActionModule$Companion r0 = com.uxcam.screenaction.di.ScreenActionModule.INSTANCE
            com.uxcam.screenaction.di.ScreenActionModule r0 = r0.getInstance()
            r3.<init>(r4, r0)
            com.uxcam.internals.bi.D = r3
        L5c:
            com.uxcam.internals.bi r3 = com.uxcam.internals.bi.D
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            com.uxcam.internals.gc r3 = r3.g()
            com.uxcam.internals.gd r3 = (com.uxcam.internals.gd) r3
            boolean r3 = r3.f170a
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            boolean r3 = r3.booleanValue()
            if (r3 != 0) goto L96
        L76:
            com.uxcam.internals.fb r3 = r1.f146a
            java.lang.Boolean r3 = r3.i()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            boolean r3 = r3.booleanValue()
            if (r3 != 0) goto L96
            com.uxcam.internals.fb r3 = r1.f146a
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            java.util.List r4 = kotlin.collections.CollectionsKt.listOf(r2)
            r3.c(r4)
            com.uxcam.internals.fb r3 = r1.f146a
            r3.c(r2)
        L96:
            com.uxcam.internals.fb r3 = r1.f146a
            java.lang.Boolean r3 = r3.i()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            boolean r3 = r3.booleanValue()
            if (r3 == 0) goto Lda
            com.uxcam.internals.fb r3 = r1.f146a
            r3.e(r2)
            goto Lda
        Lab:
            com.uxcam.internals.fb r3 = r1.f146a
            java.util.ArrayList r3 = r3.g()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            boolean r3 = r3.isEmpty()
            if (r3 == 0) goto Lda
            com.uxcam.internals.fb r3 = r1.f146a
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            java.util.List r4 = kotlin.collections.CollectionsKt.listOf(r2)
            r3.b(r4)
            com.uxcam.internals.fb r3 = r1.f146a
            java.util.ArrayList r3 = r3.k()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            boolean r3 = r3.isEmpty()
            if (r3 == 0) goto Lda
            com.uxcam.internals.fb r3 = r1.f146a
            r3.c(r2)
        Lda:
            com.uxcam.internals.fb r3 = r1.f146a
            java.util.ArrayList r3 = r3.g()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            boolean r3 = kotlin.collections.CollectionsKt.contains(r3, r2)
            if (r3 != 0) goto Lfa
            com.uxcam.internals.fb r3 = r1.f146a
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            java.util.List r4 = kotlin.collections.CollectionsKt.listOf(r2)
            r3.a(r4)
            com.uxcam.internals.fb r3 = r1.f146a
            r3.c(r2)
        Lfa:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uxcam.internals.fe.a(java.lang.String, boolean, java.lang.String):void");
    }

    @Override // com.uxcam.internals.fd
    public final void b(String fragmentName) {
        Intrinsics.checkNotNullParameter(fragmentName, "fragmentName");
        this.f146a.b(fragmentName);
    }

    @Override // com.uxcam.internals.fd
    public final String c(String str) {
        String str2;
        ArrayList arrayListK = this.f146a.k();
        return (arrayListK == null || (str2 = (String) CollectionsKt.getOrNull(arrayListK, 0)) == null) ? str == null ? "" : str : str2;
    }

    @Override // com.uxcam.internals.fd
    public final void d() {
        this.f146a.b(this.e);
        this.f146a.c(this.d);
        this.f146a.a((List<String>) this.f);
        this.c = true;
        HashMap mapA = this.f146a.a();
        Intrinsics.checkNotNull(mapA);
        if (true ^ mapA.isEmpty()) {
            this.f146a.l();
            ArrayList arrayList = new ArrayList();
            ArrayList arrayListK = this.f146a.k();
            Intrinsics.checkNotNull(arrayListK);
            arrayList.addAll(arrayListK);
            ArrayList arrayListG = this.f146a.g();
            Intrinsics.checkNotNull(arrayListG);
            arrayList.addAll(arrayListG);
            ArrayList arrayListJ = this.f146a.j();
            Intrinsics.checkNotNull(arrayListJ);
            arrayList.addAll(arrayListJ);
            this.f146a.a(arrayList);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                this.f146a.d((String) it.next());
            }
        }
    }

    @Override // com.uxcam.internals.fd
    public final String e() {
        String strQ = this.f146a.q();
        Intrinsics.checkNotNull(strQ);
        return strQ;
    }

    @Override // com.uxcam.internals.fd
    public final List<hg> f() {
        return this.f146a.h();
    }

    @Override // com.uxcam.internals.fd
    public final void g() {
        this.f146a.o();
        ArrayList arrayList = new ArrayList();
        if (!this.b) {
            ArrayList arrayListG = this.f146a.g();
            Intrinsics.checkNotNull(arrayListG);
            arrayList.addAll(arrayListG);
            ArrayList arrayListK = this.f146a.k();
            Intrinsics.checkNotNull(arrayListK);
            arrayList.addAll(arrayListK);
            this.b = true;
        }
        ArrayList arrayList2 = new ArrayList(arrayList);
        if ((!arrayList2.isEmpty()) && arrayList2.size() > 1) {
            this.f146a.a(arrayList2);
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                this.f146a.d((String) it.next());
            }
        }
        this.b = false;
        this.e.clear();
        this.d.clear();
        ArrayList arrayList3 = this.e;
        ArrayList arrayListG2 = this.f146a.g();
        Intrinsics.checkNotNull(arrayListG2);
        arrayList3.addAll(arrayListG2);
        ArrayList arrayList4 = this.d;
        ArrayList arrayListK2 = this.f146a.k();
        Intrinsics.checkNotNull(arrayListK2);
        arrayList4.addAll(arrayListK2);
        this.f146a.d();
    }

    @Override // com.uxcam.internals.fd
    public final void h() {
        this.f146a.a(Boolean.TRUE);
        this.f146a.a(this.f146a.e() + 1);
    }

    @Override // com.uxcam.internals.fd
    public final void i() {
        this.f146a.a(Boolean.FALSE);
        this.f146a.a(this.f146a.e() - 1);
        if (this.f146a.e() == 0) {
            b();
        }
    }

    @Override // com.uxcam.internals.fd
    public final void b() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayListJ = this.f146a.j();
        Intrinsics.checkNotNull(arrayListJ);
        arrayList.addAll(arrayListJ);
        ArrayList arrayList2 = new ArrayList();
        if (!this.b) {
            ArrayList arrayListG = this.f146a.g();
            Intrinsics.checkNotNull(arrayListG);
            arrayList2.addAll(arrayListG);
            ArrayList arrayListK = this.f146a.k();
            Intrinsics.checkNotNull(arrayListK);
            arrayList2.addAll(arrayListK);
            this.b = true;
        }
        arrayList.addAll(arrayList2);
        if (arrayList.size() > 0) {
            this.f146a.a(arrayList);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                this.f146a.d((String) it.next());
            }
        }
        this.f.clear();
        ArrayList arrayList3 = this.f;
        ArrayList arrayListJ2 = this.f146a.j();
        Intrinsics.checkNotNull(arrayListJ2);
        arrayList3.addAll(arrayListJ2);
        this.f146a.b();
    }

    @Override // com.uxcam.internals.fd
    public final List<String> c() {
        return this.f146a.c();
    }

    @Override // com.uxcam.internals.fd
    public final Map<String, String> a() {
        return this.f146a.a();
    }

    @Override // com.uxcam.internals.fd
    public final String a(String str, String str2) {
        ArrayList arrayListF = this.f146a.f();
        Intrinsics.checkNotNull(arrayListF);
        if (arrayListF.contains(str)) {
            return str2;
        }
        HashMap mapP = this.f146a.p();
        Intrinsics.checkNotNull(mapP);
        if (!mapP.containsKey(str2)) {
            return str2;
        }
        HashMap mapP2 = this.f146a.p();
        Intrinsics.checkNotNull(mapP2);
        return (String) mapP2.get(str2);
    }

    @Override // com.uxcam.internals.fd
    public final void a(String str) {
        this.f146a.a(str);
    }

    @Override // com.uxcam.internals.fd
    public final void a(hg hgVar) {
        this.f146a.a(hgVar);
    }

    @Override // com.uxcam.internals.fd
    public final boolean a(TreeSet activitiesToIgnore) {
        Intrinsics.checkNotNullParameter(activitiesToIgnore, "activitiesToIgnore");
        Iterator it = activitiesToIgnore.iterator();
        while (it.hasNext()) {
            if (Intrinsics.areEqual((String) it.next(), e())) {
                return true;
            }
        }
        return false;
    }
}
