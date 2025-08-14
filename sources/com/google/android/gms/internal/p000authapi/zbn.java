package com.google.android.gms.internal.p000authapi;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import androidx.media3.common.C;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.credentials.HintRequest;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;

/* compiled from: com.google.android.gms:play-services-auth@@20.5.0 */
/* loaded from: classes3.dex */
public final class zbn {
    public static PendingIntent zba(Context context, Auth.AuthCredentialsOptions authCredentialsOptions, HintRequest hintRequest, String str) {
        Preconditions.checkNotNull(context, "context must not be null");
        Preconditions.checkNotNull(hintRequest, "request must not be null");
        String strZba = TextUtils.isEmpty(str) ? zbbb.zba() : (String) Preconditions.checkNotNull(str);
        Intent intentPutExtra = new Intent("com.google.android.gms.auth.api.credentials.PICKER").putExtra("claimedCallingPackage", (String) null);
        intentPutExtra.putExtra("logSessionId", strZba);
        SafeParcelableSerializer.serializeToIntentExtra(hintRequest, intentPutExtra, "com.google.android.gms.credentials.HintRequest");
        return PendingIntent.getActivity(context, 2000, intentPutExtra, zbbc.zba | C.BUFFER_FLAG_FIRST_SAMPLE);
    }
}
