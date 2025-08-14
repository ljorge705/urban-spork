package net.time4j.calendar.astro;

/* loaded from: classes4.dex */
public enum Twilight {
    BLUE_HOUR(4.0d),
    CIVIL(6.0d),
    NAUTICAL(12.0d),
    ASTRONOMICAL(18.0d);

    private final transient double angle;

    double getAngle() {
        return this.angle;
    }

    Twilight(double d) {
        this.angle = d;
    }
}
