package net.time4j;

import com.google.common.base.Ascii;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.Externalizable;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import net.time4j.DayPeriod;
import net.time4j.engine.TimeSpan;
import net.time4j.scale.TimeScale;

/* loaded from: classes4.dex */
final class SPX implements Externalizable {
    static final int DATE_TYPE = 1;
    static final int DAY_PERIOD_TYPE = 7;
    static final int DURATION_TYPE = 6;
    static final int MACHINE_TIME_TYPE = 5;
    static final int MOMENT_TYPE = 4;
    static final int TIMESTAMP_TYPE = 8;
    static final int TIME_TYPE = 2;
    static final int WEEKMODEL_TYPE = 3;
    private static final long serialVersionUID = 1;
    private transient Object obj;
    private transient int type;

    private Object readResolve() throws ObjectStreamException {
        return this.obj;
    }

    public SPX() {
    }

    SPX(Object obj, int i) {
        this.obj = obj;
        this.type = i;
    }

    @Override // java.io.Externalizable
    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        switch (this.type) {
            case 1:
                writeDate(objectOutput);
                return;
            case 2:
                writeTime(objectOutput);
                return;
            case 3:
                writeWeekmodel(objectOutput);
                return;
            case 4:
                writeMoment(objectOutput);
                return;
            case 5:
                writeMachineTime(objectOutput);
                return;
            case 6:
                writeDuration(objectOutput);
                return;
            case 7:
                writeDayPeriod(objectOutput);
                return;
            case 8:
                writeTimestamp(objectOutput);
                return;
            default:
                throw new InvalidClassException("Unknown serialized type.");
        }
    }

    @Override // java.io.Externalizable
    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        byte b = objectInput.readByte();
        switch ((b & 255) >> 4) {
            case 1:
                this.obj = readDate(objectInput, b);
                return;
            case 2:
                this.obj = readTime(objectInput);
                return;
            case 3:
                this.obj = readWeekmodel(objectInput, b);
                return;
            case 4:
                this.obj = readMoment(objectInput, b);
                return;
            case 5:
                this.obj = readMachineTime(objectInput, b);
                return;
            case 6:
                this.obj = readDuration(objectInput, b);
                return;
            case 7:
                this.obj = readDayPeriod(objectInput, b);
                return;
            case 8:
                this.obj = readTimestamp(objectInput, b);
                return;
            default:
                throw new StreamCorruptedException("Unknown serialized type.");
        }
    }

    private void writeDate(DataOutput dataOutput) throws IOException {
        writeDate((PlainDate) this.obj, 1, dataOutput);
    }

    private static void writeDate(PlainDate plainDate, int i, DataOutput dataOutput) throws IOException {
        int i2;
        int year = plainDate.getYear();
        if (year < 1850 || year > 2100) {
            i2 = Math.abs(year) < 10000 ? 2 : 3;
        } else {
            i2 = 1;
        }
        dataOutput.writeByte((i << 4) | plainDate.getMonth());
        dataOutput.writeByte(plainDate.getDayOfMonth() | (i2 << 5));
        if (i2 == 1) {
            dataOutput.writeByte(year - 1978);
        } else if (i2 == 2) {
            dataOutput.writeShort(year);
        } else {
            dataOutput.writeInt(year);
        }
    }

    private PlainDate readDate(DataInput dataInput, byte b) throws IOException {
        int i;
        int i2 = b & 15;
        byte b2 = dataInput.readByte();
        int i3 = (b2 >> 5) & 3;
        int i4 = b2 & Ascii.US;
        if (i3 == 1) {
            i = dataInput.readByte() + 1978;
        } else if (i3 == 2) {
            i = dataInput.readShort();
        } else if (i3 == 3) {
            i = dataInput.readInt();
        } else {
            throw new StreamCorruptedException("Unknown year range.");
        }
        return PlainDate.of(i, Month.valueOf(i2), i4);
    }

    private void writeTime(DataOutput dataOutput) throws IOException {
        PlainTime plainTime = (PlainTime) this.obj;
        dataOutput.writeByte(32);
        writeTime(plainTime, dataOutput);
    }

    private static void writeTime(PlainTime plainTime, DataOutput dataOutput) throws IOException {
        if (plainTime.getNanosecond() == 0) {
            if (plainTime.getSecond() == 0) {
                if (plainTime.getMinute() == 0) {
                    dataOutput.writeByte(~plainTime.getHour());
                    return;
                } else {
                    dataOutput.writeByte(plainTime.getHour());
                    dataOutput.writeByte(~plainTime.getMinute());
                    return;
                }
            }
            dataOutput.writeByte(plainTime.getHour());
            dataOutput.writeByte(plainTime.getMinute());
            dataOutput.writeByte(~plainTime.getSecond());
            return;
        }
        dataOutput.writeByte(plainTime.getHour());
        dataOutput.writeByte(plainTime.getMinute());
        dataOutput.writeByte(plainTime.getSecond());
        dataOutput.writeInt(plainTime.getNanosecond());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v2, types: [int] */
    private PlainTime readTime(DataInput dataInput) throws IOException {
        int i;
        byte b = dataInput.readByte();
        if (b < 0) {
            return PlainTime.of(~b);
        }
        int i2 = dataInput.readByte();
        byte b2 = 0;
        if (i2 < 0) {
            i2 = ~i2;
            i = 0;
        } else {
            byte b3 = dataInput.readByte();
            if (b3 < 0) {
                b2 = ~b3;
                i = 0;
            } else {
                i = dataInput.readInt();
                b2 = b3;
            }
        }
        return PlainTime.of(b, i2, b2, i);
    }

    private void writeWeekmodel(DataOutput dataOutput) throws IOException {
        Weekmodel weekmodel = (Weekmodel) this.obj;
        boolean z = weekmodel.getStartOfWeekend() == Weekday.SATURDAY && weekmodel.getEndOfWeekend() == Weekday.SUNDAY;
        dataOutput.writeByte(!z ? 49 : 48);
        dataOutput.writeByte((weekmodel.getFirstDayOfWeek().getValue() << 4) | weekmodel.getMinimalDaysInFirstWeek());
        if (z) {
            return;
        }
        dataOutput.writeByte(weekmodel.getEndOfWeekend().getValue() | (weekmodel.getStartOfWeekend().getValue() << 4));
    }

    private Object readWeekmodel(DataInput dataInput, byte b) throws IOException {
        byte b2 = dataInput.readByte();
        Weekday weekdayValueOf = Weekday.valueOf(b2 >> 4);
        int i = b2 & 15;
        Weekday weekdayValueOf2 = Weekday.SATURDAY;
        Weekday weekdayValueOf3 = Weekday.SUNDAY;
        if ((b & 15) == 1) {
            byte b3 = dataInput.readByte();
            weekdayValueOf2 = Weekday.valueOf(b3 >> 4);
            weekdayValueOf3 = Weekday.valueOf(b3 & 15);
        }
        return Weekmodel.of(weekdayValueOf, i, weekdayValueOf2, weekdayValueOf3);
    }

    private void writeMoment(DataOutput dataOutput) throws IOException {
        ((Moment) this.obj).writeTimestamp(dataOutput);
    }

    private Object readMoment(DataInput dataInput, byte b) throws IOException {
        return Moment.readTimestamp(dataInput, (b & 1) != 0, ((b & 2) >>> 1) != 0);
    }

    private void writeTimestamp(DataOutput dataOutput) throws IOException {
        PlainTimestamp plainTimestamp = (PlainTimestamp) this.obj;
        writeDate(plainTimestamp.getCalendarDate(), 8, dataOutput);
        writeTime(plainTimestamp.getWallTime(), dataOutput);
    }

    private Object readTimestamp(DataInput dataInput, byte b) throws IOException {
        return PlainTimestamp.of(readDate(dataInput, b), readTime(dataInput));
    }

    private void writeDuration(ObjectOutput objectOutput) throws IOException {
        boolean z;
        Duration duration = (Duration) Duration.class.cast(this.obj);
        int size = duration.getTotalLength().size();
        int iMin = Math.min(size, 6);
        int i = 0;
        while (true) {
            if (i >= iMin) {
                z = false;
                break;
            } else {
                if (((TimeSpan.Item) duration.getTotalLength().get(i)).getAmount() >= 1000) {
                    z = true;
                    break;
                }
                i++;
            }
        }
        objectOutput.writeByte(z ? 97 : 96);
        objectOutput.writeInt(size);
        for (int i2 = 0; i2 < size; i2++) {
            TimeSpan.Item item = (TimeSpan.Item) duration.getTotalLength().get(i2);
            if (z) {
                objectOutput.writeLong(item.getAmount());
            } else {
                objectOutput.writeInt((int) item.getAmount());
            }
            objectOutput.writeObject(item.getUnit());
        }
        if (size > 0) {
            objectOutput.writeBoolean(duration.isNegative());
        }
    }

    private Object readDuration(ObjectInput objectInput, byte b) throws IOException, ClassNotFoundException {
        boolean z = (b & 15) == 1;
        int i = objectInput.readInt();
        if (i == 0) {
            return Duration.ofZero();
        }
        ArrayList arrayList = new ArrayList(i);
        for (int i2 = 0; i2 < i; i2++) {
            arrayList.add(TimeSpan.Item.of(z ? objectInput.readLong() : objectInput.readInt(), (IsoUnit) objectInput.readObject()));
        }
        return new Duration(arrayList, objectInput.readBoolean());
    }

    private void writeDayPeriod(ObjectOutput objectOutput) throws IOException {
        DayPeriod.Element element = (DayPeriod.Element) DayPeriod.Element.class.cast(this.obj);
        Locale locale = element.getLocale();
        int i = element.isFixed() ? 113 : 112;
        if (locale == null) {
            i |= 2;
        }
        objectOutput.writeByte(i);
        if (locale == null) {
            objectOutput.writeObject(element.getCodeMap());
            return;
        }
        String language = locale.getLanguage();
        if (!locale.getCountry().isEmpty()) {
            language = language + "-" + locale.getCountry();
        }
        objectOutput.writeUTF(language);
        objectOutput.writeUTF(element.getCalendarType());
    }

    private Object readDayPeriod(ObjectInput objectInput, byte b) throws IOException, ClassNotFoundException {
        Locale locale;
        boolean z = (b & 1) == 1;
        if ((b & 2) == 2) {
            return new DayPeriod.Element(z, DayPeriod.of((Map<PlainTime, String>) objectInput.readObject()));
        }
        String utf = objectInput.readUTF();
        String utf2 = objectInput.readUTF();
        int iIndexOf = utf.indexOf("-");
        if (iIndexOf == -1) {
            locale = new Locale(utf);
        } else {
            locale = new Locale(utf.substring(0, iIndexOf), utf.substring(iIndexOf + 1));
        }
        return new DayPeriod.Element(z, locale, utf2);
    }

    private void writeMachineTime(ObjectOutput objectOutput) throws IOException {
        MachineTime machineTime = (MachineTime) MachineTime.class.cast(this.obj);
        int i = machineTime.getScale() == TimeScale.UTC ? 81 : 80;
        if (machineTime.getFraction() == 0) {
            objectOutput.writeByte(i);
            objectOutput.writeLong(machineTime.getSeconds());
        } else {
            objectOutput.writeByte(i | 2);
            objectOutput.writeLong(machineTime.getSeconds());
            objectOutput.writeInt(machineTime.getFraction());
        }
    }

    private Object readMachineTime(ObjectInput objectInput, byte b) throws IOException {
        TimeScale timeScale = (b & 1) == 1 ? TimeScale.UTC : TimeScale.POSIX;
        long j = objectInput.readLong();
        int i = (b & 2) == 2 ? objectInput.readInt() : 0;
        if (timeScale == TimeScale.UTC) {
            return MachineTime.ofSIUnits(j, i);
        }
        return MachineTime.ofPosixUnits(j, i);
    }
}
