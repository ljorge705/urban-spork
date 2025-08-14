package com.fivvy.profiler.domain.usecases;

import com.fivvy.profiler.domain.models.AppUsageInfo;
import com.fivvy.profiler.domain.repositories.IAppUsageRepository;
import com.fivvy.profiler.domain.usecases.interfaces.IGetAppUsageUseCase;
import java.util.List;

/* loaded from: classes5.dex */
public class GetAppUsage implements IGetAppUsageUseCase {
    private final IAppUsageRepository appUsageRepository;

    public GetAppUsage(IAppUsageRepository iAppUsageRepository) {
        this.appUsageRepository = iAppUsageRepository;
    }

    @Override // com.fivvy.profiler.domain.usecases.interfaces.IGetAppUsageUseCase
    public List<AppUsageInfo> execute(int i) {
        return this.appUsageRepository.getAppUsage(i);
    }
}
