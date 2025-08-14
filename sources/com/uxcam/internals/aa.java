package com.uxcam.internals;

import android.os.Debug;
import android.os.Handler;
import android.os.Looper;
import android.util.Pair;
import io.sentry.protocol.SentryThread;
import java.util.Objects;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes6.dex */
public final class aa extends Thread {
    public static final C0191aa k = new C0191aa();
    public static Pair<JSONArray, String> l;
    public final int b;
    public final int c;

    /* renamed from: a, reason: collision with root package name */
    public final Handler f72a = new Handler(Looper.getMainLooper());
    public ac d = k;
    public volatile long e = 0;
    public volatile boolean f = false;
    public long g = 0;
    public long h = 0;
    public final ab i = new ab();
    public boolean j = true;

    /* renamed from: com.uxcam.internals.aa$aa, reason: collision with other inner class name */
    public class C0191aa implements ac {
        @Override // com.uxcam.internals.aa.ac
        public final void a(Pair<JSONArray, String> pair, long j) {
        }
    }

    public class ab implements Runnable {
        public ab() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            aa.this.e = 0L;
            aa.this.f = false;
            aa.this.h = System.currentTimeMillis() - aa.this.g;
        }
    }

    public interface ac {
        void a(Pair<JSONArray, String> pair, long j);
    }

    public aa(int i, int i2) {
        this.b = i;
        this.c = i2;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() throws JSONException, InterruptedException {
        setName("|ANR-Ticker|");
        long j = this.b;
        while (!isInterrupted() && this.j) {
            boolean z = this.e == 0;
            this.e += j;
            if (z) {
                this.g = System.currentTimeMillis();
                this.f72a.post(this.i);
            }
            try {
                Thread.sleep(j);
                if (this.e != 0 && !this.f) {
                    this.f = true;
                    Pair<JSONArray, String> pairA = hv.a(SentryThread.JsonKeys.MAIN, true);
                    l = pairA;
                    Objects.toString(pairA);
                }
                if (this.c < this.h) {
                    if (Debug.isDebuggerConnected() || Debug.waitingForDebugger()) {
                        this.f = true;
                    } else {
                        this.d.a(l, this.h);
                        j = this.b;
                        this.f = true;
                        this.h = 0L;
                    }
                }
            } catch (InterruptedException unused) {
                return;
            }
        }
    }
}
