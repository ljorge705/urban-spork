package com.fivvy.profiler.domain.usecases;

import com.fivvy.profiler.domain.models.AppInstalledInfo;
import com.fivvy.profiler.domain.repositories.InstalledAppsRepository;
import com.fivvy.profiler.domain.usecases.interfaces.IGetInstalledAppsUseCase;
import java.util.List;

/* loaded from: classes5.dex */
public class GetInstalledApps implements IGetInstalledAppsUseCase {
    private final InstalledAppsRepository installedAppsRepository;

    public GetInstalledApps(InstalledAppsRepository installedAppsRepository) {
        this.installedAppsRepository = installedAppsRepository;
    }

    @Override // com.fivvy.profiler.domain.usecases.interfaces.IGetInstalledAppsUseCase
    public List<AppInstalledInfo> execute() {
        return this.installedAppsRepository.getInstalledApps();
    }
}
