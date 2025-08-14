package com.oblador.keychain.decryptionHandler;

import com.oblador.keychain.cipherStorage.CipherStorage;
import com.oblador.keychain.exceptions.CryptoFailedException;

/* loaded from: classes2.dex */
public class DecryptionResultHandlerNonInteractive implements DecryptionResultHandler {
    private Throwable error;
    private CipherStorage.DecryptionResult result;

    @Override // com.oblador.keychain.decryptionHandler.DecryptionResultHandler
    public Throwable getError() {
        return this.error;
    }

    @Override // com.oblador.keychain.decryptionHandler.DecryptionResultHandler
    public CipherStorage.DecryptionResult getResult() {
        return this.result;
    }

    @Override // com.oblador.keychain.decryptionHandler.DecryptionResultHandler
    public void onDecrypt(CipherStorage.DecryptionResult decryptionResult, Throwable th) {
        this.result = decryptionResult;
        this.error = th;
    }

    @Override // com.oblador.keychain.decryptionHandler.DecryptionResultHandler
    public void waitResult() {
    }

    @Override // com.oblador.keychain.decryptionHandler.DecryptionResultHandler
    public void askAccessPermissions(CipherStorage.DecryptionContext decryptionContext) {
        onDecrypt(null, new CryptoFailedException("Non interactive decryption mode."));
    }
}
