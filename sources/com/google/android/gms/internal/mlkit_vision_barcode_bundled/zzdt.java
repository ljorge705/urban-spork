package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
public enum zzdt {
    DOUBLE(0, 1, zzeo.DOUBLE),
    FLOAT(1, 1, zzeo.FLOAT),
    INT64(2, 1, zzeo.LONG),
    UINT64(3, 1, zzeo.LONG),
    INT32(4, 1, zzeo.INT),
    FIXED64(5, 1, zzeo.LONG),
    FIXED32(6, 1, zzeo.INT),
    BOOL(7, 1, zzeo.BOOLEAN),
    STRING(8, 1, zzeo.STRING),
    MESSAGE(9, 1, zzeo.MESSAGE),
    BYTES(10, 1, zzeo.BYTE_STRING),
    UINT32(11, 1, zzeo.INT),
    ENUM(12, 1, zzeo.ENUM),
    SFIXED32(13, 1, zzeo.INT),
    SFIXED64(14, 1, zzeo.LONG),
    SINT32(15, 1, zzeo.INT),
    SINT64(16, 1, zzeo.LONG),
    GROUP(17, 1, zzeo.MESSAGE),
    DOUBLE_LIST(18, 2, zzeo.DOUBLE),
    FLOAT_LIST(19, 2, zzeo.FLOAT),
    INT64_LIST(20, 2, zzeo.LONG),
    UINT64_LIST(21, 2, zzeo.LONG),
    INT32_LIST(22, 2, zzeo.INT),
    FIXED64_LIST(23, 2, zzeo.LONG),
    FIXED32_LIST(24, 2, zzeo.INT),
    BOOL_LIST(25, 2, zzeo.BOOLEAN),
    STRING_LIST(26, 2, zzeo.STRING),
    MESSAGE_LIST(27, 2, zzeo.MESSAGE),
    BYTES_LIST(28, 2, zzeo.BYTE_STRING),
    UINT32_LIST(29, 2, zzeo.INT),
    ENUM_LIST(30, 2, zzeo.ENUM),
    SFIXED32_LIST(31, 2, zzeo.INT),
    SFIXED64_LIST(32, 2, zzeo.LONG),
    SINT32_LIST(33, 2, zzeo.INT),
    SINT64_LIST(34, 2, zzeo.LONG),
    DOUBLE_LIST_PACKED(35, 3, zzeo.DOUBLE),
    FLOAT_LIST_PACKED(36, 3, zzeo.FLOAT),
    INT64_LIST_PACKED(37, 3, zzeo.LONG),
    UINT64_LIST_PACKED(38, 3, zzeo.LONG),
    INT32_LIST_PACKED(39, 3, zzeo.INT),
    FIXED64_LIST_PACKED(40, 3, zzeo.LONG),
    FIXED32_LIST_PACKED(41, 3, zzeo.INT),
    BOOL_LIST_PACKED(42, 3, zzeo.BOOLEAN),
    UINT32_LIST_PACKED(43, 3, zzeo.INT),
    ENUM_LIST_PACKED(44, 3, zzeo.ENUM),
    SFIXED32_LIST_PACKED(45, 3, zzeo.INT),
    SFIXED64_LIST_PACKED(46, 3, zzeo.LONG),
    SINT32_LIST_PACKED(47, 3, zzeo.INT),
    SINT64_LIST_PACKED(48, 3, zzeo.LONG),
    GROUP_LIST(49, 2, zzeo.MESSAGE),
    MAP(50, 4, zzeo.VOID);

    private static final zzdt[] zzZ;
    private final zzeo zzab;
    private final int zzac;
    private final Class zzad;

    static {
        zzdt[] zzdtVarArrValues = values();
        zzZ = new zzdt[zzdtVarArrValues.length];
        for (zzdt zzdtVar : zzdtVarArrValues) {
            zzZ[zzdtVar.zzac] = zzdtVar;
        }
    }

    zzdt(int i, int i2, zzeo zzeoVar) {
        this.zzac = i;
        this.zzab = zzeoVar;
        zzeo zzeoVar2 = zzeo.VOID;
        int i3 = i2 - 1;
        if (i3 == 1 || i3 == 3) {
            this.zzad = zzeoVar.zza();
        } else {
            this.zzad = null;
        }
        if (i2 == 1) {
            zzeoVar.ordinal();
        }
    }

    public final int zza() {
        return this.zzac;
    }
}
