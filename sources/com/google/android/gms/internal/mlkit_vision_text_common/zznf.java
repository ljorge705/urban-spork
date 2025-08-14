package com.google.android.gms.internal.mlkit_vision_text_common;

import android.content.Context;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Transformer;
import com.google.android.datatransport.Transport;
import com.google.android.datatransport.TransportFactory;
import com.google.android.datatransport.cct.CCTDestination;
import com.google.android.datatransport.runtime.TransportRuntime;
import com.google.firebase.components.Lazy;
import com.google.firebase.inject.Provider;

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
/* loaded from: classes3.dex */
public final class zznf implements zzmw {
    private Provider zza;
    private final Provider zzb;
    private final zzmq zzc;

    public zznf(Context context, zzmq zzmqVar) {
        this.zzc = zzmqVar;
        CCTDestination cCTDestination = CCTDestination.INSTANCE;
        TransportRuntime.initialize(context);
        final TransportFactory transportFactoryNewFactory = TransportRuntime.getInstance().newFactory(cCTDestination);
        if (cCTDestination.getSupportedEncodings().contains(Encoding.of("json"))) {
            this.zza = new Lazy(new Provider() { // from class: com.google.android.gms.internal.mlkit_vision_text_common.zznd
                @Override // com.google.firebase.inject.Provider
                public final Object get() {
                    return transportFactoryNewFactory.getTransport("FIREBASE_ML_SDK", byte[].class, Encoding.of("json"), new Transformer() { // from class: com.google.android.gms.internal.mlkit_vision_text_common.zznb
                        @Override // com.google.android.datatransport.Transformer
                        public final Object apply(Object obj) {
                            return (byte[]) obj;
                        }
                    });
                }
            });
        }
        this.zzb = new Lazy(new Provider() { // from class: com.google.android.gms.internal.mlkit_vision_text_common.zzne
            @Override // com.google.firebase.inject.Provider
            public final Object get() {
                return transportFactoryNewFactory.getTransport("FIREBASE_ML_SDK", byte[].class, Encoding.of("proto"), new Transformer() { // from class: com.google.android.gms.internal.mlkit_vision_text_common.zznc
                    @Override // com.google.android.datatransport.Transformer
                    public final Object apply(Object obj) {
                        return (byte[]) obj;
                    }
                });
            }
        });
    }

    static Event zzb(zzmq zzmqVar, zzna zznaVar) {
        int iZza = zzmqVar.zza();
        return zznaVar.zza() != 0 ? Event.ofData(zznaVar.zzc(iZza, false)) : Event.ofTelemetry(zznaVar.zzc(iZza, false));
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzmw
    public final void zza(zzna zznaVar) {
        if (this.zzc.zza() != 0) {
            ((Transport) this.zzb.get()).send(zzb(this.zzc, zznaVar));
            return;
        }
        Provider provider = this.zza;
        if (provider != null) {
            ((Transport) provider.get()).send(zzb(this.zzc, zznaVar));
        }
    }
}
