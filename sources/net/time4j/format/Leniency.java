package net.time4j.format;

/* loaded from: classes4.dex */
public enum Leniency {
    STRICT,
    SMART,
    LAX;

    public boolean isLax() {
        return this == LAX;
    }

    public boolean isSmart() {
        return this == SMART;
    }

    public boolean isStrict() {
        return this == STRICT;
    }
}
