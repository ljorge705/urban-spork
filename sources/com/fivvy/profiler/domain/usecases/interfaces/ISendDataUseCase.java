package com.fivvy.profiler.domain.usecases.interfaces;

import androidx.work.Data;
import java.util.Map;

/* loaded from: classes5.dex */
public interface ISendDataUseCase {
    Data execute(Map<String, Object> map, String str, String str2, String str3, String str4);
}
