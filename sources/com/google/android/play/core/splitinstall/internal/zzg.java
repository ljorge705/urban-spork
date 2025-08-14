package com.google.android.play.core.splitinstall.internal;

import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;

/* compiled from: com.google.android.play:feature-delivery@@2.1.0 */
/* loaded from: classes3.dex */
final class zzg extends zzh {
    private final byte[] zza;

    public zzg(X509Certificate x509Certificate, byte[] bArr) {
        super(x509Certificate);
        this.zza = bArr;
    }

    @Override // com.google.android.play.core.splitinstall.internal.zzh, java.security.cert.Certificate
    public final byte[] getEncoded() throws CertificateEncodingException {
        return this.zza;
    }
}
