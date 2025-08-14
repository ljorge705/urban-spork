package com.onfido.android.sdk.capture.ui.camera.util;

import android.content.Intent;
import com.facebook.hermes.intl.Constants;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.android.sdk.capture.document.DocumentPages;
import com.onfido.android.sdk.capture.flow.CaptureStepDataBundle;
import com.onfido.android.sdk.capture.flow.NfcArguments;
import com.onfido.android.sdk.capture.ui.camera.CaptureActivity;
import com.onfido.android.sdk.capture.ui.nfc.model.NfcProperties;
import com.onfido.android.sdk.capture.upload.DocumentSide;
import com.onfido.api.client.data.DocSide;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000e\u001a\u00020\u000fJ\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u000e\u001a\u00020\u000fJ\u0018\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0018\u001a\u00020\u0017J\u0010\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0019\u0010 \u001a\u00020\u000f*\u00020\u000f2\u0006\u0010!\u001a\u00020\u0011H\u0000¢\u0006\u0002\b\"J\u0019\u0010#\u001a\u00020\u000f*\u00020\u000f2\u0006\u0010$\u001a\u00020\u001bH\u0000¢\u0006\u0002\b%J\u001b\u0010&\u001a\u00020\u000f*\u00020\u000f2\b\u0010'\u001a\u0004\u0018\u00010\u001dH\u0000¢\u0006\u0002\b(J\u0019\u0010)\u001a\u00020\u000f*\u00020\u000f2\u0006\u0010*\u001a\u00020\u001fH\u0000¢\u0006\u0002\b+R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006,"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/util/IntentHelper;", "", "()V", "CAPTURE_DATA_BUNDLE", "", "DOC_PAGES", "DOC_TYPE", "IS_FRONT_SIDE", "KEY_NFC_ARGUMENTS", "NFC_PROPERTIES", "NFC_SUPPORTED", "ONFIDO_CONFIG", "getDocTypeFrom", "Lcom/onfido/android/sdk/capture/DocumentType;", "intent", "Landroid/content/Intent;", "getDocumentDataBundle", "Lcom/onfido/android/sdk/capture/flow/CaptureStepDataBundle;", "getDocumentResultFrom", "Lcom/onfido/android/sdk/capture/upload/DocumentSide;", "getGenericDocumentPages", "Lcom/onfido/android/sdk/capture/document/DocumentPages;", "getIsDocumentFrontSide", "", Constants.COLLATION_DEFAULT, "getNFCSupported", "getNfcArguments", "Lcom/onfido/android/sdk/capture/flow/NfcArguments;", "getNfcProperties", "Lcom/onfido/android/sdk/capture/ui/nfc/model/NfcProperties;", "getOnfidoConfig", "Lcom/onfido/android/sdk/capture/OnfidoConfig;", "putCaptureStepDataBundle", "captureStepDataBundle", "putCaptureStepDataBundle$onfido_capture_sdk_core_release", "putNfcArguments", "nfcArguments", "putNfcArguments$onfido_capture_sdk_core_release", "putNfcProperties", "nfcProperties", "putNfcProperties$onfido_capture_sdk_core_release", "putOnfidoConfig", "onfidoConfig", "putOnfidoConfig$onfido_capture_sdk_core_release", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class IntentHelper {
    private static final String CAPTURE_DATA_BUNDLE = "capture_data_bundle";
    private static final String DOC_PAGES = "doc_pages";
    private static final String DOC_TYPE = "doc_type";
    public static final IntentHelper INSTANCE = new IntentHelper();
    private static final String IS_FRONT_SIDE = "is_front_side";
    private static final String KEY_NFC_ARGUMENTS = "nfc_arguments";
    private static final String NFC_PROPERTIES = "NFC_PROPERTIES";
    private static final String NFC_SUPPORTED = "nfc_supported";
    private static final String ONFIDO_CONFIG = "onfido_config";

    private IntentHelper() {
    }

    public static /* synthetic */ boolean getIsDocumentFrontSide$default(IntentHelper intentHelper, Intent intent, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        return intentHelper.getIsDocumentFrontSide(intent, z);
    }

    private final boolean getNFCSupported(Intent intent) {
        return intent.getBooleanExtra(NFC_SUPPORTED, false);
    }

    public final DocumentType getDocTypeFrom(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        Serializable serializableExtra = intent.getSerializableExtra("doc_type");
        if (serializableExtra instanceof DocumentType) {
            return (DocumentType) serializableExtra;
        }
        return null;
    }

    public final CaptureStepDataBundle getDocumentDataBundle(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        Serializable serializableExtra = intent.getSerializableExtra(CAPTURE_DATA_BUNDLE);
        Intrinsics.checkNotNull(serializableExtra, "null cannot be cast to non-null type com.onfido.android.sdk.capture.flow.CaptureStepDataBundle");
        return (CaptureStepDataBundle) serializableExtra;
    }

    public final DocumentSide getDocumentResultFrom(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        String uploadedFileId = CaptureActivity.INSTANCE.getUploadedFileId(intent);
        DocSide docSide = getIsDocumentFrontSide$default(this, intent, false, 2, null) ? DocSide.FRONT : DocSide.BACK;
        DocumentType docTypeFrom = getDocTypeFrom(intent);
        Intrinsics.checkNotNull(docTypeFrom);
        return new DocumentSide(uploadedFileId, docSide, docTypeFrom, getNFCSupported(intent));
    }

    public final DocumentPages getGenericDocumentPages(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        Serializable serializableExtra = intent.getSerializableExtra(DOC_PAGES);
        if (serializableExtra instanceof DocumentPages) {
            return (DocumentPages) serializableExtra;
        }
        return null;
    }

    public final boolean getIsDocumentFrontSide(Intent intent, boolean z) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        return intent.getBooleanExtra(IS_FRONT_SIDE, z);
    }

    public final NfcArguments getNfcArguments(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        return (NfcArguments) intent.getParcelableExtra(KEY_NFC_ARGUMENTS);
    }

    public final NfcProperties getNfcProperties(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        return (NfcProperties) intent.getParcelableExtra("NFC_PROPERTIES");
    }

    public final OnfidoConfig getOnfidoConfig(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        Object serializableExtra = intent.getSerializableExtra("onfido_config");
        Intrinsics.checkNotNull(serializableExtra, "null cannot be cast to non-null type com.onfido.android.sdk.capture.OnfidoConfig");
        return (OnfidoConfig) serializableExtra;
    }

    public final Intent putCaptureStepDataBundle$onfido_capture_sdk_core_release(Intent intent, CaptureStepDataBundle captureStepDataBundle) {
        Intrinsics.checkNotNullParameter(intent, "<this>");
        Intrinsics.checkNotNullParameter(captureStepDataBundle, "captureStepDataBundle");
        intent.putExtra(CAPTURE_DATA_BUNDLE, captureStepDataBundle);
        return intent;
    }

    public final Intent putNfcArguments$onfido_capture_sdk_core_release(Intent intent, NfcArguments nfcArguments) {
        Intrinsics.checkNotNullParameter(intent, "<this>");
        Intrinsics.checkNotNullParameter(nfcArguments, "nfcArguments");
        intent.putExtra(KEY_NFC_ARGUMENTS, nfcArguments);
        return intent;
    }

    public final Intent putNfcProperties$onfido_capture_sdk_core_release(Intent intent, NfcProperties nfcProperties) {
        Intrinsics.checkNotNullParameter(intent, "<this>");
        if (nfcProperties != null) {
            intent.putExtra("NFC_PROPERTIES", nfcProperties);
        }
        return intent;
    }

    public final Intent putOnfidoConfig$onfido_capture_sdk_core_release(Intent intent, OnfidoConfig onfidoConfig) {
        Intrinsics.checkNotNullParameter(intent, "<this>");
        Intrinsics.checkNotNullParameter(onfidoConfig, "onfidoConfig");
        intent.putExtra("onfido_config", onfidoConfig);
        return intent;
    }
}
