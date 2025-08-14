package com.google.android.gms.internal.measurement;

import androidx.core.app.NotificationCompat;
import com.onfido.android.sdk.capture.internal.util.logging.OnfidoLogMapper;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@21.3.0 */
/* loaded from: classes3.dex */
public final class zzt extends zzai {
    private final zzr zza;

    public zzt(zzr zzrVar) {
        super("internal.logger");
        this.zza = zzrVar;
        this.zze.put(OnfidoLogMapper.LOG_EVENT_TYPE, new zzs(this, false, true));
        this.zze.put(NotificationCompat.GROUP_KEY_SILENT, new zzp(this, NotificationCompat.GROUP_KEY_SILENT));
        ((zzai) this.zze.get(NotificationCompat.GROUP_KEY_SILENT)).zzr(OnfidoLogMapper.LOG_EVENT_TYPE, new zzs(this, true, true));
        this.zze.put("unmonitored", new zzq(this, "unmonitored"));
        ((zzai) this.zze.get("unmonitored")).zzr(OnfidoLogMapper.LOG_EVENT_TYPE, new zzs(this, false, false));
    }

    @Override // com.google.android.gms.internal.measurement.zzai
    public final zzap zza(zzg zzgVar, List list) {
        return zzap.zzf;
    }
}
