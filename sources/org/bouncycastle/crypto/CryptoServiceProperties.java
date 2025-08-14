package org.bouncycastle.crypto;

/* loaded from: classes4.dex */
public interface CryptoServiceProperties {
    int bitsOfSecurity();

    Object getParams();

    CryptoServicePurpose getPurpose();

    String getServiceName();
}
