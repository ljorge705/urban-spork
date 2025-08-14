package net.time4j.engine;

/* loaded from: classes4.dex */
public interface Normalizer<U> {
    TimeSpan<U> normalize(TimeSpan<? extends U> timeSpan);
}
