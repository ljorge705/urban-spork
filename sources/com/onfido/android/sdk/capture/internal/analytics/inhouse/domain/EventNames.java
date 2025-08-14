package com.onfido.android.sdk.capture.internal.analytics.inhouse.domain;

import com.oblador.keychain.KeychainModule;
import com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\f\bÀ\u0002\u0018\u00002\u00020\u0001:\n\u0003\u0004\u0005\u0006\u0007\b\t\n\u000b\fB\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\r"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames;", "", "()V", "BioToBio", "Document", KeychainModule.FACE_SUPPORTED_NAME, "Flow", "Motion", "Nfc", "Permissions", "PublicNames", "Screen", "WaitingScreen", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class EventNames {
    public static final EventNames INSTANCE = new EventNames();

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$BioToBio;", "", "()V", BioToBio.BIOMETRIC_TOKEN_RETRIEVAL, "", BioToBio.BIOMETRIC_TOKEN_RETRIEVAL_COMPLETED, BioToBio.BIOMETRIC_TOKEN_RETRIEVAL_ERROR, BioToBio.BIOMETRIC_TOKEN_STORAGE, BioToBio.BIOMETRIC_TOKEN_STORAGE_COMPLETED, BioToBio.BIOMETRIC_TOKEN_STORAGE_ERROR, "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class BioToBio {
        public static final String BIOMETRIC_TOKEN_RETRIEVAL = "BIOMETRIC_TOKEN_RETRIEVAL";
        public static final String BIOMETRIC_TOKEN_RETRIEVAL_COMPLETED = "BIOMETRIC_TOKEN_RETRIEVAL_COMPLETED";
        public static final String BIOMETRIC_TOKEN_RETRIEVAL_ERROR = "BIOMETRIC_TOKEN_RETRIEVAL_ERROR";
        public static final String BIOMETRIC_TOKEN_STORAGE = "BIOMETRIC_TOKEN_STORAGE";
        public static final String BIOMETRIC_TOKEN_STORAGE_COMPLETED = "BIOMETRIC_TOKEN_STORAGE_COMPLETED";
        public static final String BIOMETRIC_TOKEN_STORAGE_ERROR = "BIOMETRIC_TOKEN_STORAGE_ERROR";
        public static final BioToBio INSTANCE = new BioToBio();

        private BioToBio() {
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Document;", "", "()V", "DOCUMENT_CAPTURE", "", Document.DOCUMENT_CAPTURE_ERROR, Document.DOCUMENT_CAPTURE_FLOW_COMPLETED, Document.DOCUMENT_CONFIRMATION, Document.DOCUMENT_CONFIRMATION_ERROR, Document.DOCUMENT_CONFIRMATION_WARNING, Document.DOCUMENT_UPLOAD_COMPLETED, Document.DOCUMENT_UPLOAD_STARTED, Document.DOCUMENT_VALID_MRZ_EXTRACTED, Document.ONFIDO_ML_MODEL_READY, "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Document {
        public static final String DOCUMENT_CAPTURE = "DOCUMENT_CAPTURE";
        public static final String DOCUMENT_CAPTURE_ERROR = "DOCUMENT_CAPTURE_ERROR";
        public static final String DOCUMENT_CAPTURE_FLOW_COMPLETED = "DOCUMENT_CAPTURE_FLOW_COMPLETED";
        public static final String DOCUMENT_CONFIRMATION = "DOCUMENT_CONFIRMATION";
        public static final String DOCUMENT_CONFIRMATION_ERROR = "DOCUMENT_CONFIRMATION_ERROR";
        public static final String DOCUMENT_CONFIRMATION_WARNING = "DOCUMENT_CONFIRMATION_WARNING";
        public static final String DOCUMENT_UPLOAD_COMPLETED = "DOCUMENT_UPLOAD_COMPLETED";
        public static final String DOCUMENT_UPLOAD_STARTED = "DOCUMENT_UPLOAD_STARTED";
        public static final String DOCUMENT_VALID_MRZ_EXTRACTED = "DOCUMENT_VALID_MRZ_EXTRACTED";
        public static final Document INSTANCE = new Document();
        public static final String ONFIDO_ML_MODEL_READY = "ONFIDO_ML_MODEL_READY";

        private Document() {
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b#\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Face;", "", "()V", Face.FACE_SELFIE_CAPTURE, "", Face.FACE_SELFIE_CAPTURE_BACK_BUTTON_CLICKED, Face.FACE_SELFIE_CAPTURE_CAPTURE_BUTTON_CLICKED, Face.FACE_SELFIE_CONFIRMATION, Face.FACE_SELFIE_CONFIRMATION_BACK_BUTTON_CLICKED, Face.FACE_SELFIE_CONFIRMATION_RETAKE_BUTTON_CLICKED, Face.FACE_SELFIE_CONFIRMATION_UPLOAD_BUTTON_CLICKED, Face.FACE_SELFIE_CONFIRMATION_UPLOAD_STATUS, "FACE_SELFIE_INTRO", Face.FACE_SELFIE_INTRO_BACK_BUTTON_CLICKED, Face.FACE_SELFIE_INTRO_TAKE_SELFIE_BUTTON_CLICKED, Face.FACE_SELFIE_UPLOAD_COMPLETED, Face.FACE_SELFIE_UPLOAD_STARTED, Face.FACE_VIDEO_CAPTURE, Face.FACE_VIDEO_CAPTURE_ALIGNED, Face.FACE_VIDEO_CAPTURE_BACK_BUTTON_CLICKED, "FACE_VIDEO_CAPTURE_CONFIRMATION_VIDEO_ERROR", Face.FACE_VIDEO_CAPTURE_ERROR, Face.FACE_VIDEO_CAPTURE_FINISH_BUTTON_CLICKED, Face.FACE_VIDEO_CAPTURE_FIRST_CHALLENGE, Face.FACE_VIDEO_CAPTURE_NEXT_BUTTON_CLICKED, Face.FACE_VIDEO_CAPTURE_RECORD_BUTTON_CLICKED, Face.FACE_VIDEO_CAPTURE_SECOND_CHALLENGE, Face.FACE_VIDEO_CAPTURE_TIMEOUT, Face.FACE_VIDEO_CAPTURE_TIMEOUT_RETRY_BUTTON_CLICKED, Face.FACE_VIDEO_CONFIRMATION, Face.FACE_VIDEO_CONFIRMATION_BACK_BUTTON_CLICKED, Face.FACE_VIDEO_CONFIRMATION_RETAKE_BUTTON_CLICKED, Face.FACE_VIDEO_CONFIRMATION_UPLOAD_BUTTON_CLICKED, Face.FACE_VIDEO_CONFIRMATION_UPLOAD_STATUS, Face.FACE_VIDEO_INTRO, Face.FACE_VIDEO_INTRO_BACK_BUTTON_CLICKED, Face.FACE_VIDEO_INTRO_RECORD_VIDEO_BUTTON_CLICKED, Face.FACE_VIDEO_UPLOAD_COMPLETED, Face.FACE_VIDEO_UPLOAD_STARTED, "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Face {
        public static final String FACE_SELFIE_CAPTURE = "FACE_SELFIE_CAPTURE";
        public static final String FACE_SELFIE_CAPTURE_BACK_BUTTON_CLICKED = "FACE_SELFIE_CAPTURE_BACK_BUTTON_CLICKED";
        public static final String FACE_SELFIE_CAPTURE_CAPTURE_BUTTON_CLICKED = "FACE_SELFIE_CAPTURE_CAPTURE_BUTTON_CLICKED";
        public static final String FACE_SELFIE_CONFIRMATION = "FACE_SELFIE_CONFIRMATION";
        public static final String FACE_SELFIE_CONFIRMATION_BACK_BUTTON_CLICKED = "FACE_SELFIE_CONFIRMATION_BACK_BUTTON_CLICKED";
        public static final String FACE_SELFIE_CONFIRMATION_RETAKE_BUTTON_CLICKED = "FACE_SELFIE_CONFIRMATION_RETAKE_BUTTON_CLICKED";
        public static final String FACE_SELFIE_CONFIRMATION_UPLOAD_BUTTON_CLICKED = "FACE_SELFIE_CONFIRMATION_UPLOAD_BUTTON_CLICKED";
        public static final String FACE_SELFIE_CONFIRMATION_UPLOAD_STATUS = "FACE_SELFIE_CONFIRMATION_UPLOAD_STATUS";
        public static final String FACE_SELFIE_INTRO = "FACE_INTRO";
        public static final String FACE_SELFIE_INTRO_BACK_BUTTON_CLICKED = "FACE_SELFIE_INTRO_BACK_BUTTON_CLICKED";
        public static final String FACE_SELFIE_INTRO_TAKE_SELFIE_BUTTON_CLICKED = "FACE_SELFIE_INTRO_TAKE_SELFIE_BUTTON_CLICKED";
        public static final String FACE_SELFIE_UPLOAD_COMPLETED = "FACE_SELFIE_UPLOAD_COMPLETED";
        public static final String FACE_SELFIE_UPLOAD_STARTED = "FACE_SELFIE_UPLOAD_STARTED";
        public static final String FACE_VIDEO_CAPTURE = "FACE_VIDEO_CAPTURE";
        public static final String FACE_VIDEO_CAPTURE_ALIGNED = "FACE_VIDEO_CAPTURE_ALIGNED";
        public static final String FACE_VIDEO_CAPTURE_BACK_BUTTON_CLICKED = "FACE_VIDEO_CAPTURE_BACK_BUTTON_CLICKED";
        public static final String FACE_VIDEO_CAPTURE_CONFIRMATION_VIDEO_ERROR = "VIDEO_FACIAL_CAPTURE_CONFIRMATION_VIDEO_ERROR";
        public static final String FACE_VIDEO_CAPTURE_ERROR = "FACE_VIDEO_CAPTURE_ERROR";
        public static final String FACE_VIDEO_CAPTURE_FINISH_BUTTON_CLICKED = "FACE_VIDEO_CAPTURE_FINISH_BUTTON_CLICKED";
        public static final String FACE_VIDEO_CAPTURE_FIRST_CHALLENGE = "FACE_VIDEO_CAPTURE_FIRST_CHALLENGE";
        public static final String FACE_VIDEO_CAPTURE_NEXT_BUTTON_CLICKED = "FACE_VIDEO_CAPTURE_NEXT_BUTTON_CLICKED";
        public static final String FACE_VIDEO_CAPTURE_RECORD_BUTTON_CLICKED = "FACE_VIDEO_CAPTURE_RECORD_BUTTON_CLICKED";
        public static final String FACE_VIDEO_CAPTURE_SECOND_CHALLENGE = "FACE_VIDEO_CAPTURE_SECOND_CHALLENGE";
        public static final String FACE_VIDEO_CAPTURE_TIMEOUT = "FACE_VIDEO_CAPTURE_TIMEOUT";
        public static final String FACE_VIDEO_CAPTURE_TIMEOUT_RETRY_BUTTON_CLICKED = "FACE_VIDEO_CAPTURE_TIMEOUT_RETRY_BUTTON_CLICKED";
        public static final String FACE_VIDEO_CONFIRMATION = "FACE_VIDEO_CONFIRMATION";
        public static final String FACE_VIDEO_CONFIRMATION_BACK_BUTTON_CLICKED = "FACE_VIDEO_CONFIRMATION_BACK_BUTTON_CLICKED";
        public static final String FACE_VIDEO_CONFIRMATION_RETAKE_BUTTON_CLICKED = "FACE_VIDEO_CONFIRMATION_RETAKE_BUTTON_CLICKED";
        public static final String FACE_VIDEO_CONFIRMATION_UPLOAD_BUTTON_CLICKED = "FACE_VIDEO_CONFIRMATION_UPLOAD_BUTTON_CLICKED";
        public static final String FACE_VIDEO_CONFIRMATION_UPLOAD_STATUS = "FACE_VIDEO_CONFIRMATION_UPLOAD_STATUS";
        public static final String FACE_VIDEO_INTRO = "FACE_VIDEO_INTRO";
        public static final String FACE_VIDEO_INTRO_BACK_BUTTON_CLICKED = "FACE_VIDEO_INTRO_BACK_BUTTON_CLICKED";
        public static final String FACE_VIDEO_INTRO_RECORD_VIDEO_BUTTON_CLICKED = "FACE_VIDEO_INTRO_RECORD_VIDEO_BUTTON_CLICKED";
        public static final String FACE_VIDEO_UPLOAD_COMPLETED = "FACE_VIDEO_UPLOAD_COMPLETED";
        public static final String FACE_VIDEO_UPLOAD_STARTED = "FACE_VIDEO_UPLOAD_STARTED";
        public static final Face INSTANCE = new Face();

        private Face() {
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Flow;", "", "()V", Flow.FLOW_COMPLETED, "", Flow.FLOW_EXITED, Flow.FLOW_STARTED, Flow.INTERRUPTED_FLOW_ERROR, Flow.LANGUAGE_DISPLAYED, Flow.UI_THEME_DISPLAYED, Flow.USER_EXIT_TAPPED_ALERT_CANCEL, Flow.USER_EXIT_TAPPED_ALERT_CONFIRM, Flow.USER_EXIT_TAPPED_EXIT_BUTTON, "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Flow {
        public static final String FLOW_COMPLETED = "FLOW_COMPLETED";
        public static final String FLOW_EXITED = "FLOW_EXITED";
        public static final String FLOW_STARTED = "FLOW_STARTED";
        public static final Flow INSTANCE = new Flow();
        public static final String INTERRUPTED_FLOW_ERROR = "INTERRUPTED_FLOW_ERROR";
        public static final String LANGUAGE_DISPLAYED = "LANGUAGE_DISPLAYED";
        public static final String UI_THEME_DISPLAYED = "UI_THEME_DISPLAYED";
        public static final String USER_EXIT_TAPPED_ALERT_CANCEL = "USER_EXIT_TAPPED_ALERT_CANCEL";
        public static final String USER_EXIT_TAPPED_ALERT_CONFIRM = "USER_EXIT_TAPPED_ALERT_CONFIRM";
        public static final String USER_EXIT_TAPPED_EXIT_BUTTON = "USER_EXIT_TAPPED_EXIT_BUTTON";

        private Flow() {
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0016\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Motion;", "", "()V", Motion.FACE_LIVENESS_ALIGNMENT, "", Motion.FACE_LIVENESS_ALIGNMENT_STATUS, Motion.FACE_LIVENESS_CAMERA, Motion.FACE_LIVENESS_CAMERA_ERROR, Motion.FACE_LIVENESS_CAPTURE, Motion.FACE_LIVENESS_CAPTURE_ERROR_TIMEOUT, Motion.FACE_LIVENESS_CAPTURE_ERROR_TIMEOUT_RESTART_CLICKED, Motion.FACE_LIVENESS_CAPTURE_ERROR_TOO_FAST, Motion.FACE_LIVENESS_CAPTURE_ERROR_TOO_FAST_RESTART_CLICKED, Motion.FACE_LIVENESS_CAPTURE_STATUS, Motion.FACE_LIVENESS_CONNECTION_ERROR, Motion.FACE_LIVENESS_CONNECTION_ERROR_RESTART_CLICKED, Motion.FACE_LIVENESS_CONNECTION_ERROR_RETRY_CLICKED, Motion.FACE_LIVENESS_FACE_DETECTOR, Motion.FACE_LIVENESS_INTRO, Motion.FACE_LIVENESS_INTRO_READY_CLICKED, Motion.FACE_LIVENESS_MLKIT_ERROR, Motion.FACE_LIVENESS_NO_FACE_DETECTED, Motion.FACE_LIVENESS_NO_FACE_DETECTED_RESTART_CLICKED, Motion.FACE_LIVENESS_PLAY_SERVICES_ERROR, Motion.FACE_LIVENESS_UPLOAD, Motion.FACE_LIVENESS_UPLOAD_COMPLETED, "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Motion {
        public static final String FACE_LIVENESS_ALIGNMENT = "FACE_LIVENESS_ALIGNMENT";
        public static final String FACE_LIVENESS_ALIGNMENT_STATUS = "FACE_LIVENESS_ALIGNMENT_STATUS";
        public static final String FACE_LIVENESS_CAMERA = "FACE_LIVENESS_CAMERA";
        public static final String FACE_LIVENESS_CAMERA_ERROR = "FACE_LIVENESS_CAMERA_ERROR";
        public static final String FACE_LIVENESS_CAPTURE = "FACE_LIVENESS_CAPTURE";
        public static final String FACE_LIVENESS_CAPTURE_ERROR_TIMEOUT = "FACE_LIVENESS_CAPTURE_ERROR_TIMEOUT";
        public static final String FACE_LIVENESS_CAPTURE_ERROR_TIMEOUT_RESTART_CLICKED = "FACE_LIVENESS_CAPTURE_ERROR_TIMEOUT_RESTART_CLICKED";
        public static final String FACE_LIVENESS_CAPTURE_ERROR_TOO_FAST = "FACE_LIVENESS_CAPTURE_ERROR_TOO_FAST";
        public static final String FACE_LIVENESS_CAPTURE_ERROR_TOO_FAST_RESTART_CLICKED = "FACE_LIVENESS_CAPTURE_ERROR_TOO_FAST_RESTART_CLICKED";
        public static final String FACE_LIVENESS_CAPTURE_STATUS = "FACE_LIVENESS_CAPTURE_STATUS";
        public static final String FACE_LIVENESS_CONNECTION_ERROR = "FACE_LIVENESS_CONNECTION_ERROR";
        public static final String FACE_LIVENESS_CONNECTION_ERROR_RESTART_CLICKED = "FACE_LIVENESS_CONNECTION_ERROR_RESTART_CLICKED";
        public static final String FACE_LIVENESS_CONNECTION_ERROR_RETRY_CLICKED = "FACE_LIVENESS_CONNECTION_ERROR_RETRY_CLICKED";
        public static final String FACE_LIVENESS_FACE_DETECTOR = "FACE_LIVENESS_FACE_DETECTOR";
        public static final String FACE_LIVENESS_INTRO = "FACE_LIVENESS_INTRO";
        public static final String FACE_LIVENESS_INTRO_READY_CLICKED = "FACE_LIVENESS_INTRO_READY_CLICKED";
        public static final String FACE_LIVENESS_MLKIT_ERROR = "FACE_LIVENESS_MLKIT_ERROR";
        public static final String FACE_LIVENESS_NO_FACE_DETECTED = "FACE_LIVENESS_NO_FACE_DETECTED";
        public static final String FACE_LIVENESS_NO_FACE_DETECTED_RESTART_CLICKED = "FACE_LIVENESS_NO_FACE_DETECTED_RESTART_CLICKED";
        public static final String FACE_LIVENESS_PLAY_SERVICES_ERROR = "FACE_LIVENESS_PLAY_SERVICES_ERROR";
        public static final String FACE_LIVENESS_UPLOAD = "FACE_LIVENESS_UPLOAD";
        public static final String FACE_LIVENESS_UPLOAD_COMPLETED = "FACE_LIVENESS_UPLOAD_COMPLETED";
        public static final Motion INSTANCE = new Motion();

        private Motion() {
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0014\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Nfc;", "", "()V", Nfc.DOCUMENT_NFC_SUPPORTED, "", Nfc.NFC_BAC_ERROR, Nfc.NFC_CAN_ENTRY_COMPLETED, Nfc.NFC_CHIP_AUTHENTICATED, Nfc.NFC_CHIP_CONNECTION_LOST, Nfc.NFC_CHIP_DISCOVERED, Nfc.NFC_CHOOSE_ANOTHER_DOCUMENT_CLICKED, Nfc.NFC_CONTINUE_AT_INITIAL_PROMPT, Nfc.NFC_EXIT_VERIFICATION_CLICKED, "NFC_FLOW_DATA_UPLOAD_COMPLETED", "NFC_FLOW_DATA_UPLOAD_STARTED", Nfc.NFC_NO_CAN_CLICKED, Nfc.NFC_OPEN_SETTINGS_CLICKED, Nfc.NFC_PACE_ERROR, Nfc.NFC_PROPERTIES_ERROR, Nfc.NFC_RETRY_SELECTED, Nfc.NFC_SKIP_AT_INITIAL_PROMPT, Nfc.NFC_SKIP_AT_MANUAL_CAN_ENTRY, Nfc.NFC_SKIP_AT_RETRY, Nfc.NFC_START_SCANNING_SELECTED, "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Nfc {
        public static final String DOCUMENT_NFC_SUPPORTED = "DOCUMENT_NFC_SUPPORTED";
        public static final Nfc INSTANCE = new Nfc();
        public static final String NFC_BAC_ERROR = "NFC_BAC_ERROR";
        public static final String NFC_CAN_ENTRY_COMPLETED = "NFC_CAN_ENTRY_COMPLETED";
        public static final String NFC_CHIP_AUTHENTICATED = "NFC_CHIP_AUTHENTICATED";
        public static final String NFC_CHIP_CONNECTION_LOST = "NFC_CHIP_CONNECTION_LOST";
        public static final String NFC_CHIP_DISCOVERED = "NFC_CHIP_DISCOVERED";
        public static final String NFC_CHOOSE_ANOTHER_DOCUMENT_CLICKED = "NFC_CHOOSE_ANOTHER_DOCUMENT_CLICKED";
        public static final String NFC_CONTINUE_AT_INITIAL_PROMPT = "NFC_CONTINUE_AT_INITIAL_PROMPT";
        public static final String NFC_EXIT_VERIFICATION_CLICKED = "NFC_EXIT_VERIFICATION_CLICKED";
        public static final String NFC_FLOW_DATA_UPLOAD_COMPLETED = "NFC_DATA_UPLOAD_COMPLETED";
        public static final String NFC_FLOW_DATA_UPLOAD_STARTED = "NFC_DATA_UPLOAD_STARTED";
        public static final String NFC_NO_CAN_CLICKED = "NFC_NO_CAN_CLICKED";
        public static final String NFC_OPEN_SETTINGS_CLICKED = "NFC_OPEN_SETTINGS_CLICKED";
        public static final String NFC_PACE_ERROR = "NFC_PACE_ERROR";
        public static final String NFC_PROPERTIES_ERROR = "NFC_PROPERTIES_ERROR";
        public static final String NFC_RETRY_SELECTED = "NFC_RETRY_SELECTED";
        public static final String NFC_SKIP_AT_INITIAL_PROMPT = "NFC_SKIP_AT_INITIAL_PROMPT";
        public static final String NFC_SKIP_AT_MANUAL_CAN_ENTRY = "NFC_SKIP_AT_MANUAL_CAN_ENTRY";
        public static final String NFC_SKIP_AT_RETRY = "NFC_SKIP_AT_RETRY";
        public static final String NFC_START_SCANNING_SELECTED = "NFC_START_SCANNING_SELECTED";

        private Nfc() {
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Permissions;", "", "()V", Permissions.PERMISSIONS_MANAGEMENT, "", Permissions.PERMISSION_DENIED, Permissions.PERMISSION_GRANTED, Permissions.PERMISSION_SETTINGS_CLICK, "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Permissions {
        public static final Permissions INSTANCE = new Permissions();
        public static final String PERMISSIONS_MANAGEMENT = "PERMISSIONS_MANAGEMENT";
        public static final String PERMISSION_DENIED = "PERMISSION_DENIED";
        public static final String PERMISSION_GRANTED = "PERMISSION_GRANTED";
        public static final String PERMISSION_SETTINGS_CLICK = "PERMISSION_SETTINGS_CLICK";

        private Permissions() {
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0006"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$PublicNames;", "", "()V", KeychainModule.FACE_SUPPORTED_NAME, "Flow", "Other", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class PublicNames {
        public static final PublicNames INSTANCE = new PublicNames();

        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0011\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$PublicNames$Face;", "", "()V", Face.FACE_SELFIE_INTRO, "", "FACE_MOTION_ALIGNMENT", "FACE_MOTION_CAPTURE", "FACE_MOTION_CAPTURE_ERROR_TIMEOUT", "FACE_MOTION_CAPTURE_ERROR_TOO_FAST", "FACE_MOTION_CONNECTION_ERROR", "FACE_MOTION_INTRO", "FACE_MOTION_NO_FACE_DETECTED", "FACE_MOTION_UPLOAD", "FACE_MOTION_UPLOAD_COMPLETED", Face.FACE_SELFIE_CAPTURE, Face.FACE_SELFIE_CONFIRMATION, Face.FACE_SELFIE_UPLOAD_STARTED, Face.FACE_VIDEO_CAPTURE, Face.FACE_VIDEO_CONFIRMATION, Face.FACE_VIDEO_INTRO, Face.FACE_VIDEO_UPLOAD_STARTED, "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Face {
            public static final String FACE_INTRO = "FACIAL_INTRO";
            public static final String FACE_MOTION_ALIGNMENT = "MOTION_FACIAL_ALIGNMENT";
            public static final String FACE_MOTION_CAPTURE = "MOTION_FACIAL_CAPTURE";
            public static final String FACE_MOTION_CAPTURE_ERROR_TIMEOUT = "MOTION_FACIAL_CAPTURE_ERROR_TIMEOUT";
            public static final String FACE_MOTION_CAPTURE_ERROR_TOO_FAST = "MOTION_FACIAL_CAPTURE_ERROR_TOO_FAST";
            public static final String FACE_MOTION_CONNECTION_ERROR = "MOTION_FACIAL_CONNECTION_ERROR";
            public static final String FACE_MOTION_INTRO = "MOTION_FACIAL_INTRO";
            public static final String FACE_MOTION_NO_FACE_DETECTED = "MOTION_FACIAL_NO_FACE_DETECTED";
            public static final String FACE_MOTION_UPLOAD = "MOTION_FACIAL_UPLOAD";
            public static final String FACE_MOTION_UPLOAD_COMPLETED = "MOTION_FACIAL_UPLOAD_COMPLETED";
            public static final String FACE_SELFIE_CAPTURE = "FACIAL_CAPTURE";
            public static final String FACE_SELFIE_CONFIRMATION = "FACIAL_CAPTURE_CONFIRMATION";
            public static final String FACE_SELFIE_UPLOAD_STARTED = "FACIAL_UPLOAD";
            public static final String FACE_VIDEO_CAPTURE = "VIDEO_FACIAL_CAPTURE";
            public static final String FACE_VIDEO_CONFIRMATION = "VIDEO_FACIAL_CAPTURE_CONFIRMATION";
            public static final String FACE_VIDEO_INTRO = "VIDEO_FACIAL_INTRO";
            public static final String FACE_VIDEO_UPLOAD_STARTED = "VIDEO_FACIAL_UPLOAD";
            public static final Face INSTANCE = new Face();

            private Face() {
            }
        }

        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$PublicNames$Flow;", "", "()V", Flow.USER_EXITED_FLOW, "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Flow {
            public static final Flow INSTANCE = new Flow();
            public static final String USER_EXITED_FLOW = "USER_EXITED_FLOW";

            private Flow() {
            }
        }

        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$PublicNames$Other;", "", "()V", Document.DOCUMENT_UPLOAD_STARTED, "", Other.USER_CONSENT, Other.WELCOME, "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Other {
            public static final String DOCUMENT_UPLOAD_STARTED = "DOCUMENT_UPLOAD";
            public static final Other INSTANCE = new Other();
            public static final String USER_CONSENT = "USER_CONSENT";
            public static final String WELCOME = "WELCOME";

            private Other() {
            }
        }

        private PublicNames() {
        }
    }

    @Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0015\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001bB\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0015\u001c\u001d\u001e\u001f !\"#$%&'()*+,-./0¨\u00061"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen;", "", "name", "", "(Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "CountrySelection", DocumentCaptureFragment.KEY_DOCUMENT_CAPTURE_FRAGMENT_TAG, "DocumentListFetchRetried", "DocumentTypeSelection", "Final", "NfcCanEntry", "NfcCanMaxAttemptsReached", "NfcDeviceNotSupported", "NfcDocumentNotSupported", "NfcError", "NfcIntro", "NfcRead", "NfcSettingsIntro", "PoaCountrySelection", "PoaDocumentDetails", "PoaDocumentSubmission", "PoaDocumentTypeSelection", "PoaVerifyAddress", "Unknown", "UserConsent", "Welcome", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen$CountrySelection;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen$DocumentCapture;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen$DocumentListFetchRetried;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen$DocumentTypeSelection;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen$Final;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen$NfcCanEntry;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen$NfcCanMaxAttemptsReached;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen$NfcDeviceNotSupported;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen$NfcDocumentNotSupported;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen$NfcError;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen$NfcIntro;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen$NfcRead;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen$NfcSettingsIntro;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen$PoaCountrySelection;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen$PoaDocumentDetails;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen$PoaDocumentSubmission;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen$PoaDocumentTypeSelection;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen$PoaVerifyAddress;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen$Unknown;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen$UserConsent;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen$Welcome;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static abstract class Screen {
        private final String name;

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen$CountrySelection;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class CountrySelection extends Screen {
            public static final CountrySelection INSTANCE = new CountrySelection();

            private CountrySelection() {
                super("COUNTRY_SELECTION", null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen$DocumentCapture;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class DocumentCapture extends Screen {
            public static final DocumentCapture INSTANCE = new DocumentCapture();

            private DocumentCapture() {
                super("DOCUMENT_CAPTURE", null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen$DocumentListFetchRetried;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class DocumentListFetchRetried extends Screen {
            public static final DocumentListFetchRetried INSTANCE = new DocumentListFetchRetried();

            private DocumentListFetchRetried() {
                super("API_SDK_DOCUMENT_FETCH_RETRY", null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen$DocumentTypeSelection;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class DocumentTypeSelection extends Screen {
            public static final DocumentTypeSelection INSTANCE = new DocumentTypeSelection();

            private DocumentTypeSelection() {
                super("DOCUMENT_TYPE_SELECTION", null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen$Final;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Final extends Screen {
            public static final Final INSTANCE = new Final();

            private Final() {
                super("COMPLETE", null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen$NfcCanEntry;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class NfcCanEntry extends Screen {
            public static final NfcCanEntry INSTANCE = new NfcCanEntry();

            private NfcCanEntry() {
                super("NFC_CAN_ENTRY", null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen$NfcCanMaxAttemptsReached;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class NfcCanMaxAttemptsReached extends Screen {
            public static final NfcCanMaxAttemptsReached INSTANCE = new NfcCanMaxAttemptsReached();

            private NfcCanMaxAttemptsReached() {
                super("NFC_CAN_MAX_ATTEMPTS_REACHED", null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen$NfcDeviceNotSupported;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class NfcDeviceNotSupported extends Screen {
            public static final NfcDeviceNotSupported INSTANCE = new NfcDeviceNotSupported();

            private NfcDeviceNotSupported() {
                super("NFC_DEVICE_NOT_SUPPORTED", null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen$NfcDocumentNotSupported;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class NfcDocumentNotSupported extends Screen {
            public static final NfcDocumentNotSupported INSTANCE = new NfcDocumentNotSupported();

            private NfcDocumentNotSupported() {
                super("NFC_DOCUMENT_NOT_SUPPORTED", null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen$NfcError;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class NfcError extends Screen {
            public static final NfcError INSTANCE = new NfcError();

            private NfcError() {
                super("NFC_READ_ERROR", null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen$NfcIntro;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class NfcIntro extends Screen {
            public static final NfcIntro INSTANCE = new NfcIntro();

            private NfcIntro() {
                super("NFC_INTRO", null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen$NfcRead;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class NfcRead extends Screen {
            public static final NfcRead INSTANCE = new NfcRead();

            private NfcRead() {
                super("NFC_READ", null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen$NfcSettingsIntro;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class NfcSettingsIntro extends Screen {
            public static final NfcSettingsIntro INSTANCE = new NfcSettingsIntro();

            private NfcSettingsIntro() {
                super("NFC_SETTINGS_INTRO", null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen$PoaCountrySelection;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class PoaCountrySelection extends Screen {
            public static final PoaCountrySelection INSTANCE = new PoaCountrySelection();

            private PoaCountrySelection() {
                super("POA_COUNTRY_SELECTION", null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen$PoaDocumentDetails;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class PoaDocumentDetails extends Screen {
            public static final PoaDocumentDetails INSTANCE = new PoaDocumentDetails();

            private PoaDocumentDetails() {
                super("POA_DOCUMENT_DETAILS", null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen$PoaDocumentSubmission;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class PoaDocumentSubmission extends Screen {
            public static final PoaDocumentSubmission INSTANCE = new PoaDocumentSubmission();

            private PoaDocumentSubmission() {
                super("POA_DOCUMENT_SUBMISSION", null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen$PoaDocumentTypeSelection;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class PoaDocumentTypeSelection extends Screen {
            public static final PoaDocumentTypeSelection INSTANCE = new PoaDocumentTypeSelection();

            private PoaDocumentTypeSelection() {
                super("POA_DOCUMENT_TYPE_SELECTION", null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen$PoaVerifyAddress;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class PoaVerifyAddress extends Screen {
            public static final PoaVerifyAddress INSTANCE = new PoaVerifyAddress();

            private PoaVerifyAddress() {
                super("POA_VERIFY_ADDRESS", null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen$Unknown;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Unknown extends Screen {
            public static final Unknown INSTANCE = new Unknown();

            private Unknown() {
                super("UNKNOWN", null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen$UserConsent;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class UserConsent extends Screen {
            public static final UserConsent INSTANCE = new UserConsent();

            private UserConsent() {
                super(PublicNames.Other.USER_CONSENT, null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen$Welcome;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$Screen;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Welcome extends Screen {
            public static final Welcome INSTANCE = new Welcome();

            private Welcome() {
                super(PublicNames.Other.WELCOME, null);
            }
        }

        private Screen(String str) {
            this.name = str;
        }

        public final String getName() {
            return this.name;
        }

        public /* synthetic */ Screen(String str, DefaultConstructorMarker defaultConstructorMarker) {
            this(str);
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventNames$WaitingScreen;", "", "()V", WaitingScreen.WAITING_SCREEN_COMPLETED, "", WaitingScreen.WAITING_SCREEN_ENDED, WaitingScreen.WAITING_SCREEN_STARTED, "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class WaitingScreen {
        public static final WaitingScreen INSTANCE = new WaitingScreen();
        public static final String WAITING_SCREEN_COMPLETED = "WAITING_SCREEN_COMPLETED";
        public static final String WAITING_SCREEN_ENDED = "WAITING_SCREEN_ENDED";
        public static final String WAITING_SCREEN_STARTED = "WAITING_SCREEN_STARTED";

        private WaitingScreen() {
        }
    }

    private EventNames() {
    }
}
