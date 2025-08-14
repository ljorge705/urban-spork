package com.singular.sdk.internal.InstallReferrer;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes6.dex */
public class SLSamsungReferrer implements SLInstallReferrerService {
    private static final String packageName = "com.samsung.android.sdk.sinstallreferrer.api";
    private static final String sourceName = "samsung";

    @Override // com.singular.sdk.internal.InstallReferrer.SLInstallReferrerService
    public void fetchReferrer(Context context, final SLInstallReferrerCompletionHandler sLInstallReferrerCompletionHandler) {
        new SLReflectionInstallReferrer(context, packageName, new SLInstallReferrerCompletionHandler() { // from class: com.singular.sdk.internal.InstallReferrer.SLSamsungReferrer.1
            @Override // com.singular.sdk.internal.InstallReferrer.SLInstallReferrerCompletionHandler
            public void onInstallReferrerReceived(Map<String, Object> map) {
                if (map == null) {
                    sLInstallReferrerCompletionHandler.onInstallReferrerReceived(null);
                    return;
                }
                HashMap map2 = new HashMap();
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    map2.put("samsung_" + entry.getKey(), entry.getValue());
                }
                sLInstallReferrerCompletionHandler.onInstallReferrerReceived(map2);
            }
        }).startConnection();
    }
}
