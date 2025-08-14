package com.onfido.android.sdk.capture.internal.util;

import android.content.Intent;
import android.os.Build;
import androidx.exifinterface.media.ExifInterface;
import com.clevertap.android.sdk.Constants;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0016\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a(\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0080\b¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"serializable", ExifInterface.GPS_DIRECTION_TRUE, "Ljava/io/Serializable;", "Landroid/content/Intent;", Constants.KEY_KEY, "", "(Landroid/content/Intent;Ljava/lang/String;)Ljava/io/Serializable;", "onfido-capture-sdk-core_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class IntentExtensionsKt {
    public static final /* synthetic */ <T extends Serializable> T serializable(Intent intent, String key) {
        Intrinsics.checkNotNullParameter(intent, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        if (Build.VERSION.SDK_INT >= 33) {
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            return (T) intent.getSerializableExtra(key, Serializable.class);
        }
        T t = (T) intent.getSerializableExtra(key);
        Intrinsics.reifiedOperationMarker(2, ExifInterface.GPS_DIRECTION_TRUE);
        return t;
    }
}
