package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcj;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzck;
import java.io.IOException;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
public abstract class zzck<MessageType extends zzck<MessageType, BuilderType>, BuilderType extends zzcj<MessageType, BuilderType>> implements zzfl {
    protected int zzb = 0;

    int zzB() {
        throw null;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfl
    public final zzdb zzC() {
        try {
            int iZzE = zzE();
            zzdb zzdbVar = zzdb.zzb;
            byte[] bArr = new byte[iZzE];
            zzdi zzdiVarZzF = zzdi.zzF(bArr);
            zzW(zzdiVarZzF);
            zzdiVarZzF.zzG();
            return new zzcz(bArr);
        } catch (IOException e) {
            String name = getClass().getName();
            StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 72);
            sb.append("Serializing ");
            sb.append(name);
            sb.append(" to a ByteString threw an IOException (should never happen).");
            throw new RuntimeException(sb.toString(), e);
        }
    }

    void zzD(int i) {
        throw null;
    }
}
