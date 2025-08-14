package n.o.t.i.f.e.e;

import org.greenrobot.eventbus.EventBus;

/* loaded from: classes4.dex */
public class f {
    public static final f b = new f();

    /* renamed from: a, reason: collision with root package name */
    public EventBus f306a = EventBus.builder().build();

    public static void a(Object obj) {
        b.f306a.post(obj);
    }

    public static void b(Object obj) {
        b.f306a.postSticky(obj);
    }

    public static void c(Object obj) {
        b.f306a.register(obj);
    }
}
