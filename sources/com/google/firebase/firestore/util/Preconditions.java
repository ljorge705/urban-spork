package com.google.firebase.firestore.util;

import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* loaded from: classes2.dex */
public class Preconditions {
    public static void checkArgument(boolean z, @Nullable String str, @Nullable Object... objArr) {
        if (!z) {
            throw new IllegalArgumentException(String.format(str, objArr));
        }
    }

    public static <T> T checkNotNull(@Nonnull T t) {
        t.getClass();
        return t;
    }

    public static <T> T checkNotNull(@Nonnull T t, @Nullable Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(String.valueOf(obj));
    }

    public static <T> T checkNotNull(@Nonnull T t, String str, @Nullable Object... objArr) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(String.format(Locale.US, str, objArr));
    }

    public static void checkState(boolean z) {
        if (!z) {
            throw new IllegalStateException();
        }
    }

    public static void checkState(boolean z, String str) {
        if (!z) {
            throw new IllegalStateException(str);
        }
    }
}
