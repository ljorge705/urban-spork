package androidx.camera.core.processing;

import androidx.core.util.Consumer;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public class Edge<T> implements Consumer<T> {
    private Consumer<T> mListener;

    public void setListener(Consumer<T> consumer) {
        this.mListener = consumer;
    }

    @Override // androidx.core.util.Consumer
    public void accept(T t) {
        Intrinsics.checkNotNull(this.mListener, "Listener is not set.");
        this.mListener.accept(t);
    }
}
