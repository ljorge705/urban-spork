package com.google.mlkit.vision.barcode;

import com.google.android.gms.common.internal.Objects;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.0.0 */
/* loaded from: classes2.dex */
public class BarcodeScannerOptions {
    private final int zza;
    private final Executor zzb;

    /* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.0.0 */
    public static class Builder {
        private int zza = 0;
        private Executor zzb;

        public BarcodeScannerOptions build() {
            return new BarcodeScannerOptions(this.zza, this.zzb, null);
        }

        public Builder setBarcodeFormats(int i, int... iArr) {
            this.zza = i;
            if (iArr != null) {
                for (int i2 : iArr) {
                    this.zza = i2 | this.zza;
                }
            }
            return this;
        }

        public Builder setExecutor(Executor executor) {
            this.zzb = executor;
            return this;
        }
    }

    /* synthetic */ BarcodeScannerOptions(int i, Executor executor, zza zzaVar) {
        this.zza = i;
        this.zzb = executor;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BarcodeScannerOptions)) {
            return false;
        }
        BarcodeScannerOptions barcodeScannerOptions = (BarcodeScannerOptions) obj;
        return this.zza == barcodeScannerOptions.zza && Objects.equal(this.zzb, barcodeScannerOptions.zzb);
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zza), this.zzb);
    }

    public final int zza() {
        return this.zza;
    }

    public final Executor zzb() {
        return this.zzb;
    }
}
