package com.onfido.android.sdk.capture.core;

import android.content.Context;
import android.content.Intent;
import androidx.activity.result.contract.ActivityResultContract;
import com.onfido.android.sdk.FlowConfig;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0004J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0002H\u0016J\u001a\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\tH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/onfido/android/sdk/capture/core/OnfidoLauncher;", "Landroidx/activity/result/contract/ActivityResultContract;", "Lcom/onfido/android/sdk/FlowConfig;", "Lcom/onfido/android/sdk/capture/core/OnfidoResult;", "()V", "KEY_CONFIG", "", "KEY_RESULT", "createIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "input", "parseResult", "resultCode", "", "intent", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class OnfidoLauncher extends ActivityResultContract<FlowConfig, OnfidoResult> {
    public static final OnfidoLauncher INSTANCE = new OnfidoLauncher();
    public static final String KEY_CONFIG = "configuration";
    public static final String KEY_RESULT = "result";

    private OnfidoLauncher() {
    }

    @Override // androidx.activity.result.contract.ActivityResultContract
    public Intent createIntent(Context context, FlowConfig input) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(input, "input");
        Intent intent = new Intent(context, (Class<?>) CoreActivity.class);
        intent.putExtra(KEY_CONFIG, input);
        return intent;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.activity.result.contract.ActivityResultContract
    public OnfidoResult parseResult(int resultCode, Intent intent) {
        OnfidoResult onfidoResult = intent != null ? (OnfidoResult) intent.getParcelableExtra(KEY_RESULT) : null;
        return onfidoResult == null ? new OnfidoResult(CollectionsKt.emptyList(), false) : onfidoResult;
    }
}
