package com.google.android.gms.maps.model;

import com.google.android.gms.maps.model.StampStyle;

/* compiled from: com.google.android.gms:play-services-maps@@18.2.0 */
/* loaded from: classes3.dex */
public class SpriteStyle extends StampStyle {

    /* compiled from: com.google.android.gms:play-services-maps@@18.2.0 */
    public static final class Builder extends StampStyle.Builder<Builder> {
        private Builder() {
        }

        /* synthetic */ Builder(zzs zzsVar) {
        }

        public SpriteStyle build() {
            return new SpriteStyle(this.zza);
        }

        @Override // com.google.android.gms.maps.model.StampStyle.Builder
        protected Builder self() {
            return this;
        }

        @Override // com.google.android.gms.maps.model.StampStyle.Builder
        protected final /* bridge */ /* synthetic */ StampStyle.Builder self() {
            return this;
        }
    }

    public SpriteStyle(BitmapDescriptor bitmapDescriptor) {
        super(bitmapDescriptor);
    }

    public static Builder newBuilder(BitmapDescriptor bitmapDescriptor) {
        return new Builder(null).stamp(bitmapDescriptor);
    }
}
