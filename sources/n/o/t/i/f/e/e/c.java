package n.o.t.i.f.e.e;

import android.graphics.Color;
import android.os.Bundle;

/* loaded from: classes4.dex */
public class c {

    /* renamed from: a, reason: collision with root package name */
    public final Bundle f303a;

    public c(Bundle bundle) {
        this.f303a = bundle;
    }

    public Integer a() {
        if (this.f303a.containsKey("lightColor")) {
            return Integer.valueOf(Color.parseColor(this.f303a.getString("lightColor")));
        }
        return null;
    }
}
