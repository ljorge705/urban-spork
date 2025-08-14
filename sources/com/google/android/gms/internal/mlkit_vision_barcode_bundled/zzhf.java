package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
public enum zzhf {
    DOUBLE(zzhg.DOUBLE, 1),
    FLOAT(zzhg.FLOAT, 5),
    INT64(zzhg.LONG, 0),
    UINT64(zzhg.LONG, 0),
    INT32(zzhg.INT, 0),
    FIXED64(zzhg.LONG, 1),
    FIXED32(zzhg.INT, 5),
    BOOL(zzhg.BOOLEAN, 0),
    STRING(zzhg.STRING, 2),
    GROUP(zzhg.MESSAGE, 3),
    MESSAGE(zzhg.MESSAGE, 2),
    BYTES(zzhg.BYTE_STRING, 2),
    UINT32(zzhg.INT, 0),
    ENUM(zzhg.ENUM, 0),
    SFIXED32(zzhg.INT, 5),
    SFIXED64(zzhg.LONG, 1),
    SINT32(zzhg.INT, 0),
    SINT64(zzhg.LONG, 0);

    private final zzhg zzt;

    zzhf(zzhg zzhgVar, int i) {
        this.zzt = zzhgVar;
    }

    public final zzhg zza() {
        return this.zzt;
    }
}
