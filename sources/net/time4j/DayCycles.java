package net.time4j;

import java.io.Serializable;

/* loaded from: classes4.dex */
public final class DayCycles implements Serializable {
    private static final long serialVersionUID = -4124961309622141228L;
    private final long days;
    private final PlainTime time;

    public long getDayOverflow() {
        return this.days;
    }

    public PlainTime getWallTime() {
        return this.time;
    }

    DayCycles(long j, PlainTime plainTime) {
        this.days = j;
        this.time = plainTime;
    }
}
