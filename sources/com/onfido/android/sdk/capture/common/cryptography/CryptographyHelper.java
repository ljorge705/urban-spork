package com.onfido.android.sdk.capture.common.cryptography;

import android.util.Base64;
import com.onfido.android.sdk.capture.BuildConfig;
import com.onfido.javax.inject.Inject;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/common/cryptography/CryptographyHelper;", "", "()V", "getStaticMacKeyPart", "", "getTimestampInSeconds", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CryptographyHelper {
    @Inject
    public CryptographyHelper() {
    }

    public final byte[] getStaticMacKeyPart() {
        byte[] bArrDecode = Base64.decode(BuildConfig.MAC_KEY_PART, 0);
        Intrinsics.checkNotNullExpressionValue(bArrDecode, "decode(...)");
        return bArrDecode;
    }

    public final String getTimestampInSeconds() {
        return String.valueOf(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
    }
}
