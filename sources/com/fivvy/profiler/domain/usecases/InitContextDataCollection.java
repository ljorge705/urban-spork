package com.fivvy.profiler.domain.usecases;

import android.content.Context;
import com.clevertap.android.sdk.Constants;
import com.fivvy.profiler.application.UseCaseService;
import com.fivvy.profiler.domain.models.AppInstalledInfo;
import com.fivvy.profiler.domain.models.AppUsageInfo;
import com.fivvy.profiler.domain.models.DeviceInfo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class InitContextDataCollection {
    private UseCaseService useCaseService;

    public InitContextDataCollection(Context context) {
        this.useCaseService = new UseCaseService(context);
    }

    public Map<String, Object> execute(String str, String str2, int i) {
        HashMap map = new HashMap();
        List<AppUsageInfo> listCreateGetAppUsageUseCase = this.useCaseService.createGetAppUsageUseCase(i);
        DeviceInfo deviceInfoCreateGetDeviceInfoUseCase = this.useCaseService.createGetDeviceInfoUseCase();
        List<AppInstalledInfo> listCreateGetInstalledAppsUseCase = this.useCaseService.createGetInstalledAppsUseCase();
        map.put(Constants.DEVICE_ID_TAG, deviceInfoCreateGetDeviceInfoUseCase.getDeviceId());
        if (listCreateGetAppUsageUseCase.isEmpty()) {
            map.put("appUsage", null);
        } else {
            map.put("appUsage", listCreateGetAppUsageUseCase);
        }
        map.put("customerId", str2);
        map.put("deviceInfo", deviceInfoCreateGetDeviceInfoUseCase);
        map.put("installedAppsInfo", listCreateGetInstalledAppsUseCase);
        return map;
    }
}
