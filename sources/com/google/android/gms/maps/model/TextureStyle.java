package com.google.android.gms.maps.model;

import com.google.android.gms.maps.model.StampStyle;

/* compiled from: com.google.android.gms:play-services-maps@@18.2.0 */
/* loaded from: classes3.dex */
public class TextureStyle extends StampStyle {

    /* compiled from: com.google.android.gms:play-services-maps@@18.2.0 */
    public static final class Builder extends StampStyle.Builder<Builder> {
        private Builder() {
        }

        /* synthetic */ Builder(zzac zzacVar) {
        }

        public TextureStyle build() {
            return new TextureStyle(this.zza, null);
        }

        @Override // com.google.android.gms.maps.model.StampStyle.Builder
        protected final /* bridge */ /* synthetic */ StampStyle.Builder self() {
            return this;
        }

        @Override // com.google.android.gms.maps.model.StampStyle.Builder
        protected Builder self() {
            return this;
        }
    }

    /* synthetic */ TextureStyle(BitmapDescriptor bitmapDescriptor, zzad zzadVar) {
        super(bitmapDescriptor);
    }

    public static Builder newBuilder(BitmapDescriptor bitmapDescriptor) {
        return new Builder(null).stamp(bitmapDescriptor);
    }
}
