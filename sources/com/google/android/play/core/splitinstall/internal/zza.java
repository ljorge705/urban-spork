package com.google.android.play.core.splitinstall.internal;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;

/* compiled from: com.google.android.play:feature-delivery@@2.1.0 */
/* loaded from: classes3.dex */
final class zza implements zzb {
    private final ByteBuffer zza;

    public zza(ByteBuffer byteBuffer) {
        this.zza = byteBuffer.slice();
    }

    @Override // com.google.android.play.core.splitinstall.internal.zzb
    public final long zza() {
        return this.zza.capacity();
    }

    @Override // com.google.android.play.core.splitinstall.internal.zzb
    public final void zzb(MessageDigest[] messageDigestArr, long j, int i) throws IOException {
        ByteBuffer byteBufferSlice;
        synchronized (this.zza) {
            int i2 = (int) j;
            this.zza.position(i2);
            this.zza.limit(i2 + i);
            byteBufferSlice = this.zza.slice();
        }
        for (MessageDigest messageDigest : messageDigestArr) {
            byteBufferSlice.position(0);
            messageDigest.update(byteBufferSlice);
        }
    }
}
