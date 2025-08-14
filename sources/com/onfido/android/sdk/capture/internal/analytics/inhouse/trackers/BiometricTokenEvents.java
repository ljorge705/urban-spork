package com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers;

import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.AnalyticsEvent;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.AnalyticsFlowStep;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.AnalyticsPropertyKeys;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.Event;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.EventNames;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.EventType;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\b\bÇ\u0002\u0018\u00002\u00020\u0001:\u0006\u0003\u0004\u0005\u0006\u0007\bB\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\t"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/BiometricTokenEvents;", "", "()V", "BiometricTokenRetrieval", "BiometricTokenRetrievalCompleted", "BiometricTokenRetrievalError", "BiometricTokenStorage", "BiometricTokenStorageCompleted", "BiometricTokenStorageError", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class BiometricTokenEvents {
    public static final BiometricTokenEvents INSTANCE = new BiometricTokenEvents();

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/BiometricTokenEvents$BiometricTokenRetrieval;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "storageType", "", "(Ljava/lang/String;)V", "getStorageType", "()Ljava/lang/String;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class BiometricTokenRetrieval extends AnalyticsEvent {
        private final String storageType;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public BiometricTokenRetrieval(String storageType) {
            super(new Event(EventNames.BioToBio.BIOMETRIC_TOKEN_RETRIEVAL, EventType.ACTION, null, null, 12, null), MapsKt.mapOf(TuplesKt.to(AnalyticsPropertyKeys.STEP, AnalyticsFlowStep.FACE), TuplesKt.to(AnalyticsPropertyKeys.BIOMETRIC_TOKEN_STORAGE_TYPE, storageType)), null, 4, null);
            Intrinsics.checkNotNullParameter(storageType, "storageType");
            this.storageType = storageType;
        }

        public static /* synthetic */ BiometricTokenRetrieval copy$default(BiometricTokenRetrieval biometricTokenRetrieval, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = biometricTokenRetrieval.storageType;
            }
            return biometricTokenRetrieval.copy(str);
        }

        /* renamed from: component1, reason: from getter */
        public final String getStorageType() {
            return this.storageType;
        }

        public final BiometricTokenRetrieval copy(String storageType) {
            Intrinsics.checkNotNullParameter(storageType, "storageType");
            return new BiometricTokenRetrieval(storageType);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof BiometricTokenRetrieval) && Intrinsics.areEqual(this.storageType, ((BiometricTokenRetrieval) other).storageType);
        }

        public final String getStorageType() {
            return this.storageType;
        }

        public int hashCode() {
            return this.storageType.hashCode();
        }

        public String toString() {
            return "BiometricTokenRetrieval(storageType=" + this.storageType + ')';
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/BiometricTokenEvents$BiometricTokenRetrievalCompleted;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "storageType", "", "(Ljava/lang/String;)V", "getStorageType", "()Ljava/lang/String;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class BiometricTokenRetrievalCompleted extends AnalyticsEvent {
        private final String storageType;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public BiometricTokenRetrievalCompleted(String storageType) {
            super(new Event(EventNames.BioToBio.BIOMETRIC_TOKEN_RETRIEVAL_COMPLETED, EventType.ACTION, null, null, 12, null), MapsKt.mapOf(TuplesKt.to(AnalyticsPropertyKeys.STEP, AnalyticsFlowStep.FACE), TuplesKt.to(AnalyticsPropertyKeys.BIOMETRIC_TOKEN_STORAGE_TYPE, storageType)), null, 4, null);
            Intrinsics.checkNotNullParameter(storageType, "storageType");
            this.storageType = storageType;
        }

        public static /* synthetic */ BiometricTokenRetrievalCompleted copy$default(BiometricTokenRetrievalCompleted biometricTokenRetrievalCompleted, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = biometricTokenRetrievalCompleted.storageType;
            }
            return biometricTokenRetrievalCompleted.copy(str);
        }

        /* renamed from: component1, reason: from getter */
        public final String getStorageType() {
            return this.storageType;
        }

        public final BiometricTokenRetrievalCompleted copy(String storageType) {
            Intrinsics.checkNotNullParameter(storageType, "storageType");
            return new BiometricTokenRetrievalCompleted(storageType);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof BiometricTokenRetrievalCompleted) && Intrinsics.areEqual(this.storageType, ((BiometricTokenRetrievalCompleted) other).storageType);
        }

        public final String getStorageType() {
            return this.storageType;
        }

        public int hashCode() {
            return this.storageType.hashCode();
        }

        public String toString() {
            return "BiometricTokenRetrievalCompleted(storageType=" + this.storageType + ')';
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u001f\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0013"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/BiometricTokenEvents$BiometricTokenRetrievalError;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "storageType", "", "errorMessage", "(Ljava/lang/String;Ljava/lang/String;)V", "getErrorMessage", "()Ljava/lang/String;", "getStorageType", "component1", "component2", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class BiometricTokenRetrievalError extends AnalyticsEvent {
        private final String errorMessage;
        private final String storageType;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public BiometricTokenRetrievalError(String storageType, String str) {
            super(new Event(EventNames.BioToBio.BIOMETRIC_TOKEN_RETRIEVAL_ERROR, EventType.ACTION, null, null, 12, null), MapsKt.mapOf(TuplesKt.to(AnalyticsPropertyKeys.STEP, AnalyticsFlowStep.FACE), TuplesKt.to(AnalyticsPropertyKeys.BIOMETRIC_TOKEN_STORAGE_TYPE, storageType), TuplesKt.to(AnalyticsPropertyKeys.ERROR_MESSAGE, str)), null, 4, null);
            Intrinsics.checkNotNullParameter(storageType, "storageType");
            this.storageType = storageType;
            this.errorMessage = str;
        }

        public static /* synthetic */ BiometricTokenRetrievalError copy$default(BiometricTokenRetrievalError biometricTokenRetrievalError, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = biometricTokenRetrievalError.storageType;
            }
            if ((i & 2) != 0) {
                str2 = biometricTokenRetrievalError.errorMessage;
            }
            return biometricTokenRetrievalError.copy(str, str2);
        }

        /* renamed from: component1, reason: from getter */
        public final String getStorageType() {
            return this.storageType;
        }

        /* renamed from: component2, reason: from getter */
        public final String getErrorMessage() {
            return this.errorMessage;
        }

        public final BiometricTokenRetrievalError copy(String storageType, String errorMessage) {
            Intrinsics.checkNotNullParameter(storageType, "storageType");
            return new BiometricTokenRetrievalError(storageType, errorMessage);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BiometricTokenRetrievalError)) {
                return false;
            }
            BiometricTokenRetrievalError biometricTokenRetrievalError = (BiometricTokenRetrievalError) other;
            return Intrinsics.areEqual(this.storageType, biometricTokenRetrievalError.storageType) && Intrinsics.areEqual(this.errorMessage, biometricTokenRetrievalError.errorMessage);
        }

        public final String getErrorMessage() {
            return this.errorMessage;
        }

        public final String getStorageType() {
            return this.storageType;
        }

        public int hashCode() {
            int iHashCode = this.storageType.hashCode() * 31;
            String str = this.errorMessage;
            return iHashCode + (str == null ? 0 : str.hashCode());
        }

        public String toString() {
            return "BiometricTokenRetrievalError(storageType=" + this.storageType + ", errorMessage=" + this.errorMessage + ')';
        }

        public /* synthetic */ BiometricTokenRetrievalError(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? null : str2);
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/BiometricTokenEvents$BiometricTokenStorage;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "storageType", "", "(Ljava/lang/String;)V", "getStorageType", "()Ljava/lang/String;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class BiometricTokenStorage extends AnalyticsEvent {
        private final String storageType;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public BiometricTokenStorage(String storageType) {
            super(new Event(EventNames.BioToBio.BIOMETRIC_TOKEN_STORAGE, EventType.ACTION, null, null, 12, null), MapsKt.mapOf(TuplesKt.to(AnalyticsPropertyKeys.STEP, AnalyticsFlowStep.FACE), TuplesKt.to(AnalyticsPropertyKeys.BIOMETRIC_TOKEN_STORAGE_TYPE, storageType)), null, 4, null);
            Intrinsics.checkNotNullParameter(storageType, "storageType");
            this.storageType = storageType;
        }

        public static /* synthetic */ BiometricTokenStorage copy$default(BiometricTokenStorage biometricTokenStorage, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = biometricTokenStorage.storageType;
            }
            return biometricTokenStorage.copy(str);
        }

        /* renamed from: component1, reason: from getter */
        public final String getStorageType() {
            return this.storageType;
        }

        public final BiometricTokenStorage copy(String storageType) {
            Intrinsics.checkNotNullParameter(storageType, "storageType");
            return new BiometricTokenStorage(storageType);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof BiometricTokenStorage) && Intrinsics.areEqual(this.storageType, ((BiometricTokenStorage) other).storageType);
        }

        public final String getStorageType() {
            return this.storageType;
        }

        public int hashCode() {
            return this.storageType.hashCode();
        }

        public String toString() {
            return "BiometricTokenStorage(storageType=" + this.storageType + ')';
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/BiometricTokenEvents$BiometricTokenStorageCompleted;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "storageType", "", "(Ljava/lang/String;)V", "getStorageType", "()Ljava/lang/String;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class BiometricTokenStorageCompleted extends AnalyticsEvent {
        private final String storageType;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public BiometricTokenStorageCompleted(String storageType) {
            super(new Event(EventNames.BioToBio.BIOMETRIC_TOKEN_STORAGE_COMPLETED, EventType.ACTION, null, null, 12, null), MapsKt.mapOf(TuplesKt.to(AnalyticsPropertyKeys.STEP, AnalyticsFlowStep.FACE), TuplesKt.to(AnalyticsPropertyKeys.BIOMETRIC_TOKEN_STORAGE_TYPE, storageType)), null, 4, null);
            Intrinsics.checkNotNullParameter(storageType, "storageType");
            this.storageType = storageType;
        }

        public static /* synthetic */ BiometricTokenStorageCompleted copy$default(BiometricTokenStorageCompleted biometricTokenStorageCompleted, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = biometricTokenStorageCompleted.storageType;
            }
            return biometricTokenStorageCompleted.copy(str);
        }

        /* renamed from: component1, reason: from getter */
        public final String getStorageType() {
            return this.storageType;
        }

        public final BiometricTokenStorageCompleted copy(String storageType) {
            Intrinsics.checkNotNullParameter(storageType, "storageType");
            return new BiometricTokenStorageCompleted(storageType);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof BiometricTokenStorageCompleted) && Intrinsics.areEqual(this.storageType, ((BiometricTokenStorageCompleted) other).storageType);
        }

        public final String getStorageType() {
            return this.storageType;
        }

        public int hashCode() {
            return this.storageType.hashCode();
        }

        public String toString() {
            return "BiometricTokenStorageCompleted(storageType=" + this.storageType + ')';
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u001f\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0013"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/BiometricTokenEvents$BiometricTokenStorageError;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "storageType", "", "errorMessage", "(Ljava/lang/String;Ljava/lang/String;)V", "getErrorMessage", "()Ljava/lang/String;", "getStorageType", "component1", "component2", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class BiometricTokenStorageError extends AnalyticsEvent {
        private final String errorMessage;
        private final String storageType;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public BiometricTokenStorageError(String storageType, String str) {
            super(new Event(EventNames.BioToBio.BIOMETRIC_TOKEN_STORAGE_ERROR, EventType.ACTION, null, null, 12, null), MapsKt.mapOf(TuplesKt.to(AnalyticsPropertyKeys.STEP, AnalyticsFlowStep.FACE), TuplesKt.to(AnalyticsPropertyKeys.BIOMETRIC_TOKEN_STORAGE_TYPE, storageType), TuplesKt.to(AnalyticsPropertyKeys.ERROR_MESSAGE, str)), null, 4, null);
            Intrinsics.checkNotNullParameter(storageType, "storageType");
            this.storageType = storageType;
            this.errorMessage = str;
        }

        public static /* synthetic */ BiometricTokenStorageError copy$default(BiometricTokenStorageError biometricTokenStorageError, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = biometricTokenStorageError.storageType;
            }
            if ((i & 2) != 0) {
                str2 = biometricTokenStorageError.errorMessage;
            }
            return biometricTokenStorageError.copy(str, str2);
        }

        /* renamed from: component1, reason: from getter */
        public final String getStorageType() {
            return this.storageType;
        }

        /* renamed from: component2, reason: from getter */
        public final String getErrorMessage() {
            return this.errorMessage;
        }

        public final BiometricTokenStorageError copy(String storageType, String errorMessage) {
            Intrinsics.checkNotNullParameter(storageType, "storageType");
            return new BiometricTokenStorageError(storageType, errorMessage);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BiometricTokenStorageError)) {
                return false;
            }
            BiometricTokenStorageError biometricTokenStorageError = (BiometricTokenStorageError) other;
            return Intrinsics.areEqual(this.storageType, biometricTokenStorageError.storageType) && Intrinsics.areEqual(this.errorMessage, biometricTokenStorageError.errorMessage);
        }

        public final String getErrorMessage() {
            return this.errorMessage;
        }

        public final String getStorageType() {
            return this.storageType;
        }

        public int hashCode() {
            int iHashCode = this.storageType.hashCode() * 31;
            String str = this.errorMessage;
            return iHashCode + (str == null ? 0 : str.hashCode());
        }

        public String toString() {
            return "BiometricTokenStorageError(storageType=" + this.storageType + ", errorMessage=" + this.errorMessage + ')';
        }

        public /* synthetic */ BiometricTokenStorageError(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? null : str2);
        }
    }

    private BiometricTokenEvents() {
    }
}
