package com.onfido.workflow.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.ResultReceiver;
import androidx.fragment.app.Fragment;
import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.EnterpriseFeatures;
import com.onfido.android.sdk.capture.OnfidoTheme;
import com.onfido.workflow.WorkflowConfig;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WorkflowConfigImpl.kt */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0081\b\u0018\u00002\u00020\u0001Bi\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\n\u0012\u0006\u0010\u0010\u001a\u00020\u0011¢\u0006\u0002\u0010\u0012J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0011HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\bHÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\nHÆ\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\nHÆ\u0003J\t\u0010*\u001a\u00020\rHÆ\u0003J\t\u0010+\u001a\u00020\rHÆ\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\nHÆ\u0003Jw\u0010-\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\r2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u0010\u001a\u00020\u0011HÆ\u0001J\b\u0010.\u001a\u00020/H\u0016J\t\u00100\u001a\u000201HÖ\u0001J\u0013\u00102\u001a\u00020\r2\b\u00103\u001a\u0004\u0018\u000104HÖ\u0003J\t\u00105\u001a\u000201HÖ\u0001J\t\u00106\u001a\u00020\u0003HÖ\u0001J\u0019\u00107\u001a\u0002082\u0006\u00109\u001a\u00020:2\u0006\u0010;\u001a\u000201HÖ\u0001R\u0014\u0010\u000e\u001a\u00020\rX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0016\u0010\u000b\u001a\u0004\u0018\u00010\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0016\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0016\u0010\t\u001a\u0004\u0018\u00010\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0016R\u0016\u0010\u000f\u001a\u0004\u0018\u00010\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0014\u0010\u0010\u001a\u00020\u0011X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0014\u0010\f\u001a\u00020\rX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0014R\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001e¨\u0006<"}, d2 = {"Lcom/onfido/workflow/internal/WorkflowConfigImpl;", "Lcom/onfido/workflow/WorkflowConfig;", "sdkToken", "", "workflowRunId", "locale", "Ljava/util/Locale;", "enterpriseFeatures", "Lcom/onfido/android/sdk/capture/EnterpriseFeatures;", "mediaCallback", "Landroid/os/ResultReceiver;", "biometricTokenCallback", "tokenExpirationHandlerEnabled", "", "backgroundRunDisabled", "onfidoAnalyticsEventListener", "theme", "Lcom/onfido/android/sdk/capture/OnfidoTheme;", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/Locale;Lcom/onfido/android/sdk/capture/EnterpriseFeatures;Landroid/os/ResultReceiver;Landroid/os/ResultReceiver;ZZLandroid/os/ResultReceiver;Lcom/onfido/android/sdk/capture/OnfidoTheme;)V", "getBackgroundRunDisabled", "()Z", "getBiometricTokenCallback", "()Landroid/os/ResultReceiver;", "getEnterpriseFeatures", "()Lcom/onfido/android/sdk/capture/EnterpriseFeatures;", "getLocale", "()Ljava/util/Locale;", "getMediaCallback", "getOnfidoAnalyticsEventListener", "getSdkToken", "()Ljava/lang/String;", "getTheme", "()Lcom/onfido/android/sdk/capture/OnfidoTheme;", "getTokenExpirationHandlerEnabled", "getWorkflowRunId", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", Constants.COPY_TYPE, "createFragment", "Landroidx/fragment/app/Fragment;", "describeContents", "", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "onfido-workflow-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final /* data */ class WorkflowConfigImpl implements WorkflowConfig {
    public static final Parcelable.Creator<WorkflowConfigImpl> CREATOR = new Creator();
    private final boolean backgroundRunDisabled;
    private final ResultReceiver biometricTokenCallback;
    private final EnterpriseFeatures enterpriseFeatures;
    private final Locale locale;
    private final ResultReceiver mediaCallback;
    private final ResultReceiver onfidoAnalyticsEventListener;
    private final String sdkToken;
    private final OnfidoTheme theme;
    private final boolean tokenExpirationHandlerEnabled;
    private final String workflowRunId;

    /* compiled from: WorkflowConfigImpl.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class Creator implements Parcelable.Creator<WorkflowConfigImpl> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final WorkflowConfigImpl createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new WorkflowConfigImpl(parcel.readString(), parcel.readString(), (Locale) parcel.readSerializable(), (EnterpriseFeatures) parcel.readParcelable(WorkflowConfigImpl.class.getClassLoader()), (ResultReceiver) parcel.readParcelable(WorkflowConfigImpl.class.getClassLoader()), (ResultReceiver) parcel.readParcelable(WorkflowConfigImpl.class.getClassLoader()), parcel.readInt() != 0, parcel.readInt() != 0, (ResultReceiver) parcel.readParcelable(WorkflowConfigImpl.class.getClassLoader()), OnfidoTheme.valueOf(parcel.readString()));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final WorkflowConfigImpl[] newArray(int i) {
            return new WorkflowConfigImpl[i];
        }
    }

    /* renamed from: component1, reason: from getter */
    public final String getSdkToken() {
        return this.sdkToken;
    }

    /* renamed from: component10, reason: from getter */
    public final OnfidoTheme getTheme() {
        return this.theme;
    }

    /* renamed from: component2, reason: from getter */
    public final String getWorkflowRunId() {
        return this.workflowRunId;
    }

    /* renamed from: component3, reason: from getter */
    public final Locale getLocale() {
        return this.locale;
    }

    /* renamed from: component4, reason: from getter */
    public final EnterpriseFeatures getEnterpriseFeatures() {
        return this.enterpriseFeatures;
    }

    /* renamed from: component5, reason: from getter */
    public final ResultReceiver getMediaCallback() {
        return this.mediaCallback;
    }

    /* renamed from: component6, reason: from getter */
    public final ResultReceiver getBiometricTokenCallback() {
        return this.biometricTokenCallback;
    }

    /* renamed from: component7, reason: from getter */
    public final boolean getTokenExpirationHandlerEnabled() {
        return this.tokenExpirationHandlerEnabled;
    }

    /* renamed from: component8, reason: from getter */
    public final boolean getBackgroundRunDisabled() {
        return this.backgroundRunDisabled;
    }

    /* renamed from: component9, reason: from getter */
    public final ResultReceiver getOnfidoAnalyticsEventListener() {
        return this.onfidoAnalyticsEventListener;
    }

    public final WorkflowConfigImpl copy(String sdkToken, String workflowRunId, Locale locale, EnterpriseFeatures enterpriseFeatures, ResultReceiver mediaCallback, ResultReceiver biometricTokenCallback, boolean tokenExpirationHandlerEnabled, boolean backgroundRunDisabled, ResultReceiver onfidoAnalyticsEventListener, OnfidoTheme theme) {
        Intrinsics.checkNotNullParameter(sdkToken, "sdkToken");
        Intrinsics.checkNotNullParameter(workflowRunId, "workflowRunId");
        Intrinsics.checkNotNullParameter(theme, "theme");
        return new WorkflowConfigImpl(sdkToken, workflowRunId, locale, enterpriseFeatures, mediaCallback, biometricTokenCallback, tokenExpirationHandlerEnabled, backgroundRunDisabled, onfidoAnalyticsEventListener, theme);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WorkflowConfigImpl)) {
            return false;
        }
        WorkflowConfigImpl workflowConfigImpl = (WorkflowConfigImpl) other;
        return Intrinsics.areEqual(this.sdkToken, workflowConfigImpl.sdkToken) && Intrinsics.areEqual(this.workflowRunId, workflowConfigImpl.workflowRunId) && Intrinsics.areEqual(this.locale, workflowConfigImpl.locale) && Intrinsics.areEqual(this.enterpriseFeatures, workflowConfigImpl.enterpriseFeatures) && Intrinsics.areEqual(this.mediaCallback, workflowConfigImpl.mediaCallback) && Intrinsics.areEqual(this.biometricTokenCallback, workflowConfigImpl.biometricTokenCallback) && this.tokenExpirationHandlerEnabled == workflowConfigImpl.tokenExpirationHandlerEnabled && this.backgroundRunDisabled == workflowConfigImpl.backgroundRunDisabled && Intrinsics.areEqual(this.onfidoAnalyticsEventListener, workflowConfigImpl.onfidoAnalyticsEventListener) && this.theme == workflowConfigImpl.theme;
    }

    @Override // com.onfido.android.sdk.FlowConfig
    public boolean getBackgroundRunDisabled() {
        return this.backgroundRunDisabled;
    }

    @Override // com.onfido.workflow.WorkflowConfig, com.onfido.android.sdk.FlowConfig
    public ResultReceiver getBiometricTokenCallback() {
        return this.biometricTokenCallback;
    }

    @Override // com.onfido.workflow.WorkflowConfig, com.onfido.android.sdk.FlowConfig
    public EnterpriseFeatures getEnterpriseFeatures() {
        return this.enterpriseFeatures;
    }

    @Override // com.onfido.workflow.WorkflowConfig, com.onfido.android.sdk.FlowConfig
    public Locale getLocale() {
        return this.locale;
    }

    @Override // com.onfido.workflow.WorkflowConfig, com.onfido.android.sdk.FlowConfig
    public ResultReceiver getMediaCallback() {
        return this.mediaCallback;
    }

    @Override // com.onfido.workflow.WorkflowConfig, com.onfido.android.sdk.FlowConfig
    public ResultReceiver getOnfidoAnalyticsEventListener() {
        return this.onfidoAnalyticsEventListener;
    }

    @Override // com.onfido.workflow.WorkflowConfig, com.onfido.android.sdk.FlowConfig
    public String getSdkToken() {
        return this.sdkToken;
    }

    @Override // com.onfido.workflow.WorkflowConfig, com.onfido.android.sdk.FlowConfig
    public OnfidoTheme getTheme() {
        return this.theme;
    }

    @Override // com.onfido.workflow.WorkflowConfig, com.onfido.android.sdk.FlowConfig
    public boolean getTokenExpirationHandlerEnabled() {
        return this.tokenExpirationHandlerEnabled;
    }

    @Override // com.onfido.workflow.WorkflowConfig, com.onfido.android.sdk.FlowConfig
    public String getWorkflowRunId() {
        return this.workflowRunId;
    }

    public int hashCode() {
        int iHashCode = ((this.sdkToken.hashCode() * 31) + this.workflowRunId.hashCode()) * 31;
        Locale locale = this.locale;
        int iHashCode2 = (iHashCode + (locale == null ? 0 : locale.hashCode())) * 31;
        EnterpriseFeatures enterpriseFeatures = this.enterpriseFeatures;
        int iHashCode3 = (iHashCode2 + (enterpriseFeatures == null ? 0 : enterpriseFeatures.hashCode())) * 31;
        ResultReceiver resultReceiver = this.mediaCallback;
        int iHashCode4 = (iHashCode3 + (resultReceiver == null ? 0 : resultReceiver.hashCode())) * 31;
        ResultReceiver resultReceiver2 = this.biometricTokenCallback;
        int iHashCode5 = (((((iHashCode4 + (resultReceiver2 == null ? 0 : resultReceiver2.hashCode())) * 31) + Boolean.hashCode(this.tokenExpirationHandlerEnabled)) * 31) + Boolean.hashCode(this.backgroundRunDisabled)) * 31;
        ResultReceiver resultReceiver3 = this.onfidoAnalyticsEventListener;
        return ((iHashCode5 + (resultReceiver3 != null ? resultReceiver3.hashCode() : 0)) * 31) + this.theme.hashCode();
    }

    public String toString() {
        return "WorkflowConfigImpl(sdkToken=" + this.sdkToken + ", workflowRunId=" + this.workflowRunId + ", locale=" + this.locale + ", enterpriseFeatures=" + this.enterpriseFeatures + ", mediaCallback=" + this.mediaCallback + ", biometricTokenCallback=" + this.biometricTokenCallback + ", tokenExpirationHandlerEnabled=" + this.tokenExpirationHandlerEnabled + ", backgroundRunDisabled=" + this.backgroundRunDisabled + ", onfidoAnalyticsEventListener=" + this.onfidoAnalyticsEventListener + ", theme=" + this.theme + ")";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        parcel.writeString(this.sdkToken);
        parcel.writeString(this.workflowRunId);
        parcel.writeSerializable(this.locale);
        parcel.writeParcelable(this.enterpriseFeatures, flags);
        parcel.writeParcelable(this.mediaCallback, flags);
        parcel.writeParcelable(this.biometricTokenCallback, flags);
        parcel.writeInt(this.tokenExpirationHandlerEnabled ? 1 : 0);
        parcel.writeInt(this.backgroundRunDisabled ? 1 : 0);
        parcel.writeParcelable(this.onfidoAnalyticsEventListener, flags);
        parcel.writeString(this.theme.name());
    }

    public WorkflowConfigImpl(String sdkToken, String workflowRunId, Locale locale, EnterpriseFeatures enterpriseFeatures, ResultReceiver resultReceiver, ResultReceiver resultReceiver2, boolean z, boolean z2, ResultReceiver resultReceiver3, OnfidoTheme theme) {
        Intrinsics.checkNotNullParameter(sdkToken, "sdkToken");
        Intrinsics.checkNotNullParameter(workflowRunId, "workflowRunId");
        Intrinsics.checkNotNullParameter(theme, "theme");
        this.sdkToken = sdkToken;
        this.workflowRunId = workflowRunId;
        this.locale = locale;
        this.enterpriseFeatures = enterpriseFeatures;
        this.mediaCallback = resultReceiver;
        this.biometricTokenCallback = resultReceiver2;
        this.tokenExpirationHandlerEnabled = z;
        this.backgroundRunDisabled = z2;
        this.onfidoAnalyticsEventListener = resultReceiver3;
        this.theme = theme;
    }

    public /* synthetic */ WorkflowConfigImpl(String str, String str2, Locale locale, EnterpriseFeatures enterpriseFeatures, ResultReceiver resultReceiver, ResultReceiver resultReceiver2, boolean z, boolean z2, ResultReceiver resultReceiver3, OnfidoTheme onfidoTheme, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? null : locale, (i & 8) != 0 ? null : enterpriseFeatures, (i & 16) != 0 ? null : resultReceiver, resultReceiver2, (i & 64) != 0 ? false : z, z2, (i & 256) != 0 ? null : resultReceiver3, onfidoTheme);
    }

    @Override // com.onfido.android.sdk.FlowConfig
    public Fragment createFragment() {
        return WorkflowExtensions.createWorkflowFragment();
    }
}
