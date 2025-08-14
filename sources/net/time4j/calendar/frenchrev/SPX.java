package net.time4j.calendar.frenchrev;

import java.io.Externalizable;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.InvalidObjectException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;

/* loaded from: classes4.dex */
final class SPX implements Externalizable {
    static final int FRENCH_REV = 18;
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
        objectOutput.writeByte(this.type);
        if (this.type == 18) {
            writeFrenchRev(objectOutput);
            return;
        }
        throw new InvalidClassException("Unsupported calendar type.");
    }

    @Override // java.io.Externalizable
    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        if (objectInput.readByte() == 18) {
            this.obj = readFrenchRev(objectInput);
            return;
        }
        throw new InvalidObjectException("Unknown calendar type.");
    }

    private void writeFrenchRev(ObjectOutput objectOutput) throws IOException {
        FrenchRepublicanCalendar frenchRepublicanCalendar = (FrenchRepublicanCalendar) this.obj;
        objectOutput.writeInt(frenchRepublicanCalendar.getYear());
        objectOutput.writeShort(frenchRepublicanCalendar.getDayOfYear());
    }

    private FrenchRepublicanCalendar readFrenchRev(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        int i = objectInput.readInt();
        short s = objectInput.readShort();
        if (s <= 360) {
            int i2 = s - 1;
            return FrenchRepublicanCalendar.of(i, (i2 / 30) + 1, (i2 % 30) + 1);
        }
        return FrenchRepublicanCalendar.of(i, Sansculottides.valueOf(s - 360));
    }
}
