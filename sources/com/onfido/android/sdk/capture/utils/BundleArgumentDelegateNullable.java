package com.onfido.android.sdk.capture.utils;

import android.os.Bundle;
import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0006\u0012\u0004\u0018\u0001H\u00010\u0002B\u0005¢\u0006\u0002\u0010\u0004J&\u0010\u0005\u001a\u0004\u0018\u00018\u00002\b\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\bH\u0096\u0002¢\u0006\u0002\u0010\tJ.\u0010\n\u001a\u00020\u000b2\b\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b2\b\u0010\f\u001a\u0004\u0018\u00018\u0000H\u0096\u0002¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lcom/onfido/android/sdk/capture/utils/BundleArgumentDelegateNullable;", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlin/properties/ReadWriteProperty;", "Landroid/os/Bundle;", "()V", "getValue", "thisRef", "property", "Lkotlin/reflect/KProperty;", "(Landroid/os/Bundle;Lkotlin/reflect/KProperty;)Ljava/lang/Object;", "setValue", "", "value", "(Landroid/os/Bundle;Lkotlin/reflect/KProperty;Ljava/lang/Object;)V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class BundleArgumentDelegateNullable<T> implements ReadWriteProperty<Bundle, T> {
    public T getValue(Bundle thisRef, KProperty<?> property) {
        Intrinsics.checkNotNullParameter(property, "property");
        Object obj = thisRef != null ? thisRef.get(property.getName()) : null;
        if (obj == null) {
            return null;
        }
        return (T) obj;
    }

    /* renamed from: setValue, reason: avoid collision after fix types in other method */
    public void setValue2(Bundle thisRef, KProperty<?> property, T value) {
        Intrinsics.checkNotNullParameter(property, "property");
        String name = property.getName();
        if (value == null) {
            if (thisRef != null) {
                thisRef.remove(name);
            }
        } else if (thisRef != null) {
            BundleArgumentDelegateKt.put(thisRef, name, value);
        }
    }

    @Override // kotlin.properties.ReadWriteProperty, kotlin.properties.ReadOnlyProperty
    public /* bridge */ /* synthetic */ Object getValue(Object obj, KProperty kProperty) {
        return getValue((Bundle) obj, (KProperty<?>) kProperty);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.properties.ReadWriteProperty
    public /* bridge */ /* synthetic */ void setValue(Bundle bundle, KProperty kProperty, Object obj) {
        setValue2(bundle, (KProperty<?>) kProperty, (KProperty) obj);
    }
}
