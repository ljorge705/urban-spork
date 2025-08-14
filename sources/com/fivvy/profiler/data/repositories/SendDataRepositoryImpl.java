package com.fivvy.profiler.data.repositories;

import android.content.Context;
import android.util.Log;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.work.Data;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.ListenableWorker;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import com.fivvy.profiler.domain.repositories.ISendDataRepository;
import com.google.gson.Gson;
import io.sentry.SentryEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
public class SendDataRepositoryImpl implements ISendDataRepository {
    private static final int BACKGROUND_TASK_INTERVAL = 1440;
    private final Context context;
    private String contextualDataJson;
    private Data workerStatus;

    public SendDataRepositoryImpl(Context context) {
        this.context = context;
    }

    @Override // com.fivvy.profiler.domain.repositories.ISendDataRepository
    public Data scheduleBackgroundTask(Map<String, Object> map, String str, String str2, String str3, String str4) throws Throwable {
        try {
            WorkManager workManager = WorkManager.getInstance(this.context);
            workManager.cancelAllWork();
            setJson(map);
            PeriodicWorkRequest periodicWorkRequestBuild = new PeriodicWorkRequest.Builder((Class<? extends ListenableWorker>) BackgroundTask.class, 1440L, TimeUnit.MINUTES).setInputData(new Data.Builder().putString("data_file_path", saveDataToFile(this.contextualDataJson).getAbsolutePath()).putString("api-key", str).putString("api-secret", str2).putString("authApiUrl", str3).putString("sendDataApiUrl", str4).build()).build();
            workManager.enqueueUniquePeriodicWork("backgroundTaskUnique", ExistingPeriodicWorkPolicy.KEEP, periodicWorkRequestBuild);
            workManager.getWorkInfoByIdLiveData(periodicWorkRequestBuild.getId()).observe((LifecycleOwner) this.context, new Observer() { // from class: com.fivvy.profiler.data.repositories.SendDataRepositoryImpl$$ExternalSyntheticLambda0
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    this.f$0.m5128xb2c28c8e((WorkInfo) obj);
                }
            });
            return this.workerStatus;
        } catch (NullPointerException e) {
            Log.d(SentryEvent.JsonKeys.EXCEPTION, e.toString());
            return new Data.Builder().putString("message", "work finished").build();
        } catch (Exception e2) {
            Log.d(SentryEvent.JsonKeys.EXCEPTION, e2.toString());
            return new Data.Builder().putString("message", "work finished").build();
        }
    }

    /* renamed from: lambda$scheduleBackgroundTask$0$com-fivvy-profiler-data-repositories-SendDataRepositoryImpl, reason: not valid java name */
    /* synthetic */ void m5128xb2c28c8e(WorkInfo workInfo) {
        if (workInfo != null) {
            if (workInfo.getState() == WorkInfo.State.SUCCEEDED || workInfo.getState() == WorkInfo.State.ENQUEUED || workInfo.getState() == WorkInfo.State.FAILED) {
                this.workerStatus = workInfo.getOutputData();
            }
        }
    }

    private File saveDataToFile(String str) {
        try {
            File file = new File(this.context.getFilesDir(), "contextual_data.json");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(str);
            fileWriter.flush();
            fileWriter.close();
            return file;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void setJson(Map<String, Object> map) {
        this.contextualDataJson = new Gson().toJson(map);
    }
}
