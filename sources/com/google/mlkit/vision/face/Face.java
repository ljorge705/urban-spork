package com.google.mlkit.vision.face;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.SparseArray;
import com.google.android.gms.internal.mlkit_vision_face.zzd;
import com.google.android.gms.internal.mlkit_vision_face.zzf;
import com.google.android.gms.internal.mlkit_vision_face.zzn;
import com.google.android.gms.internal.mlkit_vision_face.zzos;
import com.google.android.gms.internal.mlkit_vision_face.zzow;
import com.google.android.gms.internal.mlkit_vision_face.zzpc;
import com.google.android.gms.internal.mlkit_vision_face.zzv;
import com.google.android.gms.internal.mlkit_vision_face.zzw;
import com.google.mlkit.vision.common.internal.CommonConvertUtils;
import com.oblador.keychain.KeychainModule;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-mlkit-face-detection@@17.1.0 */
/* loaded from: classes2.dex */
public class Face {
    private final Rect zza;
    private int zzb;
    private final float zzc;
    private final float zzd;
    private final float zze;
    private final float zzf;
    private final float zzg;
    private final float zzh;
    private final SparseArray zzi = new SparseArray();
    private final SparseArray zzj = new SparseArray();

    public Face(zzf zzfVar, Matrix matrix) {
        float f = zzfVar.zzc;
        float f2 = zzfVar.zze / 2.0f;
        float f3 = zzfVar.zzd;
        float f4 = zzfVar.zzf / 2.0f;
        Rect rect = new Rect((int) (f - f2), (int) (f3 - f4), (int) (f + f2), (int) (f3 + f4));
        this.zza = rect;
        if (matrix != null) {
            CommonConvertUtils.transformRect(rect, matrix);
        }
        this.zzb = zzfVar.zzb;
        for (zzn zznVar : zzfVar.zzj) {
            if (zze(zznVar.zzd)) {
                PointF pointF = new PointF(zznVar.zzb, zznVar.zzc);
                if (matrix != null) {
                    CommonConvertUtils.transformPointF(pointF, matrix);
                }
                SparseArray sparseArray = this.zzi;
                int i = zznVar.zzd;
                sparseArray.put(i, new FaceLandmark(i, pointF));
            }
        }
        for (zzd zzdVar : zzfVar.zzn) {
            int i2 = zzdVar.zzb;
            if (zzd(i2)) {
                PointF[] pointFArr = zzdVar.zza;
                pointFArr.getClass();
                long length = pointFArr.length + 5 + (r5 / 10);
                ArrayList arrayList = new ArrayList(length > 2147483647L ? Integer.MAX_VALUE : (int) length);
                Collections.addAll(arrayList, pointFArr);
                if (matrix != null) {
                    CommonConvertUtils.transformPointList(arrayList, matrix);
                }
                this.zzj.put(i2, new FaceContour(i2, arrayList));
            }
        }
        this.zzf = zzfVar.zzi;
        this.zzg = zzfVar.zzg;
        this.zzh = zzfVar.zzh;
        this.zze = zzfVar.zzm;
        this.zzd = zzfVar.zzk;
        this.zzc = zzfVar.zzl;
    }

    private static boolean zzd(int i) {
        return i <= 15 && i > 0;
    }

    private static boolean zze(int i) {
        return i == 0 || i == 1 || i == 7 || i == 3 || i == 9 || i == 4 || i == 10 || i == 5 || i == 11 || i == 6;
    }

    public List<FaceContour> getAllContours() {
        ArrayList arrayList = new ArrayList();
        int size = this.zzj.size();
        for (int i = 0; i < size; i++) {
            arrayList.add((FaceContour) this.zzj.valueAt(i));
        }
        return arrayList;
    }

    public List<FaceLandmark> getAllLandmarks() {
        ArrayList arrayList = new ArrayList();
        int size = this.zzi.size();
        for (int i = 0; i < size; i++) {
            arrayList.add((FaceLandmark) this.zzi.valueAt(i));
        }
        return arrayList;
    }

    public Rect getBoundingBox() {
        return this.zza;
    }

    public FaceContour getContour(int i) {
        return (FaceContour) this.zzj.get(i);
    }

