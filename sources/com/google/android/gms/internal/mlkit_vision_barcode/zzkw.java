package com.google.android.gms.internal.mlkit_vision_barcode;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.0.0 */
/* loaded from: classes3.dex */
public enum zzkw implements zzdh {
    TYPE_UNKNOWN(0),
    TYPE_CONTACT_INFO(1),
    TYPE_EMAIL(2),
    TYPE_ISBN(3),
    TYPE_PHONE(4),
    TYPE_PRODUCT(5),
    TYPE_SMS(6),
    TYPE_TEXT(7),
    TYPE_URL(8),
    TYPE_WIFI(9),
    TYPE_GEO(10),
    TYPE_CALENDAR_EVENT(11),
    TYPE_DRIVER_LICENSE(12);

    private final int zzo;

    zzkw(int i) {
        this.zzo = i;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzdh
    public final int zza() {
        return this.zzo;
    }
}
