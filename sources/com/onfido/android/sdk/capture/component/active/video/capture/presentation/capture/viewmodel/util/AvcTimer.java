package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.util;

import com.facebook.react.uimanager.ViewProps;
import com.oblador.keychain.KeychainModule;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.utils.RxExtensionsKt;
import com.onfido.javax.inject.Inject;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0007\u001a\u00020\bJ\u001c\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\b0\rR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/util/AvcTimer;", "", "schedulersProvider", "Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;", "(Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;)V", "compositeDisposable", "Lio/reactivex/rxjava3/disposables/CompositeDisposable;", KeychainModule.AuthPromptOptions.CANCEL, "", ViewProps.START, "milliseconds", "", "callback", "Lkotlin/Function0;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class AvcTimer {
    private final CompositeDisposable compositeDisposable;
    private final SchedulersProvider schedulersProvider;

    @Inject
    public AvcTimer(SchedulersProvider schedulersProvider) {
        Intrinsics.checkNotNullParameter(schedulersProvider, "schedulersProvider");
        this.schedulersProvider = schedulersProvider;
        this.compositeDisposable = new CompositeDisposable();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void start$lambda$0(Function0 tmp0) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke();
    }

    public final void cancel() {
        this.compositeDisposable.clear();
    }

    public final void start(long milliseconds, final Function0<Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        CompositeDisposable compositeDisposable = this.compositeDisposable;
        Disposable disposableSubscribe = Completable.timer(milliseconds, TimeUnit.MILLISECONDS, this.schedulersProvider.getTimer()).observeOn(this.schedulersProvider.getUi()).subscribe(new Action() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.util.AvcTimer$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                AvcTimer.start$lambda$0(callback);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeDisposable, disposableSubscribe);
    }
}
