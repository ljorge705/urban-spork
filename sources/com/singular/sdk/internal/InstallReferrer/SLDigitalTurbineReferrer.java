package com.singular.sdk.internal.InstallReferrer;

import android.content.ContentProviderClient;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.support.v4.media.session.PlaybackStateCompat;
import com.singular.sdk.internal.Constants;
import com.singular.sdk.internal.SingularLog;
import com.singular.sdk.internal.Utils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes6.dex */
public class SLDigitalTurbineReferrer implements SLInstallReferrerService {
    private static final SingularLog logger = SingularLog.getLogger("SLDigitalTurbineReferrer");

    @Override // com.singular.sdk.internal.InstallReferrer.SLInstallReferrerService
    public void fetchReferrer(Context context, SLInstallReferrerCompletionHandler sLInstallReferrerCompletionHandler) {
        if (context == null) {
            sLInstallReferrerCompletionHandler.onInstallReferrerReceived(null);
            return;
        }
        HashMap map = new HashMap();
        String contentProviderIntentAction = readContentProviderIntentAction(context, "android.permission.INSTALL_PACKAGES");
        if (contentProviderIntentAction != null) {
            map.put(Constants.DT_INSTALL_REFERRER_KEY, contentProviderIntentAction);
            sLInstallReferrerCompletionHandler.onInstallReferrerReceived(map);
        } else {
            sLInstallReferrerCompletionHandler.onInstallReferrerReceived(null);
        }
    }

    private String readContentProvider(Context context, String str, ContentProviderClient contentProviderClient) {
        Cursor cursorQuery;
        try {
            Uri uri = Uri.parse(str);
            String[] strArr = {"encrypted_data"};
            String[] strArr2 = {context.getPackageName()};
            if (contentProviderClient != null) {
                cursorQuery = contentProviderClient.query(uri, strArr, "package_name=?", strArr2, null);
            } else {
                cursorQuery = context.getContentResolver().query(uri, strArr, null, null, null);
            }
            if (cursorQuery == null) {
                logger.debug("Read content provider cursor null content uri [%s]", str);
                return null;
            }
            if (!cursorQuery.moveToFirst()) {
                logger.debug("Read content provider cursor empty content uri [%s]", str);
                cursorQuery.close();
                return null;
            }
            String string = cursorQuery.getString(0);
            logger.debug("Read Content Provider Payload is " + string);
            cursorQuery.close();
            return string;
        } catch (Throwable th) {
            logger.error("Exception read content provider uri [%s] error [%s]", str, th.getMessage());
            return null;
        }
    }

    private String readContentProviderIntentAction(Context context, String str) {
        List<ResolveInfo> listQueryIntentContentProviders;
        try {
            if (Build.VERSION.SDK_INT >= 33) {
                listQueryIntentContentProviders = context.getPackageManager().queryIntentContentProviders(new Intent(Constants.DT_CONTENT_PROVIDER_INTENT_ACTION_NAME), PackageManager.ResolveInfoFlags.of(PlaybackStateCompat.ACTION_PREPARE_FROM_URI));
            } else {
                listQueryIntentContentProviders = context.getPackageManager().queryIntentContentProviders(new Intent(Constants.DT_CONTENT_PROVIDER_INTENT_ACTION_NAME), 131072);
            }
            ArrayList arrayList = new ArrayList();
            for (ResolveInfo resolveInfo : listQueryIntentContentProviders) {
                if (checkIfPackageReadingPermissionIsGranted(context, str, resolveInfo)) {
                    String strFetchContentFromProvider = fetchContentFromProvider(context, resolveInfo);
                    if (!Utils.isEmptyOrNull(strFetchContentFromProvider)) {
                        arrayList.add(strFetchContentFromProvider);
                    }
                }
            }
            if (!arrayList.isEmpty()) {
                logger.debug("Payload read successfully from URI: " + arrayList);
                return (String) arrayList.get(0);
            }
        } catch (Throwable unused) {
        }
        logger.debug("No payloads found in content providers.");
        return null;
    }

    private String readContentProviderWithClient(Context context, String str) {
        try {
            ContentProviderClient contentProviderClientAcquireUnstableContentProviderClient = context.getContentResolver().acquireUnstableContentProviderClient(str);
            String contentProvider = readContentProvider(context, formatURIString(str, Constants.PREINSTALL_CONTENT_URI_PATH), contentProviderClientAcquireUnstableContentProviderClient);
            contentProviderClientAcquireUnstableContentProviderClient.release();
            return contentProvider;
        } catch (Throwable unused) {
            return null;
        }
    }

    private String formatURIString(String str, String str2) {
        return String.format("content://%s/%s", str, str2);
    }

    private boolean checkIfPackageReadingPermissionIsGranted(Context context, String str, ResolveInfo resolveInfo) {
        return str != null && context.getPackageManager().checkPermission(str, resolveInfo.providerInfo.packageName) == 0;
    }

    private String fetchContentFromProvider(Context context, ResolveInfo resolveInfo) {
        String str = resolveInfo.providerInfo.authority;
        if (Utils.isEmptyOrNull(str)) {
            return null;
        }
        return readContentProviderWithClient(context, str);
    }
}
