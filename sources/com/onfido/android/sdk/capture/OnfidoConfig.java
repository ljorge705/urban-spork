package com.onfido.android.sdk.capture;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.ResultReceiver;
import com.onfido.android.sdk.FlowConfig;
import com.onfido.android.sdk.capture.analytics.OnfidoAnalyticsEventListener;
import com.onfido.android.sdk.capture.analytics.OnfidoAnalyticsEventResultReceiver;
import com.onfido.android.sdk.capture.config.MediaCallback;
import com.onfido.android.sdk.capture.config.MediaCallbackResultReceiver;
import com.onfido.android.sdk.capture.document.GenericDocument;
import com.onfido.android.sdk.capture.errors.DuplicatedFaceCaptureVariantException;
import com.onfido.android.sdk.capture.errors.DuplicatedFlowStepException;
import com.onfido.android.sdk.capture.errors.EnterpriseFeatureNotEnabledException;
import com.onfido.android.sdk.capture.errors.EnterpriseFeatureWithoutSDKTokenException;
import com.onfido.android.sdk.capture.errors.EnterpriseFeaturesConflictingException;
import com.onfido.android.sdk.capture.errors.EnterpriseFeaturesInvalidLogoCobrandingException;
import com.onfido.android.sdk.capture.errors.InvalidDocumentTypeException;
import com.onfido.android.sdk.capture.errors.MissingTokenException;
import com.onfido.android.sdk.capture.errors.MultipleTokenException;
import com.onfido.android.sdk.capture.flow.FlowValidation;
import com.onfido.android.sdk.capture.model.NFCOptions;
import com.onfido.android.sdk.capture.token.TokenExpirationHandler;
import com.onfido.android.sdk.capture.token.TokenExpirationHandlerService;
import com.onfido.android.sdk.capture.ui.options.BaseOptions;
import com.onfido.android.sdk.capture.ui.options.CaptureScreenOptions;
import com.onfido.android.sdk.capture.ui.options.CaptureScreenStep;
import com.onfido.android.sdk.capture.ui.options.FlowAction;
import com.onfido.android.sdk.capture.ui.options.FlowStep;
import com.onfido.android.sdk.capture.utils.checker.Validator;
import com.onfido.api.client.token.Token;
import com.onfido.api.client.token.sdk.SDKToken;
import com.onfido.api.client.token.sdk.TokenEnterpriseFeatures;
import io.sentry.protocol.OperatingSystem;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: OnfidoConfig.kt */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b$\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0017\u0018\u0000 K2\u00020\u0001:\u0002JKBÓ\u0001\b\u0002\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\f\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\f\u0012\u0006\u0010\u000f\u001a\u00020\n\u0012\u000e\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0003\u0012\u0006\u0010\u0012\u001a\u00020\n\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0016\u0012\u000e\b\u0002\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018\u0012\u000e\b\u0002\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0018\u0012\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001d\u0012\b\b\u0002\u0010\u001e\u001a\u00020\u001f\u0012\n\b\u0003\u0010 \u001a\u0004\u0018\u00010!\u0012\n\b\u0003\u0010\"\u001a\u0004\u0018\u00010!¢\u0006\u0002\u0010#J\t\u0010C\u001a\u00020!HÖ\u0001J\u000e\u0010D\u001a\b\u0012\u0004\u0012\u00020\u00040\u0018H\u0016J\u0019\u0010E\u001a\u00020F2\u0006\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020!HÖ\u0001R\u001e\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0003X\u0096\u0004¢\u0006\n\n\u0002\u0010&\u001a\u0004\b$\u0010%R\u0016\u0010\r\u001a\u0004\u0018\u00010\fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0018\u0010\"\u001a\u0004\u0018\u00010!X\u0096\u0004¢\u0006\n\n\u0002\u0010+\u001a\u0004\b)\u0010*R\u001a\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-R\u0016\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b.\u0010/R\u0014\u0010\u000f\u001a\u00020\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b0\u00101R\u0018\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003X\u0092\u0004¢\u0006\u0004\n\u0002\u00102R\u001a\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0018X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b3\u0010-R\u0018\u0010 \u001a\u0004\u0018\u00010!X\u0096\u0004¢\u0006\n\n\u0002\u0010+\u001a\u0004\b4\u0010*R\u0016\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b5\u00106R\u0014\u0010\u0012\u001a\u00020\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b7\u00101R\u0016\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b8\u0010(R\u0014\u0010\u0015\u001a\u00020\u0016X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b9\u0010:R\u0016\u0010\u000e\u001a\u0004\u0018\u00010\fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b;\u0010(R\u0014\u0010\u001e\u001a\u00020\u001fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b<\u0010=R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b>\u0010?R\u0014\u0010\t\u001a\u00020\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b@\u00101R\u0016\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bA\u0010B¨\u0006L"}, d2 = {"Lcom/onfido/android/sdk/capture/OnfidoConfig;", "Landroid/os/Parcelable;", "flowSteps", "", "Lcom/onfido/android/sdk/capture/ui/options/FlowStep;", "token", "Lcom/onfido/api/client/token/Token;", "locale", "Ljava/util/Locale;", "tokenExpirationHandlerEnabled", "", "mediaCallback", "Landroid/os/ResultReceiver;", "biometricTokenCallback", "onfidoAnalyticsEventListener", "exitWhenSentToBackground", "apiCertificatePinningPKHashes", "", "manualLivenessCapture", "workflowConfig", "Lcom/onfido/android/sdk/FlowConfig;", "nfcOptions", "Lcom/onfido/android/sdk/capture/model/NFCOptions;", "documentTypes", "", "Lcom/onfido/android/sdk/capture/DocumentType;", "genericDocuments", "Lcom/onfido/android/sdk/capture/document/GenericDocument;", "enterpriseFeatures", "Lcom/onfido/android/sdk/capture/EnterpriseFeatures;", "theme", "Lcom/onfido/android/sdk/capture/OnfidoTheme;", "lightThemeResId", "", "darkThemeResId", "([Lcom/onfido/android/sdk/capture/ui/options/FlowStep;Lcom/onfido/api/client/token/Token;Ljava/util/Locale;ZLandroid/os/ResultReceiver;Landroid/os/ResultReceiver;Landroid/os/ResultReceiver;Z[Ljava/lang/String;ZLcom/onfido/android/sdk/FlowConfig;Lcom/onfido/android/sdk/capture/model/NFCOptions;Ljava/util/List;Ljava/util/List;Lcom/onfido/android/sdk/capture/EnterpriseFeatures;Lcom/onfido/android/sdk/capture/OnfidoTheme;Ljava/lang/Integer;Ljava/lang/Integer;)V", "getApiCertificatePinningPKHashes", "()[Ljava/lang/String;", "[Ljava/lang/String;", "getBiometricTokenCallback", "()Landroid/os/ResultReceiver;", "getDarkThemeResId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getDocumentTypes", "()Ljava/util/List;", "getEnterpriseFeatures", "()Lcom/onfido/android/sdk/capture/EnterpriseFeatures;", "getExitWhenSentToBackground", "()Z", "[Lcom/onfido/android/sdk/capture/ui/options/FlowStep;", "getGenericDocuments", "getLightThemeResId", "getLocale", "()Ljava/util/Locale;", "getManualLivenessCapture", "getMediaCallback", "getNfcOptions", "()Lcom/onfido/android/sdk/capture/model/NFCOptions;", "getOnfidoAnalyticsEventListener", "getTheme", "()Lcom/onfido/android/sdk/capture/OnfidoTheme;", "getToken", "()Lcom/onfido/api/client/token/Token;", "getTokenExpirationHandlerEnabled", "getWorkflowConfig", "()Lcom/onfido/android/sdk/FlowConfig;", "describeContents", "getFlowSteps", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "Builder", "Companion", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class OnfidoConfig implements Parcelable {
    private final String[] apiCertificatePinningPKHashes;
    private final ResultReceiver biometricTokenCallback;
    private final Integer darkThemeResId;
    private final List<DocumentType> documentTypes;
    private final EnterpriseFeatures enterpriseFeatures;
    private final boolean exitWhenSentToBackground;
    private final FlowStep[] flowSteps;
    private final List<GenericDocument> genericDocuments;
    private final Integer lightThemeResId;
    private final Locale locale;
    private final boolean manualLivenessCapture;
    private final ResultReceiver mediaCallback;
    private final NFCOptions nfcOptions;
    private final ResultReceiver onfidoAnalyticsEventListener;
    private final OnfidoTheme theme;
    private final Token token;
    private final boolean tokenExpirationHandlerEnabled;
    private final FlowConfig workflowConfig;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final Parcelable.Creator<OnfidoConfig> CREATOR = new Creator();
    private static final List<DocumentType> ALLOWED_DOCUMENT_TYPES = CollectionsKt.listOf((Object[]) new DocumentType[]{DocumentType.PASSPORT, DocumentType.RESIDENCE_PERMIT, DocumentType.NATIONAL_IDENTITY_CARD, DocumentType.DRIVING_LICENCE});

    /* compiled from: OnfidoConfig.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class Creator implements Parcelable.Creator<OnfidoConfig> {
        /* JADX WARN: Can't rename method to resolve collision */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.os.Parcelable.Creator
        public final OnfidoConfig createFromParcel(Parcel parcel) {
            FlowStep[] flowStepArr;
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            if (parcel.readInt() == 0) {
                flowStepArr = null;
            } else {
                int i = parcel.readInt();
                FlowStep[] flowStepArr2 = new FlowStep[i];
                for (int i2 = 0; i2 != i; i2++) {
                    flowStepArr2[i2] = parcel.readSerializable();
                }
                flowStepArr = flowStepArr2;
            }
            Token token = (Token) parcel.readSerializable();
            Locale locale = (Locale) parcel.readSerializable();
            boolean z = parcel.readInt() != 0;
            ResultReceiver resultReceiver = (ResultReceiver) parcel.readParcelable(OnfidoConfig.class.getClassLoader());
            ResultReceiver resultReceiver2 = (ResultReceiver) parcel.readParcelable(OnfidoConfig.class.getClassLoader());
            ResultReceiver resultReceiver3 = (ResultReceiver) parcel.readParcelable(OnfidoConfig.class.getClassLoader());
            boolean z2 = parcel.readInt() != 0;
            String[] strArrCreateStringArray = parcel.createStringArray();
            boolean z3 = parcel.readInt() != 0;
            FlowConfig flowConfig = (FlowConfig) parcel.readParcelable(OnfidoConfig.class.getClassLoader());
            NFCOptions nFCOptions = (NFCOptions) parcel.readParcelable(OnfidoConfig.class.getClassLoader());
            int i3 = parcel.readInt();
            ArrayList arrayList = new ArrayList(i3);
            for (int i4 = 0; i4 != i3; i4++) {
                arrayList.add(DocumentType.valueOf(parcel.readString()));
            }
            ArrayList arrayList2 = arrayList;
            int i5 = parcel.readInt();
            ArrayList arrayList3 = new ArrayList(i5);
            for (int i6 = 0; i6 != i5; i6++) {
                arrayList3.add(parcel.readSerializable());
            }
            return new OnfidoConfig(flowStepArr, token, locale, z, resultReceiver, resultReceiver2, resultReceiver3, z2, strArrCreateStringArray, z3, flowConfig, nFCOptions, arrayList2, arrayList3, parcel.readInt() == 0 ? null : EnterpriseFeatures.CREATOR.createFromParcel(parcel), OnfidoTheme.valueOf(parcel.readString()), parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt()), parcel.readInt() != 0 ? Integer.valueOf(parcel.readInt()) : null, null);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final OnfidoConfig[] newArray(int i) {
            return new OnfidoConfig[i];
        }
    }

    public /* synthetic */ OnfidoConfig(FlowStep[] flowStepArr, Token token, Locale locale, boolean z, ResultReceiver resultReceiver, ResultReceiver resultReceiver2, ResultReceiver resultReceiver3, boolean z2, String[] strArr, boolean z3, FlowConfig flowConfig, NFCOptions nFCOptions, List list, List list2, EnterpriseFeatures enterpriseFeatures, OnfidoTheme onfidoTheme, Integer num, Integer num2, DefaultConstructorMarker defaultConstructorMarker) {
        this(flowStepArr, token, locale, z, resultReceiver, resultReceiver2, resultReceiver3, z2, strArr, z3, flowConfig, nFCOptions, list, list2, enterpriseFeatures, onfidoTheme, num, num2);
    }

    @JvmStatic
    public static final Builder builder(Context context) {
        return INSTANCE.builder(context);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String[] getApiCertificatePinningPKHashes() {
        return this.apiCertificatePinningPKHashes;
    }

    public ResultReceiver getBiometricTokenCallback() {
        return this.biometricTokenCallback;
    }

    public Integer getDarkThemeResId() {
        return this.darkThemeResId;
    }

    public List<DocumentType> getDocumentTypes() {
        return this.documentTypes;
    }

    public EnterpriseFeatures getEnterpriseFeatures() {
        return this.enterpriseFeatures;
    }

    public boolean getExitWhenSentToBackground() {
        return this.exitWhenSentToBackground;
    }

    public List<GenericDocument> getGenericDocuments() {
        return this.genericDocuments;
    }

    public Integer getLightThemeResId() {
        return this.lightThemeResId;
    }

    public Locale getLocale() {
        return this.locale;
    }

    public boolean getManualLivenessCapture() {
        return this.manualLivenessCapture;
    }

    public ResultReceiver getMediaCallback() {
        return this.mediaCallback;
    }

    public NFCOptions getNfcOptions() {
        return this.nfcOptions;
    }

    public ResultReceiver getOnfidoAnalyticsEventListener() {
        return this.onfidoAnalyticsEventListener;
    }

    public OnfidoTheme getTheme() {
        return this.theme;
    }

    public Token getToken() {
        return this.token;
    }

    public boolean getTokenExpirationHandlerEnabled() {
        return this.tokenExpirationHandlerEnabled;
    }

    public FlowConfig getWorkflowConfig() {
        return this.workflowConfig;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        FlowStep[] flowStepArr = this.flowSteps;
        if (flowStepArr == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            int length = flowStepArr.length;
            parcel.writeInt(length);
            for (int i = 0; i != length; i++) {
                parcel.writeSerializable(flowStepArr[i]);
            }
        }
        parcel.writeSerializable(this.token);
        parcel.writeSerializable(this.locale);
        parcel.writeInt(this.tokenExpirationHandlerEnabled ? 1 : 0);
        parcel.writeParcelable(this.mediaCallback, flags);
        parcel.writeParcelable(this.biometricTokenCallback, flags);
        parcel.writeParcelable(this.onfidoAnalyticsEventListener, flags);
        parcel.writeInt(this.exitWhenSentToBackground ? 1 : 0);
        parcel.writeStringArray(this.apiCertificatePinningPKHashes);
        parcel.writeInt(this.manualLivenessCapture ? 1 : 0);
        parcel.writeParcelable(this.workflowConfig, flags);
        parcel.writeParcelable(this.nfcOptions, flags);
        List<DocumentType> list = this.documentTypes;
        parcel.writeInt(list.size());
        Iterator<DocumentType> it = list.iterator();
        while (it.hasNext()) {
            parcel.writeString(it.next().name());
        }
        List<GenericDocument> list2 = this.genericDocuments;
        parcel.writeInt(list2.size());
        Iterator<GenericDocument> it2 = list2.iterator();
        while (it2.hasNext()) {
            parcel.writeSerializable(it2.next());
        }
        EnterpriseFeatures enterpriseFeatures = this.enterpriseFeatures;
        if (enterpriseFeatures == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            enterpriseFeatures.writeToParcel(parcel, flags);
        }
        parcel.writeString(this.theme.name());
        Integer num = this.lightThemeResId;
        if (num == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            parcel.writeInt(num.intValue());
        }
        Integer num2 = this.darkThemeResId;
        if (num2 == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            parcel.writeInt(num2.intValue());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private OnfidoConfig(FlowStep[] flowStepArr, Token token, Locale locale, boolean z, ResultReceiver resultReceiver, ResultReceiver resultReceiver2, ResultReceiver resultReceiver3, boolean z2, String[] strArr, boolean z3, FlowConfig flowConfig, NFCOptions nFCOptions, List<? extends DocumentType> list, List<GenericDocument> list2, EnterpriseFeatures enterpriseFeatures, OnfidoTheme onfidoTheme, Integer num, Integer num2) {
        this.flowSteps = flowStepArr;
        this.token = token;
        this.locale = locale;
        this.tokenExpirationHandlerEnabled = z;
        this.mediaCallback = resultReceiver;
        this.biometricTokenCallback = resultReceiver2;
        this.onfidoAnalyticsEventListener = resultReceiver3;
        this.exitWhenSentToBackground = z2;
        this.apiCertificatePinningPKHashes = strArr;
        this.manualLivenessCapture = z3;
        this.workflowConfig = flowConfig;
        this.nfcOptions = nFCOptions;
        this.documentTypes = list;
        this.genericDocuments = list2;
        this.enterpriseFeatures = enterpriseFeatures;
        this.theme = onfidoTheme;
        this.lightThemeResId = num;
        this.darkThemeResId = num2;
    }

    /* synthetic */ OnfidoConfig(FlowStep[] flowStepArr, Token token, Locale locale, boolean z, ResultReceiver resultReceiver, ResultReceiver resultReceiver2, ResultReceiver resultReceiver3, boolean z2, String[] strArr, boolean z3, FlowConfig flowConfig, NFCOptions nFCOptions, List list, List list2, EnterpriseFeatures enterpriseFeatures, OnfidoTheme onfidoTheme, Integer num, Integer num2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(flowStepArr, token, locale, (i & 8) != 0 ? false : z, resultReceiver, resultReceiver2, resultReceiver3, z2, strArr, z3, flowConfig, (i & 2048) != 0 ? NFCOptions.Enabled.Optional.INSTANCE : nFCOptions, (i & 4096) != 0 ? CollectionsKt.emptyList() : list, (i & 8192) != 0 ? CollectionsKt.emptyList() : list2, (i & 16384) != 0 ? null : enterpriseFeatures, (32768 & i) != 0 ? OnfidoTheme.AUTOMATIC : onfidoTheme, (65536 & i) != 0 ? null : num, (i & 131072) != 0 ? null : num2);
    }

    public List<FlowStep> getFlowSteps() {
        List<FlowStep> list;
        FlowStep[] flowStepArr = this.flowSteps;
        return (flowStepArr == null || (list = ArraysKt.toList(flowStepArr)) == null) ? FlowStep.INSTANCE.getDefaultFlow() : list;
    }

    /* compiled from: OnfidoConfig.kt */
    @Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010%\u001a\u00020&J\u0006\u0010\u000e\u001a\u00020\u0000J\u0012\u0010'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010\u001dH\u0002J\u001b\u0010*\u001a\u00020\u000f2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00110\u0006H\u0002¢\u0006\u0002\u0010,J\b\u0010-\u001a\u00020(H\u0002J\u001b\u0010.\u001a\u00020(2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00110\u0006H\u0002¢\u0006\u0002\u0010/J\b\u00100\u001a\u00020(H\u0002J\b\u00101\u001a\u00020(H\u0002J\u0014\u00102\u001a\u00020\u00002\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fJ\u000e\u00103\u001a\u00020\u00002\u0006\u00104\u001a\u000205J\u001b\u00106\u001a\u00020\u00002\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006¢\u0006\u0002\u00107J\u0019\u00108\u001a\u00020\u00002\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00110\u0006¢\u0006\u0002\u00109J\u000e\u0010:\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u001dJ\u0014\u0010;\u001a\u00020\u00002\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\fJ\u000e\u0010<\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010=\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u000fJ\u000e\u0010>\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020?J\u000e\u0010@\u001a\u00020\u00002\u0006\u0010A\u001a\u00020\u001aJ\u001c\u0010B\u001a\u00020\u00002\u0006\u0010 \u001a\u00020\u00072\n\b\u0002\u0010C\u001a\u0004\u0018\u00010DH\u0007J\u000e\u0010E\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\u001fJ\u0010\u0010F\u001a\u00020\u00002\u0006\u0010#\u001a\u00020$H\u0007J\f\u0010G\u001a\u00020\u000f*\u00020\u001dH\u0002R\u0018\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0012R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u0004\u0018\u00010$X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006H"}, d2 = {"Lcom/onfido/android/sdk/capture/OnfidoConfig$Builder;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "apiCertificatePinningPKHashes", "", "", "[Ljava/lang/String;", "biometricTokenCallback", "Landroid/os/ResultReceiver;", "documentTypes", "", "Lcom/onfido/android/sdk/capture/DocumentType;", "exitWhenSentToBackground", "", "flowStepsWithOptions", "Lcom/onfido/android/sdk/capture/ui/options/FlowStep;", "[Lcom/onfido/android/sdk/capture/ui/options/FlowStep;", "genericDocuments", "Lcom/onfido/android/sdk/capture/document/GenericDocument;", "locale", "Ljava/util/Locale;", "manualLivenessCapture", "mediaCallback", "nfcOptions", "Lcom/onfido/android/sdk/capture/model/NFCOptions;", "onfidoAnalyticsEventListener", "requestedEnterpriseFeatures", "Lcom/onfido/android/sdk/capture/EnterpriseFeatures;", "theme", "Lcom/onfido/android/sdk/capture/OnfidoTheme;", "token", "Lcom/onfido/api/client/token/Token;", "tokenExpirationHandlerEnabled", "workflowConfig", "Lcom/onfido/android/sdk/FlowConfig;", OperatingSystem.JsonKeys.BUILD, "Lcom/onfido/android/sdk/capture/OnfidoConfig;", "validateConfigEnterpriseFeatures", "", "requestedFeatures", "validateDocumentType", "steps", "([Lcom/onfido/android/sdk/capture/ui/options/FlowStep;)Z", "validateDocumentTypes", "validateFlowSteps", "([Lcom/onfido/android/sdk/capture/ui/options/FlowStep;)V", "validateGenericDocuments", "validateParams", "withAllowedDocumentTypes", "withAnalyticsEventListener", "eventListener", "Lcom/onfido/android/sdk/capture/analytics/OnfidoAnalyticsEventListener;", "withCertificatePinning", "([Ljava/lang/String;)Lcom/onfido/android/sdk/capture/OnfidoConfig$Builder;", "withCustomFlow", "([Lcom/onfido/android/sdk/capture/ui/options/FlowStep;)Lcom/onfido/android/sdk/capture/OnfidoConfig$Builder;", "withEnterpriseFeatures", "withGenericDocuments", "withLocale", "withManualLivenessCapture", "withMediaCallback", "Lcom/onfido/android/sdk/capture/config/MediaCallback;", "withNFC", "option", "withSDKToken", "tokenExpirationHandler", "Lcom/onfido/android/sdk/capture/token/TokenExpirationHandler;", "withTheme", "withWorkflowConfig", "isCobrandingLogoModeEnabled", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Builder {
        private String[] apiCertificatePinningPKHashes;
        private ResultReceiver biometricTokenCallback;
        private final Context context;
        private List<? extends DocumentType> documentTypes;
        private boolean exitWhenSentToBackground;
        private FlowStep[] flowStepsWithOptions;
        private List<GenericDocument> genericDocuments;
        private Locale locale;
        private boolean manualLivenessCapture;
        private ResultReceiver mediaCallback;
        private NFCOptions nfcOptions;
        private ResultReceiver onfidoAnalyticsEventListener;
        private EnterpriseFeatures requestedEnterpriseFeatures;
        private OnfidoTheme theme;
        private Token token;
        private boolean tokenExpirationHandlerEnabled;
        private FlowConfig workflowConfig;

        public final Builder withSDKToken(String token) {
            Intrinsics.checkNotNullParameter(token, "token");
            return withSDKToken$default(this, token, null, 2, null);
        }

        public Builder(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            this.context = context;
            this.documentTypes = CollectionsKt.emptyList();
            this.genericDocuments = CollectionsKt.emptyList();
            this.theme = OnfidoTheme.AUTOMATIC;
        }

        public final Builder withCustomFlow(FlowStep[] steps) {
            Intrinsics.checkNotNullParameter(steps, "steps");
            validateFlowSteps(steps);
            this.flowStepsWithOptions = steps;
            return this;
        }

        public final Builder withMediaCallback(MediaCallback mediaCallback) {
            Intrinsics.checkNotNullParameter(mediaCallback, "mediaCallback");
            this.mediaCallback = new MediaCallbackResultReceiver(mediaCallback);
            return this;
        }

        public final Builder withAnalyticsEventListener(OnfidoAnalyticsEventListener eventListener) {
            Intrinsics.checkNotNullParameter(eventListener, "eventListener");
            this.onfidoAnalyticsEventListener = new OnfidoAnalyticsEventResultReceiver(eventListener);
            return this;
        }

        public static /* synthetic */ Builder withSDKToken$default(Builder builder, String str, TokenExpirationHandler tokenExpirationHandler, int i, Object obj) {
            if ((i & 2) != 0) {
                tokenExpirationHandler = null;
            }
            return builder.withSDKToken(str, tokenExpirationHandler);
        }

        public final Builder withSDKToken(String token, TokenExpirationHandler tokenExpirationHandler) {
            Intrinsics.checkNotNullParameter(token, "token");
            Validator.validate$default(Validator.INSTANCE, this, new Pair[]{Validator.INSTANCE.orThrow(new Function0<Boolean>() { // from class: com.onfido.android.sdk.capture.OnfidoConfig$Builder$withSDKToken$1
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Boolean invoke() {
                    return Boolean.valueOf(this.this$0.token == null);
                }
            }, new MultipleTokenException())}, false, 2, null);
            this.token = new SDKToken(token, this.context.getPackageName());
            TokenExpirationHandlerService.INSTANCE.setTokenExpirationHandler(tokenExpirationHandler);
            this.tokenExpirationHandlerEnabled = tokenExpirationHandler != null;
            return this;
        }

        public final Builder withLocale(Locale locale) {
            Intrinsics.checkNotNullParameter(locale, "locale");
            this.locale = locale;
            return this;
        }

        public final Builder withCertificatePinning(String[] apiCertificatePinningPKHashes) {
            this.apiCertificatePinningPKHashes = apiCertificatePinningPKHashes;
            return this;
        }

        public final Builder exitWhenSentToBackground() {
            this.exitWhenSentToBackground = true;
            return this;
        }

        public final Builder withEnterpriseFeatures(EnterpriseFeatures requestedEnterpriseFeatures) {
            Intrinsics.checkNotNullParameter(requestedEnterpriseFeatures, "requestedEnterpriseFeatures");
            this.requestedEnterpriseFeatures = requestedEnterpriseFeatures;
            return this;
        }

        public final Builder withAllowedDocumentTypes(List<? extends DocumentType> documentTypes) {
            Intrinsics.checkNotNullParameter(documentTypes, "documentTypes");
            this.documentTypes = documentTypes;
            return this;
        }

        public final Builder withManualLivenessCapture(boolean manualLivenessCapture) {
            this.manualLivenessCapture = manualLivenessCapture;
            return this;
        }

        public final Builder withNFC(NFCOptions option) {
            Intrinsics.checkNotNullParameter(option, "option");
            this.nfcOptions = option;
            return this;
        }

        public final Builder withGenericDocuments(List<GenericDocument> genericDocuments) {
            Intrinsics.checkNotNullParameter(genericDocuments, "genericDocuments");
            this.genericDocuments = CollectionsKt.distinct(genericDocuments);
            return this;
        }

        public final Builder withWorkflowConfig(FlowConfig workflowConfig) {
            Intrinsics.checkNotNullParameter(workflowConfig, "workflowConfig");
            this.workflowConfig = workflowConfig;
            this.biometricTokenCallback = workflowConfig.getBiometricTokenCallback();
            this.mediaCallback = workflowConfig.getMediaCallback();
            this.onfidoAnalyticsEventListener = workflowConfig.getOnfidoAnalyticsEventListener();
            this.tokenExpirationHandlerEnabled = workflowConfig.getTokenExpirationHandlerEnabled();
            return this;
        }

        public final Builder withTheme(OnfidoTheme theme) {
            Intrinsics.checkNotNullParameter(theme, "theme");
            this.theme = theme;
            return this;
        }

        public final OnfidoConfig build() {
            validateParams();
            validateConfigEnterpriseFeatures(this.requestedEnterpriseFeatures);
            FlowStep[] flowStepArr = this.flowStepsWithOptions;
            Token token = this.token;
            if (token == null) {
                Intrinsics.throwUninitializedPropertyAccessException("token");
                token = null;
            }
            Token token2 = token;
            Locale locale = this.locale;
            boolean z = this.tokenExpirationHandlerEnabled;
            boolean z2 = this.exitWhenSentToBackground;
            String[] strArr = this.apiCertificatePinningPKHashes;
            boolean z3 = this.manualLivenessCapture;
            FlowConfig flowConfig = this.workflowConfig;
            List<? extends DocumentType> list = this.documentTypes;
            List<GenericDocument> list2 = this.genericDocuments;
            ResultReceiver resultReceiver = this.mediaCallback;
            ResultReceiver resultReceiver2 = this.biometricTokenCallback;
            ResultReceiver resultReceiver3 = this.onfidoAnalyticsEventListener;
            NFCOptions.Enabled.Optional optional = this.nfcOptions;
            if (optional == null) {
                optional = NFCOptions.Enabled.Optional.INSTANCE;
            }
            return new OnfidoConfig(flowStepArr, token2, locale, z, resultReceiver, resultReceiver2, resultReceiver3, z2, strArr, z3, flowConfig, optional, list, list2, this.requestedEnterpriseFeatures, this.theme, null, null, 196608, null);
        }

        private final void validateParams() {
            Validator.validate$default(Validator.INSTANCE, this, new Pair[]{Validator.INSTANCE.orThrow(new Function0<Boolean>() { // from class: com.onfido.android.sdk.capture.OnfidoConfig$Builder$validateParams$1
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Boolean invoke() {
                    return Boolean.valueOf(this.this$0.token != null);
                }
            }, new MissingTokenException())}, false, 2, null);
            validateDocumentTypes();
            validateGenericDocuments();
        }

        private final void validateDocumentTypes() {
            if (!this.documentTypes.isEmpty()) {
                List<? extends DocumentType> list = this.documentTypes;
                List list2 = OnfidoConfig.ALLOWED_DOCUMENT_TYPES;
                if ((list instanceof Collection) && list.isEmpty()) {
                    return;
                }
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    if (!list2.contains((DocumentType) it.next())) {
                        throw new IllegalArgumentException(("Document Types list should be non-empty subset of " + OnfidoConfig.ALLOWED_DOCUMENT_TYPES).toString());
                    }
                }
            }
        }

        private final void validateGenericDocuments() {
            if (!this.genericDocuments.isEmpty()) {
                List<GenericDocument> list = this.genericDocuments;
                if ((list instanceof Collection) && list.isEmpty()) {
                    return;
                }
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    if (((GenericDocument) it.next()).getDocumentTitle().length() <= 0) {
                        throw new IllegalArgumentException("The Generic documents list must not contain documents with empty titles".toString());
                    }
                }
            }
        }

        private final boolean isCobrandingLogoModeEnabled(EnterpriseFeatures enterpriseFeatures) {
            return (enterpriseFeatures.getCobrandingLogoLightMode() == null && enterpriseFeatures.getCobrandingLogoDarkMode() == null) ? false : true;
        }

        private final void validateConfigEnterpriseFeatures(EnterpriseFeatures requestedFeatures) {
            String cobrandingText;
            if (requestedFeatures == null && this.mediaCallback == null) {
                return;
            }
            Validator.validate$default(Validator.INSTANCE, this, new Pair[]{Validator.INSTANCE.orThrow(new Function0<Boolean>() { // from class: com.onfido.android.sdk.capture.OnfidoConfig$Builder$validateConfigEnterpriseFeatures$1
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                /* JADX WARN: Removed duplicated region for block: B:10:0x001c  */
                @Override // kotlin.jvm.functions.Function0
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Boolean invoke() {
                    /*
                        r1 = this;
                        com.onfido.android.sdk.capture.OnfidoConfig$Builder r0 = r1.this$0
                        com.onfido.api.client.token.Token r0 = com.onfido.android.sdk.capture.OnfidoConfig.Builder.access$getToken$p(r0)
                        if (r0 == 0) goto L1c
                        com.onfido.android.sdk.capture.OnfidoConfig$Builder r0 = r1.this$0
                        com.onfido.api.client.token.Token r0 = com.onfido.android.sdk.capture.OnfidoConfig.Builder.access$getToken$p(r0)
                        if (r0 != 0) goto L16
                        java.lang.String r0 = "token"
                        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
                        r0 = 0
                    L16:
                        boolean r0 = r0 instanceof com.onfido.api.client.token.sdk.SDKToken
                        if (r0 == 0) goto L1c
                        r0 = 1
                        goto L1d
                    L1c:
                        r0 = 0
                    L1d:
                        java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
                        return r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.OnfidoConfig$Builder$validateConfigEnterpriseFeatures$1.invoke():java.lang.Boolean");
                }
            }, new EnterpriseFeatureWithoutSDKTokenException())}, false, 2, null);
            Token token = this.token;
            if (token == null) {
                Intrinsics.throwUninitializedPropertyAccessException("token");
                token = null;
            }
            final TokenEnterpriseFeatures enterpriseFeatures$onfido_public_api_release = ((SDKToken) token).getEnterpriseFeatures$onfido_public_api_release();
            if (this.mediaCallback != null) {
                Validator.validate$default(Validator.INSTANCE, this, new Pair[]{Validator.INSTANCE.orThrow(new Function0<Boolean>() { // from class: com.onfido.android.sdk.capture.OnfidoConfig$Builder$validateConfigEnterpriseFeatures$2
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final Boolean invoke() {
                        return Boolean.valueOf(Intrinsics.areEqual((Object) enterpriseFeatures$onfido_public_api_release.getMediaCallbacksEnabled(), (Object) true));
                    }
                }, new EnterpriseFeatureNotEnabledException(TokenEnterpriseFeatures.MEDIA_CALLBACK))}, false, 2, null);
            }
            if (requestedFeatures == null) {
                return;
            }
            if (isCobrandingLogoModeEnabled(requestedFeatures) && (cobrandingText = requestedFeatures.getCobrandingText()) != null && cobrandingText.length() != 0) {
                throw new EnterpriseFeaturesConflictingException(TokenEnterpriseFeatures.LOGO_COBRAND, TokenEnterpriseFeatures.COBRAND);
            }
            if (requestedFeatures.getHideOnfidoLogo()) {
                Validator.validate$default(Validator.INSTANCE, this, new Pair[]{Validator.INSTANCE.orThrow(new Function0<Boolean>() { // from class: com.onfido.android.sdk.capture.OnfidoConfig$Builder$validateConfigEnterpriseFeatures$3
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final Boolean invoke() {
                        return Boolean.valueOf(Intrinsics.areEqual((Object) enterpriseFeatures$onfido_public_api_release.getHideOnfidoLogo(), (Object) true));
                    }
                }, new EnterpriseFeatureNotEnabledException(TokenEnterpriseFeatures.HIDE_ONFIDO_LOGO))}, false, 2, null);
            }
            if (requestedFeatures.getDisableMobileSdkAnalytics()) {
                Validator.validate$default(Validator.INSTANCE, this, new Pair[]{Validator.INSTANCE.orThrow(new Function0<Boolean>() { // from class: com.onfido.android.sdk.capture.OnfidoConfig$Builder$validateConfigEnterpriseFeatures$4
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final Boolean invoke() {
                        return Boolean.valueOf(Intrinsics.areEqual((Object) enterpriseFeatures$onfido_public_api_release.getDisableMobileSdkAnalytics(), (Object) true));
                    }
                }, new EnterpriseFeatureNotEnabledException(TokenEnterpriseFeatures.DISABLE_MOBILE_SDK_ANALYTICS))}, false, 2, null);
            }
            String cobrandingText2 = requestedFeatures.getCobrandingText();
            if (cobrandingText2 != null && cobrandingText2.length() != 0) {
                Validator.validate$default(Validator.INSTANCE, this, new Pair[]{Validator.INSTANCE.orThrow(new Function0<Boolean>() { // from class: com.onfido.android.sdk.capture.OnfidoConfig$Builder$validateConfigEnterpriseFeatures$5
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final Boolean invoke() {
                        return Boolean.valueOf(Intrinsics.areEqual((Object) enterpriseFeatures$onfido_public_api_release.getCobrand(), (Object) true));
                    }
                }, new EnterpriseFeatureNotEnabledException(TokenEnterpriseFeatures.COBRAND))}, false, 2, null);
            }
            if (isCobrandingLogoModeEnabled(requestedFeatures)) {
                if (requestedFeatures.getCobrandingLogoLightMode() == null || requestedFeatures.getCobrandingLogoDarkMode() == null) {
                    throw new EnterpriseFeaturesInvalidLogoCobrandingException();
                }
                Validator.validate$default(Validator.INSTANCE, this, new Pair[]{Validator.INSTANCE.orThrow(new Function0<Boolean>() { // from class: com.onfido.android.sdk.capture.OnfidoConfig$Builder$validateConfigEnterpriseFeatures$6
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final Boolean invoke() {
                        return Boolean.valueOf(Intrinsics.areEqual((Object) enterpriseFeatures$onfido_public_api_release.getLogoCobrand(), (Object) true));
                    }
                }, new EnterpriseFeatureNotEnabledException(TokenEnterpriseFeatures.LOGO_COBRAND))}, false, 2, null);
            }
        }

        private final void validateFlowSteps(FlowStep[] steps) {
            ArrayList arrayList = new ArrayList(steps.length);
            for (FlowStep flowStep : steps) {
                arrayList.add(flowStep.getAction());
            }
            ArrayList arrayList2 = arrayList;
            final boolean z = !FlowValidation.INSTANCE.containsDifferentFaceCaptureVariants(arrayList2);
            final boolean z2 = CollectionsKt.distinct(arrayList2).size() == steps.length;
            final boolean zValidateDocumentType = validateDocumentType(steps);
            Validator.validate$default(Validator.INSTANCE, this, new Pair[]{Validator.INSTANCE.orThrow(new Function0<Boolean>() { // from class: com.onfido.android.sdk.capture.OnfidoConfig$Builder$validateFlowSteps$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Boolean invoke() {
                    return Boolean.valueOf(z2);
                }
            }, new DuplicatedFlowStepException())}, false, 2, null);
            Validator.validate$default(Validator.INSTANCE, this, new Pair[]{Validator.INSTANCE.orThrow(new Function0<Boolean>() { // from class: com.onfido.android.sdk.capture.OnfidoConfig$Builder$validateFlowSteps$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Boolean invoke() {
                    return Boolean.valueOf(z);
                }
            }, new DuplicatedFaceCaptureVariantException())}, false, 2, null);
            Validator.validate$default(Validator.INSTANCE, this, new Pair[]{Validator.INSTANCE.orThrow(new Function0<Boolean>() { // from class: com.onfido.android.sdk.capture.OnfidoConfig$Builder$validateFlowSteps$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Boolean invoke() {
                    return Boolean.valueOf(zValidateDocumentType);
                }
            }, new InvalidDocumentTypeException())}, false, 2, null);
        }

        private final boolean validateDocumentType(FlowStep[] steps) {
            for (FlowStep flowStep : steps) {
                if (flowStep.getAction() == FlowAction.CAPTURE_DOCUMENT && (flowStep instanceof CaptureScreenStep)) {
                    BaseOptions options = flowStep.getOptions();
                    CaptureScreenOptions captureScreenOptions = options instanceof CaptureScreenOptions ? (CaptureScreenOptions) options : null;
                    if ((captureScreenOptions != null ? captureScreenOptions.getDocumentType() : null) == DocumentType.UNKNOWN) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    /* compiled from: OnfidoConfig.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0007R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/onfido/android/sdk/capture/OnfidoConfig$Companion;", "", "()V", "ALLOWED_DOCUMENT_TYPES", "", "Lcom/onfido/android/sdk/capture/DocumentType;", "builder", "Lcom/onfido/android/sdk/capture/OnfidoConfig$Builder;", "context", "Landroid/content/Context;", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Builder builder(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return new Builder(context);
        }
    }
}
