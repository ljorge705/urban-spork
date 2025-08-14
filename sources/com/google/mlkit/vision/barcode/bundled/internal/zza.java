package com.google.mlkit.vision.barcode.bundled.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Point;
import android.media.Image;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzao;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzap;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzaq;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzar;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzas;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzat;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzau;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzav;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzaw;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzax;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzay;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzaz;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzba;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzbc;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzbk;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzbu;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcb;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcd;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzci;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb;
import com.google.android.libraries.barhopper.BarhopperV3;
import com.google.android.libraries.barhopper.RecognitionOptions;
import com.google.barhopper.deeplearning.BarhopperV3Options;
import com.google.barhopper.deeplearning.zze;
import com.google.barhopper.deeplearning.zzf;
import com.google.barhopper.deeplearning.zzh;
import com.google.barhopper.deeplearning.zzi;
import com.google.barhopper.deeplearning.zzk;
import com.google.barhopper.deeplearning.zzm;
import com.google.mlkit.vision.common.internal.ImageUtils;
import com.google.photos.vision.barhopper.BarhopperProto$BarhopperResponse;
import com.google.photos.vision.barhopper.zzac;
import com.google.photos.vision.barhopper.zzad;
import com.google.photos.vision.barhopper.zzae;
import com.google.photos.vision.barhopper.zzag;
import com.google.photos.vision.barhopper.zzaj;
import com.google.photos.vision.barhopper.zzb;
import com.google.photos.vision.barhopper.zzc;
import com.google.photos.vision.barhopper.zzn;
import com.google.photos.vision.barhopper.zzp;
import com.google.photos.vision.barhopper.zzr;
import com.google.photos.vision.barhopper.zzt;
import com.google.photos.vision.barhopper.zzy;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes2.dex */
final class zza extends zzbk {
    private static final int[] zza = {5, 7, 7, 7, 5, 5};
    private static final double[][] zzb = {new double[]{0.075d, 1.0d}, new double[]{0.1d, 1.0d}, new double[]{0.125d, 1.0d}, new double[]{0.2d, 2.0d}, new double[]{0.2d, 0.5d}, new double[]{0.15d, 1.0d}, new double[]{0.2d, 1.0d}, new double[]{0.25d, 1.0d}, new double[]{0.35d, 2.0d}, new double[]{0.35d, 0.5d}, new double[]{0.35d, 3.0d}, new double[]{0.35d, 0.3333d}, new double[]{0.3d, 1.0d}, new double[]{0.4d, 1.0d}, new double[]{0.5d, 1.0d}, new double[]{0.5d, 2.0d}, new double[]{0.5d, 0.5d}, new double[]{0.5d, 3.0d}, new double[]{0.5d, 0.3333d}, new double[]{0.6d, 1.0d}, new double[]{0.8d, 1.0d}, new double[]{1.0d, 1.0d}, new double[]{0.65d, 2.0d}, new double[]{0.65d, 0.5d}, new double[]{0.65d, 3.0d}, new double[]{0.65d, 0.3333d}, new double[]{1.0d, 1.0d}, new double[]{0.8d, 2.0d}, new double[]{0.8d, 0.5d}, new double[]{0.8d, 3.0d}, new double[]{0.8d, 0.3333d}, new double[]{1.0d, 1.0d}, new double[]{0.95d, 2.0d}, new double[]{0.95d, 0.5d}, new double[]{0.95d, 3.0d}, new double[]{0.95d, 0.3333d}};
    private final Context zzc;
    private final RecognitionOptions zzd;
    private BarhopperV3 zze;

    zza(Context context, zzbc zzbcVar) {
        RecognitionOptions recognitionOptions = new RecognitionOptions();
        this.zzd = recognitionOptions;
        this.zzc = context;
        recognitionOptions.setBarcodeFormats(zzbcVar.zza());
    }

    private static zzap zze(zzn zznVar, String str, String str2) {
        if (zznVar == null || str == null) {
            return null;
        }
        Matcher matcher = Pattern.compile(str2).matcher(str);
        return new zzap(zznVar.zzf(), zznVar.zzd(), zznVar.zza(), zznVar.zzb(), zznVar.zzc(), zznVar.zze(), zznVar.zzj(), matcher.find() ? matcher.group(1) : null);
    }

