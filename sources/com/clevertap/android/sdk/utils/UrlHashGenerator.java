package com.clevertap.android.sdk.utils;

import com.clevertap.android.sdk.Constants;
import com.microsoft.codepush.react.CodePushConstants;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

/* compiled from: UrlHashGenerator.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J!\u0010\u0003\u001a\u001d\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\u00050\u0004J\u0006\u0010\t\u001a\u00020\u0005¨\u0006\n"}, d2 = {"Lcom/clevertap/android/sdk/utils/UrlHashGenerator;", "", "()V", CodePushConstants.PENDING_UPDATE_HASH_KEY, "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", Constants.KEY_KEY, "hashWithTsSeed", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class UrlHashGenerator {
    public static final UrlHashGenerator INSTANCE = new UrlHashGenerator();

    private UrlHashGenerator() {
    }

    public final Function1<String, String> hash() {
        return new Function1<String, String>() { // from class: com.clevertap.android.sdk.utils.UrlHashGenerator.hash.1
            @Override // kotlin.jvm.functions.Function1
            public final String invoke(String key) {
                UUID uuidNameUUIDFromBytes;
                Intrinsics.checkNotNullParameter(key, "key");
                try {
                    byte[] bytes = key.getBytes(Charsets.UTF_8);
                    Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
                    uuidNameUUIDFromBytes = UUID.nameUUIDFromBytes(bytes);
                } catch (InternalError unused) {
                    String.valueOf(key.hashCode());
                    uuidNameUUIDFromBytes = null;
                }
                String string = uuidNameUUIDFromBytes != null ? uuidNameUUIDFromBytes.toString() : null;
                return string == null ? String.valueOf(key.hashCode()) : string;
            }
        };
    }

    public final String hashWithTsSeed() {
        Function1<String, String> function1Hash = hash();
        String strValueOf = String.valueOf(System.currentTimeMillis());
        Intrinsics.checkNotNullExpressionValue(strValueOf, "valueOf(System.currentTimeMillis())");
        return function1Hash.invoke(strValueOf);
    }
}
