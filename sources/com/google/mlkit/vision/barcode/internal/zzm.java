package com.google.mlkit.vision.barcode.internal;

import android.graphics.Point;
import android.graphics.Rect;
import com.google.android.gms.internal.mlkit_vision_barcode.zzo;
import com.google.android.gms.internal.mlkit_vision_barcode.zzp;
import com.google.android.gms.internal.mlkit_vision_barcode.zzq;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.barcode.common.internal.BarcodeSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.0.0 */
/* loaded from: classes2.dex */
public final class zzm implements BarcodeSource {
    private final zzq zza;

    public zzm(zzq zzqVar) {
        this.zza = zzqVar;
    }

    private static Barcode.CalendarDateTime zza(com.google.android.gms.internal.mlkit_vision_barcode.zzf zzfVar) {
        if (zzfVar == null) {
            return null;
        }
        return new Barcode.CalendarDateTime(zzfVar.zza, zzfVar.zzb, zzfVar.zzc, zzfVar.zzd, zzfVar.zze, zzfVar.zzf, zzfVar.zzg, zzfVar.zzh);
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    public final Rect getBoundingBox() {
        zzq zzqVar = this.zza;
        if (zzqVar.zze == null) {
            return null;
        }
        int iMax = Integer.MIN_VALUE;
        int iMin = Integer.MAX_VALUE;
        int i = 0;
        int iMax2 = Integer.MIN_VALUE;
        int iMin2 = Integer.MAX_VALUE;
        while (true) {
            Point[] pointArr = zzqVar.zze;
            if (i >= pointArr.length) {
                return new Rect(iMin2, iMin, iMax, iMax2);
            }
            Point point = pointArr[i];
            iMin2 = Math.min(iMin2, point.x);
            iMax = Math.max(iMax, point.x);
            iMin = Math.min(iMin, point.y);
            iMax2 = Math.max(iMax2, point.y);
            i++;
        }
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    public final Barcode.CalendarEvent getCalendarEvent() {
        com.google.android.gms.internal.mlkit_vision_barcode.zzg zzgVar = this.zza.zzl;
        if (zzgVar == null) {
            return null;
        }
        return new Barcode.CalendarEvent(zzgVar.zza, zzgVar.zzb, zzgVar.zzc, zzgVar.zzd, zzgVar.zze, zza(zzgVar.zzf), zza(zzgVar.zzg));
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    public final Barcode.ContactInfo getContactInfo() {
        com.google.android.gms.internal.mlkit_vision_barcode.zzh zzhVar = this.zza.zzm;
        if (zzhVar == null) {
            return null;
        }
        com.google.android.gms.internal.mlkit_vision_barcode.zzl zzlVar = zzhVar.zza;
        Barcode.PersonName personName = zzlVar != null ? new Barcode.PersonName(zzlVar.zza, zzlVar.zzb, zzlVar.zzc, zzlVar.zzd, zzlVar.zze, zzlVar.zzf, zzlVar.zzg) : null;
        String str = zzhVar.zzb;
        String str2 = zzhVar.zzc;
        com.google.android.gms.internal.mlkit_vision_barcode.zzm[] zzmVarArr = zzhVar.zzd;
        ArrayList arrayList = new ArrayList();
        if (zzmVarArr != null) {
            for (com.google.android.gms.internal.mlkit_vision_barcode.zzm zzmVar : zzmVarArr) {
                if (zzmVar != null) {
                    arrayList.add(new Barcode.Phone(zzmVar.zzb, zzmVar.zza));
                }
            }
        }
        com.google.android.gms.internal.mlkit_vision_barcode.zzj[] zzjVarArr = zzhVar.zze;
        ArrayList arrayList2 = new ArrayList();
        if (zzjVarArr != null) {
            for (com.google.android.gms.internal.mlkit_vision_barcode.zzj zzjVar : zzjVarArr) {
                if (zzjVar != null) {
                    arrayList2.add(new Barcode.Email(zzjVar.zza, zzjVar.zzb, zzjVar.zzc, zzjVar.zzd));
                }
            }
        }
        String[] strArr = zzhVar.zzf;
        List listAsList = strArr != null ? Arrays.asList(strArr) : new ArrayList();
        com.google.android.gms.internal.mlkit_vision_barcode.zze[] zzeVarArr = zzhVar.zzg;
        ArrayList arrayList3 = new ArrayList();
        if (zzeVarArr != null) {
            for (com.google.android.gms.internal.mlkit_vision_barcode.zze zzeVar : zzeVarArr) {
                if (zzeVar != null) {
                    arrayList3.add(new Barcode.Address(zzeVar.zza, zzeVar.zzb));
                }
            }
        }
        return new Barcode.ContactInfo(personName, str, str2, arrayList, arrayList2, listAsList, arrayList3);
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    public final Point[] getCornerPoints() {
        return this.zza.zze;
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    public final String getDisplayValue() {
        return this.zza.zzc;
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    public final Barcode.DriverLicense getDriverLicense() {
        com.google.android.gms.internal.mlkit_vision_barcode.zzi zziVar = this.zza.zzn;
        if (zziVar == null) {
            return null;
        }
        return new Barcode.DriverLicense(zziVar.zza, zziVar.zzb, zziVar.zzc, zziVar.zzd, zziVar.zze, zziVar.zzf, zziVar.zzg, zziVar.zzh, zziVar.zzi, zziVar.zzj, zziVar.zzk, zziVar.zzl, zziVar.zzm, zziVar.zzn);
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    public final Barcode.Email getEmail() {
        com.google.android.gms.internal.mlkit_vision_barcode.zzj zzjVar = this.zza.zzf;
        if (zzjVar != null) {
            return new Barcode.Email(zzjVar.zza, zzjVar.zzb, zzjVar.zzc, zzjVar.zzd);
        }
        return null;
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    public final int getFormat() {
        return this.zza.zza;
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    public final Barcode.GeoPoint getGeoPoint() {
        com.google.android.gms.internal.mlkit_vision_barcode.zzk zzkVar = this.zza.zzk;
        if (zzkVar != null) {
            return new Barcode.GeoPoint(zzkVar.zza, zzkVar.zzb);
        }
        return null;
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    public final Barcode.Phone getPhone() {
        com.google.android.gms.internal.mlkit_vision_barcode.zzm zzmVar = this.zza.zzg;
        if (zzmVar != null) {
            return new Barcode.Phone(zzmVar.zzb, zzmVar.zza);
        }
        return null;
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    public final byte[] getRawBytes() {
        return this.zza.zzo;
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    public final String getRawValue() {
        return this.zza.zzb;
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    public final Barcode.Sms getSms() {
        com.google.android.gms.internal.mlkit_vision_barcode.zzn zznVar = this.zza.zzh;
        if (zznVar != null) {
            return new Barcode.Sms(zznVar.zza, zznVar.zzb);
        }
        return null;
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    public final Barcode.UrlBookmark getUrl() {
        zzo zzoVar = this.zza.zzj;
        if (zzoVar != null) {
            return new Barcode.UrlBookmark(zzoVar.zza, zzoVar.zzb);
        }
        return null;
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    public final int getValueType() {
        return this.zza.zzd;
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    public final Barcode.WiFi getWifi() {
        zzp zzpVar = this.zza.zzi;
        if (zzpVar != null) {
            return new Barcode.WiFi(zzpVar.zza, zzpVar.zzb, zzpVar.zzc);
        }
        return null;
    }
}
