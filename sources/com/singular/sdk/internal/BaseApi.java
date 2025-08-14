package com.singular.sdk.internal;

import java.io.IOException;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
abstract class BaseApi extends SingularMap implements Api {
    static final String TIMESTAMP_KEY = "__TIMESTAMP__";
    static final String TYPE_KEY = "__TYPE__";
    private static final SingularLog logger = SingularLog.getLogger("BaseApi");

    public BaseApi(String str, long j) {
        put(TYPE_KEY, str);
        put(TIMESTAMP_KEY, String.valueOf(j));
    }

    Map<String, String> getParams() {
        HashMap map = new HashMap(this);
        map.remove(TYPE_KEY);
        map.remove(TIMESTAMP_KEY);
        return map;
    }

    public boolean isAdmonEvent() {
        try {
            String str = (String) get("e");
            if (str != null) {
                return new JSONObject(str).getBoolean(Constants.ADMON_IS_ADMON_REVENUE);
            }
            return false;
        } catch (Throwable th) {
            logger.debug("Not an admon event: " + th.getMessage());
            return false;
        }
    }

    void addParams(Map<String, String> map) {
        if (map == null) {
            return;
        }
        putAll(map);
    }

    public String getType() {
        return (String) get(TYPE_KEY);
    }

    public long getTimestamp() {
        String str = (String) get(TIMESTAMP_KEY);
        if (Utils.isEmptyOrNull(str)) {
            return -1L;
        }
        return Long.parseLong(str);
    }

    public String getUrl() {
        return "https://sdk-api-v1.singular.net/api/v1" + getPath();
    }

    public boolean makeRequest(SingularInstance singularInstance) throws IOException {
        if (!getParams().get("k").equalsIgnoreCase(Constants.SDID_KEY) && singularInstance.getDeviceInfo() != null && singularInstance.getDeviceInfo().sdid.exists()) {
            putAll(new SingularParamsBase().withDeviceInfo(singularInstance.getDeviceInfo()));
        }
        return SingularRequestHandler.makeRequest(singularInstance, getUrl(), getParams(), getTimestamp(), getOnApiCallback());
    }

    public String toJsonAsString() {
        return new JSONObject(this).toString();
    }

    public static BaseApi from(String str) throws IOException, NullPointerException {
        if (str == null) {
            throw new NullPointerException("api string cannot be null");
        }
        Map<String, String> mapFromString = fromString(str);
        String str2 = mapFromString.get(TYPE_KEY);
        String str3 = mapFromString.get(TIMESTAMP_KEY);
        long j = !Utils.isEmptyOrNull(str3) ? Long.parseLong(str3) : -1L;
        int andIncrementRetryCountForKey = Utils.getAndIncrementRetryCountForKey(SingularInstance.getInstance().getContext(), str3);
        if (andIncrementRetryCountForKey > 3) {
            mapFromString.put(Constants.RETRY_COUNT, String.valueOf(andIncrementRetryCountForKey));
        }
        if (Constants.API_TYPE_EVENT.equalsIgnoreCase(str2)) {
            ApiSubmitEvent apiSubmitEvent = new ApiSubmitEvent(j);
            apiSubmitEvent.addParams(mapFromString);
            return apiSubmitEvent;
        }
        if (Constants.API_TYPE_SESSION_START.equalsIgnoreCase(str2)) {
            ApiStartSession apiStartSession = new ApiStartSession(j);
            apiStartSession.addParams(mapFromString);
            return apiStartSession;
        }
        if (Constants.API_TYPE_GDPR_CONSENT.equalsIgnoreCase(str2)) {
            ApiGDPRConsent apiGDPRConsent = new ApiGDPRConsent(j);
            apiGDPRConsent.addParams(mapFromString);
            return apiGDPRConsent;
        }
        if (Constants.API_TYPE_GDPR_UNDER_13.equalsIgnoreCase(str2)) {
            ApiGDPRUnder13 apiGDPRUnder13 = new ApiGDPRUnder13(j);
            apiGDPRUnder13.addParams(mapFromString);
            return apiGDPRUnder13;
        }
        if (Constants.API_TYPE_CUSTOM_USER_ID.equalsIgnoreCase(str2)) {
            ApiCustomUserId apiCustomUserId = new ApiCustomUserId(j);
            apiCustomUserId.addParams(mapFromString);
            return apiCustomUserId;
        }
        throw new InvalidPropertiesFormatException(String.format("Unknown type = %s", str2));
    }

    private static Map<String, String> fromString(String str) throws IOException {
        try {
            JSONObject jSONObject = new JSONObject(str);
            HashMap map = new HashMap();
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                map.put(next, (String) jSONObject.get(next));
            }
            return map;
        } catch (JSONException e) {
            throw new IOException(e);
        }
    }
}
