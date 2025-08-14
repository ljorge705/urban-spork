package com.fivvy.profiler.data.repositories;

import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import com.fivvy.profiler.data.handlers.ReduceAppUsageHandler;
import com.fivvy.profiler.domain.models.AppUsageInfo;
import com.fivvy.profiler.domain.repositories.IAppUsageRepository;
import com.fivvy.profiler.presenter.TransparentActivity;
import io.sentry.protocol.SentryStackFrame;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class AppUsageRepositoryImpl implements IAppUsageRepository {
    private final Context context;

    public AppUsageRepositoryImpl(Context context) {
        this.context = context;
    }

    @Override // com.fivvy.profiler.domain.repositories.IAppUsageRepository
    public List<AppUsageInfo> getAppUsage(int i) {
        List<ResolveInfo> listQueryIntentActivities;
        UsageStatsManager usageStatsManager = (UsageStatsManager) this.context.getSystemService("usagestats");
        Calendar calendar = Calendar.getInstance();
        calendar.add(5, -i);
        List<UsageStats> listQueryUsageStats = usageStatsManager.queryUsageStats(4, calendar.getTimeInMillis(), System.currentTimeMillis());
        PackageManager packageManager = this.context.getPackageManager();
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        intent.addCategory("android.intent.category.LAUNCHER");
        if (Build.VERSION.SDK_INT >= 33) {
            listQueryIntentActivities = packageManager.queryIntentActivities(intent, 131072);
        } else {
            listQueryIntentActivities = packageManager.queryIntentActivities(intent, 128);
        }
        ArrayList arrayList = new ArrayList();
        System.out.println(listQueryUsageStats);
        for (UsageStats usageStats : listQueryUsageStats) {
            Iterator<ResolveInfo> it = listQueryIntentActivities.iterator();
            while (true) {
                if (it.hasNext()) {
                    ResolveInfo next = it.next();
                    if (next.activityInfo.packageName.equals(usageStats.getPackageName())) {
                        arrayList.add(new AppUsageInfo(next.loadLabel(packageManager).toString(), next.activityInfo.packageName, usageStats.getTotalTimeInForeground() / 60000.0d));
                        break;
                    }
                }
            }
        }
        return ReduceAppUsageHandler.reduceAppUsage(arrayList);
    }

    @Override // com.fivvy.profiler.domain.repositories.IAppUsageRepository
    public void goToSettingsAndTransparentActivity(final String str, final String str2, final String str3, final byte[] bArr, final String str4) {
        if (Build.VERSION.SDK_INT < 29) {
            openUsageAccessSettings();
            return;
        }
        try {
            Intent intent = new Intent("android.settings.USAGE_ACCESS_SETTINGS");
            Uri uriFromParts = Uri.fromParts(SentryStackFrame.JsonKeys.PACKAGE, this.context.getPackageName(), null);
            intent.setFlags(1082130432);
            intent.setData(uriFromParts);
            this.context.startActivity(intent);
            try {
                new Handler().postDelayed(new Runnable() { // from class: com.fivvy.profiler.data.repositories.AppUsageRepositoryImpl$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.m5126xfabf6202(str, str2, str3, bArr, str4);
                    }
                }, 500L);
            } catch (Exception e) {
                Log.e("TransparentActivity", "Error al iniciar TransparentActivity", e);
            }
        } catch (Exception e2) {
            Log.e("SettingsActivity", "Ocurrio un error inesperado", e2);
            openUsageAccessSettings();
        }
    }

    /* renamed from: lambda$goToSettingsAndTransparentActivity$0$com-fivvy-profiler-data-repositories-AppUsageRepositoryImpl, reason: not valid java name */
    /* synthetic */ void m5126xfabf6202(String str, String str2, String str3, byte[] bArr, String str4) {
        Intent intent = new Intent(this.context, (Class<?>) TransparentActivity.class);
        intent.addFlags(1216348160);
        intent.putExtra("EXTRA_PACKAGE_NAME", this.context.getPackageName());
        intent.putExtra("LN", str);
        intent.putExtra("APP_NAME", str2);
        intent.putExtra("APP_DESCRIPTION", str3);
        intent.putExtra("IMAGE_PATH", bArr);
        intent.putExtra("MODAL_TEXT", str4);
        this.context.startActivity(intent);
    }

    @Override // com.fivvy.profiler.domain.repositories.IAppUsageRepository
    public void goToSettingsAndTransparentActivityNative(final String str, final String str2, final String str3, final byte[] bArr, final String str4) {
        if (Build.VERSION.SDK_INT < 29) {
            openUsageAccessSettings();
            return;
        }
        try {
            Intent intent = new Intent("android.settings.USAGE_ACCESS_SETTINGS");
            Uri uriFromParts = Uri.fromParts(SentryStackFrame.JsonKeys.PACKAGE, this.context.getPackageName(), null);
            intent.setFlags(1350565888);
            intent.setData(uriFromParts);
            this.context.startActivity(intent);
            try {
                new Handler().postDelayed(new Runnable() { // from class: com.fivvy.profiler.data.repositories.AppUsageRepositoryImpl$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.m5127x94819aba(str, str2, str3, bArr, str4);
                    }
                }, 500L);
            } catch (Exception e) {
                Log.e("TransparentActivity", "Error al iniciar TransparentActivity", e);
            }
        } catch (Exception e2) {
            Log.e("SettingsActivity", "Ocurrio un error inesperado", e2);
            openUsageAccessSettings();
        }
    }

    /* renamed from: lambda$goToSettingsAndTransparentActivityNative$1$com-fivvy-profiler-data-repositories-AppUsageRepositoryImpl, reason: not valid java name */
    /* synthetic */ void m5127x94819aba(String str, String str2, String str3, byte[] bArr, String str4) {
        Intent intent = new Intent(this.context, (Class<?>) TransparentActivity.class);
        intent.putExtra("EXTRA_PACKAGE_NAME", this.context.getPackageName());
        intent.putExtra("LN", str);
        intent.putExtra("APP_NAME", str2);
        intent.putExtra("APP_DESCRIPTION", str3);
        intent.putExtra("IMAGE_PATH", bArr);
        intent.putExtra("MODAL_TEXT", str4);
        this.context.startActivity(intent);
    }

    @Override // com.fivvy.profiler.domain.repositories.IAppUsageRepository
    public void goToSettingsDirectly() {
        try {
            Intent intent = new Intent("android.settings.USAGE_ACCESS_SETTINGS");
            intent.setData(Uri.fromParts(SentryStackFrame.JsonKeys.PACKAGE, this.context.getPackageName(), null));
            this.context.startActivity(intent);
        } catch (Exception e) {
            Log.e("SettingsActivity", "Ocurrio un error inesperado", e);
            openUsageAccessSettings();
        }
    }

    private void openUsageAccessSettings() {
        try {
            Intent intent = new Intent("android.settings.USAGE_ACCESS_SETTINGS");
            intent.setFlags(1350565888);
            if (intent.resolveActivity(this.context.getPackageManager()) != null) {
                this.context.startActivity(intent);
            }
        } catch (Exception e) {
            Log.d("openUsageAccessSettings", e.toString());
        }
    }
}
