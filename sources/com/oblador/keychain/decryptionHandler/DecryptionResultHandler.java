package com.oblador.keychain.decryptionHandler;

import com.oblador.keychain.cipherStorage.CipherStorage;

/* loaded from: classes2.dex */
public interface DecryptionResultHandler {
    void askAccessPermissions(CipherStorage.DecryptionContext decryptionContext);

    Throwable getError();

    CipherStorage.DecryptionResult getResult();

    void onDecrypt(CipherStorage.DecryptionResult decryptionResult, Throwable th);

    void waitResult();
}
