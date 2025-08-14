package com.fivvy.profiler.domain.usecases;

import com.fivvy.profiler.domain.repositories.IAppUsageRepository;
import com.fivvy.profiler.domain.usecases.interfaces.IOpenUsageSettingsDirectlyDirectly;

/* loaded from: classes5.dex */
public class OpenUsageSettingsDirectly implements IOpenUsageSettingsDirectlyDirectly {
    private final IAppUsageRepository appUsageRepository;

    public OpenUsageSettingsDirectly(IAppUsageRepository iAppUsageRepository) {
        this.appUsageRepository = iAppUsageRepository;
    }

    @Override // com.fivvy.profiler.domain.usecases.interfaces.IOpenUsageSettingsDirectlyDirectly
    public void execute() {
        this.appUsageRepository.goToSettingsDirectly();
    }
}
