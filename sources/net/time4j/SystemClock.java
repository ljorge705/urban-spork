package net.time4j;

import androidx.media3.common.C;
import java.util.Iterator;
import net.time4j.base.MathUtils;
import net.time4j.base.ResourceLoader;
import net.time4j.base.TimeSource;
import net.time4j.scale.LeapSeconds;
import net.time4j.scale.TickProvider;
import net.time4j.scale.TimeScale;
import net.time4j.tz.TZID;
import net.time4j.tz.Timezone;

/* loaded from: classes4.dex */
public final class SystemClock implements TimeSource<Moment> {
    public static final SystemClock INSTANCE;
    private static final int MIO = 1000000;
    public static final SystemClock MONOTONIC;
    private static final boolean MONOTON_MODE;
    private static final int MRD = 1000000000;
    private static final TickProvider PROVIDER;
    private final boolean monotonic;
    private final long offset;

    static {
        TickProvider stdTickProvider;
        String property = System.getProperty("java.vm.name");
        Iterator it = ResourceLoader.getInstance().services(TickProvider.class).iterator();
        while (true) {
            if (!it.hasNext()) {
                stdTickProvider = null;
                break;
            } else {
                stdTickProvider = (TickProvider) it.next();
                if (property.equals(stdTickProvider.getPlatform())) {
                    break;
                }
            }
        }
        if (stdTickProvider == null) {
            stdTickProvider = new StdTickProvider();
        }
        PROVIDER = stdTickProvider;
        MONOTON_MODE = Boolean.getBoolean("net.time4j.systemclock.nanoTime");
        INSTANCE = new SystemClock(false, calibrate());
        MONOTONIC = new SystemClock(true, calibrate());
    }

    private SystemClock(boolean z, long j) {
        this.monotonic = z;
        this.offset = j;
    }

    @Override // net.time4j.base.TimeSource
    public Moment currentTime() {
        if ((this.monotonic || MONOTON_MODE) && LeapSeconds.getInstance().isEnabled()) {
            long jUtcNanos = utcNanos();
            return Moment.of(MathUtils.floorDivide(jUtcNanos, 1000000000), MathUtils.floorModulo(jUtcNanos, 1000000000), TimeScale.UTC);
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        return Moment.of(MathUtils.floorDivide(jCurrentTimeMillis, 1000), MathUtils.floorModulo(jCurrentTimeMillis, 1000) * 1000000, TimeScale.POSIX);
    }

    public long currentTimeInMillis() {
        if (this.monotonic || MONOTON_MODE) {
            return MathUtils.safeMultiply(LeapSeconds.getInstance().strip(MathUtils.floorDivide(utcNanos(), 1000000000)), 1000L) + MathUtils.floorModulo(r0, 1000000);
        }
        return System.currentTimeMillis();
    }

    public long currentTimeInMicros() {
        if (this.monotonic || MONOTON_MODE) {
            return MathUtils.safeMultiply(LeapSeconds.getInstance().strip(MathUtils.floorDivide(utcNanos(), 1000000000)), 1000000L) + MathUtils.floorModulo(r0, 1000);
        }
        return MathUtils.safeMultiply(System.currentTimeMillis(), 1000L);
    }

    public long realTimeInMicros() {
        if (this.monotonic || MONOTON_MODE) {
            return MathUtils.floorDivide(utcNanos(), 1000);
        }
        return MathUtils.safeMultiply(LeapSeconds.getInstance().enhance(MathUtils.floorDivide(System.currentTimeMillis(), 1000)), 1000000L) + (MathUtils.floorModulo(r2, 1000) * 1000);
    }

    public static ZonalClock inPlatformView() {
        return new ZonalClock(INSTANCE, Timezone.ofPlatform());
    }

    public static ZonalClock inLocalView() {
        return ZonalClock.ofSystem();
    }

    public static ZonalClock inZonalView(TZID tzid) {
        return new ZonalClock(INSTANCE, tzid);
    }

    public static ZonalClock inZonalView(String str) {
        return new ZonalClock(INSTANCE, str);
    }

    public static Moment currentMoment() {
        return INSTANCE.currentTime();
    }

    public SystemClock recalibrated() {
        return new SystemClock(this.monotonic, calibrate());
    }

    public SystemClock synchronizedWith(TimeSource<?> timeSource) {
        return new SystemClock(this.monotonic, MathUtils.safeSubtract(MathUtils.safeMultiply(Moment.from(timeSource.currentTime()).getElapsedTime(TimeScale.UTC), C.NANOS_PER_SECOND) + r7.getNanosecond(TimeScale.UTC), MONOTON_MODE ? System.nanoTime() : PROVIDER.getNanos()));
    }

    private static long calibrate() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        long jNanoTime = 0;
        int i = 0;
        while (i < 10) {
            jNanoTime = MONOTON_MODE ? System.nanoTime() : PROVIDER.getNanos();
            long jCurrentTimeMillis2 = System.currentTimeMillis();
            if (jCurrentTimeMillis == jCurrentTimeMillis2) {
                break;
            }
            i++;
            jCurrentTimeMillis = jCurrentTimeMillis2;
        }
        return MathUtils.safeSubtract(MathUtils.safeMultiply(LeapSeconds.getInstance().enhance(MathUtils.floorDivide(jCurrentTimeMillis, 1000)), C.NANOS_PER_SECOND) + (MathUtils.floorModulo(jCurrentTimeMillis, 1000) * 1000000), jNanoTime);
    }

    private long utcNanos() {
        return MathUtils.safeAdd(MONOTON_MODE ? System.nanoTime() : PROVIDER.getNanos(), this.offset);
    }

    private static class StdTickProvider implements TickProvider {
        @Override // net.time4j.scale.TickProvider
        public String getPlatform() {
            return "";
        }

        private StdTickProvider() {
        }

        @Override // net.time4j.scale.TickProvider
        public long getNanos() {
            return System.nanoTime();
        }
    }
}
