package n.o.t.i.f.e.e;

import android.os.Bundle;
import android.os.Parcel;
import java.util.Objects;

/* loaded from: classes4.dex */
public class l {

    public interface a<T> {
        void a(T t);
    }

    public static int a(Object obj) {
        if (obj == null) {
            return 0;
        }
        return obj instanceof Double ? (int) ((Double) obj).doubleValue() : ((Integer) obj).intValue();
    }

    public static long b(Object obj) {
        return obj instanceof Double ? (long) ((Double) obj).doubleValue() : ((Long) obj).longValue();
    }

    public static byte[] a(Bundle bundle) {
        Parcel parcelObtain = Parcel.obtain();
        parcelObtain.writeBundle(bundle);
        byte[] bArrMarshall = parcelObtain.marshall();
        parcelObtain.recycle();
        return bArrMarshall;
    }

    public static Bundle a(byte[] bArr) {
        Parcel parcelObtain = Parcel.obtain();
        parcelObtain.unmarshall(bArr, 0, bArr.length);
        parcelObtain.setDataPosition(0);
        Bundle bundle = parcelObtain.readBundle(l.class.getClassLoader());
        parcelObtain.recycle();
        return (Bundle) Objects.requireNonNull(bundle);
    }
}
