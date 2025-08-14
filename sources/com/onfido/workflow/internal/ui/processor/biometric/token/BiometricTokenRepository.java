package com.onfido.workflow.internal.ui.processor.biometric.token;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import kotlin.Metadata;

/* compiled from: BiometricTokenRepository.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b`\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\u0006H&J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0006H&¨\u0006\u000b"}, d2 = {"Lcom/onfido/workflow/internal/ui/processor/biometric/token/BiometricTokenRepository;", "", "getType", "Lcom/onfido/workflow/internal/ui/processor/biometric/token/BiometricTokenRepositoryType;", "retrieveToken", "Lio/reactivex/rxjava3/core/Observable;", "", "customerUserHash", "storeToken", "Lio/reactivex/rxjava3/core/Completable;", "biometricToken", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public interface BiometricTokenRepository {
    BiometricTokenRepositoryType getType();

    Observable<String> retrieveToken(String customerUserHash);

    Completable storeToken(String customerUserHash, String biometricToken);
}
