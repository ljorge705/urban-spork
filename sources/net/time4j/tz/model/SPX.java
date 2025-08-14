package net.time4j.tz.model;

import com.drew.metadata.exif.makernotes.NikonType2MakernoteDirectory;
import com.drew.metadata.exif.makernotes.PanasonicMakernoteDirectory;
import com.google.common.base.Ascii;
import com.google.firebase.firestore.local.SQLitePersistence;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.Externalizable;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.InvalidObjectException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.time4j.Month;
import net.time4j.PlainTime;
import net.time4j.Weekday;
import net.time4j.base.MathUtils;
import net.time4j.tz.ZonalOffset;
import net.time4j.tz.ZonalTransition;
import okio.Utf8;

/* loaded from: classes4.dex */
final class SPX implements Externalizable {
    static final int ARRAY_TRANSITION_MODEL_TYPE = 126;
    static final int COMPOSITE_TRANSITION_MODEL_TYPE = 127;
    private static final long DAYS_IN_18_BITS = 22642848000L;
    static final int DAY_OF_WEEK_IN_MONTH_PATTERN_TYPE = 121;
    static final int FIXED_DAY_PATTERN_TYPE = 120;
    static final int LAST_WEEKDAY_PATTERN_TYPE = 122;
    private static final int NO_COMPRESSION = 0;
    private static final long POSIX_TIME_1825 = -4575744000L;
    private static final long QUARTERS_IN_24_BITS = 15040511099L;
    static final int RULE_BASED_TRANSITION_MODEL_TYPE = 125;
    private static final long serialVersionUID = 6526945678752534989L;
    private transient Object obj;
    private transient int type;

    private Object readResolve() throws ObjectStreamException {
        return this.obj;
    }

    private static int toTimeIndexR(int i) {
        if (i == 0) {
            return 1;
        }
        if (i == 3600) {
            return 2;
        }
        if (i == 7200) {
            return 3;
        }
        if (i == 10800) {
            return 4;
        }
        if (i == 79200) {
            return 5;
        }
        if (i != 82800) {
            return i != 86400 ? 0 : 7;
        }
        return 6;
    }

    private static int toTimeIndexT(int i) {
        if (i == 0) {
            return 1;
        }
        if (i == 60) {
            return 2;
        }
        if (i == 3600) {
            return 3;
        }
        if (i == 7200) {
            return 4;
        }
        if (i == 10800) {
            return 5;
        }
        if (i != 14400) {
            return i != 18000 ? 0 : 7;
        }
        return 6;
    }

    private static int toTimeOfDayR(int i) {
        switch (i) {
            case 1:
                return 0;
            case 2:
                return NikonType2MakernoteDirectory.TAG_NIKON_SCAN;
            case 3:
                return 7200;
            case 4:
                return 10800;
            case 5:
                return 79200;
            case 6:
                return 82800;
            case 7:
                return 86400;
            default:
                return -1;
        }
    }

    private static int toTimeOfDayT(int i) {
        switch (i) {
            case 1:
                return 0;
            case 2:
                return 60;
            case 3:
                return NikonType2MakernoteDirectory.TAG_NIKON_SCAN;
            case 4:
                return 7200;
            case 5:
                return 10800;
            case 6:
                return 14400;
            case 7:
                return 18000;
            default:
                return -1;
        }
    }

    public SPX() {
    }

    SPX(Object obj, int i) {
        this.obj = obj;
        this.type = i;
    }