    public float getHeadEulerAngleX() {
        return this.zzf;
    }

    public float getHeadEulerAngleY() {
        return this.zzg;
    }

    public float getHeadEulerAngleZ() {
        return this.zzh;
    }

    public FaceLandmark getLandmark(int i) {
        return (FaceLandmark) this.zzi.get(i);
    }

    public Float getLeftEyeOpenProbability() {
        float f = this.zze;
        if (f < 0.0f || f > 1.0f) {
            return null;
        }
        return Float.valueOf(this.zzd);
    }

    public Float getRightEyeOpenProbability() {
        float f = this.zzc;
        if (f < 0.0f || f > 1.0f) {
            return null;
        }
        return Float.valueOf(f);
    }

    public Float getSmilingProbability() {
        float f = this.zze;
        if (f < 0.0f || f > 1.0f) {
            return null;
        }
        return Float.valueOf(f);
    }

    public Integer getTrackingId() {
        int i = this.zzb;
        if (i == -1) {
            return null;
        }
        return Integer.valueOf(i);
    }

    public String toString() {
        zzv zzvVarZza = zzw.zza(KeychainModule.FACE_SUPPORTED_NAME);
        zzvVarZza.zzc("boundingBox", this.zza);
        zzvVarZza.zzb("trackingId", this.zzb);
        zzvVarZza.zza("rightEyeOpenProbability", this.zzc);
        zzvVarZza.zza("leftEyeOpenProbability", this.zzd);
        zzvVarZza.zza("smileProbability", this.zze);
        zzvVarZza.zza("eulerX", this.zzf);
        zzvVarZza.zza("eulerY", this.zzg);
        zzvVarZza.zza("eulerZ", this.zzh);
        zzv zzvVarZza2 = zzw.zza("Landmarks");
        for (int i = 0; i <= 11; i++) {
            if (zze(i)) {
                zzvVarZza2.zzc("landmark_" + i, getLandmark(i));
            }
        }
        zzvVarZza.zzc("landmarks", zzvVarZza2.toString());
        zzv zzvVarZza3 = zzw.zza("Contours");
        for (int i2 = 1; i2 <= 15; i2++) {
            zzvVarZza3.zzc("Contour_" + i2, getContour(i2));
        }
        zzvVarZza.zzc("contours", zzvVarZza3.toString());
        return zzvVarZza.toString();
    }

    public final SparseArray zza() {
        return this.zzj;
    }

    public final void zzb(SparseArray sparseArray) {
        this.zzj.clear();
        for (int i = 0; i < sparseArray.size(); i++) {
            this.zzj.put(sparseArray.keyAt(i), (FaceContour) sparseArray.valueAt(i));
        }
    }

    public final void zzc(int i) {
        this.zzb = -1;
    }

    public Face(zzow zzowVar, Matrix matrix) {
        Rect rectZzh = zzowVar.zzh();
        this.zza = rectZzh;
        if (matrix != null) {
            CommonConvertUtils.transformRect(rectZzh, matrix);
        }
        this.zzb = zzowVar.zzg();
        for (zzpc zzpcVar : zzowVar.zzj()) {
            if (zze(zzpcVar.zza())) {
                PointF pointFZzb = zzpcVar.zzb();
                if (matrix != null) {
                    CommonConvertUtils.transformPointF(pointFZzb, matrix);
                }
                this.zzi.put(zzpcVar.zza(), new FaceLandmark(zzpcVar.zza(), pointFZzb));
            }
        }
        for (zzos zzosVar : zzowVar.zzi()) {
            int iZza = zzosVar.zza();
            if (zzd(iZza)) {
                List listZzb = zzosVar.zzb();
                listZzb.getClass();
                ArrayList arrayList = new ArrayList(listZzb);
                if (matrix != null) {
                    CommonConvertUtils.transformPointList(arrayList, matrix);
                }
                this.zzj.put(iZza, new FaceContour(iZza, arrayList));
            }
        }
        this.zzf = zzowVar.zzf();
        this.zzg = zzowVar.zzb();
        this.zzh = -zzowVar.zzd();
        this.zze = zzowVar.zze();
        this.zzd = zzowVar.zza();
        this.zzc = zzowVar.zzc();
    }
}
