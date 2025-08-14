package com.google.android.play.core.splitinstall;

import android.app.PendingIntent;
import java.util.List;

/* compiled from: com.google.android.play:feature-delivery@@2.1.0 */
/* loaded from: classes3.dex */
final class zza extends SplitInstallSessionState {
    private final int zza;
    private final int zzb;
    private final int zzc;
    private final long zzd;
    private final long zze;
    private final List zzf;
    private final List zzg;
    private final PendingIntent zzh;
    private final List zzi;

    zza(int i, int i2, int i3, long j, long j2, List list, List list2, PendingIntent pendingIntent, List list3) {
        this.zza = i;
        this.zzb = i2;
        this.zzc = i3;
        this.zzd = j;
        this.zze = j2;
        this.zzf = list;
        this.zzg = list2;
        this.zzh = pendingIntent;
        this.zzi = list3;
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallSessionState
    public final long bytesDownloaded() {
        return this.zzd;
    }

    public final boolean equals(Object obj) {
        List list;
        List list2;
        PendingIntent pendingIntent;
        List list3;
        if (obj == this) {
            return true;
        }
        if (obj instanceof SplitInstallSessionState) {
            SplitInstallSessionState splitInstallSessionState = (SplitInstallSessionState) obj;
            if (this.zza == splitInstallSessionState.sessionId() && this.zzb == splitInstallSessionState.status() && this.zzc == splitInstallSessionState.errorCode() && this.zzd == splitInstallSessionState.bytesDownloaded() && this.zze == splitInstallSessionState.totalBytesToDownload() && ((list = this.zzf) != null ? list.equals(splitInstallSessionState.zzb()) : splitInstallSessionState.zzb() == null) && ((list2 = this.zzg) != null ? list2.equals(splitInstallSessionState.zza()) : splitInstallSessionState.zza() == null) && ((pendingIntent = this.zzh) != null ? pendingIntent.equals(splitInstallSessionState.resolutionIntent()) : splitInstallSessionState.resolutionIntent() == null) && ((list3 = this.zzi) != null ? list3.equals(splitInstallSessionState.zzc()) : splitInstallSessionState.zzc() == null)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallSessionState
    public final int errorCode() {
        return this.zzc;
    }

    public final int hashCode() {
        int i = ((((this.zza ^ 1000003) * 1000003) ^ this.zzb) * 1000003) ^ this.zzc;
        long j = this.zzd;
        long j2 = j ^ (j >>> 32);
        long j3 = this.zze;
        long j4 = (j3 >>> 32) ^ j3;
        List list = this.zzf;
        int iHashCode = ((((((i * 1000003) ^ ((int) j2)) * 1000003) ^ ((int) j4)) * 1000003) ^ (list == null ? 0 : list.hashCode())) * 1000003;
        List list2 = this.zzg;
        int iHashCode2 = (iHashCode ^ (list2 == null ? 0 : list2.hashCode())) * 1000003;
        PendingIntent pendingIntent = this.zzh;
        int iHashCode3 = (iHashCode2 ^ (pendingIntent == null ? 0 : pendingIntent.hashCode())) * 1000003;
        List list3 = this.zzi;
        return iHashCode3 ^ (list3 != null ? list3.hashCode() : 0);
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallSessionState
    @Deprecated
    public final PendingIntent resolutionIntent() {
        return this.zzh;
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallSessionState
    public final int sessionId() {
        return this.zza;
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallSessionState
    public final int status() {
        return this.zzb;
    }

    public final String toString() {
        return "SplitInstallSessionState{sessionId=" + this.zza + ", status=" + this.zzb + ", errorCode=" + this.zzc + ", bytesDownloaded=" + this.zzd + ", totalBytesToDownload=" + this.zze + ", moduleNamesNullable=" + String.valueOf(this.zzf) + ", languagesNullable=" + String.valueOf(this.zzg) + ", resolutionIntent=" + String.valueOf(this.zzh) + ", splitFileIntents=" + String.valueOf(this.zzi) + "}";
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallSessionState
    public final long totalBytesToDownload() {
        return this.zze;
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallSessionState
    final List zza() {
        return this.zzg;
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallSessionState
    final List zzb() {
        return this.zzf;
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallSessionState
    final List zzc() {
        return this.zzi;
    }
}
