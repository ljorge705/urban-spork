package com.clevertap.android.sdk;

import android.content.DialogInterface;
import com.clevertap.android.sdk.inapp.CTInAppNotificationButton;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes5.dex */
public final /* synthetic */ class InAppNotificationActivity$$ExternalSyntheticLambda2 implements DialogInterface.OnClickListener {
    public final /* synthetic */ InAppNotificationActivity f$0;
    public final /* synthetic */ CTInAppNotificationButton f$1;

    public /* synthetic */ InAppNotificationActivity$$ExternalSyntheticLambda2(InAppNotificationActivity inAppNotificationActivity, CTInAppNotificationButton cTInAppNotificationButton) {
        this.f$0 = inAppNotificationActivity;
        this.f$1 = cTInAppNotificationButton;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        this.f$0.m4781xb7cc076f(this.f$1, dialogInterface, i);
    }
}
