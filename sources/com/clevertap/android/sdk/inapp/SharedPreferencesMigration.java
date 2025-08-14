package com.clevertap.android.sdk.inapp;

import android.content.SharedPreferences;
import androidx.exifinterface.media.ExifInterface;
import com.clevertap.android.sdk.StorageHelper;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SharedPreferencesMigration.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B9\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007\u0012\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\u0010\u000bJ\u0006\u0010\f\u001a\u00020\rR\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/clevertap/android/sdk/inapp/SharedPreferencesMigration;", ExifInterface.GPS_DIRECTION_TRUE, "", "oldSharedPreferences", "Landroid/content/SharedPreferences;", "newSharedPreferences", "valueType", "Ljava/lang/Class;", "condition", "Lkotlin/Function1;", "", "(Landroid/content/SharedPreferences;Landroid/content/SharedPreferences;Ljava/lang/Class;Lkotlin/jvm/functions/Function1;)V", "migrate", "", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class SharedPreferencesMigration<T> {
    private final Function1<T, Boolean> condition;
    private final SharedPreferences newSharedPreferences;
    private final SharedPreferences oldSharedPreferences;
    private final Class<T> valueType;

    /* JADX WARN: Multi-variable type inference failed */
    public SharedPreferencesMigration(SharedPreferences oldSharedPreferences, SharedPreferences newSharedPreferences, Class<T> valueType, Function1<? super T, Boolean> condition) {
        Intrinsics.checkNotNullParameter(oldSharedPreferences, "oldSharedPreferences");
        Intrinsics.checkNotNullParameter(newSharedPreferences, "newSharedPreferences");
        Intrinsics.checkNotNullParameter(valueType, "valueType");
        Intrinsics.checkNotNullParameter(condition, "condition");
        this.oldSharedPreferences = oldSharedPreferences;
        this.newSharedPreferences = newSharedPreferences;
        this.valueType = valueType;
        this.condition = condition;
    }

    public /* synthetic */ SharedPreferencesMigration(SharedPreferences sharedPreferences, SharedPreferences sharedPreferences2, Class cls, AnonymousClass1 anonymousClass1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(sharedPreferences, sharedPreferences2, cls, (i & 8) != 0 ? new Function1<T, Boolean>() { // from class: com.clevertap.android.sdk.inapp.SharedPreferencesMigration.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(T t) {
                return true;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(Object obj) {
                return invoke((AnonymousClass1) obj);
            }
        } : anonymousClass1);
    }

    public final void migrate() {
        Map<String, ?> oldData = this.oldSharedPreferences.getAll();
        SharedPreferences.Editor editorEdit = this.newSharedPreferences.edit();
        Intrinsics.checkNotNullExpressionValue(oldData, "oldData");
        for (Map.Entry<String, ?> entry : oldData.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (this.valueType.isInstance(value) && ((Boolean) this.condition.invoke(value)).booleanValue()) {
                Class<T> cls = this.valueType;
                if (Intrinsics.areEqual(cls, Boolean.class)) {
                    Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlin.Boolean");
                    editorEdit.putBoolean(key, ((Boolean) value).booleanValue());
                } else if (Intrinsics.areEqual(cls, Integer.class)) {
                    Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlin.Int");
                    editorEdit.putInt(key, ((Integer) value).intValue());
                } else if (Intrinsics.areEqual(cls, Long.class)) {
                    Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlin.Long");
                    editorEdit.putLong(key, ((Long) value).longValue());
                } else if (Intrinsics.areEqual(cls, Float.class)) {
                    Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlin.Float");
                    editorEdit.putFloat(key, ((Float) value).floatValue());
                } else if (Intrinsics.areEqual(cls, String.class)) {
                    Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlin.String");
                    editorEdit.putString(key, (String) value);
                } else if (value instanceof Boolean) {
                    editorEdit.putBoolean(key, ((Boolean) value).booleanValue());
                } else if (value instanceof Integer) {
                    editorEdit.putInt(key, ((Number) value).intValue());
                } else if (value instanceof Long) {
                    editorEdit.putLong(key, ((Number) value).longValue());
                } else if (value instanceof Float) {
                    editorEdit.putFloat(key, ((Number) value).floatValue());
                } else if (value instanceof String) {
                    editorEdit.putString(key, (String) value);
                }
            }
        }
        StorageHelper.persist(editorEdit);
        this.oldSharedPreferences.edit().clear().apply();
    }
}
