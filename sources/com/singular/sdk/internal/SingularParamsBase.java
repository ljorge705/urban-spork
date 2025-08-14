package com.singular.sdk.internal;

import android.content.SharedPreferences;

/* loaded from: classes6.dex */
public class SingularParamsBase extends SingularMap {

    private interface Constants {
        public static final String AIFA_KEYSPACE_KEY = "aifa";
        public static final String AIFA_KEYSPACE_VALUE = "AIFA";
        public static final String AMID_KEYSPACE_KEY = "amid";
        public static final String AMID_KEYSPACE_VALUE = "AMID";
        public static final String ANDI_KEYSPACE_KEY = "andi";
        public static final String ANDI_KEYSPACE_VALUE = "ANDI";
        public static final String ASID_KEYSPACE_KEY = "asid";
        public static final String ASID_KEYSPACE_VALUE = "ASID";
        public static final String IDENTIFIER_KEYSPACE_KEY = "k";
        public static final String IDENTIFIER_UNIQUE_ID_KEY = "u";
        public static final String IMEI_KEYSPACE_KEY = "imei";
        public static final String IMEI_KEYSPACE_VALUE = "IMEI";
        public static final String OAID_KEYSPACE_KEY = "oaid";
        public static final String OAID_KEYSPACE_VALUE = "OAID";
        public static final String PACKAGE_NAME_KEY = "i";
        public static final String PLATFORM_KEY = "p";
    }

    protected SingularParamsBase() {
    }

    protected SingularParamsBase withDeviceInfo(DeviceInfo deviceInfo) {
        put("i", deviceInfo.packageName);
        put("p", deviceInfo.platform);
        SharedPreferences sharedPreferences = SingularInstance.getInstance().getContext().getSharedPreferences(com.singular.sdk.internal.Constants.PREF_SESSION, 0);
        if (!Utils.isEmptyOrNull(sharedPreferences.getString(com.singular.sdk.internal.Constants.CUSTOM_SDID_KEY, null))) {
            put("k", com.singular.sdk.internal.Constants.UPPERCASE_SDID_KEY);
            put(Constants.IDENTIFIER_UNIQUE_ID_KEY, sharedPreferences.getString(com.singular.sdk.internal.Constants.CUSTOM_SDID_KEY, ""));
            put("cs", "1");
            putAsidOrAifaIfNotNull(deviceInfo);
        } else if (deviceInfo.sdid != null && !Utils.isEmptyOrNull(deviceInfo.sdid.getValue())) {
            put("k", com.singular.sdk.internal.Constants.UPPERCASE_SDID_KEY);
            put(Constants.IDENTIFIER_UNIQUE_ID_KEY, deviceInfo.sdid.getValue());
            putAsidOrAifaIfNotNull(deviceInfo);
        } else if (!Utils.isEmptyOrNull(deviceInfo.amid)) {
            put(Constants.AMID_KEYSPACE_KEY, deviceInfo.amid);
            put("k", Constants.AMID_KEYSPACE_VALUE);
            put(Constants.IDENTIFIER_UNIQUE_ID_KEY, deviceInfo.amid);
            putAsidOrAifaIfNotNull(deviceInfo);
        } else if (!Utils.isEmptyOrNull(deviceInfo.aifa)) {
            put(Constants.AIFA_KEYSPACE_KEY, deviceInfo.aifa);
            put("k", Constants.AIFA_KEYSPACE_VALUE);
            put(Constants.IDENTIFIER_UNIQUE_ID_KEY, deviceInfo.aifa);
        } else if (!Utils.isEmptyOrNull(deviceInfo.oaid)) {
            put("k", Constants.OAID_KEYSPACE_VALUE);
            put(Constants.IDENTIFIER_UNIQUE_ID_KEY, deviceInfo.oaid);
            put(Constants.OAID_KEYSPACE_KEY, deviceInfo.oaid);
            if (!Utils.isEmptyOrNull(deviceInfo.asid)) {
                put(Constants.ASID_KEYSPACE_KEY, deviceInfo.asid);
            }
        } else if (!Utils.isEmptyOrNull(deviceInfo.imei)) {
            put(Constants.IMEI_KEYSPACE_KEY, deviceInfo.imei);
            put("k", Constants.IMEI_KEYSPACE_VALUE);
            put(Constants.IDENTIFIER_UNIQUE_ID_KEY, deviceInfo.imei);
        } else if (!Utils.isEmptyOrNull(deviceInfo.asid)) {
            put("k", Constants.ASID_KEYSPACE_VALUE);
            put(Constants.IDENTIFIER_UNIQUE_ID_KEY, deviceInfo.asid);
            put(Constants.ASID_KEYSPACE_KEY, deviceInfo.asid);
        } else if (!Utils.isEmptyOrNull(deviceInfo.andi)) {
            put("k", Constants.ANDI_KEYSPACE_VALUE);
            put(Constants.IDENTIFIER_UNIQUE_ID_KEY, deviceInfo.andi);
            put(Constants.ANDI_KEYSPACE_KEY, deviceInfo.andi);
        }
        return this;
    }

    private void putAsidOrAifaIfNotNull(DeviceInfo deviceInfo) {
        if (!Utils.isEmptyOrNull(deviceInfo.aifa)) {
            put(Constants.AIFA_KEYSPACE_KEY, deviceInfo.aifa);
        } else {
            if (Utils.isEmptyOrNull(deviceInfo.asid)) {
                return;
            }
            put(Constants.ASID_KEYSPACE_KEY, deviceInfo.asid);
        }
    }
}
