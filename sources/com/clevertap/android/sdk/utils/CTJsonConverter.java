package com.clevertap.android.sdk.utils;

import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.exifinterface.media.ExifInterface;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.CoreMetaData;
import com.clevertap.android.sdk.DeviceInfo;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.ManifestInfo;
import com.clevertap.android.sdk.db.DBAdapter;
import com.clevertap.android.sdk.inapp.CTInAppNotification;
import com.clevertap.android.sdk.inbox.CTInboxMessage;
import com.clevertap.android.sdk.validation.ValidationResult;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class CTJsonConverter {
    public static JSONObject toJsonObject(String str, Logger logger, String str2) {
        JSONObject jSONObject;
        if (str != null) {
            try {
                jSONObject = new JSONObject(str);
            } catch (Throwable th) {
                logger.verbose(str2, "Error reading guid cache: " + th.toString());
            }
        } else {
            jSONObject = null;
        }
        return jSONObject != null ? jSONObject : new JSONObject();
    }

    public static JSONObject displayUnitFromExtras(Bundle bundle) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        String string = bundle.getString(Constants.DISPLAY_UNIT_PREVIEW_PUSH_PAYLOAD_KEY);
        Logger.v("Received Display Unit via push payload: " + string);
        JSONArray jSONArray = new JSONArray();
        jSONObject.put(Constants.DISPLAY_UNIT_JSON_RESPONSE_KEY, jSONArray);
        jSONArray.put(new JSONObject(string));
        return jSONObject;
    }

    public static JSONObject from(DeviceInfo deviceInfo, CoreMetaData coreMetaData, boolean z, boolean z2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        Location locationFromUser = coreMetaData.getLocationFromUser();
        jSONObject.put("Build", deviceInfo.getBuild() + "");
        jSONObject.put(Constants.CLTAP_APP_VERSION, deviceInfo.getVersionName());
        jSONObject.put(Constants.CLTAP_OS_VERSION, deviceInfo.getOsVersion());
        jSONObject.put(Constants.CLTAP_SDK_VERSION, deviceInfo.getSdkVersion());
        if (locationFromUser != null) {
            jSONObject.put(Constants.CLTAP_LATITUDE, locationFromUser.getLatitude());
            jSONObject.put(Constants.CLTAP_LONGITUDE, locationFromUser.getLongitude());
        }
        if (deviceInfo.getGoogleAdID() != null) {
            jSONObject.put(z2 ? "mt_GoogleAdID" : "GoogleAdID", deviceInfo.getGoogleAdID());
            jSONObject.put("GoogleAdIDLimit", deviceInfo.isLimitAdTrackingEnabled());
        }
        try {
            jSONObject.put(ExifInterface.TAG_MAKE, deviceInfo.getManufacturer());
            jSONObject.put(ExifInterface.TAG_MODEL, deviceInfo.getModel());
            jSONObject.put(Constants.CLTAP_CARRIER, deviceInfo.getCarrier());
            jSONObject.put("useIP", z);
            jSONObject.put("OS", deviceInfo.getOsName());
            jSONObject.put("wdt", deviceInfo.getWidth());
            jSONObject.put("hgt", deviceInfo.getHeight());
            jSONObject.put("dpi", deviceInfo.getDPI());
            jSONObject.put("dt", DeviceInfo.getDeviceType(deviceInfo.getContext()));
            jSONObject.put("locale", deviceInfo.getLocale());
            if (Build.VERSION.SDK_INT >= 28) {
                jSONObject.put("abckt", deviceInfo.getAppBucket());
            }
            if (deviceInfo.getLibrary() != null) {
                jSONObject.put("lib", deviceInfo.getLibrary());
            }
            String proxyDomain = ManifestInfo.getInstance(deviceInfo.getContext()).getProxyDomain();
            if (!TextUtils.isEmpty(proxyDomain)) {
                jSONObject.put(Constants.KEY_PROXY_DOMAIN, proxyDomain);
            }
            String spikeyProxyDomain = ManifestInfo.getInstance(deviceInfo.getContext()).getSpikeyProxyDomain();
            if (!TextUtils.isEmpty(spikeyProxyDomain)) {
                jSONObject.put(Constants.KEY_SPIKY_PROXY_DOMAIN, spikeyProxyDomain);
            }
            if (ManifestInfo.getInstance(deviceInfo.getContext()).isSSLPinningEnabled()) {
                jSONObject.put("sslpin", true);
            }
            if (!TextUtils.isEmpty(ManifestInfo.getInstance(deviceInfo.getContext()).getFCMSenderId())) {
                jSONObject.put("fcmsid", true);
            }
            String countryCode = deviceInfo.getCountryCode();
            if (countryCode != null && !countryCode.equals("")) {
                jSONObject.put("cc", countryCode);
            }
            if (z) {
                Boolean boolIsWifiConnected = deviceInfo.isWifiConnected();
                if (boolIsWifiConnected != null) {
                    jSONObject.put("wifi", boolIsWifiConnected);
                }
                Boolean boolIsBluetoothEnabled = deviceInfo.isBluetoothEnabled();
                if (boolIsBluetoothEnabled != null) {
                    jSONObject.put(Constants.CLTAP_BLUETOOTH_ENABLED, boolIsBluetoothEnabled);
                }
                String bluetoothVersion = deviceInfo.getBluetoothVersion();
                if (bluetoothVersion != null) {
                    jSONObject.put(Constants.CLTAP_BLUETOOTH_VERSION, bluetoothVersion);
                }
                String networkType = deviceInfo.getNetworkType();
                if (networkType != null) {
                    jSONObject.put(Constants.CLTAP_NETWORK_TYPE, networkType);
                }
            }
            jSONObject.put("LIAMC", deviceInfo.getLocalInAppCount());
            for (Map.Entry<String, Integer> entry : coreMetaData.getAllCustomSdkVersions().entrySet()) {
                jSONObject.put(entry.getKey(), entry.getValue());
            }
        } catch (Throwable unused) {
        }
        return jSONObject;
    }

    public static JSONObject getErrorObject(ValidationResult validationResult) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("c", validationResult.getErrorCode());
            jSONObject.put("d", validationResult.getErrorDesc());
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public static JSONArray getRenderedTargetList(DBAdapter dBAdapter) {
        String[] strArrFetchPushNotificationIds = dBAdapter.fetchPushNotificationIds();
        JSONArray jSONArray = new JSONArray();
        for (String str : strArrFetchPushNotificationIds) {
            Logger.v("RTL IDs -" + str);
            jSONArray.put(str);
        }
        return jSONArray;
    }

    public static JSONObject getWzrkFields(CTInAppNotification cTInAppNotification) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jsonDescription = cTInAppNotification.getJsonDescription();
        Iterator<String> itKeys = jsonDescription.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            if (next.startsWith(Constants.WZRK_PREFIX)) {
                jSONObject.put(next, jsonDescription.get(next));
            }
        }
        return jSONObject;
    }

    public static JSONObject getWzrkFields(CTInboxMessage cTInboxMessage) {
        return cTInboxMessage.getWzrkParams();
    }

    public static <T> Object[] toArray(JSONArray jSONArray) {
        Object[] objArr = new Object[jSONArray.length()];
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                objArr[i] = jSONArray.get(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return objArr;
    }

    public static JSONArray toJsonArray(List<?> list) {
        JSONArray jSONArray = new JSONArray();
        for (Object obj : list) {
            if (obj != null) {
                jSONArray.put(obj);
            }
        }
        return jSONArray;
    }

    public static String toJsonString(Object obj) {
        try {
            return obj.toString();
        } catch (Exception unused) {
            return null;
        }
    }

    public static ArrayList<?> toList(JSONArray jSONArray) {
        ArrayList<?> arrayList = new ArrayList<>();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                arrayList.add(jSONArray.get(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }
}