    @Override // java.io.Externalizable
    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeByte(this.type);
        switch (this.type) {
            case 120:
                writeFixedDayPattern(this.obj, objectOutput);
                return;
            case 121:
                writeDayOfWeekInMonthPattern(this.obj, objectOutput);
                return;
            case LAST_WEEKDAY_PATTERN_TYPE /* 122 */:
                writeLastDayOfWeekPattern(this.obj, objectOutput);
                return;
            case 123:
            case PanasonicMakernoteDirectory.TAG_CLEAR_RETOUCH /* 124 */:
            default:
                throw new InvalidClassException("Unknown serialized type.");
            case 125:
                writeRuleBasedTransitionModel(this.obj, objectOutput);
                return;
            case 126:
                writeArrayTransitionModel(this.obj, objectOutput);
                return;
            case 127:
                writeCompositeTransitionModel(this.obj, objectOutput);
                return;
        }
    }

    @Override // java.io.Externalizable
    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        switch (objectInput.readByte()) {
            case 120:
                this.obj = readFixedDayPattern(objectInput);
                return;
            case 121:
                this.obj = readDayOfWeekInMonthPattern(objectInput);
                return;
            case LAST_WEEKDAY_PATTERN_TYPE /* 122 */:
                this.obj = readLastDayOfWeekPattern(objectInput);
                return;
            case 123:
            case PanasonicMakernoteDirectory.TAG_CLEAR_RETOUCH /* 124 */:
            default:
                throw new StreamCorruptedException("Unknown serialized type.");
            case 125:
                this.obj = readRuleBasedTransitionModel(objectInput);
                return;
            case 126:
                this.obj = readArrayTransitionModel(objectInput);
                return;
            case Byte.MAX_VALUE:
                this.obj = readCompositeTransitionModel(objectInput);
                return;
        }
    }

    static void writeTransitions(ZonalTransition[] zonalTransitionArr, int i, DataOutput dataOutput) throws IOException {
        int iMin = Math.min(i, zonalTransitionArr.length);
        dataOutput.writeInt(iMin);
        if (iMin > 0) {
            int previousOffset = zonalTransitionArr[0].getPreviousOffset();
            writeOffset(dataOutput, previousOffset);
            for (int i2 = 0; i2 < iMin; i2++) {
                previousOffset = writeTransition(zonalTransitionArr[i2], previousOffset, dataOutput);
            }
        }
    }

    private static List<ZonalTransition> readTransitions(ObjectInput objectInput) throws IOException {
        long j;
        int i;
        int offset;
        int i2 = objectInput.readInt();
        if (i2 == 0) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(i2);
        int offset2 = readOffset(objectInput);
        long j2 = Long.MIN_VALUE;
        int i3 = offset2;
        int i4 = 0;
        while (i4 < i2) {
            byte b = objectInput.readByte();
            boolean z = b < 0;
            int i5 = (b >>> 5) & 3;
            int timeOfDayT = toTimeOfDayT((b >>> 2) & 7);
            if (timeOfDayT == -1) {
                j = objectInput.readLong();
            } else {
                j = ((((((((b & 3) << 16) | ((objectInput.readByte() & 255) << 8)) | (objectInput.readByte() & 255)) * 86400) + POSIX_TIME_1825) + timeOfDayT) - 7200) - offset2;
            }
            if (j <= j2) {
                throw new StreamCorruptedException("Wrong order of transitions.");
            }
            if (i5 != 1) {
                if (i5 != 2) {
                    offset = i5 != 3 ? readOffset(objectInput) : 7200;
                } else {
                    offset = NikonType2MakernoteDirectory.TAG_NIKON_SCAN;
                }
                i = offset;
            } else {
                i = 0;
            }
            if (z) {
                offset2 = readOffset(objectInput);
            }
            int i6 = offset2 + (i == Integer.MAX_VALUE ? 0 : i);
            arrayList.add(new ZonalTransition(j, i3, i6, i));
            i4++;
            i3 = i6;
            j2 = j;
        }
        return arrayList;
    }

    private static void writeRules(List<DaylightSavingRule> list, ObjectOutput objectOutput) throws IOException {
        objectOutput.writeByte(list.size());
        for (DaylightSavingRule daylightSavingRule : list) {
            objectOutput.writeByte(daylightSavingRule.getType());
            switch (daylightSavingRule.getType()) {
                case 120:
                    writeFixedDayPattern(daylightSavingRule, objectOutput);
                    break;
                case 121:
                    writeDayOfWeekInMonthPattern(daylightSavingRule, objectOutput);
                    break;
                case LAST_WEEKDAY_PATTERN_TYPE /* 122 */:
                    writeLastDayOfWeekPattern(daylightSavingRule, objectOutput);
                    break;
                default:
                    objectOutput.writeObject(daylightSavingRule);
                    break;
            }
        }
    }

    private static List<DaylightSavingRule> readRules(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        DaylightSavingRule fixedDayPattern;
        byte b = objectInput.readByte();
        if (b == 0) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(b);
        DaylightSavingRule daylightSavingRule = null;
        int i = 0;
        while (i < b) {
            switch (objectInput.readByte()) {
                case 120:
                    fixedDayPattern = readFixedDayPattern(objectInput);
                    break;
                case 121:
                    fixedDayPattern = readDayOfWeekInMonthPattern(objectInput);
                    break;
                case LAST_WEEKDAY_PATTERN_TYPE /* 122 */:
                    fixedDayPattern = readLastDayOfWeekPattern(objectInput);
                    break;
                default:
                    fixedDayPattern = (DaylightSavingRule) objectInput.readObject();
                    break;
            }
            if (daylightSavingRule != null && RuleComparator.INSTANCE.compare(daylightSavingRule, fixedDayPattern) >= 0) {
                throw new InvalidObjectException("Order of daylight saving rules is not ascending.");
            }
            arrayList.add(fixedDayPattern);
            i++;
            daylightSavingRule = fixedDayPattern;
        }
        return arrayList;
    }

    private static void writeOffset(DataOutput dataOutput, int i) throws IOException {
        if (i % SQLitePersistence.MAX_ARGS == 0) {
            dataOutput.writeByte(i / SQLitePersistence.MAX_ARGS);
        } else {
            dataOutput.writeByte(127);
            dataOutput.writeInt(i);
        }
    }

    private static int readOffset(DataInput dataInput) throws IOException {
        byte b = dataInput.readByte();
        return b == Byte.MAX_VALUE ? dataInput.readInt() : b * 900;
    }

    private static int readSavings(int i) {
        int i2 = i / 3;
        if (i2 == 0) {
            return 0;
        }
        if (i2 != 1) {
            return i2 != 2 ? i2 != 3 ? -1 : 7200 : NikonType2MakernoteDirectory.TAG_NIKON_SCAN;
        }
        return 1800;
    }

    private static int toTimeOfDay(GregorianTimezoneRule gregorianTimezoneRule) {
        return gregorianTimezoneRule.getTimeOfDay().getInt(PlainTime.SECOND_OF_DAY) + MathUtils.safeCast(gregorianTimezoneRule.getDayOverflow() * 86400);
    }

    private static void writeFixedDayPattern(Object obj, DataOutput dataOutput) throws IOException {
        FixedDayPattern fixedDayPattern = (FixedDayPattern) obj;
        boolean zWriteMonthIndicatorOffset = writeMonthIndicatorOffset(fixedDayPattern, dataOutput);
        int dayOfMonth = fixedDayPattern.getDayOfMonth() << 3;
        int timeOfDay = toTimeOfDay(fixedDayPattern);
        int timeIndexR = toTimeIndexR(timeOfDay);
        dataOutput.writeByte((dayOfMonth | timeIndexR) & 255);
        if (!zWriteMonthIndicatorOffset) {
            writeOffset(dataOutput, fixedDayPattern.getSavings());
        }
        if (timeIndexR == 0) {
            dataOutput.writeInt(timeOfDay);
        }
    }

    private static DaylightSavingRule readFixedDayPattern(DataInput dataInput) throws IOException {
        byte b = dataInput.readByte();
        int i = (b & 255) >>> 4;
        int i2 = b & 15;
        OffsetIndicator offsetIndicator = OffsetIndicator.VALUES[i2 % 3];
        int savings = readSavings(i2);
        byte b2 = dataInput.readByte();
        int i3 = (b2 & 255) >>> 3;
        int timeOfDayR = toTimeOfDayR(b2 & 7);
        if (savings == -1) {
            savings = readOffset(dataInput);
        }
        return new FixedDayPattern(Month.valueOf(i), i3, timeOfDayR == -1 ? dataInput.readInt() : timeOfDayR, offsetIndicator, savings);
    }

    private static void writeDayOfWeekInMonthPattern(Object obj, DataOutput dataOutput) throws IOException {
        int i;
        DayOfWeekInMonthPattern dayOfWeekInMonthPattern = (DayOfWeekInMonthPattern) obj;
        boolean zWriteMonthIndicatorOffset = writeMonthIndicatorOffset(dayOfWeekInMonthPattern, dataOutput);
        dataOutput.writeByte(((dayOfWeekInMonthPattern.getDayOfMonth() << 3) | dayOfWeekInMonthPattern.getDayOfWeek()) & 255);
        boolean z = false;
        int i2 = dayOfWeekInMonthPattern.isAfter() ? 128 : 0;
        int timeOfDay = toTimeOfDay(dayOfWeekInMonthPattern);
        if (timeOfDay % 1800 == 0) {
            i = i2 | (timeOfDay / 1800);
            z = true;
        } else {
            i = i2 | 63;
        }
        dataOutput.writeByte(i & 255);
        if (!zWriteMonthIndicatorOffset) {
            writeOffset(dataOutput, dayOfWeekInMonthPattern.getSavings());
        }
        if (z) {
            return;
        }
        dataOutput.writeInt(timeOfDay);
    }

    private static DaylightSavingRule readDayOfWeekInMonthPattern(DataInput dataInput) throws IOException {
        byte b = dataInput.readByte();
        Month monthValueOf = Month.valueOf((b & 255) >>> 4);
        int i = b & 15;
        OffsetIndicator offsetIndicator = OffsetIndicator.VALUES[i % 3];
        int savings = readSavings(i);
        byte b2 = dataInput.readByte();
        int i2 = (b2 & 255) >>> 3;
        Weekday weekdayValueOf = Weekday.valueOf(b2 & 7);
        byte b3 = dataInput.readByte();
        boolean z = ((b3 & 255) >>> 7) == 1;
        int i3 = b3 & Utf8.REPLACEMENT_BYTE;
        if (savings == -1) {
            savings = readOffset(dataInput);
        }
        return new DayOfWeekInMonthPattern(monthValueOf, i2, weekdayValueOf, i3 == 63 ? dataInput.readInt() : i3 * 1800, offsetIndicator, savings, z);
    }

    private static void writeLastDayOfWeekPattern(Object obj, DataOutput dataOutput) throws IOException {
        int i;
        boolean z;
        LastWeekdayPattern lastWeekdayPattern = (LastWeekdayPattern) obj;
        boolean zWriteMonthIndicatorOffset = writeMonthIndicatorOffset(lastWeekdayPattern, dataOutput);
        int dayOfWeek = lastWeekdayPattern.getDayOfWeek() << 5;
        int timeOfDay = toTimeOfDay(lastWeekdayPattern);
        if (timeOfDay % NikonType2MakernoteDirectory.TAG_NIKON_SCAN == 0) {
            i = dayOfWeek | (timeOfDay / NikonType2MakernoteDirectory.TAG_NIKON_SCAN);
            z = true;
        } else {
            i = dayOfWeek | 31;
            z = false;
        }
        dataOutput.writeByte(i & 255);
        if (!zWriteMonthIndicatorOffset) {
            writeOffset(dataOutput, lastWeekdayPattern.getSavings());
        }
        if (z) {
            return;
        }
        dataOutput.writeInt(timeOfDay);
    }

    private static DaylightSavingRule readLastDayOfWeekPattern(DataInput dataInput) throws IOException {
        byte b = dataInput.readByte();
        Month monthValueOf = Month.valueOf((b & 255) >>> 4);
        int i = b & 15;
        OffsetIndicator offsetIndicator = OffsetIndicator.VALUES[i % 3];
        int savings = readSavings(i);
        byte b2 = dataInput.readByte();
        Weekday weekdayValueOf = Weekday.valueOf((b2 & 255) >>> 5);
        int i2 = b2 & Ascii.US;
        if (savings == -1) {
            savings = readOffset(dataInput);
        }
        return new LastWeekdayPattern(monthValueOf, weekdayValueOf, i2 == 31 ? dataInput.readInt() : i2 * NikonType2MakernoteDirectory.TAG_NIKON_SCAN, offsetIndicator, savings);
    }

    private static void writeRuleBasedTransitionModel(Object obj, ObjectOutput objectOutput) throws IOException {
        RuleBasedTransitionModel ruleBasedTransitionModel = (RuleBasedTransitionModel) obj;
        ZonalTransition initialTransition = ruleBasedTransitionModel.getInitialTransition();
        long posixTime = initialTransition.getPosixTime();
        if (posixTime >= POSIX_TIME_1825 && posixTime < 10464767099L && posixTime % 900 == 0) {
            int i = (int) ((posixTime - POSIX_TIME_1825) / 900);
            objectOutput.writeByte((i >>> 16) & 255);
            objectOutput.writeByte((i >>> 8) & 255);
            objectOutput.writeByte(i & 255);
        } else {
            objectOutput.writeByte(255);
            objectOutput.writeLong(initialTransition.getPosixTime());
        }
        writeOffset(objectOutput, initialTransition.getPreviousOffset());
        writeOffset(objectOutput, initialTransition.getTotalOffset());
        writeOffset(objectOutput, initialTransition.getDaylightSavingOffset());
        writeRules(ruleBasedTransitionModel.getRules(), objectOutput);
    }

    private static Object readRuleBasedTransitionModel(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        long j;
        if ((objectInput.readByte() & 255) == 255) {
            j = objectInput.readLong();
        } else {
            j = (((r0 << 16) + ((objectInput.readByte() & 255) << 8) + (255 & objectInput.readByte())) * 900) + POSIX_TIME_1825;
        }
        return new RuleBasedTransitionModel(new ZonalTransition(j, readOffset(objectInput), readOffset(objectInput), readOffset(objectInput)), readRules(objectInput), false);
    }

    private static void writeArrayTransitionModel(Object obj, ObjectOutput objectOutput) throws IOException {
        ((ArrayTransitionModel) obj).writeTransitions(objectOutput);
    }

    private static Object readArrayTransitionModel(ObjectInput objectInput) throws IOException {
        return new ArrayTransitionModel(readTransitions(objectInput), false, false);
    }

    private static void writeCompositeTransitionModel(Object obj, ObjectOutput objectOutput) throws IOException {
        CompositeTransitionModel compositeTransitionModel = (CompositeTransitionModel) obj;
        compositeTransitionModel.writeTransitions(objectOutput);
        writeRules(compositeTransitionModel.getRules(), objectOutput);
    }

    private static Object readCompositeTransitionModel(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        List<ZonalTransition> transitions = readTransitions(objectInput);
        return TransitionModel.of(ZonalOffset.ofTotalSeconds(transitions.get(0).getPreviousOffset()), transitions, readRules(objectInput), false, false);
    }

    private static int writeTransition(ZonalTransition zonalTransition, int i, DataOutput dataOutput) throws IOException {
        int standardOffset = zonalTransition.getStandardOffset();
        int timeIndexT = 0;
        boolean z = standardOffset != i;
        byte b = z ? (byte) 128 : (byte) 0;
        int daylightSavingOffset = zonalTransition.getDaylightSavingOffset();
        int i2 = daylightSavingOffset != 0 ? daylightSavingOffset != 3600 ? daylightSavingOffset != 7200 ? 0 : 3 : 2 : 1;
        byte b2 = (byte) (b | (i2 << 5));
        long posixTime = zonalTransition.getPosixTime() + i;
        long j = 7200 + posixTime;
        if (j >= POSIX_TIME_1825 && j < 18067104000L) {
            timeIndexT = toTimeIndexT(MathUtils.floorModulo(j, 86400));
        }
        byte b3 = (byte) ((timeIndexT << 2) | b2);
        if (timeIndexT == 0) {
            dataOutput.writeByte(b3);
            dataOutput.writeLong(zonalTransition.getPosixTime());
        } else {
            int i3 = (int) ((posixTime + 4575751200L) / 86400);
            dataOutput.writeByte((byte) (b3 | ((byte) ((i3 >>> 16) & 3))));
            dataOutput.writeByte((i3 >>> 8) & 255);
            dataOutput.writeByte(i3 & 255);
        }
        if (i2 == 0) {
            writeOffset(dataOutput, daylightSavingOffset);
        }
        if (z) {
            writeOffset(dataOutput, standardOffset);
        }
        return standardOffset;
    }

    private static boolean writeMonthIndicatorOffset(GregorianTimezoneRule gregorianTimezoneRule, DataOutput dataOutput) throws IOException {
        int i;
        int monthValue = gregorianTimezoneRule.getMonthValue() << 4;
        int iOrdinal = gregorianTimezoneRule.getIndicator().ordinal();
        int savings = gregorianTimezoneRule.getSavings();
        boolean z = true;
        if (savings == 0) {
            i = monthValue | iOrdinal;
        } else {
            if (savings == 1800) {
                iOrdinal += 3;
            } else if (savings == 3600) {
                iOrdinal += 6;
            } else if (savings != 7200) {
                i = monthValue | (iOrdinal + 12);
                z = false;
            } else {
                iOrdinal += 9;
            }
            i = monthValue | iOrdinal;
        }
        dataOutput.writeByte(i & 255);
        return z;
    }
}
