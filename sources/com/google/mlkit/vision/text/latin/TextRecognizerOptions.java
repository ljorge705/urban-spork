package com.google.mlkit.vision.text.latin;

import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import com.google.mlkit.vision.text.TextRecognizerOptionsInterface;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition@@18.0.0 */
/* loaded from: classes2.dex */
public class TextRecognizerOptions implements TextRecognizerOptionsInterface {
    public static final TextRecognizerOptions DEFAULT_OPTIONS = new Builder().build();
    final AtomicReference zza = new AtomicReference();
    private final Executor zzb;

    /* compiled from: com.google.android.gms:play-services-mlkit-text-recognition@@18.0.0 */
    public static class Builder {
        private Executor zza;

        public TextRecognizerOptions build() {
            return new TextRecognizerOptions(this.zza, null);
        }

        public Builder setExecutor(Executor executor) {
            this.zza = executor;
            return this;
        }
    }

    /* synthetic */ TextRecognizerOptions(Executor executor, zza zzaVar) {
        this.zzb = executor;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof TextRecognizerOptions) {
            return Objects.equal(this.zzb, ((TextRecognizerOptions) obj).zzb);
        }
        return false;
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    public final String getCreatorClass() {
        return true != getIsThickClient() ? "com.google.android.gms.vision.text.mlkit.TextRecognizerCreator" : "com.google.mlkit.vision.text.bundled.latin.BundledLatinTextRecognizerCreator";
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    public final Executor getExecutor() {
        return this.zzb;
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    public final boolean getIsThickClient() {
        if (this.zza.get() != null) {
            return ((Boolean) this.zza.get()).booleanValue();
        }
        boolean z = DynamiteModule.getLocalVersion(MlKitContext.getInstance().getApplicationContext(), "com.google.mlkit.dynamite.text.latin") > 0;
        this.zza.set(Boolean.valueOf(z));
        return z;
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    public final int getLoggingEventId() {
        return getIsThickClient() ? 24317 : 24306;
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    public final int getLoggingLanguageOption() {
        return 1;
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    public final String getLoggingLibraryName() {
        return true != getIsThickClient() ? "play-services-mlkit-text-recognition" : "text-recognition";
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    public final String getModuleId() {
        return true != getIsThickClient() ? OptionalModuleUtils.OCR_MODULE_ID : "com.google.mlkit.dynamite.text.latin";
    }

    public int hashCode() {
        return Objects.hashCode(this.zzb);
    }
}
