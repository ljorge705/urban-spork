package com.google.mlkit.vision.text.internal;

import android.graphics.Matrix;
import android.graphics.Point;
import android.util.SparseArray;
import androidx.media3.common.C;
import com.google.android.gms.internal.mlkit_vision_text_common.zzab;
import com.google.android.gms.internal.mlkit_vision_text_common.zzbj;
import com.google.android.gms.internal.mlkit_vision_text_common.zzbm;
import com.google.android.gms.internal.mlkit_vision_text_common.zzbw;
import com.google.android.gms.internal.mlkit_vision_text_common.zzcr;
import com.google.android.gms.internal.mlkit_vision_text_common.zzr;
import com.google.android.gms.internal.mlkit_vision_text_common.zzu;
import com.google.android.gms.internal.mlkit_vision_text_common.zzv;
import com.google.mlkit.vision.text.Text;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
/* loaded from: classes2.dex */
final class zzi {
    static final zzv zza = zzv.zza("\n");
    private static final Comparator zzb = new Comparator() { // from class: com.google.mlkit.vision.text.internal.zzh
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return ((Integer) ((Map.Entry) obj).getValue()).compareTo((Integer) ((Map.Entry) obj2).getValue());
        }
    };

    /* JADX WARN: Multi-variable type inference failed */
    static Text zza(com.google.android.gms.internal.mlkit_vision_text_common.zzl[] zzlVarArr, final Matrix matrix) {
        SparseArray sparseArray = new SparseArray();
        int i = 0;
        for (com.google.android.gms.internal.mlkit_vision_text_common.zzl zzlVar : zzlVarArr) {
            SparseArray sparseArray2 = (SparseArray) sparseArray.get(zzlVar.zzj);
            if (sparseArray2 == null) {
                sparseArray2 = new SparseArray();
                sparseArray.append(zzlVar.zzj, sparseArray2);
            }
            sparseArray2.append(zzlVar.zzk, zzlVar);
        }
        zzbj zzbjVarZzg = zzbm.zzg();
        int i2 = 0;
        while (i2 < sparseArray.size()) {
            SparseArray sparseArray3 = (SparseArray) sparseArray.valueAt(i2);
            zzbj zzbjVarZzg2 = zzbm.zzg();
            for (int i3 = i; i3 < sparseArray3.size(); i3++) {
                zzbjVarZzg2.zzb((com.google.android.gms.internal.mlkit_vision_text_common.zzl) sparseArray3.valueAt(i3));
            }
            zzbm zzbmVarZzc = zzbjVarZzg2.zzc();
            List listZza = zzbw.zza(zzbmVarZzc, new zzu() { // from class: com.google.mlkit.vision.text.internal.zzd
                @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzu
                public final Object zza(Object obj) {
                    final Matrix matrix2 = matrix;
                    com.google.android.gms.internal.mlkit_vision_text_common.zzl zzlVar2 = (com.google.android.gms.internal.mlkit_vision_text_common.zzl) obj;
                    List listZzb = zza.zzb(zzlVar2.zzb);
                    return new Text.Line(zzab.zzb(zzlVar2.zze) ? "" : zzlVar2.zze, zza.zza(listZzb), listZzb, zzab.zzb(zzlVar2.zzg) ? C.LANGUAGE_UNDETERMINED : zzlVar2.zzg, matrix2, zzbw.zza(Arrays.asList(zzlVar2.zza), new zzu() { // from class: com.google.mlkit.vision.text.internal.zze
                        @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzu
                        public final Object zza(Object obj2) {
                            Matrix matrix3 = matrix2;
                            zzr zzrVar = (zzr) obj2;
                            List listZzb2 = zza.zzb(zzrVar.zzb);
                            return new Text.Element(zzab.zzb(zzrVar.zzd) ? "" : zzrVar.zzd, zza.zza(listZzb2), listZzb2, zzab.zzb(zzrVar.zzf) ? C.LANGUAGE_UNDETERMINED : zzrVar.zzf, matrix3);
                        }
                    }));
                }
            });
            com.google.android.gms.internal.mlkit_vision_text_common.zzf zzfVar = ((com.google.android.gms.internal.mlkit_vision_text_common.zzl) zzbmVarZzc.get(i)).zzb;
            zzcr zzcrVarListIterator = zzbmVarZzc.listIterator(i);
            int iMax = Integer.MIN_VALUE;
            int iMin = Integer.MAX_VALUE;
            int iMin2 = Integer.MAX_VALUE;
            int iMax2 = Integer.MIN_VALUE;
            while (zzcrVarListIterator.hasNext()) {
                com.google.android.gms.internal.mlkit_vision_text_common.zzf zzfVar2 = ((com.google.android.gms.internal.mlkit_vision_text_common.zzl) zzcrVarListIterator.next()).zzb;
                int i4 = zzfVar.zza;
                int i5 = zzfVar.zzb;
                zzcr zzcrVar = zzcrVarListIterator;
                double dSin = Math.sin(Math.toRadians(zzfVar.zze));
                SparseArray sparseArray4 = sparseArray;
                int i6 = i2;
                double dCos = Math.cos(Math.toRadians(zzfVar.zze));
                zzbj zzbjVar = zzbjVarZzg;
                List list = listZza;
                Point point = new Point(zzfVar2.zza, zzfVar2.zzb);
                point.offset(-i4, -i5);
                int i7 = (int) ((pointArr[0].x * dCos) + (pointArr[0].y * dSin));
                int i8 = (int) (((-pointArr[0].x) * dSin) + (pointArr[0].y * dCos));
                pointArr[0].x = i7;
                pointArr[0].y = i8;
                Point[] pointArr = {point, new Point(zzfVar2.zzc + i7, i8), new Point(zzfVar2.zzc + i7, zzfVar2.zzd + i8), new Point(i7, i8 + zzfVar2.zzd)};
                for (int i9 = 0; i9 < 4; i9++) {
                    Point point2 = pointArr[i9];
                    iMin = Math.min(iMin, point2.x);
                    iMax = Math.max(iMax, point2.x);
                    iMin2 = Math.min(iMin2, point2.y);
                    iMax2 = Math.max(iMax2, point2.y);
                }
                zzcrVarListIterator = zzcrVar;
                sparseArray = sparseArray4;
                i2 = i6;
                zzbjVarZzg = zzbjVar;
                listZza = list;
            }
            zzbj zzbjVar2 = zzbjVarZzg;
            SparseArray sparseArray5 = sparseArray;
            int i10 = i2;
            List list2 = listZza;
            int i11 = zzfVar.zza;
            int i12 = zzfVar.zzb;
            double dSin2 = Math.sin(Math.toRadians(zzfVar.zze));
            double dCos2 = Math.cos(Math.toRadians(zzfVar.zze));
            Point[] pointArr2 = {new Point(iMin, iMin2), new Point(iMax, iMin2), new Point(iMax, iMax2), new Point(iMin, iMax2)};
            int i13 = 0;
            for (int i14 = 4; i13 < i14; i14 = 4) {
                int i15 = pointArr2[i13].x;
                int i16 = pointArr2[i13].y;
                int i17 = pointArr2[i13].x;
                int i18 = pointArr2[i13].y;
                pointArr2[i13].x = (int) ((i15 * dCos2) - (i16 * dSin2));
                pointArr2[i13].y = (int) ((i17 * dSin2) + (i18 * dCos2));
                pointArr2[i13].offset(i11, i12);
                i13++;
            }
            List listAsList = Arrays.asList(pointArr2);
            zzbjVar2.zzb(new Text.TextBlock(zza.zzb(zzbw.zza(list2, new zzu() { // from class: com.google.mlkit.vision.text.internal.zzf
                @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzu
                public final Object zza(Object obj) {
                    return ((Text.Line) obj).getText();
                }
            })), zza.zza(listAsList), listAsList, zzb(list2), matrix, list2));
            i2 = i10 + 1;
            zzbjVarZzg = zzbjVar2;
            sparseArray = sparseArray5;
            i = 0;
        }
        zzbm zzbmVarZzc2 = zzbjVarZzg.zzc();
        return new Text(zza.zzb(zzbw.zza(zzbmVarZzc2, new zzu() { // from class: com.google.mlkit.vision.text.internal.zzg
            @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzu
            public final Object zza(Object obj) {
                return ((Text.TextBlock) obj).getText();
            }
        })), zzbmVarZzc2);
    }

    private static String zzb(List list) {
        HashMap map = new HashMap();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            String recognizedLanguage = ((Text.Line) it.next()).getRecognizedLanguage();
            map.put(recognizedLanguage, Integer.valueOf((map.containsKey(recognizedLanguage) ? ((Integer) map.get(recognizedLanguage)).intValue() : 0) + 1));
        }
        Set setEntrySet = map.entrySet();
        if (setEntrySet.isEmpty()) {
            return C.LANGUAGE_UNDETERMINED;
        }
        String str = (String) ((Map.Entry) Collections.max(setEntrySet, zzb)).getKey();
        return zzab.zzb(str) ? C.LANGUAGE_UNDETERMINED : str;
    }
}
