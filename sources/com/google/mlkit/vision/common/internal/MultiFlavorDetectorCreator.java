package com.google.mlkit.vision.common.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.inject.Provider;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* compiled from: com.google.mlkit:vision-common@@17.2.0 */
/* loaded from: classes2.dex */
public class MultiFlavorDetectorCreator {
    private final Map zza = new HashMap();

    /* compiled from: com.google.mlkit:vision-common@@17.2.0 */
    public interface DetectorCreator<DetectorT extends MultiFlavorDetector, OptionsT extends DetectorOptions<DetectorT>> {
        DetectorT create(OptionsT optionst);
    }

    /* compiled from: com.google.mlkit:vision-common@@17.2.0 */
    public interface DetectorOptions<DetectorT> {
    }

    /* compiled from: com.google.mlkit:vision-common@@17.2.0 */
    public interface MultiFlavorDetector {
    }

    /* compiled from: com.google.mlkit:vision-common@@17.2.0 */
    public static class Registration {
        private final Class zza;
        private final Provider zzb;
        private final int zzc;

        public <DetectorT extends MultiFlavorDetector, OptionsT extends DetectorOptions<DetectorT>> Registration(Class<? extends OptionsT> cls, Provider<? extends DetectorCreator<DetectorT, OptionsT>> provider) {
            this(cls, provider, 100);
        }

        public <DetectorT extends MultiFlavorDetector, OptionsT extends DetectorOptions<DetectorT>> Registration(Class<? extends OptionsT> cls, Provider<? extends DetectorCreator<DetectorT, OptionsT>> provider, int i) {
            this.zza = cls;
            this.zzb = provider;
            this.zzc = i;
        }

        final int zza() {
            return this.zzc;
        }

        final Provider zzb() {
            return this.zzb;
        }

        final Class zzc() {
            return this.zza;
        }
    }

    MultiFlavorDetectorCreator(Set set) {
        HashMap map = new HashMap();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            Registration registration = (Registration) it.next();
            Class clsZzc = registration.zzc();
            if (!this.zza.containsKey(clsZzc) || registration.zza() >= ((Integer) Preconditions.checkNotNull((Integer) map.get(clsZzc))).intValue()) {
                this.zza.put(clsZzc, registration.zzb());
                map.put(clsZzc, Integer.valueOf(registration.zza()));
            }
        }
    }

    public static synchronized MultiFlavorDetectorCreator getInstance() {
        return (MultiFlavorDetectorCreator) MlKitContext.getInstance().get(MultiFlavorDetectorCreator.class);
    }

    public <DetectorT extends MultiFlavorDetector, OptionsT extends DetectorOptions<DetectorT>> DetectorT create(OptionsT optionst) {
        return (DetectorT) ((DetectorCreator) ((Provider) Preconditions.checkNotNull((Provider) this.zza.get(optionst.getClass()))).get()).create(optionst);
    }
}
