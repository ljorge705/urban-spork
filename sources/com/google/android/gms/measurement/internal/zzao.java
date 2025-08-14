package com.google.android.gms.measurement.internal;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import androidx.core.content.ContextCompat;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.3.0 */
/* loaded from: classes3.dex */
public final class zzao extends zzgx {
    private long zza;
    private String zzb;
    private AccountManager zzc;
    private Boolean zzd;
    private long zze;

    zzao(zzgd zzgdVar) {
        super(zzgdVar);
    }

    final long zza() {
        zzg();
        return this.zze;
    }

    public final long zzb() {
        zzv();
        return this.zza;
    }

    public final String zzc() {
        zzv();
        return this.zzb;
    }

    final void zzd() {
        zzg();
        this.zzd = null;
        this.zze = 0L;
    }

    final boolean zze() throws OperationCanceledException, IOException, AuthenticatorException {
        Account[] result;
        zzg();
        long jCurrentTimeMillis = this.zzt.zzax().currentTimeMillis();
        if (jCurrentTimeMillis - this.zze > 86400000) {
            this.zzd = null;
        }
        Boolean bool = this.zzd;
        if (bool != null) {
            return bool.booleanValue();
        }
        if (ContextCompat.checkSelfPermission(this.zzt.zzaw(), "android.permission.GET_ACCOUNTS") != 0) {
            this.zzt.zzaA().zzm().zza("Permission error checking for dasher/unicorn accounts");
            this.zze = jCurrentTimeMillis;
            this.zzd = false;
            return false;
        }
        if (this.zzc == null) {
            this.zzc = AccountManager.get(this.zzt.zzaw());
        }
        try {
            result = this.zzc.getAccountsByTypeAndFeatures("com.google", new String[]{"service_HOSTED"}, null, null).getResult();
        } catch (AuthenticatorException | OperationCanceledException | IOException e) {
            this.zzt.zzaA().zzh().zzb("Exception checking account types", e);
        }
        if (result != null && result.length > 0) {
            this.zzd = true;
            this.zze = jCurrentTimeMillis;
            return true;
        }
        Account[] result2 = this.zzc.getAccountsByTypeAndFeatures("com.google", new String[]{"service_uca"}, null, null).getResult();
        if (result2 != null && result2.length > 0) {
            this.zzd = true;
            this.zze = jCurrentTimeMillis;
            return true;
        }
        this.zze = jCurrentTimeMillis;
        this.zzd = false;
        return false;
    }

    @Override // com.google.android.gms.measurement.internal.zzgx
    protected final boolean zzf() {
        Calendar calendar = Calendar.getInstance();
        this.zza = TimeUnit.MINUTES.convert(calendar.get(15) + calendar.get(16), TimeUnit.MILLISECONDS);
        Locale locale = Locale.getDefault();
        this.zzb = locale.getLanguage().toLowerCase(Locale.ENGLISH) + "-" + locale.getCountry().toLowerCase(Locale.ENGLISH);
        return false;
    }
}
