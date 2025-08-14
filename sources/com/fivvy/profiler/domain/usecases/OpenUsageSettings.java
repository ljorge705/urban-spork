package com.fivvy.profiler.domain.usecases;

import com.fivvy.profiler.domain.repositories.IAppUsageRepository;
import com.fivvy.profiler.domain.usecases.interfaces.IOpenUsageSettingsUseCase;

/* loaded from: classes5.dex */
public class OpenUsageSettings implements IOpenUsageSettingsUseCase {
    private final IAppUsageRepository appUsageRepository;

    public OpenUsageSettings(IAppUsageRepository iAppUsageRepository) {
        this.appUsageRepository = iAppUsageRepository;
    }

    @Override // com.fivvy.profiler.domain.usecases.interfaces.IOpenUsageSettingsUseCase
    public void execute(String str, String str2, String str3, byte[] bArr, String str4) {
        this.appUsageRepository.goToSettingsAndTransparentActivity(str, str2, str3, bArr, str4);
    }
}
