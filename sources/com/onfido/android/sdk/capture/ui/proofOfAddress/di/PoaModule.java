package com.onfido.android.sdk.capture.ui.proofOfAddress.di;

import android.content.Context;
import android.content.res.Resources;
import com.onfido.dagger.Module;
import com.onfido.dagger.Provides;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Module
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\ba\u0018\u0000 \u00022\u00020\u0001:\u0001\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/proofOfAddress/di/PoaModule;", "", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface PoaModule {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/proofOfAddress/di/PoaModule$Companion;", "", "()V", "provideResources", "Landroid/content/res/Resources;", "context", "Landroid/content/Context;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        @Provides
        public final Resources provideResources(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            Resources resources = context.getResources();
            Intrinsics.checkNotNullExpressionValue(resources, "getResources(...)");
            return resources;
        }
    }
}
