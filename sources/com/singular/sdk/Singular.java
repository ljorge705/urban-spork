package com.singular.sdk;

import android.content.Context;
import com.singular.sdk.internal.Constants;
import com.singular.sdk.internal.SingularExceptionReporter;
import com.singular.sdk.internal.SingularGlobalProperty;
import com.singular.sdk.internal.SingularInstance;
import com.singular.sdk.internal.SingularLog;
import com.singular.sdk.internal.Utils;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class Singular {
    private static SingularInstance singular;
    private static final SingularLog logger = SingularLog.getLogger("Singular");
    private static boolean isInstanceAlreadyInitialized = false;
    private static Context saved_application_context = null;
    private static Boolean limitedIdentifiersEnabled = false;

    private Singular() {
    }

    public static boolean init(Context context, String str, String str2) {
        if (context == null || str == null || str2 == null) {
            return false;
        }
        return init(context, new SingularConfig(str, str2));
    }

    public static boolean init(Context context, SingularConfig singularConfig) {
        if (context == null || singularConfig == null) {
            return false;
        }
        try {
            isInstanceAlreadyInitialized = singular != null;
            SingularInstance singularInstance = SingularInstance.getInstance(context, singularConfig);
            singular = singularInstance;
            if (isInstanceAlreadyInitialized) {
                singularInstance.startSessionIfOpenedWithDeeplink();
            }
            saved_application_context = context.getApplicationContext();
            limitedIdentifiersEnabled = singularConfig.limitedIdentifiresEnabled;
        } catch (IOException e) {
            SingularLog singularLog = logger;
            singularLog.debug("Failed to init() Singular SDK");
            singularLog.error(Utils.formatException(e));
            singular = null;
        } catch (RuntimeException e2) {
            reportException(e2);
            logger.error(Utils.formatException(e2));
        }
        return isInitialized();
    }

    private static void reportException(Throwable th) {
        try {
            SingularExceptionReporter.getReporter(saved_application_context, limitedIdentifiersEnabled).reportException(th);
        } catch (RuntimeException unused) {
        }
    }

    private static boolean isInitialized() {
        if (singular != null) {
            return true;
        }
        logger.error("Singular not initialized. You must call Singular.init() ");
        return false;
    }

    public static boolean event(String str) {
        try {
            if (!isInitialized()) {
                return false;
            }
            if (Utils.isEmptyOrNull(str)) {
                logger.error("Event name can not be null or empty");
                return false;
            }
            return singular.logEvent(str);
        } catch (RuntimeException e) {
            reportException(e);
            logger.error(Utils.formatException(e));
            return false;
        }
    }

    public static boolean event(String str, String str2) {
        try {
            if (!isInitialized()) {
                return false;
            }
            if (Utils.isEmptyOrNull(str)) {
                logger.error("Event name can not be null or empty");
                return false;
            }
            return singular.logEvent(str, str2);
        } catch (RuntimeException e) {
            reportException(e);
            logger.error(Utils.formatException(e));
            return false;
        }
    }

    public static boolean event(String str, Object... objArr) throws JSONException {
        try {
            if (!isInitialized()) {
                return false;
            }
            if (Utils.isEmptyOrNull(str)) {
                logger.error("Event name can not be null or empty");
                return false;
            }
            if (objArr.length % 2 != 0) {
                logger.error("Extra arguments must be in even numbers.");
                return false;
            }
            try {
                JSONObject jSONObject = new JSONObject();
                for (int i = 0; i < objArr.length; i += 2) {
                    jSONObject.put((String) objArr[i], objArr[i + 1]);
                }
                return eventJSON(str, jSONObject);
            } catch (JSONException e) {
                logger.error("error in serializing extra args", e);
                return false;
            }
        } catch (RuntimeException e2) {
            reportException(e2);
            logger.error("Exception", e2);
        }
    }

    public static boolean revenue(String str, double d, Object obj) {
        return customRevenue(Constants.REVENUE_EVENT_NAME, str, d, obj);
    }

    public static boolean revenue(String str, double d, Object obj, Map<String, Object> map) {
        return customRevenue(Constants.REVENUE_EVENT_NAME, str, d, obj, map);
    }

    public static boolean revenue(String str, double d) {
        return customRevenue(Constants.REVENUE_EVENT_NAME, str, d);
    }

    public static boolean revenue(String str, double d, Map<String, Object> map) {
        return customRevenue(Constants.REVENUE_EVENT_NAME, str, d, map);
    }

    public static boolean revenue(String str, double d, String str2, String str3) {
        return customRevenue(Constants.REVENUE_EVENT_NAME, str, d, str2, str3);
    }

    public static boolean revenue(String str, double d, String str2, String str3, String str4, int i, double d2) {
        return customRevenue(Constants.REVENUE_EVENT_NAME, str, d, str2, str3, str4, i, d2);
    }

    private static Object getSku(Object obj) throws Exception {
        Method declaredMethod = Utils.getDeclaredMethod(obj, "getProducts", new Class[0]);
        if (declaredMethod != null) {
            return new JSONArray(declaredMethod.invoke(obj, new Object[0]).toString());
        }
        Method declaredMethod2 = Utils.getDeclaredMethod(obj, "getSku", new Class[0]);
        if (declaredMethod2 != null) {
            return (String) declaredMethod2.invoke(obj, new Object[0]);
        }
        Method declaredMethod3 = Utils.getDeclaredMethod(obj, "getSkus", new Class[0]);
        if (declaredMethod3 != null) {
            return new JSONArray(declaredMethod3.invoke(obj, new Object[0]).toString());
        }
        return null;
    }

    public static boolean customRevenue(String str, String str2, double d, Object obj) {
        return customRevenue(str, str2, d, obj, (Map<String, Object>) null);
    }

    public static boolean customRevenue(String str, String str2, double d, Object obj, Map<String, Object> map) {
        if (obj != null && obj.getClass().getName().equals("com.android.billingclient.api.Purchase")) {
            try {
                Class<?> cls = obj.getClass();
                Object sku = getSku(obj);
                String str3 = (String) cls.getDeclaredMethod("getOriginalJson", new Class[0]).invoke(obj, new Object[0]);
                String str4 = (String) cls.getDeclaredMethod("getSignature", new Class[0]).invoke(obj, new Object[0]);
                if (map != null) {
                    try {
                        JSONObject jSONObject = new JSONObject(map);
                        jSONObject.put(Constants.REVENUE_CURRENCY_KEY, str2);
                        jSONObject.put("r", d);
                        jSONObject.put(Constants.IS_REVENUE_EVENT_KEY, true);
                        jSONObject.put(Constants.REVENUE_RECEIPT_KEY, str3);
                        jSONObject.put(Constants.REVENUE_RECEIPT_SIGNATURE, str4);
                        jSONObject.put(Constants.REVENUE_PRODUCT_SKU_KEY, sku);
                        return eventJSON(str, jSONObject);
                    } catch (JSONException e) {
                        logger.error(Utils.formatException(e));
                    }
                }
                return event(str, Constants.REVENUE_CURRENCY_KEY, str2, "r", Double.valueOf(d), Constants.REVENUE_PRODUCT_SKU_KEY, sku, Constants.REVENUE_RECEIPT_KEY, str3, Constants.REVENUE_RECEIPT_SIGNATURE, str4, Constants.IS_REVENUE_EVENT_KEY, true);
            } catch (Throwable th) {
                logger.error("customRevenue has encountered an unexpected exception. Please verify that the 'purchase' object is of type 'com.android.billingclient.api.Purchase'", th);
            }
        }
        return customRevenue(str, str2, d, map);
    }

    public static boolean customRevenue(String str, String str2, double d) {
        return event(str, Constants.REVENUE_CURRENCY_KEY, str2, "r", Double.valueOf(d), Constants.IS_REVENUE_EVENT_KEY, true);
    }

    public static boolean customRevenue(String str, String str2, double d, Map<String, Object> map) throws JSONException {
        if (map != null) {
            try {
                JSONObject jSONObject = new JSONObject(map);
                jSONObject.put(Constants.REVENUE_CURRENCY_KEY, str2);
                jSONObject.put("r", d);
                jSONObject.put(Constants.IS_REVENUE_EVENT_KEY, true);
                return eventJSON(str, jSONObject);
            } catch (JSONException e) {
                logger.error(Utils.formatException(e));
            }
        }
        return customRevenue(str, str2, d);
    }

    public static boolean customRevenue(String str, String str2, double d, String str3, String str4) {
        return event(str, Constants.REVENUE_CURRENCY_KEY, str2, "r", Double.valueOf(d), Constants.REVENUE_RECEIPT_KEY, str3, Constants.REVENUE_RECEIPT_SIGNATURE, str4, Constants.IS_REVENUE_EVENT_KEY, true);
    }

    public static boolean customRevenue(String str, String str2, double d, String str3, String str4, String str5, int i, double d2) {
        return event(str, Constants.REVENUE_CURRENCY_KEY, str2, "r", Double.valueOf(d), Constants.REVENUE_PRODUCT_SKU_KEY, str3, Constants.REVENUE_PRODUCT_NAME_KEY, str4, Constants.REVENUE_PRODUCT_CATEGORY_KEY, str5, Constants.REVENUE_PRODUCT_QUANTITY_KEY, Integer.valueOf(i), Constants.REVENUE_PRODUCT_PRICE_KEY, Double.valueOf(d2), Constants.IS_REVENUE_EVENT_KEY, true);
    }

    public static boolean eventJSON(String str, JSONObject jSONObject) {
        try {
            if (!isInitialized()) {
                return false;
            }
            if (Utils.isEmptyOrNull(str)) {
                logger.error("Event name can not be null or empty");
                return false;
            }
            return singular.logEvent(str, jSONObject != null ? jSONObject.toString() : null);
        } catch (RuntimeException e) {
            reportException(e);
            logger.error("Exception", e);
            return false;
        }
    }

    public static boolean revenue(JSONObject jSONObject) {
        return customRevenue(Constants.REVENUE_EVENT_NAME, jSONObject);
    }

    public static boolean customRevenue(String str, JSONObject jSONObject) throws JSONException {
        try {
            jSONObject.put(Constants.IS_REVENUE_EVENT_KEY, true);
        } catch (JSONException e) {
            logger.error(Utils.formatException(e));
        }
        return eventJSON(str, jSONObject);
    }

    public static void onActivityPaused() {
        try {
            if (isInitialized()) {
                singular.onActivityPaused(Utils.getCurrentTimeMillis());
            }
        } catch (RuntimeException e) {
            reportException(e);
            logger.error(Utils.formatException(e));
        }
    }

    public static void onActivityResumed() {
        try {
            if (isInitialized()) {
                singular.onActivityResumed(Utils.getCurrentTimeMillis());
            }
        } catch (RuntimeException e) {
            reportException(e);
            logger.error(Utils.formatException(e));
        }
    }

    public static void setGCMDeviceToken(String str) {
        try {
            if (isInitialized()) {
                singular.saveGCMDeviceToken(str);
            }
        } catch (RuntimeException e) {
            reportException(e);
            logger.error(Utils.formatException(e));
        }
    }

    public static void setFCMDeviceToken(String str) {
        try {
            if (isInitialized()) {
                singular.saveFCMDeviceToken(str);
            }
        } catch (RuntimeException e) {
            reportException(e);
            logger.error(Utils.formatException(e));
        }
    }

    public static long getSessionId() {
        try {
            if (isInitialized()) {
                return singular.getSessionId();
            }
            return -1L;
        } catch (RuntimeException e) {
            reportException(e);
            logger.error(Utils.formatException(e));
            return -1L;
        }
    }

    public static void setDeviceCustomUserId(String str) {
        try {
            if (isInitialized()) {
                setCustomUserId(str);
                singular.logSetDeviceCustomUserId();
            }
        } catch (RuntimeException e) {
            reportException(e);
            logger.error(Utils.formatException(e));
        }
    }

    public static void setCustomUserId(String str) {
        try {
            if (isInitialized()) {
                singular.saveCustomUserId(str);
            }
        } catch (RuntimeException e) {
            reportException(e);
            logger.error(Utils.formatException(e));
        }
    }

    public static void unsetCustomUserId() {
        try {
            if (isInitialized()) {
                singular.saveCustomUserId("");
            }
        } catch (RuntimeException e) {
            reportException(e);
            logger.error(Utils.formatException(e));
        }
    }

    public static void setIMEI(String str) {
        try {
            if (isInitialized()) {
                singular.setIMEI(str);
            }
        } catch (RuntimeException e) {
            reportException(e);
            logger.error(Utils.formatException(e));
        }
    }

    public static void setWrapperNameAndVersion(String str, String str2) {
        try {
            Utils.setWrapperNameAndVersion(str, str2);
        } catch (RuntimeException e) {
            reportException(e);
            logger.error(Utils.formatException(e));
        }
    }

    public static Map<String, String> getGlobalProperties() {
        try {
            if (isInitialized()) {
                return singular.getGlobalProperties();
            }
            return null;
        } catch (RuntimeException e) {
            logger.error(Utils.formatException(e));
            return null;
        }
    }

    public static boolean setGlobalProperty(String str, String str2, boolean z) {
        if (Utils.isEmptyOrNull(str)) {
            return false;
        }
        try {
            if (isInitialized()) {
                return singular.setGlobalProperty(new SingularGlobalProperty(str, str2, z));
            }
        } catch (RuntimeException e) {
            logger.error(Utils.formatException(e));
        }
        return false;
    }

    public static void unsetGlobalProperty(String str) {
        if (Utils.isEmptyOrNull(str)) {
            return;
        }
        try {
            if (isInitialized()) {
                singular.removeGlobalProperty(str);
            }
        } catch (RuntimeException e) {
            logger.error(Utils.formatException(e));
        }
    }

    public static void clearGlobalProperties() {
        try {
            if (isInitialized()) {
                singular.clearGlobalProperties();
            }
        } catch (RuntimeException e) {
            logger.error(Utils.formatException(e));
        }
    }

    public static void trackingOptIn() {
        singular.sendTrackingOptIn();
    }

    public static void trackingUnder13() {
        singular.sendTrackingUnder13();
    }

    public static void stopAllTracking() {
        singular.stopAllTracking();
    }

    public static void resumeAllTracking() {
        singular.resumeAllTracking();
    }

    public static boolean isAllTrackingStopped() {
        return singular.isAllTrackingStopped();
    }

    public static void adRevenue(SingularAdData singularAdData) {
        try {
            if (isInitialized() && singularAdData != null && singularAdData.hasRequiredParams()) {
                eventJSON(Constants.ADMON_REVENUE_EVENT_NAME, singularAdData);
            }
        } catch (RuntimeException e) {
            reportException(e);
            logger.error(Utils.formatException(e));
        }
    }

    public static void limitDataSharing(boolean z) {
        singular.limitDataSharing(z);
    }

    public static boolean getLimitDataSharing() {
        Boolean limitDataSharing = singular.getLimitDataSharing();
        if (limitDataSharing == null) {
            return false;
        }
        return limitDataSharing.booleanValue();
    }

    public static void createReferrerShortLink(String str, String str2, String str3, JSONObject jSONObject, ShortLinkHandler shortLinkHandler) {
        if (shortLinkHandler == null) {
            return;
        }
        singular.createReferrerShortLink(str, str2, str3, jSONObject, shortLinkHandler);
    }
}
