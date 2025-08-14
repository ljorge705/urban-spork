package org.spongycastle.cms;

import org.spongycastle.operator.OperatorCreationException;

/* loaded from: classes4.dex */
public interface SignerInformationVerifierProvider {
    SignerInformationVerifier get(SignerId signerId) throws OperatorCreationException;
}
