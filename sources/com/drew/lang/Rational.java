package com.drew.lang;

import java.io.Serializable;

/* loaded from: classes5.dex */
public class Rational extends Number implements Comparable<Rational>, Serializable {
    private static final long serialVersionUID = 510688928138848770L;
    private final long _denominator;
    private final long _numerator;

    @Override // java.lang.Number
    public double doubleValue() {
        long j = this._numerator;
        if (j == 0) {
            return 0.0d;
        }
        return j / this._denominator;
    }

    @Override // java.lang.Number
    public float floatValue() {
        long j = this._numerator;
        if (j == 0) {
            return 0.0f;
        }
        return j / this._denominator;
    }

    public final long getDenominator() {
        return this._denominator;
    }

    public final long getNumerator() {
        return this._numerator;
    }

    public int hashCode() {
        return (((int) this._denominator) * 23) + ((int) this._numerator);
    }

    public boolean isZero() {
        return this._numerator == 0 || this._denominator == 0;
    }

    public Rational(long j, long j2) {
        this._numerator = j;
        this._denominator = j2;
    }

    @Override // java.lang.Number
    public final byte byteValue() {
        return (byte) doubleValue();
    }

    @Override // java.lang.Number
    public final int intValue() {
        return (int) doubleValue();
    }

    @Override // java.lang.Number
    public final long longValue() {
        return (long) doubleValue();
    }

    @Override // java.lang.Number
    public final short shortValue() {
        return (short) doubleValue();
    }

    public Rational getReciprocal() {
        return new Rational(this._denominator, this._numerator);
    }

    public boolean isInteger() {
        long j = this._denominator;
        return j == 1 || (j != 0 && this._numerator % j == 0) || (j == 0 && this._numerator == 0);
    }

    public String toString() {
        return this._numerator + "/" + this._denominator;
    }

    public String toSimpleString(boolean z) {
        if (this._denominator == 0 && this._numerator != 0) {
            return toString();
        }
        if (isInteger()) {
            return Integer.toString(intValue());
        }
        long j = this._numerator;
        if (j != 1) {
            long j2 = this._denominator;
            if (j2 % j == 0) {
                return new Rational(1L, j2 / j).toSimpleString(z);
            }
        }
        Rational simplifiedInstance = getSimplifiedInstance();
        if (z) {
            String string = Double.toString(simplifiedInstance.doubleValue());
            if (string.length() < 5) {
                return string;
            }
        }
        return simplifiedInstance.toString();
    }

    @Override // java.lang.Comparable
    public int compareTo(Rational rational) {
        return Double.compare(doubleValue(), rational.doubleValue());
    }

    public boolean equals(Rational rational) {
        return rational.doubleValue() == doubleValue();
    }

    public boolean equalsExact(Rational rational) {
        return getDenominator() == rational.getDenominator() && getNumerator() == rational.getNumerator();
    }

    public boolean equals(Object obj) {
        return obj != null && (obj instanceof Rational) && doubleValue() == ((Rational) obj).doubleValue();
    }

    public Rational getSimplifiedInstance() {
        long jGCD = GCD(this._numerator, this._denominator);
        return new Rational(this._numerator / jGCD, this._denominator / jGCD);
    }

    private static long GCD(long j, long j2) {
        if (j < 0) {
            j = -j;
        }
        if (j2 < 0) {
            j2 = -j2;
        }
        while (j != 0 && j2 != 0) {
            if (j > j2) {
                j %= j2;
            } else {
                j2 %= j;
            }
        }
        return j == 0 ? j2 : j;
    }
}
