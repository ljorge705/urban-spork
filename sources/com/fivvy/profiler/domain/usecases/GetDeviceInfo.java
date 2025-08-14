package com.fivvy.profiler.domain.usecases;

import com.fivvy.profiler.domain.models.DeviceInfo;
import com.fivvy.profiler.domain.repositories.IDeviceInfoRepository;
import com.fivvy.profiler.domain.usecases.interfaces.IGetDeviceInfoUseCase;

/* loaded from: classes5.dex */
public class GetDeviceInfo implements IGetDeviceInfoUseCase {
    private final IDeviceInfoRepository deviceInfoRepository;

    public GetDeviceInfo(IDeviceInfoRepository iDeviceInfoRepository) {
        this.deviceInfoRepository = iDeviceInfoRepository;
    }

    @Override // com.fivvy.profiler.domain.usecases.interfaces.IGetDeviceInfoUseCase
    public DeviceInfo execute() {
        return this.deviceInfoRepository.getDeviceInfo();
    }
}
