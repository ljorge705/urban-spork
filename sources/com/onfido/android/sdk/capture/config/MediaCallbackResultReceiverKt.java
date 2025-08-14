package com.onfido.android.sdk.capture.config;

import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MediaCallbackResultReceiver.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0012\n\u0002\u0010\u0011\n\u0002\b\u0002\u001a\u0017\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u0002H\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"allocateAll", "", "", "([[B)[B", "onfido-public-api_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class MediaCallbackResultReceiverKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final byte[] allocateAll(byte[][] bArr) {
        byte[][] bArr2 = bArr;
        int length = 0;
        for (byte[] bArr3 : bArr2) {
            length += bArr3.length;
        }
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(length);
        for (byte[] bArr4 : bArr2) {
            byteBufferAllocate.put(bArr4);
        }
        byte[] bArrArray = byteBufferAllocate.array();
        Intrinsics.checkNotNullExpressionValue(bArrArray, "array(...)");
        return bArrArray;
    }
}
