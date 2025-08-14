package com.google.mlkit.vision.barcode.internal;

import android.graphics.Point;
import android.graphics.Rect;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_vision_barcode.zzob;
import com.google.android.gms.internal.mlkit_vision_barcode.zzoc;
import com.google.android.gms.internal.mlkit_vision_barcode.zzod;
import com.google.android.gms.internal.mlkit_vision_barcode.zzoe;
import com.google.android.gms.internal.mlkit_vision_barcode.zzof;
import com.google.android.gms.internal.mlkit_vision_barcode.zzog;
import com.google.android.gms.internal.mlkit_vision_barcode.zzoh;
import com.google.android.gms.internal.mlkit_vision_barcode.zzoi;
import com.google.android.gms.internal.mlkit_vision_barcode.zzoj;
import com.google.android.gms.internal.mlkit_vision_barcode.zzok;
import com.google.android.gms.internal.mlkit_vision_barcode.zzol;
import com.google.android.gms.internal.mlkit_vision_barcode.zzom;
import com.google.android.gms.internal.mlkit_vision_barcode.zzon;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.barcode.common.internal.BarcodeSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.0.0 */
/* loaded from: classes2.dex */
public final class zzk implements BarcodeSource {
    private final zzon zza;

    public zzk(zzon zzonVar) {
        this.zza = zzonVar;
    }

