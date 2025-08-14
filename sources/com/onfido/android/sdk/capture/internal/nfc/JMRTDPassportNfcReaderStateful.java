package com.onfido.android.sdk.capture.internal.nfc;

import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.AnalyticsPropertyKeys;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.NfcFlowType;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.NfcTracker;
import com.onfido.android.sdk.capture.internal.nfc.PassportNfcExtractionState;
import com.onfido.android.sdk.capture.internal.nfc.data.NfcPassportExtraction;
import com.onfido.android.sdk.capture.internal.nfc.data.PassportBACKey;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.android.sdk.capture.nfc.MRTDDataGroup;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.security.PublicKey;
import java.security.Security;
import java.security.spec.AlgorithmParameterSpec;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.ArrayDeque;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.io.ByteStreamsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import net.sf.scuba.smartcards.CardFileInputStream;
import net.sf.scuba.smartcards.CardService;
import net.sf.scuba.smartcards.CardServiceException;
import org.jmrtd.PACEKeySpec;
import org.jmrtd.PassportService;
import org.jmrtd.lds.CardAccessFile;
import org.jmrtd.lds.PACEInfo;
import org.jmrtd.lds.SecurityInfo;
import org.jmrtd.lds.icao.DG15File;
import org.jmrtd.protocol.AAResult;
import org.spongycastle.jce.provider.BouncyCastleProvider;

