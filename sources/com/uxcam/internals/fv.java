package com.uxcam.internals;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import com.uxcam.OnVerificationListener;
import com.uxcam.screenaction.utils.Util;
import io.sentry.TraceContext;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

/* loaded from: classes6.dex */
public final class fv implements fu {

    /* renamed from: a, reason: collision with root package name */
    public int f163a;
    public boolean c;
    public boolean f;
    public boolean g;
    public Application.ActivityLifecycleCallbacks h;
    public boolean j;
    public he k;
    public final fq b = new fq();
    public final CopyOnWriteArrayList d = new CopyOnWriteArrayList();
    public hx e = new hx();
    public final ArrayList<WeakReference<Activity>> i = new ArrayList<>();

    @Override // com.uxcam.internals.fu
    public final String a(Context context) {
        SharedPreferences sharedPreferences = context != null ? context.getSharedPreferences("UXCamPreferences", 0) : null;
        return sharedPreferences == null ? "" : sharedPreferences.getString(TraceContext.JsonKeys.USER_ID, null);
    }

    @Override // com.uxcam.internals.fu
    public final void a(int i) {
        this.f163a = i;
    }

    @Override // com.uxcam.internals.fu
    public final void a(hc hcVar) {
        this.h = hcVar;
    }

    @Override // com.uxcam.internals.fu
    public final void a(he heVar) {
        this.k = heVar;
    }

    @Override // com.uxcam.internals.fu
    public final void a(hx user) {
        Intrinsics.checkNotNullParameter(user, "user");
        this.e = user;
    }

    @Override // com.uxcam.internals.fu
    public final void a(boolean z) {
        this.c = z;
    }

    @Override // com.uxcam.internals.fu
    public final boolean a() {
        return this.j;
    }

    @Override // com.uxcam.internals.fu
    public final void b(boolean z) {
        this.f = z;
    }

    @Override // com.uxcam.internals.fu
    public final boolean b() {
        return this.f;
    }

    @Override // com.uxcam.internals.fu
    public final boolean b(Context context) {
        SharedPreferences sharedPreferences = context != null ? context.getSharedPreferences("UXCamPreferences", 0) : null;
        return sharedPreferences != null && sharedPreferences.getBoolean("opt_out", false);
    }

    @Override // com.uxcam.internals.fu
    public final ArrayList<WeakReference<Activity>> c() {
        return this.i;
    }

    @Override // com.uxcam.internals.fu
    public final void c(boolean z) {
        this.j = z;
    }

    @Override // com.uxcam.internals.fu
    public final Application.ActivityLifecycleCallbacks d() {
        return this.h;
    }

    @Override // com.uxcam.internals.fu
    public final hx e() {
        return this.e;
    }

    @Override // com.uxcam.internals.fu
    public final void f() {
        this.g = true;
    }

    @Override // com.uxcam.internals.fu
    public final boolean g() {
        return this.g;
    }

    @Override // com.uxcam.internals.fu
    public final boolean h() {
        return this.c;
    }

    @Override // com.uxcam.internals.fu
    public final he i() {
        return this.k;
    }

    @Override // com.uxcam.internals.fu
    public final int j() {
        return this.f163a;
    }

    public final void k() {
        fq fqVar = this.b;
        fqVar.getClass();
        fqVar.f159a = new HashMap();
    }

    @Override // com.uxcam.internals.fu
    public final void b(OnVerificationListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.d.add(listener);
    }

    @Override // com.uxcam.internals.fu
    public final void a(OnVerificationListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.d.remove(listener);
    }

    @Override // com.uxcam.internals.fu
    public final void b(String str, Object obj) {
        if (str == null) {
            return;
        }
        if (Util.getCurrentUxcamTime(fp.f157n) > 0.0f) {
            this.b.f159a.put(str, obj);
        } else {
            gk.a("UXCam.setSessionProperty()").a("UXCam:setSessionProperty: No session is being recorded - session property ‘%s’ will be ignored", str);
        }
    }

    @Override // com.uxcam.internals.fu
    public final void a(String str, Object obj) {
        if (str == null) {
            return;
        }
        this.e.b.put(str, obj);
    }

    @Override // com.uxcam.internals.fu
    public final void a(Context context, boolean z) {
        new eg(context).a("opt_out", z);
    }

    @Override // com.uxcam.internals.fu
    public final void a(Context context, String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        new eg(context).a(TraceContext.JsonKeys.USER_ID, id);
    }

    @Override // com.uxcam.internals.fu
    public final void b(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.i.add(new WeakReference<>(activity));
    }

    @Override // com.uxcam.internals.fu
    public final void a(Activity activity) {
        Object next;
        Intrinsics.checkNotNullParameter(activity, "activity");
        Iterator<T> it = this.i.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            } else {
                next = it.next();
                if (Intrinsics.areEqual(((WeakReference) next).get(), activity)) {
                    break;
                }
            }
        }
        TypeIntrinsics.asMutableCollection(this.i).remove((WeakReference) next);
    }
}
