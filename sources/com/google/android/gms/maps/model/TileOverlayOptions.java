package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.maps.zzao;
import com.google.android.gms.internal.maps.zzap;

/* compiled from: com.google.android.gms:play-services-maps@@18.2.0 */
/* loaded from: classes3.dex */
public final class TileOverlayOptions extends AbstractSafeParcelable {
    public static final Parcelable.Creator<TileOverlayOptions> CREATOR = new zzah();
    private zzap zza;
    private TileProvider zzb;
    private boolean zzc;
    private float zzd;
    private boolean zze;
    private float zzf;

    public TileOverlayOptions() {
        this.zzc = true;
        this.zze = true;
        this.zzf = 0.0f;
    }

    public TileOverlayOptions fadeIn(boolean z) {
        this.zze = z;
        return this;
    }

    public boolean getFadeIn() {
        return this.zze;
    }

    public TileProvider getTileProvider() {
        return this.zzb;
    }

    public float getTransparency() {
        return this.zzf;
    }

    public float getZIndex() {
        return this.zzd;
    }

    public boolean isVisible() {
        return this.zzc;
    }

    public TileOverlayOptions tileProvider(TileProvider tileProvider) {
        this.zzb = (TileProvider) Preconditions.checkNotNull(tileProvider, "tileProvider must not be null.");
        this.zza = new zzag(this, tileProvider);
        return this;
    }

    public TileOverlayOptions transparency(float f) {
        boolean z = false;
        if (f >= 0.0f && f <= 1.0f) {
            z = true;
        }
        Preconditions.checkArgument(z, "Transparency must be in the range [0..1]");
        this.zzf = f;
        return this;
    }

    public TileOverlayOptions visible(boolean z) {
        this.zzc = z;
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        zzap zzapVar = this.zza;
        SafeParcelWriter.writeIBinder(parcel, 2, zzapVar == null ? null : zzapVar.asBinder(), false);
        SafeParcelWriter.writeBoolean(parcel, 3, isVisible());
        SafeParcelWriter.writeFloat(parcel, 4, getZIndex());
        SafeParcelWriter.writeBoolean(parcel, 5, getFadeIn());
        SafeParcelWriter.writeFloat(parcel, 6, getTransparency());
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public TileOverlayOptions zIndex(float f) {
        this.zzd = f;
        return this;
    }

    TileOverlayOptions(IBinder iBinder, boolean z, float f, boolean z2, float f2) {
        this.zzc = true;
        this.zze = true;
        this.zzf = 0.0f;
        zzap zzapVarZzc = zzao.zzc(iBinder);
        this.zza = zzapVarZzc;
        this.zzb = zzapVarZzc == null ? null : new zzaf(this);
        this.zzc = z;
        this.zzd = f;
        this.zze = z2;
        this.zzf = f2;
    }
}
