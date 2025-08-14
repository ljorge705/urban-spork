package com.onfido.api.client;

import com.onfido.android.sdk.capture.ui.camera.IQSUploadErrorParser;

/* loaded from: classes6.dex */
public enum ValidationType {
    DOCUMENT("detect_document"),
    CUTOFF(IQSUploadErrorParser.CUTOFF_DETECTION_ERROR_KEY),
    GLARE(IQSUploadErrorParser.GLARE_DETECTION_ERROR_KEY),
    BLUR(IQSUploadErrorParser.BLUR_DETECTION_ERROR_KEY);

    private final String id;

    public String getId() {
        return this.id;
    }

    ValidationType(String str) {
        this.id = str;
    }

    public static ValidationType fromId(String str) {
        for (ValidationType validationType : values()) {
            if (validationType.id.equalsIgnoreCase(str)) {
                return validationType;
            }
        }
        return null;
    }
}
