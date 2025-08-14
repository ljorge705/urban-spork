package com.uxcam.internals;

import android.util.Pair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes6.dex */
public final class fc implements fb {

    /* renamed from: a, reason: collision with root package name */
    public int f145a;
    public String b;
    public Boolean c = Boolean.FALSE;
    public final ArrayList d = new ArrayList();
    public final ArrayList e = new ArrayList();
    public final ArrayList f = new ArrayList();
    public final ArrayList g = new ArrayList();
    public final ArrayList h = new ArrayList();
    public final ArrayList i = new ArrayList();
    public final HashMap j = new HashMap();
    public final HashMap k = new HashMap();
    public final ArrayList l = new ArrayList();
    public String m = "";

    @Override // com.uxcam.internals.fb
    public final HashMap a() {
        return this.k;
    }

    @Override // com.uxcam.internals.fb
    public final void a(int i) {
        this.f145a = i;
    }

    @Override // com.uxcam.internals.fb
    public final void a(Boolean bool) {
        this.c = bool;
    }

    @Override // com.uxcam.internals.fb
    public final void b(String str) {
        this.b = str;
    }

    @Override // com.uxcam.internals.fb
    public final ArrayList c() {
        return this.h;
    }

    @Override // com.uxcam.internals.fb
    public final void c(String str) {
        this.m = str;
    }

    @Override // com.uxcam.internals.fb
    public final void c(List<String> screenTagName) {
        Intrinsics.checkNotNullParameter(screenTagName, "screenTagName");
        this.e.addAll(screenTagName);
    }

    @Override // com.uxcam.internals.fb
    public final void d() {
        this.e.clear();
        this.f.clear();
        this.d.clear();
        if (!this.g.isEmpty()) {
            Boolean bool = this.c;
            Intrinsics.checkNotNull(bool);
            if (bool.booleanValue()) {
                return;
            }
            this.g.clear();
        }
    }

    @Override // com.uxcam.internals.fb
    public final int e() {
        return this.f145a;
    }

    @Override // com.uxcam.internals.fb
    public final ArrayList f() {
        return this.i;
    }

    @Override // com.uxcam.internals.fb
    public final ArrayList g() {
        return this.f;
    }

    @Override // com.uxcam.internals.fb
    public final ArrayList h() {
        return this.l;
    }

    @Override // com.uxcam.internals.fb
    public final Boolean i() {
        return this.c;
    }

    @Override // com.uxcam.internals.fb
    public final ArrayList j() {
        return this.g;
    }

    @Override // com.uxcam.internals.fb
    public final ArrayList k() {
        return this.e;
    }

    @Override // com.uxcam.internals.fb
    public final void l() {
        this.h.clear();
        this.k.clear();
    }

    @Override // com.uxcam.internals.fb
    public final void m() {
        this.d.clear();
        this.e.clear();
        this.f.clear();
        this.g.clear();
    }

    @Override // com.uxcam.internals.fb
    public final void n() {
    }

    @Override // com.uxcam.internals.fb
    public final void o() {
        if (!this.f.isEmpty()) {
            new Pair(this.f.get(r1.size() - 1), this.m);
        }
    }

    @Override // com.uxcam.internals.fb
    public final HashMap p() {
        return this.j;
    }

    @Override // com.uxcam.internals.fb
    public final String q() {
        return this.m;
    }

    @Override // com.uxcam.internals.fb
    public final void b(List<String> screenTagName) {
        Intrinsics.checkNotNullParameter(screenTagName, "screenTagName");
        this.f.addAll(screenTagName);
    }

    @Override // com.uxcam.internals.fb
    public final void e(String str) {
        this.j.put(this.b, str);
    }

    @Override // com.uxcam.internals.fb
    public final void a(List<String> screenTagName) {
        Intrinsics.checkNotNullParameter(screenTagName, "screenTagName");
        this.g.addAll(screenTagName);
    }

    @Override // com.uxcam.internals.fb
    public final void b() {
        this.g.clear();
    }

    @Override // com.uxcam.internals.fb
    public final void a(hg hgVar) {
        this.l.add(hgVar);
    }

    @Override // com.uxcam.internals.fb
    public final void a(String str) {
        this.i.add(str);
    }

    @Override // com.uxcam.internals.fb
    public final void d(String str) {
        this.k.put(str, this.m);
    }

    @Override // com.uxcam.internals.fb
    public final void a(ArrayList ignoreList) {
        Intrinsics.checkNotNullParameter(ignoreList, "ignoreList");
        this.h.addAll(ignoreList);
    }
}
