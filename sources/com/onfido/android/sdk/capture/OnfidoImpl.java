package com.onfido.android.sdk.capture;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import androidx.fragment.app.Fragment;
import com.onfido.android.sdk.capture.Onfido;
import com.onfido.android.sdk.capture.errors.MissingDependenciesException;
import com.onfido.android.sdk.capture.errors.OnfidoException;
import com.onfido.android.sdk.capture.internal.OnfidoConstants;
import com.onfido.android.sdk.capture.internal.usecase.HasNfcDependenciesUseCase;
import com.onfido.android.sdk.capture.model.NFCOptionsKt;
import com.onfido.android.sdk.capture.nfc.MRTDAccessControl;
import com.onfido.android.sdk.capture.nfc.MRTDDataGroup;
import com.onfido.android.sdk.capture.nfc.MRTDDocumentInfo;
import com.onfido.android.sdk.capture.nfc.MRTDReaderDelegate;
import com.onfido.android.sdk.capture.nfc.NfcSession;
import com.onfido.android.sdk.capture.upload.Captures;
import io.sentry.protocol.Request;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: OnfidoImpl.kt */
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0003J\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\bH\u0016J\"\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J \u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\bH\u0016J \u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\bH\u0016JQ\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u000e\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020 \u0018\u00010\u001f2\b\u0010!\u001a\u0004\u0018\u00010\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0016¢\u0006\u0002\u0010'J\u0010\u0010(\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lcom/onfido/android/sdk/capture/OnfidoImpl;", "Lcom/onfido/android/sdk/capture/Onfido;", "appContext", "Landroid/content/Context;", "(Landroid/content/Context;)V", "checkDependencies", "", "config", "Lcom/onfido/android/sdk/capture/OnfidoConfig;", "intent", "Landroid/content/Intent;", "createIntent", "handleActivityResult", "resultCode", "", "callback", "Lcom/onfido/android/sdk/capture/Onfido$OnfidoResultListener;", "startActivityForResult", "activity", "Landroid/app/Activity;", "requestCode", "onfidoConfig", Request.JsonKeys.FRAGMENT, "Landroidx/fragment/app/Fragment;", "startStandaloneNFC", "Lcom/onfido/android/sdk/capture/nfc/NfcSession;", "documentInfo", "Lcom/onfido/android/sdk/capture/nfc/MRTDDocumentInfo;", "preferredAccessControl", "Lcom/onfido/android/sdk/capture/nfc/MRTDAccessControl;", "dataGroups", "", "Lcom/onfido/android/sdk/capture/nfc/MRTDDataGroup;", "activeAuthenticationChallenge", "", "chipAuthentication", "", "delegate", "Lcom/onfido/android/sdk/capture/nfc/MRTDReaderDelegate;", "(Landroid/app/Activity;Lcom/onfido/android/sdk/capture/nfc/MRTDDocumentInfo;Lcom/onfido/android/sdk/capture/nfc/MRTDAccessControl;[Lcom/onfido/android/sdk/capture/nfc/MRTDDataGroup;[BZLcom/onfido/android/sdk/capture/nfc/MRTDReaderDelegate;)Lcom/onfido/android/sdk/capture/nfc/NfcSession;", "validateNfcDependencies", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class OnfidoImpl implements Onfido {
    private final Context appContext;

    public OnfidoImpl(Context appContext) {
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        Context applicationContext = appContext.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        this.appContext = applicationContext;
    }

    @Override // com.onfido.android.sdk.capture.Onfido
    public Intent createIntent(OnfidoConfig config) {
        Intrinsics.checkNotNullParameter(config, "config");
        Intent intent = new Intent();
        intent.setClassName(this.appContext, "com.onfido.android.sdk.capture.ui.OnfidoSplashActivity");
        intent.putExtra(OnfidoConstants.ONFIDO_CONFIG, config);
        intent.putExtra(OnfidoConstants.ONFIDO_SESSION_ID, System.currentTimeMillis());
        checkDependencies(config, intent);
        return intent;
    }

    @Override // com.onfido.android.sdk.capture.Onfido
    public void startActivityForResult(Activity activity, int requestCode, OnfidoConfig onfidoConfig) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(onfidoConfig, "onfidoConfig");
        activity.startActivityForResult(createIntent(onfidoConfig), requestCode);
    }

    @Override // com.onfido.android.sdk.capture.Onfido
    public void startActivityForResult(Fragment fragment, int requestCode, OnfidoConfig onfidoConfig) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        Intrinsics.checkNotNullParameter(onfidoConfig, "onfidoConfig");
        fragment.startActivityForResult(createIntent(onfidoConfig), requestCode);
    }

    @Override // com.onfido.android.sdk.capture.Onfido
    public NfcSession startStandaloneNFC(Activity activity, MRTDDocumentInfo documentInfo, MRTDAccessControl preferredAccessControl, MRTDDataGroup[] dataGroups, byte[] activeAuthenticationChallenge, boolean chipAuthentication, MRTDReaderDelegate delegate) throws IllegalAccessException, OnfidoException, InstantiationException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(documentInfo, "documentInfo");
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        try {
            if (!HasNfcDependenciesUseCase.INSTANCE.invoke()) {
                throw new MissingDependenciesException("NFC related dependencies are missing, please follow the integration documentation");
            }
            final Class<?> cls = Class.forName("com.onfido.android.sdk.capture.internal.nfc.NfcService");
            final Object objNewInstance = cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            cls.getMethod("read", Activity.class, MRTDDocumentInfo.class, MRTDAccessControl.class, MRTDDataGroup[].class, byte[].class, Boolean.TYPE, MRTDReaderDelegate.class).invoke(objNewInstance, activity, documentInfo, preferredAccessControl, dataGroups, activeAuthenticationChallenge, Boolean.valueOf(chipAuthentication), delegate);
            return new NfcSession() { // from class: com.onfido.android.sdk.capture.OnfidoImpl.startStandaloneNFC.1
                @Override // com.onfido.android.sdk.capture.nfc.NfcSession
                public void cancelSession(Activity activity2) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                    Intrinsics.checkNotNullParameter(activity2, "activity");
                    cls.getMethod("cancelSession", Activity.class).invoke(objNewInstance, activity2);
                }
            };
        } catch (Exception e) {
            throw new OnfidoException("Error: " + e.getMessage());
        }
    }

    @Override // com.onfido.android.sdk.capture.Onfido
    public void handleActivityResult(final int resultCode, final Intent intent, final Onfido.OnfidoResultListener callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        Function0<Unit> function0 = new Function0<Unit>() { // from class: com.onfido.android.sdk.capture.OnfidoImpl$handleActivityResult$corruptedResultCallback$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                String string;
                Onfido.OnfidoResultListener onfidoResultListener = callback;
                Intent intent2 = intent;
                if (intent2 == null || (string = intent2.toString()) == null) {
                    string = "null";
                }
                onfidoResultListener.onError(new OnfidoException("Unexpected result Intent. It might be a result of incorrect integration, make sure you only pass Onfido intent to handleActivityResult. It might be due to unpredictable crash or error. Please report the problem to support@onfido.com. Intent: " + string + " \n resultCode: " + resultCode));
            }
        };
        if (resultCode == -2) {
            Serializable serializableExtra = intent != null ? intent.getSerializableExtra(OnfidoConstants.ONFIDO_EXCEPTION_RESULT) : null;
            OnfidoException onfidoException = serializableExtra instanceof OnfidoException ? (OnfidoException) serializableExtra : null;
            if (onfidoException != null) {
                callback.onError(onfidoException);
                return;
            } else {
                function0.invoke();
                return;
            }
        }
        if (resultCode == -1) {
            Serializable serializableExtra2 = intent != null ? intent.getSerializableExtra("onfido_upload_result") : null;
            Captures captures = serializableExtra2 instanceof Captures ? (Captures) serializableExtra2 : null;
            if (captures != null) {
                callback.userCompleted(captures);
                return;
            } else {
                function0.invoke();
                return;
            }
        }
        if (resultCode != 0) {
            return;
        }
        Serializable serializableExtra3 = intent != null ? intent.getSerializableExtra(OnfidoConstants.ONFIDO_EXIT_CODE) : null;
        ExitCode exitCode = serializableExtra3 instanceof ExitCode ? (ExitCode) serializableExtra3 : null;
        if (exitCode == null) {
            exitCode = ExitCode.USER_LEFT_ACTIVITY;
        }
        callback.userExited(exitCode);
    }

    private final void checkDependencies(OnfidoConfig config, Intent intent) {
        validateNfcDependencies(config);
        if (intent.resolveActivity(this.appContext.getPackageManager()) == null) {
            throw new MissingDependenciesException("Onfido sdk library is missing, please add it as a dependency and then re-launch the sdk");
        }
    }

    private final void validateNfcDependencies(OnfidoConfig config) {
        if (NFCOptionsKt.isEnabled(config.getNfcOptions()) && !HasNfcDependenciesUseCase.INSTANCE.invoke()) {
            throw new MissingDependenciesException("NFC related dependencies are missing, please follow the integration documentation");
        }
    }
}
