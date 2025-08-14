package com.clevertap.android.sdk.inapp.images.memory;

import androidx.exifinterface.media.ExifInterface;
import com.clevertap.android.sdk.Constants;
import java.io.File;
import kotlin.Metadata;
import kotlin.Pair;

/* compiled from: MemoryAccessObject.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0002\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H&J+\u0010\u0007\u001a\u0004\u0018\u0001H\b\"\u0004\b\u0001\u0010\b2\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\b0\nH&¢\u0006\u0002\u0010\u000bJ\u001e\u0010\f\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u0004\u0018\u00010\r2\u0006\u0010\u0005\u001a\u00020\u0006H&J+\u0010\u000e\u001a\u0004\u0018\u0001H\b\"\u0004\b\u0001\u0010\b2\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\b0\nH&¢\u0006\u0002\u0010\u000bJ\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0005\u001a\u00020\u0006H&J\u001e\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u0004\u0018\u00010\r2\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0018\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u0014H&J$\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0005\u001a\u00020\u00062\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00040\rH&¨\u0006\u0016"}, d2 = {"Lcom/clevertap/android/sdk/inapp/images/memory/MemoryAccessObject;", ExifInterface.GPS_DIRECTION_TRUE, "", "fetchDiskMemory", "Ljava/io/File;", Constants.KEY_KEY, "", "fetchDiskMemoryAndTransform", ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "transformTo", "Lcom/clevertap/android/sdk/inapp/images/memory/MemoryDataTransformationType;", "(Ljava/lang/String;Lcom/clevertap/android/sdk/inapp/images/memory/MemoryDataTransformationType;)Ljava/lang/Object;", "fetchInMemory", "Lkotlin/Pair;", "fetchInMemoryAndTransform", "removeDiskMemory", "", "removeInMemory", "saveDiskMemory", "data", "", "saveInMemory", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public interface MemoryAccessObject<T> {
    File fetchDiskMemory(String key);

    <A> A fetchDiskMemoryAndTransform(String key, MemoryDataTransformationType<A> transformTo);

    Pair<T, File> fetchInMemory(String key);

    <A> A fetchInMemoryAndTransform(String key, MemoryDataTransformationType<A> transformTo);

    boolean removeDiskMemory(String key);

    Pair<T, File> removeInMemory(String key);

    File saveDiskMemory(String key, byte[] data);

    boolean saveInMemory(String key, Pair<? extends T, ? extends File> data);
}
