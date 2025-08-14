package net.time4j.sql;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import net.time4j.ClockUnit;
import net.time4j.Moment;
import net.time4j.PlainDate;
import net.time4j.PlainTime;
import net.time4j.PlainTimestamp;
import net.time4j.TemporalType;
import net.time4j.base.MathUtils;
import net.time4j.engine.ChronoElement;
import net.time4j.engine.ChronoException;
import net.time4j.engine.EpochDays;
import net.time4j.scale.TimeScale;
import net.time4j.tz.Timezone;

/* loaded from: classes4.dex */
public abstract class JDBCAdapter<S, T> extends TemporalType<S, T> {
    public static final JDBCAdapter<Date, PlainDate> SQL_DATE;
    public static final JDBCAdapter<Time, PlainTime> SQL_TIME;
    public static final JDBCAdapter<Timestamp, PlainTimestamp> SQL_TIMESTAMP;
    public static final JDBCAdapter<Timestamp, Moment> SQL_TIMESTAMP_WITH_ZONE;
    private static final boolean WITH_SQL_UTC_CONVERSION = Boolean.getBoolean("net.time4j.sql.utc.conversion");
    private static final PlainDate UNIX_DATE = PlainDate.of(0, EpochDays.UNIX);

    static {
        SQL_DATE = new SqlDateRule();
        SQL_TIME = new SqlTimeRule();
        SQL_TIMESTAMP = new SqlTimestampRule();
        SQL_TIMESTAMP_WITH_ZONE = new SqlMomentRule();
    }

    private JDBCAdapter() {
    }

    private static class SqlDateRule extends JDBCAdapter<Date, PlainDate> {
        private SqlDateRule() {
            super();
        }

        @Override // net.time4j.TemporalType, net.time4j.engine.Converter
        public PlainDate translate(Date date) {
            long time = date.getTime();
            if (!JDBCAdapter.WITH_SQL_UTC_CONVERSION) {
                time += Timezone.ofSystem().getOffset(Moment.of(MathUtils.floorDivide(time, 1000), TimeScale.POSIX)).getIntegralAmount() * 1000;
            }
            return (PlainDate) PlainDate.axis().getCalendarSystem().transform(MathUtils.floorDivide(time, 86400000) - 730);
        }

        @Override // net.time4j.TemporalType, net.time4j.engine.Converter
        public Date from(PlainDate plainDate) {
            int year = plainDate.getYear();
            if (year < 1900 || year > 9999) {
                throw new ChronoException("SQL-Date is only defined in year range of 1900-9999.");
            }
            long jSafeMultiply = MathUtils.safeMultiply(((Long) plainDate.get(EpochDays.UNIX)).longValue(), 86400000L);
            if (!JDBCAdapter.WITH_SQL_UTC_CONVERSION) {
                jSafeMultiply -= Timezone.ofSystem().getOffset(plainDate, PlainTime.of(0)).getIntegralAmount() * 1000;
            }
            return new Date(jSafeMultiply);
        }

        @Override // net.time4j.engine.Converter
        public Class<Date> getSourceType() {
            return Date.class;
        }
    }

    private static class SqlTimeRule extends JDBCAdapter<Time, PlainTime> {
        private SqlTimeRule() {
            super();
        }

        @Override // net.time4j.TemporalType, net.time4j.engine.Converter
        public PlainTime translate(Time time) {
            long time2 = time.getTime();
            if (!JDBCAdapter.WITH_SQL_UTC_CONVERSION) {
                time2 += Timezone.ofSystem().getOffset(Moment.of(MathUtils.floorDivide(time2, 1000), TimeScale.POSIX)).getIntegralAmount() * 1000;
            }
            return (PlainTime) PlainTime.midnightAtStartOfDay().with((ChronoElement<Integer>) PlainTime.MILLI_OF_DAY, MathUtils.floorModulo(time2, 86400000));
        }

        @Override // net.time4j.TemporalType, net.time4j.engine.Converter
        public Time from(PlainTime plainTime) {
            long integralAmount = plainTime.getInt(PlainTime.MILLI_OF_DAY);
            if (!JDBCAdapter.WITH_SQL_UTC_CONVERSION) {
                integralAmount -= Timezone.ofSystem().getOffset(JDBCAdapter.UNIX_DATE, plainTime).getIntegralAmount() * 1000;
            }
            return new Time(integralAmount);
        }

        @Override // net.time4j.engine.Converter
        public Class<Time> getSourceType() {
            return Time.class;
        }
    }

    private static class SqlTimestampRule extends JDBCAdapter<Timestamp, PlainTimestamp> {
        private SqlTimestampRule() {
            super();
        }

        @Override // net.time4j.TemporalType, net.time4j.engine.Converter
        public PlainTimestamp translate(Timestamp timestamp) {
            long time = timestamp.getTime();
            if (!JDBCAdapter.WITH_SQL_UTC_CONVERSION) {
                time += Timezone.ofSystem().getOffset(Moment.of(MathUtils.floorDivide(time, 1000), TimeScale.POSIX)).getIntegralAmount() * 1000;
            }
            return (PlainTimestamp) PlainTimestamp.of(PlainDate.of(MathUtils.floorDivide(time, 86400000), EpochDays.UNIX), PlainTime.of(0).plus(MathUtils.floorModulo(time, 86400000), ClockUnit.MILLIS)).with((ChronoElement<Integer>) PlainTime.NANO_OF_SECOND, timestamp.getNanos());
        }

        @Override // net.time4j.TemporalType, net.time4j.engine.Converter
        public Timestamp from(PlainTimestamp plainTimestamp) {
            long jSafeMultiply = MathUtils.safeMultiply(((Long) plainTimestamp.getCalendarDate().get(EpochDays.UNIX)).longValue(), 86400000L);
            long integralAmount = plainTimestamp.getInt(PlainTime.MILLI_OF_DAY);
            if (!JDBCAdapter.WITH_SQL_UTC_CONVERSION) {
                integralAmount -= Timezone.ofSystem().getOffset(plainTimestamp, plainTimestamp).getIntegralAmount() * 1000;
            }
            Timestamp timestamp = new Timestamp(MathUtils.safeAdd(jSafeMultiply, integralAmount));
            timestamp.setNanos(plainTimestamp.getInt(PlainTime.NANO_OF_SECOND));
            return timestamp;
        }

        @Override // net.time4j.engine.Converter
        public Class<Timestamp> getSourceType() {
            return Timestamp.class;
        }
    }

    private static class SqlMomentRule extends JDBCAdapter<Timestamp, Moment> {
        private SqlMomentRule() {
            super();
        }

        @Override // net.time4j.TemporalType, net.time4j.engine.Converter
        public Moment translate(Timestamp timestamp) {
            try {
                return Moment.of(MathUtils.floorDivide(timestamp.getTime(), 1000), timestamp.getNanos(), TimeScale.POSIX);
            } catch (IllegalArgumentException e) {
                throw new ChronoException(e.getMessage(), e);
            }
        }

        @Override // net.time4j.TemporalType, net.time4j.engine.Converter
        public Timestamp from(Moment moment) {
            Timestamp timestamp = new Timestamp(MathUtils.safeMultiply(moment.getPosixTime(), 1000L));
            timestamp.setNanos(moment.getNanosecond());
            return timestamp;
        }

        @Override // net.time4j.engine.Converter
        public Class<Timestamp> getSourceType() {
            return Timestamp.class;
        }
    }
}
