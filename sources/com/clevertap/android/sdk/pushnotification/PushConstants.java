package com.clevertap.android.sdk.pushnotification;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes5.dex */
public interface PushConstants {
    public static final String ADM_DELIVERY_TYPE = "adm";
    public static final String ADM_PROPERTY_REG_ID = "adm_token";
    public static final String ADM_SDK_CLASS = "com.amazon.device.messaging.ADM";
    public static final int AMAZON_PLATFORM = 2;
    public static final int ANDROID_PLATFORM = 1;
    public static final String BAIDU_DELIVERY_TYPE = "bps";
    public static final String BAIDU_SDK_CLASS = "com.baidu.android.pushservice.PushMessageReceiver";
    public static final String BPS_PROPERTY_REG_ID = "bps_token";
    public static final String CT_ADM_PROVIDER_CLASS = "com.clevertap.android.adm.AmazonPushProvider";
    public static final String CT_BAIDU_PROVIDER_CLASS = "com.clevertap.android.bps.BaiduPushProvider";
    public static final String CT_FIREBASE_PROVIDER_CLASS = "com.clevertap.android.sdk.pushnotification.fcm.FcmPushProvider";
    public static final String CT_HUAWEI_PROVIDER_CLASS = "com.clevertap.android.hms.HmsPushProvider";
    public static final String FCM_DELIVERY_TYPE = "fcm";
    public static final String FCM_LOG_TAG = PushType.FCM.toString();
    public static final String FCM_PROPERTY_REG_ID = "fcm_token";
    public static final String FIREBASE_SDK_CLASS = "com.google.firebase.messaging.FirebaseMessagingService";
    public static final String HMS_DELIVERY_TYPE = "hps";
    public static final String HPS_PROPERTY_REG_ID = "hps_token";
    public static final String HUAWEI_SDK_CLASS = "com.huawei.hms.push.HmsMessageService";
    public static final String LOG_TAG = "PushProvider";

    @Retention(RetentionPolicy.SOURCE)
    public @interface CTPushProviderClass {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface DeliveryType {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Platform {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface PushMessagingClass {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface RegKeyType {
    }

    public enum PushType {
        FCM("fcm", PushConstants.FCM_PROPERTY_REG_ID, PushConstants.CT_FIREBASE_PROVIDER_CLASS, PushConstants.FIREBASE_SDK_CLASS),
        HPS(PushConstants.HMS_DELIVERY_TYPE, PushConstants.HPS_PROPERTY_REG_ID, PushConstants.CT_HUAWEI_PROVIDER_CLASS, PushConstants.HUAWEI_SDK_CLASS),
        BPS(PushConstants.BAIDU_DELIVERY_TYPE, PushConstants.BPS_PROPERTY_REG_ID, PushConstants.CT_BAIDU_PROVIDER_CLASS, PushConstants.BAIDU_SDK_CLASS),
        ADM(PushConstants.ADM_DELIVERY_TYPE, PushConstants.ADM_PROPERTY_REG_ID, PushConstants.CT_ADM_PROVIDER_CLASS, PushConstants.ADM_SDK_CLASS);

        private final String ctProviderClassName;
        private final String messagingSDKClassName;
        private final String tokenPrefKey;
        private final String type;

        public String getCtProviderClassName() {
            return this.ctProviderClassName;
        }

        public String getMessagingSDKClassName() {
            return this.messagingSDKClassName;
        }

        public String getTokenPrefKey() {
            return this.tokenPrefKey;
        }

        public String getType() {
            return this.type;
        }

        PushType(String str, String str2, String str3, String str4) {
            this.type = str;
            this.tokenPrefKey = str2;
            this.ctProviderClassName = str3;
            this.messagingSDKClassName = str4;
        }

        @Override // java.lang.Enum
        public String toString() {
            return " [PushType:" + name() + "] ";
        }
    }
}
