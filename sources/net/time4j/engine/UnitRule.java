package net.time4j.engine;

/* loaded from: classes4.dex */
public interface UnitRule<T> {
    T addTo(T t, long j);

    long between(T t, T t2);
}