    private static Barcode.CalendarDateTime zza(zzoc zzocVar) {
        if (zzocVar == null) {
            return null;
        }
        return new Barcode.CalendarDateTime(zzocVar.zzf(), zzocVar.zzd(), zzocVar.zza(), zzocVar.zzb(), zzocVar.zzc(), zzocVar.zze(), zzocVar.zzh(), zzocVar.zzg());
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    public final Rect getBoundingBox() {
        Point[] pointArrZzo = this.zza.zzo();
        if (pointArrZzo == null) {
            return null;
        }
        int iMax = Integer.MIN_VALUE;
        int iMin = Integer.MAX_VALUE;
        int iMax2 = Integer.MIN_VALUE;
        int iMin2 = Integer.MAX_VALUE;
        for (Point point : pointArrZzo) {
            iMin2 = Math.min(iMin2, point.x);
            iMax = Math.max(iMax, point.x);
            iMin = Math.min(iMin, point.y);
            iMax2 = Math.max(iMax2, point.y);
        }
        return new Rect(iMin2, iMin, iMax, iMax2);
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    public final Barcode.CalendarEvent getCalendarEvent() {
        zzod zzodVarZzc = this.zza.zzc();
        if (zzodVarZzc != null) {
            return new Barcode.CalendarEvent(zzodVarZzc.zzg(), zzodVarZzc.zzc(), zzodVarZzc.zzd(), zzodVarZzc.zze(), zzodVarZzc.zzf(), zza(zzodVarZzc.zzb()), zza(zzodVarZzc.zza()));
        }
        return null;
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    public final Barcode.ContactInfo getContactInfo() {
        zzoe zzoeVarZzd = this.zza.zzd();
        if (zzoeVarZzd == null) {
            return null;
        }
        zzoi zzoiVarZza = zzoeVarZzd.zza();
        Barcode.PersonName personName = zzoiVarZza != null ? new Barcode.PersonName(zzoiVarZza.zzb(), zzoiVarZza.zzf(), zzoiVarZza.zze(), zzoiVarZza.zza(), zzoiVarZza.zzd(), zzoiVarZza.zzc(), zzoiVarZza.zzg()) : null;
        String strZzb = zzoeVarZzd.zzb();
        String strZzc = zzoeVarZzd.zzc();
        zzoj[] zzojVarArrZzf = zzoeVarZzd.zzf();
        ArrayList arrayList = new ArrayList();
        if (zzojVarArrZzf != null) {
            for (zzoj zzojVar : zzojVarArrZzf) {
                if (zzojVar != null) {
                    arrayList.add(new Barcode.Phone(zzojVar.zzb(), zzojVar.zza()));
                }
            }
        }
        zzog[] zzogVarArrZze = zzoeVarZzd.zze();
        ArrayList arrayList2 = new ArrayList();
        if (zzogVarArrZze != null) {
            for (zzog zzogVar : zzogVarArrZze) {
                if (zzogVar != null) {
                    arrayList2.add(new Barcode.Email(zzogVar.zza(), zzogVar.zzb(), zzogVar.zzd(), zzogVar.zzc()));
                }
            }
        }
        List listAsList = zzoeVarZzd.zzg() != null ? Arrays.asList((String[]) Preconditions.checkNotNull(zzoeVarZzd.zzg())) : new ArrayList();
        zzob[] zzobVarArrZzd = zzoeVarZzd.zzd();
        ArrayList arrayList3 = new ArrayList();
        if (zzobVarArrZzd != null) {
            for (zzob zzobVar : zzobVarArrZzd) {
                if (zzobVar != null) {
                    arrayList3.add(new Barcode.Address(zzobVar.zza(), zzobVar.zzb()));
                }
            }
        }
        return new Barcode.ContactInfo(personName, strZzb, strZzc, arrayList, arrayList2, listAsList, arrayList3);
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    public final Point[] getCornerPoints() {
        return this.zza.zzo();
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    public final String getDisplayValue() {
        return this.zza.zzl();
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    public final Barcode.DriverLicense getDriverLicense() {
        zzof zzofVarZze = this.zza.zze();
        if (zzofVarZze != null) {
            return new Barcode.DriverLicense(zzofVarZze.zzf(), zzofVarZze.zzh(), zzofVarZze.zzn(), zzofVarZze.zzl(), zzofVarZze.zzi(), zzofVarZze.zzc(), zzofVarZze.zza(), zzofVarZze.zzb(), zzofVarZze.zzd(), zzofVarZze.zzm(), zzofVarZze.zzj(), zzofVarZze.zzg(), zzofVarZze.zze(), zzofVarZze.zzk());
        }
        return null;
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    public final Barcode.Email getEmail() {
        zzog zzogVarZzf = this.zza.zzf();
        if (zzogVarZzf == null) {
            return null;
        }
        return new Barcode.Email(zzogVarZzf.zza(), zzogVarZzf.zzb(), zzogVarZzf.zzd(), zzogVarZzf.zzc());
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    public final int getFormat() {
        return this.zza.zza();
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    public final Barcode.GeoPoint getGeoPoint() {
        zzoh zzohVarZzg = this.zza.zzg();
        if (zzohVarZzg != null) {
            return new Barcode.GeoPoint(zzohVarZzg.zza(), zzohVarZzg.zzb());
        }
        return null;
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    public final Barcode.Phone getPhone() {
        zzoj zzojVarZzh = this.zza.zzh();
        if (zzojVarZzh != null) {
            return new Barcode.Phone(zzojVarZzh.zzb(), zzojVarZzh.zza());
        }
        return null;
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    public final byte[] getRawBytes() {
        return this.zza.zzn();
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    public final String getRawValue() {
        return this.zza.zzm();
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    public final Barcode.Sms getSms() {
        zzok zzokVarZzi = this.zza.zzi();
        if (zzokVarZzi != null) {
            return new Barcode.Sms(zzokVarZzi.zza(), zzokVarZzi.zzb());
        }
        return null;
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    public final Barcode.UrlBookmark getUrl() {
        zzol zzolVarZzj = this.zza.zzj();
        if (zzolVarZzj != null) {
            return new Barcode.UrlBookmark(zzolVarZzj.zza(), zzolVarZzj.zzb());
        }
        return null;
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    public final int getValueType() {
        return this.zza.zzb();
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    public final Barcode.WiFi getWifi() {
        zzom zzomVarZzk = this.zza.zzk();
        if (zzomVarZzk != null) {
            return new Barcode.WiFi(zzomVarZzk.zzc(), zzomVarZzk.zzb(), zzomVarZzk.zza());
        }
        return null;
    }
}
