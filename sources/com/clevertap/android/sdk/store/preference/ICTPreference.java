package com.clevertap.android.sdk.store.preference;

import com.clevertap.android.sdk.Constants;
import io.sentry.rrweb.RRWebVideoEvent;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;

/* compiled from: ICTPreference.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0013\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&J\u0014\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0002\b\u0003\u0018\u00010\tH&J\u0018\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0007H&J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u000eH&J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0010H&J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0012H&J\u001a\u0010\u0013\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0005H&J&\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00152\u0006\u0010\u000b\u001a\u00020\u00052\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\u0015H&J\u0010\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0005H&J\u0010\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0005H&J\b\u0010\u0018\u001a\u00020\u0010H&J\u0018\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u0007H&J\u0018\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u0007H&J\u0018\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u000eH&J\u0018\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u000eH&J\u0018\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u0010H&J\u0018\u0010\u001f\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u0010H&J\u0018\u0010 \u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u0012H&J\u0018\u0010!\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u0012H&J\"\u0010\"\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00052\u0010\u0010\u001a\u001a\f\u0012\u0004\u0012\u00020\u0005\u0012\u0002\b\u00030\tH&J\"\u0010#\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00052\u0010\u0010\u001a\u001a\f\u0012\u0004\u0012\u00020\u0005\u0012\u0002\b\u00030\tH&J\u0018\u0010$\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u0005H&J\u0018\u0010%\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u0005H&J\u001e\u0010&\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00052\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00050\u0015H&J\u001e\u0010'\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00052\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00050\u0015H&¨\u0006("}, d2 = {"Lcom/clevertap/android/sdk/store/preference/ICTPreference;", "", "changePreferenceName", "", "prefName", "", "isEmpty", "", "readAll", "", "readBoolean", Constants.KEY_KEY, com.facebook.hermes.intl.Constants.COLLATION_DEFAULT, "readFloat", "", "readInt", "", "readLong", "", "readString", "readStringSet", "", "remove", "removeImmediate", RRWebVideoEvent.JsonKeys.SIZE, "writeBoolean", "value", "writeBooleanImmediate", "writeFloat", "writeFloatImmediate", "writeInt", "writeIntImmediate", "writeLong", "writeLongImmediate", "writeMap", "writeMapImmediate", "writeString", "writeStringImmediate", "writeStringSet", "writeStringSetImmediate", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public interface ICTPreference {
    void changePreferenceName(String prefName);

    boolean isEmpty();

    Map<String, ?> readAll();

    boolean readBoolean(String key, boolean z);

    float readFloat(String key, float f);

    int readInt(String key, int i);

    long readLong(String key, long j);

    String readString(String key, String str);

    Set<String> readStringSet(String key, Set<String> set);

    void remove(String key);

    void removeImmediate(String key);

    int size();

    void writeBoolean(String key, boolean value);

    void writeBooleanImmediate(String key, boolean value);

    void writeFloat(String key, float value);

    void writeFloatImmediate(String key, float value);

    void writeInt(String key, int value);

    void writeIntImmediate(String key, int value);

    void writeLong(String key, long value);

    void writeLongImmediate(String key, long value);

    void writeMap(String key, Map<String, ?> value);

    void writeMapImmediate(String key, Map<String, ?> value);

    void writeString(String key, String value);

    void writeStringImmediate(String key, String value);

    void writeStringSet(String key, Set<String> value);

    void writeStringSetImmediate(String key, Set<String> value);
}
