package org.spongycastle.openssl;

import org.spongycastle.operator.OperatorCreationException;

/* loaded from: classes7.dex */
public interface PEMDecryptorProvider {
    PEMDecryptor get(String str) throws OperatorCreationException;
}
