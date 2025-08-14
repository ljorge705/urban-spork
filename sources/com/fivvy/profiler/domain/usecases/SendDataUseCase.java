package com.fivvy.profiler.domain.usecases;

import androidx.work.Data;
import com.fivvy.profiler.data.repositories.SendDataRepositoryImpl;
import com.fivvy.profiler.domain.repositories.ISendDataRepository;
import com.fivvy.profiler.domain.usecases.interfaces.ISendDataUseCase;
import java.util.Map;

/* loaded from: classes5.dex */
public class SendDataUseCase implements ISendDataUseCase {
    private final ISendDataRepository sendDataRepository;

    public SendDataUseCase(SendDataRepositoryImpl sendDataRepositoryImpl) {
        this.sendDataRepository = sendDataRepositoryImpl;
    }

    @Override // com.fivvy.profiler.domain.usecases.interfaces.ISendDataUseCase
    public Data execute(Map<String, Object> map, String str, String str2, String str3, String str4) {
        return this.sendDataRepository.scheduleBackgroundTask(map, str, str2, str3, str4);
    }
}
