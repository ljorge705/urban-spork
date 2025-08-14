package com.onfido.workflow.internal.ui.processor.biometric.token;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.os.ResultReceiver;
import androidx.core.os.BundleKt;
import com.onfido.android.sdk.capture.biometricToken.BiometricTokenRetrievalService;
import com.onfido.android.sdk.capture.config.BiometricTokenCallbackResultReceiver;
import com.onfido.android.sdk.capture.errors.OnfidoException;
import com.onfido.javax.inject.Inject;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.functions.Action;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BiometricTokenExternalRepository.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u001d\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\t\u001a\u00020\nH\u0016J\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\rH\u0016J \u0010\u000f\u001a\u00020\u00102\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0002J\u0018\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\rH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/onfido/workflow/internal/ui/processor/biometric/token/BiometricTokenExternalRepository;", "Lcom/onfido/workflow/internal/ui/processor/biometric/token/BiometricTokenRepository;", "applicationContext", "Landroid/content/Context;", "biometricCallback", "Landroid/os/ResultReceiver;", "(Landroid/content/Context;Landroid/os/ResultReceiver;)V", "tokenRetrievalServiceConnection", "Landroid/content/ServiceConnection;", "getType", "Lcom/onfido/workflow/internal/ui/processor/biometric/token/BiometricTokenRepositoryType;", "retrieveToken", "Lio/reactivex/rxjava3/core/Observable;", "", "customerUserHash", "sendBiometricTokenRequest", "", "requestMessenger", "Landroid/os/Messenger;", "receiverMessenger", "storeToken", "Lio/reactivex/rxjava3/core/Completable;", "biometricToken", "Companion", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class BiometricTokenExternalRepository implements BiometricTokenRepository {
    private static final long BIOMETRIC_TOKEN_RETRIEVAL_TIMEOUT = 30;
    private final Context applicationContext;
    private final ResultReceiver biometricCallback;
    private ServiceConnection tokenRetrievalServiceConnection;

    @Inject
    public BiometricTokenExternalRepository(Context applicationContext, ResultReceiver resultReceiver) {
        Intrinsics.checkNotNullParameter(applicationContext, "applicationContext");
        this.applicationContext = applicationContext;
        this.biometricCallback = resultReceiver;
    }

    @Override // com.onfido.workflow.internal.ui.processor.biometric.token.BiometricTokenRepository
    public Completable storeToken(final String customerUserHash, final String biometricToken) {
        Intrinsics.checkNotNullParameter(customerUserHash, "customerUserHash");
        Intrinsics.checkNotNullParameter(biometricToken, "biometricToken");
        Completable completableFromAction = Completable.fromAction(new Action() { // from class: com.onfido.workflow.internal.ui.processor.biometric.token.BiometricTokenExternalRepository$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                BiometricTokenExternalRepository.storeToken$lambda$0(biometricToken, customerUserHash, this);
            }
        });
        Intrinsics.checkNotNullExpressionValue(completableFromAction, "fromAction(...)");
        return completableFromAction;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void storeToken$lambda$0(String biometricToken, String customerUserHash, BiometricTokenExternalRepository this$0) {
        Intrinsics.checkNotNullParameter(biometricToken, "$biometricToken");
        Intrinsics.checkNotNullParameter(customerUserHash, "$customerUserHash");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Bundle bundleBundleOf = BundleKt.bundleOf(TuplesKt.to(BiometricTokenCallbackResultReceiver.KEY_BIOMETRIC_TOKEN, biometricToken), TuplesKt.to("biometric_customer_user_hash", customerUserHash));
        ResultReceiver resultReceiver = this$0.biometricCallback;
        if (resultReceiver != null) {
            resultReceiver.send(-1, bundleBundleOf);
        }
    }

    @Override // com.onfido.workflow.internal.ui.processor.biometric.token.BiometricTokenRepository
    public Observable<String> retrieveToken(final String customerUserHash) {
        Intrinsics.checkNotNullParameter(customerUserHash, "customerUserHash");
        Observable<String> observableDoFinally = Observable.create(new ObservableOnSubscribe() { // from class: com.onfido.workflow.internal.ui.processor.biometric.token.BiometricTokenExternalRepository$$ExternalSyntheticLambda1
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                BiometricTokenExternalRepository.retrieveToken$lambda$1(this.f$0, customerUserHash, observableEmitter);
            }
        }).timeout(BIOMETRIC_TOKEN_RETRIEVAL_TIMEOUT, TimeUnit.SECONDS, Observable.error(new OnfidoException("Biometric token retrieval from external storage timed out"))).doFinally(new Action() { // from class: com.onfido.workflow.internal.ui.processor.biometric.token.BiometricTokenExternalRepository$$ExternalSyntheticLambda2
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                BiometricTokenExternalRepository.retrieveToken$lambda$2(this.f$0);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableDoFinally, "doFinally(...)");
        return observableDoFinally;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void retrieveToken$lambda$1(final BiometricTokenExternalRepository this$0, final String customerUserHash, final ObservableEmitter emitter) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(customerUserHash, "$customerUserHash");
        Intrinsics.checkNotNullParameter(emitter, "emitter");
        final Looper mainLooper = Looper.getMainLooper();
        final Messenger messenger = new Messenger(new Handler(mainLooper) { // from class: com.onfido.workflow.internal.ui.processor.biometric.token.BiometricTokenExternalRepository$retrieveToken$1$externalBiometricTokenHandler$1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                Intrinsics.checkNotNullParameter(msg, "msg");
                String string = msg.getData().getString(BiometricTokenRetrievalService.KEY_EXTERNAL_BIOMETRIC_TOKEN);
                if (string != null) {
                    emitter.onNext(string);
                    emitter.onComplete();
                } else {
                    emitter.onError(new NoSuchElementException("No biometric token found for the given user hash in external storage"));
                }
            }
        });
        this$0.tokenRetrievalServiceConnection = new ServiceConnection() { // from class: com.onfido.workflow.internal.ui.processor.biometric.token.BiometricTokenExternalRepository$retrieveToken$1$1
            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName name) {
            }

            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName name, IBinder service) throws RemoteException {
                this.this$0.sendBiometricTokenRequest(customerUserHash, new Messenger(service), messenger);
            }
        };
        Intent intent = new Intent(this$0.applicationContext, (Class<?>) BiometricTokenRetrievalService.class);
        Context context = this$0.applicationContext;
        ServiceConnection serviceConnection = this$0.tokenRetrievalServiceConnection;
        if (serviceConnection == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tokenRetrievalServiceConnection");
            serviceConnection = null;
        }
        context.bindService(intent, serviceConnection, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void retrieveToken$lambda$2(BiometricTokenExternalRepository this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Context context = this$0.applicationContext;
        ServiceConnection serviceConnection = this$0.tokenRetrievalServiceConnection;
        if (serviceConnection == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tokenRetrievalServiceConnection");
            serviceConnection = null;
        }
        context.unbindService(serviceConnection);
    }

    @Override // com.onfido.workflow.internal.ui.processor.biometric.token.BiometricTokenRepository
    public BiometricTokenRepositoryType getType() {
        return BiometricTokenRepositoryType.EXTERNAL;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendBiometricTokenRequest(String customerUserHash, Messenger requestMessenger, Messenger receiverMessenger) throws RemoteException {
        Message message = new Message();
        message.setData(BundleKt.bundleOf(TuplesKt.to("biometric_customer_user_hash", customerUserHash)));
        message.replyTo = receiverMessenger;
        requestMessenger.send(message);
    }
}
