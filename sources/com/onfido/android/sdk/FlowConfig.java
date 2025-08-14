package com.onfido.android.sdk;

import android.os.Parcelable;
import android.os.ResultReceiver;
import androidx.fragment.app.Fragment;
import com.onfido.android.sdk.capture.EnterpriseFeatures;
import com.onfido.android.sdk.capture.OnfidoTheme;
import java.util.Locale;
import kotlin.Metadata;

/* compiled from: FlowConfig.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\b\u0010\"\u001a\u00020#H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u0004\u0018\u00010\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u0004\u0018\u00010\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u0004\u0018\u00010\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u0004\u0018\u00010\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\tR\u0014\u0010\u0014\u001a\u0004\u0018\u00010\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\tR\u0012\u0010\u0016\u001a\u00020\u0017X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0012\u0010\u001a\u001a\u00020\u001bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0012\u0010\u001e\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0005R\u0014\u0010 \u001a\u0004\u0018\u00010\u0017X¦\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\u0019¨\u0006$"}, d2 = {"Lcom/onfido/android/sdk/FlowConfig;", "Landroid/os/Parcelable;", "backgroundRunDisabled", "", "getBackgroundRunDisabled", "()Z", "biometricTokenCallback", "Landroid/os/ResultReceiver;", "getBiometricTokenCallback", "()Landroid/os/ResultReceiver;", "enterpriseFeatures", "Lcom/onfido/android/sdk/capture/EnterpriseFeatures;", "getEnterpriseFeatures", "()Lcom/onfido/android/sdk/capture/EnterpriseFeatures;", "locale", "Ljava/util/Locale;", "getLocale", "()Ljava/util/Locale;", "mediaCallback", "getMediaCallback", "onfidoAnalyticsEventListener", "getOnfidoAnalyticsEventListener", "sdkToken", "", "getSdkToken", "()Ljava/lang/String;", "theme", "Lcom/onfido/android/sdk/capture/OnfidoTheme;", "getTheme", "()Lcom/onfido/android/sdk/capture/OnfidoTheme;", "tokenExpirationHandlerEnabled", "getTokenExpirationHandlerEnabled", "workflowRunId", "getWorkflowRunId", "createFragment", "Landroidx/fragment/app/Fragment;", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface FlowConfig extends Parcelable {
    Fragment createFragment();

    boolean getBackgroundRunDisabled();

    ResultReceiver getBiometricTokenCallback();

    EnterpriseFeatures getEnterpriseFeatures();

    Locale getLocale();

    ResultReceiver getMediaCallback();

    ResultReceiver getOnfidoAnalyticsEventListener();

    String getSdkToken();

    OnfidoTheme getTheme();

    boolean getTokenExpirationHandlerEnabled();

    String getWorkflowRunId();
}
