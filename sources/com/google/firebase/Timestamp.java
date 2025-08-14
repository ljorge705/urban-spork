package com.google.firebase;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.firebase.firestore.util.Preconditions;
import java.util.Date;

/* loaded from: classes5.dex */
public final class Timestamp implements Comparable<Timestamp>, Parcelable {
    public static final Parcelable.Creator<Timestamp> CREATOR = new Parcelable.Creator<Timestamp>() { // from class: com.google.firebase.Timestamp.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Timestamp createFromParcel(Parcel parcel) {
            return new Timestamp(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Timestamp[] newArray(int i) {
            return new Timestamp[i];
        }
    };
    private final int nanoseconds;
    private final long seconds;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getNanoseconds() {
        return this.nanoseconds;
    }

    public long getSeconds() {
        return this.seconds;
    }

    public int hashCode() {
        long j = this.seconds;
        return (((((int) j) * 1369) + ((int) (j >> 32))) * 37) + this.nanoseconds;
    }

    public Timestamp(long j, int i) {
        validateRange(j, i);
        this.seconds = j;
        this.nanoseconds = i;
    }

    protected Timestamp(Parcel parcel) {
        this.seconds = parcel.readLong();
        this.nanoseconds = parcel.readInt();
    }

    public Timestamp(Date date) {
        long time = date.getTime();
        long j = time / 1000;
        int i = ((int) (time % 1000)) * 1000000;
        if (i < 0) {
            j--;
            i += 1000000000;
        }
        validateRange(j, i);
        this.seconds = j;
        this.nanoseconds = i;
    }

    public static Timestamp now() {
        return new Timestamp(new Date());
    }

    public Date toDate() {
        return new Date((this.seconds * 1000) + (this.nanoseconds / 1000000));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.seconds);
        parcel.writeInt(this.nanoseconds);
    }

    @Override // java.lang.Comparable
    public int compareTo(Timestamp timestamp) {
        long j = this.seconds;
        long j2 = timestamp.seconds;
        if (j == j2) {
            return Integer.signum(this.nanoseconds - timestamp.nanoseconds);
        }
        return Long.signum(j - j2);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof Timestamp) && compareTo((Timestamp) obj) == 0;
    }

    public String toString() {
        return "Timestamp(seconds=" + this.seconds + ", nanoseconds=" + this.nanoseconds + ")";
    }

    private static void validateRange(long j, int i) {
        Preconditions.checkArgument(i >= 0, "Timestamp nanoseconds out of range: %s", Integer.valueOf(i));
        Preconditions.checkArgument(((double) i) < 1.0E9d, "Timestamp nanoseconds out of range: %s", Integer.valueOf(i));
        Preconditions.checkArgument(j >= -62135596800L, "Timestamp seconds out of range: %s", Long.valueOf(j));
        Preconditions.checkArgument(j < 253402300800L, "Timestamp seconds out of range: %s", Long.valueOf(j));
    }
}
