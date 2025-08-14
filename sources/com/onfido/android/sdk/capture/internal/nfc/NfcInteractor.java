package com.onfido.android.sdk.capture.internal.nfc;

import android.app.Activity;
import android.content.Intent;
import androidx.activity.result.ActivityResultLauncher;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.NfcFlowType;
import com.onfido.android.sdk.capture.internal.nfc.data.PassportBACKey;
import com.onfido.android.sdk.capture.nfc.MRTDDataGroup;
import io.reactivex.rxjava3.core.Observable;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0004\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J$\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00030\bH&J\b\u0010\n\u001a\u00020\u000bH&J\b\u0010\f\u001a\u00020\u000bH&J{\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\t0\u000e2\u0006\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u000b2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010 H&¢\u0006\u0002\u0010!J\u0016\u0010\"\u001a\u00020\u00032\f\u0010#\u001a\b\u0012\u0004\u0012\u00020%0$H&¨\u0006&"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/nfc/NfcInteractor;", "", "disableReaderMode", "", "activity", "Landroid/app/Activity;", "enableReaderMode", "tagReadCallback", "Lkotlin/Function1;", "Lcom/onfido/android/sdk/capture/internal/nfc/NfcTagDelegate;", "isNfcEnabled", "", "isNfcSupported", "readNfcTag", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/onfido/android/sdk/capture/internal/nfc/NfcReadState;", "nfcTagRetries", "tag", "passportBACKey", "Lcom/onfido/android/sdk/capture/internal/nfc/data/PassportBACKey;", "aaChallenge", "", "fileIDs", "", "Lcom/onfido/android/sdk/capture/nfc/MRTDDataGroup;", "canNumber", "", "isPaceEnabled", "nfcFlowType", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/NfcFlowType;", "chipAuthentication", "realtimeNfcEvents", "Lcom/onfido/android/sdk/capture/internal/nfc/RealtimeNfcEvents;", "(Lio/reactivex/rxjava3/core/Observable;Lcom/onfido/android/sdk/capture/internal/nfc/NfcTagDelegate;Lcom/onfido/android/sdk/capture/internal/nfc/data/PassportBACKey;[B[Lcom/onfido/android/sdk/capture/nfc/MRTDDataGroup;Ljava/lang/Number;ZLcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/NfcFlowType;ZLcom/onfido/android/sdk/capture/internal/nfc/RealtimeNfcEvents;)Lio/reactivex/rxjava3/core/Observable;", "redirectToNfcSetting", "launcher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface NfcInteractor {

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ Observable readNfcTag$default(NfcInteractor nfcInteractor, Observable observable, NfcTagDelegate nfcTagDelegate, PassportBACKey passportBACKey, byte[] bArr, MRTDDataGroup[] mRTDDataGroupArr, Number number, boolean z, NfcFlowType nfcFlowType, boolean z2, RealtimeNfcEvents realtimeNfcEvents, int i, Object obj) {
            if (obj == null) {
                return nfcInteractor.readNfcTag(observable, nfcTagDelegate, passportBACKey, bArr, mRTDDataGroupArr, (i & 32) != 0 ? null : number, z, nfcFlowType, (i & 256) != 0 ? false : z2, (i & 512) != 0 ? null : realtimeNfcEvents);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: readNfcTag");
        }
    }

    void disableReaderMode(Activity activity);

    void enableReaderMode(Activity activity, Function1<? super NfcTagDelegate, Unit> tagReadCallback);

    boolean isNfcEnabled();

    boolean isNfcSupported();

    Observable<NfcReadState> readNfcTag(Observable<NfcTagDelegate> nfcTagRetries, NfcTagDelegate tag, PassportBACKey passportBACKey, byte[] aaChallenge, MRTDDataGroup[] fileIDs, Number canNumber, boolean isPaceEnabled, NfcFlowType nfcFlowType, boolean chipAuthentication, RealtimeNfcEvents realtimeNfcEvents);

    void redirectToNfcSetting(ActivityResultLauncher<Intent> launcher);
}
