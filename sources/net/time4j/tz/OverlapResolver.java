package net.time4j.tz;

/* loaded from: classes4.dex */
public enum OverlapResolver {
    EARLIER_OFFSET,
    LATER_OFFSET;

    public TransitionStrategy and(GapResolver gapResolver) {
        return gapResolver.and(this);
    }
}
