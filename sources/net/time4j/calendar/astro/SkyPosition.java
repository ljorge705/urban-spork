package net.time4j.calendar.astro;

import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* loaded from: classes4.dex */
final class SkyPosition implements EquatorialCoordinates {
    private final double dec;
    private final double ra;

    @Override // net.time4j.calendar.astro.EquatorialCoordinates
    public double getDeclination() {
        return this.dec;
    }

    @Override // net.time4j.calendar.astro.EquatorialCoordinates
    public double getRightAscension() {
        return this.ra;
    }

    SkyPosition(double d, double d2) {
        if (Double.isNaN(d) || Double.isInfinite(d) || Double.isNaN(d2) || Double.isInfinite(d2)) {
            throw new IllegalArgumentException("Not finite: " + d + "/" + d2);
        }
        this.ra = d;
        this.dec = d2;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SkyPosition)) {
            return false;
        }
        SkyPosition skyPosition = (SkyPosition) obj;
        return this.ra == skyPosition.ra && this.dec == skyPosition.dec;
    }

    public int hashCode() {
        return AstroUtils.hashCode(this.ra) + (AstroUtils.hashCode(this.dec) * 37);
    }

    public String toString() {
        return "RA/Dec=[" + this.ra + AbstractJsonLexerKt.COMMA + this.dec + AbstractJsonLexerKt.END_LIST;
    }
}
