package io.sentry.util;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes6.dex */
public final class Random implements Serializable {
    static final String BadBound = "bound must be positive";
    private static final double DOUBLE_UNIT = 1.1102230246251565E-16d;
    private static final long addend = 11;
    private static final long mask = 281474976710655L;
    private static final long multiplier = 25214903917L;
    private static final AtomicLong seedUniquifier = new AtomicLong(8682522807148012L);
    private static final long serialVersionUID = 3905348978240129619L;
    private final AtomicLong seed;

    private static long initialScramble(long j) {
        return (j ^ multiplier) & mask;
    }

    public Random() {
        this(seedUniquifier() ^ System.nanoTime());
    }

    private static long seedUniquifier() {
        AtomicLong atomicLong;
        long j;
        long j2;
        do {
            atomicLong = seedUniquifier;
            j = atomicLong.get();
            j2 = 1181783497276652981L * j;
        } while (!atomicLong.compareAndSet(j, j2));
        return j2;
    }

    public Random(long j) {
        if (getClass() == Random.class) {
            this.seed = new AtomicLong(initialScramble(j));
        } else {
            this.seed = new AtomicLong();
            setSeed(j);
        }
    }

    public synchronized void setSeed(long j) {
        this.seed.set(initialScramble(j));
    }

    private int next(int i) {
        long j;
        long j2;
        AtomicLong atomicLong = this.seed;
        do {
            j = atomicLong.get();
            j2 = ((multiplier * j) + addend) & mask;
        } while (!atomicLong.compareAndSet(j, j2));
        return (int) (j2 >>> (48 - i));
    }

    public void nextBytes(byte[] bArr) {
        int length = bArr.length;
        int i = 0;
        while (i < length) {
            int iNextInt = nextInt();
            int iMin = Math.min(length - i, 4);
            while (true) {
                int i2 = iMin - 1;
                if (iMin > 0) {
                    bArr[i] = (byte) iNextInt;
                    iNextInt >>= 8;
                    i++;
                    iMin = i2;
                }
            }
        }
    }

    final long internalNextLong(long j, long j2) {
        long jNextLong = nextLong();
        if (j >= j2) {
            return jNextLong;
        }
        long j3 = j2 - j;
        long j4 = j3 - 1;
        if ((j3 & j4) == 0) {
            return (jNextLong & j4) + j;
        }
        if (j3 > 0) {
            while (true) {
                long j5 = jNextLong >>> 1;
                long j6 = j5 + j4;
                long j7 = j5 % j3;
                if (j6 - j7 >= 0) {
                    return j7 + j;
                }
                jNextLong = nextLong();
            }
        } else {
            while (true) {
                if (jNextLong >= j && jNextLong < j2) {
                    return jNextLong;
                }
                jNextLong = nextLong();
            }
        }
    }

    final int internalNextInt(int i, int i2) {
        if (i >= i2) {
            return nextInt();
        }
        int i3 = i2 - i;
        if (i3 > 0) {
            return nextInt(i3) + i;
        }
        while (true) {
            int iNextInt = nextInt();
            if (iNextInt >= i && iNextInt < i2) {
                return iNextInt;
            }
        }
    }

    final double internalNextDouble(double d, double d2) {
        double dNextDouble = nextDouble();
        if (d >= d2) {
            return dNextDouble;
        }
        double d3 = (dNextDouble * (d2 - d)) + d;
        return d3 >= d2 ? Double.longBitsToDouble(Double.doubleToLongBits(d2) - 1) : d3;
    }

    public int nextInt() {
        return next(32);
    }

    public int nextInt(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException(BadBound);
        }
        int next = next(31);
        int i2 = i - 1;
        if ((i & i2) == 0) {
            return (int) ((i * next) >> 31);
        }
        while (true) {
            int i3 = next % i;
            if ((next - i3) + i2 >= 0) {
                return i3;
            }
            next = next(31);
        }
    }

    public long nextLong() {
        return (next(32) << 32) + next(32);
    }

    public boolean nextBoolean() {
        return next(1) != 0;
    }

    public float nextFloat() {
        return next(24) / 1.6777216E7f;
    }

    public double nextDouble() {
        return ((next(26) << 27) + next(27)) * DOUBLE_UNIT;
    }
}
