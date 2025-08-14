package com.onfido.android.sdk.capture.utils;

import com.onfido.javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/utils/DefaultTimeProvider;", "Lcom/onfido/android/sdk/capture/utils/TimeProvider;", "()V", "getCurrentTimeIsoFormat", "", "getCurrentTimestamp", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DefaultTimeProvider implements TimeProvider {
    @Inject
    public DefaultTimeProvider() {
    }

    @Override // com.onfido.android.sdk.capture.utils.TimeProvider
    public String getCurrentTimeIsoFormat() {
        String str = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US).format(Calendar.getInstance().getTime());
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    @Override // com.onfido.android.sdk.capture.utils.TimeProvider
    public long getCurrentTimestamp() {
        return System.currentTimeMillis();
    }
}
