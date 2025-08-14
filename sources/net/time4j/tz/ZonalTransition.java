package net.time4j.tz;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* loaded from: classes4.dex */
public final class ZonalTransition implements Comparable<ZonalTransition>, Serializable {
    private static final long serialVersionUID = 4594838367057225304L;
    private final int dst;
    private final long posix;
    private final int previous;
    private final int total;

    public int getDaylightSavingOffset() {
        int i = this.dst;
        if (i == Integer.MAX_VALUE) {
            return 0;
        }
        return i;
    }

    public long getPosixTime() {
        return this.posix;
    }

    public int getPreviousOffset() {
        return this.previous;
    }

    public int getSize() {
        return this.total - this.previous;
    }

    public int getTotalOffset() {
        return this.total;
    }

    public int hashCode() {
        long j = this.posix;
        return (int) (j ^ (j >>> 32));
    }

    public boolean isGap() {
        return this.total > this.previous;
    }

    public boolean isOverlap() {
        return this.total < this.previous;
    }

    public ZonalTransition(long j, int i, int i2, int i3) {
        this.posix = j;
        this.previous = i;
        this.total = i2;
        this.dst = i3;
        checkRange(i);
        checkRange(i2);
        checkDST(i3);
    }

    public int getStandardOffset() {
        return this.total - getDaylightSavingOffset();
    }

    @Override // java.lang.Comparable
    public int compareTo(ZonalTransition zonalTransition) {
        long daylightSavingOffset = this.posix - zonalTransition.posix;
        if (daylightSavingOffset == 0) {
            daylightSavingOffset = this.previous - zonalTransition.previous;
            if (daylightSavingOffset == 0) {
                daylightSavingOffset = this.total - zonalTransition.total;
                if (daylightSavingOffset == 0) {
                    daylightSavingOffset = getDaylightSavingOffset() - zonalTransition.getDaylightSavingOffset();
                    if (daylightSavingOffset == 0) {
                        return 0;
                    }
                }
            }
        }
        return daylightSavingOffset < 0 ? -1 : 1;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ZonalTransition)) {
            return false;
        }
        ZonalTransition zonalTransition = (ZonalTransition) obj;
        return this.posix == zonalTransition.posix && this.previous == zonalTransition.previous && this.total == zonalTransition.total && getDaylightSavingOffset() == zonalTransition.getDaylightSavingOffset();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("[POSIX=");
        sb.append(this.posix);
        sb.append(", previous-offset=");
        sb.append(this.previous);
        sb.append(", total-offset=");
        sb.append(this.total);
        sb.append(", dst-offset=");
        sb.append(getDaylightSavingOffset());
        sb.append(AbstractJsonLexerKt.END_LIST);
        return sb.toString();
    }

    private static void checkRange(int i) {
        if (i < -64800 || i > 64800) {
            throw new IllegalArgumentException("Offset out of range: " + i);
        }
    }

    private static void checkDST(int i) {
        if (i != Integer.MAX_VALUE) {
            checkRange(i);
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        try {
            checkRange(this.previous);
            checkRange(this.total);
            checkDST(this.dst);
        } catch (IllegalArgumentException e) {
            throw new InvalidObjectException(e.getMessage());
        }
    }
}
