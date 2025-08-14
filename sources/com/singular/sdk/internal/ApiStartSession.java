package com.singular.sdk.internal;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import com.singular.sdk.SingularConfig;
import com.singular.sdk.SingularLinkParams;
import com.singular.sdk.internal.Api;
import com.singular.sdk.internal.ApiSubmitEvent;
import com.singular.sdk.internal.Constants;
import com.singular.sdk.internal.LicenseApiHelper;
import com.singular.sdk.internal.SingularParamsBase;
import com.singular.sdk.internal.utils.UriUtils;
import io.sentry.SentryBaseEvent;
import java.io.IOException;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class ApiStartSession extends BaseApi {
    private static final int LICENSE_RETRY_INTERVAL_MS = 3000;
    private static final SingularLog logger = SingularLog.getLogger("ApiStartSession");
    static final String path = "/start";
    private int licenseAttemptsCounter;

    @Override // com.singular.sdk.internal.Api
    public String getPath() {
        return path;
    }

    static /* synthetic */ int access$108(ApiStartSession apiStartSession) {
        int i = apiStartSession.licenseAttemptsCounter;
        apiStartSession.licenseAttemptsCounter = i + 1;
        return i;
    }

    @Override // com.singular.sdk.internal.BaseApi, com.singular.sdk.internal.Api
    public /* bridge */ /* synthetic */ long getTimestamp() {
        return super.getTimestamp();
    }

    @Override // com.singular.sdk.internal.BaseApi, com.singular.sdk.internal.Api
    public /* bridge */ /* synthetic */ String getType() {
        return super.getType();
    }

    @Override // com.singular.sdk.internal.BaseApi
    public /* bridge */ /* synthetic */ String getUrl() {
        return super.getUrl();
    }

    @Override // com.singular.sdk.internal.BaseApi
    public /* bridge */ /* synthetic */ boolean isAdmonEvent() {
        return super.isAdmonEvent();
    }

    @Override // com.singular.sdk.internal.BaseApi, com.singular.sdk.internal.Api
    public /* bridge */ /* synthetic */ boolean makeRequest(SingularInstance singularInstance) throws IOException {
        return super.makeRequest(singularInstance);
    }

    @Override // com.singular.sdk.internal.BaseApi, com.singular.sdk.internal.Api
    public /* bridge */ /* synthetic */ String toJsonAsString() {
        return super.toJsonAsString();
    }

    ApiStartSession(long j) {
        super(Constants.API_TYPE_SESSION_START, j);
        this.licenseAttemptsCounter = 0;
    }

    private class ApiStartSessionConstants {
        static final String STRING_REFERRER = "referrer";

        private ApiStartSessionConstants() {
        }
    }

    @Override // com.singular.sdk.internal.Api
    public Api.OnApiCallback getOnApiCallback() {
        return new OnSessionStartCallback();
    }

    public class OnSessionStartCallback implements Api.OnApiCallback {
        /* JADX INFO: Access modifiers changed from: private */
        public boolean isLicenseResponseValid(int i) {
            return (i == -1 || i == 257 || i == 4) ? false : true;
        }

        public OnSessionStartCallback() {
        }

        @Override // com.singular.sdk.internal.Api.OnApiCallback
        public boolean handle(SingularInstance singularInstance, int i, String str) {
            String str2;
            if (i != 200) {
                return false;
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (!jSONObject.optString("status", "").equalsIgnoreCase("ok")) {
                    return false;
                }
                String strOptString = jSONObject.optString("ddl", null);
                String strOptString2 = jSONObject.optString("deferred_passthrough", null);
                if (!Utils.isOpenedWithDeeplink() && (!Utils.isEmptyOrNull(strOptString) || !Utils.isEmptyOrNull(strOptString2))) {
                    handleDDL(singularInstance, strOptString, strOptString2);
                }
                String strOptString3 = jSONObject.optString(Constants.RESOLVED_SINGULAR_LINK, null);
                if (!Utils.isEmptyOrNull(strOptString3) && (str2 = (String) ApiStartSession.this.get(Constants.SINGULAR_LINK_RESOLVE_REQUIRED)) != null && Boolean.parseBoolean(str2) && Utils.lagSince(ApiStartSession.this.getTimestamp()) < SingularInstance.getInstance().getSingularConfig().shortlinkTimeoutSec) {
                    Utils.handleSingularLink(Uri.parse(strOptString3));
                }
                JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(Constants.DEVICE_ATTRIBUTION_INFO_KEY);
                if (jSONObjectOptJSONObject != null) {
                    singularInstance.handleDeviceAttributionData(jSONObjectOptJSONObject);
                }
                String str3 = (String) ApiStartSession.this.get(SingularParamsBase.Constants.IDENTIFIER_UNIQUE_ID_KEY);
                if (Utils.isEmptyOrNull(str3) || Utils.isLicenseRetrieved(singularInstance.getContext(), str3)) {
                    return true;
                }
                Utils.saveLicenseInfo(singularInstance.getContext(), str3);
                performLicenseCheck(singularInstance, str3);
                return true;
            } catch (JSONException e) {
                ApiStartSession.logger.error("error in handle()", e);
                return false;
            }
        }

        /* renamed from: com.singular.sdk.internal.ApiStartSession$OnSessionStartCallback$1, reason: invalid class name */
        class AnonymousClass1 implements Runnable {
            final /* synthetic */ String val$identifier;
            final /* synthetic */ SingularInstance val$singular;

            AnonymousClass1(SingularInstance singularInstance, String str) {
                this.val$singular = singularInstance;
                this.val$identifier = str;
            }

            @Override // java.lang.Runnable
            public void run() {
                ApiStartSession.access$108(ApiStartSession.this);
                LicenseApiHelper.checkLicense(this.val$singular.getContext(), new LicenseApiHelper.LicenseResultHandler() { // from class: com.singular.sdk.internal.ApiStartSession.OnSessionStartCallback.1.1
                    @Override // com.singular.sdk.internal.LicenseApiHelper.LicenseResultHandler
                    public void handle(final int i, final String str, final String str2) {
                        new Thread(new Runnable() { // from class: com.singular.sdk.internal.ApiStartSession.OnSessionStartCallback.1.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                try {
                                    if (!OnSessionStartCallback.this.isLicenseResponseValid(i) && ApiStartSession.this.licenseAttemptsCounter < 3) {
                                        Thread.sleep(ApiStartSession.this.licenseAttemptsCounter * 3000);
                                        OnSessionStartCallback.this.performLicenseCheck(AnonymousClass1.this.val$singular, AnonymousClass1.this.val$identifier);
                                        return;
                                    }
                                    JSONObject jSONObject = new JSONObject();
                                    jSONObject.put("responseCode", String.valueOf(i));
                                    jSONObject.put("signedData", str);
                                    jSONObject.put("signature", str2);
                                    AnonymousClass1.this.val$singular.logEvent(new ApiSubmitEvent.RawEvent(LicenseApiHelper.LICENSING_EVENT_NAME, jSONObject.toString()));
                                } catch (Throwable th) {
                                    ApiStartSession.logger.error("Error occurred while trying to send licensing status event", th);
                                }
                            }
                        }).start();
                    }
                });
            }
        }

        void performLicenseCheck(SingularInstance singularInstance, String str) {
            ApiStartSession.logger.debug("Trying to fetch license key from the Licensing Service");
            new Thread(new AnonymousClass1(singularInstance, str)).start();
        }

        void handleDDL(SingularInstance singularInstance, final String str, String str2) {
            final SingularConfig.DDLHandler dDLHandler = singularInstance.getSingularConfig().ddlHandler;
            if (dDLHandler == null) {
                ApiStartSession.logger.error("DDLHandler is not configured, ignoring callback for url = %s", str);
                return;
            }
            if (Utils.lagSince(ApiStartSession.this.getTimestamp()) > dDLHandler.timeoutInSec) {
                ApiStartSession.logger.error("DDLHandler timedout. timeout = %dms", Long.valueOf(dDLHandler.timeoutInSec));
            } else {
                if (Utils.handleDeepLink(new SingularLinkParams(str, str2, true, Uri.parse(str))) || dDLHandler.handler == null) {
                    return;
                }
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.singular.sdk.internal.ApiStartSession.OnSessionStartCallback.2
                    @Override // java.lang.Runnable
                    public void run() {
                        dDLHandler.handler.handleLink(str);
                    }
                });
            }
        }
    }

    static class Params extends SingularParamsBase {
        private Params() {
        }

        static Params build(long j, SingularInstance singularInstance) {
            return new Params().withId(j).withSingularConfig(singularInstance.getSingularConfig()).withDeviceInfo(singularInstance.getDeviceInfo()).withConnectionType(Utils.getConnectionType(singularInstance.getContext())).withAppInstallInfo(singularInstance).withInstallReferrer(singularInstance).withDTInstallReferrer(singularInstance).withUtils();
        }

        private Params withInstallReferrer(SingularInstance singularInstance) {
            if (singularInstance.getIsFirstInstall()) {
                if (singularInstance.getGoogleInstallReferrer() != null) {
                    put("install_ref", new JSONObject(singularInstance.getGoogleInstallReferrer()).toString());
                }
                put("install_ref_timeinterval", String.valueOf(singularInstance.getInstallReferrerTimeInterval()));
                HashMap map = new HashMap();
                if (singularInstance.getSamsungInstallReferrer() != null) {
                    map.putAll(singularInstance.getSamsungInstallReferrer());
                }
                if (singularInstance.getMetaInstallReferrer() != null) {
                    map.putAll(singularInstance.getMetaInstallReferrer());
                }
                put(Constants.RequestBody.INSTALL_REFERRER_KEY, new JSONObject(map).toString());
            }
            return this;
        }

        private Params withDTInstallReferrer(SingularInstance singularInstance) {
            if (singularInstance.getIsFirstInstall() & (singularInstance.getDTInstallReferrer() != null)) {
                put(Constants.DT_INSTALL_REFERRER_KEY, singularInstance.getDTInstallReferrer());
            }
            return this;
        }

        private Params withUtils() {
            put("asid_timeinterval", String.valueOf(Utils.getASIDTimeInterval()));
            put("asid_scope", String.valueOf(Utils.getASIDScope()));
            return this;
        }

        private Params withId(long j) {
            put("s", String.valueOf(j));
            return this;
        }

        private Params withConnectionType(String str) {
            put("c", str);
            return this;
        }

        private Params withSingularConfig(SingularConfig singularConfig) {
            put("a", singularConfig.apiKey);
            Uri uri = singularConfig.openUri;
            if (UriUtils.isValid(uri)) {
                put("ref", uri.getQueryParameter("referrer"));
                put(SentryBaseEvent.JsonKeys.EXTRA, uri.getQuery());
            }
            if (singularConfig.ddlHandler != null || singularConfig.linkCallback != null) {
                put("ddl_enabled", "true");
                put("ddl_to", String.valueOf(singularConfig.ddlHandler.timeoutInSec));
            } else {
                put("ddl_enabled", com.facebook.hermes.intl.Constants.CASEFIRST_FALSE);
            }
            Uri uriResolveESPLink = singularConfig.singularLink;
            if (UriUtils.isValid(uriResolveESPLink)) {
                String queryParameter = uriResolveESPLink.getQueryParameter("referrer");
                String query = uriResolveESPLink.getQuery();
                if (!Utils.isEmptyOrNull(queryParameter)) {
                    put("ref", queryParameter);
                }
                if (!Utils.isEmptyOrNull(query)) {
                    put(SentryBaseEvent.JsonKeys.EXTRA, query);
                }
                if (Utils.isESPLink(uriResolveESPLink)) {
                    put("esp_link", uriResolveESPLink.toString());
                    uriResolveESPLink = Utils.resolveESPLink(uriResolveESPLink);
                }
                if (Utils.validateSingularLink(uriResolveESPLink)) {
                    boolean zIsShortLink = Utils.isShortLink(uriResolveESPLink);
                    if (!zIsShortLink) {
                        Utils.handleSingularLink(uriResolveESPLink);
                    }
                    put(Constants.SINGULAR_LINK, uriResolveESPLink.toString());
                    put(Constants.SINGULAR_LINK_RESOLVE_TIMEOUT, String.valueOf(singularConfig.shortlinkTimeoutSec));
                    put(Constants.SINGULAR_LINK_RESOLVE_REQUIRED, String.valueOf(zIsShortLink));
                }
                singularConfig.singularLink = null;
            }
            return this;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Removed duplicated region for block: B:7:0x0051  */
        @Override // com.singular.sdk.internal.SingularParamsBase
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public com.singular.sdk.internal.ApiStartSession.Params withDeviceInfo(com.singular.sdk.internal.DeviceInfo r4) {
            /*
                Method dump skipped, instructions count: 268
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.singular.sdk.internal.ApiStartSession.Params.withDeviceInfo(com.singular.sdk.internal.DeviceInfo):com.singular.sdk.internal.ApiStartSession$Params");
        }

        private Params withAppInstallInfo(SingularInstance singularInstance) {
            if (singularInstance.getIsFirstInstall()) {
                put("is", "true");
            } else {
                put("is", com.facebook.hermes.intl.Constants.CASEFIRST_FALSE);
            }
            return this;
        }
    }
}
