package com.onfido.android.sdk.capture.internal.nfc;

import android.nfc.Tag;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.AnalyticsPropertyKeys;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.NfcFlowType;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.NfcTracker;
import com.onfido.android.sdk.capture.internal.nfc.PassportNfcExtractionState;
import com.onfido.android.sdk.capture.internal.nfc.data.PassportBACKey;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.android.sdk.capture.nfc.MRTDDataGroup;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.security.PublicKey;
import java.security.spec.AlgorithmParameterSpec;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.io.ByteStreamsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.sf.scuba.smartcards.CardFileInputStream;
import net.sf.scuba.smartcards.CardServiceException;
import org.jmrtd.PACEKeySpec;
import org.jmrtd.PassportService;
import org.jmrtd.lds.CardAccessFile;
import org.jmrtd.lds.PACEInfo;
import org.jmrtd.lds.SecurityInfo;
import org.jmrtd.protocol.AAResult;

@Metadata(d1 = {"\u0000¦\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0004\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u001e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 >2\u00020\u0001:\u0003>?@B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J*\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0002J*\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0002J&\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u001f\u001a\u0004\u0018\u00010 2\b\u0010!\u001a\u0004\u0018\u00010\u001eH\u0002J\u0018\u0010\"\u001a\u00020\u001a2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\"\u0010#\u001a\u00020\u001a2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0002J\u001c\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00120\b2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00120&H\u0002J[\u0010'\u001a\u0004\u0018\u00010(2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010)\u001a\u00020*2\b\u0010!\u001a\u0004\u0018\u00010\u001e2\u0006\u0010+\u001a\u00020\u00102\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\f0-2\f\u0010.\u001a\b\u0012\u0004\u0012\u0002000/2\n\b\u0002\u00101\u001a\u0004\u0018\u00010\u000eH\u0000¢\u0006\u0004\b2\u00103Ju\u00104\u001a\b\u0012\u0004\u0012\u000200052\f\u00106\u001a\b\u0012\u0004\u0012\u000207052\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010!\u001a\u0004\u0018\u00010\u001e2\u0006\u00108\u001a\u0002072\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\f0-2\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u00109\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010+\u001a\u00020\u00102\b\u00101\u001a\u0004\u0018\u00010\u000eH\u0016¢\u0006\u0002\u0010:J\u001a\u0010;\u001a\u00020<*\b\u0012\u0004\u0012\u0002000/2\u0006\u0010=\u001a\u00020\tH\u0002R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\t0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006A"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/nfc/JMRTDPassportNfcReader;", "Lcom/onfido/android/sdk/capture/internal/nfc/PassportNfcReader;", "nfcScanTagTimeout", "", "nfcTracker", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/NfcTracker;", "(ILcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/NfcTracker;)V", "allReadingSteps", "", "Lcom/onfido/android/sdk/capture/internal/nfc/JMRTDPassportNfcReader$ReadingSteps;", "nfcFileIDToReadingStep", "", "Lcom/onfido/android/sdk/capture/nfc/MRTDDataGroup;", "nfcRealtimeEvents", "Lcom/onfido/android/sdk/capture/internal/nfc/RealtimeNfcEvents;", "attemptPace", "", "securityInfo", "Lorg/jmrtd/lds/SecurityInfo;", "passportService", "Lorg/jmrtd/PassportService;", "passportBACKey", "Lcom/onfido/android/sdk/capture/internal/nfc/data/PassportBACKey;", "canNumber", "", "authenticateWithChip", "Lcom/onfido/android/sdk/capture/internal/nfc/PassportAuthAccess;", "nfcFlowType", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/NfcFlowType;", "doActiveAuth", "", "publicKey", "Ljava/security/PublicKey;", "aaChallenge", "doBac", "doPace", "orderAndFilterSecurityInfos", "securityInfos", "", "readNfcInfo", "Lcom/onfido/android/sdk/capture/internal/nfc/data/NfcPassportExtraction;", "dgStreamReader", "Lcom/onfido/android/sdk/capture/internal/nfc/JMRTDDGStreamReader;", "chipAuthentication", "fileIDs", "", "emitter", "Lio/reactivex/rxjava3/core/ObservableEmitter;", "Lcom/onfido/android/sdk/capture/internal/nfc/PassportNfcExtractionState;", "realtimeNfcEvents", "readNfcInfo$onfido_capture_sdk_core_release", "(Lorg/jmrtd/PassportService;Lcom/onfido/android/sdk/capture/internal/nfc/JMRTDDGStreamReader;[BZ[Lcom/onfido/android/sdk/capture/nfc/MRTDDataGroup;Lio/reactivex/rxjava3/core/ObservableEmitter;Lcom/onfido/android/sdk/capture/internal/nfc/RealtimeNfcEvents;)Lcom/onfido/android/sdk/capture/internal/nfc/data/NfcPassportExtraction;", "readNfcTag", "Lio/reactivex/rxjava3/core/Observable;", "nfcTagRetries", "Landroid/nfc/Tag;", "tag", "isPaceEnabled", "(Lio/reactivex/rxjava3/core/Observable;Lcom/onfido/android/sdk/capture/internal/nfc/data/PassportBACKey;[BLandroid/nfc/Tag;[Lcom/onfido/android/sdk/capture/nfc/MRTDDataGroup;Ljava/lang/Number;ZLcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/NfcFlowType;ZLcom/onfido/android/sdk/capture/internal/nfc/RealtimeNfcEvents;)Lio/reactivex/rxjava3/core/Observable;", "updateStep", "", AnalyticsPropertyKeys.STEP, "Companion", "DefaultDgStreamReader", "ReadingSteps", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class JMRTDPassportNfcReader implements PassportNfcReader {
    private static final Companion Companion = new Companion(null);
    private static final String NFC_LOGGER = "NFC Logger";
    private List<? extends ReadingSteps> allReadingSteps;
    private final Map<MRTDDataGroup, ReadingSteps> nfcFileIDToReadingStep;
    private RealtimeNfcEvents nfcRealtimeEvents;
    private final int nfcScanTagTimeout;
    private final NfcTracker nfcTracker;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/nfc/JMRTDPassportNfcReader$Companion;", "", "()V", "NFC_LOGGER", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\n\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000b"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/nfc/JMRTDPassportNfcReader$DefaultDgStreamReader;", "Lcom/onfido/android/sdk/capture/internal/nfc/JMRTDDGStreamReader;", "passportService", "Lorg/jmrtd/PassportService;", "(Lorg/jmrtd/PassportService;)V", "getPassportService", "()Lorg/jmrtd/PassportService;", "readDGFileAsBytes", "", "dataGroupId", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class DefaultDgStreamReader implements JMRTDDGStreamReader {
        private final PassportService passportService;

        public DefaultDgStreamReader(PassportService passportService) {
            Intrinsics.checkNotNullParameter(passportService, "passportService");
            this.passportService = passportService;
        }

        public final PassportService getPassportService() {
            return this.passportService;
        }

        @Override // com.onfido.android.sdk.capture.internal.nfc.JMRTDDGStreamReader
        public byte[] readDGFileAsBytes(short dataGroupId) {
            try {
                CardFileInputStream inputStream = this.passportService.getInputStream(dataGroupId, PassportService.DEFAULT_MAX_BLOCKSIZE);
                Intrinsics.checkNotNull(inputStream);
                return ByteStreamsKt.readBytes(inputStream);
            } catch (IOException e) {
                Timber.INSTANCE.w("NFC Logger - " + ((int) dataGroupId) + " file exception", e);
                return null;
            } catch (InvocationTargetException e2) {
                Timber.INSTANCE.w("NFC Logger - " + ((int) dataGroupId) + " file exception", e2);
                return null;
            } catch (CardServiceException e3) {
                Timber.INSTANCE.w("NFC Logger - " + ((int) dataGroupId) + " card service exception", e3);
                return null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u0015\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00000\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001a¨\u0006\u001b"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/nfc/JMRTDPassportNfcReader$ReadingSteps;", "", "(Ljava/lang/String;I)V", "progressPercentage", "", "activeSteps", "", "Authenticated", "DG1Read", "DG2Read", "DG3Read", "DG4Read", "DG5Read", "DG6Read", "DG7Read", "DG8Read", "DG9Read", "DG10Read", "DG11Read", "DG12Read", "DG13Read", "DG14Read", "DG15Read", "DG16Read", "SODRead", "COMRead", "DoActiveAuth", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    static final class ReadingSteps {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ ReadingSteps[] $VALUES;
        public static final ReadingSteps Authenticated = new ReadingSteps("Authenticated", 0);
        public static final ReadingSteps DG1Read = new ReadingSteps("DG1Read", 1);
        public static final ReadingSteps DG2Read = new ReadingSteps("DG2Read", 2);
        public static final ReadingSteps DG3Read = new ReadingSteps("DG3Read", 3);
        public static final ReadingSteps DG4Read = new ReadingSteps("DG4Read", 4);
        public static final ReadingSteps DG5Read = new ReadingSteps("DG5Read", 5);
        public static final ReadingSteps DG6Read = new ReadingSteps("DG6Read", 6);
        public static final ReadingSteps DG7Read = new ReadingSteps("DG7Read", 7);
        public static final ReadingSteps DG8Read = new ReadingSteps("DG8Read", 8);
        public static final ReadingSteps DG9Read = new ReadingSteps("DG9Read", 9);
        public static final ReadingSteps DG10Read = new ReadingSteps("DG10Read", 10);
        public static final ReadingSteps DG11Read = new ReadingSteps("DG11Read", 11);
        public static final ReadingSteps DG12Read = new ReadingSteps("DG12Read", 12);
        public static final ReadingSteps DG13Read = new ReadingSteps("DG13Read", 13);
        public static final ReadingSteps DG14Read = new ReadingSteps("DG14Read", 14);
        public static final ReadingSteps DG15Read = new ReadingSteps("DG15Read", 15);
        public static final ReadingSteps DG16Read = new ReadingSteps("DG16Read", 16);
        public static final ReadingSteps SODRead = new ReadingSteps("SODRead", 17);
        public static final ReadingSteps COMRead = new ReadingSteps("COMRead", 18);
        public static final ReadingSteps DoActiveAuth = new ReadingSteps("DoActiveAuth", 19);

        private static final /* synthetic */ ReadingSteps[] $values() {
            return new ReadingSteps[]{Authenticated, DG1Read, DG2Read, DG3Read, DG4Read, DG5Read, DG6Read, DG7Read, DG8Read, DG9Read, DG10Read, DG11Read, DG12Read, DG13Read, DG14Read, DG15Read, DG16Read, SODRead, COMRead, DoActiveAuth};
        }

        static {
            ReadingSteps[] readingStepsArr$values = $values();
            $VALUES = readingStepsArr$values;
            $ENTRIES = EnumEntriesKt.enumEntries(readingStepsArr$values);
        }

        private ReadingSteps(String str, int i) {
        }

        public static EnumEntries<ReadingSteps> getEntries() {
            return $ENTRIES;
        }

        public static ReadingSteps valueOf(String str) {
            return (ReadingSteps) Enum.valueOf(ReadingSteps.class, str);
        }

        public static ReadingSteps[] values() {
            return (ReadingSteps[]) $VALUES.clone();
        }

        public final int progressPercentage(List<? extends ReadingSteps> activeSteps) {
            Intrinsics.checkNotNullParameter(activeSteps, "activeSteps");
            if (activeSteps.indexOf(this) == -1) {
                return 0;
            }
            return (int) ((r0 + 1) * (100.0f / activeSteps.size()));
        }
    }

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[NfcFlowType.values().length];
            try {
                iArr[NfcFlowType.BAC.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[NfcFlowType.PACE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public JMRTDPassportNfcReader(int i, NfcTracker nfcTracker) {
        Intrinsics.checkNotNullParameter(nfcTracker, "nfcTracker");
        this.nfcScanTagTimeout = i;
        this.nfcTracker = nfcTracker;
        this.nfcFileIDToReadingStep = MapsKt.mapOf(TuplesKt.to(MRTDDataGroup.DG1, ReadingSteps.DG1Read), TuplesKt.to(MRTDDataGroup.DG2, ReadingSteps.DG2Read), TuplesKt.to(MRTDDataGroup.DG3, ReadingSteps.DG3Read), TuplesKt.to(MRTDDataGroup.DG4, ReadingSteps.DG4Read), TuplesKt.to(MRTDDataGroup.DG5, ReadingSteps.DG5Read), TuplesKt.to(MRTDDataGroup.DG6, ReadingSteps.DG6Read), TuplesKt.to(MRTDDataGroup.DG7, ReadingSteps.DG7Read), TuplesKt.to(MRTDDataGroup.DG8, ReadingSteps.DG8Read), TuplesKt.to(MRTDDataGroup.DG9, ReadingSteps.DG9Read), TuplesKt.to(MRTDDataGroup.DG10, ReadingSteps.DG10Read), TuplesKt.to(MRTDDataGroup.DG11, ReadingSteps.DG11Read), TuplesKt.to(MRTDDataGroup.DG12, ReadingSteps.DG12Read), TuplesKt.to(MRTDDataGroup.DG13, ReadingSteps.DG13Read), TuplesKt.to(MRTDDataGroup.DG14, ReadingSteps.DG14Read), TuplesKt.to(MRTDDataGroup.DG15, ReadingSteps.DG15Read), TuplesKt.to(MRTDDataGroup.DG16, ReadingSteps.DG16Read), TuplesKt.to(MRTDDataGroup.SOD, ReadingSteps.SODRead), TuplesKt.to(MRTDDataGroup.COM, ReadingSteps.COMRead));
    }

    private final boolean attemptPace(SecurityInfo securityInfo, PassportService passportService, PassportBACKey passportBACKey, Number canNumber) throws CardServiceException {
        if (!(securityInfo instanceof PACEInfo)) {
            return false;
        }
        Timber.Companion companion = Timber.INSTANCE;
        companion.i("NFC Logger - Attempting PACE authentication", new Object[0]);
        PACEKeySpec pACEKeySpecCreateCANKey = canNumber != null ? PACEKeySpec.createCANKey(canNumber.toString()) : PACEKeySpec.createMRZKey(passportBACKey.getBACKey$onfido_capture_sdk_core_release());
        PACEInfo pACEInfo = (PACEInfo) securityInfo;
        String objectIdentifier = pACEInfo.getObjectIdentifier();
        BigInteger parameterId = pACEInfo.getParameterId();
        AlgorithmParameterSpec parameterSpec = PACEInfo.toParameterSpec(parameterId);
        companion.i("\n            NFC Logger - Performing PACE with " + pACEKeySpecCreateCANKey + " input\n            NFC Logger - Performing PACE : Security information object ID: " + objectIdentifier + "\n            NFC Logger - Performing PACE : Security Protocol ID: " + pACEInfo.getProtocolOIDString() + "\n            NFC Logger - Performing PACE : Security information parameter ID: " + PACEInfo.toStandardizedParamIdString(parameterId) + "\n            ", new Object[0]);
        passportService.doPACE(pACEKeySpecCreateCANKey, objectIdentifier, parameterSpec, parameterId);
        return true;
    }

    private final PassportAuthAccess authenticateWithChip(NfcFlowType nfcFlowType, PassportService passportService, PassportBACKey passportBACKey, Number canNumber) {
        int i = WhenMappings.$EnumSwitchMapping$0[nfcFlowType.ordinal()];
        if (i == 1) {
            try {
                PassportAuthAccess passportAuthAccessDoBac = doBac(passportService, passportBACKey);
                return !passportAuthAccessDoBac.getHasAccess() ? doPace(passportService, passportBACKey, canNumber) : passportAuthAccessDoBac;
            } catch (Exception unused) {
                return doPace(passportService, passportBACKey, canNumber);
            }
        }
        if (i != 2) {
            throw new NoWhenBranchMatchedException();
        }
        try {
            PassportAuthAccess passportAuthAccessDoPace = doPace(passportService, passportBACKey, canNumber);
            return !passportAuthAccessDoPace.getHasAccess() ? doBac(passportService, passportBACKey) : passportAuthAccessDoPace;
        } catch (Exception unused2) {
            return doBac(passportService, passportBACKey);
        }
    }

    private final byte[] doActiveAuth(PassportService passportService, PublicKey publicKey, byte[] aaChallenge) {
        if (publicKey == null || aaChallenge == null) {
            return null;
        }
        try {
            Timber.Companion companion = Timber.INSTANCE;
            companion.i("NFC Logger - Attempting Active authentication", new Object[0]);
            AAResult aAResultDoAA = passportService.doAA(publicKey, null, null, aaChallenge);
            byte[] response = aAResultDoAA.getResponse();
            Intrinsics.checkNotNullExpressionValue(response, "getResponse(...)");
            if (!(response.length == 0)) {
                companion.i("NFC Logger - Active authentication succeeded", new Object[0]);
            }
            return aAResultDoAA.getResponse();
        } catch (CardServiceException e) {
            Timber.INSTANCE.w("NFC Logger - Active authentication card service exception", e);
            return null;
        }
    }

    private final PassportAuthAccess doBac(PassportService passportService, PassportBACKey passportBACKey) {
        CardServiceException cardServiceException;
        boolean z;
        boolean z2;
        RealtimeNfcEvents realtimeNfcEvents = this.nfcRealtimeEvents;
        if (realtimeNfcEvents != null) {
            realtimeNfcEvents.accessControlStarted(NfcFlowType.BAC);
        }
        try {
            Timber.INSTANCE.i("NFC Logger - Attempting BAC authentication", new Object[0]);
            passportService.sendSelectApplet(false);
            passportService.doBAC(passportBACKey.getBACKey$onfido_capture_sdk_core_release());
            RealtimeNfcEvents realtimeNfcEvents2 = this.nfcRealtimeEvents;
            if (realtimeNfcEvents2 != null) {
                realtimeNfcEvents2.accessControlFinished(NfcFlowType.BAC);
            }
            z = true;
            z2 = true;
            cardServiceException = null;
        } catch (CardServiceException e) {
            RealtimeNfcEvents realtimeNfcEvents3 = this.nfcRealtimeEvents;
            if (realtimeNfcEvents3 != null) {
                realtimeNfcEvents3.accessControlFailed(NfcFlowType.BAC, e);
            }
            cardServiceException = e;
            z = false;
            z2 = false;
        }
        Timber.INSTANCE.i("NFC Logger - BAC auth ".concat(z2 ? "succeeded" : "failed"), new Object[0]);
        return new PassportAuthAccess(z, false, z2, null, cardServiceException, null, null, null, 234, null);
    }

    private final PassportAuthAccess doPace(PassportService passportService, PassportBACKey passportBACKey, Number canNumber) throws CardServiceException {
        String str;
        String str2;
        String string;
        String str3;
        String str4;
        boolean zAttemptPace;
        Exception exc;
        boolean z;
        RealtimeNfcEvents realtimeNfcEvents = this.nfcRealtimeEvents;
        if (realtimeNfcEvents != null) {
            realtimeNfcEvents.accessControlStarted(NfcFlowType.PACE);
        }
        try {
            Timber.Companion companion = Timber.INSTANCE;
            companion.i("NFC Logger - Start reading card access", new Object[0]);
            CardAccessFile cardAccessFile = new CardAccessFile(passportService.getInputStream((short) 284, PassportService.DEFAULT_MAX_BLOCKSIZE));
            companion.i("NFC Logger - Start retrieving securityInfos", new Object[0]);
            string = cardAccessFile.toString();
            try {
                companion.i("NFC Logger - CardAccess file:\n" + string, new Object[0]);
                Collection<SecurityInfo> securityInfos = cardAccessFile.getSecurityInfos();
                Intrinsics.checkNotNullExpressionValue(securityInfos, "getSecurityInfos(...)");
                List<SecurityInfo> listOrderAndFilterSecurityInfos = orderAndFilterSecurityInfos(securityInfos);
                if (!listOrderAndFilterSecurityInfos.isEmpty()) {
                    companion.i("NFC Logger - Attempting PACE", new Object[0]);
                    SecurityInfo securityInfo = (SecurityInfo) CollectionsKt.first((List) listOrderAndFilterSecurityInfos);
                    String string2 = securityInfo.toString();
                    try {
                        zAttemptPace = attemptPace(securityInfo, passportService, passportBACKey, canNumber);
                        str4 = string2;
                    } catch (CardServiceException e) {
                        e = e;
                        str3 = string2;
                        RealtimeNfcEvents realtimeNfcEvents2 = this.nfcRealtimeEvents;
                        if (realtimeNfcEvents2 != null) {
                            realtimeNfcEvents2.accessControlFailed(NfcFlowType.PACE, e);
                        }
                        Timber.Companion companion2 = Timber.INSTANCE;
                        StringBuilder sb = new StringBuilder("NFC Logger - CardServiceException - ");
                        String message = e.getMessage();
                        companion2.w(sb.append(message != null ? message : "").toString(), new Object[0]);
                        return new PassportAuthAccess(false, false, false, e, null, null, string, str3, 52, null);
                    } catch (Exception e2) {
                        e = e2;
                        str2 = string2;
                        str = string;
                        RealtimeNfcEvents realtimeNfcEvents3 = this.nfcRealtimeEvents;
                        if (realtimeNfcEvents3 != null) {
                            realtimeNfcEvents3.accessControlFailed(NfcFlowType.PACE, e);
                        }
                        Timber.INSTANCE.e("NFC Logger - " + e, new Object[0]);
                        return new PassportAuthAccess(false, false, false, e, null, null, str, str2, 52, null);
                    }
                } else {
                    str4 = null;
                    zAttemptPace = false;
                }
                try {
                    passportService.sendSelectApplet(zAttemptPace);
                    try {
                        passportService.getInputStream(PassportService.EF_COM, PassportService.DEFAULT_MAX_BLOCKSIZE).read();
                        exc = null;
                        z = true;
                    } catch (Exception e3) {
                        RealtimeNfcEvents realtimeNfcEvents4 = this.nfcRealtimeEvents;
                        if (realtimeNfcEvents4 != null) {
                            realtimeNfcEvents4.accessControlFailed(NfcFlowType.PACE, e3);
                        }
                        Timber.Companion companion3 = Timber.INSTANCE;
                        StringBuilder sb2 = new StringBuilder("NFC Logger - ");
                        String message2 = e3.getMessage();
                        companion3.w(sb2.append(message2 != null ? message2 : "").toString(), new Object[0]);
                        exc = e3;
                        z = false;
                    }
                    RealtimeNfcEvents realtimeNfcEvents5 = this.nfcRealtimeEvents;
                    if (zAttemptPace) {
                        if (realtimeNfcEvents5 != null) {
                            realtimeNfcEvents5.accessControlFinished(NfcFlowType.PACE);
                        }
                    } else if (realtimeNfcEvents5 != null) {
                        realtimeNfcEvents5.accessControlFailed(NfcFlowType.PACE, new Throwable("Unknown error"));
                    }
                    return new PassportAuthAccess(z, zAttemptPace, false, exc, null, null, string, str4, 52, null);
                } catch (CardServiceException e4) {
                    RealtimeNfcEvents realtimeNfcEvents6 = this.nfcRealtimeEvents;
                    if (realtimeNfcEvents6 != null) {
                        realtimeNfcEvents6.accessControlFailed(NfcFlowType.PACE, e4);
                    }
                    Timber.Companion companion4 = Timber.INSTANCE;
                    StringBuilder sb3 = new StringBuilder("NFC Logger - CardServiceException - ");
                    String message3 = e4.getMessage();
                    companion4.w(sb3.append(message3 != null ? message3 : "").toString(), new Object[0]);
                    return new PassportAuthAccess(false, zAttemptPace, false, e4, null, null, string, str4, 52, null);
                }
            } catch (CardServiceException e5) {
                e = e5;
                str3 = null;
            } catch (Exception e6) {
                e = e6;
                str2 = null;
            }
        } catch (CardServiceException e7) {
            e = e7;
            string = null;
            str3 = null;
        } catch (Exception e8) {
            e = e8;
            str = null;
            str2 = null;
        }
    }

    private final List<SecurityInfo> orderAndFilterSecurityInfos(Collection<? extends SecurityInfo> securityInfos) {
        ArrayList arrayList = new ArrayList();
        for (Object obj : securityInfos) {
            if (obj instanceof PACEInfo) {
                arrayList.add(obj);
            }
        }
        return CollectionsKt.sortedWith(arrayList, new Comparator() { // from class: com.onfido.android.sdk.capture.internal.nfc.JMRTDPassportNfcReader$orderAndFilterSecurityInfos$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                PACEInfo.MappingType mappingType = PACEInfo.toMappingType(((PACEInfo) t).getObjectIdentifier());
                PACEInfo.MappingType mappingType2 = PACEInfo.MappingType.IM;
                return ComparisonsKt.compareValues(Integer.valueOf(mappingType == mappingType2 ? 1 : 2), Integer.valueOf(PACEInfo.toMappingType(((PACEInfo) t2).getObjectIdentifier()) != mappingType2 ? 2 : 1));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:70:0x01f4  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x020b  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x020e  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0235  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0262  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0265  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x029d  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x02b9  */
    /* JADX WARN: Type inference failed for: r10v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r10v10 */
    /* JADX WARN: Type inference failed for: r10v11 */
    /* JADX WARN: Type inference failed for: r10v12 */
    /* JADX WARN: Type inference failed for: r10v13 */
    /* JADX WARN: Type inference failed for: r10v14 */
    /* JADX WARN: Type inference failed for: r10v15 */
    /* JADX WARN: Type inference failed for: r10v16 */
    /* JADX WARN: Type inference failed for: r10v17 */
    /* JADX WARN: Type inference failed for: r10v18 */
    /* JADX WARN: Type inference failed for: r10v19 */
    /* JADX WARN: Type inference failed for: r10v20 */
    /* JADX WARN: Type inference failed for: r10v21 */
    /* JADX WARN: Type inference failed for: r10v22 */
    /* JADX WARN: Type inference failed for: r10v23 */
    /* JADX WARN: Type inference failed for: r10v24 */
    /* JADX WARN: Type inference failed for: r10v25, types: [int] */
    /* JADX WARN: Type inference failed for: r10v26 */
    /* JADX WARN: Type inference failed for: r10v27 */
    /* JADX WARN: Type inference failed for: r10v28 */
    /* JADX WARN: Type inference failed for: r10v29 */
    /* JADX WARN: Type inference failed for: r10v3 */
    /* JADX WARN: Type inference failed for: r10v31 */
    /* JADX WARN: Type inference failed for: r10v32 */
    /* JADX WARN: Type inference failed for: r10v33 */
    /* JADX WARN: Type inference failed for: r10v34 */
    /* JADX WARN: Type inference failed for: r10v35 */
    /* JADX WARN: Type inference failed for: r10v36 */
    /* JADX WARN: Type inference failed for: r10v4 */
    /* JADX WARN: Type inference failed for: r10v6 */
    /* JADX WARN: Type inference failed for: r10v8, types: [int] */
    /* JADX WARN: Type inference failed for: r10v9 */
    /* JADX WARN: Type inference failed for: r4v1, types: [java.security.Provider, org.spongycastle.jce.provider.BouncyCastleProvider] */
    /* JADX WARN: Type inference failed for: r4v21 */
    /* JADX WARN: Type inference failed for: r4v22 */
    /* JADX WARN: Type inference failed for: r4v23 */
    /* JADX WARN: Type inference failed for: r4v24 */
    /* JADX WARN: Type inference failed for: r4v25 */
    /* JADX WARN: Type inference failed for: r4v26 */
    /* JADX WARN: Type inference failed for: r4v27 */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r4v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void readNfcTag$lambda$3(com.onfido.android.sdk.capture.internal.nfc.RealtimeNfcEvents r29, android.nfc.Tag r30, final com.onfido.android.sdk.capture.internal.nfc.JMRTDPassportNfcReader r31, com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.NfcFlowType r32, com.onfido.android.sdk.capture.internal.nfc.data.PassportBACKey r33, java.lang.Number r34, byte[] r35, boolean r36, com.onfido.android.sdk.capture.nfc.MRTDDataGroup[] r37, io.reactivex.rxjava3.core.ObservableEmitter r38) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 723
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.internal.nfc.JMRTDPassportNfcReader.readNfcTag$lambda$3(com.onfido.android.sdk.capture.internal.nfc.RealtimeNfcEvents, android.nfc.Tag, com.onfido.android.sdk.capture.internal.nfc.JMRTDPassportNfcReader, com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.NfcFlowType, com.onfido.android.sdk.capture.internal.nfc.data.PassportBACKey, java.lang.Number, byte[], boolean, com.onfido.android.sdk.capture.nfc.MRTDDataGroup[], io.reactivex.rxjava3.core.ObservableEmitter):void");
    }

    private final void updateStep(ObservableEmitter<PassportNfcExtractionState> observableEmitter, ReadingSteps readingSteps) {
        List<? extends ReadingSteps> list = this.allReadingSteps;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("allReadingSteps");
            list = null;
        }
        observableEmitter.onNext(new PassportNfcExtractionState.Reading(readingSteps.progressPercentage(list)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0295 A[Catch: IllegalArgumentException -> 0x0387, TryCatch #2 {IllegalArgumentException -> 0x0387, blocks: (B:3:0x0022, B:5:0x0028, B:6:0x0043, B:8:0x0049, B:10:0x0059, B:11:0x005d, B:12:0x006b, B:14:0x0073, B:16:0x007b, B:20:0x008f, B:22:0x0097, B:24:0x009f, B:28:0x00b3, B:30:0x00bb, B:32:0x00c9, B:34:0x00d1, B:36:0x00df, B:38:0x00e7, B:40:0x00f5, B:42:0x00fd, B:44:0x010b, B:46:0x0113, B:48:0x0121, B:50:0x0129, B:52:0x0139, B:54:0x0141, B:56:0x0151, B:58:0x0159, B:60:0x0169, B:62:0x0171, B:64:0x0181, B:66:0x0189, B:68:0x0199, B:70:0x01a1, B:72:0x01b1, B:74:0x01b9, B:76:0x01c6, B:78:0x01ce, B:80:0x01dc, B:82:0x01e4, B:84:0x01f6, B:86:0x01fe, B:88:0x0206, B:92:0x021e, B:94:0x0226, B:98:0x023a, B:100:0x023e, B:101:0x0243, B:103:0x0257, B:105:0x025b, B:115:0x028e, B:117:0x0295, B:119:0x0299, B:121:0x02a0, B:123:0x02b0, B:124:0x02b9, B:126:0x02bf, B:128:0x02c7, B:129:0x02cb, B:132:0x02d5, B:133:0x02de, B:135:0x02e4, B:137:0x02ec, B:138:0x02f0, B:142:0x02fc, B:143:0x030c, B:145:0x0312, B:147:0x0316, B:106:0x0261, B:108:0x0265, B:113:0x0286, B:110:0x0278, B:112:0x027c, B:89:0x0210, B:90:0x021b, B:25:0x00a6, B:26:0x00b1, B:17:0x0082, B:18:0x008d), top: B:177:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:164:0x036f  */
    /* JADX WARN: Type inference failed for: r28v0, types: [com.onfido.android.sdk.capture.internal.nfc.JMRTDPassportNfcReader] */
    /* JADX WARN: Type inference failed for: r3v0, types: [byte[]] */
    /* JADX WARN: Type inference failed for: r3v23, types: [com.onfido.android.sdk.capture.internal.nfc.RealtimeNfcEvents] */
    /* JADX WARN: Type inference failed for: r3v24, types: [com.onfido.android.sdk.capture.internal.nfc.RealtimeNfcEvents] */
    /* JADX WARN: Type inference failed for: r3v25, types: [com.onfido.android.sdk.capture.internal.nfc.RealtimeNfcEvents] */
    /* JADX WARN: Type inference failed for: r3v27, types: [com.onfido.android.sdk.capture.internal.nfc.RealtimeNfcEvents] */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r3v36 */
    /* JADX WARN: Type inference failed for: r3v37 */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.onfido.android.sdk.capture.internal.nfc.data.NfcPassportExtraction] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.onfido.android.sdk.capture.internal.nfc.data.NfcPassportExtraction readNfcInfo$onfido_capture_sdk_core_release(org.jmrtd.PassportService r29, com.onfido.android.sdk.capture.internal.nfc.JMRTDDGStreamReader r30, byte[] r31, boolean r32, com.onfido.android.sdk.capture.nfc.MRTDDataGroup[] r33, io.reactivex.rxjava3.core.ObservableEmitter<com.onfido.android.sdk.capture.internal.nfc.PassportNfcExtractionState> r34, com.onfido.android.sdk.capture.internal.nfc.RealtimeNfcEvents r35) {
        /*
            Method dump skipped, instructions count: 934
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.internal.nfc.JMRTDPassportNfcReader.readNfcInfo$onfido_capture_sdk_core_release(org.jmrtd.PassportService, com.onfido.android.sdk.capture.internal.nfc.JMRTDDGStreamReader, byte[], boolean, com.onfido.android.sdk.capture.nfc.MRTDDataGroup[], io.reactivex.rxjava3.core.ObservableEmitter, com.onfido.android.sdk.capture.internal.nfc.RealtimeNfcEvents):com.onfido.android.sdk.capture.internal.nfc.data.NfcPassportExtraction");
    }

    @Override // com.onfido.android.sdk.capture.internal.nfc.PassportNfcReader
    public Observable<PassportNfcExtractionState> readNfcTag(Observable<Tag> nfcTagRetries, final PassportBACKey passportBACKey, final byte[] aaChallenge, final Tag tag, final MRTDDataGroup[] fileIDs, final Number canNumber, boolean isPaceEnabled, final NfcFlowType nfcFlowType, final boolean chipAuthentication, final RealtimeNfcEvents realtimeNfcEvents) {
        Intrinsics.checkNotNullParameter(nfcTagRetries, "nfcTagRetries");
        Intrinsics.checkNotNullParameter(passportBACKey, "passportBACKey");
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(fileIDs, "fileIDs");
        Intrinsics.checkNotNullParameter(nfcFlowType, "nfcFlowType");
        Observable<PassportNfcExtractionState> observableCreate = Observable.create(new ObservableOnSubscribe() { // from class: com.onfido.android.sdk.capture.internal.nfc.JMRTDPassportNfcReader$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) throws Throwable {
                JMRTDPassportNfcReader.readNfcTag$lambda$3(realtimeNfcEvents, tag, this, nfcFlowType, passportBACKey, canNumber, aaChallenge, chipAuthentication, fileIDs, observableEmitter);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableCreate, "create(...)");
        return observableCreate;
    }
}
