package com.google.android.gms.measurement.internal;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;
import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;

/* compiled from: com.google.android.gms:play-services-measurement@@21.3.0 */
/* loaded from: classes3.dex */
public final class zzks extends zzku {
    private final AlarmManager zza;
    private zzan zzb;
    private Integer zzc;

    protected zzks(zzlh zzlhVar) {
        super(zzlhVar);
        this.zza = (AlarmManager) this.zzt.zzaw().getSystemService(NotificationCompat.CATEGORY_ALARM);
    }

    private final int zzf() {
        if (this.zzc == null) {
            this.zzc = Integer.valueOf("measurement".concat(String.valueOf(this.zzt.zzaw().getPackageName())).hashCode());
        }
        return this.zzc.intValue();
    }

    private final PendingIntent zzh() {
        Context contextZzaw = this.zzt.zzaw();
        return PendingIntent.getBroadcast(contextZzaw, 0, new Intent().setClassName(contextZzaw, "com.google.android.gms.measurement.AppMeasurementReceiver").setAction("com.google.android.gms.measurement.UPLOAD"), com.google.android.gms.internal.measurement.zzbs.zza);
    }

    private final zzan zzi() {
        if (this.zzb == null) {
            this.zzb = new zzkr(this, this.zzf.zzp());
        }
        return this.zzb;
    }

    private final void zzj() {
        JobScheduler jobScheduler = (JobScheduler) this.zzt.zzaw().getSystemService("jobscheduler");
        if (jobScheduler != null) {
            jobScheduler.cancel(zzf());
        }
    }

    public final void zza() {
        zzW();
        this.zzt.zzaA().zzj().zza("Unscheduling upload");
        AlarmManager alarmManager = this.zza;
        if (alarmManager != null) {
            alarmManager.cancel(zzh());
        }
        zzi().zzb();
        zzj();
    }

    @Override // com.google.android.gms.measurement.internal.zzku
    protected final boolean zzb() {
        AlarmManager alarmManager = this.zza;
        if (alarmManager != null) {
            alarmManager.cancel(zzh());
        }
        zzj();
        return false;
    }

    public final void zzd(long j) {
        zzW();
        this.zzt.zzay();
        Context contextZzaw = this.zzt.zzaw();
        if (!zzlp.zzal(contextZzaw)) {
            this.zzt.zzaA().zzc().zza("Receiver not registered/enabled");
        }
        if (!zzlp.zzam(contextZzaw, false)) {
            this.zzt.zzaA().zzc().zza("Service not registered/enabled");
        }
        zza();
        this.zzt.zzaA().zzj().zzb("Scheduling upload, millis", Long.valueOf(j));
        this.zzt.zzax().elapsedRealtime();
        this.zzt.zzf();
        if (j < Math.max(0L, ((Long) zzeg.zzx.zza(null)).longValue()) && !zzi().zze()) {
            zzi().zzd(j);
        }
        this.zzt.zzay();
        Context contextZzaw2 = this.zzt.zzaw();
        ComponentName componentName = new ComponentName(contextZzaw2, "com.google.android.gms.measurement.AppMeasurementJobService");
        int iZzf = zzf();
        PersistableBundle persistableBundle = new PersistableBundle();
        persistableBundle.putString(Constants.KEY_ACTION, "com.google.android.gms.measurement.UPLOAD");
        com.google.android.gms.internal.measurement.zzbt.zza(contextZzaw2, new JobInfo.Builder(iZzf, componentName).setMinimumLatency(j).setOverrideDeadline(j + j).setExtras(persistableBundle).build(), "com.google.android.gms", "UploadAlarm");
    }
}
