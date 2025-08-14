package com.onfido.android.sdk.capture.token;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import com.onfido.android.sdk.capture.errors.OnfidoException;
import com.onfido.android.sdk.capture.internal.token.OnfidoTokenProvider;
import com.onfido.api.client.exception.TokenExpiredException;
import com.onfido.javax.inject.Inject;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableEmitter;
import io.reactivex.rxjava3.core.CompletableOnSubscribe;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Function;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0019\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/onfido/android/sdk/capture/token/TokenExpirationServiceConnector;", "", "tokenProvider", "Lcom/onfido/android/sdk/capture/internal/token/OnfidoTokenProvider;", "applicationContext", "Landroid/content/Context;", "(Lcom/onfido/android/sdk/capture/internal/token/OnfidoTokenProvider;Landroid/content/Context;)V", "tokenExpirationHandlerServiceConnection", "Landroid/content/ServiceConnection;", "connect", "Lio/reactivex/rxjava3/core/Completable;", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class TokenExpirationServiceConnector {
    private static final Companion Companion = new Companion(null);
    private static final long TOKEN_EXPIRE_TIMEOUT_IN_SECONDS = 10;
    private final Context applicationContext;
    private ServiceConnection tokenExpirationHandlerServiceConnection;
    private final OnfidoTokenProvider tokenProvider;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/onfido/android/sdk/capture/token/TokenExpirationServiceConnector$Companion;", "", "()V", "TOKEN_EXPIRE_TIMEOUT_IN_SECONDS", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Inject
    public TokenExpirationServiceConnector(OnfidoTokenProvider tokenProvider, Context applicationContext) {
        Intrinsics.checkNotNullParameter(tokenProvider, "tokenProvider");
        Intrinsics.checkNotNullParameter(applicationContext, "applicationContext");
        this.tokenProvider = tokenProvider;
        this.applicationContext = applicationContext;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void connect$lambda$0(final TokenExpirationServiceConnector this$0, final CompletableEmitter emitter) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(emitter, "emitter");
        final Looper mainLooper = Looper.getMainLooper();
        Handler handler = new Handler(mainLooper) { // from class: com.onfido.android.sdk.capture.token.TokenExpirationServiceConnector$connect$1$handler$1
            @Override // android.os.Handler
            public void handleMessage(Message msg) throws OnfidoException {
                Intrinsics.checkNotNullParameter(msg, "msg");
                String string = msg.getData().getString("token");
                if (string == null) {
                    emitter.onError(new TokenExpiredException());
                } else {
                    this.this$0.tokenProvider.updateToken$onfido_capture_sdk_core_release(string);
                    emitter.onComplete();
                }
            }
        };
        Intent intent = new Intent(this$0.applicationContext, (Class<?>) TokenExpirationHandlerService.class);
        final Messenger messenger = new Messenger(handler);
        ServiceConnection serviceConnection = new ServiceConnection() { // from class: com.onfido.android.sdk.capture.token.TokenExpirationServiceConnector$connect$1$1
            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName name, IBinder service) throws RemoteException {
                Messenger messenger2 = new Messenger(service);
                Message message = new Message();
                message.replyTo = messenger;
                messenger2.send(message);
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName name) {
            }
        };
        this$0.tokenExpirationHandlerServiceConnection = serviceConnection;
        this$0.applicationContext.bindService(intent, serviceConnection, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void connect$lambda$1(TokenExpirationServiceConnector this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Context context = this$0.applicationContext;
        ServiceConnection serviceConnection = this$0.tokenExpirationHandlerServiceConnection;
        if (serviceConnection == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tokenExpirationHandlerServiceConnection");
            serviceConnection = null;
        }
        context.unbindService(serviceConnection);
    }

    public final Completable connect() {
        Completable completableDoFinally = Completable.create(new CompletableOnSubscribe() { // from class: com.onfido.android.sdk.capture.token.TokenExpirationServiceConnector$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.core.CompletableOnSubscribe
            public final void subscribe(CompletableEmitter completableEmitter) {
                TokenExpirationServiceConnector.connect$lambda$0(this.f$0, completableEmitter);
            }
        }).timeout(10L, TimeUnit.SECONDS).onErrorResumeNext(new Function() { // from class: com.onfido.android.sdk.capture.token.TokenExpirationServiceConnector.connect.2
            @Override // io.reactivex.rxjava3.functions.Function
            public final CompletableSource apply(Throwable it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Completable.error(new TokenExpiredException());
            }
        }).doFinally(new Action() { // from class: com.onfido.android.sdk.capture.token.TokenExpirationServiceConnector$$ExternalSyntheticLambda1
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                TokenExpirationServiceConnector.connect$lambda$1(this.f$0);
            }
        });
        Intrinsics.checkNotNullExpressionValue(completableDoFinally, "doFinally(...)");
        return completableDoFinally;
    }
}
