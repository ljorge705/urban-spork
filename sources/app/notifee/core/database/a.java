package app.notifee.core.database;

import android.content.Context;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.List;
import java.util.concurrent.Callable;
import n.o.t.i.f.e.e.o;
import n.o.t.i.f.e.e.q;

/* loaded from: classes5.dex */
public class a {
    public static a b;

    /* renamed from: a, reason: collision with root package name */
    public o f48a;

    public a(Context context) {
        this.f48a = NotifeeCoreDatabase.a(context).a();
    }

    public static a a(Context context) {
        a aVar;
        synchronized (a.class) {
            if (b == null) {
                b = new a(context);
            }
            aVar = b;
        }
        return aVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(q qVar) {
        this.f48a.a(qVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c(String str) {
        this.f48a.a(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ q d(String str) throws Exception {
        return this.f48a.b(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c() {
        this.f48a.b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ List d() throws Exception {
        return this.f48a.a();
    }

    public Task<q> b(final String str) {
        return Tasks.call(NotifeeCoreDatabase.b, new Callable() { // from class: app.notifee.core.database.a$$ExternalSyntheticLambda7
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.d(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ List b(Boolean bool) throws Exception {
        return this.f48a.a(bool);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c(q qVar) {
        this.f48a.b(qVar);
    }

    public void d(final q qVar) {
        NotifeeCoreDatabase.b.execute(new Runnable() { // from class: app.notifee.core.database.a$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.c(qVar);
            }
        });
    }

    public Task<List<q>> b() {
        return Tasks.call(NotifeeCoreDatabase.b, new Callable() { // from class: app.notifee.core.database.a$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.d();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(List list) {
        this.f48a.a((List<String>) list);
    }

    public void a(final q qVar) {
        NotifeeCoreDatabase.b.execute(new Runnable() { // from class: app.notifee.core.database.a$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.b(qVar);
            }
        });
    }

    public Task<List<q>> a(final Boolean bool) {
        return Tasks.call(NotifeeCoreDatabase.b, new Callable() { // from class: app.notifee.core.database.a$$ExternalSyntheticLambda5
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.b(bool);
            }
        });
    }

    public void a(final String str) {
        NotifeeCoreDatabase.b.execute(new Runnable() { // from class: app.notifee.core.database.a$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.c(str);
            }
        });
    }

    public void a(final List<String> list) {
        NotifeeCoreDatabase.b.execute(new Runnable() { // from class: app.notifee.core.database.a$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.b(list);
            }
        });
    }

    public void a() {
        NotifeeCoreDatabase.b.execute(new Runnable() { // from class: app.notifee.core.database.a$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.c();
            }
        });
    }
}
