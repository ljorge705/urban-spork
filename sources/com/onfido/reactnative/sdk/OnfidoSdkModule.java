package com.onfido.reactnative.sdk;

import android.app.Activity;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.EnterpriseFeatures;
import com.onfido.android.sdk.capture.Onfido;
import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.android.sdk.capture.OnfidoFactory;
import com.onfido.android.sdk.capture.OnfidoTheme;
import com.onfido.android.sdk.capture.config.BiometricTokenCallback;
import com.onfido.android.sdk.capture.config.MediaCallback;
import com.onfido.android.sdk.capture.config.MediaResult;
import com.onfido.android.sdk.capture.errors.EnterpriseFeatureNotEnabledException;
import com.onfido.android.sdk.capture.errors.EnterpriseFeaturesInvalidLogoCobrandingException;
import com.onfido.android.sdk.capture.model.NFCOptions;
import com.onfido.android.sdk.capture.ui.camera.face.stepbuilder.FaceCaptureStepBuilder;
import com.onfido.android.sdk.capture.ui.camera.face.stepbuilder.MotionCaptureStepBuilder;
import com.onfido.android.sdk.capture.ui.camera.face.stepbuilder.PhotoCaptureStepBuilder;
import com.onfido.android.sdk.capture.ui.camera.face.stepbuilder.VideoCaptureStepBuilder;
import com.onfido.android.sdk.capture.ui.options.FlowStep;
import com.onfido.android.sdk.capture.ui.options.stepbuilder.DocumentCaptureStepBuilder;
import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.api.client.token.sdk.TokenEnterpriseFeatures;
import com.onfido.workflow.OnfidoWorkflow;
import com.onfido.workflow.WorkflowConfig;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class OnfidoSdkModule extends ReactContextBaseJavaModule {
    private final OnfidoSdkActivityEventListener activityEventListener;
    private BiometricTokenCallbackBridge biometricTokenCallback;
    List<CallbackType> callbackTypeList;
    final Onfido client;
    private Promise currentPromise;

    @ReactMethod
    public void addListener(String str) {
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "OnfidoSdk";
    }

    @ReactMethod
    public void removeListeners(int i) {
    }

    public OnfidoSdkModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.currentPromise = null;
        this.callbackTypeList = new ArrayList();
        Onfido client = OnfidoFactory.create(reactApplicationContext).getClient();
        this.client = client;
        OnfidoSdkActivityEventListener onfidoSdkActivityEventListener = new OnfidoSdkActivityEventListener(client);
        this.activityEventListener = onfidoSdkActivityEventListener;
        reactApplicationContext.addActivityEventListener(onfidoSdkActivityEventListener);
    }

    private void setPromise(Promise promise) {
        Promise promise2 = this.currentPromise;
        if (promise2 != null) {
            promise2.reject("error", new Exception("New activity was started before old promise was resolved."));
        }
        this.currentPromise = promise;
        this.activityEventListener.setCurrentPromise(promise);
    }

    protected Activity getCurrentActivityInParentClass() {
        return super.getCurrentActivity();
    }

    @ReactMethod
    public void start(ReadableMap readableMap, Promise promise) {
        setPromise(promise);
        try {
            try {
                String sdkTokenFromConfig = getSdkTokenFromConfig(readableMap);
                Activity currentActivityInParentClass = getCurrentActivityInParentClass();
                if (currentActivityInParentClass == null) {
                    this.currentPromise.reject("error", new Exception("Android activity does not exist"));
                    this.currentPromise = null;
                    return;
                }
                try {
                    try {
                        if (!getWorkflowRunIdFromConfig(readableMap).isEmpty()) {
                            workflowSDKConfiguration(readableMap, currentActivityInParentClass, sdkTokenFromConfig);
                        } else {
                            defaultSDKConfiguration(readableMap, currentActivityInParentClass, sdkTokenFromConfig);
                        }
                    } catch (Exception e) {
                        this.currentPromise.reject("error", new Exception(e.getMessage(), e));
                        this.currentPromise = null;
                    }
                } catch (EnterpriseFeatureNotEnabledException e2) {
                    this.currentPromise.reject("error", e2);
                    this.currentPromise = null;
                } catch (EnterpriseFeaturesInvalidLogoCobrandingException unused) {
                    this.currentPromise.reject("error", new EnterpriseFeaturesInvalidLogoCobrandingException());
                    this.currentPromise = null;
                }
            } catch (Exception e3) {
                this.currentPromise.reject("config_error", e3);
                this.currentPromise = null;
            }
        } catch (Exception e4) {
            e4.printStackTrace();
            this.currentPromise.reject("error", new Exception("Unexpected error starting Onfido page", e4));
            this.currentPromise = null;
        }
    }

    private void workflowSDKConfiguration(ReadableMap readableMap, Activity activity, String str) throws Exception {
        String workflowRunIdFromConfig = getWorkflowRunIdFromConfig(readableMap);
        OnfidoWorkflow onfidoWorkflowCreate = OnfidoWorkflow.create(activity);
        WorkflowConfig.Builder builder = new WorkflowConfig.Builder(str, workflowRunIdFromConfig);
        EnterpriseFeatures.Builder enterpriseFeatures = getEnterpriseFeatures(readableMap);
        if (enterpriseFeatures != null) {
            builder.withEnterpriseFeatures(enterpriseFeatures.build());
        }
        if (this.callbackTypeList.contains(CallbackType.MEDIA)) {
            builder.withMediaCallback(addMediaCallback());
        }
        if (this.callbackTypeList.contains(CallbackType.BIOMETRIC_TOKEN)) {
            builder.withBiometricTokenCallback(addBiometricTokenCallback());
        }
        OnfidoTheme themeFromConfig = getThemeFromConfig(readableMap);
        if (themeFromConfig != null) {
            builder.withTheme(themeFromConfig);
        }
        onfidoWorkflowCreate.startActivityForResult(activity, 102030, builder.build());
    }

    private void defaultSDKConfiguration(ReadableMap readableMap, Activity activity, String str) throws Exception {
        OnfidoConfig.Builder builderWithSDKToken = OnfidoConfig.builder(activity).withSDKToken(str);
        FlowStep[] flowStepsFromConfig = getFlowStepsFromConfig(readableMap, builderWithSDKToken);
        if (flowStepsFromConfig.length != 0) {
            builderWithSDKToken.withCustomFlow(flowStepsFromConfig);
        }
        EnterpriseFeatures.Builder enterpriseFeatures = getEnterpriseFeatures(readableMap);
        if (enterpriseFeatures != null) {
            builderWithSDKToken.withEnterpriseFeatures(enterpriseFeatures.build());
        }
        if (this.callbackTypeList.contains(CallbackType.MEDIA)) {
            builderWithSDKToken.withMediaCallback(addMediaCallback());
        }
        builderWithSDKToken.withNFC(getNFCOptionFromConfig(readableMap));
        OnfidoTheme themeFromConfig = getThemeFromConfig(readableMap);
        if (themeFromConfig != null) {
            builderWithSDKToken.withTheme(themeFromConfig);
        }
        this.client.startActivityForResult(activity, 102040, builderWithSDKToken.build());
    }

    private EnterpriseFeatures.Builder getEnterpriseFeatures(ReadableMap readableMap) {
        EnterpriseFeatures.Builder builder = EnterpriseFeatures.builder();
        Activity currentActivityInParentClass = getCurrentActivityInParentClass();
        boolean z = true;
        if (getBooleanFromConfig(readableMap, "hideLogo")) {
            builder.withHideOnfidoLogo(true);
        } else if (getBooleanFromConfig(readableMap, TokenEnterpriseFeatures.LOGO_COBRAND)) {
            int identifier = currentActivityInParentClass.getApplicationContext().getResources().getIdentifier("cobrand_logo_light", "drawable", currentActivityInParentClass.getApplicationContext().getPackageName());
            int identifier2 = currentActivityInParentClass.getApplicationContext().getResources().getIdentifier("cobrand_logo_dark", "drawable", currentActivityInParentClass.getApplicationContext().getPackageName());
            if (identifier == 0 || identifier2 == 0) {
                this.currentPromise.reject("error", new Exception("Cobrand logos were not found"));
                this.currentPromise = null;
                return null;
            }
            builder.withCobrandingLogo(identifier, identifier2);
        } else {
            z = false;
        }
        if (getBooleanFromConfig(readableMap, TokenEnterpriseFeatures.DISABLE_MOBILE_SDK_ANALYTICS)) {
            builder.disableMobileSdkAnalytics();
            return builder;
        }
        if (z) {
            return builder;
        }
        return null;
    }

    public static String getSdkTokenFromConfig(ReadableMap readableMap) {
        return readableMap.getString("sdkToken");
    }

    public static String getWorkflowRunIdFromConfig(ReadableMap readableMap) {
        return readableMap.hasKey("workflowRunId") ? readableMap.getString("workflowRunId") : "";
    }

    public static OnfidoTheme getThemeFromConfig(ReadableMap readableMap) throws Exception {
        String string = readableMap.getString("theme");
        if (string == null) {
            return null;
        }
        try {
            return OnfidoTheme.valueOf(string);
        } catch (Exception unused) {
            System.err.println("Unexpected theme value: [" + string + "]");
            throw new Exception("Unexpected theme value");
        }
    }

    public static NFCOptions getNFCOptionFromConfig(ReadableMap readableMap) {
        String string = readableMap.getString("nfcOption");
        if (string == null) {
            return NFCOptions.Enabled.Optional.INSTANCE;
        }
        string.hashCode();
        if (string.equals("REQUIRED")) {
            return NFCOptions.Enabled.Required.INSTANCE;
        }
        if (string.equals("DISABLED")) {
            return NFCOptions.Disabled.INSTANCE;
        }
        return NFCOptions.Enabled.Optional.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00b0 A[Catch: Exception -> 0x00cc, TryCatch #0 {Exception -> 0x00cc, blocks: (B:3:0x0008, B:5:0x0015, B:7:0x001b, B:9:0x0021, B:11:0x0027, B:13:0x002e, B:14:0x0033, B:16:0x003b, B:18:0x0040, B:19:0x0045, B:21:0x004b, B:24:0x0053, B:26:0x0059, B:46:0x0098, B:47:0x00a0, B:48:0x00a7, B:49:0x00a8, B:50:0x00b0, B:33:0x0073, B:36:0x007d, B:39:0x0087, B:51:0x00b8, B:52:0x00c3), top: B:57:0x0008 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.onfido.android.sdk.capture.ui.options.FlowStep[] getFlowStepsFromConfig(com.facebook.react.bridge.ReadableMap r6, com.onfido.android.sdk.capture.OnfidoConfig.Builder r7) throws java.lang.Exception {
        /*
            java.lang.String r0 = "type"
            java.lang.String r1 = "captureFace"
            java.lang.String r2 = "proofOfAddress"
            java.lang.String r3 = "welcome"
            java.lang.String r4 = "flowSteps"
            com.facebook.react.bridge.ReadableMap r6 = r6.getMap(r4)     // Catch: java.lang.Exception -> Lcc
            boolean r4 = r6.hasKey(r3)     // Catch: java.lang.Exception -> Lcc
            r5 = 0
            if (r4 == 0) goto L1a
            boolean r3 = r6.getBoolean(r3)     // Catch: java.lang.Exception -> Lcc
            goto L1b
        L1a:
            r3 = r5
        L1b:
            boolean r4 = r6.hasKey(r2)     // Catch: java.lang.Exception -> Lcc
            if (r4 == 0) goto L26
            boolean r2 = r6.getBoolean(r2)     // Catch: java.lang.Exception -> Lcc
            goto L27
        L26:
            r2 = r5
        L27:
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch: java.lang.Exception -> Lcc
            r4.<init>()     // Catch: java.lang.Exception -> Lcc
            if (r3 == 0) goto L33
            com.onfido.android.sdk.capture.ui.options.FlowStep r3 = com.onfido.android.sdk.capture.ui.options.FlowStep.WELCOME     // Catch: java.lang.Exception -> Lcc
            r4.add(r3)     // Catch: java.lang.Exception -> Lcc
        L33:
            java.lang.String r3 = "captureDocument"
            boolean r3 = r6.hasKey(r3)     // Catch: java.lang.Exception -> Lcc
            if (r3 == 0) goto L3e
            extractCaptureDocumentStep(r6, r4, r7)     // Catch: java.lang.Exception -> Lcc
        L3e:
            if (r2 == 0) goto L45
            com.onfido.android.sdk.capture.ui.options.FlowStep r7 = com.onfido.android.sdk.capture.ui.options.FlowStep.PROOF_OF_ADDRESS     // Catch: java.lang.Exception -> Lcc
            r4.add(r7)     // Catch: java.lang.Exception -> Lcc
        L45:
            boolean r7 = r6.hasKey(r1)     // Catch: java.lang.Exception -> Lcc
            if (r7 == 0) goto L50
            com.facebook.react.bridge.ReadableMap r6 = r6.getMap(r1)     // Catch: java.lang.Exception -> Lcc
            goto L51
        L50:
            r6 = 0
        L51:
            if (r6 == 0) goto Lc3
            boolean r7 = r6.hasKey(r0)     // Catch: java.lang.Exception -> Lcc
            if (r7 == 0) goto Lb8
            java.lang.String r7 = r6.getString(r0)     // Catch: java.lang.Exception -> Lcc
            int r0 = r7.hashCode()     // Catch: java.lang.Exception -> Lcc
            r1 = -2014989386(0xffffffff87e5b3b6, float:-3.4561715E-34)
            r2 = 2
            r3 = 1
            if (r0 == r1) goto L87
            r1 = 76105234(0x4894612, float:3.2272895E-36)
            if (r0 == r1) goto L7d
            r1 = 81665115(0x4de1c5b, float:5.221799E-36)
            if (r0 == r1) goto L73
            goto L91
        L73:
            java.lang.String r0 = "VIDEO"
            boolean r7 = r7.equals(r0)     // Catch: java.lang.Exception -> Lcc
            if (r7 == 0) goto L91
            r7 = r3
            goto L92
        L7d:
            java.lang.String r0 = "PHOTO"
            boolean r7 = r7.equals(r0)     // Catch: java.lang.Exception -> Lcc
            if (r7 == 0) goto L91
            r7 = r5
            goto L92
        L87:
            java.lang.String r0 = "MOTION"
            boolean r7 = r7.equals(r0)     // Catch: java.lang.Exception -> Lcc
            if (r7 == 0) goto L91
            r7 = r2
            goto L92
        L91:
            r7 = -1
        L92:
            if (r7 == 0) goto Lb0
            if (r7 == r3) goto La8
            if (r7 != r2) goto La0
            com.onfido.android.sdk.capture.ui.options.FlowStep r6 = faceStepFromMotionDefinition(r6)     // Catch: java.lang.Exception -> Lcc
            r4.add(r6)     // Catch: java.lang.Exception -> Lcc
            goto Lc3
        La0:
            java.lang.Exception r6 = new java.lang.Exception     // Catch: java.lang.Exception -> Lcc
            java.lang.String r7 = "Invalid face capture type. \"type\" must be VIDEO or PHOTO."
            r6.<init>(r7)     // Catch: java.lang.Exception -> Lcc
            throw r6     // Catch: java.lang.Exception -> Lcc
        La8:
            com.onfido.android.sdk.capture.ui.options.FlowStep r6 = faceStepFromVideoDefinition(r6)     // Catch: java.lang.Exception -> Lcc
            r4.add(r6)     // Catch: java.lang.Exception -> Lcc
            goto Lc3
        Lb0:
            com.onfido.android.sdk.capture.ui.options.FlowStep r6 = faceStepFromPhotoDefinition(r6)     // Catch: java.lang.Exception -> Lcc
            r4.add(r6)     // Catch: java.lang.Exception -> Lcc
            goto Lc3
        Lb8:
            com.onfido.android.sdk.capture.ui.camera.face.stepbuilder.PhotoCaptureStepBuilder r6 = com.onfido.android.sdk.capture.ui.camera.face.stepbuilder.FaceCaptureStepBuilder.forPhoto()     // Catch: java.lang.Exception -> Lcc
            com.onfido.android.sdk.capture.ui.options.FlowStep r6 = r6.build()     // Catch: java.lang.Exception -> Lcc
            r4.add(r6)     // Catch: java.lang.Exception -> Lcc
        Lc3:
            com.onfido.android.sdk.capture.ui.options.FlowStep[] r6 = new com.onfido.android.sdk.capture.ui.options.FlowStep[r5]     // Catch: java.lang.Exception -> Lcc
            java.lang.Object[] r6 = r4.toArray(r6)     // Catch: java.lang.Exception -> Lcc
            com.onfido.android.sdk.capture.ui.options.FlowStep[] r6 = (com.onfido.android.sdk.capture.ui.options.FlowStep[]) r6     // Catch: java.lang.Exception -> Lcc
            return r6
        Lcc:
            r6 = move-exception
            r6.printStackTrace()
            java.lang.Exception r7 = new java.lang.Exception
            java.lang.String r0 = "Error generating request"
            r7.<init>(r0, r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onfido.reactnative.sdk.OnfidoSdkModule.getFlowStepsFromConfig(com.facebook.react.bridge.ReadableMap, com.onfido.android.sdk.capture.OnfidoConfig$Builder):com.onfido.android.sdk.capture.ui.options.FlowStep[]");
    }

    private static void extractCaptureDocumentStep(ReadableMap readableMap, List<FlowStep> list, OnfidoConfig.Builder builder) throws Exception {
        ReadableMap map = readableMap.getMap("captureDocument");
        if (map == null) {
            return;
        }
        extractDocumentCaptureDetails(map, list, builder);
    }

    private static void extractDocumentCaptureDetails(ReadableMap readableMap, List<FlowStep> list, OnfidoConfig.Builder builder) throws Exception {
        boolean zHasKey = readableMap.hasKey("docType");
        boolean zHasKey2 = readableMap.hasKey("alpha2CountryCode");
        boolean zHasKey3 = readableMap.hasKey("allowedDocumentTypes");
        if (zHasKey3 && zHasKey2 && zHasKey) {
            throw new IllegalArgumentException("We can either filter the documents on DocumentSelection screen, or skip the selection and go directly to capture");
        }
        if (!zHasKey && !zHasKey2 && !zHasKey3) {
            list.add(FlowStep.CAPTURE_DOCUMENT);
            return;
        }
        if (zHasKey && zHasKey2) {
            extractDocTypeAndCountryForCaptureStep(readableMap, list);
        } else {
            if (zHasKey3) {
                extractAllowedDocumentTypes(readableMap, builder);
                list.add(FlowStep.CAPTURE_DOCUMENT);
                return;
            }
            throw new Exception("For countryCode and docType: both must be specified, or both must be omitted.");
        }
    }

    private static void extractAllowedDocumentTypes(ReadableMap readableMap, OnfidoConfig.Builder builder) {
        ReadableArray array = readableMap.getArray("allowedDocumentTypes");
        ArrayList arrayList = new ArrayList();
        if (array != null) {
            for (int i = 0; i < array.size(); i++) {
                arrayList.add(DocumentType.valueOf(array.getString(i)));
            }
        }
        builder.withAllowedDocumentTypes(arrayList);
    }

    private static void extractDocTypeAndCountryForCaptureStep(ReadableMap readableMap, List<FlowStep> list) throws Exception {
        String string = readableMap.getString("docType");
        try {
            DocumentType documentTypeValueOf = DocumentType.valueOf(string);
            String string2 = readableMap.getString("alpha2CountryCode");
            CountryCode countryCodeFindCountryCodeByAlpha2 = findCountryCodeByAlpha2(string2);
            if (countryCodeFindCountryCodeByAlpha2 == null) {
                System.err.println("Unexpected countryCode value: [" + string2 + "]");
                throw new Exception("Unexpected countryCode value.");
            }
            list.add(getFlowStep(documentTypeValueOf, countryCodeFindCountryCodeByAlpha2));
        } catch (IllegalArgumentException unused) {
            System.err.println("Unexpected docType value: [" + string + "]");
            throw new Exception("Unexpected docType value.");
        }
    }

    /* renamed from: com.onfido.reactnative.sdk.OnfidoSdkModule$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$onfido$android$sdk$capture$DocumentType;

        static {
            int[] iArr = new int[DocumentType.values().length];
            $SwitchMap$com$onfido$android$sdk$capture$DocumentType = iArr;
            try {
                iArr[DocumentType.PASSPORT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$onfido$android$sdk$capture$DocumentType[DocumentType.DRIVING_LICENCE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$onfido$android$sdk$capture$DocumentType[DocumentType.NATIONAL_IDENTITY_CARD.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$onfido$android$sdk$capture$DocumentType[DocumentType.RESIDENCE_PERMIT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$onfido$android$sdk$capture$DocumentType[DocumentType.VISA.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$onfido$android$sdk$capture$DocumentType[DocumentType.WORK_PERMIT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$onfido$android$sdk$capture$DocumentType[DocumentType.GENERIC.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$onfido$android$sdk$capture$DocumentType[DocumentType.UNKNOWN.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    private static FlowStep getFlowStep(DocumentType documentType, CountryCode countryCode) throws Exception {
        switch (AnonymousClass1.$SwitchMap$com$onfido$android$sdk$capture$DocumentType[documentType.ordinal()]) {
            case 1:
                return DocumentCaptureStepBuilder.forPassport().build();
            case 2:
                return DocumentCaptureStepBuilder.forDrivingLicence().withCountry(countryCode).build();
            case 3:
                return DocumentCaptureStepBuilder.forNationalIdentity().withCountry(countryCode).build();
            case 4:
                return DocumentCaptureStepBuilder.forResidencePermit().withCountry(countryCode).build();
            case 5:
                return DocumentCaptureStepBuilder.forVisa().withCountry(countryCode).build();
            case 6:
                return DocumentCaptureStepBuilder.forWorkPermit().withCountry(countryCode).build();
            case 7:
                return DocumentCaptureStepBuilder.forGenericDocument().withCountry(countryCode).build();
            case 8:
                throw new Exception("Unexpected docType value.");
            default:
                return null;
        }
    }

    private static PhotoCaptureStepBuilder faceStepFromPhotoDefinitionBuilder(ReadableMap readableMap) {
        PhotoCaptureStepBuilder photoCaptureStepBuilderForPhoto = FaceCaptureStepBuilder.forPhoto();
        if (readableMap.hasKey("showIntro")) {
            photoCaptureStepBuilderForPhoto.withIntro(readableMap.getBoolean("showIntro"));
        }
        return photoCaptureStepBuilderForPhoto;
    }

    private static FlowStep faceStepFromPhotoDefinition(ReadableMap readableMap) {
        return faceStepFromPhotoDefinitionBuilder(readableMap).build();
    }

    private static VideoCaptureStepBuilder faceStepFromVideoDefinitionBuilder(ReadableMap readableMap) {
        VideoCaptureStepBuilder videoCaptureStepBuilderForVideo = FaceCaptureStepBuilder.forVideo();
        if (readableMap.hasKey("showIntro")) {
            videoCaptureStepBuilderForVideo.withIntro(readableMap.getBoolean("showIntro"));
        }
        if (readableMap.hasKey("showConfirmation")) {
            videoCaptureStepBuilderForVideo.withConfirmationVideoPreview(readableMap.getBoolean("showConfirmation"));
        }
        return videoCaptureStepBuilderForVideo;
    }

    private static FlowStep faceStepFromVideoDefinition(ReadableMap readableMap) {
        return faceStepFromVideoDefinitionBuilder(readableMap).build();
    }

    private static FlowStep faceStepFromMotionDefinition(ReadableMap readableMap) {
        MotionCaptureStepBuilder motionCaptureStepBuilderForMotion = FaceCaptureStepBuilder.forMotion();
        if (readableMap.hasKey("recordAudio")) {
            motionCaptureStepBuilderForMotion.withAudio(readableMap.getBoolean("recordAudio"));
        }
        return motionCaptureStepBuilderForMotion.build();
    }

    public static CountryCode findCountryCodeByAlpha2(String str) {
        CountryCode countryCode = null;
        for (CountryCode countryCode2 : CountryCode.values()) {
            if (countryCode2.name().equals(str)) {
                countryCode = countryCode2;
            }
        }
        return countryCode;
    }

    private boolean getBooleanFromConfig(ReadableMap readableMap, String str) {
        return readableMap.hasKey(str) && readableMap.getBoolean(str);
    }

    private void sendEvent(String str, WritableMap writableMap) {
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(str, writableMap);
    }

    @ReactMethod
    public void withBiometricTokenCallback() {
        this.callbackTypeList.add(CallbackType.BIOMETRIC_TOKEN);
    }

    @ReactMethod
    public void provideBiometricToken(String str) {
        this.biometricTokenCallback.provideToken(str);
    }

    private BiometricTokenCallback addBiometricTokenCallback() {
        BiometricTokenCallbackBridge biometricTokenCallbackBridge = new BiometricTokenCallbackBridge(getReactApplicationContext());
        this.biometricTokenCallback = biometricTokenCallbackBridge;
        return biometricTokenCallbackBridge;
    }

    @ReactMethod
    public void withMediaCallbacksEnabled() {
        this.callbackTypeList.add(CallbackType.MEDIA);
    }

    private MediaCallback addMediaCallback() {
        return new MediaCallback() { // from class: com.onfido.reactnative.sdk.OnfidoSdkModule$$ExternalSyntheticLambda0
            @Override // com.onfido.android.sdk.capture.config.MediaCallback
            public final void onMediaCaptured(MediaResult mediaResult) {
                this.f$0.lambda$addMediaCallback$0(mediaResult);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$addMediaCallback$0(MediaResult mediaResult) {
        sendEvent("onfidoMediaCallback", ReactNativeBridgeUtiles.getMediaResultMap(mediaResult));
    }
}
