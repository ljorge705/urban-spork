package androidx.test.espresso.idling;

import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import androidx.test.espresso.IdlingResource;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes5.dex */
public final class CountingIdlingResource implements IdlingResource {
    private static final String TAG = "CountingIdlingResource";
    private volatile long becameBusyAt;
    private volatile long becameIdleAt;
    private final AtomicInteger counter;
    private final boolean debugCounting;
    private volatile IdlingResource.ResourceCallback resourceCallback;
    private final String resourceName;

    @Override // androidx.test.espresso.IdlingResource
    public String getName() {
        return this.resourceName;
    }

    @Override // androidx.test.espresso.IdlingResource
    public void registerIdleTransitionCallback(IdlingResource.ResourceCallback resourceCallback) {
        this.resourceCallback = resourceCallback;
    }

    public CountingIdlingResource(String resourceName) {
        this(resourceName, false);
    }

    public CountingIdlingResource(String resourceName, boolean debugCounting) {
        this.counter = new AtomicInteger(0);
        this.becameBusyAt = 0L;
        this.becameIdleAt = 0L;
        if (TextUtils.isEmpty(resourceName)) {
            throw new IllegalArgumentException("resourceName cannot be empty or null!");
        }
        this.resourceName = resourceName;
        this.debugCounting = debugCounting;
    }

    @Override // androidx.test.espresso.IdlingResource
    public boolean isIdleNow() {
        return this.counter.get() == 0;
    }

    public void increment() {
        int andIncrement = this.counter.getAndIncrement();
        if (andIncrement == 0) {
            this.becameBusyAt = SystemClock.uptimeMillis();
        }
        if (this.debugCounting) {
            String str = this.resourceName;
            Log.i(TAG, new StringBuilder(String.valueOf(str).length() + 51).append("Resource: ").append(str).append(" in-use-count incremented to: ").append(andIncrement + 1).toString());
        }
    }

    public void decrement() {
        int iDecrementAndGet = this.counter.decrementAndGet();
        if (iDecrementAndGet == 0) {
            if (this.resourceCallback != null) {
                this.resourceCallback.onTransitionToIdle();
            }
            this.becameIdleAt = SystemClock.uptimeMillis();
        }
        if (this.debugCounting) {
            if (iDecrementAndGet == 0) {
                String str = this.resourceName;
                Log.i(TAG, new StringBuilder(String.valueOf(str).length() + 65).append("Resource: ").append(str).append(" went idle! (Time spent not idle: ").append(this.becameIdleAt - this.becameBusyAt).append(")").toString());
            } else {
                String str2 = this.resourceName;
                Log.i(TAG, new StringBuilder(String.valueOf(str2).length() + 51).append("Resource: ").append(str2).append(" in-use-count decremented to: ").append(iDecrementAndGet).toString());
            }
        }
        if (iDecrementAndGet <= -1) {
            throw new IllegalStateException(new StringBuilder(50).append("Counter has been corrupted! counterVal=").append(iDecrementAndGet).toString());
        }
    }

    public void dumpStateToLogs() {
        StringBuilder sbAppend = new StringBuilder("Resource: ").append(this.resourceName).append(" inflight transaction count: ").append(this.counter.get());
        if (0 == this.becameBusyAt) {
            Log.i(TAG, sbAppend.append(" and has never been busy!").toString());
            return;
        }
        sbAppend.append(" and was last busy at: ").append(this.becameBusyAt);
        if (0 == this.becameIdleAt) {
            Log.w(TAG, sbAppend.append(" AND NEVER WENT IDLE!").toString());
        } else {
            sbAppend.append(" and last went idle at: ").append(this.becameIdleAt);
            Log.i(TAG, sbAppend.toString());
        }
    }
}
