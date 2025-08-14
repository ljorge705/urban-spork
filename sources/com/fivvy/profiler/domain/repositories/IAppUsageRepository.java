package com.fivvy.profiler.domain.repositories;

import com.fivvy.profiler.domain.models.AppUsageInfo;
import java.util.List;

/* loaded from: classes5.dex */
public interface IAppUsageRepository {
    List<AppUsageInfo> getAppUsage(int i);

    void goToSettingsAndTransparentActivity(String str, String str2, String str3, byte[] bArr, String str4);

    void goToSettingsAndTransparentActivityNative(String str, String str2, String str3, byte[] bArr, String str4);

    void goToSettingsDirectly();
}
