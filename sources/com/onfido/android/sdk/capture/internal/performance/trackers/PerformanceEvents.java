package com.onfido.android.sdk.capture.internal.performance.trackers;

import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.internal.performance.domain.PerformanceAnalyticsScreen;
import com.onfido.android.sdk.capture.internal.performance.domain.PerformanceEvent;
import com.onfido.android.sdk.capture.internal.performance.domain.PerformanceEventName;
import com.onfido.android.sdk.capture.internal.performance.domain.PerformancePropertyKey;
import com.onfido.api.client.data.DocSide;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\bÀ\u0002\u0018\u00002\u00020\u0001:\u0004\u0003\u0004\u0005\u0006B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/performance/trackers/PerformanceEvents;", "", "()V", "ScreenLoadCompleted", "ScreenLoadStarted", "TraceEnd", "TraceStart", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class PerformanceEvents {
    public static final PerformanceEvents INSTANCE = new PerformanceEvents();

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0080\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u0007\u001a\u00020\u0003HÂ\u0003J\t\u0010\b\u001a\u00020\u0005HÂ\u0003J\u001d\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\u0014\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000fH\u0016J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0011HÖ\u0001R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/performance/trackers/PerformanceEvents$ScreenLoadCompleted;", "Lcom/onfido/android/sdk/capture/internal/performance/domain/PerformanceEvent;", "timeInMillis", "", "destinationScreen", "Lcom/onfido/android/sdk/capture/internal/performance/domain/PerformanceAnalyticsScreen;", "(JLcom/onfido/android/sdk/capture/internal/performance/domain/PerformanceAnalyticsScreen;)V", "component1", "component2", Constants.COPY_TYPE, "equals", "", "other", "", "getProperties", "", "Lcom/onfido/android/sdk/capture/internal/performance/domain/PerformancePropertyKey;", "", "hashCode", "", "toString", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class ScreenLoadCompleted extends PerformanceEvent {
        private final PerformanceAnalyticsScreen destinationScreen;
        private final long timeInMillis;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ScreenLoadCompleted(long j, PerformanceAnalyticsScreen destinationScreen) {
            super(PerformanceEventName.SCREEN_LOAD, j);
            Intrinsics.checkNotNullParameter(destinationScreen, "destinationScreen");
            this.timeInMillis = j;
            this.destinationScreen = destinationScreen;
        }

        /* renamed from: component1, reason: from getter */
        private final long getTimeInMillis() {
            return this.timeInMillis;
        }

        /* renamed from: component2, reason: from getter */
        private final PerformanceAnalyticsScreen getDestinationScreen() {
            return this.destinationScreen;
        }

        public static /* synthetic */ ScreenLoadCompleted copy$default(ScreenLoadCompleted screenLoadCompleted, long j, PerformanceAnalyticsScreen performanceAnalyticsScreen, int i, Object obj) {
            if ((i & 1) != 0) {
                j = screenLoadCompleted.timeInMillis;
            }
            if ((i & 2) != 0) {
                performanceAnalyticsScreen = screenLoadCompleted.destinationScreen;
            }
            return screenLoadCompleted.copy(j, performanceAnalyticsScreen);
        }

        public final ScreenLoadCompleted copy(long timeInMillis, PerformanceAnalyticsScreen destinationScreen) {
            Intrinsics.checkNotNullParameter(destinationScreen, "destinationScreen");
            return new ScreenLoadCompleted(timeInMillis, destinationScreen);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ScreenLoadCompleted)) {
                return false;
            }
            ScreenLoadCompleted screenLoadCompleted = (ScreenLoadCompleted) other;
            return this.timeInMillis == screenLoadCompleted.timeInMillis && this.destinationScreen == screenLoadCompleted.destinationScreen;
        }

        @Override // com.onfido.android.sdk.capture.internal.performance.domain.PerformanceEvent
        public Map<PerformancePropertyKey, String> getProperties() {
            return MapsKt.mapOf(TuplesKt.to(PerformancePropertyKey.DESTINATION_SCREEN, this.destinationScreen.name()));
        }

        public int hashCode() {
            return (Long.hashCode(this.timeInMillis) * 31) + this.destinationScreen.hashCode();
        }

        public String toString() {
            return "ScreenLoadCompleted(timeInMillis=" + this.timeInMillis + ", destinationScreen=" + this.destinationScreen + ')';
        }
    }

    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0080\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bJ\t\u0010\f\u001a\u00020\u0003HÂ\u0003J\t\u0010\r\u001a\u00020\u0005HÂ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÂ\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\bHÂ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\nHÂ\u0003J?\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nHÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0003J\u0014\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00190\u0017H\u0016J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u0019HÖ\u0001R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/performance/trackers/PerformanceEvents$ScreenLoadStarted;", "Lcom/onfido/android/sdk/capture/internal/performance/domain/PerformanceEvent;", "timeInMillis", "", "originScreen", "Lcom/onfido/android/sdk/capture/internal/performance/domain/PerformanceAnalyticsScreen;", "destinationScreen", "documentType", "Lcom/onfido/android/sdk/capture/DocumentType;", "documentSide", "Lcom/onfido/api/client/data/DocSide;", "(JLcom/onfido/android/sdk/capture/internal/performance/domain/PerformanceAnalyticsScreen;Lcom/onfido/android/sdk/capture/internal/performance/domain/PerformanceAnalyticsScreen;Lcom/onfido/android/sdk/capture/DocumentType;Lcom/onfido/api/client/data/DocSide;)V", "component1", "component2", "component3", "component4", "component5", Constants.COPY_TYPE, "equals", "", "other", "", "getProperties", "", "Lcom/onfido/android/sdk/capture/internal/performance/domain/PerformancePropertyKey;", "", "hashCode", "", "toString", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class ScreenLoadStarted extends PerformanceEvent {
        private final PerformanceAnalyticsScreen destinationScreen;
        private final DocSide documentSide;
        private final DocumentType documentType;
        private final PerformanceAnalyticsScreen originScreen;
        private final long timeInMillis;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ScreenLoadStarted(long j, PerformanceAnalyticsScreen originScreen, PerformanceAnalyticsScreen destinationScreen, DocumentType documentType, DocSide docSide) {
            super(PerformanceEventName.SCREEN_LOAD, j);
            Intrinsics.checkNotNullParameter(originScreen, "originScreen");
            Intrinsics.checkNotNullParameter(destinationScreen, "destinationScreen");
            this.timeInMillis = j;
            this.originScreen = originScreen;
            this.destinationScreen = destinationScreen;
            this.documentType = documentType;
            this.documentSide = docSide;
        }

        /* renamed from: component1, reason: from getter */
        private final long getTimeInMillis() {
            return this.timeInMillis;
        }

        /* renamed from: component2, reason: from getter */
        private final PerformanceAnalyticsScreen getOriginScreen() {
            return this.originScreen;
        }

        /* renamed from: component3, reason: from getter */
        private final PerformanceAnalyticsScreen getDestinationScreen() {
            return this.destinationScreen;
        }

        /* renamed from: component4, reason: from getter */
        private final DocumentType getDocumentType() {
            return this.documentType;
        }

        /* renamed from: component5, reason: from getter */
        private final DocSide getDocumentSide() {
            return this.documentSide;
        }

        public static /* synthetic */ ScreenLoadStarted copy$default(ScreenLoadStarted screenLoadStarted, long j, PerformanceAnalyticsScreen performanceAnalyticsScreen, PerformanceAnalyticsScreen performanceAnalyticsScreen2, DocumentType documentType, DocSide docSide, int i, Object obj) {
            if ((i & 1) != 0) {
                j = screenLoadStarted.timeInMillis;
            }
            long j2 = j;
            if ((i & 2) != 0) {
                performanceAnalyticsScreen = screenLoadStarted.originScreen;
            }
            PerformanceAnalyticsScreen performanceAnalyticsScreen3 = performanceAnalyticsScreen;
            if ((i & 4) != 0) {
                performanceAnalyticsScreen2 = screenLoadStarted.destinationScreen;
            }
            PerformanceAnalyticsScreen performanceAnalyticsScreen4 = performanceAnalyticsScreen2;
            if ((i & 8) != 0) {
                documentType = screenLoadStarted.documentType;
            }
            DocumentType documentType2 = documentType;
            if ((i & 16) != 0) {
                docSide = screenLoadStarted.documentSide;
            }
            return screenLoadStarted.copy(j2, performanceAnalyticsScreen3, performanceAnalyticsScreen4, documentType2, docSide);
        }

        public final ScreenLoadStarted copy(long timeInMillis, PerformanceAnalyticsScreen originScreen, PerformanceAnalyticsScreen destinationScreen, DocumentType documentType, DocSide documentSide) {
            Intrinsics.checkNotNullParameter(originScreen, "originScreen");
            Intrinsics.checkNotNullParameter(destinationScreen, "destinationScreen");
            return new ScreenLoadStarted(timeInMillis, originScreen, destinationScreen, documentType, documentSide);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ScreenLoadStarted)) {
                return false;
            }
            ScreenLoadStarted screenLoadStarted = (ScreenLoadStarted) other;
            return this.timeInMillis == screenLoadStarted.timeInMillis && this.originScreen == screenLoadStarted.originScreen && this.destinationScreen == screenLoadStarted.destinationScreen && this.documentType == screenLoadStarted.documentType && this.documentSide == screenLoadStarted.documentSide;
        }

        @Override // com.onfido.android.sdk.capture.internal.performance.domain.PerformanceEvent
        public Map<PerformancePropertyKey, String> getProperties() {
            Map mapCreateMapBuilder = MapsKt.createMapBuilder();
            mapCreateMapBuilder.put(PerformancePropertyKey.ORIGIN_SCREEN, this.originScreen.name());
            mapCreateMapBuilder.put(PerformancePropertyKey.DESTINATION_SCREEN, this.destinationScreen.name());
            DocumentType documentType = this.documentType;
            if (documentType != null) {
            }
            DocSide docSide = this.documentSide;
            if (docSide != null) {
                mapCreateMapBuilder.put(PerformancePropertyKey.DOCUMENT_SIDE, docSide.name());
            }
            return MapsKt.build(mapCreateMapBuilder);
        }

        public int hashCode() {
            int iHashCode = ((((Long.hashCode(this.timeInMillis) * 31) + this.originScreen.hashCode()) * 31) + this.destinationScreen.hashCode()) * 31;
            DocumentType documentType = this.documentType;
            int iHashCode2 = (iHashCode + (documentType == null ? 0 : documentType.hashCode())) * 31;
            DocSide docSide = this.documentSide;
            return iHashCode2 + (docSide != null ? docSide.hashCode() : 0);
        }

        public String toString() {
            return "ScreenLoadStarted(timeInMillis=" + this.timeInMillis + ", originScreen=" + this.originScreen + ", destinationScreen=" + this.destinationScreen + ", documentType=" + this.documentType + ", documentSide=" + this.documentSide + ')';
        }

        public /* synthetic */ ScreenLoadStarted(long j, PerformanceAnalyticsScreen performanceAnalyticsScreen, PerformanceAnalyticsScreen performanceAnalyticsScreen2, DocumentType documentType, DocSide docSide, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(j, performanceAnalyticsScreen, performanceAnalyticsScreen2, (i & 8) != 0 ? null : documentType, (i & 16) != 0 ? null : docSide);
        }
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0080\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0005\u001a\u00020\u0003HÂ\u0003J\u0013\u0010\u0006\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nHÖ\u0003J\u0014\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\n0\fH\u0016J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/performance/trackers/PerformanceEvents$TraceEnd;", "Lcom/onfido/android/sdk/capture/internal/performance/domain/PerformanceEvent;", "traceName", "", "(Ljava/lang/String;)V", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "getProperties", "", "Lcom/onfido/android/sdk/capture/internal/performance/domain/PerformancePropertyKey;", "hashCode", "", "toString", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class TraceEnd extends PerformanceEvent {
        private final String traceName;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public TraceEnd(String traceName) {
            super(traceName, 0L);
            Intrinsics.checkNotNullParameter(traceName, "traceName");
            this.traceName = traceName;
        }

        /* renamed from: component1, reason: from getter */
        private final String getTraceName() {
            return this.traceName;
        }

        public static /* synthetic */ TraceEnd copy$default(TraceEnd traceEnd, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = traceEnd.traceName;
            }
            return traceEnd.copy(str);
        }

        public final TraceEnd copy(String traceName) {
            Intrinsics.checkNotNullParameter(traceName, "traceName");
            return new TraceEnd(traceName);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof TraceEnd) && Intrinsics.areEqual(this.traceName, ((TraceEnd) other).traceName);
        }

        @Override // com.onfido.android.sdk.capture.internal.performance.domain.PerformanceEvent
        public Map<PerformancePropertyKey, Object> getProperties() {
            return MapsKt.emptyMap();
        }

        public int hashCode() {
            return this.traceName.hashCode();
        }

        public String toString() {
            return "TraceEnd(traceName=" + this.traceName + ')';
        }
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0080\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0005\u001a\u00020\u0003HÂ\u0003J\u0013\u0010\u0006\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nHÖ\u0003J\u0014\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\n0\fH\u0016J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/performance/trackers/PerformanceEvents$TraceStart;", "Lcom/onfido/android/sdk/capture/internal/performance/domain/PerformanceEvent;", "traceName", "", "(Ljava/lang/String;)V", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "getProperties", "", "Lcom/onfido/android/sdk/capture/internal/performance/domain/PerformancePropertyKey;", "hashCode", "", "toString", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class TraceStart extends PerformanceEvent {
        private final String traceName;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public TraceStart(String traceName) {
            super(traceName, 0L);
            Intrinsics.checkNotNullParameter(traceName, "traceName");
            this.traceName = traceName;
        }

        /* renamed from: component1, reason: from getter */
        private final String getTraceName() {
            return this.traceName;
        }

        public static /* synthetic */ TraceStart copy$default(TraceStart traceStart, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = traceStart.traceName;
            }
            return traceStart.copy(str);
        }

        public final TraceStart copy(String traceName) {
            Intrinsics.checkNotNullParameter(traceName, "traceName");
            return new TraceStart(traceName);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof TraceStart) && Intrinsics.areEqual(this.traceName, ((TraceStart) other).traceName);
        }

        @Override // com.onfido.android.sdk.capture.internal.performance.domain.PerformanceEvent
        public Map<PerformancePropertyKey, Object> getProperties() {
            return MapsKt.emptyMap();
        }

        public int hashCode() {
            return this.traceName.hashCode();
        }

        public String toString() {
            return "TraceStart(traceName=" + this.traceName + ')';
        }
    }

    private PerformanceEvents() {
    }
}
