package com.onfido.android.sdk.capture.utils;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.exifinterface.media.ExifInterface;
import com.clevertap.android.sdk.Constants;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.properties.ReadWriteProperty;

@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a-\u0010\u0000\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\b\b\u0000\u0010\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u0002H\u0003H\u0000¢\u0006\u0002\u0010\u0006\u001a\u001e\u0010\u0007\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0006\u0012\u0004\u0018\u0001H\u00030\u0001\"\u0004\b\u0000\u0010\u0003H\u0000\u001a'\u0010\b\u001a\u00020\t\"\u0004\b\u0000\u0010\u0003*\u00020\u00022\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u0002H\u0003H\u0002¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"bundleArgument", "Lkotlin/properties/ReadWriteProperty;", "Landroid/os/Bundle;", ExifInterface.GPS_DIRECTION_TRUE, "", "defaultValue", "(Ljava/lang/Object;)Lkotlin/properties/ReadWriteProperty;", "bundleArgumentNullable", "put", "", Constants.KEY_KEY, "", "value", "(Landroid/os/Bundle;Ljava/lang/String;Ljava/lang/Object;)V", "onfido-capture-sdk-core_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class BundleArgumentDelegateKt {
    public static final <T> ReadWriteProperty<Bundle, T> bundleArgument(T defaultValue) {
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        return new BundleArgumentDelegate(defaultValue);
    }

    public static final <T> ReadWriteProperty<Bundle, T> bundleArgumentNullable() {
        return new BundleArgumentDelegateNullable();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> void put(Bundle bundle, String str, T t) {
        if (t instanceof Boolean) {
            bundle.putBoolean(str, ((Boolean) t).booleanValue());
            return;
        }
        if (t instanceof Byte) {
            bundle.putByte(str, ((Number) t).byteValue());
            return;
        }
        if (t instanceof Character) {
            bundle.putChar(str, ((Character) t).charValue());
            return;
        }
        if (t instanceof Short) {
            bundle.putShort(str, ((Number) t).shortValue());
            return;
        }
        if (t instanceof Double) {
            bundle.putDouble(str, ((Number) t).doubleValue());
            return;
        }
        if (t instanceof Float) {
            bundle.putFloat(str, ((Number) t).floatValue());
            return;
        }
        if (t instanceof Integer) {
            bundle.putInt(str, ((Number) t).intValue());
            return;
        }
        if (t instanceof Long) {
            bundle.putLong(str, ((Number) t).longValue());
            return;
        }
        if (t instanceof Bundle) {
            bundle.putBundle(str, (Bundle) t);
            return;
        }
        if (t instanceof String) {
            bundle.putString(str, (String) t);
            return;
        }
        if (t instanceof CharSequence) {
            bundle.putCharSequence(str, (CharSequence) t);
            return;
        }
        if (t instanceof boolean[]) {
            bundle.putBooleanArray(str, (boolean[]) t);
            return;
        }
        if (t instanceof byte[]) {
            bundle.putByteArray(str, (byte[]) t);
            return;
        }
        if (t instanceof char[]) {
            bundle.putCharArray(str, (char[]) t);
            return;
        }
        if (t instanceof double[]) {
            bundle.putDoubleArray(str, (double[]) t);
            return;
        }
        if (t instanceof float[]) {
            bundle.putFloatArray(str, (float[]) t);
            return;
        }
        if (t instanceof int[]) {
            bundle.putIntArray(str, (int[]) t);
            return;
        }
        if (t instanceof long[]) {
            bundle.putLongArray(str, (long[]) t);
            return;
        }
        if (t instanceof short[]) {
            bundle.putShortArray(str, (short[]) t);
        } else if (t instanceof Serializable) {
            bundle.putSerializable(str, (Serializable) t);
        } else {
            if (!(t instanceof Parcelable)) {
                throw new IllegalStateException(("Type of property " + str + " is not supported").toString());
            }
            bundle.putParcelable(str, (Parcelable) t);
        }
    }
}
