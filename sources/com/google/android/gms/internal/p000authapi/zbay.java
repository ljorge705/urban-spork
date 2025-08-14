package com.google.android.gms.internal.p000authapi;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.BeginSignInResult;
import com.google.android.gms.auth.api.identity.GetPhoneNumberHintIntentRequest;
import com.google.android.gms.auth.api.identity.GetSignInIntentRequest;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.auth.api.identity.zbp;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-auth@@20.5.0 */
/* loaded from: classes3.dex */
public final class zbay extends GoogleApi implements SignInClient {
    private static final Api.ClientKey zba;
    private static final Api.AbstractClientBuilder zbb;
    private static final Api zbc;
    private final String zbd;

    static {
        Api.ClientKey clientKey = new Api.ClientKey();
        zba = clientKey;
        zbat zbatVar = new zbat();
        zbb = zbatVar;
        zbc = new Api("Auth.Api.Identity.SignIn.API", zbatVar, clientKey);
    }

    public zbay(Activity activity, zbp zbpVar) {
        super(activity, (Api<zbp>) zbc, zbpVar, GoogleApi.Settings.DEFAULT_SETTINGS);
        this.zbd = zbbb.zba();
    }

    @Override // com.google.android.gms.auth.api.identity.SignInClient
    public final Task<BeginSignInResult> beginSignIn(BeginSignInRequest beginSignInRequest) {
        Preconditions.checkNotNull(beginSignInRequest);
        BeginSignInRequest.Builder builderZba = BeginSignInRequest.zba(beginSignInRequest);
        builderZba.zba(this.zbd);
        final BeginSignInRequest beginSignInRequestBuild = builderZba.build();
        return doRead(TaskApiCall.builder().setFeatures(zbba.zba).run(new RemoteCall() { // from class: com.google.android.gms.internal.auth-api.zbap
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) throws RemoteException {
                zbay zbayVar = this.zba;
                BeginSignInRequest beginSignInRequest2 = beginSignInRequestBuild;
                ((zbai) ((zbaz) obj).getService()).zbc(new zbau(zbayVar, (TaskCompletionSource) obj2), (BeginSignInRequest) Preconditions.checkNotNull(beginSignInRequest2));
            }
        }).setAutoResolveMissingFeatures(false).setMethodKey(1553).build());
    }

    @Override // com.google.android.gms.auth.api.identity.SignInClient
    public final Task<PendingIntent> getPhoneNumberHintIntent(final GetPhoneNumberHintIntentRequest getPhoneNumberHintIntentRequest) {
        Preconditions.checkNotNull(getPhoneNumberHintIntentRequest);
        return doRead(TaskApiCall.builder().setFeatures(zbba.zbh).run(new RemoteCall() { // from class: com.google.android.gms.internal.auth-api.zbas
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) throws RemoteException {
                this.zba.zba(getPhoneNumberHintIntentRequest, (zbaz) obj, (TaskCompletionSource) obj2);
            }
        }).setMethodKey(1653).build());
    }

    @Override // com.google.android.gms.auth.api.identity.SignInClient
    public final Task<PendingIntent> getSignInIntent(GetSignInIntentRequest getSignInIntentRequest) {
        Preconditions.checkNotNull(getSignInIntentRequest);
        GetSignInIntentRequest.Builder builderZba = GetSignInIntentRequest.zba(getSignInIntentRequest);
        builderZba.zba(this.zbd);
        final GetSignInIntentRequest getSignInIntentRequestBuild = builderZba.build();
        return doRead(TaskApiCall.builder().setFeatures(zbba.zbf).run(new RemoteCall() { // from class: com.google.android.gms.internal.auth-api.zbaq
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) throws RemoteException {
                zbay zbayVar = this.zba;
                GetSignInIntentRequest getSignInIntentRequest2 = getSignInIntentRequestBuild;
                ((zbai) ((zbaz) obj).getService()).zbe(new zbaw(zbayVar, (TaskCompletionSource) obj2), (GetSignInIntentRequest) Preconditions.checkNotNull(getSignInIntentRequest2));
            }
        }).setMethodKey(1555).build());
    }

    @Override // com.google.android.gms.auth.api.identity.SignInClient
    public final Task<Void> signOut() {
        getApplicationContext().getSharedPreferences("com.google.android.gms.signin", 0).edit().clear().apply();
        Iterator<GoogleApiClient> it = GoogleApiClient.getAllClients().iterator();
        while (it.hasNext()) {
            it.next().maybeSignOut();
        }
        GoogleApiManager.reportSignOut();
        return doWrite(TaskApiCall.builder().setFeatures(zbba.zbb).run(new RemoteCall() { // from class: com.google.android.gms.internal.auth-api.zbar
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) throws RemoteException {
                this.zba.zbb((zbaz) obj, (TaskCompletionSource) obj2);
            }
        }).setAutoResolveMissingFeatures(false).setMethodKey(1554).build());
    }

    /* JADX WARN: Multi-variable type inference failed */
    final /* synthetic */ void zba(GetPhoneNumberHintIntentRequest getPhoneNumberHintIntentRequest, zbaz zbazVar, TaskCompletionSource taskCompletionSource) throws RemoteException {
        ((zbai) zbazVar.getService()).zbd(new zbax(this, taskCompletionSource), getPhoneNumberHintIntentRequest, this.zbd);
    }

    /* JADX WARN: Multi-variable type inference failed */
    final /* synthetic */ void zbb(zbaz zbazVar, TaskCompletionSource taskCompletionSource) throws RemoteException {
        ((zbai) zbazVar.getService()).zbf(new zbav(this, taskCompletionSource), this.zbd);
    }

    @Override // com.google.android.gms.auth.api.identity.SignInClient
    public final String getPhoneNumberFromIntent(Intent intent) throws ApiException {
        if (intent == null) {
            throw new ApiException(Status.RESULT_INTERNAL_ERROR);
        }
        Status status = (Status) SafeParcelableSerializer.deserializeFromIntentExtra(intent, "status", Status.CREATOR);
        if (status == null) {
            throw new ApiException(Status.RESULT_CANCELED);
        }
        if (!status.isSuccess()) {
            throw new ApiException(status);
        }
        String stringExtra = intent.getStringExtra("phone_number_hint_result");
        if (stringExtra != null) {
            return stringExtra;
        }
        throw new ApiException(Status.RESULT_INTERNAL_ERROR);
    }

    @Override // com.google.android.gms.auth.api.identity.SignInClient
    public final SignInCredential getSignInCredentialFromIntent(Intent intent) throws ApiException {
        if (intent == null) {
            throw new ApiException(Status.RESULT_INTERNAL_ERROR);
        }
        Status status = (Status) SafeParcelableSerializer.deserializeFromIntentExtra(intent, "status", Status.CREATOR);
        if (status == null) {
            throw new ApiException(Status.RESULT_CANCELED);
        }
        if (!status.isSuccess()) {
            throw new ApiException(status);
        }
        SignInCredential signInCredential = (SignInCredential) SafeParcelableSerializer.deserializeFromIntentExtra(intent, "sign_in_credential", SignInCredential.CREATOR);
        if (signInCredential != null) {
            return signInCredential;
        }
        throw new ApiException(Status.RESULT_INTERNAL_ERROR);
    }

    public zbay(Context context, zbp zbpVar) {
        super(context, (Api<zbp>) zbc, zbpVar, GoogleApi.Settings.DEFAULT_SETTINGS);
        this.zbd = zbbb.zba();
    }
}
