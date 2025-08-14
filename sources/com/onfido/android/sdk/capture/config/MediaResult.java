package com.onfido.android.sdk.capture.config;

import com.onfido.reactnative.sdk.ReactNativeBridgeUtiles;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MediaCallback.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0003\u0002\u0003\u0004\u0082\u0001\u0003\u0005\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/onfido/android/sdk/capture/config/MediaResult;", "", "DocumentResult", "LivenessResult", "SelfieResult", "Lcom/onfido/android/sdk/capture/config/MediaResult$DocumentResult;", "Lcom/onfido/android/sdk/capture/config/MediaResult$LivenessResult;", "Lcom/onfido/android/sdk/capture/config/MediaResult$SelfieResult;", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface MediaResult {

    /* compiled from: MediaCallback.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0096\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0011"}, d2 = {"Lcom/onfido/android/sdk/capture/config/MediaResult$DocumentResult;", "Lcom/onfido/android/sdk/capture/config/MediaResult;", ReactNativeBridgeUtiles.KEY_FILE_DATA, "Lcom/onfido/android/sdk/capture/config/MediaFile;", "documentMetadata", "Lcom/onfido/android/sdk/capture/config/DocumentMetadata;", "(Lcom/onfido/android/sdk/capture/config/MediaFile;Lcom/onfido/android/sdk/capture/config/DocumentMetadata;)V", "getDocumentMetadata", "()Lcom/onfido/android/sdk/capture/config/DocumentMetadata;", "getFileData", "()Lcom/onfido/android/sdk/capture/config/MediaFile;", "equals", "", "other", "", "hashCode", "", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class DocumentResult implements MediaResult {
        private final DocumentMetadata documentMetadata;
        private final MediaFile fileData;

        public final DocumentMetadata getDocumentMetadata() {
            return this.documentMetadata;
        }

        public final MediaFile getFileData() {
            return this.fileData;
        }

        public DocumentResult(MediaFile fileData, DocumentMetadata documentMetadata) {
            Intrinsics.checkNotNullParameter(fileData, "fileData");
            Intrinsics.checkNotNullParameter(documentMetadata, "documentMetadata");
            this.fileData = fileData;
            this.documentMetadata = documentMetadata;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null)) {
                return false;
            }
            Intrinsics.checkNotNull(other, "null cannot be cast to non-null type com.onfido.android.sdk.capture.config.MediaResult.DocumentResult");
            DocumentResult documentResult = (DocumentResult) other;
            return Intrinsics.areEqual(this.fileData, documentResult.fileData) && Intrinsics.areEqual(this.documentMetadata, documentResult.documentMetadata);
        }

        public int hashCode() {
            return (this.fileData.hashCode() * 31) + this.documentMetadata.hashCode();
        }
    }

    /* compiled from: MediaCallback.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0013\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0096\u0002J\b\u0010\u000b\u001a\u00020\fH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\r"}, d2 = {"Lcom/onfido/android/sdk/capture/config/MediaResult$SelfieResult;", "Lcom/onfido/android/sdk/capture/config/MediaResult;", ReactNativeBridgeUtiles.KEY_FILE_DATA, "Lcom/onfido/android/sdk/capture/config/MediaFile;", "(Lcom/onfido/android/sdk/capture/config/MediaFile;)V", "getFileData", "()Lcom/onfido/android/sdk/capture/config/MediaFile;", "equals", "", "other", "", "hashCode", "", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class SelfieResult implements MediaResult {
        private final MediaFile fileData;

        public final MediaFile getFileData() {
            return this.fileData;
        }

        public SelfieResult(MediaFile fileData) {
            Intrinsics.checkNotNullParameter(fileData, "fileData");
            this.fileData = fileData;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null)) {
                return false;
            }
            Intrinsics.checkNotNull(other, "null cannot be cast to non-null type com.onfido.android.sdk.capture.config.MediaResult.SelfieResult");
            return Intrinsics.areEqual(this.fileData, ((SelfieResult) other).fileData);
        }

        public int hashCode() {
            return this.fileData.hashCode();
        }
    }

    /* compiled from: MediaCallback.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0013\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0096\u0002J\b\u0010\u000b\u001a\u00020\fH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\r"}, d2 = {"Lcom/onfido/android/sdk/capture/config/MediaResult$LivenessResult;", "Lcom/onfido/android/sdk/capture/config/MediaResult;", ReactNativeBridgeUtiles.KEY_FILE_DATA, "Lcom/onfido/android/sdk/capture/config/MediaFile;", "(Lcom/onfido/android/sdk/capture/config/MediaFile;)V", "getFileData", "()Lcom/onfido/android/sdk/capture/config/MediaFile;", "equals", "", "other", "", "hashCode", "", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class LivenessResult implements MediaResult {
        private final MediaFile fileData;

        public final MediaFile getFileData() {
            return this.fileData;
        }

        public LivenessResult(MediaFile fileData) {
            Intrinsics.checkNotNullParameter(fileData, "fileData");
            this.fileData = fileData;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null)) {
                return false;
            }
            Intrinsics.checkNotNull(other, "null cannot be cast to non-null type com.onfido.android.sdk.capture.config.MediaResult.LivenessResult");
            return Intrinsics.areEqual(this.fileData, ((LivenessResult) other).fileData);
        }

        public int hashCode() {
            return this.fileData.hashCode();
        }
    }
}
