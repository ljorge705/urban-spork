package org.spongycastle.pqc.crypto;

import org.spongycastle.crypto.params.AsymmetricKeyParameter;

/* loaded from: classes7.dex */
public interface ExchangePairGenerator {
    ExchangePair GenerateExchange(AsymmetricKeyParameter asymmetricKeyParameter);

    ExchangePair generateExchange(AsymmetricKeyParameter asymmetricKeyParameter);
}
