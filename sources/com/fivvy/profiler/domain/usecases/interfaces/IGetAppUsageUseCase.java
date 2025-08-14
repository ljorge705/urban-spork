package com.fivvy.profiler.domain.usecases.interfaces;

import com.fivvy.profiler.domain.models.AppUsageInfo;
import java.util.List;

/* loaded from: classes5.dex */
public interface IGetAppUsageUseCase {
    List<AppUsageInfo> execute(int i);
}