    private final BarhopperProto$BarhopperResponse zzf(ByteBuffer byteBuffer, zzbu zzbuVar) {
        BarhopperV3 barhopperV3 = (BarhopperV3) Preconditions.checkNotNull(this.zze);
        if (((ByteBuffer) Preconditions.checkNotNull(byteBuffer)).isDirect()) {
            return barhopperV3.recognize(zzbuVar.zzd(), zzbuVar.zza(), byteBuffer, this.zzd);
        }
        if (byteBuffer.hasArray() && byteBuffer.arrayOffset() == 0) {
            return barhopperV3.recognize(zzbuVar.zzd(), zzbuVar.zza(), byteBuffer.array(), this.zzd);
        }
        byte[] bArr = new byte[byteBuffer.remaining()];
        byteBuffer.get(bArr);
        return barhopperV3.recognize(zzbuVar.zzd(), zzbuVar.zza(), bArr, this.zzd);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzbl
    public final List zzb(IObjectWrapper iObjectWrapper, zzbu zzbuVar) {
        BarhopperProto$BarhopperResponse barhopperProto$BarhopperResponseRecognize;
        zzat zzatVar;
        zzaw zzawVar;
        zzax zzaxVar;
        zzaz zzazVar;
        zzay zzayVar;
        zzau zzauVar;
        zzaq zzaqVar;
        int i;
        zzar zzarVar;
        zzas zzasVar;
        int i2;
        Point[] pointArr;
        int i3;
        int i4;
        zzaw[] zzawVarArr;
        zzat[] zzatVarArr;
        zzao[] zzaoVarArr;
        int iZzb = zzbuVar.zzb();
        int i5 = -1;
        int i6 = 0;
        if (iZzb == -1) {
            barhopperProto$BarhopperResponseRecognize = ((BarhopperV3) Preconditions.checkNotNull(this.zze)).recognize((Bitmap) ObjectWrapper.unwrap(iObjectWrapper), this.zzd);
        } else if (iZzb == 17) {
            barhopperProto$BarhopperResponseRecognize = zzf((ByteBuffer) ObjectWrapper.unwrap(iObjectWrapper), zzbuVar);
        } else if (iZzb != 35) {
            if (iZzb != 842094169) {
                int iZzb2 = zzbuVar.zzb();
                StringBuilder sb = new StringBuilder(37);
                sb.append("Unsupported image format: ");
                sb.append(iZzb2);
                throw new IllegalArgumentException(sb.toString());
            }
            barhopperProto$BarhopperResponseRecognize = zzf((ByteBuffer) ObjectWrapper.unwrap(iObjectWrapper), zzbuVar);
        } else {
            barhopperProto$BarhopperResponseRecognize = zzf(((Image) Preconditions.checkNotNull((Image) ObjectWrapper.unwrap(iObjectWrapper))).getPlanes()[0].getBuffer(), zzbuVar);
        }
        ArrayList arrayList = new ArrayList();
        Matrix uprightRotationMatrix = ImageUtils.getInstance().getUprightRotationMatrix(zzbuVar.zzd(), zzbuVar.zza(), zzbuVar.zzc());
        for (zzc zzcVar : barhopperProto$BarhopperResponseRecognize.zzc()) {
            if (zzcVar.zza() > 0 && uprightRotationMatrix != null) {
                float[] fArr = new float[8];
                List listZzo = zzcVar.zzo();
                int iZza = zzcVar.zza();
                for (int i7 = i6; i7 < iZza; i7++) {
                    int i8 = i7 + i7;
                    fArr[i8] = ((zzae) listZzo.get(i7)).zza();
                    fArr[i8 + 1] = ((zzae) listZzo.get(i7)).zzb();
                }
                uprightRotationMatrix.mapPoints(fArr);
                int iZzc = zzbuVar.zzc();
                for (int i9 = i6; i9 < iZza; i9++) {
                    zzb zzbVar = (zzb) zzcVar.zzG();
                    int i10 = i9 + i9;
                    zzad zzadVarZzc = zzae.zzc();
                    zzadVarZzc.zza((int) fArr[i10]);
                    zzadVarZzc.zzb((int) fArr[i10 + 1]);
                    zzbVar.zza((i9 + iZzc) % iZza, (zzae) zzadVarZzc.zzl());
                    zzcVar = (zzc) zzbVar.zzl();
                }
            }
            if (zzcVar.zzt()) {
                zzy zzyVarZzh = zzcVar.zzh();
                zzatVar = new zzat(zzyVarZzh.zzf() + i5, zzyVarZzh.zzc(), zzyVarZzh.zze(), zzyVarZzh.zzd());
            } else {
                zzatVar = null;
            }
            if (zzcVar.zzv()) {
                zzci zzciVarZzb = zzcVar.zzb();
                zzawVar = new zzaw(zzciVarZzb.zzd() + i5, zzciVarZzb.zzc());
            } else {
                zzawVar = null;
            }
            if (zzcVar.zzw()) {
                zzag zzagVarZzj = zzcVar.zzj();
                zzaxVar = new zzax(zzagVarZzj.zzc(), zzagVarZzj.zzd());
            } else {
                zzaxVar = null;
            }
            if (zzcVar.zzy()) {
                com.google.photos.vision.barhopper.zzao zzaoVarZzl = zzcVar.zzl();
                zzazVar = new zzaz(zzaoVarZzl.zzd(), zzaoVarZzl.zzc(), zzaoVarZzl.zze() + i5);
            } else {
                zzazVar = null;
            }
            if (zzcVar.zzx()) {
                zzaj zzajVarZzk = zzcVar.zzk();
                zzayVar = new zzay(zzajVarZzk.zzc(), zzajVarZzk.zzd());
            } else {
                zzayVar = null;
            }
            if (zzcVar.zzu()) {
                zzac zzacVarZzi = zzcVar.zzi();
                zzauVar = new zzau(zzacVarZzi.zza(), zzacVarZzi.zzb());
            } else {
                zzauVar = null;
            }
            if (zzcVar.zzq()) {
                zzp zzpVarZzd = zzcVar.zzd();
                zzaqVar = new zzaq(zzpVarZzd.zzj(), zzpVarZzd.zze(), zzpVarZzd.zzf(), zzpVarZzd.zzh(), zzpVarZzd.zzi(), zze(zzpVarZzd.zzb(), zzcVar.zzm().zzn() ? zzcVar.zzm().zzv() : null, "DTSTART:([0-9TZ]*)"), zze(zzpVarZzd.zza(), zzcVar.zzm().zzn() ? zzcVar.zzm().zzv() : null, "DTEND:([0-9TZ]*)"));
            } else {
                zzaqVar = null;
            }
            if (zzcVar.zzr()) {
                zzr zzrVarZze = zzcVar.zze();
                zzcd zzcdVarZza = zzrVarZze.zza();
                zzav zzavVar = zzcdVarZza != null ? new zzav(zzcdVarZza.zzd(), zzcdVarZza.zzi(), zzcdVarZza.zzh(), zzcdVarZza.zzc(), zzcdVarZza.zzf(), zzcdVarZza.zze(), zzcdVarZza.zzj()) : null;
                String strZzd = zzrVarZze.zzd();
                String strZze = zzrVarZze.zze();
                List listZzi = zzrVarZze.zzi();
                if (listZzi.isEmpty()) {
                    zzawVarArr = null;
                } else {
                    zzaw[] zzawVarArr2 = new zzaw[listZzi.size()];
                    for (int i11 = 0; i11 < listZzi.size(); i11++) {
                        zzawVarArr2[i11] = new zzaw(((zzci) listZzi.get(i11)).zzd() + i5, ((zzci) listZzi.get(i11)).zzc());
                    }
                    zzawVarArr = zzawVarArr2;
                }
                List listZzh = zzrVarZze.zzh();
                if (listZzh.isEmpty()) {
                    zzatVarArr = null;
                } else {
                    zzat[] zzatVarArr2 = new zzat[listZzh.size()];
                    int i12 = 0;
                    while (i12 < listZzh.size()) {
                        zzatVarArr2[i12] = new zzat(((zzy) listZzh.get(i12)).zzf() + i5, ((zzy) listZzh.get(i12)).zzc(), ((zzy) listZzh.get(i12)).zze(), ((zzy) listZzh.get(i12)).zzd());
                        i12++;
                        i5 = -1;
                    }
                    zzatVarArr = zzatVarArr2;
                }
                String[] strArr = (String[]) zzrVarZze.zzj().toArray(new String[0]);
                List listZzf = zzrVarZze.zzf();
                if (listZzf.isEmpty()) {
                    i = 0;
                    zzaoVarArr = null;
                } else {
                    zzao[] zzaoVarArr2 = new zzao[listZzf.size()];
                    for (int i13 = 0; i13 < listZzf.size(); i13++) {
                        zzaoVarArr2[i13] = new zzao(((zzcb) listZzf.get(i13)).zzc() - 1, (String[]) ((zzcb) listZzf.get(i13)).zzb().toArray(new String[0]));
                    }
                    i = 0;
                    zzaoVarArr = zzaoVarArr2;
                }
                zzarVar = new zzar(zzavVar, strZzd, strZze, zzawVarArr, zzatVarArr, strArr, zzaoVarArr);
            } else {
                i = 0;
                zzarVar = null;
            }
            if (zzcVar.zzs()) {
                zzt zztVarZzf = zzcVar.zzf();
                zzasVar = new zzas(zztVarZzf.zzi(), zztVarZzf.zzk(), zztVarZzf.zzq(), zztVarZzf.zzo(), zztVarZzf.zzl(), zztVarZzf.zze(), zztVarZzf.zzc(), zztVarZzf.zzd(), zztVarZzf.zzf(), zztVarZzf.zzp(), zztVarZzf.zzm(), zztVarZzf.zzj(), zztVarZzf.zzh(), zztVarZzf.zzn());
            } else {
                zzasVar = null;
            }
            switch (zzcVar.zzz() - 1) {
                case 0:
                    i2 = i;
                    break;
                case 1:
                    i2 = 1;
                    break;
                case 2:
                    i2 = 2;
                    break;
                case 3:
                    i2 = 4;
                    break;
                case 4:
                    i2 = 8;
                    break;
                case 5:
                    i2 = 16;
                    break;
                case 6:
                    i2 = 32;
                    break;
                case 7:
                    i2 = 64;
                    break;
                case 8:
                    i2 = 128;
                    break;
                case 9:
                    i2 = 256;
                    break;
                case 10:
                    i2 = 512;
                    break;
                case 11:
                    i2 = 1024;
                    break;
                case 12:
                    i2 = 2048;
                    break;
                case 13:
                    i2 = 4096;
                    break;
                default:
                    i2 = -1;
                    break;
            }
            String strZzn = zzcVar.zzn();
            String strZzv = zzcVar.zzm().zzn() ? zzcVar.zzm().zzv() : null;
            byte[] bArrZzy = zzcVar.zzm().zzy();
            List listZzo2 = zzcVar.zzo();
            if (listZzo2.isEmpty()) {
                pointArr = null;
            } else {
                Point[] pointArr2 = new Point[listZzo2.size()];
                for (int i14 = i; i14 < listZzo2.size(); i14++) {
                    pointArr2[i14] = new Point(((zzae) listZzo2.get(i14)).zza(), ((zzae) listZzo2.get(i14)).zzb());
                }
                pointArr = pointArr2;
            }
            switch (zzcVar.zzA() - 1) {
                case 1:
                    i3 = 1;
                    continue;
                    arrayList.add(new zzba(i2, strZzn, strZzv, bArrZzy, pointArr, i3, zzatVar, zzawVar, zzaxVar, zzazVar, zzayVar, zzauVar, zzaqVar, zzarVar, zzasVar));
                    i5 = -1;
                    i6 = i;
                case 2:
                    i3 = 2;
                    continue;
                    arrayList.add(new zzba(i2, strZzn, strZzv, bArrZzy, pointArr, i3, zzatVar, zzawVar, zzaxVar, zzazVar, zzayVar, zzauVar, zzaqVar, zzarVar, zzasVar));
                    i5 = -1;
                    i6 = i;
                case 3:
                    i4 = 3;
                    break;
                case 4:
                    i3 = 4;
                    continue;
                    arrayList.add(new zzba(i2, strZzn, strZzv, bArrZzy, pointArr, i3, zzatVar, zzawVar, zzaxVar, zzazVar, zzayVar, zzauVar, zzaqVar, zzarVar, zzasVar));
                    i5 = -1;
                    i6 = i;
                case 5:
                    i4 = 5;
                    break;
                case 6:
                    i4 = 6;
                    break;
                case 7:
                    i4 = 7;
                    break;
                case 8:
                    i3 = 8;
                    continue;
                    arrayList.add(new zzba(i2, strZzn, strZzv, bArrZzy, pointArr, i3, zzatVar, zzawVar, zzaxVar, zzazVar, zzayVar, zzauVar, zzaqVar, zzarVar, zzasVar));
                    i5 = -1;
                    i6 = i;
                case 9:
                    i4 = 9;
                    break;
                case 10:
                    i4 = 10;
                    break;
                case 11:
                    i4 = 11;
                    break;
                case 12:
                    i4 = 12;
                    break;
                default:
                    i3 = i;
                    continue;
                    arrayList.add(new zzba(i2, strZzn, strZzv, bArrZzy, pointArr, i3, zzatVar, zzawVar, zzaxVar, zzazVar, zzayVar, zzauVar, zzaqVar, zzarVar, zzasVar));
                    i5 = -1;
                    i6 = i;
            }
            i3 = i4;
            arrayList.add(new zzba(i2, strZzn, strZzv, bArrZzy, pointArr, i3, zzatVar, zzawVar, zzaxVar, zzazVar, zzayVar, zzauVar, zzaqVar, zzarVar, zzasVar));
            i5 = -1;
            i6 = i;
        }
        return arrayList;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzbl
    public final void zzc() throws IOException {
        if (this.zze != null) {
            return;
        }
        this.zze = new BarhopperV3();
        zzh zzhVarZza = zzi.zza();
        zze zzeVarZza = zzf.zza();
        int i = 16;
        int i2 = 0;
        for (int i3 = 0; i3 < 6; i3++) {
            com.google.barhopper.deeplearning.zzb zzbVarZza = com.google.barhopper.deeplearning.zzc.zza();
            zzbVarZza.zzc(i);
            zzbVarZza.zzd(i);
            for (int i4 = 0; i4 < zza[i3]; i4++) {
                double[] dArr = zzb[i2];
                float f = (float) (dArr[0] * 320.0d);
                float fSqrt = (float) Math.sqrt(dArr[1]);
                zzbVarZza.zza(f / fSqrt);
                zzbVarZza.zzb(f * fSqrt);
                i2++;
            }
            i += i;
            zzeVarZza.zza(zzbVarZza);
        }
        zzhVarZza.zza(zzeVarZza);
        try {
            InputStream inputStreamOpen = this.zzc.getAssets().open("mlkit_barcode_models/barcode_ssd_mobilenet_v1_dmp25_quant.tflite");
            try {
                InputStream inputStreamOpen2 = this.zzc.getAssets().open("mlkit_barcode_models/oned_auto_regressor_mobile.tflite");
                try {
                    InputStream inputStreamOpen3 = this.zzc.getAssets().open("mlkit_barcode_models/oned_feature_extractor_mobile.tflite");
                    try {
                        BarhopperV3 barhopperV3 = (BarhopperV3) Preconditions.checkNotNull(this.zze);
                        zzk zzkVarZza = BarhopperV3Options.zza();
                        zzhVarZza.zzb(zzdb.zzt(inputStreamOpen));
                        zzkVarZza.zza(zzhVarZza);
                        zzm zzmVarZza = com.google.barhopper.deeplearning.zzn.zza();
                        zzmVarZza.zza(zzdb.zzt(inputStreamOpen2));
                        zzmVarZza.zzb(zzdb.zzt(inputStreamOpen3));
                        zzkVarZza.zzb(zzmVarZza);
                        barhopperV3.create(zzkVarZza.zzl());
                        if (inputStreamOpen3 != null) {
                            inputStreamOpen3.close();
                        }
                        if (inputStreamOpen2 != null) {
                            inputStreamOpen2.close();
                        }
                        if (inputStreamOpen != null) {
                            inputStreamOpen.close();
                        }
                    } catch (Throwable th) {
                        if (inputStreamOpen3 != null) {
                            try {
                                inputStreamOpen3.close();
                            } catch (Throwable unused) {
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    if (inputStreamOpen2 != null) {
                        try {
                            inputStreamOpen2.close();
                        } catch (Throwable unused2) {
                        }
                    }
                    throw th2;
                }
            } catch (Throwable th3) {
                if (inputStreamOpen != null) {
                    try {
                        inputStreamOpen.close();
                    } catch (Throwable unused3) {
                    }
                }
                throw th3;
            }
        } catch (IOException e) {
            throw new IllegalStateException("Failed to open Barcode models", e);
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzbl
    public final void zzd() {
        BarhopperV3 barhopperV3 = this.zze;
        if (barhopperV3 != null) {
            barhopperV3.close();
            this.zze = null;
        }
    }
}