@Metadata(d1 = {"\u0000¼\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\n\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0004\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0000\u0018\u0000 F2\u00020\u0001:\u0005FGHIJB\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ \u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J2\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u001a\u001a\u0004\u0018\u00010\u00192\u0014\u0010\u001b\u001a\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u001cH\u0002J*\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"2\u0006\u0010#\u001a\u00020\u0011H\u0002J(\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\u00192\u0014\u0010\u001b\u001a\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u001cH\u0002J!\u0010'\u001a\b\u0012\u0004\u0012\u00020\u000f0(2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u000e0*H\u0002¢\u0006\u0002\u0010+J\u001c\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00130\n2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00130.H\u0002JB\u0010/\u001a\u0002002\u0006\u0010\u0014\u001a\u00020\u00152\f\u00101\u001a\b\u0012\u0004\u0012\u000203022\f\u00104\u001a\b\u0012\u0004\u0012\u00020\u000f0(2\u0014\u0010\u001b\u001a\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u001cH\u0002JN\u00105\u001a\u0004\u0018\u00010%2\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u001a\u001a\u0004\u0018\u00010\u00192\f\u00101\u001a\b\u0012\u0004\u0012\u000203022\f\u00104\u001a\b\u0012\u0004\u0012\u00020\u000f0(2\u0014\u0010\u001b\u001a\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u001cH\u0002Ju\u00106\u001a\b\u0012\u0004\u0012\u000203072\f\u00108\u001a\b\u0012\u0004\u0012\u000209072\u0006\u0010\u001f\u001a\u00020 2\b\u0010\u001a\u001a\u0004\u0018\u00010\u00192\u0006\u0010:\u001a\u0002092\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u000e0*2\b\u0010!\u001a\u0004\u0018\u00010\"2\u0006\u0010;\u001a\u00020\u00112\u0006\u0010<\u001a\u00020=2\u0006\u0010>\u001a\u00020\u00112\b\u0010?\u001a\u0004\u0018\u00010@H\u0016¢\u0006\u0002\u0010AJ^\u0010B\u001a\b\u0012\u0004\u0012\u000203072\u0006\u0010\u001f\u001a\u00020 2\b\u0010\u001a\u001a\u0004\u0018\u00010\u00192\u0006\u0010:\u001a\u0002092\b\u0010!\u001a\u0004\u0018\u00010\"2\f\u00104\u001a\b\u0012\u0004\u0012\u00020\u000f0(2\u0014\u0010\u001b\u001a\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u001c2\u0006\u0010#\u001a\u00020\u0011H\u0002J\f\u0010C\u001a\u00020\u000b*\u00020\u000fH\u0002J\u001a\u0010D\u001a\u000200*\b\u0012\u0004\u0012\u000203022\u0006\u0010E\u001a\u00020\u000bH\u0002R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006K"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/nfc/JMRTDPassportNfcReaderStateful;", "Lcom/onfido/android/sdk/capture/internal/nfc/PassportNfcReader;", "jmrtdHelper", "Lcom/onfido/android/sdk/capture/internal/nfc/JMRTDPassportNfcReaderStateful$JMRTDHelper;", "nfcScanTagTimeout", "", "nfcTracker", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/NfcTracker;", "(Lcom/onfido/android/sdk/capture/internal/nfc/JMRTDPassportNfcReaderStateful$JMRTDHelper;ILcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/NfcTracker;)V", "allReadingSteps", "", "Lcom/onfido/android/sdk/capture/internal/nfc/JMRTDPassportNfcReaderStateful$ReadingStep;", "nfcFileIDToShort", "", "Lcom/onfido/android/sdk/capture/nfc/MRTDDataGroup;", "", "doPace", "", "securityInfo", "Lorg/jmrtd/lds/SecurityInfo;", "passportService", "Lorg/jmrtd/PassportService;", "paceKeySpec", "Lorg/jmrtd/PACEKeySpec;", "getAaResponse", "", "aaChallenge", "dataGroupValues", "", "getAuthAccess", "Lcom/onfido/android/sdk/capture/internal/nfc/PassportAuthAccess;", "passportBACKey", "Lcom/onfido/android/sdk/capture/internal/nfc/data/PassportBACKey;", "canNumber", "", "tryPace", "getNfcPassportExtraction", "Lcom/onfido/android/sdk/capture/internal/nfc/data/NfcPassportExtraction;", "aaResponse", "mapDataGroupsToBeRead", "Lkotlin/collections/ArrayDeque;", "fileIDs", "", "([Lcom/onfido/android/sdk/capture/nfc/MRTDDataGroup;)Lkotlin/collections/ArrayDeque;", "orderAndFilterSecurityInfos", "securityInfos", "", "readDataGroups", "", "emitter", "Lio/reactivex/rxjava3/core/ObservableEmitter;", "Lcom/onfido/android/sdk/capture/internal/nfc/PassportNfcExtractionState;", "dataGroups", "readNfcInfo", "readNfcTag", "Lio/reactivex/rxjava3/core/Observable;", "nfcTagRetries", "Landroid/nfc/Tag;", "tag", "isPaceEnabled", "nfcFlowType", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/NfcFlowType;", "chipAuthentication", "realtimeNfcEvents", "Lcom/onfido/android/sdk/capture/internal/nfc/RealtimeNfcEvents;", "(Lio/reactivex/rxjava3/core/Observable;Lcom/onfido/android/sdk/capture/internal/nfc/data/PassportBACKey;[BLandroid/nfc/Tag;[Lcom/onfido/android/sdk/capture/nfc/MRTDDataGroup;Ljava/lang/Number;ZLcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/NfcFlowType;ZLcom/onfido/android/sdk/capture/internal/nfc/RealtimeNfcEvents;)Lio/reactivex/rxjava3/core/Observable;", "readNfcTagInternal", "toReadingStep", "updateStep", AnalyticsPropertyKeys.STEP, "Companion", "ConnectionLostException", "JMRTDHelper", "JMRTDHelperImpl", "ReadingStep", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class JMRTDPassportNfcReaderStateful implements PassportNfcReader {
    private static final Companion Companion = new Companion(null);
    private static final String NFC_LOGGER = "NFC Logger";
    private static final int TIMEOUT_IN_MS = 60000;
    private List<? extends ReadingStep> allReadingSteps;
    private final JMRTDHelper jmrtdHelper;
    private final Map<MRTDDataGroup, Short> nfcFileIDToShort;
    private final int nfcScanTagTimeout;
    private final NfcTracker nfcTracker;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/nfc/JMRTDPassportNfcReaderStateful$Companion;", "", "()V", "NFC_LOGGER", "", "TIMEOUT_IN_MS", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0005¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/nfc/JMRTDPassportNfcReaderStateful$ConnectionLostException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class ConnectionLostException extends Exception {
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0010\n\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&J&\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\r\u001a\u0004\u0018\u00010\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\fH&J\u0018\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0011H&¨\u0006\u0012"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/nfc/JMRTDPassportNfcReaderStateful$JMRTDHelper;", "", "createCardAccessFile", "Lorg/jmrtd/lds/CardAccessFile;", "passportService", "Lorg/jmrtd/PassportService;", "createPassportService", "tag", "Landroid/nfc/Tag;", "nfcScanTagTimeout", "", "doActiveAuth", "", "dg15Data", "aaChallenge", "readBytes", "fid", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface JMRTDHelper {
        CardAccessFile createCardAccessFile(PassportService passportService);

        PassportService createPassportService(Tag tag, int nfcScanTagTimeout);

        byte[] doActiveAuth(PassportService passportService, byte[] dg15Data, byte[] aaChallenge);

        byte[] readBytes(PassportService passportService, short fid);
    }

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0010\n\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J&\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\rH\u0016J\u0018\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0012H\u0016¨\u0006\u0013"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/nfc/JMRTDPassportNfcReaderStateful$JMRTDHelperImpl;", "Lcom/onfido/android/sdk/capture/internal/nfc/JMRTDPassportNfcReaderStateful$JMRTDHelper;", "()V", "createCardAccessFile", "Lorg/jmrtd/lds/CardAccessFile;", "passportService", "Lorg/jmrtd/PassportService;", "createPassportService", "tag", "Landroid/nfc/Tag;", "nfcScanTagTimeout", "", "doActiveAuth", "", "dg15Data", "aaChallenge", "readBytes", "fid", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class JMRTDHelperImpl implements JMRTDHelper {
        public static final JMRTDHelperImpl INSTANCE = new JMRTDHelperImpl();

        private JMRTDHelperImpl() {
        }

        @Override // com.onfido.android.sdk.capture.internal.nfc.JMRTDPassportNfcReaderStateful.JMRTDHelper
        public CardAccessFile createCardAccessFile(PassportService passportService) {
            Intrinsics.checkNotNullParameter(passportService, "passportService");
            return new CardAccessFile(passportService.getInputStream((short) 284, PassportService.DEFAULT_MAX_BLOCKSIZE));
        }

        @Override // com.onfido.android.sdk.capture.internal.nfc.JMRTDPassportNfcReaderStateful.JMRTDHelper
        public PassportService createPassportService(Tag tag, int nfcScanTagTimeout) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            IsoDep isoDep = IsoDep.get(tag);
            Security.insertProviderAt(new BouncyCastleProvider(), 1);
            isoDep.setTimeout(nfcScanTagTimeout);
            return new PassportService(CardService.getInstance(isoDep), 256, PassportService.DEFAULT_MAX_BLOCKSIZE, false, false);
        }

        @Override // com.onfido.android.sdk.capture.internal.nfc.JMRTDPassportNfcReaderStateful.JMRTDHelper
        public byte[] doActiveAuth(PassportService passportService, byte[] dg15Data, byte[] aaChallenge) {
            Intrinsics.checkNotNullParameter(passportService, "passportService");
            PublicKey publicKey = new DG15File(new ByteArrayInputStream(dg15Data)).getPublicKey();
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

        @Override // com.onfido.android.sdk.capture.internal.nfc.JMRTDPassportNfcReaderStateful.JMRTDHelper
        public byte[] readBytes(PassportService passportService, short fid) throws CardServiceException {
            Intrinsics.checkNotNullParameter(passportService, "passportService");
            CardFileInputStream inputStream = passportService.getInputStream(fid, PassportService.DEFAULT_MAX_BLOCKSIZE);
            Intrinsics.checkNotNullExpressionValue(inputStream, "getInputStream(...)");
            return ByteStreamsKt.readBytes(inputStream);
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u0014\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00000\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019¨\u0006\u001a"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/nfc/JMRTDPassportNfcReaderStateful$ReadingStep;", "", "(Ljava/lang/String;I)V", "progressPercentage", "", "activeSteps", "", "Authenticated", "DG1Read", "DG2Read", "DG3Read", "DG4Read", "DG5Read", "DG6Read", "DG7Read", "DG8Read", "DG9Read", "DG10Read", "DG11Read", "DG12Read", "DG13Read", "DG14Read", "DG15Read", "DG16Read", "SODRead", "DoActiveAuth", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class ReadingStep {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ ReadingStep[] $VALUES;
        public static final ReadingStep Authenticated = new ReadingStep("Authenticated", 0);
        public static final ReadingStep DG1Read = new ReadingStep("DG1Read", 1);
        public static final ReadingStep DG2Read = new ReadingStep("DG2Read", 2);
        public static final ReadingStep DG3Read = new ReadingStep("DG3Read", 3);
        public static final ReadingStep DG4Read = new ReadingStep("DG4Read", 4);
        public static final ReadingStep DG5Read = new ReadingStep("DG5Read", 5);
        public static final ReadingStep DG6Read = new ReadingStep("DG6Read", 6);
        public static final ReadingStep DG7Read = new ReadingStep("DG7Read", 7);
        public static final ReadingStep DG8Read = new ReadingStep("DG8Read", 8);
        public static final ReadingStep DG9Read = new ReadingStep("DG9Read", 9);
        public static final ReadingStep DG10Read = new ReadingStep("DG10Read", 10);
        public static final ReadingStep DG11Read = new ReadingStep("DG11Read", 11);
        public static final ReadingStep DG12Read = new ReadingStep("DG12Read", 12);
        public static final ReadingStep DG13Read = new ReadingStep("DG13Read", 13);
        public static final ReadingStep DG14Read = new ReadingStep("DG14Read", 14);
        public static final ReadingStep DG15Read = new ReadingStep("DG15Read", 15);
        public static final ReadingStep DG16Read = new ReadingStep("DG16Read", 16);
        public static final ReadingStep SODRead = new ReadingStep("SODRead", 17);
        public static final ReadingStep DoActiveAuth = new ReadingStep("DoActiveAuth", 18);

        private static final /* synthetic */ ReadingStep[] $values() {
            return new ReadingStep[]{Authenticated, DG1Read, DG2Read, DG3Read, DG4Read, DG5Read, DG6Read, DG7Read, DG8Read, DG9Read, DG10Read, DG11Read, DG12Read, DG13Read, DG14Read, DG15Read, DG16Read, SODRead, DoActiveAuth};
        }

        static {
            ReadingStep[] readingStepArr$values = $values();
            $VALUES = readingStepArr$values;
            $ENTRIES = EnumEntriesKt.enumEntries(readingStepArr$values);
        }

        private ReadingStep(String str, int i) {
        }

        public static EnumEntries<ReadingStep> getEntries() {
            return $ENTRIES;
        }

        public static ReadingStep valueOf(String str) {
            return (ReadingStep) Enum.valueOf(ReadingStep.class, str);
        }

        public static ReadingStep[] values() {
            return (ReadingStep[]) $VALUES.clone();
        }

        public final int progressPercentage(List<? extends ReadingStep> activeSteps) {
            Intrinsics.checkNotNullParameter(activeSteps, "activeSteps");
            return (int) (((activeSteps.indexOf(this) + 1) / activeSteps.size()) * 100);
        }
    }

    public JMRTDPassportNfcReaderStateful(JMRTDHelper jmrtdHelper, int i, NfcTracker nfcTracker) {
        Intrinsics.checkNotNullParameter(jmrtdHelper, "jmrtdHelper");
        Intrinsics.checkNotNullParameter(nfcTracker, "nfcTracker");
        this.jmrtdHelper = jmrtdHelper;
        this.nfcScanTagTimeout = i;
        this.nfcTracker = nfcTracker;
        this.nfcFileIDToShort = MapsKt.mapOf(TuplesKt.to(MRTDDataGroup.DG1, Short.valueOf(PassportService.EF_DG1)), TuplesKt.to(MRTDDataGroup.DG2, Short.valueOf(PassportService.EF_DG2)), TuplesKt.to(MRTDDataGroup.DG3, Short.valueOf(PassportService.EF_DG3)), TuplesKt.to(MRTDDataGroup.DG4, Short.valueOf(PassportService.EF_DG4)), TuplesKt.to(MRTDDataGroup.DG5, Short.valueOf(PassportService.EF_DG5)), TuplesKt.to(MRTDDataGroup.DG6, Short.valueOf(PassportService.EF_DG6)), TuplesKt.to(MRTDDataGroup.DG7, Short.valueOf(PassportService.EF_DG7)), TuplesKt.to(MRTDDataGroup.DG8, Short.valueOf(PassportService.EF_DG8)), TuplesKt.to(MRTDDataGroup.DG9, Short.valueOf(PassportService.EF_DG9)), TuplesKt.to(MRTDDataGroup.DG10, Short.valueOf(PassportService.EF_DG10)), TuplesKt.to(MRTDDataGroup.DG11, Short.valueOf(PassportService.EF_DG11)), TuplesKt.to(MRTDDataGroup.DG12, Short.valueOf(PassportService.EF_DG12)), TuplesKt.to(MRTDDataGroup.DG13, Short.valueOf(PassportService.EF_DG13)), TuplesKt.to(MRTDDataGroup.DG14, Short.valueOf(PassportService.EF_DG14)), TuplesKt.to(MRTDDataGroup.DG15, Short.valueOf(PassportService.EF_DG15)), TuplesKt.to(MRTDDataGroup.DG16, Short.valueOf(PassportService.EF_DG16)), TuplesKt.to(MRTDDataGroup.SOD, (short) 285));
    }

    private final boolean doPace(SecurityInfo securityInfo, PassportService passportService, PACEKeySpec paceKeySpec) throws CardServiceException {
        if (!(securityInfo instanceof PACEInfo)) {
            return false;
        }
        Timber.Companion companion = Timber.INSTANCE;
        companion.i("NFC Logger - Attempting PACE authentication", new Object[0]);
        PACEInfo pACEInfo = (PACEInfo) securityInfo;
        String objectIdentifier = pACEInfo.getObjectIdentifier();
        BigInteger parameterId = pACEInfo.getParameterId();
        AlgorithmParameterSpec parameterSpec = PACEInfo.toParameterSpec(parameterId);
        companion.i("\n            NFC Logger - Performing PACE with " + paceKeySpec + " input\n            NFC Logger - Performing PACE : Security information object ID: " + objectIdentifier + "\n            NFC Logger - Performing PACE : Security Protocol ID: " + pACEInfo.getProtocolOIDString() + "\n            NFC Logger - Performing PACE : Security information parameter ID: " + PACEInfo.toStandardizedParamIdString(parameterId) + "\n            ", new Object[0]);
        passportService.doPACE(paceKeySpec, objectIdentifier, parameterSpec, parameterId);
        return true;
    }

    private final byte[] getAaResponse(PassportService passportService, byte[] aaChallenge, Map<Short, byte[]> dataGroupValues) {
        byte[] bArr = dataGroupValues.get(Short.valueOf(PassportService.EF_DG15));
        if (bArr != null) {
            return this.jmrtdHelper.doActiveAuth(passportService, bArr, aaChallenge);
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0186  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0159 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0116 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final com.onfido.android.sdk.capture.internal.nfc.PassportAuthAccess getAuthAccess(org.jmrtd.PassportService r26, com.onfido.android.sdk.capture.internal.nfc.data.PassportBACKey r27, java.lang.Number r28, boolean r29) throws java.security.GeneralSecurityException, net.sf.scuba.smartcards.CardServiceException {
        /*
            Method dump skipped, instructions count: 462
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.internal.nfc.JMRTDPassportNfcReaderStateful.getAuthAccess(org.jmrtd.PassportService, com.onfido.android.sdk.capture.internal.nfc.data.PassportBACKey, java.lang.Number, boolean):com.onfido.android.sdk.capture.internal.nfc.PassportAuthAccess");
    }

    private final NfcPassportExtraction getNfcPassportExtraction(byte[] aaResponse, Map<Short, byte[]> dataGroupValues) {
        byte[] bArr = dataGroupValues.get(Short.valueOf(PassportService.EF_DG1));
        if (bArr == null) {
            throw new IllegalArgumentException("DG1 Failed".toString());
        }
        byte[] bArr2 = bArr;
        byte[] bArr3 = dataGroupValues.get(Short.valueOf(PassportService.EF_DG2));
        if (bArr3 == null) {
            throw new IllegalArgumentException("DG2 Failed".toString());
        }
        byte[] bArr4 = bArr3;
        byte[] bArr5 = dataGroupValues.get(Short.valueOf(PassportService.EF_DG3));
        byte[] bArr6 = dataGroupValues.get(Short.valueOf(PassportService.EF_DG4));
        byte[] bArr7 = dataGroupValues.get(Short.valueOf(PassportService.EF_DG5));
        byte[] bArr8 = dataGroupValues.get(Short.valueOf(PassportService.EF_DG6));
        byte[] bArr9 = dataGroupValues.get(Short.valueOf(PassportService.EF_DG7));
        byte[] bArr10 = dataGroupValues.get(Short.valueOf(PassportService.EF_DG8));
        byte[] bArr11 = dataGroupValues.get(Short.valueOf(PassportService.EF_DG9));
        byte[] bArr12 = dataGroupValues.get(Short.valueOf(PassportService.EF_DG10));
        byte[] bArr13 = dataGroupValues.get(Short.valueOf(PassportService.EF_DG11));
        byte[] bArr14 = dataGroupValues.get(Short.valueOf(PassportService.EF_DG12));
        byte[] bArr15 = dataGroupValues.get(Short.valueOf(PassportService.EF_DG13));
        byte[] bArr16 = dataGroupValues.get(Short.valueOf(PassportService.EF_DG14));
        byte[] bArr17 = dataGroupValues.get(Short.valueOf(PassportService.EF_DG15));
        byte[] bArr18 = dataGroupValues.get(Short.valueOf(PassportService.EF_DG16));
        byte[] bArr19 = dataGroupValues.get((short) 285);
        if (bArr19 != null) {
            return new NfcPassportExtraction(bArr2, bArr4, bArr5, bArr6, bArr7, bArr8, bArr9, bArr10, bArr11, bArr12, bArr13, bArr14, bArr15, bArr16, bArr17, bArr18, bArr19, aaResponse, null);
        }
        throw new IllegalArgumentException("SOD RAW Bytes Failed".toString());
    }

    private final ArrayDeque<Short> mapDataGroupsToBeRead(MRTDDataGroup[] fileIDs) {
        ArrayList arrayList = new ArrayList();
        for (MRTDDataGroup mRTDDataGroup : fileIDs) {
            Short sh = this.nfcFileIDToShort.get(mRTDDataGroup);
            if (sh != null) {
                arrayList.add(sh);
            }
        }
        return new ArrayDeque<>(arrayList);
    }

    private final List<SecurityInfo> orderAndFilterSecurityInfos(Collection<? extends SecurityInfo> securityInfos) {
        ArrayList arrayList = new ArrayList();
        for (Object obj : securityInfos) {
            if (obj instanceof PACEInfo) {
                arrayList.add(obj);
            }
        }
        return CollectionsKt.sortedWith(arrayList, new Comparator() { // from class: com.onfido.android.sdk.capture.internal.nfc.JMRTDPassportNfcReaderStateful$orderAndFilterSecurityInfos$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                PACEInfo.MappingType mappingType = PACEInfo.toMappingType(((PACEInfo) t).getObjectIdentifier());
                PACEInfo.MappingType mappingType2 = PACEInfo.MappingType.IM;
                return ComparisonsKt.compareValues(Integer.valueOf(mappingType == mappingType2 ? 1 : 2), Integer.valueOf(PACEInfo.toMappingType(((PACEInfo) t2).getObjectIdentifier()) != mappingType2 ? 2 : 1));
            }
        });
    }

    private final void readDataGroups(PassportService passportService, ObservableEmitter<PassportNfcExtractionState> emitter, ArrayDeque<Short> dataGroups, Map<Short, byte[]> dataGroupValues) throws Exception {
        short sShortValue;
        Exception e;
        byte[] bytes;
        while ((!dataGroups.isEmpty()) && !emitter.isDisposed()) {
            try {
                sShortValue = dataGroups.first().shortValue();
            } catch (Exception e2) {
                sShortValue = 0;
                e = e2;
            }
            try {
                ReadingStep readingStep = toReadingStep(sShortValue);
                Timber.INSTANCE.i("NFC Logger - readNfcDataGroup - " + readingStep, new Object[0]);
                bytes = this.jmrtdHelper.readBytes(passportService, sShortValue);
                dataGroups.removeFirst();
                updateStep(emitter, readingStep);
            } catch (Exception e3) {
                e = e3;
                if (passportService.isConnectionLost(e)) {
                    throw new ConnectionLostException();
                }
                if (e instanceof CardServiceException) {
                    Timber.INSTANCE.w("NFC Logger - " + ((int) sShortValue) + " card service exception", e);
                } else {
                    if (!(e instanceof IOException) && !(e instanceof InvocationTargetException)) {
                        throw e;
                    }
                    Timber.INSTANCE.w("NFC Logger - " + ((int) sShortValue) + " file exception", e);
                }
                dataGroups.removeFirst();
                bytes = null;
                dataGroupValues.put(Short.valueOf(sShortValue), bytes);
            }
            dataGroupValues.put(Short.valueOf(sShortValue), bytes);
        }
    }

    private final NfcPassportExtraction readNfcInfo(PassportService passportService, byte[] aaChallenge, ObservableEmitter<PassportNfcExtractionState> emitter, ArrayDeque<Short> dataGroups, Map<Short, byte[]> dataGroupValues) throws Exception {
        readDataGroups(passportService, emitter, dataGroups, dataGroupValues);
        byte[] aaResponse = getAaResponse(passportService, aaChallenge, dataGroupValues);
        updateStep(emitter, ReadingStep.DoActiveAuth);
        try {
            return getNfcPassportExtraction(aaResponse, dataGroupValues);
        } catch (IllegalArgumentException e) {
            Timber.INSTANCE.e("NFC Logger - " + e.getMessage(), new Object[0]);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Observable<PassportNfcExtractionState> readNfcTagInternal(final PassportBACKey passportBACKey, final byte[] aaChallenge, final Tag tag, final Number canNumber, final ArrayDeque<Short> dataGroups, final Map<Short, byte[]> dataGroupValues, final boolean tryPace) {
        Observable<PassportNfcExtractionState> observableCreate = Observable.create(new ObservableOnSubscribe() { // from class: com.onfido.android.sdk.capture.internal.nfc.JMRTDPassportNfcReaderStateful$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) throws Throwable {
                JMRTDPassportNfcReaderStateful.readNfcTagInternal$lambda$3(this.f$0, tag, passportBACKey, canNumber, tryPace, dataGroups, aaChallenge, dataGroupValues, observableEmitter);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableCreate, "create(...)");
        return observableCreate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:35:0x013b  */
    /* JADX WARN: Type inference failed for: r4v11, types: [org.jmrtd.PassportService] */
    /* JADX WARN: Type inference failed for: r4v16 */
    /* JADX WARN: Type inference failed for: r4v6, types: [java.security.Provider, org.spongycastle.jce.provider.BouncyCastleProvider] */
    /* JADX WARN: Type inference failed for: r4v7 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void readNfcTagInternal$lambda$3(com.onfido.android.sdk.capture.internal.nfc.JMRTDPassportNfcReaderStateful r27, android.nfc.Tag r28, com.onfido.android.sdk.capture.internal.nfc.data.PassportBACKey r29, java.lang.Number r30, boolean r31, kotlin.collections.ArrayDeque r32, byte[] r33, java.util.Map r34, io.reactivex.rxjava3.core.ObservableEmitter r35) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 671
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.internal.nfc.JMRTDPassportNfcReaderStateful.readNfcTagInternal$lambda$3(com.onfido.android.sdk.capture.internal.nfc.JMRTDPassportNfcReaderStateful, android.nfc.Tag, com.onfido.android.sdk.capture.internal.nfc.data.PassportBACKey, java.lang.Number, boolean, kotlin.collections.ArrayDeque, byte[], java.util.Map, io.reactivex.rxjava3.core.ObservableEmitter):void");
    }

    private final ReadingStep toReadingStep(short s) {
        if (s == 257) {
            return ReadingStep.DG1Read;
        }
        if (s == 258) {
            return ReadingStep.DG2Read;
        }
        if (s == 259) {
            return ReadingStep.DG3Read;
        }
        if (s == 260) {
            return ReadingStep.DG4Read;
        }
        if (s == 261) {
            return ReadingStep.DG5Read;
        }
        if (s == 262) {
            return ReadingStep.DG6Read;
        }
        if (s == 263) {
            return ReadingStep.DG7Read;
        }
        if (s == 264) {
            return ReadingStep.DG8Read;
        }
        if (s == 265) {
            return ReadingStep.DG9Read;
        }
        if (s == 266) {
            return ReadingStep.DG10Read;
        }
        if (s == 267) {
            return ReadingStep.DG11Read;
        }
        if (s == 268) {
            return ReadingStep.DG12Read;
        }
        if (s == 269) {
            return ReadingStep.DG13Read;
        }
        if (s == 270) {
            return ReadingStep.DG14Read;
        }
        if (s == 271) {
            return ReadingStep.DG15Read;
        }
        if (s == 272) {
            return ReadingStep.DG16Read;
        }
        if (s == 285) {
            return ReadingStep.SODRead;
        }
        throw new IllegalArgumentException(((int) s) + " data group can not be mapped to ReadingStep");
    }

    private final void updateStep(ObservableEmitter<PassportNfcExtractionState> observableEmitter, ReadingStep readingStep) {
        if (observableEmitter.isDisposed()) {
            return;
        }
        List<? extends ReadingStep> list = this.allReadingSteps;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("allReadingSteps");
            list = null;
        }
        observableEmitter.onNext(new PassportNfcExtractionState.Reading(readingStep.progressPercentage(list)));
    }

    @Override // com.onfido.android.sdk.capture.internal.nfc.PassportNfcReader
    public Observable<PassportNfcExtractionState> readNfcTag(Observable<Tag> nfcTagRetries, final PassportBACKey passportBACKey, final byte[] aaChallenge, Tag tag, MRTDDataGroup[] fileIDs, final Number canNumber, final boolean isPaceEnabled, NfcFlowType nfcFlowType, boolean chipAuthentication, RealtimeNfcEvents realtimeNfcEvents) {
        Intrinsics.checkNotNullParameter(nfcTagRetries, "nfcTagRetries");
        Intrinsics.checkNotNullParameter(passportBACKey, "passportBACKey");
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(fileIDs, "fileIDs");
        Intrinsics.checkNotNullParameter(nfcFlowType, "nfcFlowType");
        final Ref.IntRef intRef = new Ref.IntRef();
        final ArrayDeque<Short> arrayDequeMapDataGroupsToBeRead = mapDataGroupsToBeRead(fileIDs);
        final LinkedHashMap linkedHashMap = new LinkedHashMap();
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        listCreateListBuilder.add(ReadingStep.Authenticated);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayDequeMapDataGroupsToBeRead, 10));
        Iterator<Short> it = arrayDequeMapDataGroupsToBeRead.iterator();
        while (it.hasNext()) {
            arrayList.add(toReadingStep(it.next().shortValue()));
        }
        listCreateListBuilder.addAll(arrayList);
        listCreateListBuilder.add(ReadingStep.DoActiveAuth);
        this.allReadingSteps = CollectionsKt.build(listCreateListBuilder);
        Observable<PassportNfcExtractionState> observableTakeUntil = Observable.merge(nfcTagRetries, Observable.just(tag)).switchMap(new Function() { // from class: com.onfido.android.sdk.capture.internal.nfc.JMRTDPassportNfcReaderStateful.readNfcTag.2
            @Override // io.reactivex.rxjava3.functions.Function
            public final ObservableSource<? extends PassportNfcExtractionState> apply(Tag it2) {
                Intrinsics.checkNotNullParameter(it2, "it");
                return JMRTDPassportNfcReaderStateful.this.readNfcTagInternal(passportBACKey, aaChallenge, it2, canNumber, arrayDequeMapDataGroupsToBeRead, linkedHashMap, isPaceEnabled).startWithItem(new PassportNfcExtractionState.Reading(intRef.element)).timeout(60000L, TimeUnit.MILLISECONDS);
            }
        }).doOnNext(new Consumer() { // from class: com.onfido.android.sdk.capture.internal.nfc.JMRTDPassportNfcReaderStateful.readNfcTag.3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(PassportNfcExtractionState state) {
                Intrinsics.checkNotNullParameter(state, "state");
                if (state instanceof PassportNfcExtractionState.Reading) {
                    intRef.element = ((PassportNfcExtractionState.Reading) state).getProgress();
                }
            }
        }).takeUntil((Predicate) new Predicate() { // from class: com.onfido.android.sdk.capture.internal.nfc.JMRTDPassportNfcReaderStateful.readNfcTag.4
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(PassportNfcExtractionState it2) {
                Intrinsics.checkNotNullParameter(it2, "it");
                return (it2 instanceof PassportNfcExtractionState.Success) || (it2 instanceof PassportNfcExtractionState.Failed);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableTakeUntil, "takeUntil(...)");
        return observableTakeUntil;
    }
}
