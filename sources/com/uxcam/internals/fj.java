package com.uxcam.internals;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes6.dex */
public final class fj {

    /* renamed from: a, reason: collision with root package name */
    public String f152a = "[ #event# ]";
    public HashMap<String, String> b = new HashMap<>();

    public final fj b(String str) {
        a("EXCEPTION");
        a("site_of_error", str);
        return this;
    }

    public final fj a(String str, String str2) {
        if (this.b == null) {
            this.b = new HashMap<>();
        }
        this.b.put(str, str2);
        return this;
    }

    public final fj a(String str) {
        this.f152a = this.f152a.replace("#event#", str);
        return this;
    }

    public final void a(int i) {
        if (i == 2) {
            ht.a(this.f152a, (Map<String, String>) this.b);
            return;
        }
        if (i == 4) {
            ht.c(this.f152a, this.b);
        } else if (i == 1) {
            ht.b(this.f152a, this.b);
        } else if (i == 3) {
            ht.a(this.f152a, (HashMap) this.b);
        }
    }
}
