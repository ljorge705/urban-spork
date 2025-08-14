package io.sentry.util;

/* loaded from: classes6.dex */
public final class LazyEvaluator<T> {
    private final Evaluator<T> evaluator;
    private volatile T value = null;

    public interface Evaluator<T> {
        T evaluate();
    }

    public LazyEvaluator(Evaluator<T> evaluator) {
        this.evaluator = evaluator;
    }

    public T getValue() {
        if (this.value == null) {
            synchronized (this) {
                if (this.value == null) {
                    this.value = this.evaluator.evaluate();
                }
            }
        }
        return this.value;
    }

    public void setValue(T t) {
        synchronized (this) {
            this.value = t;
        }
    }

    public void resetValue() {
        synchronized (this) {
            this.value = null;
        }
    }
}
