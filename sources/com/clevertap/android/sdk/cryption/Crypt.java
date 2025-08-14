package com.clevertap.android.sdk.cryption;

import kotlin.Metadata;

/* compiled from: Crypt.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0000\b&\u0018\u00002\u00020\u0001B\u0007\b\u0004¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H&J\u001a\u0010\u0007\u001a\u0004\u0018\u00010\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H&J\u0012\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0005\u001a\u00020\u0004H$¨\u0006\u000b"}, d2 = {"Lcom/clevertap/android/sdk/cryption/Crypt;", "", "()V", "decryptInternal", "", "cipherText", "accountID", "encryptInternal", "plainText", "parseCipherText", "", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public abstract class Crypt {
    public abstract String decryptInternal(String cipherText, String accountID);

    public abstract String encryptInternal(String plainText, String accountID);

    protected abstract byte[] parseCipherText(String cipherText);

    protected Crypt() {
    }
}
