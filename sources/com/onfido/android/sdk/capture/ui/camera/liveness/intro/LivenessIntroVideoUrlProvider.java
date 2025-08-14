package com.onfido.android.sdk.capture.ui.camera.liveness.intro;

import com.onfido.android.sdk.capture.BuildConfig;
import com.onfido.android.sdk.capture.ui.camera.liveness.intro.error.LivenessIntroVideoIndexEmpty;
import com.onfido.javax.inject.Inject;
import io.reactivex.rxjava3.core.Single;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\b\u0016\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0007\b\u0007¢\u0006\u0002\u0010\u0002J\u000e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016J\u001c\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000eH\u0016R\u001b\u0010\u0003\u001a\u00020\u00048RX\u0092\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/liveness/intro/LivenessIntroVideoUrlProvider;", "", "()V", "random", "Ljava/security/SecureRandom;", "getRandom", "()Ljava/security/SecureRandom;", "random$delegate", "Lkotlin/Lazy;", "getIndexUrl", "Lio/reactivex/rxjava3/core/Single;", "", "getVideoUrl", "videoUrlsList", "", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class LivenessIntroVideoUrlProvider {
    private static final String LIVENESS_INTRO_VIDEO_NAME_ROOT = "liveness_intro";

    /* renamed from: random$delegate, reason: from kotlin metadata */
    private final Lazy random = LazyKt.lazy(new Function0<SecureRandom>() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.intro.LivenessIntroVideoUrlProvider$random$2
        @Override // kotlin.jvm.functions.Function0
        public final SecureRandom invoke() {
            return new SecureRandom();
        }
    });

    @Inject
    public LivenessIntroVideoUrlProvider() {
    }

    private SecureRandom getRandom() {
        return (SecureRandom) this.random.getValue();
    }

    public Single<String> getIndexUrl() {
        Single<String> singleJust = Single.just("https://assets.onfido.com/mobile-sdk-assets/android/v2/index.json");
        Intrinsics.checkNotNullExpressionValue(singleJust, "just(...)");
        return singleJust;
    }

    public Single<String> getVideoUrl(List<String> videoUrlsList) {
        Intrinsics.checkNotNullParameter(videoUrlsList, "videoUrlsList");
        ArrayList arrayList = new ArrayList();
        for (Object obj : videoUrlsList) {
            if (StringsKt.contains$default((CharSequence) obj, (CharSequence) LIVENESS_INTRO_VIDEO_NAME_ROOT, false, 2, (Object) null)) {
                arrayList.add(obj);
            }
        }
        Single<String> singleError = arrayList.isEmpty() ? Single.error(new LivenessIntroVideoIndexEmpty()) : Single.just(BuildConfig.SDK_ASSETS_REMOTE_FOLDER + ((String) arrayList.get(getRandom().nextInt(arrayList.size()))));
        Intrinsics.checkNotNull(singleError);
        return singleError;
    }
}
