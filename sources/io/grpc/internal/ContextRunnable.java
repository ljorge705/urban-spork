package io.grpc.internal;

import io.grpc.Context;

/* loaded from: classes6.dex */
abstract class ContextRunnable implements Runnable {
    private final Context context;

    public abstract void runInContext();

    protected ContextRunnable(Context context) {
        this.context = context;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Context contextAttach = this.context.attach();
        try {
            runInContext();
        } finally {
            this.context.detach(contextAttach);
        }
    }
}
