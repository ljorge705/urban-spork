package com.onfido.android.sdk.capture.internal.usecase;

import com.clevertap.android.sdk.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/usecase/AccessibleDocumentCaptureUseCaseResult;", "", "()V", "AutoCaptured", "DocumentPositionChanged", "Lcom/onfido/android/sdk/capture/internal/usecase/AccessibleDocumentCaptureUseCaseResult$AutoCaptured;", "Lcom/onfido/android/sdk/capture/internal/usecase/AccessibleDocumentCaptureUseCaseResult$DocumentPositionChanged;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public abstract class AccessibleDocumentCaptureUseCaseResult {

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/usecase/AccessibleDocumentCaptureUseCaseResult$AutoCaptured;", "Lcom/onfido/android/sdk/capture/internal/usecase/AccessibleDocumentCaptureUseCaseResult;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class AutoCaptured extends AccessibleDocumentCaptureUseCaseResult {
        public static final AutoCaptured INSTANCE = new AutoCaptured();

        private AutoCaptured() {
            super(null);
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0013"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/usecase/AccessibleDocumentCaptureUseCaseResult$DocumentPositionChanged;", "Lcom/onfido/android/sdk/capture/internal/usecase/AccessibleDocumentCaptureUseCaseResult;", "mainTextResId", "", "contentDescriptionResId", "(II)V", "getContentDescriptionResId", "()I", "getMainTextResId", "component1", "component2", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class DocumentPositionChanged extends AccessibleDocumentCaptureUseCaseResult {
        private final int contentDescriptionResId;
        private final int mainTextResId;

        public DocumentPositionChanged(int i, int i2) {
            super(null);
            this.mainTextResId = i;
            this.contentDescriptionResId = i2;
        }

        public static /* synthetic */ DocumentPositionChanged copy$default(DocumentPositionChanged documentPositionChanged, int i, int i2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i = documentPositionChanged.mainTextResId;
            }
            if ((i3 & 2) != 0) {
                i2 = documentPositionChanged.contentDescriptionResId;
            }
            return documentPositionChanged.copy(i, i2);
        }

        /* renamed from: component1, reason: from getter */
        public final int getMainTextResId() {
            return this.mainTextResId;
        }

        /* renamed from: component2, reason: from getter */
        public final int getContentDescriptionResId() {
            return this.contentDescriptionResId;
        }

        public final DocumentPositionChanged copy(int mainTextResId, int contentDescriptionResId) {
            return new DocumentPositionChanged(mainTextResId, contentDescriptionResId);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DocumentPositionChanged)) {
                return false;
            }
            DocumentPositionChanged documentPositionChanged = (DocumentPositionChanged) other;
            return this.mainTextResId == documentPositionChanged.mainTextResId && this.contentDescriptionResId == documentPositionChanged.contentDescriptionResId;
        }

        public final int getContentDescriptionResId() {
            return this.contentDescriptionResId;
        }

        public final int getMainTextResId() {
            return this.mainTextResId;
        }

        public int hashCode() {
            return (Integer.hashCode(this.mainTextResId) * 31) + Integer.hashCode(this.contentDescriptionResId);
        }

        public String toString() {
            return "DocumentPositionChanged(mainTextResId=" + this.mainTextResId + ", contentDescriptionResId=" + this.contentDescriptionResId + ')';
        }
    }

    private AccessibleDocumentCaptureUseCaseResult() {
    }

    public /* synthetic */ AccessibleDocumentCaptureUseCaseResult(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
