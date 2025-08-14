package androidx.camera.core.impl.utils;

/* loaded from: classes.dex */
final class LongRational {
    private final long mDenominator;
    private final long mNumerator;

    long getDenominator() {
        return this.mDenominator;
    }

    long getNumerator() {
        return this.mNumerator;
    }

    double toDouble() {
        return this.mNumerator / this.mDenominator;
    }

    LongRational(long j, long j2) {
        this.mNumerator = j;
        this.mDenominator = j2;
    }

    LongRational(double d) {
        this((long) (d * 10000.0d), 10000L);
    }

    public String toString() {
        return this.mNumerator + "/" + this.mDenominator;
    }
}
