package com.clevertap.android.sdk.events;

import android.content.Context;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.CoreMetaData;
import com.clevertap.android.sdk.LocalDataStore;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.ProfileValueHandler;
import com.clevertap.android.sdk.StorageHelper;
import com.clevertap.android.sdk.variables.JsonUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class EventMediator {
    private final CoreMetaData cleverTapMetaData;
    private final CleverTapInstanceConfig config;
    private final Context context;
    private final LocalDataStore localDataStore;
    private final ProfileValueHandler profileValueHandler;

    public EventMediator(Context context, CleverTapInstanceConfig cleverTapInstanceConfig, CoreMetaData coreMetaData, LocalDataStore localDataStore, ProfileValueHandler profileValueHandler) {
        this.context = context;
        this.config = cleverTapInstanceConfig;
        this.localDataStore = localDataStore;
        this.profileValueHandler = profileValueHandler;
        this.cleverTapMetaData = coreMetaData;
    }

    public boolean shouldDeferProcessingEvent(JSONObject jSONObject, int i) {
        if (i == 8 || this.config.isCreatedPostAppLaunch()) {
            return false;
        }
        if (jSONObject.has(Constants.KEY_EVT_NAME)) {
            try {
                if (Arrays.asList(Constants.SYSTEM_EVENTS).contains(jSONObject.getString(Constants.KEY_EVT_NAME))) {
                    return false;
                }
            } catch (JSONException unused) {
            }
        }
        return i == 4 && !this.cleverTapMetaData.isAppLaunchPushed();
    }

    public boolean shouldDropEvent(JSONObject jSONObject, int i) {
        if (i != 7 && i != 8) {
            if (this.cleverTapMetaData.isCurrentUserOptedOut()) {
                this.config.getLogger().debug(this.config.getAccountId(), "Current user is opted out dropping event: " + (jSONObject == null ? "null" : jSONObject.toString()));
                return true;
            }
            if (isMuted()) {
                this.config.getLogger().verbose(this.config.getAccountId(), "CleverTap is muted, dropping event - " + jSONObject.toString());
                return true;
            }
        }
        return false;
    }

    public boolean isAppLaunchedEvent(JSONObject jSONObject) {
        try {
            if (jSONObject.has(Constants.KEY_EVT_NAME)) {
                return jSONObject.getString(Constants.KEY_EVT_NAME).equals(Constants.APP_LAUNCHED_EVENT);
            }
            return false;
        } catch (JSONException unused) {
            return false;
        }
    }

    public boolean isEvent(JSONObject jSONObject) {
        return jSONObject.has(Constants.KEY_EVT_NAME);
    }

    public String getEventName(JSONObject jSONObject) {
        try {
            return jSONObject.getString(Constants.KEY_EVT_NAME);
        } catch (JSONException unused) {
            return null;
        }
    }

    public Map<String, Object> getEventProperties(JSONObject jSONObject) {
        if (jSONObject.has(Constants.KEY_EVT_NAME) && jSONObject.has(Constants.KEY_EVT_DATA)) {
            try {
                return JsonUtil.mapFromJson(jSONObject.getJSONObject(Constants.KEY_EVT_DATA));
            } catch (JSONException e) {
                Logger.v("Could not convert JSONObject to Map - " + e.getMessage());
            }
        }
        return new HashMap();
    }

    public boolean isChargedEvent(JSONObject jSONObject) {
        try {
            if (jSONObject.has(Constants.KEY_EVT_NAME)) {
                return jSONObject.getString(Constants.KEY_EVT_NAME).equals(Constants.CHARGED_EVENT);
            }
            return false;
        } catch (JSONException unused) {
            return false;
        }
    }

    public List<Map<String, Object>> getChargedEventItemDetails(JSONObject jSONObject) {
        try {
            return JsonUtil.listFromJson(jSONObject.getJSONObject(Constants.KEY_EVT_DATA).getJSONArray(Constants.KEY_ITEMS));
        } catch (JSONException unused) {
            return new ArrayList();
        }
    }

    public Map<String, Object> getChargedEventDetails(JSONObject jSONObject) throws JSONException {
        try {
            Object objRemove = jSONObject.getJSONObject(Constants.KEY_EVT_DATA).remove(Constants.KEY_ITEMS);
            Map<String, Object> mapMapFromJson = JsonUtil.mapFromJson(jSONObject.getJSONObject(Constants.KEY_EVT_DATA));
            jSONObject.getJSONObject(Constants.KEY_EVT_DATA).put(Constants.KEY_ITEMS, objRemove);
            return mapMapFromJson;
        } catch (JSONException unused) {
            return new HashMap();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x0091  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.lang.Object>> computeUserAttributeChangeProperties(org.json.JSONObject r15) throws org.json.JSONException {
        /*
            Method dump skipped, instructions count: 332
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.clevertap.android.sdk.events.EventMediator.computeUserAttributeChangeProperties(org.json.JSONObject):java.util.Map");
    }

    private boolean isMuted() {
        return ((int) (System.currentTimeMillis() / 1000)) - StorageHelper.getIntFromPrefs(this.context, this.config, Constants.KEY_MUTED, 0) < 86400;
    }
}
