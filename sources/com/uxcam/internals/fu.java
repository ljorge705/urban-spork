package com.uxcam.internals;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import com.uxcam.OnVerificationListener;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public interface fu {
    String a(Context context);

    void a(int i);

    void a(Activity activity);

    void a(Context context, String str);

    void a(Context context, boolean z);

    void a(OnVerificationListener onVerificationListener);

    void a(hc hcVar);

    void a(he heVar);

    void a(hx hxVar);

    void a(String str, Object obj);

    void a(boolean z);

    boolean a();

    void b(Activity activity);

    void b(OnVerificationListener onVerificationListener);

    void b(String str, Object obj);

    void b(boolean z);

    boolean b();

    boolean b(Context context);

    ArrayList<WeakReference<Activity>> c();

    void c(boolean z);

    Application.ActivityLifecycleCallbacks d();

    hx e();

    void f();

    boolean g();

    boolean h();

    he i();

    int j();
}
