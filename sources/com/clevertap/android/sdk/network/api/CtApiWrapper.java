package com.clevertap.android.sdk.network.api;

import android.content.Context;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.DeviceInfo;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CtApiWrapper.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8GX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/clevertap/android/sdk/network/api/CtApiWrapper;", "", "context", "Landroid/content/Context;", "config", "Lcom/clevertap/android/sdk/CleverTapInstanceConfig;", "deviceInfo", "Lcom/clevertap/android/sdk/DeviceInfo;", "(Landroid/content/Context;Lcom/clevertap/android/sdk/CleverTapInstanceConfig;Lcom/clevertap/android/sdk/DeviceInfo;)V", "ctApi", "Lcom/clevertap/android/sdk/network/api/CtApi;", "getCtApi", "()Lcom/clevertap/android/sdk/network/api/CtApi;", "ctApi$delegate", "Lkotlin/Lazy;", "needsHandshake", "", "isViewedEvent", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class CtApiWrapper {
    private final CleverTapInstanceConfig config;
    private final Context context;

    /* renamed from: ctApi$delegate, reason: from kotlin metadata */
    private final Lazy ctApi;
    private final DeviceInfo deviceInfo;

    public CtApiWrapper(Context context, CleverTapInstanceConfig config, DeviceInfo deviceInfo) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(deviceInfo, "deviceInfo");
        this.context = context;
        this.config = config;
        this.deviceInfo = deviceInfo;
        this.ctApi = LazyKt.lazy(new Function0<CtApi>() { // from class: com.clevertap.android.sdk.network.api.CtApiWrapper$ctApi$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CtApi invoke() {
                return CtApiProvider.INSTANCE.provideDefaultCtApi$clevertap_core_release(this.this$0.context, this.this$0.config, this.this$0.deviceInfo);
            }
        });
    }

    public final CtApi getCtApi() {
        return (CtApi) this.ctApi.getValue();
    }

    public final boolean needsHandshake(boolean isViewedEvent) {
        return getCtApi().needsHandshake(isViewedEvent);
    }
}
