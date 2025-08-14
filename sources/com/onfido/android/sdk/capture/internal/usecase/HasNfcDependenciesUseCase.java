package com.onfido.android.sdk.capture.internal.usecase;

import kotlin.Metadata;

/* compiled from: HasNfcDependenciesUseCase.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\t\u0010\u0003\u001a\u00020\u0004H\u0086\u0002¨\u0006\u0005"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/usecase/HasNfcDependenciesUseCase;", "", "()V", "invoke", "", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class HasNfcDependenciesUseCase {
    public static final HasNfcDependenciesUseCase INSTANCE = new HasNfcDependenciesUseCase();

    private HasNfcDependenciesUseCase() {
    }

    public final boolean invoke() {
        try {
            Class.forName("net.sf.scuba.smartcards.CardService");
            Class.forName("org.jmrtd.PACEKeySpec");
            Class.forName("org.spongycastle.util.encoders.Base64");
            Class.forName("org.bouncycastle.asn1.eac.EACObjectIdentifiers");
            Class.forName("org.ejbca.cvc.CVCertificate");
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }
}
