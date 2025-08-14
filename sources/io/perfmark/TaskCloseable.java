package io.perfmark;

import java.io.Closeable;

/* loaded from: classes6.dex */
public final class TaskCloseable implements Closeable {
    static final TaskCloseable INSTANCE = new TaskCloseable();

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        PerfMark.stopTask();
    }

    private TaskCloseable() {
    }
}
