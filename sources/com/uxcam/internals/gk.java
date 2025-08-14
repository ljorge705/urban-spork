package com.uxcam.internals;

import java.util.ArrayList;

/* loaded from: classes6.dex */
public final class gk {

    /* renamed from: a, reason: collision with root package name */
    public static final ArrayList f176a = new ArrayList();
    public static volatile ab[] b = new ab[0];
    public static final aa c = new aa();

    public static void a() {
        c.getClass();
    }

    public class aa extends ab {
        @Override // com.uxcam.internals.gk.ab
        public final void a(String str, Object... objArr) {
            for (ab abVar : gk.b) {
                abVar.a(str, objArr);
            }
        }

        @Override // com.uxcam.internals.gk.ab
        public final void a(int i, String str, String str2) {
            throw new AssertionError("Missing override for log method.");
        }
    }

    public static aa a(String str) {
        for (ab abVar : b) {
            abVar.f177a.set(str);
        }
        return c;
    }

    public static abstract class ab {

        /* renamed from: a, reason: collision with root package name */
        public final ThreadLocal<String> f177a = new ThreadLocal<>();

        public abstract void a(int i, String str, String str2);

        public final void a(int i, String str, Object... objArr) {
            String str2 = this.f177a.get();
            if (str2 != null) {
                this.f177a.remove();
            }
            if (str != null && str.length() == 0) {
                str = null;
            }
            if (str == null) {
                return;
            }
            if (objArr.length > 0) {
                str = String.format(str, objArr);
            }
            a(i, str2, str);
        }

        public void a(String str, Object... objArr) {
            a(4, str, objArr);
        }
    }
}
