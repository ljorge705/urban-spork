package net.time4j.base;

import net.time4j.base.UnixTime;

/* loaded from: classes4.dex */
public interface TimeSource<T extends UnixTime> {
    T currentTime();
}
